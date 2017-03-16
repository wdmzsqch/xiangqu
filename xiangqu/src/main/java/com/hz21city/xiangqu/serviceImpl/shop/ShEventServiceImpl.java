package com.hz21city.xiangqu.serviceImpl.shop;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hz21city.xiangqu.dao.ICouponInfoDao;
import com.hz21city.xiangqu.dao.IEventInfoDao;
import com.hz21city.xiangqu.dao.IEventSignDao;
import com.hz21city.xiangqu.dao.IIncomeRecordDao;
import com.hz21city.xiangqu.dao.IUserCouponDao;
import com.hz21city.xiangqu.pojo.EventInfo;
import com.hz21city.xiangqu.pojo.EventSign;
import com.hz21city.xiangqu.service.shop.IShEventService;

@Service("shEventService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ShEventServiceImpl implements IShEventService {

	@Resource
	private IEventInfoDao eventInfoDao;
	@Resource
	private IEventSignDao eventSignDao;

	@Override
	public List<EventInfo> getShopEventList(Long shopid) {
		List<EventInfo> eventlist = eventInfoDao.getEventListByStoreId(shopid);
		return eventlist;
	}

	@Override
	public HashMap<String, Object> getEventByCode(String code, long storeId) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		EventSign eventSign = eventSignDao.getSignInfoByCode(code);
		if (eventSign != null) {
			EventInfo eventInfo = eventInfoDao.selectByPrimaryKey(eventSign.getEventId());
			if (eventInfo != null) {
				if (eventInfo.getStoreId() == storeId) {
					result.put("event", eventInfo);
					result.put("eventsign", eventSign);
					result.put("isjoin", eventSign.getIsJoined());
					if (eventSign.getIsJoined() != null && eventSign.getIsJoined() != 1) {
						eventSign.setIsJoined(1);
						eventSignDao.updateByPrimaryKeySelective(eventSign);
					}
				} else {
					result.put("errMsg", "非本店活动");
				}
			}
		}
		return result;
	}

	@Override
	public EventInfo getEventInfoById(long id) {
		EventInfo event = eventInfoDao.selectByPrimaryKey(id);
		if(event!=null){
			int signcount = eventSignDao.getEventSignCount(id, null);	
			event.setSignCount(signcount);
			int joincount = eventSignDao.getEventSignCount(id, 1);
			event.setJoinCount(joincount);
		}
		return event;
	}

}
