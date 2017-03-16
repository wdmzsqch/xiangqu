package com.hz21city.xiangqu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hz21city.xiangqu.pojo.UserAddress;

public interface IUserAddressDao {
    int deleteByPrimaryKey(Long id);

    int insert(UserAddress record);

    int insertSelective(UserAddress record);

    UserAddress selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserAddress record);

    int updateByPrimaryKey(UserAddress record);
    
    UserAddress getUserDefaultAddress(long id);
    
    List<UserAddress> getUserAddress(long userid);
    
    List<UserAddress> getUserAddressList(@Param("userId") Long userId, @Param("page") Integer page);

	int getUserAddressListSize(Long userId);

	void updateUserAddressCombine(@Param("mobileuserid") Long mobileuserid, @Param("combineduserid") Long combineduserid);
}