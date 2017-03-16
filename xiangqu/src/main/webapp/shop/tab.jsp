<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style type="text/css">
.tab_bar {
	position: fixed;
	z-index: 999;
	width: 100%;
	bottom: 0px;
	left: 0px;
	background-color: #F6F6F6;
	border-top: #ccc 1px solid;
	font-family: serif;
	font-size: 16px;
}

.tab_border {
	width: 33%;
	float: left;
	position: relative;
	margin-bottom: 1px;
}

.tab_img {
	position: absolute;
	background-size: 22px 22px;
	width: 22px;
	height: 22px;
	margin-left: -11px;
	margin-top: 5px;
	left: 50%;
}

.tab_text {
	text-align: center;
	margin-top: 7px;
	color: #727272;
	font-size: 10px;
	line-height: 20px;
}

.tab_text_selected {
	text-align: center;
	margin-top: 7px;
	color: #DA4E4F;
	font-size: 10px;
	line-height: 20px;
}

.tab_bar a {
	text-decoration: none;
}
</style>
<script type="text/javascript">
	//记得引用jquery；
	function setTabSelected(index) {
		switch (index) {
		case 1:
			$('#tab1text').removeClass('tab_text');
			$('#tab1text').addClass('tab_text_selected');
			$('#tab1img').css("background-image",
					"url('./imgs/share_selected.png')");
			break;
		case 2:
			$('#tab2text').removeClass('tab_text');
			$('#tab2text').addClass('tab_text_selected');
			$('#tab2img').css("background-image",
					"url('./imgs/earn_selected.png')");
			break;
		case 3:
			$('#tab3text').removeClass('tab_text');
			$('#tab3text').addClass('tab_text_selected');
			$('#tab3img').css("background-image",
					"url('./imgs/shop_selected.png')");
			break;
		case 4:
			$('#tab4text').removeClass('tab_text');
			$('#tab4text').addClass('tab_text_selected');
			$('#tab4img').css("background-image",
					"url('./imgs/my_selected.png')");
			break;
		default:
			break;
		}
	}

	function changeSelected(index) {
		switch (index) {
		case 1:
			window.location.replace('./mission');
			break;
		case 2:
			window.location.replace('./bill');
			break;
		case 3:
			window.location.replace('./orderlist');
			break;
		case 4:
			window.location.replace('./ucenter');
			break;
		default:
			break;
		}
	}
</script>

<div class="tab_bar">
	<div class="tab_border" style="width: 33%;" onclick="changeSelected(1)">
		<div id="tab1img" class="tab_img"
			style="background-image: url('./imgs/share_unselected.png');"></div>
		<br />
		<div id="tab1text" class="tab_text">任务管理</div>
	</div>

<!-- 	<div class="tab_border" onclick="changeSelected(2)">
		<div id="tab2img" class="tab_img"
			style="background-image: url('./imgs/earn_unselected.png');"></div>
		<br />
		<div id="tab2text" class="tab_text">账单明细</div>
	</div> -->

	<div class="tab_border"  style="width: 33%;" onclick="changeSelected(3)">
		<div id="tab3img" class="tab_img"
			style="background-image: url('./imgs/shop_unselected.png');"></div>
		<br />
		<div id="tab3text" class="tab_text">订单管理</div>
	</div>

	<div class="tab_border" style="width: 33%;" onclick="changeSelected(4)">
		<div id="tab4img" class="tab_img"
			style="background-image: url('./imgs/my_unselected.png');"></div>
		<br />
		<div id="tab4text" class="tab_text">商家明细</div>
	</div>

</div>