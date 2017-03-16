package com.hz21city.xiangqu.pojo;

import java.util.Date;

public class MissionInfo {

	private Long id;
	private String name;// 任务名
	private Integer totalTimes;// 投放次数
	private Integer ramianTimes;// 剩余次数（产生收益时减）
	private Float income;// 收益
	private Date startDate;// 开始时间
	private Date endTime;// 结束时间
	private Integer state;// 状态 1未开始2进行中3分享上限（结束）4结束
	private Date publishTime;// 发布时间./
	private String detailUrl;// 详情页链接
	private Integer delFlg;// 逻辑删 1删 0未删
	private String pic;
	private Integer online;// 上下线 1下线 0上线
	private String intro;// 简介
	private Integer showTimes;// 前段显示投放数
	private Long industryId;
	private Integer sort;
	private Long createUserId;
	private Integer incomeTime;// 可以获取收益的次数
	/** 非数据库字段 **/
	private int isShare;// 已分享 0未分享 1已分享
	private String areaname;
	private int count;
	private Float allIncome;
	private String cityname;
	// --------------------------
	private String contractNo;// 合同编号
	private Long area;// 投放范围
	private Long city; // 市
	private Long province; // 省
	private Long shopId;// 所属商家
	private Long cotegoryId;// 所属任务类型
	private Float totalMoney;// 投放总金额
	private Date updateTime;
	// ----------审核
	private Integer checkStatus;// 内容审核 1通过 0不通过
	private Integer checkStatusY;// 技术审核 1通过 0不通过
	private Integer checkStatusC;// 财务审核 1通过 0不通过
	private Integer checkStatusB;// 最终审核 1通过 0不通过
	private String comment;
	private String comment1;
	private String comment2;
	private String comment3;
	private Integer isCheck;// 0未审 1已审
	// --------------------------
	private String shopName;
	private Integer overtime;
	private String weekOfDate;
	// -----------------------
	private Integer brushNum;

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

	public Integer getTotalTimes() {
		return totalTimes;
	}

	public void setTotalTimes(Integer totalTimes) {
		this.totalTimes = totalTimes;
	}

	public Integer getRamianTimes() {
		return ramianTimes;
	}

	public void setRamianTimes(Integer ramianTimes) {
		this.ramianTimes = ramianTimes;
	}

	public Float getIncome() {
		return income;
	}

	public void setIncome(Float income) {
		this.income = income;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public String getDetailUrl() {
		return detailUrl;
	}

	public void setDetailUrl(String detailUrl) {
		this.detailUrl = detailUrl == null ? null : detailUrl.trim();
	}

	public Integer getDelFlg() {
		return delFlg;
	}

	public void setDelFlg(Integer delFlg) {
		this.delFlg = delFlg;
	}

	public int getIsShare() {
		return isShare;
	}

	public void setIsShare(int isShare) {
		this.isShare = isShare;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo == null ? null : contractNo.trim();
	}

	public Long getArea() {
		return area;
	}

	public void setArea(Long area) {
		this.area = area;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public Float getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Float totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Long getCotegoryId() {
		return cotegoryId;
	}

	public void setCotegoryId(Long cotegoryId) {
		this.cotegoryId = cotegoryId;
	}

	public Long getCity() {
		return city;
	}

	public void setCity(Long city) {
		this.city = city;
	}

	public Long getProvince() {
		return province;
	}

	public void setProvince(Long province) {
		this.province = province;
	}

	public Integer getOvertime() {
		return overtime;
	}

	public void setOvertime(Integer overtime) {
		this.overtime = overtime;
	}

	public Integer getOnline() {
		return online;
	}

	public void setOnline(Integer online) {
		this.online = online;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Float getAllIncome() {
		return allIncome;
	}

	public void setAllIncome(Float allIncome) {
		this.allIncome = allIncome;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public String getWeekOfDate() {
		return weekOfDate;
	}

	public void setWeekOfDate(String weekOfDate) {
		this.weekOfDate = weekOfDate;
	}

	public Integer getShowTimes() {
		return showTimes;
	}

	public void setShowTimes(Integer showTimes) {
		this.showTimes = showTimes;
	}

	public Long getIndustryId() {
		return industryId;
	}

	public void setIndustryId(Long industryId) {
		this.industryId = industryId;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
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

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
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

	public Integer getBrushNum() {
		return brushNum;
	}

	public void setBrushNum(Integer brushNum) {
		this.brushNum = brushNum;
	}

	public Integer getIncomeTime() {
		return incomeTime;
	}

	public void setIncomeTime(Integer incomeTime) {
		this.incomeTime = incomeTime;
	}
}