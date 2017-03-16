package com.hz21city.xiangqu.service.manage;

import java.util.List;

import com.hz21city.xiangqu.pojo.EventInfo;
import com.hz21city.xiangqu.pojo.EventSign;

public interface IMaEventService {

	public abstract List<EventInfo> getEventListByPage(Integer page);

	public abstract int getEventListSize();

	public abstract EventInfo selectByPrimaryKey(Long id);

	public abstract void updateEventInfo(EventInfo event);

	public abstract void addEventInfo(EventInfo event);

	public abstract List<EventSign> getSubjectListByPage(Long event_id, Integer page, String code, String phone, Integer searchType);

	public abstract int getSubjectListSize(Long event_id, String code,String phone);

	public abstract int getAllEventSize();

}
