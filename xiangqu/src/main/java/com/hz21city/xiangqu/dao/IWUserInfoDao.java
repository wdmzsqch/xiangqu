package com.hz21city.xiangqu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hz21city.xiangqu.pojo.WUserInfo;

public interface IWUserInfoDao {

    int deleteByPrimaryKey(Long id);

    int insert(WUserInfo record);

    int insertSelective(WUserInfo record);

    WUserInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WUserInfo record);

    int updateByPrimaryKey(WUserInfo record);

	List<WUserInfo> getWUserListByPage(@Param("keywords") String keywords, @Param("page") Integer page, @Param("searchType") Integer searchType);

	int getWUserListSize(@Param("keywords") String keywords, @Param("searchType") Integer searchType);

	int getAllWUserCount();

	WUserInfo getWUserInfoByPhone(@Param("phone") String phone);
}