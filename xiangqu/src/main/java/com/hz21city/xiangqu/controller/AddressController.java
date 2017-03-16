package com.hz21city.xiangqu.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.pojo.Area;
import com.hz21city.xiangqu.pojo.GoodsInfo;
import com.hz21city.xiangqu.pojo.UserAddress;
import com.hz21city.xiangqu.service.IGoodsService;
import com.hz21city.xiangqu.service.IUserAddressService;
import com.hz21city.xiangqu.service.IUserService;

@Controller
@RequestMapping("/user")
public class AddressController {
	@Resource
	private IUserAddressService useraddressService;
	@Resource
	private IGoodsService goodsService;
	@Resource
	private IUserService userServie;

	@RequestMapping("/myaddress")
	public ModelAndView myaddress(HttpSession session,String property,Long addressid, Integer goodstype, Long goodsid, Integer goodsnum,Long shareuserid, String channelCode) {
		// 暂时注释，用户ID
		long userid = CommonUtils.parseLong(String.valueOf(session.getAttribute("userid")), 0);
		ModelAndView mov = new ModelAndView();
		mov.addObject("addressid", addressid);
		mov.addObject("goodstype", goodstype);
		mov.addObject("goodsid", goodsid);
		mov.addObject("goodsnum", goodsnum);
		mov.addObject("shareuserid", shareuserid);
		mov.addObject("property", property);
		mov.addObject("channelCode", channelCode);
		List<UserAddress> addresslist = useraddressService.getUserAddress(userid);
		mov.addObject("addresslist", addresslist);
		return mov;
	}

	@RequestMapping("/addressdetail")
	public ModelAndView addressdetail(String property,Long editaddressid, Long addressid, int goodstype, long goodsid, int goodsnum,Long shareuserid, String channelCode) {
		ModelAndView mov = new ModelAndView();
		mov.addObject("addressid", addressid);
		mov.addObject("goodstype", goodstype);
		mov.addObject("goodsid", goodsid);
		mov.addObject("goodsnum", goodsnum);
		mov.addObject("property", property);
		UserAddress useraddress = null;
		if (editaddressid != null && editaddressid > 0) {
			useraddress = useraddressService.getAddress(editaddressid);
		}
		mov.addObject("useraddress", useraddress);
		mov.addObject("channelCode", channelCode);
		mov.addObject("shareuserid", shareuserid);
		List<Area> prolist = useraddressService.getAreaList(0);
		mov.addObject("prolist", prolist);
		if (useraddress != null) {
			if (useraddress.getProvince() != null && useraddress.getProvince() > 0) {
				List<Area> citylist = useraddressService.getAreaList(useraddress.getProvince());
				mov.addObject("citylist", citylist);
			}
			if (useraddress.getCity() != null && useraddress.getCity() > 0) {
				List<Area> arealist = useraddressService.getAreaList(useraddress.getCity());
				mov.addObject("arealist", arealist);
			}
		} else {
			if (prolist != null && prolist.size() > 0) {
				long proid = prolist.get(0).getId();
				List<Area> citylist = useraddressService.getAreaList(proid);
				mov.addObject("citylist", citylist);
				if (citylist != null && citylist.size() > 0) {
					long cityid = citylist.get(0).getId();
					List<Area> arealist = useraddressService.getAreaList(cityid);
					mov.addObject("arealist", arealist);
				}
			}
		}
		return mov;
	}

	@RequestMapping("/getAreaListAsyn")
	@ResponseBody
	public HashMap<String, Object> getAreaListAsyn(long parentid) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<Area> arealist = useraddressService.getAreaList(parentid);
		result.put("arealist", arealist);
		return result;
	}

	@RequestMapping("/editaddress")
	public ModelAndView editaddress(HttpSession session,UserAddress address,String property, Long addressid, int goodstype, long goodsid, int goodsnum,Long shareuserid, String channelCode) {
		ModelAndView mov = new ModelAndView();
		long userid = CommonUtils.parseLong(String.valueOf(session.getAttribute("userid")), 0);
		if (address.getId() == null) {
			// 暂时注释，用户ID
			address.setUserId(userid);
			List<UserAddress> addresslist = useraddressService.getUserAddress(userid);
			if (addresslist != null && addresslist.size() > 0) {
				address.setIsDefult(0);
			} else {
				address.setIsDefult(1);
			}
			useraddressService.addUserAddress(address);
		} else {
			useraddressService.updateUserAddress(address);
		}
		mov.addObject("addressid", addressid);
		mov.addObject("goodstype", goodstype);
		mov.addObject("goodsid", goodsid);
		mov.addObject("goodsnum", goodsnum);
		mov.addObject("shareuserid", shareuserid);
		mov.addObject("property",property);
		mov.addObject("channelCode",channelCode);
		List<UserAddress> addresslist = useraddressService.getUserAddress(userid);
		mov.addObject("addresslist", addresslist);
		mov.setViewName("/user/myaddress");
		return mov;
	}

	@RequestMapping("/checkaddress")
	public ModelAndView checkaddress(HttpSession session,String property,Long checkaddressid, Long addressid, int goodstype, long goodsid, int goodsnum,Long shareuserid, String channelCode) {
		ModelAndView mov = new ModelAndView();
		UserAddress useraddress = useraddressService.getAddress(checkaddressid);
		long userid = CommonUtils.parseLong(String.valueOf(session.getAttribute("userid")), 0);
		if (useraddress != null) {
			// 暂时注释，用户ID
			UserAddress defaultaddress = useraddressService.getUserDefaultAddress(userid);
			if(defaultaddress!=null){
				defaultaddress.setIsDefult(0);
				useraddressService.updateUserAddress(defaultaddress);
			}
			useraddress.setIsDefult(1);
			useraddressService.updateUserAddress(useraddress);
		}
		// 暂时注释，用户ID
		// long userid = (long) session.getAttribute("userid");
		GoodsInfo goods = goodsService.getGoodsInfo(goodsid);
		mov.addObject("useraddress", useraddress);
		mov.addObject("goods", goods);
		mov.addObject("goodstype", goodstype);
		mov.addObject("fileRootUrl", CommonUtils.getFileRootUrl());
		mov.addObject("shareuserid", shareuserid);
		mov.addObject("property", property);
		mov.addObject("channelCode", channelCode);
		mov.addObject("userinfo", userServie.getUserInfoById(userid));
		mov.setViewName("/user/makeorder");
		return mov;
	};
	
	@RequestMapping("/useraddress")
	public ModelAndView useraddress(HttpSession session) {
		// 暂时注释，用户ID
		long userid = CommonUtils.parseLong(String.valueOf(session.getAttribute("userid")), 0);
		ModelAndView mov = new ModelAndView();
		List<UserAddress> addresslist = useraddressService.getUserAddress(userid);
		mov.addObject("addresslist", addresslist);
		return mov;
	}
	
	@RequestMapping("/useraddressdetail")
	public ModelAndView useraddressdetail(Long editaddressid) {
		ModelAndView mov = new ModelAndView();
		UserAddress useraddress = null;
		if (editaddressid != null && editaddressid > 0) {
			useraddress = useraddressService.getAddress(editaddressid);
		}
		mov.addObject("useraddress", useraddress);
		List<Area> prolist = useraddressService.getAreaList(0);
		mov.addObject("prolist", prolist);
		if (useraddress != null) {
			if (useraddress.getProvince() != null && useraddress.getProvince() > 0) {
				List<Area> citylist = useraddressService.getAreaList(useraddress.getProvince());
				mov.addObject("citylist", citylist);
			}
			if (useraddress.getCity() != null && useraddress.getCity() > 0) {
				List<Area> arealist = useraddressService.getAreaList(useraddress.getCity());
				mov.addObject("arealist", arealist);
			}
		} else {
			if (prolist != null && prolist.size() > 0) {
				long proid = prolist.get(0).getId();
				List<Area> citylist = useraddressService.getAreaList(proid);
				mov.addObject("citylist", citylist);
				if (citylist != null && citylist.size() > 0) {
					long cityid = citylist.get(0).getId();
					List<Area> arealist = useraddressService.getAreaList(cityid);
					mov.addObject("arealist", arealist);
				}
			}
		}
		return mov;
	}
	
	
	@RequestMapping("/edituseraddress")
	public ModelAndView edituseraddress(UserAddress address,HttpSession session) {
		ModelAndView mov = new ModelAndView();
		long userid = CommonUtils.parseLong(String.valueOf(session.getAttribute("userid")), 0);
		if (address.getId() == null) {
			// 暂时注释，用户ID
			address.setUserId(userid);
			List<UserAddress> addresslist = useraddressService.getUserAddress(userid);
			if (addresslist != null && addresslist.size() > 0) {
				address.setIsDefult(0);
			} else {
				address.setIsDefult(1);
			}
			useraddressService.addUserAddress(address);
		} else {
			useraddressService.updateUserAddress(address);
		}
		List<UserAddress> addresslist = useraddressService.getUserAddress(userid);
		mov.addObject("addresslist", addresslist);
		mov.setViewName("/user/useraddress");
		return mov;
	}
	
	
	@RequestMapping("/checkuseraddress")
	public ModelAndView checkuseraddress(Long checkaddressid,HttpSession session) {
		ModelAndView mov = new ModelAndView();
		UserAddress useraddress = useraddressService.getAddress(checkaddressid);
		long userid = CommonUtils.parseLong(String.valueOf(session.getAttribute("userid")), 0);
		if (useraddress != null) {
			// 暂时注释，用户ID
			UserAddress defaultaddress = useraddressService.getUserDefaultAddress(userid);
			if(defaultaddress!=null){
				defaultaddress.setIsDefult(0);
				useraddressService.updateUserAddress(defaultaddress);
			}
			useraddress.setIsDefult(1);
			useraddressService.updateUserAddress(useraddress);
		}
		// 暂时注释，用户ID
		// long userid = (long) session.getAttribute("userid");
		List<UserAddress> addresslist = useraddressService.getUserAddress(userid);
		mov.addObject("addresslist", addresslist);
		mov.setViewName("/user/useraddress");
		return mov;
	};
}
