package com.hz21city.xiangqu.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hz21city.xiangqu.dao.IStoreCotegoryDao;
import com.hz21city.xiangqu.pojo.StoreCotegory;
import com.hz21city.xiangqu.service.ICategoryService;

@Service("categoryService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class CategoryServiceImpl implements ICategoryService{

	@Resource
	private IStoreCotegoryDao storeCotegoryDao;
	
	@Override
	public List<StoreCotegory> getCotegoryList() {
		List<StoreCotegory> cotelist = storeCotegoryDao.selectAll();
		return cotelist;
	}
	
}
