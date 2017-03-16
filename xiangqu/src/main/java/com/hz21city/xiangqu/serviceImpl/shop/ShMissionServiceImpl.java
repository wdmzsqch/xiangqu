package com.hz21city.xiangqu.serviceImpl.shop;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hz21city.xiangqu.dao.IAreaDao;
import com.hz21city.xiangqu.dao.IIncomeRecordDao;
import com.hz21city.xiangqu.dao.IMissionInfoDao;
import com.hz21city.xiangqu.dao.ITaskNeedsDao;
import com.hz21city.xiangqu.pojo.Area;
import com.hz21city.xiangqu.pojo.MissionInfo;
import com.hz21city.xiangqu.pojo.TaskNeeds;
import com.hz21city.xiangqu.service.shop.IShMissionService;

@Service("shMissionService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ShMissionServiceImpl implements IShMissionService{

	@Resource
	private IMissionInfoDao missioninfoDao;
	
	@Resource
	private IIncomeRecordDao incomeRecordDao;
	
	@Resource
	private ITaskNeedsDao taskneedsDao;

	@Resource
	private IAreaDao areaDao;
	
	@Override
	public List<MissionInfo> getMissionInfoByType(long storeid, int type) {
		List<MissionInfo> missionlist = missioninfoDao.getMissionListByType(storeid, type);
		for(MissionInfo mission:missionlist){
			int count = incomeRecordDao.incomeCount(null, null, 1, new Long(mission.getId()).intValue());
			mission.setCount(count);
			Float allincome = incomeRecordDao.getShIncome(1, new Long(mission.getId()).intValue());
			if(allincome==null)
				allincome = 0f;
			mission.setAllIncome(allincome);
		}
		return missionlist;
	}

	@Override
	public MissionInfo getMissionDetail(long id) {
		MissionInfo mission = missioninfoDao.selectByPrimaryKey(id);
		int count = incomeRecordDao.incomeCount(null, null, 1, new Long(mission.getId()).intValue());
		mission.setCount(count);
		Area area = areaDao.selectByPrimaryKey(mission.getArea());
		Area city = areaDao.selectByPrimaryKey(mission.getCity());
		if(city != null){
			mission.setCityname(city.getName());
		}else{
			mission.setCityname("全国");
		}
		if(area!=null){
			mission.setAreaname(area.getName());
		}else{
			mission.setAreaname("全国");
		}
		return mission;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public void addTaskNeeds(TaskNeeds taskneed) {
		taskneedsDao.insertSelective(taskneed);
	}
	
	
	
}
