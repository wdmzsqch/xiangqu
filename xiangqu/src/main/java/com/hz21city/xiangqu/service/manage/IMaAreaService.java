package com.hz21city.xiangqu.service.manage;

import java.util.List;

import com.hz21city.xiangqu.pojo.Area;

public interface IMaAreaService {

	public abstract List<Area> getSelectByParentId(Long parentId);

	public abstract Area selectByPrimaryKey(Long id);

	public abstract List<Area> getAllCityList(Long parentId);
}
