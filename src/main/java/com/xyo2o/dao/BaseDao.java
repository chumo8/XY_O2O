package com.xyo2o.dao;

import java.util.List;

/**
 * ������Dao
 * @author Hu
 *
 */
public interface BaseDao<T> {
	int insert(T t);   //���һ����¼
	int update(T t); //����һ����¼
	int deleteByid(Long id); //ɾ��һ����¼
	T selectOneByid(Long id); //����ID ��ѯһ����¼
	List<T> selectAll();	//��ѯ���м�¼
}
