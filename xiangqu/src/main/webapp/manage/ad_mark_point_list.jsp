<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>后台管理</title>
<link rel="stylesheet" type="text/css" href="css/common.css" />
<link rel="stylesheet" type="text/css" href="css/main.css" />
<link rel="stylesheet" type="text/css" href="css/page.css" />

<style type="text/css">
.submit_link {
	color: #428BCA;
	text-decoration: none;
	box-sizing: border-box;
	line-height: 35px;
	border-radius: 0;
	background-image: none;
	box-shadow: none;
	background-color: transparent;
	background-repeat: repeat;
	background-position: center;
	border-color: transparent;
	cursor: pointer;
	border: 0 solid #000000;
	font: 14px/1.5 "微软雅黑";
	padding: 0px;
	margin: 0px;
}
</style>
<script type="text/javascript" src="js/libs/modernizr.min.js"></script>
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
<script src="./js/jquery-1.11.3.min.js"></script>
<script src="js/page.js"></script>
<script type="text/javascript">
$(function(){
	$("#allchecked").click(function(){
		$("input[name=ids]").prop("checked", this.checked);
	});
});

function del_ad_point(id){
	if(confirm("是否删除")){
		$.ajax({
			type: "post",
			url: "./del_ad_point",
			data: {id :id},
			success: function(data){
				if(data == "success"){
					alert("删除成功");
					window.location.reload();
				}else{
					alert("删除失败");
					window.location.reload();
				}
			}
		});
	}
}

function addmarkadpoint(){
	$("#pageForm").attr("action","./editor_mark_ad_point").submit();
}


function makeactionpointmap(){
	window.location.href="./make_action_point_map";
}

function markactionpoint(id){
	window.location.href="./make_action_point_map?id="+id;
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
				<span class="crumb-step">&gt;</span><span class="crumb-name">广告点位管理</span>
			</div>
		</div>
		<form action="./select_mark_ad_point" method="post" name="pageForm" id="pageForm">
		<input type="hidden" name="markid" value="${markid }"/>
		<div class="search-wrap">
				<div class="search-content">

					<table class="search-tab">
						<tr>
							<th width="90">点位名称:</th>
							<td><input class="common-text" placeholder="关键字" name="keywords" value="${keywords}" id="" type="text"></td>
							<th width="90">所在区域:</th>
							<td><input class="common-text" placeholder="所在区域" name="areaSearch" value="${areaSearch}" id="" type="text"></td>
							<td><select name="SourceClass" id="SourceClass" class="common-text" style="margin-top: 10px;">
									<option value="">--广告资源类型--</option>
										<option value="电梯轿厢广告1.0" <c:if test="${SourceClass eq '电梯轿厢广告1.0' }">selected="selected"</c:if>>电梯轿厢广告1.0</option>
										<option value="电梯轿厢广告2.0" <c:if test="${SourceClass eq '电梯轿厢广告2.0' }">selected="selected"</c:if>>电梯轿厢广告2.0</option>
										<option value="电梯轿厢广告3.0" <c:if test="${SourceClass eq '电梯轿厢广告3.0' }">selected="selected"</c:if>>电梯轿厢广告3.0</option>
										<option value="社区灯箱" <c:if test="${SourceClass eq '社区灯箱' }">selected="selected"</c:if>>社区灯箱</option>
										<option value="电梯门广告" <c:if test="${SourceClass eq '电梯门广告' }">selected="selected"</c:if>>电梯门广告</option>
							</select></td>
							<td><input class="btn btn-primary btn2" name="sub" value="搜索" type="submit"></td>
						</tr>
					</table>
				</div>
			</div>
		<div class="result-wrap">
			<div class="result-title">
					<div class="result-list">
						<input class="btn btn-primary btn2" name="creat_task" value="添加点位记录" type="button" onclick="addmarkadpoint()">
					</div>
				</div>
			<div class="result-content">
				<table class="result-tab" width="100%">
					<tr>
						<th><input type="checkbox" id="allchecked"/>全选</th>
						<th>点位名称</th>
						<th>经度</th>
						<th>纬度</th>
						<th>广告编号</th>
						<th>区域</th>
						<th>广告资源类型</th>
						<th>操作</th>

					</tr>
					<c:forEach items="${list}" var="ad">
						<tr>
							<td><input type="checkbox" value="${ad.id}" name="ids"/></td>
							<td>${ad.plotName}</td>
							<td>${ad.longitude }</td>
							<td>${ad.latitude }</td>
							<td>${ad.adPointNum }</td>
							<td>${ad.adArea }</td>
							<td>${ad.adSourceClass }</td>
							<td>
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
</html>