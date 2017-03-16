<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, user-scalable=no">
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/common.css" />
<script type="text/javascript">
	function resetpassword(){
		var userName = $("input[name=userName]").val();
		if(userName == ""){
			$("#error").text("用户名不能为空");
			return;
		}
		$("#resetForm").submit();
	}
</script>
<title>找回密码</title>
</head>
<body style="font-family: '微软雅黑';">
<form action="./resetpassword" method="post" id="resetForm">
	<div style="width: 100%; height: 30px;"></div>
	<div
		style="width: 75%; height: 39px; border: 1px solid #e3e3e3; border-radius: 20px; margin: 0 auto;">
		<img src="imgs/user_icon.png"
			style="float: left; width: 11px; height: 16px; margin-left: 15px; margin-top: 10px;">
		<input
			style="width: 60%; height: 23px; border: 0px; border-left: 1px solid #e3e3e3; float: left; margin-top: 7px; margin-left: 10px; padding-left: 5px; font-size: 13px;" placeholder="请输入用户名" name="userName"/>
	</div>
	<div style="width: 100%; height: 17px; color: #ff5c4b; line-height: 17px; text-align: center;font-size: 14px;" id="error"></div>
	<div
		style="width: 75%; height: 39px; background: #00d1bc; color: #fff; border: 1px solid #e3e3e3; border-radius: 20px; margin: 0 auto;text-align: center; line-height: 39px;" onclick="resetpassword()">
		下一步
	</div>
</form>
<%@ include file="cs.jsp" %>
<%CS cs = new CS(1256812462);cs.setHttpServlet(request,response);
String imgurl = cs.trackPageView();%> 
<img src="<%= imgurl %>" width="0" height="0"  />
</body>
</html>