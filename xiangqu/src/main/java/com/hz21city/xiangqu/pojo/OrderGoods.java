package com.hz21city.xiangqu.pojo;

import java.util.List;

public class OrderGoods {
	private Long id; // 订单商品id
	private Float backMoney;// 返现价格
	private String description; // 详情中的商品介绍
	private Long goodsId; // 商品id
	private String name;// 商品名称
	private Integer num; // 份数
	private Long orderId; // 订单id
	private Float originPrice; // 原价
	private String pic;// 列表图片
	private Float price;// 单价
	private String property; // 详情中的商品属性(json)
	private Long storeId;// 店铺id
	private Integer isServe;// 是否为服务型商品 1是0不是
	private int paytype;
	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Float getOriginPrice() {
		return originPrice;
	}

	public void setOriginPrice(Float originPrice) {
		this.originPrice = originPrice;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
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

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public int getPaytype() {
		return paytype;
	}

	public void setPaytype(int paytype) {
		this.paytype = paytype;
	}

	public List<GoodsProNetData> getProlist() {
		return prolist;
	}

	public void setProlist(List<GoodsProNetData> prolist) {
		this.prolist = prolist;
	}

	public Integer getIsServe() {
		return isServe;
	}

	public void setIsServe(Integer isServe) {
		this.isServe = isServe;
	}
	
	
}