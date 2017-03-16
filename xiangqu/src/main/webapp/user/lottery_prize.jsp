<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, user-scalable=no">
<link rel="stylesheet" type="text/css" href="./css/common.css" />
<script type="text/javascript" src="./js/jquery-1.11.3.min.js"></script>
<title>中奖记录</title>
<style type="text/css">
body{
	font-family: "微软雅黑";
	font-size: 14px;
}
.text-overflow {
	display: block; /*内联对象需加*/
	word-break: keep-all; /* 不换行 */
	white-space: nowrap; /* 不换行 */
	overflow: hidden; /* 内容超出宽度时隐藏超出部分的内容 */
	text-overflow: ellipsis;
	/* 当对象内文本溢出时显示省略标记(...);需与overflow:hidden;一起使用。*/
	float: left; 
	margin-left: 5%;
	width: 80%; 
}

.line {
	width: 100%;
	height:1px;
	border-bottom: 1px solid #ccc;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	if('${state}' == 1){
		$("#recordspan").addClass("select");
		$("#lotteryDiv").show();
		$("#prizeDiv").hide();
	}else{
		$("#missionspan").addClass("select");
		$("#lotteryDiv").hide();
		$("#prizeDiv").show();
	}
});
function getPrize(lotteryid){
	window.location.replace("./tocouponlottery?lotteryid="+lotteryid);
}

function lottery(state){
	window.location.replace("./mylottery?state="+state);
}

function prize(){
	window.location.href = "./myprize";
}
</script>
<style type="text/css">
	.notselect{
		float: left; height: 100%; font-family: '微软雅黑';color: #d0d0d0;
	}
	
	.select{
		border-bottom: 1px solid #00d1bc; color: #00d1bc;
	}
	

</style>
</head>
<body style="margin: 0px; padding: 0px; border: 0px; background-color: #f6f6f6; font-family: '华文细黑'">
<div style="width: 92%; height: 38px; border-bottom: 1px solid #e8e8e8; margin: 0 auto; line-height: 38px; font-size: 12px;">
	<div style="width: 120px; height: 100%; margin:0 auto; cursor: pointer;">
	<div class="notselect" id="recordspan" onclick="lottery(1)">抽奖机会</div>
	<div class="notselect" style="margin-left: 4px;" id="missionspan" onclick="lottery(2)">中奖记录</div>
	</div>
</div>
<div id="lotteryDiv">
	<c:forEach items="${lotterylist}" var="p">
		<div style="width: 100%; height: auto; background-color: #fff; border-bottom: 1px solid #ccc;" <c:if test="${p.state == 0 }">onclick="getPrize(${p.id })"</c:if>>
			<div style="float: right; font-size: 14px; width: 50px; height: 40px; line-height: 40px;font-family: '微软雅黑'; text-align: center;"><c:if test="${p.state == 0 }">未抽奖</c:if><c:if test="${p.state == 1 }">已抽奖</c:if> </div>
			<div style="margin-right: 50px; padding: 5px 10px;font-family: '微软雅黑';">您在<fmt:formatDate value="${p.addtime}" pattern="yyyy-MM-dd HH:mm" />使用<font color="#FF0033">${p.couponinfo.title }</font> 优惠券一张，获得一次抽奖机会</div>
			<div style="clear:both;"></div>
		</div>
		
	</c:forEach>
</div>
<div id="prizeDiv" style="display: none;">
	<c:forEach items="${prizelist}" var="p">
		<div style="width: 100%; height: auto; background-color: #fff; border-bottom: 1px solid #ccc;">
			<div style="float: right; font-size: 14px; width: 50px; height: 40px; line-height: 40px; text-align: center; font-family: '微软雅黑';"><c:if test="${p.state == 2 }">未兑换</c:if><c:if test="${p.state == 1 }">已兑换</c:if> </div>
			<div style="margin-right: 50px; padding: 5px 10px;font-family: '微软雅黑';">恭喜您在<fmt:formatDate value="${p.addtime}" pattern="yyyy-MM-dd HH:mm" />抽到了<font color="#FF0033">${p.prize }</font></div>
			<div style="clear:both;"></div>
		</div>
		
	</c:forEach>
</div>
<%@ include file="cs.jsp" %>
<%CS cs = new CS(1256812462);cs.setHttpServlet(request,response);
String imgurl = cs.trackPageView();%> 
<img src="<%= imgurl %>" width="0" height="0"  />
</body>

</html>