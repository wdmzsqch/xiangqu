/**
 * 
 */
package com.hz21city.xiangqu.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hz21city.xiangqu.pojo.UserInfo;
import com.hz21city.xiangqu.service.IUserService;

import net.sf.json.JSONObject;

/**
 * @author qiuch
 *
 */
public class WxAuth {

	HttpServletRequest request;
	HttpServletResponse response;
	IUserService userService;
	String openId, accessToken;
	String unionId, code = null;

	public WxAuth(HttpServletRequest request, HttpServletResponse response, IUserService userService) {
		this.request = request;
		this.response = response;
		this.userService = userService;
	}

	public UserInfo wxAuth() {
		code = request.getParameter("code");
		// String openid = String.valueOf(request.getSession().getAttribute("openid"));
		// String accesstoken = String.valueOf(request.getSession().getAttribute("accesstoken"));
		// System.out.println("param code---" + code);
		// System.out.println("param openid---" + openid);
		// System.out.println("param accesstoken---" + accesstoken);
		if (!CommonUtils.isEmptyString(code)) {
			if (getAccessToken(code)) {
				if (!CommonUtils.isEmptyString(unionId)) {
					return getUserByUnionid(this.unionId);
				} else if (getUnionID()) {
					return getUserByUnionid(this.unionId);
				}
			}
		}
		reAuth();
		return null;
	}

	private boolean getAccessToken(String code) {
		openId = null;
		accessToken = null;
		unionId = null; // 不管如何先清空数据
		String accessUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + Constants.WX_APPID + "&secret=" + Constants.WX_APPSECRET + "&code=" + code + "&grant_type=authorization_code";
		JSONObject jsonObject = CommonUtils.httpsRequest(accessUrl, "GET", null);
		// System.out.println("get unoin id json: " + jsonObject);
		if (null != jsonObject) {
			try {
				openId = jsonObject.getString("openid");
				accessToken = jsonObject.getString("access_token");
				unionId = jsonObject.getString("unionid");
			} catch (Exception e) {
			}
		}
		// System.out.println("openId----------" + openId);
		// System.out.println("accessToken----------" + accessToken);
		// System.out.println("unionId----------" + unionId);
		if (!CommonUtils.isEmptyString(openId)) {
			request.getSession().setAttribute("openid", openId);
			// request.getSession().setAttribute("accesstoken", accessToken);
			return true;
		}
		return false;
	}

	private boolean getUnionID() {
		unionId = null;// 不管如何，重取前置空
		String UnionURL = "https://api.weixin.qq.com/sns/userinfo?lang=zh_CN&openid=" + openId + "&access_token=" + accessToken;
		JSONObject jsonObject = CommonUtils.httpsRequest(UnionURL, "GET", null);
		// System.out.println("get unoin id json: " + jsonObject);
		if (null != jsonObject) {
			try {
				unionId = jsonObject.getString("unionid");
			} catch (Exception e) {
			}
		}
		if (!CommonUtils.isEmptyString(unionId)) {
			// System.out.println("get unionid ===========" + unionId);
			return true;
		} else {
			// System.out.println("cant get unionid");
			return false;
		}
	}

	private UserInfo getUserByUnionid(String unionId) {
		UserInfo userInfo = null;
		try {
			userInfo = (UserInfo) userService.thirdPartLogin(2, unionId).get("user");
		} catch (Exception e) {
			userInfo = (UserInfo) userService.getThirdPartUser(2, unionId);
		}
		if (userInfo != null) {
			request.getSession().setAttribute("userid", userInfo.getId());
			return userInfo;
		} else {
			// System.out.println("userinfo error");
			return null;
		}
	}

	private String getFullURLWithParam(HttpServletRequest request) {
		String requestUrl = request.getRequestURL().toString() + "?";
		Map<String, String[]> map = request.getParameterMap();
		Set<Entry<String, String[]>> keSet = map.entrySet();
		for (Iterator<Entry<String, String[]>> itr = keSet.iterator(); itr.hasNext();) {
			Map.Entry<String, String[]> me = itr.next();
			String key = me.getKey(); // 获取参数名
			String value = me.getValue()[0]; // 获取参数值
			requestUrl = requestUrl + key + "=" + value + "&";
		}
		try {
			requestUrl = URLEncoder.encode(requestUrl, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return requestUrl;
	}

	public void reAuth() {
		// 清空session，重新获取授权
		request.getSession().setAttribute("openid", "");
		request.getSession().setAttribute("accesstoken", "");
		try {
			response.sendRedirect("./wxsq?reurl=" + getFullURLWithParam(request));// 返回到配置文件中定义的路径
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
