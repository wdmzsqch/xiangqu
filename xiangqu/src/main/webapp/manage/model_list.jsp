<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>后台管理</title>
<link rel="stylesheet" type="text/css" href="css/common.css" />
<link rel="stylesheet" type="text/css" href="css/main.css" />
<link rel="stylesheet" type="text/css" href="css/page.css" />

<style type="text/css">
.submit_link {
	color: #428BCA;
	text-decoration: none;
	box-sizing: border-box;
	line-height: 35px;
	border-radius: 0;
	background-image: none;
	box-shadow: none;
	background-color: transparent;
	background-repeat: repeat;
	background-position: center;
	border-color: transparent;
	cursor: pointer;
	border: 0 solid #000000;
	font: 14px/1.5 "微软雅黑";
	padding: 0px;
	margin: 0px;
}
</style>
<script type="text/javascript" src="js/libs/modernizr.min.js"></script>
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
<script src="./js/jquery-1.11.3.min.js"></script>
</head>
<body>
	<jsp:include page="./sidebar.jsp"></jsp:include>
	<div class="main-wrap">

		<div class="crumb-wrap">
			<div class="crumb-list">
				<i class="icon-font"></i>
				<a href="./home_page">首页</a>
				<span class="crumb-step">&gt;</span><span class="crumb-name">模块管理</span>
			</div>
		</div>

		<div class="result-wrap">

			<div class="result-content">
				<table class="result-tab" width="100%">


					<tr>
						<th class="tc" style="min-width: 480px;">图片</th>
						<th>标题</th>
						<th>链接地址</th>
						<th>操作</th>

					</tr>
					<c:forEach items="${list}" var="p">
						<tr>
							<form action="./edit_model" method="post" enctype="multipart/form-data">
								<td><img src="${fileurl}${p.pic}" style="max-width: 200px;" /> 
								<%-- <input name="pic" value="${fileurl}${ad.pic}" type="hidden" />  --%>
								<input class="common-text required" name="modelimg" size="20" value="" type="file" /></td>
								<td><input class="common-text required" name="title" type="text" value="${p.title}" /></td>
								<td><input class="common-text required" name="url" type="text" value="${p.url}" /></td>
								<td><input name="id" value="${p.id}" type="hidden"> <input class="submit_link" type="submit" value="提交修改">
							</form>
							<a class="link-update" href="del_model?modelid=${p.id}">删除</a>
							</td>
						</tr>
					</c:forEach>

					<tr>
						<form action="./edit_model" method="post" enctype="multipart/form-data">
							<td><input style="margin-left: 200px;" class="common-text required" name="modelimg" size="20" value="" type="file" /></td>
							<td><input class="common-text required" name="title" type="text" value="" /></td>
							<td><input class="common-text required" name="url" type="text" value="" /></td>
							<td><input name="id" value="" type="hidden"> <input class="submit_link" type="submit" value="添加"></td>
						</form>

					</tr>

				</table>

			</div>
		</div>
	</div>

</body>
</html>