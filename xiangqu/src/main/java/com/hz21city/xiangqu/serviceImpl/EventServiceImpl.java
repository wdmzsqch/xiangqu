package com.hz21city.xiangqu.serviceImpl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hz21city.xiangqu.dao.IEventInfoDao;
import com.hz21city.xiangqu.dao.IEventSignDao;
import com.hz21city.xiangqu.dao.IIncomeRecordDao;
import com.hz21city.xiangqu.dao.INewYearLotteryDao;
import com.hz21city.xiangqu.dao.IUserInfoDao;
import com.hz21city.xiangqu.pojo.EventInfo;
import com.hz21city.xiangqu.pojo.EventSign;
import com.hz21city.xiangqu.pojo.IncomeRecord;
import com.hz21city.xiangqu.pojo.NewYearLottery;
import com.hz21city.xiangqu.pojo.UserInfo;
import com.hz21city.xiangqu.service.IEventService;

@Service("eventService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class EventServiceImpl implements IEventService {

	@Resource
	private IEventInfoDao eventInfoDao;
	@Resource
	private IEventSignDao eventSignDao;
	@Resource
	private IIncomeRecordDao incomeRecordDao;
	@Resource
	private IUserInfoDao userInfoDao;
	@Resource
	private INewYearLotteryDao newYearLotteryDao;

	@Override
	public List<EventInfo> getAllEventList() {
		return eventInfoDao.getAllList();
	}

	@Override
	public EventInfo selectByPrimaryKey(Long id) {
		return eventInfoDao.selectByPrimaryKey(id);
	}

	@Override
	public EventSign getEventSignByEventUser(Long event_id, Long userid) {
		return eventSignDao.getEventSignByEventUser(event_id, userid);
	}

	@Override
	public void addEventSign(EventSign sign) {
		eventSignDao.insertSelective(sign);
	}

	@Override
	public void updateEventSign(EventSign sign) {
		eventSignDao.updateByPrimaryKeySelective(sign);
	}

	@Override
	public Integer getEventSignCountByCode(String code) {
		return eventSignDao.getEventSignCountByCode(code);
	}

	@Override
	public void addSignRecord(Long userid, Long fromuserid, Integer type, Integer relativeid, Integer income) {
		int isRecord = incomeRecordDao.incomeCount(userid, fromuserid, 6, relativeid);
		if (isRecord == 0) {
			IncomeRecord incomeRecord = new IncomeRecord();
			incomeRecord.setUserId(userid);
			incomeRecord.setFromUserId(fromuserid);
			incomeRecord.setIncome(income.floatValue());
			incomeRecord.setRelativeId(relativeid.longValue());
			incomeRecord.setType(6);
			incomeRecord.setTime(new Date());
			incomeRecordDao.insertSelective(incomeRecord);
			UserInfo userInfo = userInfoDao.selectByPrimaryKey(userid);
			userInfo.setBalance(userInfo.getBalance() + income.floatValue());
			userInfoDao.updateByPrimaryKeySelective(userInfo);
		}
	}

	@Override
	public Integer getEventSignCount(Long eventId) {
		return eventSignDao.getEventSignCount(eventId, null);
	}

	@Override
	public UserInfo getUserInfoById(Long userid) {
		return userInfoDao.selectByPrimaryKey(userid);
	}

	@Override
	public List<EventSign> getEventSignByUser(Long userid) {
		return eventSignDao.getEventSignByUser(userid);
	}

	@Override
	public NewYearLottery getLotteryInfo(String mobile) {
		return newYearLotteryDao.getLotteryByMobile(mobile);
	}

	@Override
	public void updateLotteryInfo(NewYearLottery lottery) {
		newYearLotteryDao.updateByPrimaryKeySelective(lottery);
	}
}
