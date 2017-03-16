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
<script charset="utf-8" src="js/kindeditor/kindeditor-min.js" type="text/javascript"></script>
<script charset="utf-8" src="js/kindeditor/lang/zh_CN.js"></script>
<script type="text/javascript">
var editor;
//添加
KindEditor.ready(function(K) {
	editor = K.create('textarea[name="detail"]', {
		pasteType : 2,
		//设置可否改变编辑器大小
		resizeType : 1,
		allowPreviewEmoticons : false,
		allowImageUpload : true,
		uploadJson : 'js/kindeditor/jsp/upload_json.jsp',
fileManagerJson : '	js/kindeditor/jsp/file_manager_json.jsp',
		width: "900px", 
		height: "270px",
		urlType: "domain"
	});
});

function checkForm(){
	var name = $("input[name=name]").val();
	if(name == ""){
		alert("名字不能为空");
		return false;
	}
	var intro = $("input[name=intro]").val();
	if(intro == ""){
		alert("简介不能为空");
		return false;
	}
	var income = $("input[name=income]").val();
	if(income == ""){
		alert("收益不能为空");
		return false;
	}
	// 取得HTML内容
	html = editor.html();
	editor.html(html);

	// 同步数据后可以直接取得textarea的value
	editor.sync();
	
	$("#eventForm").submit();
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
				<i class="icon-font"></i><a href="./home_page">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="./event_list">活动管理</a><span class="crumb-step">&gt;</span>新建任务
			</div>
		</div>
		<div class="result-wrap">
			<div class="result-content">
				<form action="./event_edit" method="post" id="eventForm" enctype="multipart/form-data">
					<input type="hidden" value="${event.id }" name="id"/>
					<table class="insert-tab" width="100%">
						<tbody>
							<tr>
								<th width="120">活动名：</th>
								<td><input class="common-text" name="name" size="50" value="${event.name}" type="text"></td>
							</tr>
							<tr>
								<th><i class="require-red">*</i>活动主图：</th>
								<td>
								<c:if test="${empty event.pic }">
									<img src="images/defalut.png" width="45px" height="45px" id="eventimgs">
								</c:if>
								<c:if test="${not empty event.pic }">
									<img src="${fileUrl }${event.pic}" width="45px" height="45px" id="eventimgs">
								</c:if>
								<input type="file" name="eventpic" id="eventpic" value="选择图片" size="20" onchange="change('eventimgs','eventpic')">
								</td>
							</tr>
							<tr>
								<th height="auto;">介绍：</th>
								<td><textarea name="intro"style="width: 80%; height: 60px; resize: none;">${event.intro}</textarea></td>
							</tr>
							<tr>
								<th height="auto;">详情：</th>
								<td><textarea name="detail"style="width: 80%; height: 60px; resize: none;">${event.detail}</textarea></td>
							</tr>
							<tr>
								<th>报名收益：</th>
								<td><input class="common-text" name="income" size="10" value="${event.income}" type="number">积分</td>
							</tr>
							<tr>
								<th>限制人数：</th>
								<td><input class="common-text" name="signLimit" size="10" value="${event.signLimit}" type="number">人</td>
							</tr>
							<tr>
								<th>活动时间：</th>
								<td><input class="common-text" name="startDateStr" size="20" value="<fmt:formatDate value="${event.startTime}" type="date" pattern="yyyy-MM-dd"/>" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
								&nbsp;至&nbsp;
								<input class="common-text" name="endTimeStr" size="20" value="<fmt:formatDate value="${event.endTime}" type="date" pattern="yyyy-MM-dd"/>"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
							</tr>
							<tr>
								<th>关联商家：</th>
								<td>
									<select name="storeId">
										<c:forEach items="${shoplist }" var="shop">
										<c:if test="${event.storeId ==  shop.id}">
											<option value="${shop.id }" selected="selected">${shop.companyName }</option>
										</c:if>
										<c:if test="${event.storeId !=  shop.id}">
											<option value="${shop.id }">${shop.companyName }</option>
										</c:if>
										</c:forEach>
									</select>
								</td>
							</tr>
							<c:if test="${(sessionScope.adminPrivilege != 1 && sessionScope.adminPrivilege != 2 && sessionScope.adminPrivilege != 3 && sessionScope.adminPrivilege != 4) && allcheck == 0 || (sessionScope.adminPrivilege == 5)}">
							<tr>
								<th></th>
								<td><input class="btn btn-primary btn6 mr10" value="提交" type="button" onclick="return checkForm()"> 
							</tr>
							</c:if>
						</tbody>
					</table>
				</form>
			</div>
		</div>

	</div>

</body>
</html>