package com.hz21city.xiangqu.dao;

import java.util.List;

import com.hz21city.xiangqu.pojo.NewYearLottery;

public interface INewYearLotteryDao {

	int deleteByPrimaryKey(Long id);

	int insert(NewYearLottery record);

	int insertSelective(NewYearLottery record);

	NewYearLottery selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(NewYearLottery record);

	int updateByPrimaryKey(NewYearLottery record);

	NewYearLottery getLotteryByMobile(String mobile);

	int getLotteryCount(String user_id);

	int getUserLotteryCount(String user_id);

	List<NewYearLottery> getALLottery();

	int getLotteryCountAlready(String lotteryname);

	int TruncateTable();

	NewYearLottery getLotteryData(String user_id);
}