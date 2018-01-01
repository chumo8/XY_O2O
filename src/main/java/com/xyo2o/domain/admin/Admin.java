package com.xyo2o.domain.admin;

import java.io.Serializable;
import java.util.Date;
/**
 *
 * @author Hu
 *管理员模型
 */
public class Admin implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;//管理员主键ID
	private  String username;//管理员账户
	private String password;//管理员密码
	private String name;//管理员姓名
	private String ip;//管理员 登录IP
	private Date time;//管理员登录时间
	private int state;//管理员状态  0 正常 1 注销
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "Admin [id=" + id + ", username=" + username + ", password="
				+ password + ", name=" + name + ", ip=" + ip + ", time=" + time
				+ ", state=" + state + "]";
	}

}
