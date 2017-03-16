package com.hz21city.xiangqu.service.shop;

import java.util.List;

import com.hz21city.xiangqu.pojo.MissionInfo;
import com.hz21city.xiangqu.pojo.TaskNeeds;

public interface IShMissionService {

	List<MissionInfo> getMissionInfoByType(long storeid,int type);
	
	MissionInfo getMissionDetail(long id);
	
	void addTaskNeeds(TaskNeeds taskneed);
}
