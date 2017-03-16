package com.hz21city.xiangqu.serviceImpl.manage;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hz21city.xiangqu.dao.ICheckInfoDao;
import com.hz21city.xiangqu.dao.IEventInfoDao;
import com.hz21city.xiangqu.dao.IEventSignDao;
import com.hz21city.xiangqu.dao.IShopInfoDao;
import com.hz21city.xiangqu.dao.IUserInfoDao;
import com.hz21city.xiangqu.pojo.CheckInfo;
import com.hz21city.xiangqu.pojo.EventInfo;
import com.hz21city.xiangqu.pojo.EventSign;
import com.hz21city.xiangqu.pojo.ShopInfo;
import com.hz21city.xiangqu.pojo.UserInfo;
import com.hz21city.xiangqu.service.manage.IMaEventService;

@Service("maEventService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class MaEventServiceImpl implements IMaEventService {

	@Resource
	private IEventInfoDao eventInfoDao;
	@Resource
	private IEventSignDao eventSignDao;
	@Resource
	private IUserInfoDao userInfo;
	@Resource
	private IShopInfoDao shopInfoDao;
	@Resource
	private ICheckInfoDao checkInfoDao;

	@Override
	public List<EventInfo> getEventListByPage(Integer page) {
		if (page == null) {
			page = 0;
		} else if (page > 0) {
			page = (page - 1) * 10;
		}
		List<EventInfo> list = eventInfoDao.getEventListByPage(page);
		if (list != null && list.size() > 0) {
			for (EventInfo eventInfo : list) {
				if (eventInfo.getStoreId() != null && eventInfo.getStoreId() != 0) {
					ShopInfo shopinfo = shopInfoDao.selectByPrimaryKey(eventInfo.getStoreId());
					if (shopinfo != null) {
						eventInfo.setStorename(shopinfo.getCompanyName());
					}
				}
				Integer singCount = eventSignDao.getEventSignCountNotNull(eventInfo.getId());
				eventInfo.setSignCount(singCount);
				Integer checkCount = eventSignDao.getEventSignCountNull(eventInfo.getId());
				eventInfo.setCheckCount(checkCount);
				CheckInfo checkinfo = checkInfoDao.getCheckInfoByAllWays(3, eventInfo.getId(), 1);
				if (checkinfo == null) {
					eventInfo.setCheckStatus(0);
				} else {
					eventInfo.setCheckStatus(1);
				}
				CheckInfo checkinfo2 = checkInfoDao.getCheckInfoByAllWays(3, eventInfo.getId(), 2);
				if (checkinfo2 == null) {
					eventInfo.setCheckStatusY(0);
				} else {
					eventInfo.setCheckStatusY(1);
				}
				CheckInfo checkinfo3 = checkInfoDao.getCheckInfoByAllWays(3, eventInfo.getId(), 3);
				if (checkinfo3 == null) {
					eventInfo.setCheckStatusC(0);
				} else {
					eventInfo.setCheckStatusC(1);
				}
				CheckInfo checkinfo4 = checkInfoDao.getCheckInfoByAllWays(3, eventInfo.getId(), 4);
				if (checkinfo4 == null) {
					eventInfo.setCheckStatusB(0);
				} else {
					eventInfo.setCheckStatusB(1);
				}
			}
		}
		return list;
	}

	@Override
	public int getEventListSize() {
		int total = eventInfoDao.getUserListSize();
		return (int) Math.ceil((double) total / 10.0d);
	}

	@Override
	public EventInfo selectByPrimaryKey(Long id) {
		return eventInfoDao.selectByPrimaryKey(id);
	}

	@Override
	public void updateEventInfo(EventInfo event) {
		eventInfoDao.updateByPrimaryKeySelective(event);
	}

	@Override
	public void addEventInfo(EventInfo event) {
		eventInfoDao.insertSelective(event);
	}

	@Override
	public List<EventSign> getSubjectListByPage(Long event_id, Integer page, String code, String phone, Integer searchType) {
		if (page == null) {
			page = 0;
		} else if (page > 0) {
			page = (page - 1) * 10;
		}
		List<EventSign> list = eventSignDao.getSubjectListByPage(event_id, page, code, phone, searchType);
		if (list != null && list.size() > 0) {
			for (EventSign eventSign : list) {
				EventInfo eventinfo = eventInfoDao.selectByPrimaryKey(eventSign.getEventId());
				if (eventinfo != null) {
					eventSign.setEvent_name(eventinfo.getName());
				}
				UserInfo userinfo = userInfo.selectByPrimaryKey(eventSign.getUserId());
				if (userinfo != null) {
					eventSign.setUser_name(userinfo.getNickName());
				}
			}
		}
		return list;
	}

	@Override
	public int getSubjectListSize(Long event_id, String code, String phone) {
		int total = eventSignDao.getSubjectListSize(event_id, code, phone);
		return (int) Math.ceil((double) total / 10.0d);
	}

	@Override
	public int getAllEventSize() {
		return eventInfoDao.getUserListSize();
	}
}
