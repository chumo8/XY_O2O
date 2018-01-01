package com.xyo2o.dao.store;

import java.util.List;

import com.xyo2o.domain.store.StoreAdmin;
import com.xyo2o.dto.store.StoreAdminQueryPageDTO;
import org.apache.ibatis.annotations.Param;


public interface StoreAdminDAO {
	//获取总记录数
	int getCount(StoreAdminQueryPageDTO dto);
	//高级查询加分页
	List<StoreAdmin> query(StoreAdminQueryPageDTO dto);
	
	List<StoreAdmin> findAll();
	//添加
	int insert(@Param("a") StoreAdmin admin, @Param("s_id") int store);
	
	int update(@Param("a") StoreAdmin admin, @Param("s_id") int store, @Param("id") int id);
	
	int delAdmin(@Param("id") int id);
	
	StoreAdmin findOne(int id);

}
