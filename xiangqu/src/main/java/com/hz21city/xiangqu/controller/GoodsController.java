package com.hz21city.xiangqu.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.pojo.AdInfo;
import com.hz21city.xiangqu.pojo.CgrecordInfo;
import com.hz21city.xiangqu.pojo.ChannelInfo;
import com.hz21city.xiangqu.pojo.GoodsInfo;
import com.hz21city.xiangqu.pojo.GoodsProNetData;
import com.hz21city.xiangqu.pojo.StoreCotegory;
import com.hz21city.xiangqu.pojo.UserAddress;
import com.hz21city.xiangqu.pojo.UserInfo;
import com.hz21city.xiangqu.service.IAdService;
import com.hz21city.xiangqu.service.ICategoryService;
import com.hz21city.xiangqu.service.IGoodsService;
import com.hz21city.xiangqu.service.IUserAddressService;
import com.hz21city.xiangqu.service.IUserService;
import com.hz21city.xiangqu.service.manage.IMaSysService;

@Controller
@RequestMapping("/user")
public class GoodsController {
	@Resource
	private IGoodsService goodsService;
	@Resource
	private ICategoryService categoryService;
	@Resource
	private IUserAddressService useraddressService;
	@Resource
	private IAdService adService;
	@Resource
	private IUserService userService;
	@Resource
	private IMaSysService maSysService;

	@RequestMapping("/mall")
	public ModelAndView mall(Integer sort, Long categoryId, Integer goodstype, HttpSession session, Integer showType, Integer isapp) {
		ModelAndView mov = new ModelAndView();
		List<AdInfo> adlist = adService.getAdListByType(0);
		mov.addObject("adlist", adlist);
		List<StoreCotegory> cotegorylist = categoryService.getCotegoryList();
		if (categoryId == null) {
			if (cotegorylist != null && cotegorylist.size() > 0) {
				categoryId = cotegorylist.get(0).getId();
			} else {
				categoryId = 0l;
			}
		}
		if (sort == null)
			sort = 1;
		if (goodstype == null) {
			goodstype = 2;
		}
		if(showType == null){
			showType = 0;
		}
		List<GoodsInfo> goodslist = goodsService.getGoodsList(sort, categoryId, goodstype);
		mov.addObject("cotegorylist", cotegorylist);
		mov.addObject("goodslist", goodslist);
		mov.addObject("categoryId", categoryId);
		mov.addObject("goodstype", goodstype);
		mov.addObject("sort", sort);
		mov.addObject("showType", showType);
		mov.addObject("fileRootUrl", CommonUtils.getFileRootUrl());
		long userid = CommonUtils.parseInt(String.valueOf(session.getAttribute("userid")), 0);
		UserInfo userinfo = userService.getUserInfoById(userid);
		String startTime = CommonUtils.getTimeFormat(userinfo.getUpdateTime(), "yyyy-MM-dd HH:mm:ss");
		int count = maSysService.getNewSysmessage(startTime);
		mov.addObject("count", count);
		mov.addObject("shareuserid", userid);
		mov.addObject("isapp", isapp);
		return mov;
	}

	@RequestMapping("/goods")
	public ModelAndView goods(Long goodsid, Integer goodstype, Long shareuserid, String channelCode) {
		ModelAndView mov = new ModelAndView();
		if (goodsid != null && goodsid > 0) {
			GoodsInfo goods = goodsService.getGoodsInfo(goodsid);
			if(!CommonUtils.isEmptyString(channelCode)){
				ChannelInfo channel = maSysService.getChannelInfoByCode(channelCode);
				CgrecordInfo record = new CgrecordInfo();
				record.setAddtime(new Date());
				record.setChannelId(channel.getId());
				record.setGoodId(goodsid);
				maSysService.insertCgrecordInfo(record);
			}
			mov.addObject("goods", goods);
			mov.addObject("goodstype", goodstype);
			mov.addObject("shareuserid", shareuserid);
			mov.addObject("channelCode", channelCode);
			mov.addObject("fileRootUrl", CommonUtils.getFileRootUrl());
		} else {
			mov.setViewName("redirect:/user/mall");
		}
		return mov;
	}

	@RequestMapping("/makeorder")
	public ModelAndView makeorder(Long addressid, Long goodsid, Integer goodstype, Long shareuserid,String property, String channelCode, HttpSession session) {
		ModelAndView mov = new ModelAndView();
		if (goodsid != null && goodsid > 0) {
			// 暂时注释，用户ID
			 long userid = CommonUtils.parseLong(String.valueOf(session.getAttribute("userid")), 0);
			GoodsInfo goods = goodsService.getGoodsInfo(goodsid);
			if (addressid != null && addressid > 0) {
				UserAddress useraddress = useraddressService.getAddress(addressid);
				mov.addObject("useraddress", useraddress);
			} else {
				UserAddress useraddress = useraddressService.getUserDefaultAddress(userid);
				mov.addObject("useraddress", useraddress);
			}
			mov.addObject("userinfo", userService.getUserInfoById(userid));
			mov.addObject("goods", goods);
			mov.addObject("goodstype", goodstype);
			mov.addObject("property",property);
			mov.addObject("fileRootUrl", CommonUtils.getFileRootUrl());
			mov.addObject("shareuserid", shareuserid);
			mov.addObject("channelCode", channelCode);
			mov.addObject("isServe", goods.getIsServe());
		} else {
			mov.setViewName("redirect:/user/mall");
		}
		return mov;
	}

	
	
	@RequestMapping("/sharegoods")
	public ModelAndView sharegoods(HttpSession session) {
		ModelAndView mov = new ModelAndView();
		List<GoodsInfo> goodslist = goodsService.getAllGoodsList();
		mov.addObject("fileRootUrl", CommonUtils.getFileRootUrl());
		mov.addObject("goodslist", goodslist);
		long userid = CommonUtils.parseInt(String.valueOf(session.getAttribute("userid")), 0);
		mov.addObject("shareuserid", userid);
		return mov;
	}
	
	@RequestMapping("/creatgoodjson")
	@ResponseBody
	public HashMap<String,Object> creatgoodjson(String[] proname,String[] provalue){
		HashMap<String,Object> result = new HashMap<String,Object>();
		if(proname!=null&&proname.length>0){
			List<GoodsProNetData>  goodspro = new ArrayList<GoodsProNetData>();
			for(int i =0 ;i<proname.length;i++){
				GoodsProNetData prodata = new GoodsProNetData();
				prodata.setName(proname[i]);
				prodata.setValue(provalue[i]);
				goodspro.add(prodata);
			}
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").disableHtmlEscaping().create();;
			String projson = gson.toJson(goodspro);
			result.put("code", 1);
			result.put("projson", projson);
		}else{
			result.put("code", 500);
		}
		return result;
	}

}
