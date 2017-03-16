package com.hz21city.xiangqu.serviceImpl.manage;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hz21city.xiangqu.dao.IDailyRewordDao;
import com.hz21city.xiangqu.pojo.DailyReword;
import com.hz21city.xiangqu.service.manage.IMaDailyRewordService;

@Service("maDailyRewordService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class MaDailyRewordService implements IMaDailyRewordService{
	
	@Resource
	private IDailyRewordDao dailyRewordDao;

	@Override
	public List<DailyReword> getDailyRewordListByPage(int page) {
		List<DailyReword> list = dailyRewordDao.getDailyRewordListByPage((page - 1) * 15);
		return list;
	}

	@Override
	public int getDailyRewordListSize() {
		int total = dailyRewordDao.getDailyRewordListSize();
		return (int) Math.ceil((double) total / 15.0d);
	}

	@Override
	public DailyReword getDailyRewordById(Long id) {
		return dailyRewordDao.selectByPrimaryKey(id);
	}

	@Override
	public void addDailyReword(DailyReword dailyreword) {
		dailyRewordDao.insertSelective(dailyreword);
	}

	@Override
	public void updateDailyReword(DailyReword reword) {
		dailyRewordDao.updateByPrimaryKeySelective(reword);
	}

}
