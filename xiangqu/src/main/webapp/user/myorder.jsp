<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no">
<link rel="stylesheet" type="text/css" href="./css/common.css" />
<link rel="stylesheet" type="text/css" href="./css/myorder.css" />
<link rel="stylesheet" href="./iscroll/iscroll2.css">
<script type="text/javascript" src="./js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="./iscroll/iscroll.js"></script>
<script type="text/javascript" src="./js/orderiscroll.js"></script>
<script type="text/javascript">
$(function(){
	var state = $("#state").val();
	if(state==''){
		$("#li_all").addClass("select");
	}else{
		$("#li_"+state).addClass("select");
	}
});

var myScroll;
function loaded() {
	myScroll = new iScroll('wrapper',{ onScrollEnd: function () {  
        //如果滑动到底部，则加载更多数据（距离最底部10px高度）  
        if ((this.y - this.maxScrollY) <5) {
             pullUpAction();   
        }  
    } ,vScroll:true,vScrollbar:false});
}
document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);
document.addEventListener('DOMContentLoaded', function () { setTimeout(loaded, 100); }, false); 

function detail(orderid){
	window.location.href="orderdetail?orderid="+orderid;
}

function reciveorder(orderid){
	if(confirm("确认收货?")){
		$.ajax({
			url:'./reciveorder',
			type:'post',
			data:{"orderid":orderid},
			success:function(msg){
				var code = msg.code;
				if(code==1){
					alert("收货成功，完成订单!")
					window.location.reload();
				}else{
					alert(msg.message);
				}
			}
		})
	}
}
</script>
<title>订单列表</title>
</head>
<body style="font-family:'华文细黑'">
<%-- <jsp:include page="./top.jsp"/> --%>
<%@ include file="ordertop.jsp"%> 

<div id="wrapper" style="clear:both; margin-top: 38px;">
<div id="scroller">
<div id="orderlist">
<c:forEach items="${orderlist}" var="order">
<div class="ordernumdiv" onclick="detail(${order.id })">
<!-- <img src="./imgs/order.png" width=15px style="position: relative;top: 2px;"> -->
<font style="font-size:15px;"><font style="color:rgb(191,191,191);">订单编号&nbsp;&nbsp;</font>${order.orderNo }</font>
</div>
<div class="contentdiv" onclick="detail(${order.id })">
<img  style="float:left;width:65px;height:65px;" src="${fileRootUrl }${order.pic}">
<div style="margin-left:12px;float:left; width: 70%; overflow: hidden;">
<div style="width:100%;font-size:12px;height:24px; overflow: hidden;">${order.goodsname }</div>
<div style="height: 12px;font-size:12px;color:#bfbfbf;overflow: hidden;">
<c:forEach items="${order.prolist}" var="pro">
${pro.name }:<c:forEach items="${pro.valuelist }" var ="pvalue" >
${pvalue }
</c:forEach>&nbsp;&nbsp;
</c:forEach>
</div>
<div style="height: 30px;"> 
<span style="float: left;"><c:if test="${order.paytype!=2 }">￥${order.totalPrice }</c:if><c:if test="${order.paytype==2 }"><fmt:parseNumber integerOnly="true" value="${order.totalPrice }" />分</c:if></span>
<span style="float: right; color: rgb(191,191,191)">x${order.goodscount }</span>
</div>
</div>
</div>
<div class="bottomdiv">
<font style="font-size:13px;">共${order.goodscount }件商品&nbsp;&nbsp;合计：<c:if test="${order.paytype!=2 }">￥${order.totalPrice }</c:if><c:if test="${order.paytype==2 }"><fmt:parseNumber integerOnly="true" value="${order.totalPrice }" />分</c:if></font>
<c:if test="${order.state==1 }">
<div class="detailbtn" onclick="detail(${order.id })">付款</div>
</c:if>
<c:if test="${order.state==2}">
<div class="detailbtn" style="border: 0px; width: 60px;" onclick="detail(${order.id })">等待发货</div>
</c:if>
<c:if test="${order.state==3 }">
<div class="detailbtn" style="width:70px; border: 1px solid RGB(0,209,188); color: RGB(0,209,188)" onclick="reciveorder(${order.id })">确认收货</div>
</c:if>
<c:if test="${order.state==4 }">
<%-- <div class="detailbtn" style="width:70px; border: 1px solid RGB(0,209,188); color: RGB(0,209,188)" onclick="reciveorder(${order.id })">评价</div> --%>
</c:if>
</div>
</c:forEach>
</div>
<c:if test="${loadmore == 1}">
<div id="load_more" class="load_more"><span id="loadmorecontent">下拉加载更多</span></div>
<input type="hidden" value="0" id="pushflg">
</c:if>
<c:if test="${loadmore == 0}">
<div id="load_more" class="load_more" style="margin-top: 20px;"><span id="loadmorecontent">暂无更多商品!</span></div>
<input type="hidden" value="1" id="pushflg">
</c:if>
</div>
</div>
<input type="hidden" value="1" id="pageIndex" >
<input type="hidden" value="${state }" id="state" >

<%@ include file="cs.jsp" %>
<%CS cs = new CS(1256812462);cs.setHttpServlet(request,response);
String imgurl = cs.trackPageView();%> 
<img src="<%= imgurl %>" width="0" height="0"  />
</body>
</html>