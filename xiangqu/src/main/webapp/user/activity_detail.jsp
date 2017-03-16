<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no">
<title>活动详情</title>
<link rel="stylesheet" type="text/css" href="css/common.css" />
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<style type="text/css">
	.title_time{
		width: 100%;
		height: 135px;
		background-image: url(imgs/title_bg.png);
		background-position: center;
		background-size: cover;
	}
	
	.title{
		width: 76%;
		height: 52px;
		color: #fff;
		font-size: 18px;
		line-height: 20px;
		text-align: center;
		margin: 0 auto;
		padding-top: 33px;
		overflow: hidden;
	}
	
	.time{
		padding:5px 10px;
		background: #f1c9d2;
		border-radius: 18px;
		margin: 0 auto;
		color: #d86e6b;
		font-size: 12px;
		width: 127px;
	}
</style>
<script type="text/javascript">
	function onloadbody(){
		var width = $(".title_time").width();
		$("#top_img").css("margin-left",parseFloat(width-36)/2);
	}
</script>
</head>
<body onload="onloadbody()">
	<div class="title_time">
		<div class="title">${activity.title }</div>
		<div class="time">活动时间：<fmt:formatDate value="${activity.acitvityTime }" pattern="yyyy-MM-dd"/> </div>
		<img src="imgs/small_center_icon.png" id="top_img" style="width: 36px; height: 36px; margin-top: 5px;">
	</div>
	<div style="width: 86%; height: auto; margin-left: 7%; margin-top: 42px;">
		<div style="width; 100%;">${activity.content }</div>
		<div style="width: 60px; height: 47px; border-top: 2px solid #ff5c4b; color: #ff5c4b; font-size: 14px; text-align: center; margin-top: 35px; line-height: 47px;">下期预告</div>
		<div style="width: 100%; color: #858585; font-size: 11px; line-height: 17px; margin-bottom: 20px;">${activity.nextIntro }</div>
	</div>
	<%@ include file="cs.jsp"%>
	<%
		CS cs = new CS(1256812462);
		cs.setHttpServlet(request, response);
		String imgurl = cs.trackPageView();
	%>
	<img src="<%=imgurl%>" width="0" height="0" />
</body>
</html>