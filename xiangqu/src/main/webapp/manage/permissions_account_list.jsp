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
<script src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/libs/modernizr.min.js"></script>
<script type="text/javascript">
	function editoradmin(id){
		window.location.href = "./edit_admin?id="+id;
	}
	
	function del_admin(id){
		if(confirm("是否删除")){
			window.location.href = "./del_admin?id="+id;
		}
	}
	
	function recharge(id){
		window.location.href = "./recharge?id="+id;
	}
</script>
</head>
<body>
	<jsp:include page="./sidebar.jsp"></jsp:include>
	<div class="main-wrap">
		<div class="crumb-wrap">
			<div class="crumb-list">
				<i class="icon-font"></i><a href=./home_page>首页</a><span class="crumb-step">&gt;</span>权限账号列表
			</div>
		</div>

		<div class="result-wrap">
			<form name="myform" id="myform" method="post" >
				<div class="result-title">
					<div class="result-list">
						<input class="btn btn-primary btn2" type="button" name="account_insert" value="创建账号" onclick="editoradmin(0)">
					</div>
				</div>
				<div class="result-content">
					<table class="result-tab" width="100%">
						<tr>
							<th class="tc" width="10%">用户名</th>
							<th>姓名</th>
							<th>所属权限</th>
							<th>手机号码</th>
<%-- 							<c:if test="${sessionScope.adminPrivilege == 9}"> --%>
							<th>可用条数</th>
							<th>已用条数</th>
<%-- 							</c:if> --%>
							<th>最近登录</th>
							<th>操作</th>
						</tr>

						<c:forEach items="${adminlist}" var="ad" varStatus="status">
							<tr>
								<td class="tc">${ad.userName}</td>
								<td>${ad.realName}</td>
								<td>${ad.roleName}</td>
								<td>${ad.mobile}</td>
<%-- 								<c:if test="${sessionScope.adminPrivilege == 9}"> --%>
								<td>${ad.leftintegral}</td>
								<td>${ad.integral-ad.leftintegral}</td>
<%-- 								</c:if> --%>
								<td><fmt:formatDate value="${ad.createTime}" type="both" /></td>
								<td><a class="link-update" href="./edit_admin?id=${ad.id}">修改</a> 
									<c:if test="${not status.first }">
										<a class="link-del" onclick="del_admin(${ad.id})" style="cursor: pointer;">删除</a>
									</c:if>
									<c:if test="${ad.type == 6 }">
										<a class="link-del" onclick="recharge(${ad.id})" style="cursor: pointer;">充值</a>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</form>
		</div>
	</div>
</body>
</html>