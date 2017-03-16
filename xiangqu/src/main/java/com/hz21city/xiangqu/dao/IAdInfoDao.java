package com.hz21city.xiangqu.dao;

import java.util.List;

import com.hz21city.xiangqu.pojo.AdInfo;

public interface IAdInfoDao {
	int deleteByPrimaryKey(Long id);

	int insert(AdInfo record);

	int insertSelective(AdInfo record);

	AdInfo selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(AdInfo record);

	int updateByPrimaryKey(AdInfo record);

	List<AdInfo> selectAll();

	List<AdInfo> getAdListByType(Integer type);
}