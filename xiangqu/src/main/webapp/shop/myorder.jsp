<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, user-scalable=no">
<link rel="stylesheet" type="text/css" href="./css/common.css" />
<link rel="stylesheet" type="text/css" href="./css/myorder.css" />
<link rel="stylesheet" href="./iscroll/iscroll2.css">
<script type="text/javascript" src="./js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="./iscroll/iscroll.js"></script>
<script type="text/javascript" src="./js/orderiscroll.js"></script>
<script type="text/javascript">

$(function(){
	setTabSelected(3);
	var state = $("#state").val();
	if(state==''){
		$("#li_all").addClass("select");
	}else{
		$("#li_"+state).addClass("select");
	}
})

var myScroll;
function loaded() {
	myScroll = new iScroll('wrapper',{ onScrollEnd: function () {  
        //如果滑动到底部，则加载更多数据（距离最底部10px高度）  
        if ((this.y - this.maxScrollY) <5) {
             pullUpAction();   
        }  
    } ,vScroll:true,vScrollbar:false});
	
	var width = document.documentElement.clientWidth;
	var alertwidth = $(".sendalert").width();
	var left = (width-alertwidth)/2;
	$(".sendalert").css("left",left);
}
document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);
document.addEventListener('DOMContentLoaded', function () { setTimeout(loaded, 100); }, false); 

function detail(orderid){
	window.location.href="orderdetail?orderid="+orderid;
}

function sendOrdershow(orderid){
	$("#sendorder").val(orderid);
	$(".mask").show();
	$(".sendalert").show();
}

function articlesend(type){
	$("#sendtype").val(type);
	if(type==1){
		$(".unusetype").removeClass("unusetype");
		$(".typeleft").addClass("unusetype");
		$("#noarticle").hide();
		$("#article").show();
	}else{
		$(".unusetype").removeClass("unusetype");
		$(".typeright").addClass("unusetype");
		$("#article").hide();
		$("#noarticle").show();
	}
}

function cancelsendOrder(){
	articlesend(1);
	$("#sendorder").val("");
	$(".mask").hide();
	$(".sendalert").hide();
	$("#courierNum").val("");
	$("#express").val("");
}

function sendOrder(){
	var sendtype = $("#sendtype").val();
	if(sendtype==1){
		var courierNum = $("#courierNum").val();
		var express = $("#express").val();
		if(courierNum==''||express==''){
			alert("请填写物流单号和物流信息!");
			return;
		}
	}
	$.ajax({
	url:'./sendorder',
	type:'post',
	data:$("#sendForm").serialize(),
	success:function(msg){
		var code = msg.code;
		if(code==1){
			alert("发货成功!");
			window.location.reload();
		}else{
			alert(msg.message);
		}
	}
	})
}

</script>
<title>订单列表</title>
</head>
<body style="font-family:'华文细黑'">
<%@ include file="ordertop.jsp"%> 

<div id="wrapper" style="clear:both;">
<div id="scroller">
<div id="orderlist">
<c:forEach items="${orderlist}" var="order">
<div class="ordernumdiv" onclick="detail(${order.id})">
<img src="./imgs/order.png" width=15px style="position: relative;top: 2px;">
<font style="font-size:15px;color:#666666;">订单编号：${order.orderNo }</font>
</div>
<div class="contentdiv" onclick="detail(${order.id})">
<img  style="float:left;width:65px;height:65px;" src="${fileRootUrl }${order.pic}">
<div style="margin-left:77px;width: 100%;">
<div style="width:100%;font-size:16px;height:22px;">收件人：${order.consignee }</div>
<div style="width:100%;font-size:16px;height:22px;">电话：${order.phone }</div>
<div style="width:100%;font-size:16px;height:auto;">地址：${order.address }</div>
<div style="width:100%;font-size:16px;height:22px;">${order.goodsname }</div>
<p class="goodscount">
共${order.goodscount }件
</p>
</div>
</div>
<div class="bottomdiv">
<font style="font-size:13px;color:#666666">总计:</font><font style="font-size:13px;color:#989898">￥</font><font style="color:#E84E4D;font-size:13px;">${order.totalPrice }</font>
<c:if test="${order.state!=2 }">
<div class="detailbtn" onclick="detail(${order.id })">详情</div>
</c:if>
<c:if test="${order.state==2 }">
<div class="detailbtn" onclick="sendOrdershow(${order.id })">发货</div>
</c:if>
</div>
</c:forEach>
</div>
<c:if test="${loadmore == 1}">
<div id="load_more" class="load_more" style="margin-bottom: 47px;"><span id="loadmorecontent">下拉加载更多</span></div>
<input type="hidden" value="0" id="pushflg">
</c:if>
<c:if test="${loadmore == 0}">
<div id="load_more" class="load_more" style="margin-bottom: 47px;"><span id="loadmorecontent">暂无更多商品!</span></div>
<input type="hidden" value="1" id="pushflg">
</c:if>
</div>
</div>
<input type="hidden" value="1" id="pageIndex" >
<input type="hidden" value="${state }" id="state" >

<div class="mask" style="display: none;" onclick="hide()"></div>
<div class="sendalert">
<form id="sendForm">
<input id="sendorder" value="" type="hidden" name="orderid"> 
<input id="sendtype" value="1" type="hidden"> 
<div class="typediv" >
<div class="typebtn typeleft unusetype" onclick="articlesend(1)">物流发货</div>
<div class="typebtn typeright " onclick="articlesend(2)">无需物流发货</div>
</div>
<hr style="height:1px;border:0;border-bottom:1px solid #cccccc;"/>
<div style="text-align:center;height:200px;width:100%" id="article">
<div><input id="courierNum" name="courierNum" style="padding-left:5px;border-radius:4px;width:178px;line-height:20px;margin:0 auto; margin-top:10px;margin-bottom:10px;border:1px solid #cccccc;" placeholder="请输入快递单号"></div>
<div><input id="express" name="express" style="padding-left:5px;border-radius:4px;width:178px;line-height:20px;margin:0 auto;border:1px solid #cccccc;" placeholder="请输入物流公司"></div>
</div>
<div style="text-align:center;height:200px;width:100%;display:none;" id="noarticle">
<div style="width:100%;padding-top:90px;text-align:center;line-height:10px;line-height: 13px;font-size: 13px;color:#cccccc">
无需填写物流信息
<br>
请点击直接发货
</div>
</div>
<div style="width:100%;border-top:1px solid #cccccc;">
<div style="float:left;width:124px;height:30px;line-height:30px;text-align:center;border-right:1px solid #cccccc;color:rgb(0,143,203);font-weight:bold;" onclick="cancelsendOrder()">取消</div>
<div style="float:left;width:125px;height:30px;line-height:30px;text-align:center;color:rgb(0,143,203);font-weight:bold;" onclick="sendOrder()">发货</div>
</div>
</form>
</div>
<%@ include file="tab.jsp"%> 
</body>
</html>