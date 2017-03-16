<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no">
<title>我商城</title>
<script src="./js/jquery-1.11.3.min.js"></script>
<script src="./js/modernizr-custom-v2.7.1.min.js"></script>
<script src="./js/jquery-finger-v0.1.0.min.js"></script>
<script src="./js/flickerplate.min.js"></script>
<link rel="stylesheet" type="text/css" href="./css/flickerplate.css" />
<link rel="stylesheet" type="text/css" href="./css/common.css" />
<link rel="stylesheet" type="text/css" href="./css/mall.css" />
<link rel="stylesheet" href="./iscroll/iscroll2.css">
<script type="text/javascript" src="./iscroll/iscroll.js"></script>
<script src="js/jquery.lazyload.js?v=1.9.1"></script>
<script type="text/javascript">
$(document).ready(function(){
	if('${isapp}' != 1){
		setTabSelected(3);
	}
});

$(function(){
	var showType = $("#showType").val();
	if(showType == 0){
		$("#sortTitlediv").show();
		$(".sorttypesearch").hide();
	}else{
		$("#sortTitlediv").hide();
		$(".sorttypesearch").show();
	}
});

function showSearch(){
	$("#sortTitlediv").hide();
	$(".sorttypesearch").show();
}

function hideSearch(){
	$("#sortTitlediv").show();
	$(".sorttypesearch").hide();
}


$(function(){
	$("img.lazy").lazyload({
		 effect: "fadeIn",
		 threshold: 200,
		 placeholder : "imgs/load_default.jpg"
	});
	 $("li").lazyload({effect: "fadeIn"});
    $('.flicker-example').flicker({
        arrows: false,
        arrows_constraint: true,
        auto_flick: false,
        auto_flick_delay: 3,
        block_text: false,
        dot_navigation: true,
        dot_alignment: 'center',
        flick_animation: 'jquery-slide',
        flick_position: 1,
        inner_width: false,
        theme: 'light'
    });
    
    var categoryid = $("#categoryId").val();
    $("#li_"+categoryid).addClass("select");
     var goodstype = $("#goodstype").val();
     if(goodstype==1){
    	$(".typeleft").addClass("unusetype");
     }else{
    	 $(".typeright").addClass("unusetype");
     }
     var sort = $("#sort").val();
     $("#sesort").val(sort);
});

function changeCote(cotegoryid){
	var goodstype = $("#goodstype").val();
	var sort = $("#sort").val();
	window.location.replace("./mall?categoryId="+cotegoryid+"&goodstype="+goodstype+"&sort="+sort+"&showType="+1);
}

function changeType(type){
	var categoryId = $("#categoryId").val();
	var sort = $("#sort").val();
	window.location.replace("./mall?categoryId="+categoryId+"&goodstype="+type+"&sort="+sort+"&showType="+0+"&isapp="+'${isapp}');
}

function changeSort(){
	var categoryId = $("#categoryId").val();
	var goodstype = $("#goodstype").val();
	var sort = $("#sesort").val();
	window.location.replace("./mall?categoryId="+categoryId+"&goodstype="+goodstype+"&sort="+sort+"&showType="+0+"&isapp="+'${isapp}');
}

function goods(goodsid){
	var goodstype = $("#goodstype").val();
	var shareuserid = $("#shareuserid").val();
	window.location.href = "./goods?goodsid="+goodsid+"&goodstype="+goodstype+"&shareuserid="+shareuserid;
}

function gohref(url){
	window.location.href=url;
}

</script>
</head>
<body>

	<%-- <jsp:include page="./top_noback.jsp"/> --%>
	<div class="flicker-example">
		<ul>
			<c:forEach items="${adlist}" var="p">
				<li data-background="${fileRootUrl }${p.pic}" onclick='gohref("${p.url}")' style="background-image: url('${fileRootUrl }${p.pic}');"></li>
			</c:forEach>
		</ul>
	</div>
	
	<div id="sortTitlediv" style="height: 32px;">
		<div style="width: 100%; margin-top: 8px;"></div>
		<div class="typediv" style="width: 140px; float:left; margin-left: 2%;">
			<div class="typebtn typeleft " onclick="changeType(2)">兑换商品</div>
			<div style="float: left; width: 10px; height: 25px;"></div>
			<div class="typebtn typeright " onclick="changeType(1)">购买商品</div>
		</div>
		<div style="float: right; width: 20px; height: 13px; margin-top: 4px; margin-right: 2%;" onclick="showSearch()">
			<img src="imgs/more_icon.png" style="width: 20px; height: 13px;">
		</div>
		<div style="float: right; color: #e8e8e8; margin-left: 10px; margin-right: 10px; margin-top: 2px;">|</div>
		<div class="sort" style="float:right;">
			<%-- <c:if test="${goodstype==1 }"> --%>
			<select id="sesort" onchange="changeSort()" style="width: 63px; color: #ff5c4b; font-size: 13px; height: 28px; -webkit-appearance: none; border: none; float: left;">
				<%-- </c:if> --%>
				<%-- <c:if test="${goodstype==2 }"> --%>
				<!-- <select id="sesort" onchange="changeSort()" style="width: 61px; font-size:13px;height: 28px;-webkit-appearance:none;border:none;float:left;"> -->
				<%-- </c:if> --%>
				<option value="1">综合排序</option>
				<c:if test="${goodstype==1 }">
					<option value="2">价格排序</option>
				</c:if>
				<c:if test="${goodstype==2 }">
					<option value="2">积分排序</option>
				</c:if>
			</select>
			<div class="triangle-down" style="float: left; margin-top: 0px;">
				<%-- <c:if test="${goodstype==1 }"> --%>
				<img src="imgs/sort_icon_2.png" width="12px" height="9px">
				<%-- </c:if> --%>
				<%-- <c:if test="${goodstype==2 }"> --%>
				<!-- <img src="imgs/sort_icon.png" width="12px" height="9px"> -->
				<%-- </c:if> --%>
			</div>
		</div>
		<div style="float: right; color: #e8e8e8; margin-right: 8px; margin-top: 2px;">|</div>
		<div style="width: 100%; margin-top: 10px;"></div>
	</div>
	<div class="sorttypesearch" style="border-top: 1px solid #e8e8e8; display: none;">
		<!-- <div id="wrapper"> -->
		<!--     <div id="scroller"> -->
		<div class="typebanner" id="typebanner">
			<ul>
				<c:forEach items="${cotegorylist}" var="p">
					<li id="li_${p.id }" onclick="changeCote(${p.id})">${p.cotegoryName }</li>
				</c:forEach>
				<img src="imgs/close_icon.png" style="float: right; width: 16px; height: 16px; margin-top: 13px; margin-right: 4%;" onclick="hideSearch()">
			</ul>
		</div>
		<!-- 	</div> -->
		<!-- </div> -->
		<!-- <input type="text" class="search">  -->
	</div>
	<c:forEach items="${goodslist}" var="p">
		<div style="width: 100%; height: 8px; background: #f2f2ef; border-bottom: 1px solid #e8e8e8;"></div>
		<div class="goodsDiv" onclick="goods(${p.id})">
			<img class="mallgoodsimg lazy" data-original="${fileRootUrl }${p.mianPic}" style=" border-radius: 8px; min-height: 100px;">
			<div style="position: absolute; height: 20px; background: #4c4b49; opacity: 0.8; color: #fff; z-index: 200;top: 5px; right: 5px;overflow: hidden; border-radius: 16px; line-height: 20px; font-size: 10px;"class="overflowOmit">
				<div style="float:left; padding-left:10px; padding-right: 10px; text-align: center;" class="overflowOmit">剩余数量&nbsp;${p.stock }</div>
			</div>
			<div class="goodsTitle overflowOmit" style="border-bottom: 1px solid #e8e8e8;">
				<div style="line-height: 30px;">${p.name }</div>
			</div>
			<div class="moneyInfo">
				<div style="float: left;">
					<span class="price"> <font style="font-size: 12px"> <c:if test="${goodstype==1 }">
						￥
						</c:if> <c:if test="${goodstype==2 }">
						所需积分
						</c:if>
											</font> <c:if test="${goodstype==1 }">
						${p.price }
						</c:if> <c:if test="${goodstype==2 }">
							<fmt:formatNumber type="number" value="${p.price }" maxFractionDigits="0" />
						</c:if>
					</span>
					<c:if test="${goodstype==1 }">
						<font class="originPrice"> ￥${p.originPrice } </font>
					</c:if>
<!-- 					<br />  -->
<%-- 					<font class="stock"> 剩余数量<font style="color: #414141;">${p.stock }</font> --%>
<!-- 					</font> -->
				</div>
				<%-- <c:if test="${goodstype==1 }"> --%>
				<!-- <div class="" style="float:right;height:30px;line-height:30px;width:70px;border-radius:16px; border: 1px solid #00d1bc; color: #00d1bc; font-size: 12px; text-align: center; line-height: 30px;">加购物车</div> -->
				<div class=""
					style="float: right; height: 30px; line-height: 30px; width: 70px; border-radius: 16px; color:#fff;background: #00d1bc; font-size: 12px; text-align: center; line-height: 30px; margin-right: 5px;">
					<c:if test="${goodstype==1 }">
					立即购买
					</c:if>
					<c:if test="${goodstype==2 }">
					立即兑换
					</c:if>
				</div>
				<%-- </c:if> --%>
				<%-- <c:if test="${goodstype==2 }"> --%>
				<!-- <div class="" style="float:right;height:30px;line-height:30px;width:70px;border-radius:16px; border: 1px solid #00d1bc; color: #00d1bc; font-size: 12px; text-align: center; line-height: 30px;">加购物车</div> -->
				<%-- </c:if> --%>
			</div>
		</div>
	</c:forEach>
	<div style="clear: both; width: 100%; height: 10px;"></div>
	<input type="hidden" value="${categoryId }" id="categoryId">
	<input type="hidden" value="${goodstype }" id="goodstype">
	<input type="hidden" value="${sort }" id="sort">
	<input type="hidden" value="${shareuserid }" id="shareuserid">
	<input type="hidden" value="${showType }" id="showType">

	<br />
	<br />
	<c:if test="${isapp != 1 }">
	<jsp:include page="./tab.jsp" />
	</c:if>
<%@ include file="cs.jsp" %>
<%CS cs = new CS(1256812462);cs.setHttpServlet(request,response);
String imgurl = cs.trackPageView();%> 
<img src="<%= imgurl %>" width="0" height="0"  />
</body>
</html>