<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, user-scalable=no">
<title>修改地址</title>
<link rel="stylesheet" type="text/css" href="./css/common.css" />
<link rel="stylesheet" type="text/css" href="css/user.css" />
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
$(function() {
	var hidpro = $("#hidpro").val();
	if(hidpro!=''){
		$("#pro").val(hidpro);
	}
	var hidcity = $("#hidcity").val();
	if(hidcity!=''){
		$("#city").val(hidcity);
	}
	var hidarea = $("#hidarea").val();
	if(hidarea!=''){
		$("#area").val(hidarea);
	}
	var parent = $("#pro").val();
	var parentid = $("#city").val();
	var areaid = $("#area").val();
	if('${type}' == 0){
	document.getElementById("xqlocation").setAttribute("href","xqlocation://province="+parent+"&city="+parentid+"&area="+areaid);
	}
})

function selectCity(){
	var parentid = $("#pro").val();
	var cityid;
	if(parentid == ""){
		$("#city").empty();
		html = '';
		$("#city").append(html);
		if('${type}' == 0){
			document.getElementById("xqlocation").setAttribute("href","xqlocation://province=&city=&area="); 
		}
	}else{
		$.ajax({
			type:"post",
			url: "./getAreaByParentId",
			data: {parentid :parentid, type :1},
			success: function(msg){
				var msgdata = eval("("+msg+")");
				var citylist = msgdata.citylist;
				var arealist = msgdata.arealist;
				$("#city").empty();
				$("#area").empty();
				html = '';
				html +='<option value=""></option>';
				for(var i= 0; i< citylist.length; i++){
					html += '<option value="'+citylist[i].id+'">'+citylist[i].name+'</option>';
				}
				html2 = '';
				html2 +='<option value=""></option>';
				for(var i= 0; i<arealist.length; i++){
					html2 += '<option value="'+arealist[i].id+'">'+arealist[i].name+'</option>';
				}
				$("#city").append(html);
				$("#area").append(html2);
				cityid = citylist[0].id;
				if('${type}' == 0){
					document.getElementById("xqlocation").setAttribute("href","xqlocation://province="+parentid+"&city="+cityid+"&area="); 
				}
			}
		});
	}
}

function selectArea(){
	var parent = $("#pro").val();
	var parentid = $("#city").val();
	$.ajax({
		type:"post",
		url: "./getAreaByParentId",
		data: {parentid :parentid, type :2},
		success: function(msg){
			var msgdata = eval("("+msg+")");
			var arealist = msgdata.arealist;
			$("#area").empty();
			html = '';
			html +='<option value=""></option>';
			for(var i= 0; i<arealist.length; i++){
				html += '<option value="'+arealist[i].id+'">'+arealist[i].name+'</option>';
			}
			$("#area").append(html);
			areaid = arealist[0].id;
			if('${type}' == 0){
				document.getElementById("xqlocation").setAttribute("href","xqlocation://province="+parent+"&city="+parentid+"&area="+areaid); 
			}
		}
	});
}

function chooseAddress(){
	var parent = $("#pro").val();
	var parentid = $("#city").val();
	var areaid = $("#area").val();
	$("#editorform").submit();
// 	window.location.href = "./mission?province="+parent+"&city="+parentid+"&area="+areaid;
}
</script>
</head>

<body class="bj01">
<form action="mission" enctype="multipart/form-data" method="post" id="editorform">
<div style="width: 100%; height: auto; background: #fff; text-align: center;">
	<div style="width: 92%; height: 45px; border-bottom: 1px solid #e8e8e8; margin: 0 auto; line-height: 45px;">
		<div style="float:left;">省</div>
		<select style="float: left; margin-top: 10px; margin-left: 10px; width:100px;" id="pro" name="province" onchange="selectCity()">
			<option value="">全国</option>
			<c:forEach items="${prolist }" var="pro">
				<option value="${pro.id }">${pro.name }</option>
			</c:forEach>
		</select>
	</div>
	<div style="width: 92%; height: 45px; border-bottom: 1px solid #e8e8e8; margin: 0 auto; line-height: 45px;">
	<div style="float:left;">市</div>
		<select style="float: left; margin-top: 10px; margin-left: 10px; width:100px;" id="city" name="city" onchange="selectArea()">
			<option value=""></option>
			<c:forEach items="${citylist }" var="ci">
				<option value="${ci.id }">${ci.name }</option>
			</c:forEach>
		</select>
	</div>
	<div style="width: 92%; height: 45px; border-bottom: 1px solid #e8e8e8;margin: 0 auto; line-height: 45px;">
	<div style="float:left;">区</div>
		<select style="float: left; margin-top: 10px; margin-left: 10px; width:100px;" id="area" name="area">
			<option value=""></option>
			<c:forEach items="${arealist }" var="ar">
				<option value="${ar.id }">${ar.name }</option>
			</c:forEach>
		</select>
	</div>
</div>
<div style="position: fixed; bottom: 0px; width: 100%; height: 48px; border-top: 1px solid #e8e8e8; background: #fff;">
<div style="height: 8px;"></div>
<c:if test="${type == 0}">
<a id="xqlocation" href="">
	<div style="width: 73%; height: 32px; line-height: 32px; margin: 0 auto; text-align: center; background: #00d1bc; color: #fff; border-radius: 16px;">保存修改</div>
</a>
</c:if>
<c:if test="${type == 1}">
	<div style="width: 73%; height: 32px; line-height: 32px; margin: 0 auto; text-align: center; background: #00d1bc; color: #fff; border-radius: 16px;" onclick="chooseAddress()">保存修改</div>
</c:if>
</div>
</form>

<%@ include file="cs.jsp" %>
<%CS cs = new CS(1256812462);cs.setHttpServlet(request,response);
String imgurl = cs.trackPageView();%> 
<img src="<%= imgurl %>" width="0" height="0"  />
</body>
</html>
