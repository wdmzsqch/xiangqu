package com.hz21city.xiangqu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hz21city.xiangqu.pojo.WOrderInfo;

public interface IWOrderInfoDao {

	int deleteByPrimaryKey(Long id);

    int insert(WOrderInfo record);

    int insertSelective(WOrderInfo record);

    WOrderInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WOrderInfo record);

    int updateByPrimaryKey(WOrderInfo record);

	List<WOrderInfo> getWOrderListByPage(@Param("keywords") String keywords, @Param("page") Integer page, @Param("searchType") Integer searchType, @Param("start") String start, @Param("end") String end);

	int getWOrderListSize(@Param("keywords") String keywords, @Param("searchType") Integer searchType, @Param("start") String start, @Param("end") String end);

	int getAllWOrderCount();

	int getSumWOrderCount(@Param("keywords") String keywords, @Param("searchType") Integer searchType, @Param("start") String start, @Param("end") String end);
}