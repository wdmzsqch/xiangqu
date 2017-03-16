<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, user-scalable=no">
<title>修改头像</title>
<link rel="stylesheet" type="text/css" href="css/user.css" />
<link rel="stylesheet" type="text/css" href="css/common.css" />
<script type="text/javascript" src="./js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
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
		//	if (size > (1024 * 1024)) {
		//		alert("文件大小不能超过1M,请重新编辑后上传");
		//		file.outerHTML = file.outerHTML.replace(/(value=\").+\"/i, "$1\"");
		//		return;
		//	}

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
				pic.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='image',src=\"" + reallocalpath + "\")";
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

	function editorUser() {
		$("#editorform").submit();
	}
</script>
</head>

<body class="bj01">
	<form action="editor_pic" enctype="multipart/form-data" method="post" id="editorform">
		<input type="hidden" value="${id }" name="id" />
		<div style="width: 100%; height: 100px; background: #fff; text-align: center;">
			<img src="${picurl }" style="width: 80px; height: 80px; border-radius: 40px; margin-top: 10px;" id="picimage">
			<input type="file" name="picfile" style="position: relative; width: 100%; height: 100%; z-index: 101; top: -94px; opacity: 0;" id="picfile" onchange="change('picimage','picfile')" />
		</div>
		<div style="position: fixed; bottom: 0px; width: 100%; height: 48px; border-top: 1px solid #e8e8e8; background: #fff;">
			<div style="height: 8px;"></div>
			<div style="width: 73%; height: 32px; line-height: 32px; margin: 0 auto; text-align: center; background: #00d1bc; color: #fff; border-radius: 16px;" onclick="editorUser()">保存修改</div>
		</div>
	</form>

	<%@ include file="cs.jsp"%>
	<%
		CS cs = new CS(1256812462);
		cs.setHttpServlet(request, response);
		String imgurl = cs.trackPageView();
	%>
	<img src="<%=imgurl%>" width="0" height="0" />
</body>
</html>
