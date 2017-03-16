<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="css/common.css" />
<link rel="stylesheet" type="text/css" href="css/main.css" />
<script src="./js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/libs/modernizr.min.js"></script>
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

function selectActivity(type, formid){
	$("input[name=type]").val(type);
	$("#activityForm_"+formid).attr("action","./selectActivity").submit();
}
</script>
<title>首页管理</title>
</head>
<body>
<jsp:include page="./sidebar.jsp"></jsp:include>
<div class="main-wrap">

		<div class="crumb-wrap">
			<div class="crumb-list">
				<i class="icon-font"></i><a href="./home_page">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">广告页管理</span>
			</div>
		</div>

		<div class="result-wrap">

			<div class="result-content">
				<table class="result-tab" width="100%">


					<tr>
						<th class="tc" style="min-width: 480px;">图片</th>
						<th>链接地址</th>
						<th>类型</th>
						<th>关联内容</th>
						<th>操作</th>

					</tr>
						<tr>
						<form action="./edit_activity" method="post" enctype="multipart/form-data" id="activityForm_1">
								<td><img src="${fileurl}${homeActivityOne.pic}" style="max-width: 200px;" id="activityimage1"/> <input name="pic" value="${homeActivityOne.pic}" type="hidden" /> <input class="common-text required"
									name="activityfile" size="20" value="" type="file" id="activityfile1" onchange="change('activityimage1','activityfile1')"/></td>
								<td><input class="common-text required" name="detailUrl" type="text" value="${homeActivityOne.detailUrl}" /></td>
								<td><%-- <c:if test="${homeActivityOne.type == 1 }">活动</c:if><c:if test="${homeActivityOne.type == 2 }">专题</c:if> --%>
								<select name = "type">
								<option value="0" <c:if test="${homeActivityOne.type == 0 }">selected="selected"</c:if>>外链</option>
								<option value="1" <c:if test="${homeActivityOne.type == 1 }">selected="selected"</c:if>>活动</option>
								<option value="2" <c:if test="${homeActivityOne.type == 2 }">selected="selected"</c:if>>专题</option>
								</select> </td>
								<td>${homeActivityOne.activityname }</td>
								<td><input name="valuekey" value="homeActivityOne" type="hidden"> 
									<input name="type" id="type_1" value="${homeActivityOne.type }" type="hidden"> 
									<input name="id" value="${homeActivityOne.id }" type="hidden"> 
									<input class="submit_link" type="button" value="关联活动" onclick="selectActivity(1,1)">
									<input class="submit_link" type="button" value="关联专题" onclick="selectActivity(2,1)">
									<input class="submit_link" type="submit" value="提交修改">
								</td>
						</form>
						</tr>
						<tr>
						<form action="./edit_activity" method="post" enctype="multipart/form-data" id="activityForm_2">
								<td><img src="${fileurl}${homeActivityTwo.pic}" style="max-width: 200px;" id="activityimage2"/> <input name="pic" value="${homeActivityTwo.pic}" type="hidden" /> <input class="common-text required"
									name="activityfile" size="20" value="" type="file" id="activityfile2" onchange="change('activityimage2','activityfile2')"/></td>
								<td><input class="common-text required" name="detailUrl" type="text" value="${homeActivityTwo.detailUrl}" /></td>
								<%-- <c:if test="${homeActivityTwo.type == 1 }">活动</c:if><c:if test="${homeActivityTwo.type == 2 }">专题</c:if> --%>
								<td>
								<select name = "type">
								<option value="0" <c:if test="${homeActivityTwo.type == 0 }">selected="selected"</c:if>>外链</option>
								<option value="1" <c:if test="${homeActivityTwo.type == 1 }">selected="selected"</c:if>>活动</option>
								<option value="2" <c:if test="${homeActivityTwo.type == 2 }">selected="selected"</c:if>>专题</option>
								</select>
								 </td>
								<td>${homeActivityTwo.activityname }</td>
								<td><input name="valuekey" value="homeActivityTwo" type="hidden"> 
								<input name="type" id="type_2" value="${homeActivityTwo.type }" type="hidden">
									<input name="id" value="${homeActivityTwo.id }" type="hidden"> 
									<input class="submit_link" type="button" value="关联活动" onclick="selectActivity(1,2)">
									<input class="submit_link" type="button" value="关联专题" onclick="selectActivity(2,2)">
									<input class="submit_link" type="submit" value="提交修改">
								</td>
						</form>
						</tr>
						<tr>
						<form action="./edit_activity" method="post" enctype="multipart/form-data" id="activityForm_3">
								<td><img src="${fileurl}${homeActivityThree.pic}" style="max-width: 200px;" id="activityimage3"/> <input name="pic" value="${homeActivityThree.pic}" type="hidden" /> <input class="common-text required"
									name="activityfile" size="20" value="" type="file" id="activityfile3" onchange="change('activityimage3','activityfile3')"/></td>
								<td><input class="common-text required" name="detailUrl" type="text" value="${homeActivityThree.detailUrl}" /></td>
								<td>
<select name = "type">
								<option value="0" <c:if test="${homeActivityThree.type == 0 }">selected="selected"</c:if>>外链</option>
								<option value="1" <c:if test="${homeActivityThree.type == 1 }">selected="selected"</c:if>>活动</option>
								<option value="2" <c:if test="${homeActivityThree.type == 2 }">selected="selected"</c:if>>专题</option>
								</select> </td>
								<td>${homeActivityThree.activityname }</td>
								<td><input name="valuekey" value="homeActivityThree" type="hidden"> 
								<input name="id" value="${homeActivityThree.id }" type="hidden"> 
								<input name="type" id="type_3" value="${homeActivityThree.type }" type="hidden">
								<input class="submit_link" type="button" value="关联活动" onclick="selectActivity(1,3)">
									<input class="submit_link" type="button" value="关联专题" onclick="selectActivity(2,3)">
								<input class="submit_link" type="submit" value="提交修改"></td>
						</form>
						</tr>
						<tr>
						<form action="./edit_activity" method="post" enctype="multipart/form-data" id="activityForm_4">
								<td><img src="${fileurl}${homeActivityFour.pic}" style="max-width: 200px;" id="activityimage4"/> <input name="pic" value="${homeActivityFour.pic}" type="hidden" /> <input class="common-text required"
									name="activityfile" size="20" value="" type="file" id="activityfile4" onchange="change('activityimage4','activityfile4')"/></td>
								<td><input class="common-text required" name="detailUrl" type="text" value="${homeActivityFour.detailUrl}" /></td>
								<td>
								<select name = "type">
								<option value="0" <c:if test="${homeActivityFour.type == 0 }">selected="selected"</c:if>>外链</option>
								<option value="1" <c:if test="${homeActivityFour.type == 1 }">selected="selected"</c:if>>活动</option>
								<option value="2" <c:if test="${homeActivityFour.type == 2 }">selected="selected"</c:if>>专题</option>
								</select> </td>
								<td>${homeActivityFour.activityname }</td>
								<td><input name="valuekey" value="homeActivityFour" type="hidden"> 
								<input name="id" value="${homeActivityFour.id }" type="hidden"> 
								<input name="type" id="type_4" value="${homeActivityFour.type }" type="hidden">
								<input class="submit_link" type="button" value="关联活动" onclick="selectActivity(1,4)">
									<input class="submit_link" type="button" value="关联专题" onclick="selectActivity(2,4)">
								<input class="submit_link" type="submit" value="提交修改"></td>
						</form>
						</tr>
						<tr>
						<form action="./edit_activity" method="post" enctype="multipart/form-data" id="activityForm_5">
								<td><img src="${fileurl}${homeActivityFive.pic}" style="max-width: 200px;" id="activityimage5"/> <input name="pic" value="${homeActivityFive.pic}" type="hidden" /> <input class="common-text required"
									name="activityfile" size="20" value="" type="file" id="activityfile5" onchange="change('activityimage5','activityfile5')"/></td>
								<td><input class="common-text required" name="detailUrl" type="text" value="${homeActivityFive.detailUrl}" /></td>
								<td><select name = "type">
								<option value="0" <c:if test="${homeActivityFive.type == 0 }">selected="selected"</c:if>>外链</option>
								<option value="1" <c:if test="${homeActivityFive.type == 1 }">selected="selected"</c:if>>活动</option>
								<option value="2" <c:if test="${homeActivityFive.type == 2 }">selected="selected"</c:if>>专题</option>
								</select></td>
								<td>${homeActivityFive.activityname }</td>
								<td><input name="valuekey" value="homeActivityFive" type="hidden"> 
								<input name="id" value="${homeActivityFive.id }" type="hidden"> 
								<input name="type" id="type_5" value="${homeActivityFive.type }" type="hidden">
								<input class="submit_link" type="button" value="关联活动" onclick="selectActivity(1,5)">
								<input class="submit_link" type="button" value="关联专题" onclick="selectActivity(2,5)">
								<input class="submit_link" type="submit" value="提交修改"></td>
						</form>
						</tr>

				</table>

			</div>
		</div>
	</div>
</body>
</html>