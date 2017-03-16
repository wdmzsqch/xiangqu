package com.hz21city.xiangqu.service;

import java.util.List;

import com.hz21city.xiangqu.pojo.EventInfo;
import com.hz21city.xiangqu.pojo.EventSign;
import com.hz21city.xiangqu.pojo.NewYearLottery;
import com.hz21city.xiangqu.pojo.UserInfo;

public interface IEventService {

	public abstract List<EventInfo> getAllEventList();

	public abstract EventInfo selectByPrimaryKey(Long id);

	public abstract EventSign getEventSignByEventUser(Long event_id, Long userid);

	public abstract void addEventSign(EventSign sign);

	public abstract void updateEventSign(EventSign sign);

	public abstract Integer getEventSignCountByCode(String code);

	public abstract void addSignRecord(Long userid, Long fromuserid, Integer type, Integer relativeid, Integer income);

	public abstract Integer getEventSignCount(Long eventId);

	public abstract UserInfo getUserInfoById(Long userid);

	public abstract List<EventSign> getEventSignByUser(Long combineduserid);

	// 新年抽奖，借用下service
	public abstract NewYearLottery getLotteryInfo(String mobile);

	public abstract void updateLotteryInfo(NewYearLottery lottery);
}
