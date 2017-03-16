package com.hz21city.xiangqu.dao;

import com.hz21city.xiangqu.pojo.UserIncome;

public interface IUserIncomeDao {
    int deleteByPrimaryKey(Long id);

    int insert(UserIncome record);

    int insertSelective(UserIncome record);

    UserIncome selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserIncome record);

    int updateByPrimaryKey(UserIncome record);
}