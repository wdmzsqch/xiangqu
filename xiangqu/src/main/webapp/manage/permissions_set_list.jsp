<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>后台管理</title>
<link rel="stylesheet" type="text/css" href="css/common.css" />
<link rel="stylesheet" type="text/css" href="css/main.css" />
<script src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/libs/modernizr.min.js"></script>
<script type="text/javascript">
	function editorperssions(id){
		window.location.href = "./edi_perssions?id="+id;
	}
	
	function del_perssions(id){
		if(confirm("是否删除")){
			window.location.href = "./del_perssion?id="+id;
		}
	}
</script>
</head>
<body>
	<jsp:include page="./sidebar.jsp"></jsp:include>
	<div class="main-wrap">

		<div class="crumb-wrap">
			<div class="crumb-list">
				<i class="icon-font"></i><a href="./home_page">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">权限组列表</span>
			</div>
		</div>
		<div class="search-wrap">
			<div class="search-content"></div>
		</div>
		<div class="result-wrap">

			<input class="btn btn-primary btn2" type="button" name="account_insert" value="创建权限组" onclick="editorperssions(0)"> <br />
			
			<div class="result-content" style="margin-top: 10px;">
				<table class="result-tab" width="100%">
					<tr>
						<th width="50%" align="center" class="tc">权限组名称</th>
						<th align="center" class="tc">操作</th>
					</tr>
					<c:forEach items="${rolelist}" var="role">
						<tr>
							<td align="center" class="tc">${role.roleName }</td>
							<td align="center"><a class="link-update" href="./edi_perssions?id=${role.id }">修改</a> 
							<a class="link-update" onclick="del_perssions(${role.id })" style="cursor: pointer;">删除</a></td>
						</tr>
					</c:forEach>

				</table>
			</div>

		</div>
	</div>
	
</body>
</html>