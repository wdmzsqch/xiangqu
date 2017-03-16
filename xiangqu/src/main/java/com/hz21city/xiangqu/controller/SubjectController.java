package com.hz21city.xiangqu.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.pojo.SubjectInfo;
import com.hz21city.xiangqu.service.ICommentService;
import com.hz21city.xiangqu.service.ISubjectService;

@Controller
@RequestMapping("/user")
public class SubjectController {

	@Resource
	private ISubjectService subjectService;
	
	@Resource
	private ICommentService commentService;
	
	@RequestMapping("/subject")
	public ModelAndView subject(){
		ModelAndView mov = new ModelAndView("/user/subject");
		List<SubjectInfo> list = subjectService.getAllSubjectList();
		mov.addObject("list", list);
		mov.addObject("fileurl", CommonUtils.getFileRootUrl());
		return mov;
	}
	
	@RequestMapping("/subjectdetail")
	public ModelAndView subjectdetail(Long id, Long shareuserid, HttpSession session){
		ModelAndView mov = new ModelAndView("/user/subjectdetail");
		mov.addObject("subject", subjectService.getSubjectById(id));
		mov.addObject("commentcount", commentService.getCommentListCount(id, 3));
		mov.addObject("webrooturl", CommonUtils.getWebRootUrl());
		mov.addObject("shareuserid", shareuserid);
		mov.addObject("id", id);
		return mov;
	}
	
	@RequestMapping("/subjectcode")
	public ModelAndView subjectcode(Long subject_id, HttpSession session){
		ModelAndView mov = new ModelAndView();
		long userid = CommonUtils.parseLong(String.valueOf(session.getAttribute("userid")), 0);
		mov.setViewName("redirect:/user/subjectdetail?id=" + subject_id + "&shareuserid=" + userid);
		return mov;
	}
}
