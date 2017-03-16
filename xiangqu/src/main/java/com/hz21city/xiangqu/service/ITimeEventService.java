package com.hz21city.xiangqu.service;

import java.util.List;

import com.hz21city.xiangqu.pojo.NewYearDinner;
import com.hz21city.xiangqu.pojo.NewYearLottery;

public interface ITimeEventService {
	public abstract List<NewYearDinner> getAll();

	public abstract NewYearDinner getLatest();

	public abstract void update(NewYearDinner newYearDinner);

	public abstract NewYearDinner getbyid(Long id);

	public abstract NewYearLottery addLottery(NewYearLottery newYearLottery);

	public abstract int getLotteryCount(Long user_id);

	public abstract List<NewYearLottery> getLotteryList();

	public abstract int getLotteryCountAlready(String lottery);

	public abstract void clearTable();

	public abstract int getUserLotteryCount(Long user_id);
	
	public abstract NewYearLottery getNewLotteryData(Long user_id);
	
	public abstract void updateLottery(NewYearLottery newYearLottery);
}