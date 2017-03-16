package com.hz21city.xiangqu.controller;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daoshun.exception.CustomException;
import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.common.Constants;
import com.hz21city.xiangqu.pojo.ShopInfo;
import com.hz21city.xiangqu.service.shop.IShShopService;

@Controller
@RequestMapping("/api/shop")
public class ApiShopController extends ApiBaseController {
	
	@Resource
	private IShShopService shShopService;
	/**
	 * 登录
	 * 
	 * @param request
	 * @param rep
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/login", produces = "application/json; charset=UTF-8")
	public String login(HttpServletRequest request, HttpServletResponse rep, HttpSession session) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			request.setCharacterEncoding("utf-8");
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			String client_id = request.getParameter("client_id");
			CommonUtils.validateEmpty(userName);
			CommonUtils.validateEmpty(password);
			ShopInfo shopinfo = shShopService.login(userName, password);
			if(!CommonUtils.isEmptyString(client_id)){
				shopinfo.setClient_id(client_id);
				shShopService.updateShop(shopinfo);
			}
			map.put("shop", shopinfo);
			map.putAll(Constants.SUCCESS_RESULT_MAP);
			return gson.toJson(map);
		} catch (CustomException e) {
			return e.getMessage();
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.EXCEPTION;
		}
	}

}
