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
<script src="./js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" >
function cleartabbel(){
	$.ajax({ url: "./clear_table",
		type:'post',
		success: function(data){
			if(data == "success"){
				alert("操作成功");
				window.location.reload();
			}
		}
		});
	}

</script>
</head>
<body>
	<jsp:include page="./sidebar.jsp"></jsp:include>
	<div class="main-wrap">
		<div class="crumb-wrap">
			<div class="crumb-list">
				<i class="icon-font"></i><a href="./home_page">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">抽奖管理</span>
			</div>
		</div>
		<div class="search-wrap">
			<div class="search-content">
				<table class="search-tab">
				</table>
			</div>
		</div>
		<div class="result-wrap">
			<div class="result-title">
				<div class="result-list">
				<form action="./change_probability" method="post">
					修改概率: <input class="common-text" name="probability" value="${probability}" type="number" style="width: 50px;"> ‰ &nbsp; &nbsp;
					一等奖 <input class="common-text" name="one" value="${one}" type="number" style="width: 50px;">个&nbsp; &nbsp;
					二等奖<input class="common-text" name="two" value="${two}" type="number" style="width: 50px;">个 &nbsp; &nbsp; 
					三等奖<input class="common-text" name="three" value="${three}" type="number" style="width: 50px;">个
					<input class="btn btn-primary btn2" value="提交修改" type="submit"> &nbsp; &nbsp; <input class="btn btn-primary btn2" value="清空重来（谨慎点击）" type="button" onclick="cleartabbel()">
				</form>
				</div>
			</div>
			<div class="result-content">
				<form action="./lottery_list" method="post" name="pageForm" id="pageForm">
					<table class="result-tab" width="100%">
						<tr>
							<th class="tc" width="10%">用户</th>
							<th>手机</th>
							<th>奖项</th>
						</tr>
						<c:forEach items="${lotteryList}" var="lotteryinfo">
							<tr>
								<td class="tc">${lotteryinfo.address}</td>
								<td>${lotteryinfo.phone }</td>
								<td>${lotteryinfo.lottery }</td>
							</tr>
						</c:forEach>
					</table>
				</form>
			</div>
		</div>
	</div>
</body>
</html>