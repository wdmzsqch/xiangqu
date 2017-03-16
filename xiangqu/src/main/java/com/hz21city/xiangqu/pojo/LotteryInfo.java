package com.hz21city.xiangqu.pojo;

import java.util.Date;

/**
 * 抽奖机会
 * @author shenyj
 *
 */
public class LotteryInfo {

	private Long id;

    private Long userId;//用户id

    private Long couponId;//优惠券id

    private Integer state;//有没有抽过奖2 没抽过 1 已抽过

    private Date addtime;//得到机会时间
    
    private CouponInfo couponinfo;

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

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

	public CouponInfo getCouponinfo() {
		return couponinfo;
	}

	public void setCouponinfo(CouponInfo couponinfo) {
		this.couponinfo = couponinfo;
	}
}