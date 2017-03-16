package com.hz21city.xiangqu.service;

import java.util.List;

import com.hz21city.xiangqu.pojo.DailyReword;
import com.hz21city.xiangqu.pojo.DailyRewordInfo;

public interface IDailyService {

	List<DailyReword> getDailyList();

	DailyReword getdetail(Long id);
	
	List<DailyRewordInfo> myreword(Long id);
	
	void getreword(Long id);
}
