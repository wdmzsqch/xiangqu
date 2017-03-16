/**
 * 
 */
package com.hz21city.xiangqu.controller;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.pojo.UserInfo;
import com.hz21city.xiangqu.service.IEarnService;
import com.hz21city.xiangqu.service.IUserService;
import com.hz21city.xiangqu.service.manage.IMaSysService;

@Controller
@RequestMapping("/user")
public class EarnController {

	@Resource
	private IEarnService earnService;
	@Resource
	private IUserService userService;
	@Resource
	private IMaSysService maSysService;

	@RequestMapping("/today_earn")
	public ModelAndView todayEarn(HttpSession session, Integer isapp) {
		ModelAndView mv = new ModelAndView("/user/today_earn");
		long userid = CommonUtils.parseLong(String.valueOf(session.getAttribute("userid")), 0);
		UserInfo userinfo = userService.getUserInfoById(userid);
		String startTime = CommonUtils.getTimeFormat(userinfo.getUpdateTime(), "yyyy-MM-dd HH:mm:ss");
		int count = maSysService.getNewSysmessage(startTime);
		mv.addObject("count", count);
		mv.addObject("income", earnService.getDailyIncome(userid));
		// 总收益
		mv.addObject("allincome", earnService.getAllIncome(userid));
		//
		mv.addObject("allRecord", earnService.getAllRecordList());
		mv.addObject("balance", userinfo.getBalance());
		// mv.addObject("balance", earnService.getMyBalance(userid));
		// mv.addObject("missionCount", earnService.getDailyMissionCount(userid));
		// mv.addObject("missionPV", earnService.getDailyMissionPv(userid));
		// share表中查
		mv.addObject("goodsCount", earnService.getDailyGoodsCount(userid));
		// income表中查
		// mv.addObject("goodsPurchase", earnService.getDailyGoodsPurchase(userid));
		 mv.addObject("missionList", earnService.getDailyMission(userid));
		// mv.addObject("goodsList", earnService.getDailyGoods(userid));
		// mv.addObject("couponList", earnService.getDailyCoupons(userid));
		// mv.addObject("eventList", earnService.getDailyEvents(userid));
		mv.addObject("fileurl", CommonUtils.getFileRootUrl());
		mv.addObject("isapp", isapp);
		return mv;
	}

	@RequestMapping("/month_earn")
	public ModelAndView mohthEarn(HttpSession session, String month) {
		ModelAndView mv = new ModelAndView("/user/month_earn");
		long userid = CommonUtils.parseLong(String.valueOf(session.getAttribute("userid")), 0);
		Date date = new Date();
		if (!CommonUtils.isEmptyString(month)) {
			date = CommonUtils.str2Date(month, "yyyy-MM");
		} else {
			Calendar c = Calendar.getInstance();
			c.set(Calendar.DAY_OF_MONTH, 1);
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			date = c.getTime();
		}
		mv.addObject("income", earnService.getMonthIncome(date, userid));
		mv.addObject("balance", earnService.getMyBalance(userid));
		mv.addObject("missionCount", earnService.getMonthMissionCount(date, userid));
		mv.addObject("missionPV", earnService.getMonthMissionPv(date, userid));
		mv.addObject("goodsCount", earnService.getMonthGoodsCount(date, userid));
		mv.addObject("goodsPurchase", earnService.getMonthGoodsPurchase(date, userid));
		mv.addObject("missionList", earnService.getMonthMission(date, userid));
		mv.addObject("goodsList", earnService.getMonthGoods(date, userid));
		mv.addObject("couponList", earnService.getMonthCoupons(date, userid));
		mv.addObject("eventList", earnService.getMonthEvents(date, userid));
		mv.addObject("month", CommonUtils.time2Str(date, "yyyy-MM"));
		mv.addObject("fileurl", CommonUtils.getFileRootUrl());
		return mv;
	}

	@RequestMapping("/myincome")
	public ModelAndView myincom(HttpSession session, Integer isapp) {
		ModelAndView mv = new ModelAndView("/user/myincome");
		long userid = CommonUtils.parseLong(String.valueOf(session.getAttribute("userid")), 0);
		mv.addObject("myRecord", earnService.getMyRecordList(userid));
		mv.addObject("isapp", isapp);
		return mv;
	}
}
