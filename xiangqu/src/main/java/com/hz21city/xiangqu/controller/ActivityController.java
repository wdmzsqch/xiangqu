package com.hz21city.xiangqu.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hz21city.xiangqu.pojo.ActivityInfo;
import com.hz21city.xiangqu.service.IActivityService;

@Controller
@RequestMapping("/user")
public class ActivityController extends ApiBaseController{
	
	@Resource
	private IActivityService activityService;
	
	@RequestMapping("/activity_detail")
	public ModelAndView activity_detail(Long id){
		ModelAndView mov = new ModelAndView("user/activity_detail");
		ActivityInfo activity = activityService.selectByPrimaryKey(id);
		mov.addObject("activity", activity);
		return mov;
	}
	
}
