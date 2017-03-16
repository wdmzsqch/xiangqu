package com.hz21city.xiangqu.controller.manage;

import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.common.Constants;
import com.hz21city.xiangqu.pojo.Order;
import com.hz21city.xiangqu.pojo.ShopInfo;
import com.hz21city.xiangqu.pojo.UserInfo;
import com.hz21city.xiangqu.service.IUserService;
import com.hz21city.xiangqu.service.manage.IMaOrderService;
import com.hz21city.xiangqu.service.manage.IMaShopService;
import com.hz21city.xiangqu.service.shop.IShOrderService;
import com.hz21city.xiangqu.service.shop.IShShopService;

@Controller
public class MaOrderController extends MaBaseController {
	@Resource
	private IMaOrderService orderSerivce;
	
	@Resource
	private IMaShopService maShopService;
	
	@Resource
	private IUserService userService;
	
	@Resource
	private IShShopService shopService;
	
	@Resource
	private IShOrderService shOrderService;

	@RequestMapping("/order_list")
	public ModelAndView orderList(Integer searchtype, String keywords, String starttime, String endtime, Integer pageIndex, Long storeId, String channel) {
		ModelAndView mv = new ModelAndView("/manage/order_list");
		if (pageIndex == null || pageIndex <= 0) {
			pageIndex = 1;
		}
		Date startDate = null;
		if (!CommonUtils.isEmptyString(starttime)) {
			startDate = CommonUtils.str2Date(starttime, "yyyy-MM-dd");
		}
		Date endDate = null;
		if (!CommonUtils.isEmptyString(endtime)) {
			endDate = CommonUtils.str2Date(endtime, "yyyy-MM-dd");
		}
		mv.addObject("orderList", orderSerivce.getOtherOrderList(searchtype, keywords, startDate, endDate, pageIndex, storeId, channel));
		mv.addObject("pageCount", orderSerivce.getOtherOrderListSize(searchtype, keywords, startDate, endDate, storeId, channel));
		mv.addObject("shoplist", maShopService.getAllShopList());
		// mv.addObject("fileurl", CommonUtils.getFileRootUrl());
		mv.addObject("searchtype", searchtype);
		mv.addObject("keywords", keywords);
		mv.addObject("starttime", starttime);
		mv.addObject("endtime", endtime);
		mv.addObject("pageIndex", pageIndex);
		mv.addObject("storeId", storeId);
		mv.addObject("channel", channel);
		mv.addObject("perCount", orderSerivce.getAllOrderSize());
		return mv;
	}

	@RequestMapping("/order_detail")
	public ModelAndView orderDetail(Long order_id) {
		ModelAndView mv = new ModelAndView("/manage/order_detail");
		if (order_id != null && order_id > 0) {
			mv.addObject("order", orderSerivce.getOrderDetail(order_id));
		} else {
			mv.addObject(Constants.PAGE_ERR_KEY, "订单信息有误，请返回重试");
		}
		return mv;
	}
	
	@RequestMapping("/masendorder")
	@ResponseBody
	public HashMap<String, Object> masendorder(long orderid, String courierNum, String express) {
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
//				OrderGoods goods = order.getGoodsList().get(0);
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
}
