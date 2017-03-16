<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>后台管理</title>
<link rel="stylesheet" type="text/css" href="css/common.css" />
<link rel="stylesheet" type="text/css" href="css/main.css" />
<link rel="stylesheet" type="text/css" href="css/page.css" />

<style type="text/css">
</style>
<script type="text/javascript" src="js/libs/modernizr.min.js"></script>
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
<script src="./js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	function checkvalue(){
		var rechargeMoney = $("input[name=rechargeMoney]").val();
		if(rechargeMoney == ""){
			alert("条数不能为空");
			return;
		}
		if(parseInt(rechargeMoney) < 0){
			alert("条数不能小于0");
			return;
		}
		$("#rechargeForm").submit();
	}
</script>
</head>
<body>
	<jsp:include page="./sidebar.jsp"></jsp:include>
	<div class="main-wrap">

		<div class="crumb-wrap">
			<div class="crumb-list">
				<i class="icon-font"></i><a href="./home_page">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">充值管理</span>
			</div>
		</div>

		<div class="result-wrap">

			<div class="result-content">
				<table class="result-tab" width="100%">
				<form id="rechargeForm" method="post" action="./editorRecharge">
				<input type="hidden" value="${toRechargeId }" name="toRechargeId"/>
				<input type="hidden" value="${fromRechargeId }" name="fromRechargeId"/>
				<input type="hidden" value="${recharge.id }" name="id"/>
						<tr>
							<th width="120"><i class="require-red">*</i>条数增加：</th>
							<td>
							<c:if test="${empty recharge}">
							<input class="common-text" name="rechargeMoney" size="50" value="${recharge.rechargeMoney}" type="number">
							</c:if>
							<c:if test="${not empty recharge}">
							<input class="common-text" name="rechargeMoney" size="50" readonly="readonly" value="${recharge.rechargeMoney}" type="number">
							</c:if>
							</td>
						</tr>
						<c:if test="${not empty recharge}">
						<tr>
							<td>
								<c:if test="${recharge.checkStatusC == 1 }">财务已审核</c:if>
								<c:if test="${recharge.checkStatusC == 0 }">财务未审核</c:if>
							</td>
							<td>
								<c:if test="${recharge.checkStatusB == 1 }">最终已审核</c:if>
								<c:if test="${recharge.checkStatusB == 0 }">最终未审核</c:if>
							</td>
						</tr>
						</c:if>
						<c:if test="${empty recharge}">
						<tr>
							<th></th>
							<td><input class="btn btn-primary btn6 mr10" value="提交" onclick="checkvalue()" type="button"></td>
						</tr>
						</c:if>
				</form>
				</table>
			</div>
		</div>
	</div>

</body>
</html>