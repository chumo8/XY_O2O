package com.xyo2o.domain.store;

import java.io.Serializable;
import java.util.Date;

/**
 * 店铺管理员
 * @author YLJ
 *
 */
public class StoreAdmin implements Serializable{
	private static final long serialVersionUID = 1L;
	private long sa_id; // id
	private String userName; // 店铺管理员账户
	private String passWord; // 店铺管理员密码
	private String nickName; // 店铺管理员昵称
	private Date registerDate; // 注册时间
	private Store store; //店铺
	public long getSa_id() {
		return sa_id;
	}
	public void setSa_id(long sa_id) {
		this.sa_id = sa_id;
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
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	@Override
	public String toString() {
		return "StoreAdmin [sa_id=" + sa_id + ", userName=" + userName
				+ ", passWord=" + passWord + ", nickName=" + nickName
				+ ", registerDate=" + registerDate + ", store=" + store + "]";
	}
}
