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
<script type="text/javascript">
	function checkForm(){
		var name = $("input[name=name]").val();
		if(name == ""){
			alert("姓名不能为空");
			return false;
		}
		var phone = $("input[name=phone]").val();
		if(phone == ""){
			alert("手机号不能为空");
			return false;
		}
		var wtcount = $("input[name=wtcount]").val();
		var wucount = $("input[name=wucount]").val();
		if(wtcount == ""){
			wtcount = 0;
		}
		if(wucount == ""){
			wucount = 0;
		}
		var wtcounthidden = $("#wtcounthidden").val();
		var wucounthidden = $("#wucounthidden").val();
		if(parseInt(wtcount) - parseInt(wtcounthidden) < 0){
			alert("总数不能小于"+wtcounthidden);
			return;
		}
		if(parseInt(wucount) - parseInt(wucounthidden) < 0){
			alert("已使用数量不能小于"+wucounthidden);
			return;
		}
		
		$.ajax({
			type: "post",
			url: "./wuser_edit",
			data: $("#pageForm").serialize(),
			success: function(data){
				if(data == "success"){
					window.location.href = "./w_user_list";
				}else{
					alert("该手机号已存在");
					return;
				}
			}
		});
	}
	
	function selectCity(){
		var parentid = $("#province").val();
		if(parentid != ""){
			$.ajax({
				type: "post",
				url: "./selectWaterArea",
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
				url: "./selectWaterArea",
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

function changeSpan(){
	var wtcount = $("input[name=wtcount]").val();
	var wucount = $("input[name=wucount]").val();
	if(wtcount == ""){
		wtcount = 0;
	}
	if(wucount == ""){
		wucount = 0;
	}
	
	$("#nouse").text(parseInt(wtcount-wucount));
}
</script>
</head>
<body>
	<jsp:include page="./sidebar.jsp"></jsp:include>
	<div class="main-wrap">

		<div class="crumb-wrap">
			<div class="crumb-list">
				<i class="icon-font"></i><a href="./home_page">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="./w_user_list">用户列表管理</a><span class="crumb-step">&gt;</span>修改信息
			</div>
		</div>
		<div class="result-wrap">
			<div class="result-content">
				<form action="" id="pageForm" method="post" enctype="multipart/form-data">
				<input type="hidden" value="${user.id }" name="id"/>
				<input type="hidden" value="${user.wtcount }" id="wtcounthidden"/>
				<input type="hidden" value="${user.wucount }" id="wucounthidden"/>
					<table class="insert-tab" width="100%">
						<tbody>
							<tr>
								<th width="120"><i class="require-red">*</i>姓名：</th>
								<td><input class="common-text" name="name" size="50" value="${user.name}" type="text"></td>
							</tr>
							<tr>
								<th><i class="require-red">*</i>手机号：</th>
								<td>
								<input class="common-text" name="phone" size="50" value="${user.phone}" type="text"></td>
							</tr>
							<tr>
								<th>地址：</th>
								<td><select name="provice" id="province" class="common-text" style="height: 27px;" onchange="selectCity()">
										<option value="">请选择</option>
										<c:forEach items="${prolist }" var="pro">
											<c:if test="${pro.id == user.provice}">
												<option value="${pro.id}" selected="selected">${pro.name }</option>
											</c:if>
											<c:if test="${pro.id != user.provice}">
												<option value="${pro.id}">${pro.name }</option>
											</c:if>
										</c:forEach>
								</select>&nbsp;<select name="city" id="city" class="common-text" style="height: 27px;" onchange="selectArea()">
										<option value="">请选择</option>
										<c:forEach items="${citylist }" var="city">
											<c:if test="${city.id == user.city}">
												<option value="${city.id}" selected="selected">${city.name }</option>
											</c:if>
											<c:if test="${city.id != user.city}">
												<option value="${city.id}">${city.name }</option>
											</c:if>
										</c:forEach>
								</select>&nbsp;&nbsp;<select name="area" id="area" class="common-text" style="height: 27px;">
										<option value="">请选择</option>
										<c:forEach items="${arealist }" var="area">
											<c:if test="${area.id == user.area}">
												<option value="${area.id}" selected="selected">${area.name }</option>
											</c:if>
											<c:if test="${area.id != user.area}">
												<option value="${area.id}">${area.name }</option>
											</c:if>
										</c:forEach>
								</select>&nbsp;&nbsp;<input class="common-text" name="address" size="50" value="${user.address}" type="text"></td>
							</tr>
							<tr>
								<th>备注：</th>
								<td><input class="common-text" name="mark" size="50" value="${user.mark}" type="text"></td>
							</tr>
							<tr>
								<th>备注：</th>
								<td>总数量:<input class="common-text" name="wtcount" size="50" value="${user.wtcount}" type="number" onkeyup="changeSpan()">&nbsp;&nbsp;
								已使用:<input class="common-text" name="wucount" size="50" value="${user.wucount}" type="number" onkeyup="changeSpan()">&nbsp;&nbsp;
								未使用：<span id="nouse">${user.wtcount-user.wucount}</span></td>
							</tr>
							<tr>
								<th></th>
								<td><input class="btn btn-primary btn6 mr10" value="提交" type="button" onclick="return checkForm()"> 
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>

	</div>

</body>
</html>