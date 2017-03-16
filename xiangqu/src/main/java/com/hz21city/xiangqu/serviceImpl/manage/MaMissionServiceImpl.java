/**
 * 
 */
package com.hz21city.xiangqu.serviceImpl.manage;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.dao.ICheckInfoDao;
import com.hz21city.xiangqu.dao.IIncomeRecordDao;
import com.hz21city.xiangqu.dao.IMissionInfoDao;
import com.hz21city.xiangqu.dao.IShopInfoDao;
import com.hz21city.xiangqu.dao.ITaskNeedsDao;
import com.hz21city.xiangqu.dao.IUserInfoDao;
import com.hz21city.xiangqu.dao.IndustryDao;
import com.hz21city.xiangqu.dao.IMissionCatogryDao;
import com.hz21city.xiangqu.pojo.CheckInfo;
import com.hz21city.xiangqu.pojo.IncomeRecord;
import com.hz21city.xiangqu.pojo.Industry;
import com.hz21city.xiangqu.pojo.MissionCatogry;
import com.hz21city.xiangqu.pojo.MissionInfo;
import com.hz21city.xiangqu.pojo.ShopInfo;
import com.hz21city.xiangqu.pojo.TaskNeeds;
import com.hz21city.xiangqu.pojo.UserInfo;
import com.hz21city.xiangqu.service.manage.IMaMissionService;

@Service("maMissionService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class MaMissionServiceImpl implements IMaMissionService {

	@Resource
	private IMissionInfoDao missionInfoDao;
	@Resource
	private IShopInfoDao shopInfoDao;
	@Resource
	private IndustryDao industryDao;
	@Resource
	private ITaskNeedsDao taskNeedsDao;
	@Resource
	private IMissionCatogryDao missionCatogryDao;
	@Resource
	private IIncomeRecordDao incomeRecordDao;
	@Resource
	private ICheckInfoDao checkInfoDao;
	@Resource
	private IUserInfoDao userInfoDao;
	
	@Override
	public List<MissionInfo> getMissionList(Integer searchtype, String keywords, Integer pageIndex, Long province,
			Long city, Long area, Long cotegory_id, Long createUserId) {
		if (!CommonUtils.isEmptyString(keywords)) {
			keywords = "%" + keywords + "%";
		}
		List<MissionInfo> list = missionInfoDao.getMaMissionList(searchtype, keywords, (pageIndex - 1) * 10, province, city, area, cotegory_id, createUserId);
		if(list != null && list.size() > 0){
			for (MissionInfo missionInfo : list) {
				missionInfo.setBrushNum(missionInfoDao.getMissionBrushCount(missionInfo.getId()));
				CheckInfo checkinfo = checkInfoDao.getCheckInfoByAllWays(1, missionInfo.getId(), 1);
				if(checkinfo == null){
					missionInfo.setCheckStatus(0);
				} else {
					missionInfo.setCheckStatus(1);
				}
				CheckInfo checkinfo2 = checkInfoDao.getCheckInfoByAllWays(1, missionInfo.getId(), 2);
				if(checkinfo2 == null){
					missionInfo.setCheckStatusY(0);
				}else{
					missionInfo.setCheckStatusY(1);
				}
				CheckInfo checkinfo3 = checkInfoDao.getCheckInfoByAllWays(1, missionInfo.getId(), 3);
				if(checkinfo3 == null){
					missionInfo.setCheckStatusC(0);
				}else{
					missionInfo.setCheckStatusC(1);
				}
				CheckInfo checkinfo4 = checkInfoDao.getCheckInfoByAllWays(1, missionInfo.getId(), 4);
				if(checkinfo4 == null){
					missionInfo.setCheckStatusB(0);
				}else{
					missionInfo.setCheckStatusB(1);
				}
			}
		}
		return list;
	}

	@Override
	public int getMissionListSize(Integer searchtype, String keywords, Long province, Long city, Long area, Long cotegory_id, Long createUserId) {
		int total = missionInfoDao.getMaMissionListSize(searchtype, keywords, province, city, area, cotegory_id, createUserId);
		return (int) Math.ceil((double) total / 10.0d);
	}

	@Override
	public MissionInfo getMissionById(Long id) {
		return missionInfoDao.getMaMissionDetail(id);
	}

	@Override
	public void updateMission(MissionInfo missionInfo) {
		if (missionInfo.getId() != null) {
			missionInfoDao.updateByPrimaryKeySelective(missionInfo);
		} else {
			missionInfoDao.insertSelective(missionInfo);
		}
	}


	@Override
	public List<ShopInfo> getShopList() {
		return shopInfoDao.getAllShopList();
	}

	@Override
	public List<Industry> getIndustryList() {
		return industryDao.getAllIndustry();
	}

	@Override
	public List<TaskNeeds> getTaskNeedsList(Long shopId, String keywords, Integer page) {
		if (page == null) {
			page = 0;
		} else if (page > 0) {
			page = (page - 1) * 10;
		}
		if (!CommonUtils.isEmptyString(keywords)) {
			keywords = "%" + keywords + "%";
		}
		return taskNeedsDao.getTaskNeedsList(shopId, keywords, page);
	}

	@Override
	public int getTaskNeedsListSize(Long shopId, String keywords) {
		int total = taskNeedsDao.getTaskNeedsListSize(shopId, keywords);
		return (int) Math.ceil((double) total / 10.0d);
	}

	@Override
	public void updateByPrimaryKeySelective(TaskNeeds taskneeds) {
		taskNeedsDao.updateByPrimaryKeySelective(taskneeds);
	}

	@Override
	public List<MissionCatogry> getAllCotegoryList() {
		return missionCatogryDao.getAllMissionCatogry();
	}

	@Override
	public int incomeCount(Long id) {
		return incomeRecordDao.incomeCount(null, null, 1, id.intValue());
	}

	@Override
	public Integer getMaxSort() {
		Integer sort = 0;
		List<MissionInfo> list = missionInfoDao.getMissionListBySort();
		if(list != null && list.size() > 0){
			if(list.get(0).getSort() != null){
				sort = list.get(0).getSort();
			}
		}
		return sort;
	}

	@Override
	public int getAllMissionSize() {
		return missionInfoDao.getAllMissionSize();
	}

	@Override
	public CheckInfo getCheckInfoByAllWays(Integer type, Long relativeId, Integer checkType) {
		return checkInfoDao.getCheckInfoByAllWays(type, relativeId, checkType);
	}

	@Override
	public void updateMissionState(Long missionid, Integer state) {
		
	}

	@Override
	public void delMission(Long missionid) {
		
	}

	@Override
	public void addMission(MissionInfo missionInfo) {
		
	}

	@Override
	public void addCheckInfo(CheckInfo checkinfo) {
		checkInfoDao.insertSelective(checkinfo);
	}

	@Override
	public void updateCheckInfo(CheckInfo checkinfo) {
		checkInfoDao.updateByPrimaryKeySelective(checkinfo);
	}

	@Override
	public int getAllOutTimesMission(Long createUserId) {
		return missionInfoDao.getAllOutTimesMission(createUserId);
	}

	@Override
	public void delCheckInfo(CheckInfo checkinfo) {
		checkInfoDao.deleteByPrimaryKey(checkinfo.getId());
	}

	@Override
	public void addIncomeRecord(IncomeRecord record) {
		incomeRecordDao.insertSelective(record);
	}

	@Override
	public void updateMissionAll(MissionInfo missionInfo) {
		missionInfoDao.updateByPrimaryKey(missionInfo);
	}

	@Override
	public int getAreaMissionCount(Long province, Long city, Long area) {
		return missionInfoDao.getAreaMissionCount(province, city, area);
	}
}
