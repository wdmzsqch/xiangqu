<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no">
<title>水票验证</title>
<link rel="stylesheet" type="text/css" href="css/common.css" />
<link rel="stylesheet" type="text/css" href="css/main.css" />
<script src="./js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
function checkForm(){
	var ticketNum = $("input[name=ticketNum]").val();
	if(ticketNum == ""){
		alert("请输入串号");
		return;
	}
	$("#pageForm").submit();
}
</script>
</head>
<body>
	<div class="topbar-wrap white">
	<div class="topbar-inner clearfix">
		<div class="topbar-logo-wrap clearfix">
			<h1 class="topbar-logo">
				<a href="#" class="navbar-brand">水票验证</a>
			</h1>
		</div>
		<div class="top-info-wrap">
			<ul class="top-info-list clearfix">
				<li></li>
			</ul>
		</div>
	</div>
</div>
	<div class="main-wrap" style="margin-left:0px;">
		<div class="crumb-wrap">
			<div class="crumb-list">
				<i class="icon-font"></i>
			</div>
		</div>
		<div class="result-wrap">
			<div class="result-content">
				<form action="./check_ticket" id="pageForm" method="post" enctype="multipart/form-data">
					<table class="insert-tab" width="100%">
						<tbody>
							<tr>
								<th><i class="require-red">*</i>水票串号：</th>
								<td><input class="common-text" name="ticketNum" value="${ticketNum}" type="text"></td>
							</tr>
							<tr>
								<th></th>
								<td><input class="btn btn-primary btn6 mr10" value="提交" type="button" onclick="return checkForm()"> 
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>

	</div>

</body>
</html>