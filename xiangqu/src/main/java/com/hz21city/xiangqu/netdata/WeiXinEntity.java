package com.hz21city.xiangqu.netdata;

import com.google.gson.JsonObject;
import com.hz21city.xiangqu.common.CommonUtils;
import com.hz21city.xiangqu.common.Constants;

public class WeiXinEntity {

	// 获取access_token的接口地址（GET） 限200（次/天）
		public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
		
		public final static String message_url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
		
		// 获取jsapi_ticket_url的接口地址（GET） 限200（次/天）
		public final static String jsapi_ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
		
		// 自定义菜单创建接口
		public final static String create_menu_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
		
		public AccessToken accessToken;
		
		// 自定义菜单创建接口
		public int createMenu(String jsonMenu) {
			int result = -1;
			AccessToken token = getAccessToken();
			if (accessToken != null && accessToken.isExpires()) {
				// 拼装url
				String url = create_menu_url.replace("ACCESS_TOKEN", token.getAccess_token());
				// 调用接口创建菜单
				JsonObject jsonObject = CommonUtils.httpRequest(url, "POST", jsonMenu);
				// 请求成功
				if (null != jsonObject) {
					result = jsonObject.get("errcode").getAsInt();
				}
			}
			return result;
		}

		public int deleteMenu() {
			int result = -1;
			AccessToken token = getAccessToken();
			if (accessToken != null && accessToken.isExpires()) {
				// 拼装url
				String url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN".replace("ACCESS_TOKEN", token.getAccess_token());
				// 调用接口创建菜单
				JsonObject jsonObject = CommonUtils.httpRequest(url, "POST", "");
				// 请求成功
				if (null != jsonObject) {
					result = jsonObject.get("errcode").getAsInt();
				}
			}
			return result;
		}
		
		/**
		 * 取得微信access_token
		 * 
		 * @return
		 */
		public AccessToken getAccessToken() {
			if (accessToken != null && accessToken.isExpires()) {
				return this.accessToken;
			}
			String requestUrl = access_token_url.replace("APPID", Constants.WX_APPID).replace("APPSECRET", Constants.WX_APPSECRET);
			JsonObject jsonObject = CommonUtils.httpRequest(requestUrl, "GET", null);
			// 请求成功
			if (null != jsonObject) {
				try {
					String access_token = jsonObject.get("access_token").getAsString();
					String jsapi_ticketurl = jsapi_ticket_url.replace("ACCESS_TOKEN", access_token);
					JsonObject tickObject = CommonUtils.httpRequest(jsapi_ticketurl, "GET", null);
					String jsapi_ticke = tickObject.get("ticket").getAsString();
					accessToken = new AccessToken(jsonObject.get("access_token").getAsString(), jsonObject.get("expires_in").getAsInt(),jsapi_ticke);
				} catch (Exception e) {
					accessToken = null;
				}
			}
			return this.accessToken;
		}

		public String getJsTicket() {
			AccessToken token = getAccessToken();
			if (accessToken != null && accessToken.isExpires()) {
				return token.getJsapi_ticket();
			}
			return null;
		}
		
		/**
		 *主菜单
		 * 
		 * @return
		 */
		public static String getMainMenu() {
			StringBuffer buffer = new StringBuffer();
			buffer.append("");
			// buffer.append("饭宝君，就可以分享给您附近的各种外卖啦。");
			return buffer.toString();
		}

		// 发送客服文本信息
		public int sendTextMessage(String touser, String content) {
			int result = 0;
			AccessToken token = getAccessToken();
			if (accessToken != null && accessToken.isExpires()) {
				// 拼装url
				String url = message_url.replace("ACCESS_TOKEN", token.getAccess_token());
				// 将菜单对象转换成json字符串
				String jsonMsg = "{\"touser\":\"" + touser + "\",\"msgtype\":\"text\",\"text\":{\"content\":\"" + content + "\"}}";
				// 调用接口创建菜单
				JsonObject jsonObject = CommonUtils.httpRequest(url, "POST", jsonMsg);
				// 请求成功
				if (null != jsonObject) {
					if (0 != jsonObject.get("errcode").getAsInt()) {
						result = jsonObject.get("errcode").getAsInt();
					}
				}
			}
			return result;
		}
}
