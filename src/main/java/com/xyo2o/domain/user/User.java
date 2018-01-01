package com.xyo2o.domain.user;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户模型
 * @author YLJ
 *
 */
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	private long userId; //id 
	private String userName; // 账号
	private String passWord; // 密码
	private String nickName; // 昵称
	private Date registerDate; // 注册时间
	private int state; // 状态 0---离线  1----在线 2---注销  3----删除
	private Info info; // 个人信息
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Info getInfo() {
		return info;
	}
	public void setInfo(Info info) {
		this.info = info;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName
				+ ", passWord=" + passWord + ", nickName=" + nickName
				+ ", registerDate=" + registerDate + ", info=" + info + "]";
	}
	
}
