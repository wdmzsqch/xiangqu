package com.hz21city.xiangqu.service.manage;

import java.util.List;

import com.hz21city.xiangqu.pojo.SubjectInfo;

public interface IMaSubjectService {

	public abstract List<SubjectInfo> getSubjectListByPage(Integer page);

	public abstract int getSubjectListSize();

	public abstract SubjectInfo selectByPrimaryKey(Long id);

	public abstract void updateSubjectInfo(SubjectInfo subject);

	public abstract void addSubjectInfo(SubjectInfo subject);

	public abstract void deleteByPrimaryKey(Long id);

	public abstract int getAllSubjectSize();

}
