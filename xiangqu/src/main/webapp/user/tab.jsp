<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css"> 
.tab_bar{
position:fixed;
z-index:999;
width:100%;
bottom:0px;
left:0px;
background-color:#F6F6F6;
border-top: #ccc 1px solid;
font-family: serif;
}
.tab_border{
width:25%;
float:left;
position:relative;
margin-bottom:1px;
}

.tab_img1{
	position:absolute;
	background-size:25.5px 17.5px;
	width: 25.5px;
	height: 17.5px;
	margin-left:-11px;
	margin-top:5px;
	left:50%;
}
.tab_img2{
	position:absolute;
	background-size:25px 21px;
	width: 25px;
	height: 21px;
	margin-left:-11px;
	margin-top:5px;
	left:50%;
}

.tab_img3{
	position:absolute;
	background-size:26.5px 21px;
	width: 26.5px;
	height: 21px;
	margin-left:-11px;
	margin-top:5px;
	left:50%;
}

.tab_img5{
	position:absolute;
	background-size:26.5px 21px;
	width: 26.5px;
	height: 21px;
	margin-left:-11px;
	margin-top:5px;
	left:50%;
}

.tab_img4{
	position:absolute;
	background-size:20px 21px;
	width: 20px;
	height: 21px;
	margin-left:-11px;
	margin-top:5px;
	left:50%;
}

.tab_text{
text-align:center;
margin-top:7px;
color: #727272;
font-size: 10px;
line-height: 20px;
}

.tab_text_selected{
text-align:center;
margin-top:7px;
color: #00d1bc;
font-size: 10px;
line-height: 20px;
}

.tab_bar a{
text-decoration:none;
}

</style> 
<script type="text/javascript">
//记得引用jquery；
function setTabSelected(index){
	switch(index)
	{
	case 1:
	  $('#tab1text').removeClass('tab_text');
	  $('#tab1text').addClass('tab_text_selected');
	  $('#tab1img').css("background-image","url('./imgs/share_icon.png')");
	  break;
	case 2:
		$('#tab2text').removeClass('tab_text');
		$('#tab2text').addClass('tab_text_selected');
		$('#tab2img').css("background-image","url('./imgs/earn_icon.png')");
	  break;
	case 3:
		$('#tab3text').removeClass('tab_text');
		$('#tab3text').addClass('tab_text_selected');
		$('#tab3img').css("background-image","url('./imgs/shop_icon.png')");
	  break;
	case 4:
		$('#tab4text').removeClass('tab_text');
		$('#tab4text').addClass('tab_text_selected');
		$('#tab4img').css("background-image","url('./imgs/my_icon.png')");
	  break;
	case 5:
		$('#tab5text').removeClass('tab_text');
		$('#tab5text').addClass('tab_text_selected');
		$('#tab5img').css("background-image","url('./imgs/coupon_icon.png')");
	  break;
	default:
	  break;
	}
}

function changeSelected(index){
	switch(index)
	{
	case 1:
		window.location.replace('./missiongeo');
// 		window.location.replace('./mission');
	  break;
	case 2:
		window.location.replace('./today_earn');
	  break;
	case 3:
		window.location.replace('./mall');
	  break;
	case 4:
		window.location.replace('./ucenter');
	  break;
	case 5:
		window.location.replace('./coupon');
	  break;
	default:
	  break;
	}
}
</script>

<div class="tab_bar">
<div class="tab_border" onclick="changeSelected(1)">
<div id="tab1img" class="tab_img1" style="background-image:url('./imgs/unshare_icon.png'); "></div>
<br/>
<div id="tab1text" class="tab_text">我分享</div>
</div>

<div class="tab_border" onclick="changeSelected(2)">
<div id="tab2img" class="tab_img2" style="background-image:url('./imgs/unearn.png'); "></div>
<br/>
<div id="tab2text" class="tab_text">我收益</div>
</div>

<div class="tab_border" onclick="changeSelected(3)">
<div id="tab3img" class="tab_img3" style="background-image:url('./imgs/unshop_icon.png'); "></div>
<br/>
<div id="tab3text" class="tab_text">我商城</div>
</div>
<!-- <div class="tab_border" onclick="changeSelected(5)"> -->
<!-- <div id="tab5img" class="tab_img5" style="background-image:url('./imgs/uncoupon_icon.png'); "></div> -->
<!-- <br/> -->
<!-- <div id="tab5text" class="tab_text">优惠券</div> -->
<!-- </div> -->
<div class="tab_border" onclick="changeSelected(4)">
<div id="tab4img" class="tab_img4" style="background-image:url('./imgs/unmy_icon.png'); "></div>
<c:if test="${count > 0 }">
<div style="position: absolute; width: 8px; height: 8px; border-radius: 4px; left: 55%; top: 5px; background: #FF0000;"></div>
</c:if>
<br/>
<div id="tab4text" class="tab_text">我的</div>
</div>

</div>