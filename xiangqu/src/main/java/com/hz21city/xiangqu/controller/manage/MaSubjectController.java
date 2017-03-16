package com.hz21city.xiangqu.controller.manage;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.pojo.SubjectInfo;
import com.hz21city.xiangqu.service.manage.IMaSubjectService;

@Controller
public class MaSubjectController extends MaBaseController {
	
	@Resource
	private IMaSubjectService maSubjectService;
	
	@RequestMapping("/subject_list")
	public ModelAndView subject_list(Integer pageIndex){
		ModelAndView mov = new ModelAndView("/manage/subject_list");
		int page = 1;
		if (pageIndex != null && pageIndex > 0) {
			page = pageIndex;
		}
		mov.addObject("subject_list", maSubjectService.getSubjectListByPage(page));
		mov.addObject("pageCount", maSubjectService.getSubjectListSize());
		mov.addObject("fileUrl", CommonUtils.getFileRootUrl());
		mov.addObject("webrooturl", CommonUtils.getWebRootUrl());
		mov.addObject("perCount", maSubjectService.getAllSubjectSize());
		return mov;
	}
	
	@RequestMapping("/subject_detail")
	public ModelAndView subject_detail(Long id){
		ModelAndView mov = new ModelAndView("/manage/subject_insert");
		if(id != null && id > 0){
			mov.addObject("subject", maSubjectService.selectByPrimaryKey(id));
			mov.addObject("fileUrl", CommonUtils.getFileRootUrl());
		}
		return mov;
	}
	
	@RequestMapping("/subject_edit")
	public ModelAndView subject_edit(SubjectInfo subject, @RequestParam(value = "subjectpic", required = false) MultipartFile subjectpic){
		ModelAndView mov = new ModelAndView("redirect:/manage/subject_list");
		if (subjectpic != null && !subjectpic.isEmpty()) {
			subject.setPic(getFilePathString(subjectpic));
		}
		subject.setAddTime(new Date());
		if(subject.getId() != null){
			maSubjectService.updateSubjectInfo(subject);
		}else{
			maSubjectService.addSubjectInfo(subject);
		}
		return mov;
	}
	
	@RequestMapping("/del_subject")
	public ModelAndView del_subject(Long id){
		ModelAndView mov = new ModelAndView("redirect:/manage/subject_list");
		SubjectInfo subject = maSubjectService.selectByPrimaryKey(id);
		if(subject != null){
			maSubjectService.deleteByPrimaryKey(id);
		}
		return mov;
	}
}
