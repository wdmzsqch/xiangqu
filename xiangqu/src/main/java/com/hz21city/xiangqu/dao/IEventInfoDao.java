package com.hz21city.xiangqu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hz21city.xiangqu.pojo.EventInfo;

public interface IEventInfoDao {

	int deleteByPrimaryKey(Long id);

	int insert(EventInfo record);

	int insertSelective(EventInfo record);

	EventInfo selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(EventInfo record);

	int updateByPrimaryKeyWithBLOBs(EventInfo record);

	int updateByPrimaryKey(EventInfo record);

	List<EventInfo> getAllList();

	List<EventInfo> getEventListByPage(@Param("page") Integer page);
	
	List<EventInfo> getEventListByStoreId(@Param("storeid") long storeid);

	int getUserListSize();
	/* shop */
	
	EventInfo selectByCode(Long id);

}