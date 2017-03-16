<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, user-scalable=no">
<link rel="stylesheet" type="text/css" href="./css/common.css" />
<script type="text/javascript" src="./js/jquery-1.11.3.min.js"></script>
<title>系统消息</title>
<style type="text/css">
.text-overflow {
	display: block; /*内联对象需加*/
	word-break: keep-all; /* 不换行 */
	white-space: nowrap; /* 不换行 */
	overflow: hidden; /* 内容超出宽度时隐藏超出部分的内容 */
	text-overflow: ellipsis;
	/* 当对象内文本溢出时显示省略标记(...);需与overflow:hidden;一起使用。*/
	float: left; 
	margin-left: 5%;
	width: 80%; 
}

.line {
	width: 100%;
	height:1px;
	border-bottom: 1px solid #ccc;
}
</style>
<script type="text/javascript">
	function messagedetail(id) {
		window.location.href= './messagedetail?id='+id;
	}
</script>
</head>
<body style="margin: 0px; padding: 0px; border: 0px; background-color: #f6f6f6; font-family: '华文细黑'">
<div class="line"></div>
	<c:forEach items="${usermessage}" var="p">
		<div style="width: 100%; height: 85px; background-color: #fff; position: relative;" onclick="messagedetail(${p.id})">
			<span style="margin-top: 10px; font-size: 15px; color: #414141" class="text-overflow">[${p.title }]</span> 
			<span style="margin-top: 10px; font-size: 13px; color: #bfbfbf;" class="text-overflow">${p.content }</span>
			<span style="margin-top: 5px;   font-size: 12px; color: #bfbfbf;" class="text-overflow">
			<fmt:formatDate value="${p.publishTime }" pattern="yyyy-MM-dd HH:mm" /></span>
			<img src="./imgs/rightarrow.png" style="position:absolute; width:6px; height:12px; top:32px; right: 25px;" />
		</div>
		<div class="line"></div>
		
	</c:forEach>
	
<%@ include file="cs.jsp" %>
<%CS cs = new CS(1256812462);cs.setHttpServlet(request,response);
String imgurl = cs.trackPageView();%> 
<img src="<%= imgurl %>" width="0" height="0"  />
</body>

</html>