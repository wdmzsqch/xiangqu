package com.hz21city.xiangqu.dao;


import org.apache.ibatis.annotations.Param;

import com.hz21city.xiangqu.pojo.VerifyCode;

public interface IVerifyCodeDao {
    int deleteByPrimaryKey(Long id);

    int insert(VerifyCode record);

    int insertSelective(VerifyCode record);

    VerifyCode selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VerifyCode record);

    int updateByPrimaryKey(VerifyCode record);

	VerifyCode getVerifyCodeByMoblieandVcode(@Param("phone") String phone, @Param("code") String code);

	VerifyCode getVerifyCodeByMoblie(@Param("phone") String phone);

}