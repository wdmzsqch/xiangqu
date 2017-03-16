<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, user-scalable=no">
	<link rel="stylesheet" type="text/css" href="./css/common.css" />
	<script type="text/javascript" src="./js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
$(function(){
	var err = '${err}';
	if(err!=''){
		alert(err);
	}
})

	function changeinfo() {
		var file = $("#file");
		var name = $("#name").val();
		var address = $("#address").val();

		if (name == '') {
			alert("公司名称不能为空!");
			return;
		}

		if (address == '') {
			alert("商家地址不能为空!");
			return;
		}
		
		$("#labelForm").submit();
	}
	
	function change(a, b) {
 		var pic = document.getElementById(a);
		var file = document.getElementById(b);

		var fileval = $("#" + b).val();
		if (fileval == "") {
			return;
		}
		// gif在IE浏览器暂时无法显示
		if (!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(file.value)) {
			alert("图片类型必须是.gif,jpeg,jpg,png中的一种,请重新上传!");
			file.outerHTML = file.outerHTML.replace(/(value=\").+\"/i, "$1\"");
			return;
		}

		var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
		var fileSize = 0;
		if (isIE && !file.files) {
			var filePath = file.value;
			var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
			var fileImg = fileSystem.GetFile(filePath);
			fileSize = fileImg.Size;
		} else {
			fileSize = file.files[0].size;
		}
		var size = fileSize / 1024 * 1024; //单位B
//		if (size > (1024 * 1024)) {
//			alert("文件大小不能超过1M,请重新编辑后上传");
//			file.outerHTML = file.outerHTML.replace(/(value=\").+\"/i, "$1\"");
//			return;
//		}

		// IE浏览器
		if (document.all) {
			file.select();
			var reallocalpath = document.selection.createRange().text;
			var ie6 = /msie 6/i.test(navigator.userAgent);
			// IE6浏览器设置img的src为本地路径可以直接显示图片
			if (ie6)
				pic.src = reallocalpath;
			else {
				// 非IE6版本的IE由于安全问题直接设置img的src无法显示本地图片，但是可以通过滤镜来实现
				pic.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='image',src=\""
						+ reallocalpath + "\")";
				// 设置img的src为base64编码的透明图片 取消显示浏览器默认图片
				pic.src = 'data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==';
			}
		} else {
			html5Reader(file, pic);
		}
	}

 function html5Reader(file, pic) {
		var file = file.files[0];
		var reader = new FileReader();
		reader.readAsDataURL(file);
		reader.onload = function(e) {

			pic.src = this.result;
		};
	}

</script>
<title>商家资料</title>
</head>
<body
	style="margin: 0px; padding: 0px; border: 0px; background-color: #f6f6f6">
<form action="changeninfo" enctype="multipart/form-data"  method="post" id="labelForm">
	<div
		style="line-height: 62px; font-size: 13px; width: 100%; height: 62px; background-color: #ffffff">
		<span style="float: left; margin-left: 18px">商家头像</span>

		<div style="padding: 8px; margin-right: 6px; position:relative">
			<div
				style="width: 46px; height: 46px; border-radius: 100px; overflow:auto ; float: right;position:absolute;right:14px;top:8px">
				<img id="head" src="${fileRootUrl }${shopinfo.pic }" style="width: 46px; height: 46px;"/>
			</div>
			<div
				style="width: 46px; height: 46px; border-radius: 100px; overflow: hidden; float: right;position:absolute;right:14px;top:8px">
				<input type="file" name="file" id="file" accept="image/x" style="width: 46px; height: 46px;opacity:0" value="" onchange="change('head','file')"/>
			</div>
		</div>
	</div>

	<!-- 账号 -->
	<div
		style="margin-top: 13px; line-height: 34px; font-size: 13px; width: 100%; height: 34px; background-color: #ffffff;">
		<span style="float: left; margin-left: 18px">账号</span>

		<div style="float: right; margin-right: 18px">
			<span style="color: #a9a9a9">${shopinfo.userName }</span>
		</div>
	</div>

	<div style="clear:both;border-bottom: 1px solid #CCCCCC; margin-left: 22px"></div>

	<!-- 公司名称 -->
	<div
		style="line-height: 34px; font-size: 13px; width: 100%; height: 34px; background-color: #ffffff;">
		<span style="float: left; margin-left: 18px">公司名称</span>

		<div style="float: right; margin-right: 18px; width: 70%;">
			<input type="text" name="name" id="name" style="height: 28px; width:100%; border: 0px;text-align: right;" value="${shopinfo.companyName }"/>
		</div>
	</div>

	<!-- 商家地址 -->
	<div
		style="line-height: 34px; font-size: 13px; width: 100%; height: 34px; background-color: #ffffff;">
		<span style="float: left; margin-left: 18px">商家地址</span>

		<div style="float: right; margin-right: 18px; width: 70%;">
			<input type="text" name="address" id="address" style="height: 28px; width:100%; border: 0px;text-align: right;" value="${shopinfo.address }"/>
		</div>
	</div>
	
	<div style="width: 100%; height: 25px; background-color: #ffffffff"></div>
	
	<!-- 提交修改 -->
	<div style=" margin: auto; width:90%; height: 45px; background-color: #e84e4e; line-height: 45px; text-align:center; font-size: 13px; border-radius: 5px;" onclick="changeinfo()">
		<span style="color: #ffffff; font-size: 18px;">提交修改</span>
	</div>
</form>
</body>

</html>








