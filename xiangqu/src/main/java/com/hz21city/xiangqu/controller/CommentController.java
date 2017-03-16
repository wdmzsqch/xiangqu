package com.hz21city.xiangqu.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.pojo.Comment;
import com.hz21city.xiangqu.pojo.UserInfo;
import com.hz21city.xiangqu.service.ICommentService;
import com.hz21city.xiangqu.service.IUserService;

@Controller
@RequestMapping("/user")
public class CommentController {

	@Resource
	private ICommentService commentService;
	@Resource
	private IUserService userService;

	@RequestMapping("/comment")
	public ModelAndView comment(Long relative_id, Integer type, HttpSession session) {
		ModelAndView mov = new ModelAndView();
		List<Comment> commentlist = commentService.getCommentList(relative_id, type);
		mov.addObject("commentlist", commentlist);
		mov.addObject("relative_id", relative_id);
		mov.addObject("type", type);
		mov.addObject("fileRootUrl", CommonUtils.getFileRootUrl());
		UserInfo userinfo = userService.getUserInfoById(CommonUtils.parseLong(String.valueOf(session.getAttribute("userid")), 0));
		if (userinfo != null && !CommonUtils.isEmptyString(userinfo.getMoblie())) {
			mov.addObject("hasMoblie", 1);
		} else {
			mov.addObject("hasMoblie", 0);
		}
		return mov;
	}

	@RequestMapping("/publishcontent")
	@ResponseBody
	public String publishcontent(Long relative_id, Integer type, String content, HttpSession session) {
		Comment comment = new Comment();
		long userid = CommonUtils.parseLong(String.valueOf(session.getAttribute("userid")), 0);
		comment.setContent(content);
		comment.setPublishTime(new Date());
		comment.setRelativeId(relative_id);
		comment.setRelativeType(type);
		comment.setUserId(userid);
		commentService.addComment(comment);
		return "success";
	}
}
