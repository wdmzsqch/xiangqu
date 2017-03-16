package com.hz21city.xiangqu.service.manage;

import java.util.List;

import com.hz21city.xiangqu.pojo.ModelInfo;

public interface IMaModelService {

	List<ModelInfo> getAllModelList();

	void addModel(ModelInfo modelInfo);

	void editModel(ModelInfo modelInfo);

	ModelInfo getModelInfo(Long modelid);

	void delModelInfo(Long modelid);

}
