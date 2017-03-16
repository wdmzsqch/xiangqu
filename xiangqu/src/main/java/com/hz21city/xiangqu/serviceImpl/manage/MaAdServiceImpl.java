package com.hz21city.xiangqu.serviceImpl.manage;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hz21city.xiangqu.dao.IAdInfoDao;
import com.hz21city.xiangqu.pojo.AdInfo;
import com.hz21city.xiangqu.service.manage.IMaAdService;

@Service("maAdService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class MaAdServiceImpl implements IMaAdService {

	@Resource
	private IAdInfoDao adInfoDao;

	@Override
	public List<AdInfo> getAdList() {
		return adInfoDao.selectAll();
	}

	@Override
	public void editAd(AdInfo ad) {
		adInfoDao.updateByPrimaryKeySelective(ad);
	}

	@Override
	public void addAd(AdInfo ad) {
		adInfoDao.insertSelective(ad);
	}

	@Override
	public void delAd(Long adid) {
		adInfoDao.deleteByPrimaryKey(adid);
	}

	@Override
	public List<AdInfo> getAdListByType(Integer type) {
		return adInfoDao.getAdListByType(type);
	}
}
