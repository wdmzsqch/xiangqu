package com.hz21city.xiangqu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hz21city.xiangqu.pojo.WTicketInfo;

public interface IWTicketInfoDao {
	
    int deleteByPrimaryKey(Long id);

    int insert(WTicketInfo record);

    int insertSelective(WTicketInfo record);

    WTicketInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WTicketInfo record);

    int updateByPrimaryKey(WTicketInfo record);

	List<WTicketInfo> getWTicketListByPage(@Param("page") Integer page, @Param("state") Integer state, @Param("ticketNum") String ticketNum);

	int getWTicketListSize(@Param("state") Integer state, @Param("ticketNum") String ticketNum);

	int getAllWTicketCount();

	int getAllUseWTicketCount();

	int getAllSellWTicketCount();

	List<WTicketInfo> getMyTicketListByPage(@Param("page") Integer page, @Param("wuser_id") Long wuser_id);

	int getMyTicketListSize(@Param("wuser_id") Long wuser_id);

	int getAllMyUseTicketCount(@Param("wuser_id") Long wuser_id);

	int getAllMyNouseWTicketCount(@Param("wuser_id") Long wuser_id);

	WTicketInfo getWTicketByTickNum(@Param("ticketNum") String ticketNum);

	List<WTicketInfo> getNoUseTicketList();
}