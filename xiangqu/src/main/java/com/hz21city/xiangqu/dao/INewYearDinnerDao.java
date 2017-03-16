package com.hz21city.xiangqu.dao;

import java.util.List;

import com.hz21city.xiangqu.pojo.NewYearDinner;

public interface INewYearDinnerDao {

	int deleteByPrimaryKey(Long id);

	int insert(NewYearDinner record);

	int insertSelective(NewYearDinner record);

	NewYearDinner selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(NewYearDinner record);

	int updateByPrimaryKey(NewYearDinner record);

	NewYearDinner selectNewest();

	List<NewYearDinner> selectAll();
}