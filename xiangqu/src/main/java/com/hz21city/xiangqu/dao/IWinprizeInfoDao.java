package com.hz21city.xiangqu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hz21city.xiangqu.pojo.WinprizeInfo;

public interface IWinprizeInfoDao {
    int deleteByPrimaryKey(Long id);

    int insert(WinprizeInfo record);

    int insertSelective(WinprizeInfo record);

    WinprizeInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WinprizeInfo record);

    int updateByPrimaryKey(WinprizeInfo record);

	WinprizeInfo getWinprizeInfoByLottery(@Param("lotteryid")Long lotteryid);

	List<WinprizeInfo> getWinprizeInfoList(@Param("userid") Long userid);

	List<WinprizeInfo> getUserPrizeListByPage(@Param("page") int page, @Param("userName") String userName, @Param("moblie") String moblie, @Param("state") Integer state);

	int getUserPrizeListSize(@Param("userName") String userName, @Param("moblie") String moblie, @Param("state") Integer state);

}