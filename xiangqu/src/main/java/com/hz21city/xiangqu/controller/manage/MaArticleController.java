package com.hz21city.xiangqu.controller.manage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.pojo.ArticleInfo;
import com.hz21city.xiangqu.service.ITimeEventService;
import com.hz21city.xiangqu.service.manage.IMaArticleService;

@Controller
@RequestMapping("/manage")
public class MaArticleController extends MaBaseController {
	@Resource
	private IMaArticleService maArticleService;
	@Resource
	private ITimeEventService timeEventService;

	@RequestMapping("/article_list")
	public ModelAndView article_list(Integer pageIndex, String keywords) {
		ModelAndView mov = new ModelAndView("/manage/article_list");
		int page = 1;
		if (pageIndex != null && pageIndex > 0) {
			page = pageIndex;
		}
		mov.addObject("keywords", keywords);
		mov.addObject("pageIndex", page);
		mov.addObject("article_list", maArticleService.getArticleListByPage(page, keywords));
		mov.addObject("pageCount", maArticleService.getArticleListSize(keywords));
		mov.addObject("webRootUrl", CommonUtils.getWebRootUrl());
		return mov;
	}

	@RequestMapping("/article_detail")
	public ModelAndView article_detail(Long id) {
		ModelAndView mov = new ModelAndView("/manage/article_insert");
		if (id != null && id > 0) {
			int count = maArticleService.getMissionUrlArticleCount("/xiangqu/user/article?id=" + id);
			mov.addObject("article", maArticleService.selectByPrimaryKey(id));
			mov.addObject("count", count);
		}
		return mov;
	}

	@RequestMapping("/article_edit")
	public ModelAndView article_edit(ArticleInfo articleInfo) {
		ModelAndView mov = new ModelAndView("redirect:/manage/article_list");
		String content = articleInfo.getDetail();
		Pattern p = Pattern.compile("<img\\s*([^>]*)>");
		Matcher m = p.matcher(content);
		while (m.find()) {
			String s0 = m.group();
			String string2 = s0.replace("/>", " style='width:98%;'/>");
			content = content.replace(m.group(), string2);
		}
		content = "<body style=''><br/><div style='width: 100%;word-wrap: break-word;word-break: break-all;'>" + content + "</div></body>";
		content = content.replaceAll("&lt;", "<");
		content = content.replaceAll("&gt;", ">");
		content = content.replaceAll("(<iframe[^>]*?)\\s+width\\s*=\\s*\\S+", "$1");
		Pattern piframe = Pattern.compile("<iframe\\s*([^>]*)>");
		Matcher miframe = piframe.matcher(content);
		while (miframe.find()) {
			String s0 = miframe.group();
			String string2 = s0.replace(">", " style='width:98%;'>");
			content = content.replace(miframe.group(), string2);
		}
		articleInfo.setDetail(content);
		if (articleInfo.getId() == null) {
			maArticleService.addArticleInfo(articleInfo);
		} else {
			maArticleService.updateArticleInfo(articleInfo);
		}
		return mov;
	}

	@RequestMapping("/lottery_list")
	public ModelAndView lottery_list() {
		ModelAndView mov = new ModelAndView("/manage/lottery_list");
		mov.addObject("lotteryList", timeEventService.getLotteryList());
		Properties properties = new Properties();
		try {
			InputStream inStream = new FileInputStream(new File(CommonUtils.getLotteryProp()));
			properties.load(inStream);
			inStream.close();// 一定要在修改值之前关闭fis
		} catch (IOException e) {
			e.printStackTrace();
		}
		mov.addObject("probability", properties.getProperty("probability"));
		mov.addObject("one", properties.getProperty("one"));
		mov.addObject("two", properties.getProperty("two"));
		mov.addObject("three", properties.getProperty("three"));
		return mov;
	}

	@RequestMapping("/change_probability")
	public ModelAndView changeProbability(String probability, String one, String two, String three) {
		Properties prop = new Properties();
		try {
			File propFile = new File(CommonUtils.getLotteryProp());
			InputStream inStream = new FileInputStream(propFile);
			prop.load(inStream);
			inStream.close();// 一定要在修改值之前关闭fis
			OutputStream fos = new FileOutputStream(propFile);
			prop.setProperty("probability", probability);
			prop.setProperty("one", one);
			prop.setProperty("two", two);
			prop.setProperty("three", three);
			prop.store(fos, "comment");
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ModelAndView mov = new ModelAndView("redirect:/manage/lottery_list");
		return mov;
	}

	@ResponseBody
	@RequestMapping("/clear_table")
	public String clearTable() {
		timeEventService.clearTable();
		return "success";
	}
}
