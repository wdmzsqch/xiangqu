package com.hz21city.xiangqu.controller.manage;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.pojo.ModelInfo;
import com.hz21city.xiangqu.service.manage.IMaModelService;

@Controller
@RequestMapping("/manage")
public class MaModelController extends MaBaseController{
	
	@Resource
	private IMaModelService maModelService;
	
	@RequestMapping("/model_list")
	public ModelAndView model_list(){
		ModelAndView mov = new ModelAndView();
		List<ModelInfo> list = maModelService.getAllModelList();
		mov.addObject("list", list);
		mov.addObject("fileurl", CommonUtils.getFileRootUrl());
		return mov;
	}
	
	@RequestMapping("/edit_model")
	public ModelAndView edit_model(ModelInfo modelInfo, @RequestParam(value = "modelimg", required = false) MultipartFile modelimg){
		ModelAndView mv = new ModelAndView("redirect:/manage/model_list");
		if (modelimg != null && !modelimg.isEmpty()) {
			modelInfo.setPic(getFilePathString(modelimg));
		}
		if (modelInfo.getId() == null) {
			maModelService.addModel(modelInfo);
		} else {
			maModelService.editModel(modelInfo);
		}
		return mv;
	}
	
	@RequestMapping("/del_model")
	public ModelAndView del_model(Long modelid){
		ModelAndView mv = new ModelAndView("redirect:/manage/model_list");
		ModelInfo modelInfo = maModelService.getModelInfo(modelid);
		if(modelInfo != null){
			maModelService.delModelInfo(modelid);
		}
		return mv;
	}
}
