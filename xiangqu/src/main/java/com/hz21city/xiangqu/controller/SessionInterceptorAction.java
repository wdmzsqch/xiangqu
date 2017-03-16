package com.hz21city.xiangqu.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.common.WxAuth;
import com.hz21city.xiangqu.service.IUserService;

public class SessionInterceptorAction extends HandlerInterceptorAdapter {

	public String[] allowUrls;
	@Resource
	private IUserService userService;

	public void setAllowUrls(String[] allowUrls) {
		this.allowUrls = allowUrls;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		String requestUrl = request.getRequestURL().toString();
		if (null != allowUrls && allowUrls.length >= 1)
			for (String url : allowUrls) {
				if (requestUrl.contains(url)) {
					return true;
				}
			}
		// System.out.println(requestUrl);
		long id = CommonUtils.parseInt(String.valueOf(request.getSession().getAttribute("userid")), 0);
		// System.out.println(id);
		if (id == 0) {
			String userAgent = request.getHeader("user-agent");
			if (userAgent.contains("MicroMessenger")) {
				if (requestUrl.contains("weixin.qq.com")) {
					return true;
				} else {
					return new WxAuth(request, response, userService).wxAuth() != null;
				}
			} else {
				// 不是微信 未登录 跳转到登录页面
				response.sendRedirect("./index");// 返回到配置文件中定义的路径
				return false;
			}
		}
		return true;
	}
}
