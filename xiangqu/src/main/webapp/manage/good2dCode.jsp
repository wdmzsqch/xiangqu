<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>后台管理</title>
<link rel="stylesheet" type="text/css" href="css/common.css" />
<link rel="stylesheet" type="text/css" href="css/main.css" />
<link rel="stylesheet" type="text/css" href="css/page.css" />
<script type="text/javascript" src="js/libs/modernizr.min.js"></script>
<script src="./js/jquery-1.11.3.min.js"></script>
<script src="./js/page.js"></script>
<script type="text/javascript">
</script>
</head>
<body>


	<jsp:include page="./sidebar.jsp"></jsp:include>

	<div class="main-wrap">
		<form name="pageForm" id="pageForm" method="post" action="./goods_list">
			<div class="crumb-wrap">
				<div class="crumb-list">
					<i class="icon-font"></i><a href="./home_page">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">商品管理</span>
				</div>
			</div>
			<div class="search-wrap">
				<div class="search-content">
				</div>
			</div>
			<div class="result-wrap">
				<div class="result-content">
					<table class="result-tab" width="100%">
						<tr>
							<th>二维码</th>
							<th>渠道名</th>
							<th>渠道码</th>
							<th>销售量</th>
							<th>昨日销售量</th>
							<th>访问量</th>
							<th>昨日访问量</th>
						</tr>
						<c:forEach items="${list}" var="p" varStatus="st">
							<tr>
								<td><img src="./getImage?good_id=${good_id }&channel_id=${p.id}" width="100" height="100"></td>
								<td>${p.channelName}</td>
								<td>${p.channelCode}</td>
								<td>${p.sellcount}</td>
								<td>${p.yestoday_sellcount}</td>
								<td>${p.lookcount}</td>
								<td>${p.yestody_lookcount}</td>
							</tr>
						</c:forEach>
					</table>
				</div>

			</div>

		</form>
	</div>
	<!--/main-->
	<div class="mask" style="display: none;" onclick="hide()"></div>
	<div class="alert" style="position: fixed; display: none; width: 400px; height: 120px; overflow: hidden; background: #fff; z-index: 101; padding: 20px;"></div>
</body>
</html>