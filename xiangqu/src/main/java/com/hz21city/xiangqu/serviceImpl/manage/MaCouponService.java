package com.hz21city.xiangqu.serviceImpl.manage;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.dao.ICouponInfoDao;
import com.hz21city.xiangqu.dao.IShopInfoDao;
import com.hz21city.xiangqu.dao.IUserCouponDao;
import com.hz21city.xiangqu.dao.IUserInfoDao;
import com.hz21city.xiangqu.dao.IWinprizeInfoDao;
import com.hz21city.xiangqu.pojo.CouponInfo;
import com.hz21city.xiangqu.pojo.ShopInfo;
import com.hz21city.xiangqu.pojo.UserCoupon;
import com.hz21city.xiangqu.pojo.UserInfo;
import com.hz21city.xiangqu.pojo.WinprizeInfo;
import com.hz21city.xiangqu.service.manage.IMaCouponService;

@Service("maCouponService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class MaCouponService implements IMaCouponService{
	
	@Resource
	private ICouponInfoDao couponInfoDao;
	
	@Resource
	private IShopInfoDao shopInfoDao;
	
	@Resource
	private IUserCouponDao userCouponDao;
	
	@Resource
	private IUserInfoDao userInfoDao;
	
	@Resource
	private IWinprizeInfoDao winprizeInfoDao;

	@Override
	public List<CouponInfo> getCouponListByPage(Integer page, String key, Date endDate) {
		String endStr = null;
		if(endDate != null){
			endStr = CommonUtils.time2Str(endDate, "yyyy-MM-dd");
			endStr = endStr + " 23:59:59";
		}
		String keywords = null;
		if (!CommonUtils.isEmptyString(key)) {
			keywords = "%" + key + "%";
		}
		List<CouponInfo> list = couponInfoDao.getCouponListByPage(keywords, endStr, (page - 1) * 15);
		if(list != null && list.size() > 0){
			for (CouponInfo couponInfo : list) {
				if(couponInfo.getStoreId() != null && couponInfo.getStoreId() != 0){
					ShopInfo shopInfo = shopInfoDao.selectByPrimaryKey(couponInfo.getStoreId());
					if(shopInfo != null){
						couponInfo.setStorename(shopInfo.getCompanyName());
					}
				}
			}
		}
		return list;
	}

	@Override
	public int getCouponListSize(String key, Date endDate) {
		String endStr = CommonUtils.time2Str(endDate, "yyyy-MM-dd HH:mm:ss");
		String keywords = null;
		if (!CommonUtils.isEmptyString(key)) {
			keywords = "%" + key + "%";
		}
		int total = couponInfoDao.getMaCouponListSize(keywords, endStr);
		return (int) Math.ceil((double) total / 15.0d);
	}

	@Override
	public CouponInfo selectByPrimaryKey(Long id) {
		return couponInfoDao.selectByPrimaryKey(id);
	}

	@Override
	public void updateCouponInfo(CouponInfo couponinfo) {
		couponInfoDao.updateByPrimaryKeySelective(couponinfo);
	}

	@Override
	public void addCouponInfo(CouponInfo couponinfo) {
		couponInfoDao.insertSelective(couponinfo);
	}

	@Override
	public Integer getCouponCountByCode(String code) {
		return couponInfoDao.getCouponCountByCode(code);
	}

	@Override
	public int getAllCouponSize() {
		return couponInfoDao.getAllCouponSize();
	}

	@Override
	public List<UserCoupon> getUserCouponListByPage(Integer page, Long couponId) {
		List<UserCoupon> list = userCouponDao.getUserCouponListByPage((page - 1) * 15, couponId);
		if(list != null && list.size() > 0){
			for (UserCoupon couponInfo : list) {
				if(couponInfo.getUserId() != null){
					UserInfo userinfo = userInfoDao.selectByPrimaryKey(couponInfo.getUserId());
					if(!CommonUtils.isEmptyString(userinfo.getNickName())){
						couponInfo.setUser_name(userinfo.getNickName());
					}else if(!CommonUtils.isEmptyString(userinfo.getRealName())){
						couponInfo.setUser_name(userinfo.getRealName());
					}else{
						couponInfo.setUser_name(userinfo.getUserName());
					}
				}
			}
		}
		return list;
	}

	@Override
	public int getUserCouponListSize(Long couponId) {
		int total = userCouponDao.getMaUserCouponListSize(couponId);
		return (int) Math.ceil((double) total / 15.0d);
	}

	@Override
	public List<WinprizeInfo> getUserPrizeListByPage(Integer page, String userName, String moblie, Integer state) {
		if (!CommonUtils.isEmptyString(userName)) {
			userName = "%" + userName + "%";
		}
		if (!CommonUtils.isEmptyString(moblie)) {
			moblie = "%" + moblie + "%";
		}
		List<WinprizeInfo> list = winprizeInfoDao.getUserPrizeListByPage((page - 1) * 15, userName, moblie, state);
		if(list != null && list.size() > 0){
			for (WinprizeInfo winprizeInfo : list) {
				UserInfo user = userInfoDao.selectByPrimaryKey(winprizeInfo.getUserid());
				if(user != null){
					winprizeInfo.setUser(user);
				}
			}
		}
		return list;
	}

	@Override
	public int getUserPrizeListSize(String userName, String moblie, Integer state) {
		int total = winprizeInfoDao.getUserPrizeListSize(userName, moblie, state);
		return (int) Math.ceil((double) total / 15.0d);
	}

	@Override
	public WinprizeInfo getWinprizeInfoById(Long id) {
		return winprizeInfoDao.selectByPrimaryKey(id);
	}

	@Override
	public void updateWinprizeInfo(WinprizeInfo winprize) {
		winprizeInfoDao.updateByPrimaryKeySelective(winprize);
	}

}
