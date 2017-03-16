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
<script src="js/page.js"></script>
<script type="text/javascript">
function geteventurl(id){
	$(".mask").show();
	$(".alert").css("top",($(".mask").height()-120)/2);
	$(".alert").css("left",($(".mask").width()-400)/2);
	$(".alert").text("${webrooturl}"+"user/lookeventdetail?id="+id);
	$(".alert").show();
}

function hide(){
	$(".mask").hide();
	$(".alert").hide();
}

function delevent(id,checkstate1,checkstate2,checkstate3,checkstate4){
	if(checkstate1 == 0){
		alert("内容未审核");
		return;
	}else if(checkstate2 == 0){
		alert("技术未审核");
		return;
	}else if(checkstate3 == 0){
		alert("财务未审核");
		return;
	}else if(checkstate4 == 0){
		alert("最终未审核");
		return;
	}else{
		window.location.href="./del_event?id="+id;
	}
}

function check(relativeId,index){
	var comment = $("#comment_"+index).val();
	if(confirm("是否确认审核？")){
		$.ajax({
			type: "post",
			url: "./adminCheck",
			data:{relativeId :relativeId, type :3, comment :comment},
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
				<i class="icon-font"></i><a href="./home_page">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">活动管理</span>
			</div>
		</div>
		<form action="./event_list" method="post" name="pageForm" id="pageForm">
			<div class="search-wrap">
				<div class="search-content">

					<table class="search-tab">
					</table>
					<div style="position: absolute; right: 40px; top: 97px;">当前总数：${perCount}</div>
				</div>
			</div>
			<div class="result-wrap">
				<div class="result-title">
					<div class="result-list">
					<c:if test="${sessionScope.adminPrivilege != 1  && sessionScope.adminPrivilege != 2 && sessionScope.adminPrivilege != 3 && sessionScope.adminPrivilege != 4}">
						<input class="btn btn-primary btn2" name="creat_task" value="新建活动" type="button" onclick="javascript:location='./event_detail'">
					</c:if>
					</div>
				</div>
				<div class="result-content">
					<table class="result-tab" width="100%">
						<tr>
							<th class="tc" width="10%">活动编号</th>
							<th>图片</th>
							<th>活动名</th>
							<th>报名收益(积分)</th>
							<th>活动起止时间</th>
							<th>关联商家</th>
							<th>已报名人数</th>
							<th>已登记人数</th>
							<th>审核状态</th>
							<c:if test="${sessionScope.adminPrivilege == 1 || sessionScope.adminPrivilege == 2 || sessionScope.adminPrivilege == 3 || sessionScope.adminPrivilege == 4 }">
							<th>备注</th>
							</c:if>
							<th width="7%">操作</th>
						</tr>
						
						<c:forEach items="${event_list}" var="event" varStatus="st">
							<tr>
								<td class="tc">${event.id}</td>
								<td>
								<img src="${fileUrl}${event.pic }" width="60px" height="60px">
								</td>
								<td>${event.name}</td>
								<td>
								<c:if test="${empty event.income}">0</c:if>
								<c:if test="${not empty event.income}">${event.income}</c:if>
								</td>
								<td><fmt:formatDate value="${event.startTime}" type="date" pattern="yyyy-MM-dd" />至<fmt:formatDate value="${event.endTime}" type="date" pattern="yyyy-MM-dd"/></td>
								<td>${event.storename }</td>
								<td>${event.signCount }</td>
								<td>${event.checkCount }</td>
								<td width="10%">
								<c:if test="${event.checkStatus == 1 }">
								内容已审核
								</c:if>
								<c:if test="${event.checkStatus == 0 }">
								内容未审核
								</c:if>
								<br />
								<c:if test="${event.checkStatusY == 1 }">
								技术已审核
								</c:if>
								<c:if test="${event.checkStatusY == 0 }">
								技术未审核
								</c:if>
								<br />
								<c:if test="${event.checkStatusC == 1 }">
								财务已审核
								</c:if>
								<c:if test="${event.checkStatusC == 0 }">
								财务未审核
								</c:if>
								<br />
								<c:if test="${event.checkStatusB == 1 }">
								最终已审核
								</c:if>
								<c:if test="${event.checkStatusB == 0 }">
								最终未审核
								</c:if>
								</td>
								<c:if test="${sessionScope.adminPrivilege == 1 || sessionScope.adminPrivilege == 2 || sessionScope.adminPrivilege == 3 || sessionScope.adminPrivilege == 4 }">
								<td>
								<c:if test="${checktype == 1 }">
								<input id="comment_${st.index }" class="common-text" value="${event.comment }">
								<br>
								<input  class="common-text" value="${event.comment1 }" readonly="readonly" disabled="disabled">
								<br>
								<input  class="common-text" value="${event.comment2 }" readonly="readonly" disabled="disabled">
								<br>
								<input  class="common-text" value="${event.comment3 }" readonly="readonly" disabled="disabled">
								</c:if>
								<c:if test="${checktype == 2 }">
								<input  class="common-text" value="${event.comment }" readonly="readonly" disabled="disabled">
								<br>
								<input id="comment_${st.index }" class="common-text" value="${event.comment1 }">
								<br>
								<input  class="common-text" value="${event.comment2 }" readonly="readonly" disabled="disabled">
								<br>
								<input  class="common-text" value="${event.comment3 }" readonly="readonly" disabled="disabled">
								</c:if>
								<c:if test="${checktype == 3 }">
								<input  class="common-text" value="${event.comment }" readonly="readonly" disabled="disabled">
								<br>
								<input  class="common-text" value="${event.comment1 }" readonly="readonly" disabled="disabled">
								<br>
								<input id="comment_${st.index }" class="common-text" value="${event.comment2 }">
								<br>
								<input  class="common-text" value="${event.comment3 }" readonly="readonly" disabled="disabled">
								</c:if>
								<c:if test="${checktype == 4 }">
								<input  class="common-text" value="${event.comment }" readonly="readonly" disabled="disabled">
								<br>
								<input  class="common-text" value="${event.comment1 }" readonly="readonly" disabled="disabled">
								<br>
								<input  class="common-text" value="${event.comment2 }" readonly="readonly" disabled="disabled">
								<br>
								<input id="comment_${st.index }" class="common-text" value="${event.comment3 }">
								</c:if>
								</td>
								</c:if>
								<td>
								<c:if test="${sessionScope.adminPrivilege == 5 }">
									<a class="link-del" href="./event_detail?id=${event.id}">查看详情</a>
									<br/>
									<a class="link-del" href="./sign_list?event_id=${event.id}">查看报名</a>
									<br/>
									<a class="link-del" onclick="delevent(${event.id},${event.checkStatus},${event.checkStatusY},${event.checkStatusC},${event.checkStatusB})">删除</a> 
									<br/>
									<div style="cursor: pointer; color: #428bca;" onclick="geteventurl(${event.id})">获取活动地址</div>
								</c:if>
								<c:if test="${sessionScope.adminPrivilege == 1  || sessionScope.adminPrivilege == 2 || sessionScope.adminPrivilege == 3 || sessionScope.adminPrivilege == 4}">
								<a class="link-del" href="./event_detail?id=${event.id}">查看详情</a>
								<c:if test="${event.isCheck == 0 }">
								<br/>
								<a class="link-update" style="cursor: pointer;" onclick="check(${event.id},${st.index })">审核</a>
								</c:if>
								<c:if test="${event.isCheck == 1 }"><br/>已审核</c:if>
								</c:if>
								
								<c:if test="${sessionScope.adminPrivilege == 8 }">
									<a class="link-del" href="./sign_list?event_id=${event.id}">查看报名</a>
									<br/>
									<div style="cursor: pointer; color: #428bca;" onclick="geteventurl(${event.id})">获取活动地址</div>
								</c:if>
								
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
<div class="mask" style="display: none;" onclick="hide()"></div>
<div class="alert" style="position: fixed; display: none; width: 400px; height: 120px; overflow: hidden; background: #fff; z-index: 101; padding: 20px;"></div>
</html>