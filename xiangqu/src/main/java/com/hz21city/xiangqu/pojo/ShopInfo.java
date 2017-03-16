package com.hz21city.xiangqu.pojo;

import java.util.Date;
import java.util.List;

public class ShopInfo {
	private Long id;
	private String userName;// 登录商户端用户名
	private String companyName;// 商户名
	private String legal;// 法人
	private String businessLicense;// 营业执照
	private String address;// 地址
	private Date updateTime;// 更新时间
	private String password;// 密码（md5加密）
	private String phone;// 电话
	private String pic;// 头像
	private Integer delFlg;// 0未删 1已删
	private Long area; // 区
	private Long city; // 市
	private Long province; // 省
	private Long industry_id;
	private String proname;
	private String cityname;
	private String areaname;
	private String mobile;//手机
	private String client_id;

	private List<CouponInfo> couponlist;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName == null ? null : companyName.trim();
	}

	public String getLegal() {
		return legal;
	}

	public void setLegal(String legal) {
		this.legal = legal == null ? null : legal.trim();
	}

	public String getBusinessLicense() {
		return businessLicense;
	}

	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense == null ? null : businessLicense.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
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

	public Long getArea() {
		return area;
	}

	public void setArea(Long area) {
		this.area = area;
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

	public String getProname() {
		return proname;
	}

	public void setProname(String proname) {
		this.proname = proname;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	public Long getIndustry_id() {
		return industry_id;
	}

	public void setIndustry_id(Long industry_id) {
		this.industry_id = industry_id;
	}

	public List<CouponInfo> getCouponlist() {
		return couponlist;
	}

	public void setCouponlist(List<CouponInfo> couponlist) {
		this.couponlist = couponlist;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	
}