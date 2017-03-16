package com.hz21city.xiangqu.controller.wticket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hz21city.xiangqu.common.CommonUtils;

public class WUSessionInterceptorAction extends HandlerInterceptorAdapter {
	public String[] allowUrls;

	public void setAllowUrls(String[] allowUrls) {
		this.allowUrls = allowUrls;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		String requestUrl = request.getRequestURI().replace(request.getContextPath(), "");
		if (null != allowUrls && allowUrls.length >= 1)
			for (String url : allowUrls) {
				if (requestUrl.contains(url)) {
					return true;
				}
			}
		long id = CommonUtils.parseInt(String.valueOf(request.getSession().getAttribute("adminWticketUserId")), 0);
		if (id == 0) {
			// 未登录 跳转到登录页面
			response.sendRedirect("./login.jsp");// 返回到配置文件中定义的路径
			return false;
		}
		return true;
	}

	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
	}
}
