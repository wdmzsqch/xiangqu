<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>后台管理</title>
<link rel="stylesheet" type="text/css" href="css/common.css" />
<link rel="stylesheet" type="text/css" href="css/main.css" />
<script type="text/javascript" src="js/libs/modernizr.min.js"></script>
</head>
<body>

	<jsp:include page="./sidebar.jsp"></jsp:include>
	<div class="main-wrap">
		<div class="crumb-wrap">
			<div class="crumb-list">
				<i class="icon-font"></i><a href="./home_page">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">订单管理</span><span class="crumb-step">&gt;</span><a
					href="/order_detail.html">订单详情</a>
			</div>
		</div>
		<div class="search-wrap">
			<div class="search-content">
				<form action="/jscss/admin/design/index" method="post">
					<table width="100%;" border="1">
						<tr>
							<th colspan="4" scope="col" style="background-color: #CCC;">基本信息</th>
						</tr>
						<tr>
							<td width="8%" align="center">订单编号：</td>
							<td width="42%">${order.orderNo}</td>
							<td width="8%" align="center">订单状态：</td>
							<td width="42%"><c:if test="${order.state==1}">待付款</c:if> <c:if test="${order.state==2}">待发货</c:if> <c:if test="${order.state==3}">已发货</c:if> <c:if
									test="${order.state==4}">已完成</c:if></td>
						</tr>
						<tr>
							<td align="center">下单会员：</td>
							<td>${order.userName}</td>
							<td align="center">下单时间：</td>
							<td><fmt:formatDate value="${order.orderTime}" type="both" /></td>
						</tr>
						<tr>
							<td align="center">订单总价：</td>
							<td>${order.totalPrice}元</td>
							<td align="center">付款时间：</td>
							<td><fmt:formatDate value="${order.payTime}" type="both" /></td>
						</tr>
						<tr>
							<td align="center">支付方式：</td>
							<td>${order.goodsList[0].price}支付宝</td>
							<td align="center">
								<!-- 交易号： -->&nbsp;
							</td>
							<td>&nbsp;</td>
						</tr>
					</table>

					<table width="100%" border="1" style="margin-top: 10PX;">
						<tr>
							<th colspan="4" scope="col" style="background-color: #CCC;">商品信息</th>
						</tr>
						<tr>
							<th width="25%">商品名称</th>
							<th>单价</th>
							<th>数量</th>
							<th>总价</th>
						</tr>
						<c:forEach items="${order.goodsList}" var="goods">
							<tr>
								<td align="center">${goods.name}</td>
								<td align="center">${goods.price}</td>
								<td align="center">${goods.num}</td>
								<td align="center">${goods.price*goods.num}</td>
							</tr>
						</c:forEach>
					</table>



					<table width="100%" border="1" style="margin-top: 10PX;">
						<tr>
							<th colspan="4" scope="col" style="background-color: #CCC;">配送信息</th>
						</tr>
						<tr>
							<td width="10%" align="center">配送方式：</td>
							<td width="40%">${order.express}</td>
							<td width="10%" align="center">快递单号：</td>
							<td width="40%">${order.courierNum}</td>
						</tr>
						<tr>
							<td align="center">收货地址：</td>
							<td>${order.address}</td>
							<td align="center">订单备注：</td>
							<td>${order.comment}</td>
							<!-- <td align="center">运费：</td><td>0元</td> -->
						</tr>

						<tr>
							<td align="center">收货人姓名：</td>
							<td>${order.consignee}</td>
							<td align="center">手机号码：</td>
							<td>${order.phone}</td>
						</tr>
						<tr>
							<!-- <td align="center">商家备注：</td><td>&nbsp;</td> -->
						</tr>
					</table>
					<table align="center">
						<tr>
							<td width="100%;"><br /> <br /> <input class="btn btn-primary btn6 mr10" value="返回" type="button" onclick="location.href='order_list'"></td>
						</tr>
					</table>


				</form>
			</div>
		</div>
	</div>
</body>
</html>