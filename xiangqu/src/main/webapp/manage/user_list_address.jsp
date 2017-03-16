<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<script type="text/javascript">
function pageto(pindex) {
	$('#pageIndex').val(pindex);
	$('#myform').submit();
}

function pageprev() {
	var page = Number($('#pageIndex').val()) - 1;
	if (page > 0) {
		$('#pageIndex').val(page);
		$('#myform').submit();
	}
}

function pagenext() {
	var page = Number($('#pageIndex').val()) + 1;
	if (page <= Number($('#pageCount').val())) {
		$('#pageIndex').val(page);
		$('#myform').submit();
	}
}
</script>
</head>
<body>
	<jsp:include page="./sidebar.jsp"></jsp:include>
	<div class="main-wrap">

		<div class="crumb-wrap">
			<div class="crumb-list">
				<i class="icon-font"></i><a href="./home_page">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">用户列表管理</span><span class="crumb-step">&gt;</span><span
					class="crumb-name">收件地址</span>
			</div>
		</div>
		<div class="result-wrap">
			<form name="myform" id="myform" method="post">
				<div class="result-content">
					<table class="result-tab" width="100%">
						<tr>
							<th class="tc" width="10%">收件人姓名</th>
							<th>收件人手机</th>
							<th>收件地址</th>
						</tr>
						<c:forEach items="${addresslist }" var="address">
						<tr>
							<td class="tc">${address.consignee }</td>
							<td>${address.phone }</td>
							<td>${address.proname }${address.cityname }${address.areaname }${address.address }</td>
						</tr>
						</c:forEach>
					</table>
					
					<br />
					<c:if test="${pageCount>1}">
						<input id="pageCount" value="${pageCount}" type="hidden">
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
			</form>
		</div>
	</div>

</body>
</html>