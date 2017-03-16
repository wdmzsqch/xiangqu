package com.hz21city.xiangqu.pojo;

import java.util.Date;

public class CheckInfo {
    private Long id;//主键

    private Integer type;//1任务 2商品 3活动 4充值审核（只需财务和最终审核）

    private Long relativeId;//关联id

    private Integer checkType;//1内容审核 2技术确认 3财务确认 4最终审核

    private String comment;//备注

    private String adminAccount;//审核用户名

    private Date checkTime;//审核时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRelativeId() {
        return relativeId;
    }

    public void setRelativeId(Long relativeId) {
        this.relativeId = relativeId;
    }

    public Integer getCheckType() {
        return checkType;
    }

    public void setCheckType(Integer checkType) {
        this.checkType = checkType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public String getAdminAccount() {
        return adminAccount;
    }

    public void setAdminAccount(String adminAccount) {
        this.adminAccount = adminAccount == null ? null : adminAccount.trim();
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}