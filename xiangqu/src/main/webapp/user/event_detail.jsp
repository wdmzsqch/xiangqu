<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no">
<title>活动</title>
<link rel="stylesheet" type="text/css" href="css/common.css" />
<link rel="stylesheet" type="text/css" href="css/event.css" />
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/common.js"></script>

<style type="text/css">
.couponalert {
	width: 290px;
	border-radius: 4px;
	position: fixed;
	background-color: #ffffff;
	height: auto;
	z-index: 100;
	display: none;
}

.getvcode {
	width: 19%;
	height: 39px;
	background: #00d1bc;
	text-align: center;
	line-height: 39px;
	color: #fff;
	font-size: 12px;
	float: left;
	border-radius: 20px;
	margin-left: 3%;
}

input[type=checkbox] {
	display: none;
}

input[type=checkbox]+label {
	background-color: #FFF;
	border: 1px solid #C1CACA;
	box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05), inset 0px -15px 10px -12px
		rgba(0, 0, 0, 0.05);
	padding: 7px;
	border-radius: 3px;
	display: inline-block;
	position: relative;
}

input[type=checkbox]+label:active {
	box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05), inset 0px 1px 3px
		rgba(0, 0, 0, 0.1);
}

input[type=checkbox]:checked+label {
	background-color: #ECF2F7;
	border: 1px solid #92A1AC;
	box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05), inset 0px -15px 10px -12px
		rgba(0, 0, 0, 0.05), inset 15px 10px -12px rgba(255, 255, 255, 0.1);
	color: #243441;
}

input[type=checkbox]:checked+label:after {
	content: '\2714';
	position: absolute;
	top: 0px;
	left: 0px;
	color: #758794;
	width: 100%;
	text-align: center;
	font-size: 1em;
	vertical-align: text-top;
}
</style>

<script type="text/javascript">
	function goback() {
		window.location.href = "./eventlist";
	}

	function selectmale() {
		document.getElementById("male").src = "imgs/checked.png";
		$("#gender").val("男");
		document.getElementById("fmale").src = "imgs/uncheck.png";
	}

	function selectfmale() {
		document.getElementById("fmale").src = "imgs/checked.png";
		$("#gender").val("女");
		document.getElementById("male").src = "imgs/uncheck.png";
	}

	function turntosign() {
		$(".bottom").hide();
		$(".singbutton").removeClass("notuser");
		$(".singbutton").attr("onclick", "addEvent()");
	}

	function addEvent() {
		var name = $("#sign_name").val();
		if (name == "") {
			alert("名字不能为空");
			return;
		}
		var phone = $("#sign_phone").val();
		if (phone == "" || isNaN(phone)) {
			alert("请填写正确的手机号");
			return;
		}

		var age = $("#sign_age").val();
		if (age == "" || isNaN(age)) {
			alert("请填写正确的数字年龄");
			return;
		}

		var sign_address = $("#sign_address").val();
		if (sign_address == "") {
			alert("身份证不能为空");
			return;
		}

		var agree = $("#agreecheck").prop('checked');
		if (!agree) {
			alert("需同意《彩跑报名须知》方可报名");
			return;
		}

		var event_id = $("#event_id").val();
		$("#addEventForm").submit();

		// 		$.ajax({
		// 			type: "post",
		// 			url: "./addEventSign",
		// 			data: $("#addEventForm").serialize(),
		// 			success: function(data){
		// 				if(data == "success"){
		// 					window.location.href = "./lookeventdetail?id=" +event_id;
		// 					return;
		// 				}
		// 				if(data == "error"){
		// 					alert("报名名额已满！请关注其他活动，详情咨询：400-618-2121。");
		// 					return;
		// 				}
		// 			}
		// 		});
	}

	function looksign(event_id) {
		window.location.href = "./looksign?event_id=" + event_id;
	}

	//-------------------------------------------------

	function addEventEX() {
		var hasMoblie = $("#hasMoblie").val();
		if (hasMoblie == 1) {
			var name = $("#sign_name").val();
			if (name == "") {
				alert("名字不能为空");
				return;
			}
			var phone = $("#sign_phone").val();
			if (phone == "" || isNaN(phone)) {
				alert("请填写正确的手机号");
				return;
			}

			var age = $("#sign_age").val();
			if (age == "" || isNaN(age)) {
				alert("请填写正确的数字年龄");
				return;
			}
			var sign_address = $("#sign_address").val();
			if (sign_address == "") {
				alert("身份证不能为空");
				return;
			}

			var agree = $("#agreecheck").prop('checked');
			if (!agree) {
				alert("需同意《彩跑报名须知》方可报名");
				return;
			}

			var event_id = $("#event_id").val();
			$("#addEventForm").submit();
		} else {
			$(".couponalert").css("left", ($(".mask").width() - 290) / 2);
			$(".couponalert").css("top", ($(".mask").height() - $(".couponalert").height()) / 2);
			$(".mask").show();
			$(".couponalert").show();
		}

	}

	function hide() {
		$(".mask").hide();
		$(".couponalert").hide();
	}

	function submitForm() {
		var moblie = $("input[name=moblie]").val();
		if (moblie == "") {
			alert("手机号不能为空");
			return;
		}
		var vcode = $("input[name=vcode]").val();
		if (vcode == "") {
			alert("验证码不能为空");
			return;
		}
		$.ajax({
			type : "post",
			url : "./addUserMoblie",
			data : {
				moblie : moblie,
				vcode : vcode
			},
			success : function(data) {
				if (data == "success") {
					window.location.reload();
				}
				if (data == "error") {
					alert("验证码错误");
					return;
				}
				if (data == "error1") {
					alert("验证码已过期");
					return;
				}
			}
		});
	}

	function getvcode() {
		var moblie = $("input[name=moblie]").val();
		if (moblie == "") {
			alert("请输入手机号码");
			return;
		}
		$.ajax({
			type : "post",
			url : "../api/user/adlist",
			success : function(data) {
				$.ajax({
					type : "post",
					url : "../api/user/getvcode",
					data : {
						moblie : moblie,
						type : "page"
					},
					success : function(data) {
						times();
					}
				});
			}
		});

	}

	var wait = 60;
	function times() {
		if (wait == 0) {
			$(".getvcode").css("background", "#00d1bc");
			$(".getvcode").attr("onclick", "getvcode()");
			$(".getvcode").text("获取");
			wait = 60;
		} else {
			$(".getvcode").css("background", "#d2d2d2");
			$(".getvcode").removeAttr("onclick");
			$(".getvcode").text(wait + "″");
			wait--;
			setTimeout(function() {
				times()
			}, 1000)
		}
	}
</script>
</head>
<body style="font-family: '微软雅黑'; background: #f2f2ef;">
	<form action="./addEventSign" id="addEventForm" method="post" enctype="multipart/form-data">
		<input type="hidden" value="${event.id }" name="eventId" id="event_id" />
		<input type="hidden" value="${sign.id }" name="id" />
		<input type="hidden" value="${shareuserid}" name="shareuserid" />
		<input type="hidden" value="${event.income}" name="income" />
		<!-- 		<div style="position: fixed; top: 7px; left: 4%; width: 31px; height: 31px; background-image: url(imgs/back_icon.png); background-size: 100% 100%;" onclick="goback()"></div> -->
		<img src="${fileUrl }${event.pic}" width="100%">
		<div style="width: 100%; background: #fff; padding-bottom: 10px;">
			<div style="width: 92%; margin: 0 auto;">
				<div style="line-height: 20px;">${event.name }</div>
				<div style="line-height: 20px; font-size: 13px; color: #6a6a6a;">${event.detail }</div>
			</div>
		</div>
		<c:if test="${empty sign }">
			<div style="width: 100%; background: #fff; margin-top: 10px; height: auto;">
				<div style="width: 92%; margin: 0 auto;">
					<div style="width: 100%; height: 43px; line-height: 43px; border-bottom: 1px solid #e8e8e8;">填写报名信息</div>
					<div style="width: 80%; margin: 0 auto; padding-top: 20px; padding-bottom: 20px;">
						<!-- 						<div style="width: 80px; height: 80px; margin: 0 auto;"> -->
						<!-- 						<img src="imgs/add_img.png" style="width: 80px; height: 80px;" id="picimg"> -->
						<!-- 						<input type="file" name="picfile" id="picfile" style="position: relative; top: -80px; width: 80px; height: 80px; opacity: 0;" onchange="change('picimg','picfile')"/> -->
						<!-- 						</div> -->
						<div style="height: 32px; line-height: 32px; margin-top: 20px;">
							<div style="width: 65px; float: left; font-size: 13px;">姓名：</div>
							<input id="sign_name" style="float: left; width: 65%; height: 30px; border: 1px solid #e8e8e8; border-radius: 6px;" name="name" />
						</div>
						<div style="height: 32px; line-height: 32px; margin-top: 20px;">
							<div style="width: 65px; float: left; font-size: 13px;">性别：</div>
							<img src="imgs/checked.png" id="male" style="width: 21px; height: 21px; float: left; margin-top: 5px;" onclick="selectmale()">
							<span style="float: left;">男</span>
							<img src="imgs/uncheck.png" id="fmale" style="width: 21px; height: 21px; float: left; margin-top: 5px; margin-left: 40px;" onclick="selectfmale()">
							<span style="float: left;">女</span>
							<input type="hidden" value="男" id="gender" name="gender" />
						</div>
						<div style="height: 32px; line-height: 32px; margin-top: 20px;">
							<div style="width: 65px; float: left; font-size: 13px;">年龄：</div>
							<input id="sign_age" type="number" style="float: left; width: 65%; height: 30px; border: 1px solid #e8e8e8; border-radius: 6px;" name="age" />
						</div>
						<div style="height: 32px; line-height: 32px; margin-top: 20px;">
							<div style="width: 65px; float: left; font-size: 13px;">电话：</div>
							<input id="sign_phone" style="float: left; width: 65%; height: 30px; border: 1px solid #e8e8e8; border-radius: 6px;" name="phone" />
						</div>
						<div style="height: 32px; line-height: 32px; margin-top: 20px; display: none;">
							<div style="width: 65px;; float: left; font-size: 13px;">身份证号：</div>
							<input id="sign_address" style="float: left; width: 65%; height: 30px; border: 1px solid #e8e8e8; border-radius: 6px;" name="address"  value="defaultidnum"/>
						</div>
						<div style="height: 77px; line-height: 32px; margin-top: 20px;">
							<div style="width: 65px; float: left; font-size: 13px;">备注：</div>
							<textarea style="float: left; width: 65%; height: 75px; border: 1px solid #e8e8e8; border-radius: 6px; resize: none;" name="comment"></textarea>
						</div>
						<div style="height: 77px; line-height: 20px; margin-top: 20px; font-size: 10px; display: none;">
							<input type="checkbox" id="agreecheck" checked="checked" value="" />
							<label for="agreecheck"></label> 我已阅读并同意
							<a style="text-decoration: underline;" href="http://www.xiangqu100.com/colorrun2016.html">《彩跑报名须知》</a>
							中的所有条款。
						</div>

					</div>
					<div class="singbutton" onclick="addEventEX()">提交报名信息</div>
				</div>

				<div style="width: 92%; margin: 0 auto; height: 50px;"></div>
			</div>
		</c:if>
		<c:if test="${not empty sign }">
			<div style="width: 100%; background: #fff; margin-top: 10px; padding-bottom: 15px;">
				<div style="width: 92%; margin: 0 auto;">
					<c:if test="${not empty sign.codePic }">
						<div style="width: 100%; height: 43px; line-height: 43px; border-bottom: 1px solid #e8e8e8; font-size: 13px;">
							报名信息<font color="#ff9a92"> (您已经报名此活动) </font>
						</div>
					</c:if>
					<c:if test="${empty sign.codePic }">
						<div style="width: 100%; height: auto; line-height: 20px; border-bottom: 1px solid #e8e8e8; font-size: 13px;">
							报名信息<font color="#ff9a92"> (报名名额已满！请关注其他活动，详情咨询：400-618-2121。) </font>
						</div>
					</c:if>
					<c:if test="${not empty sign.codePic }">
						<div style="width: 100%; height: 137px; border-bottom: 1px solid #e8e8e8; text-align: center; font-size: 16px;">
							<img src="${fileUrl }${sign.codePic }" style="width: 100px; height: 100px;">
							<br /> <font color="#54daca">序列号&nbsp;&nbsp;${sign.code }</font>
						</div>
					</c:if>
					<c:if test="${not empty sign.pic }">
						<div style="width: 80px; height: 80px; margin: 0 auto;">
							<img src="${fileUrl }${sign.pic }" style="width: 80px; height: 80px;" id="picimg">
						</div>
					</c:if>
					<div style="width: 100%; height: auto; overflow: hidden; line-height: 45px; font-size: 13px;">
						<div style="width: 20%; float: left; color: #6a6a6a;">姓名：</div>
						<div style="width: 70%; float: left; margin-left: 2%;">${sign.name }</div>
					</div>
					<div style="width: 100%; height: auto; overflow: hidden; line-height: 45px; font-size: 13px;">
						<div style="width: 20%; float: left; color: #6a6a6a;">性别：</div>
						<div style="width: 70%; float: left; margin-left: 2%;">${sign.gender }</div>
					</div>
					<div style="width: 100%; height: auto; overflow: hidden; line-height: 45px; font-size: 13px;">
						<div style="width: 20%; float: left; color: #6a6a6a;">年龄：</div>
						<div style="width: 70%; float: left; margin-left: 2%;">${sign.age }</div>
					</div>
					<div style="width: 100%; height: auto; overflow: hidden; line-height: 45px; font-size: 13px;">
						<div style="width: 20%; float: left; color: #6a6a6a;">电话：</div>
						<div style="width: 70%; float: left; margin-left: 2%;">${sign.phone }</div>
					</div>
					<div style="width: 100%; height: auto; overflow: hidden; line-height: 45px; font-size: 13px;">
						<div style="width: 20%; float: left; color: #6a6a6a;">地址：</div>
						<div style="width: 70%; float: left; margin-left: 2%;">${sign.address }</div>
					</div>
					<div style="width: 100%; height: auto; overflow: hidden; line-height: 45px; font-size: 13px;">
						<div style="width: 20%; float: left; color: #6a6a6a;">备注：</div>
						<div style="width: 70%; float: left; margin-left: 2%; line-height: 17px; margin-top: 12px;">${sign.comment }</div>
					</div>
				</div>
			</div>
		</c:if>
		<!-- 	<div class="bottom"> -->
		<%-- 		<c:if test="${empty sign }"> --%>
		<!-- 		<div class="applicationbutton" onclick="turntosign()">我要报名</div> -->
		<%-- 		</c:if> --%>
		<%-- 		<c:if test="${not empty sign }"> --%>
		<%-- 		<div class="lookapplicationbutton" onclick="looksign(${event.id })">查看报名信息</div> --%>
		<%-- 		</c:if> --%>
		<!-- 	</div> -->
	</form>
	<input type="hidden" value="${hasMoblie }" id="hasMoblie">




	<div class="mask" style="display: none;" onclick="hide()"></div>
	<div class="couponalert">
		<form action="" method="post" id="couponuserForm">
			<div style="width: 100%; height: 36px; text-align: center; line-height: 36px;">第一次报名请输入手机号</div>
			<div style="width: 100%; height: 40px; text-align: center;">
				<input type="text" name="moblie" style="width: 80%; height: 27px; border: 1px solid #e8e8e8; padding-left: 5px;" placeholder="请输入手机号" />
			</div>
			<div style="width: 100%; height: 40px; text-align: center;">
				<input type="text" name="vcode" style="width: 50%; margin-left: 9%; height: 27px; border: 1px solid #e8e8e8; padding-left: 5px; float: left; margin-top: 2px;" placeholder="请输入验证码" />
				<div class="getvcode" onclick="getvcode()">获取</div>
			</div>
			<div style="width: 100%; height: 60px;">
				<div style="width: 60px; height: 30px; float: left; background: #00d1bc; text-align: center; line-height: 30px; border-radius: 6px; margin-left: 50px; color: #fff; margin-top: 13px;"
					onclick="submitForm()">提交</div>
				<div style="width: 60px; height: 30px; float: left; background: #ff5c4b; text-align: center; line-height: 30px; border-radius: 6px; color: #fff; margin-left: 30px; margin-top: 13px;"
					onclick="hide()">取消</div>
			</div>
		</form>
	</div>



	<%@ include file="cs.jsp"%>
	<%
		CS cs = new CS(1256812462);
		cs.setHttpServlet(request, response);
		String imgurl = cs.trackPageView();
	%>
	<img src="<%=imgurl%>" width="0" height="0" />
</body>
</html>