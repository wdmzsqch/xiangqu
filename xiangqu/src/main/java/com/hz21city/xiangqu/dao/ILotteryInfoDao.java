package com.hz21city.xiangqu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hz21city.xiangqu.pojo.LotteryInfo;

public interface ILotteryInfoDao {

	int deleteByPrimaryKey(Long id);

    int insert(LotteryInfo record);

    int insertSelective(LotteryInfo record);

    LotteryInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LotteryInfo record);

    int updateByPrimaryKey(LotteryInfo record);

	List<LotteryInfo> getLotteryListByUser(@Param("userid") Long userid);
}