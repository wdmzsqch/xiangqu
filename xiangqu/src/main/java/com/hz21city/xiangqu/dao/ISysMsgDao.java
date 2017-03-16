package com.hz21city.xiangqu.dao;

import com.hz21city.xiangqu.pojo.SysMsg;

public interface ISysMsgDao {
    int deleteByPrimaryKey(Long id);

    int insert(SysMsg record);

    int insertSelective(SysMsg record);

    SysMsg selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysMsg record);

    int updateByPrimaryKey(SysMsg record);
    
}