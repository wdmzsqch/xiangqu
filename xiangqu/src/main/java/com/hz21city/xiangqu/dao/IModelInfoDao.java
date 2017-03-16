package com.hz21city.xiangqu.dao;

import java.util.List;

import com.hz21city.xiangqu.pojo.ModelInfo;

public interface IModelInfoDao {
	int deleteByPrimaryKey(Long id);

    int insert(ModelInfo record);

    int insertSelective(ModelInfo record);

    ModelInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ModelInfo record);

    int updateByPrimaryKey(ModelInfo record);

	List<ModelInfo> getAll();
}