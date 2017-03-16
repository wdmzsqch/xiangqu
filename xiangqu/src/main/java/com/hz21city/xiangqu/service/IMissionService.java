package com.hz21city.xiangqu.service;

import java.util.List;

import com.hz21city.xiangqu.pojo.HomeActivity;
import com.hz21city.xiangqu.pojo.MissionInfo;

public interface IMissionService {
	
	public List<MissionInfo> getMissionList(Long userid, Long province, Long city);

	public MissionInfo getMissionById(long id);

	public HomeActivity getHomeActivityByKey(String valuekey);
	
	public void updateMissionInfo(MissionInfo mission);

	public int isUserShare(long userid, int i, Long id);

	public MissionInfo addIncomeRecord(Long shareuserid, long userid, Integer relativeid, Long missionid);

	public List<MissionInfo> getMissionList(Long userid, Long province, Long city, Long area_id);
}
