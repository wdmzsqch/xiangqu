<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>后台管理</title>
<link rel="stylesheet" type="text/css" href="css/common.css" />
<link rel="stylesheet" type="text/css" href="css/main.css" />
<script src="./js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/libs/modernizr.min.js"></script>
</head>
<body>
	<jsp:include page="./sidebar.jsp"></jsp:include>
	<div class="main-wrap">

		<div class="crumb-wrap">
			<div class="crumb-list">
				<i class="icon-font"></i><a href="./home_page">首页</a><span class="crumb-step">&gt;</span>城市列表
			</div>
		</div>
		<div class="search-wrap">
			<div class="search-content">
				<form action="./city_list" method="post">
					<table class="search-tab">
						<th width="70">省份筛选:</th>
						<td><select name="id">
								<c:forEach items="${selectprolist }" var = "sepro">
									<c:if test="${proid == sepro.id }">
										<option value="${sepro.id }" selected="selected">${sepro.name }</option>
									</c:if>
									<c:if test="${proid != sepro.id }">
										<option value="${sepro.id }">${sepro.name }</option>
									</c:if>
								</c:forEach>
						</select></td>
						<td><input class="btn btn-primary btn2" name="sub" value="确定" type="submit"></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="result-wrap">
			<form name="myform" id="myform" method="post">
				<div class="result-content">

					<table width="100%" border="1">
						<tr>
							<th align="center" style="background-color: #CCC;">省份</th>
							<td align="center" style="background-color: #CCC;">城市</td>
							<td align="center" style="background-color: #CCC;">是否开通</td>
							<td align="center" style="background-color: #CCC;">操作</td>
						</tr>
						<tr >
							<td rowspan="${provice.citysize }" align="center">${provice.name }</td>
						</tr>
						<c:forEach items="${provice.citylist }" var = "city">
							<tr>
								<td align="center">${city.name }</td>
								<td align="center">是</td>
								<td align="center"><a class="link-update" href="#">开通</a> <a class="link-del" href="#">关闭</a></td>
							</tr>
						</c:forEach>
					</table>
					
				</div>
			</form>
		</div>
	</div>

</body>
</html>