package com.hz21city.xiangqu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hz21city.xiangqu.pojo.HomeActivity;

public interface IHomeActivityDao {
    int deleteByPrimaryKey(Long id);

    int insert(HomeActivity record);

    int insertSelective(HomeActivity record);

    HomeActivity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HomeActivity record);

    int updateByPrimaryKey(HomeActivity record);

	List<HomeActivity> getAllList();
	
	HomeActivity getActivityByValueKey(@Param("valuekey") String valuekey);

}