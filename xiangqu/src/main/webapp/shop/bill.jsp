<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<style type="text/css">
</style>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, user-scalable=no">
<link rel="stylesheet" type="text/css" href="./css/common.css" />
<link rel="stylesheet" type="text/css" href="./css/bill.css" />
<script type="text/javascript" src="./js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	$(function() {
		setTabSelected(2);
	})
</script>
<title>账单明细</title>
</head>
<body
	style="margin: 0px; padding: 0px; border: 0px; background-color: #f6f6f6; font-family: '华文细黑'; font-size: 13px">

<div class="shop_bills_column_w">
  <div class="shop_bills_column">
    <div class="float_l shop_bills_column_l_xz" onclick="javascript:window.location.replace('./bill');">今日明细</div>
    <div class="float_l shop_bills_column_r_wxz" onclick="javascript:window.location.replace('./month_bill');">月总明细</div>
  </div>
</div>
	<div
		style="width: 100%; height: 55px; background-color: #fffff; display: inline; float: left;">
		<div
			style="width: 50%; height: 55px; float: left; background-color: #ffffff; overflow: hidden; line-height: 25px;">

			<span
				style="float: left; color: #333333; margin-left: 13px; width: 100%;">总点击量（次）</span><br>
			<span
				style="float: left; color: #333333; margin-left: 13px; width: 100%;">${totalCont }
<%-- 				￥${totalMoney } --%>
				</span>

		</div>
		<div
			style="width: 50%; height: 55px; float: right; background-color: #ffffff; overflow: hidden; line-height: 25px;">
			<div
				style="width: 1px; height: 55px; background: #e2e2e2; float: left;"></div>
			<span
				style="color: #333333; margin-left: 13px; margin-top: 10px; width: 100%;">剩余可投放量（条）</span><br>
			<span style="color: #333333; margin-left: 13px; width: 100%;">${RamainCount}
<%-- 			￥${LastMoney } --%>
			</span>
		</div>
	</div>

	<div
		style="width: 100%; height: 1px; background-color: #e2e2e2; float: left;"></div>


	<div
		style="width: 100%; height: 55px; background-color: #fffff; display: inline; float: left; line-height: 25px;">
		<div
			style="width: 50%; height: 55px; float: left; background-color: #ffffff; overflow: hidden;">

			<span style="color: #333333; margin-left: 13px; width: 100%;">今日任务数</span>
			<br/>
			<span style="color: #333333; margin-left: 13px; width: 100%;">${missionCount }</span>

		</div>
		<div
			style="width: 50%; height: 55px; float: right; background-color: #ffffff; overflow: hidden;">
			<div
				style="width: 1px; height: 55px; background: #e2e2e2; float: left;"></div>

			<span style="color: #333333; margin-left: 13px; width: 100%;">今日点击量</span>
			<br> <span
				style="color: #333333; margin-left: 13px; width: 100%;">${incomeRecordCount}</span>
		</div>
	</div>

	<div
		style="width: 100%; height: 1px; background-color: #e2e2e2; float: left;"></div>

	<div
		style="height: 30px; line-height: 30px; float: left; margin-left: 13px;">今日任务</div>

	<div
		style="width: 100%; height: 25px; background-color: #ffffff; float: left;line-height: 25px;"><span style=" margin-left: 20px;"><fmt:formatDate value="${time }" pattern="yyyy-MM-dd"/></span></div>
	<div
		style="width: 100%; height: 1px; background-color: #e2e2e2; float: left;"></div>




<c:forEach items="${missionList }" var="mission">
	<div
		style="background-color: #ffffff; width: 100%; height: 75px; float: left; display: inline; border-bottom: 1px solid #e2e2e2;">
		<img src="${fileRootUrl }${mission.pic }"
			style="width: 56px; height: 56px; margin-top: 9px; margin-left: 18px; float: left;">

		<div style="width: 70%; height: 75px; float: left;">
			<span
				style="color: #333333; float: left; margin-left: 10px; margin-top: 15px; width: 100%;"
				class="overflowOmit">${mission.name }</span>

			<div
				style="width: 70%; height: auto; float: left; margin-top: 6px; margin-left: 10px; display: inline;">

				<span style="color: #c1c1c1; float: left; width: auto;"
					class="overflowOmit">发放金额 : </span> <span
					style="color: #e95a5c; float: left; margin-left: 10px; width: auto;"
					class="overflowOmit"><fmt:formatNumber>${mission.totalMoney }</fmt:formatNumber></span>
			</div>
		</div>
	</div>
</c:forEach>
<div style="clear:both; height: 45px;"></div>
	<%@ include file="tab.jsp"%>

</body>
</html>