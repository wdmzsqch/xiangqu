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
function getcouponurl(id){
	$(".mask").show();
	$(".alert").css("top",($(".mask").height()-120)/2);
	$(".alert").css("left",($(".mask").width()-400)/2);
	$(".alert").text("${webrooturl}"+"user/coupondetail?coupon_id="+id);
	$(".alert").show();
}

function hide(){
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
				<i class="icon-font"></i><a href="./home_page">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">优惠券管理</span>
			</div>
		</div>
		<form action="./coupon_list" method="post" name="pageForm" id="pageForm">
			<div class="search-wrap">
				<div class="search-content">
					<table class="search-tab">
						<tr>
							<th width="120">优惠券搜索:</th>
							<td><input class="common-text" placeholder="关键字" name="keywords" value="${keywords}" id="" type="text"></td>
							<td>截止时间：<input value="${endtime}" name="endtime" id="endtime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" /></td>
							<td><input class="btn btn-primary btn2" name="sub" value="搜索" type="submit"></td>


						</tr>
					</table>
					<div style="position: absolute; right: 40px; top: 115px;">当前总数：${perCount}</div>
				</div>
			</div>
			<div class="result-wrap">
				<div class="result-title">
					<div class="result-list">
						<input class="btn btn-primary btn2" name="creat_task" value="新建优惠券" type="button" onclick="javascript:location='./coupon_detail'">
					</div>
				</div>
				<div class="result-content">
					<table class="result-tab" width="100%">
						<tr>
							<th class="tc" width="10%">标题</th>
							<th>优惠金额</th>
							<th>满多少使用</th>
							<th>关联商家</th>
							<th>类型</th>
							<th>优惠券码</th>
							<th>截止时间</th>
							<th width="7%">操作</th>
						</tr>
						
						<c:forEach items="${coupon_list}" var="coupon">
							<tr>
								<td class="tc">${coupon.title}</td>
								<td>${coupon.money}</td>
								<td>${coupon.moneyLimit }</td>
								<td>${coupon.storename }</td>
								<td>
								<c:if test="${coupon.type == 1 }">实物券</c:if>
								<c:if test="${coupon.type == 2 }">抵扣券</c:if>
								</td>
								<td>${coupon.code}</td>
								<td><fmt:formatDate value="${coupon.validity}" type="date" pattern="yyyy-MM-dd" /></td>
								<td><a class="link-del" href="./coupon_detail?id=${coupon.id}">查看详情</a>
								<br/>
								<a class="link-del" href="./get_coupon?coupon_id=${coupon.id}">领取名单</a>
								<br/>
									<div style="cursor: pointer; color: #428bca;" onclick="getcouponurl(${coupon.id})">获取优惠券地址</div>
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