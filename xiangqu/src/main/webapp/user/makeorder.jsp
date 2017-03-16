<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no">
<title>确认订单</title>
<link rel="stylesheet" type="text/css" href="./css/common.css" />
<link rel="stylesheet" type="text/css" href="./css/order.css" />
<script src="./js/jquery-1.11.3.min.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js" ></script>
<script>
$(function(){
	var ua = navigator.userAgent.toLowerCase();  
	
    if(ua.match(/MicroMessenger/i)=="micromessenger") {  
    	$("#wechatpay").show();
    	$("#paytype").val(1);
    } else {  
    	$("#alipay").show();
    	$("#paytype").val(2);
    }  
}) 

function addnum(){
    var num = parseInt($("#buynum").val());
    num = num +1;
    $("#buynum").val(num);
    var goodstype = $("#goodstype").val(); 
	 if(goodstype == 2){
		 var price = parseFloat($("#goodsprice").val());
		    $("#allprice").text((num*price).toFixed(0));
		    $("#goldprice").text((num*price).toFixed(0));
		    $("#pointprice").text((num*price).toFixed(0));
	 }else{
		 var price = parseFloat($("#goodsprice").val());
		    $("#allprice").text((num*price).toFixed(2));
		    $("#goldprice").text("￥"+(num*price).toFixed(2));
		    $("#pointprice").text("￥"+(num*price).toFixed(2));
	 }
}
    	
function buyorder(){
	if('${userinfo.moblie}' != ""){
		var goodstype = $("#goodstype").val();
		var isServe = $("#isServe").val();
		if(isServe==0){
			var useraddressid  = $("#useraddressid").val();
			if(useraddressid==''){
				alert("请选择收货地址!");
				return;
			}
		}else if(isServe == 2){
			var comment  = $("#comment").val();
			if(comment==''){
				alert("请输入充值号码!");
				return;
			}
		}
		
		$.ajax({
			url:'./createorder',
			type:'post',
			data:$('#labelForm').serialize(),
			success:function(msg){
				var code = msg.code;
				if(code == 500){
					$("#messageDiv").show();
					$("#messageDiv").text(msg.message);
				}else{
					var orderid = msg.orderid;
					if(goodstype==2){
					$.ajax({
						url:'./pointpay',
						type:'post',
						data:{orderid:orderid},
						success:function(msg){
							var code = msg.code;
							if(code==1){
								alert("兑换成功!");
								window.location.href="./orderdetail?orderid="+orderid;
							}else{
								$("#messageDiv").show();
								$("#messageDiv").text(msg.message);
//		 						alert(msg.message);
							}
						}
					})
					}else{
						var paytype = $("#paytype").val();
						if(paytype==2){
							var order = msg.order;
							$("#WIDout_trade_no").val(order.orderNo);
							$("#WIDtotal_fee").val(order.totalPrice);
							$("#WIDsubject").val("享趣："+order.orderNo);
							$("#WIDbody").val("享趣："+order.orderNo+"订单支付");
							$("#alipayment").submit();
						}else{
							$.ajax({
								url:'./wxpay',
								data:{orderid:orderid},
								type:'get',
								success:function(msg){
									var appid = msg.appid;
									var timeStamp = msg.timeStamp;
									var nonceStr = msg.nonceStr;
									var packagename = msg.packagename;
									var sign = msg.sign;
									$("#appid").val(appid);
									$("#timeStamp").val(timeStamp);
									$("#nonceStr").val(nonceStr);
									$("#packagename").val(packagename);
									$("#sign").val(sign);
									callpay(orderid);
								}
							})
						}
					}
				}
			}
		})
	}else{
		$(".makeorderalert").css("left",($(".mask").width()-290)/2);
		$(".makeorderalert").css("top",($(".mask").height()-$(".makeorderalert").height())/2);
		$(".mask").show();
		$(".makeorderalert").show();
	}
}    	

function hide(){
	$(".mask").hide();
	$(".makeorderalert").hide();
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
			if(data == "error"){
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

function subnum(){
	 	var num = parseInt($("#buynum").val());
	    num = num -1;
	    if(num>0){
	    $("#buynum").val(num);
	    var goodstype = $("#goodstype").val(); 
		 if(goodstype == 2){
			 var price = parseFloat($("#goodsprice").val());
			    $("#allprice").text((num*price).toFixed(0));
			    $("#goldprice").text((num*price).toFixed(0));
			    $("#pointprice").text((num*price).toFixed(0));
		 }else{
			 var price = parseFloat($("#goodsprice").val());
			    $("#allprice").text((num*price).toFixed(2));
			    $("#goldprice").text("￥"+(num*price).toFixed(2));
			    $("#pointprice").text("￥"+(num*price).toFixed(2));
		 }
	    }
}

function checkNum(e,id) {
	
	var textval = $("#"+id).val();
    var k = window.event ? e.keyCode : e.which;
    if (((k >= 48) && (k <= 57)) || k == 8 || k == 0 || ((k >=96 ) && (k <= 105)) || k == 46 || k == 37 || k == 39) {
    } else {
        if (window.event) {
            window.event.returnValue = false;
        }
        else {
            e.preventDefault(); // for firefox
        }
        $("#"+id).val(textval.replace(/\D/g,''));
    }
}

function accountprice(){
	var textval = $("#buynum").val();
	$("#buynum").val(textval.replace(/\D/g,''));
	var num = parseInt($("#buynum").val());
	if(num<1){
		num = 1;
	}
	 $("#buynum").val(num);
	 var goodstype = $("#goodstype").val(); 
	 if(goodstype == 2){
		 var price = parseFloat($("#goodsprice").val());
		    $("#allprice").text((num*price).toFixed(0));
		    $("#goldprice").text((num*price).toFixed(0));
		    $("#pointprice").text((num*price).toFixed(0)); 
	 }else{
		 var price = parseFloat($("#goodsprice").val());
		    $("#allprice").text((num*price).toFixed(2));
		    $("#goldprice").text("￥"+(num*price).toFixed(2));
		    $("#pointprice").text("￥"+(num*price).toFixed(2)); 
	 }
}

function editaddress(){
	var useraddressid =$("#useraddressid").val();
	var num = $("#buynum").val();
	var goodsid = $("#goodsid").val();
	var goodstype = $("#goodstype").val();
	var shareuserid = $("#shareuserid").val();
	var property = $("#property").val();
	var channelCode = $("#channelCode").val();
	window.location.href="./myaddress?addressid="+useraddressid+"&goodstype="+goodstype+"&goodsid="+goodsid+"&goodsnum="+num+"&shareuserid="+shareuserid+"&property="+property+"&channelCode="+channelCode;
}


function callpay(orderid){	
	
		WeixinJSBridge.invoke('getBrandWCPayRequest',{
	  		 "appId" :$("#appid").val(),"timeStamp" : $("#timeStamp").val(), "nonceStr" : $("#nonceStr").val(), "package" : $("#packagename").val(),"signType" : "MD5", "paySign" : $("#sign").val(), 
	   			},function(res){
					WeixinJSBridge.log(res.err_msg);
		            if(res.err_msg == "get_brand_wcpay_request:ok"){  
		            	alert("支付成功!");
		            	window.location.href="./orderdetail?orderid="+orderid;
		            }else if(res.err_msg == "get_brand_wcpay_request:cancel"){  
		            	alert("用户取消支付!");
		            }else{  
		            }  
				});
	}
</script>
<style type="text/css">
.makeorderalert {
	width: 290px;
	border-radius: 4px;
	position: fixed;
	background-color: #ffffff;
	height: auto;
	z-index: 100;
	display: none;
}

.getvcode{
	width: 19%; 
	height: 39px; 
	background: #00d1bc; 
	text-align: center; 
	line-height: 39px; 
	color: #fff; 
	font-size: 12px; 
	float:left;
	border-radius: 20px; 
	margin-left: 3%;
}
</style>
</head>
<body>
<form id="labelForm" >
<c:if test="${not (isServe == 1) }">
<c:if test="${not empty useraddress }">
<!-- <div class="addresstitle">
<div  class="addressimgdiv"> 
<img src="./imgs/address.png" class="addressimg" >
</div>
<div style="height:46px;line-height:46px;float:left;font-size:13px;font-family:'华文细黑'">请设置收货地址</div>
</div> -->
<div class="addresstitle" style="height:auto;overflow:hidden;padding-top:15px;padding-bottom:15px;" onclick="editaddress()">
<div  class="addressimgdiv" style="height:42px;line-height:42px;">
<img src="imgs/user_center_ico02.png" class="addressimg" style="top:6px;">
</div>
<div  class="addressdiv">
<div class="addresscontent"><font style="font-size: 16px;">${useraddress.consignee }</font> &nbsp;&nbsp;${useraddress.phone }</div>
<!-- <div class="addresscontent">联系电话：</div> -->
<div class="addresscontent">${useraddress.proname }${useraddress.cityname }${useraddress.areaname }${useraddress.address }</div>
</div>
<div class="addressedit">
<img src="./imgs/right.png" class="addressimg" style="width:9px;">
</div>
</div>
<input type="hidden" value="${useraddress.id }" id="useraddressid" name="useraddressid">
</c:if>
<c:if test="${empty useraddress}">
<div class="addresstitle" onclick="editaddress()">
<div  class="addressimgdiv">
<img src="./imgs/address.png" class="addressimg"  >
</div>
<div style="height:46px;line-height:46px;float:left;font-size:13px;font-family:'华文细黑'">请设置收货地址</div>
<div class="addressedit" style="float:right">
<img src="./imgs/right.png" class="addressimg" style="width:9px;top:15px;">
</div>
</div>
<input type="hidden" value="" id="useraddressid" name="useraddressid">
</c:if>
<div style="width: 100%; height: 8px; background: #f2f2ef; border-top: 1px solid #e8e8e8; border-bottom: 1px solid #e8e8e8;"></div>
</c:if>
<div class="goodsdetail" >
<div  class="goodsimgdiv" >
<img src="${fileRootUrl }${goods.mianPic}" class="goodsimg" >
</div>
<div  class="goodsdiv" >
<div class="goodscontent">${goods.name }</div>
<div class="goodscontent" style="min-height: 8px;"></div>
<!-- <div class="goodscontent">颜色：随机</div> -->
<div class="goodscontent">
<div class="goodsprice" style="font-size: 13px; width: 36%; overflow: hidden; line-height: 30px;">
<c:if test="${goodstype == 2 }">积分<fmt:formatNumber type="number" value="${goods.price }" maxFractionDigits="0"/></c:if>
<c:if test="${goodstype == 1 }">￥${goods.price }</c:if> </div>
<input type="hidden" value="${goods.price  }" id="goodsprice">
<!-- <div class="goodsnum" id="goodsnum">x1</div> -->

<c:if test="${goods.isServe==1  || goods.isServe==2}">
<div style="line-height:30px;text-align:right;width:50%;float:right;padding-right:2%;">
<input type="hidden" name="buynum" id="buynum" value="1">
x1
</div>
</c:if>
<c:if test="${goods.isServe==0}">
<div style="width:50%;float:right;padding-right:2%;">
<div style="float:right;width:23px; height: 23px; line-height: 23px; text-align: center; font-size: 16px; border: 1px solid #e8e8e8;" onclick="addnum()">+</div>
<!-- <img style="float:right;width:14px" src="./imgs/add.png" onclick="addnum()" > -->
<input type="text" value="1" onkeydown= "return checkNum(event,'buynum')" onblur="accountprice()" name="buynum" id="buynum" style="text-align:center;float: right;width: 23px;padding: 0px;height: 23px;border: 0; border-top: 1px solid #e8e8e8; border-bottom: 1px solid #e8e8e8;">
<!-- <img style="float:right;width:14px" src="./imgs/sub.png" onclick="subnum()" > -->
<div style="float:right;width:23px; height: 23px; line-height: 23px; text-align: center; font-size: 16px; border: 1px solid #e8e8e8;" onclick="subnum()">-</div>
<!-- <font style="float:right;margin-right: 5px;">购买数量:</font> -->
</div>
</c:if>

</div>
</div>

</div>
<div style="width: 100%; height: 8px; background: #f2f2ef; border-top: 1px solid #e8e8e8; border-bottom: 1px solid #e8e8e8;"></div>
<div class="remarkdiv">
<font style="font-size:13px;float: left; color: #e8e8e8;">
<c:if test="${goods.isServe==2}">请输入你的充值号码</c:if>
<c:if test="${goods.isServe!=2}">备注:</c:if>
</font><br/>
<textarea class="remark" name="comment" id="comment" style="margin-left: 0px; height: 30px; border: 1px solid #e8e8e8;"></textarea>
</div>

<!-- <div class="moneydiv"> -->
<!-- <div class="allprice"> -->
<!-- <font style="font-size:13px">总金额：</font> -->
<%-- <font style="font-size:15px;color:#F84E4E" id="allprice">${goods.price }</font> --%>
<!-- </div> -->
<%-- <c:if test="${goodstype == 2 }"> --%>
<!-- <div class="pointpay"> -->
<!-- <font style="font-size:13px">使用余额：</font> -->

<!-- </div> -->
<%-- </c:if> --%>
<%-- <c:if test="${goodstype != 2 }" > --%>
<!-- <div class="moneypay"> -->
<!-- <font style="font-size:13px">现金还需支付：</font> -->
<%-- <font style="font-size:15px;color:#F84E4E" id="goldprice">${goods.price }元 </font> --%>
<!-- </div> -->
<%-- </c:if> --%>
<!-- </div> -->

<c:if test="${goodstype == 1 }">
<div class="paytype" id="alipay" style="display:none;">
<img src="./imgs/choosed.png" style="position:relative;top:2px;" width=12px><font style="font-size:13px;">支付宝支付</font>
</div>

<div class="paytype" id="wechatpay"  style="display:none;">
<img src="./imgs/choosed.png" style="position:relative;top:2px;" width=12px><font style="font-size:13px;">微信支付</font>
</div>
<input type="hidden" id="paytype" value="">
</c:if>

<div style="height: 24px; line-height: 24px; position: fixed; bottom: 48px; background: #ffd403; color: #fff; display: none; width: 100%; text-align: center;" id="messageDiv"></div>
<div class="submitdiv" >
<c:if test="${goodstype == 2 }">
	<font style="color:#F84E4E; font-size: 13px; margin-left: 4%;">积分</font><font style="font-size:15px;color:#F84E4E" id="pointprice"><fmt:formatNumber type="number" value="${goods.price }" maxFractionDigits="0"/></font>
	<font style="font-size:13px;margin-left:5px; color: #e8e8e8;">(可用积分<font style="color: #000;">&nbsp;&nbsp;<fmt:formatNumber type="number" value="${userinfo.balance }" maxFractionDigits="0"/></font>)</font>
</c:if>
<c:if test="${goodstype == 1}">
	<font style=" font-size: 13px; margin-left: 4%;">支付</font><font style="font-size:15px;color:#F84E4E" id="pointprice">￥${goods.price }</font>
</c:if>
<div class="submit" style="float:right; margin-right: 4%; border-radius: 16px; width: 72px; background: #ff5c4b; color: #fff;" onclick="buyorder()" id="subbtn">
<c:if test="${goodstype == 2 }">确认并兑换</c:if><c:if test="${goodstype == 1}">确认并支付</c:if></div>
</div>
<input type="hidden" value="${goods.id }" id="goodsid" name="goodsid">
<input type="hidden" value="${goodstype }" id="goodstype" name="goodstype">
<input type="hidden" value="${shareuserid }" id="shareuserid" name="shareuserid">
<input type="hidden" value="${channelCode }" id="channelCode" name="channelCode">
<input type="hidden" value='${property }' id="property" name="property">
<input type="hidden" value="${isServe }" id="isServe">
</form>

<form name=alipayment id="alipayment" action=alipayapi.jsp method=post >
<input type="hidden" id="WIDout_trade_no" name="WIDout_trade_no" value="">
<input type="hidden" id="WIDtotal_fee" name="WIDtotal_fee" value="">
<input type="hidden" id="WIDsubject" name="WIDsubject" value="">
<input type="hidden" id="WIDbody" name="WIDbody" value="">
</form>
<input type="hidden" value="" id="appid">
<input type="hidden" value="" id="timeStamp">
<input type="hidden" value="" id="nonceStr">
<input type="hidden" value="" id="packagename">
<input type="hidden" value="" id="sign">

<div class="mask" style="display: none;" onclick="hide()"></div>
	<div class="makeorderalert">
		<form action="" method="post" id="makeorderForm">
			<div style="width: 100%; height: 36px; text-align: center; line-height: 36px;">请输入手机号</div>
			<div style="width: 100%; height: 40px; text-align: center;">
				<input type="text" name="moblie" style="width: 80%; height: 27px; border: 1px solid #e8e8e8; padding-left: 5px; " placeholder="请输入手机号"/>
			</div>
			<div style="width: 100%; height: 40px; text-align: center;">
				<input type="text" name="vcode" style="width: 50%; margin-left: 9%; height: 27px; border: 1px solid #e8e8e8; padding-left: 5px; float: left; margin-top: 2px;" placeholder="请输入验证码"/>
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
<%@ include file="cs.jsp" %>
<%CS cs = new CS(1256812462);cs.setHttpServlet(request,response);
String imgurl = cs.trackPageView();%> 
<img src="<%= imgurl %>" width="0" height="0"  />
</body>
</html>