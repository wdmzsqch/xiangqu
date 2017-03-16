<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no">
<link rel="stylesheet" type="text/css" href="./css/coupon.css" />
<link rel="stylesheet" type="text/css" href="./css/common.css" />
<script type="text/javascript" src="./js/jquery-1.11.3.min.js"></script>
<link rel="stylesheet" href="iscroll/iscroll2.css">
<script type="text/javascript" src="iscroll/iscroll.js"></script>
<script type="text/javascript">
$(function() {
	var height = document.documentElement.clientHeight;
	$("#searchdiv").css("height",height);
	$("#searchcontentdiv").css("height",height-81);
})

function coupondetail(id){
	var share_userid = $("#share_userid").val();
	window.location.href="./coupondetail?coupon_id="+id+"&share_userid="+share_userid;
}

var myScroll;
function loaded() {
	myScroll = new iScroll('wrapper',{ onScrollEnd: function () {  
        //如果滑动到底部，则加载更多数据（距离最底部10px高度）  
       
    } ,vScroll:true,vScrollbar:false});
}
document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);
document.addEventListener('DOMContentLoaded', function () { setTimeout(loaded, 100); }, false); 

function coupon(){
	window.location.href="./coupon";
}
</script>
<title>优惠券</title>
</head>
<body>
<div id="wrapper">
<div id="scroller">
<div class="topsearch">
<font style="color:#9d9d9d;float:left;">筛选结果，共${allcoupon }张优惠券</font>
<div class="searchdiv" onclick="coupon()" style="color:#00d1bc;background-image:url('imgs/choose_green.png');">取消筛选</div>
</div>
<div style="padding-left:13px;padding-right:12px;">
<c:forEach items="${couponlist}" var="coupon" varStatus="status">
<div style="width:50%;float:left;padding-bottom:5px;padding-top: 10px;min-width:165px;" >
<div onclick="coupondetail(${coupon.id})" style="width:95%;height:auto;overflow:hidden;"  class="<c:if test="${coupon.type==1}" >couponpurple</c:if><c:if test="${coupon.type==2}" ><c:if test="${status.count%4==1}" >couponred</c:if><c:if test="${status.count%4==2}" >couponyellow</c:if><c:if test="${status.count%4==3}" >coupongreen</c:if><c:if test="${status.count%4==0}" >couponcyan</c:if></c:if> " >
<c:if test="${coupon.type==2 }">
<div style="float:left;width:90%;padding-left:5%;padding-right:5%;height:auto;overflow:hidden;">
<div style="float:left;width:40%;height:42px;line-height: 42px;">
<font style="font-size:12px;float:left;">￥</font>
<div style="width:38px;float:left;font-size:20px;overflow: hidden; word-break: break-word;"><fmt:formatNumber type='number' value='${coupon.money }' maxFractionDigits="0"></fmt:formatNumber></div>
</div>
<div style="float:left;width:60%;height:42px;">
<div style="padding-left:10px;padding-right:10px;overflow : hidden;text-overflow: ellipsis;display: -webkit-box;-webkit-line-clamp: 2;-webkit-box-orient: vertical;height:26px;padding-top:8px;pandding-bottom:8px;line-height:13px;float:left;font-size:10px;">
${coupon.intro }
</div>
</div>
</div>
</c:if>
<c:if test="${coupon.type==1 }">
<div style="float:left;width:90%;padding-left:5%;padding-right:5%;height:auto;overflow:hidden;">
<div style="float:left;width:40%;height:42px;line-height: 42px;background-repeat: no-repeat;background-image: url(imgs/gift.png); background-position: center;background-size:30px 30px;">
</div>
<div style="float:left;width:60%;height:42px;">
<div style="text-align:center;padding-left:10px;padding-right:10px;height:26px;padding-top:8px;pandding-bottom:8px;float:left;font-size:10px;">
<div style="height:13px;line-height:13px;overflow : hidden;text-overflow: ellipsis;display: -webkit-box;-webkit-line-clamp: 1;-webkit-box-orient: vertical;">${coupon.title }</div>
<div style="height:13px;line-height:13px;overflow : hidden;text-overflow: ellipsis;display: -webkit-box;-webkit-line-clamp: 1;-webkit-box-orient: vertical;">实物券</div>
</div>
</div>
</div>
</c:if>
<div style="float:left;width:100%;height:20px;line-height: 20px;background-color:#ffffff;">
<font style="float:left;font-size:10px;color:#bcbcbc;padding-left:5%;">有效期至<fmt:formatDate value="${coupon.validity }" pattern="yy.MM.dd"/> </font>
<font style="float:right;color:#414141;font-size:10px;padding-right:5%;">立即领取</font>
</div>
</div>
</div>
</c:forEach>
</div>

<div style="width:100%;height:50px;"></div>
</div>
</div>

<input type="hidden" value="${share_userid }" id="share_userid">


<%@ include file="cs.jsp" %>
<%CS cs = new CS(1256812462);cs.setHttpServlet(request,response);
String imgurl = cs.trackPageView();%> 
<img src="<%= imgurl %>" width="0" height="0"  />
</body>
</html>