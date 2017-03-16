package com.hz21city.xiangqu.controller.manage;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.pojo.DailyReword;
import com.hz21city.xiangqu.service.manage.IMaDailyRewordService;

@Controller
@RequestMapping("/manage")
public class MaDailyRewordController extends MaBaseController{
	
	@Resource
	private IMaDailyRewordService maDailyRewordService;

	/**
	 * 每日分享列表
	 * @param pageIndex
	 * @return
	 */
	@RequestMapping("/dailyreword_list")
	public ModelAndView dailyreword_list(Integer pageIndex){
		ModelAndView mov = new ModelAndView();
		int page = 1;
		if (pageIndex != null && pageIndex > 0) {
			page = pageIndex;
		}
		mov.addObject("pageIndex", page);
		mov.addObject("dailyreword_list", maDailyRewordService.getDailyRewordListByPage(page));
		mov.addObject("pageCount", maDailyRewordService.getDailyRewordListSize());
		return mov;
	}
	
	/**
	 * 页面详细
	 * @param id
	 * @return
	 */
	@RequestMapping("/dailyreword_detail")
	public ModelAndView dailyreword_detail(Long id){
		ModelAndView mov = new ModelAndView("manage/dailyreword_insert");
		if(id != null){
			DailyReword dailyreword = maDailyRewordService.getDailyRewordById(id);
			mov.addObject("dailyreword", dailyreword);
		}
		return mov;
	}
	
	/**
	 * 编辑每日详细
	 * @return
	 */
	@RequestMapping("/dailyreword_edit")
	public ModelAndView dailyreword_edit(DailyReword dailyreword, String start){
		ModelAndView mov = new ModelAndView("redirect:/manage/dailyreword_list");
		Date startTime = null;
		Date endTime = null;
		String end = "";
		String time = "";
		if (!CommonUtils.isEmptyString(start)) {
			time = start + " 00:00:00";
			startTime = CommonUtils.str2Date(time, "yyyy-MM-dd HH:mm:ss");
			end = start + " 23:59:59";
			endTime = CommonUtils.str2Date(end, "yyyy-MM-dd HH:mm:ss");
		}
		if(dailyreword.getId() == null){
			dailyreword.setCreateTime(new Date());
			Calendar endtime = Calendar.getInstance();  
			endtime.setTime(endTime);  
			endtime.set(Calendar.DATE, endtime.get(Calendar.DATE) + (dailyreword.getLasting()-1));
			dailyreword.setEndTime(endtime.getTime());
			dailyreword.setStartTime(startTime);
			maDailyRewordService.addDailyReword(dailyreword);
		}else{
			DailyReword reword = maDailyRewordService.getDailyRewordById(dailyreword.getId());
			reword.setContentUrl(dailyreword.getContentUrl());
			reword.setIncome(dailyreword.getIncome());
			reword.setIntro(dailyreword.getIntro());
			reword.setLasting(dailyreword.getLasting());
			reword.setName(dailyreword.getName());
			reword.setStartTime(dailyreword.getStartTime());
			Calendar endtime = Calendar.getInstance();  
			endtime.setTime(endTime);  
			endtime.set(Calendar.DATE, endtime.get(Calendar.DATE) + (dailyreword.getLasting()-1));
			reword.setEndTime(endtime.getTime());
			maDailyRewordService.updateDailyReword(reword);
		}
		return mov;
	}
}
