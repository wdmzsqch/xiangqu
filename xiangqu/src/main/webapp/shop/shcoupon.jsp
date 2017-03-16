<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, user-scalable=no">
<link rel="stylesheet" type="text/css" href="./css/common.css" />
<link rel="stylesheet" type="text/css" href="./css/mission.css" />
<title>优惠券列表</title>
<script type="text/javascript" src="./js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
function detail(id){
	window.location.href="./shcoupondetail?id="+id;
}
</script>
</head>
<body style="margin:0;background-color:rgb(246,246,246);font-family:'华文细黑'">

<c:forEach items="${couponlist}" var="p">
<div style="padding-top:15px;clear:both;">
<div style="padding-top:8px;padding-bottom:8px;border-bottom:1px solid rgb(166,166,166);padding-left:18px;padding-right:18px;background-color:#ffffff;height:auto;overflow:hidden">
<div style="line-height:18px;float:left;font-size:13px;">
<img src="./imgs/order.png" style="width:13px;position:relative;top:2px;">
${p.title }
</div>
<div style="line-height:18px;float:right;text-algin:right;font-size:13px;color:rgb(166,166,166)">
结束时间:<fmt:formatDate value="${p.validity}" type="date" dateStyle="medium" />
</div>
<img src="./imgs/time.png" style="width:13px;float:right;position:relative;top:2px;">

</div>
<div class="contentdiv" onclick="detail(${p.id })">
<div style="margin-left:12px;float:left;width: 70%;">
<div style="width:100%;font-size:16px;height:22px;" class="overflowOmit">${p.money }元<c:if test="${p.type==1 }">实物券</c:if><c:if test="${p.type==2 }">抵扣券</c:if></div>
<p class="goodscount">
￥&nbsp;&nbsp;分享收益:<font style="color:#F84E4E">${p.shareIncome }</font>
</p>
<p class="goodscount">
￥&nbsp;&nbsp;使用收益:<font style="color:#F84E4E">${p.useIncome }</font>
</p>
<div class="goodscount">
<img src="./imgs/times.png" style="width:13px;">&nbsp;&nbsp;总张数:${p.total }
<div style="float:right;width:30px;text-align:center;height:18px;line-height:15px;background-color:rgb(230,230,232)">...</div>
</div>
</div>

</div>
</div>
</c:forEach>
</body>
</html>