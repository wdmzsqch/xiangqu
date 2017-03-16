package com.hz21city.xiangqu.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.pojo.NewYearDinner;
import com.hz21city.xiangqu.pojo.NewYearLottery;
import com.hz21city.xiangqu.pojo.UserInfo;
import com.hz21city.xiangqu.pojo.VerifyCode;
import com.hz21city.xiangqu.service.ITimeEventService;
import com.hz21city.xiangqu.service.IUserService;

@Controller
@RequestMapping("/event")
public class TimeEventController extends ApiBaseController {

	@Resource
	private ITimeEventService timeEventService;
	@Resource
	private IUserService userService;

	@RequestMapping("/nianhui")
	public ModelAndView nianhui() {
		ModelAndView mov = new ModelAndView("/timeevent/nianhui");
		mov.addObject("clist", timeEventService.getAll());
		mov.addObject("cname", timeEventService.getLatest().getCompanyName());
		return mov;
	}

	@ResponseBody
	@RequestMapping(value = "/nianhui_upd", produces = "application/json; charset=UTF-8")
	public String nianhuUpd(Long cid) {
		try {
			NewYearDinner dinner = timeEventService.getbyid(cid);
			if (dinner == null) {
				return "fail";
			}
			dinner.setArriveTime(new Date());
			dinner.setIsArrive(1);
			timeEventService.update(dinner);
		} catch (Exception e) {
			return "fail";
		}
		return "success";
	}

	@ResponseBody
	@RequestMapping(value = "/nianhui_all", produces = "application/json; charset=UTF-8")
	public String nianhuiAll() {
		try {
			for (long i = 1; i <= 188; i++) {
				NewYearDinner dinner = timeEventService.getbyid(i);
				if (dinner == null) {
					continue;
				}
				if (dinner.getIsArrive() == 1) {
					continue;
				}
				dinner.setArriveTime(new Date());
				dinner.setIsArrive(1);
				timeEventService.update(dinner);
			}
		} catch (Exception e) {
			return "fail";
		}
		return "success";
	}

	@ResponseBody
	@RequestMapping(value = "/execmdel", produces = "application/json; charset=UTF-8")
	public String execShell() {
		Process process;
		String cmd = "/etc/mdel.sh";// 这里必须要给文件赋权限 chmod u+x fileName;
		try {
			// 使用Runtime来执行command，生成Process对象
			Runtime runtime = Runtime.getRuntime();
			process = runtime.exec(cmd);
			// 取得命令结果的输出流
			InputStream is = process.getInputStream();
			// 用一个读输出流类去读
			InputStreamReader isr = new InputStreamReader(is);
			// 用缓冲器读行
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			String result = "";
			while ((line = br.readLine()) != null) {
				result = result + line;
			}
			// 执行关闭操作
			is.close();
			isr.close();
			br.close();
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			return "fail";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/new_lottery", produces = "text/html; charset=UTF-8")
	public String new_lottery(HttpSession session) {
		long userid = CommonUtils.parseLong(String.valueOf(session.getAttribute("userid")), 0);
		if (userid == 0) {
			return "用户信息有误";
		}else{
			UserInfo userinfo = userService.getUserInfoById(userid);
			if(CommonUtils.isEmptyString(userinfo.getMoblie())){
				return "用户手机号为空";
			}
		}
		if (timeEventService.getLotteryCount(userid) >= 3) {
			return "抽奖次数已满";
		}
		if (timeEventService.getUserLotteryCount(userid) > 0) {
			return "您已中奖";
		}
		// 一共四等奖，概率从config 获取
		Properties properties = new Properties();
		try {
			InputStream inStream = new FileInputStream(new File(CommonUtils.getLotteryProp()));
			properties.load(inStream);
			inStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int probability = Integer.parseInt(properties.getProperty("probability"));
		int reword = new Random().nextInt(1000) + 1;
		int angel = 30;
		String rewordStr = "";
		if (reword <= probability) {
			if (CommonUtils.parseInt(properties.getProperty("one"), 0) - timeEventService.getLotteryCountAlready("一等奖") > 0) {
				angel = new Random().nextInt(59) + 61;
				rewordStr = "一等奖";
			}
		} else if (reword <= probability * 2) {
			if (CommonUtils.parseInt(properties.getProperty("two"), 0) - timeEventService.getLotteryCountAlready("二等奖") > 0) {
				angel = new Random().nextInt(59) + 121;
				rewordStr = "二等奖";
			}
		} else if (reword <= probability * 3) {
			if (CommonUtils.parseInt(properties.getProperty("three"), 0) - timeEventService.getLotteryCountAlready("三等奖") > 0) {
				angel = new Random().nextInt(119) + 181;
				rewordStr = "三等奖";
			}
		} else {
			angel = new Random().nextInt(120) - 60;
			if (angel < 0) {
				angel = angel + 360;
			}
		}
		UserInfo userInfo = userService.getUserInfoById(userid);
		NewYearLottery newYearLottery = new NewYearLottery();
		newYearLottery.setPhone(userInfo.getMoblie());
		newYearLottery.setLottery(rewordStr);
		newYearLottery.setTime(new Date());
		newYearLottery.setName(String.valueOf(userid));
		newYearLottery.setAddress(userInfo.getNickName());
		timeEventService.addLottery(newYearLottery);
		return String.valueOf(angel);
	}
	
	@RequestMapping("/editorUserMoblie")
	public void addUserMoblie(String moblie, String vcode, HttpSession session, HttpServletResponse response) {
		long userid = CommonUtils.parseLong(String.valueOf(session.getAttribute("userid")), 0);
		// VerifyCode verifyCode = userService.getVerifyCode(moblie, vcode);
		VerifyCode code = userService.getVerifyCode(moblie);
		Date nowdate = new Date();
		if (code == null || !code.getCode().endsWith(vcode)) {
			CommonUtils.setResponseStr("error", response);
			return;
		} else if ((nowdate.getTime() - code.getCreateTime().getTime()) > 300000) {
			CommonUtils.setResponseStr("error1", response);
			return;
		} else {
			UserInfo userinfo = userService.getUserInfoById(userid);
			userinfo.setMoblie(moblie);
			userService.updateUser(userinfo);
			CommonUtils.setResponseStr("success", response);
		}
	}

	@RequestMapping("/check_new_lottery")
	public ModelAndView check_new_lottery(HttpSession session) {
		ModelAndView mov = new ModelAndView("/user/check_new_lottery");
		long userid = CommonUtils.parseLong(String.valueOf(session.getAttribute("userid")), 0);
		mov.addObject("lottery", timeEventService.getNewLotteryData(userid));
		mov.addObject("user", userService.getUserInfoById(userid));
		return mov;
	}

	@RequestMapping("/update_info")
	public ModelAndView update_info(HttpSession session, String name, String phone) {
		ModelAndView mov = new ModelAndView("redirect:/event/check_new_lottery");
		long userid = CommonUtils.parseLong(String.valueOf(session.getAttribute("userid")), 0);
		NewYearLottery lottery = timeEventService.getNewLotteryData(userid);
		lottery.setAddress(name);
		lottery.setPhone(phone);
		timeEventService.updateLottery(lottery);
		return mov;
	}

}
