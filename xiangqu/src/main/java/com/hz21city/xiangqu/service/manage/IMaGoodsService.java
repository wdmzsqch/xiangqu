package com.hz21city.xiangqu.service.manage;

import java.util.List;

import com.hz21city.xiangqu.pojo.ChannelInfo;
import com.hz21city.xiangqu.pojo.GoodsInfo;
import com.hz21city.xiangqu.pojo.StoreCotegory;

public interface IMaGoodsService {
	public abstract GoodsInfo getGoodsById(long id);

	public abstract void addGoods(GoodsInfo goods);

	public abstract void editGoods(GoodsInfo goods);

	public abstract void delGoodsById(long id);

	public abstract List<GoodsInfo> getGoodsList(String keywords, Integer type, Integer page,Long storeId, Long categoryId, Integer delFlg);

	public abstract int getGoodsListSize(String keywords, Integer type, Long storeId, Long categoryId, Integer delFlg);

	public abstract void changeGoods(Integer type, Integer value);

	public abstract List<StoreCotegory> getCotegoryList();

	public abstract void changeGoodsState(Long[] ids, Integer type);

	public abstract Integer getGoodMaxSort();

	public abstract int getAllGoodSize();

	public abstract List<ChannelInfo> getChannelList();

	public abstract ChannelInfo getChannlInfoByID(Long channel_id);

	public abstract int getChannelSellCount(String channelCode, Long goodsId);

	public abstract int getChannelYesTodaySellCount(String channelCode, Long goodsId);

	public abstract int getChannelLookCount(Long channelId, Long goodId);

	public abstract int getChannelYesTodayLookCount(Long channelId, Long goodId);
}
