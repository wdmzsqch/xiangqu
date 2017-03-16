package com.hz21city.xiangqu.service.shop;

import java.util.List;

import com.hz21city.xiangqu.pojo.Order;

public interface IShOrderService {

	public abstract List<Order> getStoreListByState(Integer state, long store_id, int pageIndex, int pageSize);

	public abstract Order getOrderById(long id);

	public abstract void updateOrder(Order order);

	public abstract Order checkServiceOrder(String code);
}
