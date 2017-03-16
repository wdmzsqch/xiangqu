package com.hz21city.xiangqu.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hz21city.xiangqu.pojo.GoodsInfo;
import com.hz21city.xiangqu.pojo.IncomeRecord;
import com.hz21city.xiangqu.pojo.MissionInfo;

public interface IIncomeRecordDao {

	int deleteByPrimaryKey(Long id);

	int insert(IncomeRecord record);

	int insertSelective(IncomeRecord record);

	IncomeRecord selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(IncomeRecord record);

	int updateByPrimaryKey(IncomeRecord record);

	int incomeCount(@Param("userid") Long userid, @Param("fromuserid") Long fromuserid, @Param("type") Integer type, @Param("relativeid") Integer relativeid);

	// ------------------------------------------------------------------
	public abstract float getIncome(@Param("userid") Long userid, @Param("start") String start, @Param("end") String end);

	public abstract Float getShIncome(@Param("type") Integer type, @Param("relativeid") Integer relativeid);

	public abstract int getMissionCount(@Param("userid") Long userid, @Param("start") String start, @Param("end") String end);

	public abstract int getMissionPv(@Param("userid") Long userid, @Param("start") String start, @Param("end") String end);

	public abstract int getGoodsCount(@Param("userid") Long userid, @Param("start") String start, @Param("end") String end);

	public abstract int getGoodsPurchase(@Param("userid") Long userid, @Param("start") String start, @Param("end") String end);

	public abstract float getMyBalance(Long userid);

	public abstract List<MissionInfo> getMission(@Param("userid") Long userid, @Param("start") String start, @Param("end") String end);

	public abstract List<GoodsInfo> getGoods(@Param("userid") Long userid, @Param("start") String start, @Param("end") String end);
	// ----------------------------------------------------------------------

	public abstract int getIncomRecordCount(@Param("shopId") Long shopId, @Param("start") String start, @Param("end") String end);

	public abstract List<HashMap<String, Object>> getShareIds(@Param("userid") Long userid, @Param("start") String start, @Param("end") String end, @Param("type") int type);

	// 优惠券分享收益
	public abstract int getCouponShare(@Param("userid") Long userid, @Param("start") String start, @Param("end") String end);

	// 优惠券使用收益
	public abstract int getCouponUse(@Param("userid") Long userid, @Param("start") String start, @Param("end") String end);

	public abstract List<HashMap<String, Object>> getCouponShareIds(@Param("userid") Long userid, @Param("start") String start, @Param("end") String end);

	public abstract List<IncomeRecord> getAllRecordList();

	public abstract int getTodayotteryCount(@Param("userid") Long userid, @Param("start") String start, @Param("end") String end);

	List<IncomeRecord> getIncomeRecordListByUSerId(@Param("userid") Long userid);

	List<IncomeRecord> getIncomeRecordListByUser(@Param("userid") Long userid, @Param("type") Integer type);

	List<IncomeRecord> getRecordListByUserId(@Param("userid") Long userid);

	List<IncomeRecord> getRecordListByFromUserId(Long userid);

	void updateCombinegetIncome(@Param("mobileuserid") Long mobileuserid, @Param("combineduserid") Long combineduserid);

	void updateCombinefromIncome(@Param("mobileuserid") Long mobileuserid, @Param("combineduserid") Long combineduserid);

	int getTimeIncomeCount(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("userid") Long userid,@Param("type") Integer type);

	List<IncomeRecord> getIncomeRecordListByUserAndTime(@Param("userid") Long userid, @Param("type") Integer type, @Param("startTime") String startTime, @Param("endTime") String endTime);
}