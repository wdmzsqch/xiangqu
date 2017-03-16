package com.hz21city.xiangqu.pojo;

import java.util.Date;

public class WOrderInfo {

	private Long id;

    private Long wuserId;//用户id

    private WUserInfo user;
    
    private Date order_time;

    private Integer ortickernum;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWuserId() {
        return wuserId;
    }

    public void setWuserId(Long wuserId) {
        this.wuserId = wuserId;
    }

	public WUserInfo getUser() {
		return user;
	}

	public void setUser(WUserInfo user) {
		this.user = user;
	}

	public Date getOrder_time() {
		return order_time;
	}

	public void setOrder_time(Date order_time) {
		this.order_time = order_time;
	}

	public Integer getOrtickernum() {
		return ortickernum;
	}

	public void setOrtickernum(Integer ortickernum) {
		this.ortickernum = ortickernum;
	}
}