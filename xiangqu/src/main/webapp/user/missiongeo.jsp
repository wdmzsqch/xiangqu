<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no">
<title>我分享</title>
<link rel="stylesheet" type="text/css" href="./css/common.css" />
<link rel="stylesheet" type="text/css" href="./css/share.css" />
<link rel="stylesheet" type="text/css" href="./css/flickerplate.css" />
<script type="text/javascript" src="./js/jquery-1.11.3.min.js"></script>
<script src="./js/modernizr-custom-v2.7.1.min.js"></script>
<script src="./js/jquery-finger-v0.1.0.min.js"></script>
<script src="./js/flickerplate.min.js"></script>
<script src="js/jquery.lazyload.js?v=1.9.1"></script>
<script type="text/javascript">
	$(document).ready(function() {
		if('${isapp}' != 1){
			setTabSelected(1);
		}
		if(isWeiXin()){
			if (navigator.geolocation)
			{
				navigator.geolocation.getCurrentPosition(showPosition,errorCallback);
			}else{
				//页面输出设备不支持
				window.location.replace("./mission"); 	
			}
		}else{
			//页面输出设备不支持
			window.location.replace("./mission"); 
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
	
	function goodsShare() {
		window.location.replace("./sharegoods");
	}
	
	function missiondetail(id,ramianTimes, overtime, state){
		if(state <= 2){
			window.location.href="./missiondetail?id="+id+"&shareuserid="+'${userid}';
		}else{
			$(".missalert").css("left",($(".mask").width()-290)/2);
			$(".missalert").css("top",($(".mask").height()-$(".missalert").height())/2);
			$(".mask").show();
			$(".missalert").show();
			$("#alertId").val(id);
			$("#shareId").val('${userid}');
		}
	}
	
	function submitForm(){
		window.location.href="./missiondetail?id="+$("#alertId").val()+"&shareuserid="+$("#shareId").val();
	}
	
	$(function(){
		 $("img.lazy").lazyload({
			 effect: "fadeIn",
			 threshold: 200,
			 placeholder : "imgs/load_default.jpg"
		});
		 $("li").lazyload({effect: "fadeIn"});
	    $('.flicker-example').flicker({
	        arrows: false,
	        arrows_constraint: true,
	        auto_flick: true,
	        auto_flick_delay: 2,
	        block_text: false,
	        dot_navigation: true,
	        dot_alignment: 'center',
	        flick_animation: 'jquery-slide',
	        flick_position: 1,
	        inner_width: false,
	        theme: 'light'
	    });
	});
	

	function hide(){
		$(".mask").hide();
		$(".missalert").hide();
	}
	
	function getsubject(){
		window.location.href = "./subject";
	}
	
	function getevent(){
		window.location.href = "./eventlist";
	}
	
	function turntonew(type, id){
		if(type == 1){
			window.location.href = "./lookeventdetail?id="+id;
		}else{
			window.location.href = "./subjectdetail?id="+id;
		}
	}
	
	function editorAddress(userid){
		window.location.href = "./chooseaddress?userid="+userid+"&type=1";
	}
	
	function gohref(url){
		window.location.href=url;
	}
	
	function turntosysmessage(){
		window.location.href = "./message";
	}
	
	function showPosition(position){
		var url2 = "http://api.map.baidu.com/geoconv/v1/?coords="+position.coords.longitude+ "," +position.coords.latitude+
			"&from=1&to=5&ak=N0qQQgEE7fAe8boAQOdkxvBl";
		$.ajax({ 
			type: "GET", 
			dataType: "jsonp", 
			url: url2, 
			success: function (json) { 
			if (json == null || typeof (json) == "undefined") { 
			return; 
			} 
			if (json.status != "0") { 
			return; 
			} 
			  var result = json.result;
		        var lon = result[0].x;//经度
		        var lat = result[0].y;//纬度
		    	var url = "http://api.map.baidu.com/geocoder/v2/?ak=733977786673d4f0a6e7aff104a51244" + 
		    	"&callback=renderReverse" + 
		    	"&location=" + lat + "," +lon + 
		    	"&output=json" + 
		    	"&pois=0"; 
		    	$.ajax({ 
		    		type: "GET", 
		    		dataType: "jsonp", 
		    		url: url, 
		    		success: function (json) { 
		    		if (json == null || typeof (json) == "undefined") { 
		    		return; 
		    		} 
		    		if (json.status != "0") { 
		    		return; 
		    		} 
// 		    		json.result.addressComponent.province;  取省
// 		    		json.result.addressComponent.city;  取市
// 		    		json.result.addressComponent.district;  取区
		    		window.location.replace("./getcityid?city="+json.result.addressComponent.city+"&area="+json.result.addressComponent.district);
		    		}, 
		    		error: function (XMLHttpRequest, textStatus, errorThrown) { 
		    			window.location.replace("./mission");	
		    		} 
		    		}); 
			}, 
			error: function (XMLHttpRequest, textStatus, errorThrown) { 
				window.location.replace("./mission");
			} 
			}); 
		
	}

	function errorCallback(error){
		 switch(error.code){  
	     case error.TIMEOUT:  
//	          $(".errormsg").text("连接超时，请重试");  
			 window.location.replace("./mission");	
	         break;  
	     case error.PERMISSION_DENIED:  
//	          $(".errormsg").text("您拒绝了使用位置共享服务，查询已取消");  
			 window.location.replace("./mission"); 	
	         break;  
	     case error.POSITION_UNAVAILABLE:  
//	          $(".errormsg").text("亲爱的火星网友，非常抱歉，我们暂时无法为您所在的星球提供位置服务");  
			 window.location.replace("./mission"); 	
	         break;  
	 }
	}
</script>
</head>
<body style="font-family: '微软雅黑'">
	<%-- 	<jsp:include page="./top_noback.jsp" /> --%>
<div style="position: fixed; top: 0px; z-index: 1000; height: 44px; line-height: 44px; width: 100%; background: #00d1bc; color: #fff;">
		<img src="imgs/location_bai.png" style="width: 12px; height: 16px; margin-top: 14px; float:left; margin-left: 12px;" onclick="editorAddress(${userid})">
		<div style="padding-left: 6px; padding-right: 12px; font-size: 11px; float: left;" onclick="editorAddress(${userid})">
				<c:if test="${empty proviceName && empty cityName && empty areaName}">全国</c:if>
				<c:if test="${not empty proviceName || not empty cityName || not empty areaName}">${proviceName }${cityName }${areaName }</c:if>
		</div>
		<img src="imgs/message_icon.png" style="float: right;margin-right: 12px; margin-top: 13px; width: 19px; height: 15px;" onclick="turntosysmessage()">
	</div>

	<div class="flicker-example" style="margin-top: 44px;">
		<ul>
			<c:forEach items="${adlist}" var="p">
				<li data-background="${fileRootUrl }${p.pic}" onclick='gohref("${p.url}")' style="background-image: url('${fileRootUrl }${p.pic}');"></li>
			</c:forEach>
		</ul>
	</div>
	<c:if test="${not empty modelList }">
	<div style="width: 100%; height: 75px; background: #fff; border-bottom: 1px solid #e8e8e8;">
		<div style="width: 92%; height: 75px; margin: 0 auto;">
			<c:forEach items="${modelList }" var="model">
				<a href="${model.url }">
				<div style="width: 20%; height: 75px; float: left; text-align: center; font-size: 11px;">
					<img src="${fileRootUrl }${model.pic}" style="width: 49px; height: 49px; margin-top: 12px; border-radius: 49px;">
					<br/>
					${model.title }
				</div>
				</a>
			</c:forEach>
		</div>
	</div>
	</c:if>
	
	<div style="padding-bottom: 47px; text-align: center; line-height: 50px;">
		定位中...
	</div>
	<c:if test="${isapp != 1 }">
	<jsp:include page="./tab.jsp" />
	</c:if>

<%@ include file="cs.jsp" %>
<%CS cs = new CS(1256812462);cs.setHttpServlet(request,response);
String imgurl = cs.trackPageView();%> 
<img src="<%= imgurl %>" width="0" height="0"  />
</body>
<div class="mask" style="display: none; z-index: 1000" onclick="hide()"></div>
<div class="missalert" style="width: 290px;border-radius: 4px;position: fixed; padding: 5px; background-color: #ffffff;height: auto;z-index: 100;display: none; z-index: 100000;">
	<div style="width: 100%; height: 36px; text-align: center; line-height: 36px;">任务已结束，再分享点击不再有收益。</div>
	<input type="hidden" id="alertId"/>
	<input type="hidden" id="shareId"/>
	<div style="width: 100%; height: 30px; float: left; margin:0 auto; color: #33CCFF; font-weight: bold;  text-align: center; line-height: 30px; border-radius: 6px;margin-top: 13px;  border-top: 1px solid #e8e8e8;"
					onclick="submitForm()">我知道了</div>
</div>
</html>