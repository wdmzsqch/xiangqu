package com.hz21city.xiangqu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hz21city.xiangqu.pojo.CardRecordInfo;

public interface ICardRecordInfoDao {

    int deleteByPrimaryKey(Long id);

    int insert(CardRecordInfo record);

    int insertSelective(CardRecordInfo record);

    CardRecordInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CardRecordInfo record);

    int updateByPrimaryKey(CardRecordInfo record);

	List<CardRecordInfo> getCardRecordListByPage(@Param("page") Integer page);

	int getCardRecordListSize();
}