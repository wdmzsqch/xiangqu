package com.hz21city.xiangqu.service.shop;

import java.util.HashMap;
import java.util.List;

import com.hz21city.xiangqu.pojo.CouponInfo;
import com.hz21city.xiangqu.pojo.LotteryInfo;

public interface IShCouponService {

	HashMap<String, Object> getCouponByCode(String code, long storeId);
	
	List<CouponInfo> getCouponInfoList(long storeId);
	
	CouponInfo getCouponInfo(long id);

	void insertLotterInfo(LotteryInfo lottery);
}
