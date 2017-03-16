package com.hz21city.xiangqu.pojo;

import java.util.Date;
/**
 * 中奖记录
 * @author shenyj
 *
 */
public class WinprizeInfo {
    private Long id;

    private Long userid;//用户id

    private String prize;//奖品

    private Integer state;//是否兑换0没有兑换 1已兑换

    private Date addtime;//中奖记录时间
    
    private Long lotteryid;
    
    private UserInfo user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize == null ? null : prize.trim();
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

	public Long getLotteryid() {
		return lotteryid;
	}

	public void setLotteryid(Long lotteryid) {
		this.lotteryid = lotteryid;
	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}
}