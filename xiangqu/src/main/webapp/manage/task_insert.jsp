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
	$(function(){
		if('${checktype}'== 6){
			$("#province").val('${province_id}');
			$("#city").val('${city_id}');
			$("#area").val('${area_id}');
			$("#province").attr("disabled","disabled");
			$("#city").attr("disabled","disabled");
			$("#area").attr("disabled","disabled");
		}
	});
	function checkForm(){
		var id = $("input[name=id]").val();
		var contractNo = $("input[name=contractNo]").val();
		if(contractNo == ""){
			alert("合同编号不能为空");
			return false;
		}
		var name = $("input[name=name]").val();
		if(name == ""){
			alert("任务主题不能为空");
			return false;
		}
		
		if(id == ""){
			var taskpic = $("input[name=taskpic]").val();
			if(taskpic == ""){
				alert("任务主图不能为空");
				return false;
			}
		}
		var income = $("input[name=income]").val();
		if(income == ""){
			alert("分享收益不能为空");
			return false;
		}
		var totalTimes = $("input[name=totalTimes]").val();
		if(totalTimes == ""){
			alert("投放总量不能为空");
			return false;
		}
		if('${checktype}'== 6){
			$.ajax({
				type: "post",
				url: "./checkAreaAdminTimes", 
				data: {totalTimes :totalTimes},
				success: function(data){
					if(data == "error"){
						alert("剩余次数不足");
						return false;
					}
					if(data == "success"){
						$("#taskForm").submit();
					}
				}
			});
		}else{
			$("#taskForm").submit();
		}
		
	}
	
	function selectCity(){
		var parentid = $("#province").val();
		if(parentid != ""){
			$.ajax({
				type: "post",
				url: "./selectMissionArea",
				data: {parentid :parentid, type :1},
				success: function(msg){
					var msgdata = eval("("+msg+")");
					var citylist = msgdata.citylist;
					var arealist = msgdata.arealist;
					$("#city").empty();
					$("#area").empty();
					html = '';
					html += '<option value="">请选择</option>';
					for(var i= 0; i< citylist.length; i++){
						html += '<option value="'+citylist[i].id+'">'+citylist[i].name+'</option>';
					}
					$("#city").append(html);
					html2 = '';
					html2 += '<option value="">请选择</option>';
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
			html2 = '';
			html2 += '<option value="">请选择</option>';
			$("#city").append(html);
			$("#area").append(html2);
		}
	}

	function selectArea(){
		var parentid = $("#city").val();
		if(parentid != ""){
			$.ajax({
				type: "post",
				url: "./selectMissionArea",
				data: {parentid :parentid, type :2},
				success: function(msg){
					var msgdata = eval("("+msg+")");
					var arealist = msgdata.arealist;
					$("#area").empty();
					html = '';
					html += '<option value="">请选择</option>';
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
				<i class="icon-font"></i><a href="./home_page">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="./task_list">任务管理</a><span class="crumb-step">&gt;</span>新建任务
			</div>
		</div>
		<div class="result-wrap">
			<div class="result-content">
				<form action="./task_edit" id="taskForm" method="post" enctype="multipart/form-data">
				<input type="hidden" value="${mission.id }" name="id"/>
				<input type="hidden" value="${mission.state }" name="state"/>
					<table class="insert-tab" width="100%">
						<tbody>
							<tr>
								<th width="120">合同编号：</th>
								<td><input class="common-text" name="contractNo" size="50" value="${mission.contractNo}" type="text"></td>
							</tr>
							<tr>
								<th><i class="require-red">*</i>所属商家：</th>
								<td><select id="shopId" name="shopId" style="width: 150px;">
										<!-- <option value="0">请选择商家</option> -->
										<c:forEach items="${shoplist}" var="shop">
											<option value="${shop.id }" <c:if test="${mission.shopId==shop.id}">selected="selected"</c:if>>${shop.companyName}</option>
										</c:forEach>
								</select></td>
							</tr>
							<tr>
								<th>所属分类：</th>
								<td><select id="cotegoryId" name="cotegoryId" style="width: 150px;">
										<option value="0">请选择分类</option>
										<c:forEach items="${cotegorylist}" var="cotegory">
											<option value="${cotegory.id }" <c:if test="${cotegory.id==mission.cotegoryId}">selected="selected"</c:if>>${cotegory.name}</option>
										</c:forEach>
								</select></td>
							</tr>
							<tr>
								<th><i class="require-red">*</i>任务主题：</th>
								<td>
								<input class="common-text" name="name" size="50" value="${mission.name}" type="text"></td>
							</tr>
							<tr>
								<th><i class="require-red">*</i>任务主图：</th>
								<td>
								<c:if test="${empty mission.pic }">
								<img src="images/defalut.png" style="width: 45px; height: 45px;" id="detailPicimgs"/>
								</c:if>
								<c:if test="${not empty mission.pic }">
								<img src="${fileurl}${mission.pic}" style="width: 45px; height: 45px;" id="detailPicimgs"/>
								</c:if>
								<input type="file" name="taskpic" value="选择图片" id="picfile"  size="20" onchange="change('detailPicimgs','picfile')"></td>
							</tr>
							<tr>
								<th height="auto;">任务详情链接：</th>
								<td>
								<input class="common-text" name="detailUrl" size="80"  value="${mission.detailUrl}" type="text" style="width: 80%;"></td>

							</tr>
							<tr>
								<th>简介：</th>
								<td>
									<textarea style="width: 300px; height: 60px; margin-top: 10px; resize: none;" name="intro">${mission.intro }</textarea>
								</td>

							</tr>
							<tr>
								<th>投放时间：</th>
								<td><input class="common-text" name="startDateStr" size="20" value="<fmt:formatDate value="${mission.startDate}" type="date" pattern="yyyy-MM-dd" />" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
								&nbsp;至&nbsp;
								<input class="common-text" name="endTimeStr" size="20" value="<fmt:formatDate value="${mission.endTime}" type="date" pattern="yyyy-MM-dd" />"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
							</tr>
							<tr>
								<th>投放范围：</th>
								<td><select name="province" id="province" class="common-text" style="height: 27px;" onchange="selectCity()">
										<option value="">全国</option>
										<c:forEach items="${prolist }" var="pro">
											<c:if test="${pro.id == mission.province}">
												<option value="${pro.id}" selected="selected">${pro.name }</option>
											</c:if>
											<c:if test="${pro.id != mission.province}">
												<option value="${pro.id}">${pro.name }</option>
											</c:if>
										</c:forEach>
								</select>&nbsp;<select name="city" id="city" class="common-text" style="height: 27px;" onchange="selectArea()">
										<option value="">请选择</option>
										<c:forEach items="${citylist }" var="city">
											<c:if test="${city.id == mission.city}">
												<option value="${city.id}" selected="selected">${city.name }</option>
											</c:if>
											<c:if test="${city.id != mission.city}">
												<option value="${city.id}">${city.name }</option>
											</c:if>
										</c:forEach>
								</select>&nbsp;&nbsp;<select name="area" id="area" class="common-text" style="height: 27px;">
										<option value="">请选择</option>
										<c:forEach items="${arealist }" var="area">
											<c:if test="${area.id == mission.area}">
												<option value="${area.id}" selected="selected">${area.name }</option>
											</c:if>
											<c:if test="${area.id != mission.area}">
												<option value="${area.id}">${area.name }</option>
											</c:if>
										</c:forEach>
								</select></td>
							</tr>
							<tr>
								<th>分享收益：</th>
								<td><input class="common-text" name="income" size="10" value="${mission.income}" type="number">积分／次</td>
							</tr>
							<tr>
								<th>总投放量：</th>
								<td><input class="common-text" name="totalTimes" size="10" value="${mission.totalTimes}" type="number">条</td>
							</tr>
							<tr>
								<th>收益次数：</th>
								<td><input class="common-text" name="incomeTime" size="10" value="${mission.incomeTime}" type="number">条</td>
							</tr>
							<%-- <tr>
								<th>前端显示投放增加数：</th>
								<td><input class="common-text" name="showTimes" size="10" value="${mission.showTimes}" type="number"></td>
							</tr> --%>
<%-- 							<if${checktype}'=> --%>
<%-- 							<c:if test="${sessionScope.adminPrivilege != 1 && sessionScope.adminPrivilege != 2 && sessionScope.adminPrivilege != 3 && sessionScope.adminPrivilege != 4 && (allcheck == 0 || mission.online == 1)}"> --%>
<%-- 							<c:if test="${sessionScope.adminPrivilege != 1 && sessionScope.adminPrivilege != 2 && sessionScope.adminPrivilege != 3 && sessionScope.adminPrivilege != 4 && allcheck == 0 && (mission.online == 1 || empty mission.online) || sessionScope.adminPrivilege == 5}"> --%>
<!-- 							<tr> -->
<!-- 								<th></th> -->
<!-- 								<td><input class="btn btn-primary btn6 mr10" value="提交" type="button" onclick="return checkForm()">  -->
<!-- 							</tr> -->
<%-- 							</c:if> --%>
<%-- 							<c:if test="${sessionScope.adminPrivilege != 1 && sessionScope.adminPrivilege != 2 && sessionScope.adminPrivilege != 3 && sessionScope.adminPrivilege != 4 && (mission.online == 1 || empty mission.online) || sessionScope.adminPrivilege == 5}"> --%>
							<c:if test="${ (mission.online == 1 || empty mission.online) || sessionScope.adminPrivilege == 5}">
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