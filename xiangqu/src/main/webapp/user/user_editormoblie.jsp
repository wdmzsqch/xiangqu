<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, user-scalable=no">
<title>用户手机号码</title>
<link rel="stylesheet" type="text/css" href="css/user.css" />
<link rel="stylesheet" type="text/css" href="css/common.css" />
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript">

function getvcode(){
	var moblie = $("input[name=moblie]").val();
	if(!checkphone(moblie)){
		alert("请输入正确的手机号码!");
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

function editorMobile(){
	var vcode = $("input[name=vcode]").val();
	if(vcode==''){
		alert("请输入验证码!");
		return;
	}
	$.ajax({
		url:'./editor_moblie',
		type:"post",
		data:$("#editorform").serialize(),
		success:function(msg){
			var code =msg.code;
			if(code==1){
				window.location.href="./edit_user";
			}else{
				alert(msg.message);
			}
		}
	})
}
</script>
</head>

<body class="bj01">
<form action="" enctype="multipart/form-data" method="post" id="editorform">
<input type="hidden" value="${id }" name="id"/>
<div style="width: 100%; height: 80px; background: #fff; text-align: center;">
	<input type="text" name="moblie" style="width: 90%; height: 30px; margin-top:30px; margin-left: 5%; border: 1px solid #e8e8e8; border-radius: 6px;" value="${moblie }" />
</div>
<div style="width:100%;height:auto;overflow:hidden;background-color:#ffffff;padding-bottom:10px;">
<div class="vcodediv">
		<img src="imgs/sms_icon.png" class="content_img">
		<input class="content_input" placeholder="请输入验证码" name="vcode"/>
	</div>
<div class="getvcode" onclick="getvcode()">获取</div>
</div>
<div style="position: fixed; bottom: 0px; width: 100%; height: 48px; border: 1px solid #e8e8e8; background: #fff;">
<div style="height: 8px;"></div>
	<div style="width: 73%; height: 32px; line-height: 32px; margin: 0 auto; text-align: center; background: #00d1bc; color: #fff; border-radius: 16px;" onclick="editorMobile()">保存修改</div>
</div>
</form>

<%@ include file="cs.jsp" %>
<%CS cs = new CS(1256812462);cs.setHttpServlet(request,response);
String imgurl = cs.trackPageView();%> 
<img src="<%= imgurl %>" width="0" height="0"  />
</body>
</html>
