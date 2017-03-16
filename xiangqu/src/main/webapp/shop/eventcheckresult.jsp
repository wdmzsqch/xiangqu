<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, user-scalable=no">
<title>任务认证结果</title>
<style type="text/css">
.con {
	margin-left: 10%;
	text-align: left;
	color: black;
	font-size: 15px;
	font-weight: normal;
}

.line {
	width: 100%;
	height: 5px;
	border-bottom: 1px solid #ccc;
}
</style>
</head>
<body>
	<div style="width: 100%; text-align: center; margin-top: 10px; color: red; font-size: 20px; font-weight: bold;">
		<c:if test="${empty eventsign}">认证失败<br />
			<br />
		${errMsg}<br />
		</c:if>
		<c:if test="${not empty eventsign}">
			<c:if test="${not (isjoin==1)}">
			认证成功<br /><br />
			</c:if>
			<c:if test="${isjoin==1}">
			认证失败<br /><br />
				<div class="con" style="color: red;">该用户已经认证过，本次为重复认证</div>
				<div class="line"></div>
			</c:if>

			<div class="con">
				<span style="font-weight: bold;">任务名称：</span>${event.name}<span style="color: red; font-size: 10px;">(注意核对活动信息)</span>
			</div>
			<div class="line"></div>
			<div class="con" style="font-weight: bold; margin-bottom: 5px;">报名信息：</div>
			<div class="con">姓名：${eventsign.name}</div>
			<div class="line"></div>
			<div class="con">性别：${eventsign.gender}</div>
			<div class="line"></div>
			<div class="con">电话：${eventsign.phone}</div>
			<div class="line"></div>
			<div class="con">备注：${eventsign.comment}</div>
			<div class="line"></div>
		</c:if>
	</div>


	<input type="button" value="返回" style="width: 80%; margin-left: 10%; margin-top: 20px; height: 30px; border-radius: 8px; border: 1px solid #ccc;" onclick="javascript:history.back()" />

</body>
</html>