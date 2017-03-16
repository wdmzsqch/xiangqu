<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, user-scalable=no">
<link rel="stylesheet" type="text/css" href="./css/common.css" />
<link rel="stylesheet" type="text/css" href="./css/myorder.css" />
<script type="text/javascript" src="./js/jquery-1.11.3.min.js"></script>
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

</script>
<title>订单列表</title>
</head>
<body style="font-family:'华文细黑'">
<%@ include file="ordertop.jsp"%> 
<div style="padding-bottom: 48px;">
<div class="ordernumdiv" style="background-color:#798398;">
<img src="./imgs/detailorder.png" width=15px style="position: relative;top: 2px;">
<font style="font-size:15px;color:#ffffff;">订单状态:
<c:if test="${order.state==1 }">等待买家付款</c:if>
<c:if test="${order.state==2 }">等待卖家发货</c:if>
<c:if test="${order.state==3 }">订单已发货</c:if>
<c:if test="${order.state==4 }">订单已完成</c:if></font>
</div>
<div style="padding-left:17px;background-color:#f6f6f6;height:30px;line-height:30px;font-size:13px;">
订单概况
</div>
<div style="width:100%;background-color:#ffffff;">
<c:forEach items="${order.goodsList}" var="p" varStatus="status">
<c:if test="${status.first }">
<div class="contentdiv" style="padding-left:0px;padding-right:0px;margin-left:17px;margin-right:13px;">
<img  style="float:left;width:50px;height:50px;" src="${fileRootUrl }${p.pic}">
<div style="margin-left:12px;float:left;width: 70%;">
<div style="width:100%;font-size:16px;height: auto;overflow: hidden;">${p.name }</div>
</div>
</div>
</c:if>
<div style="height:30px;line-height:30px;margin-left:17px;margin-right:13px;font-size:13px;border-bottom: 1px solid #CCCCCC;">
<div style="float:left;width:50%; overflow: hidden; height: 30px;">${p.name }</div>
<div style="float:left;width:20%">x${p.num }</div>
<div style="float:right;width:25%;text-align:right;"><fmt:formatNumber value="${p.price }" type="currency" /></div>
</div>
<c:forEach items="${p.prolist}" var="pro">
<div style="border-bottom: 1px solid #CCCCCC;line-height:30px;font-size:13px;height:auto;overflow:hidden;margin-left:17px;margin-right:13px;">
<div style="float:left;color:#989898">${pro.name }</div>
<div style="color:#666666;float:right;width:50%;text-align:right;overflow-wrap: break-word;word-break: break-all;line-height: 14px;margin-bottom: 8px;margin-top: 8px;">
<c:forEach items="${pro.valuelist }" var ="pvalue" >
${pvalue }
</c:forEach>
</div>
</div>
</c:forEach>
</c:forEach>

<div style="border-bottom: 1px solid #CCCCCC;line-height:30px;font-size:13px;height:auto;overflow:hidden;margin-left:17px;margin-right:13px;">
<div style="float:left;color:#989898">备注信息</div>
<div style="color:#666666;float:right;width:50%;text-align:right;overflow-wrap: break-word;word-break: break-all;line-height: 14px;margin-bottom: 8px;margin-top: 8px;">
<c:if test="${order.comment != '' }">${order.comment  }</c:if>
<c:if test="${empty order.comment || order.comment == '' }">暂无备注</c:if>
</div>
</div>

<div style="font-size:13px;line-height:30px;text-align:right;margin-left:17px;margin-right:13px;">
<font style="color:#989898">共计:</font>
<font style="color:#E84E4D"><fmt:formatNumber value="${order.totalPrice }" type="currency" /></font>
</div>
<div style="padding-left:17px;font-size:13px;line-heigh:30px;border-bottom:2px solid #f6f6f6; background: #f6f6f6;">
订单详情
</div>

<div style="border-bottom: 1px solid #CCCCCC;line-height:30px;font-size:13px;height:auto;overflow:hidden;margin-left:17px;margin-right:13px;">
<div style="float:left;color:#989898">订单编号</div>
<div style="color:#666666;float:right;width:50%;text-align:right;overflow-wrap: break-word;word-break: break-all;line-height: 14px;margin-bottom: 8px;margin-top: 8px;">
${order.orderNo }
</div>
</div>

<div style="border-bottom: 1px solid #CCCCCC;line-height:30px;font-size:13px;height:auto;overflow:hidden;margin-left:17px;margin-right:13px;">
<div style="float:left;color:#989898">订单时间</div>
<div style="color:#666666;float:right;width:50%;text-align:right;overflow-wrap: break-word;word-break: break-all;line-height: 14px;margin-bottom: 8px;margin-top: 8px;">
<fmt:formatDate value="${order.orderTime}" type="both" pattern="yyyy-MM-dd"/>
</div>
</div>

<div style="border-bottom: 1px solid #CCCCCC;line-height:30px;font-size:13px;height:auto;overflow:hidden;margin-left:17px;margin-right:13px;">
<div style="float:left;color:#989898">收货人</div>
<div style="color:#666666;float:right;width:50%;text-align:right;overflow-wrap: break-word;word-break: break-all;line-height: 14px;margin-bottom: 8px;margin-top: 8px;">
${order.consignee }
</div>
</div>

<div style="border-bottom: 1px solid #CCCCCC;line-height:30px;font-size:13px;height:auto;overflow:hidden;margin-left:17px;margin-right:13px;">
<div style="float:left;color:#989898">手机号</div>
<div style="color:#666666;float:right;width:50%;text-align:right;overflow-wrap: break-word;word-break: break-all;line-height: 14px;margin-bottom: 8px;margin-top: 8px;">
${order.phone }
</div>
</div>

<div style="border-bottom: 1px solid #CCCCCC;line-height:30px;font-size:13px;height:auto;overflow:hidden;margin-left:17px;margin-right:13px;">
<div style="float:left;color:#989898">收货地址</div>
<div style="color:#666666;float:right;width:50%;text-align:right;overflow-wrap: break-word;word-break: break-all;line-height: 14px;margin-bottom: 8px;margin-top: 8px;">
${order.address }
</div>
</div>

<c:if test="${order.state == 3 || order.state == 4 }">

<div style="border-bottom: 1px solid #CCCCCC;line-height:30px;font-size:13px;height:auto;overflow:hidden;margin-left:17px;margin-right:13px;">
<div style="float:left;color:#989898">快递公司</div>
<div style="color:#666666;float:right;width:50%;text-align:right;overflow-wrap: break-word;word-break: break-all;line-height: 14px;margin-bottom: 8px;margin-top: 8px;">
${order.express }
</div>
</div>

<div style="line-height:30px;font-size:13px;height:auto;overflow:hidden;margin-left:17px;margin-right:13px;">
<div style="float:left;color:#989898">快递单号</div>
<div style="color:#666666;float:right;width:50%;text-align:right;overflow-wrap: break-word;word-break: break-all;line-height: 14px;margin-bottom: 8px;margin-top: 8px;">
${order.courierNum }
</div>
</div>

</c:if>

</div>
</div>
<input type="hidden" value="${order.state }" id="state">
<div class="mask" style="display: none;" onclick="hide()"></div>
<div class="sendalert">
<form id="sendForm">
<input id="sendorder" value="" type="hidden" name="orderid"> 
<input id="sendtype" value="" type="hidden"> 
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