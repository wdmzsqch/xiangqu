package com.hz21city.xiangqu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hz21city.xiangqu.pojo.SystemMessage;

public interface ISystemMessageDao {
    int deleteByPrimaryKey(Long id);

    int insert(SystemMessage record);

    int insertSelective(SystemMessage record);

    SystemMessage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SystemMessage record);

    int updateByPrimaryKeyWithBLOBs(SystemMessage record);

    int updateByPrimaryKey(SystemMessage record);
    
    List<SystemMessage> getSystemMessageListByPage(@Param("page") int page);
    
    int getSystemMessageListSize();
    
    List<SystemMessage> getSysMessage(int type);
    
    SystemMessage getSysMessageById(long id);

	int getNewSysmessage(@Param("start") String start);
}