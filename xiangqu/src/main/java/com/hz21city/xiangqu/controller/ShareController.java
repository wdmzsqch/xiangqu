package com.hz21city.xiangqu.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.pojo.AdInfo;
import com.hz21city.xiangqu.common.Constants;
import com.hz21city.xiangqu.common.HttpRequest;
import com.hz21city.xiangqu.netdata.UserOpenId;
import com.hz21city.xiangqu.netdata.WxUserInfo;
import com.hz21city.xiangqu.pojo.Area;
import com.hz21city.xiangqu.pojo.HomeActivity;
import com.hz21city.xiangqu.pojo.MissionInfo;
import com.hz21city.xiangqu.pojo.ModelInfo;
import com.hz21city.xiangqu.pojo.SubjectInfo;
import com.hz21city.xiangqu.pojo.UserInfo;
import com.hz21city.xiangqu.service.IAdService;
import com.hz21city.xiangqu.service.ICommentService;
import com.hz21city.xiangqu.service.IMissionService;
import com.hz21city.xiangqu.service.IOrderService;
import com.hz21city.xiangqu.service.ISubjectService;
import com.hz21city.xiangqu.service.IUserService;
import com.hz21city.xiangqu.service.manage.IMaAreaService;
import com.hz21city.xiangqu.service.manage.IMaModelService;
import com.hz21city.xiangqu.service.manage.IMaSysService;

@Controller
@RequestMapping("/user")
public class ShareController {

	@Resource
	private IMissionService missionService;
	@Resource
	private IOrderService orderSerivce;
	@Resource
	private IUserService userService;
	@Resource
	private IAdService adService;
	@Resource
	private ICommentService commentService;
	@Resource
	private ISubjectService subjectService;
	@Resource
	private IMaModelService maModelService;
	@Resource
	private IMaAreaService maAreaService;
	@Resource
	private IMaSysService maSysService;

	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mov = new ModelAndView();
		String code = request.getParameter("code");
		UserOpenId useropenid = null;
		WxUserInfo wxuserinfo = null;
		if (!CommonUtils.isEmptyString(code)) {
			String openidurl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + Constants.WX_APPID + "&secret=" + Constants.WX_APPSECRET + "&code=" + code
					+ "&grant_type=authorization_code";
			String openiddata = HttpRequest.sendGet(openidurl, null);
			if (!CommonUtils.isEmptyString(openiddata)) {
				Gson gson = new Gson();// new一个Gson对象
				useropenid = gson.fromJson(openiddata, UserOpenId.class);
				if (!CommonUtils.isEmptyString(useropenid.getAccess_token()) && !CommonUtils.isEmptyString(useropenid.getOpenid())) {
					String userinfourl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + useropenid.getAccess_token() + "&openid=" + useropenid.getOpenid() + "&lang=zh_CN";
					String userinfo = HttpRequest.sendGet(userinfourl, null);
					wxuserinfo = gson.fromJson(userinfo, WxUserInfo.class);
				}
			}
		}
		String openid = "";
		String unionid = "";
		if (useropenid != null) {
			openid = useropenid.getOpenid();
			unionid = useropenid.getUnionid();
		}
		if (!CommonUtils.isEmptyString(openid) && !CommonUtils.isEmptyString(unionid)) {
			request.getSession().setAttribute("openid", openid);
			UserInfo userInfo = (UserInfo) userService.thirdPartLogin(2, unionid).get("user");
			if (userInfo != null) {
				if (userInfo.getNickName().contains("微信")) {
					if (wxuserinfo != null) {
						userInfo.setNickName(CommonUtils.filterEmoji(wxuserinfo.getNickname()));
						userInfo.setPic(wxuserinfo.getHeadimgurl());
						userService.updateUser(userInfo);
					}
				}
				request.getSession().setAttribute("userid", userInfo.getId());
				if (CommonUtils.isEmptyString(userInfo.getMoblie())) {
					mov.setViewName("redirect:/user/toregister?fromweixin=1");
				} else {
					mov.setViewName("redirect:/user/missiongeo");
				}
			} else {
				mov.setViewName("redirect:/user/missiongeo");
			}
		} else {
			mov.setViewName("/user/login");
		}
		return mov;
	}

	@RequestMapping("/sessionsync")
	public ModelAndView savesession(String userid, HttpSession session) {
		ModelAndView mov = new ModelAndView();
		session.setAttribute("userid", userid);
		mov.setViewName("/user/sessionsync");
		return mov;
	}

	@RequestMapping("/mission")
	public ModelAndView mission(HttpSession session, Long province, Long city, Long area, Integer isapp) {
		ModelAndView mov = new ModelAndView();
		List<AdInfo> adlist = adService.getAdListByType(1);
		mov.addObject("adlist", adlist);
		long userid = CommonUtils.parseLong(String.valueOf(session.getAttribute("userid")), 0);
		UserInfo user = userService.getUserInfoById(userid);
		String startTime = CommonUtils.getTimeFormat(user.getUpdateTime(), "yyyy-MM-dd HH:mm:ss");
		int count = maSysService.getNewSysmessage(startTime);
		if (province != null && province != 0) {
			mov.addObject("proviceName", userService.getAreaById(province).getName());
			session.setAttribute("province", province);
		}
		if (city != null && city != 0) {
			mov.addObject("cityName", userService.getAreaById(city).getName());
			session.setAttribute("city", city);
		}
		if (area != null && area != 0) {
			mov.addObject("areaName", userService.getAreaById(area).getName());
			session.setAttribute("city", city);
		}
		// List<MissionInfo> missionlist = missionService.getMissionList(userid, province, city);
		List<MissionInfo> missionlist = missionService.getMissionList(userid, province, city, area);
		List<AdInfo> dylist = adService.getAdListByType(3);
		if (dylist != null && dylist.size() > 0) {
			mov.addObject("dynamic", dylist.get(0));
		}
		List<ModelInfo> modelList = maModelService.getAllModelList();
		mov.addObject("modelList", modelList);
		HomeActivity homeActivityOne = missionService.getHomeActivityByKey("homeActivityOne");
		HomeActivity homeActivityTwo = missionService.getHomeActivityByKey("homeActivityTwo");
		HomeActivity homeActivityThree = missionService.getHomeActivityByKey("homeActivityThree");
		HomeActivity homeActivityFour = missionService.getHomeActivityByKey("homeActivityFour");
		HomeActivity homeActivityFive = missionService.getHomeActivityByKey("homeActivityFive");
		// Date date = new Date();
		// mov.addObject("date", date);
		// mov.addObject("weekOfDate", CommonUtils.getWeekOfDate(date));
		mov.addObject("homeActivityOne", homeActivityOne);
		mov.addObject("homeActivityTwo", homeActivityTwo);
		mov.addObject("homeActivityThree", homeActivityThree);
		mov.addObject("homeActivityFour", homeActivityFour);
		mov.addObject("homeActivityFive", homeActivityFive);
		mov.addObject("missionlist", missionlist);
		mov.addObject("fileRootUrl", CommonUtils.getFileRootUrl());
		mov.addObject("userid", userid);
		mov.addObject("isapp", isapp);
		mov.addObject("count", count);
		return mov;
	}

	@RequestMapping("/missiongeo")
	public ModelAndView missiongeo(HttpSession session, Integer type) {
		ModelAndView mov = new ModelAndView();
		if (type == null) {
			type = 0;
		}
		if (type == 1) {
			List<AdInfo> adlist = adService.getAdListByType(1);
			mov.addObject("adlist", adlist);
			List<AdInfo> dylist = adService.getAdListByType(3);
			if (dylist != null && dylist.size() > 0) {
				mov.addObject("dynamic", dylist.get(0));
			}
			long userid = CommonUtils.parseLong(String.valueOf(session.getAttribute("userid")), 0);
			UserInfo user = userService.getUserInfoById(userid);
			String startTime = CommonUtils.getTimeFormat(user.getUpdateTime(), "yyyy-MM-dd HH:mm:ss");
			int count = maSysService.getNewSysmessage(startTime);
			mov.addObject("count", count);
			mov.addObject("userid", userid);
			List<ModelInfo> modelList = maModelService.getAllModelList();
			mov.addObject("modelList", modelList);
			mov.addObject("fileRootUrl", CommonUtils.getFileRootUrl());
		} else {
			long province = CommonUtils.parseLong(String.valueOf(session.getAttribute("province")), 0);
			long city = CommonUtils.parseLong(String.valueOf(session.getAttribute("city")), 0);
			long area = CommonUtils.parseLong(String.valueOf(session.getAttribute("area")), 0);
			if (province != 0 || city != 0 || area != 0) {
				mov.setViewName("redirect:/user/mission?province=" + province + "&city=" + city + "&area=" + area);
			} else {
				List<AdInfo> adlist = adService.getAdListByType(1);
				mov.addObject("adlist", adlist);
				List<AdInfo> dylist = adService.getAdListByType(3);
				if (dylist != null && dylist.size() > 0) {
					mov.addObject("dynamic", dylist.get(0));
				}
				long userid = CommonUtils.parseLong(String.valueOf(session.getAttribute("userid")), 0);
				UserInfo user = userService.getUserInfoById(userid);
				String startTime = CommonUtils.getTimeFormat(user.getUpdateTime(), "yyyy-MM-dd HH:mm:ss");
				int count = maSysService.getNewSysmessage(startTime);
				mov.addObject("count", count);
				mov.addObject("userid", userid);
				List<ModelInfo> modelList = maModelService.getAllModelList();
				mov.addObject("modelList", modelList);
				mov.addObject("fileRootUrl", CommonUtils.getFileRootUrl());
			}
		}
		return mov;
	}

	@RequestMapping("/missiondetail")
	public ModelAndView missiondetail(long id, Long shareuserid, HttpSession session, HttpServletResponse response) {
		ModelAndView mov = new ModelAndView();
		long userid = CommonUtils.parseLong(String.valueOf(session.getAttribute("userid")), 0);
		MissionInfo missioninfo = missionService.getMissionById(id);
		if (shareuserid != null) {
			if (userid != shareuserid) {
				if (missioninfo.getRamianTimes() > 0) {
					missioninfo = missionService.addIncomeRecord(shareuserid, userid, new Long(id).intValue(), missioninfo.getId());
				} else {
					Integer state = missioninfo.getState();
					if (state == null || state < 3) {
						missioninfo.setState(3);
						missionService.updateMissionInfo(missioninfo);
					}
				}
			}
		}
		if (missioninfo.getDetailUrl().contains("xiangqu/user/subjectcode")) {
			mov.setViewName("");
			String subject_id = missioninfo.getDetailUrl().substring(missioninfo.getDetailUrl().lastIndexOf("subject_id=") + 11);
			SubjectInfo subjectInfo = subjectService.getSubjectById(CommonUtils.parseLong(subject_id, 0));
			if (subjectInfo != null) {
				String url = subjectInfo.getDetailUrl();
				try {
					response.sendRedirect(url);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}
		}
		mov.addObject("mission", missioninfo);
		mov.addObject("type", 1);
		long connent_id = missioninfo.getId();
		mov.addObject("connent_id", connent_id);
		mov.addObject("commentcount", commentService.getCommentListCount(connent_id, 2));
		mov.addObject("shareuserid", shareuserid);
		mov.addObject("fileRootUrl", CommonUtils.getFileRootUrl());
		return mov;
	}

	@RequestMapping("/scancode")
	public ModelAndView scancode(Long mission_id, HttpSession session) {
		ModelAndView mov = new ModelAndView();
		long userid = CommonUtils.parseLong(String.valueOf(session.getAttribute("userid")), 0);
		mov.setViewName("redirect:/user/missiondetail?id=" + mission_id + "&shareuserid=" + userid);
		return mov;
	}

	@RequestMapping("/chooseaddress")
	public ModelAndView editoraddress(Long userid, Integer type) {
		// ModelAndView mv = new ModelAndView("/user/choose_address");
		ModelAndView mv = new ModelAndView("/user/new_choose_address");
		List<Area> list = maAreaService.getAllCityList(0l);
		// List<Area> prolist = userService.getAreaByParentId(Long.valueOf(0));
		// if (prolist != null && prolist.size() > 0) {
		// List<Area> citylist = userService.getAreaByParentId(prolist.get(0).getId());
		// mv.addObject("citylist", citylist);
		// if(citylist != null && citylist.size() > 0){
		// List<Area> arealist = userService.getAreaByParentId(citylist.get(0).getId());
		// mv.addObject("arealist", arealist);
		// }
		// }
		// type 0 设置传入 1网页传入
		mv.addObject("list", list);
		mv.addObject("prolist", "");
		mv.addObject("userid", userid);
		mv.addObject("type", type);
		return mv;
	}
}
