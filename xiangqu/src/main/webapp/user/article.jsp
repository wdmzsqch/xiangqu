<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no">
<link rel="stylesheet" type="text/css" href="./css/common.css" />
<style type="text/css">
a:link {
	text-decoration: none;
	color: black;
}

a:hover {
	text-decoration: none;
	color: black;
}

a:active {
	text-decoration: none;
	color: black;
}

a:visited {
	text-decoration: none;
	color: black;
}

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
</style>

<script src="./js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	$(function() {
		var width = document.documentElement.clientWidth;
		var inputwidth = width - 120;
		$("#publishinput").css("width", inputwidth);
	})

	function publishcontent() {
		var hasMoblie = $("#hasMoblie").val();
		if (hasMoblie == 1) {
			var content = $("#publishinput").val();
			if (content == '') {
				alert("请输入评论内容!");
				return;
			}
			var relative_id = $("#relative_id").val();
			var type = $("#type").val();
			$("#publishbtn").removeAttr("onclick");
			$.ajax({
				url : './publishcontent',
				type : 'post',
				data : {
					content : content,
					relative_id : relative_id,
					type : type
				},
				success : function(msg) {
					alert("发布评论成功!");
					window.location.reload();
				}
			});

		} else {
			$(".couponalert").css("left", ($(".mask").width() - 290) / 2);
			$(".couponalert").css("bottom", (window.screen.availHeight - $(".couponalert").height()) / 2);
			$(".mask").show();
			$(".couponalert").show();
		}
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

	function hide() {
		$(".mask").hide();
		$(".couponalert").hide();
	}
</script>
<title>${article.title }</title>
</head>
<body>
	<div style="text-align: center;">${article.detail }</div>
	<div>
		<div style="text-align: right; width: 100%; height: 30px; font-size: 12px; line-height: 30px; border-bottom: 1px solid #e8e8e8;">
			<a href="./comment?relative_id=${relative_id}&type=2">更多评论></a>
		</div>
		<c:forEach items="${commentlist }" var="comment" varStatus="st">
			<div style="position: relative; padding-top: 13px; min-height: 50px; padding-bottom: 5px; border-bottom: 1px solid #e8e8e8;">
				<img style="position: absolute; left: 10px; top: 14px; width: 40px; height: 40px; border-radius: 50%;"
					src="
				<c:if test="${fn:contains(comment.user.pic, 'http://')}">${comment.user.pic}</c:if>
				<c:if test="${!fn:contains(comment.user.pic, 'http://')}">${fileRootUrl}${comment.user.pic}</c:if>">
				<div style="padding-left: 60px; height: auto; overflow: hidden; padding-right: 10px;">
					<font style="float: left; color: #00d1bc; font-size: 12px;">${comment.user.nickName }</font> <font style="float: right; color: #bfbfbf; font-size: 12px;">${lsize - st.index}</font>
					<div style="clear: both; color: #bfbfbf; line-height: 12px; font-size: 10px;">
						<fmt:formatDate value="${comment.publishTime }" pattern="yyyy-MM-dd" />
					</div>
					<div style="clear: both; line-height: 16px; font-size: 16px; padding-top: 1px; word-break: break-all; word-wrap: break-word;">${comment.content }</div>
				</div>
			</div>
		</c:forEach>
		<div style="width: 100%; height: 50px"></div>
		<div style="position: fixed; height: 49px; border-top: 1px solid #e5e5e5; width: 100%; bottom: 0px; background-color: #fff;">
			<input type="text" id="publishinput" style="-webkit-appearance: none; width: 70%; height: 26px; border: 1px solid #e5e5e5; margin-left: 10px; margin-top: 8px; border-radius: 4px; font-size: 14px;">
			<div onclick="publishcontent()" id="publishbtn"
				style="float: right; margin-right: 10px; width: 70px; height: 30px; line-height: 30px; background-color: #00d1bc; margin-top: 9px; text-align: center; color: #ffffff; border-radius: 8px; font-family: '微软雅黑'">发布</div>
		</div>
		<input type="hidden" value="${relative_id }" id="relative_id">
		<input type="hidden" value="${type }" id="type">
	</div>



	<input type="hidden" value="${hasMoblie }" id="hasMoblie">
	<div class="mask" style="display: none;" onclick="hide()"></div>
	<div class="couponalert">
		<form action="" method="post" id="couponuserForm">
			<div style="width: 100%; height: 36px; text-align: center; line-height: 36px;">如需评论请输入手机号</div>
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


</body>


</html>