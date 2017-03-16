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
<script type="text/javascript" src="js/libs/modernizr.min.js"></script>
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
<script src="js/page.js"></script>
<script type="text/javascript">
	function sortlist(){
		$("#isSort").val(1);
		$("#baSort").val(0);
		$("#incomeSort").val(0);
		$("#pageForm").submit();
	}
	
	function sortincome(){
		$("#isSort").val(0);
		$("#baSort").val(0);
		$("#incomeSort").val(1);
		$("#pageForm").submit();
	}
	
	function sortbalance(){
		$("#baSort").val(1);
		$("#isSort").val(0);
		$("#incomeSort").val(0);
		$("#pageForm").submit();
	}
	
	function setpromoter(type,uid){
		$.ajax({ url: "./edit_promoter?type="+type+"&userId="+uid,
    		async:true,
    		type:'post',
    		success: function(){
    			window.location.reload();
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
				<i class="icon-font"></i>
				<a href="./home_page">首页</a>
				<span class="crumb-step">&gt;</span><span class="crumb-name">用户列表管理</span>
			</div>
		</div>
		<form action="./user_list" method="post" id="pageForm" name="pageForm">
			<input type="hidden" name="isSort" id="isSort" value="${isSort }" />
			<input type="hidden" name="baSort" id="baSort" value="${baSort }" />
			<input type="hidden" name="incomeSort" id="incomeSort" value="${incomeSort }" />
			<div class="search-wrap">
				<div class="search-content">
					<table class="search-tab">
						<tr>
							<th width="70">关键字:</th>
							<td><input class="common-text" placeholder="关键字" value="${keywords}" name="keywords" value="" id="" type="text"></td>
							<td><select class="common-text" name="searchType" id="">
									<option value="">--全部用户--</option>
									<option value="1" <c:if test='${searchType == 1}'>selected</c:if>>有昵称用户</option>
									<option value="2" <c:if test='${searchType == 2}'>selected</c:if>>系统昵称用户</option>
							</select></td>

						</tr>
					</table>
					<table class="search-tab">
						<tr>
							<th width="100">生成时间段：</th>
							<td><input value="${starttime}" name="starttime" id="starttime" style="width: 80px;" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" /></td>
							<td>——</td>
							<td><input value="${endtime}" name="endtime" id="endtime" style="width: 80px;" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" /></td>
							<td>积分范围：</td>
							<td><input value="${UCollectCount}" name="UCollectCount" style="width: 80px;" id="UCollectCount" /></td>
							<td>——</td>
							<td><input value="${DCollectCount}" name="DCollectCount" style="width: 80px;" id="DCollectCount" /></td>
							<td><input class="btn btn-primary btn2" name="sub" value="搜索" type="submit"></td>
							<td><input class="btn btn-primary btn2" name="sub" value="按邀请人数排序" type="button" onclick="sortlist()"></td>
							<td><input class="btn btn-primary btn2" name="sub" value="按积分排序" type="button" onclick="sortbalance()"></td>
							<td><input class="btn btn-primary btn2" name="sub" value="按昨日收益排序" type="button" onclick="sortincome()"></td>
						</tr>
					</table>
					<div style="position: absolute; right: 40px; top: 115px;">当前用户总数：${userCount}</div>
				</div>
			</div>
			<div class="result-wrap">
				<div class="result-content">
					<table class="result-tab" width="100%">
						<tr>
							<th class="tc" width="10%">用户名</th>
							<th>姓名</th>
							<th>昵称</th>
							<th>性别</th>
							<th>手机</th>
							<th>地址</th>
							<th>当前积分</th>
							<th>截止昨日总积分</th>
							<th>昨日获取积分</th>
							<th>已邀请人数</th>
							<th>操作</th>
						</tr>
						<c:forEach items="${userlist }" var="user">
							<tr>
								<td class="tc">${user.userName }</td>
								<td>${user.realName }</td>
								<td>${user.nickName }</td>
								<td>${user.gender }</td>
								<td>${user.moblie }</td>
								<td>${user.proname }${user.cityname }${user.areaname }${user.address }</td>
								<td>${user.balance }</td>
								<td>${user.totalIncome }</td>
								<td>${user.dailyIncome }</td>
								<td><c:if test="${ empty user.invietCount }">0</c:if> <c:if test="${not empty user.invietCount }">${user.invietCount }</c:if></td>
								<td>
								<c:if test="${ not (sessionScope.adminPrivilege == 8) }">
								<a class="link-del" href="./user_modify?userId=${user.id }">修改信息</a>丨 
								<a class="link-del" href="./user_password?userId=${user.id }">更改密码</a>丨 
								</c:if>
								<a class="link-update" href="./user_record?userId=${user.id }">兑换/购买记录</a>丨 
								<a class="link-del" href="./user_address?userId=${user.id }">收件地址</a>丨 
								<a class="link-del" href="./myincome?userId=${user.id }">查看明细</a> 
								<c:if test="${not empty user.invietCount and user.invietCount > 0 }">
										<c:if test="${empty user.type or user.type < 0 }">
									丨<a class="link-del" onclick="setpromoter(0,${user.id})">设为推广员 </a>
										</c:if>
										<c:if test="${not empty user.type and user.type >= 0 }">
									丨<a class="link-del" onclick="setpromoter(-2,${user.id})">设为普通用户 </a>
										</c:if>
									</c:if></td>
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