package com.hz21city.xiangqu.service;

import com.hz21city.xiangqu.pojo.ShankeInfo;
import com.hz21city.xiangqu.pojo.ShankeRecordInfo;

public interface IShankeService {

	ShankeInfo getShankeInfo(Long id);

	ShankeInfo getShankeInfoByUser(Long userId);

	void insertShankeInfo(ShankeInfo shanke);

	void insertShankeRecordInfo(ShankeRecordInfo record);

	void updateShankeInfo(ShankeInfo shanke);

}
