<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, user-scalable=no">
<link rel="stylesheet" type="text/css" href="./css/common.css" />
<link rel="stylesheet" type="text/css" href="./css/mission.css" />
	<script type="text/javascript" src="./js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
$(function(){
	setTabSelected(1);
})

</script>
<title>${mission.name }</title>
</head>
<body style="background-color:rgb(246,246,246);font-family:'华文细黑'">
<div style="padding-left:18px;padding-right:18px;line-height:18px;font-size:14px;padding-top:5px;padding-bottom:5px;">
任务概况
</div>
<div class="contentdiv" >
<img  style="float:left;width:65px;height:65px;" src="${fileRootUrl }${mission.pic}">
<div style="margin-left:12px;float:left;width: 70%;">
<div style="width:100%;font-size:16px;height:22px;" >${mission.name }</div>
</div>
</div>
<!-- <div class="detailcontent"> -->
<!-- <font style="float:left;">分享单价(元)</font> -->
<%-- <font style="float:right;"><fmt:formatNumber value="${mission.income/100 }" pattern="#,#0.00#" type="currency"/></font> --%>
<!-- </div> -->
<div class="detailcontent">
<font style="float:left;">剩余可投放量(条)</font>
<font style="float:right;">${mission.ramianTimes }
<%-- <fmt:formatNumber value="${mission.income* mission.ramianTimes/100}" pattern="#,#0.00#" type="currency"/> --%>
</font>
</div>
<div class="detailcontent">
<font style="float:left;">点击量(次)</font>
<font style="float:right;"><fmt:formatNumber value="${mission.totalTimes- mission.ramianTimes}" /></font>
</div>
<div class="detailcontent" style="border-bottom:none;">
<font style="float:left;">总投放量(条)</font>
<font style="float:right;color:#F84E4E">${mission.totalTimes }
<%-- <fmt:formatNumber value="${mission.income*mission.count/100}" pattern="#,#0.00#" type="currency"/> --%>
</font>
</div>
<div style="padding-left:18px;padding-right:18px;line-height:18px;font-size:14px;padding-top:5px;padding-bottom:5px;">
任务详情
</div>
<div class="detailcontent" >
<font style="float:left;color:rgb(152,152,152)">任务编号</font>
<font style="float:right;">${mission.id}</font>
</div>
<div class="detailcontent" >
<font style="float:left;color:rgb(152,152,152)">合同编号</font>
<font style="float:right;">${mission.contractNo }</font>
</div>
<div class="detailcontent" >
<font style="float:left;color:rgb(152,152,152)">开始时间</font>
<font style="float:right;"><fmt:formatDate value="${mission.startDate }" dateStyle="medium"/></font>
</div>
<div class="detailcontent" >
<font style="float:left;color:rgb(152,152,152)">结束时间</font>
<font style="float:right;"><fmt:formatDate value="${mission.endTime }" dateStyle="medium"/></font>
</div>
<div class="detailcontent"  style="border-bottom:none;">
<font style="float:left;color:rgb(152,152,152)">投放城市</font>
<font style="float:right;">${mission.cityname }</font>
</div>
<%@ include file="tab.jsp"%> 
</body>

</html>