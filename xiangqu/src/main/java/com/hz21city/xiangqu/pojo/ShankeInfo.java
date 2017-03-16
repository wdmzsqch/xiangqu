package com.hz21city.xiangqu.pojo;

import java.util.Date;

/**
 * 摇一摇
 * @author shenyj
 *
 */
public class ShankeInfo {

	private Long id;

    private Long userId;//用户id

    private Integer amCount;//上午摇的次数 >3不再再摇

    private Integer pmCount;//下午摇的次数 >3不能再摇

    private Integer point;//摇到的积分

    private Integer dayTimes;//第几天

    private Date updateTime;//更新日期

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

    public Integer getAmCount() {
        return amCount;
    }

    public void setAmCount(Integer amCount) {
        this.amCount = amCount;
    }

    public Integer getPmCount() {
        return pmCount;
    }

    public void setPmCount(Integer pmCount) {
        this.pmCount = pmCount;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getDayTimes() {
        return dayTimes;
    }

    public void setDayTimes(Integer dayTimes) {
        this.dayTimes = dayTimes;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}