package com.hz21city.xiangqu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hz21city.xiangqu.pojo.GoodsInfo;

public interface IGoodsInfoDao {
	int deleteByPrimaryKey(Long id);

	int insert(GoodsInfo record);

	int insertSelective(GoodsInfo record);

	GoodsInfo selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(GoodsInfo record);

	int updateByPrimaryKey(GoodsInfo record);

	List<GoodsInfo> getGoodsList(@Param("sort") Integer sort,@Param("goodstype") Integer goodstype, @Param("categoryId") Long categoryId);

	List<GoodsInfo> getMaGoodsList(@Param("keywords") String keywords, @Param("type") Integer type, @Param("page") Integer page, @Param("storeId") Long storeId, @Param("categoryId")  Long categoryId,  @Param("delFlg") Integer delFlg);

	int delGoods(Long[] ids);

	int putaway(Long[] ids);

	int soldout(Long[] ids);

	int getGoodsListCount(@Param("keywords") String keywords, @Param("type") Integer type, @Param("storeId") Long storeId, @Param("categoryId")  Long categoryId,  @Param("delFlg") Integer delFlg);
	
	List<GoodsInfo> getAllGoodsList();

	List<GoodsInfo> getGoodsListBySort();

	int getAllGoodSize();
}