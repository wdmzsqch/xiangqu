<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, user-scalable=no">
<title>用户昵称</title>
<link rel="stylesheet" type="text/css" href="css/user.css" />
<link rel="stylesheet" type="text/css" href="css/common.css" />
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">

function editorUser(){
	$("#editorform").submit();
}
</script>
</head>

<body class="bj01">
<form action="editor_nikeName" enctype="multipart/form-data" method="post" id="editorform">
<input type="hidden" value="${id }" name="id"/>
<div style="width: 100%; height: 90px; background: #fff; text-align: center;">
	<input type="text" name="nickName" style="width: 90%; height: 30px; margin-top:30px; margin-left: 5%; border: 1px solid #e8e8e8; border-radius: 6px;" id="${userName }" />
</div>
<div style="position: fixed; bottom: 0px; width: 100%; height: 48px; border: 1px solid #e8e8e8; background: #fff;">
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
