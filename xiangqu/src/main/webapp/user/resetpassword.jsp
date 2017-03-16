<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, user-scalable=no">
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/common.css" />
<script type="text/javascript">
function getvcode(){
	var moblie = $("#mobilediv").text();
	if(moblie == ""){
		$("#errordiv").text("手机号码为空不能获取验证码");
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

function changepwd(){
	var vcode = $("input[name=vcode]").val();
	if(vcode == ""){
		$("#errordiv").text("验证码不能为空");
		return;
	}
	
	var fpwd = $("#fpwd").val();
	if(fpwd == ""){
		$("#errordiv").text("密码不能为空");
		return;
	}
	
	var spwd = $("#spwd").val();
	if(spwd == ""){
		$("#errordiv").text("确认密码不能为空");
		return;
	}
	
	if(spwd != fpwd){
		$("#errordiv").text("两次密码不一致");
		return;
	}
	$.ajax({
		type: "post",
		url: "./registerpassword",
		data: $("#resetForm").serialize(),
		success: function(data){
			if(data == "success"){
				window.location.href="./index";
			}
			if(data == "error"){
				$("#errordiv").text("验证码错误或已失效");
				return;
			}
		}
	});
	
}
</script>
<title>找回密码</title>
</head>
<body style="font-family: '微软雅黑';">
<form action="" method="post" id="resetForm">
	<input type="hidden" value="${userinfo.id }" name="user_id"/>
	<input type="hidden" value="${userinfo.moblie }" name="moblie"/>
	<div style="width: 100%; height: 30px;"></div>
	<div style="text-align: center; color: #d2d2d2; line-height: 20px; font-size: 12px;">您绑定的手机为</div>
	<div style="text-align: center; color: #00d1bc; height: 30px; line-height: 30px; font-size: 18px;" id="mobilediv">${userinfo.moblie }</div>
	<div style="width: 100%; height: 30px;"></div>
	<div
		style="width: 53%; height: 39px; border: 1px solid #e3e3e3; border-radius: 20px; margin-left: 12.5%; float:left;">
		<img src="imgs/sms_icon.png"
			style="float: left; width: 10px; height: 16px; margin-left: 15px; margin-top: 10px;">
		<input
			style="width: 60%; height: 23px; border: 0px; border-left: 1px solid #e3e3e3; float: left; margin-top: 7px; margin-left: 10px; padding-left: 5px; font-size: 13px;" placeholder="请输入验证码" name="vcode"/>
	</div>
	<div style="width: 19%; height: 39px; background: #00d1bc; text-align: center; line-height: 39px; color: #fff; font-size: 12px; float:left;border-radius: 20px; margin-left: 3%; cursor: pointer;" class="getvcode" onclick="getvcode()">获取</div>
	<div style="width: 100%; height: 8px; clear:both;"></div>
	<div
		style="width: 75%; height: 39px; border: 1px solid #e3e3e3; border-radius: 20px; margin: 0 auto;">
		<img src="imgs/key_icon.png"
			style="float: left; width: 10px; height: 16px; margin-left: 15px; margin-top: 10px;">
		<input
			style="width: 60%; height: 23px; border: 0px; border-left: 1px solid #e3e3e3; float: left; margin-top: 7px; margin-left: 10px; padding-left: 5px; font-size: 13px;" type="password" id="fpwd" placeholder="请设置新密码" name="password"/>
	</div>
	<div style="width: 100%; height: 8px;"></div>
	<div
		style="width: 75%; height: 39px; border: 1px solid #e3e3e3; border-radius: 20px; margin: 0 auto;">
		<img src="imgs/key_icon.png"
			style="float: left; width: 10px; height: 16px; margin-left: 15px; margin-top: 10px;">
		<input
			style="width: 60%; height: 23px; border: 0px; border-left: 1px solid #e3e3e3; float: left; margin-top: 7px; margin-left: 10px; padding-left: 5px; font-size: 13px;" type="password" id="spwd" placeholder="再次确认新密码"  name=""/>
	</div>
	<div style="width: 100%; height: 17px;"></div>
	<div style="width: 100%; height: 16px; font-size: 12px; line-height: 16px; color: #ff5c4b; text-align: center;" id="errordiv">${error}</div>
	<div
		style="width: 75%; height: 39px; background: #00d1bc; color: #fff; border: 1px solid #e3e3e3; border-radius: 20px; cursor: pointer; margin: 0 auto;text-align: center; line-height: 39px;" onclick="changepwd()">
		重设
	</div>
</form>
<%@ include file="cs.jsp" %>
<%CS cs = new CS(1256812462);cs.setHttpServlet(request,response);
String imgurl = cs.trackPageView();%> 
<img src="<%= imgurl %>" width="0" height="0"  />
</body>
</html>