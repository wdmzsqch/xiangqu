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
			</div>
		</div>
		<form action="./w_user_list" method="post" id="pageForm" name="pageForm">
			<div class="search-wrap">
				<div class="search-content">
					<table class="search-tab">
						<tr>
							<th width="70">关键字:</th>
							<td><select class="common-text" name="searchType" id="">
									<option value="">--全部用户--</option>
									<option value="1" <c:if test='${searchType == 1}'>selected</c:if>>客户姓名</option>
									<option value="2" <c:if test='${searchType == 2}'>selected</c:if>>手机号码</option>
							</select></td>
							<td><input class="common-text" placeholder="关键字" value="${keywords}" name="keywords" value="" id="" type="text"></td>
							<td><input class="btn btn-primary btn2" name="sub" value="搜索" type="submit"></td>
							<td><a class="link-update" href="./user_insert?userId="><input class="btn btn-primary btn2" name="sub" value="新建用户" type="button"></a></td>
						</tr>
					</table>
					<div style="position: absolute; right: 40px; top: 115px;">当前用户总数：${userCount}</div>
				</div>
			</div>
			<div class="result-wrap">
				<div class="result-content">
					<table class="result-tab" width="100%">
						<tr>
							<th class="tc" width="10%">姓名</th>
							<th class="tc">手机</th>
							<th class="tc">水票数量</th>
							<th class="tc">地址</th>
							<th class="tc">备注</th>
							<th class="tc">操作</th>
						</tr>
						<c:forEach items="${userlist }" var="user">
							<tr>
								<td class="tc">${user.name }</td>
								<td class="tc">${user.phone }</td>
								<td class="tc">${user.wtcount }<br>(已使用：${user.wucount },未使用：${user.wnucount })</td>
								<td class="tc">${user.proname }${user.cityname }${user.areaname }${user.address }</td>
								<td class="tc">${user.mark }</td>
								<td class="tc">
<%-- 								<a class="link-update" href="./my_wticket?userId=${user.id }">查看水票详情</a>丨  --%>
								<a class="link-del" href="./user_insert?userId=${user.id }">修改</a>
								</td>
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