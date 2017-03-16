package com.hz21city.xiangqu.controller;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.service.IDailyService;

@Controller
@RequestMapping("/daily")
public class DailyController {

	@Resource
	private IDailyService dailyService;

	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView mov = new ModelAndView();
		mov.addObject("fileRootUrl", CommonUtils.getFileRootUrl());
		return mov;
	}

	@RequestMapping("/detail")
	public ModelAndView detail(Long dailyid) {
		ModelAndView mov = new ModelAndView();
		mov.addObject("fileRootUrl", CommonUtils.getFileRootUrl());
		return mov;
	}

	@RequestMapping("/myreword")
	public ModelAndView myreword(Long dailyid) {
		ModelAndView mov = new ModelAndView();
		mov.addObject("fileRootUrl", CommonUtils.getFileRootUrl());
		return mov;
	}

	@ResponseBody
	@RequestMapping("/getreword")
	public ModelAndView getreword(Long dailyid) {
		ModelAndView mov = new ModelAndView();
		mov.addObject("fileRootUrl", CommonUtils.getFileRootUrl());
		return mov;
	}
}
