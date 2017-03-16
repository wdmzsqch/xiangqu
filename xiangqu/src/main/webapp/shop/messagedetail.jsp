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
<title>消息详情</title>
</head>
<body
	style="margin: 0px; padding: 0px; border: 0px; background-color: #f6f6f6; font-family: '华文细黑'">

	<div style="width: 100%; height: auto; float: left;">

		<span
			style="width: 97%; float: left; margin-left: 3%; font-size: 16px; margin-top: 23px;">[${shopmessage.title }]</span>
		<span
			style="width: 97%; float: left; margin-left: 3%; font-size: 10px; margin-top: 10px; color: #bfbfbf;"><fmt:formatDate value="${shopmessage.publishTime }" pattern="yyyy-MM-dd HH:mm"/>
			</span> <span
			style="width: 94%; float: left; margin-left: 3%; margin-right: 3%; font-size: 13px; margin-top: 35px; word-break: break-all">${shopmessage.content }</span>
	</div>

</body>

</html>