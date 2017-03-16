package com.hz21city.xiangqu.service.manage;

import java.util.List;

import com.hz21city.xiangqu.pojo.AdInfo;

public interface IMaAdService {
	public abstract List<AdInfo> getAdList();

	public abstract void editAd(AdInfo ad);

	public abstract void addAd(AdInfo ad);

	public abstract void delAd(Long adid);

	public abstract List<AdInfo> getAdListByType(Integer type);

}
