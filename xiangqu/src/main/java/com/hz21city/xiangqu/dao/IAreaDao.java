package com.hz21city.xiangqu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hz21city.xiangqu.pojo.Area;

public interface IAreaDao {

	int deleteByPrimaryKey(Long id);

	int insert(Area record);

	int insertSelective(Area record);

	Area selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(Area record);

	int updateByPrimaryKey(Area record);

	List<Area> getAreaListByParentid(@Param("parentid") Long parentid);

	Area getAreaByName(@Param("name") String name);
}