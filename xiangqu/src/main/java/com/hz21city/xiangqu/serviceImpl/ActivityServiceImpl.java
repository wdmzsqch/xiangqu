package com.hz21city.xiangqu.serviceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hz21city.xiangqu.dao.IActivityInfoDao;
import com.hz21city.xiangqu.pojo.ActivityInfo;
import com.hz21city.xiangqu.service.IActivityService;

@Service("activityService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ActivityServiceImpl implements IActivityService{
	
	@Resource
	private IActivityInfoDao activityInfoDao;

	@Override
	public ActivityInfo selectByPrimaryKey(Long id) {
		ActivityInfo info = activityInfoDao.selectByPrimaryKey(id);
		return info;
	}

}
