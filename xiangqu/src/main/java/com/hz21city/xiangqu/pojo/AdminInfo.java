package com.hz21city.xiangqu.pojo;

import java.util.Date;

/**
 * 
 * 后台管理员表
 *
 */
public class AdminInfo {

	private Long id;
	private String userName;// 登录名
	private String realName;// 姓名
	private Long type;// 类型（角色）1内容审核 2技术确认 3财务确认 4最终审核5管理员6地区管理员7 积分管理员 8内容管理员9地区账号管理员
	private String password;// 密码
	private Date createTime;// 创建时间
	private String mobile; //手机号码
	// ------------------------------------------------------------
	private String roleName;// 角色名（从type映射到Role表）
	// 关联地区id
	private Long areaId;
	// 积分
	private Integer integral;
	
	private Integer leftintegral;

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

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getLeftintegral() {
		return leftintegral;
	}

	public void setLeftintegral(Integer leftintegral) {
		this.leftintegral = leftintegral;
	}
}