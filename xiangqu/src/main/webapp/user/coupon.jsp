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

function searchdiv(){
	$("#searchdiv").show();
}

function hidesearch(){
	$("#searchdiv").hide();
}

function checkalltype(object){
	$(".typechoose").removeClass("choosed");
	$(".typechoose").addClass("unchoosed");
	$(object).removeClass("unchoosed");
	$(object).addClass("choosed");
}

function checktype(object){
	$(".typeallchoose").removeClass("choosed");
	$(".typeallchoose").addClass("unchoosed");
	if($(object).hasClass("unchoosed")){
		$(object).removeClass("unchoosed");
		$(object).addClass("choosed");
	}else{
		$(object).removeClass("choosed");
		$(object).addClass("unchoosed");
	}
	if($(".typechoose.choosed").length==0){
		$(".typeallchoose").removeClass("unchoosed");
		$(".typeallchoose").addClass("choosed");
	}
}

function checkalldate(object){
	$(".datechoose").removeClass("choosed");
	$(".datechoose").addClass("unchoosed");
	$(object).removeClass("unchoosed");
	$(object).addClass("choosed");
}

function checksevdate(object){
	$(".dateallchoose").removeClass("choosed");
	$(".dateallchoose").addClass("unchoosed");
	if($(object).hasClass("unchoosed")){
		$(object).removeClass("unchoosed");
		$(object).addClass("choosed");
	}else{
		$(object).removeClass("choosed");
		$(object).addClass("unchoosed");
	}
	if($(".datechoose.choosed").length==0){
		$(".dateallchoose").removeClass("unchoosed");
		$(".dateallchoose").addClass("choosed");
	}
}

function checkallshop(object){
	$(".shopchoose").removeClass("choosed");
	$(".shopchoose").addClass("unchoosed");
	$(object).removeClass("unchoosed");
	$(object).addClass("choosed");
}

function checkshop(object){
	$(".shopallchoose").removeClass("choosed");
	$(".shopallchoose").addClass("unchoosed");
	if($(object).hasClass("unchoosed")){
		$(object).removeClass("unchoosed");
		$(object).addClass("choosed");
	}else{
		$(object).removeClass("choosed");
		$(object).addClass("unchoosed");
	}
	if($(".shopchoose.choosed").length==0){
		$(".shopallchoose").removeClass("unchoosed");
		$(".shopallchoose").addClass("choosed");
	}
}

function choosecoupon(){
	$(".typechoose.choosed").each(
			function(){
				var name = $(this).text();
				var value = $("#"+name+"_value").val();
				var typevalue = $("#type").val();
				if(typevalue==''){
					typevalue += value;
				}else{
					typevalue += ","+value;
				}
				 $("#type").val(typevalue);
			}
	)
	
	if($(".datechoose.choosed").length==0){
		$("#datetype").val(0)
	}else{
		$("#datetype").val(1);
	}
	$(".shopchoose.choosed").each(
			function(){
				var name = $(this).text();
				var value = $("#shop"+name+"_value").val();
				var shops = $("#shops").val();
				if(shops==''){
					shops += value;
				}else{
					shops += ","+value;
				}
				 $("#shops").val(shops);
			}
	)
// 	alert($("#type").val());
// 	alert($("#datetype").val());
// 	alert($("#shops").val());
	$("#searchform").submit();
}
</script>
<title>优惠券</title>
</head>
<body>
<div id="wrapper">
<div id="scroller">
<div class="topsearch">
<font style="color:#9d9d9d;float:left;">共${allcoupon }张优惠券</font>
<div class="searchdiv" onclick="searchdiv()">筛选</div>
</div>
<c:forEach items="${shoplist}" var="shop">
<div style="padding-left:13px;padding-right:12px;padding-top:10px;padding-bottom:10px;height: auto;overflow: hidden;font-size:12px;font-family:'微软雅黑';">
<img src="${fileurl }${shop.pic}" width="48px" height="48px" style="float:left">
<div style="float:left;margin-left:10px;line-height:48px;">${shop.companyName }</div>
</div>
<div style="padding-left:13px;padding-right:12px;">
<c:forEach items="${shop.couponlist}" var="coupon" varStatus="status">
<div style="width:50%;float:left;padding-bottom:10px;min-width:165px;" >
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
<font style="float:left;font-size:10px;color:#bcbcbc;padding-left:5%;">有效期至<fmt:formatDate value="${coupon.validity }" pattern="yyyy.MM.dd"/> </font>
<font style="float:right;color:#414141;font-size:10px;padding-right:5%;">立即领取</font>
</div>
</div>
</div>
</c:forEach>
</div>
<div style="clear: both;width:100%;height:10px;background-color:#f2f2ef;border-top:1px solid #e8e8e8;border-bottom:1px solid #e8e8e8;"></div>
</c:forEach>
<div style="width:100%;height:50px;"></div>
</div>
</div>

<input type="hidden" value="${share_userid }" id="share_userid">
<form action="searchcoupon" method="post" id="searchform">
<div style="position:fixed;z-index:1000;top:0px;width:100%;background-color:#ffffff;display:none;" id="searchdiv">
<div class="topsearch">
<font style="color:#9d9d9d;float:left;">请选择你的条件</font>
<div class="searchdiv" style="background-image:url('imgs/choose_red.png');color:#ff5c4b;">筛选</div>
</div>
<div id="searchcontentdiv">
<div style="height:auto;overflow:hidden;margin-left:13px;margin-right:13px;padding-top:12px;padding-bottom:10px;border-bottom:1px solid #e8e8e8">
<div style="padding-bottom:12px;height:auto;overflow:hidden;">
<div style="float:left;color:#bfbfbf;font-size:12px;">优惠券类型</div><img src="./imgs/choosebtn.png" style="float:right;width:13px;">
</div>
<div style="clear:both;" class="choosebtn choosed typeallchoose" onclick="checkalltype(this);">全部</div>
<div  class="choosebtn unchoosed typechoose" onclick="checktype(this)">实物券</div>
<div  class="choosebtn unchoosed typechoose" onclick="checktype(this)">抵扣券</div>
<input type="hidden" id="实物券_value" value="1">
<input type="hidden" id="抵扣券_value" value="2">
<input type="hidden" name="type" id="type" value="">
</div>
<div style="height:auto;overflow:hidden;margin-left:13px;margin-right:13px;padding-top:12px;padding-bottom:10px;border-bottom:1px solid #e8e8e8">
<div style="padding-bottom:12px;height:auto;overflow:hidden;">
<div style="float:left;color:#bfbfbf;font-size:12px;">有效期</div><img src="./imgs/choosebtn.png" style="float:right;width:13px;">
</div>
<div style="clear:both;" class="choosebtn choosed dateallchoose" onclick="checkalldate(this)">全部</div>
<div  class="choosebtn unchoosed datechoose" onclick="checksevdate(this)">最近七天到期</div>
<input type="hidden" name="datetype" id="datetype" value="">
</div>

<div style="height:auto;overflow:hidden;margin-left:13px;margin-right:13px;padding-top:12px;padding-bottom:10px;border-bottom:1px solid #e8e8e8">
<div style="padding-bottom:12px;height:auto;overflow:hidden;">
<div style="float:left;color:#bfbfbf;font-size:12px;">所有商家</div><img src="./imgs/choosebtn.png" style="float:right;width:13px;">
</div>
<div style="clear:both;" class="choosebtn choosed shopallchoose" onclick="checkallshop(this)">全部</div>
<c:forEach items="${shoplist}" var="shop">
<div  class="choosebtn unchoosed shopchoose" onclick="checkshop(this)">${shop.companyName }</div>
<input type="hidden" id="shop${shop.companyName }_value" value="${shop.id}">
</c:forEach>
<input type="hidden" name="shops" id="shops" value="">
</div>
</div>

<div style="position:fixed;width:100%;bottom:0;height:46px;border-bottom:1px solid #e5e5e5;background-color:#f8f8f8;">
<div style="float:right;width:75px;margin-right:12px;margin-top: 7px;height:30px;background-color:#ff5c4b;color:#ffffff;border-radius:20px;border:1px solid #ff5c4b;text-align:center;line-height:30px;" onclick="choosecoupon()">筛选</div>
<div style="float:right;margin-right:9px;margin-top: 7px;width:75px;height:30px;background-color:#ffffff;color:#ff5c4b;border-radius:20px;border:1px solid #ff5c4b;text-align:center;line-height:30px;" onclick="hidesearch()">取消</div>
</div>
</div>
</form>

<%@ include file="cs.jsp" %>
<%CS cs = new CS(1256812462);cs.setHttpServlet(request,response);
String imgurl = cs.trackPageView();%> 
<img src="<%= imgurl %>" width="0" height="0"  />
</body>
</html>