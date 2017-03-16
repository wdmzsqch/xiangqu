/**
 * 
 */
package com.hz21city.xiangqu.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.daoshun.exception.CustomException;
import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.common.Constants;
import com.hz21city.xiangqu.common.JpushClientUtil;
import com.hz21city.xiangqu.dao.IAreaDao;
import com.hz21city.xiangqu.dao.IArticleInfoDao;
import com.hz21city.xiangqu.dao.ICardInfoDao;
import com.hz21city.xiangqu.dao.ICardRecordInfoDao;
import com.hz21city.xiangqu.dao.ICommentDao;
import com.hz21city.xiangqu.dao.ICouponInfoDao;
import com.hz21city.xiangqu.dao.IEventSignDao;
import com.hz21city.xiangqu.dao.IIncomeRecordDao;
import com.hz21city.xiangqu.dao.ILotteryInfoDao;
import com.hz21city.xiangqu.dao.IOrderDao;
import com.hz21city.xiangqu.dao.IShareRecordDao;
import com.hz21city.xiangqu.dao.ISystemMessageDao;
import com.hz21city.xiangqu.dao.IUserAddressDao;
import com.hz21city.xiangqu.dao.IUserInfoDao;
import com.hz21city.xiangqu.dao.IVerifyCodeDao;
import com.hz21city.xiangqu.dao.IWinprizeInfoDao;
import com.hz21city.xiangqu.pojo.Area;
import com.hz21city.xiangqu.pojo.ArticleInfo;
import com.hz21city.xiangqu.pojo.CardInfo;
import com.hz21city.xiangqu.pojo.CardRecordInfo;
import com.hz21city.xiangqu.pojo.CouponInfo;
import com.hz21city.xiangqu.pojo.IncomeRecord;
import com.hz21city.xiangqu.pojo.LotteryInfo;
import com.hz21city.xiangqu.pojo.ShareRecord;
import com.hz21city.xiangqu.pojo.SystemMessage;
import com.hz21city.xiangqu.pojo.UserInfo;
import com.hz21city.xiangqu.pojo.VerifyCode;
import com.hz21city.xiangqu.pojo.WinprizeInfo;
import com.hz21city.xiangqu.service.IUserService;

@Service("userService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class UserSereviceImpl implements IUserService {

	@Resource
	private IUserInfoDao userInfoDao;
	@Resource
	private IVerifyCodeDao verifyCodeDao;
	@Resource
	private IShareRecordDao shareRecordDao;
	@Resource
	private IAreaDao areaDao;
	@Resource
	private ISystemMessageDao sysMsgfoDao;
	@Resource
	private IArticleInfoDao articleInfoDao;
	@Resource
	private IIncomeRecordDao incomerecordDao;
	@Resource
	private ICommentDao commentDao;
	@Resource
	private IEventSignDao eventSignDao;
	@Resource
	private IOrderDao orderDao;
	@Resource
	private IUserAddressDao useraddressDao;
	@Resource
	private ILotteryInfoDao lotteryInfoDao;
	@Resource
	private ICouponInfoDao couponInfoDao;
	@Resource
	private IWinprizeInfoDao winPrizeInfoDao;
	@Resource
	private ICardInfoDao cardInfoDao;
	@Resource
	private ICardRecordInfoDao cardRecordInfoDao;
	@Override
	public List<SystemMessage> getUserSysMessage() {
		List<SystemMessage> usermessage = sysMsgfoDao.getSysMessage(1);
		return usermessage;
	}

	@Override
	public UserInfo getUserInfoById(Long id) {
		UserInfo userinfo = userInfoDao.selectByPrimaryKey(id);
		if (userinfo != null) {
			// 得到省
			if (userinfo.getProvince() != null && userinfo.getProvince() != 0) {
				Area pro = areaDao.selectByPrimaryKey(userinfo.getProvince());
				if (pro != null) {
					userinfo.setProname(pro.getName());
				}
			}
			// 得到市
			if (userinfo.getCity() != null && userinfo.getCity() != 0) {
				Area city = areaDao.selectByPrimaryKey(userinfo.getCity());
				if (city != null) {
					userinfo.setCityname(city.getName());
				}
			}
		}
		return userinfo;
	}

	@Override
	public void updateUser(UserInfo userInfo) {
		userInfoDao.updateByPrimaryKeySelective(userInfo);
	}

	@Override
	public UserInfo login(String userName, String password) throws CustomException {
		UserInfo userinfo = userInfoDao.selectByuserName(userName);
		if (userinfo == null) {
			throw new CustomException(Constants.USER_NAME_EXCEPTION);
		}
		if (!userinfo.getPassword().equals(password)) {
			throw new CustomException(Constants.USER_PWD_EXCEPTION);
		}
		return userinfo;
	}

	@Override
	public synchronized HashMap<String, Object> thirdPartLogin(int type, String openid) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		UserInfo userInfo = null;
		if (type == 1) {
			userInfo = userInfoDao.getUserInfoByQQ_openid(openid);
		}
		if (type == 2) {
			userInfo = userInfoDao.getUserInfoByWeixin_openid(openid);
		}
		if (userInfo != null) {
			result.put("is_first", 0);
		} else {
			Date now = new Date();
			userInfo = new UserInfo();
			char[] nick = CommonUtils.getTimeFormat(now, "HHmmssyyMMdd").toCharArray();
			StringBuffer nickBuffer = new StringBuffer();
			for (int i = 0; i < nick.length; i++) {
				if (i % 4 == 0) {
					nickBuffer.append(new Random().nextInt(10));
				}
				nickBuffer.append(nick[i]);
			}
			String randomId = nickBuffer.toString();
			if (type == 1) {
				userInfo.setUserName("QQ用户" + randomId);
				userInfo.setNickName("QQ用户" + randomId);
				userInfo.setQq_openid(openid);
			} else {
				userInfo.setUserName("微信用户" + randomId);
				userInfo.setNickName("微信用户" + randomId);
				userInfo.setWeixin_openid(openid);
			}
			userInfo.setPassword(CommonUtils.MD5(randomId));
			userInfo.setProvince(Long.valueOf(0));
			userInfo.setCity(Long.valueOf(0));
			userInfo.setArea(Long.valueOf(0));
			userInfo.setUpdateTime(now);
			// String randNum = null;
			// Integer count = 0;
			// do {
			// //6位邀请码
			// randNum = CommonUtils.genRandomNum(6);
			// count = userInfoDao.getUserCountByCode(randNum);
			// } while (count > 0);
			// userInfo.setInvietCode(randNum);
			userInfoDao.insertSelective(userInfo);
			if (type == 1) {
				userInfo = userInfoDao.getUserInfoByQQ_openid(openid);
			}
			if (type == 2) {
				userInfo = userInfoDao.getUserInfoByWeixin_openid(openid);
			}
			userInfo.setInvietCode(CommonUtils.frontCompWidth(userInfo.getId(), 8));
			userInfo.setBalance(userInfo.getBalance() + 500);
			IncomeRecord newUserIncome = new IncomeRecord();
			newUserIncome.setIncome(500.0f);
			newUserIncome.setTime(now);
			newUserIncome.setType(14);
			newUserIncome.setFromUserId(0l);
			newUserIncome.setUserId(userInfo.getId());
			incomerecordDao.insertSelective(newUserIncome);
			userInfoDao.updateByPrimaryKeySelective(userInfo);
			result.put("is_first", 1);
		}
		result.put("user", userInfo);
		return result;
	}

	@Override
	public UserInfo getThirdPartUser(int type, String openid) {
		UserInfo userInfo = null;
		if (type == 1) {
			userInfo = userInfoDao.getUserInfoByQQ_openid(openid);
		}
		if (type == 2) {
			userInfo = userInfoDao.getUserInfoByWeixin_openid(openid);
		}
		return userInfo;
	}

	@Override
	public String getVcode(String moblie) {
		String vcode = "";
		vcode = String.valueOf(new Random().nextInt(9000) + 1000);
		VerifyCode verifyCode = new VerifyCode();
		verifyCode.setPhone(moblie);
		verifyCode.setCode(vcode);
		verifyCode.setCreateTime(new Date());
		verifyCodeDao.insertSelective(verifyCode);
		return vcode;
	}

	@Override
	public void insertShareRecord(Long userId, Long relativeId, Integer type) {
		ShareRecord record = new ShareRecord();
		record.setUserId(userId);
		record.setRelativeId(relativeId);
		record.setType(type);
		record.setDestination(0);
		record.setShareTime(new Date());
		shareRecordDao.insertSelective(record);
	}

	@Override
	public UserInfo register(String userName, String password, String moblie, String vcode, String invietCode) throws CustomException {
		UserInfo userinfo = userInfoDao.getUserInfoByNameOrMoblie(userName, moblie);
		if (userinfo != null) {
			throw new CustomException(Constants.USER_EXIST_EXCEPTION);
		}
		VerifyCode code = verifyCodeDao.getVerifyCodeByMoblie(moblie);
		Date nowdate = new Date();
		// VerifyCode code = verifyCodeDao.getVerifyCodeByMoblieandVcode(moblie, vcode);
		if (code == null || !code.getCode().endsWith(vcode)) {
			throw new CustomException(Constants.VCODE_ERR_EXCEPTION);
		} else if ((nowdate.getTime() - code.getCreateTime().getTime()) > 300000) {
			throw new CustomException(Constants.VCODE_ERR_EXCEPTION);
		}
		// String randNum = null;
		// Integer count = 0;
		// do {
		// //6位邀请码
		// randNum = CommonUtils.genRandomNum(6);
		// count = userInfoDao.getUserCountByCode(randNum);
		// } while (count > 0);
		userinfo = new UserInfo();
		userinfo.setUserName(userName);
		userinfo.setPassword(password);
		userinfo.setMoblie(moblie);
		userinfo.setUpdateTime(new Date());
		userinfo.setProvince(Long.valueOf(0));
		userinfo.setCity(Long.valueOf(0));
		userinfo.setArea(Long.valueOf(0));
		userinfo.setInvietCode(CommonUtils.frontCompWidth(userinfo.getId(), 8));
		userInfoDao.insertSelective(userinfo);
		userinfo = userInfoDao.getUserInfoByNameOrMoblie(userName, moblie);
		// --------------加500积分
		userinfo.setBalance(userinfo.getBalance() + 500);
		IncomeRecord newUserIncome = new IncomeRecord();
		newUserIncome.setIncome(500.0f);
		newUserIncome.setTime(new Date());
		newUserIncome.setType(14);
		newUserIncome.setFromUserId(0l);
		newUserIncome.setUserId(userinfo.getId());
		incomerecordDao.insertSelective(newUserIncome);
		userInfoDao.updateByPrimaryKeySelective(userinfo);
		// ----调整积分（带推广员）----
		if (!CommonUtils.isEmptyString(invietCode)) {
			addPromoteRegisterP(userinfo.getId(), invietCode);
		}
		return userinfo;
	}

	@Override
	public void resetpwd(Long user_id, String password, String moblie, String vcode) throws CustomException {
		UserInfo userinfo = userInfoDao.selectByPrimaryKey(user_id);
		if (userinfo != null) {
			VerifyCode code = verifyCodeDao.getVerifyCodeByMoblie(moblie);
			Date nowdate = new Date();
			// VerifyCode code = verifyCodeDao.getVerifyCodeByMoblieandVcode(moblie, vcode);
			if (code == null || !code.getCode().endsWith(vcode)) {
				throw new CustomException(Constants.VCODE_ERR_EXCEPTION);
			} else if ((nowdate.getTime() - code.getCreateTime().getTime()) > 300000) {
				throw new CustomException(Constants.VCODE_ERR_EXCEPTION);
			}
			userinfo.setPassword(password);
			userInfoDao.updateByPrimaryKeySelective(userinfo);
		}
	}

	@Override
	public UserInfo getUserByName(String userName) {
		return userInfoDao.selectByuserName(userName);
	}

	@Override
	public UserInfo changePassword(Long id, String old_pwd, String new_pwd) throws CustomException {
		UserInfo user = userInfoDao.selectByPrimaryKey(id);
		if (user == null) {
			throw new CustomException(Constants.USER_ERR_EXCEPTION);
		}
		if (!user.getPassword().equals(old_pwd)) {
			throw new CustomException(Constants.USER_OLD_PWD_EXCEPTION);
		}
		user.setPassword(new_pwd);
		userInfoDao.updateByPrimaryKeySelective(user);
		return user;
	}

	@Override
	public UserInfo editUser(long id, String nickName, String password, String moblie, String realName, String gender, String pic, String invietCode) throws CustomException {
		UserInfo user = userInfoDao.selectByPrimaryKey(id);
		if (user == null) {
			throw new CustomException(Constants.USER_ERR_EXCEPTION);
		}
		if (!CommonUtils.isEmptyString(moblie)) {
			UserInfo puser = userInfoDao.getUserInfoByMoblie(moblie);
			if (puser != null && puser.getId() != user.getId()) {
				throw new CustomException(Constants.PHONE_EXIST_EXCEPTION);
			} else {
				if (CommonUtils.isEmptyString(user.getMoblie())) {
					user.setUserName(moblie);
					String pwd = CommonUtils.getRandomNum(6);
					user.setPassword(CommonUtils.MD5(pwd));
					CommonUtils.YMsendSms(moblie, "您的用户名为" + moblie + ",初始密码为" + pwd);
					JpushClientUtil jPushClientUtil = new JpushClientUtil();
					if (!CommonUtils.isEmptyString(user.getClient_id())) {
						jPushClientUtil.sendPushSolo(user.getClient_id(), "您的用户名为" + moblie + ",初始密码为" + pwd);
					}
				} else {
					user.setPassword(password);
				}
				user.setMoblie(moblie);
			}
		}
		// user.setNickName(nickName);
		if (!CommonUtils.isEmptyString(nickName)) {
			user.setNickName(CommonUtils.filterEmoji(nickName));
		}
		if (!CommonUtils.isEmptyString(realName)) {
			user.setRealName(CommonUtils.filterEmoji(realName));
		}
		user.setGender(gender);
		user.setPic(pic);
		userInfoDao.updateByPrimaryKeySelective(user);
		// ----调整积分（带推广员）----
		if (!CommonUtils.isEmptyString(invietCode)) {
			addPromoteRegisterP(user.getId(), invietCode);
		}
		return user;
	}

	@Override
	public List<Area> getAreaByParentId(Long parentId) {
		return areaDao.getAreaListByParentid(parentId);
	}

	@Override
	public Area getAreaById(Long id) {
		return areaDao.selectByPrimaryKey(id);
	}

	@Override
	public Integer getShareRecordAllValue(Long userId, Long relativeId, Integer type) {
		return shareRecordDao.userShare(userId, type, relativeId);
	}

	@Override
	public VerifyCode getVerifyCode(String mobile, String vcode) {
		VerifyCode code = verifyCodeDao.getVerifyCodeByMoblieandVcode(mobile, vcode);
		return code;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = CustomException.class)
	public synchronized void testAdd() throws CustomException {
		UserInfo userInfo = userInfoDao.selectByPrimaryKey(4l);
//		System.out.println("select:" + userInfo.getBalance());
		userInfo.setBalance(userInfo.getBalance() + 1f);
		userInfoDao.updateByPrimaryKey(userInfo);
	}

	@Override
	public ArticleInfo getArticleInfo(Long id) {
		return articleInfoDao.selectByPrimaryKey(id);
	}

	@Override
	public UserInfo getUserInfoByMoblie(String moblie) {
		return userInfoDao.getUserInfoByMoblie(moblie);
	}

	@Override
	public List<IncomeRecord> getMyInvitelist(Long userid, Integer type) {
		List<IncomeRecord> list = incomerecordDao.getIncomeRecordListByUser(userid, type);
		if (list != null && list.size() > 0) {
			for (IncomeRecord incomeRecord : list) {
				UserInfo userinfo = userInfoDao.selectByPrimaryKey(incomeRecord.getFromUserId());
				if (userinfo != null) {
					if (!CommonUtils.isEmptyString(userinfo.getNickName())) {
						incomeRecord.setRelativename(userinfo.getNickName());
					} else if (!CommonUtils.isEmptyString(userinfo.getRealName())) {
						incomeRecord.setRelativename(userinfo.getRealName());
					} else {
						incomeRecord.setRelativename(userinfo.getUserName());
					}
				}
			}
		}
		return list;
	}

	@Override
	public UserInfo getUserInfoByCode(String invietCode) {
		return userInfoDao.getUserInfoByInvietCode(invietCode);
	}

	@Override
	public void addIncomeRecord(IncomeRecord record) {
		incomerecordDao.insertSelective(record);
	}

	@Override
	public VerifyCode getVerifyCode(String moblie) {
		return verifyCodeDao.getVerifyCodeByMoblie(moblie);
	}

	@Override
	public Area getAreaByName(String name) {
		return areaDao.getAreaByName(name);
	}

	@Override
	public List<UserInfo> getUserListHasntInviteCode() {
		return userInfoDao.getUserListHasntInviteCode();
	}

	@Override
	public Integer getUserCountByCode(String randNum) {
		return userInfoDao.getUserCountByCode(randNum);
	}

	@Override
	public List<UserInfo> getAllUserList() {
		return userInfoDao.AllUserList();
	}

	@Override
	public List<ShareRecord> getShareRecordByUser(Long userid) {
		return shareRecordDao.getShareRecordByUser(userid);
	}

	@Override
	public void updateShareRecord(ShareRecord shareRecord) {
		shareRecordDao.updateByPrimaryKeySelective(shareRecord);
	}

	@Override
	public void delUserInfo(UserInfo combineduser) {
		userInfoDao.deleteByPrimaryKey(combineduser.getId());
	}

	@Override
	public void anthorRegister(String moblie, String vcode, String invietCode) throws CustomException {
		UserInfo userinfo = userInfoDao.getUserInfoByMoblie(moblie);
		UserInfo username = userInfoDao.selectByuserName(moblie);
		if (userinfo != null || username != null) {
			throw new CustomException(Constants.USER_EXIST_EXCEPTION);
		}
		VerifyCode code = verifyCodeDao.getVerifyCodeByMoblie(moblie);
		Date nowdate = new Date();
		if (code == null || !code.getCode().endsWith(vcode)) {
			throw new CustomException(Constants.VCODE_ERR_EXCEPTION);
		} else if ((nowdate.getTime() - code.getCreateTime().getTime()) > 300000) {
			throw new CustomException(Constants.VCODE_ERR_EXCEPTION);
		}
		String pword = CommonUtils.getRandomNum(6);
		userinfo = new UserInfo();
		userinfo.setUserName(moblie);
		userinfo.setPassword(CommonUtils.MD5(pword));
		userinfo.setMoblie(moblie);
		userinfo.setUpdateTime(new Date());
		userinfo.setProvince(Long.valueOf(0));
		userinfo.setCity(Long.valueOf(0));
		userinfo.setArea(Long.valueOf(0));
		userInfoDao.insertSelective(userinfo);
		userinfo = userInfoDao.getUserInfoByMoblie(moblie);
		userinfo.setInvietCode(CommonUtils.frontCompWidth(userinfo.getId(), 8));
		// --------------加500积分
		userinfo.setBalance(userinfo.getBalance() + 500);
		IncomeRecord newUserIncome = new IncomeRecord();
		newUserIncome.setIncome(500.0f);
		newUserIncome.setTime(new Date());
		newUserIncome.setType(14);
		newUserIncome.setFromUserId(0l);
		newUserIncome.setUserId(userinfo.getId());
		incomerecordDao.insertSelective(newUserIncome);
		userInfoDao.updateByPrimaryKeySelective(userinfo);
		// ----调整积分（带推广员）----
		if (!CommonUtils.isEmptyString(invietCode)) {
			addPromoteRegisterP(userinfo.getId(), invietCode);
		}
		CommonUtils.YMsendSms(moblie, "您的用户名为" + moblie + ",密码是" + pword);
	}

	@Override
	public void thirdRegister(UserInfo userInfo, String moblie, String invietCode) {
		String pword = CommonUtils.getRandomNum(6);
		userInfo.setMoblie(moblie);
		userInfo.setUserName(moblie);
		userInfo.setPassword(CommonUtils.MD5(pword));
		userInfoDao.updateByPrimaryKeySelective(userInfo);
		// ----调整积分（带推广员）----
		if (!CommonUtils.isEmptyString(invietCode)) {
			addPromoteRegisterP(userInfo.getId(), invietCode);
		}
		CommonUtils.YMsendSms(moblie, "您的用户名为" + moblie + ",密码是" + pword);
	}

	@Override
	public UserInfo anthorApiRegister(String moblie, String vcode, String invietCode) throws CustomException {
		// UserInfo userinfo = userInfoDao.getUserInfoByNameOrMoblie(moblie, moblie);
		// 根据name
		UserInfo username = userInfoDao.selectByuserName(moblie);
		if (username != null) {
			throw new CustomException(Constants.USER_EXIST_EXCEPTION);
		}
		UserInfo userphone = userInfoDao.getUserInfoByMoblie(moblie);
		if (userphone != null) {
			throw new CustomException(Constants.PHONE_EXIST_EXCEPTION);
		}
		VerifyCode code = verifyCodeDao.getVerifyCodeByMoblie(moblie);
		Date nowdate = new Date();
		if (code == null || !code.getCode().endsWith(vcode)) {
			throw new CustomException(Constants.VCODE_ERR_EXCEPTION);
		} else if ((nowdate.getTime() - code.getCreateTime().getTime()) > 300000) {
			throw new CustomException(Constants.VCODE_ERR_EXCEPTION);
		}
		String pword = CommonUtils.getRandomNum(6);
		UserInfo userinfo = new UserInfo();
		userinfo.setUserName(moblie);
		userinfo.setPassword(CommonUtils.MD5(pword));
		userinfo.setMoblie(moblie);
		userinfo.setUpdateTime(new Date());
		userinfo.setProvince(Long.valueOf(0));
		userinfo.setCity(Long.valueOf(0));
		userinfo.setArea(Long.valueOf(0));
		userInfoDao.insertSelective(userinfo);
		userinfo = userInfoDao.getUserInfoByMoblie(moblie);
		userinfo.setInvietCode(CommonUtils.frontCompWidth(userinfo.getId(), 8));
		// --------------加500积分
		userinfo.setBalance(userinfo.getBalance() + 500);
		IncomeRecord newUserIncome = new IncomeRecord();
		newUserIncome.setIncome(500.0f);
		newUserIncome.setTime(new Date());
		newUserIncome.setType(14);
		newUserIncome.setFromUserId(0l);
		newUserIncome.setUserId(userinfo.getId());
		incomerecordDao.insertSelective(newUserIncome);
		userInfoDao.updateByPrimaryKeySelective(userinfo);
		// ----调整积分（带推广员）----
		if (!CommonUtils.isEmptyString(invietCode)) {
			addPromoteRegisterP(userinfo.getId(), invietCode);
		}
		CommonUtils.YMsendSms(moblie, "您的用户名为" + moblie + ",密码是" + pword);
		UserInfo userInfo = userInfoDao.getUserInfoByMoblie(moblie);
		return userInfo;
	}

	@Override
	public void combineUser(long mobileuserid, long combineduserid) {
		// 合并评论
		commentDao.updateCombineComment(mobileuserid, combineduserid);
		// 合并活动报名
		eventSignDao.updateCombineSign(mobileuserid, combineduserid);
		// 合并收益
		// 用户获取的收益记录
		incomerecordDao.updateCombinegetIncome(mobileuserid, combineduserid);
		// 用户产生的收益记录
		incomerecordDao.updateCombinefromIncome(mobileuserid, combineduserid);
		// 合并订单
		// 下单用户合并
		orderDao.updateCreatOrder(mobileuserid, combineduserid);
		// 分享用户合并
		orderDao.updateShareOrder(mobileuserid, combineduserid);
		// 合并分享记录
		shareRecordDao.updateShareCombine(mobileuserid, combineduserid);
		// 合并收货地址
		useraddressDao.updateUserAddressCombine(mobileuserid, combineduserid);
		// 合并用户
		userInfoDao.updateUserInviteCombine(mobileuserid, combineduserid);
		UserInfo combineduser = userInfoDao.selectByPrimaryKey(combineduserid);
		UserInfo mobileuser = userInfoDao.selectByPrimaryKey(mobileuserid);
		mobileuser.setUserName(mobileuser.getMoblie());
		mobileuser.setWeixin_openid(combineduser.getWeixin_openid());
		mobileuser.setBalance(mobileuser.getBalance() + combineduser.getBalance());
		if (CommonUtils.isEmptyString(mobileuser.getRealName())) {
			if (!CommonUtils.isEmptyString(combineduser.getRealName())) {
				mobileuser.setRealName(combineduser.getRealName());
			}
		}
		if (CommonUtils.isEmptyString(mobileuser.getNickName())) {
			if (!CommonUtils.isEmptyString(combineduser.getNickName())) {
				mobileuser.setNickName(CommonUtils.filterEmoji(combineduser.getNickName()));
			}
		}
		if (CommonUtils.isEmptyString(mobileuser.getGender())) {
			if (!CommonUtils.isEmptyString(combineduser.getGender())) {
				mobileuser.setGender(combineduser.getGender());
			}
		}
		if (CommonUtils.isEmptyString(mobileuser.getGender())) {
			if (!CommonUtils.isEmptyString(combineduser.getGender())) {
				mobileuser.setGender(combineduser.getGender());
			}
		}
		if (CommonUtils.isEmptyString(mobileuser.getPic())) {
			if (!CommonUtils.isEmptyString(combineduser.getPic())) {
				mobileuser.setPic(combineduser.getPic());
			}
		}
		userInfoDao.deleteByPrimaryKey(combineduserid);
		userInfoDao.updateByPrimaryKeySelective(mobileuser);
		CommonUtils.YMsendSms(mobileuser.getMoblie(), "用户已合并完成，您的用户名改为" + mobileuser.getMoblie());
	}

	@Override
	public int getTimeIncomeCount(String startTime, String endTime, Long userid, Integer type) {
		if (!CommonUtils.isEmptyString(startTime)) {
			startTime = startTime + " 00:00:00";
		}
		if (!CommonUtils.isEmptyString(endTime)) {
			endTime = endTime + " 23:59:59";
		}
		return incomerecordDao.getTimeIncomeCount(startTime, endTime, userid, type);
	}

	@Override
	public List<IncomeRecord> getMyInvitelist(String startTime, String endTime, Long userid, Integer type) {
		if (!CommonUtils.isEmptyString(startTime)) {
			startTime = startTime + " 00:00:00";
		}
		if (!CommonUtils.isEmptyString(endTime)) {
			endTime = endTime + " 23:59:59";
		}
		List<IncomeRecord> list = incomerecordDao.getIncomeRecordListByUserAndTime(userid, type, startTime, endTime);
		if (list != null && list.size() > 0) {
			for (IncomeRecord incomeRecord : list) {
				UserInfo userinfo = userInfoDao.selectByPrimaryKey(incomeRecord.getFromUserId());
				if (userinfo != null) {
					if (!CommonUtils.isEmptyString(userinfo.getNickName())) {
						incomeRecord.setRelativename(userinfo.getNickName());
					} else if (!CommonUtils.isEmptyString(userinfo.getRealName())) {
						incomeRecord.setRelativename(userinfo.getRealName());
					} else {
						incomeRecord.setRelativename(userinfo.getUserName());
					}
				}
			}
		}
		return list;
	}

	@Override
	public synchronized void addPromoteRegisterP(Long userid, String inviteCode) {
		UserInfo inviteUser = userInfoDao.getUserInfoByInvietCode(inviteCode);
		UserInfo registerUser = userInfoDao.selectByPrimaryKey(userid);
		if (inviteUser != null && inviteUser != null) {
			// 邀请人收益记录
			registerUser.setInviteUserId(inviteUser.getId());
			IncomeRecord inviteRecord = new IncomeRecord();
			inviteRecord.setFromUserId(registerUser.getId());
			inviteRecord.setIncome(Constants.INVITEINCOME);
			inviteRecord.setTime(new Date());
			inviteRecord.setType(8);
			inviteRecord.setUserId(inviteUser.getId());
			incomerecordDao.insertSelective(inviteRecord);
			// 被邀请人收益记录
			IncomeRecord beInviterecord = new IncomeRecord();
			beInviterecord.setFromUserId(inviteUser.getId());
			beInviterecord.setIncome(Constants.BEINVITEINCOME);
			beInviterecord.setTime(new Date());
			beInviterecord.setType(10);
			beInviterecord.setUserId(registerUser.getId());
			incomerecordDao.insertSelective(beInviterecord);
			// 更新两个用户的积分
			inviteUser.setInvietCount(inviteUser.getInvietCount() + 1);
			inviteUser.setBalance(inviteUser.getBalance() + Constants.INVITEINCOME);
			userInfoDao.updateByPrimaryKeySelective(inviteUser);
			registerUser.setBalance(registerUser.getBalance() + Constants.BEINVITEINCOME);
			userInfoDao.updateByPrimaryKeySelective(registerUser);
			// -----------推广员---------------------------
			UserInfo promoter = userInfoDao.selectByPrimaryKey(inviteUser.getInviteUserId());
			if (promoter != null && promoter.getType() != null && promoter.getType() > 0) {
				Integer incomeP = Math.round(Constants.INVITEINCOME * promoter.getType() / 100.0f);
				IncomeRecord incomerecordP = new IncomeRecord();
				incomerecordP.setFromUserId(inviteUser.getId());
				incomerecordP.setUserId(promoter.getId());
				incomerecordP.setType(13);
				incomerecordP.setRelativeId(0l);
				incomerecordP.setIncome(incomeP.floatValue());
				incomerecordP.setTime(new Date());
				incomerecordDao.insertSelective(incomerecordP);
				promoter.setBalance(promoter.getBalance() + incomeP);
				userInfoDao.updateByPrimaryKey(promoter);
			}
		}
	}

	@Override
	public List<LotteryInfo> getLotteryListByUser(long userid) {
		List<LotteryInfo> list = lotteryInfoDao.getLotteryListByUser(userid);
		if(list != null && list.size() > 0){
			for (LotteryInfo lotteryInfo : list) {
				CouponInfo couponinfo = couponInfoDao.selectByPrimaryKey(lotteryInfo.getCouponId());
				if(couponinfo != null){
					lotteryInfo.setCouponinfo(couponinfo);
				}
			}
		}
		return list;
	}

	@Override
	public String couponLottery(String reword_num, long userid, int angel, Long lotteryid) {
		WinprizeInfo prizeinfo = winPrizeInfoDao.getWinprizeInfoByLottery(lotteryid);
		if(prizeinfo != null){
			return "抽奖机会已使用";
		}else{
			WinprizeInfo info = new WinprizeInfo();
			if(angel >= 60){
				info.setPrize(reword_num);
			}else{
				info.setPrize("谢谢惠顾");
			}
			info.setAddtime(new Date());
			info.setState(2);
			info.setUserid(userid);
			info.setLotteryid(lotteryid);
			winPrizeInfoDao.insertSelective(info);
			LotteryInfo lottery = lotteryInfoDao.selectByPrimaryKey(lotteryid);
			lottery.setState(1);
			lotteryInfoDao.updateByPrimaryKeySelective(lottery);
			return "";
		}
	}

	@Override
	public List<WinprizeInfo> getWinprizeInfoList(long userid) {
		List<WinprizeInfo> list = winPrizeInfoDao.getWinprizeInfoList(userid);
		return list;
	}

	@Override
	public CardInfo getCardInfo(String cardNum, String cardPwd, int state) {
		CardInfo info = cardInfoDao.getCardInfo(cardNum, cardPwd, state);
		return info;
	}

	@Override
	public void insertCardRecordInfo(CardRecordInfo record) {
		cardRecordInfoDao.insertSelective(record);
	}

	@Override
	public void updateCardInfo(CardInfo info) {
		cardInfoDao.updateByPrimaryKeySelective(info);
	}

	@Override
	public CardInfo getCardInfoById(Long cardId) {
		CardInfo info = cardInfoDao.selectByPrimaryKey(cardId);
		return info;
	}
}
