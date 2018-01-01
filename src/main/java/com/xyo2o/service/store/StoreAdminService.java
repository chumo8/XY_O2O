package com.xyo2o.service.store;

import com.xyo2o.domain.store.StoreAdmin;
import com.xyo2o.dto.store.StoreAdminQueryPageDTO;

import java.util.List;

public interface StoreAdminService {

	List<StoreAdmin> query(StoreAdminQueryPageDTO dto);

	List<StoreAdmin> findAll();
}
