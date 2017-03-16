<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no">
<script src="./js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
$(function(){
	if (navigator.geolocation)
	{
		navigator.geolocation.getCurrentPosition(showPosition,errorCallback);
	}else{
		//页面输出设备不支持
		window.location.href = "./mission"; 	
	}
});

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
	    	var url = "http://api.map.baidu.com/geocoder/v2/?ak=N0qQQgEE7fAe8boAQOdkxvBl" + 
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
	    		window.location.href = "./getcityid?city="+json.result.addressComponent.city;
	    		}, 
	    		error: function (XMLHttpRequest, textStatus, errorThrown) { 
	    			window.location.href = "./mission";	
	    		} 
	    		}); 
		}, 
		error: function (XMLHttpRequest, textStatus, errorThrown) { 
			window.location.href = "./mission";
		} 
		}); 
	
}

function errorCallback(error){
	 switch(error.code){  
     case error.TIMEOUT:  
//          $(".errormsg").text("连接超时，请重试");  
		 window.location.href = "./mission";	
         break;  
     case error.PERMISSION_DENIED:  
//          $(".errormsg").text("您拒绝了使用位置共享服务，查询已取消");  
		 window.location.href = "./mission"; 	
         break;  
     case error.POSITION_UNAVAILABLE:  
//          $(".errormsg").text("亲爱的火星网友，非常抱歉，我们暂时无法为您所在的星球提供位置服务");  
		 window.location.href = "./mission"; 	
         break;  
 }
}
</script>
<title>关于享趣</title>
</head>
<body>
<div style="text-align: center;">
</div>
</body>
</html>