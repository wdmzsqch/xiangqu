package com.hz21city.xiangqu.service.manage;

import java.util.Date;
import java.util.List;

import com.hz21city.xiangqu.pojo.CouponInfo;
import com.hz21city.xiangqu.pojo.UserCoupon;
import com.hz21city.xiangqu.pojo.WinprizeInfo;

public interface IMaCouponService {

	public abstract List<CouponInfo> getCouponListByPage(Integer page, String keywords, Date endDate);

	public abstract int getCouponListSize(String keywords, Date endDate);

	public abstract CouponInfo selectByPrimaryKey(Long id);

	public abstract void updateCouponInfo(CouponInfo couponinfo);

	public abstract void addCouponInfo(CouponInfo couponinfo);

	public abstract Integer getCouponCountByCode(String code);

	public abstract int getAllCouponSize();

	public abstract List<UserCoupon> getUserCouponListByPage(Integer page, Long couponId);

	public abstract int getUserCouponListSize(Long couponId);

	public abstract List<WinprizeInfo> getUserPrizeListByPage(Integer page, String userName, String moblie, Integer state);

	public abstract int getUserPrizeListSize(String userName, String moblie, Integer state);

	public abstract WinprizeInfo getWinprizeInfoById(Long id);

	public abstract void updateWinprizeInfo(WinprizeInfo winprize);

}
