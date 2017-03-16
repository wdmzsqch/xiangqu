package com.hz21city.xiangqu.serviceImpl.manage;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hz21city.xiangqu.dao.IIncomeStatsDao;
import com.hz21city.xiangqu.service.manage.IMaStatsService;

@Service("maStatsService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class MaStatsServiceImpl implements IMaStatsService {

	@Resource
	private IIncomeStatsDao incomeStatsDao;

	@Override
	public float getAllIncome(String start, String end) {
		return incomeStatsDao.getAllIncome(start, end);
	}

	@Override
	public float getAllOutcome(String start, String end) {
		return incomeStatsDao.getAllOutcome(start, end);
	}

	@Override
	public float getAllLotteryIncome(String start, String end) {
		return incomeStatsDao.getAllLotteryIncome(start, end);
	}

	@Override
	public float getAllLotteryOutcome(String start, String end) {
		return incomeStatsDao.getAllLotteryOutcome(start, end);
	}

	@Override
	public float getBrushedIncome(String start, String end) {
		return incomeStatsDao.getBrushedIncome(start, end);
	}

	@Override
	public int getNewUserCount(String start, String end) {
		return incomeStatsDao.getNewUserCount(start, end);
	}

	@Override
	public int getNewRegisterUserCount(String start, String end) {
		return incomeStatsDao.getNewRegisterUserCount(start, end);
	}
}
