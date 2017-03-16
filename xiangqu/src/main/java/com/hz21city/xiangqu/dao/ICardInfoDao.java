package com.hz21city.xiangqu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hz21city.xiangqu.pojo.CardInfo;

public interface ICardInfoDao {

	int deleteByPrimaryKey(Long id);

    int insert(CardInfo record);

    int insertSelective(CardInfo record);

    CardInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CardInfo record);

    int updateByPrimaryKey(CardInfo record);

	List<CardInfo> getCardListByPage(@Param("page") Integer page);

	int getCardListSize();

	CardInfo getCardByNum(@Param("cardNum") String cardNum);

	CardInfo getCardInfo(@Param("cardNum") String cardNum, @Param("cardPwd") String cardPwd, @Param("state") int state);

}