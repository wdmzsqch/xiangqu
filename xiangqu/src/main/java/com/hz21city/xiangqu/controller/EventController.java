package com.hz21city.xiangqu.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.pojo.EventInfo;
import com.hz21city.xiangqu.pojo.EventSign;
import com.hz21city.xiangqu.pojo.UserInfo;
import com.hz21city.xiangqu.service.IEventService;
import com.hz21city.xiangqu.service.IUserService;

@Controller
@RequestMapping("/user")
public class EventController extends ApiBaseController {

	@Resource
	private IEventService eventService;
	@Resource
	private IUserService userService;

	@RequestMapping("/eventlist")
	public ModelAndView eventlist() {
		ModelAndView mov = new ModelAndView("/user/event");
		List<EventInfo> list = eventService.getAllEventList();
		mov.addObject("list", list);
		mov.addObject("fileurl", CommonUtils.getFileRootUrl());
		return mov;
	}

	@RequestMapping("/lookeventdetail")
	public ModelAndView lookeventdetail(Long id, Long shareuserid, HttpSession session) {
		ModelAndView mov = new ModelAndView();
		long userid = CommonUtils.parseLong(String.valueOf(session.getAttribute("userid")), 0);
		EventInfo event = eventService.selectByPrimaryKey(id);
		event.setDetail(event.getDetail().replaceAll("\r\n", "<br/>"));
		EventSign sign = eventService.getEventSignByEventUser(id, userid);
		mov.addObject("userid", userid);
		mov.addObject("event", event);
		mov.addObject("sign", sign);
		mov.addObject("fileUrl", CommonUtils.getFileRootUrl());
		mov.addObject("shareuserid", shareuserid);
		UserInfo userinfo = userService.getUserInfoById(userid);
		if (CommonUtils.isEmptyString(userinfo.getMoblie())) {
			mov.addObject("hasMoblie", 0);
		} else {
			mov.addObject("hasMoblie", 1);
		}
		mov.setViewName("/user/event_detail");
		return mov;
	}

	@RequestMapping("/addEventSign")
	public ModelAndView addEventSign(EventSign sign, Long shareuserid, Integer income, @RequestParam(value = "picfile", required = false) MultipartFile picfile, HttpSession session,
			HttpServletResponse response) {
		ModelAndView mov = new ModelAndView();
		long userid = CommonUtils.parseLong(String.valueOf(session.getAttribute("userid")), 0);
		EventSign eventsign = eventService.getEventSignByEventUser(sign.getEventId(), userid);
		Integer alsignCount = eventService.getEventSignCount(sign.getEventId());
		EventInfo eventinfo = eventService.selectByPrimaryKey(sign.getEventId());
		if (eventsign == null) {
			if (alsignCount < eventinfo.getSignLimit()) {
				String randNum = null;
				Integer signcount = 0;
				do {
					randNum = CommonUtils.genRandomNum(8);
					signcount = eventService.getEventSignCountByCode(randNum);
				} while (signcount > 0);
				sign.setCode(randNum);
				String codepic = CommonUtils.creatQrcode("com.hz21city.xiangqu_event_" + sign.getEventId() + "_" + sign.getCode());
				sign.setCodePic(codepic);
			} else {
				sign.setCode("");
				sign.setCodePic("");
			}
			sign.setUserId(userid);
			sign.setIsJoined(0);
			sign.setSignTime(new Date());
			if (picfile != null && !picfile.isEmpty()) {
				sign.setPic(getFilePathString(picfile));
			}
			if (sign.getId() == null) {
				eventService.addEventSign(sign);
			}
			// 添加分享收益
			if (alsignCount < eventinfo.getSignLimit()) {
				UserInfo userinfo = eventService.getUserInfoById(userid);
				if (userinfo != null) {
					// 活动报名模板（短信和微信通知）
					if (!CommonUtils.isEmptyString(userinfo.getMoblie())) {
						CommonUtils.YMsendSms(userinfo.getMoblie(), "您已成功报名了" + eventinfo.getName() + "您的验证码是" + sign.getCode());
					}
				}
				if (shareuserid != null && shareuserid > 0 && shareuserid != userid) {
					eventService.addSignRecord(shareuserid, userid, 6, sign.getEventId().intValue(), income);
				}
			}
		}
		mov.setViewName("redirect:/user/lookeventdetail?id=" + sign.getEventId());
		return mov;
	}

	@RequestMapping("/looksign")
	public ModelAndView looksign(Long event_id, HttpSession session) {
		ModelAndView mov = new ModelAndView("/user/sign_detail");
		long userid = CommonUtils.parseLong(String.valueOf(session.getAttribute("userid")), 0);
		EventInfo event = eventService.selectByPrimaryKey(event_id);
		event.setDetail(event.getDetail().replaceAll("\r\n", "<br/>"));
		EventSign sign = eventService.getEventSignByEventUser(event_id, userid);
		mov.addObject("userid", userid);
		mov.addObject("event", event);
		mov.addObject("sign", sign);
		mov.addObject("fileUrl", CommonUtils.getFileRootUrl());
		return mov;
	}
}
