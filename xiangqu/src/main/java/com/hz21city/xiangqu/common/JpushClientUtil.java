/**
 * 
 */
package com.hz21city.xiangqu.common;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
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
public class JpushClientUtil {

	private static final String appKey = "9ebf0813d1bfe54bebc828aa";
	private static final String masterSecret = "3b71bb191f58f24193ff8b9b";
	private JPushClient jpushClient;

	public JpushClientUtil() {
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

	public void sendPushSolo(String registrationID, String content) {
		try {
			PushPayload payload = PushPayload.newBuilder().setPlatform(Platform.android_ios()).setNotification(Notification.alert(content)).setAudience(Audience.registrationId(registrationID))
					.setOptions(Options.newBuilder().setApnsProduction(true).build()).build();
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
		// long time1 = System.currentTimeMillis();
		try {
			// PushResult result = jpushClient.sendNotificationAll(alert);
			PushPayload payload = PushPayload.newBuilder().setPlatform(Platform.all()).setNotification(Notification.alert(alert)).setAudience(Audience.all())
					.setOptions(Options.newBuilder().setApnsProduction(true).build()).build();
			PushResult result = jpushClient.sendPush(payload);
			System.out.println(result.toString());
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIRequestException e) {
			System.out.println("HTTP Status: " + e.getStatus() + "Error Code: " + e.getErrorCode() + "Error Message: " + e.getErrorMessage() + "Msg ID: " + e.getMsgId());
		}
		// long time2 = System.currentTimeMillis();
		// System.out.println(time2-time1);
	}
}
