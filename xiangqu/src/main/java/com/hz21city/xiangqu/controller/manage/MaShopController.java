package com.hz21city.xiangqu.controller.manage;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.pojo.Area;
import com.hz21city.xiangqu.pojo.Industry;
import com.hz21city.xiangqu.pojo.ShopInfo;
import com.hz21city.xiangqu.service.manage.IMaShopService;

@Controller
public class MaShopController extends MaBaseController {
	@Resource
	private IMaShopService maShopService;

	@RequestMapping("/merchants_list")
	public ModelAndView merchants_list(String keywords, Integer pageIndex, Long industry_id, Long province, Long city, Long area) {
		ModelAndView mv = new ModelAndView("/manage/merchants_list");
		int page = 1;
		if (pageIndex != null && pageIndex > 0) {
			page = pageIndex;
		}
		if(province != null && province != 0){
			List<Area> prolist  = maShopService.getAreaListByParentid(Long.valueOf(0));
			mv.addObject("prolist", prolist);
			List<Area> citylist = maShopService.getAreaListByParentid(province);
			mv.addObject("citylist", citylist);
			if(city != null && city != 0){
				List<Area> arealist = maShopService.getAreaListByParentid(city);
				mv.addObject("arealist", arealist);
			}else{
				List<Area> arealist = maShopService.getAreaListByParentid(citylist.get(0).getId());
				mv.addObject("arealist", arealist);
			}
			
		}else{
			List<Area> prolist  = maShopService.getAreaListByParentid(Long.valueOf(0));
			mv.addObject("prolist", prolist);
			if(prolist != null && prolist.size() > 0){
				List<Area> citylist = maShopService.getAreaListByParentid(prolist.get(0).getId());
				mv.addObject("citylist", citylist);
				if(citylist != null && citylist.size() > 0){
					List<Area> arealist = maShopService.getAreaListByParentid(citylist.get(0).getId());
					mv.addObject("arealist", arealist);
				}
			}
		}
		List<ShopInfo> shoplist = maShopService.getShopList(keywords, page, industry_id, province, city, area);
		if(shoplist != null && shoplist.size() > 0){
			for (ShopInfo shopInfo : shoplist) {
				//得到省
				if(shopInfo.getProvince() != null && shopInfo.getProvince() != 0){
					Area proa = maShopService.getArea(shopInfo.getProvince());
					if(proa != null){
						shopInfo.setProname(proa.getName());
					}
				}
				//得到市
				if(shopInfo.getCity() != null && shopInfo.getCity() != 0){
					Area citya = maShopService.getArea(shopInfo.getCity());
					if(citya != null){
						shopInfo.setCityname(citya.getName());
					}
				}
				//得到区
				if(shopInfo.getArea() != null && shopInfo.getArea() != 0){
					Area areaA = maShopService.getArea(shopInfo.getArea());
					if(areaA != null){
						shopInfo.setAreaname(areaA.getName());
					}
				}
			}
		}
		mv.addObject("province", province);
		mv.addObject("city", city);
		mv.addObject("area", area);
		List<Industry> industrylist = maShopService.getIndustryList();
		mv.addObject("shoplist", shoplist);
		mv.addObject("industrylist", industrylist);
		mv.addObject("pageCount", maShopService.getShopListSize(keywords, industry_id, province, city, area));
		mv.addObject("pageIndex", page);
		mv.addObject("keywords", keywords);
		mv.addObject("industry_id", industry_id);
		 mv.addObject("perCount", maShopService.getAllShopSize());
		return mv;
	}

	@RequestMapping("/merchant_detail")
	public ModelAndView shopDetail(Long shopid) {
		ModelAndView mv = new ModelAndView("/manage/merchants_insert");
		if (shopid != null && shopid > 0) {
			ShopInfo shopinfo = maShopService.getShopByid(shopid);
			mv.addObject("shop", shopinfo);
			mv.addObject("fileurl", CommonUtils.getFileRootUrl());
			List<Area> prolist  = maShopService.getAreaListByParentid(Long.valueOf(0));
			mv.addObject("prolist", prolist);
			if(shopinfo.getProvince() != null && shopinfo.getProvince() != 0){
				List<Area> citylist = maShopService.getAreaListByParentid(shopinfo.getProvince());
				mv.addObject("citylist", citylist);
				if(shopinfo.getCity() != null && shopinfo.getCity() != 0){
					List<Area> arealist = maShopService.getAreaListByParentid(shopinfo.getCity());
					mv.addObject("arealist", arealist);
				}else{
					List<Area> arealist = maShopService.getAreaListByParentid(citylist.get(0).getId());
					mv.addObject("arealist", arealist);
				}
			}else{
				List<Area> citylist = maShopService.getAreaListByParentid(prolist.get(0).getId());
				mv.addObject("citylist", citylist);
				if(citylist != null && citylist.size() > 0){
					List<Area> arealist = maShopService.getAreaListByParentid(citylist.get(0).getId());
					mv.addObject("arealist", arealist);
				}
			}
		}else{
			List<Area> prolist  = maShopService.getAreaListByParentid(Long.valueOf(0));
			mv.addObject("prolist", prolist);
			if(prolist != null && prolist.size() > 0){
				List<Area> citylist = maShopService.getAreaListByParentid(prolist.get(0).getId());
				mv.addObject("citylist", citylist);
				if(citylist != null && citylist.size() > 0){
					List<Area> arealist = maShopService.getAreaListByParentid(citylist.get(0).getId());
					mv.addObject("arealist", arealist);
				}
			}
		}
		List<Industry> industrylist = maShopService.getIndustryList();
		mv.addObject("industrylist", industrylist);
		return mv;
	}

	@RequestMapping("/edit_merchant")
	public ModelAndView edit_merchant(ShopInfo shopInfo, @RequestParam(value = "shoppic", required = false) MultipartFile shoppic) {
		ModelAndView mv = new ModelAndView("redirect:/manage/merchants_list");
		if (shoppic != null && !shoppic.isEmpty()) {
			shopInfo.setPic(getFilePathString(shoppic));
		}
		shopInfo.setPassword(CommonUtils.MD5(shopInfo.getPassword()));
		maShopService.editShop(shopInfo);
		return mv;
	}

	@RequestMapping("/del_merchant")
	public ModelAndView delShop(Long shopid) {
		ModelAndView mv = new ModelAndView("redirect:/manage/merchants_list");
		maShopService.delShop(shopid);
		return mv;
	}
	
	@RequestMapping("/getAreaByParentId")
	public void selectArea(Long parentid, Integer type, HttpServletResponse response){
		HashMap<String,Object> result = new HashMap<String,Object>();
		List<Area> list = maShopService.getAreaListByParentid(parentid);
		if(type == 1){
			if(list != null && list.size() > 0){
				List<Area> arealist = maShopService.getAreaListByParentid(list.get(0).getId());
				result.put("arealist", arealist);
			}
			result.put("citylist", list);
			CommonUtils.setMaptoJson(response, result);
		}else{
			result.put("arealist", list);
			CommonUtils.setMaptoJson(response, result);
		}
		
	}
}
