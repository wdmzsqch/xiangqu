/**
 * 
 */
package com.hz21city.xiangqu.service;

import java.util.Date;
import java.util.List;

import com.hz21city.xiangqu.pojo.CouponInfo;
import com.hz21city.xiangqu.pojo.EventInfo;
import com.hz21city.xiangqu.pojo.GoodsInfo;
import com.hz21city.xiangqu.pojo.IncomeRecord;
import com.hz21city.xiangqu.pojo.MissionInfo;

public interface IEarnService {

	public abstract float getDailyIncome(Long userid);

	public abstract int getDailyMissionCount(Long userid);

	public abstract int getDailyMissionPv(Long userid);

	public abstract int getDailyGoodsCount(Long userid);

	public abstract int getDailyGoodsPurchase(Long userid);

	public abstract float getMonthIncome(Date month, Long userid);

	public abstract int getMonthMissionCount(Date month, Long userid);

	public abstract int getMonthMissionPv(Date month, Long userid);

	public abstract int getMonthGoodsCount(Date month, Long userid);

	public abstract int getMonthGoodsPurchase(Date month, Long userid);

	public abstract float getMyBalance(Long userid);

	public abstract List<MissionInfo> getDailyMission(Long userid);

	public abstract List<MissionInfo> getMonthMission(Date month, Long userid);

	public abstract List<GoodsInfo> getDailyGoods(Long userid);

	public abstract List<GoodsInfo> getMonthGoods(Date month, Long userid);

	public abstract int getDailyCouponShare(Long userid);

	public abstract int getDailyCoupon(Long userid);

	public abstract int getMonthCouponShare(Date month, Long userid);

	public abstract int getMonthCouponUse(Date month, Long userid);

	public abstract List<CouponInfo> getDailyCoupons(Long userid);

	public abstract List<CouponInfo> getMonthCoupons(Date month, Long userid);

	public abstract List<EventInfo> getDailyEvents(Long userid);

	public abstract List<EventInfo> getMonthEvents(Date month, Long userid);

	public abstract float getAllIncome(long userid);

	public abstract List<IncomeRecord> getAllRecordList();

	public abstract String lottery(Integer income, Long userid);

	public abstract List<IncomeRecord> getMyRecordList(Long userid);

	public abstract List<IncomeRecord> getRecordListByUserId(Long combineduserid);

	public abstract void updateRecord(IncomeRecord incomeRecord);

	public abstract List<IncomeRecord> getRecordListByFromUserId(Long combineduserid);

	public abstract void insertIncomeRecord(IncomeRecord incomeRecord);
}
