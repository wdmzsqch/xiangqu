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
<script type="text/javascript" src="js/libs/modernizr.min.js"></script>
<script type="text/javascript" src="js/page.js"></script>
</head>
<body>
	<jsp:include page="./sidebar.jsp"></jsp:include>
	<div class="main-wrap">

		<div class="crumb-wrap">
			<div class="crumb-list">
				<i class="icon-font"></i><a href="./home_page">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">报名管理</span>
			</div>
		</div>
		<form action="./sign_list" method="post" name="pageForm" id="pageForm">
			<input type="hidden" value="${event_id }" name="event_id"/>
			<div class="search-wrap">
				<div class="search-content">

					<table class="search-tab">
						<tr>
							<th width="70">序列号:</th>
							<td><input class="common-text" placeholder="序列号" value="${code}" name="code"  type="text"></td>
							<td><input class="common-text" placeholder="手机号" value="${phone}" name="phone"  type="text"></td>
							<td>
								<select name="searchType" class="common-text">
									<option value="">--报名类型--</option>
									<option value="1" <c:if test="${searchType ==1 }">selected="selected"</c:if>>报名成功</option>
									<option value="2" <c:if test="${searchType ==2 }">selected="selected"</c:if>>报名未成功</option>
								</select>
							</td>
							<td><input class="btn btn-primary btn2" name="sub" value="搜索" type="submit"></td>
						</tr>
					</table>

				</div>
			</div>
			<div class="result-wrap">
				<div class="result-title">
					<div class="result-list">
<!-- 						<input class="btn btn-primary btn2" name="creat_task" value="新建活动" type="button" onclick="javascript:location='./event_detail'"> -->
					</div>
				</div>
				<div class="result-content">
					<table class="result-tab" width="100%">
						<tr>
							<th class="tc" width="10%">报名编号</th>
							<th>活动名</th>
							<th>报名用户</th>
							<th>姓名</th>
							<th>性别</th>
							<th>年龄</th>
							<th>电话</th>
							<th>地址</th>
							<th>备注</th>
							<th>序列号</th>
							<th>文件</th>
							<th>报名时间</th>
							<th>是否参加</th>
							<th>参加时间</th>
						</tr>
						
						<c:forEach items="${sign_list}" var="sign">
							<tr>
								<td class="tc">${sign.id}</td>
								<td>${sign.event_name}</td>
								<td>${sign.user_name}</td>
								<td>${sign.name}</td>
								<td>${sign.gender}</td>
								<td>${sign.age}</td>
								<td>${sign.phone}</td>
								<td>${sign.address}</td>
								<td style="width: 20%; line-height: 15px;">${sign.comment}</td>
								<td>${sign.code}</td>
								<td><img style="width:100px; height:100px;" src="${fileUrl}${sign.pic}" /></td>
								<td><fmt:formatDate value="${sign.signTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td>
								<c:if test="${sign.isJoined == 0}">未参加</c:if>
								<c:if test="${sign.isJoined == 1}">已参加</c:if>
								</td>
								<td>
								<fmt:formatDate value="${sign.joinTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss" />
								</td>
							</tr>
						</c:forEach>

					</table>

					<c:if test="${pageCount>1}">
							<input type="hidden" value="${pageCount }" id="pageCount" name="pageCount"/>
					<input type="hidden" value="${pageIndex }" id="pageIndex" name="pageIndex"/>
					<div id="untreatedpage" ></div>
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
<script type="text/javascript">
</script>
</html>