package com.hz21city.xiangqu.dao;

import com.hz21city.xiangqu.pojo.ShankeRecordInfo;

public interface IShankeRecordInfoDao {

	int deleteByPrimaryKey(Long id);

    int insert(ShankeRecordInfo record);

    int insertSelective(ShankeRecordInfo record);

    ShankeRecordInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShankeRecordInfo record);

    int updateByPrimaryKey(ShankeRecordInfo record);
}