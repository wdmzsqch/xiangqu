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
<title>任务管理</title>
<script type="text/javascript" src="./js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
$(function(){
	setTabSelected(1);
})

function mission(type){
	window.location.href="./mission?type="+type;
}

function detail(id){
	window.location.href="./missiondetail?id="+id;
}

function addmission(){
	window.location.href="./turnaddmission";
}
</script>
</head>
<body style="margin:0;background-color:rgb(246,246,246);font-family:'华文细黑'">
<div style="width:100%;padding-top: 10px;padding-bottom: 10px; background-color:#ffffff;position: relative;">
<div class="typediv" >
<div class="typebtn typeleft <c:if test="${type==1 }">unusetype</c:if>" onclick="mission(1)">进行中</div>
<div class="typebtn typeright <c:if test="${type==2 }">unusetype</c:if>" onclick="mission(2)">已结束</div>
<div style="position:absolute;right: 10px;font-size: 14px;" onclick="addmission()">发布任务</div>
</div>
</div>

<c:forEach items="${missionlist}" var="p">
<div style="padding-top:15px;clear:both;">
<div style="padding-top:8px;padding-bottom:8px;border-bottom:1px solid rgb(166,166,166);padding-left:18px;padding-right:18px;background-color:#ffffff;height:auto;overflow:hidden">
<div style="line-height:18px;float:left;font-size:13px;">
<img src="./imgs/order.png" style="width:13px;position:relative;top:2px;">
任务编号:${p.id }
</div>
<div style="line-height:18px;float:right;text-algin:right;font-size:13px;color:rgb(166,166,166)">
结束时间:<fmt:formatDate value="${p.endTime}" type="date" dateStyle="medium" />
</div>
<img src="./imgs/time.png" style="width:13px;float:right;position:relative;top:2px;">

</div>
<div class="contentdiv" onclick="detail(${p.id })">
<img  style="float:left;width:65px;height:65px;" src="${fileRootUrl }${p.pic}">
<div style="margin-left:12px;float:left;width: 70%;">
<div style="width:100%;font-size:16px;height:22px;" class="overflowOmit">${p.name }</div>
<div class="goodscount">
<img src="./imgs/times.png" style="width:13px;">&nbsp;&nbsp;点击量:<fmt:formatNumber value="${p.totalTimes-p.ramianTimes }" />
<div style="float:right;width:30px;text-align:center;height:18px;line-height:15px;background-color:rgb(230,230,232)">...</div>
</div>
<p class="goodscount">
剩余可投放量:<font style="color:#F84E4E">${p.ramianTimes }
<%-- <fmt:formatNumber value="${p.income*p.count/100 }" pattern="#,#0.00#" type="currency"/>  --%>
</font>
</p>
</div>

</div>
</div>
</c:forEach>
<div style="width: 100%; height: 47px;"></div>
<%@ include file="tab.jsp"%> 
</body>
</html>