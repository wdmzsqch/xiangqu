package com.hz21city.xiangqu.pojo;

import java.util.Date;

public class EventInfo {

	private Long id;
	private String name;// 活动名
	private Date startTime;// 开始时间
	private Date endTime;// 结束时间
	private Date createTime;// 创建时间
	private String intro;// 介绍文本
	private String pic;// 图片
	private Integer delFlg;// 0未删 1已删
	private String detail;// 详情文本
	private Long storeId;// 关联商家
	// ---------
	private Integer income;// 报名收益
	/**
	 * 非数据库字段
	 */
	private Integer joinCount;
	private Integer signCount;
	private String storename;
	private Float allIncome;
	private Integer signLimit;
	//登记
	private Integer checkCount;
	
	//----------审核
	private Integer checkStatus;//内容审核 1通过 0不通过
	private Integer checkStatusY;//技术审核 1通过 0不通过
	private Integer checkStatusC;//财务审核 1通过 0不通过
	private Integer checkStatusB;//最终审核 1通过 0不通过
	private String comment;
	private String comment1;
	private String comment2;
	private String comment3;
	
	private Integer isCheck;//0未审 1已审

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

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro == null ? null : intro.trim();
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic == null ? null : pic.trim();
	}

	public Integer getDelFlg() {
		return delFlg;
	}

	public void setDelFlg(Integer delFlg) {
		this.delFlg = delFlg;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail == null ? null : detail.trim();
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public String getStorename() {
		return storename;
	}

	public void setStorename(String storename) {
		this.storename = storename;
	}

	public Integer getJoinCount() {
		return joinCount;
	}

	public void setJoinCount(Integer joinCount) {
		this.joinCount = joinCount;
	}

	public Integer getSignCount() {
		return signCount;
	}

	public void setSignCount(Integer signCount) {
		this.signCount = signCount;
	}

	public Integer getIncome() {
		return income;
	}

	public void setIncome(Integer income) {
		this.income = income;
	}

	public Float getAllIncome() {
		return allIncome;
	}

	public void setAllIncome(Float allIncome) {
		this.allIncome = allIncome;
	}

	public Integer getSignLimit() {
		return signLimit;
	}

	public void setSignLimit(Integer signLimit) {
		this.signLimit = signLimit;
	}

	public Integer getCheckCount() {
		return checkCount;
	}

	public void setCheckCount(Integer checkCount) {
		this.checkCount = checkCount;
	}

	public Integer getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}

	public Integer getCheckStatusY() {
		return checkStatusY;
	}

	public void setCheckStatusY(Integer checkStatusY) {
		this.checkStatusY = checkStatusY;
	}

	public Integer getCheckStatusC() {
		return checkStatusC;
	}

	public void setCheckStatusC(Integer checkStatusC) {
		this.checkStatusC = checkStatusC;
	}

	public Integer getCheckStatusB() {
		return checkStatusB;
	}

	public void setCheckStatusB(Integer checkStatusB) {
		this.checkStatusB = checkStatusB;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getComment1() {
		return comment1;
	}

	public void setComment1(String comment1) {
		this.comment1 = comment1;
	}

	public String getComment2() {
		return comment2;
	}

	public void setComment2(String comment2) {
		this.comment2 = comment2;
	}

	public String getComment3() {
		return comment3;
	}

	public void setComment3(String comment3) {
		this.comment3 = comment3;
	}

	public Integer getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(Integer isCheck) {
		this.isCheck = isCheck;
	}
}