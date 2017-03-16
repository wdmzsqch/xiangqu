<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>后台管理</title>
<link rel="stylesheet" type="text/css" href="css/common.css" />
<link rel="stylesheet" type="text/css" href="css/main.css" />
<script src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/libs/modernizr.min.js"></script>
<script type="text/javascript">
	function checkpassword(){
		var password = $("#password").val();
		if(password == ""){
			alert("请输入新密码");
			return false;
		}
		var password2 = $("#password2").val();
		if(password2 == ""){
			alert("请确认密码");
			return false;
		}
		if(password != password2){
			alert("确认密码错误");
			return false
		}
	}
</script>
</head>
<body>
	<jsp:include page="./sidebar.jsp"></jsp:include>
	<div class="main-wrap">

		<div class="crumb-wrap">
			<div class="crumb-list">
				<i class="icon-font"></i><a href="./home_page">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="/jscss/admin/design/">用户列表管理</a><span
					class="crumb-step">&gt;</span><span>修改信息</span>
			</div>
		</div>
		<div class="result-wrap">
			<div class="result-content">
				<form action="./editorpassword" method="post" id="myform" name="myform" enctype="multipart/form-data">
					<input type="hidden" value="${userId }" name="id"/>
					<table class="insert-tab" width="100%">
						<tbody>
							<tr>
								<th>重设新密码：</th>
								<td><input class="common-text required" id="password" name="password" size="50" type="password"></td>
							</tr>
							<tr>
								<th>确认新密码：</th>
								<td><input class="common-text required" id="password2" name="password2" size="50" type="password"></td>
							</tr>
							<tr>
								<th></th>
								<td><input class="btn btn-primary btn6 mr10" value="保存" type="submit" onclick="return checkpassword()"> <input class="btn btn6" onClick="history.go(-1)" value="返回" type="button"></td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>

	</div>
</body>
</html>