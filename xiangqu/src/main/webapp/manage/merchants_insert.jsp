<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>后台管理</title>
<link rel="stylesheet" type="text/css" href="css/common.css" />
<link rel="stylesheet" type="text/css" href="css/main.css" />
<script src="js/jquery-1.11.3.min.js"></script>
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

function selectCity(){
	var parentid = $("#province").val();
	if(parentid != ""){
		$.ajax({
			type: "post",
			url: "./getAreaByParentId",
			data: {parentid :parentid, type :1},
			success: function(msg){
				var msgdata = eval("("+msg+")");
				var citylist = msgdata.citylist;
				var arealist = msgdata.arealist;
				$("#city").empty();
				$("#area").empty();
				html = '';
				for(var i= 0; i< citylist.length; i++){
					html += '<option value="'+citylist[i].id+'">'+citylist[i].name+'</option>';
				}
				$("#city").append(html);
				html2 = '';
				for(var i= 0; i< arealist.length; i++){
					html2 += '<option value="'+arealist[i].id+'">'+arealist[i].name+'</option>';
				}
				$("#area").append(html2);
			}
		});
	}else{
		$("#city").empty();
		$("#area").empty();
		html = '';
		html += '<option value="">请选择</option>';
		$("#city").append(html);
		$("#area").append(html);
	}
}

function selectArea(){
	var parentid = $("#city").val();
	if(parentid != ""){
		$.ajax({
			type: "post",
			url: "./getAreaByParentId",
			data: {parentid :parentid, type :2},
			success: function(msg){
				var msgdata = eval("("+msg+")");
				var arealist = msgdata.arealist;
				$("#area").empty();
				html = '';
				for(var i= 0; i< arealist.length; i++){
					html += '<option value="'+arealist[i].id+'">'+arealist[i].name+'</option>';
				}
				$("#area").append(html);
			}
		});
	}else{
		$("#area").empty();
		html = '';
		html += '<option value="0">请选择</option>';
		$("#area").append(html);
	}
}

function checkForm(){
	var id = $("input[name=id]").val();
	var userName = $("input[name=userName]").val();
	if(userName == ""){
		alert("用户名不能为空");
		return false;
	}
	var pwd1 = $("#pwd1").val();
	var pwd2 = $("#pwd2").val();
	if(id == ""){
		if(pwd1 == ""){
			alert("密码不能为空");
			return false;
		}
		if(pwd2 == ""){
			alert("确认密码不能为空");
			return false;
		}
	}
	if(pwd1 != "" && pwd2 != ""){
		if(pwd1 != pwd2){
			alert("两次密码不一致，请重新输入");
			return false;
		}
	}
	var companyName = $("input[name=companyName]").val();
	if(companyName == ""){
		alert("商家名不能为空");
		return false;
	}
	var phone = $("input[name=phone]").val();
	if(phone == ""){
		alert("电话不能为空");
		return false;
	}
	var mobile = $("input[name=mobile]").val();
	if(mobile == ""){
		alert("手机不能为空");
		return false;
	}
}
</script>
</head>
<body>
	<jsp:include page="./sidebar.jsp"></jsp:include>
	<div class="main-wrap">
		<div class="crumb-wrap">
			<div class="crumb-list">
				<i class="icon-font"></i><a href="./home_page">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="./merchants_list">商家管理</a><span class="crumb-step">&gt;</span><span>新增商家</span>
			</div>
		</div>
		<div class="result-wrap">
			<div class="result-content">
				<form action="./edit_merchant" method="post" id="shopedit" enctype="multipart/form-data">
					<input type="hidden" value="${shop.id}" name="id"/>
					<table class="insert-tab" width="100%">
						<tbody>
							<tr>
								<th width="120">头像：</th>
								<td class="sjtx"><img src="${fileurl}${shop.pic}" width="330" height="330" id="shopimgs"><br /> <input type="file" name="shoppic" id="shoppic" value="" onchange="change('shopimgs','shoppic')" /></td>
							</tr>
							<tr>
								<th width="120">账号：</th>
								<td><input class="common-text" name="userName" size="20" value="${shop.userName}" type="text"></td>
							</tr>
							<tr>
								<th>密码：</th>
								<td><input class="common-text required" id="pwd1" name="password" size="20" type="password"></td>
							</tr>
							<tr>
								<th>确认密码：</th>
								<td><input class="common-text required" id="pwd2" size="20" value="" type="password"></td>
							</tr>
							<tr>
								<th>商家名称：</th>
								<td><input class="common-text" name="companyName" size="30" value="${shop.companyName}" type="text"></td>
							</tr>
							<tr>
								<th>联系电话：</th>
								<td><input class="common-text" name="phone" size="30" value="${shop.phone}" type="text"></td>
							</tr>
							<tr>
								<th>手机号码：</th>
								<td><input class="common-text" name="mobile" size="11" value="${shop.mobile}" type="text"></td>
							</tr>
							<tr>
								<th height="auto;">所在地区：</th>
								<td><select name="province" id="province" class="common-text" style="height: 27px;" onchange="selectCity()">
										<option value="">全国</option>
										<c:forEach items="${prolist }" var="pro">
											<c:if test="${pro.id == shop.province}">
												<option value="${pro.id}" selected="selected">${pro.name }</option>
											</c:if>
											<c:if test="${pro.id != shop.province}">
												<option value="${pro.id}">${pro.name }</option>
											</c:if>
										</c:forEach>
								</select>&nbsp;<select name="city" id="city" class="common-text" style="height: 27px;" onchange="selectArea()">
										<option value="">请选择</option>
										<c:forEach items="${citylist }" var="city">
											<c:if test="${city.id == shop.city}">
												<option value="${city.id}" selected="selected">${city.name }</option>
											</c:if>
											<c:if test="${city.id != shop.city}">
												<option value="${city.id}">${city.name }</option>
											</c:if>
										</c:forEach>
								</select>&nbsp;&nbsp;<select name="area" id="area" class="common-text" style="height: 27px;">
										<option value="">请选择</option>
										<c:forEach items="${arealist }" var="area">
											<c:if test="${area.id == shop.area}">
												<option value="${area.id}" selected="selected">${area.name }</option>
											</c:if>
											<c:if test="${area.id != shop.area}">
												<option value="${area.id}">${area.name }</option>
											</c:if>
										</c:forEach>
								</select>&nbsp;<input class="common-text" name="address" size="60" value="${shop.address}" type="text"></td>
							</tr>
							<tr>
								<th height="auto;">从事行业：</th>
								<td><select name="industry_id" id="industry_id" class="common-text" style="height: 27px;">
										<option value="0">请选择</option>
										<c:forEach items="${industrylist }" var="in">
											<c:if test="${in.id == shop.industry_id}">
												<option value="${in.id}" selected="selected">${in.name }</option>
											</c:if>
											<c:if test="${in.id != shop.industry_id}">
												<option value="${in.id}">${in.name }</option>
											</c:if>
										</c:forEach>
								</select>
							</tr>
							<tr>
								<th></th>
								<td><input class="btn btn-primary btn6 mr10" value="提交" type="submit" onclick="return checkForm()"> <!-- <input class="btn btn6" name="reset" value="返回" type="reset"> --></td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>
	</div>
</body>
</html>