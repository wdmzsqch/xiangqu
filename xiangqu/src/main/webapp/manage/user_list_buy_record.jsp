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
<link rel="stylesheet" type="text/css" href="css/page.css" />
<script src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/libs/modernizr.min.js"></script>
<script src="js/page.js"></script>
</head>
<body>
	<jsp:include page="./sidebar.jsp"></jsp:include>
	<div class="main-wrap">
		<div class="crumb-wrap">
			<div class="crumb-list">
				<i class="icon-font"></i><a href="./home_page">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">用户列表管理</span><span class="crumb-step">&gt;</span><span
					class="crumb-name">兑换/购买记录</span>
			</div>
		</div>
		<form action="./user_record" method="post" id="pageForm" name="pageForm">
		<div class="result-wrap">
				<div class="result-content">
					<table class="result-tab" width="100%">
						<tr>
							<th class="tc" width="10%">时间</th>
							<th>详情</th>
							<th>积分变更</th>
						</tr>
						<c:forEach items="${recordlist }" var = "record">
						<tr>
							<td class="tc"><fmt:formatDate value="${record.orderTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td>购买了${record.goodsList[0].name }</td>
							<td>${record.totalPrice }</td>
						</tr>
						</c:forEach>
					</table>
					
					<br />
					<c:if test="${pageCount>1}">
						<input id="pageCount" value="${pageCount}" type="hidden">
						<input id="userId" value="${userId}" name="userId" type="hidden">
						<input id="pageIndex" name="pageIndex" value="${pageIndex}" type="hidden">
						<div class="pagecontainer middle">
							<div class="pagination">
								<ul class="pageulli">
									<li class="pageulli"><a onclick="pageprev()"></a></li>
									<c:forEach begin="1" end="${pageCount}" var="item">
										<li class="pageulli  <c:if test="${pageIndex==item}">active</c:if>"><a onclick="pageto(${item})"></a></li>
									</c:forEach>
									<li class="pageulli"><a onclick="pagenext()"></a></li>
								</ul>
							</div>
						</div>
					</c:if>
				</div>
		</div>
	</form>
	</div>
</body>
</html>