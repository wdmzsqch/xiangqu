package com.hz21city.xiangqu.controller.shop;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.common.Constants;
import com.hz21city.xiangqu.pojo.Order;
import com.hz21city.xiangqu.pojo.ShopInfo;
import com.hz21city.xiangqu.pojo.UserInfo;
import com.hz21city.xiangqu.service.IGoodsService;
import com.hz21city.xiangqu.service.IOrderService;
import com.hz21city.xiangqu.service.IUserService;
import com.hz21city.xiangqu.service.shop.IShOrderService;
import com.hz21city.xiangqu.service.shop.IShShopService;

@Controller
@RequestMapping("/shop")
public class ShOrderController {
	@Resource
	private IOrderService orderSerivce;
	@Resource
	private IShOrderService shOrderService;
	@Resource
	private IUserService userService;
	@Resource
	private IShShopService shopService;
	@Resource
	private IGoodsService goodsService;
	@RequestMapping("/orderlist")
	public ModelAndView orderlist(Integer state, Integer pageIndex, HttpSession session) {
		ModelAndView mov = new ModelAndView();
		long shopId = CommonUtils.parseLong(String.valueOf(session.getAttribute("storeid")), 0);
		if (pageIndex == null)
			pageIndex = 0;
		if (state == null) {
			state = 2;
		}
		List<Order> orderlist = orderSerivce.getOrderListByShopState(state, shopId, pageIndex, Constants.USER_ORDER_PAGESIZE);
		if (orderlist != null && orderlist.size() == Constants.USER_ORDER_PAGESIZE) {
			mov.addObject("loadmore", 1);
		} else {
			mov.addObject("loadmore", 0);
		}
		mov.addObject("state", state);
		mov.addObject("orderlist", orderlist);
		mov.addObject("fileRootUrl", CommonUtils.getFileRootUrl());
		mov.setViewName("/shop/myorder");
		return mov;
	}

	@RequestMapping("/orderlistpush")
	@ResponseBody
	public HashMap<String, Object> orderList(Integer state, Integer pageIndex, HttpServletRequest request, HttpSession session) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		// Order order = orderSerivce.getOrderById(1l);
		// 暂时注释，用户ID
		long shopId = CommonUtils.parseLong(String.valueOf(session.getAttribute("storeid")), 0);
		List<Order> orderlist = orderSerivce.getOrderListByShopState(state, shopId, pageIndex, Constants.USER_ORDER_PAGESIZE);
		if (orderlist != null && orderlist.size() == Constants.USER_ORDER_PAGESIZE) {
			result.put("loadmore", 1);
		} else {
			result.put("loadmore", 0);
		}
		result.put("orderlist", orderlist);
		result.put("fileRootUrl", CommonUtils.getFileRootUrl());
		return result;
	}

	@RequestMapping("/order")
	public ModelAndView order(Integer state, Integer pageIndex, HttpServletRequest request) {
		ModelAndView mov = new ModelAndView();
		// 暂时注释，用户ID
		// long storeid =
		// CommonUtils.parseLong(String.valueOf(session.getAttribute("storeid")),
		// 0);
		if (state == null) {
			state = 1;
		}
		if (pageIndex == null)
			pageIndex = 0;
		List<Order> orderlist = shOrderService.getStoreListByState(state, 1, pageIndex, Constants.USER_ORDER_PAGESIZE);
		if (orderlist != null && orderlist.size() == Constants.USER_ORDER_PAGESIZE) {
			mov.addObject("loadmore", 1);
		} else {
			mov.addObject("loadmore", 0);
		}
		mov.addObject("state", state);
		mov.addObject("orderlist", orderlist);
		mov.addObject("fileRootUrl", CommonUtils.getFileRootUrl());
		mov.setViewName("/shop/myorder");
		return mov;
	}

	@RequestMapping("/orderpush")
	@ResponseBody
	public HashMap<String, Object> orderpush(Integer state, Integer pageIndex, HttpServletRequest request) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		// 暂时注释，用户ID
		// long userid = (long) session.getAttribute("userid");
		List<Order> orderlist = shOrderService.getStoreListByState(state, 1, pageIndex, Constants.USER_ORDER_PAGESIZE);
		if (orderlist != null && orderlist.size() == Constants.USER_ORDER_PAGESIZE) {
			result.put("loadmore", 1);
		} else {
			result.put("loadmore", 0);
		}
		result.put("orderlist", orderlist);
		result.put("fileRootUrl", CommonUtils.getFileRootUrl());
		return result;
	}

	@RequestMapping("/orderdetail")
	public ModelAndView orderdetail(long orderid) {
		ModelAndView mov = new ModelAndView();
		Order order = shOrderService.getOrderById(orderid);
		mov.addObject("order", order);
		mov.addObject("fileRootUrl", CommonUtils.getFileRootUrl());
		return mov;
	}

	@RequestMapping("/sendorder")
	@ResponseBody
	public HashMap<String, Object> sendorder(long orderid, String courierNum, String express) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		Order order = shOrderService.getOrderById(orderid);
		UserInfo userinfo = userService.getUserInfoById(order.getUserId());
		ShopInfo shopinfo = shopService.getShopInfoById(order.getStoreId());
		if (order.getState() == 2) {
			order.setCourierNum(courierNum);
			order.setExpress(express);
			order.setState(3);
			shOrderService.updateOrder(order);
			//发货成功后给买家发信息
			if(!CommonUtils.isEmptyString(shopinfo.getPhone())){
//				GoodsInfo goodsinfo = goodsService.getGoodsInfo(order.getGoodsList().get(0).getGoodsId());
				if(order.getGoodsList().get(0).getIsServe() == null || order.getGoodsList().get(0).getIsServe() == 0){
					String sms = "购买的" + order.getGoodsList().get(0).getName()+"已经发货";
					if(!CommonUtils.isEmptyString(order.getExpress())){
						sms += ","+order.getExpress();
					}
					if(!CommonUtils.isEmptyString(order.getCourierNum())){
						sms += ",单号："+order.getCourierNum();
					}
					sms += "。商家咨询电话"+shopinfo.getPhone();
					CommonUtils.YMsendSms(userinfo.getMoblie(), sms);
				}else{
					CommonUtils.YMsendSms(userinfo.getMoblie(), "您兑换/购买的" + order.getGoodsList().get(0).getName()+"我们已经给你发货，商家电话"+shopinfo.getPhone());
				}
			}else{
				if(order.getGoodsList().get(0).getIsServe() == null || order.getGoodsList().get(0).getIsServe() == 0){
					String sms = "购买的" + order.getGoodsList().get(0).getName()+"已经发货";
					if(!CommonUtils.isEmptyString(order.getExpress())){
						sms += ","+order.getExpress();
					}
					if(!CommonUtils.isEmptyString(order.getCourierNum())){
						sms += ",单号："+order.getCourierNum();
					}
					CommonUtils.YMsendSms(userinfo.getMoblie(), sms);
				}else{
					CommonUtils.YMsendSms(userinfo.getMoblie(), "您兑换/购买的" + order.getGoodsList().get(0).getName()+"我们已经给你发货！");
				}
			}
			result.put("code", 1);
		} else {
			result.put("code", 500);
			result.put("message", "订单数据错误!");
		}
		return result;
	}

	@RequestMapping("/order_check")
	public ModelAndView orderCheck(String code, Integer orderid, HttpSession session) {
		long shopId = CommonUtils.parseLong(String.valueOf(session.getAttribute("storeid")), 0);
		ModelAndView mov = new ModelAndView("./shop/ordercheckresult");
		mov.addObject("code", code);
		mov.addObject("fileRootUrl", CommonUtils.getFileRootUrl());
		Order order = shOrderService.checkServiceOrder(code);
		if (order != null) {
			if (order.getStoreId() == shopId) {
				mov.addObject("order", order);
				mov.addObject("ostate", order.getState());
				if(order.getState()!=null&&order.getState()==3){
					order.setState(4);
					shOrderService.updateOrder(order);
				}
			} else {
				mov.addObject("errMsg", "非本店铺商品");
			}
		}
		return mov;
	}
}
