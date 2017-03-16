<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, user-scalable=no">
<title>我的</title>
<link rel="stylesheet" type="text/css" href="css/user.css" />
<link rel="stylesheet" type="text/css" href="css/common.css" />
<script type="text/javascript" src="./js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	if('${isapp}' != 1){
		setTabSelected(4);
	}
	if(isWeiXin()){
		$(".exitclass").hide();
	}else{
		$(".exitclass").show();
	}
});

function isWeiXin(){
    var ua = window.navigator.userAgent.toLowerCase();
    if(ua.match(/MicroMessenger/i) == 'micromessenger'){
        return true;
    }else{
        return false;
    }
}	

function sw(index) {
	switch (index) {
case 1:
	window.location.href= './useraddress';return false;
	break;
case 2:
	window.location.href= './message';return false;
	break;
case 3:
	window.location.href= "http://www.xiangqu100.com/about.html";return false;
	break;
case 4:
	window.location.href= './mycoupon';return false;
	break;
case 5:
	window.location.href= "http://www.xiangqu100.com/help.html";return false;
	break;
case 6:
	window.location.href= './myinvited';return false;
	break;
case 7:
	var isapp = '${isapp}';
	window.location.href= './mylottery?isapp='+isapp;return false;
default:
	break;
}
}

function clearsession(){
	$.ajax({
		type: "post",
		url: "./clearsession",
		success: function(data){
			if(data == "success"){
				window.location.reload();
			}
		}
	});
}

</script>
</head>

<body class="bj01">
<%-- <jsp:include page="./top_noback.jsp"/> --%>
<div class="user_center_txbj">

<img src="<c:if test="${fn:contains(user.pic, 'http://')}">${user.pic}</c:if>
<c:if test="${!fn:contains(user.pic, 'http://')}">${fileurl}${user.pic}</c:if>"  style="margin-top: 25px; width: 44px; height:44px; margin-left: 4%; float:left;
border:#fff 1px solid;
border-radius: 40px;
-webkit-border-radius: 40px;
-moz-border-radius: 40px;" />
<a href="./editornikeName?id=${user.id }">
<div style="float:left; margin-left: 10px; color: #fff; width: 40%;" class="overflowOmit">
<span style="width: 80%;">
<c:if test="${not empty user.nickName}">${user.nickName }</c:if>
<c:if test="${empty user.nickName}">
<c:if test="${not empty user.realName}">${user.realName }</c:if>
<c:if test="${empty user.realName}">${user.userName }</c:if>
</c:if>

</span><img src="imgs/editor_name.png" style="width: 6px; height: 12px; margin-left: 5px; margin-top: 40px;">
</div>
</a>
<a href="./edit_user">
<img src="imgs/shezhi.png" style="float:right; width: 40px; height: 28px; margin-top: 33px;">
</a>
</div>

<div style="width: 100%; height: 8px; background: #f2f2ef; border-bottom: 1px solid #e8e8e8;"></div>
<div class="user_center_column_w">
  <div class="user_center_column_l"></div>
  <div class="user_center_column_r">
    <div class="user_center_column_name">
      <div class="float_l"><a href="./order">我的订单</a></div>
   	  <div class="user_center_column_jt"><img src="imgs/user_center_jt.png"  /></div>
      <div class="float_r" ><a href="./order" style="color: #67ddce; font-size: 12px;">查看全部订单</a></div>
    </div>
  </div>
</div>
<div class="user_center_column_dd">
  <div class="user_center_column_dd_item">
    <div style="height: 30px;"><a href="./order?state=1"><img src="imgs/user_center_dd01.png" style="width: 22px; height: 20px; margin-top: 4px;" /></a>
    <c:if test="${ordercount1 > 0}">
    <div style="position: relative; width: 20px; height: 20px; border: 2px solid #ff5c4b; font-size: 10px; 
    color: #ff5c4b; text-align: center; line-height: 15px; border-radius: 15px; top: -30px; left: 51%; background-color: #fff;  overflow: hidden;">${ordercount1}</div>
    </c:if>
    </div>
    <div class="user_center_column_dd_txt"><a href="./order?state=1">待付款</a></div>
  </div>
  <div class="user_center_column_dd_item">
    <div style="height: 30px;"><a href="./order?state=2"><img src="imgs/user_center_dd02.png"  style="width: 20px; height: 20px; margin-top: 4px;"/></a>
     <c:if test="${ordercount2 > 0}">
    <div style="position: relative; width: 20px; height: 20px; border: 2px solid #ff5c4b; font-size: 10px; 
    color: #ff5c4b; text-align: center; line-height: 15px; border-radius: 15px; top: -30px; left: 51%; background-color: #fff;  overflow: hidden;">${ordercount2}</div>
	</c:if>
    </div>
    <div class="user_center_column_dd_txt"><a href="./order?state=2">待发货</a></div>
  </div>
  <div class="user_center_column_dd_item">
    <div style="height: 30px;"><a href="./order?state=3"><img src="imgs/user_center_dd03.png"  style="width: 33px; height: 20px; margin-top: 4px;"/></a>
    <c:if test="${ordercount3 > 0}">
    <div style="position: relative; width: 20px; height: 20px; border: 2px solid #ff5c4b; font-size: 10px; 
    color: #ff5c4b; text-align: center; line-height: 15px; border-radius: 15px; top: -30px; left: 57%; background-color: #fff; overflow: hidden;">${ordercount3}</div>
    </c:if>
    </div>
    <div class="user_center_column_dd_txt"><a href="./order?state=3">待收货</a></div>
  </div>
  <div class="user_center_column_dd_item">
    <div style="height: 30px;"><a href="./order?state=4"><img src="imgs/user_center_dd04.png"  style="width: 22px; height: 20px; margin-top: 4px;"/></a></div>
    <div class="user_center_column_dd_txt"><a href="./order?state=4">已成功</a></div>
  </div>
</div>

<div class="user_center_column_h" onclick="sw(1)">
  <div class="user_center_column_hr">
    <div class="user_center_column_ico"><img src="imgs/user_center_ico02.png"  style="width: 12px; height: 16px; margin-top: 11px;"/></div>
    <div style="line-height: 49px; font-size: 14px; float:left;">地址管理</div>
    <div class="user_center_column_jt"  style="margin-top: 13px;"><img src="imgs/user_center_jt.png"  /></div>
  </div>
</div>

<div class="user_center_column_h" onclick="sw(4)">
  <div class="user_center_column_hr">
    <div class="user_center_column_ico"><img src="imgs/coupon_icon.png"  style="width: 16px; height: 13px; margin-top: 12px;"/></div>
   <div style="line-height: 49px; font-size: 14px; float:left;">我的优惠券</div>
    <div class="user_center_column_jt" style="margin-top: 13px;"><img src="imgs/user_center_jt.png"  /></div>
  </div>
</div>

<div class="user_center_column_h" onclick="sw(7)">
  <div class="user_center_column_hr">
    <div class="user_center_column_ico"><img src="imgs/icon_gift.png"  style="width: 16px; height: 15px; margin-top: 11px;"/></div>
   <div style="line-height: 49px; font-size: 14px; float:left;">我要摇奖</div>
    <div class="user_center_column_jt" style="margin-top: 13px;"><img src="imgs/user_center_jt.png"  /></div>
  </div>
</div>

<div class="user_center_column_h" onclick="sw(2)">
  <div class="user_center_column_hr">
    <div class="user_center_column_ico"><img src="imgs/user_center_ico03.png"  style="float: left; width: 16px; height: 13px; margin-top: 12px;"/>
    <c:if test="${count > 0 }">
    <div style="float: left; margin-left: 13px; margin-top: -16px; width: 8px; height: 8px; border-radius: 4px; left: 13px; top:17px; background: #FF0000;"></div>
    </c:if>
    </div>
   <div style="line-height: 49px; font-size: 14px; float:left;">系统消息</div>
    <div class="user_center_column_jt" style="margin-top: 13px;"><img src="imgs/user_center_jt.png"  /></div>
  </div>
</div>

<div class="user_center_column_h" onclick="sw(6)">
  <div class="user_center_column_hr">
    <div class="user_center_column_ico"><img src="imgs/invitation.png"  style="width: 16px; height: 15px; margin-top: 11px;"/></div>
    <div style="line-height: 49px; font-size: 14px; float:left;">我的邀请</div>
    <div class="user_center_column_jt" style="margin-top: 13px;"><img src="imgs/user_center_jt.png"  /></div>
  </div>
</div>





<div class="user_center_column_h" onclick="sw(3)">
  <div class="user_center_column_hr" >
    <div class="user_center_column_ico"><img src="imgs/about_2.png"  style="width: 16px; height: 15px; margin-top: 11px;"/></div>
    <div style="line-height: 49px; font-size: 14px; float:left;">关于享趣</div>
    <div class="user_center_column_jt" style="margin-top: 13px;"><img src="imgs/user_center_jt.png"  /></div>
  </div>
</div>
<div class="user_center_column_h" onclick="sw(5)">
  <div class="user_center_column_hr">
    <div class="user_center_column_ico"><img src="imgs/help.png"  style="width: 16px; height: 15px; margin-top: 11px;"/></div>
    <div style="line-height: 49px; font-size: 14px; float:left;">新手帮助</div>
    <div class="user_center_column_jt" style="margin-top: 13px;"><img src="imgs/user_center_jt.png"  /></div>
  </div>
</div>

<!-- <a href="tel:400-618-2121"> -->
<div class="user_center_column_h" >
  <div class="user_center_column_hr" style="border: 0;">
    <div class="user_center_column_ico"><img src="imgs/phone.png"  style="width: 16px; height: 13px; margin-top: 12px;"/></div>
   <div style="line-height: 49px; font-size: 14px; float:left;">客服热线</div>
<!--     <div class="user_center_column_jt" style="margin-top: 13px;"> -->
<!--     <img src="imgs/user_center_jt.png"  /> -->
<!--     </div> -->
    <div style="line-height: 49px; font-size: 12px; float:right;">400-618-2121（8:30-17:00）</div>
  </div>
</div>
<div style="height: 10px;"></div>
<c:if test="${isapp == 1 }">
<a href="xqexit://userid=${user.id }">
<div class="exitclass" style="width: 80%; height: 33px; line-height: 33px; margin: 0 auto; background: #00d1bc; border-radius: 16px; text-align: center; color: #fff;">
  退出登录
</div>
</a>
</c:if>
<c:if test="${isapp != 1 }">
<div class="exitclass" style="width: 80%; height: 33px; line-height: 33px; margin: 0 auto; background: #00d1bc; border-radius: 16px; text-align: center; color: #fff;" onclick="clearsession()">
  退出登录
</div>
</c:if>

<div style="height: 49px;"></div>

<c:if test="${isapp != 1 }">
<jsp:include page="./tab.jsp"/>
</c:if>
<%@ include file="cs.jsp" %>
<%CS cs = new CS(1256812462);cs.setHttpServlet(request,response);
String imgurl = cs.trackPageView();%> 
<img src="<%= imgurl %>" width="0" height="0"  />
</body>
</html>
