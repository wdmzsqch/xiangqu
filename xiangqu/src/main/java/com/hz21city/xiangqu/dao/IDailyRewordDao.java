package com.hz21city.xiangqu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hz21city.xiangqu.pojo.DailyReword;

public interface IDailyRewordDao {
    int deleteByPrimaryKey(Long id);

    int insert(DailyReword record);

    int insertSelective(DailyReword record);

    DailyReword selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DailyReword record);

    int updateByPrimaryKeyWithBLOBs(DailyReword record);

    int updateByPrimaryKey(DailyReword record);

	List<DailyReword> getDailyRewordListByPage( @Param("page") Integer page);

	int getDailyRewordListSize();
}