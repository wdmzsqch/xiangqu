package com.hz21city.xiangqu.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hz21city.xiangqu.dao.INewYearDinnerDao;
import com.hz21city.xiangqu.dao.INewYearLotteryDao;
import com.hz21city.xiangqu.pojo.NewYearDinner;
import com.hz21city.xiangqu.pojo.NewYearLottery;
import com.hz21city.xiangqu.service.ITimeEventService;

@Service("timeEventService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class TimeEventServiceImpl implements ITimeEventService {

	@Resource
	private INewYearDinnerDao newYearDinnerDao;
	@Resource
	private INewYearLotteryDao newYearLotteryDao;

	@Override
	public List<NewYearDinner> getAll() {
		return newYearDinnerDao.selectAll();
	}

	@Override
	public NewYearDinner getLatest() {
		return newYearDinnerDao.selectNewest();
	}

	@Override
	public void update(NewYearDinner newYearDinner) {
		newYearDinnerDao.updateByPrimaryKeySelective(newYearDinner);
	}

	@Override
	public NewYearDinner getbyid(Long id) {
		return newYearDinnerDao.selectByPrimaryKey(id);
	}

	@Override
	public NewYearLottery addLottery(NewYearLottery newYearLottery) {
		newYearLotteryDao.insertSelective(newYearLottery);
		return null;
	}

	@Override
	public int getLotteryCount(Long user_id) {
		return newYearLotteryDao.getLotteryCount(String.valueOf(user_id));
	}

	@Override
	public List<NewYearLottery> getLotteryList() {
		return newYearLotteryDao.getALLottery();
	}

	@Override
	public int getLotteryCountAlready(String lottery) {
		return newYearLotteryDao.getLotteryCountAlready(lottery);
	}

	@Override
	public void clearTable() {
		newYearLotteryDao.TruncateTable();
	}

	@Override
	public int getUserLotteryCount(Long user_id) {
		return newYearLotteryDao.getUserLotteryCount(String.valueOf(user_id));
	}

	@Override
	public NewYearLottery getNewLotteryData(Long user_id) {
		return newYearLotteryDao.getLotteryData(String.valueOf(user_id));
	}

	@Override
	public void updateLottery(NewYearLottery newYearLottery) {
		newYearLotteryDao.updateByPrimaryKeySelective(newYearLottery);
	}
}
