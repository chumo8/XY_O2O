package com.xyo2o.dao.admin;

import com.xyo2o.dao.BaseDao;
import com.xyo2o.domain.admin.Admin;

/**
 * 管理员DAO
 * @author Hu
 *
 */
public interface AdminDAO extends BaseDao<Admin> {
	/**
	 * 根据用户名查询 单个Admin
	 * @param username
	 * @return
	 */
	Admin selectOneByUsername(String username);//根据用户名获取单个Admin信息
	/**
	 * 根据Admin ID 修改密码
	 * @param admin
	 * @return
	 */
	int updatePassword(Admin admin);
	/**
	 * 根据Admin ID修改账号状态
	 * @param admin
	 * @return
	 */
	int updateStateById(Admin admin);


}
