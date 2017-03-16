package com.hz21city.xiangqu.serviceImpl.manage;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.dao.IActivityInfoDao;
import com.hz21city.xiangqu.pojo.ActivityInfo;
import com.hz21city.xiangqu.service.manage.IMaActivityService;

@Service("maActivityService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class MaActivityServiceImpl implements IMaActivityService{
	
	@Resource
	private IActivityInfoDao activityDao;

	@Override
	public List<ActivityInfo> getActivityListByPage(int page, String keywords) {
		if (!CommonUtils.isEmptyString(keywords)) {
			keywords = "%" + keywords + "%";
		}
		//一页15条
		List<ActivityInfo> list = activityDao.getActivityListByPage(keywords,(page - 1) * 15);
		return list;
	}

	@Override
	public int getActivityListSize(String keywords) {
		if (!CommonUtils.isEmptyString(keywords)) {
			keywords = "%" + keywords + "%";
		}
		int total = activityDao.getActivityListSize(keywords);
		return (int) Math.ceil((double) total / 15.0d);
	}

	@Override
	public ActivityInfo selectByPrimaryKey(Long id) {
		ActivityInfo info = activityDao.selectByPrimaryKey(id);
		return info;
	}

	@Override
	public void insertActivityInfo(ActivityInfo activity) {
		activityDao.insertSelective(activity);
	}

	@Override
	public void updateActivityInfo(ActivityInfo activity) {
		activityDao.updateByPrimaryKeySelective(activity);
	}

}
