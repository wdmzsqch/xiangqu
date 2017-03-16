package com.hz21city.xiangqu.dao;

import org.apache.ibatis.annotations.Param;

import com.hz21city.xiangqu.pojo.CheckInfo;

public interface ICheckInfoDao {
    int deleteByPrimaryKey(Long id);

    int insert(CheckInfo record);

    int insertSelective(CheckInfo record);

    CheckInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CheckInfo record);

    int updateByPrimaryKey(CheckInfo record);

	CheckInfo getCheckInfoByAllWays(@Param("type") Integer type,@Param("relativeId") Long relativeId, @Param("checkType") Integer checkType);
}