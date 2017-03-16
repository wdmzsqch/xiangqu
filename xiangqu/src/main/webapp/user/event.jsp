<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no">
<title>活动</title>
<link rel="stylesheet" type="text/css" href="css/common.css" />
<link rel="stylesheet" type="text/css" href="css/event.css" />
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	function lookeventdetail(id){
		window.location.href = "./lookeventdetail?id="+id;
	}
</script>
</head>
<body style="font-family: '微软雅黑'; background: #f2f2ef;">
	<div style="width: 92%; margin: 0 auto;">
	<c:forEach items="${list}" var="p">
		<div class="event_Div" onclick="lookeventdetail(${p.id})">
			<img src="${fileurl }${p.pic}" width="100%" height="138px;">
			<div style="width: 92%; height: 76px; margin: 0 auto;">
				<div class="event_Div_title">${p.name }</div>
				<div class="event_Dic_intro">${p.intro }</div>
			</div>
		</div>
	</c:forEach>
	</div>
<%@ include file="cs.jsp" %>
<%CS cs = new CS(1256812462);cs.setHttpServlet(request,response);
String imgurl = cs.trackPageView();%> 
<img src="<%= imgurl %>" width="0" height="0"  />
</body>
</html>