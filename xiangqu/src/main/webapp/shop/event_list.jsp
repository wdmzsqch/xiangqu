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
<title>活动列表</title>
<script type="text/javascript" src="./js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">


function detail(id){
	window.location.href="./sheventdetail?id="+id;
}

</script>
</head>
<body style="margin:0;background-color:rgb(246,246,246);font-family:'华文细黑'">

<c:forEach items="${eventlist}" var="p">
<div style="padding-top:15px;clear:both;">
<div style="padding-top:8px;padding-bottom:8px;border-bottom:1px solid rgb(166,166,166);padding-left:18px;padding-right:18px;background-color:#ffffff;height:auto;overflow:hidden">
<div style="line-height:18px;float:left;font-size:13px;">
<img src="./imgs/order.png" style="width:13px;position:relative;top:2px;">
${p.name }
</div>
<div style="line-height:18px;float:right;text-algin:right;font-size:13px;color:rgb(166,166,166)">

活动时间:<fmt:formatDate value="${p.startTime}" pattern="yyyy.MM.dd" />-<fmt:formatDate value="${p.endTime}" pattern="yyyy.MM.dd"  />
</div>
<img src="./imgs/time.png" style="width:13px;float:right;position:relative;top:2px;">

</div>
<div class="contentdiv" onclick="detail(${p.id })">
<img  style="float:left;width:65px;height:65px;" src="${fileRootUrl }${p.pic}">
<div style="margin-left:12px;float:left;width: 70%;">
<div style="width:100%;font-size:16px;height:22px;" class="overflowOmit">${p.intro }</div>
</div>

</div>
</div>
</c:forEach>
</body>
</html>