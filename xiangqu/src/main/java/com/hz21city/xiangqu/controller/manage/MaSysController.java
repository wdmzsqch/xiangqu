package com.hz21city.xiangqu.controller.manage;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.common.JpushClientUtil;
import com.hz21city.xiangqu.common.JpushShopUtil;
import com.hz21city.xiangqu.pojo.AdInfo;
import com.hz21city.xiangqu.pojo.AdPoint;
import com.hz21city.xiangqu.pojo.AdminRole;
import com.hz21city.xiangqu.pojo.Area;
import com.hz21city.xiangqu.pojo.ChannelInfo;
import com.hz21city.xiangqu.pojo.HomeActivity;
import com.hz21city.xiangqu.pojo.Industry;
import com.hz21city.xiangqu.pojo.MarkAdPoint;
import com.hz21city.xiangqu.pojo.MissionCatogry;
import com.hz21city.xiangqu.pojo.SubjectInfo;
import com.hz21city.xiangqu.pojo.SystemMessage;
import com.hz21city.xiangqu.service.manage.IMaAdService;
import com.hz21city.xiangqu.service.manage.IMaAreaService;
import com.hz21city.xiangqu.service.manage.IMaEventService;
import com.hz21city.xiangqu.service.manage.IMaIndustryService;
import com.hz21city.xiangqu.service.manage.IMaSubjectService;
import com.hz21city.xiangqu.service.manage.IMaSysService;
import com.hz21city.xiangqu.service.manage.IMaUserService;

@Controller
public class MaSysController extends MaBaseController {

	@Resource
	private IMaAdService maAdService;
	@Resource
	private IMaSysService maSysService;
	@Resource
	private IMaAreaService maAreaService;
	@Resource
	private IMaIndustryService maIndustryService;
	@Resource
	private IMaEventService maEventService;
	@Resource
	private IMaSubjectService maSubjectService;
	@Resource
	private IMaUserService maUserService;

	@RequestMapping("/ad_list")
	public ModelAndView orderList(Integer searchtype, String keywords, String starttime, String endtime, Integer pageIndex) {
		ModelAndView mv = new ModelAndView("/manage/ad_list");
		mv.addObject("adList", maAdService.getAdList());
		mv.addObject("fileurl", CommonUtils.getFileRootUrl());
		return mv;
	}

	@RequestMapping("/edit_ad")
	public ModelAndView editAd(AdInfo adInfo, @RequestParam(value = "adimg", required = false) MultipartFile adimg) {
		ModelAndView mv = new ModelAndView("redirect:/manage/ad_list");
		if (adimg != null && !adimg.isEmpty()) {
			adInfo.setPic(getFilePathString(adimg));
		}
		if (adInfo.getId() == null) {
			maAdService.addAd(adInfo);
		} else {
			maAdService.editAd(adInfo);
		}
		return mv;
	}

	@RequestMapping("/del_ad")
	public ModelAndView delAd(Long adid) {
		ModelAndView mv = new ModelAndView("redirect:/manage/ad_list");
		maAdService.delAd(adid);
		return mv;
	}

	/**
	 * 权限组列表
	 * 
	 * @return
	 */
	@RequestMapping("/admin_role")
	public ModelAndView adminRole() {
		ModelAndView mv = new ModelAndView("/manage/permissions_set_list");
		mv.addObject("rolelist", maSysService.getAdminRoleList());
		return mv;
	}

	/**
	 * 跳转到编辑页面
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/edi_perssions")
	public ModelAndView edi_perssions(Long id) {
		ModelAndView mv = new ModelAndView("/manage/permissions_set_insert");
		if (id != 0) {
			mv.addObject("perssions", maSysService.selectByPrimaryKey(id));
		}
		return mv;
	}

	/**
	 * 保存或修改权限
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping("/perssions_edit")
	public ModelAndView perssions_edit(AdminRole role) {
		ModelAndView mv = new ModelAndView("redirect:/manage/admin_role");
		if (role.getId() == null) {
			maSysService.insert(role);
		} else {
			maSysService.updateByPrimaryKeySelective(role);
		}
		return mv;
	}

	/**
	 * 删除权限
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/del_perssion")
	public ModelAndView del_perssion(Long id) {
		ModelAndView mv = new ModelAndView("redirect:/manage/admin_role");
		if (maSysService.selectByPrimaryKey(id) != null) {
			maSysService.deleteByPrimaryKey(id);
		}
		return mv;
	}

	/**
	 * 行业列表
	 * 
	 * @param pageIndex
	 * @return
	 */
	@RequestMapping("/industry_list")
	public ModelAndView industryList(Integer pageIndex) {
		ModelAndView mv = new ModelAndView("/manage/industry_list");
		int page = 1;
		if (pageIndex != null && pageIndex > 0) {
			page = pageIndex;
		}
		mv.addObject("industrylist", maIndustryService.getIndustryList(page));
		mv.addObject("pageCount", maIndustryService.getIndustryListSize());
		mv.addObject("pageIndex", page);
		return mv;
	}

	/**
	 * 跳转到编辑行业
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/editorindustry")
	public ModelAndView editorindustry(Long id) {
		ModelAndView mv = new ModelAndView("/manage/industry_insert");
		if (id != 0) {
			mv.addObject("industry", maIndustryService.selectByPrimaryKey(id));
		}
		return mv;
	}

	/**
	 * 添加或修改行业
	 * 
	 * @param industry
	 * @return
	 */
	@RequestMapping("/industry_edit")
	public ModelAndView industry_edit(Industry industry) {
		ModelAndView mv = new ModelAndView("redirect:/manage/industry_list");
		if (industry.getId() == null) {
			maIndustryService.insert(industry);
		} else {
			maIndustryService.updateByPrimaryKeySelective(industry);
		}
		return mv;
	}

	/**
	 * 删除行业
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delindustry")
	public ModelAndView delindustry(Long id) {
		ModelAndView mv = new ModelAndView("redirect:/manage/industry_list");
		if (maIndustryService.selectByPrimaryKey(id) != null) {
			maIndustryService.deleteByPrimaryKey(id);
		}
		return mv;
	}

	/**
	 * 城市列表
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/city_list")
	public ModelAndView cityList(Long id) {
		ModelAndView mv = new ModelAndView("/manage/city_list");
		List<Area> selectprolist = maAreaService.getSelectByParentId(Long.valueOf(0));
		if (id == null) {
			id = Long.valueOf(11);
		}
		Area provice = maAreaService.selectByPrimaryKey(id);
		if (provice != null) {
			List<Area> citylist = maAreaService.getSelectByParentId(provice.getId());
			if (citylist != null && citylist.size() > 0) {
				provice.setCitylist(citylist);
				provice.setCitysize(citylist.size() + 1);
			} else {
				provice.setCitysize(2);
			}
		}
		mv.addObject("selectprolist", selectprolist);
		mv.addObject("provice", provice);
		mv.addObject("proid", id);
		return mv;
	}

	/**
	 * 任务类型
	 * 
	 * @param pageIndex
	 * @return
	 */
	@RequestMapping("/missioncatogry_list")
	public ModelAndView missioncatogryList(Integer pageIndex) {
		ModelAndView mv = new ModelAndView("/manage/missioncatogry_list");
		int page = 1;
		if (pageIndex != null && pageIndex > 0) {
			page = pageIndex;
		}
		mv.addObject("missioncatogrylist", maSysService.getMisstionCatogryList(page));
		mv.addObject("pageCount", maSysService.getMisstionCatogryListSize());
		mv.addObject("pageIndex", page);
		return mv;
	}

	/**
	 * 跳转到任务类型
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/editorcatogry")
	public ModelAndView editorcatogry(Long id) {
		ModelAndView mv = new ModelAndView("/manage/missioncatogry_insert");
		if (id != 0) {
			mv.addObject("missioncatogry", maSysService.selectCatogryByPrimaryKey(id));
		}
		return mv;
	}

	/**
	 * 添加或修改任务类型
	 * 
	 * @param industry
	 * @return
	 */
	@RequestMapping("/missioncatogry_edit")
	public ModelAndView missioncatogry_edit(MissionCatogry catogry) {
		ModelAndView mv = new ModelAndView("redirect:/manage/missioncatogry_list");
		if (catogry.getId() == null) {
			maSysService.insertCatogry(catogry);
		} else {
			maSysService.updateCatogryByPrimaryKeySelective(catogry);
		}
		return mv;
	}

	/**
	 * 首页活动
	 * 
	 * @return
	 */
	@RequestMapping("/home_activity")
	public ModelAndView home_activity() {
		ModelAndView mv = new ModelAndView("/manage/home_activity");
		HomeActivity homeActivityOne = maSysService.getHomeActivityByKey("homeActivityOne");
		HomeActivity homeActivityTwo = maSysService.getHomeActivityByKey("homeActivityTwo");
		HomeActivity homeActivityThree = maSysService.getHomeActivityByKey("homeActivityThree");
		HomeActivity homeActivityFour = maSysService.getHomeActivityByKey("homeActivityFour");
		HomeActivity homeActivityFive = maSysService.getHomeActivityByKey("homeActivityFive");
		mv.addObject("homeActivityOne", homeActivityOne);
		mv.addObject("homeActivityTwo", homeActivityTwo);
		mv.addObject("homeActivityThree", homeActivityThree);
		mv.addObject("homeActivityFour", homeActivityFour);
		mv.addObject("homeActivityFive", homeActivityFive);
		mv.addObject("fileurl", CommonUtils.getFileRootUrl());
		return mv;
	}

	@RequestMapping("/edit_activity")
	public ModelAndView edit_activity(HomeActivity activity, @RequestParam(value = "activityfile", required = false) MultipartFile activityfile) {
		ModelAndView mv = new ModelAndView("redirect:/manage/home_activity");
		if (activityfile != null && !activityfile.isEmpty()) {
			activity.setPic(getFilePathString(activityfile));
		}
		activity.setAddTime(new Date());
		if (activity.getId() == null) {
			maSysService.insertHomeActivity(activity);
		} else {
			maSysService.updateHomeActivity(activity);
		}
		// HomeActivity homeactivity = maSysService.getHomeActivityByKey(activity.getValuekey());
		// if(homeactivity == null){
		// homeactivity = new HomeActivity();
		// if(activityfile != null && !activityfile.isEmpty()){
		// homeactivity.setPic(getFilePathString(activityfile));
		// }
		// homeactivity.setValuekey(activity.getValuekey());
		// maSysService.insertHomeActivity(homeactivity);
		// }else{
		// if(activityfile != null && !activityfile.isEmpty()){
		// homeactivity.setPic(getFilePathString(activityfile));
		// }
		// maSysService.updateHomeActivity(homeactivity);
		// }
		return mv;
	}

	@RequestMapping("/selectActivity")
	public ModelAndView selectActivity(HomeActivity activity, @RequestParam(value = "activityfile", required = false) MultipartFile activityfile, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		if (activityfile != null && !activityfile.isEmpty()) {
			activity.setPic(getFilePathString(activityfile));
		}
		session.setAttribute("homeActivity", activity);
		if (activity.getType() == 1) {
			mv.setViewName("redirect:/manage/selectEvent");
		} else {
			mv.setViewName("redirect:/manage/selectSubject");
		}
		return mv;
	}

	@RequestMapping("/selectEvent")
	public ModelAndView selectEvent(Integer pageIndex) {
		ModelAndView mov = new ModelAndView("/manage/selectEvent_list");
		int page = 1;
		if (pageIndex != null && pageIndex > 0) {
			page = pageIndex;
		}
		mov.addObject("event_list", maEventService.getEventListByPage(page));
		mov.addObject("pageCount", maEventService.getEventListSize());
		mov.addObject("fileUrl", CommonUtils.getFileRootUrl());
		return mov;
	}

	@RequestMapping("/selectSubject")
	public ModelAndView selectSubject(Integer pageIndex) {
		ModelAndView mov = new ModelAndView("/manage/selectSubject_list");
		int page = 1;
		if (pageIndex != null && pageIndex > 0) {
			page = pageIndex;
		}
		mov.addObject("subject_list", maSubjectService.getSubjectListByPage(page));
		mov.addObject("pageCount", maSubjectService.getSubjectListSize());
		mov.addObject("fileUrl", CommonUtils.getFileRootUrl());
		return mov;
	}

	@RequestMapping("/chooseActivity")
	public ModelAndView chooseActivity(Long relatived_id, HttpSession session) {
		ModelAndView mv = new ModelAndView("redirect:/manage/home_activity");
		HomeActivity homeactivity = (HomeActivity) session.getAttribute("homeActivity");
		homeactivity.setRelatived_id(relatived_id);
		homeactivity.setAddTime(new Date());
		if (homeactivity.getType() == 2) {
			SubjectInfo subject = maSubjectService.selectByPrimaryKey(relatived_id);
			if (subject != null) {
				homeactivity.setDetailUrl(subject.getDetailUrl());
			}
		} else {
			homeactivity.setDetailUrl("");
		}
		if (homeactivity.getId() == null) {
			maSysService.insertHomeActivity(homeactivity);
		} else {
			maSysService.updateHomeActivity(homeactivity);
		}
		return mv;
	}

	@RequestMapping("/system_message")
	public ModelAndView system_message(Integer pageIndex) {
		ModelAndView mov = new ModelAndView("/manage/system_message");
		if (pageIndex == null)
			pageIndex = 1;
		mov.addObject("message_list", maSysService.getSystemMessageListByPage(pageIndex));
		mov.addObject("pageCount", maSysService.getSystemMessageListSize());
		mov.addObject("pageIndex", pageIndex);
		return mov;
	}

	@RequestMapping("/turn_sendmessage")
	public ModelAndView turn_sendmessage() {
		ModelAndView mov = new ModelAndView();
		mov.setViewName("/manage/addsystem_message");
		return mov;
	}

	@RequestMapping("/sendsysmessage")
	@ResponseBody
	public HashMap<String, Object> sendsysmessage(SystemMessage systemmessage) {
		HashMap<String, Object> resultmap = new HashMap<String, Object>();
		systemmessage.setPublishTime(new Date());
		maSysService.sendSysmessage(systemmessage);
		JpushClientUtil jPushClientUtil = new JpushClientUtil();
		JpushShopUtil jPushShopUtil = new JpushShopUtil();
		if (systemmessage.getType() == 1) {// 后台消息发给用户
			// 给用户发推送
			jPushClientUtil.sendPush2All(systemmessage.getContent());
		} else if (systemmessage.getType() == 2) {// 后台消息发给商家
			// 商家推送还未确定
			jPushShopUtil.sendPush2All(systemmessage.getContent());
		} else {
			// 给所有用户发推送
			jPushClientUtil.sendPush2All(systemmessage.getContent());
			jPushShopUtil.sendPush2All(systemmessage.getContent());
		}
		return resultmap;
	}

	@RequestMapping("/delMessage")
	@ResponseBody
	public HashMap<String, Object> delMessage(String[] messageid) {
		HashMap<String, Object> resultmap = new HashMap<String, Object>();
		for (String id : messageid) {
			maSysService.delSystemMessage(CommonUtils.parseLong(id, 0));
		}
		return resultmap;
	}

	@RequestMapping("/dynamic")
	public ModelAndView dynamic() {
		ModelAndView mov = new ModelAndView();
		List<AdInfo> list = maAdService.getAdListByType(3);
		if (list != null && list.size() > 0) {
			mov.addObject("dynamic", list.get(0));
		}
		mov.setViewName("/manage/dynamic");
		return mov;
	}

	@RequestMapping("/edit_dy")
	public ModelAndView edit_dy(AdInfo adInfo) {
		ModelAndView mov = new ModelAndView();
		if (adInfo.getId() == null) {
			maAdService.addAd(adInfo);
		} else {
			maAdService.editAd(adInfo);
		}
		mov.setViewName("redirect:/manage/dynamic");
		return mov;
	}

	@RequestMapping("/ad_point_list")
	public ModelAndView ad_point_list(Integer pageIndex, String keywords, String SourceClass, String areaSearch) {
		ModelAndView mv = new ModelAndView("/manage/ad_point_list");
		int page = 1;
		if (pageIndex != null && pageIndex > 0) {
			page = pageIndex;
		}
		mv.addObject("list", maSysService.getAdPointList(page, keywords, SourceClass, areaSearch));
		mv.addObject("pageCount", maSysService.getAdPointListSize(keywords, SourceClass, areaSearch));
		mv.addObject("pageIndex", page);
		mv.addObject("keywords", keywords);
		mv.addObject("SourceClass", SourceClass);
		mv.addObject("areaSearch", areaSearch);
		return mv;
	}

	@RequestMapping("/ad_point_detail")
	public ModelAndView ad_point_detail(Long id) {
		ModelAndView mv = new ModelAndView("/manage/ad_point_insert");
		if (id != null) {
			mv.addObject("adpoint", maSysService.selectAdPointByPrimaryKey(id));
		}
		return mv;
	}

	@RequestMapping("/ad_point_edit")
	public ModelAndView ad_point_edit(AdPoint adpoint) {
		ModelAndView mv = new ModelAndView("redirect:/manage/ad_point_list");
		if (adpoint.getId() == null) {
			adpoint.setAddTime(new Date());
			if (CommonUtils.isEmptyString(adpoint.getBackground())) {
				adpoint.setBackground("0x008000");
			}
			maSysService.insertAdPoint(adpoint);
		} else {
			if (CommonUtils.isEmptyString(adpoint.getBackground())) {
				adpoint.setBackground("0x008000");
			}
			maSysService.updateAdPointByPrimaryKeySelective(adpoint);
		}
		return mv;
	}

	@RequestMapping("/makepointmap2")
	public void makepointmap2(HttpServletResponse response, String keywords, String SourceClass, String areaSearch, double longitude, double latitude) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<AdPoint> list = maSysService.getAdPointListByLoLa2(longitude, latitude, keywords, SourceClass, areaSearch);
		result.put("list", list);
		result.put("returnlongitude", longitude);
		result.put("returnlatitude", latitude);
		CommonUtils.setMaptoJson(response, result);
	}

	@RequestMapping("/makepointmap")
	public void makepointmap(HttpServletResponse response, String keywords, String SourceClass, String areaSearch) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		// 第1个点中心点经纬度
		double longitude1 = 119.824582;
		double latitude1 = 31.126021;
		// 第2个点中心点经纬度
		double longitude2 = 119.996582;
		double latitude2 = 31.126021;
		// 第3个点中心点经纬度
		double longitude3 = 120.168582;
		double latitude3 = 31.126021;
		// 第4个点中心点经纬度
		double longitude4 = 120.340582;
		double latitude4 = 31.126021;
		// 第5个点中心点经纬度
		double longitude5 = 120.5212582;
		double latitude5 = 31.126021;
		// 第6个点中心点经纬度
		double longitude6 = 119.824582;
		double latitude6 = 31.018021;
		// 第7个点中心点经纬度
		double longitude7 = 119.996582;
		double latitude7 = 31.018021;
		// 第7个点中心点经纬度
		double longitude8 = 120.168582;
		double latitude8 = 31.018021;
		// 第9个点中心点经纬度
		double longitude9 = 120.340582;
		double latitude9 = 31.018021;
		// 第10个点中心点经纬度
		double longitude10 = 120.5212582;
		double latitude10 = 31.018021;
		// 第11个点中心点经纬度
		double longitude11 = 119.824582;
		double latitude11 = 30.910021;
		// 第12个点中心点经纬度
		double longitude12 = 119.996582;
		double latitude12 = 30.910021;
		// 第13个点中心点经纬度
		double longitude13 = 120.168582;
		double latitude13 = 30.910021;
		// 第14个点中心点经纬度
		double longitude14 = 120.340582;
		double latitude14 = 30.910021;
		// 第15个点中心点经纬度
		double longitude15 = 120.5212582;
		double latitude15 = 30.910021;
		// 第16个点中心点经纬度
		double longitude16 = 119.824582;
		double latitude16 = 30.802021;
		// 第17个点中心点经纬度
		double longitude17 = 119.996582;
		double latitude17 = 30.802021;
		// 第18个点中心点经纬度
		double longitude18 = 120.168582;
		double latitude18 = 30.802021;
		// 第19个点中心点经纬度
		double longitude19 = 120.340582;
		double latitude19 = 30.802021;
		// 第20个点中心点经纬度
		double longitude20 = 120.521258;
		double latitude20 = 30.802021;
		// 第21个点中心点经纬度
		double longitude21 = 120.13446808;
		double latitude21 = 30.85250035;
		List<AdPoint> list1 = maSysService.getAdPointListByLoLa(longitude1, latitude1, keywords, SourceClass, areaSearch);
		result.put("list1", list1);
		List<AdPoint> list2 = maSysService.getAdPointListByLoLa(longitude2, latitude2, keywords, SourceClass, areaSearch);
		result.put("list2", list2);
		List<AdPoint> list3 = maSysService.getAdPointListByLoLa(longitude3, latitude3, keywords, SourceClass, areaSearch);
		result.put("list3", list3);
		List<AdPoint> list4 = maSysService.getAdPointListByLoLa(longitude4, latitude4, keywords, SourceClass, areaSearch);
		result.put("list4", list4);
		List<AdPoint> list5 = maSysService.getAdPointListByLoLa(longitude5, latitude5, keywords, SourceClass, areaSearch);
		result.put("list5", list5);
		List<AdPoint> list6 = maSysService.getAdPointListByLoLa(longitude6, latitude6, keywords, SourceClass, areaSearch);
		result.put("list6", list6);
		List<AdPoint> list7 = maSysService.getAdPointListByLoLa(longitude7, latitude7, keywords, SourceClass, areaSearch);
		result.put("list7", list7);
		List<AdPoint> list8 = maSysService.getAdPointListByLoLa(longitude8, latitude8, keywords, SourceClass, areaSearch);
		result.put("list8", list8);
		List<AdPoint> list9 = maSysService.getAdPointListByLoLa(longitude9, latitude9, keywords, SourceClass, areaSearch);
		result.put("list9", list9);
		List<AdPoint> list10 = maSysService.getAdPointListByLoLa(longitude10, latitude10, keywords, SourceClass, areaSearch);
		result.put("list10", list10);
		List<AdPoint> list11 = maSysService.getAdPointListByLoLa(longitude11, latitude11, keywords, SourceClass, areaSearch);
		result.put("list11", list11);
		List<AdPoint> list12 = maSysService.getAdPointListByLoLa(longitude12, latitude12, keywords, SourceClass, areaSearch);
		result.put("list12", list12);
		List<AdPoint> list13 = maSysService.getAdPointListByLoLa(longitude13, latitude13, keywords, SourceClass, areaSearch);
		result.put("list13", list13);
		List<AdPoint> list14 = maSysService.getAdPointListByLoLa(longitude14, latitude14, keywords, SourceClass, areaSearch);
		result.put("list14", list14);
		List<AdPoint> list15 = maSysService.getAdPointListByLoLa(longitude15, latitude15, keywords, SourceClass, areaSearch);
		result.put("list15", list15);
		List<AdPoint> list16 = maSysService.getAdPointListByLoLa(longitude16, latitude16, keywords, SourceClass, areaSearch);
		result.put("list16", list16);
		List<AdPoint> list17 = maSysService.getAdPointListByLoLa(longitude17, latitude17, keywords, SourceClass, areaSearch);
		result.put("list17", list17);
		List<AdPoint> list18 = maSysService.getAdPointListByLoLa(longitude18, latitude18, keywords, SourceClass, areaSearch);
		result.put("list18", list18);
		List<AdPoint> list19 = maSysService.getAdPointListByLoLa(longitude19, latitude19, keywords, SourceClass, areaSearch);
		result.put("list19", list19);
		List<AdPoint> list20 = maSysService.getAdPointListByLoLa(longitude20, latitude20, keywords, SourceClass, areaSearch);
		result.put("list20", list20);
		List<AdPoint> list21 = maSysService.getAdPointListByLoLa(longitude21, latitude21, keywords, SourceClass, areaSearch);
		result.put("list21", list21);
		CommonUtils.setMaptoJson(response, result);
	}

	@RequestMapping("/del_ad_point")
	public void del_ad_point(HttpServletResponse response, Long id) {
		AdPoint point = maSysService.selectAdPointByPrimaryKey(id);
		if (point != null) {
			maSysService.deleteAdPointByKey(id);
			CommonUtils.setResponseStr("success", response);
			return;
		} else {
			CommonUtils.setResponseStr("error", response);
			return;
		}
	}

	@RequestMapping("/add_mark_ad_point")
	public void add_mark_ad_point(String ids, HttpServletResponse response) {
		if (!CommonUtils.isEmptyString(ids)) {
			MarkAdPoint markap = new MarkAdPoint();
			markap.setAddTime(new Date());
			markap.setPointId(ids);
			maSysService.insertMarkAdPoint(markap);
			CommonUtils.setResponseStr("success", response);
			return;
		} else {
			CommonUtils.setResponseStr("error", response);
			return;
		}
	}

	@RequestMapping("/makepointmapids2")
	public void makepointmapids2(HttpServletResponse response, String ids, double longitude, double latitude) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<Long> pointlist = new ArrayList<Long>();
		String[] points = ids.split(",");
		if (points != null && points.length > 0) {
			for (String str : points) {
				pointlist.add(CommonUtils.parseLong(str, 0));
			}
		}
		List<AdPoint> list = maSysService.getAdPointListByLoLaIds2(longitude, latitude, pointlist);
		result.put("list", list);
		result.put("returnlongitude", longitude);
		result.put("returnlatitude", latitude);
		CommonUtils.setMaptoJson(response, result);
	}

	@RequestMapping("/makepointmapids")
	public void makepointmapids(HttpServletResponse response, String ids) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		// 第1个点中心点经纬度
		double longitude1 = 119.824582;
		double latitude1 = 31.126021;
		// 第2个点中心点经纬度
		double longitude2 = 119.996582;
		double latitude2 = 31.126021;
		// 第3个点中心点经纬度
		double longitude3 = 120.168582;
		double latitude3 = 31.126021;
		// 第4个点中心点经纬度
		double longitude4 = 120.340582;
		double latitude4 = 31.126021;
		// 第5个点中心点经纬度
		double longitude5 = 120.5212582;
		double latitude5 = 31.126021;
		// 第6个点中心点经纬度
		double longitude6 = 119.824582;
		double latitude6 = 31.018021;
		// 第7个点中心点经纬度
		double longitude7 = 119.996582;
		double latitude7 = 31.018021;
		// 第7个点中心点经纬度
		double longitude8 = 120.168582;
		double latitude8 = 31.018021;
		// 第9个点中心点经纬度
		double longitude9 = 120.340582;
		double latitude9 = 31.018021;
		// 第10个点中心点经纬度
		double longitude10 = 120.5212582;
		double latitude10 = 31.018021;
		// 第11个点中心点经纬度
		double longitude11 = 119.824582;
		double latitude11 = 30.910021;
		// 第12个点中心点经纬度
		double longitude12 = 119.996582;
		double latitude12 = 30.910021;
		// 第13个点中心点经纬度
		double longitude13 = 120.168582;
		double latitude13 = 30.910021;
		// 第14个点中心点经纬度
		double longitude14 = 120.340582;
		double latitude14 = 30.910021;
		// 第15个点中心点经纬度
		double longitude15 = 120.5212582;
		double latitude15 = 30.910021;
		// 第16个点中心点经纬度
		double longitude16 = 119.824582;
		double latitude16 = 30.802021;
		// 第17个点中心点经纬度
		double longitude17 = 119.996582;
		double latitude17 = 30.802021;
		// 第18个点中心点经纬度
		double longitude18 = 120.168582;
		double latitude18 = 30.802021;
		// 第19个点中心点经纬度
		double longitude19 = 120.340582;
		double latitude19 = 30.802021;
		// 第20个点中心点经纬度
		double longitude20 = 120.5212582;
		double latitude20 = 30.802021;
		String[] points = ids.split(",");
		List<Long> pointlist = new ArrayList<Long>();
		if (points != null && points.length > 0) {
			for (String str : points) {
				pointlist.add(CommonUtils.parseLong(str, 0));
			}
		}
		List<AdPoint> list1 = maSysService.getAdPointListByLoLaIds(longitude1, latitude1, pointlist);
		result.put("list1", list1);
		List<AdPoint> list2 = maSysService.getAdPointListByLoLaIds(longitude2, latitude2, pointlist);
		result.put("list2", list2);
		List<AdPoint> list3 = maSysService.getAdPointListByLoLaIds(longitude3, latitude3, pointlist);
		result.put("list3", list3);
		List<AdPoint> list4 = maSysService.getAdPointListByLoLaIds(longitude4, latitude4, pointlist);
		result.put("list4", list4);
		List<AdPoint> list5 = maSysService.getAdPointListByLoLaIds(longitude5, latitude5, pointlist);
		result.put("list5", list5);
		List<AdPoint> list6 = maSysService.getAdPointListByLoLaIds(longitude6, latitude6, pointlist);
		result.put("list6", list6);
		List<AdPoint> list7 = maSysService.getAdPointListByLoLaIds(longitude7, latitude7, pointlist);
		result.put("list7", list7);
		List<AdPoint> list8 = maSysService.getAdPointListByLoLaIds(longitude8, latitude8, pointlist);
		result.put("list8", list8);
		List<AdPoint> list9 = maSysService.getAdPointListByLoLaIds(longitude9, latitude9, pointlist);
		result.put("list9", list9);
		List<AdPoint> list10 = maSysService.getAdPointListByLoLaIds(longitude10, latitude10, pointlist);
		result.put("list10", list10);
		List<AdPoint> list11 = maSysService.getAdPointListByLoLaIds(longitude11, latitude11, pointlist);
		result.put("list11", list11);
		List<AdPoint> list12 = maSysService.getAdPointListByLoLaIds(longitude12, latitude12, pointlist);
		result.put("list12", list12);
		List<AdPoint> list13 = maSysService.getAdPointListByLoLaIds(longitude13, latitude13, pointlist);
		result.put("list13", list13);
		List<AdPoint> list14 = maSysService.getAdPointListByLoLaIds(longitude14, latitude14, pointlist);
		result.put("list14", list14);
		List<AdPoint> list15 = maSysService.getAdPointListByLoLaIds(longitude15, latitude15, pointlist);
		result.put("list15", list15);
		List<AdPoint> list16 = maSysService.getAdPointListByLoLaIds(longitude16, latitude16, pointlist);
		result.put("list16", list16);
		List<AdPoint> list17 = maSysService.getAdPointListByLoLaIds(longitude17, latitude17, pointlist);
		result.put("list17", list17);
		List<AdPoint> list18 = maSysService.getAdPointListByLoLaIds(longitude18, latitude18, pointlist);
		result.put("list18", list18);
		List<AdPoint> list19 = maSysService.getAdPointListByLoLaIds(longitude19, latitude19, pointlist);
		result.put("list19", list19);
		List<AdPoint> list20 = maSysService.getAdPointListByLoLaIds(longitude20, latitude20, pointlist);
		result.put("list20", list20);
		CommonUtils.setMaptoJson(response, result);
	}

	@RequestMapping("/mark_ad_point_list")
	public ModelAndView mark_ad_point_list() {
		ModelAndView mv = new ModelAndView("/manage/mark_ad_point_list");
		mv.addObject("list", maSysService.getMarkAdPointList());
		return mv;
	}

	@RequestMapping("/del_mark_ad_point")
	public void del_mark_ad_point(long id, HttpServletResponse response) {
		MarkAdPoint point = maSysService.selectMarkAdPointByKey(id);
		if (point != null) {
			maSysService.deleteMarkAdPointByKey(id);
			CommonUtils.setResponseStr("success", response);
			return;
		} else {
			CommonUtils.setResponseStr("error", response);
			return;
		}
	}

	@RequestMapping("/mark_ad_point_map2")
	public void mark_ad_point_map2(HttpServletResponse response, Long id, double longitude, double latitude) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		MarkAdPoint point = maSysService.getMarkAdPointByid(id);
		String[] points = point.getPointId().split(",");
		List<Long> pointlist = new ArrayList<Long>();
		if (points != null && points.length > 0) {
			for (String str : points) {
				pointlist.add(CommonUtils.parseLong(str, 0));
			}
		}
		List<AdPoint> list = maSysService.getAdPointListByLoLaIds2(longitude, latitude, pointlist);
		result.put("list", list);
		result.put("returnlongitude", longitude);
		result.put("returnlatitude", latitude);
		CommonUtils.setMaptoJson(response, result);
	}

	@RequestMapping("/mark_ad_point_map")
	public void mark_ad_point_map(HttpServletResponse response, Long id) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		// 第1个点中心点经纬度
		double longitude1 = 119.824582;
		double latitude1 = 31.126021;
		// 第2个点中心点经纬度
		double longitude2 = 119.996582;
		double latitude2 = 31.126021;
		// 第3个点中心点经纬度
		double longitude3 = 120.168582;
		double latitude3 = 31.126021;
		// 第4个点中心点经纬度
		double longitude4 = 120.340582;
		double latitude4 = 31.126021;
		// 第5个点中心点经纬度
		double longitude5 = 120.5212582;
		double latitude5 = 31.126021;
		// 第6个点中心点经纬度
		double longitude6 = 119.824582;
		double latitude6 = 31.018021;
		// 第7个点中心点经纬度
		double longitude7 = 119.996582;
		double latitude7 = 31.018021;
		// 第7个点中心点经纬度
		double longitude8 = 120.168582;
		double latitude8 = 31.018021;
		// 第9个点中心点经纬度
		double longitude9 = 120.340582;
		double latitude9 = 31.018021;
		// 第10个点中心点经纬度
		double longitude10 = 120.5212582;
		double latitude10 = 31.018021;
		// 第11个点中心点经纬度
		double longitude11 = 119.824582;
		double latitude11 = 30.910021;
		// 第12个点中心点经纬度
		double longitude12 = 119.996582;
		double latitude12 = 30.910021;
		// 第13个点中心点经纬度
		double longitude13 = 120.168582;
		double latitude13 = 30.910021;
		// 第14个点中心点经纬度
		double longitude14 = 120.340582;
		double latitude14 = 30.910021;
		// 第15个点中心点经纬度
		double longitude15 = 120.5212582;
		double latitude15 = 30.910021;
		// 第16个点中心点经纬度
		double longitude16 = 119.824582;
		double latitude16 = 30.802021;
		// 第17个点中心点经纬度
		double longitude17 = 119.996582;
		double latitude17 = 30.802021;
		// 第18个点中心点经纬度
		double longitude18 = 120.168582;
		double latitude18 = 30.802021;
		// 第19个点中心点经纬度
		double longitude19 = 120.340582;
		double latitude19 = 30.802021;
		// 第20个点中心点经纬度
		double longitude20 = 120.5212582;
		double latitude20 = 30.802021;
		MarkAdPoint point = maSysService.getMarkAdPointByid(id);
		String[] points = point.getPointId().split(",");
		List<Long> pointlist = new ArrayList<Long>();
		if (points != null && points.length > 0) {
			for (String str : points) {
				pointlist.add(CommonUtils.parseLong(str, 0));
			}
		}
		List<AdPoint> list1 = maSysService.getAdPointListByLoLaIds(longitude1, latitude1, pointlist);
		result.put("list1", list1);
		List<AdPoint> list2 = maSysService.getAdPointListByLoLaIds(longitude2, latitude2, pointlist);
		result.put("list2", list2);
		List<AdPoint> list3 = maSysService.getAdPointListByLoLaIds(longitude3, latitude3, pointlist);
		result.put("list3", list3);
		List<AdPoint> list4 = maSysService.getAdPointListByLoLaIds(longitude4, latitude4, pointlist);
		result.put("list4", list4);
		List<AdPoint> list5 = maSysService.getAdPointListByLoLaIds(longitude5, latitude5, pointlist);
		result.put("list5", list5);
		List<AdPoint> list6 = maSysService.getAdPointListByLoLaIds(longitude6, latitude6, pointlist);
		result.put("list6", list6);
		List<AdPoint> list7 = maSysService.getAdPointListByLoLaIds(longitude7, latitude7, pointlist);
		result.put("list7", list7);
		List<AdPoint> list8 = maSysService.getAdPointListByLoLaIds(longitude8, latitude8, pointlist);
		result.put("list8", list8);
		List<AdPoint> list9 = maSysService.getAdPointListByLoLaIds(longitude9, latitude9, pointlist);
		result.put("list9", list9);
		List<AdPoint> list10 = maSysService.getAdPointListByLoLaIds(longitude10, latitude10, pointlist);
		result.put("list10", list10);
		List<AdPoint> list11 = maSysService.getAdPointListByLoLaIds(longitude11, latitude11, pointlist);
		result.put("list11", list11);
		List<AdPoint> list12 = maSysService.getAdPointListByLoLaIds(longitude12, latitude12, pointlist);
		result.put("list12", list12);
		List<AdPoint> list13 = maSysService.getAdPointListByLoLaIds(longitude13, latitude13, pointlist);
		result.put("list13", list13);
		List<AdPoint> list14 = maSysService.getAdPointListByLoLaIds(longitude14, latitude14, pointlist);
		result.put("list14", list14);
		List<AdPoint> list15 = maSysService.getAdPointListByLoLaIds(longitude15, latitude15, pointlist);
		result.put("list15", list15);
		List<AdPoint> list16 = maSysService.getAdPointListByLoLaIds(longitude16, latitude16, pointlist);
		result.put("list16", list16);
		List<AdPoint> list17 = maSysService.getAdPointListByLoLaIds(longitude17, latitude17, pointlist);
		result.put("list17", list17);
		List<AdPoint> list18 = maSysService.getAdPointListByLoLaIds(longitude18, latitude18, pointlist);
		result.put("list18", list18);
		List<AdPoint> list19 = maSysService.getAdPointListByLoLaIds(longitude19, latitude19, pointlist);
		result.put("list19", list19);
		List<AdPoint> list20 = maSysService.getAdPointListByLoLaIds(longitude20, latitude20, pointlist);
		result.put("list20", list20);
		CommonUtils.setMaptoJson(response, result);
	}

	/**
	 * 跳转到地图页
	 * 
	 * @param keywords
	 * @param SourceClass
	 * @param areaSearch
	 * @param session
	 * @return
	 */
	@RequestMapping("/make_action_point_map")
	public ModelAndView make_action_point_map(Long id) {
		ModelAndView mov = new ModelAndView("/manage/gd_map");
		mov.addObject("type", 1);
		mov.addObject("id", id);
		return mov;
	}

	@RequestMapping("/getmaplist")
	public void getmaplist(HttpServletResponse response, Integer type, Long id, Long markid) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<AdPoint> maplist = new ArrayList<AdPoint>();
		if (type == 1) {
			if (id == null) {
				maplist = maSysService.getMapList("", "", "");
			} else {
				AdPoint info = maSysService.getAdPointById(id);
				if (info != null) {
					maplist.add(info);
				}
			}
		} else {
			MarkAdPoint point = maSysService.getMarkAdPointByid(markid);
			String[] points = point.getPointId().split(",");
			List<Long> pointlist = new ArrayList<Long>();
			if (points != null && points.length > 0) {
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
	public ModelAndView make_mark_point_map(Long markid) {
		ModelAndView mov = new ModelAndView("/manage/gd_map");
		mov.addObject("type", 2);
		mov.addObject("markid", markid);
		return mov;
	}

	@RequestMapping("/select_mark_ad_point")
	public ModelAndView select_mark_ad_point(Integer pageIndex, String keywords, String SourceClass, String areaSearch, Long markid) {
		ModelAndView mv = new ModelAndView("/manage/ad_mark_point_list");
		int page = 1;
		if (pageIndex != null && pageIndex > 0) {
			page = pageIndex;
		}
		mv.addObject("list", maSysService.getAdPointList(page, keywords, SourceClass, areaSearch));
		mv.addObject("pageCount", maSysService.getAdPointListSize(keywords, SourceClass, areaSearch));
		mv.addObject("pageIndex", page);
		mv.addObject("keywords", keywords);
		mv.addObject("SourceClass", SourceClass);
		mv.addObject("areaSearch", areaSearch);
		mv.addObject("markid", markid);
		return mv;
	}

	@RequestMapping("/editor_mark_ad_point")
	public ModelAndView editor_mark_ad_point(String[] ids, Long markid) {
		ModelAndView mov = new ModelAndView("redirect:/manage/mark_ad_point_list");
		MarkAdPoint point = maSysService.getMarkAdPointByid(markid);
		String pointids = point.getPointId();
		if (ids != null && ids.length > 0) {
			for (String str : ids) {
				if (!pointids.contains(str)) {
					if (CommonUtils.isEmptyString(pointids)) {
						pointids = str;
					} else {
						pointids += "," + str;
					}
				}
			}
		}
		point.setPointId(pointids);
		maSysService.updateMarkAdPoint(point);
		return mov;
	}

	@RequestMapping("/card_list")
	public ModelAndView card_list(Integer pageIndex) {
		ModelAndView mv = new ModelAndView("/manage/card_list");
		int page = 1;
		if (pageIndex != null && pageIndex > 0) {
			page = pageIndex;
		}
		mv.addObject("list", maSysService.getCardList(page));
		mv.addObject("pageCount", maSysService.getCardListSize());
		mv.addObject("pageIndex", page);
		return mv;
	}
	
	@RequestMapping("/card_record_list")
	public ModelAndView card_record_list(Integer pageIndex) {
		ModelAndView mv = new ModelAndView("/manage/card_record_list");
		int page = 1;
		if (pageIndex != null && pageIndex > 0) {
			page = pageIndex;
		}
		mv.addObject("list", maSysService.getCardRecordList(page));
		mv.addObject("pageCount", maSysService.getCardRecordListSize());
		mv.addObject("pageIndex", page);
		return mv;
	}

	@RequestMapping("/importCard")
	public ModelAndView importCard(@RequestParam(value = "cardFile", required = false) MultipartFile cardFile) {
		ModelAndView mv = new ModelAndView();
		// MultipartFile是对当前上传的文件的封装，当要同时上传多个文件时，可以给定多个MultipartFile参数
		String path = CommonUtils.getLocationPath();
		String name = cardFile.getOriginalFilename();
		String filepath = path + name;
		// 得到上传的文件的文件名
		if (!cardFile.isEmpty()) {
			try {
				InputStream inputStream = cardFile.getInputStream();
				byte[] bytes = cardFile.getBytes();
				// 文件流写到服务器端
				FileOutputStream outputStream = new FileOutputStream(filepath);
				outputStream.write(bytes);
				inputStream.close();
				outputStream.close();
			} catch (Exception e) {
			}
			maSysService.importExcel(filepath);
		}
		mv.setViewName("redirect:/manage/card_list");
		return mv;
	}
	
	@RequestMapping("/channel_list")
	public ModelAndView channel_list(Integer pageIndex) {
		ModelAndView mv = new ModelAndView("/manage/channel_list");
		int page = 1;
		if (pageIndex != null && pageIndex > 0) {
			page = pageIndex;
		}
		mv.addObject("list", maSysService.getChannelList(page));
		mv.addObject("pageCount", maSysService.getChannelListSize());
		mv.addObject("pageIndex", page);
		return mv;
	}
	
	@RequestMapping("/addchannel")
	public ModelAndView addchannel(String channelName, String channel_id){
		ModelAndView mv = new ModelAndView("redirect:/manage/channel_list");
		if(CommonUtils.isEmptyString(channel_id)){
			String randNum = "";
			 Integer count = 0;
			 do {
			 //6位邀请码
			 randNum = CommonUtils.genRandomNum(6);
			 count = maSysService.getChannelCountByCode(randNum);
			 } while (count > 0);
			ChannelInfo channel = new ChannelInfo();
			channel.setChannelName(channelName);
			channel.setChannelCode(randNum);
			maSysService.insertChannelInfo(channel);
		}else{
			ChannelInfo info = maSysService.getChannelInfoById(CommonUtils.parseLong(channel_id, 0));
			info.setChannelName(channelName);
			maSysService.updateChannelInfo(info);
		}
		return mv;
	}
	
}
