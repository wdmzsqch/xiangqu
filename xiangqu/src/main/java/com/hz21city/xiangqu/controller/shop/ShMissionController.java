package com.hz21city.xiangqu.controller.shop;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.pojo.MissionInfo;
import com.hz21city.xiangqu.pojo.ShopInfo;
import com.hz21city.xiangqu.pojo.TaskNeeds;
import com.hz21city.xiangqu.service.shop.IShMissionService;
import com.hz21city.xiangqu.service.shop.IShShopService;

@Controller
@RequestMapping("/shop")
public class ShMissionController {
	
	@Resource
	private IShMissionService shMissionService;

	@Resource
	private IShShopService shShopService;
	
	@RequestMapping("/mission")
	public ModelAndView mission(Integer type,HttpSession session) {
		ModelAndView mov = new ModelAndView();
		if(type==null)
			type = 1;
		
		long storeid = CommonUtils.parseLong(String.valueOf(session.getAttribute("storeid")), 0);
		List<MissionInfo> missionlist = shMissionService.getMissionInfoByType(storeid, type);
		mov.addObject("missionlist", missionlist);
		mov.addObject("type", type);
		mov.addObject("fileRootUrl", CommonUtils.getFileRootUrl());
		return mov;
	}
	
	@RequestMapping("/missiondetail")
	public ModelAndView missiondetail(long id){
		ModelAndView mov = new ModelAndView();
		MissionInfo mission = shMissionService.getMissionDetail(id);
		mov.addObject("fileRootUrl", CommonUtils.getFileRootUrl());
		mov.addObject("mission", mission);
		return mov;
	}
	
	@RequestMapping("/turnaddmission")
	public ModelAndView turnaddmission(){
		ModelAndView mov = new ModelAndView();
		mov.setViewName("/shop/addmission");
		return mov;
	}
	
	@RequestMapping("/addmission")
	public void addmission(String intro,HttpSession session, HttpServletResponse response ){
		long storeid = CommonUtils.parseLong(String.valueOf(session.getAttribute("storeid")), 0);
		ShopInfo shop = shShopService.getShopInfoById(storeid);
		TaskNeeds taskneed = new TaskNeeds();
		taskneed.setIntro(intro);
		taskneed.setPublishTime(new Date());
		taskneed.setShopId(storeid);
		taskneed.setShopName(shop.getCompanyName());
		taskneed.setShopPhone(shop.getPhone());
		taskneed.setStatus(0);
		shMissionService.addTaskNeeds(taskneed);
		CommonUtils.setResponseStr("success", response);
	}
}
	
