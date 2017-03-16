package com.hz21city.xiangqu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hz21city.xiangqu.pojo.RechargeInfo;

public interface IRechargeInfoDao {
    int deleteByPrimaryKey(Long id);

    int insert(RechargeInfo record);

    int insertSelective(RechargeInfo record);

    RechargeInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RechargeInfo record);

    int updateByPrimaryKey(RechargeInfo record);

    List<RechargeInfo> getRechargeListByToRechargeId(@Param("toRechargeId") Long toRechargeId);

	List<RechargeInfo> getRechargeListByPage(@Param("page") Integer page);

	int getRechargeListSize();
}