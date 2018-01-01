package com.xyo2o.dto.store;

import com.xyo2o.domain.store.StoreAdmin;
import com.xyo2o.dto.BaseQueryPageDTO;

public class StoreAdminQueryPageDTO extends BaseQueryPageDTO<StoreAdmin> {
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "StoreAdminQueryPageDTO [username=" + username + "]";
	}
	
}
