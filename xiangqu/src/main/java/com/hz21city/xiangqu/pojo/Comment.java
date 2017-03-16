package com.hz21city.xiangqu.pojo;

import java.util.Date;

public class Comment {

	private Long id;
	private Long userId;
	private Long relativeId;
	private Integer relativeType;
	private Date publishTime;
	private String content;
	// 非数据库字段
	private UserInfo user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRelativeId() {
		return relativeId;
	}

	public void setRelativeId(Long relativeId) {
		this.relativeId = relativeId;
	}

	public Integer getRelativeType() {
		return relativeType;
	}

	public void setRelativeType(Integer relativeType) {
		this.relativeType = relativeType;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}
}