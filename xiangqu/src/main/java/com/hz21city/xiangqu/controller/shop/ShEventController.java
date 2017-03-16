package com.hz21city.xiangqu.controller.shop;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.pojo.EventInfo;
import com.hz21city.xiangqu.service.shop.IShEventService;

@Controller
@RequestMapping("/shop")
public class ShEventController {

	@Resource
	private IShEventService shEventService;

	@RequestMapping("/event_list")
	public ModelAndView event_list(HttpSession session) {
		ModelAndView mov = new ModelAndView();
		long storeId = CommonUtils.parseLong(String.valueOf(session.getAttribute("storeid")), 0);
		List<EventInfo> eventlist = shEventService.getShopEventList(storeId);
		mov.addObject("eventlist", eventlist);
		mov.addObject("fileRootUrl", CommonUtils.getFileRootUrl());
		return mov;
	}
	
	@RequestMapping("/sheventdetail")
	public ModelAndView sheventdetail(HttpSession session,Long id) {
		ModelAndView mov = new ModelAndView();
		if(id!=null&&id>0){
			EventInfo eventinfo = shEventService.getEventInfoById(id);
			if(eventinfo!=null){
				mov.addObject("fileRootUrl", CommonUtils.getFileRootUrl());
				mov.addObject("eventinfo", eventinfo);
			}else{
				mov.setViewName("redirect:/shop/event_list");
			}
		}else{
			mov.setViewName("redirect:/shop/event_list");
		}
		return mov;
	}

	@RequestMapping("/event_check")
	public ModelAndView eventCheck(String code, Integer eventid, HttpSession session) {
		ModelAndView mov = new ModelAndView("/shop/eventcheckresult");
		long storeId = CommonUtils.parseLong(String.valueOf(session.getAttribute("storeid")), 0);
		// EventInfo eventInfo = shEventService.getEventByCode(code);
		mov.addObject("code", code);
		mov.addAllObjects(shEventService.getEventByCode(code, storeId));
		return mov;
	}
	
	

}
