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
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
<script src="js/page.js"></script>
<script type="text/javascript">
function exchange(id){
	if(confirm("是否兑换")){
		$.ajax({
			type: "post",
			url: "./exchangePrize",
			data: {id :id},
			success: function(data){
				if(data == "success"){
					alert("兑换成功");
					window.location.reload();
				}else{
					alert("兑换失败");
					return;
				}
			}
		});
	}
}
</script>
</head>
<body>
	<jsp:include page="./sidebar.jsp"></jsp:include>
	<div class="main-wrap">

		<div class="crumb-wrap">
			<div class="crumb-list">
				<i class="icon-font"></i><a href="./home_page">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">中奖兑换管理</span>
			</div>
		</div>
		<form action="./prize_list" method="post" name="pageForm" id="pageForm">
			<div class="search-wrap">
				<div class="search-content">
				<table class="search-tab">
						<tr>
							<th >用户名：</th>
							<td><input value="${userName}" name="userName"/></td>
							<td>手机号：</td>
							<td><input value="${moblie}" name="moblie" /></td>
							<td>兑换状态：</td>
							<td><select class="common-text" name="state" id="">
									<option value="">--全部--</option>
									<option value="2" <c:if test='${state == 2}'>selected</c:if>>未兑奖</option>
									<option value="1" <c:if test='${state == 1}'>selected</c:if>>已兑奖</option>
							</select></td>
							<td><input class="btn btn-primary btn2" name="sub" value="搜索" type="submit"></td>
						</tr>
					</table>
				</div>
			</div>
			<div class="result-wrap">
				<div class="result-title">
					<div class="result-list">
					</div>
				</div>
				<div class="result-content">
					<table class="result-tab" width="100%">
						<tr>
							<th>用户</th>
							<th>中奖时间</th>
							<th>中奖内容</th>
							<th>兑换情况</th>
							<th>操作</th>
						</tr>
						
						<c:forEach items="${list}" var="p">
							<tr>
								<td>${p.user.userName}</td>
								<td><fmt:formatDate value="${p.addtime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td>${p.prize }</td>
								<td>
								<c:if test="${p.state == 2 }">未兑换</c:if>
								<c:if test="${p.state == 1 }">已兑换</c:if>
								</td>
								<td><c:if test="${p.state == 0 }"><a class="link-del" onclick="exchange(${p.id})">兑换</a></c:if></td>
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
</html>