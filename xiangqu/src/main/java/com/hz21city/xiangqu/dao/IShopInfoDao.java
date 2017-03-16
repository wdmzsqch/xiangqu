package com.hz21city.xiangqu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hz21city.xiangqu.pojo.ShopInfo;

public interface IShopInfoDao {
	int deleteByPrimaryKey(Long id);

	int insert(ShopInfo record);

	int insertSelective(ShopInfo record);

	ShopInfo selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(ShopInfo record);

	int updateByPrimaryKey(ShopInfo record);

	List<ShopInfo> getShopList(@Param("keywords") String keywords, @Param("page") Integer page, @Param("industry_id") Long industry_id, @Param("province") Long province, @Param("city") Long city, @Param("area") Long area);

	int getShopListSize(@Param("keywords") String keywords, @Param("industry_id") Long industry_id, @Param("province") Long province, @Param("city") Long city, @Param("area") Long area);
	
	ShopInfo selectByUserName(@Param("username") String username);
	
	List<ShopInfo> getAllShopList();
	
	int changePwdByShopInfo(ShopInfo record);

	int getAllShopSize();
}