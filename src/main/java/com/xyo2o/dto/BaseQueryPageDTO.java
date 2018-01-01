package com.xyo2o.dto;

import java.util.ArrayList;
import java.util.List;

public class BaseQueryPageDTO<T> {
	private List<T> rows = new ArrayList<>();
	private int currentPage = 1;//当前页码
	private int total;//结果集
	private int totalPage;//总页
	private int pageSize = 10;
	private int beginIndex; //从第几页开始
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getBeginIndex() {
		this.beginIndex = (getCurrentPage()-1)*getPageSize();
		return beginIndex;
	}
	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setList(List<T> list) {
		this.rows = list;
	}
	public int getCurrentPage() {
		if (this.currentPage < 0) {
			this.currentPage = 1;
		}
		
		if (this.getTotalPage() > 0 && this.currentPage > this.getTotalPage()) {
			this.currentPage = this.getTotalPage();
		}
		
		return this.currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getTotalPage() {
		this.totalPage = this.total%this.pageSize==0?this.total/this.pageSize:this.total/this.pageSize+1;
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	@Override
	public String toString() {
		return "BaseQueryPageDTO [rows=" + rows + ", currentPage="
				+ currentPage + ", total=" + total + ", totalPage=" + totalPage
				+ ", pageSize=" + pageSize + ", beginIndex=" + beginIndex + "]";
	}
}
