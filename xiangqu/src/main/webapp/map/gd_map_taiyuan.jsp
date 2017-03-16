<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="chrome=1">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <script src="./js/jquery-1.11.3.min.js"></script>
    <style type="text/css">
      body,html,#container{
        height: 100%;
        margin: 0px;
        font: 12px Helvetica, 'Hiragino Sans GB', 'Microsoft Yahei', '微软雅黑', Arial;
      }
      .info-title{
        color: white;
        font-size: 14px;
        background-color: rgba(0,155,255,0.8);
        line-height: 26px;
        padding: 0px 0 0 6px;
        font-weight: lighter;
        letter-spacing: 1px
      }
      .info-content{
        padding: 4px;
        color: #666666;
        line-height: 23px;
      }
      .info-content img{
        float: left;
        margin: 3px;
      }
      
      /* 定义自定义点样式 */
.markerContentStyle{position:relative;}
.markerContentStyle span{
	background-color: #FFFFFF;
	color:#FF1493;
	width:120px;
	height:80px;
	border:2px solid #D8BFD8;
	FONT-FAMILY:华文行楷;
	position:absolute;
	top:-10px;left:25px;
	white-space:nowrap
	-webkit-border-radius:5px;
	border-radius:5px;
}

/*** copied from demo #43 添加自定义信息窗体 ***/
/* 定义自定义信息窗体样式 */
div.info {
	position: relative;
	z-index: 100;
	border: 1px solid #BCBCBC;
	box-shadow: 0 0 10px #B7B6B6;
	border-radius: 8px;
	background-color: rgba(255,255,255,0.9);
	transition-duration: 0.25s;
}
div.info:hover {
	box-shadow: 0px 0px 15px #0CF;
}

div.info-top {
	position: relative;
	background: none repeat scroll 0 0 #F9F9F9;
	border-bottom: 1px solid #CCC;
	border-radius:5px 5px 0 0;
}
div.info-top div {
	display: inline-block;
	color: #333333;
	font-size:14px;
	font-weight:bold;
	line-height:31px;
	padding:0 10px;
}
div.info-top img {
	position: absolute;
	top: 10px;
	right: 10px;
	transition-duration: 0.25s;
}
div.info-top img:hover{
	box-shadow: 0px 0px 5px #000;
}
div.info-middle {
	font-size:12px;
	padding:10px;
	line-height:21px;
}
div.info-bottom {
	height:0px;
	width:100%;
	clear:both;
	text-align:center;
}
div.info-bottom img{
	position: relative;
	z-index:104;
}
.amap-marker{
	width:300px;;
}
    </style>
    <title>点位地图</title>
  </head>
  <body>
   <div id="container" tabindex="0"></div>
   <div id='panel'></div>
   <input type="hidden" value="${type }" id="type"/>
   <input type="hidden" value="${id }" id="mapid"/>
   <input type="hidden" value="${markid }" id="markid"/>
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=c191eaf5cfd33538f759ce493b9b99ca"></script>
   <script type="text/javascript">
    var map = new AMap.Map('container',{
            resizeEnable: true,
            zoom: 10,
            center: [112.535705, 37.853169]
    });
    addMarker(); 
    function addMarker(){
    	var type = $("#type").val();
    	var id = $("#mapid").val();
    	var markid = $("#markid").val();
    	$.ajax({
    		type: "post",
    		url: "./getmaplist",
    		data: {type :type, id :id, markid :markid, areatype :1},
    		success: function(msg){
    			var data = eval("("+msg+")");
    			var maplist = data.maplist;
    			if(maplist != null && maplist.length > 0){
    				for(var i=0; i<maplist.length; i++){
    					var background;
    					if(maplist[i].background == "" || typeof(maplist[i].background) == "undefined"){
    						background = "008000";
    					}else{
    						background = maplist[i].background.substring(maplist[i].background.indexOf("x")+1);
    					}
    					marker = new AMap.Marker({ 
    			            position:new AMap.LngLat(maplist[i].longitude, maplist[i].latitude),
//     			            content:' <span style="font-size:12px;color:green;background: url(http://www.21cheng.net/themes/led/images/default_map1.gif)"><div style="height:33px; color:#FFF;">  <div style="width:auto; float:left; height:33px; padding:4px 5px 0px 5px; color:#fff; font-size:12px; background:url(http://www.21cheng.net/themes/led/images/default_map1.gif) no-repeat;">'+maplist[i].plotName+'</div>  <div style="float:left; width:5px; height:33px; background:url(http://www.21cheng.net/images/dttb_0_r1.gif) no-repeat;"></div></div>',
//     			            content:' <span style="font-size:12px;background: #'+background+'"><div style="height:33px; color:#FFF;">  <div style="width:auto; float:left; height:33px; padding:4px 5px 0px 5px; color:#fff; font-size:12px; background:#'+background+';">'+maplist[i].plotName+'</div>  <div style="float:left; width:5px; height:33px; background:#'+background+')"></div></div>',
    			            content:' <span style="font-size:12px;background: #'+background+'"><div style="color:#FFF;">  <div style="width:auto; float:left; padding:4px 5px 4px 5px; color:#fff; font-size:12px; background:#'+background+';">'+maplist[i].plotName+'</div>  <div style="float:left; width:5px; background:#'+background+')"></div></div>',
    			            icon:"http://webapi.amap.com/images/marker_sprite.png", 
    			        });  
    			    	marker.setMap(map);  //在地图上添加点
    				}
    			}
    		}
    	});
    }
   </script>
  </body>
</html>