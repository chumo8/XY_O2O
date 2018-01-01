package com.xyo2o.domain.store;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 夜猫店模型
 * @author YLJ
 *
 */
public class Store implements Serializable{
	private static final long serialVersionUID = 1L;
	private long s_id; // id
	private String s_name; //店铺名称
	private int status; //店铺状态
	private String openDate; // 营业时间
	private String building; // 楼栋 
	private Date registerDate; // 店铺注册时间
	private List<StoreAdmin> storeAdmins;
	public long getS_id() {
		return s_id;
	}
	public void setS_id(long s_id) {
		this.s_id = s_id;
	}
	public String getS_name() {
		return s_name;
	}
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getOpenDate() {
		return openDate;
	}
	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	public List<StoreAdmin> getStoreAdmins() {
		return storeAdmins;
	}
	public void setStoreAdmins(List<StoreAdmin> storeAdmins) {
		this.storeAdmins = storeAdmins;
	}
	@Override
	public String toString() {
		return "Store [s_id=" + s_id + ", s_name=" + s_name + ", status="
				+ status + ", openDate=" + openDate + ", building=" + building
				+ ", registerDate=" + registerDate + ", storeAdmins="
				+ storeAdmins + "]";
	}
	
}
