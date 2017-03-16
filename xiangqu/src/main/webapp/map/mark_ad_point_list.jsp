<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>后台管理</title>
<link rel="stylesheet" type="text/css" href="css/common.css" />
<link rel="stylesheet" type="text/css" href="css/main.css" />
<link rel="stylesheet" type="text/css" href="css/page.css" />

<style type="text/css">
.submit_link {
	color: #428BCA;
	text-decoration: none;
	box-sizing: border-box;
	line-height: 35px;
	border-radius: 0;
	background-image: none;
	box-shadow: none;
	background-color: transparent;
	background-repeat: repeat;
	background-position: center;
	border-color: transparent;
	cursor: pointer;
	border: 0 solid #000000;
	font: 14px/1.5 "微软雅黑";
	padding: 0px;
	margin: 0px;
}
</style>
<script type="text/javascript" src="js/libs/modernizr.min.js"></script>
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
<script src="./js/jquery-1.11.3.min.js"></script>
<script src="js/page.js"></script>
<script type="text/javascript">
function del_ad_point(id){
	if(confirm("是否删除")){
		$.ajax({
			type: "post",
			url: "./del_mark_ad_point",
			data: {id :id},
			success: function(data){
				if(data == "success"){
					alert("删除成功");
					window.location.reload();
				}else{
					alert("删除失败");
					window.location.reload();
				}
			}
		});
	}
}

var lolaArray = new Array(20); 
var indexxx = 0;
$(function(){
	for(var i = 0; i< 5; i++){
		var loaction = parseFloat(120.064269+0.020*i).toFixed(6); 
		for(var j = 0; j < 4; j++){
			var latitude = parseFloat(30.896305-0.016*j).toFixed(6);
			lolaArray[parseInt(indexxx)] =  loaction+','+latitude;
			indexxx++;
		}
	}
	$("#li_2").css("color","#428bca");
});
// lolaArray[0] = "120.067348,30.892429";
// lolaArray[1] = "120.067348,30.864429";
// lolaArray[2] = "120.067348,30.836429";
// lolaArray[3] = "120.112348,30.892429";
// lolaArray[4] = "120.112348,30.864429";
// lolaArray[5] = "120.112348,30.836429";
// lolaArray[6] = "120.157348,30.892429";
// lolaArray[7] = "120.157348,30.864429";
// lolaArray[8] = "120.157348,30.836429";
var font = 2;
var bold = 0;
var fontSize = 12;
var zoom = 15;
var key = "c191eaf5cfd33538f759ce493b9b99ca";
var fontColor = "0xFFFFFF";
function makebigmap2(id){
	setadpoint(id, lolaArray[0], 0);
}

function setadpoint(id, array, index){
	if(index == 20){
		return;
	}else{
		var arraylist = array.split(",");
		var longitude = arraylist[0];
		var latitude = arraylist[1];
		$.ajax({
			type: "post",
			url: "mark_ad_point_map2",
			data: {id :id, longitude :longitude, latitude: latitude},
			success: function(msg){
				var data = eval("("+msg+")");
				var list = data.list;
				var returnlongitude = data.returnlongitude;
				var returnlatitude = data.returnlatitude;
				var href = "http://restapi.amap.com/v3/staticmap?location="+returnlongitude+","+returnlatitude;
				href += "&zoom="+zoom+"&size=1024*1024";
				if(list != null && list.length > 0){
					for(var i = 0; i< list.length; i++){
						//标签内容，字符最大数目为15
						var content = list[i].plotName;
						if(content > 15){
							content = content.substring(0,16);
						}
						var longitude = list[i].longitude;
						var latitude = list[i].latitude;
						var background = list[i].background;
						if(background == "" || typeof(background) == "undefined"){
							background = "0x008000";
						}
						if(i == 0){
							href += "&labels="+content+","+font+","+bold+","+fontSize+","+fontColor+","+background+":"+longitude+","+latitude;
						}else{
							href += "|"+content+","+font+","+bold+","+fontSize+","+fontColor+","+background+":"+longitude+","+latitude;
						}
					}
				href += "&key="+key;
				window.open(href);
				}
				index++;
				setadpoint(id, lolaArray[index], index);
			}
		});
	}
}

function makebigmap(id){
	$.ajax({
		type: "post",
		url: "./mark_ad_point_map",
		data: {id :id},
		success: function(msg){
			//第1个点中心点经纬度
			var longitude1 = 119.824582; 
			var latitude1 = 31.126021;
			//第2个点中心点经纬度
			var longitude2 = 119.996582; 
			var latitude2 = 31.126021;
			//第3个点中心点经纬度
			var longitude3 = 120.168582; 
			var latitude3 = 31.126021;
			//第4个点中心点经纬度
			var longitude4 = 120.340582; 
			var latitude4 = 31.126021;
			//第5个点中心点经纬度
			var longitude5 = 120.5212582; 
			var latitude5 = 31.126021;
			//第6个点中心点经纬度
			var longitude6 = 119.824582; 
			var latitude6 = 31.018021;
			//第7个点中心点经纬度
			var longitude7 = 119.996582; 
			var latitude7 = 31.018021;
			//第7个点中心点经纬度
			var longitude8 = 120.168582; 
			var latitude8 = 31.018021;
			//第9个点中心点经纬度
			var longitude9 = 120.340582; 
			var latitude9 = 31.018021;
			//第10个点中心点经纬度
			var longitude10 = 120.5212582; 
			var latitude10 = 31.018021;
			//第11个点中心点经纬度
			var longitude11 = 119.824582; 
			var latitude11 = 30.910021;
			//第12个点中心点经纬度
			var longitude12 = 119.996582; 
			var latitude12 = 30.910021;
			//第13个点中心点经纬度
			var longitude13 = 120.168582; 
			var latitude13 = 30.910021;
			//第14个点中心点经纬度
			var longitude14 = 120.340582; 
			var latitude14 = 30.910021;
			//第15个点中心点经纬度
			var longitude15 = 120.5212582; 
			var latitude15 = 30.910021;
			//第16个点中心点经纬度
			var longitude16 = 119.824582; 
			var latitude16 = 30.802021;
			//第17个点中心点经纬度
			var longitude17 = 119.996582; 
			var latitude17 = 30.802021;
			//第18个点中心点经纬度
			var longitude18 = 120.168582; 
			var latitude18 = 30.802021;
			//第19个点中心点经纬度
			var longitude19 = 120.340582; 
			var latitude19 = 30.802021;
			//第20个点中心点经纬度
			var longitude20 = 120.5212582; 
			var latitude20 = 30.802021;
			var data = eval("("+msg+")");
			var list1 = data.list1;
			var list2 = data.list2;
			var list3 = data.list3;
			var list4 = data.list4;
			var list5 = data.list5;
			var list6 = data.list6;
			var list7 = data.list7;
			var list8 = data.list8;
			var list9 = data.list9;
			var list10 = data.list10;
			var list11 = data.list11;
			var list12 = data.list12;
			var list13 = data.list13;
			var list14 = data.list14;
			var list15 = data.list15;
			var list16 = data.list16;
			var list17 = data.list17;
			var list18 = data.list18;
			var list19 = data.list19;
			var list20 = data.list20;
			var font = 2;
			var bold = 0;
			var fontSize = 12;
			var zoom = 12;
			var key = "c191eaf5cfd33538f759ce493b9b99ca";
			var fontColor = "0xFFFFFF";
			var href1 = "http://restapi.amap.com/v3/staticmap?location="+longitude1+","+latitude1;
			href1 += "&zoom="+zoom+"&size=1024*1024";
			if(list1 != null && list1.length > 0){
				for(var i = 0; i< list1.length; i++){
					//标签内容，字符最大数目为15
					var content = list1[i].plotName;
					if(content > 15){
						content = content.substring(0,16);
					}
					var longitude = list1[i].longitude;
					var latitude = list1[i].latitude;
					var background = list1[i].background;
					if(background == ""){
						background = "0x008000";
					}
					if(i == 0){
						href1 += "&labels="+content+","+font+","+bold+","+fontSize+","+fontColor+","+background+":"+longitude+","+latitude;
					}else{
						href1 += "|"+content+","+font+","+bold+","+fontSize+","+fontColor+","+background+":"+longitude+","+latitude;
					}
				}
			}
			href1 += "&key="+key;
			window.open(href1);
			var href2 = "http://restapi.amap.com/v3/staticmap?location="+longitude2+","+latitude2;
			href2 += "&zoom="+zoom+"&size=1024*1024";
			if(list2 != null && list2.length > 0){
				for(var j = 0; j< list2.length; j++){
					//标签内容，字符最大数目为15
					var content = list2[j].plotName;
					if(content > 15){
						content = content.substring(0,16);
					}
					var longitude = list2[j].longitude;
					var latitude = list2[j].latitude;
					var background = list2[j].background;
					if(background == ""){
						background = "0x008000";
					}
					if(j == 0){
						href2 += "&labels="+content+","+font+","+bold+","+fontSize+","+fontColor+","+background+":"+longitude+","+latitude;
					}else{
						href2 += "|"+content+","+font+","+bold+","+fontSize+","+fontColor+","+background+":"+longitude+","+latitude;
					}
				}
			}
			href2 += "&key="+key;
			window.open(href2);  
			
			var href3 = "http://restapi.amap.com/v3/staticmap?location="+longitude3+","+latitude3;
			href3 += "&zoom="+zoom+"&size=1024*1024";
			if(list3 != null && list3.length > 0){
				for(var k = 0; k< list3.length; k++){
					//标签内容，字符最大数目为15
					var content = list3[k].plotName;
					if(content > 15){
						content = content.substring(0,16);
					}
					var longitude = list3[k].longitude;
					var latitude = list3[k].latitude;
					var background = list3[k].background;
					if(background == ""){
						background = "0x008000";
					}
					if(k == 0){
						href3 += "&labels="+content+","+font+","+bold+","+fontSize+","+fontColor+","+background+":"+longitude+","+latitude;
					}else{
						href3 += "|"+content+","+font+","+bold+","+fontSize+","+fontColor+","+background+":"+longitude+","+latitude;
					}
				}
			}
			href3 += "&key="+key;
			window.open(href3);  
			
			var href4 = "http://restapi.amap.com/v3/staticmap?location="+longitude4+","+latitude4;
			href4 += "&zoom="+zoom+"&size=1024*1024";
			if(list4 != null && list4.length > 0){
				for(var l = 0; l< list4.length; l++){
					//标签内容，字符最大数目为15
					var content = list4[l].plotName;
					if(content > 15){
						content = content.substring(0,16);
					}
					var longitude = list4[l].longitude;
					var latitude = list4[l].latitude;
					var background = list4[l].background;
					if(background == ""){
						background = "0x008000";
					}
					if(l == 0){
						href4 += "&labels="+content+","+font+","+bold+","+fontSize+","+fontColor+","+background+":"+longitude+","+latitude;
					}else{
						href4 += "|"+content+","+font+","+bold+","+fontSize+","+fontColor+","+background+":"+longitude+","+latitude;
					}
				}
			}
			href4 += "&key="+key;
			window.open(href4);  
			
			var href5 = "http://restapi.amap.com/v3/staticmap?location="+longitude5+","+latitude5;
			href5 += "&zoom="+zoom+"&size=1024*1024";
			if(list5 != null && list5.length > 0){
				for(var m = 0; m< list5.length; m++){
					//标签内容，字符最大数目为15
					var content = list5[m].plotName;
					if(content > 15){
						content = content.substring(0,16);
					}
					var longitude = list5[m].longitude;
					var latitude = list5[m].latitude;
					var background = list5[m].background;
					if(background == ""){
						background = "0x008000";
					}
					if(m == 0){
						href5 += "&labels="+content+","+font+","+bold+","+fontSize+","+fontColor+","+background+":"+longitude+","+latitude;
					}else{
						href5 += "|"+content+","+font+","+bold+","+fontSize+","+fontColor+","+background+":"+longitude+","+latitude;
					}
				}
			}
			href5 += "&key="+key;
			window.open(href5);  

			var href6 = "http://restapi.amap.com/v3/staticmap?location="+longitude6+","+latitude6;
			href6 += "&zoom="+zoom+"&size=1024*1024";
			if(list6 != null && list6.length > 0){
				for(var n = 0; n< list6.length; n++){
					//标签内容，字符最大数目为15
					var content = list6[n].plotName;
					if(content > 15){
						content = content.substring(0,16);
					}
					var longitude = list6[n].longitude;
					var latitude = list6[n].latitude;
					var background = list6[n].background;
					if(background == ""){
						background = "0x008000";
					}
					if(n == 0){
						href6 += "&labels="+content+","+font+","+bold+","+fontSize+","+fontColor+","+background+":"+longitude+","+latitude;
					}else{
						href6 += "|"+content+","+font+","+bold+","+fontSize+","+fontColor+","+background+":"+longitude+","+latitude;
					}
				}
			}
			href6 += "&key="+key;
			window.open(href6); 

			var href7 = "http://restapi.amap.com/v3/staticmap?location="+longitude7+","+latitude7;
			href7 += "&zoom="+zoom+"&size=1024*1024";
			if(list7 != null && list7.length > 0){
				for(var b = 0; b< list7.length; n++){
					//标签内容，字符最大数目为15
					var content = list7[b].plotName;
					if(content > 15){
						content = content.substring(0,16);
					}
					var longitude = list7[b].longitude;
					var latitude = list7[b].latitude;
					var background = list7[b].background;
					if(background == ""){
						background = "0x008000";
					}
					if(b == 0){
						href7 += "&labels="+content+","+font+","+bold+","+fontSize+","+fontColor+","+background+":"+longitude+","+latitude;
					}else{
						href7 += "|"+content+","+font+","+bold+","+fontSize+","+fontColor+","+background+":"+longitude+","+latitude;
					}
				}
			}
			href7 += "&key="+key;
			window.open(href7); 

			var href8 = "http://restapi.amap.com/v3/staticmap?location="+longitude8+","+latitude8;
			href8 += "&zoom="+zoom+"&size=1024*1024";
			if(list8 != null && list8.length > 0){
				for(var v = 0; v< list8.length; v++){
					//标签内容，字符最大数目为15
					var content = list8[v].plotName;
					if(content > 15){
						content = content.substring(0,16);
					}
					var longitude = list8[v].longitude;
					var latitude = list8[v].latitude;
					var background = list8[v].background;
					if(background == ""){
						background = "0x008000";
					}
					if(v == 0){
						href8 += "&labels="+content+","+font+","+bold+","+fontSize+","+fontColor+","+background+":"+longitude+","+latitude;
					}else{
						href8 += "|"+content+","+font+","+bold+","+fontSize+","+fontColor+","+background+":"+longitude+","+latitude;
					}
				}
			}
			href8 += "&key="+key;
			window.open(href8); 

			var href9 = "http://restapi.amap.com/v3/staticmap?location="+longitude9+","+latitude9;
			href9 += "&zoom="+zoom+"&size=1024*1024";
			if(list9 != null && list9.length > 0){
				for(var c = 0; c< list9.length; c++){
					//标签内容，字符最大数目为15
					var content = list9[c].plotName;
					if(content > 15){
						content = content.substring(0,16);
					}
					var longitude = list9[c].longitude;
					var latitude = list9[c].latitude;
					var background = list9[c].background;
					if(background == ""){
						background = "0x008000";
					}
					if(c == 0){
						href9 += "&labels="+content+","+font+","+bold+","+fontSize+","+fontColor+","+background+":"+longitude+","+latitude;
					}else{
						href9 += "|"+content+","+font+","+bold+","+fontSize+","+fontColor+","+background+":"+longitude+","+latitude;
					}
				}
			}
			href9 += "&key="+key;
			window.open(href9); 

			var href10 = "http://restapi.amap.com/v3/staticmap?location="+longitude10+","+latitude10;
			href10 += "&zoom="+zoom+"&size=1024*1024";
			if(list10 != null && list10.length > 0){
				for(var x = 0; x< list10.length; x++){
					//标签内容，字符最大数目为15
					var content = list10[x].plotName;
					if(content > 15){
						content = content.substring(0,16);
					}
					var longitude = list10[x].longitude;
					var latitude = list10[x].latitude;
					var background = list10[x].background;
					if(background == ""){
						background = "0x008000";
					}
					if(x == 0){
						href10 += "&labels="+content+","+font+","+bold+","+fontSize+","+fontColor+","+background+":"+longitude+","+latitude;
					}else{
						href10 += "|"+content+","+font+","+bold+","+fontSize+","+fontColor+","+background+":"+longitude+","+latitude;
					}
				}
			}
			href10 += "&key="+key;
			window.open(href10); 

			var href11 = "http://restapi.amap.com/v3/staticmap?location="+longitude11+","+latitude11;
			href11 += "&zoom="+zoom+"&size=1024*1024";
			if(list11 != null && list11.length > 0){
				for(var z = 0; z< list11.length; z++){
					//标签内容，字符最大数目为15
					var content = list11[z].plotName;
					if(content > 15){
						content = content.substring(0,16);
					}
					var longitude = list11[z].longitude;
					var latitude = list11[z].latitude;
					var background = list11[z].background;
					if(background == ""){
						background = "0x008000";
					}
					if(z == 0){
						href11 += "&labels="+content+","+font+","+bold+","+fontSize+","+fontColor+","+background+":"+longitude+","+latitude;
					}else{
						href11 += "|"+content+","+font+","+bold+","+fontSize+","+fontColor+","+background+":"+longitude+","+latitude;
					}
				}
			}
			href11 += "&key="+key;
			window.open(href11); 

			var href12 = "http://restapi.amap.com/v3/staticmap?location="+longitude12+","+latitude12;
			href12 += "&zoom="+zoom+"&size=1024*1024";
			if(list12 != null && list12.length > 0){
				for(var h = 0; h< list12.length; h++){
					//标签内容，字符最大数目为15
					var content = list12[h].plotName;
					if(content > 15){
						content = content.substring(0,16);
					}
					var longitude = list12[h].longitude;
					var latitude = list12[h].latitude;
					var background = list12[h].background;
					if(background == ""){
						background = "0x008000";
					}
					if(h == 0){
						href12 += "&labels="+content+","+font+","+bold+","+fontSize+","+fontColor+","+background+":"+longitude+","+latitude;
					}else{
						href12 += "|"+content+","+font+","+bold+","+fontSize+","+fontColor+","+background+":"+longitude+","+latitude;
					}
				}
			}
			href12 += "&key="+key;
			window.open(href12); 

			var href13 = "http://restapi.amap.com/v3/staticmap?location="+longitude13+","+latitude13;
			href13 += "&zoom="+zoom+"&size=1024*1024";
			if(list13 != null && list13.length > 0){
				for(var g = 0; g< list13.length; g++){
					//标签内容，字符最大数目为15
					var content = list13[g].plotName;
					if(content > 15){
						content = content.substring(0,16);
					}
					var longitude = list13[g].longitude;
					var latitude = list13[g].latitude;
					var background = list13[g].background;
					if(background == ""){
						background = "0x008000";
					}
					if(g == 0){
						href13 += "&labels="+content+","+font+","+bold+","+fontSize+","+fontColor+","+background+":"+longitude+","+latitude;
					}else{
						href13 += "|"+content+","+font+","+bold+","+fontSize+","+fontColor+","+background+":"+longitude+","+latitude;
					}
				}
			}
			href13 += "&key="+key;
			window.open(href13); 

			var href14 = "http://restapi.amap.com/v3/staticmap?location="+longitude14+","+latitude14;
			href14 += "&zoom="+zoom+"&size=1024*1024";
			if(list14 != null && list14.length > 0){
				for(var f = 0; f< list14.length; f++){
					//标签内容，字符最大数目为15
					var content = list14[f].plotName;
					if(content > 15){
						content = content.substring(0,16);
					}
					var longitude = list14[f].longitude;
					var latitude = list14[f].latitude;
					var background = list14[f].background;
					if(background == ""){
						background = "0x008000";
					}
					if(f == 0){
						href14 += "&labels="+content+","+font+","+bold+","+fontSize+","+fontColor+","+background+":"+longitude+","+latitude;
					}else{
						href14 += "|"+content+","+font+","+bold+","+fontSize+","+fontColor+","+background+":"+longitude+","+latitude;
					}
				}
			}
			href14 += "&key="+key;
			window.open(href14); 

			var href15 = "http://restapi.amap.com/v3/staticmap?location="+longitude15+","+latitude15;
			href15 += "&zoom="+zoom+"&size=1024*1024";
			if(list15 != null && list15.length > 0){
				for(var d = 0; d< list15.length; d++){
					//标签内容，字符最大数目为15
					var content = list15[d].plotName;
					if(content > 15){
						content = content.substring(0,16);
					}
					var longitude = list15[d].longitude;
					var latitude = list15[d].latitude;
					var background = list15[d].background;
					if(background == ""){
						background = "0x008000";
					}
					if(d == 0){
						href15 += "&labels="+content+","+font+","+bold+","+fontSize+","+fontColor+","+background+":"+longitude+","+latitude;
					}else{
						href15 += "|"+content+","+font+","+bold+","+fontSize+","+fontColor+","+background+":"+longitude+","+latitude;
					}
				}
			}
			href15 += "&key="+key;
			window.open(href15); 

			var href16 = "http://restapi.amap.com/v3/staticmap?location="+longitude16+","+latitude16;
			href16 += "&zoom="+zoom+"&size=1024*1024";
			if(list16 != null && list16.length > 0){
				for(var s = 0; s< list16.length; s++){
					//标签内容，字符最大数目为15
					var content = list16[s].plotName;
					if(content > 15){
						content = content.substring(0,16);
					}
					var longitude = list16[s].longitude;
					var latitude = list16[s].latitude;
					var background = list16[s].background;
					if(background == ""){
						background = "0x008000";
					}
					if(s == 0){
						href16 += "&labels="+content+","+font+","+bold+","+fontSize+","+fontColor+","+background+":"+longitude+","+latitude;
					}else{
						href16 += "|"+content+","+font+","+bold+","+fontSize+","+fontColor+","+background+":"+longitude+","+latitude;
					}
				}
			}
			href16 += "&key="+key;
			window.open(href16); 

			var href17 = "http://restapi.amap.com/v3/staticmap?location="+longitude17+","+latitude17;
			href17 += "&zoom="+zoom+"&size=1024*1024";
			if(list17 != null && list17.length > 0){
				for(var a = 0; a< list17.length; a++){
					//标签内容，字符最大数目为15
					var content = list17[a].plotName;
					if(content > 15){
						content = content.substring(0,16);
					}
					var longitude = list17[a].longitude;
					var latitude = list17[a].latitude;
					var background = list17[a].background;
					if(background == ""){
						background = "0x008000";
					}
					if(a == 0){
						href17 += "&labels="+content+","+font+","+bold+","+fontSize+","+fontColor+","+background+":"+longitude+","+latitude;
					}else{
						href17 += "|"+content+","+font+","+bold+","+fontSize+","+fontColor+","+background+":"+longitude+","+latitude;
					}
				}
			}
			href17 += "&key="+key;
			window.open(href17); 

			var href18 = "http://restapi.amap.com/v3/staticmap?location="+longitude18+","+latitude18;
			href18 += "&zoom="+zoom+"&size=1024*1024";
			if(list18 != null && list18.length > 0){
				for(var t = 0; t< list18.length; t++){
					//标签内容，字符最大数目为15
					var content = list18[t].plotName;
					if(content > 15){
						content = content.substring(0,16);
					}
					var longitude = list18[t].longitude;
					var latitude = list18[t].latitude;
					var background = list18[t].background;
					if(background == ""){
						background = "0x008000";
					}
					if(t == 0){
						href18 += "&labels="+content+","+font+","+bold+","+fontSize+","+fontColor+","+background+":"+longitude+","+latitude;
					}else{
						href18 += "|"+content+","+font+","+bold+","+fontSize+","+fontColor+","+background+":"+longitude+","+latitude;
					}
				}
			}
			href18 += "&key="+key;
			window.open(href18); 

			var href19 = "http://restapi.amap.com/v3/staticmap?location="+longitude19+","+latitude19;
			href19 += "&zoom="+zoom+"&size=1024*1024";
			if(list19 != null && list19.length > 0){
				for(var r = 0; r< list19.length; r++){
					//标签内容，字符最大数目为15
					var content = list19[r].plotName;
					if(content > 15){
						content = content.substring(0,16);
					}
					var longitude = list19[r].longitude;
					var latitude = list19[r].latitude;
					var background = list19[r].background;
					if(background == ""){
						background = "0x008000";
					}
					if(r == 0){
						href19 += "&labels="+content+","+font+","+bold+","+fontSize+","+fontColor+","+background+":"+longitude+","+latitude;
					}else{
						href19 += "|"+content+","+font+","+bold+","+fontSize+","+fontColor+","+background+":"+longitude+","+latitude;
					}
				}
			}
			href19 += "&key="+key;
			window.open(href19); 

			var href20 = "http://restapi.amap.com/v3/staticmap?location="+longitude20+","+latitude20;
			href20 += "&zoom="+zoom+"&size=1024*1024";
			if(list20 != null && list20.length > 0){
				for(var e = 0; e< list20.length; e++){
					//标签内容，字符最大数目为15
					var content = list20[e].plotName;
					if(content > 15){
						content = content.substring(0,16);
					}
					var longitude = list20[e].longitude;
					var latitude = list20[e].latitude;
					var background = list20[e].background;
					if(background == ""){
						background = "0x008000";
					}
					if(e == 0){
						href20 += "&labels="+content+","+font+","+bold+","+fontSize+","+fontColor+","+background+":"+longitude+","+latitude;
					}else{
						href20 += "|"+content+","+font+","+bold+","+fontSize+","+fontColor+","+background+":"+longitude+","+latitude;
					}
				}
			}
			href20 += "&key="+key;
			window.open(href20); 
			return;
		}
	});
}

function makepointmap(markid){
	window.location.href = "./make_mark_point_map?markid="+markid+"&areatype=0";
}

function add_ad_point(markid){
	window.location.href = "./select_mark_ad_point?markid="+markid+"&areatype=0";
}
</script>
</head>
<body>
	<jsp:include page="./sidebar.jsp"></jsp:include>
	<div class="main-wrap">

		<div class="crumb-wrap">
			<div class="crumb-list">
				<i class="icon-font"></i>
				<a href="./home_page">首页</a>
				<span class="crumb-step">&gt;</span><span class="crumb-name">广告点位管理</span>
			</div>
		</div>
		<form action="./mark_ad_point_list" method="post" name="pageForm" id="pageForm">
		<div class="search-wrap">
				<div class="search-content">
				</div>
			</div>
		<div class="result-wrap">
			<div class="result-title">
					<div class="result-list">
					</div>
				</div>
			<div class="result-content">
				<table class="result-tab" width="100%">
					<tr>
						<th>点位</th>
						<th>操作</th>

					</tr>
					<c:forEach items="${list}" var="ad">
						<tr>
							<td>${ad.pointname}</td>
							<td>
								<input class="btn btn-primary btn2" name="creat_task" value="生成点位地图" type="button" onclick="makebigmap2(${ad.id})">
								<input class="btn btn-primary btn2" name="creat_task" value="生成动态地图" type="button" onclick="makepointmap(${ad.id})">
								<input class="btn btn-primary btn2" name="creat_task" value="添加" type="button" onclick="add_ad_point(${ad.id})">
								<input class="btn btn-primary btn2" name="creat_task" value="删除" type="button" onclick="del_ad_point(${ad.id})">
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		</form>
	</div>

</body>
</html>