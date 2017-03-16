package com.hz21city.xiangqu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hz21city.xiangqu.pojo.AdminInfo;

public interface IAdminInfoDao {
	int deleteByPrimaryKey(Long id);

	int insert(AdminInfo record);

	int insertSelective(AdminInfo record);

	AdminInfo selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(AdminInfo record);

	int updateByPrimaryKey(AdminInfo record);

	AdminInfo getAdminByPwd(@Param("username") String username, @Param("pwd") String pwd);
	
	List<AdminInfo> getAllAdmin();

	AdminInfo getAdminInfoByName(@Param("username") String username);

	List<AdminInfo> getadminListByArea(@Param("areaId") Long areaId, @Param("id") Long id);

	List<AdminInfo> getAreaAdminByType(@Param("type") int type);
}