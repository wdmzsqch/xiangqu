package com.hz21city.xiangqu.service.shop;

import java.util.Date;
import java.util.List;

import com.daoshun.exception.CustomException;
import com.hz21city.xiangqu.pojo.MissionInfo;
import com.hz21city.xiangqu.pojo.ShopInfo;
import com.hz21city.xiangqu.pojo.SystemMessage;

public interface IShShopService {

	ShopInfo getShopInfoByName(String username);
	
	ShopInfo getShopInfoById(long id);
	
	public abstract void changePwdByShopInfo(ShopInfo shopinfo);

	public abstract void updateByPrimaryKeySelective(ShopInfo shopinfo);
	
	List<SystemMessage> getShopSysMessage();
	
	SystemMessage getSysMessageById(long id);

	//商店今日已投放金额
	public abstract float getDailyTotalMoney(long id);
	//商店今日剩余可投放金额
	public abstract float getDailyLastMoney(long id);
	//商店今日任务数
	public abstract int getDailyMissionCount(long id);
	//商店今日任务
	public abstract List<MissionInfo> getDailyMissionList(long id);
	//商店今日点击量
	public abstract int getDailIncomRecordCount(Long id);
	//商店当月已投放金额
	public abstract float getMonthTotalMoney(Date month, Long id);
	//商店当月剩余可投放金额
	public abstract float getMonthLastMoney(Date date, Long id);
	//商店当月任务数
	public abstract int getMonthMissionCount(Date date, Long id);
	//商店当月任务
	public abstract List<MissionInfo> getMothMissionList(Date date, Long id);
	//商店当月点击量
	public abstract int getMonthIncomRecordCount(Date date, Long id);
	//商店今日已投放数
	public abstract int getDailyTotalCont(long id);
	//商店今日剩余可投放数
	public abstract int getDailyRamainCount(long id);
	//商店当月已投放数
	public abstract int getMonthTotalCont(Date date, Long id);
	//商店当月剩余可投放数
	public abstract int getMonthRamainCount(Date date, Long id);

	public abstract ShopInfo login(String userName, String password) throws CustomException;

	public abstract void updateShop(ShopInfo shopinfo);

}
