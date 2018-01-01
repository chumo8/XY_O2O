package com.xyo2o.domain.bbs;

import java.util.Date;

/**
 * 回帖的模型
 * @author Chen
 *
 */
public class ReplyNote {
	private long id;
	private String content;
	private Date time;
	private long userId;
	private long mainId;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getMainId() {
		return mainId;
	}
	public void setMainId(long mainId) {
		this.mainId = mainId;
	}
	@Override
	public String toString() {
		return "ReplyNote [id=" + id + ", content=" + content + ", time="
				+ time + ", userId=" + userId + ", mainId=" + mainId + "]";
	}
	
}
