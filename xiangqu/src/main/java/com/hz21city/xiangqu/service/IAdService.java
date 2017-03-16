package com.hz21city.xiangqu.service;

import java.util.List;

import com.hz21city.xiangqu.pojo.AdInfo;
import com.hz21city.xiangqu.pojo.HomeActivity;

public interface IAdService {

	public abstract List<AdInfo> getAllAdList();

	public abstract List<AdInfo> getAdListByType(Integer type);
	
	public abstract List<HomeActivity> getHomeActivities();
	
}
