package com.hz21city.xiangqu.dao;

import org.apache.ibatis.annotations.Param;

import com.hz21city.xiangqu.pojo.CgrecordInfo;

public interface ICgrecordInfoDao {

	int deleteByPrimaryKey(Long id);

    int insert(CgrecordInfo record);

    int insertSelective(CgrecordInfo record);

    CgrecordInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CgrecordInfo record);

    int updateByPrimaryKey(CgrecordInfo record);

	int getChannelLookCount(@Param("channelId")Long channelId, @Param("goodId")Long goodId);

	int getChannelYesTodayLookCount(@Param("channelId") Long channelId, @Param("goodId") Long goodId, @Param("start") String start, @Param("end") String end);
}