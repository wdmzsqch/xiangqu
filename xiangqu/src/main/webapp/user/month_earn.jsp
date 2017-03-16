<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, user-scalable=no">
<title>我收益</title>
<link rel="stylesheet" type="text/css" href="css/user.css" >
<link rel="stylesheet" type="text/css" href="css/common.css" />
<script type="text/javascript" src="./js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">

var divtopheight;
$(function(){
	divtopheight = document.getElementById("steal_top").getBoundingClientRect().top;
});
window.onscroll =function (){
    var bodyTopHeight  =document.body.scrollTop;
   
    if(bodyTopHeight==0)
    {
        bodyTopHeight = document.documentElement.scrollTop;
    }
   
      //网页正文部分上边距
     if(bodyTopHeight>=divtopheight){
       
        $("#steal_top").attr("style", "position:fixed;top:0px;background-color: #fff; z-index:999999;  width: 100%;");
        
     }else{
         $("#steal_top").attr("style", "width: 100%; background: #fff; border-top: 1px solid #e8e8e8;  margin-top: 8px;");
     };

 };
 
$(document).ready(function(){
	setTabSelected(2);
});

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

function prevMonth(){
	var date = $('#mMonth').val();
	var month = date.substr(5,2);
	var monthNum = parseInt(month)-1;
	if(monthNum< 1){
		monthNum = 12;
		var year = date.substr(0,4);
		var yearNum = parseInt(year)-1;
		date = yearNum+"-"+monthNum;
	}else{
		if(monthNum<10){
			monthNum = "0"+monthNum;
		}
		date = date.substr(0,5)+monthNum;
	}
	$('#mMonth').val(date);
	$('#earn_form').submit();
	
}

function nextMonth(){
	var date = $('#mMonth').val();
	var month = date.substr(5,2);
	var monthNum = parseInt(month)+1;
	if(monthNum>12){
		monthNum = "01";
		var year = date.substr(0,4);
		var yearNum = parseInt(year)+1;
		date = yearNum+"-"+monthNum;
	}else{
		if(monthNum<10){
			monthNum = "0"+monthNum;
		}
		date = date.substr(0,5)+monthNum;
	}
	$('#mMonth').val(date);
	$('#earn_form').submit();
}
</script>
</head>

<body class="bj01">
<%-- <jsp:include page="./top_noback.jsp"/> --%>
<div class="user_earnings_column_w">
  <div class=" user_earnings_column">
    <div class="float_l user_earnings_column_l_wxz" onclick="javascript:window.location.replace('./today_earn');">今日明细</div>
    <div class="float_l user_earnings_column_r_xz" onclick="javascript:window.location.replace('./month_earn');">月总明细</div>
  </div>
</div>
<div class="user_earnings_time_w" style=" border-top: 1px solid #e8e8e8;">
  <div class="user_earnings_time">
    <div class="user_earnings_time_l" onclick="prevMonth()"><img src="imgs/user_earnings_ico03.png" width="9px" height="10px;"  /></div>
    <div class="user_earnings_time_m">${month}</div>
    <form id="earn_form" action="./month_earn" method="post"><input type="hidden" id="mMonth" name="month" value="${month}"></form>
    <div class="user_earnings_time_r" onclick="nextMonth()"><img src="imgs/user_earnings_ico04.png" width="9px" height="10px;" /></div>
  </div>
</div>
<div style="width:100%; height:auto; width: 100%; background: #fff;">
<div style="width: 92%; height: auto; overflow: hidden; margin: 0 auto;">
<div class="user_earnings_data_line">
  <div class="user_earnings_data_line_l">
 	<div class="color01 overflowOmit" style="line-height: 40px; font-size: 19px;"><fmt:formatNumber type="number" value="${income}" maxFractionDigits="0"/></div>
  	<div class="color02" style="margin-top: -11px; font-size: 11px;">本月总收益（积分）</div>
<!--   	<div style="border-bottom: 1px solid #e8e8e8; width: 81%; height: 1px; margin: 0 auto; "></div>  -->
  </div>
  <div style="float: left; height: 30px; margin-top: 15px; border-left: 1px solid #e8e8e8; "></div>
  <div class="user_earnings_data_line_r">
 	<div class="color01 overflowOmit" style="line-height: 40px; font-size: 19px;"><fmt:formatNumber type="number" value="${balance}" maxFractionDigits="0"/></div>
  	<div class="color02" style="margin-top: -11px; font-size: 11px;"> 可用余额（积分）</div> 
<!--   	<div style="border-bottom: 1px solid #e8e8e8; width: 81%; height: 1px; margin: 0 auto; "></div>  -->
  </div>
</div>
<!-- <div class="user_earnings_data_line"> -->
<!--   <div class="user_earnings_data_line_l">本月任务数<br /> -->
<%--     <span class="color01">${missionCount}</span></div> --%>
<!--   <div class="user_earnings_data_line_r">本月点击量<br /> -->
<%--     <span class="color01">${missionPV}</span></div> --%>
<!-- </div> -->
<!-- <div class="user_earnings_data_line"> -->
<!--   <div class="user_earnings_data_line_l"> -->
<%--  	<div class="color03 overflowOmit" style="line-height: 40px; font-size: 19px;">${goodsCount}</div> --%>
<!--   	<div class="color02" style="margin-top: -11px; font-size: 11px;">本月商品数</div> -->
<!--   </div> -->
<!--   <div style="float: left; height: 30px; margin-top: 15px; border-left: 1px solid #e8e8e8; "></div> -->
<!--   <div class="user_earnings_data_line_r"> -->
<%--   	<div class="color03 overflowOmit" style="line-height: 40px; font-size: 19px;">${goodsPurchase}</div> --%>
<!--   	<div class="color02" style="margin-top: -11px; font-size: 11px;"> 本月下单量</div> -->
<!--   </div> -->
<!-- </div> -->
</div>
</div>
<div style="border-top:#e8e8e8 1px solid;width:100%; height:1px;"></div>

<div style="width: 100%; background: #fff; border-top: 1px solid #e8e8e8;  margin-top: 8px;" id="steal_top">
<div class="user_earnings_cont_item">
  <div id="mtext" class="user_earnings_cont_item_xz" onclick="changeToM()" style="margin-left: 20%;">任务</div>
  <div id="stext" class="user_earnings_cont_item_wxz" onclick="changeToS()">商品</div>
  <div id="ctext" class="user_earnings_cont_item_wxz" onclick="changeToC()">优惠券</div>
  <div id="etext" class="user_earnings_cont_item_wxz" onclick="changeToE()">活动</div>
</div>
</div>
<div id="mission">
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


<div id="shopgoods" style="display: none;">
<!-- <div style="border-top:#ccc 1px solid;width:100%; height:1px; margin-top: 2px;"></div> -->
<c:forEach items="${goodsList}" var="goods">
<div class="user_earnings_cont_line">
  <div class="user_earnings_cont_line_l"><img src="${fileurl}${goods.mianPic}"  /></div>
  <div class="user_earnings_cont_line_r">
    <div class="user_earnings_cont_line_name">${goods.name}</div>
    <div><span class="color01">收益：<fmt:formatNumber type="number" value="${goods.allIncome}" maxFractionDigits="0"/>积分</span></div>
  </div>
</div>
</c:forEach>
</div>

<div id="coupon" style="display: none; background: #fff;">
<!-- <div style="border-top:#ccc 1px solid;width:100%; height:1px; margin-top: 2px;"></div> -->
<c:forEach items="${couponList}" var="coupon">
<div class="user_earnings_cont_line">
  <div class="user_earnings_cont_line_r">
    <div class="user_earnings_cont_line_name overflowOmit">${coupon.title}</div>
    <div><span class="color01">收益：<fmt:formatNumber type="number" value="${coupon.allIncome}" maxFractionDigits="0"/>积分</span></div>
  </div>
</div>
</c:forEach>
</div>



<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>



<jsp:include page="./tab.jsp"/>

<%@ include file="cs.jsp" %>
<%CS cs = new CS(1256812462);cs.setHttpServlet(request,response);
String imgurl = cs.trackPageView();%> 
<img src="<%= imgurl %>" width="0" height="0"  />
</body>
</html>
