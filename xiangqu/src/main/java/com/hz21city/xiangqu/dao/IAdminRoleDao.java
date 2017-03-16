package com.hz21city.xiangqu.dao;

import java.util.List;

import com.hz21city.xiangqu.pojo.AdminRole;

public interface IAdminRoleDao {
	int deleteByPrimaryKey(Long id);

	int insert(AdminRole record);

	int insertSelective(AdminRole record);

	AdminRole selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(AdminRole record);

	int updateByPrimaryKey(AdminRole record);

	List<AdminRole> getAllAdminRole();
}