<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, user-scalable=no">
<title>登陆</title>
<script type="text/javascript" src="./js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	function login() {
		var loginname = $("#loginname").val();
		var password = $("#password").val();
		if (loginname == '') {
			alert("请输入用户名!");
			return;
		}
		if (password == '') {
			alert("请输入密码!");
			return;
		}
		$.ajax({
			url : 'login',
			type : 'post',
			data : {
				loginname : loginname,
				password : password
			},
			success : function(msg) {
				var code = msg.code;
				if (code == 1) {
					alert("登入成功!");
					window.location.replace('./mission');
				} else {
					alert(msg.message);
				}
			}
		})
	}
</script>
<style type="text/css">
.text {
	color: #ffffff;
	background-color: #F84E4E;
	border: 1px solid #ffffff;
	width: 85%;
	padding-left: 5%;
	margin-left: 5%;
	height: 40px;
	line-height: 40px
}
</style>
</head>
<body style="margin: 0; background-color: #F84E4E;">
	<div style="margin-top: 50px;">
		<form action="login">
			<input class="text" type="text" id="loginname" placeholder="用户名" style="border-radius:8px 8px 0px 0px; ">
			<input class="text" type="password" id="password" placeholder="密码" style="margin-top: -1px; border-radius:0px 0px 8px 8px; ">
			<input type="button" onclick="login()" value="登入" style="font-size:16px; border-color: #ffffff; border-radius:8px; height:40px; background: #ffffff; color: #f84e4e; width: 90%; margin-left: 5%; margin-top: 20px;">
		</form>
	</div>
	<div style="margin-top: 20px; color: #fff; text-align: center;">获取商家登陆账号请致电400-618-2121</div>
</body>

</html>