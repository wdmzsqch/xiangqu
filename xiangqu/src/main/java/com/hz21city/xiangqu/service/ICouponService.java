package com.hz21city.xiangqu.service;

import java.util.List;

import com.hz21city.xiangqu.pojo.CouponInfo;
import com.hz21city.xiangqu.pojo.ShopInfo;
import com.hz21city.xiangqu.pojo.UserCoupon;

public interface ICouponService {

	List<ShopInfo> getShopCouponList();
	
	List<ShopInfo> getSearchShopCouponList(String type,Integer datetype,String shops);

	CouponInfo getCouponInfoById(long id);

	ShopInfo getShopInfoById(long id);

	List<UserCoupon> getMyCoupon(long userid);
	
	int userCouponCount(long userid,long coupon_id);
	
	int getCouponByCode(String code);
	
	void addUserCoupon(UserCoupon usercoupon);
	
	void updateCoupon(CouponInfo coupon);
	
	UserCoupon getUserCouponById(long usercoupon_id);
	
	List<UserCoupon> getMyCouponSearch(Long userid,String type,Integer datetype,Long[] shops);

	CouponInfo getCouponInfoByCode(String code);
}
