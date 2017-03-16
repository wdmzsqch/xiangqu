<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统消息管理</title>
<link rel="stylesheet" type="text/css" href="css/common.css" />
<link rel="stylesheet" type="text/css" href="css/main.css" />
<link rel="stylesheet" type="text/css" href="css/page.css" />
<script type="text/javascript" src="js/libs/modernizr.min.js"></script>
<script src="./js/jquery-1.11.3.min.js"></script>
<script src="./js/page.js"></script>
<script type="text/javascript">
function delmessage(id){
	if(confirm("确定删除系统消息?")){
		$.ajax({
			url:'./delMessage',
			type:'post',
			data:{messageid:id},
			success:function(msg){
				alert("删除成功!");
				window.location.reload();
			}
		})
	}
}

function delall() {
	if(confirm("确定删除系统消息?")){
		$.ajax({ url: "./delMessage",
			async:false,
			type:'post',
			data:$("input[name='messageid']:checked").serialize(),
			success:function(msg){
				alert("删除成功!");
				window.location.reload();
			}
		});
	}
}

$("#allChoose").change(function() {
		$("input[name='messageid']").prop("checked",$("#allChoose").prop("checked")); 
	});
</script>
</head>
<body>

	<jsp:include page="./sidebar.jsp"></jsp:include>

	<div class="main-wrap">
		<form name="pageForm" id="pageForm" method="post" action="./system_message">
			<div class="crumb-wrap">
				<div class="crumb-list">
					<i class="icon-font"></i><a href="./home_page">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">系统消息管理</span>
				</div>
			</div>

			<div class="result-wrap">
			
			<div class="result-title">
					<div class="result-list">
						<input class="btn btn-primary btn2" name="creat_task" value="发送消息" type="button" onclick="javascript:location='./turn_sendmessage'">
					</div>
				</div>
				<!-- <div class="result-title">
                    <div class="result-list">
                        <input class="btn btn-primary btn2" name="creat_task" value="创建商品" type="button" onClick="javascript:location='goods_insert.html'" >
                       
                    </div>
                </div> -->
				<div class="result-content">
					<table class="result-tab" width="100%">
						<tr>
							<th class="tc" width="5%"><input id="allChoose" type="checkbox"></th>
							<th>标题</th>
							<th>内容</th>
							<th>用户</th>
							<th>发送时间</th>
							<th>操作</th>
						</tr>


						<c:forEach items="${message_list}" var="message">
							<tr>
								<td class="tc"><input name="messageid" value="${message.id}" type="checkbox"></td>
								<td>${message.title}</td>
								<td>${message.content }</td>
								<td><c:if test="${message.type == 1}">用户</c:if><c:if test="${message.type == 2}">商家</c:if><c:if test="${message.type == 3}">全部</c:if></td>
								<td><fmt:formatDate value="${message.publishTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td> 
								<td><a class="link-del" href="javascript:void(0)" onclick="delmessage(${message.id})">删除</a></td>
							</tr>
						</c:forEach>



					</table>
					<div style="float: left">
						<font size="+1">批量处理:</font><input class="btn btn-primary btn2" type="button" value="删除" onclick="delall()">
					</div>



					<br />
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