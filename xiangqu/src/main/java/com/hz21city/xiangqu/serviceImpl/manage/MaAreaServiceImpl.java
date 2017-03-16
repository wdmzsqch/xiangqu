package com.hz21city.xiangqu.serviceImpl.manage;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hz21city.xiangqu.dao.IAreaDao;
import com.hz21city.xiangqu.pojo.Area;
import com.hz21city.xiangqu.service.manage.IMaAreaService;

@Service("maAreaService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class MaAreaServiceImpl implements IMaAreaService {
	@Resource
	private IAreaDao areaDao;

	@Override
	public List<Area> getSelectByParentId(Long parentId) {
		return areaDao.getAreaListByParentid(parentId);
	}

	@Override
	public Area selectByPrimaryKey(Long id) {
		return areaDao.selectByPrimaryKey(id);
	}

	@Override
	public List<Area> getAllCityList(Long parentId) {
		List<Area> list = areaDao.getAreaListByParentid(parentId);
		if(list != null && list.size() > 0){
			for (Area area : list) {
				List<Area> citylist = areaDao.getAreaListByParentid(area.getId());
				if(citylist != null && citylist.size() > 0){
					area.setCitylist(citylist);
				}
			}
		}
		return list;
	}

}
