package com.xyo2o.service.admin;

import java.util.List;

import com.xyo2o.dao.admin.AdminDAO;
import com.xyo2o.domain.admin.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 后台管理员 service
 * @author Hu
 *
 */
@Service 							//改注解是告诉Spring,我这个类是用来处理业务的
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDAO adminDAO;  //管理员DAO
    /**
     * 查询所有的 管理员信息
     * @return
     */
    public List<Admin> selectAll(){
        return adminDAO.selectAll();
    }
    /**
     * 根据用户名查询单个Admin信息
     * @param username
     * @return
     */
    public Admin selectOneByUsername(String username){
        return adminDAO.selectOneByUsername(username);
    }

    /**
     * 根据ID查询单个Admin 信息
     * @param id
     * @return
     */
    public Admin selectOne(Long id){
        return adminDAO.selectOneByid(id);
    }
    /**
     * 添加一个Admin
     * @param admin
     * @return
     */
    public int insert(Admin admin){
        return adminDAO.insert(admin);
    }
    /**
     * 修改Admin
     * @param admin
     * @return
     */
    public int update(Admin admin){
        return adminDAO.update(admin);
    }
    /**
     * 根据Admin ID 修改状态 为 注销
     * @param id
     * @return
     */
    public int delete(Long id){
        return adminDAO.deleteByid(id);
    }
    /**
     * 根据 Admin 信息来修改密码
     * @param admin
     * @return
     */
    public int updatePassword(Admin admin){
        return adminDAO.updatePassword(admin);
    }
    /**
     * 根据 Admin 信息来修改 账户状态 0 正常，1 注销
     * @param admin
     * @return
     */
    public int updateStateById(Admin admin){
        return adminDAO.updateStateById(admin);
    }


}
