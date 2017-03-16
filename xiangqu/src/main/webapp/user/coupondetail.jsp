<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no">
<link rel="stylesheet" type="text/css" href="./css/common.css" />
<script type="text/javascript" src="./js/jquery-1.11.3.min.js"></script>
<title>优惠券详细</title>
<style type="text/css">
.couponalert {
	width: 290px;
	border-radius: 4px;
	position: fixed;
	background-color: #ffffff;
	height: auto;
	z-index: 100;
	display: none;
}

.getvcode {
	width: 19%;
	height: 39px;
	background: #00d1bc;
	text-align: center;
	line-height: 39px;
	color: #fff;
	font-size: 12px;
	float: left;
	border-radius: 20px;
	margin-left: 3%;
}
</style>

<script type="text/javascript">
function getCoupon(coupon_id){
	var hasMoblie = $("#hasMoblie").val();
	if(hasMoblie == 1){
		$("#getdiv").removeAttr("onclick");
		var share_userid = $("#share_userid").val();
		$.ajax({
			url:'./getCoupon',
			type:'post',
			data:{coupon_id:coupon_id,share_userid:share_userid},
			success:function(msg){
				var code = msg.code;
				if(code==1){
					alert("领取成功!");
					window.location.href="./mycoupon"
				}else{
					alert(msg.message);
					window.location.reload();
				}
			}
		})
	}else{
		$(".couponalert").css("left",($(".mask").width()-290)/2);
		$(".couponalert").css("top",($(".mask").height()-$(".couponalert").height())/2);
		$(".mask").show();
		$(".couponalert").show();
	}

}

function hide(){
	$(".mask").hide();
	$(".couponalert").hide();
}

function submitForm(){
	var moblie = $("input[name=moblie]").val();
	if(moblie == ""){
		alert("手机号不能为空");
		return;
	}
	var vcode = $("input[name=vcode]").val();
	if(vcode == ""){
		alert("验证码不能为空");
		return;
	}
	$.ajax({
		type: "post",
		url: "./addUserMoblie",
		data: {moblie :moblie, vcode :vcode},
		success: function(data){
			if(data == "success"){
				window.location.reload();
			}
			if(data == "error"){
				alert("验证码错误");
				return;
			}
			if(data == "error1"){
				alert("验证码已过期");
				return;
			}
		}
	});
}

function getvcode(){
	var moblie = $("input[name=moblie]").val();
	if(moblie == ""){
		alert("请输入手机号码");
		return;
	}
	$.ajax({
		type: "post",
		url: "../api/user/adlist",
		success: function(data){
			$.ajax({
				type: "post",
				url: "../api/user/getvcode",
				data: {moblie :moblie, type:"page"},
				success: function(data){
					times();
				}
			});
		}
	});
	
	
}

var wait = 60;
function times(){
	   if (wait == 0) {  
		   $(".getvcode").css("background","#00d1bc");
		   $(".getvcode").attr("onclick", "getvcode()");
		   $(".getvcode").text("获取"); 
           wait = 60;  
        } else {  
        	$(".getvcode").css("background","#d2d2d2");
    		$(".getvcode").removeAttr("onclick");
    		$(".getvcode").text(wait+"″");  
            wait--;  
            setTimeout(function() {  
                times()  
            },  
            1000)  
        }  
}

$(function(){
	var ua = window.navigator.userAgent.toLowerCase();
    if(ua.match(/MicroMessenger/i) == 'micromessenger'){
    	$("#downloadappbtn").css("display","block"); 
    }
});

function downloadapp(){
	window.location.href = "http://www.xiangqu100.com/download.html";
}
</script>

</head>
<body style="background-color: #f2f2ef;">
	<div
		style="color: #ffffff; height: auto; overflow: hidde; margin-top: 16px; margin-left: 13px; margin-right: 13px; background-repeat: no-repeat; background-size: contain; background-size: 100% 100%; background-image: url(imgs/couponbackground.png)">
		<div style="padding-left: 20px; text-align: center; height: auto; overflow: hidden; padding-bottom: 9px; padding-top: 9px; padding-right: 20px;">
			<div style="font-size: 24px;">
				<c:if test="${coupon.money >0}">
					<fmt:formatNumber type='number' value='${coupon.money }' maxFractionDigits="0"></fmt:formatNumber>元</c:if>
				<c:if test="${coupon.type==1 }">实物券</c:if>
				<c:if test="${coupon.type==2 }">抵扣券</c:if>
			</div>
			<div style="font-size: 12px; padding-top: 10px;">${coupon.intro }</div>
			<div style="padding-top: 5px; padding-bottom: 5px; font-size: 12px; line-height: 14px; text-align: center; padding-left: 20px; padding-right: 20px;">
				有效期至
				<fmt:formatDate value="${coupon.validity }" pattern="yyyy-MM-dd" />
			</div>
		</div>
		<div style="clear: both; margin-left: 20px; margin-right: 20px; height: 3px; border-top: 1px solid rgba(255, 255, 255, 0.5); border-bottom: 1px solid rgba(255, 255, 255, 0.5);"></div>
		<div style="margin-left: 20px; margin-right: 20px; padding-top: 10px; padding-bottom: 30px; text-align: left;">
			<img src="${fileurl }${shop.pic}" width="23px" height="23px" style="float: left; border-radius: 6px;">
			<div style="float: left; margin-left: 10px; font-size: 11px; line-height: 23px;">${shop.companyName }</div>
		</div>
		<div style="margin-left: 20px; margin-right: 20px; padding-bottom: 15px; font-size: 11px; line-height: 23px; text-align: left;">
			电话：&nbsp;&nbsp;${shop.phone } <br> 地址：&nbsp;&nbsp;${shop.proname }${shop.cityname }${shop.areaname }${shop.address }
		</div>

	</div>
	<div style="width: 100%; padding-top: 10px; text-align: center; font-size: 12px; color: #bfbfbf">最终解释权归享趣所有</div>
	<div style="padding-left: 12px; padding-right: 12px; height: auto; overflow: hidden; margin-top: 20px; background-color: #ffffff;">
		<div style="font-size: 12px; color: #bfbfbf; line-height: 13px; padding-top: 10px;">数量说明</div>
		<div style="padding-top: 15px; padding-bottom: 10px;">
			<font style="font-size: 14px; color: #414141;">共发放${coupon.total }张,仅剩<font style="color: #ff5c4b">${coupon.total-coupon.getNum }</font>张
			</font>
		</div>
	</div>
	<div style="text-align: right; color: #3598db; font-size: 12px;"><br/><a href="./mycoupon">查看我的优惠券 &gt;&nbsp;&nbsp;&nbsp; </a></div>
	<!-- <div style="width: 100%; height: 50px;"></div> -->
	<div id="downloadappbtn" style="position: fixed; bottom: 50px; text-align: center; width: 100%; font-size: 15px; display: none;">
		<div style="color: #3598db; margin-bottom: 5px;" onclick="downloadapp()">微信登录App即可使用</div>
		<!-- <div style="height: 45px; margin-bottom: 20px;border-radius:5px;width: 80%; background-color: #3598db; margin-left: 10%;line-height: 45px; color: #fff; " onclick="downloadapp()">
	立即下载&nbsp;享趣App
	</div> -->
	</div>
	<div style="position: fixed; text-align: center; background-color: #ffffff; height: 50px; width: 100%; bottom: 100px;">
		<div style="margin: 0 auto; width: 135px; height: 30px; line-height: 30px; text-align: center; border: 1px solid #ff5c4b; color: #ff5c4b; margin-top: 9px; border-radius: 20px;" id="getdiv"
			onclick="getCoupon(${coupon.id})">领取优惠券</div>
	</div>
	<input type="hidden" value="${share_userid }" id="share_userid">
	<input type="hidden" value="${hasMoblie }" id="hasMoblie">

	<div class="mask" style="display: none;" onclick="hide()"></div>
	<div class="couponalert">
		<form action="" method="post" id="couponuserForm">
			<div style="width: 100%; height: 36px; text-align: center; line-height: 36px;">第一次领取请输入手机号</div>
			<div style="width: 100%; height: 40px; text-align: center;">
				<input type="text" name="moblie" style="width: 80%; height: 27px; border: 1px solid #e8e8e8; padding-left: 5px;" placeholder="请输入手机号" />
			</div>
			<div style="width: 100%; height: 40px; text-align: center;">
				<input type="text" name="vcode" style="width: 50%; margin-left: 9%; height: 27px; border: 1px solid #e8e8e8; padding-left: 5px; float: left; margin-top: 2px;" placeholder="请输入验证码" />
				<div class="getvcode" onclick="getvcode()">获取</div>
			</div>
			<div style="width: 100%; height: 60px;">
				<div style="width: 60px; height: 30px; float: left; background: #00d1bc; text-align: center; line-height: 30px; border-radius: 6px; margin-left: 50px; color: #fff; margin-top: 13px;"
					onclick="submitForm()">提交</div>
				<div style="width: 60px; height: 30px; float: left; background: #ff5c4b; text-align: center; line-height: 30px; border-radius: 6px; color: #fff; margin-left: 30px; margin-top: 13px;"
					onclick="hide()">取消</div>
			</div>
		</form>
	</div>

	<%@ include file="cs.jsp"%>
	<%
		CS cs = new CS(1256812462);
		cs.setHttpServlet(request, response);
		String imgurl = cs.trackPageView();
	%>
	<img src="<%=imgurl%>" width="0" height="0" />
</body>

</html>