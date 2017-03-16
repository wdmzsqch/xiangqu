package com.hz21city.xiangqu.service;

import java.util.List;

import com.hz21city.xiangqu.pojo.GoodsInfo;

public interface IGoodsService {

	public abstract List<GoodsInfo> getGoodsList(int sort,long categoryId,int goodstype);
	
	public abstract GoodsInfo getGoodsInfo(long goodsid);
	
	public abstract List<GoodsInfo> getAllGoodsList();
	
	public abstract void updateGoodsInfo(GoodsInfo goods);
}
