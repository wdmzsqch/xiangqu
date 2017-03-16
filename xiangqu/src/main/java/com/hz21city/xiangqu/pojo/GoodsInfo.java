package com.hz21city.xiangqu.pojo;

import java.util.List;

public class GoodsInfo {

	private Long id;// 商品id
	private Float backMoney;// 返现价格
	private String category;// 分类
	private Long categoryId;// 分类id
	private Integer delFlg;// 0下架 1上架 -1删除
	private String description;// 详情中的商品介绍
	private String name;// 商品名称
	private Float originPrice;// 原价
	private String mianPic; // 列表图片
	private String detailPic1;// 详细图片集
	private String detailPic2;// 详细图片集
	private String detailPic3;// 详细图片集
	private String detailPic4;// 详细图片集
	private String detailPic5;// 详细图片集
	private Float price;// 单价
	private String property;// 详情中的商品属性(json)
	private Integer stock;// 库存
	private Integer isServe;// 是否为服务型商品 1是0不是2充值商品类型
	private Long storeId;// 店铺id
	private Integer sort;
	private Integer limitNum;
	// -------------------------------------
	private Integer payType;// 支付方式, 0默认，混合支付 1只支持现金（支付宝，微信包含） 2只支持余额
	private String storeName;// 店铺名
	/** 非数据库字段 **/
	private Float allIncome;
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
	private List<GoodsProNetData> prolist;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Float getBackMoney() {
		return backMoney;
	}

	public void setBackMoney(Float backMoney) {
		this.backMoney = backMoney;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category == null ? null : category.trim();
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getDelFlg() {
		return delFlg;
	}

	public void setDelFlg(Integer delFlg) {
		this.delFlg = delFlg;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Float getOriginPrice() {
		return originPrice;
	}

	public void setOriginPrice(Float originPrice) {
		this.originPrice = originPrice;
	}

	public String getMianPic() {
		return mianPic;
	}

	public void setMianPic(String mianPic) {
		this.mianPic = mianPic == null ? null : mianPic.trim();
	}

	public String getDetailPic1() {
		return detailPic1;
	}

	public void setDetailPic1(String detailPic1) {
		this.detailPic1 = detailPic1 == null ? null : detailPic1.trim();
	}

	public String getDetailPic2() {
		return detailPic2;
	}

	public void setDetailPic2(String detailPic2) {
		this.detailPic2 = detailPic2 == null ? null : detailPic2.trim();
	}

	public String getDetailPic3() {
		return detailPic3;
	}

	public void setDetailPic3(String detailPic3) {
		this.detailPic3 = detailPic3 == null ? null : detailPic3.trim();
	}

	public String getDetailPic4() {
		return detailPic4;
	}

	public void setDetailPic4(String detailPic4) {
		this.detailPic4 = detailPic4 == null ? null : detailPic4.trim();
	}

	public String getDetailPic5() {
		return detailPic5;
	}

	public void setDetailPic5(String detailPic5) {
		this.detailPic5 = detailPic5 == null ? null : detailPic5.trim();
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property == null ? null : property.trim();
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName == null ? null : storeName.trim();
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public Integer getIsServe() {
		return isServe;
	}

	public void setIsServe(Integer isServe) {
		this.isServe = isServe;
	}

	public List<GoodsProNetData> getProlist() {
		return prolist;
	}

	public void setProlist(List<GoodsProNetData> prolist) {
		this.prolist = prolist;
	}

	public Float getAllIncome() {
		return allIncome;
	}

	public void setAllIncome(Float allIncome) {
		this.allIncome = allIncome;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
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

	public Integer getLimitNum() {
		return limitNum;
	}

	public void setLimitNum(Integer limitNum) {
		this.limitNum = limitNum;
	}

}