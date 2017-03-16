package com.hz21city.xiangqu.serviceImpl.manage;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hz21city.xiangqu.dao.ISubjectInfoDao;
import com.hz21city.xiangqu.pojo.SubjectInfo;
import com.hz21city.xiangqu.service.manage.IMaSubjectService;

@Service("maSubjectService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class MaSubjectServiceImpl implements IMaSubjectService{

	@Resource
	private ISubjectInfoDao subjectInfoDao;

	@Override
	public List<SubjectInfo> getSubjectListByPage(Integer page) {
		if (page == null) {
			page = 0;
		} else if (page > 0) {
			page = (page - 1) * 10;
		}
		List<SubjectInfo> list = subjectInfoDao.getSubjectListByPage(page);
		return list;
	}

	@Override
	public int getSubjectListSize() {
		int total = subjectInfoDao.getSubjectListSize();
		return (int) Math.ceil((double) total / 10.0d);
	}

	@Override
	public SubjectInfo selectByPrimaryKey(Long id) {
		return subjectInfoDao.selectByPrimaryKey(id);
	}

	@Override
	public void updateSubjectInfo(SubjectInfo subject) {
		subjectInfoDao.updateByPrimaryKeySelective(subject);
	}

	@Override
	public void addSubjectInfo(SubjectInfo subject) {
		subjectInfoDao.insertSelective(subject);
	}

	@Override
	public void deleteByPrimaryKey(Long id) {
		subjectInfoDao.deleteByPrimaryKey(id);
	}

	@Override
	public int getAllSubjectSize() {
		return subjectInfoDao.getAllSubject();
	}
}
