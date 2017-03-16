package com.hz21city.xiangqu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hz21city.xiangqu.pojo.TaskNeeds;

public interface ITaskNeedsDao {
    int deleteByPrimaryKey(Long id);

    int insert(TaskNeeds record);

    int insertSelective(TaskNeeds record);

    TaskNeeds selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TaskNeeds record);

    int updateByPrimaryKey(TaskNeeds record);

	List<TaskNeeds> getTaskNeedsList(@Param("shopId") Long shopId, @Param("keywords") String keywords, @Param("page") Integer page);

	int getTaskNeedsListSize(@Param("shopId") Long shopId, @Param("keywords") String keywords);
}