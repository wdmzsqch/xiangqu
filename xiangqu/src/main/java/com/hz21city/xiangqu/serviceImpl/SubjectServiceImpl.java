package com.hz21city.xiangqu.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hz21city.xiangqu.dao.ISubjectInfoDao;
import com.hz21city.xiangqu.pojo.SubjectInfo;
import com.hz21city.xiangqu.service.ISubjectService;

@Service("subjetService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class SubjectServiceImpl implements ISubjectService{

	@Resource
	private ISubjectInfoDao subjectInfoDao;

	@Override
	public List<SubjectInfo> getAllSubjectList() {
		return subjectInfoDao.getAllList();
	}

	@Override
	public SubjectInfo getSubjectById(Long id) {
		return subjectInfoDao.selectByPrimaryKey(id);
	}
}
