<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, user-scalable=no">
<title>我收益</title>
<link rel="stylesheet" type="text/css" href="css/user.css">
<link rel="stylesheet" type="text/css" href="css/common.css">
<script type="text/javascript" src="./js/jquery-1.11.3.min.js"></script>
<script src="js/jquery.lazyload.js?v=1.9.1"></script>
<script type="text/javascript">
	
</script>
</head>

<body>
	<div
		style="background: #fff; width: 100%; height: auto; margin-top: 8px;">
		<c:forEach items="${myRecord }" var="p">
			<div
				style="width: 92%; height: auto; margin-left: 4%; clear: both; border-bottom: 1px solid #e8e8e8; padding-bottom: 5px;">
				<div style="width: 11%; height: 23px; float: left; margin-top: 5px;">
					<img
						src="<c:if test='${p.type == 1}'>imgs/task_icon_task.png</c:if>
				<c:if test='${p.type == 2}'>imgs/task_icon_goods.png</c:if>
				<c:if test='${p.type == 3}'>imgs/task_icon_buy.png</c:if>
				<c:if test='${p.type == 4}'>imgs/task_icon_share.png</c:if>
				<c:if test='${p.type == 5}'>imgs/task_icon_use.png</c:if>
				<c:if test='${p.type == 6}'>imgs/task_icon_apply.png</c:if>
				<c:if test='${p.type == 7}'>imgs/task_icon_lucky.png</c:if>
				<c:if test='${p.type == 8}'>imgs/task_icon_invitation.png</c:if>
				<c:if test='${p.type == 9}'>imgs/task_icon_lucky.png</c:if>
				<c:if test='${p.type == 10}'>imgs/task_icon_task.png</c:if>
				<c:if test='${p.type == 11}'>imgs/task_icon_task.png</c:if>
				<c:if test='${p.type == 12}'>imgs/task_icon_task.png</c:if>
				<c:if test='${p.type == 13}'>imgs/task_icon_task.png</c:if>
				<c:if test='${p.type == 15}'>imgs/task_icon_task.png</c:if>
				<c:if test='${p.type == 16}'>imgs/task_icon_task.png</c:if>"
						style="width: 23px; height: 23px; border-radius: 10px;">
				</div>
				<div
					style="width: 88%; hegiht: auto; overflow: hidden; font-size: 12px; word-break: break-all; margin-top: 10px;">
					<div style="width: 75%; float: left; height: auto;">
						<c:if test="${p.type == 1}">
							<font color="333">分享任务被点击，获得<fmt:formatNumber
									type="number" value="${p.income }" maxFractionDigits="0" />个积分
							</font>
						</c:if>
						<c:if test="${p.type == 2}">
							<font color="333">分享商品被点击，获得<fmt:formatNumber
									type="number" value="${p.income }" maxFractionDigits="0" />个积分
							</font>
						</c:if>
						<c:if test="${p.type == 3}">
							<font color="333"><c:if test="${p.goodstype == 2}">成功兑换了</c:if>
								<c:if test="${p.goodstype == 1}">成功购买了</c:if>${p.relativename }</font>
						</c:if>
						<c:if test="${p.type == 4}">
							<font color="333">分享优惠券被点击，获得<fmt:formatNumber
									type="number" value="${p.income }" maxFractionDigits="0" />个积分
							</font>
						</c:if>
						<c:if test="${p.type == 5}">
							<font color="333">分享优惠券被使用，获得<fmt:formatNumber
									type="number" value="${p.income }" maxFractionDigits="0" />个积分
							</font>
						</c:if>
						<c:if test="${p.type == 6}">
							<font color="333">分享活动被报名，获得<fmt:formatNumber
									type="number" value="${p.income }" maxFractionDigits="0" />个积分
							</font>
						</c:if>
						<c:if test="${p.type == 7}">
							<font color="333">参与每日抽奖，抽到了<c:if test="${p.income == 10}">安慰奖</c:if>
								<c:if test="${p.income == 50}">三等奖</c:if> <c:if
									test="${p.income == 100}">二等奖</c:if> <c:if
									test="${p.income == 1000}">一等奖</c:if> <c:if
									test="${p.income == 5000}"> 特等奖</c:if>
								<fmt:formatNumber type="number" value="${p.income }"
									maxFractionDigits="0" />个积分
							</font>
						</c:if>
						<c:if test="${p.type == 8}">
							<font color="333">${p.relativename}接受了您的邀请，获得<fmt:formatNumber
									type="number" value="${p.income }" maxFractionDigits="0" />个积分
							</font>
						</c:if>
						<c:if test="${p.type == 9}">
							<font color="333">参与每日抽奖使用，使用<fmt:formatNumber
									type="number" value="${p.income }" maxFractionDigits="0" />个积分
							</font>
						</c:if>
						<c:if test="${p.type == 10}">
							<font color="333">接受了的${p.relativename}邀请，获得<fmt:formatNumber
									type="number" value="${p.income }" maxFractionDigits="0" />个积分
							</font>
						</c:if>
						<c:if test="${p.type == 11}">
							<font color="333">${p.relativename}任务收益${p.relativeId}，奖励您<fmt:formatNumber
									type="number" value="${p.income }" maxFractionDigits="0" />个积分
							</font>
						</c:if>
						<c:if test="${p.type == 12}">
							<font color="333">${p.relativename}大转盘获得${p.relativeId}，奖励您<fmt:formatNumber
									type="number" value="${p.income }" maxFractionDigits="0" />个积分
							</font>
						</c:if>
						<c:if test="${p.type == 13}">
							<font color="333">${p.relativename}其他收益${p.relativeId}，奖励您<fmt:formatNumber
									type="number" value="${p.income }" maxFractionDigits="0" />个积分
							</font>
						</c:if>
						<c:if test="${p.type == 15}">
							<font color="333">参与每日签到，奖励您<fmt:formatNumber
									type="number" value="${p.income }" maxFractionDigits="0" />个积分
							</font>
						</c:if>
						<c:if test="${p.type == 16}">
							<font color="333">连续签到7天，奖励您<fmt:formatNumber
									type="number" value="${p.income }" maxFractionDigits="0" />个积分
							</font>
						</c:if>
						<br> <font color="333"><fmt:formatDate
								value="${p.time }" pattern="yyyy-MM-dd HH:mm" /></font>
					</div>
					<div
						style="width: 25%; float: left; height: 20px; text-align: right; overflow: hidden; word-break: break-all; margin-top: 5px;">
						<c:if test="${p.income > 0 }">
							<font color="00d1bc">+<fmt:formatNumber type="number"
									value="${p.income }" maxFractionDigits="0" />积分
							</font>
						</c:if>
						<c:if test="${p.income == 0 }">
							<font color="00d1bc"><fmt:formatNumber type="number"
									value="${p.income }" maxFractionDigits="0" />积分</font>
						</c:if>
						<c:if test="${p.income < 0 }">
							<font color=ff5c4b><fmt:formatNumber type="number"
									value="${p.income }" maxFractionDigits="0" />积分</font>
						</c:if>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</body>
</html>
