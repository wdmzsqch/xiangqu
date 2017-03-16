<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, user-scalable=no">
<title>服务商品认证结果</title>
<link rel="stylesheet" type="text/css" href="./css/myorder.css" />
</head>
<body>
	<div style="width: 100%; text-align: center; margin-top: 10px; color: red; font-size: 20px; font-weight: bold;">
		<c:if test="${empty order}">认证失败<br /><br />
			${errMsg}<br />
		</c:if>
		<c:if test="${not empty order}">
		<c:if test="${not (ostate==4) }">认证成功</c:if>
		<c:if test="${ostate==4 }">认证失败</c:if>
		<br /><br />
		

<div style="padding-bottom: 48px; color:black; font-weight: normal;">
<div style="width:100%;background-color:#ffffff;">
<c:forEach items="${order.goodsList}" var="p" varStatus="status">
<c:if test="${status.first }">
<div class="contentdiv" style="padding-left:0px;padding-right:0px;margin-left:17px;margin-right:13px;">
<img  style="float:left;width:50px;height:50px;" src="${fileRootUrl }${p.pic}">
<div style="margin-left:12px;float:left;width: 70%;">
<div style="width:100%;font-size:16px;height: auto;overflow: hidden; text-align: left;">${p.name }</div>
</div>
</div>
</c:if>
<div style="height:30px;line-height:30px;margin-left:17px;margin-right:13px;font-size:13px;border-bottom: 1px solid #CCCCCC;">
<div style="float:left;width:50%; overflow: hidden; height: 30px; text-align: left;">${p.name }</div>
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
<div style="float:left;color:#989898">订单状态</div>
<div style="color:red;float:right;width:50%;text-align:right;overflow-wrap: break-word;word-break: break-all;line-height: 14px;margin-bottom: 8px;margin-top: 8px;">
<c:if test="${ostate==4 }">该订单已完成，为重复验证</c:if>
<c:if test="${ostate==3 }">该订单验证有效</c:if>
</div>
</div>

<div style="line-height:30px;font-size:13px;height:auto;overflow:hidden;margin-left:17px;margin-right:13px;">
<div style="float:left;color:#989898">订单时间</div>
<div style="color:#666666;float:right;width:50%;text-align:right;overflow-wrap: break-word;word-break: break-all;line-height: 14px;margin-bottom: 8px;margin-top: 8px;">

<fmt:formatDate value="${order.orderTime}" type="both" pattern="yyyy-MM-dd HH:mm"  />
</div>
</div>

</div>
</div>

</c:if>
</div>

<input type="button" value="返回" style="width: 80%; margin-left: 10%; margin-top: 20px; height: 30px; border-radius: 8px; border: 1px solid #ccc;" onclick="javascript:history.back()" />

</body>
</html>