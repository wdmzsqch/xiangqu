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
</script>
</head>
<body>
	<jsp:include page="./sidebar.jsp"></jsp:include>
	<div class="main-wrap">
		<div class="crumb-wrap">
			<div class="crumb-list">
				<i class="icon-font"></i>
				<a href="./home_page">首页</a>
				<span class="crumb-step">&gt;</span><span class="crumb-name">用户列表管理</span>
				<span class="crumb-step">&gt;</span><span class="crumb-name">水票详情</span>
			</div>
		</div>
		<form action="./my_wticket" method="post" id="pageForm" name="pageForm">
			<div class="search-wrap">
				<div class="search-content">
					<table class="search-tab">
						<tr>
							<th>总数量：${myCount }，已使用：${useCount }，未使用：${noUseCount }</th>
						</tr>
					</table>
				</div>
			</div>
			<div class="result-wrap">
				<div class="result-content">
					<table class="result-tab" width="100%">
						<tr>
							<th class="tc">水票串号</th>
							<th class="tc">水票状态</th>
						</tr>
						<c:forEach items="${list }" var="l">
							<tr>
								<td class="tc">${l.ticketNum }</td>
								<td class="tc"><c:if test="${l.useState == 0 }">未使用</c:if><c:if test="${l.useState == 1 }">已使用</c:if> </td>
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
</html>