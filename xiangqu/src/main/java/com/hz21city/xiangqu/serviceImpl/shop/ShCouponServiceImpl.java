package com.hz21city.xiangqu.serviceImpl.shop;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hz21city.xiangqu.dao.ICouponInfoDao;
import com.hz21city.xiangqu.dao.IIncomeRecordDao;
import com.hz21city.xiangqu.dao.ILotteryInfoDao;
import com.hz21city.xiangqu.dao.IUserCouponDao;
import com.hz21city.xiangqu.dao.IUserInfoDao;
import com.hz21city.xiangqu.pojo.CouponInfo;
import com.hz21city.xiangqu.pojo.IncomeRecord;
import com.hz21city.xiangqu.pojo.LotteryInfo;
import com.hz21city.xiangqu.pojo.UserCoupon;
import com.hz21city.xiangqu.pojo.UserInfo;
import com.hz21city.xiangqu.service.shop.IShCouponService;

@Service("shCouponService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ShCouponServiceImpl implements IShCouponService {

	@Resource
	private IUserCouponDao userCouponDao;
	@Resource
	private ICouponInfoDao couponInfoDao;
	@Resource
	private IIncomeRecordDao incomeRecordDao;
	@Resource
	private IUserInfoDao userInfoDao;
	@Resource
	private ILotteryInfoDao lotteryInfoDao;

	@Override
	public synchronized HashMap<String, Object> getCouponByCode(String code, long storeId) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		UserCoupon userCoupon = userCouponDao.checkCoupon(code);
		if (userCoupon != null) {
			CouponInfo couponInfo = couponInfoDao.selectByPrimaryKey(userCoupon.getCouponId());
			if (couponInfo != null) {
				if (couponInfo.getStoreId() != storeId) {
					result.put("errMsg", "非本店优惠券");
				} else if (couponInfo.getValidity().getTime() < new Date().getTime()) {
					result.put("errMsg", "该优惠券已过期");
				} else {
					result.put("userCoupon", userCoupon);
					result.put("couponInfo", couponInfo);
					result.put("isUsed", userCoupon.getIsUsed());
					// 判断是否使用，没使用更新成使用
					if (userCoupon.getIsUsed() == 0) {
						userCoupon.setIsUsed(1);
						userCoupon.setUseTime(new Date());
						userCouponDao.updateByPrimaryKeySelective(userCoupon);
						// 使用张数+1
						if (couponInfo.getUseNum() == null) {
							couponInfo.setUseNum(1);
						} else {
							couponInfo.setUseNum(couponInfo.getUseNum() + 1);
						}
					}
					couponInfoDao.updateByPrimaryKeySelective(couponInfo);
					// 使用时增加收益
					if (userCoupon.getInviteUserId() != null && userCoupon.getInviteUserId() > 0 && couponInfo.getUseIncome() != null && couponInfo.getUseIncome() > 0) {
						int isRecord = incomeRecordDao.incomeCount(userCoupon.getInviteUserId(), userCoupon.getUserId(), 5, couponInfo.getId().intValue());
						if (isRecord == 0) {
							IncomeRecord incomeRecord = new IncomeRecord();
							incomeRecord.setUserId(userCoupon.getInviteUserId());
							incomeRecord.setFromUserId(userCoupon.getUserId());
							incomeRecord.setIncome(couponInfo.getUseIncome().floatValue());
							incomeRecord.setRelativeId(couponInfo.getId());
							incomeRecord.setType(5);
							incomeRecord.setTime(new Date());
							incomeRecordDao.insertSelective(incomeRecord);
							UserInfo userInfo = userInfoDao.selectByPrimaryKey(userCoupon.getInviteUserId());
							userInfo.setBalance(userInfo.getBalance() + couponInfo.getUseIncome().floatValue());
							userInfoDao.updateByPrimaryKeySelective(userInfo);
						}
					}
				}
			}
		}
		return result;
	}

	@Override
	public List<CouponInfo> getCouponInfoList(long storeId) {
		List<CouponInfo> couponlist = couponInfoDao.getCouponListByStore(storeId);
		return couponlist;
	}

	@Override
	public CouponInfo getCouponInfo(long id) {
		CouponInfo coupon = couponInfoDao.selectByPrimaryKey(id);
		return coupon;
	}

	@Override
	public void insertLotterInfo(LotteryInfo lottery) {
		lotteryInfoDao.insertSelective(lottery);
	}
}
