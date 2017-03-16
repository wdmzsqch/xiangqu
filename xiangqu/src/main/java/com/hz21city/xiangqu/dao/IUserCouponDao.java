package com.hz21city.xiangqu.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.hz21city.xiangqu.pojo.UserCoupon;

public interface IUserCouponDao {
	int deleteByPrimaryKey(Long id);

	int insert(UserCoupon record);

	int insertSelective(UserCoupon record);

	UserCoupon selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(UserCoupon record);

	int updateByPrimaryKey(UserCoupon record);

	List<UserCoupon> getMyCoupon(Long userid);
	
    int userCouponCount(@Param("user_id") long user_id,@Param("coupon_id") long coupon_id);
    
    int getUserCouponByCode(String code);
    
    UserCoupon getUserCouponById(long id);
    
    UserCoupon checkCoupon(String code);
    
    List<UserCoupon> getMyCouponSearch(@Param("userid") Long userid,@Param("type") String type,@Param("datetype") Integer datetype,@Param("shops") Long[] shops);

	List<UserCoupon> getUserCouponListByPage(@Param("page") Integer page, @Param("couponId") Long couponId);

	int getMaUserCouponListSize(@Param("couponId") Long couponId);

}