package com.xyo2o.dao.store;

import com.xyo2o.domain.store.Store;

import java.util.List;


public interface StoreDAO {

	List<Store> findAll();

	List<Store> getStore();
	
}
