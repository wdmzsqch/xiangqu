package com.hz21city.xiangqu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alipay.util.AlipayNotify;
import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.pojo.GoodsInfo;
import com.hz21city.xiangqu.pojo.IncomeRecord;
import com.hz21city.xiangqu.pojo.Order;
import com.hz21city.xiangqu.pojo.OrderGoods;
import com.hz21city.xiangqu.pojo.ShopInfo;
import com.hz21city.xiangqu.pojo.UserInfo;
import com.hz21city.xiangqu.service.IGoodsService;
import com.hz21city.xiangqu.service.IOrderService;
import com.hz21city.xiangqu.service.IUserService;
import com.hz21city.xiangqu.service.shop.IShOrderService;
import com.hz21city.xiangqu.service.shop.IShShopService;

@Controller
@RequestMapping("/user")
public class AlliWapCallBack {

	@Resource
	private IOrderService orderSerivce;
	@Resource
	private IUserService userService;
	@Resource
	private IGoodsService goodsService;
	@Resource
	private IShShopService shopService;

	/**
	 * 校验信息是否是从微信服务器发过来的。
	 * 
	 * @param weChat
	 * @param out
	 * @throws IOException
	 */
	@RequestMapping("/allicallback")
	public void allicallback(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// System.out.println("支付宝回调");
		try {
			// 获取支付宝POST过来反馈信息
			Map<String, String> params = new HashMap<String, String>();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
				params.put(name, valueStr);
			}
			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			// 商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
			// 支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
			// 交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
			String total_fee = new String(request.getParameter("total_fee").getBytes("ISO-8859-1"), "UTF-8");
			String buyer_id = new String(request.getParameter("buyer_id").getBytes("ISO-8859-1"), "UTF-8");
			String buyer_email = new String(request.getParameter("buyer_email").getBytes("ISO-8859-1"), "UTF-8");
			// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
			if (AlipayNotify.verify(params)) {// 验证成功
				// ////////////////////////////////////////////////////////////////////////////////////////
				// 请在这里加上商户的业务逻辑程序代码
				// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
				if (trade_status.equals("TRADE_FINISHED")) {
					// 判断该笔订单是否在商户网站中已经做过处理
					// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					// 如果有做过处理，不执行商户的业务程序
					// 注意：
					// 该种交易状态只在两种情况下出现
					// 1、开通了普通即时到账，买家付款成功后。
					// 2、开通了高级即时到账，从该笔交易成功时间算起，过了签约时的可退款时限（如：三个月以内可退款、一年以内可退款等）后。
					PrintWriter out = response.getWriter();
					out.print("success");
					out.flush();
					out.close();
				} else if (trade_status.equals("TRADE_SUCCESS")) {
					// 判断该笔订单是否在商户网站中已经做过处理
					// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					// 如果有做过处理，不执行商hoolo户的业务程序
					// 注意：
					// 该种交易状态只在一种情况下出现——开通了高级即时到账，买家付款成功后。
					// System.out.println("支付宝回调成功" + out_trade_no);
					Order order = orderSerivce.getOrderByOutTradeNo(out_trade_no);
					List<OrderGoods> ordergoods = orderSerivce.getOrderGoodsList(order.getId());
					UserInfo userinfo = userService.getUserInfoById(order.getUserId());
					ShopInfo shopinfo = shopService.getShopInfoById(order.getStoreId());
					if (ordergoods != null && ordergoods.size() > 0) {
						if (ordergoods.get(0).getIsServe() == 0) {
							order.setState(2);
							// 购买成功后给买家发信息
							try {
								System.out.println("mobile：" + order.getPhone() + "|||  goodname:" + ordergoods.get(0).getName() + "  |||shopinfo" + shopinfo.getPhone());
								CommonUtils.YMsendSms(order.getPhone(), "您已成功兑换/购买" + ordergoods.get(0).getName() + "我们将尽快处理您的订单。感谢您的惠顾！商家电话" + shopinfo.getPhone());
							} catch (Exception e) {
								System.out.println("send sms fail");
								e.printStackTrace();
							}
						} else {
							order.setState(3);
							Order randorder = null;
							String randNum = null;
							do {
								randNum = CommonUtils.genRandomNum(8);
								randorder = orderSerivce.getOrderByCode(randNum);
							} while (randorder == null);
							order.setCode(randNum);
							String codepic = CommonUtils.creatQrcode("com.hz21city.xiangqu_goods_" + order.getId() + "_" + randNum);
							order.setCodepic(codepic);
						}
					}
					order.setPayTime(new Date());
					orderSerivce.updateOrder(order);
					// 购买成功后给商家发信息
					try {
						CommonUtils.YMsendSms(shopinfo.getMobile(), "您有新的订单，订单编号为" + order.getOrderNo());
					} catch (Exception e) {
					}
					
					float backmoney = 0f;
					for (OrderGoods ordergood : ordergoods) {
						if (ordergood.getBackMoney() != null && ordergood.getBackMoney() > 0&&order.getShareuserId() != null && order.getShareuserId() > 0) {
							// int incomecount = orderSerivce.getIncomeCount(order.getShareuserId(), order.getUserId(), 2,new Long(ordergood.getId()).intValue());
							// if(incomecount==0){
							IncomeRecord incomerecord = new IncomeRecord();
							incomerecord.setFromUserId(order.getUserId());
							incomerecord.setUserId(order.getShareuserId());
							incomerecord.setType(2);
							incomerecord.setRelativeId(ordergood.getGoodsId());
							incomerecord.setIncome(ordergood.getBackMoney() * ordergood.getNum());
							incomerecord.setTime(new Date());
							orderSerivce.addIncome(incomerecord);
							backmoney += ordergood.getBackMoney() * ordergood.getNum();
							// }
						}
						GoodsInfo goodsinfo = goodsService.getGoodsInfo(ordergood.getGoodsId());
						goodsinfo.setStock(goodsinfo.getStock() - ordergood.getNum());
						System.out.println("oeder_num:  stock:" + goodsinfo.getStock() + "||num:" + ordergood.getNum());
						goodsService.updateGoodsInfo(goodsinfo);
					}
					if (order.getShareuserId() != null && order.getShareuserId() > 0) {
						UserInfo shareuser = userService.getUserInfoById(order.getShareuserId());
						shareuser.setBalance(shareuser.getBalance() + backmoney);
						userService.updateUser(shareuser);
					}
					System.out.println("wapsuccess");
					PrintWriter out = response.getWriter();
					out.print("success");
					out.flush();
					out.close();
				}
				// ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
				// out.println("success"); // 请不要修改或删除
				// ////////////////////////////////////////////////////////////////////////////////////////
			} else {// 验证失败
				// out.println("fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
