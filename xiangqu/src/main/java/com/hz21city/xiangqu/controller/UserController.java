/**
 * 
 */
package com.hz21city.xiangqu.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.daoshun.exception.CustomException;
import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.common.Constants;
import com.hz21city.xiangqu.common.JpushClientUtil;
import com.hz21city.xiangqu.common.WxAuth;
import com.hz21city.xiangqu.pojo.Area;
import com.hz21city.xiangqu.pojo.ArticleInfo;
import com.hz21city.xiangqu.pojo.CardInfo;
import com.hz21city.xiangqu.pojo.CardRecordInfo;
import com.hz21city.xiangqu.pojo.IncomeRecord;
import com.hz21city.xiangqu.pojo.LotteryInfo;
import com.hz21city.xiangqu.pojo.SystemMessage;
import com.hz21city.xiangqu.pojo.UserInfo;
import com.hz21city.xiangqu.pojo.VerifyCode;
import com.hz21city.xiangqu.pojo.WinprizeInfo;
import com.hz21city.xiangqu.service.ICommentService;
import com.hz21city.xiangqu.service.IEarnService;
import com.hz21city.xiangqu.service.IEventService;
import com.hz21city.xiangqu.service.IOrderService;
import com.hz21city.xiangqu.service.IUserAddressService;
import com.hz21city.xiangqu.service.IUserService;
import com.hz21city.xiangqu.service.manage.IMaSysService;
import com.hz21city.xiangqu.service.shop.IShShopService;

@Controller
@RequestMapping("/user")
public class UserController extends ApiBaseController {

	@Resource
	private IUserService userService;
	@Resource
	private IShShopService shShopService;
	@Resource
	private IOrderService orderService;
	@Resource
	private ICommentService commentService;
	@Resource
	private IUserAddressService userAddressService;
	@Resource
	private IEventService eventService;
	@Resource
	private IEarnService earnService;
	@Resource
	private IMaSysService maSysService;

	@RequestMapping("/login")
	@ResponseBody
	public String login(HttpSession session, String userName, String password) {
		String result = "";
		try {
			UserInfo userinfo = userService.login(userName, CommonUtils.MD5(password));
			session.setAttribute("userid", userinfo.getId());
			if (CommonUtils.isEmptyString(userinfo.getMoblie())) {
				result = "falsesuccess";
			} else {
				result = "success";
			}
		} catch (CustomException e) {
			result = "用户名或密码错误";
		}
		return result;
	}

	@RequestMapping("/wxsq")
	public ModelAndView xqsq(HttpSession session, String reurl) throws UnsupportedEncodingException {
		if (reurl.contains("&code=")) {
			reurl = reurl.replaceAll("&code=", "&old_code=");
			// System.out.println("reurl-------" + reurl);
		}
		String URI = "https://open.weixin.qq.com/connect/oauth2/authorize?" + "appid=" + Constants.WX_APPID + "&redirect_uri=" + URLEncoder.encode(reurl, "UTF-8") + "&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
		ModelAndView mov = new ModelAndView("redirect:" + URI);
		return mov;
	}

	@RequestMapping("/toregister")
	public ModelAndView toregister(HttpServletRequest request, HttpServletResponse response, String invietCode, Integer fromweixin) throws IOException {
		ModelAndView mov = new ModelAndView();
		String userAgent = request.getHeader("user-agent");
		if (userAgent.contains("MicroMessenger")) {
			UserInfo userInfo = new WxAuth(request, response, userService).wxAuth();
			if (userInfo == null) {
				return null;
			}
			if (CommonUtils.isEmptyString(userInfo.getMoblie())) {
				mov.addObject("isweixin", 1);
				mov.setViewName("/user/register");
			} else {
				mov.setViewName("redirect:/user/missiongeo");
			}
		} else {
			mov.addObject("isweixin", 0);
			mov.setViewName("/user/register");
		}
		mov.addObject("invietCode", invietCode);
		mov.addObject("fromweixin", fromweixin);
		return mov;
	}

	@RequestMapping("/register")
	public ModelAndView useregister(HttpSession session, HttpServletRequest request, HttpServletResponse response, String vcode, String moblie, String invietCode, Integer isweixin, Integer fromweixin) throws IOException {
		ModelAndView mov = new ModelAndView();
		if (isweixin == null) {
			isweixin = 0;
		}
		if (isweixin == 1) {
			long userid = CommonUtils.parseLong(String.valueOf(session.getAttribute("userid")), 0);
			UserInfo userInfo = userService.getUserInfoById(userid);
			UserInfo userinfo = userService.getUserInfoByMoblie(moblie);
			UserInfo username = userService.getUserByName(moblie);
			if (CommonUtils.isEmptyString(userInfo.getMoblie())) {
				if (username != null) {
					mov.addObject("error", "用户名或手机号已被使用");
					mov.addObject("moblie", moblie);
					mov.addObject("isweixin", 1);
					mov.setViewName("/user/register");
				}
				if (userinfo != null) {
					mov.addObject("error", "用户名或手机号已被使用");
					mov.addObject("combine", "1");
					mov.addObject("moblie", moblie);
					mov.addObject("isweixin", 1);
					mov.setViewName("/user/register");
				} else {
					VerifyCode vCode = userService.getVerifyCode(moblie);
					Date nowdate = new Date();
					if (vCode == null || !vCode.getCode().endsWith(vcode)) {
						mov.addObject("error", "验证码错误或已失效");
						mov.addObject("isweixin", 1);
						mov.setViewName("/user/register");
					} else if ((nowdate.getTime() - vCode.getCreateTime().getTime()) > 300000) {
						mov.addObject("error", "验证码错误或已失效");
						mov.addObject("isweixin", 1);
						mov.setViewName("/user/register");
					} else {
						userService.thirdRegister(userInfo, moblie, invietCode);
						session.setAttribute("userid", userInfo.getId());
						mov.setViewName("redirect:/user/missiongeo");
					}
				}
			}
		} else {
			try {
				userService.anthorRegister(moblie, vcode, invietCode);
				UserInfo userInfo = userService.getUserInfoByMoblie(moblie);
				session.setAttribute("userid", userInfo.getId());
				mov.setViewName("redirect:/user/missiongeo");
			} catch (CustomException e) {
				if (e.getMessage().equals(Constants.USER_EXIST_EXCEPTION)) {
					mov.addObject("error", "用户名或手机号已被使用");
				} else {
					mov.addObject("error", "验证码错误或已失效");
				}
				mov.addObject("isweixin", 0);
				mov.setViewName("/user/register");
			}
		}
		mov.addObject("invietCode", invietCode);
		mov.addObject("fromweixin", fromweixin);
		return mov;
	}

	@RequestMapping("/registerpassword")
	public void registerpassword(Long user_id, String moblie, String vcode, String password, HttpServletResponse response) {
		try {
			userService.resetpwd(user_id, CommonUtils.MD5(password), moblie, vcode);
			CommonUtils.setResponseStr("success", response);
		} catch (CustomException e) {
			if (e.getMessage().equals(Constants.VCODE_ERR_EXCEPTION)) {
				CommonUtils.setResponseStr("error", response);
				return;
			}
		}
	}

	@RequestMapping("/ucenter")
	public ModelAndView userCenter(HttpSession session, Integer isapp) {
		ModelAndView mv = new ModelAndView("/user/user_center");
		long userid = CommonUtils.parseLong(String.valueOf(session.getAttribute("userid")), 0);
		UserInfo user = userService.getUserInfoById(userid);
		String startTime = CommonUtils.getTimeFormat(user.getUpdateTime(), "yyyy-MM-dd HH:mm:ss");
		int count = maSysService.getNewSysmessage(startTime);
		mv.addObject("user", user);
		mv.addObject("count", count);
		// 待付款
		mv.addObject("ordercount1", orderService.getOderCountByState(userid, 1));
		// 待发货
		mv.addObject("ordercount2", orderService.getOderCountByState(userid, 2));
		// 已发货
		mv.addObject("ordercount3", orderService.getOderCountByState(userid, 3));
		mv.addObject("fileurl", CommonUtils.getFileRootUrl());
		mv.addObject("isapp", isapp);
		return mv;
	}

	@RequestMapping("/edit_user")
	public ModelAndView editUser(HttpSession session, Long uid, String nick, String gender) {
		ModelAndView mv = new ModelAndView("/user/user_edit");
		if (uid == null) {
			long userid = CommonUtils.parseLong(String.valueOf(session.getAttribute("userid")), 0);
			mv.addObject("user", userService.getUserInfoById(userid));
		} else {
			UserInfo userInfo = userService.getUserInfoById(uid);
			userInfo.setNickName(CommonUtils.filterEmoji(nick));
			userInfo.setGender(gender);
			userService.updateUser(userInfo);
			mv.addObject("user", userInfo);
		}
		mv.addObject("fileurl", CommonUtils.getFileRootUrl());
		return mv;
	}

	@RequestMapping("/resetpassword")
	public ModelAndView resetpassword(String userName) {
		ModelAndView mv = new ModelAndView("/user/resetpassword");
		UserInfo userinfo = userService.getUserByName(userName);
		mv.addObject("userinfo", userinfo);
		return mv;
	}

	@RequestMapping("/editorpic")
	public ModelAndView editorpic(Long id, String picurl) {
		ModelAndView mv = new ModelAndView("/user/user_editorpic");
		mv.addObject("id", id);
		mv.addObject("picurl", picurl);
		return mv;
	}

	@RequestMapping("/editornikeName")
	public ModelAndView editoruserName(Long id, String nickName) {
		ModelAndView mv = new ModelAndView("/user/user_editoruserName");
		mv.addObject("id", id);
		return mv;
	}

	@RequestMapping("/editormoblie")
	public ModelAndView editormoblie(Long id, String moblie) {
		ModelAndView mov = new ModelAndView("/user/user_editormoblie");
		mov.addObject("id", id);
		mov.addObject("moblie", moblie);
		return mov;
	}

	@ResponseBody
	@RequestMapping("/editor_moblie")
	public HashMap<String, Object> editor_moblie(Long id, String moblie, String vcode) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		UserInfo user = userService.getUserInfoByMoblie(moblie);
		if (user != null && user.getId() != id) {
			result.put("code", 501);
			result.put("message", "该手机已被注册!");
		} else {
			UserInfo userinfo = userService.getUserInfoById(id);
			if (!CommonUtils.isEmptyString(moblie)) {
				VerifyCode code = userService.getVerifyCode(moblie);
				Date nowdate = new Date();
				// VerifyCode verifycode = userService.getVerifyCode(moblie, vcode);
				if (code == null || !code.getCode().endsWith(vcode)) {
					result.put("code", 500);
					result.put("message", "验证码错误!");
				} else if ((nowdate.getTime() - code.getCreateTime().getTime()) > 300000) {
					result.put("code", 501);
					result.put("message", "验证码已过期!");
				} else {
					userinfo.setMoblie(moblie);
					userService.updateUser(userinfo);
					result.put("code", 1);
				}
			}
		}
		return result;
	}

	@RequestMapping("/loginedmoblie")
	@ResponseBody
	public HashMap<String, Object> loginedmoblie(HttpSession session, String moblie, String vcode, String invietCode) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		Long userid = CommonUtils.parseLong(String.valueOf(session.getAttribute("userid")), 0);
		UserInfo user = userService.getUserInfoByMoblie(moblie);
		if (user != null && user.getId() != userid) {
			result.put("code", 501);
			result.put("message", "该手机已被注册!");
		} else {
			UserInfo userinfo = userService.getUserInfoById(userid);
			if (!CommonUtils.isEmptyString(moblie)) {
				VerifyCode code = userService.getVerifyCode(moblie);
				Date nowdate = new Date();
				if (code == null || !code.getCode().endsWith(vcode)) {
					result.put("code", 500);
					result.put("message", "验证码错误!");
				} else if ((nowdate.getTime() - code.getCreateTime().getTime()) > 300000) {
					result.put("code", 501);
					result.put("message", "验证码已过期!");
				} else {
					if (CommonUtils.isEmptyString(userinfo.getMoblie())) {
						userinfo.setUserName(moblie);
						String pwd = CommonUtils.getRandomNum(6);
						userinfo.setPassword(CommonUtils.MD5(pwd));
						CommonUtils.YMsendSms(moblie, "您的用户名为" + userinfo.getUserName() + ",初始密码为" + pwd);
						JpushClientUtil jPushClientUtil = new JpushClientUtil();
						if (!CommonUtils.isEmptyString(userinfo.getClient_id())) {
							jPushClientUtil.sendPushSolo(userinfo.getClient_id(), "您的用户名为" + userinfo.getUserName() + ",初始密码为" + pwd);
						}
					}
					userinfo.setMoblie(moblie);
					userService.updateUser(userinfo);
					// 只要输入验证码 都能获取增加积分
					if (!CommonUtils.isEmptyString(invietCode)) {
						userService.addPromoteRegisterP(userinfo.getId(), invietCode);
					}
					result.put("code", 1);
				}
			}
		}
		return result;
	}

	@RequestMapping("/editorgender")
	public ModelAndView editorgender(Long id, String gender) {
		ModelAndView mv = new ModelAndView("/user/user_editorgender");
		mv.addObject("id", id);
		mv.addObject("gender", gender);
		return mv;
	}

	@RequestMapping("/editor_pic")
	public ModelAndView editor_pic(Long id, @RequestParam(value = "picfile", required = false) MultipartFile picfile) {
		ModelAndView mv = new ModelAndView("redirect:/user/edit_user");
		UserInfo userinfo = userService.getUserInfoById(id);
		if (picfile != null && !picfile.isEmpty()) {
			userinfo.setPic(getFilePathString(picfile));
		}
		userService.updateUser(userinfo);
		return mv;
	}

	@RequestMapping("/editor_nikeName")
	public ModelAndView editor_userName(Long id, String nickName) {
		ModelAndView mv = new ModelAndView("redirect:/user/edit_user");
		UserInfo userinfo = userService.getUserInfoById(id);
		if (!CommonUtils.isEmptyString(nickName)) {
			userinfo.setNickName(CommonUtils.filterEmoji(nickName));
		}
		userService.updateUser(userinfo);
		return mv;
	}

	@RequestMapping("/editor_gender")
	public ModelAndView editor_gender(Long id, String gender) {
		ModelAndView mv = new ModelAndView("redirect:/user/edit_user");
		UserInfo userinfo = userService.getUserInfoById(id);
		userinfo.setGender(gender);
		userService.updateUser(userinfo);
		return mv;
	}

	@RequestMapping("/editor_address")
	public ModelAndView editor_address(Long id, Long province, Long city, Long area) {
		ModelAndView mv = new ModelAndView("redirect:/user/edit_user");
		UserInfo userinfo = userService.getUserInfoById(id);
		if (province == null) {
			userinfo.setProvince(Long.valueOf(0));
		} else {
			userinfo.setProvince(province);
		}
		if (city == null) {
			userinfo.setCity(Long.valueOf(0));
		} else {
			userinfo.setCity(city);
		}
		if (area == null) {
			userinfo.setArea(Long.valueOf(0));
		} else {
			userinfo.setArea(area);
		}
		userService.updateUser(userinfo);
		return mv;
	}

	@RequestMapping("/editoraddress")
	public ModelAndView editoraddress(Long id, Integer type) {
		ModelAndView mv = new ModelAndView("/user/edit_address");
		List<Area> prolist = userService.getAreaByParentId(Long.valueOf(0));
		UserInfo userinfo = userService.getUserInfoById(id);
		if (userinfo.getProvince() != null) {
			List<Area> citylist = userService.getAreaByParentId(userinfo.getProvince());
			mv.addObject("citylist", citylist);
			if (userinfo.getCity() != null) {
				if (citylist != null && citylist.size() > 0) {
					List<Area> arealist = userService.getAreaByParentId(userinfo.getCity());
					mv.addObject("arealist", arealist);
				}
			} else {
				if (citylist != null && citylist.size() > 0) {
					List<Area> arealist = userService.getAreaByParentId(citylist.get(0).getId());
					mv.addObject("arealist", arealist);
				}
			}
		} else {
			if (prolist != null && prolist.size() > 0) {
				List<Area> citylist = userService.getAreaByParentId(prolist.get(0).getId());
				mv.addObject("citylist", citylist);
				if (citylist != null && citylist.size() > 0) {
					List<Area> arealist = userService.getAreaByParentId(citylist.get(0).getId());
					mv.addObject("arealist", arealist);
				}
			}
		}
		mv.addObject("prolist", prolist);
		mv.addObject("type", type);
		mv.addObject("user", userinfo);
		mv.addObject("id", id);
		return mv;
	}

	@RequestMapping("/getAreaByParentId")
	public void selectArea(Long parentid, Integer type, HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<Area> list = userService.getAreaByParentId(parentid);
		if (type == 1) {
			if (list != null && list.size() > 0) {
				List<Area> arealist = userService.getAreaByParentId(list.get(0).getId());
				result.put("arealist", arealist);
			}
			result.put("citylist", list);
			CommonUtils.setMaptoJson(response, result);
		} else {
			result.put("arealist", list);
			CommonUtils.setMaptoJson(response, result);
		}
	}

	@RequestMapping("/message")
	public ModelAndView message(HttpServletRequest request, HttpSession session) {
		ModelAndView mov = new ModelAndView();
		long userid = CommonUtils.parseLong(String.valueOf(session.getAttribute("userid")), 0);
		UserInfo userinfo = userService.getUserInfoById(userid);
		userinfo.setUpdateTime(new Date());
		userService.updateUser(userinfo);
		List<SystemMessage> usermessage = userService.getUserSysMessage();
		mov.addObject("usermessage", usermessage);
		return mov;
	}

	@RequestMapping("/messagedetail")
	public ModelAndView messagedetail(HttpServletRequest request, HttpSession session, long id) {
		ModelAndView mov = new ModelAndView();
		if (id <= 0) {
			System.out.println("id错误");
			return null;
		}
		SystemMessage shopmessage = shShopService.getSysMessageById(id);
		mov.addObject("shopmessage", shopmessage);
		mov.setViewName("/user/messagedetail");
		return mov;
	}

	@RequestMapping("/clearsession")
	public void clearsession(HttpServletResponse response, HttpSession session) {
		session.removeAttribute("userid");
		CommonUtils.setResponseStr("success", response);
	}

	@RequestMapping("/article")
	public ModelAndView article(Long id, Long mission_id, HttpSession session) {
		ModelAndView mov = new ModelAndView();
		ArticleInfo articleinfo = userService.getArticleInfo(id);
		mov.addObject("article", articleinfo);
		mov.addObject("commentlist", commentService.getFiveComments(mission_id, 2));
		mov.addObject("lsize", commentService.getCommentListCount(mission_id, 2));
		mov.addObject("relative_id", mission_id);
		mov.addObject("type", 2);
		mov.addObject("fileRootUrl", CommonUtils.getFileRootUrl());
		UserInfo userinfo = userService.getUserInfoById(CommonUtils.parseLong(String.valueOf(session.getAttribute("userid")), 0));
		if (userinfo != null && !CommonUtils.isEmptyString(userinfo.getMoblie())) {
			mov.addObject("hasMoblie", 1);
		} else {
			mov.addObject("hasMoblie", 0);
		}
		mov.setViewName("/user/article");
		return mov;
	}

	@RequestMapping("/myinvited")
	public ModelAndView myinvited(HttpSession session, String startTime, String endTime) {
		ModelAndView mov = new ModelAndView();
		long userid = CommonUtils.parseLong(String.valueOf(session.getAttribute("userid")), 0);
		List<IncomeRecord> list = new ArrayList<IncomeRecord>();
		// list = userService.getMyInvitelist(userid, 8);
		mov.addObject("invietCount", userService.getTimeIncomeCount(startTime, endTime, userid, 8));
		list = userService.getMyInvitelist(startTime, endTime, userid, 8);
		mov.addObject("list", list);
		mov.addObject("startTime", startTime);
		mov.addObject("endTime", endTime);
		return mov;
	}

	@RequestMapping("/getinvitedcode")
	public ModelAndView getinvitedcode(HttpSession session) {
		ModelAndView mov = new ModelAndView("/user/getinvitedcode");
		long userid = CommonUtils.parseLong(String.valueOf(session.getAttribute("userid")), 0);
		UserInfo userinfo = userService.getUserInfoById(userid);
		mov.addObject("invietCode", userinfo.getInvietCode());
		String webRootUrl = CommonUtils.getWebRootUrl();
		String lookurl = webRootUrl + "user/toregister?invietCode=" + userinfo.getInvietCode();
		String codepic = CommonUtils.creatQrcode(lookurl);
		String fileRootUrl = CommonUtils.getFileRootUrl();
		mov.addObject("lookurl", lookurl);
		mov.addObject("userinfo", userinfo);
		mov.addObject("codepic", codepic);
		mov.addObject("fileRootUrl", fileRootUrl);
		return mov;
	}

	@RequestMapping("/getcityid")
	public ModelAndView getcityid(String city, String area, HttpServletResponse response) {
		ModelAndView mov = new ModelAndView();
		Area cityarea = userService.getAreaByName(city);
		Area areaArea = userService.getAreaByName(area);
		if (cityarea == null && areaArea == null) {
			mov.setViewName("redirect:/user/mission");
		} else if (cityarea != null && areaArea == null) {
			mov.setViewName("redirect:/user/mission?city=" + cityarea.getId());
		} else if (cityarea == null && areaArea != null) {
			mov.setViewName("redirect:/user/mission?area=" + areaArea.getId());
		} else {
			mov.setViewName("redirect:/user/mission?city=" + cityarea.getId() + "&area=" + areaArea.getId());
		}
		return mov;
	}

	@RequestMapping("/combineuser")
	public void combineuser(String mobile, HttpSession session, HttpServletResponse response) {
		long combineduserid = CommonUtils.parseLong(String.valueOf(session.getAttribute("userid")), 0);
		UserInfo mobileuser = userService.getUserInfoByMoblie(mobile);
		if (!CommonUtils.isEmptyString(mobileuser.getWeixin_openid())) {
			CommonUtils.setResponseStr("error1", response);
		} else {
			long mobileuserid = mobileuser.getId();
			// 合并
			userService.combineUser(mobileuserid, combineduserid);
			session.setAttribute("userid", mobileuserid);
			CommonUtils.setResponseStr("success", response);
		}
	}

	@RequestMapping("/mylottery")
	public ModelAndView mylottery(HttpSession session, Integer state) {
		ModelAndView mov = new ModelAndView("/user/lottery_prize");
		long userid = CommonUtils.parseLong(String.valueOf(session.getAttribute("userid")), 0);
		if (state == null) {
			state = 1;
		}
		// if(state == 1){
		// 取抽奖机会记录
		List<LotteryInfo> lotterylist = userService.getLotteryListByUser(userid);
		mov.addObject("lotterylist", lotterylist);
		// }else{
		// 取抽奖机会记录
		List<WinprizeInfo> prizelist = userService.getWinprizeInfoList(userid);
		mov.addObject("prizelist", prizelist);
		// }
		mov.addObject("state", state);
		return mov;
	}

	@RequestMapping("/tocouponlottery")
	public ModelAndView tocouponlottery(HttpSession session, Long lotteryid) {
		ModelAndView mov = new ModelAndView();
		if (lotteryid == null || lotteryid == 0) {
			mov.setViewName("redirect:/user/mylottery");
		} else {
			mov.addObject("lotteryid", lotteryid);
			mov.setViewName("/user/couponlottery");
		}
		return mov;
	}

	@RequestMapping("/myprize")
	public ModelAndView myprize(HttpSession session, Integer isapp) {
		ModelAndView mov = new ModelAndView("/user/myprize");
		long userid = CommonUtils.parseLong(String.valueOf(session.getAttribute("userid")), 0);
		// 取抽奖机会记录
		List<WinprizeInfo> list = userService.getWinprizeInfoList(userid);
		mov.addObject("isapp", isapp);
		mov.addObject("list", list);
		return mov;
	}

	@RequestMapping("/cardVerification")
	public ModelAndView cardVerification(HttpSession session) {
		ModelAndView mov = new ModelAndView();
		mov.setViewName("/user/card_verification");
		return mov;
	}

	@RequestMapping("/checkCard")
	public ModelAndView checkCard(String cardNum, String cardPwd) {
		ModelAndView mov = new ModelAndView();
		CardInfo info = userService.getCardInfo(cardNum, cardPwd, 0);
		if (info == null) {
			mov.addObject("message", "您输入信息有误，请核对信息");
			mov.setViewName("/user/card_verification");
		} else {
			mov.addObject("cardId", info.getId());
			mov.setViewName("/user/card_checkinfo");
		}
		return mov;
	}

	@RequestMapping("/addCardRecord")
	public ModelAndView addCardRecord(String address, String name, String phone, Long cardId) {
		ModelAndView mov = new ModelAndView();
		CardRecordInfo record = new CardRecordInfo();
		record.setAddress(address);
		record.setAddtime(new Date());
		record.setCardId(cardId);
		record.setName(name);
		record.setPhone(phone);
		userService.insertCardRecordInfo(record);
		CardInfo info = userService.getCardInfoById(cardId);
		info.setState(1);
		userService.updateCardInfo(info);
		mov.setViewName("/user/verification_success");
		return mov;
	}
}
