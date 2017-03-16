package com.hz21city.xiangqu.service.manage;

import java.util.List;

import com.hz21city.xiangqu.pojo.CheckInfo;
import com.hz21city.xiangqu.pojo.IncomeRecord;
import com.hz21city.xiangqu.pojo.Industry;
import com.hz21city.xiangqu.pojo.MissionCatogry;
import com.hz21city.xiangqu.pojo.MissionInfo;
import com.hz21city.xiangqu.pojo.ShopInfo;
import com.hz21city.xiangqu.pojo.TaskNeeds;

public interface IMaMissionService {
	public abstract List<MissionInfo> getMissionList(Integer searchtype, String keywords, Integer pageIndex, Long province, Long city, Long area, Long cotegory_id, Long createUserId);

	public abstract int getMissionListSize(Integer searchtype, String keywords, Long province, Long city, Long area, Long cotegory_id, Long createUserId);

	public abstract MissionInfo getMissionById(Long id);

	public abstract void updateMission(MissionInfo missionInfo);

	public abstract void updateMissionState(Long missionid, Integer state);

	public abstract void delMission(Long missionid);

	public abstract void addMission(MissionInfo missionInfo);

	public abstract List<ShopInfo> getShopList();

	public abstract List<Industry> getIndustryList();

	public abstract List<TaskNeeds> getTaskNeedsList(Long shopId, String keywords, Integer pageIndex);

	public abstract int getTaskNeedsListSize(Long shopId, String keywords);

	public abstract void updateByPrimaryKeySelective(TaskNeeds taskneeds);

	public abstract List<MissionCatogry> getAllCotegoryList();
	
	public abstract int incomeCount(Long id);

	public abstract Integer getMaxSort();

	public abstract int getAllMissionSize();

	public abstract CheckInfo getCheckInfoByAllWays(Integer type, Long relativeId, Integer checkType);

	public abstract void addCheckInfo(CheckInfo checkinfo);

	public abstract void updateCheckInfo(CheckInfo checkinfo);

	public abstract int getAllOutTimesMission(Long admin_id);

	public abstract void delCheckInfo(CheckInfo checkinfo);

	public abstract void addIncomeRecord(IncomeRecord record);

	public abstract void updateMissionAll(MissionInfo missionInfo);

	public abstract int getAreaMissionCount(Long province, Long city, Long area);


}
