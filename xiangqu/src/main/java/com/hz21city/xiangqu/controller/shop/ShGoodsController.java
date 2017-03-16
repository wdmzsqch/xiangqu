package com.hz21city.xiangqu.controller.shop;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hz21city.xiangqu.pojo.Order;
import com.hz21city.xiangqu.service.IOrderService;

@Controller
@RequestMapping("/shop/goods")
public class ShGoodsController {

	@Resource
	private IOrderService orderSerivce;

//	@RequestMapping("/orderlist")
//	@ResponseBody
	public HashMap<String, Object> orderList(HttpServletRequest request) {
		Order order = orderSerivce.getOrderById(1l);
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("order", order);
		return result;
	}

	// @RequestMapping("/orderdetail")
	// @ResponseBody
	public HashMap<String, Object> orderDetail(HttpServletRequest request) {
		Order order = orderSerivce.getOrderById(1l);
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("order", order);
		return result;
	}

}
