<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>后台管理</title>
<link rel="stylesheet" type="text/css" href="css/common.css" />
<link rel="stylesheet" type="text/css" href="css/main.css" />
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
	<div class="main-wrap" style="margin-left: 0px;">

		<div class="crumb-wrap">
			<div class="crumb-list">
				<i class="icon-font"></i>
				<a href="./home_page">首页</a>
				<span class="crumb-step">&gt;</span><span class="crumb-name">任务管理</span>
			</div>
		</div>
		<form action="./invite_count" method="post">
			<div class="search-wrap">
				<div class="search-content">

					<table class="search-tab">
						<tr>
							<th width="120">任务搜索:</th>
							<td>开始时间<input type="text" name="start" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" /></td>
							<td>结束时间<input type="text" name="end" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" /></td>
							<td><input class="btn btn-primary btn2" value="搜索" type="submit"></td>

						</tr>
					</table>
				</div>
			</div>
		</form>
		<div class="result-wrap">

			<div class="result-content">
				<table class="result-tab" width="100%">
					<tr>
						<th>排名</th>
						<th>用户名</th>
						<th>昵称</th>
						<th>真实姓名</th>
						<th>手机号</th>
						<th>邀请数</th>
					</tr>

					<c:forEach items="${users}" var="user" varStatus="st">
						<tr>
							<td>${st.count}</td>
							<td>${user.userName}</td>
							<td>${user.nickName}</td>
							<td>${user.realName}</td>
							<td>${user.moblie}</td>
							<td>${user.icount}</td>
						</tr>
					</c:forEach>

				</table>


			</div>

		</div>


	</div>



</body>

</html>