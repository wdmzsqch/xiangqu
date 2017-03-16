package com.hz21city.xiangqu.pojo;

import java.util.Date;

public class NewYearDinner {
    private Long id;

    private String companyName;

    private Date arriveTime;

    private Integer isArrive;

    private String pic;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public Date getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }

    public Integer getIsArrive() {
        return isArrive;
    }

    public void setIsArrive(Integer isArrive) {
        this.isArrive = isArrive;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }
}