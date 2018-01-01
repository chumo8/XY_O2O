package com.xyo2o.domain.bbs;

import java.util.Date;
/**
 * bbs论坛主贴的模型
 * @author Chen
 *
 */
public class MainNote {
	private long id;
	private String title;
	private String content;
	private Date time;
	private int number;
	private long user_id;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	@Override
	public String toString() {
		return "MainNote [id=" + id + ", title=" + title + ", content="
				+ content + ", time=" + time + ", number=" + number
				+ ", user_id=" + user_id + "]";
	}
	
	
}
