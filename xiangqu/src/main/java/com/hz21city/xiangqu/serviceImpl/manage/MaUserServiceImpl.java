package com.hz21city.xiangqu.serviceImpl.manage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.dao.IAdminInfoDao;
import com.hz21city.xiangqu.dao.IAdminRoleDao;
import com.hz21city.xiangqu.dao.IAreaDao;
import com.hz21city.xiangqu.dao.ICheckInfoDao;
import com.hz21city.xiangqu.dao.ICouponInfoDao;
import com.hz21city.xiangqu.dao.IEventInfoDao;
import com.hz21city.xiangqu.dao.IGoodsInfoDao;
import com.hz21city.xiangqu.dao.IIncomeRecordDao;
import com.hz21city.xiangqu.dao.IMissionInfoDao;
import com.hz21city.xiangqu.dao.IOrderDao;
import com.hz21city.xiangqu.dao.IRechargeInfoDao;
import com.hz21city.xiangqu.dao.IUserAddressDao;
import com.hz21city.xiangqu.dao.IUserInfoDao;
import com.hz21city.xiangqu.dao.IVerifyCodeDao;
import com.hz21city.xiangqu.pojo.AdminInfo;
import com.hz21city.xiangqu.pojo.AdminRole;
import com.hz21city.xiangqu.pojo.Area;
import com.hz21city.xiangqu.pojo.CheckInfo;
import com.hz21city.xiangqu.pojo.CouponInfo;
import com.hz21city.xiangqu.pojo.EventInfo;
import com.hz21city.xiangqu.pojo.GoodsInfo;
import com.hz21city.xiangqu.pojo.IncomeRecord;
import com.hz21city.xiangqu.pojo.MissionInfo;
import com.hz21city.xiangqu.pojo.Order;
import com.hz21city.xiangqu.pojo.RechargeInfo;
import com.hz21city.xiangqu.pojo.UserAddress;
import com.hz21city.xiangqu.pojo.UserInfo;
import com.hz21city.xiangqu.pojo.UserInviteCount;
import com.hz21city.xiangqu.pojo.VerifyCode;
import com.hz21city.xiangqu.service.manage.IMaUserService;

@Service("maUserService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class MaUserServiceImpl implements IMaUserService {

	@Resource
	private IAdminInfoDao adminInfoDao;
	@Resource
	private IUserInfoDao userInfoDao;
	@Resource
	private IUserAddressDao IUserAddressDao;
	@Resource
	private IAdminRoleDao adminRoleDao;
	@Resource
	private IAreaDao areaDao;
	@Resource
	IOrderDao orderDao;
	@Resource
	private IRechargeInfoDao rechargeInfoDao;
	@Resource
	private ICheckInfoDao checkInfoDao;
	@Resource
	private IVerifyCodeDao verifyCodeDao;
	@Resource
	private IMissionInfoDao missionInfoDao;
	@Resource
	private IEventInfoDao eventInfoDao;
	@Resource
	private ICouponInfoDao couponInfoDao;
	@Resource
	private IIncomeRecordDao incomeRecordDao;
	@Resource
	private IGoodsInfoDao goodsInfoDao;

	@Override
	public AdminInfo getAdminInfo(String username, String pwd) {
		return adminInfoDao.getAdminByPwd(username, pwd);
	}

	@Override
	public List<AdminInfo> getAdminList() {
		List<AdminInfo> list = adminInfoDao.getAllAdmin();
		if (list != null && list.size() > 0) {
			for (AdminInfo adminInfo : list) {
				if (adminInfo.getType() == 6) {
					if (adminInfo.getIntegral() != null) {
						int usecount = missionInfoDao.getAllOutTimesMission(adminInfo.getId());
						adminInfo.setLeftintegral(adminInfo.getIntegral() - usecount);
					} else {
						adminInfo.setLeftintegral(0);
					}
				}
			}
		}
		return list;
	}

	@Override
	public List<UserInfo> getUserList(String keywords, Integer page, Integer searchType, Date start, Date end, Float UCollectCount, Float DCollectCount) {
		if (page == null) {
			page = 0;
		} else if (page > 0) {
			page = (page - 1) * 10;
		}
		if (!CommonUtils.isEmptyString(keywords)) {
			keywords = "%" + keywords + "%";
		}
		String startStr = CommonUtils.time2Str(start, "yyyy-MM-dd HH:mm:ss");
		String endStr = CommonUtils.time2Str(end, "yyyy-MM-dd HH:mm:ss");
		return userInfoDao.getUserList(keywords, page, searchType, startStr, endStr, UCollectCount, DCollectCount);
	}

	@Override
	public List<UserInfo> getUserList(String keywords, Integer page, Integer searchType, Date start, Date end, Float UCollectCount, Float DCollectCount, Integer isSort, Integer baSort,
			Integer incomeSort) {
		if (page == null) {
			page = 0;
		} else if (page > 0) {
			page = (page - 1) * 10;
		}
		if (!CommonUtils.isEmptyString(keywords)) {
			keywords = "%" + keywords + "%";
		}
		String startStr = CommonUtils.time2Str(start, "yyyy-MM-dd HH:mm:ss");
		String endStr = CommonUtils.time2Str(end, "yyyy-MM-dd HH:mm:ss");
		return userInfoDao.getUserSortList(keywords, page, searchType, startStr, endStr, UCollectCount, DCollectCount, isSort, baSort, incomeSort);
	}

	@Override
	public int getUserListSize(String keywords, Integer searchType, Date start, Date end, Float UCollectCount, Float DCollectCount) {
		if (!CommonUtils.isEmptyString(keywords)) {
			keywords = "%" + keywords + "%";
		}
		String startStr = CommonUtils.time2Str(start, "yyyy-MM-dd HH:mm:ss");
		String endStr = CommonUtils.time2Str(end, "yyyy-MM-dd HH:mm:ss");
		int total = userInfoDao.getUserListSize(keywords, searchType, startStr, endStr, UCollectCount, DCollectCount);
		return (int) Math.ceil((double) total / 10.0d);
	}

	@Override
	public List<Order> getRecordList(Integer page, Long userId) {
		if (page == null) {
			page = 0;
		} else if (page > 0) {
			page = (page - 1) * 10;
		}
		return orderDao.getOrderListByUser(page, userId);
	}

	@Override
	public int getRecordListSize(Long userId) {
		int total = orderDao.getRecordListSize(userId);
		return (int) Math.ceil((double) total / 10.0d);
	}

	@Override
	public UserInfo getUserModeify(Long userId) {
		return userInfoDao.selectByPrimaryKey(userId);
	}

	@Override
	public void editorUserInfo(UserInfo userinfo) {
		userInfoDao.updateByPrimaryKeySelective(userinfo);
	}

	@Override
	public List<UserAddress> getUserAddressList(Long userId, Integer page) {
		if (page == null) {
			page = 0;
		} else if (page > 0) {
			page = (page - 1) * 10;
		}
		return IUserAddressDao.getUserAddressList(userId, page);
	}

	@Override
	public int getUserAddressListSize(Long userId) {
		int total = IUserAddressDao.getUserAddressListSize(userId);
		return (int) Math.ceil((double) total / 10.0d);
	}

	@Override
	public Area getArea(Long id) {
		Area area = areaDao.selectByPrimaryKey(id);
		return area;
	}

	@Override
	public List<AdminRole> getAllAdminRole() {
		return adminRoleDao.getAllAdminRole();
	}

	@Override
	public AdminInfo selectByPrimaryKey(Long id) {
		return adminInfoDao.selectByPrimaryKey(id);
	}

	@Override
	public void insertAdmin(AdminInfo admin) {
		adminInfoDao.insert(admin);
	}

	@Override
	public void updateByPrimaryKeySelective(AdminInfo admin) {
		adminInfoDao.updateByPrimaryKeySelective(admin);
	}

	@Override
	public void deleteByPrimaryKey(Long id) {
		adminInfoDao.deleteByPrimaryKey(id);
	}

	@Override
	public List<Area> getAreaListByParentid(Long parentid) {
		List<Area> list = areaDao.getAreaListByParentid(parentid);
		return list;
	}

	@Override
	public Integer getAllUserSize() {
		return userInfoDao.getAllUserSize();
	}

	@Override
	public List<UserInfo> getAllUserList() {
		return userInfoDao.AllUserList();
	}

	@Override
	public List<RechargeInfo> getRechargeListByToRechargeId(Long toRechargeId) {
		return rechargeInfoDao.getRechargeListByToRechargeId(toRechargeId);
	}

	@Override
	public void addRecharge(RechargeInfo recharge) {
		rechargeInfoDao.insertSelective(recharge);
	}

	@Override
	public List<RechargeInfo> getRechargeListByPage(Integer page) {
		if (page == null) {
			page = 0;
		} else if (page > 0) {
			page = (page - 1) * 10;
		}
		List<RechargeInfo> list = rechargeInfoDao.getRechargeListByPage(page);
		if (list != null && list.size() > 0) {
			for (RechargeInfo rechargeInfo : list) {
				if (rechargeInfo.getFromRechargeId() != null && rechargeInfo.getFromRechargeId() > 0) {
					AdminInfo admin = adminInfoDao.selectByPrimaryKey(rechargeInfo.getFromRechargeId());
					if (admin != null) {
						rechargeInfo.setFrom_name(admin.getUserName());
					}
				}
				if (rechargeInfo.getToRechargeId() != null && rechargeInfo.getToRechargeId() > 0) {
					AdminInfo admin = adminInfoDao.selectByPrimaryKey(rechargeInfo.getToRechargeId());
					if (admin != null) {
						rechargeInfo.setTo_name(admin.getUserName());
					}
				}
				CheckInfo checkinfo3 = checkInfoDao.getCheckInfoByAllWays(4, rechargeInfo.getId(), 3);
				if (checkinfo3 == null) {
					rechargeInfo.setCheckStatusC(0);
				} else {
					rechargeInfo.setCheckStatusC(1);
				}
				CheckInfo checkinfo4 = checkInfoDao.getCheckInfoByAllWays(4, rechargeInfo.getId(), 4);
				if (checkinfo4 == null) {
					rechargeInfo.setCheckStatusB(0);
				} else {
					rechargeInfo.setCheckStatusB(1);
				}
			}
		}
		return list;
	}

	@Override
	public int getRechargeListSize() {
		int total = rechargeInfoDao.getRechargeListSize();
		return (int) Math.ceil((double) total / 10.0d);
	}

	@Override
	public RechargeInfo getRechargeInfo(Long relativeId) {
		return rechargeInfoDao.selectByPrimaryKey(relativeId);
	}

	@Override
	public AdminInfo getAdminInfoByName(String username) {
		return adminInfoDao.getAdminInfoByName(username);
	}

	@Override
	public void insertVerifyCode(VerifyCode verifyCode) {
		verifyCodeDao.insertSelective(verifyCode);
	}

	@Override
	public VerifyCode getVerifyCode(String mobile) {
		return verifyCodeDao.getVerifyCodeByMoblie(mobile);
	}

	@Override
	public List<AdminInfo> getAreaAdminList(Long areaId, Long id) {
		List<AdminInfo> list = adminInfoDao.getadminListByArea(areaId, id);
		if (list != null && list.size() > 0) {
			for (AdminInfo adminInfo : list) {
				if (adminInfo.getIntegral() != null) {
					int usecount = missionInfoDao.getAllOutTimesMission(adminInfo.getId());
					adminInfo.setLeftintegral(adminInfo.getIntegral() - usecount);
				} else {
					adminInfo.setLeftintegral(0);
				}
			}
		}
		return list;
	}

	@Override
	public UserInfo getUserRandom() {
		return userInfoDao.getUserRandom();
	}

	@Override
	public List<AdminInfo> getAreaAdminByType(int type) {
		List<AdminInfo> list = adminInfoDao.getAreaAdminByType(type);
		if (list != null && list.size() > 0) {
			for (AdminInfo adminInfo : list) {
				if (adminInfo.getIntegral() != null) {
					int usecount = missionInfoDao.getAllOutTimesMission(adminInfo.getId());
					adminInfo.setLeftintegral(adminInfo.getIntegral() - usecount);
				} else {
					adminInfo.setLeftintegral(0);
				}
			}
		}
		return list;
	}

	@Override
	public List<UserInfo> getInviteInfo(String start, String end) {
		List<UserInviteCount> cList = userInfoDao.getUserInviteData(start, end);
		List<UserInfo> uList = new ArrayList<UserInfo>();
		if (cList != null && cList.size() > 0) {
			for (UserInviteCount userInviteCount : cList) {
				UserInfo u = userInfoDao.selectByPrimaryKey(userInviteCount.getUser_id());
				u.setIcount(userInviteCount.getIcount());
				uList.add(u);
			}
		}
		return uList;
	}

	@Override
	public List<IncomeRecord> getMyRecordList(Long userid) {
		List<IncomeRecord> list = incomeRecordDao.getIncomeRecordListByUSerId(userid);
		if (list != null && list.size() > 0) {
			for (IncomeRecord incomeRecord : list) {
				if (incomeRecord.getUserId() != null && incomeRecord.getUserId() > 0) {
					UserInfo userinfo = userInfoDao.selectByPrimaryKey(incomeRecord.getUserId());
					if (userinfo != null) {
						incomeRecord.setUsername(userinfo.getNickName());
					}
					if (incomeRecord.getType() == 1) {// 任务
						MissionInfo missionInfo = missionInfoDao.selectByPrimaryKey(incomeRecord.getRelativeId());
						if (missionInfo != null) {
							incomeRecord.setRelativename(String.valueOf(missionInfo.getIncome()));
						}
					} else if (incomeRecord.getType() == 2) {// 分享商品
						GoodsInfo goodsInfo = goodsInfoDao.selectByPrimaryKey(incomeRecord.getRelativeId());
						if (goodsInfo != null) {
							incomeRecord.setRelativename(goodsInfo.getName());
						}
					} else if (incomeRecord.getType() == 3) {// 购买商品
						GoodsInfo goodsInfo = goodsInfoDao.selectByPrimaryKey(incomeRecord.getRelativeId());
						if (goodsInfo != null) {
							incomeRecord.setRelativename(goodsInfo.getName());
							if (goodsInfo.getPayType() == 1) {
								incomeRecord.setGoodstype(1);
							} else {
								incomeRecord.setGoodstype(2);
							}
						}
					} else if (incomeRecord.getType() == 4 || incomeRecord.getType() == 5) {// 优惠券分享
																							// 或
																							// 优惠券使用
						CouponInfo couponInfo = couponInfoDao.selectByPrimaryKey(incomeRecord.getRelativeId());
						if (couponInfo != null) {
							incomeRecord.setRelativename(couponInfo.getTitle());
						}
					} else if (incomeRecord.getType() == 6) {
						EventInfo eventInfo = eventInfoDao.selectByPrimaryKey(incomeRecord.getRelativeId());
						if (eventInfo != null) {
							incomeRecord.setRelativename(eventInfo.getName());
						}
					} else if (incomeRecord.getType() == 8 || incomeRecord.getType() == 10) {
						UserInfo userinfo2 = userInfoDao.selectByPrimaryKey(incomeRecord.getFromUserId());
						if (userinfo2 != null) {
							if (!CommonUtils.isEmptyString(userinfo2.getNickName())) {
								incomeRecord.setRelativename(userinfo2.getNickName());
							} else if (!CommonUtils.isEmptyString(userinfo2.getRealName())) {
								incomeRecord.setRelativename(userinfo2.getRealName());
							} else {
								incomeRecord.setRelativename(userinfo2.getUserName());
							}
						}
					} else if (incomeRecord.getType() == 11 || incomeRecord.getType() == 12 || incomeRecord.getType() == 13) {
						UserInfo userinfo2 = userInfoDao.selectByPrimaryKey(incomeRecord.getFromUserId());
						if (userinfo2 != null) {
							if (!CommonUtils.isEmptyString(userinfo2.getNickName())) {
								incomeRecord.setRelativename(userinfo2.getNickName());
							} else if (!CommonUtils.isEmptyString(userinfo2.getRealName())) {
								incomeRecord.setRelativename(userinfo2.getRealName());
							} else {
								incomeRecord.setRelativename(userinfo2.getUserName());
							}
						}
					}
				}
			}
		}
		return list;
	}

	// ----------推广员-----------------
	@Override
	public List<UserInfo> getPromoterList(String keyword, Integer page) {
		if (page == null) {
			page = 0;
		} else if (page > 0) {
			page = (page - 1) * 10;
		}
		return userInfoDao.getPromoterList(keyword, page);
	}

	@Override
	public void editPromoter(Long userid, Integer type) {
		UserInfo userInfo = userInfoDao.selectByPrimaryKey(userid);
		if (userInfo != null) {
			userInfo.setType(type);
			userInfoDao.updateByPrimaryKeySelective(userInfo);
		}
	}

	@Override
	public Integer getPromoterListSize(String keywords) {
		if (!CommonUtils.isEmptyString(keywords)) {
			keywords = "%" + keywords + "%";
		}
		int total = userInfoDao.getPromoterListSize(keywords);
		return (int) Math.ceil((double) total / 10.0d);
	}

	@Override
	public Integer getAllPromoterCount() {
		return userInfoDao.getPromoterListSize(null);
	}
}
