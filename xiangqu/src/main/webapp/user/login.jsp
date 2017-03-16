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
	function login(){
// 		$("#loginForm").submit();
		$.ajax({
            type: "POST",
            url: "./login",
            data: $("#loginForm").serialize(),
            success: function(msg){
            			if(msg=="success"){
            				window.location.replace("./missiongeo");
            			}else if(msg=="falsesuccess"){
            				window.location.replace("./toregister");
            			}else{
            				alert("用户名或密码错误!");
            				window.location.reload();
            			}
                     }
        });
	}
	
	function toregister(){
		window.location.href = "./toregister";
	}
	
	function tofindpassword(){
		window.location.href = "./findpassword.jsp";
	}
	
</script>
<title>登陆界面</title>
</head>
<body>
<form action="login" method="post" id="loginForm">
<div class="login_top"><img src="imgs/login_icon2.png" style="margin-top: 37px;" width="70px" height="70"></div>
<div class="ellipse"></div>
<div style="width: 100%; height: 13px;"></div>
<div class="content_div">
		<img src="imgs/user_icon.png" class="content_img">
		<div style="width:1px;height:23px;margin-left:9px;margin-top:7px;float:left;background-color: #e3e3e3;"></div>
		<input class="content_input" style="margin-left:0px;border-left:none;" placeholder="请输入用户名" name="userName"/>
</div>
<div style="width: 100%; height: 8px;"></div>
<div class="content_div">
		<img src="imgs/key_icon.png" class="content_img">
		<div style="width:1px;height:23px;margin-left:9px;margin-top:7px;float:left;background-color: #e3e3e3;"></div>
		<input class="content_input" style="margin-left:0px;border-left:none;" placeholder="请输入密码" name="password" type="password"/>
	</div>
<div style="width: 100%; height: 16px; font-size: 12px; line-height: 16px; color: #ff5c4b; text-align: center;">${error}</div>
<div class="bottom_submit" style="cursor: pointer; background: #00d1bc;" onclick="login()">登录享趣</div>
<div style="margin-right: 13%; float:right; line-height: 40px; font-size: 12px;" onclick="tofindpassword()">忘记密码？</div>
<div style="margin-right: 15px; float:right; line-height: 40px; font-size: 12px;color: #eeeeee;">|</div>
<div style="margin-right: 15px; float:right; line-height: 40px; font-size: 12px;" onclick="toregister()">注册享趣帐号</div>
<!-- <input type="submit" value="点击登录" > -->
</form>
<%@ include file="cs.jsp" %>
<%CS cs = new CS(1256812462);cs.setHttpServlet(request,response);
String imgurl = cs.trackPageView();%> 
<img src="<%= imgurl %>" width="0" height="0"  />
</body>
</html>