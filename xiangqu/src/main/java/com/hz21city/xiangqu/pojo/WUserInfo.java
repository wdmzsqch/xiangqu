package com.hz21city.xiangqu.pojo;

public class WUserInfo {
	private Long id;
	
	private String name;
	
	private String phone;
	
	private Long provice;
	
	private Long city;
	
	private Long area;
	
	private String address;
	
	private String mark;
	
	private String proname;
	
	private String cityname;
	
	private String areaname;
	
	private Integer wtcount;//水票总量
	
	private Integer wucount;//已使用水票数量
	
	private Integer wnucount;
	
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public Long getProvice() {
		return provice;
	}

	public void setProvice(Long provice) {
		this.provice = provice;
	}

	public Long getCity() {
		return city;
	}

	public void setCity(Long city) {
		this.city = city;
	}

	public Long getArea() {
		return area;
	}

	public void setArea(Long area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark == null ? null : mark.trim();
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

	public int getWtcount() {
		return wtcount;
	}

	public void setWtcount(int wtcount) {
		this.wtcount = wtcount;
	}

	public int getWucount() {
		return wucount;
	}

	public void setWucount(int wucount) {
		this.wucount = wucount;
	}

	public int getWnucount() {
		return wnucount;
	}

	public void setWnucount(int wnucount) {
		this.wnucount = wnucount;
	}
}