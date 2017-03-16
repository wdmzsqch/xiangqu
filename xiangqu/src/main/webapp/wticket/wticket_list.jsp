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
<script src="js/page.js"></script>
<script type="text/javascript">
$(function(){
	$("#allchecked").click(function(){
		$("input[name=id]").prop("checked", true);
	});
});

function makeTicketUse(){
	var length = $("input[name=id]:checked").length;
	if(length == 0){
		alert("选选择水票");
		return;
	}else{
		$("#pageForm").attr("action","./makeTicketUse").submit();
	}
}

function makeTicketSell(){
	var length = $("input[name=id]:checked").length;
	if(length == 0){
		alert("选选择水票");
		return;
	}else{
		$("#pageForm").attr("action","./makeTicketSell").submit();
	}
}

function showalert(){
	$(".mask").show();
	$(".alert").show();
	$(".alert").css({"top": parseFloat($(".mask").height()-$(".alert").height())/2,"left":parseFloat($(".mask").width()-$(".alert").width())/2});
}

function hide(){
	$(".mask").hide();
	$(".alert").hide();
}
function submitform(){
	var csvfile = $("#csvfile").val();
	if(csvfile == ""){
		alert("选择文件");
		return;
	}
	$("#alertForm").submit();
}

function verification(){
	window.location.href = "./verification";
}

function ticketCode(){
	window.location.href = "./createTickCode";
}
</script>
</head>
<body>
	<jsp:include page="./sidebar.jsp"></jsp:include>
	<div class="main-wrap">
		<div class="crumb-wrap">
			<div class="crumb-list">
				<i class="icon-font"></i>
				<a href="./home_page">首页</a>
				<span class="crumb-step">&gt;</span><span class="crumb-name">水票管理</span>
			</div>
		</div>
		<form action="./wticket_list" method="post" id="pageForm" name="pageForm">
			<div class="search-wrap">
				<div class="search-content">
					<table class="search-tab">
						<tr>
							<th width="70">串号搜索:</th>
							<td><input class="common-text" placeholder="串号" value="${ticketNum}" name="ticketNum" value="" id="" type="text">
							</td>
<!-- 							<td>筛选使用状态：<select class="common-text" name="state" id=""> -->
<!-- 									<option value="">--全部数据--</option> -->
<%-- 									<option value="1" <c:if test='${state == 1}'>selected</c:if>>未使用</option> --%>
<%-- 									<option value="2" <c:if test='${state == 2}'>selected</c:if>>已使用</option> --%>
<%-- 									<option value="3" <c:if test='${state == 3}'>selected</c:if>>未出售</option> --%>
<%-- 									<option value="4" <c:if test='${state == 4}'>selected</c:if>>已出售</option> --%>
<!-- 							</select></td> -->
							<td><input class="btn btn-primary btn2" name="sub" value="搜索" type="submit"></td>
						</tr>
					</table>
				</div>
			</div>
			<div class="result-wrap">
				<div style="width: 100%; height: 40px;">
<!-- 				<input class="btn btn-primary btn2" name="sub" value="全选" type="button" id="allchecked"> -->
				<input class="btn btn-primary btn2" name="sub" value="新建导入" type="button" onclick="showalert()">
				<input class="btn btn-primary btn2" name="sub" value="批量生成二维码" type="button" onclick="ticketCode()">
<!-- 				<input class="btn btn-primary btn2" name="sub" value="批量已使用" type="button" onclick="makeTicketUse()"> -->
<!-- 				<input class="btn btn-primary btn2" name="sub" value="批量已售出" type="button" onclick="makeTicketSell()"> -->
				<input class="btn btn-primary btn2" name="sub" value="手输验证" type="button" onclick="verification()">
				<span style="float: right;">未使用：${ticketCount-useCount }</span>
				<span style="float: right;">已使用：${useCount }，</span>
				<span style="float: right;">水票总数：${ticketCount}，</span>
				</div>
				<div class="result-content">
					<table class="result-tab" width="100%;">
						<tr>
<!-- 							<th class="tc">选择</th> -->
							<th class="tc">编号</th>
							<th class="tc">水票串号</th>
							<th class="tc">使用状态</th>
<!-- 							<th class="tc">出售状态</th> -->
<!-- 							<th class="tc">操作</th> -->
						</tr>
						<c:forEach items="${list }" var="l">
							<tr>
<%-- 								<td class="tc"><input type="checkbox" value="${l.id }" name="id" /></td> --%>
								<td class="tc">${l.id }</td>
								<td class="tc">${l.ticketNum  }</td>
								<td class="tc"><c:if test="${l.useState == 0 }">未使用</c:if><c:if test="${l.useState == 1 }">已使用</c:if> </td>
<%-- 								<td class="tc"><c:if test="${l.sellState == 0 }">未出售</c:if><c:if test="${l.sellState == 1 }">已出售</c:if></td> --%>
<!-- 								<td class="tc"> -->
<%-- 								<c:if test="${l.useState == 0 }"><a class="link-update" href="">已使用</a></c:if> --%>
<%-- 								<c:if test="${l.sellState == 0 }"><a class="link-update" href="">已出售</a></c:if> --%>
<!-- 								</td> -->
							</tr>
						</c:forEach>
					</table>

					<br />
					<c:if test="${pageCount>1}">
						<input type="hidden" value="${pageCount }" id="pageCount" name="pageCount" />
						<input type="hidden" value="${pageIndex }" id="pageIndex" name="pageIndex" />
						<div id="untreatedpage"></div>
						<script type="text/javascript">
						//container 容器，count 总页数 pageindex 当前页数
						function setPage(container, count, pageindex) {
						var a = [];
						  //总页数少于10 全部显示,大于10 显示前3 后3 中间3 其余....
						  if (pageindex == 1) {
							  //alert(pageindex);
						    a[a.length] = "<a onclick=\"\" class=\"hide_page_prev pagehover unclickprev \">&lt;&nbsp;上一页</a>";
						  } else {
						    a[a.length] = "<a onclick=\"pageprev()\" class=\"pagehover page_prev\">&lt;&nbsp;上一页</a>";
						  }
						  function setPageList() {
						    if (pageindex == i) {
						      a[a.length] = "<a onclick=\"pageto(" + i + ")\" class=\"on\">" + i + "</a>";
						    } else {
						      a[a.length] = "<a onclick=\"pageto(" + i + ")\">" + i + "</a>";
						    }
						  }
						  //总页数小于10
						  if (count <= 10) {
						    for (var i = 1; i <= count; i++) {
						      setPageList();
						    };
						  } else {
							//总页数大于10页
						    if (pageindex <= 4) {
						      for (var i = 1; i <= 5; i++) {
						        setPageList();
						      }
						      a[a.length] = "...<a onclick=\"pageto(" + count + ")\">" + count + "</a>";
						    } else if (pageindex >= count - 3) {
						      a[a.length] = "<a onclick=\"pageto(1)\">1</a>...";
						      for (var i = count - 4; i <= count; i++) {
						        setPageList();
						      };
						    } else { //当前页在中间部分
						      a[a.length] = "<a onclick=\"pageto(1)\">1</a>...";
						      for (var i = pageindex - 2; i <= pageindex+2; i++) {
						        setPageList();
						      }
						      a[a.length] = "...<a onclick=\"pageto(" + count + ")\">" + count + "</a>";
						    }
						  }
						  if (pageindex == count) {
							    a[a.length] = "<a onclick=\"\" class=\"hide_page_next pagehover unclicknext \">下一页&nbsp;&gt;</a> ";
							  } else {
							    a[a.length] = 
							    	"<a onclick=\"pagenext()\" "+
							    	"class=\"pagehover page_next \">下一页&nbsp;&gt;</a> ";
							  }
						  container.innerHTML = a.join("");
						} 
						setPage(document.getElementById("untreatedpage"),parseInt($("#pageCount").val()),parseInt($("#pageIndex").val()));
						</script>
					</c:if>
				</div>
			</div>
		</form>
	</div>
</body>
<div class="mask" style="display: none;" onclick="hide()"></div>
<div class="alert" style="position: fixed; width: 450px; height: 200px; z-index: 10001; background: #fff; display: none;">
	<form id="alertForm" action="./importFile" enctype="multipart/form-data" method="post">
		<div style="width: 100%; height: 45px; line-height: 45px; text-align: center; font-size: 25px;" id="alert_title">导入</div>
		<div style="width: 250px; height: 100px; margin:0 auto;">
			<input type="file" name="csvfile" id="csvfile"/>
		</div>	
		<div style="height: 10px;"></div>
		<div style="width: 105px; height: 40px; line-height: 40px; background: #36aa47; color: #fff;text-align: center; margin: 0 auto;cursor: pointer;" onclick="submitform()">提交</div>
	</form>
</div>
</html>