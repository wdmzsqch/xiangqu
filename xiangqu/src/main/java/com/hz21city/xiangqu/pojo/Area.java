package com.hz21city.xiangqu.pojo;

import java.util.List;

public class Area {
    private Long id;

    private String name;//名字

    private Long parentId;//上级id
    
    private List<Area> citylist;
    
    private int citysize;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

	public List<Area> getCitylist() {
		return citylist;
	}

	public void setCitylist(List<Area> citylist) {
		this.citylist = citylist;
	}

	public int getCitysize() {
		return citysize;
	}

	public void setCitysize(int citysize) {
		this.citysize = citysize;
	}
}