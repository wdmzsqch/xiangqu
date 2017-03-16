package com.hz21city.xiangqu.dao;

import java.util.List;

import com.hz21city.xiangqu.pojo.OrderGoods;

public interface IOrderGoodsDao {
	int deleteByPrimaryKey(Long id);

	int insert(OrderGoods record);

	int insertSelective(OrderGoods record);

	OrderGoods selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(OrderGoods record);

	int updateByPrimaryKey(OrderGoods record);
	
	List<OrderGoods> getOrderGoods(Long id);

	int getGoodsCount(Long orderId);
}