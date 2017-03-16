package com.hz21city.xiangqu.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.pojo.CouponInfo;
import com.hz21city.xiangqu.pojo.IncomeRecord;
import com.hz21city.xiangqu.pojo.ShopInfo;
import com.hz21city.xiangqu.pojo.UserCoupon;
import com.hz21city.xiangqu.pojo.UserInfo;
import com.hz21city.xiangqu.pojo.VerifyCode;
import com.hz21city.xiangqu.service.ICouponService;
import com.hz21city.xiangqu.service.IOrderService;
import com.hz21city.xiangqu.service.IUserService;

@Controller
@RequestMapping("/user")
public class CouponController {

	@Resource
	private ICouponService couponService;
	@Resource
	private IOrderService orderSerivce;
	@Resource
	private IUserService userService;

	@RequestMapping("/coupon")
	public ModelAndView coupon(HttpSession session) {
		ModelAndView mov = new ModelAndView();
		List<ShopInfo> shoplist = couponService.getShopCouponList();
		mov.addObject("shoplist", shoplist);
		mov.addObject("fileurl", CommonUtils.getFileRootUrl());
		long userid = CommonUtils.parseInt(String.valueOf(session.getAttribute("userid")), 0);
		mov.addObject("share_userid", userid);
		int allcoupon = 0;
		for (ShopInfo shop : shoplist) {
			allcoupon += shop.getCouponlist().size();
		}
		mov.addObject("allcoupon", allcoupon);
		return mov;
	}

	@RequestMapping("/searchcoupon")
	public ModelAndView searchcoupon(HttpSession session, String type, Integer datetype, String shops) {
		ModelAndView mov = new ModelAndView();
		List<ShopInfo> shoplist = couponService.getSearchShopCouponList(type, datetype, shops);
		mov.addObject("fileurl", CommonUtils.getFileRootUrl());
		long userid = CommonUtils.parseInt(String.valueOf(session.getAttribute("userid")), 0);
		mov.addObject("share_userid", userid);
		List<CouponInfo> couponlist = new ArrayList<CouponInfo>();
		int allcoupon = 0;
		for (ShopInfo shop : shoplist) {
			couponlist.addAll(shop.getCouponlist());
			allcoupon += shop.getCouponlist().size();
		}
		mov.addObject("couponlist", couponlist);
		mov.addObject("allcoupon", allcoupon);
		return mov;
	}

	@RequestMapping("/coupondetail")
	public ModelAndView coupondetail(Long coupon_id, Long share_userid, HttpSession session) {
		ModelAndView mov = new ModelAndView();
		long userid = CommonUtils.parseInt(String.valueOf(session.getAttribute("userid")), 0);
		UserInfo userinfo = userService.getUserInfoById(userid);
		if (coupon_id != null && coupon_id > 0) {
			CouponInfo coupon = couponService.getCouponInfoById(coupon_id);
			if (coupon != null) {
				ShopInfo shop = couponService.getShopInfoById(coupon.getStoreId());
				mov.addObject("shop", shop);
				mov.addObject("coupon", coupon);
				mov.addObject("share_userid", share_userid);
				mov.addObject("fileurl", CommonUtils.getFileRootUrl());
				if (CommonUtils.isEmptyString(userinfo.getMoblie())) {
					mov.addObject("hasMoblie", 0);
				} else {
					mov.addObject("hasMoblie", 1);
				}
			} else {
				mov.setViewName("redirect:/user/coupon");
			}
		} else {
			mov.setViewName("redirect:/user/coupon");
		}
		return mov;
	}

	@RequestMapping("/getCoupon")
	@ResponseBody
	public HashMap<String, Object> getCoupon(Long share_userid, Long coupon_id, HttpSession session) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		if (coupon_id != null && coupon_id > 0) {
			CouponInfo coupon = couponService.getCouponInfoById(coupon_id);
			if (coupon != null) {
				long userid = CommonUtils.parseInt(String.valueOf(session.getAttribute("userid")), 0);
				if (coupon.getTotal() > coupon.getGetNum()) {
					int usercoupon = couponService.userCouponCount(userid, coupon_id);
					if (usercoupon < coupon.getNumLimit()) {
						result.put("code", "1");
						UserCoupon userCoupon = new UserCoupon();
						userCoupon.setUserId(userid);
						userCoupon.setCouponId(coupon_id);
						userCoupon.setGetTime(new Date());
						if (share_userid != null && userid != share_userid) {
							userCoupon.setInviteUserId(share_userid);
						} else {
							userCoupon.setInviteUserId(0l);
						}
						userCoupon.setIsUsed(0);
						int randcoupon = 0;
						String randNum = null;
						do {
							randNum = CommonUtils.genRandomNum(8);
							randcoupon = couponService.getCouponByCode(randNum);
						} while (randcoupon > 0);
						userCoupon.setCode(randNum);
						String codepic = CommonUtils.creatQrcode("com.hz21city.xiangqu_coupon_" + coupon.getId() + "_" + randNum);
						userCoupon.setCodePic(codepic);
						couponService.addUserCoupon(userCoupon);
						coupon.setGetNum(coupon.getGetNum() + 1);
						couponService.updateCoupon(coupon);
						if (share_userid != null && userid != share_userid) {
							int incomecount = orderSerivce.getIncomeCount(share_userid, userid, 4, new Long(coupon.getId()).intValue());
							if (incomecount == 0) {
								IncomeRecord incomerecord = new IncomeRecord();
								incomerecord.setFromUserId(userid);
								incomerecord.setUserId(share_userid);
								incomerecord.setType(4);
								incomerecord.setRelativeId(coupon.getId());
								incomerecord.setIncome(coupon.getShareIncome().floatValue());
								incomerecord.setTime(new Date());
								orderSerivce.addIncome(incomerecord);
								UserInfo shareuser = userService.getUserInfoById(share_userid);
								shareuser.setBalance(shareuser.getBalance() + coupon.getShareIncome());
								userService.updateUser(shareuser);
							}
						}
						UserInfo userinfo = userService.getUserInfoById(userid);
						ShopInfo shopInfo = couponService.getShopInfoById(coupon.getStoreId());
						if (userinfo != null) {
							if (!CommonUtils.isEmptyString(userinfo.getMoblie())) {
								String endtime = CommonUtils.getTimeFormat(coupon.getValidity(), "yyyy年MM月dd日");
								if (!CommonUtils.isEmptyString(shopInfo.getPhone())) {
									CommonUtils.YMsendSms(userinfo.getMoblie(),
											"恭喜您成功已领取了" + coupon.getTitle() + "优惠券，优惠券码为" + randNum + "，请于" + endtime + "前使用，商家电话" + shopInfo.getPhone() + "，商家地址" + shopInfo.getAddress());
								} else {
									CommonUtils.YMsendSms(userinfo.getMoblie(),
											"恭喜您成功已领取了" + coupon.getTitle() + "优惠券，优惠券码为" + randNum + "，请于" + endtime + "前使用，商家电话" + shopInfo.getPhone() + "，商家地址" + shopInfo.getAddress());
								}
							}
						}
					} else {
						result.put("code", "500");
						result.put("message", "已到达可领的优惠券上限，领取失败!");
					}
				} else {
					result.put("code", "500");
					result.put("message", "优惠券已经被领光了！");
				}
			}
		}
		return result;
	}

	@RequestMapping("/getCouponByCode")
	@ResponseBody
	public HashMap<String, Object> getCouponByCode(Long share_userid, String code, HttpSession session) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		if (!CommonUtils.isEmptyString(code)) {
			CouponInfo coupon = couponService.getCouponInfoByCode(code);
			if (coupon != null) {
				long userid = CommonUtils.parseInt(String.valueOf(session.getAttribute("userid")), 0);
				if (coupon.getTotal() > coupon.getGetNum()) {
					int usercoupon = couponService.userCouponCount(userid, coupon.getId());
					if (usercoupon < coupon.getNumLimit()) {
						result.put("code", "1");
						UserCoupon userCoupon = new UserCoupon();
						userCoupon.setUserId(userid);
						userCoupon.setCouponId(coupon.getId());
						userCoupon.setGetTime(new Date());
						if (share_userid != null && userid != share_userid) {
							userCoupon.setInviteUserId(share_userid);
						} else {
							userCoupon.setInviteUserId(0l);
						}
						userCoupon.setIsUsed(0);
						int randcoupon = 0;
						String randNum = null;
						do {
							randNum = CommonUtils.genRandomNum(8);
							randcoupon = couponService.getCouponByCode(randNum);
						} while (randcoupon > 0);
						userCoupon.setCode(randNum);
						String codepic = CommonUtils.creatQrcode("com.hz21city.xiangqu_coupon_" + coupon.getId() + "_" + randNum);
						userCoupon.setCodePic(codepic);
						couponService.addUserCoupon(userCoupon);
						coupon.setGetNum(coupon.getGetNum() + 1);
						couponService.updateCoupon(coupon);
						if (share_userid != null && userid != share_userid) {
							int incomecount = orderSerivce.getIncomeCount(share_userid, userid, 4, new Long(coupon.getId()).intValue());
							if (incomecount == 0) {
								IncomeRecord incomerecord = new IncomeRecord();
								incomerecord.setFromUserId(userid);
								incomerecord.setUserId(share_userid);
								incomerecord.setType(4);
								incomerecord.setRelativeId(coupon.getId());
								incomerecord.setIncome(coupon.getShareIncome().floatValue());
								incomerecord.setTime(new Date());
								orderSerivce.addIncome(incomerecord);
								UserInfo shareuser = userService.getUserInfoById(share_userid);
								shareuser.setBalance(shareuser.getBalance() + coupon.getShareIncome());
								userService.updateUser(shareuser);
							}
						}
					} else {
						result.put("code", "500");
						result.put("message", "已到达可领的优惠券上限，领取失败!");
					}
				} else {
					result.put("code", "500");
					result.put("message", "优惠券已经被领光了！");
				}
			} else {
				result.put("code", "500");
				result.put("message", "无效的优惠券码！");
			}
		}
		return result;
	}

	@RequestMapping("/mycoupon")
	public ModelAndView myCoupon(HttpSession session) {
		ModelAndView mov = new ModelAndView("/user/usercoupon");
		long userid = CommonUtils.parseLong(String.valueOf(session.getAttribute("userid")), 0);
		List<UserCoupon> couponlist = couponService.getMyCoupon(userid);
		if (couponlist != null && couponlist.size() > 0) {
			Date nowDate = new Date();
			for (UserCoupon userCoupon : couponlist) {
				if ((nowDate.getTime() - userCoupon.getValidity().getTime()) <= 0) {
					// 未过期
					userCoupon.setIsover(0);
				} else {
					// 已过期
					userCoupon.setIsover(1);
				}
			}
		}
		mov.addObject("couponlist", couponlist);
		HashSet<Long> shopset = new HashSet<Long>();
		for (UserCoupon coupon : couponlist) {
			shopset.add(coupon.getShop_id());
		}
		List<ShopInfo> shoplist = new ArrayList<ShopInfo>();
		for (Long shopid : shopset) {
			ShopInfo shop = couponService.getShopInfoById(shopid);
			shoplist.add(shop);
		}
		mov.addObject("shoplist", shoplist);
		return mov;
	}

	@RequestMapping("/searchusercoupon")
	public ModelAndView searchusercoupon(HttpSession session, String type, Integer datetype, Long[] shops) {
		ModelAndView mov = new ModelAndView();
		long userid = CommonUtils.parseLong(String.valueOf(session.getAttribute("userid")), 0);
		List<UserCoupon> couponlist = couponService.getMyCouponSearch(userid, type, datetype, shops);
		if (couponlist != null && couponlist.size() > 0) {
			Date nowDate = new Date();
			for (UserCoupon userCoupon : couponlist) {
				if ((nowDate.getTime() - userCoupon.getValidity().getTime()) <= 0) {
					// 未过期
					userCoupon.setIsover(0);
				} else {
					// 已过期
					userCoupon.setIsover(1);
				}
			}
		}
		mov.addObject("couponlist", couponlist);
		mov.setViewName("/user/usersearchcoupon");
		return mov;
	}

	@RequestMapping("/usercoupon")
	public ModelAndView usercoupon(Long id) {
		ModelAndView mov = new ModelAndView();
		if (id != null && id > 0) {
			UserCoupon usercoupon = couponService.getUserCouponById(id);
			if (usercoupon != null) {
				mov.addObject("fileurl", CommonUtils.getFileRootUrl());
				mov.addObject("usercoupon", usercoupon);
				mov.setViewName("/user/usercoupondetail");
			} else {
				mov.setViewName("redirect:/user/mycoupon");
			}
		} else {
			mov.setViewName("redirect:/user/mycoupon");
		}
		return mov;
	}

	@RequestMapping("/addUserMoblie")
	public void addUserMoblie(String moblie, String vcode, HttpSession session, HttpServletResponse response) {
		long userid = CommonUtils.parseLong(String.valueOf(session.getAttribute("userid")), 0);
		// VerifyCode verifyCode = userService.getVerifyCode(moblie, vcode);
		VerifyCode code = userService.getVerifyCode(moblie);
		Date nowdate = new Date();
		if (code == null || !code.getCode().endsWith(vcode)) {
			CommonUtils.setResponseStr("error", response);
			return;
		} else if ((nowdate.getTime() - code.getCreateTime().getTime()) > 300000) {
			CommonUtils.setResponseStr("error1", response);
			return;
		} else {
			UserInfo userinfo = userService.getUserInfoById(userid);
			userinfo.setMoblie(moblie);
			userService.updateUser(userinfo);
			CommonUtils.setResponseStr("success", response);
		}
	}
}
