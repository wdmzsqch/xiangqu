package com.hz21city.xiangqu.service.shop;

import java.util.HashMap;
import java.util.List;

import com.hz21city.xiangqu.pojo.EventInfo;

public interface IShEventService {

	List<EventInfo> getShopEventList(Long shopid);

	HashMap<String, Object> getEventByCode(String code, long storeId);

	EventInfo getEventInfoById(long id);
}
