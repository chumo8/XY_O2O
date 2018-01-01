package com.xyo2o.dao.user;

import java.util.List;

import com.xyo2o.domain.user.Info;
import com.xyo2o.domain.user.User;
import com.xyo2o.domain.user.Wallet;
import com.xyo2o.dto.user.UserQueryPageDTO;
import org.apache.ibatis.annotations.Param;


public interface UserDAO {

	//获取总记录数
	int getCount(UserQueryPageDTO dto);
	//高级查询加分页
	List<User> queryUser(UserQueryPageDTO dto);
	//添加用户
	int insertUser(User user);
	//添加钱包表
	void insertWallet(Wallet wallet);
	//添加用户信息表
	void insertInfo(Info info);
	//验证昵称是否存在
	User testNickName(@Param("nickName") String nickname);
	//验证用户名是否存在
	User testUserName(@Param("userName") String userName);
	//根据ID获取user
	User findOneById(@Param("id") long id);
	//修改user
	int updateUser(@Param("userId") long userId, @Param("user") User user);
	//修改wallet
	void updateWallet(@Param("walletId") long walletId, @Param("wallet") Wallet wallet);
	//修改info
	void updateInfo(@Param("infoId") long infoId, @Param("info") Info info);
	//根据ID删除user
	int delUserById(List<Integer> ids);

}
