package com.hz21city.xiangqu.service;

import java.util.List;

import com.hz21city.xiangqu.pojo.SubjectInfo;

public interface ISubjectService {

	public abstract List<SubjectInfo> getAllSubjectList();

	public abstract SubjectInfo getSubjectById(Long id);

}
