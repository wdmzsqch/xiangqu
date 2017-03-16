package com.hz21city.xiangqu.service.manage;

import java.util.Date;
import java.util.List;

import com.hz21city.xiangqu.pojo.Order;

public interface IMaOrderService {
	public abstract Order getOrderById(long id);

	public abstract List<Order> getOrderList(Integer type, String key, Date start, Date end, Integer page);

	public abstract int getOrderListSize(Integer type, String key, Date start, Date end);

	public abstract Order getOrderDetail(long id);

	public abstract int getAllOrderSize();

	public abstract List<Order> getOtherOrderList(Integer type, String key, Date start, Date end, Integer page, Long storeId, String channel);

	public abstract int getOtherOrderListSize(Integer type, String key, Date start, Date end, Long storeId, String channel);
}
