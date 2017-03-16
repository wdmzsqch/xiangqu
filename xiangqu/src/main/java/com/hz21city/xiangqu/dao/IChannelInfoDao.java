package com.hz21city.xiangqu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hz21city.xiangqu.pojo.ChannelInfo;

public interface IChannelInfoDao {

    int deleteByPrimaryKey(Long id);

    int insert(ChannelInfo record);

    int insertSelective(ChannelInfo record);

    ChannelInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ChannelInfo record);

    int updateByPrimaryKey(ChannelInfo record);

	List<ChannelInfo> getChannelListByPage(@Param("page") Integer page);

	int getChannelListSize();

	Integer getChannelCountCode(@Param("channelCode") String channelCode);

	List<ChannelInfo> getChannelList();

	ChannelInfo getChannelInfoByCode(@Param("channelCode") String channelCode);
}