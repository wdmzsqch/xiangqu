package com.hz21city.xiangqu.controller.manage;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.pojo.CheckInfo;
import com.hz21city.xiangqu.pojo.EventInfo;
import com.hz21city.xiangqu.pojo.ShopInfo;
import com.hz21city.xiangqu.service.manage.IMaEventService;
import com.hz21city.xiangqu.service.manage.IMaMissionService;
import com.hz21city.xiangqu.service.manage.IMaShopService;

@Controller
@RequestMapping("/manage")
public class MaEventController extends MaBaseController {

	@Resource
	private IMaEventService maEventService;
	@Resource
	private IMaShopService mashopService;
	@Resource
	private IMaMissionService maMissionService;

	@RequestMapping("/event_list")
	public ModelAndView event_list(HttpSession session, Integer pageIndex) {
		ModelAndView mov = new ModelAndView("/manage/event_list");
		int page = 1;
		if (pageIndex != null && pageIndex > 0) {
			page = pageIndex;
		}
		List<EventInfo> list = maEventService.getEventListByPage(page);
		Integer checktype = CommonUtils.parseInt(String.valueOf(session.getAttribute("adminPrivilege")), 0);
		if (list != null && list.size() > 0) {
			for (EventInfo eventInfo : list) {
				CheckInfo checkinfo = maMissionService.getCheckInfoByAllWays(3, eventInfo.getId(), checktype);
				if (checkinfo != null) {
					eventInfo.setIsCheck(1);
				} else {
					eventInfo.setIsCheck(0);
				}
				CheckInfo checkinfo1 = maMissionService.getCheckInfoByAllWays(3, eventInfo.getId(), 1);
				CheckInfo checkinfo2 = maMissionService.getCheckInfoByAllWays(3, eventInfo.getId(), 2);
				CheckInfo checkinfo3 = maMissionService.getCheckInfoByAllWays(3, eventInfo.getId(), 3);
				CheckInfo checkinfo4 = maMissionService.getCheckInfoByAllWays(3, eventInfo.getId(), 4);
				if (checkinfo1 != null) {
					eventInfo.setComment(checkinfo1.getComment());
				}
				if (checkinfo2 != null) {
					eventInfo.setComment1(checkinfo2.getComment());
				}
				if (checkinfo3 != null) {
					eventInfo.setComment2(checkinfo3.getComment());
				}
				if (checkinfo4 != null) {
					eventInfo.setComment3(checkinfo4.getComment());
				}
			}
		}
		mov.addObject("event_list", list);
		mov.addObject("pageCount", maEventService.getEventListSize());
		mov.addObject("fileUrl", CommonUtils.getFileRootUrl());
		mov.addObject("webrooturl", CommonUtils.getWebRootUrl());
		mov.addObject("pageIndex", page);
		mov.addObject("perCount", maEventService.getAllEventSize());
		mov.addObject("checktype", checktype);
		return mov;
	}

	@RequestMapping("/event_detail")
	public ModelAndView event_detail(Long id) {
		ModelAndView mov = new ModelAndView("/manage/event_insert");
		List<ShopInfo> shoplist = mashopService.getAllShopList();
		mov.addObject("shoplist", shoplist);
		if (id != null && id > 0) {
			mov.addObject("event", maEventService.selectByPrimaryKey(id));
			CheckInfo checkinfo1 = maMissionService.getCheckInfoByAllWays(3, id, 1);
			CheckInfo checkinfo2 = maMissionService.getCheckInfoByAllWays(3, id, 2);
			CheckInfo checkinfo3 = maMissionService.getCheckInfoByAllWays(3, id, 3);
			CheckInfo checkinfo4 = maMissionService.getCheckInfoByAllWays(3, id, 4);
			if (checkinfo1 != null && checkinfo2 != null && checkinfo3 != null && checkinfo4 != null) {
				mov.addObject("allcheck", 1);
			} else {
				mov.addObject("allcheck", 0);
			}
			mov.addObject("fileUrl", CommonUtils.getFileRootUrl());
		}
		mov.addObject("allcheck", 0);
		return mov;
	}

	@RequestMapping("/event_edit")
	public ModelAndView event_edit(EventInfo event, @RequestParam(value = "eventpic", required = false) MultipartFile eventpic, String startDateStr, String endTimeStr) {
		ModelAndView mov = new ModelAndView("redirect:/manage/event_list");
		if (eventpic != null && !eventpic.isEmpty()) {
			event.setPic(getFilePathString(eventpic));
		}
		if (!CommonUtils.isEmptyString(startDateStr)) {
			event.setStartTime(CommonUtils.str2Date(startDateStr, "yyyy-MM-dd"));
		}
		if (!CommonUtils.isEmptyString(endTimeStr)) {
			endTimeStr = endTimeStr + " 23:59:59";
			event.setEndTime(CommonUtils.str2Date(endTimeStr, "yyyy-MM-dd HH:mm:ss"));
		}
		event.setCreateTime(new Date());
		event.setDelFlg(0);
		String content = event.getDetail();
		Pattern p = Pattern.compile("<img\\s*([^>]*)>");
		Matcher m = p.matcher(content);
		while (m.find()) {
			String s0 = m.group();
			String string2 = s0.replace("/>", " style='width:98%;'/>");
			content = content.replace(m.group(), string2);
		}
		content = "<body style=''><br/>" + "<div style='width: 100%;word-wrap: break-word;word-break: break-all;'>" + content + "</div></body>";
		content = content.replaceAll("&lt;", "<");
		content = content.replaceAll("&gt;", ">");
		content = content.replaceAll("(<iframe[^>]*?)\\s+width\\s*=\\s*\\S+", "$1");
		Pattern piframe = Pattern.compile("<iframe\\s*([^>]*)>");
		Matcher miframe = piframe.matcher(content);
		while (miframe.find()) {
			String s0 = miframe.group();
			String string2 = s0.replace(">", " style='width:98%;'>");
			content = content.replace(miframe.group(), string2);
		}
		event.setDetail(content);
		if (event.getId() != null) {
			maEventService.updateEventInfo(event);
		} else {
			maEventService.addEventInfo(event);
		}
		return mov;
	}

	@RequestMapping("/del_event")
	public ModelAndView del_event(Long id) {
		ModelAndView mov = new ModelAndView("redirect:/manage/event_list");
		EventInfo eventInfo = maEventService.selectByPrimaryKey(id);
		if (eventInfo != null) {
			eventInfo.setDelFlg(1);
			maEventService.updateEventInfo(eventInfo);
		}
		return mov;
	}

	@RequestMapping("/sign_list")
	public ModelAndView sign_list(Long event_id, Integer pageIndex, String code, String phone, Integer searchType) {
		ModelAndView mov = new ModelAndView("/manage/sign_list");
		int page = 1;
		if (pageIndex != null && pageIndex > 0) {
			page = pageIndex;
		}
		mov.addObject("sign_list", maEventService.getSubjectListByPage(event_id, page, code, phone, searchType));
		mov.addObject("pageCount", maEventService.getSubjectListSize(event_id, code, phone));
		mov.addObject("fileUrl", CommonUtils.getFileRootUrl());
		mov.addObject("code", code);
		mov.addObject("phone", phone);
		mov.addObject("pageIndex", page);
		mov.addObject("event_id", event_id);
		mov.addObject("searchType", searchType);
		return mov;
	}
}
