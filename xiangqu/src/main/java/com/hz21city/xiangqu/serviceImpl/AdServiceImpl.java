package com.hz21city.xiangqu.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hz21city.xiangqu.dao.IAdInfoDao;
import com.hz21city.xiangqu.dao.IHomeActivityDao;
import com.hz21city.xiangqu.pojo.AdInfo;
import com.hz21city.xiangqu.pojo.HomeActivity;
import com.hz21city.xiangqu.service.IAdService;

@Service("adService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class AdServiceImpl implements IAdService{

	@Resource
	private IAdInfoDao adInfoDao;
	
	@Resource
	private IHomeActivityDao homeActivityDao;
	
	@Override
	public List<AdInfo> getAllAdList() {
		List<AdInfo> adlist = adInfoDao.selectAll();
		return adlist;
	}

	@Override
	public List<AdInfo> getAdListByType(Integer type) {
		return adInfoDao.getAdListByType(type);
	}

	@Override
	public List<HomeActivity> getHomeActivities() {
		return homeActivityDao.getAllList();
	}

}
