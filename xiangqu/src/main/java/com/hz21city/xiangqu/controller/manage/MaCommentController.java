package com.hz21city.xiangqu.controller.manage;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hz21city.xiangqu.service.manage.IMaCommentService;

@Controller
public class MaCommentController extends MaBaseController {

	@Resource
	private IMaCommentService maCommentService;

	@RequestMapping("/comment_list")
	public ModelAndView article_list(Integer pageIndex, String keywords) {
		ModelAndView mov = new ModelAndView("/manage/comment_list");
		int page = 1;
		if (pageIndex != null && pageIndex > 0) {
			page = pageIndex;
		}
		mov.addObject("keywords", keywords);
		mov.addObject("pageIndex", page);
		mov.addObject("clist", maCommentService.getCommentList(page, keywords));
		mov.addObject("pageCount", maCommentService.getCommentListSize(keywords));
		return mov;
	}

	@RequestMapping("/comment_del")
	public ModelAndView article_edit(Long cid) {
		ModelAndView mov = new ModelAndView("redirect:/manage/comment_list");
		maCommentService.delComment(cid);
		return mov;
	}
}
