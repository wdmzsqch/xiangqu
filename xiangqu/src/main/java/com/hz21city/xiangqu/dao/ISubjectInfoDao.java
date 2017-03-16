package com.hz21city.xiangqu.dao;

import java.util.List;

import com.hz21city.xiangqu.pojo.SubjectInfo;

public interface ISubjectInfoDao {
    int deleteByPrimaryKey(Long id);

    int insert(SubjectInfo record);

    int insertSelective(SubjectInfo record);

    SubjectInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SubjectInfo record);

    int updateByPrimaryKey(SubjectInfo record);

	List<SubjectInfo> getSubjectListByPage(Integer page);

	int getSubjectListSize();

	List<SubjectInfo> getAllList();

	int getAllSubject();
}