<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no">
<script type="text/javascript" src="./js/jquery-1.11.3.min.js"></script>
<link rel="stylesheet" href="iscroll/iscroll2.css">
<link rel="stylesheet" href="css/coupon.css">
<link rel="stylesheet" href="css/common.css">
<script type="text/javascript" src="iscroll/iscroll.js"></script>
<script type="text/javascript">
var myScroll;
function loaded() {
	myScroll = new iScroll('wrapper',{ onScrollEnd: function () {  
        //如果滑动到底部，则加载更多数据（距离最底部10px高度）  
       
    } ,vScroll:true,vScrollbar:false});
}
document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);
document.addEventListener('DOMContentLoaded', function () { setTimeout(loaded, 100); }, false); 

$(function(){
	var height = document.documentElement.clientHeight;
	$("#searchdiv").css("height",height);
	$("#searchcontentdiv").css("height",height-81);
})


function usercoupon(id){
	window.location.href="./usercoupon?id="+id;
}

function coupon(){
	window.location.href="./mycoupon";
}
</script>
<title>我的优惠券</title>
<style type="text/css">
.rotate {
	transform: rotate(7deg);
	-ms-transform: rotate(7deg); /* IE 9 */
	-moz-transform: rotate(7deg); /* Firefox */
	-webkit-transform: rotate(7deg); /* Safari 和 Chrome */
	-o-transform: rotate(7deg); /* Opera */
}

.intro {
	color: #bfbfbf;
	font-size: 12px;
	margin-left: 12px;
	height: 14px; 
	overflow: hidden;
}

.leftborder {
	border-bottom: 1px solid #e8e8e8;
	border-top: 1px solid #e8e8e8;
	border-left: 1px solid #e8e8e8;
	border-radius: 5px 0px 0px 5px;
	width: 60%;
	margin-left: 5%;
	height: 87px;
	float: left;
	background-color: #fff;
}

.middle {
	width: 9px;
	height: 87px;
	background-image: url("./imgs/coupon_r.png");
	background-size: cover;
	float: left;
}

.right {
	width: 89px;
	height: 87px;
	background-image: url("./imgs/in_use.png");
	background-size: cover;
	float: left;
}

.right_use {
	width: 95px;
	height: 93px;
	background-image: url("./imgs/used.png");
	background-size: cover;
	float: left;
}

.right_un_use {
    width: 89px;
    height: 87px;
    background-image: url("./imgs/un_use.png");
    background-size: cover;
    float: left;
}
</style>
</head>
<body style="background-color: #f2f2ef;">
	<div id="wrapper">
	<div id="scroller">
	<div class="topsearch">
	<font style="color:#9d9d9d;float:left;">筛选结果，共${fn:length(couponlist)}张优惠券</font>
	<div class="searchdiv" onclick="coupon()" style="color:#00d1bc;background-image:url('imgs/choose_green.png');">取消筛选</div>
	</div>
	<c:forEach items="${couponlist}" var="c">
		<div style="margin-top: 10px; height: 89px;" onclick="usercoupon(${c.id})">
			<div class="leftborder">
				<div style="color: #414141; font-size: 17px; margin-top: 15px; margin-left: 12px; height: 22px; white-space:nowrap; text-overflow:ellipsis;  -o-text-overflow:ellipsis; overflow: hidden;">${c.title}</div>
				<div class="intro" style="margin-top: 12px;white-space:nowrap; text-overflow:ellipsis;  -o-text-overflow:ellipsis; overflow: hidden;">${c.intro}</div>
				<div class="intro">
					有效期至：
					<fmt:formatDate value="${c.validity}" type="both" pattern="yyyy-MM-dd" />	
				</div>

			</div>
			<div class="middle"></div>
			<c:if test="${c.isUsed == 0}">
				<c:if test="${c.isover == 0 }">
					<div class="right"></div>
				</c:if>
				<c:if test="${c.isover == 1 }">
					<div class="right_un_use"></div>
				</c:if>
			</c:if>
			<c:if test="${c.isUsed == 1}">
				<div class="right_use"></div>
			</c:if>
		</div>

	</c:forEach>
	</div>
	</div>

<%@ include file="cs.jsp" %>
<%CS cs = new CS(1256812462);cs.setHttpServlet(request,response);
String imgurl = cs.trackPageView();%> 
<img src="<%= imgurl %>" width="0" height="0"  />
</body>
</html>