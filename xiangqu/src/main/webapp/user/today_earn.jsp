<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, user-scalable=no">
<title>我收益</title>
<link rel="stylesheet" type="text/css" href="css/user.css" >
<link rel="stylesheet" type="text/css" href="css/common.css" >
<script type="text/javascript" src="./js/jquery-1.11.3.min.js"></script>
<script src="js/jquery.lazyload.js?v=1.9.1"></script>
<script type="text/javascript">
var divtopheight;
$(function(){
// 	divtopheight = document.getElementById("steal_top").getBoundingClientRect().top;
	 $("img.lazy").lazyload({
		 effect: "fadeIn",
	});
	 if('${isapp}' != 1){
		 setTabSelected(2);
	 }
});
// window.onscroll =function (){
//     var bodyTopHeight  =document.body.scrollTop;
   
//     if(bodyTopHeight==0)
//     {
//         bodyTopHeight = document.documentElement.scrollTop;
//     }
   
//       //网页正文部分上边距
//      if(bodyTopHeight>=divtopheight){
       
//         $("#steal_top").attr("style", "position:fixed;top:0px;background-color: #fff; z-index:999999;  width: 100%;");
        
//      }else{
//          $("#steal_top").attr("style", "width: 100%; background: #fff; border-top: 1px solid #e8e8e8;  margin-top: 8px;");
//      };

//  };

function changeToM(){
	$('#mtext').removeClass('user_earnings_cont_item_wxz');
	$('#mtext').addClass('user_earnings_cont_item_xz');
	$('#ctext').removeClass('user_earnings_cont_item_xz');
	$('#ctext').addClass('user_earnings_cont_item_wxz');
	$('#stext').removeClass('user_earnings_cont_item_xz');
	$('#stext').addClass('user_earnings_cont_item_wxz');
	$('#etext').removeClass('user_earnings_cont_item_xz');
	$('#etext').addClass('user_earnings_cont_item_wxz');
	$('#mission').css("display","block"); 
	$('#coupon').css("display","none"); 
	$('#shopgoods').css("display","none"); 
	$('#event').css("display","none"); 
}

function changeToS(){
	$('#mtext').removeClass('user_earnings_cont_item_xz');
	$('#mtext').addClass('user_earnings_cont_item_wxz');
	$('#ctext').removeClass('user_earnings_cont_item_xz');
	$('#ctext').addClass('user_earnings_cont_item_wxz');
	$('#stext').removeClass('user_earnings_cont_item_wxz');
	$('#stext').addClass('user_earnings_cont_item_xz');
	$('#etext').removeClass('user_earnings_cont_item_xz');
	$('#etext').addClass('user_earnings_cont_item_wxz');
	$('#mission').css("display","none"); 
	$('#coupon').css("display","none"); 
	$('#shopgoods').css("display","block"); 
	$('#event').css("display","none");  
}

function changeToC(){
	$('#ctext').removeClass('user_earnings_cont_item_wxz');
	$('#ctext').addClass('user_earnings_cont_item_xz');
	$('#mtext').removeClass('user_earnings_cont_item_xz');
	$('#mtext').addClass('user_earnings_cont_item_wxz');
	$('#stext').removeClass('user_earnings_cont_item_xz');
	$('#stext').addClass('user_earnings_cont_item_wxz');
	$('#etext').removeClass('user_earnings_cont_item_xz');
	$('#etext').addClass('user_earnings_cont_item_wxz');
	$('#mission').css("display","none"); 
	$('#coupon').css("display","block"); 
	$('#shopgoods').css("display","none"); 
	$('#event').css("display","none"); 
}

function changeToE(){
	$('#ctext').removeClass('user_earnings_cont_item_xz');
	$('#ctext').addClass('user_earnings_cont_item_wxz');
	$('#mtext').removeClass('user_earnings_cont_item_xz');
	$('#mtext').addClass('user_earnings_cont_item_wxz');
	$('#stext').removeClass('user_earnings_cont_item_xz');
	$('#stext').addClass('user_earnings_cont_item_wxz');
	$('#etext').removeClass('user_earnings_cont_item_wxz');
	$('#etext').addClass('user_earnings_cont_item_xz');
	$('#mission').css("display","none"); 
	$('#coupon').css("display","none"); 
	$('#shopgoods').css("display","none"); 
	$('#event').css("display","block"); 
}

function myincom(){
	window.location.href = "./myincome";
}

function changeToallRecord(){
	$("#missionspan").css({"color":"#d0d0d0","border":"0"});
	$("#recordspan").css({"border-bottom":"1px solid #00d1bc", "color":"#00d1bc"});
	$("#allrecord").show();
	$("#mission").hide();
}

function changeTomission(){
	$("#recordspan").css({"color":"#d0d0d0","border":"0"});
	$("#missionspan").css({"border-bottom":"1px solid #00d1bc", "color":"#00d1bc"});
	$("#mission").show();
	$("#allrecord").hide();
}
</script>
</head>

<body class="bj01">
<%-- <jsp:include page="./top_noback.jsp"/> --%>
<!-- <div class="user_earnings_column_w"> -->
<!--   <div class=" user_earnings_column"> -->
<!--     <div class="float_l user_earnings_column_l_xz" onclick="javascript:window.location.replace('./today_earn');">今日明细</div> -->
<!--     <div class="float_l user_earnings_column_r_wxz" onclick="javascript:window.location.replace('./month_earn');">月总明细</div> -->
<!--   </div> -->
<!-- </div> -->
<!-- <div class="user_earnings_time_w">
  <div class="user_earnings_time">
    <div class="user_earnings_time_l"><a href="#"><img src="images/user_earnings_ico01.png"  /></a></div>
    <div class="user_earnings_time_m">2015-09</div>
    <div class="user_earnings_time_r"><a href="#"><img src="images/user_earnings_ico02.png"  /></a></div>
  </div>
</div> -->
<div style="width:100%; height: 113px; width: 100%; background: #00d1bc; color: #fff;">
<div style="width: 100%; line-height: 44px; font-size: 12px; text-align: right;" onclick="myincom()"><span style="margin-right: 12px;">明细</span></div>
<div style="font-size: 34px; margin-left: 18px;"><fmt:formatNumber type="number" value="${income}" maxFractionDigits="0"/></div>
<div style="font-size: 13px; margin-left: 18px; line-height: 26px;">今日收益(积分)</div>
</div>
<div style="width:100%; border-top: 1px solid #e8e8e8; height:auto;  background: #fff;">
<div style="width: 92%; height: auto; overflow: hidden; margin: 0 auto;">
<div class="user_earnings_data_line" style="border: 0px;">
  <div class="user_earnings_data_line_l" style="border: 0px;">
    <div class="color01 overflowOmit" style="line-height: 40px; font-size: 19px;"><fmt:formatNumber type="number" value="${allincome}" maxFractionDigits="0"/></div>
  	<div class="color02" style="margin-top: -11px; font-size: 11px;">总收益（积分）</div>
<!--   	<div style="border-bottom: 1px solid #e8e8e8; width: 81%; height: 1px; margin: 0 auto; "></div> -->
  </div>
  <div style="float: left; height: 30px; margin-top: 15px; border-left: 1px solid #e8e8e8; "></div>
  <div class="user_earnings_data_line_r">
  	<div class="color01 overflowOmit" style="line-height: 40px; font-size: 19px;"><fmt:formatNumber type="number" value="${balance}" maxFractionDigits="0"/></div>
  	<div class="color02" style="margin-top: -11px; font-size: 11px;">可用积分</div>
<!--   	<div style="border-bottom: 1px solid #e8e8e8; width: 81%; height: 1px; margin: 0 auto; "></div> -->
  </div>
</div>
<!-- <div class="user_earnings_data_line"> -->
<!--   <div class="user_earnings_data_line_l">今日任务数<br /> -->
<%--     <span class="color01">${missionCount}</span></div> --%>
<!--   <div class="user_earnings_data_line_r">今日点击量<br /> -->
<%--     <span class="color01">${missionPV}</span></div> --%>
<!-- </div> -->
<!-- <div class="user_earnings_data_line" style="border: 0px;"> -->
<!--   <div class="user_earnings_data_line_l" style="border: 0px;"> -->
<%--     <div class="color03 overflowOmit" style="line-height: 40px; font-size: 19px;">${goodsCount}</div> --%>
<!--   	<div class="color02" style="margin-top: -11px; font-size: 11px;">今日商品数</div> -->
<!--   </div> -->
<!--   <div style="float: left; height: 30px; margin-top: 15px; border-left: 1px solid #e8e8e8; "></div> -->
<!--   <div class="user_earnings_data_line_r"> -->
<%--   	<div class="color03 overflowOmit" style="line-height: 40px; font-size: 19px;">${goodsPurchase}</div> --%>
<!--   	<div class="color02" style="margin-top: -11px; font-size: 11px;">今日下单量</div> -->
<!--   </div> -->
<!-- </div> -->
</div>
</div>
<div style="border-top:#e8e8e8 1px solid;width:100%; height:1px;"></div>

<div style="background: #fff; width: 100%; height: auto; margin-top: 8px; padding-bottom: 50px;border-top: 1px solid #e8e8e8;">
	<!-- <div style="width: 92%; height: auto; border-bottom: 1px solid #e8e8e8; margin: 0 auto; line-height: 38px; font-size: 12px;"></div>
	<div style="width: 92%; height: 100px;"></div> -->
<div style="width: 92%; height: 38px; border-bottom: 1px solid #e8e8e8; margin: 0 auto; line-height: 38px; font-size: 12px;">
	<div style="width: 120px; height: 100%; margin:0 auto; cursor: pointer;">
	<div style="float: left; height: 100%; border-bottom: 1px solid #00d1bc; color: #00d1bc;" id="recordspan" onclick="changeToallRecord()">积分动态</div>
	<div style="float: left; height: 100%; color: #d0d0d0; margin-left: 4px;" id="missionspan" onclick="changeTomission()">今日任务</div>
	</div>
</div>
<div id="allrecord">
	<c:forEach items="${allRecord }" var="p">
	<c:if test="${p.type == 1 or p.type == 2 or p.type ==3 or p.type == 7 or p.type == 8}">
		<div style="width: 92%; height: auto; padding-top: 18px; margin-left: 4%; clear: both;">
			<div style="width: 11%; height: 23px; float: left;">
				<img src="<c:if test='${p.type == 1}'>imgs/task_icon_task.png</c:if>
				<c:if test='${p.type == 2}'>imgs/task_icon_goods.png</c:if>
				<c:if test='${p.type == 3}'>imgs/task_icon_buy.png</c:if>
				<c:if test='${p.type == 7}'>imgs/task_icon_lucky.png</c:if>
				<c:if test='${p.type == 8}'>imgs/task_icon_invitation.png</c:if>" style="width: 23px; height: 23px; border-radius: 10px;">
			</div>
			<div style="width: 88%; hegiht: auto; overflow: hidden; font-size: 12px;">
				<c:if test="${p.type == 1}">
				${p.username }&nbsp;
				<font color="bfbfbf">在</font>&nbsp;<fmt:formatDate value="${p.time }" pattern="yyyy-MM-dd"/>&nbsp;
				<font color="bfbfbf">分享任务被点击获得</font>
				&nbsp;<font color="ff5c4b">「<fmt:formatNumber type="number" value="${p.income }" maxFractionDigits="0"/>个积分收益」</font>
				</c:if>	
				<c:if test="${p.type == 2}">
				${p.username }&nbsp;
				<font color="bfbfbf">在</font>&nbsp;<fmt:formatDate value="${p.time }" pattern="yyyy-MM-dd"/>&nbsp;
				<font color="bfbfbf">分享商品被点击，获得</font>
				&nbsp;<font color="ff5c4b">「<fmt:formatNumber type="number" value="${p.income }" maxFractionDigits="0"/>个积分收益」</font>
				</c:if>	
				<c:if test="${p.type == 3}">
				${p.username }&nbsp;
				<font color="bfbfbf">在</font>&nbsp;<fmt:formatDate value="${p.time }" pattern="yyyy-MM-dd"/>&nbsp;
				<font color="bfbfbf"><c:if test="${p.goodstype == 2}">成功兑换了</c:if><c:if test="${p.goodstype == 1}">成功购买了</c:if></font>
				&nbsp;<font color="ff5c4b">「${p.relativename }」</font>
				</c:if>	
				<c:if test="${p.type == 7}">
				${p.username }&nbsp;
<%-- 				<font color="bfbfbf">在</font>&nbsp;<fmt:formatDate value="${p.time }" pattern="yyyy-MM-dd HH"/>时&nbsp; --%>
				<font color="bfbfbf">参与每日抽奖，抽到了</font>
				&nbsp;<font color="ff5c4b">「
				<c:if test="${p.income == 10}">安慰奖</c:if>
				<c:if test="${p.income == 50}">三等奖</c:if>
				<c:if test="${p.income == 100}">二等奖</c:if>
				<c:if test="${p.income == 1000}">一等奖</c:if>
				<c:if test="${p.income == 5000}"> 特等奖</c:if>
				<fmt:formatNumber type="number" value="${p.income }" maxFractionDigits="0"/>个积分」
				</font>
				</c:if>	
				<c:if test="${p.type == 8}">
				${p.username }&nbsp;
				<font color="bfbfbf">在</font>&nbsp;<fmt:formatDate value="${p.time }" pattern="yyyy-MM-dd"/>&nbsp;
				<font color="bfbfbf">成功邀请</font>&nbsp;${p.relativename}&nbsp;<font color="bfbfbf">，获得</font>
				&nbsp;<font color="ff5c4b">「<fmt:formatNumber type="number" value="${p.income }" maxFractionDigits="0"/>个积分收益」</font>
				</c:if>	
			</div>
		</div>
	</c:if>
	</c:forEach>
	</div>
	<div id="mission" style="background: #fff; display: none;">
	<c:forEach items="${missionList}" var="mission">
	<div class="user_earnings_cont_line">
	  <div class="user_earnings_cont_line_l"><img src="${fileurl}${mission.pic}"  /></div>
	  <div class="user_earnings_cont_line_r">
	    <div class="user_earnings_cont_line_name">${mission.name}</div>
	    <div><span class="color01">收益：<fmt:formatNumber type="number" value="${mission.allIncome}" maxFractionDigits="0"/>积分</span></div>
	  </div>
	</div>
	</c:forEach>
	</div>
</div>

<!-- <div style="width: 100%; background: #fff; border-top: 1px solid #e8e8e8;  margin-top: 8px;" id="steal_top"> -->
<!-- <div class="user_earnings_cont_item"> -->
<!--   <div id="mtext" class="user_earnings_cont_item_xz" style="margin-left: 20%;" onclick="changeToM()">任务</div> -->
<!--   <div id="stext" class="user_earnings_cont_item_wxz" onclick="changeToS()">商品</div> -->
<!--   <div id="ctext" class="user_earnings_cont_item_wxz" onclick="changeToC()">优惠券</div> -->
<!--   <div id="etext" class="user_earnings_cont_item_wxz" onclick="changeToE()">活动</div> -->
<!-- </div> -->
<!-- </div> -->
<!-- <div id="mission" style="background: #fff;"> -->
<%-- <c:forEach items="${missionList}" var="mission"> --%>
<!-- <div class="user_earnings_cont_line"> -->
<%--   <div class="user_earnings_cont_line_l"><img src="${fileurl}${mission.pic}"  /></div> --%>
<!--   <div class="user_earnings_cont_line_r"> -->
<%--     <div class="user_earnings_cont_line_name">${mission.name}</div> --%>
<%--     <div><span class="color01">收益：<fmt:formatNumber type="number" value="${mission.allIncome}" maxFractionDigits="0"/>积分</span></div> --%>
<!--   </div> -->
<!-- </div> -->
<%-- </c:forEach> --%>
<!-- </div> -->


<!-- <div id="shopgoods" style="display: none; background: #fff;"> -->
<%-- <c:forEach items="${goodsList}" var="goods"> --%>
<!-- <div class="user_earnings_cont_line"> -->
<%--   <div class="user_earnings_cont_line_l"><img src="${fileurl}${goods.mianPic}"  /></div> --%>
<!--   <div class="user_earnings_cont_line_r"> -->
<%--     <div class="user_earnings_cont_line_name overflowOmit">${goods.name}</div> --%>
<%--     <div><span class="color01">收益：<fmt:formatNumber type="number" value="${goods.allIncome}" maxFractionDigits="0"/>积分</span></div> --%>
<!--   </div> -->
<!-- </div> -->
<%-- </c:forEach> --%>
<!-- </div> -->

<!-- <div id="coupon" style="display: none; background: #fff;"> -->
<%-- <c:forEach items="${couponList}" var="coupon"> --%>
<!-- <div class="user_earnings_cont_line"> -->
<!--   <div class="user_earnings_cont_line_r"> -->
<%--     <div class="user_earnings_cont_line_name overflowOmit">${coupon.title}</div> --%>
<%--     <div><span class="color01">收益：<fmt:formatNumber type="number" value="${coupon.allIncome}" maxFractionDigits="0"/>积分</span></div> --%>
<!--   </div> -->
<!-- </div> -->
<%-- </c:forEach> --%>
<!-- </div> -->

<!-- <div id="event" style="display: none; background: #fff;"> -->
<%-- <c:forEach items="${eventList}" var="event"> --%>
<!-- <div class="user_earnings_cont_line"> -->
<%--   <div class="user_earnings_cont_line_l"><img src="${fileurl}${event.pic}"  /></div> --%>
<!--   <div class="user_earnings_cont_line_r"> -->
<%--     <div class="user_earnings_cont_line_name overflowOmit">${event.name}</div> --%>
<%--     <div><span class="color01">收益：<fmt:formatNumber type="number" value="${event.allIncome}" maxFractionDigits="0"/>积分</span></div> --%>
<!--   </div> -->
<!-- </div> -->
<%-- </c:forEach> --%>
<!-- </div> -->


<c:if test="${isapp != 1}">
<jsp:include page="./tab.jsp"/>
</c:if>

<%@ include file="cs.jsp" %>
<%CS cs = new CS(1256812462);cs.setHttpServlet(request,response);
String imgurl = cs.trackPageView();%> 
<img src="<%= imgurl %>" width="0" height="0"  />
</body>
</html>
