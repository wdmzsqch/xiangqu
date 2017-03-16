package com.hz21city.xiangqu.serviceImpl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.dao.IHomeActivityDao;
import com.hz21city.xiangqu.dao.IIncomeRecordDao;
import com.hz21city.xiangqu.dao.IMissionInfoDao;
import com.hz21city.xiangqu.dao.IShareRecordDao;
import com.hz21city.xiangqu.dao.IUserInfoDao;
import com.hz21city.xiangqu.pojo.HomeActivity;
import com.hz21city.xiangqu.pojo.IncomeRecord;
import com.hz21city.xiangqu.pojo.MissionInfo;
import com.hz21city.xiangqu.pojo.UserInfo;
import com.hz21city.xiangqu.service.IMissionService;

@Service("missionService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class MissionServiceImpl implements IMissionService {

	@Resource
	private IMissionInfoDao missionInfoDao;
	@Resource
	private IShareRecordDao shareRecordDao;
	@Resource
	private IHomeActivityDao homeactivityDao;
	@Resource
	private IIncomeRecordDao incomeRecordDao;
	@Resource
	private IUserInfoDao userInfoDao;

	@Override
	public List<MissionInfo> getMissionList(Long userid, Long province, Long city) {
		Date nowDate = new Date();
		List<MissionInfo> missionlist = missionInfoDao.getMissionListByLocal(province, city);
		if (userid != null && userid > 0) {
			for (MissionInfo mission : missionlist) {
				int sharecount = shareRecordDao.userShare(userid, 1, mission.getId());
				mission.setIsShare(sharecount);
				if ((nowDate.getTime() - mission.getEndTime().getTime()) <= 0) {
					mission.setOvertime(0);
				} else {
					mission.setOvertime(1);
				}
				mission.setWeekOfDate(CommonUtils.getWeekOfDate(mission.getPublishTime()));
			}
		}
		return missionlist;
	}

	@Override
	public MissionInfo getMissionById(long id) {
		MissionInfo missioninfo = missionInfoDao.getMaMissionDetail(id);
		return missioninfo;
	}

	@Override
	public HomeActivity getHomeActivityByKey(String valuekey) {
		return homeactivityDao.getActivityByValueKey(valuekey);
	}

	@Override
	public void updateMissionInfo(MissionInfo mission) {
		missionInfoDao.updateByPrimaryKeySelective(mission);
	}

	@Override
	public int isUserShare(long userid, int i, Long id) {
		int sharecount = shareRecordDao.userShare(userid, 1, id);
		return sharecount;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public synchronized MissionInfo addIncomeRecord(Long shareuserid, long userid, Integer relativeid, Long missionid) {
		MissionInfo missioninfo = missionInfoDao.getMaMissionDetail(missionid);
		if (missioninfo != null && missioninfo.getRamianTimes() > 0 && (missioninfo.getState() == null || missioninfo.getState() < 3)) {
			int incomecount = incomeRecordDao.incomeCount(shareuserid, userid, 1, relativeid);
			if (incomecount < missioninfo.getIncomeTime()) {
				IncomeRecord incomerecord = new IncomeRecord();
				incomerecord.setFromUserId(userid);
				incomerecord.setUserId(shareuserid);
				incomerecord.setType(1);
				incomerecord.setRelativeId(missionid);
				incomerecord.setIncome(missioninfo.getIncome());
				incomerecord.setTime(new Date());
				incomeRecordDao.insertSelective(incomerecord);
				UserInfo shareuser = userInfoDao.selectByPrimaryKey(shareuserid);
				shareuser.setBalance(shareuser.getBalance() + missioninfo.getIncome());
				userInfoDao.updateByPrimaryKeySelective(shareuser);
				missioninfo.setRamianTimes(missioninfo.getRamianTimes() - 1);
				if (missioninfo.getRamianTimes() <= 0) {
					missioninfo.setState(3);
				}
				missionInfoDao.updateByPrimaryKeySelective(missioninfo);
				// 给推广员加积分
				if (shareuser.getInviteUserId() != null && shareuser.getInviteUserId() > 0) {
					UserInfo promoter = userInfoDao.selectByPrimaryKey(shareuser.getInviteUserId());
					if (promoter.getType() != null && promoter.getType() > 0) {
						Integer incomeP = Math.round(missioninfo.getIncome() * promoter.getType() / 100.0f);
						IncomeRecord incomerecordP = new IncomeRecord();
						incomerecordP.setFromUserId(shareuserid);
						incomerecordP.setUserId(promoter.getId());
						incomerecordP.setType(11);
						incomerecordP.setRelativeId(missioninfo.getIncome().longValue());// 设成下家获得的积分
						incomerecordP.setIncome(incomeP.floatValue());
						incomerecordP.setTime(new Date());
						incomeRecordDao.insertSelective(incomerecordP);
						promoter.setBalance(promoter.getBalance() + incomeP);
						userInfoDao.updateByPrimaryKey(promoter);
					}
				}
			}
		}
		return missioninfo;
	}

	@Override
	public List<MissionInfo> getMissionList(Long userid, Long province, Long city, Long area) {
		Date nowDate = new Date();
		List<MissionInfo> missionlist = missionInfoDao.getMissionListByAllLocal(province, city, area);
		if (userid != null && userid > 0) {
			for (MissionInfo mission : missionlist) {
				int sharecount = shareRecordDao.userShare(userid, 1, mission.getId());
				mission.setIsShare(sharecount);
				if ((nowDate.getTime() - mission.getEndTime().getTime()) <= 0) {
					mission.setOvertime(0);
				} else {
					mission.setOvertime(1);
				}
				mission.setWeekOfDate(CommonUtils.getWeekOfDate(mission.getPublishTime()));
			}
		}
		return missionlist;
	}
}
