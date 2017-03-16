package com.hz21city.xiangqu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hz21city.xiangqu.pojo.EventSign;

public interface IEventSignDao {

	int deleteByPrimaryKey(Long id);

	int insert(EventSign record);

	int insertSelective(EventSign record);

	EventSign selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(EventSign record);

	int updateByPrimaryKey(EventSign record);

	EventSign getEventSignByEventUser(@Param("eventId") Long eventId, @Param("userId") Long userId);

	List<EventSign> getSubjectListByPage(@Param("eventId") Long eventId, @Param("page") Integer page, @Param("code") String code, @Param("phone") String phone,
			@Param("searchType") Integer searchType);

	int getSubjectListSize(@Param("eventId") Long event_id, @Param("code") String code, @Param("phone") String phone);

	EventSign getSignInfoByCode(@Param("code") String code);

	Integer getEventSignCountByCode(@Param("code") String code);

	int getEventSignCount(@Param("event_id") Long event_id, @Param("isJoined") Integer isJoined);

	Integer getEventSignCountNotNull(@Param("event_id") Long event_id);

	Integer getEventSignCountNull(@Param("event_id") Long event_id);

	List<EventSign> getEventSignByUser(@Param("userid") Long userid);

	void updateCombineSign(@Param("mobileuserid") Long mobileuserid, @Param("combineduserid") Long combineduserid);
}