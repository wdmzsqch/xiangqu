<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no">
<link rel="stylesheet" type="text/css" href="./css/common.css" />
<link rel="stylesheet" type="text/css" href="./css/address.css" />
<script src="./js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
function goback(){
	window.location.replace("./cardVerification");
}
</script>
<title>验证卡密</title>
</head>
<body style="color: #bfbfbf;">
<div style="width: 100%; height: 40px;"></div>
<div style="width: 100%; height: 20px; line-height: 20px; text-align: center; color: #000; font-weight: bold; ">验证成功</div>
<div class="submitdiv" style="text-align:center;">
<div style="height: 8px;"></div>
<input type="button" onclick="goback()" style="width: 73%; height: 32px; line-height: 32px; margin: 0 auto; text-align: center; background: #00d1bc; color: #fff; border-radius: 16px;" value="返回"></input>
</div>

<%@ include file="cs.jsp" %>
<%CS cs = new CS(1256812462);cs.setHttpServlet(request,response);
String imgurl = cs.trackPageView();%> 
<img src="<%= imgurl %>" width="0" height="0"  />
</body>
</html>