package com.hz21city.xiangqu.service.manage;

import java.util.List;

import com.hz21city.xiangqu.pojo.DailyReword;

public interface IMaDailyRewordService {

	List<DailyReword> getDailyRewordListByPage(int page);

	int getDailyRewordListSize();

	DailyReword getDailyRewordById(Long id);

	void addDailyReword(DailyReword dailyreword);

	void updateDailyReword(DailyReword reword);

}
