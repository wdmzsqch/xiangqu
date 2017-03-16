package com.hz21city.xiangqu.pojo;

import java.util.Date;

/**
 * 点位记录
 * @author shenyj
 *
 */
public class MarkAdPoint {

	private Long id;

	//定点id
    private String pointId;

    //添加时间
    private Date addTime;
    
    private String pointname;
    
    //0 吴兴  1太原
    private int areatype;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPointId() {
		return pointId;
	}

	public void setPointId(String pointId) {
		this.pointId = pointId;
	}

	public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

	public String getPointname() {
		return pointname;
	}

	public void setPointname(String pointname) {
		this.pointname = pointname;
	}

	public int getAreatype() {
		return areatype;
	}

	public void setAreatype(int areatype) {
		this.areatype = areatype;
	}

}