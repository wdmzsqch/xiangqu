<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, user-scalable=no">
<title>我的邀请码</title>
<link rel="stylesheet" type="text/css" href="css/user.css">
<link rel="stylesheet" type="text/css" href="css/common.css">
<script type="text/javascript" src="./js/jquery-1.11.3.min.js"></script>
<script src="js/jquery.lazyload.js?v=1.9.1"></script>
</head>
<body>
	<div
		style="background: #fff; width: 100%; height: auto; margin-top: 8px; text-align:  center;">
		<div style="width: 100%; height: 40px; line-height: 40px; text-align: center; color: #00d1bc; font-size: 18px;">我的邀请码：${userinfo.invietCode }</div>
		<img src="imgs/downloadapp.png" style="width: 80%;">
		<div style="width: 80%; font-size: 14px; text-align: center; margin: 0 auto;color: #f37d55;">每邀请一位即可获得享趣100积分奖励！</div>
		<div style="width: 100%; height: 10px;"></div>
		<div style="width: 96%; font-size: 14px; text-align: left; margin: 0 auto;">如何邀请：</div>
		<div style="width: 88%; font-size: 14px; text-align: left; margin: 0 auto; line-height: 24px; font-size: 13px;">第一步：先引导用户扫二维码下载享趣100APP;</div>
		<div style="width: 88%; font-size: 14px; text-align: left; margin: 0 auto; line-height: 24px; font-size: 13px;">第二步：被邀请者登录APP注册输入你的邀请码即可获得奖励;</div>
		<div style="width: 100%; height: 10px;"></div>
<!-- 		<div style="width: 39%; height: 32px; line-height: 32px; background: #00d1bc; color: #fff; font-size: 15px; text-align: center; margin: 0 auto; border-radius: 16px;">分享二维码</div> -->
<!-- 		<div style="width: 80%; font-size: 14px; text-align: left; margin: 0 auto;color: #00d1bc;">我的推广地址:</div> -->
<!-- 		<div style="width: 80%; height: auto; margin: 0 auto; overflow: hidden; word-break:break-all; text-align: left;font-size: 14px;"> -->
<%-- 		<textarea disabled="disabled" style="width: 80%; background: #fff; border: 0px; min-height: 70px;">${lookurl }</textarea> --%>
<!-- 		</div> -->
	</div>
</body>
</html>
