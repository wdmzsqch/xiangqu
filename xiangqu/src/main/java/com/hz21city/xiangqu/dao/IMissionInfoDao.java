package com.hz21city.xiangqu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hz21city.xiangqu.pojo.MissionInfo;

public interface IMissionInfoDao {

	int deleteByPrimaryKey(Long id);

	int insert(MissionInfo record);

	int insertSelective(MissionInfo record);

	MissionInfo selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(MissionInfo record);

	int updateByPrimaryKey(MissionInfo record);

	List<MissionInfo> getMissionList();

	List<MissionInfo> getMaMissionList(@Param("type") Integer type, @Param("keywords") String keywords, @Param("page") Integer page, @Param("province") Long province, @Param("city") Long city,
			@Param("area") Long area, @Param("cotegoryId") Long cotegoryId, @Param("createUserId") Long createUserId);

	int getMaMissionListSize(@Param("type") Integer type, @Param("keywords") String keywords, @Param("province") Long province, @Param("city") Long city, @Param("area") Long area,
			@Param("cotegoryId") Long cotegoryId, @Param("createUserId") Long createUserId);

	MissionInfo getMaMissionDetail(Long id);

	List<MissionInfo> getMissionListByType(@Param("shopId") Long shopId, @Param("type") int type);

	// 商店今日或当月已投放金额
	public abstract float getTotalMoneyByShop(@Param("shopId") Long shopId, @Param("start") String start, @Param("end") String end);

	// 商店今日或当月剩余可投放金额
	public abstract float getLastMoney(@Param("shopId") Long shopId, @Param("start") String start, @Param("end") String end);

	// 商店今日或当月任务数
	public abstract int getMissionCount(@Param("shopId") Long shopId, @Param("start") String start, @Param("end") String end);

	// 商店今日或当月任务
	public abstract List<MissionInfo> getMissionListByShop(@Param("shopId") Long shopId, @Param("start") String start, @Param("end") String end);

	List<MissionInfo> getMissionListByLocal(@Param("province") Long province, @Param("city") Long city);

	// 商店今日或当月总点击量
	public abstract int getTotalCountByShop(@Param("shopId") Long shopId, @Param("start") String start, @Param("end") String end);

	// 商店今日或当月剩余可投放数
	public abstract int getRamainCount(@Param("shopId") Long shopId, @Param("start") String start, @Param("end") String end);

	List<MissionInfo> getMissionListBySort();

	int getAllMissionSize();

	int getAllOutTimesMission(@Param("createUserId") Long createUserId);

	int getMissionUrlArticleCount(@Param("url") String url);

	List<MissionInfo> getMissionListByAllLocal(@Param("province") Long province, @Param("city") Long city, @Param("area") Long area);

	int getAreaMissionCount(@Param("province") Long province, @Param("city") Long city, @Param("area") Long area);

	int getMissionBrushCount(Long id);
}