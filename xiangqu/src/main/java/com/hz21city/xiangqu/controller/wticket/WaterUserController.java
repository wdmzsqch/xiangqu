package com.hz21city.xiangqu.controller.wticket;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.common.Constants;
import com.hz21city.xiangqu.pojo.AdminInfo;
import com.hz21city.xiangqu.pojo.Area;
import com.hz21city.xiangqu.pojo.WOrderInfo;
import com.hz21city.xiangqu.pojo.WTicketInfo;
import com.hz21city.xiangqu.pojo.WUserInfo;
import com.hz21city.xiangqu.service.manage.IMaUserService;
import com.hz21city.xiangqu.service.wticket.IWticketService;

@Controller
@RequestMapping("/wticket")
public class WaterUserController {
	
	@Resource
	private IMaUserService maUserService;
	
	@Resource
	private IWticketService wticketService;

	@RequestMapping("/wlogin")
	public ModelAndView wlogin(HttpSession session, String username, String pwd){
		ModelAndView mv = new ModelAndView();
		if (CommonUtils.isEmptyString(username) || CommonUtils.isEmptyString(pwd)) {
			mv.setViewName("/wticket/login");
			mv.addObject(Constants.PAGE_ERR_KEY, "请输入用户名、密码和验证码");
			return mv;
		}
		AdminInfo adminInfo = maUserService.getAdminInfo(username, CommonUtils.MD5(pwd));
		if (adminInfo == null) {
			mv.setViewName("/wticket/login");
			mv.addObject(Constants.PAGE_ERR_KEY, "用户名或者密码错误");
		}else if(adminInfo.getType() != -1){
			mv.setViewName("/map/login");
			mv.addObject(Constants.PAGE_ERR_KEY, "管理员没有此权限");
		} else {
			mv.setViewName("redirect:/wticket/w_user_list");
			session.setAttribute("adminWticketUserId", adminInfo.getId());
		}
		return mv;
	}
	
	/**
	 * 获取用户列表
	 * @param keywords
	 * @param pageIndex
	 * @param searchType
	 * @return
	 */
	@RequestMapping("/w_user_list")
	public ModelAndView w_user_list(String keywords, Integer pageIndex, Integer searchType){
		ModelAndView mv = new ModelAndView("/wticket/user_list");
		int page = 1;
		if (pageIndex != null && pageIndex > 0) {
			page = pageIndex;
		}
		List<WUserInfo> userlist = wticketService.getWUserListByPage(keywords, page, searchType);
		mv.addObject("userlist", userlist);
		mv.addObject("pageCount", wticketService.getWUserListSize(keywords, searchType));
		mv.addObject("searchType", searchType);
		mv.addObject("pageIndex", page);
		mv.addObject("keywords", keywords);
		mv.addObject("userCount", wticketService.getAllWUserCount());
		return mv;
	}
	
	/**
	 * 我的水票
	 * @param userId
	 * @param pageIndex
	 * @return
	 */
	@RequestMapping("/my_wticket")
	public ModelAndView my_wticket(Long userId,Integer pageIndex){
		ModelAndView mv = new ModelAndView("/wticket/my_wticked");
		int page = 1;
		if (pageIndex != null && pageIndex > 0) {
			page = pageIndex;
		}
		List<WTicketInfo> list = wticketService.getMyTicketListByPage(page, userId);
		mv.addObject("list", list);
		mv.addObject("pageCount", wticketService.getMyTicketListSize(userId));
		mv.addObject("pageIndex", page);
		mv.addObject("myCount", wticketService.getAllMyTicketCount(userId));
		mv.addObject("useCount", wticketService.getAllMyUseTicketCount(userId));
		mv.addObject("noUseCount", wticketService.getAllMyNouseWTicketCount(userId));
		return mv;
	}
	
	/**
	 * 跳转到新建或编辑客户
	 * @param userId
	 * @return
	 */
	@RequestMapping("/user_insert")
	public ModelAndView user_insert(Long userId){
		ModelAndView mv = new ModelAndView("/wticket/user_insert");
		//省
		List<Area> prolist = wticketService.getAreaListByParentid(Long.valueOf(0));
		mv.addObject("prolist", prolist);
		if(userId != null){
			WUserInfo userinfo = wticketService.getWUserInfo(userId);
			if(userinfo.getProvice() != null && userinfo.getProvice() != 0){
				//根据省取city
				List<Area> citylist = wticketService.getAreaListByParentid(userinfo.getProvice());
				mv.addObject("citylist", citylist);
				if(userinfo.getCity() != null && userinfo.getCity() != 0){
					//根据城市取area
					List<Area> arealist = wticketService.getAreaListByParentid(userinfo.getCity());
					mv.addObject("arealist", arealist);
				}else{
					if(citylist != null && citylist.size() > 0){
						//根据城市取area
						List<Area> arealist = wticketService.getAreaListByParentid(citylist.get(0).getId());
						mv.addObject("arealist", arealist);
					}
				}
			}else{
				if(prolist != null && prolist.size() > 0){
					//根据省取city
					List<Area> citylist = wticketService.getAreaListByParentid(prolist.get(0).getId());
					mv.addObject("citylist", citylist);
					if(citylist != null && citylist.size() > 0){
						//根据城市取area
						List<Area> arealist = wticketService.getAreaListByParentid(citylist.get(0).getId());
						mv.addObject("arealist", arealist);
					}
				}
			}
			mv.addObject("user", userinfo);
		}else{
		}
		
		return mv;
	}
	
	/**
	 * 根据父级取城市列表
	 * @param parentid
	 * @param type
	 * @param response
	 */
	@RequestMapping("/selectWaterArea")
	public void selectWaterArea(Long parentid, Integer type, HttpServletResponse response){
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<Area> list = wticketService.getAreaListByParentid(parentid);
		if (type == 1) {
			if (list != null && list.size() > 0) {
				List<Area> arealist = wticketService.getAreaListByParentid(list.get(0).getId());
				result.put("arealist", arealist);
			}
			result.put("citylist", list);
			CommonUtils.setMaptoJson(response, result);
		} else {
			result.put("arealist", list);
			CommonUtils.setMaptoJson(response, result);
		}
	}
	
	/**
	 * 添加或修改用户
	 * @param id
	 * @param name
	 * @param phone
	 * @param province
	 * @param city
	 * @param area
	 * @param address
	 * @return
	 */
	@RequestMapping("/wuser_edit")
	public void wuser_edit(Long id, String name, String phone, Long provice, Long city, Long area, String address, String mark, Integer wtcount, Integer wucount, HttpServletResponse response){
		WUserInfo user = wticketService.getWUserInfoByPhone(phone);
		if(id == null){
			if(user == null){
				WUserInfo info = new WUserInfo();
				info.setName(name);
				info.setPhone(phone);
				info.setProvice(provice);
				info.setCity(city);
				info.setArea(area);
				info.setAddress(address);
				info.setMark(mark);
				if(wtcount == null){
					info.setWtcount(0);
				}else{
					info.setWtcount(wtcount);
				}
				if(wucount == null){
					info.setWucount(0);
				}else{
					info.setWucount(wucount);
				}
				wticketService.insertWUser(info);
//				WUserInfo userinfo = wticketService.getWUserInfoByPhone(phone);
//				WOrderInfo record = new WOrderInfo();
//				record.setOrder_time(new Date());
//				record.setWuserId(userinfo.getId());
//				if(wucount == null){
//					record.setOrtickernum(0);
//				}else{
//					record.setOrtickernum(wucount);
//				}
//				wticketService.insertWOrderInfo(record);
				CommonUtils.setResponseStr("success", response);
			}else{
				CommonUtils.setResponseStr("error", response);
			}
		}else{
			if(user != null && user.getId() == id){
				WUserInfo info = wticketService.getWUserInfo(id);
				if(wucount == null){
					wucount = 0;
				}
				if(wucount != info.getWucount()){
					WOrderInfo record = new WOrderInfo();
					record.setOrder_time(new Date());
					record.setWuserId(id);
					record.setOrtickernum(wucount-info.getWucount());
					wticketService.insertWOrderInfo(record);
				}
				info.setName(name);
				info.setPhone(phone);
				info.setProvice(provice);
				info.setCity(city);
				info.setArea(area);
				info.setAddress(address);
				info.setMark(mark);
				if(wtcount == null){
					info.setWtcount(0);
				}else{
					info.setWtcount(wtcount);
				}
				info.setWucount(wucount);
				wticketService.updateWUser(info);
				
				CommonUtils.setResponseStr("success", response);
			}else{
				CommonUtils.setResponseStr("error", response);
			}
		}
	}
	
	/**
	 * 订水记录
	 * @param keywords
	 * @param pageIndex
	 * @param searchType
	 * @return
	 */
	@RequestMapping("/orderw_list")
	public ModelAndView orderw_list(String keywords, Integer pageIndex, Integer searchType, String starttime, String endtime){
		ModelAndView mv = new ModelAndView("/wticket/orderw_list");
		int page = 1;
		if (pageIndex != null && pageIndex > 0) {
			page = pageIndex;
		}
		Date startDate = null;
		if (!CommonUtils.isEmptyString(starttime)) {
			startDate = CommonUtils.str2Date(starttime, "yyyy-MM-dd");
		}
		Date endDate = null;
		if (!CommonUtils.isEmptyString(endtime)) {
			endDate = CommonUtils.str2Date(endtime, "yyyy-MM-dd");
		}
		List<WOrderInfo> list = wticketService.getWOrderListByPage(keywords, page, searchType, startDate, endDate);
		mv.addObject("list", list);
		mv.addObject("pageCount", wticketService.getWOrderListSize(keywords, searchType, startDate, endDate));
		mv.addObject("keywords", keywords);
		mv.addObject("pageIndex", page);
		mv.addObject("searchType", searchType);
		mv.addObject("starttime", starttime);
		mv.addObject("endtime", endtime);
		mv.addObject("orderCount", wticketService.getSumWOrderCount(keywords, searchType, startDate, endDate));
		return mv;
	}
	
	/**
	 * 水票列表
	 * @param pageIndex
	 * @param numstart
	 * @param numend
	 * @param state
	 * @return
	 */
	@RequestMapping("/wticket_list")
	public ModelAndView wticket_list(Integer pageIndex, String ticketNum, Integer state){
		ModelAndView mv = new ModelAndView();
		int page = 1;
		if (pageIndex != null && pageIndex > 0) {
			page = pageIndex;
		}
		List<WTicketInfo> list = wticketService.getWTicketListByPage(page, state, ticketNum);
		mv.addObject("list", list);
		mv.addObject("pageCount", wticketService.getWTicketListSize(state, ticketNum));
		mv.addObject("pageIndex", page);
		mv.addObject("ticketNum", ticketNum);
		mv.addObject("state", state);
		mv.addObject("ticketCount", wticketService.getAllWTicketCount());
		mv.addObject("useCount", wticketService.getAllUseWTicketCount());
		mv.addObject("sellCount", wticketService.getAllSellWTicketCount());
		return mv;
	}
	
	/**
	 * 批量已使用
	 * @param id
	 * @return
	 */
	@RequestMapping("/makeTicketUse")
	public ModelAndView makeTicketUse(Long[] id){
		ModelAndView mv = new ModelAndView();
		if(id != null && id.length > 0){
			for (Long long1 : id) {
				WTicketInfo info = wticketService.getWTicketInfo(long1);
				info.setUseState(1);
				wticketService.updateWTicketInfo(info);
			}
		}
		mv.setViewName("redirect:/wticket/wticket_list");
		return mv;
	}
	
	/**
	 * 批量已售出
	 * @param id
	 * @return
	 */
	@RequestMapping("/makeTicketSell")
	public ModelAndView makeTicketSell(Long[] id){
		ModelAndView mv = new ModelAndView();
		if(id != null && id.length > 0){
			for (Long long1 : id) {
				WTicketInfo info = wticketService.getWTicketInfo(long1);
				info.setSellState(1);
				wticketService.updateWTicketInfo(info);
			}
		}
		mv.setViewName("redirect:/wticket/wticket_list");
		return mv;
	}
	
	@RequestMapping("/importFile")
	public ModelAndView importFile(@RequestParam(value = "csvfile", required = false) MultipartFile csvfile){
		ModelAndView mv = new ModelAndView();
		if (!csvfile.isEmpty()) {
			List<String> dataList = wticketService.importFile(csvfile);
			if(dataList != null && dataList.size() > 0){
				for (String string : dataList) {
					String[] list = string.split(",");
					if(list != null && list.length > 0){
						WTicketInfo info = wticketService.getWTicketByTickNum(list[0]);
						if(info == null){
							WTicketInfo ticket = new WTicketInfo();
							ticket.setTicketNum(list[0]);
							ticket.setUseState(CommonUtils.parseInt(list[1], 0));
							ticket.setSellState(CommonUtils.parseInt(list[2], 0));
							wticketService.insertWTicketInfo(ticket);
						}
					}
				}
			}
		}
		mv.setViewName("redirect:/wticket/wticket_list");
		return mv;
	}
	
	/**
	 * 跳转到验证水票页面
	 * @param ticketNum
	 * @return
	 */
	@RequestMapping("/verification")
	public ModelAndView verification(String ticketNum){
		ModelAndView mv = new ModelAndView();
		mv.addObject("ticketNum", ticketNum);
		mv.setViewName("/wticket/w_verification");
		return mv;
	}
	
	/**
	 * 将水票变成已使用
	 * @return
	 */
	@RequestMapping("/check_ticket")
	public ModelAndView check_ticket(String ticketNum){
		ModelAndView mv = new ModelAndView();
		WTicketInfo info = wticketService.getWTicketByTickNum(ticketNum);
		if(info == null){
			mv.setViewName("/wticket/w_verification_error");
		}else{
			if(info.getUseState() != 1){
				info.setUseState(1);
				wticketService.updateWTicketInfo(info);
				mv.setViewName("/wticket/w_verification_success");
			}else{
				mv.setViewName("/wticket/w_verification_error");
			}
		}
		return mv;
	}
	
	@RequestMapping("/clearwsession")
	public ModelAndView clearwsession(HttpSession session) {
		ModelAndView mv = new ModelAndView("/wticket/login");
		session.removeAttribute("adminWticketUserId");
		return mv;
	}
	
	@RequestMapping("/createTickCode")
	public ModelAndView createTickCode(){
		ModelAndView mv = new ModelAndView();
		List<WTicketInfo> list = wticketService.getNoUseTicketList();
		if(list != null && list.size() > 0){
			for (WTicketInfo wTicketInfo : list) {
				CommonUtils.creatWaterQrcode("http://www.xiangqu100.com/xiangqu/wticket/verification?ticketNum="+wTicketInfo.getTicketNum(), wTicketInfo.getTicketNum());
			}
		}
		mv.setViewName("redirect:/wticket/wticket_list");
		return mv;
	}
}
