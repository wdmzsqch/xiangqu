package com.hz21city.xiangqu.service;

import java.util.List;

import com.hz21city.xiangqu.pojo.IncomeRecord;
import com.hz21city.xiangqu.pojo.Order;
import com.hz21city.xiangqu.pojo.OrderGoods;

public interface IOrderService {

	public abstract Order getOrderById(long id);

	public abstract List<Order> getOrderListByState(Integer state, long user_id, int pageIndex, int pageSize);

	public abstract long addOrder(Order order);

	public abstract void addOrderGoods(OrderGoods ordergoods);

	public abstract List<OrderGoods> getOrderGoodsList(long orderid);

	public abstract void updateOrder(Order order);

	public abstract Order getOrderByOutTradeNo(String tradeno);

	public abstract int getIncomeCount(long userid, long fromuserid, int type, int relativeid);

	public abstract void addIncome(IncomeRecord incomerecord);

	public abstract List<Order> getOrderListByShopState(Integer state, long storeId, Integer pageIndex, int pageSize);

	public abstract Order getOrderByCode(String code);

	public abstract int getOderCountByState(long userid, int state);

	public abstract List<Order> getOrderListByUser(Long combineduserid);

	public abstract List<Order> getOrderListByShareUser(Long combineduserid);

	public abstract int getOrderCount(long userid, long goodsid);
}
