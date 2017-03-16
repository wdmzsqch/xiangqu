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
<script src="js/page.js"></script>
<script type="text/javascript">
	function getcouponurl(id) {
		$(".mask").show();
		$(".alert").css("top", ($(".mask").height() - 120) / 2);
		$(".alert").css("left", ($(".mask").width() - 400) / 2);
		$(".alert").text("${webrooturl}" + "user/coupondetail?coupon_id=" + id);
		$(".alert").show();
	}

	function hide() {
		$(".mask").hide();
		$(".alert").hide();
	}
</script>
</head>
<body>
	<jsp:include page="./sidebar.jsp"></jsp:include>
	<div class="main-wrap">

		<div class="crumb-wrap">
			<div class="crumb-list">
				<i class="icon-font"></i>
				<a href="./home_page">首页</a>
				<span class="crumb-step">&gt;</span><span class="crumb-name">文章管理</span>
			</div>
		</div>
		<form action="./comment_list" method="post" name="pageForm" id="pageForm">
			<%-- <div class="search-wrap">
				<div class="search-content">
					<table class="search-tab">
						<tr>
							<th width="120">文章搜索:</th>
							<td><input class="common-text" placeholder="关键字" name="keywords" value="${keywords}" id="" type="text"></td>
							<td><input class="btn btn-primary btn2" name="sub" value="搜索" type="submit"></td>
						</tr>
					</table>
				</div>
			</div> --%>
			<div class="result-wrap">
				<!-- <div class="result-title">
					<div class="result-list">
						<input class="btn btn-primary btn2" name="creat_task" value="新建文章" type="button" onclick="javascript:location='./article_detail'">
					</div>
				</div> -->
				<div class="result-content">
					<table class="result-tab" width="100%">
						<tr>
							<th class="tc">发布人</th>
							<th class="tc">手机号</th>
							<th class="tc">评论内容</th>
							<th class="tc">发布时间</th>
							<th width="7%">操作</th>
						</tr>

						<c:forEach items="${clist}" var="p">
							<tr>
								<td class="tc">${p.user.nickName}</td>
								<td class="tc">${p.user.moblie}</td>
								<td class="tc">${p.content}</td>
								<td class="tc"><fmt:formatDate value="${p.publishTime}" type="date" /></td>
								<td><a class="link-del" href="./comment_del?cid=${p.id}">删除</a></td>
							</tr>
						</c:forEach>

					</table>

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
									}
									;
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
										}
										;
									} else { //当前页在中间部分
										a[a.length] = "<a onclick=\"pageto(1)\">1</a>...";
										for (var i = pageindex - 2; i <= pageindex + 2; i++) {
											setPageList();
										}
										a[a.length] = "...<a onclick=\"pageto(" + count + ")\">" + count + "</a>";
									}
								}
								if (pageindex == count) {
									a[a.length] = "<a onclick=\"\" class=\"hide_page_next pagehover unclicknext \">下一页&nbsp;&gt;</a> ";
								} else {
									a[a.length] = "<a onclick=\"pagenext()\" " + "class=\"pagehover page_next \">下一页&nbsp;&gt;</a> ";
								}
								container.innerHTML = a.join("");
							}
							setPage(document.getElementById("untreatedpage"), parseInt($("#pageCount").val()), parseInt($("#pageIndex").val()));
						</script>
					</c:if>

				</div>

			</div>
		</form>

	</div>

</body>
<div class="mask" style="display: none;" onclick="hide()"></div>
<div class="alert" style="position: fixed; display: none; width: 400px; height: 120px; overflow: hidden; background: #fff; z-index: 101; padding: 20px;"></div>
</html>