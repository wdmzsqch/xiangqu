<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>后台管理</title>
<link rel="stylesheet" type="text/css" href="css/common.css" />
<link rel="stylesheet" type="text/css" href="css/main.css" />
<script type="text/javascript" src="js/libs/modernizr.min.js"></script>
<script src="./js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
</script>
</head>
<body>
	<jsp:include page="./sidebar.jsp"></jsp:include>
	<div class="main-wrap">

		<div class="crumb-wrap">
			<div class="crumb-list">
				<i class="icon-font"></i> <a href="./home_page">首页</a> <span class="crumb-step">&gt;</span> <a class="crumb-name" href="./industry_list">行业管理</a> <span
					class="crumb-step">&gt;</span><span>编辑行业</span>
			</div>
		</div>
		<div class="result-wrap">
			<div class="result-content">
				<form action="./industry_edit" method="post" id="myform" name="myform" enctype="multipart/form-data">
					<input id="id" name="id" value="${industry.id}" type="hidden">
					<table class="insert-tab" width="100%">
						<tbody>
							<tr>
								<th width="120"><i class="require-red">*</i>行业名称：</th>
								<td><input class="common-text" name="name" size="50" value="${industry.name}" type="text"></td>
							</tr>
							<tr>
								<th></th>
								<td><input class="btn btn-primary btn6 mr10" value="提交" type="submit"></td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>

	</div>

</body>
</html>