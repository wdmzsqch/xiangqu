package com.hz21city.xiangqu.pojo;

import java.util.Date;

public class IncomeRecord {

	private Long id;
	private Integer type;// 类型 1任务 2商品 3购买商品 4优惠券分享 5优惠券使用 6活动报名收益 7大转盘抽奖 8 邀请他人 9抽奖-10 10被邀请人收益
	//11 推广员下家任务收益 12 推广员下家大转盘收益 13 推广员下家邀请收益 14新用户奖励500积分  15签到 16签到7天奖励
	private Long relativeId;// 关联任务或者商品id （11 12 13的时候保存下家收益）
	private Float income;// 收益（从任务活着商品中取）
	private Date time;// 收益产生的时间
	private Long userId;// 获取收益的用户id
	private Long fromUserId;// 产生收益的用户id
	private Integer goodstype;//1现金 2余额
	private String username;
	private String relativename;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getRelativeId() {
		return relativeId;
	}

	public void setRelativeId(Long relativeId) {
		this.relativeId = relativeId;
	}

	public Float getIncome() {
		return income;
	}

	public void setIncome(Float income) {
		this.income = income;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(Long fromUserId) {
		this.fromUserId = fromUserId;
	}


	public Integer getGoodstype() {
		return goodstype;
	}

	public void setGoodstype(Integer goodstype) {
		this.goodstype = goodstype;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRelativename() {
		return relativename;
	}

	public void setRelativename(String relativename) {
		this.relativename = relativename;
	}
}