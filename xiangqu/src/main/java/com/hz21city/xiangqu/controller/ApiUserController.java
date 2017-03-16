package com.hz21city.xiangqu.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daoshun.exception.CustomException;
import com.daoshun.exception.NullParameterException;
import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.common.Constants;
import com.hz21city.xiangqu.pojo.AdInfo;
import com.hz21city.xiangqu.pojo.Area;
import com.hz21city.xiangqu.pojo.CouponInfo;
import com.hz21city.xiangqu.pojo.EventInfo;
import com.hz21city.xiangqu.pojo.GoodsInfo;
import com.hz21city.xiangqu.pojo.HomeActivity;
import com.hz21city.xiangqu.pojo.IncomeRecord;
import com.hz21city.xiangqu.pojo.MissionInfo;
import com.hz21city.xiangqu.pojo.ModelInfo;
import com.hz21city.xiangqu.pojo.NewYearLottery;
import com.hz21city.xiangqu.pojo.ShankeInfo;
import com.hz21city.xiangqu.pojo.ShankeRecordInfo;
import com.hz21city.xiangqu.pojo.SubjectInfo;
import com.hz21city.xiangqu.pojo.UserInfo;
import com.hz21city.xiangqu.service.IAdService;
import com.hz21city.xiangqu.service.ICommentService;
import com.hz21city.xiangqu.service.ICouponService;
import com.hz21city.xiangqu.service.IEarnService;
import com.hz21city.xiangqu.service.IEventService;
import com.hz21city.xiangqu.service.IGoodsService;
import com.hz21city.xiangqu.service.IMissionService;
import com.hz21city.xiangqu.service.IOrderService;
import com.hz21city.xiangqu.service.IShankeService;
import com.hz21city.xiangqu.service.ISubjectService;
import com.hz21city.xiangqu.service.IUserAddressService;
import com.hz21city.xiangqu.service.IUserService;
import com.hz21city.xiangqu.service.manage.IMaModelService;
import com.hz21city.xiangqu.service.manage.IMaSysService;

@Controller
@RequestMapping("/api/user")
public class ApiUserController extends ApiBaseController {

	@Resource
	private IUserService userService;
	@Resource
	private IMissionService missionService;
	@Resource
	private IGoodsService goodsService;
	@Resource
	private IEventService eventService;
	@Resource
	private ISubjectService subjectService;
	@Resource
	private ICouponService couponService;
	@Resource
	private IAdService adService;
	@Resource
	private IEarnService earnService;
	@Resource
	private IMaModelService maModelService;
	@Resource
	private ICommentService commentService;
	@Resource
	private IOrderService orderService;
	@Resource
	private IUserAddressService userAddressService;
	@Resource
	private IMaSysService maSysService;
	@Resource
	private IShankeService shankeService;

	/**
	 * 登录
	 * 
	 * @param request
	 * @param rep
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/login", produces = "application/json; charset=UTF-8")
	public String login(HttpServletRequest request, HttpServletResponse rep, HttpSession session) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			request.setCharacterEncoding("utf-8");
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			String client_id = request.getParameter("client_id");
			CommonUtils.validateEmpty(userName);
			CommonUtils.validateEmpty(password);
			UserInfo userinfo = userService.login(userName, password);
			if (!CommonUtils.isEmptyString(client_id)) {
				userinfo.setClient_id(client_id);
				userService.updateUser(userinfo);
			}
			map.put("user", userinfo);
			session.setAttribute("userid", userinfo.getId());
			map.putAll(Constants.SUCCESS_RESULT_MAP);
			return gson.toJson(map);
		} catch (CustomException e) {
			return e.getMessage();
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.EXCEPTION;
		}
	}

	/**
	 * 注册
	 */
	@ResponseBody
	@RequestMapping(value = "/register", produces = "application/json; charset=UTF-8")
	public String register(HttpServletRequest request, HttpServletResponse rep) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			request.setCharacterEncoding("utf-8");
			// String userName = request.getParameter("userName");
			// String password = request.getParameter("password");
			String moblie = request.getParameter("moblie");
			String vcode = request.getParameter("vcode");
			String invietCode = request.getParameter("invietCode");
			// CommonUtils.validateEmpty(userName);
			// CommonUtils.validateEmpty(password);
			CommonUtils.validateEmpty(moblie);
			CommonUtils.validateEmpty(vcode);
			map.put("user", userService.anthorApiRegister(moblie, vcode, invietCode));
			map.putAll(Constants.SUCCESS_RESULT_MAP);
			return gson.toJson(map);
		} catch (CustomException e) {
			return e.getMessage();
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.EXCEPTION;
		}
	}

	/**
	 * 第三方登录
	 */
	@ResponseBody
	@RequestMapping(value = "/thirdpartlogin", produces = "application/json; charset=UTF-8")
	public String thirdPartLogin(HttpServletRequest request, HttpServletResponse rep) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			request.setCharacterEncoding("utf-8");
			String type = request.getParameter("type");
			String openid = request.getParameter("openid");
			CommonUtils.validateEmpty(openid);
			int typeint = CommonUtils.parseInt(type, 0);
			if (typeint != 1 && typeint != 2) {
				throw new NullParameterException();
			}
			map.putAll(userService.thirdPartLogin(typeint, openid));
			map.putAll(Constants.SUCCESS_RESULT_MAP);
			return gson.toJson(map);
			// return mapper.writeValueAsString(map);
		} catch (CustomException e) {
			return e.getMessage();
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.EXCEPTION;
		}
	}

	/**
	 * 获取验证码
	 */
	@ResponseBody
	@RequestMapping(value = "/getvcode", produces = "application/json; charset=UTF-8")
	public String getVcode(HttpServletRequest request, HttpServletResponse rep, HttpSession session) {
		String sessionStr = String.valueOf(session.getAttribute("user_info"));
		// System.out.println("get session [" + new Date().toString() + "] || " + sessionStr);
		if (CommonUtils.isEmptyString(sessionStr) || "null".equals(sessionStr)) {
			// System.out.println("no session");
			return Constants.EXCEPTION;
		}
		String requestUrl = request.getRequestURL().toString();
		if (!requestUrl.contains("https") && request.getParameter("type") != null && !request.getParameter("type").contains("page")) {
			System.out.println("no https");
			// System.out.println(requestUrl);
			// try {
			// rep.sendRedirect(requestUrl.replace("http", "https"));
			// // rep.sendRedirect("https://192.168.1.88:8443/xiangqu/api/user/getvcode");
			// } catch (IOException e) {
			// e.printStackTrace();
			// }
			return Constants.EXCEPTION;
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			request.setCharacterEncoding("utf-8");
			String moblie = request.getParameter("moblie");
			CommonUtils.validateEmpty(moblie);
			String vcode = userService.getVcode(moblie);
			map.put("vcode", CommonUtils.MD5(CommonUtils.MD5(vcode) + Constants.SALT));
			map.putAll(Constants.SUCCESS_RESULT_MAP);
			CommonUtils.YMsendSms(moblie, "您的验证码是：" + vcode);
			// CommonUtils.httpsRequest("http://ums.zj165.com:8888/sms_hb/services/Sms?wsdl",
			// "post",
			// "In0=005660&In1=hz_esy&In2=esy1227&In3=短信内容&In4=15067129600&In5=&In6=&In7=1");
			return gson.toJson(map);
		} catch (CustomException e) {
			return e.getMessage();
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.EXCEPTION;
		}
	}

	/**
	 * 根据用户名获取用户
	 * 
	 * @param request
	 * @param rep
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getuser", produces = "application/json; charset=UTF-8")
	public String userphone(HttpServletRequest request, HttpServletResponse rep) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			request.setCharacterEncoding("utf-8");
			String userName = request.getParameter("userName");
			CommonUtils.validateEmpty(userName);
			map.put("user", userService.getUserByName(userName));
			map.putAll(Constants.SUCCESS_RESULT_MAP);
			return gson.toJson(map);
		} catch (CustomException e) {
			return e.getMessage();
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.EXCEPTION;
		}
	}

	/**
	 * 修改密码
	 */
	@ResponseBody
	@RequestMapping(value = "/changepwd", produces = "application/json; charset=UTF-8")
	public String changePasswod(HttpServletRequest request, HttpServletResponse rep) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			request.setCharacterEncoding("utf-8");
			String user_id = request.getParameter("user_id");
			String old_pwd = request.getParameter("old_pwd");
			String new_pwd = request.getParameter("new_pwd");
			CommonUtils.validateEmpty(user_id);
			CommonUtils.validateEmpty(old_pwd);
			CommonUtils.validateEmpty(new_pwd);
			map.put("user", userService.changePassword(CommonUtils.parseLong(user_id, 0), old_pwd, new_pwd));
			map.putAll(Constants.SUCCESS_RESULT_MAP);
			return gson.toJson(map);
			// return mapper.writeValueAsString(map);
		} catch (CustomException e) {
			return e.getMessage();
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.EXCEPTION;
		}
	}

	/**
	 * 修改用户信息
	 */
	@ResponseBody
	@RequestMapping(value = "/edituser", produces = "application/json; charset=UTF-8")
	public String editUser(HttpServletRequest request, HttpServletResponse rep) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			request.setCharacterEncoding("utf-8");
			String user_id = request.getParameter("user_id");
			String password = request.getParameter("password");
			String realName = request.getParameter("realName");
			String nickName = request.getParameter("nickName");
			String gender = request.getParameter("gender");
			String moblie = request.getParameter("moblie");
			String invietCode = request.getParameter("invietCode");
			String pic = getFilePathString(request);
			CommonUtils.validateEmpty(user_id);
			map.put("user", userService.editUser(CommonUtils.parseLong(user_id, 0), nickName, password, moblie, realName, gender, pic, invietCode));
			map.putAll(Constants.SUCCESS_RESULT_MAP);
			return gson.toJson(map);
		} catch (CustomException e) {
			return e.getMessage();
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.EXCEPTION;
		}
	}

	/**
	 * 分享后调此接口
	 * 
	 * @param request
	 * @param rep
	 */
	@ResponseBody
	@RequestMapping(value = "/shareRecord", produces = "application/json; charset=UTF-8")
	public void shareRecord(HttpServletRequest request, HttpServletResponse rep) {
		try {
			request.setCharacterEncoding("utf-8");
			// 分享人id
			String userId = request.getParameter("userId");
			CommonUtils.validateEmpty(userId);
			// 分享的内容（任务或商品）id
			String relativeId = request.getParameter("relativeId");
			CommonUtils.validateEmpty(relativeId);
			// 分享类型 1任务 2商品 3专题 4活动
			String type = request.getParameter("type");
			CommonUtils.validateEmpty(type);
			if (CommonUtils.parseInt(type, 0) == 1) {
				MissionInfo missionInfo = missionService.getMissionById(CommonUtils.parseLong(relativeId, 0));
				// Date nowDate = new Date();
				if (missionInfo.getState() <= 2 || missionInfo.getRamianTimes() > 0) {
					// 没有结束或分享没有至达上限时没有分享过时可以分享
					Integer shareRecord = userService.getShareRecordAllValue(CommonUtils.parseLong(userId, 0), CommonUtils.parseLong(relativeId, 0), CommonUtils.parseInt(type, 0));
					if (shareRecord == 0) {
						userService.insertShareRecord(CommonUtils.parseLong(userId, 0), CommonUtils.parseLong(relativeId, 0), CommonUtils.parseInt(type, 0));
					}
					missionInfo.setRamianTimes(missionInfo.getRamianTimes() - 1);
					missionService.updateMissionInfo(missionInfo);
				} else {
					// 活动结束或分享到达上限时不再记录
				}
			} else {
				Integer shareRecord = userService.getShareRecordAllValue(CommonUtils.parseLong(userId, 0), CommonUtils.parseLong(relativeId, 0), CommonUtils.parseInt(type, 0));
				if (shareRecord == 0) {
					userService.insertShareRecord(CommonUtils.parseLong(userId, 0), CommonUtils.parseLong(relativeId, 0), CommonUtils.parseInt(type, 0));
				}
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}

	/**
	 * 分享内容
	 * 
	 * @param request
	 * @param rep
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/sharedetail", produces = "application/json; charset=UTF-8")
	public String sharedetail(HttpServletRequest request, HttpServletResponse rep) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			request.setCharacterEncoding("utf-8");
			// 分享id
			String id = request.getParameter("id");
			CommonUtils.validateEmpty(id);
			// 分享类型 1任务，2商品， 3活动， 4专题， 5优惠券
			String type = request.getParameter("type");
			CommonUtils.validateEmpty(type);
			if (CommonUtils.parseInt(type, 0) == 1) {
				MissionInfo missioninfo = missionService.getMissionById(CommonUtils.parseLong(id, 0));
				map.put("title", missioninfo.getName());
				map.put("picurl", CommonUtils.getFileRootUrl() + missioninfo.getPic());
				if (!CommonUtils.isEmptyString(missioninfo.getIntro())) {
					map.put("detail", missioninfo.getIntro());
				} else {
					map.put("detail", missioninfo.getName());
				}
			} else if (CommonUtils.parseInt(type, 0) == 2) {
				GoodsInfo goodsinfo = goodsService.getGoodsInfo(CommonUtils.parseLong(id, 0));
				map.put("title", goodsinfo.getName());
				map.put("picurl", CommonUtils.getFileRootUrl() + goodsinfo.getMianPic());
				map.put("detail", goodsinfo.getName());
			} else if (CommonUtils.parseInt(type, 0) == 3) {
				EventInfo eventinfo = eventService.selectByPrimaryKey(CommonUtils.parseLong(id, 0));
				map.put("title", eventinfo.getName());
				map.put("picurl", CommonUtils.getFileRootUrl() + eventinfo.getPic());
				if (!CommonUtils.isEmptyString(eventinfo.getIntro())) {
					map.put("detail", eventinfo.getIntro());
				} else {
					map.put("detail", eventinfo.getName());
				}
			} else if (CommonUtils.parseInt(type, 0) == 4) {
				SubjectInfo subjectinfo = subjectService.getSubjectById(CommonUtils.parseLong(id, 0));
				map.put("title", subjectinfo.getName());
				map.put("picurl", CommonUtils.getFileRootUrl() + subjectinfo.getPic());
				if (!CommonUtils.isEmptyString(subjectinfo.getIntro())) {
					map.put("detail", subjectinfo.getIntro());
				} else {
					map.put("detail", subjectinfo.getName());
				}
			} else if (CommonUtils.parseInt(type, 0) == 5) {
				CouponInfo couponinfo = couponService.getCouponInfoById(CommonUtils.parseLong(id, 0));
				map.put("title", couponinfo.getTitle());
				if (!CommonUtils.isEmptyString(couponinfo.getIntro())) {
					map.put("detail", couponinfo.getIntro());
				} else {
					map.put("detail", couponinfo.getTitle());
				}
			}
			map.put("code", 1);
			map.put("message", "分享成功");
			return gson.toJson(map);
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	/**
	 * 获取导图
	 * 
	 * @param request
	 * @param rep
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/adlist", produces = "application/json; charset=UTF-8")
	public String adlist(HttpServletRequest request, HttpServletResponse rep, HttpSession session) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			List<String> adlist = new ArrayList<String>();
			List<AdInfo> adlists = adService.getAdListByType(2);
			if (adlists != null && adlists.size() > 0) {
				for (AdInfo adInfo : adlists) {
					String picurl = CommonUtils.getFileRootUrl() + adInfo.getPic();
					adInfo.setPic(picurl);
					adlist.add(picurl);
				}
			}
			map.put("adlist", adlist);
			map.put("code", 1);
			map.put("message", "获取成功");
			String sessionStr = UUID.randomUUID().toString();
			System.out.println("set session [" + new Date().toString() + "] || " + sessionStr);
			session.setAttribute("user_info", sessionStr);
			return gson.toJson(map);
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	/**
	 * 获取分享banner
	 * 
	 * @param request
	 * @param rep
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bannerlist", produces = "application/json; charset=UTF-8")
	public String bannerlist(HttpServletRequest request, HttpServletResponse rep) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			List<AdInfo> adlists = adService.getAdListByType(1);
			if (adlists != null && adlists.size() > 0) {
				for (AdInfo adInfo : adlists) {
					String picurl = CommonUtils.getFileRootUrl() + adInfo.getPic();
					adInfo.setPic(picurl);
				}
			}
			List<HomeActivity> homeActivities = adService.getHomeActivities();
			if (homeActivities != null && homeActivities.size() > 0) {
				for (HomeActivity homeActivity : homeActivities) {
					String picurl = CommonUtils.getFileRootUrl() + homeActivity.getPic();
					homeActivity.setPic(picurl);
				}
			}
			map.put("bannerlist", adlists);
			map.put("activitylist", homeActivities);
			map.put("code", 1);
			map.put("message", "获取成功");
			return gson.toJson(map);
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	/**
	 * 任务列表
	 * 
	 * @param request
	 * @param rep
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/missionlist", produces = "application/json; charset=UTF-8")
	public String missionlist(HttpServletRequest request, HttpServletResponse rep) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			request.setCharacterEncoding("utf-8");
			String user_id = request.getParameter("user_id");
			String province = request.getParameter("province");
			String city = request.getParameter("city");
			String area = request.getParameter("area");
			CommonUtils.validateEmpty(user_id);
			if (!CommonUtils.isEmptyString(province) && !province.equals("0")) {
				map.put("proviceName", userService.getAreaById(CommonUtils.parseLong(province, 0)).getName());
			}
			if (!CommonUtils.isEmptyString(city) && !city.equals("0")) {
				map.put("cityName", userService.getAreaById(CommonUtils.parseLong(city, 0)).getName());
			}
			if (!CommonUtils.isEmptyString(area) && !area.equals("0")) {
				map.put("areaName", userService.getAreaById(CommonUtils.parseLong(area, 0)).getName());
			}
			// List<MissionInfo> missionlist = missionService.getMissionList(CommonUtils.parseLong(user_id, 0), CommonUtils.parseLong(province, 0), CommonUtils.parseLong(city, 0));
			List<MissionInfo> missionlist = missionService.getMissionList(CommonUtils.parseLong(user_id, 0), CommonUtils.parseLong(province, 0), CommonUtils.parseLong(city, 0),
					CommonUtils.parseLong(area, 0));
			if (missionlist != null && missionlist.size() > 0) {
				for (MissionInfo missionInfo : missionlist) {
					missionInfo.setPic(CommonUtils.getFileRootUrl() + missionInfo.getPic());
				}
			}
			map.put("missionlist", missionlist);
			map.put("code", 1);
			return gson.toJson(map);
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	/**
	 * 定位任务列表
	 * 
	 * @param request
	 * @param rep
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/missionlistgeo", produces = "application/json; charset=UTF-8")
	public String missionlistgeo(HttpServletRequest request, HttpServletResponse rep) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			request.setCharacterEncoding("utf-8");
			String user_id = request.getParameter("user_id");
			String cityname = request.getParameter("cityname");
			String areaname = request.getParameter("areaname");
			CommonUtils.validateEmpty(user_id);
			Long city_id = null;
			Long area_id = null;
			if (!CommonUtils.isEmptyString(cityname)) {
				Area area = userService.getAreaByName(cityname);
				city_id = area.getId();
			}
			if (!CommonUtils.isEmptyString(areaname)) {
				Area area = userService.getAreaByName(areaname);
				area_id = area.getId();
			}
			// List<MissionInfo> missionlist = missionService.getMissionList(CommonUtils.parseLong(user_id, 0), null, city_id);
			List<MissionInfo> missionlist = missionService.getMissionList(CommonUtils.parseLong(user_id, 0), null, city_id, area_id);
			if (missionlist != null && missionlist.size() > 0) {
				for (MissionInfo missionInfo : missionlist) {
					missionInfo.setPic(CommonUtils.getFileRootUrl() + missionInfo.getPic());
				}
			}
			map.put("cityName", cityname);
			map.put("areaName", areaname);
			map.put("missionlist", missionlist);
			map.put("code", 1);
			return gson.toJson(map);
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	/**
	 * 获取动态
	 * 
	 * @param request
	 * @param rep
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/dynamic", produces = "application/json; charset=UTF-8")
	public String dynamic(HttpServletRequest request, HttpServletResponse rep) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			List<AdInfo> adlists = adService.getAdListByType(3);
			if (adlists != null && adlists.size() > 0) {
				map.put("dynamic", adlists.get(0));
				map.put("code", 1);
				map.put("message", "获取成功");
			} else {
				map.put("code", 500);
				map.put("message", "暂无数据");
			}
			return gson.toJson(map);
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@ResponseBody
	@RequestMapping(value = "/test", produces = "application/json; charset=UTF-8")
	public String test(HttpServletRequest request, HttpServletResponse rep) {
		try {
			userService.testAdd();
		} catch (CustomException e) {
		}
		return "ok";
	}

	@ResponseBody
	@RequestMapping(value = "/submitcontent", produces = "text/html; charset=UTF-8")
	public String submitcontent(String mobile, String name, String address) {
		return "success";
	}

	@ResponseBody
	@RequestMapping(value = "/insertinvitecode", produces = "text/html; charset=UTF-8")
	public String insertinvitecode(HttpServletRequest request, HttpServletResponse rep) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			request.setCharacterEncoding("utf-8");
			// List<UserInfo> userlist = userService.getUserListHasntInviteCode();
			List<UserInfo> userlist = userService.getAllUserList();
			if (userlist != null && userlist.size() > 0) {
				for (UserInfo userInfo : userlist) {
					// String randNum = null;
					// Integer count = 0;
					// do {
					// //6位邀请码
					// randNum = CommonUtils.genRandomNum(6);
//					 count = userService.getUserCountByCode(randNum);
					// } while (count > 0);
					userInfo.setInvietCode(CommonUtils.frontCompWidth(userInfo.getId(), 8));
					userService.updateUser(userInfo);
				}
			}
			map.put("code", 1);
			return gson.toJson(map);
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@ResponseBody
	@RequestMapping(value = "/model", produces = "text/html; charset=UTF-8")
	public String model(HttpServletRequest request, HttpServletResponse rep) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			request.setCharacterEncoding("utf-8");
			List<ModelInfo> model = maModelService.getAllModelList();
			String fileUrl = CommonUtils.getFileRootUrl();
			if (model != null && model.size() > 0) {
				for (ModelInfo modelInfo : model) {
					if (!CommonUtils.isEmptyString(modelInfo.getPic())) {
						modelInfo.setPic(fileUrl + modelInfo.getPic());
					}
				}
			}
			map.put("model", model);
			map.put("bgpic", "http://www.xiangqu100.com/modelbg1.jpg");
			map.put("code", 1);
			return gson.toJson(map);
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	/**
	 * 
	 * @param mobileuserid
	 *            拥有手机号的userid
	 * @param combineduserid
	 *            合并后需要删除的userid
	 */
	@ResponseBody
	@RequestMapping(value = "/combine", produces = "text/html; charset=UTF-8")
	public String combine(HttpServletRequest request, HttpServletResponse rep) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			request.setCharacterEncoding("utf-8");
			String mobile = request.getParameter("mobile");
			String userid = request.getParameter("userid");
			UserInfo mobileuser = userService.getUserInfoByMoblie(mobile);
			if (!CommonUtils.isEmptyString(mobileuser.getWeixin_openid())) {
				map.put("code", 101);
				map.put("message", "该手机号已和其他微信号绑定");
			} else {
				long mobileuserid = mobileuser.getId();
				long combineduserid = CommonUtils.parseLong(userid, 0);
				// 合并
				userService.combineUser(mobileuserid, combineduserid);
				UserInfo newUser = userService.getUserInfoById(mobileuserid);
				map.put("code", 1);
				map.put("user", newUser);
			}
			return gson.toJson(map);
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@ResponseBody
	@RequestMapping(value = "/syscount", produces = "text/html; charset=UTF-8")
	public String syscount(HttpServletRequest request, HttpServletResponse rep) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			request.setCharacterEncoding("utf-8");
			String userid = request.getParameter("userid");
			UserInfo user = userService.getUserInfoById(CommonUtils.parseLong(userid, 0));
			String startTime = CommonUtils.getTimeFormat(user.getUpdateTime(), "yyyy-MM-dd HH:mm:ss");
			int count = maSysService.getNewSysmessage(startTime);
			map.put("count", count);
			map.put("code", 1);
			return gson.toJson(map);
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@ResponseBody
	@RequestMapping(value = "/lottery", produces = "text/html; charset=UTF-8")
	public String lottery(HttpSession session) {
		long userid = CommonUtils.parseLong(String.valueOf(session.getAttribute("userid")), 0);
		// 0-60 谢谢参与 60%
		// 60-120 一等奖 0.5%
		// 120-180 二等奖 2%
		// 180-240 三等奖 4%
		// 240-300 安慰奖 37%
		// 300-360 特等奖 0.3%
		int reword = new Random().nextInt(1000) + 1;
		int angel = 30;
		int reword_num = 0;
		if (reword <= 3) {
			angel = new Random().nextInt(59) + 301;
			reword_num = 500;
		} else if (reword <= 8) {
			angel = new Random().nextInt(59) + 61;
			reword_num = 200;
		} else if (reword <= 28) {
			angel = new Random().nextInt(59) + 121;
			reword_num = 80;
		} else if (reword <= 68) {
			angel = new Random().nextInt(59) + 181;
			reword_num = 30;
		} else if (reword <= 438) {
			angel = new Random().nextInt(59) + 241;
			reword_num = 10;
		} else {
			angel = new Random().nextInt(59) + 1;
		}
		String err = earnService.lottery(reword_num, userid);
		if (CommonUtils.isEmptyString(err)) {
			return String.valueOf(angel);
		} else {
			return err;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/newlottery", produces = "text/html; charset=UTF-8")
	public String newlottery(String mobile) {
		if (CommonUtils.isEmptyString(mobile)) {
			return "{\"err\":\"请输入手机号\"}";
		}
		NewYearLottery lottery = eventService.getLotteryInfo(mobile);
		if (lottery == null) {
			return "{\"err\":\"对不起！此手机号无法参与抽奖\"}";
		}
		if (!CommonUtils.isEmptyString(lottery.getAddress())) {
			if (!CommonUtils.isEmptyString(lottery.getLottery())) {
				return "{\"err\":\"您已经领取过礼品了，礼品为" + lottery.getLottery() + "\"}";
			} else {
				// return "{\"err\":\"您已经抽过奖了，奖项为" + lottery.getLottery() + ",请完善信息\"}";
			}
		}
		return "{\"reword\":\"" + lottery.getLottery() + "\"}";
	}

	@ResponseBody
	@RequestMapping(value = "/newlotteryinfo", produces = "text/html; charset=UTF-8")
	public String newlotteryinfo(String mobile, String name, String address) {
		if (CommonUtils.isEmptyString(mobile)) {
			return "请输入手机号";
		}
		if (CommonUtils.isEmptyString(name)) {
			return "请输入姓名";
		}
		if (CommonUtils.isEmptyString(address)) {
			return "请输入地址";
		}
		NewYearLottery lottery = eventService.getLotteryInfo(mobile);
		if (lottery == null) {
			return "error";
		}
		lottery.setAddress(address);
		lottery.setName(name);
		lottery.setTime(new Date());
		// lottery.setLottery(lotteryStr);
		eventService.updateLotteryInfo(lottery);
		return "success";
	}
	
	/**
	 * 连续摇了几天
	 * @param request
	 * @param rep
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getDayTime", produces = "text/html; charset=UTF-8")
	public String getDayTime(HttpServletRequest request, HttpServletResponse rep) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			request.setCharacterEncoding("utf-8");
			String userid = request.getParameter("userid");
			//1上午2下午
			String type = request.getParameter("type");
			CommonUtils.validateEmpty(userid);
			CommonUtils.validateEmpty(type);
			ShankeInfo shanke = shankeService.getShankeInfoByUser(CommonUtils.parseLong(userid, 0));
			if(shanke == null){//第一次摇
				map.put("daytimes", 0);
				map.put("allpoint", 0);
				if(type.equals("1")){
					map.put("leftTimes", 6);
				}else{
					map.put("leftTimes", 3);
				}
				map.put("message", "操作成功");
				map.put("code", 1);
			}else{
				Date nowtime = new Date();
				String ds1 = CommonUtils.time2Str(nowtime, "yyyy-MM-dd");
				String ds2 = CommonUtils.time2Str(shanke.getUpdateTime(), "yyyy-MM-dd");
				if(shanke.getDayTimes() == null){
					map.put("daytimes", 0);
				}else{
					if(shanke.getDayTimes() == 7){
						if(!ds1.equals(ds2)){//不是同一天
							map.put("daytimes", 0);
						}else{
							map.put("daytimes", shanke.getDayTimes());
						}
					}else{
						if(ds1.equals(ds2)){//同一天
							map.put("daytimes", shanke.getDayTimes());
						}else{
							Calendar c = Calendar.getInstance();
							 c.add(Calendar.DATE,   -1);
							//前一天的日期
							Date qiangDate = c.getTime();
							String qiangStr = CommonUtils.getTimeFormat(qiangDate, "yyyy-MM-dd");
							String shankeupdateStr = CommonUtils.getTimeFormat(shanke.getUpdateTime(), "yyyy-MM-dd");
							if(qiangStr.equals(shankeupdateStr)){//昨天 连续摇积分
								map.put("daytimes", shanke.getDayTimes());
							}else{
								map.put("daytimes", 0);
							}
						}
					}
				}
				if(shanke.getPoint() == null){
					map.put("allpoint", 0);
				}else{
					map.put("allpoint", shanke.getPoint());
				}
				if(ds1.equals(ds2)){//同一天
					if(type.equals("1")){
						if(shanke.getAmCount() == null){
							map.put("leftTimes", 6);
						}else{
							map.put("leftTimes", 6-shanke.getAmCount());
						}
					}else{
						if(shanke.getPmCount() == null){
							map.put("leftTimes", 3);
						}else{
							map.put("leftTimes", 3-shanke.getPmCount());
						}
					}
				}else{
					shanke.setAmCount(0);
					shanke.setPmCount(0);
					shankeService.updateShankeInfo(shanke);
					if(type.equals("1")){
						map.put("leftTimes", 6);
					}else{
						map.put("leftTimes", 3);
					}
				}
				map.put("message", "操作成功");
				map.put("code", 1);
			}
		} catch (CustomException e) {
			return e.getMessage();
		} catch (Exception e) {
			return e.getMessage();
		}
		return gson.toJson(map);
	}
	
	
	/**
	 * 摇一摇取积分
	 * @param request
	 * @param rep
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/shakepoint", produces = "text/html; charset=UTF-8")
	public String shakepoint(HttpServletRequest request, HttpServletResponse rep) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			request.setCharacterEncoding("utf-8");
			String userid = request.getParameter("userid");
			//1上午2下午
			String type = request.getParameter("type");
			CommonUtils.validateEmpty(userid);
			CommonUtils.validateEmpty(type);
			Date nowtime = new Date();
			CommonUtils.isInDate(nowtime);
			Integer[] points = new Integer[]{10,30,80,200,500};
			// 0-60 谢谢参与 60% angel=0
			// 60-120 一等奖 0.5% angel=4
			// 120-180 二等奖 2% angel=3
			// 180-240 三等奖 4% angel=2
			// 240-300 安慰奖 37% angel=1
			// 300-360 特等奖 0.3% angel=0
			int rewordint = new Random().nextInt(1000) + 1;
			int angel = 0;
			if (rewordint <= 3) {
//				angel = new Random().nextInt(59) + 301;
				angel=0;
			} else if (rewordint <= 8) {
				angel=4;
			} else if (rewordint <= 28) {
				angel=3;
			} else if (rewordint <= 68) {
				angel=2;
			} else if (rewordint <= 438) {
				angel=1;
			} else {
				angel=0;
			}
			int reword = angel;
			ShankeInfo shanke = shankeService.getShankeInfoByUser(CommonUtils.parseLong(userid, 0));
			if(shanke == null){//第一次摇
				shanke = new ShankeInfo();
				if(type.equals("1")){
					shanke.setAmCount(1);
					map.put("leftTimes", 5);
				}else{
					shanke.setPmCount(1);
					map.put("leftTimes", 2);
				}
				shanke.setDayTimes(1);
				shanke.setPoint(points[reword]);
				shanke.setUserId(CommonUtils.parseLong(userid, 0));
				shanke.setUpdateTime(nowtime);
				shankeService.insertShankeInfo(shanke);
				//添加记录
				ShankeRecordInfo record = new ShankeRecordInfo();
				record.setAddTime(nowtime);
				record.setPoint(points[reword]);
				record.setUserId(CommonUtils.parseLong(userid, 0));
				shankeService.insertShankeRecordInfo(record);
				IncomeRecord incomeRecord = new IncomeRecord();
				incomeRecord.setIncome(points[reword].floatValue());
				incomeRecord.setFromUserId(0l);
				incomeRecord.setTime(new Date());
				incomeRecord.setType(15);
				incomeRecord.setUserId(CommonUtils.parseLong(userid, 0));
				earnService.insertIncomeRecord(incomeRecord);
				UserInfo userinfo = userService.getUserInfoById(CommonUtils.parseLong(userid, 0));
				userinfo.setBalance(userinfo.getBalance()+points[reword].floatValue());
				userService.updateUser(userinfo);
				map.put("daytimes", 1);
				map.put("point", points[reword]);
				map.put("allpoint", points[reword]);
				map.put("message", "操作成功");
				map.put("code", 1);
			}else{//不是第一次摇
				String ds1 = CommonUtils.time2Str(nowtime, "yyyy-MM-dd");
				String ds2 = CommonUtils.time2Str(shanke.getUpdateTime(), "yyyy-MM-dd");
				if(ds1.equals(ds2)){//同一天
					if(type.equals("1")){//上午
						if(shanke.getAmCount() != null && shanke.getAmCount() >= 3){
							map.put("message", "本时段摇奖次数已经用完");
							map.put("code", 201);
						}else{
							if(shanke.getAmCount() == null){
								shanke.setAmCount(1);
								map.put("leftTimes", 5);
							}else{
								shanke.setAmCount(shanke.getAmCount()+1);
								map.put("leftTimes", 6-shanke.getAmCount());
							}
							if(shanke.getPoint() == null){
								shanke.setPoint(points[reword]);
							}else{
								shanke.setPoint(shanke.getPoint()+points[reword]);
							}
							if(shanke.getDayTimes() == null){
								shanke.setDayTimes(1);
							}
							shanke.setUpdateTime(nowtime);
							shankeService.updateShankeInfo(shanke);
							//添加记录
							ShankeRecordInfo record = new ShankeRecordInfo();
							record.setAddTime(nowtime);
							record.setPoint(points[reword]);
							record.setUserId(CommonUtils.parseLong(userid, 0));
							shankeService.insertShankeRecordInfo(record);
							IncomeRecord incomeRecord = new IncomeRecord();
							incomeRecord.setIncome(points[reword].floatValue());
							incomeRecord.setFromUserId(0l);
							incomeRecord.setTime(new Date());
							incomeRecord.setType(15);
							incomeRecord.setUserId(CommonUtils.parseLong(userid, 0));
							earnService.insertIncomeRecord(incomeRecord);
							UserInfo userinfo = userService.getUserInfoById(CommonUtils.parseLong(userid, 0));
							userinfo.setBalance(userinfo.getBalance()+points[reword].floatValue());
							userService.updateUser(userinfo);
							map.put("daytimes", shanke.getDayTimes());
							map.put("point", points[reword]);
							map.put("allpoint", shanke.getPoint());
							map.put("message", "操作成功");
							map.put("code", 1);
						}
					}else{//下午
						if(shanke.getPmCount() != null && shanke.getPmCount() >= 3){
							map.put("message", "本时段摇奖次数已经用完");
							map.put("code", 202);
						}else{
							if(shanke.getPmCount() == null){
								shanke.setPmCount(1);
								map.put("leftTimes", 2);
							}else{
								shanke.setPmCount(shanke.getPmCount()+1);
								map.put("leftTimes", 3-shanke.getPmCount());
							}
							if(shanke.getPoint() == null){
								shanke.setPoint(points[reword]);
							}else{
								shanke.setPoint(shanke.getPoint()+points[reword]);
							}
							if(shanke.getDayTimes() == null){
								shanke.setDayTimes(1);
							}
							shanke.setUpdateTime(nowtime);
							shankeService.updateShankeInfo(shanke);
							//添加记录
							ShankeRecordInfo record = new ShankeRecordInfo();
							record.setAddTime(nowtime);
							record.setPoint(points[reword]);
							record.setUserId(CommonUtils.parseLong(userid, 0));
							shankeService.insertShankeRecordInfo(record);
							IncomeRecord incomeRecord = new IncomeRecord();
							incomeRecord.setIncome(points[reword].floatValue());
							incomeRecord.setFromUserId(0l);
							incomeRecord.setTime(new Date());
							incomeRecord.setType(15);
							incomeRecord.setUserId(CommonUtils.parseLong(userid, 0));
							earnService.insertIncomeRecord(incomeRecord);
							UserInfo userinfo = userService.getUserInfoById(CommonUtils.parseLong(userid, 0));
							userinfo.setBalance(userinfo.getBalance()+points[reword].floatValue());
							userService.updateUser(userinfo);
							map.put("daytimes", shanke.getDayTimes());
							map.put("point", points[reword]);
							map.put("allpoint", shanke.getPoint());
							map.put("message", "操作成功");
							map.put("code", 1);
						}
					}
				}else{//不同一天
					Calendar c = Calendar.getInstance();
					 c.add(Calendar.DATE,   -1);
					//前一天的日期
					Date qiangDate = c.getTime();
					String qiangStr = CommonUtils.getTimeFormat(qiangDate, "yyyy-MM-dd");
					String shankeupdateStr = CommonUtils.getTimeFormat(shanke.getUpdateTime(), "yyyy-MM-dd");
					if(qiangStr.equals(shankeupdateStr)){//昨天 连续摇积分
						if(type.equals("1")){//上午
							if(shanke.getAmCount() != null && shanke.getAmCount() >= 3){
								map.put("message", "本时段摇奖次数已经用完");
								map.put("code", 201);
							}else{
								if(shanke.getAmCount() == null){
									shanke.setAmCount(1);
									map.put("leftTimes", 5);
								}else{
									shanke.setAmCount(shanke.getAmCount()+1);
									map.put("leftTimes", 6-shanke.getAmCount());
								}
								if(shanke.getPoint() == null){
									shanke.setPoint(points[reword]);
								}else{
									shanke.setPoint(shanke.getPoint()+points[reword]);
								}
								if(shanke.getDayTimes() == null || shanke.getDayTimes() == 7){
									shanke.setDayTimes(1);
									map.put("daytimes", 1);
								}else{
									shanke.setDayTimes(shanke.getDayTimes()+1);
									map.put("daytimes", shanke.getDayTimes());
								}
								shanke.setUpdateTime(nowtime);
								shankeService.updateShankeInfo(shanke);
								//添加记录
								ShankeRecordInfo record = new ShankeRecordInfo();
								record.setAddTime(nowtime);
								record.setPoint(points[reword]);
								record.setUserId(CommonUtils.parseLong(userid, 0));
								shankeService.insertShankeRecordInfo(record);
								IncomeRecord incomeRecord = new IncomeRecord();
								incomeRecord.setIncome(points[reword].floatValue());
								incomeRecord.setFromUserId(0l);
								incomeRecord.setTime(new Date());
								incomeRecord.setType(15);
								incomeRecord.setUserId(CommonUtils.parseLong(userid, 0));
								earnService.insertIncomeRecord(incomeRecord);
								UserInfo userinfo = userService.getUserInfoById(CommonUtils.parseLong(userid, 0));
								userinfo.setBalance(userinfo.getBalance()+points[reword].floatValue());
								userService.updateUser(userinfo);
								map.put("point", points[reword]);
								map.put("allpoint", shanke.getPoint());
								map.put("message", "操作成功");
								map.put("code", 1);
							}
						}else{//下午
							if(shanke.getPmCount() != null && shanke.getPmCount() >= 3){
								map.put("message", "本时段摇奖次数已经用完");
								map.put("code", 202);
							}else{
								if(shanke.getPmCount() == null){
									shanke.setPmCount(1);
									map.put("leftTimes", 2);
								}else{
									shanke.setPmCount(shanke.getPmCount()+1);
									map.put("leftTimes", 3-shanke.getPmCount());
								}
								if(shanke.getPoint() == null){
									shanke.setPoint(points[reword]);
								}else{
									shanke.setPoint(shanke.getPoint()+points[reword]);
								}
								if(shanke.getDayTimes() == null || shanke.getDayTimes() == 7){
									shanke.setDayTimes(1);
									map.put("daytimes", 1);
								}else{
									if((shanke.getAmCount() == null && shanke.getPmCount() == null) || (shanke.getAmCount() == null && shanke.getPmCount() == 0 ) && (shanke.getAmCount() == 0 ||  shanke.getPmCount() == null) || (shanke.getAmCount() == 0 ||  shanke.getPmCount() == 0)){
										shanke.setDayTimes(shanke.getDayTimes()+1);
										map.put("daytimes", shanke.getDayTimes());
									}else{
										map.put("daytimes", shanke.getDayTimes());
									}
								}
								shanke.setUpdateTime(nowtime);
								shankeService.updateShankeInfo(shanke);
								//添加记录
								ShankeRecordInfo record = new ShankeRecordInfo();
								record.setAddTime(nowtime);
								record.setPoint(points[reword]);
								record.setUserId(CommonUtils.parseLong(userid, 0));
								shankeService.insertShankeRecordInfo(record);
								IncomeRecord incomeRecord = new IncomeRecord();
								incomeRecord.setIncome(points[reword].floatValue());
								incomeRecord.setFromUserId(0l);
								incomeRecord.setTime(new Date());
								incomeRecord.setType(15);
								incomeRecord.setUserId(CommonUtils.parseLong(userid, 0));
								earnService.insertIncomeRecord(incomeRecord);
								UserInfo userinfo = userService.getUserInfoById(CommonUtils.parseLong(userid, 0));
								userinfo.setBalance(userinfo.getBalance()+points[reword].floatValue());
								userService.updateUser(userinfo);
								map.put("point", points[reword]);
								map.put("allpoint", shanke.getPoint());
								map.put("message", "操作成功");
								map.put("code", 1);
							}
						}
					}else{//不连续摇积分
						if(type.equals("1")){//上午
							if(shanke.getAmCount() != null && shanke.getAmCount() >= 3){
								map.put("message", "本时段摇奖次数已经用完");
								map.put("code", 201);
							}else{
								if(shanke.getAmCount() == null){
									shanke.setAmCount(1);
									map.put("leftTimes", 5);
								}else{
									shanke.setAmCount(shanke.getAmCount()+1);
									map.put("leftTimes", 6-shanke.getAmCount());
								}
								if(shanke.getPoint() == null){
									shanke.setPoint(points[reword]);
								}else{
									shanke.setPoint(shanke.getPoint()+points[reword]);
								}
								shanke.setDayTimes(1);
								shanke.setUpdateTime(nowtime);
								shankeService.updateShankeInfo(shanke);
								//添加记录
								ShankeRecordInfo record = new ShankeRecordInfo();
								record.setAddTime(nowtime);
								record.setPoint(points[reword]);
								record.setUserId(CommonUtils.parseLong(userid, 0));
								shankeService.insertShankeRecordInfo(record);
								IncomeRecord incomeRecord = new IncomeRecord();
								incomeRecord.setIncome(points[reword].floatValue());
								incomeRecord.setFromUserId(0l);
								incomeRecord.setTime(new Date());
								incomeRecord.setType(15);
								incomeRecord.setUserId(CommonUtils.parseLong(userid, 0));
								earnService.insertIncomeRecord(incomeRecord);
								UserInfo userinfo = userService.getUserInfoById(CommonUtils.parseLong(userid, 0));
								userinfo.setBalance(userinfo.getBalance()+points[reword].floatValue());
								userService.updateUser(userinfo);
								map.put("daytimes", 1);
								map.put("point", points[reword]);
								map.put("allpoint", shanke.getPoint());
								map.put("message", "操作成功");
								map.put("code", 1);
							}
						}else{//下午
							if(shanke.getPmCount() != null && shanke.getPmCount() >= 3){
								map.put("message", "本时段摇奖次数已经用完");
								map.put("code", 202);
							}else{
								if(shanke.getPmCount() == null){
									shanke.setPmCount(1);
									map.put("leftTimes", 2);
								}else{
									shanke.setPmCount(shanke.getPmCount()+1);
									map.put("leftTimes", 3-shanke.getPmCount());
								}
								if(shanke.getPoint() == null){
									shanke.setPoint(points[reword]);
								}else{
									shanke.setPoint(shanke.getPoint()+points[reword]);
								}
								if((shanke.getAmCount() == null && shanke.getPmCount() == null) || (shanke.getAmCount() == null && shanke.getPmCount() == 0 ) && (shanke.getAmCount() == 0 ||  shanke.getPmCount() == null) || (shanke.getAmCount() == 0 ||  shanke.getPmCount() == 0)){
									shanke.setDayTimes(1);
								}
								shanke.setUpdateTime(nowtime);
								shankeService.updateShankeInfo(shanke);
								//添加记录
								ShankeRecordInfo record = new ShankeRecordInfo();
								record.setAddTime(nowtime);
								record.setPoint(points[reword]);
								record.setUserId(CommonUtils.parseLong(userid, 0));
								shankeService.insertShankeRecordInfo(record);
								IncomeRecord incomeRecord = new IncomeRecord();
								incomeRecord.setIncome(points[reword].floatValue());
								incomeRecord.setFromUserId(0l);
								incomeRecord.setTime(new Date());
								incomeRecord.setType(15);
								incomeRecord.setUserId(CommonUtils.parseLong(userid, 0));
								earnService.insertIncomeRecord(incomeRecord);
								UserInfo userinfo = userService.getUserInfoById(CommonUtils.parseLong(userid, 0));
								userinfo.setBalance(userinfo.getBalance()+points[reword].floatValue());
								userService.updateUser(userinfo);
								map.put("daytimes", 1);
								map.put("point", points[reword]);
								map.put("allpoint", shanke.getPoint());
								map.put("message", "操作成功");
								map.put("code", 1);
							}
						}
					}
					
				}
			}
			
			return gson.toJson(map);
		} catch (CustomException e) {
			return e.getMessage();
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/getHighPoint", produces = "text/html; charset=UTF-8")
	public String getHighPoint(HttpServletRequest request, HttpServletResponse rep) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			request.setCharacterEncoding("utf-8");
			String userid = request.getParameter("userid");
			CommonUtils.validateEmpty(userid);
			ShankeInfo shanke = shankeService.getShankeInfoByUser(CommonUtils.parseLong(userid, 0));
//			int count = 0;
//			if(shanke.getAmCount() == null){
//				count += 0;
//			}else{
//				count += shanke.getAmCount();
//			}
//			if(shanke.getPmCount() == null){
//				count += 0;
//			}else{
//				count += shanke.getPmCount();
//			}
//			if(count >= 1){
//				map.put("message", "已领取");
//				map.put("code", 203);
//			}else{
				shanke.setPoint(shanke.getPoint()+500);
				shankeService.updateShankeInfo(shanke);
				//添加记录
				ShankeRecordInfo record = new ShankeRecordInfo();
				record.setAddTime(new Date());
				record.setPoint(500);
				record.setUserId(CommonUtils.parseLong(userid, 0));
				shankeService.insertShankeRecordInfo(record);
				IncomeRecord incomeRecord = new IncomeRecord();
				incomeRecord.setIncome(500f);
				incomeRecord.setFromUserId(0l);
				incomeRecord.setTime(new Date());
				incomeRecord.setType(16);
				incomeRecord.setUserId(CommonUtils.parseLong(userid, 0));
				earnService.insertIncomeRecord(incomeRecord);
				UserInfo userinfo = userService.getUserInfoById(CommonUtils.parseLong(userid, 0));
				userinfo.setBalance(userinfo.getBalance()+500f);
				userService.updateUser(userinfo);
				map.put("message", "操作成功");
				map.put("code", 1);
//			}
		} catch (CustomException e) {
			return e.getMessage();
		} catch (Exception e) {
			return e.getMessage();
		}
		return gson.toJson(map);
	}
	
	@ResponseBody
	@RequestMapping(value = "/couponlottery", produces = "text/html; charset=UTF-8")
	public String couponlottery(HttpSession session, Long lotteryid) {
		long userid = CommonUtils.parseLong(String.valueOf(session.getAttribute("userid")), 0);
		// 0-60 谢谢参与 60%
		// 60-120 一等奖 0.5%
		// 120-180 二等奖 2%
		// 180-240 三等奖 4%
		// 240-300 安慰奖 37%
		// 300-360 特等奖 0.3%
		int reword = new Random().nextInt(1000) + 1;
		int angel = 30;
		String reword_num = "";
		if (reword <= 3) {
			angel = new Random().nextInt(59) + 301;
			reword_num = "500";
		} else if (reword <= 8) {
			angel = new Random().nextInt(59) + 61;
			reword_num = "200";
		} else if (reword <= 28) {
			angel = new Random().nextInt(59) + 121;
			reword_num = "80";
		} else if (reword <= 68) {
			angel = new Random().nextInt(59) + 181;
			reword_num = "30";
		} else if (reword <= 438) {
			angel = new Random().nextInt(59) + 241;
			reword_num = "10";
		} else {
			angel = new Random().nextInt(59) + 1;
		}
		String err = userService.couponLottery(reword_num, userid, angel, lotteryid);
		if (CommonUtils.isEmptyString(err)) {
			return String.valueOf(angel);
		} else {
			return err;
		}
	}
}
