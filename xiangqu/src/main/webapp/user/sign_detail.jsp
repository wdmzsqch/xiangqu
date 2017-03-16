<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, user-scalable=no">
<title>活动</title>
<link rel="stylesheet" type="text/css" href="css/common.css" />
<link rel="stylesheet" type="text/css" href="css/event.css" />
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	function goback() {
		history.back(-1);
	}

</script>
</head>
<body style="font-family: '微软雅黑'; background: #f2f2ef;">
<form action="./addEventSign" id="addEventForm" method="post">
<input type="hidden" value="${event.id }" name="eventId"/>
<input type="hidden" value="${sign.id }" name="id"/>
<!-- 	<div -->
<!-- 		style="position: fixed; top: 7px; left: 4%; width: 31px; height: 31px; background-image: url(imgs/back_icon.png); background-size: 100% 100%;" -->
<!-- 		onclick="goback()"></div> -->
	<img src="${fileUrl }${event.pic}" width="100%" height="150px">
	<div style="width: 100%; background: #fff; padding-bottom: 10px;">
		<div style="width: 92%; margin: 0 auto;">
			<div style="line-height: 47px;">${event.name }</div>
			<div style="line-height: 20px; font-size: 13px; color: #6a6a6a;">${event.detail }</div>
		</div>
	</div>
	<div
		style="width: 100%; background: #fff; margin-top: 10px; padding-bottom: 15px;">
		<div style="width: 92%; margin: 0 auto;">
			<div
				style="width: 100%; height: 43px; line-height: 43px; border-bottom: 1px solid #e8e8e8; font-size: 13px;">报名信息<font color="#ff9a92">(您已经报名此活动)</font></div>
			<div style="width: 100%; height: 137px; border-bottom: 1px solid #e8e8e8;text-align: center; font-size: 16px;">
				<img src="${fileUrl }${sign.codePic }" style="width: 100px; height: 100px;">
				<br/>
				<font color="#54daca">序列号&nbsp;&nbsp;${sign.code }</font>
			</div>
			<div style="width: 100%; height: auto; overflow: hidden; line-height: 45px; font-size: 13px;">
				<div style="width: 20%; float:left; color: #6a6a6a;">姓名：</div>
				<div style="width: 70%; float:left; margin-left: 2%;">${sign.name }</div>
			</div>
			<div style="width: 100%; height: auto; overflow: hidden; line-height: 45px;  font-size: 13px;">
				<div style="width: 20%; float:left; color: #6a6a6a;">性别：</div>
				<div style="width: 70%; float:left; margin-left: 2%;">${sign.gender }</div>
			</div>
			<div style="width: 100%; height: auto; overflow: hidden; line-height: 45px; font-size: 13px;">
				<div style="width: 20%; float:left; color: #6a6a6a;">电话：</div>
				<div style="width: 70%; float:left; margin-left: 2%;">${sign.phone }</div>
			</div>
			<div style="width: 100%; height: auto; overflow: hidden; line-height: 45px; font-size: 13px;">
				<div style="width: 20%; float:left; color: #6a6a6a;">备注：</div>
				<div style="width: 70%; float:left; margin-left: 2%; line-height: 17px;margin-top: 12px;">${sign.comment }</div>
			</div>
		</div>
	</div>
</form>

<%@ include file="cs.jsp" %>
<%CS cs = new CS(1256812462);cs.setHttpServlet(request,response);
String imgurl = cs.trackPageView();%> 
<img src="<%= imgurl %>" width="0" height="0"  />
</body>
</html>