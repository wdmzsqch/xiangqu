package com.hz21city.xiangqu.controller.manage;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.pojo.ActivityInfo;
import com.hz21city.xiangqu.service.manage.IMaActivityService;

@Controller
@RequestMapping("/manage")
public class MaActivityController extends MaBaseController{

	@Resource
	private IMaActivityService maActivityService;
	
	/**
	 * 会员活动列表
	 * @param pageIndex
	 * @param keywords
	 * @return
	 */
	@RequestMapping("/activity_list")
	public ModelAndView activity_list(Integer pageIndex, String keywords){
		ModelAndView mov = new ModelAndView("/manage/activity_list");
		int page = 1;
		if (pageIndex != null && pageIndex > 0) {
			page = pageIndex;
		}
		mov.addObject("keywords", keywords);
		mov.addObject("pageIndex", page);
		mov.addObject("activity_list", maActivityService.getActivityListByPage(page, keywords));
		mov.addObject("pageCount", maActivityService.getActivityListSize(keywords));
		return mov;
	}
	
	/**
	 * 跳转到编辑活动页面
	 * @param id
	 * @return
	 */
	@RequestMapping("/activity_detail")
	public ModelAndView activity_detail(Long id){
		ModelAndView mov = new ModelAndView("/manage/activity_insert");
		if (id != null && id > 0) {
			mov.addObject("activity", maActivityService.selectByPrimaryKey(id));
		}
		return mov;
	}
	
	@RequestMapping("/activity_edit")
	public ModelAndView activity_edit(ActivityInfo activity, String activitytime){
		ModelAndView mov = new ModelAndView("redirect:/manage/activity_list");
		if (!CommonUtils.isEmptyString(activitytime)) {
			activity.setAcitvityTime(CommonUtils.str2Date(activitytime, "yyyy-MM-dd"));
		}
		activity.setAddTime(new Date());
		String content = activity.getContent();
		Pattern p = Pattern.compile("<img\\s*([^>]*)>");
		Matcher m = p.matcher(content);
		while (m.find()) {
			String s0 = m.group();
			if(!s0.contains("width:100%;")){
				String string2 = s0.replace("/>", " style='width:100%;'/>");
				content = content.replace(m.group(), string2);
			}
		}
		if(!content.contains("<body style=''>")){
			content = "<body style=''>" + "<div style='width: 100%;word-wrap: break-word;word-break: break-all;'>" + content + "</div></body>";
		}
		activity.setContent(content);
		if(activity.getId() == null){
			maActivityService.insertActivityInfo(activity);
		}else{
			maActivityService.updateActivityInfo(activity);
		}
		return mov;
	}
}
