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
<title>${eventinfo.name }</title>
</head>
<body style="background-color:rgb(246,246,246);font-family:'华文细黑'">
<div style="padding-left:18px;padding-right:18px;line-height:18px;font-size:14px;padding-top:5px;padding-bottom:5px;">
活动概况
</div>
<div class="contentdiv" >
<img  style="float:left;width:65px;height:65px;" src="${fileRootUrl }${eventinfo.pic}">
<div style="margin-left:12px;float:left;width: 70%;">
<div style="width:100%;font-size:16px;height:22px;" >${eventinfo.name }</div>
</div>
</div>
<div class="detailcontent">
<font style="float:left;">开始时间</font>
<font style="float:right;"><fmt:formatDate value="${eventinfo.startTime}" type="date" dateStyle="medium" /></font>
</div>
<div class="detailcontent">
<font style="float:left;">结束时间</font>
<font style="float:right;"><fmt:formatDate value="${eventinfo.endTime}" type="date" dateStyle="medium" /></font>
</div>
<div class="detailcontent">
<font style="float:left;">介绍</font>
<font style="float:right;">${eventinfo.intro }</font>
</div>
<div class="detailcontent" style="height: auto;min-height: 18px;">
<div style="float:left; width: 40px;">详情</div>
<div style="margin-left: 40px; width: 100%;">${eventinfo.detail }</div>
</div>

<div class="detailcontent">
<font style="float:left;">报名人数</font>
<font style="float:right;">${eventinfo.signCount }</font>
</div>
<div class="detailcontent">
<font style="float:left;">参加人数</font>
<font style="float:right;">${eventinfo.joinCount }</font>
</div>
</body>

</html>