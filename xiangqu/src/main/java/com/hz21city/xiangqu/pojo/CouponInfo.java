package com.hz21city.xiangqu.pojo;

import java.util.Date;

public class CouponInfo {

	private Long id;
	private Float money;// 优惠金额
	private Float moneyLimit;// 满多少使用
	private Date validity;// 截止时间
	private Long storeId;// 关联商店
	private Integer type;// 类型 1实物券 2 抵扣券
	private String intro;// 简介
	private String title;// 标题
	private Integer numLimit;// 限领张数
	private Date createTime;// 创建时间
	private String code;// 兑换码
	private Integer total;// 总数
	private Integer getNum;// 领取数量
	private Integer useNum;// 使用数量
	private Integer shareIncome;// 分享收益
	private Integer useIncome;// 使用收益
	private String storename;
	private float allIncome;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Float getMoney() {
		return money;
	}

	public void setMoney(Float money) {
		this.money = money;
	}

	public Float getMoneyLimit() {
		return moneyLimit;
	}

	public void setMoneyLimit(Float moneyLimit) {
		this.moneyLimit = moneyLimit;
	}

	public Date getValidity() {
		return validity;
	}

	public void setValidity(Date validity) {
		this.validity = validity;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro == null ? null : intro.trim();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public Integer getNumLimit() {
		return numLimit;
	}

	public void setNumLimit(Integer numLimit) {
		this.numLimit = numLimit;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code == null ? null : code.trim();
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getGetNum() {
		return getNum;
	}

	public void setGetNum(Integer getNum) {
		this.getNum = getNum;
	}

	public Integer getUseNum() {
		return useNum;
	}

	public void setUseNum(Integer useNum) {
		this.useNum = useNum;
	}

	public Integer getShareIncome() {
		return shareIncome;
	}

	public void setShareIncome(Integer shareIncome) {
		this.shareIncome = shareIncome;
	}

	public Integer getUseIncome() {
		return useIncome;
	}

	public void setUseIncome(Integer useIncome) {
		this.useIncome = useIncome;
	}

	public String getStorename() {
		return storename;
	}

	public void setStorename(String storename) {
		this.storename = storename;
	}

	public float getAllIncome() {
		return allIncome;
	}

	public void setAllIncome(float allIncome) {
		this.allIncome = allIncome;
	}
}