package com.hz21city.xiangqu.pojo;

import java.util.Date;

public class AdPoint {
    private Long id;

    //小区名称
    private String plotName;

    //经度
    private Double longitude;

    //纬度
    private Double latitude;

    //广告编号
    private String adPointNum;

    //区域
    private String adArea;

    //广告资源类型
    private String adSourceClass;

    //添加时间
    private Date addTime;
    
    //背景颜色
    private String background;
    
    private int flag;
    
    //0 吴兴  1太原
    private int areatype;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlotName() {
        return plotName;
    }

    public void setPlotName(String plotName) {
        this.plotName = plotName == null ? null : plotName.trim();
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getAdPointNum() {
        return adPointNum;
    }

    public void setAdPointNum(String adPointNum) {
        this.adPointNum = adPointNum == null ? null : adPointNum.trim();
    }

    public String getAdArea() {
        return adArea;
    }

    public void setAdArea(String adArea) {
        this.adArea = adArea == null ? null : adArea.trim();
    }

    public String getAdSourceClass() {
        return adSourceClass;
    }

    public void setAdSourceClass(String adSourceClass) {
        this.adSourceClass = adSourceClass == null ? null : adSourceClass.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public int getAreatype() {
		return areatype;
	}

	public void setAreatype(int areatype) {
		this.areatype = areatype;
	}
}