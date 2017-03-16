package com.hz21city.xiangqu.serviceImpl.manage;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hz21city.xiangqu.dao.IModelInfoDao;
import com.hz21city.xiangqu.pojo.ModelInfo;
import com.hz21city.xiangqu.service.manage.IMaModelService;

@Service("maModelService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class MaModelService implements IMaModelService{
	
	@Resource
	private IModelInfoDao modelInfoDao;

	@Override
	public List<ModelInfo> getAllModelList() {
		return modelInfoDao.getAll();
	}

	@Override
	public void addModel(ModelInfo modelInfo) {
		modelInfoDao.insertSelective(modelInfo);
	}

	@Override
	public void editModel(ModelInfo modelInfo) {
		modelInfoDao.updateByPrimaryKeySelective(modelInfo);
	}

	@Override
	public ModelInfo getModelInfo(Long modelid) {
		return modelInfoDao.selectByPrimaryKey(modelid);
	}

	@Override
	public void delModelInfo(Long modelid) {
		modelInfoDao.deleteByPrimaryKey(modelid);
	}

}
