package com.hz21city.xiangqu.serviceImpl.shop;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.dao.IOrderDao;
import com.hz21city.xiangqu.dao.IOrderGoodsDao;
import com.hz21city.xiangqu.pojo.GoodsProNetData;
import com.hz21city.xiangqu.pojo.Order;
import com.hz21city.xiangqu.pojo.OrderGoods;
import com.hz21city.xiangqu.service.shop.IShOrderService;

@Service("ShOrderService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ShOrderServiceImpl implements IShOrderService {

	@Resource
	private IOrderDao orderDao;
	@Resource
	private IOrderGoodsDao orderGoodsDao;

	@Override
	public List<Order> getStoreListByState(Integer state, long store_id, int pageIndex, int pageSize) {
		List<Order> orderlist = orderDao.getStoreListByState(state, store_id, pageIndex * pageSize, pageSize);
		for (Order order : orderlist) {
			List<OrderGoods> goodslist = orderGoodsDao.getOrderGoods(order.getId());
			order.setGoodscount(goodslist.size());
			if (goodslist != null && goodslist.size() > 0) {
				order.setGoodsname(goodslist.get(0).getName());
				order.setPic(goodslist.get(0).getPic());
			}
		}
		return orderlist;
	}

	@Override
	public Order getOrderById(long id) {
		Order order = orderDao.selectByPrimaryKey(id);
		List<OrderGoods> goodslist = orderGoodsDao.getOrderGoods(order.getId());
		Gson gson = new Gson();
		if (goodslist != null && goodslist.size() > 0) {
			for (OrderGoods goodsinfo : goodslist) {
				if (!CommonUtils.isEmptyString(goodsinfo.getProperty())) {
					List<GoodsProNetData> ps = gson.fromJson(goodsinfo.getProperty(), new TypeToken<List<GoodsProNetData>>() {
					}.getType());
					for (GoodsProNetData prodata : ps) {
						if (!CommonUtils.isEmptyString(prodata.getValue())) {
							String[] values = prodata.getValue().split(",");
							prodata.setValuelist(Arrays.asList(values));
						}
					}
					goodsinfo.setProlist(ps);
				}
			}
		}
		order.setGoodsList(goodslist);
		return order;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public void updateOrder(Order order) {
		orderDao.updateByPrimaryKeySelective(order);
	}

	@Override
	public Order checkServiceOrder(String code) {
		return orderDao.getOrderByCode(code);
	}
}
