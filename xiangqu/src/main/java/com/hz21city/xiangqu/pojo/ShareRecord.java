package com.hz21city.xiangqu.pojo;

import java.util.Date;

public class ShareRecord {
    private Long id;

    private Long userId;//分享人id

    private Long relativeId;//分享的内容（任务或商品）id

    private Integer destination;//分享平台，暂时不用

    private Date shareTime;//分享时间

    private Integer type;//分享类型 1任务 2商品

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

    public Integer getDestination() {
        return destination;
    }

    public void setDestination(Integer destination) {
        this.destination = destination;
    }

    public Date getShareTime() {
        return shareTime;
    }

    public void setShareTime(Date shareTime) {
        this.shareTime = shareTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}