package com.hz21city.xiangqu.serviceImpl.shop;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.daoshun.exception.CustomException;
import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.common.Constants;
import com.hz21city.xiangqu.dao.IIncomeRecordDao;
import com.hz21city.xiangqu.dao.IMissionInfoDao;
import com.hz21city.xiangqu.dao.IShopInfoDao;
import com.hz21city.xiangqu.dao.ISystemMessageDao;
import com.hz21city.xiangqu.pojo.MissionInfo;
import com.hz21city.xiangqu.pojo.ShopInfo;
import com.hz21city.xiangqu.pojo.SystemMessage;
import com.hz21city.xiangqu.service.shop.IShShopService;

@Service("ShShopService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ShShopServiceImpl implements IShShopService{

	@Resource
	private IShopInfoDao shopinfoDao;
	
	@Resource
	private ISystemMessageDao sysMsgfoDao;
	
	@Resource
	private IMissionInfoDao missioninfoDao;
	
	@Resource
	private IIncomeRecordDao incomeRecordDao;
	
	@Override
	public SystemMessage getSysMessageById(long id) {
		SystemMessage message = sysMsgfoDao.getSysMessageById(id);
		return message;
	}
	
	@Override
	public ShopInfo getShopInfoByName(String username) {
		ShopInfo shopinfo = shopinfoDao.selectByUserName(username);
		return shopinfo;
	}

	@Override
	public ShopInfo getShopInfoById(long id) {
		ShopInfo shopinfo = shopinfoDao.selectByPrimaryKey(id);
		return shopinfo;
	}
	
	@Override
	public List<SystemMessage> getShopSysMessage() {
		List<SystemMessage> shopmessage = sysMsgfoDao.getSysMessage(2);
		return shopmessage;
	}
	
	@Override
	public void changePwdByShopInfo(ShopInfo shopinfo) {
		shopinfoDao.changePwdByShopInfo(shopinfo);
	}

	@Override
	public void updateByPrimaryKeySelective(ShopInfo shopinfo) {
		shopinfoDao.updateByPrimaryKeySelective(shopinfo);
	}

	@Override
	public float getDailyTotalMoney(long shopId) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		Date start = c.getTime();
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		Date end = c.getTime();
		return missioninfoDao.getTotalMoneyByShop(shopId, CommonUtils.time2Str(start, "yyyy-MM-dd HH:mm:ss"),
				CommonUtils.time2Str(end, "yyyy-MM-dd HH:mm:ss"))/100;
	}
	

	@Override
	public int getDailyTotalCont(long shopId) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		Date start = c.getTime();
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		Date end = c.getTime();
		return missioninfoDao.getTotalCountByShop(shopId, CommonUtils.time2Str(start, "yyyy-MM-dd HH:mm:ss"),
				CommonUtils.time2Str(end, "yyyy-MM-dd HH:mm:ss"));
	}


	@Override
	public float getDailyLastMoney(long shopId) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		Date start = c.getTime();
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		Date end = c.getTime();
		return missioninfoDao.getLastMoney(shopId, CommonUtils.time2Str(start, "yyyy-MM-dd HH:mm:ss"),
				CommonUtils.time2Str(end, "yyyy-MM-dd HH:mm:ss"))/100;
	}

	@Override
	public int getDailyRamainCount(long shopId) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		Date start = c.getTime();
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		Date end = c.getTime();
		return missioninfoDao.getRamainCount(shopId, CommonUtils.time2Str(start, "yyyy-MM-dd HH:mm:ss"),
				CommonUtils.time2Str(end, "yyyy-MM-dd HH:mm:ss"));
	}
	
	@Override
	public int getDailyMissionCount(long shopId) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		Date start = c.getTime();
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		Date end = c.getTime();
		return missioninfoDao.getMissionCount(shopId, CommonUtils.time2Str(start, "yyyy-MM-dd HH:mm:ss"),
				CommonUtils.time2Str(end, "yyyy-MM-dd HH:mm:ss"));
	}

	@Override
	public List<MissionInfo> getDailyMissionList(long shopId) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		Date start = c.getTime();
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		Date end = c.getTime();
		return missioninfoDao.getMissionListByShop(shopId, CommonUtils.time2Str(start, "yyyy-MM-dd HH:mm:ss"),
				CommonUtils.time2Str(end, "yyyy-MM-dd HH:mm:ss"));
	}

	@Override
	public float getMonthTotalMoney(Date month, Long shopId) {
		Calendar c = Calendar.getInstance();
		c.setTime(month);
		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.add(Calendar.DAY_OF_MONTH, -1);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		Date end = c.getTime();
		return missioninfoDao.getTotalMoneyByShop(shopId, CommonUtils.time2Str(month, "yyyy-MM-dd HH:mm:ss"),
				CommonUtils.time2Str(end, "yyyy-MM-dd HH:mm:ss"))/100;
	}
	

	@Override
	public int getMonthTotalCont(Date month, Long shopId) {
		Calendar c = Calendar.getInstance();
		c.setTime(month);
		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.add(Calendar.DAY_OF_MONTH, -1);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		Date end = c.getTime();
		return missioninfoDao.getTotalCountByShop(shopId, CommonUtils.time2Str(month, "yyyy-MM-dd HH:mm:ss"),
				CommonUtils.time2Str(end, "yyyy-MM-dd HH:mm:ss"));
	}

	@Override
	public float getMonthLastMoney(Date month, Long shopId) {
		Calendar c = Calendar.getInstance();
		c.setTime(month);
		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.add(Calendar.DAY_OF_MONTH, -1);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		Date end = c.getTime();
		return missioninfoDao.getLastMoney(shopId, CommonUtils.time2Str(month, "yyyy-MM-dd HH:mm:ss"),
				CommonUtils.time2Str(end, "yyyy-MM-dd HH:mm:ss"))/100;
	}
	

	@Override
	public int getMonthRamainCount(Date month, Long shopId) {
		Calendar c = Calendar.getInstance();
		c.setTime(month);
		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.add(Calendar.DAY_OF_MONTH, -1);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		Date end = c.getTime();
		return missioninfoDao.getRamainCount(shopId, CommonUtils.time2Str(month, "yyyy-MM-dd HH:mm:ss"),
				CommonUtils.time2Str(end, "yyyy-MM-dd HH:mm:ss"));
	}


	@Override
	public int getMonthMissionCount(Date month, Long shopId) {
		Calendar c = Calendar.getInstance();
		c.setTime(month);
		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.add(Calendar.DAY_OF_MONTH, -1);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		Date end = c.getTime();
		return missioninfoDao.getMissionCount(shopId, CommonUtils.time2Str(month, "yyyy-MM-dd HH:mm:ss"),
				CommonUtils.time2Str(end, "yyyy-MM-dd HH:mm:ss"));
	}

	@Override
	public List<MissionInfo> getMothMissionList(Date month, Long shopId) {
		Calendar c = Calendar.getInstance();
		c.setTime(month);
		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.add(Calendar.DAY_OF_MONTH, -1);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		Date end = c.getTime();
		return missioninfoDao.getMissionListByShop(shopId, CommonUtils.time2Str(month, "yyyy-MM-dd HH:mm:ss"),
				CommonUtils.time2Str(end, "yyyy-MM-dd HH:mm:ss"));
	}

	@Override
	public int getDailIncomRecordCount(Long shopId) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		Date start = c.getTime();
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		Date end = c.getTime();
		return incomeRecordDao.getIncomRecordCount(shopId, CommonUtils.time2Str(start, "yyyy-MM-dd HH:mm:ss"),
				CommonUtils.time2Str(end, "yyyy-MM-dd HH:mm:ss"));
	}

	@Override
	public int getMonthIncomRecordCount(Date month, Long shopId) {
		Calendar c = Calendar.getInstance();
		c.setTime(month);
		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.add(Calendar.DAY_OF_MONTH, -1);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		Date end = c.getTime();
		return incomeRecordDao.getIncomRecordCount(shopId, CommonUtils.time2Str(month, "yyyy-MM-dd HH:mm:ss"),
				CommonUtils.time2Str(end, "yyyy-MM-dd HH:mm:ss"));
	}

	@Override
	public ShopInfo login(String userName, String password) throws CustomException {
		ShopInfo userinfo = shopinfoDao.selectByUserName(userName);
		if (userinfo == null) {
			throw new CustomException(Constants.USER_NAME_EXCEPTION);
		}
		if (!userinfo.getPassword().equals(password)) {
			throw new CustomException(Constants.USER_PWD_EXCEPTION);
		}
		return userinfo;
	}

	@Override
	public void updateShop(ShopInfo shopinfo) {
		shopinfoDao.updateByPrimaryKeySelective(shopinfo);
	}
}
