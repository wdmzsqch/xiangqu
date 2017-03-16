<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>后台管理</title>
    <link rel="stylesheet" type="text/css" href="css/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
    <link rel="stylesheet" type="text/css" href="css/page.css" />
    <script src="./js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="js/libs/modernizr.min.js"></script>
    <script src="js/page.js"></script>
    <style type="text/css">
    	.mask{position: fixed;top: 0px;left: 0px;right: 0px;bottom: 0px;opacity: 0.8;background: #10090d;}

		.alert{width: 560px;height: 316px;background: #fff;	position: fixed; }
    </style>
    <script type="text/javascript">
function pageto(pindex) {
	$('#pageIndex').val(pindex);
	$('#myform').submit();
}

function pageprev() {
	var page = Number($('#pageIndex').val()) - 1;
	if (page > 0) {
		$('#pageIndex').val(page);
		$('#myform').submit();
	}
}

function pagenext() {
	var page = Number($('#pageIndex').val()) + 1;
	if (page <= Number($('#pageCount').val())) {
		$('#pageIndex').val(page);
		$('#myform').submit();
	}
}

function showalert(id, status, title){
	$("#alert_id").val(id);
	$("#alert_status").val(status);
	$("#alert_title").text(title);
	$(".alert").css("top",$(".mask").height()/2-$(".alert").height()/2);
	$(".alert").css("left",document.body.clientWidth/2-$(".alert").width()/2);
	$(".alert").show();
	$(".mask").show();
}

function hide(){
	$(".alert").hide();
	$(".mask").hide();
}

function submitform(){
	var alert_comment = $("#alert_comment").val();
	if(alert_comment == ''){
		alert("备注不能为空");
		return;
	}
	$("#alertForm").submit();
}
</script>
</head>
<body>
<jsp:include page="./sidebar.jsp"></jsp:include>
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="./home_page">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">任务需求管理</span></div>
        </div>
        <form name="myform" id="myform" method="post" action = "./task_needs">
        <div class="search-wrap">
            <div class="search-content">
                <form action="./task_needs" method="post">
                    <table class="search-tab">
                        <tr>
                            <th width="120">任务需求筛选:</th>
                            <td>
                                <select name="shopId" id="">
                                	<option value="">全部商家</option>
                                	<c:forEach items="${shoplist }" var = "shop">
                                		<c:if test="${shop.id == shopId }">
                                   		 <option value="${shop.id }" selected="selected">${shop.companyName }</option>
                                		</c:if>
                                		<c:if test="${shop.id != shopId }">
                                   		 <option value="${shop.id }">${shop.companyName }</option>
                                		</c:if>
                                	</c:forEach>
                                    
                                </select>
                            </td>
                            <td><input class="common-text" placeholder="关键字" name="keywords" value="${keywords }" id="" type="text"></td>
                            <td><input class="btn btn-primary btn2" name="sub" value="搜索" type="submit"></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <div class="result-wrap">
                <div class="result-title">                    
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th class="tc" width="10%">商家名称</th>
                            <th>联系电话</th>
                            <th>任务概况</th>
                            <th>受理状态</th>
                            <th>提交时间</th>                            
                            <th>受理时间</th>
                            <th>备注</th>
                            <th>操作</th>
                            
                        </tr>
                        <c:forEach items="${tasklist }" var = "task" >
	                        <tr>
	                            <td class="tc">${task.shopName }</td>
	                            <td>${task.shopPhone }</td>
	                            <td>${task.intro }</td>
	                            <td>
	                            	<c:if test="${task.status == 0 }">未受理</c:if>
	                            	<c:if test="${task.status == 1 }">已受理</c:if>
	                            	<c:if test="${task.status == 2 }">拒绝</c:if>
	                            </td>
	                            <td><fmt:formatDate value="${task.publishTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	                            <td><fmt:formatDate value="${task.acceptTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	                            <td>${task.comment}</td>
	                            <td>
	                            	<c:if test="${task.status == 0 }">
	                            	<a class="link-update" onclick="showalert(${task.id}, 1, '受理')" style="cursor: pointer;">受理</a>丨
	                                <a class="link-del" onclick="showalert(${task.id}, 2, '拒绝')" style="cursor: pointer;">拒绝</a>
	                            	</c:if>
<!-- 	                                <a class="link-del" href="#">详情</a> -->
	                            </td>
	                        </tr>
                        </c:forEach>
                    </table>
                    
                    <br/>
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
    </div>
    </form>

</body>
<div class="mask" style="display:none;"></div>
<div class="alert" id="alertdiv" style="display:none;z-index: 201;">
<div style="width: 30px; height: 30px; float: right; cursor: pointer; text-align: center; line-height: 30px; color: red; " onclick="hide()">X</div>
<form id="alertForm" action="./editor_task" enctype="multipart/form-data" method="post">
	<input type="hidden" value="" id="alert_id" name="id"/>
	<input type="hidden" value="" id="alert_status" name="status"/>
	<div style="width: 100%; height: 68px; line-height: 68px; text-align: center; font-size: 25px;margin-top: 60px;" id="alert_title"></div>
	<div style="width: 250px; height: 100px; margin:0 auto;">
	备注：<textarea name="comment" id="alert_comment" style="width: 250px; height: 50px; border: 1px solid #ccc;"></textarea>
	</div>	
	<div style="height: 10px;"></div>
	<div style="width: 105px; height: 40px; line-height: 40px; background: #36aa47; color: #fff;text-align: center; margin: 0 auto;cursor: pointer;" onclick="submitform()">提交</div>
</form>
</div>
</html>