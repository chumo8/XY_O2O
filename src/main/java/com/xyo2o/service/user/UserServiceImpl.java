package com.xyo2o.service.user;

import java.util.List;

import com.xyo2o.dao.user.UserDAO;
import com.xyo2o.domain.user.User;
import com.xyo2o.dto.user.UserQueryPageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    public List<User> findAll(UserQueryPageDTO dto) {
        int count = userDAO.getCount(dto);
        dto.setTotal(count);
        return userDAO.queryUser(dto);
    }

    public int insertUser(User user) {

        //插入wallet表
        userDAO.insertWallet(user.getInfo().getWallet());
        user.getInfo().getWallet().setWalletId(user.getInfo().getWallet().getWalletId());
        //插入info表
        userDAO.insertInfo(user.getInfo());
        user.getInfo().setInfoId(user.getInfo().getInfoId());
        //插入user表
        return userDAO.insertUser(user);
    }
    //验证昵称是否存在
    public User testNickName(String nickname) {
        return userDAO.testNickName(nickname);
    }
    //验证用户名是否存在
    public User testUserName(String username) {
        return userDAO.testUserName(username);
    }
    //根据ID查询user（修改）
    public User findOneById(long id) {
        return userDAO.findOneById(id);
    }
    //修改user
    public int updateUser(User user, User user_update) {

        //修改wallet表
        userDAO.updateWallet(user_update.getInfo().getWallet().getWalletId(),user.getInfo().getWallet());
        //修改info表
        userDAO.updateInfo(user_update.getInfo().getInfoId(),user.getInfo());
        //修改user表
        return userDAO.updateUser(user_update.getUserId(),user);
    }
    //根据ID删除user
    public int delUserById(List<Integer> ids) {
        return userDAO.delUserById(ids);
    }

    @Override
    public User findOneById(Integer id) {
        return userDAO.findOneById(id);
    }

}
