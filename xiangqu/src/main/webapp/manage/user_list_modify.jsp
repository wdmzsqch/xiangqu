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
				url: "./selectArea",
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
				url: "./selectArea",
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
			html += '<option value="">请选择</option>';
			$("#area").append(html);
		}
	}
</script>
</head>
<body>
	<jsp:include page="./sidebar.jsp"></jsp:include>
	<div class="main-wrap">

		<div class="crumb-wrap">
			<div class="crumb-list">
				<i class="icon-font"></i><a href="./home_page">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="./user_list">用户列表管理</a><span
					class="crumb-step">&gt;</span><span>修改信息</span>
			</div>
		</div>
		<div class="result-wrap">
			<div class="result-content">
				<form action="./editor_user" method="post" id="myform" name="myform" enctype="multipart/form-data">
					<input name="id" type="hidden" value="${user_modify.id }"/>
					<table class="insert-tab" width="100%">
						<tbody>
							<tr>
								<th width="120">头像：</th>
								<td class="sjtx">
								<c:if test="${empty user_modify.pic }">
								<img src="" width="100px;" height="100px;" id="pic_imgs">
								</c:if>
								<c:if test="${not empty user_modify.pic }">
								<img src="${fileurl}${user_modify.pic}" width="100px;" height="100px;" id="pic_imgs">
								</c:if>
								<input type="file" name="modify_pic" id="modify_pic" style="position: relative;left: -105px; width: 100px; height: 100px; opacity: 0;" onchange="change('pic_imgs','modify_pic')"/>
								</td>
							</tr>
							<tr>
								<th width="120">用户名：</th>
								<td><input class="common-text required" id="title2" name="userName" size="50" value="${user_modify.userName }" type="text"></td>
							</tr>
							<tr>
								<th>姓名：</th>
								<td><input class="common-text required" id="title" name="realName" size="50" value="${user_modify.realName }" type="text"></td>
							</tr>
							<tr>
								<th>性别：</th>
								<td><select name="gender" id="colId" class="required">
									<c:if test="${empty user_modify.gender }">
										<option value="">请选择</option>
										<option value="男">男</option>
										<option value="女">女</option>
									</c:if>
									<c:if test="${not empty user_modify.gender }">
										<c:if test="${user_modify.gender eq '男' }">
										<option value="">请选择</option>
										<option value="男" selected="selected">男</option>
										<option value="女">女</option>
										</c:if>
										<c:if test="${user_modify.gender eq '女' }">
										<option value="">请选择</option>
										<option value="男">男</option>
										<option value="女" selected="selected">女</option>
										</c:if>
									</c:if>
								</select></td>
							</tr>
							<tr>
								<th>手机：</th>
								<td><input class="common-text" name="moblie" size="50" value="${user_modify.moblie }" type="text"></td>
							</tr>
							<tr>
								<th>地址：</th>
								<td><select name="province" id="province" class="common-text" style="height: 27px;" onchange="selectCity()">
										<option value="">请选择</option>
										<c:forEach items="${prolist }" var="pro">
											<c:if test="${pro.id == user_modify.province}">
												<option value="${pro.id}" selected="selected">${pro.name }</option>
											</c:if>
											<c:if test="${pro.id != user_modify.province}">
												<option value="${pro.id}">${pro.name }</option>
											</c:if>
										</c:forEach>
								</select>&nbsp;<select name="city" id="city" class="common-text" style="height: 27px;" onchange="selectArea()">
										<option value="">请选择</option>
										<c:forEach items="${citylist }" var="city">
											<c:if test="${city.id == user_modify.city}">
												<option value="${city.id}" selected="selected">${city.name }</option>
											</c:if>
											<c:if test="${city.id != user_modify.city}">
												<option value="${city.id}">${city.name }</option>
											</c:if>
										</c:forEach>
								</select>&nbsp;&nbsp;<select name="area" id="area" class="common-text" style="height: 27px;">
										<option value="">请选择</option>
										<c:forEach items="${arealist }" var="area">
											<c:if test="${area.id == user_modify.area}">
												<option value="${area.id}" selected="selected">${area.name }</option>
											</c:if>
											<c:if test="${area.id != user_modify.area}">
												<option value="${area.id}">${area.name }</option>
											</c:if>
										</c:forEach>
								</select>&nbsp;<input class="common-text" name="address" size="50" value="${user_modify.address }" type="text"></td>
							</tr>
							<tr>
								<th>积分：</th>
								<td><i class="require-red">${user_modify.balance }</i>（积分不可编辑）</td>
							</tr>
							<tr>
								<th></th>
								<td><input class="btn btn-primary btn6 mr10" value="保存" type="submit"> <input class="btn btn6" onClick="history.go(-1)" value="返回" type="button"></td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>

	</div>

</body>
</html>