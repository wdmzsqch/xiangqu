<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no">
<link rel="stylesheet" type="text/css" href="./css/common.css" />
<script type="text/javascript" src="./js/jquery-1.11.3.min.js"></script>
<title>我的优惠券</title>
</head>
<body style="background-color:#f2f2ef;">
<div style="color:#ffffff;height:auto;overflow:hidde;margin-top:16px;margin-left:13px;margin-right:13px;background-repeat: no-repeat;background-size: contain; background-size: 100% 100%;background-image:url(imgs/couponbackground.png)">
<div style="padding-left:20px;height:auto;overflow:hidden;padding-bottom:9px;padding-top:9px;padding-right:20px; text-align: center;">
	<div style="font-size: 24px;">
		<c:if test="${usercoupon.money >0}">
			<fmt:formatNumber type='number' value='${usercoupon.money }' maxFractionDigits="0"></fmt:formatNumber>元</c:if>
		<c:if test="${usercoupon.type==1 }">实物券</c:if>
		<c:if test="${usercoupon.type==2 }">抵扣券</c:if>
	</div>
	<div style="font-size: 12px; padding-top: 10px;">${usercoupon.intro }</div>
	<div style="padding-top: 5px; padding-bottom: 5px; font-size: 12px; line-height: 14px; text-align: center; padding-left: 20px; padding-right: 20px;">
		有效期至
		<fmt:formatDate value="${usercoupon.validity }" pattern="yyyy-MM-dd" />
	</div>
</div>
<div style="clear:both;margin-left:20px;margin-right:20px;height:3px;border-top:1px solid rgba(255,255,255,0.5);border-bottom:1px solid rgba(255,255,255,0.5);"></div>
<div style="margin-left: 20px; margin-right: 20px; padding-top: 10px; padding-bottom: 30px; text-align: left; ">
	<img src="${fileurl }${usercoupon.shop_pic}" width="23px" height="23px" style="float: left; border-radius: 6px;">
	<div style="float: left; margin-left: 10px; font-size: 11px; line-height: 23px;">${usercoupon.shop_name }</div>
</div>
<div style="margin-left: 20px; margin-right: 20px; padding-bottom: 15px; font-size: 11px; line-height: 23px; text-align: left;">
	电话：&nbsp;&nbsp;${usercoupon.shop_phone }
	<br>
	地址：&nbsp;&nbsp;${usercoupon.shop_address }
</div>
</div>
<div style="width: 100%; padding-top: 10px; text-align: center;font-size: 12px; color: #bfbfbf">最终解释权归享趣所有</div>
<div style="padding-left:12px;padding-right:12px;height:auto;overflow:hidden;margin-top:20px;background-color:#ffffff;">
<div style="font-size:12px;color:#bfbfbf;line-height:13px;padding-top:10px;">二维码</div>
<div style="padding-top:15px;text-align: center;">
<img style="margin:0 auto" width="50%" src="${fileurl }${usercoupon.codePic}">
</div>
<div style="padding-bottom:15px;text-align: center;font-size:14px;color:#414141">
序列号&nbsp;&nbsp;${usercoupon.code }
</div>
</div>

<%@ include file="cs.jsp" %>
<%CS cs = new CS(1256812462);cs.setHttpServlet(request,response);
String imgurl = cs.trackPageView();%> 
<img src="<%= imgurl %>" width="0" height="0"  />
</body>
</html>