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
<script src="./js/page.js"></script>
<script type="text/javascript">
function check(relativeId, index){
	var comment = $("#comment_"+index).val();
// 	if(comment == ""){
// 		alert("备注不能为空");
// 		return;
// 	}
	if(confirm("是否确认审核？")){
		$.ajax({
			type: "post",
			url: "./adminCheck",
			data:{relativeId :relativeId, type :4, comment :comment},
			success: function(data){
				if(data == "success"){
					alert("审核成功");
					window.location.reload();
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
				<i class="icon-font"></i><a href="./home_page">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">充值管理</span>
			</div>
		</div>
		<form action="./recharge_list" method="post" name="pageForm" id="pageForm">
			<div class="search-wrap">
				<div class="search-content">
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
							<th>充值对像</th>
							<th>管理员</th>
							<th>充值金额</th>
							<th>充值时间</th>
							<th>审核状态</th>
							<th>备注</th>
							<th width="7%">操作</th>
						</tr>
						
						<c:forEach items="${rechargelist}" var="p" varStatus="st">
							<tr>
								<td>${p.to_name}</td>
								<td>${p.from_name}</td>
								<td>${p.rechargeMoney}</td>
								<td><fmt:formatDate value="${p.rechargeTime}" type="date" /></td>
								<td>
								<c:if test="${p.checkStatusC == 1 }">
								财务已审核
								</c:if>
								<c:if test="${p.checkStatusC == 0 }">
								财务未审核
								</c:if>
								<br />
								<c:if test="${p.checkStatusB == 1 }">
								最终已审核
								</c:if>
								<c:if test="${p.checkStatusB == 0 }">
								最终未审核
								</c:if>
								</td>
								<td>
								<c:if test="${checktype == 3 }">
								<input id="comment_${st.index }" class="common-text" value="${p.comment }">
								<br>
								<input  class="common-text" value="${p.comment1 }" readonly="readonly" disabled="disabled">
								</c:if>
								<c:if test="${checktype == 4 }">
								<input  class="common-text" value="${p.comment }" readonly="readonly" disabled="disabled">
								<br>
								<input id="comment_${st.index }" class="common-text" value="${p.comment1 }">
								</c:if>
								</td>
								<td width="6%">
								<c:if test="${p.isCheck == 0 }">
								<a class="link-update" style="cursor: pointer;" onclick="check(${p.id},${st.index })">审核</a>
								</c:if>
								<c:if test="${p.isCheck == 1 }">已审核</c:if>
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
	
	
	<div class="mask" style="display: none;" onclick="hide()"></div>
	<div class="alert" style="position: fixed; display: none; width: 400px; height: 120px; overflow: hidden; background: #fff; z-index: 101; padding: 20px;"></div>
	<div class="addTimesalert" style="position: fixed; border-radius: 8px; display: none; width: 400px; height: 240px; overflow: hidden; background: #fff; z-index: 101; padding: 20px;">
	<form action="" method="post" id="addTimesForm">
	<input type="hidden" id="alert_id" name="mission_id"/>
	<p style="text-align: center; font-size: 24px;">加投数量</p>
	<br>
	<div style="width: 180px; height: 40px; margin: 0 auto;">数量：<input id="alert_times" name="times" style="width: 120px; height: 38px; border: 1px solid #e8e8e8;"/></div>
	<br>
	<div style="width: 145px; height: 40px; margin: 0 auto;">
	<div style="width: 60px; height: 38px; background: #428bca; color: #fff; float: left; text-align: center; line-height: 38px; cursor: pointer;" onclick="submitTimes()">确定</div>
	<div style="width: 60px; height: 38px; background: #ccc; color: #fff; float: right; text-align: center; line-height: 38px; cursor: pointer;" onclick="hide()">取消</div>
	</div>
	</form>
	</div>

</body>

</html>