<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, user-scalable=no">
<title>修改性别</title>
<link rel="stylesheet" type="text/css" href="css/user.css" />
<link rel="stylesheet" type="text/css" href="css/common.css" />
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
$(function(){
	var gender = $("#gender").val();
	if(gender == "男"){
		$("#m").show();
		$("#f").hide();
	}else{
		$("#f").show();
		$("#m").hide();
	}
});

function editorUser(){
	$("#editorform").submit();
}

function selectM(){
	$("#gender").val("男");
	$("#m").show();
	$("#f").hide();
}
function selectF(){
	$("#gender").val("女");
	$("#f").show();
	$("#m").hide();
}
</script>
</head>

<body class="bj01">
<form action="editor_gender" enctype="multipart/form-data" method="post" id="editorform">
<input type="hidden" value="${id }" name="id"/>
<input type="hidden" value="${gender }" name="gender" id="gender"/>
<div style="width: 100%; height: 90px; background: #fff; text-align: center;">
	<div style="width: 92%; height: 45px; border-bottom: 1px solid #e8e8e8; margin: 0 auto; line-height: 45px;" onclick="selectM()">
		<div style="float:left;">男</div>
		<div style="float:right;" id="m">
		<img src="imgs/select_gender.png" style="width: 14px; height: 11px; margin-top: 17px;"> 
		</div>
	</div>
	<div style="width: 92%; height: 45px;margin: 0 auto; line-height: 45px;" onclick="selectF()">
		<div style="float:left;">女</div>
		<div style="float:right;" id="f">
		<img src="imgs/select_gender.png" style="width: 14px; height: 11px; margin-top: 17px;"> 
		</div>
	</div>
</div>
<div style="position: fixed; bottom: 0px; width: 100%; height: 48px; border-top: 1px solid #e8e8e8; background: #fff;">
<div style="height: 8px;"></div>
	<div style="width: 73%; height: 32px; line-height: 32px; margin: 0 auto; text-align: center; background: #00d1bc; color: #fff; border-radius: 16px;" onclick="editorUser()">保存修改</div>
</div>
</form>

<%@ include file="cs.jsp" %>
<%CS cs = new CS(1256812462);cs.setHttpServlet(request,response);
String imgurl = cs.trackPageView();%> 
<img src="<%= imgurl %>" width="0" height="0"  />
</body>
</html>
