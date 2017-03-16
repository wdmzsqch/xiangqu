package com.hz21city.xiangqu.serviceImpl.manage;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.dao.IOrderDao;
import com.hz21city.xiangqu.dao.IShopInfoDao;
import com.hz21city.xiangqu.pojo.Order;
import com.hz21city.xiangqu.pojo.ShopInfo;
import com.hz21city.xiangqu.service.manage.IMaOrderService;

@Service("maOrderService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class MaOrderServiceImpl implements IMaOrderService {
	@Resource
	private IOrderDao orderDao;
	
	@Resource
	private IShopInfoDao shopInfoDao;

	@Override
	public Order getOrderById(long id) {
		Order info = orderDao.selectByPrimaryKey(id);
		return info;
	}

	@Override
	public List<Order> getOrderList(Integer type, String key, Date start, Date end, Integer page) {
		String startStr = CommonUtils.time2Str(start, "yyyy-MM-dd HH:mm:ss");
		String endStr = CommonUtils.time2Str(end, "yyyy-MM-dd HH:mm:ss");
		String keywords = null;
		if (!CommonUtils.isEmptyString(key)) {
			keywords = "%" + key + "%";
		}
		List<Order> list = orderDao.getMaOrderList(type, keywords, startStr, endStr, (page - 1) * 15);
		if(list != null && list.size() > 0){
			for (Order order : list) {
				if(order.getStoreId() != null){
					ShopInfo shop = shopInfoDao.selectByPrimaryKey(order.getStoreId());
					if(shop != null){
						order.setStoreName(shop.getCompanyName());
					}
				}
			}
		}
		return list;
	}

	@Override
	public Order getOrderDetail(long id) {
		return orderDao.getMaOrderDetail(id);
	}

	@Override
	public int getOrderListSize(Integer type, String key, Date start, Date end) {
		String startStr = CommonUtils.time2Str(start, "yyyy-MM-dd HH:mm:ss");
		String endStr = CommonUtils.time2Str(end, "yyyy-MM-dd HH:mm:ss");
		String keywords = null;
		if (!CommonUtils.isEmptyString(key)) {
			keywords = "%" + key + "%";
		}
		int total = orderDao.getMaOrderListSize(type, keywords, startStr, endStr);
		return (int) Math.ceil((double) total / 15.0d);
	}

	@Override
	public int getAllOrderSize() {
		return orderDao.getAllOrderSize();
	}

	@Override
	public List<Order> getOtherOrderList(Integer type, String key, Date start, Date end, Integer page, Long storeId, String channel) {
		String startStr = CommonUtils.time2Str(start, "yyyy-MM-dd HH:mm:ss");
		String endStr = CommonUtils.time2Str(end, "yyyy-MM-dd HH:mm:ss");
		String keywords = null;
		if (!CommonUtils.isEmptyString(key)) {
			keywords = "%" + key + "%";
		}
		if (!CommonUtils.isEmptyString(channel)) {
			channel = "%" + channel + "%";
		}
		List<Order> list = orderDao.getMaOtherOrderList(type, keywords, startStr, endStr, (page - 1) * 15, storeId, channel);
		if(list != null && list.size() > 0){
			for (Order order : list) {
				if(order.getStoreId() != null){
					ShopInfo shop = shopInfoDao.selectByPrimaryKey(order.getStoreId());
					if(shop != null){
						order.setStoreName(shop.getCompanyName());
					}
				}
			}
		}
		return list;
	}

	@Override
	public int getOtherOrderListSize(Integer type, String key, Date start, Date end, Long storeId, String channel) {
		String startStr = CommonUtils.time2Str(start, "yyyy-MM-dd HH:mm:ss");
		String endStr = CommonUtils.time2Str(end, "yyyy-MM-dd HH:mm:ss");
		String keywords = null;
		if (!CommonUtils.isEmptyString(key)) {
			keywords = "%" + key + "%";
		}
		if (!CommonUtils.isEmptyString(channel)) {
			channel = "%" + channel + "%";
		}
		int total = orderDao.getMaOtherOrderListSize(type, keywords, startStr, endStr, storeId, channel);
		return (int) Math.ceil((double) total / 15.0d);
	}
}
