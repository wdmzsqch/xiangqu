package com.hz21city.xiangqu.service.manage;

import java.util.List;

import com.hz21city.xiangqu.pojo.ActivityInfo;

public interface IMaActivityService {

	List<ActivityInfo> getActivityListByPage(int page, String keywords);

	int getActivityListSize(String keywords);

	ActivityInfo selectByPrimaryKey(Long id);

	void insertActivityInfo(ActivityInfo activity);

	void updateActivityInfo(ActivityInfo activity);

}
