package com.hz21city.xiangqu.serviceImpl.manage;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.dao.ICgrecordInfoDao;
import com.hz21city.xiangqu.dao.IChannelInfoDao;
import com.hz21city.xiangqu.dao.ICheckInfoDao;
import com.hz21city.xiangqu.dao.IGoodsInfoDao;
import com.hz21city.xiangqu.dao.IOrderDao;
import com.hz21city.xiangqu.dao.IStoreCotegoryDao;
import com.hz21city.xiangqu.pojo.ChannelInfo;
import com.hz21city.xiangqu.pojo.CheckInfo;
import com.hz21city.xiangqu.pojo.GoodsInfo;
import com.hz21city.xiangqu.pojo.GoodsProNetData;
import com.hz21city.xiangqu.pojo.StoreCotegory;
import com.hz21city.xiangqu.service.manage.IMaGoodsService;

@Service("maGoodsService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class MaGoodsServiceImpl implements IMaGoodsService {
	@Resource
	private IGoodsInfoDao goodsInfoDao;
	@Resource
	private IStoreCotegoryDao storeCotegoryDao;
	@Resource
	private ICheckInfoDao checkInfoDao;
	@Resource
	private IChannelInfoDao channelInfoDao;
	@Resource IOrderDao orderDao;
	@Resource
	private ICgrecordInfoDao cgrdcordInfoDao;

	@Override
	public GoodsInfo getGoodsById(long id) {
		GoodsInfo goodsinfo= goodsInfoDao.selectByPrimaryKey(id);
		Gson gson = new Gson();  
		if(!CommonUtils.isEmptyString(goodsinfo.getProperty())){
			List<GoodsProNetData> ps = gson.fromJson(goodsinfo.getProperty(), new TypeToken<List<GoodsProNetData>>(){}.getType());
			for(GoodsProNetData prodata:ps){
				if(!CommonUtils.isEmptyString(prodata.getValue())){
					String[] values = prodata.getValue().split(",");
					prodata.setValuelist(Arrays.asList(values));
				}
			}
			goodsinfo.setProlist(ps);
		}
		return goodsinfo;
	}

	@Override
	public void addGoods(GoodsInfo goods) {
		goodsInfoDao.insertSelective(goods);
	}

	@Override
	public void editGoods(GoodsInfo goods) {
		goodsInfoDao.updateByPrimaryKeySelective(goods);
	}

	@Override
	public void delGoodsById(long id) {
		goodsInfoDao.deleteByPrimaryKey(id);
	}

	@Override
	public List<GoodsInfo> getGoodsList(String keywords, Integer type, Integer page,Long storeId, Long categoryId, Integer delFlg) {
		if (page == null) {
			page = 0;
		} else if (page > 0) {
			page = (page - 1) * 10;
		}
		if (!CommonUtils.isEmptyString(keywords)) {
			keywords = "%" + keywords + "%";
		}
		List<GoodsInfo> list = goodsInfoDao.getMaGoodsList(keywords, type, page, storeId, categoryId, delFlg);
		if(list != null && list.size() > 0){
			for (GoodsInfo goodsInfo : list) {
				CheckInfo checkinfo = checkInfoDao.getCheckInfoByAllWays(2, goodsInfo.getId(), 1);
				if(checkinfo == null){
					goodsInfo.setCheckStatus(0);
				}else{
					goodsInfo.setCheckStatus(1);
				}
				CheckInfo checkinfo2 = checkInfoDao.getCheckInfoByAllWays(2, goodsInfo.getId(), 2);
				if(checkinfo2 == null){
					goodsInfo.setCheckStatusY(0);
				}else{
					goodsInfo.setCheckStatusY(1);
				}
				CheckInfo checkinfo3 = checkInfoDao.getCheckInfoByAllWays(2, goodsInfo.getId(), 3);
				if(checkinfo3 == null){
					goodsInfo.setCheckStatusC(0);
				}else{
					goodsInfo.setCheckStatusC(1);
				}
				CheckInfo checkinfo4 = checkInfoDao.getCheckInfoByAllWays(2, goodsInfo.getId(), 4);
				if(checkinfo4 == null){
					goodsInfo.setCheckStatusB(0);
				}else{
					goodsInfo.setCheckStatusB(1);
				}
			}
		}
		return list;
	}

	@Override
	public void changeGoods(Integer type, Integer value) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<StoreCotegory> getCotegoryList() {
		return storeCotegoryDao.selectAll();
	}

	@Override
	public void changeGoodsState(Long[] ids, Integer type) {
		if (type != null) {
			if (type == 1) {
				goodsInfoDao.putaway(ids);
			}
			if (type == 0) {
				goodsInfoDao.soldout(ids);
			}
			if (type == -1) {
				goodsInfoDao.delGoods(ids);
			}
		}
	}

	@Override
	public int getGoodsListSize(String keywords, Integer type, Long storeId, Long categoryId, Integer delFlg) {
		if (!CommonUtils.isEmptyString(keywords)) {
			keywords = "%" + keywords + "%";
		}
		int total = goodsInfoDao.getGoodsListCount(keywords, type, storeId, categoryId, delFlg);
		return (int) Math.ceil((double) total / 10.0d);
	}

	@Override
	public Integer getGoodMaxSort() {
		Integer sort = 0;
		List<GoodsInfo> list = goodsInfoDao.getGoodsListBySort();
		if(list != null && list.size() > 0){
			if(list.get(0).getSort() != null){
				sort = list.get(0).getSort();
			}
		}
		return sort;
	}

	@Override
	public int getAllGoodSize() {
		return goodsInfoDao.getAllGoodSize();
	}

	@Override
	public List<ChannelInfo> getChannelList() {
		List<ChannelInfo> list = channelInfoDao.getChannelList();
		return list;
	}

	@Override
	public ChannelInfo getChannlInfoByID(Long channel_id) {
		ChannelInfo info = channelInfoDao.selectByPrimaryKey(channel_id);
		return info;
	}

	@Override
	public int getChannelSellCount(String channelCode, Long goodsId) {
		int count = orderDao.getCountByChannelCode(channelCode, goodsId);
		return count;
	}

	@Override
	public int getChannelYesTodaySellCount(String channelCode, Long goodsId) {
		Date nowtime = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(nowtime);
		calendar.add(Calendar.DATE, -1);
		Date yestoday = calendar.getTime();
		String startStr = CommonUtils.time2Str(yestoday, "yyyy-MM-dd 00:00:00");
		String endStr = CommonUtils.time2Str(yestoday, "yyyy-MM-dd 23:59:59");
		int count = orderDao.getChannelYesTodaySellCount(channelCode, goodsId, startStr, endStr);
		return count;
	}

	@Override
	public int getChannelLookCount(Long channelId, Long goodId) {
		int count = cgrdcordInfoDao.getChannelLookCount(channelId, goodId);
		return count;
	}

	@Override
	public int getChannelYesTodayLookCount(Long channelId, Long goodId) {
		Date nowtime = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(nowtime);
		calendar.add(Calendar.DATE, -1);
		Date yestoday = calendar.getTime();
		String startStr = CommonUtils.time2Str(yestoday, "yyyy-MM-dd 00:00:00");
		String endStr = CommonUtils.time2Str(yestoday, "yyyy-MM-dd 23:59:59");
		int count = cgrdcordInfoDao.getChannelYesTodayLookCount(channelId, goodId, startStr, endStr);
		return count;
	}
}
