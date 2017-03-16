package com.hz21city.xiangqu.controller;

import java.io.IOException;
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
import org.springframework.web.servlet.ModelAndView;

import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.common.Constants;
import com.hz21city.xiangqu.pojo.GoodsInfo;
import com.hz21city.xiangqu.pojo.IncomeRecord;
import com.hz21city.xiangqu.pojo.Order;
import com.hz21city.xiangqu.pojo.OrderGoods;
import com.hz21city.xiangqu.pojo.ShopInfo;
import com.hz21city.xiangqu.pojo.UserAddress;
import com.hz21city.xiangqu.pojo.UserInfo;
import com.hz21city.xiangqu.service.IGoodsService;
import com.hz21city.xiangqu.service.IOrderService;
import com.hz21city.xiangqu.service.IUserAddressService;
import com.hz21city.xiangqu.service.IUserService;
import com.hz21city.xiangqu.service.shop.IShShopService;

@Controller
@RequestMapping("/user")
public class OrderController {

	@Resource
	private IOrderService orderSerivce;
	@Resource
	private IUserAddressService useraddressService;
	@Resource
	private IGoodsService goodsService;
	@Resource
	private IUserService userService;
	@Resource
	private IShShopService shopService;

	@RequestMapping("/order")
	public ModelAndView order(Integer state, Integer pageIndex, HttpSession session) {
		ModelAndView mov = new ModelAndView();
		// 暂时注释，用户ID
		long userid = CommonUtils.parseLong(String.valueOf(session.getAttribute("userid")), 0);
		if (pageIndex == null)
			pageIndex = 0;
		List<Order> orderlist = orderSerivce.getOrderListByState(state, userid, pageIndex, Constants.USER_ORDER_PAGESIZE);
		if (orderlist != null && orderlist.size() == Constants.USER_ORDER_PAGESIZE) {
			mov.addObject("loadmore", 1);
		} else {
			mov.addObject("loadmore", 0);
		}
		mov.addObject("state", state);
		mov.addObject("orderlist", orderlist);
		mov.addObject("fileRootUrl", CommonUtils.getFileRootUrl());
		mov.setViewName("/user/myorder");
		return mov;
	}

	@RequestMapping("/orderpush")
	@ResponseBody
	public HashMap<String, Object> orderpush(Integer state, Integer pageIndex, HttpServletRequest request, HttpSession session) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		// 暂时注释，用户ID
		long userid = CommonUtils.parseLong(String.valueOf(session.getAttribute("userid")), 0);
		List<Order> orderlist = orderSerivce.getOrderListByState(state, userid, pageIndex, Constants.USER_ORDER_PAGESIZE);
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
		Order order = orderSerivce.getOrderById(orderid);
		if (order.getGoodsList() != null && order.getGoodsList().size() > 0) {
			order.setPaytype(order.getGoodsList().get(0).getPaytype());
		}
		mov.addObject("order", order);
		mov.addObject("fileRootUrl", CommonUtils.getFileRootUrl());
		return mov;
	}

	@RequestMapping("/createorder")
	@ResponseBody
	public HashMap<String, Object> cerateorder(String comment, Long goodsid, int buynum, Long useraddressid, Long shareuserid, String property, String channelCode, HttpSession session) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		// 暂时注释，用户ID
		long userid = CommonUtils.parseLong(String.valueOf(session.getAttribute("userid")), 0);
		GoodsInfo goods = goodsService.getGoodsInfo(goodsid);
		if (goods.getStock() < buynum) {
			result.put("code", 500);
			result.put("message", "库存不足");
			return result;
		}
		if (goods.getLimitNum() != null && goods.getLimitNum() > 0) {
			if (buynum > goods.getLimitNum()) {
				result.put("code", 500);
				result.put("message", "此商品限购" + goods.getLimitNum() + "份");
				return result;
			}
			int count = orderSerivce.getOrderCount(userid, goods.getId());
			if (count + buynum > goods.getLimitNum()) {
				result.put("code", 500);
				result.put("message", "此商品限购" + goods.getLimitNum() + "份，您已经购买过" + count + "份");
				return result;
			}
		}
		OrderGoods ordergoods = new OrderGoods();
		ordergoods.setBackMoney(goods.getBackMoney());
		ordergoods.setDescription(goods.getDescription());
		ordergoods.setGoodsId(goods.getId());
		ordergoods.setName(goods.getName());
		ordergoods.setNum(buynum);
		ordergoods.setOriginPrice(goods.getOriginPrice());
		ordergoods.setPic(goods.getMianPic());
		ordergoods.setPrice(goods.getPrice());
		ordergoods.setStoreId(goods.getStoreId());
		ordergoods.setProperty(property);
		ordergoods.setIsServe(goods.getIsServe());
		Order addorder = new Order();
		if (useraddressid != null && useraddressid > 0) {
			UserAddress useraddress = useraddressService.getAddress(useraddressid);
			addorder.setConsignee(useraddress.getConsignee());
			addorder.setPhone(useraddress.getPhone());
			addorder.setAddress(useraddress.getAddress());
		}
		addorder.setOrderNo(CommonUtils.makeOrderNo());
		addorder.setComment(comment);
		addorder.setOrderTime(new Date());
		addorder.setState(1);
		addorder.setTotalPrice(goods.getPrice() * buynum);
		addorder.setUserId(userid);
		addorder.setStoreId(goods.getStoreId());
		addorder.setShareuserId(shareuserid);
		addorder.setChannelCode(channelCode);
		long orderid = orderSerivce.addOrder(addorder);
		ordergoods.setOrderId(orderid);
		orderSerivce.addOrderGoods(ordergoods);
		result.put("orderid", orderid);
		result.put("order", addorder);
		result.put("code", 1);
		return result;
	}

	@RequestMapping("/pointpay")
	@ResponseBody
	public HashMap<String, Object> pointpay(long orderid, HttpSession session) throws IOException {
		HashMap<String, Object> result = new HashMap<String, Object>();
		// 暂时注释，用户ID
		long userid = CommonUtils.parseLong(String.valueOf(session.getAttribute("userid")), 0);
		Order order = orderSerivce.getOrderById(orderid);
		if (order != null && order.getState() == 1) {
			UserInfo userinfo = userService.getUserInfoById(userid);
			if (order.getTotalPrice() <= userinfo.getBalance()) {
				List<OrderGoods> ordergoods = orderSerivce.getOrderGoodsList(order.getId());
				userinfo.setBalance(userinfo.getBalance() - order.getTotalPrice());
				userService.updateUser(userinfo);
				order.setPayTime(new Date());
				ShopInfo shopinfo = shopService.getShopInfoById(order.getStoreId());
				if (ordergoods != null && ordergoods.size() > 0) {
					if (ordergoods.get(0).getIsServe() == 0) {
						order.setState(2);
						// 购买成功后给买家发信息
						CommonUtils.YMsendSms(userinfo.getMoblie(), "您已成功兑换/购买" + ordergoods.get(0).getName() + "我们将尽快处理您的订单。感谢您的惠顾！商家电话" + shopinfo.getPhone());
					} else {
						order.setState(3);
						Order randorder = null;
						String randNum = null;
						do {
							randNum = CommonUtils.genRandomNum(8);
							randorder = orderSerivce.getOrderByCode(randNum);
						} while (randorder != null);
						order.setCode(randNum);
						String codepic = CommonUtils.creatQrcode("com.hz21city.xiangqu_goods_" + order.getId() + "_" + randNum);
						order.setCodepic(codepic);
					}
				}
				orderSerivce.updateOrder(order);
				// 购买成功后给商家发信息
				CommonUtils.YMsendSms(shopinfo.getMobile(), "您有新的订单，订单编号为" + order.getOrderNo());
				for (OrderGoods ordergood : ordergoods) {
					IncomeRecord incomerecord = new IncomeRecord();
					incomerecord.setFromUserId(0l);
					incomerecord.setUserId(userinfo.getId());
					incomerecord.setType(3);
					incomerecord.setRelativeId(ordergood.getGoodsId());
					incomerecord.setIncome((-1) * ordergood.getPrice() * ordergood.getNum());
					incomerecord.setTime(new Date());
					orderSerivce.addIncome(incomerecord);
				}
				if (order.getShareuserId() != null && order.getShareuserId() > 0) {
					float backmoney = 0f;
					for (OrderGoods ordergood : ordergoods) {
						if (ordergood.getBackMoney() != null && ordergood.getBackMoney() > 0) {
							IncomeRecord incomerecord = new IncomeRecord();
							incomerecord.setFromUserId(userinfo.getId());
							incomerecord.setUserId(order.getShareuserId());
							incomerecord.setType(2);
							incomerecord.setRelativeId(ordergood.getGoodsId());
							incomerecord.setIncome(ordergood.getBackMoney() * ordergood.getNum());
							incomerecord.setTime(new Date());
							orderSerivce.addIncome(incomerecord);
							backmoney += ordergood.getBackMoney() * ordergood.getNum();
						}
						GoodsInfo goodsinfo = goodsService.getGoodsInfo(ordergood.getGoodsId());
						goodsinfo.setStock(goodsinfo.getStock() - ordergood.getNum());
						goodsService.updateGoodsInfo(goodsinfo);
					}
					UserInfo shareuser = userService.getUserInfoById(order.getShareuserId());
					shareuser.setBalance(shareuser.getBalance() + backmoney);
					userService.updateUser(shareuser);
				}
				result.put("code", 1);
			} else {
				result.put("code", 500);
				result.put("message", "用户积分不足，兑换失败!");
			}
		} else {
			result.put("code", 500);
			result.put("message", "订单信息错误");
		}
		return result;
	}

	@RequestMapping("/reciveorder")
	@ResponseBody
	public HashMap<String, Object> reciveorder(long orderid) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		Order order = orderSerivce.getOrderById(orderid);
		if (order.getState() == 3) {
			order.setState(4);
			orderSerivce.updateOrder(order);
			result.put("code", 1);
		} else {
			result.put("code", 500);
			result.put("message", "订单数据错误!");
		}
		return result;
	}

	@RequestMapping("/checkgoods")
	public void checkgoods(long orderid, HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String message = "";
		List<OrderGoods> list = orderSerivce.getOrderGoodsList(orderid);
		if (list != null && list.size() > 0) {
			for (OrderGoods orderGoods : list) {
				GoodsInfo goodsinfo = goodsService.getGoodsInfo(orderGoods.getGoodsId());
				if (goodsinfo.getDelFlg() == 0) {
					if (CommonUtils.isEmptyString(message)) {
						message = goodsinfo.getName();
					} else {
						message = message + goodsinfo.getName();
					}
				}
			}
		}
		if (CommonUtils.isEmptyString(message)) {
			result.put("code", 1);
			result.put("message", "订单信息正确");
		} else {
			result.put("code", 500);
			message = message + "已下架";
			result.put("message", message);
		}
		CommonUtils.setMaptoJson(response, result);
	}
}
