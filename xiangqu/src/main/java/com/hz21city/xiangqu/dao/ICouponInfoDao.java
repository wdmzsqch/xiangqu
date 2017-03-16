package com.hz21city.xiangqu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hz21city.xiangqu.pojo.CouponInfo;

public interface ICouponInfoDao {
	int deleteByPrimaryKey(Long id);

	int insert(CouponInfo record);

	int insertSelective(CouponInfo record);

	CouponInfo selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(CouponInfo record);

	int updateByPrimaryKey(CouponInfo record);

	List<Long> getStoreIdByCoupon();

	List<CouponInfo> getCouponListByStore(@Param("store_id") long store_id);
	
	List<CouponInfo> getSearchCouponListByStore(@Param("store_id") long store_id,@Param("datetype") Integer datetype,@Param("type") String type);

	List<CouponInfo> getCouponListByPage(@Param("keywords") String keywords, @Param("end") String end, @Param("page") Integer page);

	int getMaCouponListSize(@Param("keywords") String keywords, @Param("end") String end);

	Integer getCouponCountByCode(@Param("code") String code);

	CouponInfo CouponInfoByCode(@Param("code") String code);

	int getAllCouponSize();
}