package com.hz21city.xiangqu.pojo;

import java.util.Date;
import java.util.List;

public class Order {
	private Long id; // 订单id
	private String orderNo; // 订单编号
	private String comment; // 备注
	private Date orderTime;// 下单时间
	private Date payTime;//支付时间
	private Integer state;// 状态 1待付款 2待发货 3已发货 4已完成
	private Float totalPrice;// 总价
	private Long userId;// 下单用户id
	private Long storeId;// 店铺id
	private Long shareuserId;//分享用户ID
	private String code; //订单码
	private String codepic; //订单二维码图片
	// ---------------收货信息------------------
	private String consignee; // 收货人
	private String phone; // 手机号
	private String address;// 地址
	// ---------------物流信息-----------------
	private String courierNum; // 快递单号
	private String express; // 快递公司
	// ---------------商品信息-----------------
	private List<OrderGoods> goodsList;
	private int goodscount; //订单商品数量
	private String goodsname; //订单商品数量
	private String pic;//订单商品预览：取第一个商品图片
	// ---------------用户信息-----------------
	private String userName; // 用户名
	
	private String storeName;
	
	private String channelCode;
	private List<GoodsProNetData> prolist;
	
	private int paytype;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment == null ? null : comment.trim();
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee == null ? null : consignee.trim();
	}

	public String getCourierNum() {
		return courierNum;
	}

	public void setCourierNum(String courierNum) {
		this.courierNum = courierNum == null ? null : courierNum.trim();
	}

	public String getExpress() {
		return express;
	}

	public void setExpress(String express) {
		this.express = express == null ? null : express.trim();
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<OrderGoods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<OrderGoods> goodsList) {
		this.goodsList = goodsList;
	}

	public int getGoodscount() {
		return goodscount;
	}

	public void setGoodscount(int goodscount) {
		this.goodscount = goodscount;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
	
    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Long getShareuserId() {
		return shareuserId;
	}

	public void setShareuserId(Long shareuserId) {
		this.shareuserId = shareuserId;
	}

	public List<GoodsProNetData> getProlist() {
		return prolist;
	}

	public void setProlist(List<GoodsProNetData> prolist) {
		this.prolist = prolist;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodepic() {
		return codepic;
	}

	public void setCodepic(String codepic) {
		this.codepic = codepic;
	}

	public int getPaytype() {
		return paytype;
	}

	public void setPaytype(int paytype) {
		this.paytype = paytype;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	
}