package com.hz21city.xiangqu.serviceImpl;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.dao.IGoodsInfoDao;
import com.hz21city.xiangqu.dao.IIncomeRecordDao;
import com.hz21city.xiangqu.dao.IOrderDao;
import com.hz21city.xiangqu.dao.IOrderGoodsDao;
import com.hz21city.xiangqu.pojo.GoodsInfo;
import com.hz21city.xiangqu.pojo.GoodsProNetData;
import com.hz21city.xiangqu.pojo.IncomeRecord;
import com.hz21city.xiangqu.pojo.Order;
import com.hz21city.xiangqu.pojo.OrderGoods;
import com.hz21city.xiangqu.service.IOrderService;

@Service("orderService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class OrderServiceImpl implements IOrderService {

	@Resource
	private IOrderDao orderDao;
	@Resource
	private IOrderGoodsDao orderGoodsDao;
	@Resource
	private IGoodsInfoDao goodsDao;
	@Resource
	private IIncomeRecordDao incomeRecordDao;
	// @Resource
	// private IUserInfoDao userInfoDao;

	@Override
	public Order getOrderById(long id) {
		Order order = this.orderDao.selectByPrimaryKey(id);
		List<OrderGoods> goodslist = orderGoodsDao.getOrderGoods(order.getId());
		for (OrderGoods goods : goodslist) {
			GoodsInfo goodsinfo = goodsDao.selectByPrimaryKey(goods.getGoodsId());
			goods.setPaytype(goodsinfo.getPayType());
			if (!CommonUtils.isEmptyString(goods.getProperty())) {
				Gson gson = new Gson();
				List<GoodsProNetData> ps = gson.fromJson(goods.getProperty(), new TypeToken<List<GoodsProNetData>>() {
				}.getType());
				for (GoodsProNetData prodata : ps) {
					if (!CommonUtils.isEmptyString(prodata.getValue())) {
						String[] values = prodata.getValue().split(",");
						prodata.setValuelist(Arrays.asList(values));
					}
				}
				goods.setProlist(ps);
			}
		}
		order.setGoodsList(goodslist);
		return order;
	}

	@Override
	public List<Order> getOrderListByState(Integer state, long user_id, int pageIndex, int pageSize) {
		List<Order> orderlist = orderDao.getOrderListByState(state, user_id, pageIndex * pageSize, pageSize);
		for (Order order : orderlist) {
			List<OrderGoods> goodslist = orderGoodsDao.getOrderGoods(order.getId());
			order.setGoodscount(goodslist.size());
			if (goodslist != null && goodslist.size() > 0) {
				order.setGoodsname(goodslist.get(0).getName());
				order.setPic(goodslist.get(0).getPic());
				GoodsInfo goods = goodsDao.selectByPrimaryKey(goodslist.get(0).getGoodsId());
				order.setPaytype(goods.getPayType());
				if (!CommonUtils.isEmptyString(goodslist.get(0).getProperty())) {
					Gson gson = new Gson();
					List<GoodsProNetData> ps = gson.fromJson(goodslist.get(0).getProperty(), new TypeToken<List<GoodsProNetData>>() {
					}.getType());
					for (GoodsProNetData prodata : ps) {
						if (!CommonUtils.isEmptyString(prodata.getValue())) {
							String[] values = prodata.getValue().split(",");
							prodata.setValuelist(Arrays.asList(values));
						}
					}
					order.setProlist(ps);
				}
			}
		}
		return orderlist;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public long addOrder(Order order) {
		orderDao.insertSelective(order);
		return order.getId();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public void addOrderGoods(OrderGoods ordergoods) {
		orderGoodsDao.insertSelective(ordergoods);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public void updateOrder(Order order) {
		orderDao.updateByPrimaryKeySelective(order);
	}

	@Override
	public Order getOrderByOutTradeNo(String tradeno) {
		Order order = orderDao.getOrderByOutTradeNo(tradeno);
		return order;
	}

	@Override
	public List<OrderGoods> getOrderGoodsList(long orderid) {
		List<OrderGoods> goodslist = orderGoodsDao.getOrderGoods(orderid);
		return goodslist;
	}

	@Override
	public int getIncomeCount(long userid, long fromuserid, int type, int relativeid) {
		int incomecount = incomeRecordDao.incomeCount(userid, fromuserid, type, relativeid);
		return incomecount;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public void addIncome(IncomeRecord incomerecord) {
		incomeRecordDao.insertSelective(incomerecord);
		// UserInfo userInfo = userInfoDao.selectByPrimaryKey(incomerecord.getUserId());
		// userInfo.setBalance(userInfo.getBalance() + incomerecord.getIncome());
		// userInfoDao.updateByPrimaryKeySelective(userInfo);
	}

	@Override
	public List<Order> getOrderListByShopState(Integer state, long storeId, Integer pageIndex, int pageSize) {
		List<Order> orderlist = orderDao.getOrderListByShopState(state, storeId, pageIndex * pageSize, pageSize);
		for (Order order : orderlist) {
			List<OrderGoods> goodslist = orderGoodsDao.getOrderGoods(order.getId());
			int goodscount = orderGoodsDao.getGoodsCount(order.getId());
			// order.setGoodscount(goodslist.size());
			order.setGoodscount(goodscount);
			if (goodslist != null && goodslist.size() > 0) {
				order.setGoodsname(goodslist.get(0).getName());
				order.setPic(goodslist.get(0).getPic());
				if (!CommonUtils.isEmptyString(goodslist.get(0).getProperty())) {
					Gson gson = new Gson();
					List<GoodsProNetData> ps = gson.fromJson(goodslist.get(0).getProperty(), new TypeToken<List<GoodsProNetData>>() {
					}.getType());
					for (GoodsProNetData prodata : ps) {
						if (!CommonUtils.isEmptyString(prodata.getValue())) {
							String[] values = prodata.getValue().split(",");
							prodata.setValuelist(Arrays.asList(values));
						}
					}
					order.setProlist(ps);
				}
			}
		}
		return orderlist;
	}

	@Override
	public Order getOrderByCode(String code) {
		Order order = orderDao.getOrderByCode(code);
		return order;
	}

	@Override
	public int getOderCountByState(long userid, int state) {
		return orderDao.getOrderCountByState(userid, state);
	}

	@Override
	public List<Order> getOrderListByUser(Long userid) {
		return orderDao.OrderListByUser(userid);
	}

	@Override
	public List<Order> getOrderListByShareUser(Long userid) {
		return orderDao.OrderLsitByShareUser(userid);
	}

	@Override
	public int getOrderCount(long userid, long goodsid) {
		return orderDao.getOrderCount(userid, goodsid);
	}
}
