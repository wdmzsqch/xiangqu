<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>后台管理</title>
<link rel="stylesheet" type="text/css" href="css/common.css" />
<link rel="stylesheet" type="text/css" href="css/main.css" />
<script src="./js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/libs/modernizr.min.js"></script>
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	function checkForm(){
		var id = $("input[name=id]").val();
		var name = $("input[name=name]").val();
		if(name == ""){
			alert("专题名不能为空");
			return false;
		}
		var intro = $("input[name=intro]").val();
		if(intro == ""){
			alert("介绍不能为空");
			return false;
		}
		if(id == ""){
			var subjectpic = $("input[name=subjectpic]").val();
			if(subjectpic == ""){
				alert("专题主图不能为空");
				return false;
			}
		}
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
		if (size > (1024 * 1024)) {
			alert("文件大小不能超过1M,请重新编辑后上传");
			file.outerHTML = file.outerHTML.replace(/(value=\").+\"/i, "$1\"");
			return;
		}

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
		$(".selectedfile").val(file.value);
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
</head>
<body>
	<jsp:include page="./sidebar.jsp"></jsp:include>
	<div class="main-wrap">

		<div class="crumb-wrap">
			<div class="crumb-list">
				<i class="icon-font"></i><a href="./home_page">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="./subject_list">专题管理</a><span class="crumb-step">&gt;</span>新建专题
			</div>
		</div>
		<div class="result-wrap">
			<div class="result-content">
				<form action="./subject_edit" method="post" enctype="multipart/form-data">
					<input type="hidden" value="${subject.id }" name="id"/>
					<table class="insert-tab" width="100%">
						<tbody>
							<tr>
								<th width="120">专题名：</th>
								<td><input class="common-text" name="name" size="50" value="${subject.name}" type="text"></td>
							</tr>
							<tr>
								<th><i class="require-red">*</i>专题主图：</th>
								<td>
								<c:if test="${empty subject.pic }">
									<img src="images/defalut.png" width="45px" height="45px" id="subjectimgs">
								</c:if>
								<c:if test="${not empty subject.pic }">
									<img src="${fileUrl }${subject.pic}" width="45px" height="45px" id="subjectimgs">
								</c:if>
								<input type="file" name="subjectpic" id="subjectpic" value="选择图片" size="20" onchange="change('subjectimgs','subjectpic')">
								</td>
							</tr>
							<tr>
								<th height="auto;">介绍：</th>
								<td><textarea name="intro"style="width: 80%; height: 60px;">${subject.intro}</textarea></td>
							</tr>
							<tr>
								<th height="auto;">外链：</th>
								<td><input class="common-text" name="detailUrl" size="50" value="${subject.detailUrl }"/></td>
							</tr>
							<tr>
								<th></th>
								<td><input class="btn btn-primary btn6 mr10" value="提交" type="submit" onclick="return checkForm()"> 
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>

	</div>

</body>
</html>