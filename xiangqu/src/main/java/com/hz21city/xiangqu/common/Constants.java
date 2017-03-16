/**
 * 
 */
package com.hz21city.xiangqu.common;

import java.util.HashMap;

public class Constants {

	public static final HashMap<String, Object> SUCCESS_RESULT_MAP = new HashMap<String, Object>() {

		private static final long serialVersionUID = 1L;

		{
			put("code", 1);
			put("message", "操作成功");
		}
	};
	public static final String EXCEPTION = "{\"message\":\"网络状况不佳，请稍后再试\",\"code\":100}";
	public static final String PAGE_ERR_KEY = "errmsg";
	public static final int USER_ORDER_PAGESIZE = 10;
	public static final String WX_APPID = "wxc125a3cdb870ef00";
	public static final String WX_APPSECRET = "2ea5cdaebc351106194c6f5f8b4ac9e9";
	public static final String WX_PARTNER = "1272956101";
	public static final String WX_PARTNERKEY = "HZ21chengXiangqu100Weixinmiyao21";
	// 邀请人收获60积分
	public static final float INVITEINCOME = 100f;
	// 被邀请人收获50积分
	public static final float BEINVITEINCOME = 60f;
	// 用户相关错误定义
	public static final String USER_EXIST_EXCEPTION = "{\"message\":\"手机号已被使用，无法注册\",\"code\":89}";
	public static final String PHONE_EXIST_EXCEPTION = "{\"message\":\"手机号已被使用\",\"code\":88}";
	public static final String VCODE_ERR_EXCEPTION = "{\"message\":\"验证码错误或已失效\",\"code\":87}";
	public static final String VCODE_REQ_EXCEPTION = "{\"message\":\"验证码获取时间过短，请稍后再试\",\"code\":86}";
	public static final String USER_ERR_EXCEPTION = "{\"message\":\"用户信息有误，请稍后再试\",\"code\":85}";
	public static final String USER_PWD_EXCEPTION = "{\"message\":\"密码错误，请确认后重试\",\"code\":84}";
	public static final String USER_NAME_EXCEPTION = "{\"message\":\"用户不存在，请确认用户名或者手机号\",\"code\":83}";
	public static final String USER_OLD_PWD_EXCEPTION = "{\"message\":\"原密码错误，请确认后重试\",\"code\":82}";
	public static final String COMBINE_USER = "{\"message\":\"该手机号已和其他微信号绑定\",\"code\":101}";
	//加密salt
	public static final String SALT = "hz21xiangqu";
}
