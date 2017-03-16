package com.hz21city.xiangqu.controller.shop;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.pojo.ShopInfo;
import com.hz21city.xiangqu.service.shop.IShShopService;


@Controller
@RequestMapping("/shop")
public class ShLoginController {

	@Resource
	private IShShopService shShopService;
	
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mov = new ModelAndView();
		mov.setViewName("/shop/login");
		return mov;
	}

	@RequestMapping("/login")
	@ResponseBody
	public HashMap<String,Object> login(String loginname,String password,HttpServletRequest request,HttpSession session) {
		HashMap<String,Object> result = new HashMap<String,Object>();
		ShopInfo shopinfo = shShopService.getShopInfoByName(loginname);
		if(shopinfo!=null){
			if(CommonUtils.MD5(password).equals(shopinfo.getPassword())){
				result.put("code",1);
				session.setAttribute("storeid", shopinfo.getId());
			}else{
				result.put("code",500);
				result.put("message","用户名或密码错误!");
			}
		}else{
			result.put("code",500);
			result.put("message","用户名或密码错误!");
		}
		return result;
	}
	
	@RequestMapping("/sessionsync")
	public ModelAndView savesession(String storeid, HttpSession session) {
		ModelAndView mov = new ModelAndView();
		session.setAttribute("storeid", storeid);
		mov.setViewName("redirect:/shop/mission");
		return mov;
	}
}
