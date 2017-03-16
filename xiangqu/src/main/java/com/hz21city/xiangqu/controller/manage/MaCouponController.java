package com.hz21city.xiangqu.controller.manage;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.pojo.CouponInfo;
import com.hz21city.xiangqu.pojo.ShopInfo;
import com.hz21city.xiangqu.pojo.WinprizeInfo;
import com.hz21city.xiangqu.service.manage.IMaCouponService;
import com.hz21city.xiangqu.service.manage.IMaShopService;

@Controller
@RequestMapping("/manage")
public class MaCouponController extends MaBaseController{
	
	@Resource
	private IMaCouponService maCouponService;

	@Resource
	private IMaShopService mashopService;
	
	@RequestMapping("/coupon_list")
	public ModelAndView coupon_list(Integer pageIndex, String keywords, String endtime){
		ModelAndView mov = new ModelAndView("/manage/coupon_list");
		int page = 1;
		if (pageIndex != null && pageIndex > 0) {
			page = pageIndex;
		}
		Date endDate = null;
		if (!CommonUtils.isEmptyString(endtime)) {
			endDate = CommonUtils.str2Date(endtime, "yyyy-MM-dd");
		}
		mov.addObject("keywords", keywords);
		mov.addObject("endtime", endtime);
		mov.addObject("pageIndex", page);
		mov.addObject("coupon_list", maCouponService.getCouponListByPage(page,keywords,endDate));
		mov.addObject("pageCount", maCouponService.getCouponListSize(keywords,endDate));
		mov.addObject("webrooturl", CommonUtils.getWebRootUrl());
		mov.addObject("perCount", maCouponService.getAllCouponSize());
		return mov;
	}
	
	@RequestMapping("/coupon_detail")
	public ModelAndView coupon_detail(Long id){
		ModelAndView mov = new ModelAndView("/manage/coupon_insert");
		List<ShopInfo> shoplist = mashopService.getAllShopList();
		mov.addObject("shoplist", shoplist);
		if(id != null && id > 0){
			mov.addObject("coupon", maCouponService.selectByPrimaryKey(id));
			mov.addObject("fileUrl", CommonUtils.getFileRootUrl());
		}
		return mov;
	}
	
	@RequestMapping("/coupon_edit")
	public ModelAndView coupon_edit(CouponInfo couponinfo , String endTime){
		ModelAndView mov = new ModelAndView("redirect:/manage/coupon_list");
		if (!CommonUtils.isEmptyString(endTime)) {
			endTime = endTime + " 23:59:59";
			couponinfo.setValidity(CommonUtils.str2Date(endTime, "yyyy-MM-dd HH:mm:ss"));
		}
		if(couponinfo.getId() != null){
			maCouponService.updateCouponInfo(couponinfo);
		}else{
			String randNum = null;
			Integer count = 0;
			do{
				randNum= CommonUtils.genRandomNum(8);
				count = maCouponService.getCouponCountByCode(randNum);
				}while(count > 0);
			couponinfo.setCode(randNum);
			if(couponinfo.getGetNum()==null)
				couponinfo.setGetNum(0);
			if(couponinfo.getUseNum()==null)
				couponinfo.setUseNum(0);
			couponinfo.setCreateTime(new Date());
			maCouponService.addCouponInfo(couponinfo);
		}
		return mov;
	}
	
	@RequestMapping("/get_coupon")
	public ModelAndView get_coupon(Integer pageIndex, Long coupon_id){
		ModelAndView mov = new ModelAndView("/manage/user_coupon_list");
		int page = 1;
		if (pageIndex != null && pageIndex > 0) {
			page = pageIndex;
		}
		mov.addObject("pageIndex", page);
		mov.addObject("coupon_id", coupon_id);
		mov.addObject("user_coupon_list", maCouponService.getUserCouponListByPage(page,coupon_id));
		mov.addObject("pageCount", maCouponService.getUserCouponListSize(coupon_id));
		return mov;
	}
	
	/**
	 * 中奖记录
	 * @param pageIndex
	 * @param userName 用户名
	 * @param moblie 手机号
	 * @param state 兑换状态
	 * @return
	 */
	@RequestMapping("/prize_list")
	public ModelAndView prize_list(Integer pageIndex, String userName, String moblie, Integer state){
		ModelAndView mov = new ModelAndView("/manage/user_prize_list");
		int page = 1;
		if (pageIndex != null && pageIndex > 0) {
			page = pageIndex;
		}
		mov.addObject("pageIndex", page);
		mov.addObject("userName", userName);
		mov.addObject("moblie", moblie);
		mov.addObject("state", state);
		mov.addObject("list", maCouponService.getUserPrizeListByPage(page, userName, moblie, state));
		mov.addObject("pageCount", maCouponService.getUserPrizeListSize(userName, moblie, state));
		return mov;
	}
	
	@RequestMapping("/exchangePrize")
	public void exchangePrize(Long id, HttpServletResponse response){
		if(id != null){
			WinprizeInfo winprize = maCouponService.getWinprizeInfoById(id);
			if(winprize != null){
				winprize.setState(1);
				maCouponService.updateWinprizeInfo(winprize);
				CommonUtils.setResponseStr("success", response);
				return;
			}else{
				CommonUtils.setResponseStr("error", response);
				return;
			}
		}else{
			CommonUtils.setResponseStr("error", response);
			return;
		}
	}

}
