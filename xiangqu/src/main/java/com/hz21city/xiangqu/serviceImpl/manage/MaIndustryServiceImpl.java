package com.hz21city.xiangqu.serviceImpl.manage;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hz21city.xiangqu.dao.IndustryDao;
import com.hz21city.xiangqu.pojo.Industry;
import com.hz21city.xiangqu.service.manage.IMaIndustryService;

@Service("maIndustryService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class MaIndustryServiceImpl implements IMaIndustryService {
	@Resource
	private IndustryDao industryDao;

	@Override
	public List<Industry> getIndustryList(Integer page) {
		if (page == null) {
			page = 0;
		} else if (page > 0) {
			page = (page - 1) * 10;
		}
		return industryDao.getIndustryList(page);
	}

	@Override
	public int getIndustryListSize() {
		int total = industryDao.getUserListSize();
		return (int) Math.ceil((double) total / 10.0d);
	}

	@Override
	public Industry selectByPrimaryKey(Long id) {
		return industryDao.selectByPrimaryKey(id);
	}

	@Override
	public void insert(Industry industry) {
		industryDao.insertSelective(industry);
	}

	@Override
	public void updateByPrimaryKeySelective(Industry industry) {
		industryDao.updateByPrimaryKeySelective(industry);
	}

	@Override
	public void deleteByPrimaryKey(Long id) {
		industryDao.deleteByPrimaryKey(id);
	}


}
