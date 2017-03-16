package com.hz21city.xiangqu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hz21city.xiangqu.pojo.Industry;

public interface IndustryDao {
	int deleteByPrimaryKey(Long id);

	int insert(Industry record);

	int insertSelective(Industry record);

	Industry selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(Industry record);

	int updateByPrimaryKey(Industry record);

	List<Industry> getAllIndustry();

	List<Industry> getIndustryList(@Param("page") Integer page);

	int getUserListSize();
}