package com.hz21city.xiangqu.pojo;

import java.util.Date;

/**
 * 摇一摇记录表
 * @author shenyj
 *
 */
public class ShankeRecordInfo {

	private Long id;

    private Long userId;//用户id

    private Integer point;//积分

    private Date addTime;//添加时间

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

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}