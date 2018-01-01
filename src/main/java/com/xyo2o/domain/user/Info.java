package com.xyo2o.domain.user;

import java.io.Serializable;

/**
 * 用户信息模型
 * @author YLJ
 *
 */
public class Info implements Serializable{
	private static final long serialVersionUID = 1L;
	private long infoId; // id 
	private String tel; // 电话
	private int sex; // 性别
	private int status; // 类型 0--普通用户  1--会员   3--已删除
	private String avatar; // 头像
	private String email; // 邮件
	private int stu_id; // 学号
	private String name; // 真实姓名
	private Wallet wallet; // 钱包
	public long getInfoId() {
		return infoId;
	}
	public void setInfoId(long infoId) {
		this.infoId = infoId;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
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
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getStu_id() {
		return stu_id;
	}
	public void setStu_id(int stu_id) {
		this.stu_id = stu_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Wallet getWallet() {
		return wallet;
	}
	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
	@Override
	public String toString() {
		return "Info [infoId=" + infoId + ", tel=" + tel + ", sex=" + sex
				+ ", status=" + status + ", avatar=" + avatar + ", email="
				+ email + ", stu_id=" + stu_id + ", name=" + name + ", wallet="
				+ wallet + "]";
	}
	
}
