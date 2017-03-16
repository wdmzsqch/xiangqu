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
<script type="text/javascript" src="js/libs/modernizr.min.js"></script>
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
<script src="./js/jquery-1.11.3.min.js"></script>
<script src="./js/page.js"></script>
<script type="text/javascript">
	function showsendOrder(orderid){
		$(".mask").show();
    	$(".alert").css("top",($(".mask").height()-294)/2);
    	$(".alert").css("left",($(".mask").width()-250)/2);
    	$("#sendorder").val(orderid);
    	$(".alert").show();
	}
	
	function articlesend(type){
		$("#sendtype").val(type);
		if(type==1){
			$(".unusetype").removeClass("unusetype");
			$(".typeleft").addClass("unusetype");
			$("#noarticle").hide();
			$("#article").show();
		}else{
			$(".unusetype").removeClass("unusetype");
			$(".typeright").addClass("unusetype");
			$("#article").hide();
			$("#noarticle").show();
		}
	}
	
	function sendOrder(){
		var sendtype = $("#sendtype").val();
		if(sendtype==1){
			var courierNum = $("#courierNum").val();
			var express = $("#express").val();
			if(courierNum==''||express==''){
				alert("请填写物流单号和物流信息!");
				return;
			}
		}
		$.ajax({
		url:'./masendorder',
		type:'post',
		data:$("#sendForm").serialize(),
		success:function(msg){
			var code = msg.code;
			if(code==1){
				alert("发货成功!");
				window.location.reload();
			}else{
				alert(msg.message);
			}
		}
		})
	}
	
	function cancelsendOrder(){
		articlesend(1);
		$("#sendorder").val("");
		$(".mask").hide();
		$(".alert").hide();
		$("#courierNum").val("");
		$("#express").val("");
	}
</script>
<style type="text/css">
.typediv {
    padding-top: 10px;
    padding-bottom: 10px;
    margin: auto;
    height: 25px;
    line-height: 25px;
    width: 178px;
}
.typebtn {
    float: left;
    width: 88px;
    background: #ffffff;
    font-size: 13px;
    font-family: '华文细黑';
    text-align: center;
    color: #000000;
}

.typeright {
    border-radius: 0px 4px 4px 0px;
    border-top: 1px solid #000000;
    border-bottom: 1px solid #000000;
    border-right: 1px solid #000000;
}

.typeleft {
    border-radius: 4px 0px 0px 4px;
    border-top: 1px solid #000000;
    border-bottom: 1px solid #000000;
    border-left: 1px solid #000000;
}

.unusetype {
    background: #000000;
    color: #ffffff;
}

</style>
</head>
<body>
	<jsp:include page="./sidebar.jsp"></jsp:include>
	<div class="main-wrap">
		<div class="crumb-wrap">
			<div class="crumb-list">
				<i class="icon-font"></i><a href="./home_page">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">订单管理</span>
			</div>
		</div>

		<form name="pageForm" id="pageForm" method="post" action="./order_list">
			<div class="search-wrap">
				<div class="search-content">
					<table class="search-tab">
						<tr>
							<th width="120">订单搜索:</th>
							<td><select name="searchtype">
									<option value="1" <c:if test="${searchtype==1}">selected</c:if>>订单编号</option>
									<option value="2" <c:if test="${searchtype==2}">selected</c:if>>收件人姓名</option>
									<option value="3" <c:if test="${searchtype==3}">selected</c:if>>收件人手机号</option>
									<option value="4" <c:if test="${searchtype==4}">selected</c:if>>用户名</option>
							</select></td>
							<td><select name="storeId">
								<option value="">全部</option>
								<c:forEach items="${shoplist }" var="shop">
									<option value="${shop.id }" <c:if test="${storeId==shop.id}">selected</c:if>>${shop.companyName }</option>
								</c:forEach>
							</select></td>
							<td><input class="common-text" placeholder="关键字" name="keywords" value="${keywords}" id="" type="text"></td>
							<td><input class="common-text" placeholder="渠道" name="channel" value="${channel}" id="" type="text"></td>
							<td><input class="btn btn-primary btn2" name="sub" value="搜索" type="submit"></td>


						</tr>
					</table>
					<table class="search-tab">
						<tr>
							<th width="120">订单筛选</th>
							<td><input value="${starttime}" name="starttime" id="starttime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" /></td>
							<td>——</td>
							<td><input value="${endtime}" name="endtime" id="endtime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" /></td>
							<td><input class="btn btn-primary btn2" name="sub" value="确认" type="submit"></td>



						</tr>
					</table>
					<div style="position: absolute; right: 40px; top: 115px;">当前总数：${perCount}</div>
				</div>
			</div>




			<div class="result-wrap">

				<div class="result-content">
					<table class="result-tab" width="100%">


						<tr>
							<th class="tc" style="min-width: 110px;">订单编号</th>
							<th>订单产品</th>
							<th>用户名</th>
							<th>收件人姓名</th>
							<th>收件人联系方式</th>
							<th>订单总价</th>
							<th>订单状态</th>
							<th>商家</th>
							<th>操作</th>

						</tr>
						<c:forEach items="${orderList}" var="order">
							<tr>
								<td class="tc">${order.orderNo}</td>
								<td>${order.goodsList[0].name}</td>
								<td>${order.userName}</td>
								<td>${order.consignee}</td>
								<td>${order.phone}</td>
								<td>${order.totalPrice}</td>
								<td><c:if test="${order.state==1}">待付款</c:if> <c:if test="${order.state==2}">待发货</c:if> <c:if test="${order.state==3}">已发货</c:if> <c:if test="${order.state==4}">已完成</c:if></td>
								<td>${order.storeName}</td>
								<td><a class="link-update" href="order_detail?order_id=${order.id}">查看详情</a>
									<br/>
									<c:if test="${order.state==2}"><a class="link-update" onclick="showsendOrder(${order.id})">发货</a></c:if>
								</td>
							</tr>
						</c:forEach>

					</table>

					<input id="pageIndex" name="pageIndex" value="${pageIndex}" type="hidden"> <br />
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
	<div class="alert" style="position: fixed; display: none; width: 250px; height: 294px; background: #fff; border-radius: 4px; z-index: 101;">
	<form id="sendForm">
		<input id="sendorder" value="" type="hidden" name="orderid">
		<input id="sendtype" value="1" type="hidden">
		<div class="typediv">
			<div class="typebtn typeleft unusetype" onclick="articlesend(1)">物流发货</div>
			<div class="typebtn typeright " onclick="articlesend(2)">无需物流发货</div>
		</div>
		<hr style="height:1px;border:0;border-bottom:1px solid #cccccc;">
		<div style="text-align: center; height: 200px; width: 100%; display: block;" id="article">
			<div><input id="courierNum" name="courierNum" style="padding-left:5px;border-radius:4px;width:178px;line-height:20px;margin:0 auto; margin-top:10px;margin-bottom:10px;border:1px solid #cccccc;" placeholder="请输入快递单号"></div>
			<div><input id="express" name="express" style="padding-left:5px;border-radius:4px;width:178px;line-height:20px;margin:0 auto;border:1px solid #cccccc;" placeholder="请输入物流公司"></div>
		</div>
		<div style="text-align: center; height: 200px; width: 100%; display: none;" id="noarticle">
			<div style="width:100%;padding-top:90px;text-align:center;line-height:10px;line-height: 13px;font-size: 13px;color:#cccccc">
			无需填写物流信息
			<br>
			请点击直接发货
			</div>
		</div>
		<div style="width:100%;border-top:1px solid #cccccc;">
			<div style="float:left;width:124px;height:30px;line-height:30px;text-align:center;border-right:1px solid #cccccc;color:rgb(0,143,203);font-weight:bold;" onclick="cancelsendOrder()">取消</div>
			<div style="float:left;width:125px;height:30px;line-height:30px;text-align:center;color:rgb(0,143,203);font-weight:bold;" onclick="sendOrder()">发货</div>
		</div>
		</form>
	</div>
</body>
</html>