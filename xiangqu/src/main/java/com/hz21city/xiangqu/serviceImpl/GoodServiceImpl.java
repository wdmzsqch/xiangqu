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
import com.hz21city.xiangqu.pojo.GoodsInfo;
import com.hz21city.xiangqu.pojo.GoodsProNetData;
import com.hz21city.xiangqu.service.IGoodsService;

@Service("goodsService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class GoodServiceImpl implements IGoodsService{

	@Resource
	private IGoodsInfoDao goodsDao;
	
	@Override
	public List<GoodsInfo> getGoodsList(int sort,long categoryId, int goodstype) {
		List<GoodsInfo> goodsinfo = goodsDao.getGoodsList(sort,goodstype,categoryId);
		return goodsinfo;
	}

	@Override
	public GoodsInfo getGoodsInfo(long goodsid) {
		GoodsInfo goodinfo = goodsDao.selectByPrimaryKey(goodsid);
		if(!CommonUtils.isEmptyString( goodinfo.getProperty())){
			Gson gson = new Gson();  
			List<GoodsProNetData> ps = gson.fromJson( goodinfo.getProperty(), new TypeToken<List<GoodsProNetData>>(){}.getType());
			for(GoodsProNetData prodata:ps){
				if(!CommonUtils.isEmptyString(prodata.getValue())){
					String[] values = prodata.getValue().split(",");
					prodata.setValuelist(Arrays.asList(values));
				}
			}
			 goodinfo.setProlist(ps);
		}
		return goodinfo;
	}

	@Override
	public List<GoodsInfo> getAllGoodsList() {
		List<GoodsInfo> goodslist = goodsDao.getAllGoodsList();
		return goodslist;
	}

	@Override
	public void updateGoodsInfo(GoodsInfo goods) {
		goodsDao.updateByPrimaryKeySelective(goods);
	}

}
