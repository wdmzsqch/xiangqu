<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, user-scalable=no">
<title>个人资料</title>
<link rel="stylesheet" type="text/css" href="./css/common.css" />
<link rel="stylesheet" type="text/css" href="css/user.css" />
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">

	function changeimage(id, picurl){
		window.location.href = "editorpic?id="+id+"&picurl="+picurl;
	}
	
	function changenikeName(id, nickName){
		window.location.href = "editornikeName?id="+id+"&nickName="+nickName;
	}
	
	function changegender(id, gender){
		window.location.href = "editorgender?id="+id+"&gender="+gender;
	}
	
	function changemoblie(id, moblie){
		window.location.href = "editormoblie?id="+id+"&moblie="+moblie;
	}
	
	
	function changeaddress(id){
		window.location.href = "editoraddress?id="+id+"&type=2";
	}
</script>
<style type="text/css">
.common_div {
	border-bottom: #ccc 1px solid;
	color: #666666;
	line-height: 30px;
	position: relative;
}

.key_left {
	margin-left: 10px;
}

.value_right {
	position:absolute;
	right: 10px;
	top:0px;
	color: #ccc;
}

.common_div  input{
	text-align: right;
	color: #ccc;
	border: 0px;
	background-color: #f6f6f6;
}
</style>
</head>

<body class="bj01">
<%-- 	<jsp:include page="./top.jsp" /> --%>
	<form action="./edit_user" method="post">
	<div style="height: 62px;">
		<div style="width: 92%; height: 62px; border-bottom: 1px solid #e8e8e8; margin: 0 auto; line-height: 62px;" onclick="changeimage(${user.id},<c:if test="${fn:contains(user.pic, 'http://')}">'${user.pic}'</c:if>
<c:if test="${!fn:contains(user.pic, 'http://')}">'${fileurl}${user.pic}'</c:if>)">
		 <div style="float:left;">用户头像</div>
		 <img src="imgs/user_center_jt.png" style="float:right; margin-top: 17px;" />
		 <img src="<c:if test="${fn:contains(user.pic, 'http://')}">${user.pic}</c:if>
<c:if test="${!fn:contains(user.pic, 'http://')}">${fileurl}${user.pic}</c:if>" style="float:right; width: 31px; height: 31px; margin-top: 14px; border-radius: 15px;">
		</div>
	</div>	
	<div style="height: 47px;">
		<div style="width: 92%; height: 47px; border-bottom: 1px solid #e8e8e8; margin: 0 auto; line-height: 47px;" >
		<div style="float:left;">帐号</div>
		 <img src="imgs/user_center_jt.png" style="float:right; margin-top: 12px;" />
		<div style="float:right; color: #bfbfbf; font-size: 15px;">${user.userName}</div>
		</div>
	</div>

	<div style="height: 47px;">
		<div style="width: 92%; height: 47px; border-bottom: 1px solid #e8e8e8; margin: 0 auto; line-height: 47px;" onclick="changenikeName(${user.id},'${user.nickName}')">
		<div style="float:left;">昵称</div>
		<img src="imgs/user_center_jt.png" style="float:right; margin-top: 12px;" />
		<div style="float:right; color: #bfbfbf; font-size: 15px;">${user.nickName}</div>
		</div>
	</div>
	
	<div style="height: 47px;">
		<div style="width: 92%; height: 47px; border-bottom: 1px solid #e8e8e8; margin: 0 auto; line-height: 47px;" onclick="changemoblie(${user.id},'${user.moblie}')">
		<div style="float:left;">手机号码</div>
		<img src="imgs/user_center_jt.png" style="float:right; margin-top: 12px;" />
		<div style="float:right; color: #bfbfbf; font-size: 15px;">${user.moblie}</div>
		</div>
	</div>

	<div style="height: 47px;">
		<div style="width: 92%; height: 47px; border-bottom: 1px solid #e8e8e8; margin: 0 auto; line-height: 47px;" onclick="changegender(${user.id},'${user.gender}')">
		<div style="float:left;">性别</div>
		<img src="imgs/user_center_jt.png" style="float:right; margin-top: 12px;" />
		<div style="float:right; color: #bfbfbf; font-size: 15px;">${user.gender}</div>
		</div>
	</div>

	<div style="height: 47px;">
		<div style="width: 92%; height: 47px; border-bottom: 1px solid #e8e8e8; margin: 0 auto; line-height: 47px;" onclick="changeaddress(${user.id})">
		<div style="float:left;">所在地</div>
		<img src="imgs/user_center_jt.png" style="float:right; margin-top: 12px;" />
		<div style="float:right; color: #bfbfbf; font-size: 15px;">${user.proname}&nbsp;${user.cityname }</div>
		</div>
	</div>

<!-- 	<div class="common_div">
		<div class="key_left">所在地</div>
		<div class="value_right">所在地</div>
	</div> -->
	<input type="hidden" name="uid" value="${user.id }"/>
</form>

<%@ include file="cs.jsp" %>
<%CS cs = new CS(1256812462);cs.setHttpServlet(request,response);
String imgurl = cs.trackPageView();%> 
<img src="<%= imgurl %>" width="0" height="0"  />
</body>
</html>
