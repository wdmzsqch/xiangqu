/**
 * 
 */
package com.hz21city.xiangqu.serviceImpl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.dao.ICouponInfoDao;
import com.hz21city.xiangqu.dao.IEventInfoDao;
import com.hz21city.xiangqu.dao.IGoodsInfoDao;
import com.hz21city.xiangqu.dao.IIncomeRecordDao;
import com.hz21city.xiangqu.dao.IMissionInfoDao;
import com.hz21city.xiangqu.dao.IUserInfoDao;
import com.hz21city.xiangqu.pojo.CouponInfo;
import com.hz21city.xiangqu.pojo.EventInfo;
import com.hz21city.xiangqu.pojo.GoodsInfo;
import com.hz21city.xiangqu.pojo.IncomeRecord;
import com.hz21city.xiangqu.pojo.MissionInfo;
import com.hz21city.xiangqu.pojo.UserInfo;
import com.hz21city.xiangqu.service.IEarnService;

@Service("earnService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class EarnServiceImpl implements IEarnService {
	@Resource
	private IIncomeRecordDao incomeRecordDao;
	@Resource
	private IMissionInfoDao missionInfoDao;
	@Resource
	private IGoodsInfoDao goodsDao;
	@Resource
	private ICouponInfoDao couponInfoDao;
	@Resource
	private IEventInfoDao eventInfoDao;
	@Resource
	private IUserInfoDao userInfoDao;

	@Override
	public float getDailyIncome(Long userid) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		Date start = c.getTime();
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		Date end = c.getTime();
		return incomeRecordDao.getIncome(userid, CommonUtils.time2Str(start, "yyyy-MM-dd HH:mm:ss"),
				CommonUtils.time2Str(end, "yyyy-MM-dd HH:mm:ss"));
	}

	@Override
	public int getDailyMissionCount(Long userid) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		Date start = c.getTime();
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		Date end = c.getTime();
		return incomeRecordDao.getMissionCount(userid, CommonUtils.time2Str(start, "yyyy-MM-dd HH:mm:ss"),
				CommonUtils.time2Str(end, "yyyy-MM-dd HH:mm:ss"));
	}

	@Override
	public int getDailyMissionPv(Long userid) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		Date start = c.getTime();
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		Date end = c.getTime();
		return incomeRecordDao.getMissionPv(userid, CommonUtils.time2Str(start, "yyyy-MM-dd HH:mm:ss"),
				CommonUtils.time2Str(end, "yyyy-MM-dd HH:mm:ss"));
	}

	@Override
	public int getDailyGoodsCount(Long userid) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		Date start = c.getTime();
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		Date end = c.getTime();
		return incomeRecordDao.getGoodsCount(userid, CommonUtils.time2Str(start, "yyyy-MM-dd HH:mm:ss"),
				CommonUtils.time2Str(end, "yyyy-MM-dd HH:mm:ss"));
	}

	@Override
	public int getDailyGoodsPurchase(Long userid) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		Date start = c.getTime();
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		Date end = c.getTime();
		return incomeRecordDao.getGoodsPurchase(userid, CommonUtils.time2Str(start, "yyyy-MM-dd HH:mm:ss"),
				CommonUtils.time2Str(end, "yyyy-MM-dd HH:mm:ss"));
	}

	@Override
	public float getMonthIncome(Date month, Long userid) {
		Calendar c = Calendar.getInstance();
		c.setTime(month);
		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.add(Calendar.DAY_OF_MONTH, -1);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		Date end = c.getTime();
		return incomeRecordDao.getIncome(userid, CommonUtils.time2Str(month, "yyyy-MM-dd HH:mm:ss"),
				CommonUtils.time2Str(end, "yyyy-MM-dd HH:mm:ss"));
	}

	@Override
	public int getMonthMissionCount(Date month, Long userid) {
		Calendar c = Calendar.getInstance();
		c.setTime(month);
		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.add(Calendar.DAY_OF_MONTH, -1);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		Date end = c.getTime();
		return incomeRecordDao.getMissionCount(userid, CommonUtils.time2Str(month, "yyyy-MM-dd HH:mm:ss"),
				CommonUtils.time2Str(end, "yyyy-MM-dd HH:mm:ss"));
	}

	@Override
	public int getMonthMissionPv(Date month, Long userid) {
		Calendar c = Calendar.getInstance();
		c.setTime(month);
		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.add(Calendar.DAY_OF_MONTH, -1);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		Date end = c.getTime();
		return incomeRecordDao.getMissionPv(userid, CommonUtils.time2Str(month, "yyyy-MM-dd HH:mm:ss"),
				CommonUtils.time2Str(end, "yyyy-MM-dd HH:mm:ss"));
	}

	@Override
	public int getMonthGoodsCount(Date month, Long userid) {
		Calendar c = Calendar.getInstance();
		c.setTime(month);
		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.add(Calendar.DAY_OF_MONTH, -1);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		Date end = c.getTime();
		return incomeRecordDao.getGoodsCount(userid, CommonUtils.time2Str(month, "yyyy-MM-dd HH:mm:ss"),
				CommonUtils.time2Str(end, "yyyy-MM-dd HH:mm:ss"));
	}

	@Override
	public int getMonthGoodsPurchase(Date month, Long userid) {
		Calendar c = Calendar.getInstance();
		c.setTime(month);
		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.add(Calendar.DAY_OF_MONTH, -1);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		Date end = c.getTime();
		return incomeRecordDao.getGoodsPurchase(userid, CommonUtils.time2Str(month, "yyyy-MM-dd HH:mm:ss"),
				CommonUtils.time2Str(end, "yyyy-MM-dd HH:mm:ss"));
	}

	@Override
	public float getMyBalance(Long userid) {
		Float balance = incomeRecordDao.getMyBalance(userid);
		if (balance != null && balance > 0.0f) {
			// balance = 0.0f;
		} else {
			balance = 0.0f;
		}
		return balance.floatValue();
	}

	@Override
	public List<MissionInfo> getDailyMission(Long userid) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		Date start = c.getTime();
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		Date end = c.getTime();
		List<HashMap<String, Object>> missionids = incomeRecordDao.getShareIds(userid, CommonUtils.time2Str(start, "yyyy-MM-dd HH:mm:ss"),
				CommonUtils.time2Str(end, "yyyy-MM-dd HH:mm:ss"), 1);
		List<MissionInfo> missionlist = new ArrayList<MissionInfo>();
		for (HashMap<String, Object> result : missionids) {
			MissionInfo mission = missionInfoDao.getMaMissionDetail(((BigInteger) result.get("relative_id")).longValue());
			if (mission != null) {
				float allincome = ((Double) result.get("income")).floatValue();
				BigDecimal b = new BigDecimal(allincome);
				float allincomescale2 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
				mission.setAllIncome(allincomescale2);
				missionlist.add(mission);
			}
		}
		return missionlist;
	}

	@Override
	public List<MissionInfo> getMonthMission(Date month, Long userid) {
		Calendar c = Calendar.getInstance();
		c.setTime(month);
		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.add(Calendar.DAY_OF_MONTH, -1);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		Date end = c.getTime();
		List<HashMap<String, Object>> missionids = incomeRecordDao.getShareIds(userid, CommonUtils.time2Str(month, "yyyy-MM-dd HH:mm:ss"),
				CommonUtils.time2Str(end, "yyyy-MM-dd HH:mm:ss"), 1);
		List<MissionInfo> missionlist = new ArrayList<MissionInfo>();
		for (HashMap<String, Object> result : missionids) {
			MissionInfo mission = missionInfoDao.getMaMissionDetail(((BigInteger) result.get("relative_id")).longValue());
			if (mission != null) {
				float allincome = ((Double) result.get("income")).floatValue();
				BigDecimal b = new BigDecimal(allincome);
				float allincomescale2 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
				mission.setAllIncome(allincomescale2);
				missionlist.add(mission);
			}
		}
		return missionlist;
	}

	@Override
	public List<GoodsInfo> getDailyGoods(Long userid) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		Date start = c.getTime();
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		Date end = c.getTime();
		List<HashMap<String, Object>> goodsids = incomeRecordDao.getShareIds(userid, CommonUtils.time2Str(start, "yyyy-MM-dd HH:mm:ss"),
				CommonUtils.time2Str(end, "yyyy-MM-dd HH:mm:ss"), 2);
		List<GoodsInfo> goodslist = new ArrayList<GoodsInfo>();
		for (HashMap<String, Object> result : goodsids) {
			GoodsInfo goods = goodsDao.selectByPrimaryKey(((BigInteger) result.get("relative_id")).longValue());
			if (goods != null) {
				float allincome = ((Double) result.get("income")).floatValue();
				BigDecimal b = new BigDecimal(allincome);
				float allincomescale2 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
				goods.setAllIncome(allincomescale2);
				goodslist.add(goods);
			}
		}
		return goodslist;
	}

	@Override
	public List<GoodsInfo> getMonthGoods(Date month, Long userid) {
		Calendar c = Calendar.getInstance();
		c.setTime(month);
		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.add(Calendar.DAY_OF_MONTH, -1);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		Date end = c.getTime();
		List<HashMap<String, Object>> goodsids = incomeRecordDao.getShareIds(userid, CommonUtils.time2Str(month, "yyyy-MM-dd HH:mm:ss"),
				CommonUtils.time2Str(end, "yyyy-MM-dd HH:mm:ss"), 2);
		List<GoodsInfo> goodslist = new ArrayList<GoodsInfo>();
		for (HashMap<String, Object> result : goodsids) {
			GoodsInfo goods = goodsDao.selectByPrimaryKey(((BigInteger) result.get("relative_id")).longValue());
			if (goods != null) {
				float allincome = ((Double) result.get("income")).floatValue();
				BigDecimal b = new BigDecimal(allincome);
				float allincomescale2 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
				goods.setAllIncome(allincomescale2);
				goodslist.add(goods);
			}
		}
		return goodslist;
	}

	@Override
	public int getDailyCouponShare(Long userid) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		Date start = c.getTime();
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		Date end = c.getTime();
		return incomeRecordDao.getCouponShare(userid, CommonUtils.time2Str(start, "yyyy-MM-dd HH:mm:ss"),
				CommonUtils.time2Str(end, "yyyy-MM-dd HH:mm:ss"));
	}

	@Override
	public int getDailyCoupon(Long userid) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		Date start = c.getTime();
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		Date end = c.getTime();
		return incomeRecordDao.getCouponUse(userid, CommonUtils.time2Str(start, "yyyy-MM-dd HH:mm:ss"),
				CommonUtils.time2Str(end, "yyyy-MM-dd HH:mm:ss"));
	}

	@Override
	public int getMonthCouponShare(Date month, Long userid) {
		Calendar c = Calendar.getInstance();
		c.setTime(month);
		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.add(Calendar.DAY_OF_MONTH, -1);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		Date end = c.getTime();
		return incomeRecordDao.getCouponShare(userid, CommonUtils.time2Str(month, "yyyy-MM-dd HH:mm:ss"),
				CommonUtils.time2Str(end, "yyyy-MM-dd HH:mm:ss"));
	}

	@Override
	public int getMonthCouponUse(Date month, Long userid) {
		Calendar c = Calendar.getInstance();
		c.setTime(month);
		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.add(Calendar.DAY_OF_MONTH, -1);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		Date end = c.getTime();
		return incomeRecordDao.getCouponUse(userid, CommonUtils.time2Str(month, "yyyy-MM-dd HH:mm:ss"),
				CommonUtils.time2Str(end, "yyyy-MM-dd HH:mm:ss"));
	}

	@Override
	public List<CouponInfo> getDailyCoupons(Long userid) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		Date start = c.getTime();
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		Date end = c.getTime();
		List<HashMap<String, Object>> coupons = incomeRecordDao.getCouponShareIds(userid, CommonUtils.time2Str(start, "yyyy-MM-dd HH:mm:ss"),
				CommonUtils.time2Str(end, "yyyy-MM-dd HH:mm:ss"));
		List<CouponInfo> couponlist = new ArrayList<CouponInfo>();
		for (HashMap<String, Object> result : coupons) {
			CouponInfo coupon = couponInfoDao.selectByPrimaryKey(((BigInteger) result.get("relative_id")).longValue());
			if (coupon != null) {
				float allincome = ((Double) result.get("income")).floatValue();
				BigDecimal b = new BigDecimal(allincome);
				float allincomescale2 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
				coupon.setAllIncome(allincomescale2);
				couponlist.add(coupon);
			}
		}
		return couponlist;
	}

	@Override
	public List<CouponInfo> getMonthCoupons(Date month, Long userid) {
		Calendar c = Calendar.getInstance();
		c.setTime(month);
		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.add(Calendar.DAY_OF_MONTH, -1);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		Date end = c.getTime();
		List<HashMap<String, Object>> coupons = incomeRecordDao.getCouponShareIds(userid, CommonUtils.time2Str(month, "yyyy-MM-dd HH:mm:ss"),
				CommonUtils.time2Str(end, "yyyy-MM-dd HH:mm:ss"));
		List<CouponInfo> couponlist = new ArrayList<CouponInfo>();
		for (HashMap<String, Object> result : coupons) {
			CouponInfo coupon = couponInfoDao.selectByPrimaryKey(((BigInteger) result.get("relative_id")).longValue());
			if (coupon != null) {
				float allincome = ((Double) result.get("income")).floatValue();
				BigDecimal b = new BigDecimal(allincome);
				float allincomescale2 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
				coupon.setAllIncome(allincomescale2);
				couponlist.add(coupon);
			}
		}
		return couponlist;
	}

	@Override
	public List<EventInfo> getDailyEvents(Long userid) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		Date start = c.getTime();
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		Date end = c.getTime();
		List<HashMap<String, Object>> eventids = incomeRecordDao.getShareIds(userid, CommonUtils.time2Str(start, "yyyy-MM-dd HH:mm:ss"),
				CommonUtils.time2Str(end, "yyyy-MM-dd HH:mm:ss"), 6);
		List<EventInfo> eventlist = new ArrayList<EventInfo>();
		for (HashMap<String, Object> result : eventids) {
			EventInfo event = eventInfoDao.selectByPrimaryKey(((BigInteger) result.get("relative_id")).longValue());
			if (event != null) {
				float allincome = ((Double) result.get("income")).floatValue();
				BigDecimal b = new BigDecimal(allincome);
				float allincomescale2 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
				event.setAllIncome(allincomescale2);
				eventlist.add(event);
			}
		}
		return eventlist;
	}

	@Override
	public List<EventInfo> getMonthEvents(Date month, Long userid) {
		Calendar c = Calendar.getInstance();
		c.setTime(month);
		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.add(Calendar.DAY_OF_MONTH, -1);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		Date end = c.getTime();
		List<HashMap<String, Object>> eventids = incomeRecordDao.getShareIds(userid, CommonUtils.time2Str(month, "yyyy-MM-dd HH:mm:ss"),
				CommonUtils.time2Str(end, "yyyy-MM-dd HH:mm:ss"), 6);
		List<EventInfo> eventlist = new ArrayList<EventInfo>();
		for (HashMap<String, Object> result : eventids) {
			EventInfo event = eventInfoDao.selectByPrimaryKey(((BigInteger) result.get("relative_id")).longValue());
			if (event != null) {
				float allincome = ((Double) result.get("income")).floatValue();
				BigDecimal b = new BigDecimal(allincome);
				float allincomescale2 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
				event.setAllIncome(allincomescale2);
				eventlist.add(event);
			}
		}
		return eventlist;
	}

	@Override
	public float getAllIncome(long userid) {
		return incomeRecordDao.getIncome(userid, null, null);
	}

	@Override
	public List<IncomeRecord> getAllRecordList() {
		List<IncomeRecord> list = incomeRecordDao.getAllRecordList();
		if (list != null && list.size() > 0) {
			for (IncomeRecord incomeRecord : list) {
				if (incomeRecord.getUserId() != null && incomeRecord.getUserId() > 0) {
					UserInfo userinfo = userInfoDao.selectByPrimaryKey(incomeRecord.getUserId());
					if (userinfo != null) {
						if (!CommonUtils.isEmptyString(userinfo.getNickName())) {
							incomeRecord.setUsername(userinfo.getNickName());
						} else if (!CommonUtils.isEmptyString(userinfo.getRealName())) {
							incomeRecord.setUsername(userinfo.getRealName());
						} else {
							incomeRecord.setUsername(userinfo.getUserName());
						}
					}
					if (incomeRecord.getType() == 1) {// 任务
						MissionInfo missionInfo = missionInfoDao.selectByPrimaryKey(incomeRecord.getRelativeId());
						if (missionInfo != null) {
							incomeRecord.setRelativename(String.valueOf(missionInfo.getIncome()));
						}
					} else if (incomeRecord.getType() == 2) {// 分享商品 或 购买商品
						GoodsInfo goodsInfo = goodsDao.selectByPrimaryKey(incomeRecord.getRelativeId());
						if (goodsInfo != null) {
							incomeRecord.setRelativename(goodsInfo.getName());
						}
					} else if (incomeRecord.getType() == 3) {// 购买商品
						GoodsInfo goodsInfo = goodsDao.selectByPrimaryKey(incomeRecord.getRelativeId());
						if (goodsInfo != null) {
							incomeRecord.setRelativename(goodsInfo.getName());
							if (goodsInfo.getPayType() == 1) {
								incomeRecord.setGoodstype(1);
							} else {
								incomeRecord.setGoodstype(2);
							}
						}
					} else if (incomeRecord.getType() == 4 || incomeRecord.getType() == 5) {// 优惠券分享
																							// 或
																							// 优惠券使用
						CouponInfo couponInfo = couponInfoDao.selectByPrimaryKey(incomeRecord.getRelativeId());
						if (couponInfo != null) {
							incomeRecord.setRelativename(couponInfo.getTitle());
						}
					} else if (incomeRecord.getType() == 6) {
						EventInfo eventInfo = eventInfoDao.selectByPrimaryKey(incomeRecord.getRelativeId());
						if (eventInfo != null) {
							incomeRecord.setRelativename(eventInfo.getName());
						}
					} else if (incomeRecord.getType() == 8) {
						UserInfo userinfo2 = userInfoDao.selectByPrimaryKey(incomeRecord.getFromUserId());
						if (userinfo2 != null) {
							if (!CommonUtils.isEmptyString(userinfo2.getNickName())) {
								incomeRecord.setRelativename(userinfo2.getNickName());
							} else if (!CommonUtils.isEmptyString(userinfo2.getRealName())) {
								incomeRecord.setRelativename(userinfo2.getRealName());
							} else {
								incomeRecord.setRelativename(userinfo2.getUserName());
							}
						}
					}
				}
			}
		}
		return list;
	}

	@Override
	public synchronized String lottery(Integer income, Long userid) {
		UserInfo userInfo = userInfoDao.selectByPrimaryKey(userid);
		if (userInfo == null) {
			return "用户信息有误";
		}
		String today = CommonUtils.getTimeFormat(new Date(), "yyyy-MM-dd ");
		int hasGet = incomeRecordDao.getTodayotteryCount(userid, today + "00:00:00", today + "23:59:00");
		float finalIncome = income.floatValue();
		if (hasGet > 0) {
			if (userInfo.getBalance() < 10) {
				return "你的积分不足";
			} else {
				// -10分抽奖分
				IncomeRecord incomeRecord2 = new IncomeRecord();
				incomeRecord2.setIncome(-10.0f);
				incomeRecord2.setTime(new Date());
				incomeRecord2.setType(9);
				incomeRecord2.setUserId(userid);
				incomeRecord2.setFromUserId(0l);
				incomeRecordDao.insertSelective(incomeRecord2);
				finalIncome = finalIncome - 10;
				// userInfo.setBalance(userInfo.getBalance() +
				// income.floatValue() - 10.0f);
				// userInfoDao.updateByPrimaryKeySelective(userInfo);
			}
		}
		// 中奖+抽奖分
		// IncomeRecord incomeRecord = new IncomeRecord();
		// incomeRecord.setIncome(income.floatValue());
		// incomeRecord.setTime(new Date());
		// incomeRecord.setType(7);
		// incomeRecord.setUserId(userid);
		// incomeRecord.setFromUserId(0l);
		// incomeRecordDao.insertSelective(incomeRecord);
		// 中奖+抽奖分
		IncomeRecord incomeRecord = new IncomeRecord();
		incomeRecord.setIncome(income.floatValue());
		incomeRecord.setTime(new Date());
		incomeRecord.setType(7);
		incomeRecord.setUserId(userid);
		incomeRecord.setFromUserId(0l);
		incomeRecordDao.insertSelective(incomeRecord);
		userInfo.setBalance(userInfo.getBalance() + finalIncome);
		userInfoDao.updateByPrimaryKeySelective(userInfo);
		// 给推广员加积分
		if (income > 0 && userInfo.getInviteUserId() != null && userInfo.getInviteUserId() > 0) {
			UserInfo promoter = userInfoDao.selectByPrimaryKey(userInfo.getInviteUserId());
			if (promoter.getType() != null && promoter.getType() > 0) {
				Integer incomeP = Math.round(income.floatValue() * promoter.getType() / 100.0f);
				IncomeRecord incomerecordP = new IncomeRecord();
				incomerecordP.setFromUserId(userid);
				incomerecordP.setUserId(promoter.getId());
				incomerecordP.setType(12);
				incomerecordP.setRelativeId(income.longValue());// 设成下家获得的积分
				incomerecordP.setIncome(incomeP.floatValue());
				incomerecordP.setTime(new Date());
				incomeRecordDao.insertSelective(incomerecordP);
				promoter.setBalance(promoter.getBalance() + incomeP);
				userInfoDao.updateByPrimaryKey(promoter);
			}
		}
		return null;
	}

	@Override
	public List<IncomeRecord> getMyRecordList(Long userid) {
		List<IncomeRecord> list = incomeRecordDao.getIncomeRecordListByUSerId(userid);
		if (list != null && list.size() > 0) {
			for (IncomeRecord incomeRecord : list) {
				if (incomeRecord.getUserId() != null && incomeRecord.getUserId() > 0) {
					UserInfo userinfo = userInfoDao.selectByPrimaryKey(incomeRecord.getUserId());
					if (userinfo != null) {
						incomeRecord.setUsername(userinfo.getNickName());
					}
					if (incomeRecord.getType() == 1) {// 任务
						MissionInfo missionInfo = missionInfoDao.selectByPrimaryKey(incomeRecord.getRelativeId());
						if (missionInfo != null) {
							incomeRecord.setRelativename(String.valueOf(missionInfo.getIncome()));
						}
					} else if (incomeRecord.getType() == 2) {// 分享商品
						GoodsInfo goodsInfo = goodsDao.selectByPrimaryKey(incomeRecord.getRelativeId());
						if (goodsInfo != null) {
							incomeRecord.setRelativename(goodsInfo.getName());
						}
					} else if (incomeRecord.getType() == 3) {// 购买商品
						GoodsInfo goodsInfo = goodsDao.selectByPrimaryKey(incomeRecord.getRelativeId());
						if (goodsInfo != null) {
							incomeRecord.setRelativename(goodsInfo.getName());
							if (goodsInfo.getPayType() == 1) {
								incomeRecord.setGoodstype(1);
							} else {
								incomeRecord.setGoodstype(2);
							}
						}
					} else if (incomeRecord.getType() == 4 || incomeRecord.getType() == 5) {// 优惠券分享
																							// 或
																							// 优惠券使用
						CouponInfo couponInfo = couponInfoDao.selectByPrimaryKey(incomeRecord.getRelativeId());
						if (couponInfo != null) {
							incomeRecord.setRelativename(couponInfo.getTitle());
						}
					} else if (incomeRecord.getType() == 6) {
						EventInfo eventInfo = eventInfoDao.selectByPrimaryKey(incomeRecord.getRelativeId());
						if (eventInfo != null) {
							incomeRecord.setRelativename(eventInfo.getName());
						}
					} else if (incomeRecord.getType() == 8 || incomeRecord.getType() == 10) {
						UserInfo userinfo2 = userInfoDao.selectByPrimaryKey(incomeRecord.getFromUserId());
						if (userinfo2 != null) {
							if (!CommonUtils.isEmptyString(userinfo2.getNickName())) {
								incomeRecord.setRelativename(userinfo2.getNickName());
							} else if (!CommonUtils.isEmptyString(userinfo2.getRealName())) {
								incomeRecord.setRelativename(userinfo2.getRealName());
							} else {
								incomeRecord.setRelativename(userinfo2.getUserName());
							}
						}
					} else if (incomeRecord.getType() == 11 || incomeRecord.getType() == 12 || incomeRecord.getType() == 13) {
						UserInfo userinfo2 = userInfoDao.selectByPrimaryKey(incomeRecord.getFromUserId());
						if (userinfo2 != null) {
							if (!CommonUtils.isEmptyString(userinfo2.getNickName())) {
								incomeRecord.setRelativename(userinfo2.getNickName());
							} else if (!CommonUtils.isEmptyString(userinfo2.getRealName())) {
								incomeRecord.setRelativename(userinfo2.getRealName());
							} else {
								incomeRecord.setRelativename(userinfo2.getUserName());
							}
						}
					}
				}
			}
		}
		return list;
	}

	@Override
	public List<IncomeRecord> getRecordListByUserId(Long userid) {
		return incomeRecordDao.getRecordListByUserId(userid);
	}

	@Override
	public void updateRecord(IncomeRecord incomeRecord) {
		incomeRecordDao.updateByPrimaryKeySelective(incomeRecord);
	}

	@Override
	public List<IncomeRecord> getRecordListByFromUserId(Long userid) {
		return incomeRecordDao.getRecordListByFromUserId(userid);
	}

	@Override
	public void insertIncomeRecord(IncomeRecord incomeRecord) {
		incomeRecordDao.insertSelective(incomeRecord);
	}
}
