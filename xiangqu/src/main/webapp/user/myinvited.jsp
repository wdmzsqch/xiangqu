<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, user-scalable=no">
<title>我邀请</title>
<link rel="stylesheet" type="text/css" href="css/user.css">
<link rel="stylesheet" type="text/css" href="css/common.css">
<script type="text/javascript" src="./js/jquery-1.11.3.min.js"></script>
<script src="js/mobiscroll_002.js" type="text/javascript"></script>
<script src="js/mobiscroll_004.js" type="text/javascript"></script>
<link href="css/mobiscroll_002.css" rel="stylesheet" type="text/css">
<link href="css/mobiscroll.css" rel="stylesheet" type="text/css">
<script src="js/mobiscroll.js" type="text/javascript"></script>
<script src="js/mobiscroll_003.js" type="text/javascript"></script>
<script src="js/mobiscroll_005.js" type="text/javascript"></script>
<link href="css/mobiscroll_003.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
$(function () {
	var currYear = (new Date()).getFullYear();	
	var opt={};
	opt.date = {preset : 'date'};
	opt.default = {
		theme: 'android-ics light', //皮肤样式
        display: 'modal', //显示方式 
        mode: 'scroller', //日期选择模式
		dateFormat: 'yyyy-mm-dd',
		lang: 'zh',
		showNow: true,
		nowText: "今天",
        startYear: currYear - 20, //开始年份
        endYear: currYear + 20 //结束年份
	};
	$("#appDate").val('').scroller('destroy').scroller($.extend(opt['date'], opt['default']));
	$("#appDate2").val('').scroller('destroy').scroller($.extend(opt['date'], opt['default']));
  });


	function getinvitedcode(){
		window.location.href = "./getinvitedcode";
	}
	
	function tosearch(){
		$("#searchForm").submit();
	}
</script>
<style type="text/css">
#appDate, #appDate2{
	border: 1px solid #e8e8e8;
	font-size: 12px;
	height: 24px;
}
</style>
</head>

<body>
	<form action="./myinvited" method="post" id="searchForm">
	<div style="position: fixed; top: 0px; width: 100%; height: 64px; font-size: 12px; border-bottom: 1px solid #e8e8e8; line-height: 30px;">
	<div style="height: 24px; margin-left: 4%;">共邀请：${invietCount }人</div>
	<div>
	<div style="float: left; height: 30px; margin-top: 5px; margin-left: 4%;">
	<input value="${startTime }" class="" style="width: 90px;" name="startTime" id="appDate" type="text"/>-<input value="${endTime }" class="" style="width: 90px;" name="endTime" id="appDate2" type="text"/>
	</div>
	<div style="margin-left: 2%;padding-left: 5px; padding-right: 5px; height:　30px; float: left; margin-top: 5px; color: #fff; line-height: 30px; background: #00d1bc;" onclick="tosearch()">
	查询
	</div>
	</div>
	</div>
	</form>
	<div
		style="background: #fff; width: 100%; height: auto; margin-top: 72px; margin-bottom: 44px;">
		<c:forEach items="${list }" var="p">
			<div
				style="width: 92%; height: auto; margin-left: 4%; clear: both; border-bottom: 1px solid #e8e8e8; padding-bottom: 5px;">
				<div style="width: 11%; height: 23px; float: left; margin-top: 5px;">
					<img src="imgs/task_icon_invitation.png"
						style="width: 23px; height: 23px; border-radius: 10px;">
				</div>
				<div
					style="width: 88%; hegiht: auto; overflow: hidden; font-size: 12px; word-break: break-all; margin-top: 10px;">
					<div style="width: 75%; float: left; height: auto;">
						<font color="333">${p.relativename}</font> <br> <font
							color="333"><fmt:formatDate value="${p.time }"
								pattern="yyyy-MM-dd HH:mm" /></font>
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
	<div class="exitclass" style="position:fixed; bottom: 10px; width: 80%; height: 33px; line-height: 33px; 
	left: 10%; background: #00d1bc; border-radius: 16px; text-align: center; color: #fff;" onclick="getinvitedcode()">我的邀请码</div>
</body>
</html>
