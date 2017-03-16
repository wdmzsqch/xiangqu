<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no">
<link rel="stylesheet" type="text/css" href="css/common.css" />
<script type="text/javascript" src="./js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
$(function(){
	var height = document.documentElement.clientHeight;
	$("#detailframe").attr("height",parseInt(height-49));
	$("#detailframe").attr("src",'${subject.detailUrl}'); 
	
	if(isWeiXin()){
        $("#bottomdiv").hide();
        $("#otherbottom").show();
    }else{
    	$("#bottomdiv").show();
	    $("#otherbottom").hide();
    }
})

	function isWeiXin(){
	    var ua = window.navigator.userAgent.toLowerCase();
	    if(ua.match(/MicroMessenger/i) == 'micromessenger'){
	        return true;
	    }else{
	        return false;
	    }
	}	
	
	function truntodown(){
		window.location.href = "http://www.xiangqu100.com/download.html";
	}
	
	function turntocomment() {
		window.location.href = "./comment?relative_id=" + '${id}' + "&type=3";
	}
</script>
<title>专题详情</title>
</head>
<body style="margin:0;">
<div style="padding-bottom:49px;">
<iframe id="detailframe" src="" width="100%" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="yes" allowtransparency="yes"></iframe>
</div>
<div id="bottomdiv" style="width: 100%; height: 49px; position: fixed; bottom: 0px; background: #fff; z-index: 101;">
<!-- 	<div style="position: absolute; bottom: 14px; z-index: 201; left: 4%; font-size: 13px;" onclick="turntocomment()"> -->
<!-- 		<img src="imgs/comment.png" style="width: 16px; height: 15px; float: left;"> -->
<%-- 		<div style="float: left; line-height: 14px; margin-left: 10px;">评论(${commentcount })</div> --%>
<!-- 	</div> -->
	<a href="xqshare://type=4&id=${id}">
		<div
			style="position: absolute; bottom: 8px; z-index: 201; right: 4%; width: 43%; height: 32px; background: #00d1bc; border-radius: 16px; text-align: center; line-height: 32px; color: #fff; margin: 0 auto;">
			<img src="imgs/small_share_icon.png" style="width: 16px; height: 15px;">
			分享赚积分
		</div>
	</a>
	</div>
	<div id="otherbottom" style="width: 100%; height: 49px; position: fixed; bottom: 0px; background: #fff; z-index: 101; display: none;" onclick="truntodown()">
		<div style="width: 96%; margin-left: 2%; height: 45px; margin-top: 2px; text-align: center; background: #3598dc; border-radius: 6px; font-size: 16px; color: #fff; line-height: 45px;">
		<font style="font-size: 23px; color: RGB(255,0,8);">点击这里</font>关注<font style="font-size: 23px;">“享趣100</font>公众号<font style="font-size: 23px;">”</font>一起分享赚钱
<!-- 		<img src="imgs/download.png" style="width: 28px; height: 31px; margin-top: 7px; float: right; margin-right: 4%;">		 -->
		</div>
	</div>

<%@ include file="cs.jsp" %>
<%CS cs = new CS(1256812462);cs.setHttpServlet(request,response);
String imgurl = cs.trackPageView();%> 
<img src="<%= imgurl %>" width="0" height="0"  />
</body>
</html>