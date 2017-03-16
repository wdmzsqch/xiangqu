package com.hz21city.xiangqu.pojo;

import java.util.Date;

public class HomeActivity {
	private Long id;
	private Long relatived_id;//关联活动或专题的id
	private Integer type;// 活动 1或专题 2
	private String pic;// 图片
	private String valuekey;// homeActivityOne第一个 homeActivityTwo第二个 homeActivityThree第三个 homeActivityFour第四个 homeActivityFive第五个
	private String detailUrl;// 详情外链 专题的时候有
	private Date addTime;// 添加时间
	
	private String activityname;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getRelatived_id() {
		return relatived_id;
	}
	public void setRelatived_id(Long relatived_id) {
		this.relatived_id = relatived_id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getValuekey() {
		return valuekey;
	}
	public void setValuekey(String valuekey) {
		this.valuekey = valuekey;
	}
	public String getDetailUrl() {
		return detailUrl;
	}
	public void setDetailUrl(String detailUrl) {
		this.detailUrl = detailUrl;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public String getActivityname() {
		return activityname;
	}
	public void setActivityname(String activityname) {
		this.activityname = activityname;
	}
	
}