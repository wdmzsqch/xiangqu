package com.hz21city.xiangqu.dao;

import java.util.List;

import com.hz21city.xiangqu.pojo.StoreCotegory;

public interface IStoreCotegoryDao {
	int deleteByPrimaryKey(Long id);

	int insert(StoreCotegory record);

	int insertSelective(StoreCotegory record);

	StoreCotegory selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(StoreCotegory record);

	int updateByPrimaryKey(StoreCotegory record);

	List<StoreCotegory> selectAll();
}