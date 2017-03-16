package com.hz21city.xiangqu.service;

import java.util.HashMap;
import java.util.List;

import com.daoshun.exception.CustomException;
import com.hz21city.xiangqu.pojo.Area;
import com.hz21city.xiangqu.pojo.ArticleInfo;
import com.hz21city.xiangqu.pojo.CardInfo;
import com.hz21city.xiangqu.pojo.CardRecordInfo;
import com.hz21city.xiangqu.pojo.IncomeRecord;
import com.hz21city.xiangqu.pojo.LotteryInfo;
import com.hz21city.xiangqu.pojo.ShareRecord;
import com.hz21city.xiangqu.pojo.SystemMessage;
import com.hz21city.xiangqu.pojo.UserInfo;
import com.hz21city.xiangqu.pojo.VerifyCode;
import com.hz21city.xiangqu.pojo.WinprizeInfo;

public interface IUserService {

	public abstract UserInfo getUserInfoById(Long id);

	public abstract void updateUser(UserInfo userInfo);

	public abstract UserInfo login(String userName, String password) throws CustomException;

	public abstract HashMap<String, Object> thirdPartLogin(int type, String openid);

	public abstract UserInfo getThirdPartUser(int type, String openid);

	public abstract String getVcode(String moblie);

	public abstract void insertShareRecord(Long userId, Long relativeId, Integer type);

	public abstract UserInfo register(String userName, String password, String moblie, String vcode, String invietCode) throws CustomException;

	public abstract void resetpwd(Long user_id, String password, String moblie, String vcode) throws CustomException;

	public abstract UserInfo getUserByName(String userName);

	public abstract UserInfo changePassword(Long id, String old_pwd, String new_pwd) throws CustomException;

	public abstract UserInfo editUser(long id, String nickName, String password, String moblie, String realName, String gender, String pic, String invietCode) throws CustomException;

	public abstract List<Area> getAreaByParentId(Long parentId);

	public abstract Area getAreaById(Long id);

	List<SystemMessage> getUserSysMessage();

	public abstract Integer getShareRecordAllValue(Long userId, Long relativeId, Integer type);

	public abstract VerifyCode getVerifyCode(String mobile, String vcode);

	public abstract void testAdd() throws CustomException;

	public abstract ArticleInfo getArticleInfo(Long id);

	public abstract UserInfo getUserInfoByMoblie(String moblie);

	public abstract List<IncomeRecord> getMyInvitelist(Long userid, Integer type);

	public abstract UserInfo getUserInfoByCode(String invietCode);

	public abstract void addIncomeRecord(IncomeRecord record);

	public abstract VerifyCode getVerifyCode(String moblie);

	public abstract Area getAreaByName(String name);

	public abstract List<UserInfo> getUserListHasntInviteCode();

	public abstract Integer getUserCountByCode(String randNum);

	public abstract List<UserInfo> getAllUserList();

	public abstract List<ShareRecord> getShareRecordByUser(Long combineduserid);

	public abstract void updateShareRecord(ShareRecord shareRecord);

	public abstract void delUserInfo(UserInfo combineduser);

	public abstract void anthorRegister(String moblie, String vcode, String invietCode) throws CustomException;

	public abstract void thirdRegister(UserInfo userInfo, String moblie, String invietCode);

	public abstract UserInfo anthorApiRegister(String moblie, String vcode, String invietCode) throws CustomException;

	public abstract void combineUser(long mobileuserid, long combineduserid);

	public abstract int getTimeIncomeCount(String startTime, String endTime, Long userid, Integer type);

	public abstract List<IncomeRecord> getMyInvitelist(String startTime, String endTime, Long userid, Integer type);

	// ------添加推广员的邀请收益-----
	public abstract void addPromoteRegisterP(Long userid, String inviteCode);
	//	取抽奖机会记录	
	public abstract List<LotteryInfo> getLotteryListByUser(long userid);

	public abstract String couponLottery(String reword_num, long userid, int angel, Long lotteryid);

	public abstract List<WinprizeInfo> getWinprizeInfoList(long userid);

	public abstract CardInfo getCardInfo(String cardNum, String cardPwd, int state);

	public abstract void insertCardRecordInfo(CardRecordInfo record);

	public abstract void updateCardInfo(CardInfo info);

	public abstract CardInfo getCardInfoById(Long cardId);
}
