package com.hz21city.xiangqu.service.manage;

import java.util.List;

import com.hz21city.xiangqu.pojo.Industry;

public interface IMaIndustryService {

	public abstract List<Industry> getIndustryList(Integer page);

	public abstract int getIndustryListSize();

	public abstract Industry selectByPrimaryKey(Long id);

	public abstract void insert(Industry industry);

	public abstract void updateByPrimaryKeySelective(Industry industry);

	public abstract void deleteByPrimaryKey(Long id);

}
