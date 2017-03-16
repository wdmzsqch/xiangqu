package com.hz21city.xiangqu.controller.map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.common.Constants;
import com.hz21city.xiangqu.pojo.AdPoint;
import com.hz21city.xiangqu.pojo.AdminInfo;
import com.hz21city.xiangqu.pojo.MarkAdPoint;
import com.hz21city.xiangqu.service.manage.IMaSysService;
import com.hz21city.xiangqu.service.manage.IMaUserService;

@Controller
@RequestMapping("/map")
public class MapController {
	
	@Resource
	private IMaUserService maUserService;
	
	@Resource
	private IMaSysService maSysService;

	/**
	 * 管理员登录
	 * @param session
	 * @param username
	 * @param pwd
	 * @return
	 */
	@RequestMapping("/mlogin")
	public ModelAndView wlogin(HttpSession session, String username, String pwd){
		ModelAndView mv = new ModelAndView();
		if (CommonUtils.isEmptyString(username) || CommonUtils.isEmptyString(pwd)) {
			mv.setViewName("/map/login");
			mv.addObject(Constants.PAGE_ERR_KEY, "请输入用户名、密码和验证码");
			return mv;
		}
		AdminInfo adminInfo = maUserService.getAdminInfo(username, CommonUtils.MD5(pwd));
		if (adminInfo == null) {
			mv.setViewName("/map/login");
			mv.addObject(Constants.PAGE_ERR_KEY, "用户名或者密码错误");
		}else if(adminInfo.getType() != -2){
			mv.setViewName("/map/login");
			mv.addObject(Constants.PAGE_ERR_KEY, "管理员没有此权限");
		} else {
			mv.setViewName("redirect:/map/ad_point_list");
			session.setAttribute("adminMapUserId", adminInfo.getId());
		}
		return mv;
	}
	
	/**
	 * 点位列表
	 * @param pageIndex
	 * @param keywords
	 * @param SourceClass
	 * @param areaSearch
	 * @return
	 */
	@RequestMapping("/ad_point_list")
	public ModelAndView ad_point_list(Integer pageIndex, String keywords, String SourceClass, String areaSearch){
		ModelAndView mv = new ModelAndView("/map/ad_point_list");
		int page = 1;
		if (pageIndex != null && pageIndex > 0) {
			page = pageIndex;
		}
		mv.addObject("list", maSysService.getAdPointList(page, keywords, SourceClass, areaSearch, 0));
		mv.addObject("pageCount", maSysService.getAdPointListSize(keywords, SourceClass, areaSearch, 0));
		mv.addObject("pageIndex", page);
		mv.addObject("keywords", keywords);
		mv.addObject("SourceClass", SourceClass);
		mv.addObject("areaSearch", areaSearch);
		return mv;
	}
	
	@RequestMapping("/ad_point_list_taiyuan")
	public ModelAndView ad_point_list_taiyuan(Integer pageIndex, String keywords, String SourceClass, String areaSearch){
		ModelAndView mv = new ModelAndView("/map/ad_point_list_taiyuan");
		int page = 1;
		if (pageIndex != null && pageIndex > 0) {
			page = pageIndex;
		}
		mv.addObject("list", maSysService.getAdPointList(page, keywords, SourceClass, areaSearch, 1));
		mv.addObject("pageCount", maSysService.getAdPointListSize(keywords, SourceClass, areaSearch, 1));
		mv.addObject("pageIndex", page);
		mv.addObject("keywords", keywords);
		mv.addObject("SourceClass", SourceClass);
		mv.addObject("areaSearch", areaSearch);
		return mv;
	}
	
	@RequestMapping("/ad_point_detail")
	public ModelAndView ad_point_detail(Long id){
		ModelAndView mv = new ModelAndView("/map/ad_point_insert");
		if (id != null) {
			mv.addObject("adpoint", maSysService.selectAdPointByPrimaryKey(id));
		}
		return mv;
	}
	
	@RequestMapping("/ad_point_detail_taiyuan")
	public ModelAndView ad_point_detail_taiyuan(Long id){
		ModelAndView mv = new ModelAndView("/map/ad_point_insert_taiyuan");
		if (id != null) {
			mv.addObject("adpoint", maSysService.selectAdPointByPrimaryKey(id));
		}
		return mv;
	}
	
	@RequestMapping("/ad_point_edit")
	public ModelAndView ad_point_edit(AdPoint adpoint){
		ModelAndView mv = new ModelAndView();
		if(adpoint.getId() == null){
			adpoint.setAddTime(new Date());
			if(CommonUtils.isEmptyString(adpoint.getBackground())){
				adpoint.setBackground("0x008000");
			}
			maSysService.insertAdPoint(adpoint);
		}else{
			if(CommonUtils.isEmptyString(adpoint.getBackground())){
				adpoint.setBackground("0x008000");
			}
			maSysService.updateAdPointByPrimaryKeySelective(adpoint);
		}
		if(adpoint.getAreatype() == 0){
			mv.setViewName("redirect:/map/ad_point_list");
		}else{
			mv.setViewName("redirect:/map/ad_point_list_taiyuan");
		}
		return mv;
	}
	
	@RequestMapping("/makepointmap2")
	public void makepointmap2(HttpServletResponse response, String keywords, String SourceClass, String areaSearch, int areatype, double longitude, double latitude){
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<AdPoint> list = maSysService.getAdPointListByLoLa2(longitude,latitude, keywords, SourceClass, areaSearch, areatype);
		result.put("list", list);
		result.put("returnlongitude", longitude);
		result.put("returnlatitude", latitude);
		CommonUtils.setMaptoJson(response, result);
	}
	
//	@RequestMapping("/makepointmap")
//	public void makepointmap(HttpServletResponse response, String keywords, String SourceClass, String areaSearch){
//		HashMap<String, Object> result = new HashMap<String, Object>();
//		//第1个点中心点经纬度
//		double longitude1 = 119.824582; 
//		double latitude1 = 31.126021;
//		//第2个点中心点经纬度
//		double longitude2 = 119.996582; 
//		double latitude2 = 31.126021;
//		//第3个点中心点经纬度
//		double longitude3 = 120.168582; 
//		double latitude3 = 31.126021;
//		//第4个点中心点经纬度
//		double longitude4 = 120.340582; 
//		double latitude4 = 31.126021;
//		//第5个点中心点经纬度
//		double longitude5 = 120.5212582; 
//		double latitude5 = 31.126021;
//		//第6个点中心点经纬度
//		double longitude6 = 119.824582; 
//		double latitude6 = 31.018021;
//		//第7个点中心点经纬度
//		double longitude7 = 119.996582; 
//		double latitude7 = 31.018021;
//		//第7个点中心点经纬度
//		double longitude8 = 120.168582; 
//		double latitude8 = 31.018021;
//		//第9个点中心点经纬度
//		double longitude9 = 120.340582; 
//		double latitude9 = 31.018021;
//		//第10个点中心点经纬度
//		double longitude10 = 120.5212582; 
//		double latitude10 = 31.018021;
//		//第11个点中心点经纬度
//		double longitude11 = 119.824582; 
//		double latitude11 = 30.910021;
//		//第12个点中心点经纬度
//		double longitude12 = 119.996582; 
//		double latitude12 = 30.910021;
//		//第13个点中心点经纬度
//		double longitude13 = 120.168582; 
//		double latitude13 = 30.910021;
//		//第14个点中心点经纬度
//		double longitude14 = 120.340582; 
//		double latitude14 = 30.910021;
//		//第15个点中心点经纬度
//		double longitude15 = 120.5212582; 
//		double latitude15 = 30.910021;
//		//第16个点中心点经纬度
//		double longitude16 = 119.824582; 
//		double latitude16 = 30.802021;
//		//第17个点中心点经纬度
//		double longitude17 = 119.996582; 
//		double latitude17 = 30.802021;
//		//第18个点中心点经纬度
//		double longitude18 = 120.168582; 
//		double latitude18 = 30.802021;
//		//第19个点中心点经纬度
//		double longitude19 = 120.340582; 
//		double latitude19 = 30.802021;
//		//第20个点中心点经纬度
//		double longitude20 = 120.521258; 
//		double latitude20 = 30.802021;
//		//第21个点中心点经纬度
//		double longitude21 = 120.13446808; 
//		double latitude21 = 30.85250035;
//		List<AdPoint> list1 = maSysService.getAdPointListByLoLa(longitude1,latitude1, keywords, SourceClass, areaSearch);
//		result.put("list1", list1);
//		List<AdPoint> list2 = maSysService.getAdPointListByLoLa(longitude2,latitude2, keywords, SourceClass, areaSearch);
//		result.put("list2", list2);
//		List<AdPoint> list3 = maSysService.getAdPointListByLoLa(longitude3,latitude3, keywords, SourceClass, areaSearch);
//		result.put("list3", list3);
//		List<AdPoint> list4 = maSysService.getAdPointListByLoLa(longitude4,latitude4, keywords, SourceClass, areaSearch);
//		result.put("list4", list4);
//		List<AdPoint> list5 = maSysService.getAdPointListByLoLa(longitude5,latitude5, keywords, SourceClass, areaSearch);
//		result.put("list5", list5);
//		List<AdPoint> list6 = maSysService.getAdPointListByLoLa(longitude6,latitude6, keywords, SourceClass, areaSearch);
//		result.put("list6", list6);
//		List<AdPoint> list7 = maSysService.getAdPointListByLoLa(longitude7,latitude7, keywords, SourceClass, areaSearch);
//		result.put("list7", list7);
//		List<AdPoint> list8 = maSysService.getAdPointListByLoLa(longitude8,latitude8, keywords, SourceClass, areaSearch);
//		result.put("list8", list8);
//		List<AdPoint> list9 = maSysService.getAdPointListByLoLa(longitude9,latitude9, keywords, SourceClass, areaSearch);
//		result.put("list9", list9);
//		List<AdPoint> list10 = maSysService.getAdPointListByLoLa(longitude10,latitude10, keywords, SourceClass, areaSearch);
//		result.put("list10", list10);
//		List<AdPoint> list11 = maSysService.getAdPointListByLoLa(longitude11,latitude11, keywords, SourceClass, areaSearch);
//		result.put("list11", list11);
//		List<AdPoint> list12 = maSysService.getAdPointListByLoLa(longitude12,latitude12, keywords, SourceClass, areaSearch);
//		result.put("list12", list12);
//		List<AdPoint> list13 = maSysService.getAdPointListByLoLa(longitude13,latitude13, keywords, SourceClass, areaSearch);
//		result.put("list13", list13);
//		List<AdPoint> list14 = maSysService.getAdPointListByLoLa(longitude14,latitude14, keywords, SourceClass, areaSearch);
//		result.put("list14", list14);
//		List<AdPoint> list15 = maSysService.getAdPointListByLoLa(longitude15,latitude15, keywords, SourceClass, areaSearch);
//		result.put("list15", list15);
//		List<AdPoint> list16 = maSysService.getAdPointListByLoLa(longitude16,latitude16, keywords, SourceClass, areaSearch);
//		result.put("list16", list16);
//		List<AdPoint> list17 = maSysService.getAdPointListByLoLa(longitude17,latitude17, keywords, SourceClass, areaSearch);
//		result.put("list17", list17);
//		List<AdPoint> list18 = maSysService.getAdPointListByLoLa(longitude18,latitude18, keywords, SourceClass, areaSearch);
//		result.put("list18", list18);
//		List<AdPoint> list19 = maSysService.getAdPointListByLoLa(longitude19,latitude19, keywords, SourceClass, areaSearch);
//		result.put("list19", list19);
//		List<AdPoint> list20 = maSysService.getAdPointListByLoLa(longitude20,latitude20, keywords, SourceClass, areaSearch);
//		result.put("list20", list20);
//		List<AdPoint> list21 = maSysService.getAdPointListByLoLa(longitude21,latitude21, keywords, SourceClass, areaSearch);
//		result.put("list21", list21);
//		CommonUtils.setMaptoJson(response, result);
//	}
	
	@RequestMapping("/del_ad_point")
	public void del_ad_point(HttpServletResponse response, Long id){
		AdPoint point = maSysService.selectAdPointByPrimaryKey(id);
		if(point != null){
			maSysService.deleteAdPointByKey(id);
			CommonUtils.setResponseStr("success", response);
			return;
		}else{
			CommonUtils.setResponseStr("error", response);
			return;
		}
	}
	
	@RequestMapping("/add_mark_ad_point")
	public void add_mark_ad_point(String ids, int areatype, HttpServletResponse response){
		if(!CommonUtils.isEmptyString(ids)){
			MarkAdPoint markap = new MarkAdPoint();
			markap.setAddTime(new Date());
			markap.setPointId(ids);
			markap.setAreatype(areatype);
			maSysService.insertMarkAdPoint(markap);
			CommonUtils.setResponseStr("success", response);
			return;
		}else{
			CommonUtils.setResponseStr("error", response);
			return;
		}
	}
	
	@RequestMapping("/makepointmapids2")
	public void makepointmapids2(HttpServletResponse response, String ids, double longitude, double latitude, int areatype){
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<Long> pointlist = new ArrayList<Long>();
		String[] points = ids.split(",");
		if(points != null && points.length > 0){
			for (String str : points) {
				pointlist.add(CommonUtils.parseLong(str, 0));
			}
		}
		List<AdPoint> list = maSysService.getAdPointListByLoLaIds2(longitude,latitude, pointlist, areatype);
		result.put("list", list);
		result.put("returnlongitude", longitude);
		result.put("returnlatitude", latitude);
		CommonUtils.setMaptoJson(response, result);
	}
	
	@RequestMapping("/makepointmapids")
	public void makepointmapids(HttpServletResponse response, String ids){
		HashMap<String, Object> result = new HashMap<String, Object>();
		//第1个点中心点经纬度
		double longitude1 = 119.824582; 
		double latitude1 = 31.126021;
		//第2个点中心点经纬度
		double longitude2 = 119.996582; 
		double latitude2 = 31.126021;
		//第3个点中心点经纬度
		double longitude3 = 120.168582; 
		double latitude3 = 31.126021;
		//第4个点中心点经纬度
		double longitude4 = 120.340582; 
		double latitude4 = 31.126021;
		//第5个点中心点经纬度
		double longitude5 = 120.5212582; 
		double latitude5 = 31.126021;
		//第6个点中心点经纬度
		double longitude6 = 119.824582; 
		double latitude6 = 31.018021;
		//第7个点中心点经纬度
		double longitude7 = 119.996582; 
		double latitude7 = 31.018021;
		//第7个点中心点经纬度
		double longitude8 = 120.168582; 
		double latitude8 = 31.018021;
		//第9个点中心点经纬度
		double longitude9 = 120.340582; 
		double latitude9 = 31.018021;
		//第10个点中心点经纬度
		double longitude10 = 120.5212582; 
		double latitude10 = 31.018021;
		//第11个点中心点经纬度
		double longitude11 = 119.824582; 
		double latitude11 = 30.910021;
		//第12个点中心点经纬度
		double longitude12 = 119.996582; 
		double latitude12 = 30.910021;
		//第13个点中心点经纬度
		double longitude13 = 120.168582; 
		double latitude13 = 30.910021;
		//第14个点中心点经纬度
		double longitude14 = 120.340582; 
		double latitude14 = 30.910021;
		//第15个点中心点经纬度
		double longitude15 = 120.5212582; 
		double latitude15 = 30.910021;
		//第16个点中心点经纬度
		double longitude16 = 119.824582; 
		double latitude16 = 30.802021;
		//第17个点中心点经纬度
		double longitude17 = 119.996582; 
		double latitude17 = 30.802021;
		//第18个点中心点经纬度
		double longitude18 = 120.168582; 
		double latitude18 = 30.802021;
		//第19个点中心点经纬度
		double longitude19 = 120.340582; 
		double latitude19 = 30.802021;
		//第20个点中心点经纬度
		double longitude20 = 120.5212582; 
		double latitude20 = 30.802021;
		String[] points = ids.split(",");
		List<Long> pointlist = new ArrayList<Long>();
		if(points != null && points.length > 0){
			for (String str : points) {
				pointlist.add(CommonUtils.parseLong(str, 0));
			}
		}
		List<AdPoint> list1 = maSysService.getAdPointListByLoLaIds(longitude1,latitude1, pointlist);
		result.put("list1", list1);
		List<AdPoint> list2 = maSysService.getAdPointListByLoLaIds(longitude2,latitude2, pointlist);
		result.put("list2", list2);
		List<AdPoint> list3 = maSysService.getAdPointListByLoLaIds(longitude3,latitude3, pointlist);
		result.put("list3", list3);
		List<AdPoint> list4 = maSysService.getAdPointListByLoLaIds(longitude4,latitude4, pointlist);
		result.put("list4", list4);
		List<AdPoint> list5 = maSysService.getAdPointListByLoLaIds(longitude5,latitude5, pointlist);
		result.put("list5", list5);
		List<AdPoint> list6 = maSysService.getAdPointListByLoLaIds(longitude6,latitude6, pointlist);
		result.put("list6", list6);
		List<AdPoint> list7 = maSysService.getAdPointListByLoLaIds(longitude7,latitude7, pointlist);
		result.put("list7", list7);
		List<AdPoint> list8 = maSysService.getAdPointListByLoLaIds(longitude8,latitude8, pointlist);
		result.put("list8", list8);
		List<AdPoint> list9 = maSysService.getAdPointListByLoLaIds(longitude9,latitude9, pointlist);
		result.put("list9", list9);
		List<AdPoint> list10 = maSysService.getAdPointListByLoLaIds(longitude10,latitude10, pointlist);
		result.put("list10", list10);
		List<AdPoint> list11 = maSysService.getAdPointListByLoLaIds(longitude11,latitude11, pointlist);
		result.put("list11", list11);
		List<AdPoint> list12 = maSysService.getAdPointListByLoLaIds(longitude12,latitude12, pointlist);
		result.put("list12", list12);
		List<AdPoint> list13 = maSysService.getAdPointListByLoLaIds(longitude13,latitude13, pointlist);
		result.put("list13", list13);
		List<AdPoint> list14 = maSysService.getAdPointListByLoLaIds(longitude14,latitude14, pointlist);
		result.put("list14", list14);
		List<AdPoint> list15 = maSysService.getAdPointListByLoLaIds(longitude15,latitude15, pointlist);
		result.put("list15", list15);
		List<AdPoint> list16 = maSysService.getAdPointListByLoLaIds(longitude16,latitude16, pointlist);
		result.put("list16", list16);
		List<AdPoint> list17 = maSysService.getAdPointListByLoLaIds(longitude17,latitude17, pointlist);
		result.put("list17", list17);
		List<AdPoint> list18 = maSysService.getAdPointListByLoLaIds(longitude18,latitude18, pointlist);
		result.put("list18", list18);
		List<AdPoint> list19 = maSysService.getAdPointListByLoLaIds(longitude19,latitude19, pointlist);
		result.put("list19", list19);
		List<AdPoint> list20 = maSysService.getAdPointListByLoLaIds(longitude20,latitude20, pointlist);
		result.put("list20", list20);
		CommonUtils.setMaptoJson(response, result);
	}
	
	@RequestMapping("/mark_ad_point_list")
	public ModelAndView mark_ad_point_list(){
		ModelAndView mv = new ModelAndView("/map/mark_ad_point_list");
		mv.addObject("list", maSysService.getMarkAdPointList(0));
		return mv;
	}
	
	@RequestMapping("/mark_ad_point_list_taiyuan")
	public ModelAndView mark_ad_point_list_taiyuan(){
		ModelAndView mv = new ModelAndView("/map/mark_ad_point_list_taiyuan");
		mv.addObject("list", maSysService.getMarkAdPointList(1));
		return mv;
	}
	
	@RequestMapping("/del_mark_ad_point")
	public void del_mark_ad_point(long id, HttpServletResponse response){
		MarkAdPoint point = maSysService.selectMarkAdPointByKey(id);
		if(point != null){
			maSysService.deleteMarkAdPointByKey(id);
			CommonUtils.setResponseStr("success", response);
			return;
		}else{
			CommonUtils.setResponseStr("error", response);
			return;
		}
	}
	
	@RequestMapping("/mark_ad_point_map2")
	public void mark_ad_point_map2(HttpServletResponse response, Long id, double longitude, double latitude){
		HashMap<String, Object> result = new HashMap<String, Object>();
		MarkAdPoint point = maSysService.getMarkAdPointByid(id);
		String[] points = point.getPointId().split(",");
		List<Long> pointlist = new ArrayList<Long>();
		if(points != null && points.length > 0){
			for (String str : points) {
				pointlist.add(CommonUtils.parseLong(str, 0));
			}
		}
		List<AdPoint> list = maSysService.getAdPointListByLoLaIds2(longitude,latitude, pointlist);
		result.put("list", list);
		result.put("returnlongitude", longitude);
		result.put("returnlatitude", latitude);
		CommonUtils.setMaptoJson(response, result);
	}
	
	@RequestMapping("/mark_ad_point_map")
	public void mark_ad_point_map(HttpServletResponse response, Long id){
		HashMap<String, Object> result = new HashMap<String, Object>();
		//第1个点中心点经纬度
				double longitude1 = 119.824582; 
				double latitude1 = 31.126021;
				//第2个点中心点经纬度
				double longitude2 = 119.996582; 
				double latitude2 = 31.126021;
				//第3个点中心点经纬度
				double longitude3 = 120.168582; 
				double latitude3 = 31.126021;
				//第4个点中心点经纬度
				double longitude4 = 120.340582; 
				double latitude4 = 31.126021;
				//第5个点中心点经纬度
				double longitude5 = 120.5212582; 
				double latitude5 = 31.126021;
				//第6个点中心点经纬度
				double longitude6 = 119.824582; 
				double latitude6 = 31.018021;
				//第7个点中心点经纬度
				double longitude7 = 119.996582; 
				double latitude7 = 31.018021;
				//第7个点中心点经纬度
				double longitude8 = 120.168582; 
				double latitude8 = 31.018021;
				//第9个点中心点经纬度
				double longitude9 = 120.340582; 
				double latitude9 = 31.018021;
				//第10个点中心点经纬度
				double longitude10 = 120.5212582; 
				double latitude10 = 31.018021;
				//第11个点中心点经纬度
				double longitude11 = 119.824582; 
				double latitude11 = 30.910021;
				//第12个点中心点经纬度
				double longitude12 = 119.996582; 
				double latitude12 = 30.910021;
				//第13个点中心点经纬度
				double longitude13 = 120.168582; 
				double latitude13 = 30.910021;
				//第14个点中心点经纬度
				double longitude14 = 120.340582; 
				double latitude14 = 30.910021;
				//第15个点中心点经纬度
				double longitude15 = 120.5212582; 
				double latitude15 = 30.910021;
				//第16个点中心点经纬度
				double longitude16 = 119.824582; 
				double latitude16 = 30.802021;
				//第17个点中心点经纬度
				double longitude17 = 119.996582; 
				double latitude17 = 30.802021;
				//第18个点中心点经纬度
				double longitude18 = 120.168582; 
				double latitude18 = 30.802021;
				//第19个点中心点经纬度
				double longitude19 = 120.340582; 
				double latitude19 = 30.802021;
				//第20个点中心点经纬度
				double longitude20 = 120.5212582; 
				double latitude20 = 30.802021;
				MarkAdPoint point = maSysService.getMarkAdPointByid(id);
				String[] points = point.getPointId().split(",");
				List<Long> pointlist = new ArrayList<Long>();
				if(points != null && points.length > 0){
					for (String str : points) {
						pointlist.add(CommonUtils.parseLong(str, 0));
					}
				}
				List<AdPoint> list1 = maSysService.getAdPointListByLoLaIds(longitude1,latitude1, pointlist);
				result.put("list1", list1);
				List<AdPoint> list2 = maSysService.getAdPointListByLoLaIds(longitude2,latitude2, pointlist);
				result.put("list2", list2);
				List<AdPoint> list3 = maSysService.getAdPointListByLoLaIds(longitude3,latitude3, pointlist);
				result.put("list3", list3);
				List<AdPoint> list4 = maSysService.getAdPointListByLoLaIds(longitude4,latitude4, pointlist);
				result.put("list4", list4);
				List<AdPoint> list5 = maSysService.getAdPointListByLoLaIds(longitude5,latitude5, pointlist);
				result.put("list5", list5);
				List<AdPoint> list6 = maSysService.getAdPointListByLoLaIds(longitude6,latitude6, pointlist);
				result.put("list6", list6);
				List<AdPoint> list7 = maSysService.getAdPointListByLoLaIds(longitude7,latitude7, pointlist);
				result.put("list7", list7);
				List<AdPoint> list8 = maSysService.getAdPointListByLoLaIds(longitude8,latitude8, pointlist);
				result.put("list8", list8);
				List<AdPoint> list9 = maSysService.getAdPointListByLoLaIds(longitude9,latitude9, pointlist);
				result.put("list9", list9);
				List<AdPoint> list10 = maSysService.getAdPointListByLoLaIds(longitude10,latitude10, pointlist);
				result.put("list10", list10);
				List<AdPoint> list11 = maSysService.getAdPointListByLoLaIds(longitude11,latitude11, pointlist);
				result.put("list11", list11);
				List<AdPoint> list12 = maSysService.getAdPointListByLoLaIds(longitude12,latitude12, pointlist);
				result.put("list12", list12);
				List<AdPoint> list13 = maSysService.getAdPointListByLoLaIds(longitude13,latitude13, pointlist);
				result.put("list13", list13);
				List<AdPoint> list14 = maSysService.getAdPointListByLoLaIds(longitude14,latitude14, pointlist);
				result.put("list14", list14);
				List<AdPoint> list15 = maSysService.getAdPointListByLoLaIds(longitude15,latitude15, pointlist);
				result.put("list15", list15);
				List<AdPoint> list16 = maSysService.getAdPointListByLoLaIds(longitude16,latitude16, pointlist);
				result.put("list16", list16);
				List<AdPoint> list17 = maSysService.getAdPointListByLoLaIds(longitude17,latitude17, pointlist);
				result.put("list17", list17);
				List<AdPoint> list18 = maSysService.getAdPointListByLoLaIds(longitude18,latitude18, pointlist);
				result.put("list18", list18);
				List<AdPoint> list19 = maSysService.getAdPointListByLoLaIds(longitude19,latitude19, pointlist);
				result.put("list19", list19);
				List<AdPoint> list20 = maSysService.getAdPointListByLoLaIds(longitude20,latitude20, pointlist);
				result.put("list20", list20);
		CommonUtils.setMaptoJson(response, result);
	}
	
	/**
	 * 跳转到地图页
	 * @param keywords
	 * @param SourceClass
	 * @param areaSearch
	 * @param session
	 * @return
	 */
	@RequestMapping("/make_action_point_map")
	public ModelAndView make_action_point_map(Long id, int areatype){
		ModelAndView mov = new ModelAndView();
		mov.addObject("type", 1);
		mov.addObject("id", id);
		if(areatype == 0){
			mov.setViewName("/map/gd_map");
		}else{
			mov.setViewName("/map/gd_map_taiyuan");
		}
		return mov;
	}
	
	@RequestMapping("/getmaplist")
	public void getmaplist(HttpServletResponse response, Integer type, Long id, Long markid, int areatype){
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<AdPoint> maplist = new ArrayList<AdPoint>();
		if(type == 1){
			if(id == null){
				maplist = maSysService.getMapList("", "", "",areatype);
			}else{
				AdPoint info = maSysService.getAdPointById(id);
				if(info != null){
					maplist.add(info);
				}
			}
		}else{
			MarkAdPoint point = maSysService.getMarkAdPointByid(markid);
			String[] points = point.getPointId().split(",");
			List<Long> pointlist = new ArrayList<Long>();
			if(points != null && points.length > 0){
				for (String str : points) {
					pointlist.add(CommonUtils.parseLong(str, 0));
				}
			}
			maplist = maSysService.getMarkPointList(pointlist);
		}
		result.put("maplist", maplist);
		CommonUtils.setMaptoJson(response, result);
	}
	
	@RequestMapping("/make_mark_point_map")
	public ModelAndView make_mark_point_map(Long markid, int areatype){
		ModelAndView mov = new ModelAndView();
		mov.addObject("type", 2);
		mov.addObject("markid", markid);
		if(areatype == 0){
			mov.setViewName("/map/gd_map");
		}else{
			mov.setViewName("/map/gd_map_taiyuan");
		}
		return mov;
	}
	

	@RequestMapping("/select_mark_ad_point")
	public ModelAndView select_mark_ad_point(Integer pageIndex, String keywords, String SourceClass, String areaSearch, Long markid, int areatype){
		ModelAndView mv = new ModelAndView();
		int page = 1;
		if (pageIndex != null && pageIndex > 0) {
			page = pageIndex;
		}
		mv.addObject("list", maSysService.getAdPointList(page, keywords, SourceClass, areaSearch,areatype));
		mv.addObject("pageCount", maSysService.getAdPointListSize(keywords, SourceClass, areaSearch,areatype));
		mv.addObject("pageIndex", page);
		mv.addObject("keywords", keywords);
		mv.addObject("SourceClass", SourceClass);
		mv.addObject("areaSearch", areaSearch);
		mv.addObject("markid", markid);
		if(areatype == 0){
			mv.setViewName("/map/ad_mark_point_list");
		}else{
			mv.setViewName("/map/ad_mark_point_list_taiyuan");
		}
		return mv;
	}
	
	@RequestMapping("/editor_mark_ad_point")
	public ModelAndView editor_mark_ad_point(String[] ids, Long markid, int areatype){
		ModelAndView mov = new ModelAndView();
		MarkAdPoint point = maSysService.getMarkAdPointByid(markid);
		String pointids = point.getPointId();
		String[] pointlist = point.getPointId().split(",");
		if(ids != null && ids.length > 0){
			for (String str : ids) {
				if(!Arrays.asList(pointlist).contains(str)){
					if(CommonUtils.isEmptyString(pointids)){
						pointids = str;
					}else{
						pointids += ","+str;
					}
				}
			}
		}
		point.setPointId(pointids);
		maSysService.updateMarkAdPoint(point);
		if(areatype == 0){
			mov.setViewName("redirect:/map/mark_ad_point_list");
		}else{
			mov.setViewName("redirect:/map/mark_ad_point_list_taiyuan");
		}
		return mov;
	}
	
	
	@RequestMapping("/clearmsession")
	public ModelAndView clearmsession(HttpSession session) {
		ModelAndView mv = new ModelAndView("/map/login");
		session.removeAttribute("adminMapUserId");
		return mv;
	}
}
