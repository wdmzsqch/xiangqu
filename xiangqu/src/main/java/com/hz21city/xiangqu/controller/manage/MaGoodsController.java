package com.hz21city.xiangqu.controller.manage;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.pojo.ChannelInfo;
import com.hz21city.xiangqu.pojo.CheckInfo;
import com.hz21city.xiangqu.pojo.GoodsInfo;
import com.hz21city.xiangqu.pojo.GoodsProNetData;
import com.hz21city.xiangqu.service.manage.IMaGoodsService;
import com.hz21city.xiangqu.service.manage.IMaMissionService;
import com.hz21city.xiangqu.service.manage.IMaShopService;

@Controller
public class MaGoodsController extends MaBaseController {
	@Resource
	private IMaGoodsService maGoodsService;
	
	@Resource
	private IMaShopService maShopService;
	
	@Resource
	private IMaMissionService maMissionService;

	@RequestMapping("/goods_list")
	public ModelAndView goodsList(HttpSession session, String keywords, Integer type, Integer pageIndex, Long storeId, Long categoryId, Integer delFlg ) {
		ModelAndView mv = new ModelAndView("/manage/goods_list");
		if (pageIndex == null || pageIndex <= 0) {
			pageIndex = 1;
		}
		List<GoodsInfo> list = maGoodsService.getGoodsList(keywords, type, pageIndex, storeId, categoryId, delFlg);
		Integer checktype = CommonUtils.parseInt(String.valueOf(session.getAttribute("adminPrivilege")), 0);
		if(list != null && list.size() > 0){
			for (GoodsInfo goodsInfo : list) {
				CheckInfo checkinfo = maMissionService.getCheckInfoByAllWays(2, goodsInfo.getId(), checktype);
				if (checkinfo != null) {
					goodsInfo.setIsCheck(1);
				}else{
					goodsInfo.setIsCheck(0);
				}
				CheckInfo checkinfo1 = maMissionService.getCheckInfoByAllWays(2, goodsInfo.getId(), 1);
				CheckInfo checkinfo2 = maMissionService.getCheckInfoByAllWays(2, goodsInfo.getId(), 2);
				CheckInfo checkinfo3 = maMissionService.getCheckInfoByAllWays(2, goodsInfo.getId(), 3);
				CheckInfo checkinfo4 = maMissionService.getCheckInfoByAllWays(2, goodsInfo.getId(), 4);
				if (checkinfo1 != null) {
					goodsInfo.setComment(checkinfo1.getComment());
				}
				if (checkinfo2 != null) {
					goodsInfo.setComment1(checkinfo2.getComment());
				}
				if (checkinfo3 != null) {
					goodsInfo.setComment2(checkinfo3.getComment());
				}
				if (checkinfo4 != null) {
					goodsInfo.setComment3(checkinfo4.getComment());
				}
			}
		}
		mv.addObject("goodslist", list);
		mv.addObject("keywords", keywords);
		mv.addObject("type", type);
		mv.addObject("pageIndex", pageIndex);
		mv.addObject("storeId", storeId);
		mv.addObject("categoryId", categoryId);
		mv.addObject("delFlg", delFlg);
		mv.addObject("pageCount", maGoodsService.getGoodsListSize(keywords, type, storeId, categoryId, delFlg));
		mv.addObject("fileurl", CommonUtils.getFileRootUrl());
		mv.addObject("shoplist", maShopService.getAllShopList());
		mv.addObject("categorylist", maShopService.getAllCotegoryList());
		 mv.addObject("webrooturl", CommonUtils.getWebRootUrl());
		 mv.addObject("perCount", maGoodsService.getAllGoodSize());
		 mv.addObject("checktype", checktype);
		return mv;
	}

	@RequestMapping("/goods_detail")
	public ModelAndView goodsDetail(Long goodsid, Long storeid, String storename) {
		ModelAndView mv = new ModelAndView("/manage/goods_insert");
		if (goodsid != null && goodsid > 0) {
			mv.addObject("goods", maGoodsService.getGoodsById(goodsid));
			CheckInfo checkinfo1 = maMissionService.getCheckInfoByAllWays(2, goodsid, 1);
			CheckInfo checkinfo2 = maMissionService.getCheckInfoByAllWays(2, goodsid, 2);
			CheckInfo checkinfo3 = maMissionService.getCheckInfoByAllWays(2, goodsid, 3);
			CheckInfo checkinfo4 = maMissionService.getCheckInfoByAllWays(2, goodsid, 4);
			if(checkinfo1 != null && checkinfo2 != null && checkinfo3 != null && checkinfo4 != null){
				mv.addObject("allcheck", 1);
			}else{
				mv.addObject("allcheck", 0);
			}
		} else {
			GoodsInfo goodsInfo = new GoodsInfo();
			goodsInfo.setStoreId(storeid);
			goodsInfo.setStoreName(storename);
			mv.addObject("goods", goodsInfo);
			mv.addObject("allcheck", 0);
		}
		mv.addObject("fileurl", CommonUtils.getFileRootUrl());
		mv.addObject("categorylist", maGoodsService.getCotegoryList());
		return mv;
	}

	@RequestMapping("/goods_state")
	public void goodsState(Long[] goodids, Integer statetype, HttpServletResponse response) {
		maGoodsService.changeGoodsState(goodids, statetype);
		CommonUtils.setResponseStr("success", response);
	}

	@RequestMapping("/goods_edit")
	public ModelAndView addGoods(GoodsInfo goods, @RequestParam(value = "main", required = false) MultipartFile main,
			@RequestParam(value = "mainpic", required = false) MultipartFile mainpic,
			@RequestParam(value = "detailpic1", required = false) MultipartFile detailpic1,
			@RequestParam(value = "detailpic2", required = false) MultipartFile detailpic2,
			@RequestParam(value = "detailpic3", required = false) MultipartFile detailpic3,
			@RequestParam(value = "detailpic4", required = false) MultipartFile detailpic4,
			@RequestParam(value = "detailpic5", required = false) MultipartFile detailpic5,
			String[] proname,String[] provalue) {
		ModelAndView mv = new ModelAndView();
		if (goods == null) {
			mv.setViewName("/manage/goods_insert");
		} else {
			String content = goods.getDescription();
			Pattern p = Pattern.compile("<img\\s*([^>]*)>");
			Matcher m = p.matcher(content);
			while (m.find()) { 
	             String s0 = m.group(); 
	             String string2 =  s0.replace("/>", " style='width:98%;'/>");
	             content = content.replace(m.group(), string2);
			 } 
			content = "<body style=''><br/>" +
					"<div style='width: 100%;word-wrap: break-word;word-break: break-all;'>"+
					content + "</div></body>";
			goods.setDescription(content);
			if(proname!=null&&proname.length>0){
				List<GoodsProNetData>  goodspro = new ArrayList<GoodsProNetData>();
				if(proname.length==1){
					GoodsProNetData prodata = new GoodsProNetData();
					prodata.setName(proname[0]);
					StringBuffer provaluebuf = new StringBuffer();
					for(int i=0;i<provalue.length;i++){
						if(i==0){
							provaluebuf.append(provalue[i]);
						}else{
							provaluebuf.append(","+provalue[i]);
						}
					}
					prodata.setValue(provaluebuf.toString());
					goodspro.add(prodata);
				}else{
					for(int i =0 ;i<proname.length;i++){
						GoodsProNetData prodata = new GoodsProNetData();
						prodata.setName(proname[i]);
						prodata.setValue(provalue[i]);
						goodspro.add(prodata);
					}
				}
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").disableHtmlEscaping().create();;
				String projson = gson.toJson(goodspro);
				goods.setProperty(projson);
			}
			if (mainpic != null && !mainpic.isEmpty()) {
				goods.setMianPic(getFilePathString(mainpic));
			}
			if (detailpic1 != null && !detailpic1.isEmpty()) {
				goods.setDetailPic1(getFilePathString(detailpic1));
			}
			if (detailpic2 != null && !detailpic2.isEmpty()) {
				goods.setDetailPic2(getFilePathString(detailpic2));
			}
			if (detailpic3 != null && !detailpic3.isEmpty()) {
				goods.setDetailPic3(getFilePathString(detailpic3));
			}
			if (detailpic4 != null && !detailpic4.isEmpty()) {
				goods.setDetailPic4(getFilePathString(detailpic4));
			}
			if (detailpic5 != null && !detailpic5.isEmpty()) {
				goods.setDetailPic5(getFilePathString(detailpic5));
			}
			if (goods.getId() != null && goods.getId() > 0) {
				maGoodsService.editGoods(goods);
			} else {
				goods.setDelFlg(0);
				maGoodsService.addGoods(goods);
			}
			mv.setViewName("redirect:/manage/goods_list");
		}
		return mv;
	}
	
	/**
	 * 上架或下架type1上架0下架
	 * @param goodsid
	 * @return
	 */
	@RequestMapping("/changeonline")
	public void changeonline(Long goodsid, Integer type, HttpServletResponse response){
		GoodsInfo goodsinfo = maGoodsService.getGoodsById(goodsid);
		if(goodsinfo != null){
			if (type == 1) {
				goodsinfo.setDelFlg(1);
			}else{
				goodsinfo.setDelFlg(0);
			}
			maGoodsService.editGoods(goodsinfo);
		}
		CommonUtils.setResponseStr("success", response);
		return;
	}
	
	/**
	 * 下架
	 * @param goodsid
	 * @return
	 */
	@RequestMapping("/changeoutline")
	public ModelAndView changeoutline(Long goodsid){
		ModelAndView mv = new ModelAndView();
		GoodsInfo goodsinfo = maGoodsService.getGoodsById(goodsid);
		if(goodsinfo != null){
			goodsinfo.setDelFlg(0);
			maGoodsService.editGoods(goodsinfo);
		}
		mv.setViewName("redirect:/manage/goods_list");
		return mv;
	}
	
	@RequestMapping("/goods_del")
	public ModelAndView goods_del(Long goodsid){
		ModelAndView mv = new ModelAndView();
		GoodsInfo goodsinfo = maGoodsService.getGoodsById(goodsid);
		if(goodsinfo != null){
			goodsinfo.setDelFlg(-1);
			maGoodsService.editGoods(goodsinfo);
		}
		mv.setViewName("redirect:/manage/goods_list");
		return mv;
	}
	
	@RequestMapping("/goodsSort_up")
	public ModelAndView goodsSort_up(Long id){
		ModelAndView mv = new ModelAndView();
		Integer maxSort = maGoodsService.getGoodMaxSort();
		GoodsInfo goodsinfo = maGoodsService.getGoodsById(id);
		if(goodsinfo != null){
			goodsinfo.setSort(maxSort+1);
			maGoodsService.editGoods(goodsinfo);
		}
		mv.setViewName("redirect:/manage/goods_list");
		return mv;
	}
	
	@RequestMapping("/good2dCode")
	public ModelAndView good2dCode(Long id, HttpServletResponse response){
		ModelAndView mv = new ModelAndView("/manage/good2dCode");
//		GoodsInfo goodsinfo = maGoodsService.getGoodsById(id);
//		String url = CommonUtils.getWebRootUrl()+"user/goods?goodsid="+goodsinfo.getId()+"&goodstype="+goodsinfo.getPayType();
		List<ChannelInfo> list = maGoodsService.getChannelList();
		if(list != null && list.size() > 0){
			for (ChannelInfo channelInfo : list) {
//				CommonUtils.GetImageStr(url+"&channelCode="+channelInfo.getChannelCode(),response);
				int sellcount = maGoodsService.getChannelSellCount(channelInfo.getChannelCode(),id);
				channelInfo.setSellcount(sellcount);
				int yestoday_sellcount = maGoodsService.getChannelYesTodaySellCount(channelInfo.getChannelCode(),id);
				channelInfo.setYestoday_sellcount(yestoday_sellcount);
				int lookcount = maGoodsService.getChannelLookCount(channelInfo.getId(),id);
				channelInfo.setLookcount(lookcount);
				int yestody_lookcount = maGoodsService.getChannelYesTodayLookCount(channelInfo.getId(),id);
				channelInfo.setYestody_lookcount(yestody_lookcount);
			}
		}
		mv.addObject("list", list);
		mv.addObject("good_id", id);
		return mv;
	}
	
	@RequestMapping("/getImage")
	public void getImage(Long good_id, Long channel_id, HttpServletResponse response){
		GoodsInfo goodsinfo = maGoodsService.getGoodsById(good_id);
		String url = CommonUtils.getWebRootUrl()+"user/goods?goodsid="+goodsinfo.getId()+"&goodstype="+goodsinfo.getPayType();
		ChannelInfo channelInfo = maGoodsService.getChannlInfoByID(channel_id);
		CommonUtils.GetImageStr(url+"&channelCode="+channelInfo.getChannelCode(),response);
	}
}
