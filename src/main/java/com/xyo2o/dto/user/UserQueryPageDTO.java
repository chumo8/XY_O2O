package com.xyo2o.dto.user;


import com.xyo2o.domain.user.User;
import com.xyo2o.dto.BaseQueryPageDTO;

public class UserQueryPageDTO extends BaseQueryPageDTO<User> {
	private String username;
	private int sex;
	private int status;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
