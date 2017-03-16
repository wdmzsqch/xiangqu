package com.hz21city.xiangqu.service.manage;

import java.util.List;

import com.hz21city.xiangqu.pojo.Area;
import com.hz21city.xiangqu.pojo.Industry;
import com.hz21city.xiangqu.pojo.ShopInfo;
import com.hz21city.xiangqu.pojo.StoreCotegory;

public interface IMaShopService {
	public abstract List<ShopInfo> getShopList(String keywords, Integer page, Long industry_id, Long province, Long city, Long area );

	public abstract int getShopListSize(String keywords, Long industry_id, Long province, Long city, Long area);

	public abstract ShopInfo getShopByid(Long id);

	public abstract void editShop(ShopInfo shopInfo);

	public abstract void delShop(Long id);

	public abstract List<Area> getAreaListByParentid(Long parentid);

	public abstract Area getArea(Long province);

	public abstract List<Industry> getIndustryList();

	public abstract List<ShopInfo> getAllShopList();

	public abstract List<StoreCotegory> getAllCotegoryList();

	public abstract int getAllShopSize();


}
