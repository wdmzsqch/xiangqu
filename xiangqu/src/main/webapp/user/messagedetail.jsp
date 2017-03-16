<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<title>消息详情</title>
</head>
<body style="margin: 0px; padding: 0px; border: 0px; background-color: #fff; font-family: '华文细黑'">
	<div style="width: 96%; height: auto; color: #414141; margin-left: 2%;">
		<div style="font-size: 17px; margin-top: 15px;">[${shopmessage.title }]</div>
		<div style="font-size: 10px; margin-top: 5px; color: #bfbfbf;">
		 <fmt:formatDate value="${shopmessage.publishTime }" pattern="yyyy-MM-dd HH:mm" /> 
		 </div> 
		 <div style="font-size: 13px; margin-top: 20px; word-break: break-all">${shopmessage.content }</div>
	</div>
<%@ include file="cs.jsp" %>
<%CS cs = new CS(1256812462);cs.setHttpServlet(request,response);
String imgurl = cs.trackPageView();%> 
<img src="<%= imgurl %>" width="0" height="0"  />
</body>

</html>