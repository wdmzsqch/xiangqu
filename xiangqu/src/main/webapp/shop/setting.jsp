<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, user-scalable=no">
<link rel="stylesheet" type="text/css" href="./css/common.css" />
<script type="text/javascript" src="./js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	$(function() {
		setTabSelected(4);
	})
	
	function changepage(index) {
		switch (index) {
		case 1:
			window.location.href= './password';return false;
			break;
		case 2:
			window.location.href= 'http://www.baidu.com';return false;
			break;
		default:
			break;
		}
	}
	
	function logout(){
		$.ajax({
			url:'logout',
			type:'post',
		})
		window.location.href= './index';return false;
	}
	
	function clearsession(){
		$.ajax({
			type: "post",
			url: "./clearsession",
			success: function(data){
				if(data == "success"){
					window.location.reload();
				}
			}
		});
	}
</script>
<title>系统设置</title>
</head>
<body style="background-color: #f6f6f6">

	<!-- 修改密码 -->
	<div style="line-height: 35px; font-size: 13px; width: 100%; height: 35px; background-color: #ffffff" onclick="changepage(1)">
		<span style="float: left; margin-left: 18px">修改密码</span>
		<div style="float: right;">
			<img src="./imgs/rightarrow.png" width="15px" height="15px " style="margin-right: 7px; margin-top: 10px;"/>
		</div>
	</div>
	<div style="clear:both;border-bottom: 1px solid #CCCCCC; margin-left: 22px"></div>

 	<!-- 清除当前缓存 -->
<!-- 	<div style="line-height: 35px; font-size: 13px; width: 100%; height: 35px; background-color: #ffffff" onclick="clearsession()"> -->
<!-- 		<span style="float: left; margin-left: 18px">清除当前缓存</span> -->
<!-- 		<div style="float: right;"> -->
<!-- 			<span style="float: right; margin-right: 18px; color: #e84e4e"></span> -->
<!-- 		</div> -->
<!-- 	</div> -->

	<div style="width:100%; height: 23px; background-color: #ffffffff; "></div> 

	<!-- 注销登入 -->
	<c:if test="${isapp == 1}">
	<a href="xqshopexit://">
	<div style=" margin: auto; width:90%; height: 45px; background-color: #e84e4e; line-height: 45px; text-align:center; font-size: 13px; border-radius: 5px;">
		<span style="color: #ffffff; font-size: 18px;">注销登入</span>
	</div>
	</a>
	</c:if>
	<c:if test="${isapp != 1 }">
	<div style=" margin: auto; width:90%; height: 45px; background-color: #e84e4e; line-height: 45px; text-align:center; font-size: 13px; border-radius: 5px;" onclick="logout()">
		<span style="color: #ffffff; font-size: 18px;">注销登入</span>
	</div>
	</c:if>

	<%@ include file="tab.jsp"%>
</body>

</html>