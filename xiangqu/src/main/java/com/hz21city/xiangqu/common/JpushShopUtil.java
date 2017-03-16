/**
 * 
 */
package com.hz21city.xiangqu.common;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

/**
 * 客户端的推送类
 * 
 * @author qiuch
 *
 */
public class JpushShopUtil {

	private static final String appKey = "a9abdd0044e5c283f0806b7b";
	private static final String masterSecret = "f07e5b0c324bf509c3c935ff";
	private JPushClient jpushClient;

	public JpushShopUtil() {
		jpushClient = new JPushClient(masterSecret, appKey, 3);
	}

	/**
	 * 系统推送
	 * 
	 * @param title
	 * @param content
	 * @param registrationID
	 */
	public void sendMsgPush(String registrationID, String content) {
		try {
			PushResult result = jpushClient.sendMessageWithRegistrationID("您有一条新消息", content, registrationID);
			System.out.println(result.toString());
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIRequestException e) {
			System.out.println("HTTP Status: " + e.getStatus() + "Error Code: " + e.getErrorCode() + "Error Message: " + e.getErrorMessage() + "Msg ID: " + e.getMsgId());
		}
	}

	/**
	 * 给单人发公告
	 * @param registrationID
	 * @param content
	 */
	public void sendPushSolo(String registrationID, String content){
		try {
			PushPayload payload = PushPayload.newBuilder().setPlatform(Platform.android_ios()).setNotification(Notification.alert(content)).setAudience(Audience.registrationId(registrationID)).build();
			PushResult result = jpushClient.sendPush(payload);
			System.out.println(result.toString());
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIRequestException e) {
			System.out.println("HTTP Status: " + e.getStatus() + "Error Code: " + e.getErrorCode() + "Error Message: " + e.getErrorMessage() + "Msg ID: " + e.getMsgId());
		}
	}
	/**
	 * 发公告
	 * 
	 * @param alert
	 */
	public void sendPush2All(String alert) {
		try {
			PushResult result = jpushClient.sendNotificationAll(alert);
			System.out.println(result.toString());
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIRequestException e) {
			System.out.println("HTTP Status: " + e.getStatus() + "Error Code: " + e.getErrorCode() + "Error Message: " + e.getErrorMessage() + "Msg ID: " + e.getMsgId());
		}
	}
}
