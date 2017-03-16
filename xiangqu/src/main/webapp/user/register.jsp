<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no">
<link rel="stylesheet" type="text/css" href="css/common.css" />
<link rel="stylesheet" type="text/css" href="css/login.css" />
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
$(function(){
	var combine = $("#combine").val();
	var fromweixin = $("#fromweixin").val();
	if(combine == 1){
		var top = parseInt($(".mask").height()-$(".alert").height());
		var left = parseInt($(".mask").width()-$(".alert").width());
		$(".alert").css("top",top/2);
		$(".alert").css("left",left/3);
		$(".mask").show();
		$(".alert").show();
	}
	if(fromweixin == 1){
		$(".fromweixin").hide();
	}else{
		$(".fromweixin").show();
	}
});
function changeflag(){
	var flag = $("#flag").val();
	if(flag == 1){
		document.getElementById("flagImg").src = "imgs/uncheck_icon.png";
		$("#flag").val(0);
	}else{
		document.getElementById("flagImg").src = "imgs/ischeck_icon.png";
		$("#flag").val(1);
	}
}
	function getvcode(){
		var moblie = $("input[name=moblie]").val();
		if(moblie == ""){
			$("#errordiv").text("请输入手机号码");
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
	
	function register(){
// 		var userName = $("input[name=userName]").val();
// 		if(userName == ""){
// 			$("#errordiv").text("请输入用户名");
// 			return;
// 		}
		var moblie = $("input[name=moblie]").val();
		if(moblie == ""){
			$("#errordiv").text("请输入手机号码");
			return;
		}
		var vcode = $("input[name=vcode]").val();
		if(vcode == ""){
			$("#errordiv").text("请输入验证码");
			return;
		}
// 		var password = $("input[name=password]").val();
// 		if(password == ""){
// 			$("#errordiv").text("请输入密码");
// 			return;
// 		}
		var flag = $("#flag").val();
		if(flag == 0){
			$("#errordiv").text("请阅读并同意《享趣入驻协议》")
			return;
		}
		$("#registerForm").submit();
	}
	
	function hide(){
		$(".mask").hide();
		$(".alert").hide();
		$("#combine").val("");
	}
	
	function tocombine(){
		var moblie = $("input[name=moblie]").val();
		$.ajax({
			type: "post",
			url: "combineuser",
			data: {mobile : moblie},
			success: function(data){
				if(data == "success"){
					window.location.href = "./missiongeo";
				}else if(data == "error1"){
					alert("该手机号已和其他微信号绑定");
					$(".mask").hide();
					$(".alert").hide();
					$("input[name=moblie]").val("");
					$("#combine").val("");
				}
			}
		});
	}
</script>
<title>注册界面</title>
</head>
<body>
<form action="register" method="post" id="registerForm">
<input type="hidden" id="combine" name="combine" value="${combine }"/>
<input type="hidden" id="isweixin" name="isweixin" value="${isweixin }"/>
<input type="hidden" id="fromweixin" name="fromweixin" value="${fromweixin }"/>
<div style="width: 100%; height: 29px;"></div>
<!-- <div class="content_div"> -->
<!-- 		<img src="imgs/user_icon.png" class="content_img"> -->
<!-- 		<input class="content_input" placeholder="请输入用户名" name="userName"/> -->
<!-- </div> -->
<div style="width: 100%; height: 8px;"></div>
<div class="content_div">
		<img src="imgs/phone_icon.png" class="content_img">
		<input class="content_input" value="${moblie }" placeholder="请输入手机号" name="moblie" type="number"/>
</div>
<div style="width: 100%; height: 8px;"></div>
<div class="vcodediv">
		<img src="imgs/sms_icon.png" class="content_img">
		<input class="content_input" placeholder="请输入验证码" name="vcode"/>
	</div>
<div class="getvcode" onclick="getvcode()">获取</div>
<div style="width: 100%; height: 8px; clear:both;"></div>
<!-- <div class="content_div"> -->
<!-- 		<img src="imgs/key_icon.png" class="content_img"> -->
<!-- 		<input class="content_input" placeholder="请设置密码" name="password"/> -->
<!-- 	</div> -->
<div style="width: 100%; height: 8px; clear:both;" class="fromweixin"></div>
<div class="content_div fromweixin">
		<img src="imgs/key_icon.png" class="content_img">
		<input class="content_input" placeholder="请输入邀请码" value="${invietCode }" name="invietCode"/>
	</div>
<div style="width: 100%; height: 8px; clear:both;"></div>
<div style="width: 70%; height: 23px; margin: 0 auto; line-height: 23px;">
	<img src="imgs/ischeck_icon.png" style="width: 22px; height: 22px; float:left;" id="flagImg" onclick="changeflag()">
	<div style="font-size: 12px; float:left;">&nbsp;已阅读并同意<a href="http://www.xiangqu100.com/protocol.htm" >《享趣用户注册协议》</a></div>
	<input type="hidden" value="1" id="flag"/>
</div>	
<div style="width: 100%; height: 16px; "></div>
<div style="width: 100%; height: 16px; font-size: 12px; line-height: 16px; color: #ff5c4b; text-align: center;" id="errordiv">${error}</div>
<div class="bottom_submit" style="background: #00d1bc;" onclick="register()">
		注册享趣
	</div>
</form>
<%@ include file="cs.jsp" %>
<%CS cs = new CS(1256812462);cs.setHttpServlet(request,response);
String imgurl = cs.trackPageView();%> 
<img src="<%= imgurl %>" width="0" height="0"  />
</body>
<div class="mask" style="display: none; z-index: 1000" onclick="hide()"></div>
<div class="alert" style="width: 290px;border-radius: 4px;position: fixed; margin: 0 auto; padding: 5px; background-color: #ffffff;height: auto;z-index: 100;display: none; z-index: 100000;">
	<div style="width: 100%; height: auto; text-align: left; line-height: 36px;">该手机号已在使用，是否绑定到该手机原有账号？</div>
	<input type="hidden" id="alertId"/>
	<input type="hidden" id="shareId"/>
	<div style="width: 100%; height: 30px; float: left; margin:0 auto; color: #33CCFF; font-weight: bold;  text-align: center; line-height: 30px; border-radius: 6px;margin-top: 13px;  border-top: 1px solid #e8e8e8;">
		<div style="width: 50%; height: 30px; float: left; border-right: 1px solid #e8e8e8;" onclick="tocombine()">确定</div>
		<div style="width: 49%; height: 30px; float: left;" onclick="hide()">取消</div>
	</div>
</div>
</html>