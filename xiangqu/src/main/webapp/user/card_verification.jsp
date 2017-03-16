<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no">
<link rel="stylesheet" type="text/css" href="./css/common.css" />
<link rel="stylesheet" type="text/css" href="./css/address.css" />
<script src="./js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
function checkvalue(){
		var cardNum = $("#cardNum").val();
		if(cardNum==''){
			alert("请输入卡号!");
			return false;
		}
		var cardPwd = $("#cardPwd").val();
		if(cardPwd==''){
			alert("请输入卡密!");
			return false;
		}
}

function changePro(){
	var province = $("#province").val();
	if(province==''){
		return;
	}
	$.ajax({
		url:'./getAreaListAsyn',
		type:'POST',
		data:{'parentid':province},
		success:function(msg){
			var arealist =msg.arealist;
			$("#city").empty();
			var html = '';
			for(var i = 0;i<arealist.length;i++){
				html += '<option value="'+arealist[i].id+'">'+arealist[i].name+'</option>';
			}
			$("#city").append(html);
			changeCity();
		}
	})
}

function changeCity(){
	var city = $("#city").val();
	if(city==''){
		return;
	}
	$.ajax({
		url:'./getAreaListAsyn',
		type:'POST',
		data:{'parentid':city},
		success:function(msg){
			var arealist =msg.arealist;
			$("#area").empty();
			var html = '';
			for(var i = 0;i<arealist.length;i++){
				html += '<option value="'+arealist[i].id+'">'+arealist[i].name+'</option>';
			}
			$("#area").append(html);
		}
	})
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

function checkTextLength(obj, length) {   
    
    if(obj.value.length > length)   {   
         
        obj.value = obj.value.substr(0, length);   
    }   

}

</script>
<title>验证卡密</title>
</head>
<body style="color: #bfbfbf;">
<form action="checkCard" method="post">
<div class="username">
<div style="float:left;width:65px;">卡号：</div><input type="text" style="width:200px;" required="required" id="cardNum" name="cardNum" value="">
</div>
<div class="username">
<div style="float:left;width:65px;">密码：</div><input type="text" style="width:200px;" required="required" id="cardPwd" name="cardPwd" value="">
</div>
<div style="width: 100%; height: 20px; line-height: 20px; text-align: center; color: red;">${message }</div>
<div class="submitdiv" style="text-align:center;">
<div style="height: 8px;"></div>
<input type="submit" onclick="return checkvalue()" style="width: 73%; height: 32px; line-height: 32px; margin: 0 auto; text-align: center; background: #00d1bc; color: #fff; border-radius: 16px;" value="提交"></input>
</div>
</form>

<%@ include file="cs.jsp" %>
<%CS cs = new CS(1256812462);cs.setHttpServlet(request,response);
String imgurl = cs.trackPageView();%> 
<img src="<%= imgurl %>" width="0" height="0"  />
</body>
</html>