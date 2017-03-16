<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, user-scalable=no">
<link rel="stylesheet" type="text/css" href="./css/common.css" />
<script type="text/javascript" src="./js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	function changepwd() {

		var oldpwd = $("#oldpwd").val();
		var newpwd = $("#newpwd").val();
		var pwdagain = $("#pwdagain").val();

		if (oldpwd == '') {
			alert("请输入原始密码!");
			return;
		}

		if (newpwd == '') {
			alert("请输入新密码!");
			return;
		}

		if (pwdagain == '') {
			alert("请输入确认密码!");
			return;
		}

		if (newpwd != pwdagain) {
			alert("两次输入的新密码不一致！");
			return;
		}

		if (oldpwd == newpwd) {
			alert("新旧密码不可以相同！");
			return;
		}

		$.ajax({
			url : 'changepwd',
			type : 'post',
			data : {
				oldpwd : oldpwd,
				newpwd : newpwd
			},
			success : function(result) {
				var code = result.code;
				if (code == 1) {
					alert("修改成功!");
					window.location.replace('./setting');
				} else {
					alert(result.message);
				}
			}
		})
	}
</script>
<title>修改密码</title>
</head>
<body
	style="margin: 0px; padding: 0px; border: 0px; background-color: #f6f6f6; font-family: '华文细黑'">

	<!-- 原始密码-->
	<div
		style="line-height: 35px; font-size: 13px; width: 100%; height: 35px; background-color: #ffffff">
		<span style="float: left; margin-left: 18px">原始密码</span>
		<div style="float: left; position:fixed; margin-left: 130px ">
			<input type="text" id="oldpwd" placeholder="请输入原始密码" style="height: 31px ; width:100%;  border: 0px ; text_hint:'www';" />
		</div>
	</div>
	<div
		style="clear: both; border-bottom: 1px solid #CCCCCC; margin-left: 22px"></div>

	<!-- 新密码 -->
	<div
		style="line-height: 35px; font-size: 13px; width: 100%; height: 35px; background-color: #ffffff">
		<span style="float: left; margin-left: 18px">新密码</span>
		<div style="float: left; position:fixed; margin-left: 130px ">
			<input type="text" id="newpwd" placeholder="请输入新密码" style="height: 31px ; width:100%;  border: 0px;" />
		</div>
	</div>

	<div
		style="clear: both; border-bottom: 1px solid #CCCCCC; margin-left: 22px"></div>

	<!-- 确认密码 -->
	<div
		style="line-height: 35px; font-size: 13px; width: 100%; height: 35px; background-color: #ffffff">
		<span style="float: left; margin-left: 18px">确认密码</span>
		<div style="float: left; position:fixed; margin-left: 130px ">
			<input type="text" id="pwdagain" placeholder="请输入新密码" style="height: 31px ; width:100%;  border: 0px;"/>
		</div>
	</div>

	<div style="width: 100%; height: 22px; background: #fffffffff;"></div>

	<!-- 确认 -->
	<div
		style="margin: auto; width: 90%; height: 45px; background-color: #e84e4e; line-height: 45px; text-align: center; font-size: 13px; border-radius: 5px;"
		onclick="changepwd()">
		<span style="color: #ffffff; font-size: 18px;">确认</span>
	</div>

</body>

</html>