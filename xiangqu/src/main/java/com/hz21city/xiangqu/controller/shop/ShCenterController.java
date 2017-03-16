package com.hz21city.xiangqu.controller.shop;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.controller.manage.MaBaseController;
import com.hz21city.xiangqu.pojo.ShopInfo;
import com.hz21city.xiangqu.pojo.SystemMessage;
import com.hz21city.xiangqu.service.shop.IShMissionService;
import com.hz21city.xiangqu.service.shop.IShShopService;

@Controller
@RequestMapping("/shop")
public class ShCenterController extends MaBaseController{
	
	@Resource
	private IShShopService shShopService;
	
	@Resource
	private IShMissionService shMissionService;
	
	/*---页面---*/
	
	@RequestMapping("/ucenter")
	public ModelAndView center(HttpServletRequest request,HttpSession session){
		ModelAndView mov = new ModelAndView();
		
			long id = CommonUtils.parseLong(String.valueOf(session.getAttribute("storeid")), 0);
			ShopInfo shopinfo = shShopService.getShopInfoById(id);
			
		mov.addObject("shopinfo", shopinfo);
		mov.addObject("fileRootUrl", CommonUtils.getFileRootUrl());
		return mov;
	} 
	
	@RequestMapping("/setting")
	public ModelAndView setting(HttpServletRequest request,HttpSession session, Integer isapp){
		ModelAndView mov = new ModelAndView();
		mov.addObject("isapp", isapp);
		return mov;
	} 
	
	@RequestMapping("/password")
	public ModelAndView password(HttpServletRequest request,HttpSession session){
		ModelAndView mov = new ModelAndView();
		return mov;
	}
	
	@RequestMapping("/shopdata")
	public ModelAndView shopdata(HttpServletRequest request,HttpSession session){
		ModelAndView mov = new ModelAndView();
		
		long id = CommonUtils.parseLong(String.valueOf(session.getAttribute("storeid")), 0);
		ShopInfo shopinfo = shShopService.getShopInfoById(id);
		
	mov.addObject("shopinfo", shopinfo);
	mov.addObject("fileRootUrl", CommonUtils.getFileRootUrl());
	return mov;
	}
	
	@RequestMapping("/message")
	public ModelAndView message(HttpServletRequest request,HttpSession session){
		ModelAndView mov = new ModelAndView();
		
		List<SystemMessage> shopmessage = shShopService.getShopSysMessage();
		mov.addObject("shopmessage", shopmessage);
		
		return mov;
	}
	
	@RequestMapping("/messagedetail")
	public ModelAndView messagedetail(HttpServletRequest request,HttpSession session, long id){
		ModelAndView mov = new ModelAndView();
		if (id <= 0) {
			System.out.println("id错误");
			return null;
		}
		SystemMessage shopmessage = shShopService.getSysMessageById(id);
		mov.addObject("shopmessage", shopmessage);
		mov.setViewName("/shop/messagedetail");
		return mov;
	}
	
	/**
	 * 今日明细
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/bill")
	public ModelAndView bill(HttpServletRequest request,HttpSession session){
		ModelAndView mov = new ModelAndView();
		long id = CommonUtils.parseLong(String.valueOf(session.getAttribute("storeid")), 0);
		//商店今日已投放金额
		mov.addObject("totalMoney", shShopService.getDailyTotalMoney(id));
		//商店今日总点击量
		mov.addObject("totalCont",shShopService.getDailyTotalCont(id));
		//商店今日剩余可投放金额
		mov.addObject("LastMoney", shShopService.getDailyLastMoney(id));
		//商店今日剩余可投放
		mov.addObject("RamainCount", shShopService.getDailyRamainCount(id));
		//商店今日任务数
		mov.addObject("missionCount", shShopService.getDailyMissionCount(id));
		//商店今日点击量
		mov.addObject("incomeRecordCount", shShopService.getDailIncomRecordCount(id));
		//商店今日任务
		mov.addObject("missionList", shShopService.getDailyMissionList(id));
		mov.addObject("time", new Date());
		mov.addObject("fileRootUrl", CommonUtils.getFileRootUrl());
		return mov;
	} 
	
	/**
	 * 月总明细
	 * @param session
	 * @param month
	 * @return
	 */
	@RequestMapping("/month_bill")
	public ModelAndView month_bill(HttpSession session, String month){
		ModelAndView mov = new ModelAndView();
		long id = CommonUtils.parseLong(String.valueOf(session.getAttribute("storeid")), 0);
		Date date = new Date();
		if (!CommonUtils.isEmptyString(month)) {
			date = CommonUtils.str2Date(month, "yyyy-MM");
		} else {
			Calendar c = Calendar.getInstance();
			c.set(Calendar.DAY_OF_MONTH, 1);
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			date = c.getTime();
		}
		//商店当月已投放金额
		mov.addObject("totalMoney", shShopService.getMonthTotalMoney(date, id));
		//商店今日总点击量
		mov.addObject("totalCont",shShopService.getMonthTotalCont(date, id));
		//商店当月剩余可投放金额
		mov.addObject("LastMoney", shShopService.getMonthLastMoney(date, id));
		//商店当月剩余可投放金额
		mov.addObject("RamainCount", shShopService.getMonthRamainCount(date, id));
		//商店当月任务数
		mov.addObject("missionCount", shShopService.getMonthMissionCount(date, id));
		//商店当月点击量
		mov.addObject("incomeRecordCount", shShopService.getMonthIncomRecordCount(date, id));		
		//商店当月任务
		mov.addObject("missionList", shShopService.getMothMissionList(date, id));
		mov.addObject("time", new Date());
		mov.addObject("month", CommonUtils.time2Str(date, "yyyy-MM"));
		mov.addObject("fileRootUrl", CommonUtils.getFileRootUrl());
		return mov;
	}
	
	
	/*---功能---*/
	@RequestMapping("/logout")
	public void logout(HttpServletRequest request,HttpSession session){
		session.setAttribute("storeid", null);
	}
	
	@RequestMapping("/changepwd")
	@ResponseBody
	public HashMap<String,Object> changepwd(String oldpwd, String newpwd, HttpServletRequest request,HttpSession session){
		HashMap<String,Object> result = new HashMap<String,Object>();
		long id = CommonUtils.parseLong(String.valueOf(session.getAttribute("storeid")), 0);
		ShopInfo shopinfo = shShopService.getShopInfoById(id);
		
		if (shopinfo!=null) {
			if(CommonUtils.MD5(oldpwd).equals(shopinfo.getPassword())){
				shopinfo.setPassword(CommonUtils.MD5(newpwd));
				shShopService.changePwdByShopInfo(shopinfo);
				
				result.put("code",1);
				result.put("message","修改成功");
			}else{
				result.put("code",500);
				result.put("message","原始密码错误!");
			}
		}else{
			result.put("code",500);
			result.put("message","服务器正忙，请稍后再试!");
		}
		return result;
	}
	
	@RequestMapping("/changeninfo")
	public ModelAndView changeninfo(MultipartFile file, String name, String address, HttpServletRequest request,HttpSession session){
		
		ModelAndView mov = new ModelAndView();
		
		long id = CommonUtils.parseLong(String.valueOf(session.getAttribute("storeid")), 0);
		ShopInfo shopinfo = new ShopInfo();
		
		if (file != null && !file.isEmpty()) {
			String imgPath = getFilePathString(file);
			
			shopinfo.setPic(imgPath);
		}
		
		shopinfo.setId(id);
		shopinfo.setCompanyName(name);
		shopinfo.setAddress(address);
		
		shShopService.updateByPrimaryKeySelective(shopinfo);
		
		ShopInfo newshopinfo = shShopService.getShopInfoById(id);
		mov.addObject("shopinfo", newshopinfo);
		mov.addObject("fileRootUrl", CommonUtils.getFileRootUrl());
		
//		mov.addObject("err", "");
		mov.setViewName("/shop/shopdata");
		return mov;
	}
	
	@RequestMapping("/clearsession")
	public void clearsession(HttpServletResponse response, HttpSession session) {
		session.removeAttribute("storeid");
		CommonUtils.setResponseStr("success", response);
	}
}
