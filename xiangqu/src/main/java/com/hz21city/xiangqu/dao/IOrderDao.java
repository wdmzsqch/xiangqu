package com.hz21city.xiangqu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hz21city.xiangqu.pojo.Order;
import com.hz21city.xiangqu.pojo.OrderGoods;

public interface IOrderDao {

	int deleteByPrimaryKey(Long id);

	int insert(Order record);

	int insertSelective(Order record);

	Order selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(Order record);

	int updateByPrimaryKey(Order record);

	List<Order> getMaOrderList(@Param("type") Integer type, @Param("key") String key, @Param("start") String start, @Param("end") String end, @Param("page") Integer page);

	int getMaOrderListSize(@Param("type") Integer type, @Param("key") String key, @Param("start") String start, @Param("end") String end);

	Order getMaOrderDetail(Long id);

	List<OrderGoods> getOrderGoods(Long id);

	List<Order> getOrderListByState(@Param("state") Integer state, @Param("user_id") Long user_id, @Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize);

	List<Order> getStoreListByState(@Param("state") Integer state, @Param("store_id") Long store_id, @Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize);

	Order getOrderByOutTradeNo(@Param("order_no") String order_no);

	List<Order> getOrderListByUser(@Param("page") Integer page, @Param("userId") Long userId);

	int getRecordListSize(@Param("userId") Long userId);

	List<Order> getOrderListByShopState(@Param("state") Integer state, @Param("storeId") Long storeId, @Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);

	Order getOrderByCode(String code);

	int getAllOrderSize();

	int getOrderCountByState(@Param("userId") Long userId, @Param("state") Integer state);

	List<Order> getMaOtherOrderList(@Param("type") Integer type, @Param("key") String key, @Param("start") String start, @Param("end") String end, @Param("page") Integer page,
			@Param("storeId") Long storeId, @Param("channelName") String channelName);

	int getMaOtherOrderListSize(@Param("type") Integer type, @Param("key") String key, @Param("start") String start, @Param("end") String end, @Param("storeId") Long storeId, @Param("channelName") String channelName);

	List<Order> OrderListByUser(@Param("userid") Long userid);

	List<Order> OrderLsitByShareUser(@Param("userid") Long userid);

	void updateCreatOrder(@Param("mobileuserid") Long mobileuserid, @Param("combineduserid") Long combineduserid);

	void updateShareOrder(@Param("mobileuserid") Long mobileuserid, @Param("combineduserid") Long combineduserid);

	int getOrderCount(@Param("userid") Long userid, @Param("goodsid") Long goodsid);

	int getCountByChannelCode(@Param("channelCode") String channelCode, @Param("goodsid") Long goodsid);

	int getChannelYesTodaySellCount(@Param("channelCode") String channelCode, @Param("goodsid") Long goodsid, @Param("start") String start, @Param("end") String end);
}