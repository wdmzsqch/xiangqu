package com.hz21city.xiangqu.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
import com.hz21city.xiangqu.service.shop.IShShopService;
import com.utils.GetWxOrderno;

@Controller
@RequestMapping("/user")
public class WxCallBack {

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
	@RequestMapping("/wxpaycallback")
	public void wxpaycallback(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		System.out.println("微信回调");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream()));
			String line = null;
			StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			System.out.println(sb.toString());
			Map map = GetWxOrderno.doXMLParse(sb.toString());
			String return_code = (String) map.get("return_code");
			String result_code = (String) map.get("result_code");
			if ("SUCCESS".equals(return_code)) {
				if ("SUCCESS".equals(result_code)) {
					String attach = (String) map.get("attach");
					// String openid = (String) map.get("openid");
					// String transaction_id = (String) map.get("transaction_id");
					// String total_fee = (String) map.get("total_fee");
					if (!CommonUtils.isEmptyString(attach)) {
						Order order = orderSerivce.getOrderByOutTradeNo(attach);
						List<OrderGoods> ordergoods = orderSerivce.getOrderGoodsList(order.getId());
						UserInfo userinfo = userService.getUserInfoById(order.getUserId());
						ShopInfo shopinfo = shopService.getShopInfoById(order.getStoreId());
						if (ordergoods != null && ordergoods.size() > 0) {
							if (ordergoods.get(0).getIsServe() == 0) {// 只给非服务性商品发短信
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
							if (ordergood.getBackMoney() != null && ordergood.getBackMoney() > 0 && order.getShareuserId() != null && order.getShareuserId() > 0) {
								IncomeRecord incomerecord = new IncomeRecord();
								incomerecord.setFromUserId(order.getUserId());
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
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
