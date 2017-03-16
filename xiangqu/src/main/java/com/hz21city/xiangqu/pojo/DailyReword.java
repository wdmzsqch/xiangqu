package com.hz21city.xiangqu.pojo;

import java.util.Date;

public class DailyReword {
    private Long id;

    //开始时间
    private Date startTime;

    //结束时间
    private Date endTime;

    //持续天数
    private Integer lasting;

    //收益
    private Float income;

    //名字
    private String name;

    //创建时间
    private Date createTime;

    //页面内容
    private String contentUrl;

    //简介
    private String intro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getLasting() {
        return lasting;
    }

    public void setLasting(Integer lasting) {
        this.lasting = lasting;
    }

    public Float getIncome() {
        return income;
    }

    public void setIncome(Float income) {
        this.income = income;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl == null ? null : contentUrl.trim();
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }
}