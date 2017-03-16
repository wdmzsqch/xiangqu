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
	$("#searchform").submit();
}

function usercoupon(id){
	window.location.href="./usercoupon?id="+id;
}

function showalert(){
	$(".couponalert").css("left",($(".mask").width()-290)/2);
	$(".couponalert").css("top",($(".mask").height()-$(".couponalert").height())/2);
	$(".mask").show();
	$(".couponalert").show();
}

function hide(){
	$(".mask").hide();
	$(".couponalert").hide();
}

function submitForm(){
	var code = $("#alertcode").val();
	if(code == ""){
		alert("优惠券码不能为空");
		return;
	}
	$.ajax({
		url:'./getCouponByCode',
		type:'post',
		data:{code :code},
		success:function(msg){
			var code = msg.code;
			if(code==1){
				alert("领取成功!");
				window.location.href="./mycoupon"
			}else{
				alert(msg.message);
				window.location.reload();
			}
		}
	})
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

.right_un_use{
	width: 89px;
	height: 87px;
	background-image: url("./imgs/un_use.png");
	background-size: cover;
	float: left;
}
</style>
</head>
<body style="background-color: #f2f2ef;">
	<div id="wrapper" style="margin-bottom: 46px;">
	<div id="scroller">
	<div style="height: 15px; margin-top: 5px;">
		<div style="margin-left: 5%; color: #9d9d9d; font-size: 12px; width: 60%; float: left;">共${fn:length(couponlist)}张优惠券</div>
		<div style="width: 98px; height: 15px; float: left;" onclick="searchdiv()">
			<img src="./imgs/choose.png" style="width: 52px; height: 15px; margin-left: 46px;" />
		</div>
	</div>
	<c:forEach items="${couponlist}" var="c">
		<div style="margin-top: 10px; height: 89px;" onclick="usercoupon(${c.id})">
			<div class="leftborder">
				<div style="color: #414141; font-size: 17px; margin-top: 15px; margin-left: 12px; height: 22px; white-space:nowrap; text-overflow:ellipsis;  -o-text-overflow:ellipsis; overflow: hidden;">${c.title}</div>
				<div class="intro" style="margin-top: 12px; white-space:nowrap; text-overflow:ellipsis;  -o-text-overflow:ellipsis; overflow: hidden; height: 16px;">${c.intro}</div>
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
	
	<div style="position: fixed; float: right; width: auto; right: 12px; bottom: 7px; height: 30px; background-color: #ff5c4b; color: #ffffff; border-radius: 20px;
    border: 1px solid #ff5c4b; font-size: 12px; text-align: center;  line-height: 30px; padding-left: 10px; padding-right: 10px;" onclick="showalert()">惠券码兑换</div>
	<form action="searchusercoupon" method="post" id="searchform">
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

<div class="mask" style="display: none;" onclick="hide()"></div>
<div class="couponalert" style="width: 290px;border-radius: 4px;position: fixed;background-color: #ffffff;height: auto;
	z-index: 100;display: none;">
<form action="" method="post" id="couponuserForm">
	<div style="width: 100%; height: 36px; text-align: center; line-height: 36px;">请输入优惠券码</div>
	<div style="width: 100%; height: 40px; text-align: center;">
		<input type="text" name="code" id="alertcode" style="width: 80%; height: 27px; border: 1px solid #e8e8e8; padding-left: 5px; " placeholder="请输入优惠券码"/>
	</div>
	<div style="width: 100%; height: 60px;">
	<div style="width: 150px; height: 60px; margin: 0 auto;">
		<div style="width: 60px; height: 30px; float: left; background: #00d1bc; text-align: center; line-height: 30px; border-radius: 6px; color: #fff; margin-top: 13px;"
			onclick="submitForm()">提交</div>
		<div style="width: 60px; height: 30px; float: right; background: #ff5c4b; text-align: center; line-height: 30px; border-radius: 6px; color: #fff; margin-top: 13px;"
			onclick="hide()">取消</div>
	</div>
	</div>
</form>
</div>

<%@ include file="cs.jsp" %>
<%CS cs = new CS(1256812462);cs.setHttpServlet(request,response);
String imgurl = cs.trackPageView();%> 
<img src="<%= imgurl %>" width="0" height="0"  />
</body>
</html>