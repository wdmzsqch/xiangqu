<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
.text-overflow {
	display: block; /*内联对象需加*/
	word-break: keep-all; /* 不换行 */
	white-space: nowrap; /* 不换行 */
	overflow: hidden; /* 内容超出宽度时隐藏超出部分的内容 */
	text-overflow: ellipsis;
	/* 当对象内文本溢出时显示省略标记(...);需与overflow:hidden;一起使用。*/
}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, user-scalable=no">
<link rel="stylesheet" type="text/css" href="./css/common.css" />
<script type="text/javascript" src="./js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	$(function() {
		setTabSelected(4);
	})
	
	function messagedetail(id) {
		window.location.href= './messagedetail?id='+id;
	}
	
</script>
<title>系统消息</title>
</head>
<body
	style="margin: 0px; padding: 0px; border: 0px; background-color: #f6f6f6; font-family: '华文细黑'">

<c:forEach items="${shopmessage}" var="p">
	<div
		style="width: 100%; height: 80px; font-size: 13px; background-color: #ffffff;" onclick="messagedetail(${p.id})">
		<span
			style="float: left; margin-left: 5%; margin-right:20px; margin-top: 10px; width: 80%; font-weight: bold; font-size: 15px;"
			class="text-overflow">[${p.title }]</span>
		<img src="./imgs/rightarrow.png" style="margin-top: 12px; float: right; margin-right: 5%;">
			
		 <span
			style="float: left; margin-left: 5%; margin-top: 5px; width: 90%; font-weight: bold;"
			class="text-overflow">${p.content }</span>
		<span
			style="float: right; margin-right: 5%; margin-top: 10px; width: 100%; line-height:15px; text-align: right; color: #a6a6a6; font-weight: bold; font-size: 10px">查看详情&nbsp;&nbsp;<fmt:formatDate value="${p.publishTime }" pattern="MM-dd"/></span>
	</div>
	<div style="width: 100%; height: 1px; background-color:#f6f6f6; "></div>
</c:forEach>

	<%@ include file="tab.jsp"%>
</body>
</html>