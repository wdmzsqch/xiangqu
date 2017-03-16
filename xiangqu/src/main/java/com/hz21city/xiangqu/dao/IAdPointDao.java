package com.hz21city.xiangqu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hz21city.xiangqu.pojo.AdPoint;

public interface IAdPointDao {

    int deleteByPrimaryKey(Long id);

    int insert(AdPoint record);

    int insertSelective(AdPoint record);

    AdPoint selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AdPoint record);

    int updateByPrimaryKey(AdPoint record);

	List<AdPoint> getAdPointListByPage(@Param("page") Integer page, @Param("keywords") String keywords, @Param("SourceClass") String SourceClass, @Param("areaSearch") String areaSearch);

	int getAdPointListSize(@Param("keywords") String keywords, @Param("SourceClass") String SourceClass, @Param("areaSearch") String areaSearch);

	List<AdPoint> getAdPointListByLoLa(@Param("slongitude") double slongitude, @Param("elongitude") double elongitude, @Param("slatitude") double slatitude, @Param("elatitude") double elatitude, @Param("keywords") String keywords, @Param("SourceClass") String SourceClass, @Param("areaSearch") String areaSearch);

	List<AdPoint> getAdPointListByLoLaIds(@Param("slongitude") double slongitude, @Param("elongitude") double elongitude, @Param("slatitude") double slatitude, @Param("elatitude") double elatitude, @Param("ids") List<Long> ids);

	List<AdPoint> getMarkAdPointListLfive(@Param("ids") List<Long> ids);

	List<AdPoint> getMapList(@Param("keywords") String keywords, @Param("SourceClass") String SourceClass, @Param("areaSearch") String areaSearch);

	List<AdPoint> getMarkPointList(@Param("ids") List<Long> ids);

	List<AdPoint> getAdPointListByPageByArea(@Param("page") Integer page, @Param("keywords") String keywords, @Param("SourceClass") String SourceClass, @Param("areaSearch") String areaSearch, @Param("areatype") int areatype);

	int getAdPointListSizeByArea(@Param("keywords") String keywords, @Param("SourceClass") String SourceClass, @Param("areaSearch") String areaSearch, @Param("areatype") int areatype);

	List<AdPoint> getAdPointListByLoLaByArea(@Param("slongitude") double slongitude, @Param("elongitude") double elongitude, @Param("slatitude") double slatitude, @Param("elatitude") double elatitude, @Param("keywords") String keywords, @Param("SourceClass") String SourceClass, @Param("areaSearch") String areaSearch, @Param("areatype") int areatype);

	List<AdPoint> getAdPointListByLoLaIdsByArea(@Param("slongitude") double slongitude, @Param("elongitude") double elongitude, @Param("slatitude") double slatitude, @Param("elatitude") double elatitude, @Param("ids") List<Long> ids, @Param("areatype") int areatype);

	List<AdPoint> getMapListByArea(@Param("keywords") String keywords, @Param("SourceClass") String SourceClass, @Param("areaSearch") String areaSearch, @Param("areatype") int areatype);
}