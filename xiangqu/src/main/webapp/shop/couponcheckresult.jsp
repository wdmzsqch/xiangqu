<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, user-scalable=no">
<title>优惠券认证结果</title>
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
		<c:if test="${empty userCoupon}">认证失败<br />
			<br />
		${errMsg}<br />
		</c:if>
		<c:if test="${not empty userCoupon}">
			<c:if test="${isused!=1}">
		认证成功<br /><br />
			</c:if>
			<c:if test="${isused==1}">
			认证失败<br /><br />
				<div class="con" style="color: red;">该用户已经使用过本券，本次为重复认证</div>
				<div class="line"></div>
			</c:if>

			<div class="con">券名：${couponInfo.title}</div>
			<div class="line"></div>
			<div class="con">简介：${couponInfo.intro}</div>
			<div class="line"></div>
			<div class="con">
				截止：
				<fmt:formatDate value="${couponInfo.validity}" type="both" pattern="yyyy-MM-dd" />
			</div>
			<div class="line"></div>
			<div class="con">
				类型：
				<c:if test="${couponInfo.type == 1}">实物券</c:if>
				<c:if test="${couponInfo.type == 2}">抵扣券</c:if>
			</div>
			<div class="line"></div>
			<div class="con">优惠：${couponInfo.money}</div>
			<div class="line"></div>
		</c:if>
	</div>


	<input type="button" value="返回" style="width: 80%; margin-left: 10%; margin-top: 20px; height: 30px; border-radius: 8px; border: 1px solid #ccc;" onclick="javascript:history.back()" />

</body>
</html>