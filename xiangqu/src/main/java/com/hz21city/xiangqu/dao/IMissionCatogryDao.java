package com.hz21city.xiangqu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hz21city.xiangqu.pojo.MissionCatogry;

public interface IMissionCatogryDao {
	int deleteByPrimaryKey(Long id);

	int insert(MissionCatogry record);

	int insertSelective(MissionCatogry record);

	MissionCatogry selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(MissionCatogry record);

	int updateByPrimaryKey(MissionCatogry record);

	List<MissionCatogry> getAllMissionCatogry();

	List<MissionCatogry> getIndustryList(@Param("page") Integer page);

	int getUserListSize();

	List<MissionCatogry> getMissionCatogryList(@Param("page") Integer page);

	int getMissionCatogryListSize();
}