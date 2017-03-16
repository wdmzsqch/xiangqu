package com.hz21city.xiangqu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hz21city.xiangqu.pojo.UserInfo;
import com.hz21city.xiangqu.pojo.UserInviteCount;

public interface IUserInfoDao {

	int deleteByPrimaryKey(Long id);

	int insert(UserInfo record);

	int insertSelective(UserInfo record);

	UserInfo selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(UserInfo record);

	int updateByPrimaryKey(UserInfo record);

	List<UserInfo> getUserList(@Param("keywords") String keywords, @Param("page") Integer page, @Param("searchType") Integer searchType, @Param("start") String start, @Param("end") String end,
			@Param("UCollectCount") Float UCollectCount, @Param("DCollectCount") Float DCollectCount);

	List<UserInfo> getUserSortList(@Param("keywords") String keywords, @Param("page") Integer page, @Param("searchType") Integer searchType, @Param("start") String start, @Param("end") String end,
			@Param("UCollectCount") Float UCollectCount, @Param("DCollectCount") Float DCollectCount, @Param("isSort") Integer isSort, @Param("baSort") Integer baSort,
			@Param("incomeSort") Integer incomeSort);

	int getUserListSize(@Param("keywords") String keywords, @Param("searchType") Integer searchType, @Param("start") String start, @Param("end") String end,
			@Param("UCollectCount") Float UCollectCount, @Param("DCollectCount") Float DCollectCount);

	UserInfo getUserModeifyById(Long id);

	UserInfo selectByuserName(@Param("userName") String userName);

	UserInfo getUserInfoByQQ_openid(@Param("qq_openid") String qq_openid);

	UserInfo getUserInfoByWeixin_openid(@Param("weixin_openid") String weixin_openid);

	UserInfo getUserInfoByNameOrMoblie(@Param("userName") String userName, @Param("moblie") String moblie);

	int getAllUserSize();

	List<UserInfo> AllUserList();

	UserInfo getUserInfoByMoblie(@Param("moblie") String moblie);

	UserInfo getUserInfoByInvietCode(@Param("invietCode") String invietCode);

	Integer getUserCountByCode(@Param("invietCode") String invietCode);

	UserInfo getUserRandom();

	List<UserInfo> getUserListHasntInviteCode();

	void updateUserInviteCombine(@Param("mobileuserid") Long mobileuserid, @Param("combineduserid") Long combineduserid);

	List<UserInviteCount> getUserInviteData(@Param("start") String start, @Param("end") String end);

	// ---------------推广员----------------------
	List<UserInfo> getPromoterList(@Param("keywords") String keywords, @Param("page") Integer page);

	int getPromoterListSize(@Param("keywords") String keywords);

	List<UserInfo> getPromoterList(@Param("userId") Long userId, @Param("type") Integer type);
}