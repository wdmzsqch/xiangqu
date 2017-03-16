package com.hz21city.xiangqu.dao;

import org.apache.ibatis.annotations.Param;

public interface IIncomeStatsDao {

	public abstract float getAllIncome(@Param("start") String start, @Param("end") String end);

	public abstract float getAllOutcome(@Param("start") String start, @Param("end") String end);

	public abstract float getAllLotteryIncome(@Param("start") String start, @Param("end") String end);

	public abstract float getAllLotteryOutcome(@Param("start") String start, @Param("end") String end);

	public abstract float getBrushedIncome(@Param("start") String start, @Param("end") String end);

	public abstract int getNewUserCount(@Param("start") String start, @Param("end") String end);

	public abstract int getNewRegisterUserCount(@Param("start") String start, @Param("end") String end);
}