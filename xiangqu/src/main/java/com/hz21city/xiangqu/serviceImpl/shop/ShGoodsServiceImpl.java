package com.hz21city.xiangqu.serviceImpl.shop;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hz21city.xiangqu.dao.IGoodsInfoDao;
import com.hz21city.xiangqu.service.shop.IShGoodsService;

@Service("shGoodsService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ShGoodsServiceImpl implements IShGoodsService {

	@Resource
	private IGoodsInfoDao goodsInfoDao;
//
//	@Override
//	public GoodsInfo getGoodsById(long id) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
