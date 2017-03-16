package com.hz21city.xiangqu.serviceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hz21city.xiangqu.dao.IShankeInfoDao;
import com.hz21city.xiangqu.dao.IShankeRecordInfoDao;
import com.hz21city.xiangqu.pojo.ShankeInfo;
import com.hz21city.xiangqu.pojo.ShankeRecordInfo;
import com.hz21city.xiangqu.service.IShankeService;

@Service("shankeService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ShankeServiceImpl implements IShankeService{
	
	@Resource
	private IShankeInfoDao shankeInfoDao;
	
	@Resource
	private IShankeRecordInfoDao shankeRecordInfoDao;

	@Override
	public ShankeInfo getShankeInfo(Long id) {
		ShankeInfo info = shankeInfoDao.selectByPrimaryKey(id);
		return info;
	}

	@Override
	public ShankeInfo getShankeInfoByUser(Long userId) {
		ShankeInfo info = shankeInfoDao.getShankeInfoByUser(userId);
		return info;
	}

	@Override
	public void insertShankeInfo(ShankeInfo shanke) {
		shankeInfoDao.insertSelective(shanke);
	}

	@Override
	public void insertShankeRecordInfo(ShankeRecordInfo record) {
		shankeRecordInfoDao.insertSelective(record);
	}

	@Override
	public void updateShankeInfo(ShankeInfo shanke) {
		shankeInfoDao.updateByPrimaryKeySelective(shanke);
	}

}
