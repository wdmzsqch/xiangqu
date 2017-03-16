package com.hz21city.xiangqu.controller.shop;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.pojo.CouponInfo;
import com.hz21city.xiangqu.pojo.LotteryInfo;
import com.hz21city.xiangqu.pojo.UserCoupon;
import com.hz21city.xiangqu.service.shop.IShCouponService;

@Controller
@RequestMapping("/shop")
public class ShCouponController {
	
	@Resource
	private IShCouponService shCouponService;

	
	// 借用下，验证优惠券
	@RequestMapping("/coupon_check")
	public ModelAndView couponCheck(String code, Integer couponid, HttpSession session) {
		ModelAndView mov = new ModelAndView("/shop/couponcheckresult");
		long storeId = CommonUtils.parseLong(String.valueOf(session.getAttribute("storeid")), 0);
		mov.addObject("code", code);
		HashMap<String, Object> map = shCouponService.getCouponByCode(code, storeId);
		UserCoupon userCoupon = (UserCoupon) map.get("userCoupon");
		int isused = (int) map.get("isUsed");
		if(userCoupon != null && isused != 1){
			LotteryInfo lottery = new LotteryInfo();
			lottery.setUserId(userCoupon.getUserId());
			lottery.setCouponId(userCoupon.getCouponId());
			lottery.setState(0);
			lottery.setAddtime(new Date());
			shCouponService.insertLotterInfo(lottery);
		}
		mov.addObject("isused", isused);
		mov.addAllObjects(shCouponService.getCouponByCode(code, storeId));
		return mov;
	}
	
	@RequestMapping("/shcoupon")
	public ModelAndView shcoupon(HttpSession session){
		ModelAndView mov = new ModelAndView();
		long storeId = CommonUtils.parseLong(String.valueOf(session.getAttribute("storeid")), 0);
		List<CouponInfo> couponlist = shCouponService.getCouponInfoList(storeId);
		mov.addObject("couponlist",couponlist);
		return mov;
	}
	
	@RequestMapping("/shcoupondetail")
	public ModelAndView shcoupondetail(Long id){
		ModelAndView mov = new ModelAndView();
		CouponInfo coupon = shCouponService.getCouponInfo(id);
		mov.addObject("coupon",coupon);
		return mov;
	}
}
