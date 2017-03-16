package com.hz21city.xiangqu.service.manage;

import java.util.Date;
import java.util.List;

import com.hz21city.xiangqu.pojo.AdminInfo;
import com.hz21city.xiangqu.pojo.AdminRole;
import com.hz21city.xiangqu.pojo.Area;
import com.hz21city.xiangqu.pojo.IncomeRecord;
import com.hz21city.xiangqu.pojo.Order;
import com.hz21city.xiangqu.pojo.RechargeInfo;
import com.hz21city.xiangqu.pojo.UserAddress;
import com.hz21city.xiangqu.pojo.UserInfo;
import com.hz21city.xiangqu.pojo.VerifyCode;

public interface IMaUserService {

	public abstract AdminInfo getAdminInfo(String username, String pwd);

	public abstract List<AdminInfo> getAdminList();

	public abstract List<UserInfo> getUserList(String keywords, Integer page, Integer searchType, Date start, Date end, Float UCollectCount, Float DCollectCount);

	public abstract List<UserInfo> getUserList(String keywords, Integer page, Integer searchType, Date start, Date end, Float UCollectCount, Float DCollectCount, Integer isSort, Integer baSort,
			Integer incomeSort);

	public abstract int getUserListSize(String keywords, Integer searchType, Date start, Date end, Float UCollectCount, Float DCollectCount);

	public abstract List<Order> getRecordList(Integer page, Long userId);

	public abstract int getRecordListSize(Long userId);

	public abstract UserInfo getUserModeify(Long userId);

	public abstract void editorUserInfo(UserInfo userinfo);

	public abstract List<UserAddress> getUserAddressList(Long userId, Integer page);

	public abstract int getUserAddressListSize(Long userId);

	public abstract Area getArea(Long id);

	public abstract List<AdminRole> getAllAdminRole();

	public abstract AdminInfo selectByPrimaryKey(Long id);

	public abstract void insertAdmin(AdminInfo admin);

	public abstract void updateByPrimaryKeySelective(AdminInfo admin);

	public abstract void deleteByPrimaryKey(Long id);

	public abstract List<Area> getAreaListByParentid(Long parentid);

	public abstract Integer getAllUserSize();

	public abstract List<UserInfo> getAllUserList();

	public abstract List<RechargeInfo> getRechargeListByToRechargeId(Long toRechargeId);

	public abstract void addRecharge(RechargeInfo recharge);

	public abstract List<RechargeInfo> getRechargeListByPage(Integer page);

	public abstract int getRechargeListSize();

	public abstract RechargeInfo getRechargeInfo(Long relativeId);

	public abstract AdminInfo getAdminInfoByName(String username);

	public abstract void insertVerifyCode(VerifyCode verifyCode);

	public abstract VerifyCode getVerifyCode(String mobile);

	public abstract List<AdminInfo> getAreaAdminList(Long areaId, Long id);

	public abstract UserInfo getUserRandom();

	public abstract Object getAreaAdminByType(int type);

	public abstract List<UserInfo> getInviteInfo(String start, String end);

	public abstract List<IncomeRecord> getMyRecordList(Long userid);

	public abstract List<UserInfo> getPromoterList(String keyword, Integer page);

	public abstract Integer getPromoterListSize(String keyword);

	public abstract void editPromoter(Long userid, Integer type);

	public abstract Integer getAllPromoterCount();
}
