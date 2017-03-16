<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, user-scalable=no">
<link rel="stylesheet" type="text/css" href="./css/common.css" />
<link rel="stylesheet" type="text/css" href="./css/mission.css" />
	<script type="text/javascript" src="./js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript">
$(function(){
	setTabSelected(1);
})

</script>
<title>${coupon.title }</title>
</head>
<body style="background-color:rgb(246,246,246);font-family:'华文细黑'">
<div style="padding-left:18px;padding-right:18px;line-height:18px;font-size:14px;padding-top:5px;padding-bottom:5px;">
优惠券概况
</div>
<div class="contentdiv" >
<div style="margin-left:12px;float:left;width: 70%;">
<div style="width:100%;font-size:16px;height:22px;" >${coupon.title }</div>
</div>
</div>
<div class="detailcontent">
<font style="float:left;">使用条件</font>
<font style="float:right;">满<fmt:formatNumber value="${coupon.moneyLimit }" type="currency"/></font>
</div>
<div class="detailcontent">
<font style="float:left;">优惠金额</font>
<font style="float:right;"><fmt:formatNumber value="${coupon.money }" type="currency"/></font>
</div>
<div class="detailcontent">
<font style="float:left;">优惠券类型</font>
<font style="float:right;"><c:if test="${coupon.type==1 }">实物券</c:if><c:if test="${coupon.type==2 }">抵扣券</c:if></font>
</div>
<div class="detailcontent">
<font style="float:left;">简介</font>
<font style="float:right;">${coupon.intro }</font>
</div>
<div class="detailcontent">
<font style="float:left;">发放总数</font>
<font style="float:right;">${coupon.total }</font>
</div>
<div class="detailcontent">
<font style="float:left;">兑换码</font>
<font style="float:right;">${coupon.code }</font>
</div>
<div class="detailcontent">
<font style="float:left;">限领张数</font>
<font style="float:right;">${coupon.numLimit }</font>
</div>
<div class="detailcontent">
<font style="float:left;">分享收益</font>
<font style="float:right;"><fmt:formatNumber value="${coupon.shareIncome }" type="currency"/></font>
</div>
<div class="detailcontent">
<font style="float:left;">使用收益</font>
<font style="float:right;"><fmt:formatNumber value="${coupon.useIncome }" type="currency"/></font>
</div>

<div class="detailcontent">
<font style="float:left;">领取数量</font>
<font style="float:right;">${coupon.getNum }</font>
</div>
<div class="detailcontent">
<font style="float:left;">总领取金额</font>
<font style="float:right;color:#F84E4E"><fmt:formatNumber value="${coupon.getNum*coupon.shareIncome }" type="currency"/></font>
</div>
<div class="detailcontent">
<font style="float:left;">使用数量</font>
<font style="float:right;">${coupon.useNum }</font>
</div>
<div class="detailcontent" style="border-bottom:none;">
<font style="float:left;">总使用金额</font>
<font style="float:right;color:#F84E4E"><fmt:formatNumber value="${coupon.useNum*coupon.useIncome }" type="currency"/></font>
</div>

</body>

</html>