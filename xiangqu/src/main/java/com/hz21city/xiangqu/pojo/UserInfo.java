package com.hz21city.xiangqu.pojo;

import java.util.Date;

public class UserInfo {

	private Long id;
	private String userName;// 登录名
	private String realName;// 姓名
	private String nickName;// 昵称
	private String moblie;// 手机
	private String gender;// 性别
	private String eMail;// 邮箱
	private String password;// 密码（md5加密）
	private Integer inviteCode;// 邀请码（被邀请用户id）这个不用了
	private Date updateTime;// 上次登录时间
	private String pic;// 头像
	private Float balance;// 余额
	// 第三方QQ绑定
	private String qq_openid;
	// 第三方QQ绑定
	private String weixin_openid;
	private String address; // 地址
	private Long area; // 区
	private Long city; // 市
	private Long province; // 省
	private String client_id;//
	private String invietCode;
	private Long inviteUserId;
	private String proname;
	private String cityname;
	private String areaname;
	private Integer type;// 类型，－1:人造账号 －2或者null 普通用户 0～100超级推广员（的收益比例） //101 可以成为超级推广员的人
	private Integer invietCount;// 总邀请数
	private Integer icount;// 按时间段查询的邀请数
	private Float dailyIncome;// 每日收益
	private Float totalIncome;// 总收益

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

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName == null ? null : realName.trim();
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName == null ? null : nickName.trim();
	}

	public String getMoblie() {
		return moblie;
	}

	public void setMoblie(String moblie) {
		this.moblie = moblie == null ? null : moblie.trim();
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender == null ? null : gender.trim();
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail == null ? null : eMail.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public Integer getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(Integer inviteCode) {
		this.inviteCode = inviteCode;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic == null ? null : pic.trim();
	}

	public Float getBalance() {
		return balance;
	}

	public void setBalance(Float balance) {
		this.balance = balance;
	}

	public String getQq_openid() {
		return qq_openid;
	}

	public void setQq_openid(String qq_openid) {
		this.qq_openid = qq_openid;
	}

	public String getWeixin_openid() {
		return weixin_openid;
	}

	public void setWeixin_openid(String weixin_openid) {
		this.weixin_openid = weixin_openid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public String getInvietCode() {
		return invietCode;
	}

	public void setInvietCode(String invietCode) {
		this.invietCode = invietCode == null ? null : invietCode.trim();
	}

	public Long getInviteUserId() {
		return inviteUserId;
	}

	public void setInviteUserId(Long inviteUserId) {
		this.inviteUserId = inviteUserId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getInvietCount() {
		return invietCount;
	}

	public void setInvietCount(Integer invietCount) {
		this.invietCount = invietCount;
	}

	public Integer getIcount() {
		return icount;
	}

	public void setIcount(Integer icount) {
		this.icount = icount;
	}

	public Float getDailyIncome() {
		return dailyIncome;
	}

	public void setDailyIncome(Float dailyIncome) {
		this.dailyIncome = dailyIncome;
	}

	public Float getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(Float totalIncome) {
		this.totalIncome = totalIncome;
	}
}