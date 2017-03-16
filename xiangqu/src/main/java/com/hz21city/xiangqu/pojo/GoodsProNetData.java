package com.hz21city.xiangqu.pojo;

import java.util.List;

public class GoodsProNetData {

	//属性名称
	private String name;
	
	//属性值，用,分割
	private String value;
	
	private List<String> valuelist;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<String> getValuelist() {
		return valuelist;
	}

	public void setValuelist(List<String> valuelist) {
		this.valuelist = valuelist;
	}
	
}
