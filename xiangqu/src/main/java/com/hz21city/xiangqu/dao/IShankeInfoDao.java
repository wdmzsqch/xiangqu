package com.hz21city.xiangqu.dao;

import com.hz21city.xiangqu.pojo.ShankeInfo;

public interface IShankeInfoDao {

	int deleteByPrimaryKey(Long id);

    int insert(ShankeInfo record);

    int insertSelective(ShankeInfo record);

    ShankeInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShankeInfo record);

    int updateByPrimaryKey(ShankeInfo record);

	ShankeInfo getShankeInfoByUser(Long user_id);
}