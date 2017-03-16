package com.hz21city.xiangqu.pojo;

import java.util.Date;

public class UserCoupon {

	private Long id;
	private Long userId;// 用户id
	private Long couponId;// 优惠券id
	private Date getTime;// 领取时间
	private Date useTime;// 使用时间
	private Long inviteUserId;// 邀请人的用户id
	private Integer isUsed;// 0未使用 1已使用
	private String code;// 券码
	private String codePic;// 券码生成的二维码
	// -------------关联查询-----
	private Date validity;// 有效期
	private String intro;// 简介
	private String title;// 标题
	private String shop_name;// 简介
	private String shop_pic;// 标题
	private Long shop_id;// 标题
	private Integer type;// 类型 1实物券 2 抵扣券
	private Float money;// 优惠金额
	private String shop_phone;//店铺电话
	private String shop_address;//店铺地址
	private Integer isover;//是否过期
	private String user_name;
	
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

	public Date getGetTime() {
		return getTime;
	}

	public void setGetTime(Date getTime) {
		this.getTime = getTime;
	}

	public Date getUseTime() {
		return useTime;
	}

	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}

	public Long getInviteUserId() {
		return inviteUserId;
	}

	public void setInviteUserId(Long inviteUserId) {
		this.inviteUserId = inviteUserId;
	}

	public Integer getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(Integer isUsed) {
		this.isUsed = isUsed;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code == null ? null : code.trim();
	}

	public String getCodePic() {
		return codePic;
	}

	public void setCodePic(String codePic) {
		this.codePic = codePic == null ? null : codePic.trim();
	}

	public Date getValidity() {
		return validity;
	}

	public void setValidity(Date validity) {
		this.validity = validity;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShop_name() {
		return shop_name;
	}

	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}

	public String getShop_pic() {
		return shop_pic;
	}

	public void setShop_pic(String shop_pic) {
		this.shop_pic = shop_pic;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Float getMoney() {
		return money;
	}

	public void setMoney(Float money) {
		this.money = money;
	}

	public Long getShop_id() {
		return shop_id;
	}

	public void setShop_id(Long shop_id) {
		this.shop_id = shop_id;
	}

	public String getShop_phone() {
		return shop_phone;
	}

	public void setShop_phone(String shop_phone) {
		this.shop_phone = shop_phone;
	}

	public String getShop_address() {
		return shop_address;
	}

	public void setShop_address(String shop_address) {
		this.shop_address = shop_address;
	}

	public Integer getIsover() {
		return isover;
	}

	public void setIsover(Integer isover) {
		this.isover = isover;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
}