package com.hz21city.xiangqu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hz21city.xiangqu.pojo.ActivityInfo;

public interface IActivityInfoDao {
    int deleteByPrimaryKey(Long id);

    int insert(ActivityInfo record);

    int insertSelective(ActivityInfo record);

    ActivityInfo selectByPrimaryKey(Long id);
    
    int updateByPrimaryKeySelective(ActivityInfo record);

    int updateByPrimaryKeyWithBLOBs(ActivityInfo record);

    int updateByPrimaryKey(ActivityInfo record);
    
    List<ActivityInfo> getActivityListByPage(@Param("keywords") String keywords, @Param("page") Integer page);

	int getActivityListSize(@Param("keywords") String keywords);

}