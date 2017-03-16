package com.hz21city.xiangqu.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hz21city.xiangqu.pojo.MarkAdPoint;

public interface IMarkAdPointDao {


    int deleteByPrimaryKey(Long id);

    int insert(MarkAdPoint record);

    int insertSelective(MarkAdPoint record);

    MarkAdPoint selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MarkAdPoint record);

    int updateByPrimaryKey(MarkAdPoint record);

	MarkAdPoint getMarkAdPointBypointId(@Param("point_id") Long point_id);

	List<MarkAdPoint> getMarkAdPointList();

	List<MarkAdPoint> getMarkAdPointListByArea(@Param("areatype") int areatype);

}