<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>后台管理</title>
<link rel="stylesheet" type="text/css" href="css/common.css" />
<link rel="stylesheet" type="text/css" href="css/main.css" />
<script src="./js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/libs/modernizr.min.js"></script>
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	function checkForm(){
		var name = $("input[name=name]").val();
		if(name == ""){
			alert("标题不能为空");
			return false;
		}
		var intro = $("textarea[name=intro]").val();
		if(intro == ""){
			alert("简介不能为空");
			return false;
		}
		var start = $("input[name=start]").val();
		if(start == ""){
			alert("开始时间不能为空");
			return false;
		}
		var lasting = $("input[name=lasting]").val();
		if(lasting == ""){
			alert("持续天数不能为空");
			return false;
		}
		var income = $("input[name=income]").val();
		if(income == ""){
			alert("收益不能为空");
			return false;
		}
		var contentUrl = $("input[name=contentUrl]").val();
		if(contentUrl == ""){
			alert("页面内容不能为空");
			return false;
		}
	}
</script>
</head>
<body>
	<jsp:include page="./sidebar.jsp"></jsp:include>
	<div class="main-wrap">

		<div class="crumb-wrap">
			<div class="crumb-list">
				<i class="icon-font"></i><a href="./home_page">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="./coupon_list">天天奖励管理</a><span class="crumb-step">&gt;</span>新建天天奖励
			</div>
		</div>
		<div class="result-wrap">
			<div class="result-content">
				<form action="./dailyreword_edit" method="post" enctype="multipart/form-data">
					<input type="hidden" value="${dailyreword.id }" name="id"/>
					<table class="insert-tab" width="100%">
						<tbody>
							<tr>
								<th width="120">标题：</th>
								<td><input class="common-text" name="name" size="50" value="${dailyreword.name}" type="text"></td>
							</tr>
							<tr>
								<th>开始时间：</th>
								<td><input class="common-text" name="start" size="50" value="<fmt:formatDate value="${dailyreword.startTime}" type="date" pattern="yyyy-MM-dd"/>" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
								</td>
							</tr>
							<tr>
								<th height="auto;">持续天数：</th>
								<td><input class="common-text" name="lasting" size="50" value="${dailyreword.lasting}" type="number"></td>
							</tr>
							<tr>
								<th height="auto;">收益：</th>
								<td><input class="common-text" name="income" size="50" value="${dailyreword.income}" type="number"></td>
							</tr>
							<tr>
								<th height="auto;">页面内容：</th>
								<td><input class="common-text" name="contentUrl" size="50" value="${dailyreword.contentUrl}"></td>
							</tr>
							<tr>
								<th height="auto;">介绍：</th>
								<td><textarea name="intro"style="width: 80%; height: 60px;">${dailyreword.intro}</textarea></td>
							</tr>
							<tr>
								<th></th>
								<td><input class="btn btn-primary btn6 mr10" value="提交" type="submit" onclick="return checkForm()"> 
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>

	</div>

</body>
</html>