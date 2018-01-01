package com.xyo2o.dto.admin;


import com.xyo2o.domain.admin.Admin;
import com.xyo2o.dto.BaseQueryPageDTO;

public class AdminQueryPageDTO extends BaseQueryPageDTO<Admin> {
	private String username; //根据管理员账户
	private String realname;//根据管理员姓名
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	@Override
	public String toString() {
		return "AdminQueryPageDTO [username=" + username + ", realname="
				+ realname + "]";
	}

}
