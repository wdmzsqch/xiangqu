<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>享趣后台管理</title>
<link rel="stylesheet" type="text/css" href="css/common.css" />
<link rel="stylesheet" type="text/css" href="css/main.css" />
<script type="text/javascript" src="js/libs/modernizr.min.js"></script>
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
	<jsp:include page="./sidebar.jsp"></jsp:include>
	<div class="main-wrap">
		<div class="crumb-wrap">
			<div class="crumb-list">
				<i class="icon-font">&#xe06b;</i><span>欢迎使用享趣后台管理。</span>
			</div>
		</div>
		<!-- <div class="result-wrap">
			<div class="result-title">
				<h1>快捷操作</h1>
			</div>
			<div class="result-content">
				<div class="short-wrap">
					<a href="#">新建任务</a> <a href="#">新建商品</a>
				</div>
			</div>
		</div> -->
		
		<input class="btn btn-primary btn2" name="creat_task" value="管理抽奖" type="button" onclick="javascript:location='./lottery_list'">
		<c:if test="${sessionScope.adminPrivilege == 1 }">
			<div class="result-wrap">
				<div class="result-title">
					<h1>平台基本数据</h1>
				</div>
				<div class="result-content">
					<form action="./home_page">
						<input value="${date}" name="date" id="date" style="width: 120px;" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
						&nbsp;
						<input class="btn btn-primary btn2" name="sub" value="搜索" type="submit">
					</form>
					<ul class="sys-info-list">
						<li><label class="res-lab">总积分（分享+大转盘）</label><span class="res-info">${all }</span></li>
						<li><label class="res-lab">总消耗（商城+大转盘）</label><span class="res-info">${allout }</span></li>
						<li><label class="res-lab">大转盘总积分</label><span class="res-info">${lottery }</span></li>
						<li><label class="res-lab">大转盘总消耗</label><span class="res-info">${lotteryout }</span></li>
						<li><label class="res-lab">500号的总积分</label><span class="res-info">${brushed }</span></li>
						<li>-----------------------------------------------------------------------------------<br /> 
						<label class="res-lab">用户总数</label><span class="res-info">${alluser }</span></li>
						<li><label class="res-lab">手机号用户总数</label><span class="res-info">${allregisteruser }</span></li>
					</ul>
				</div>
			</div>
		</c:if>
	</div>

</body>
</html>