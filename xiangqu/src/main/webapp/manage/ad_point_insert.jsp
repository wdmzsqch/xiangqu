<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>后台管理</title>
<link rel="stylesheet" type="text/css" href="css/common.css" />
<link rel="stylesheet" type="text/css" href="css/main.css" />
<script type="text/javascript" src="js/libs/modernizr.min.js"></script>
<script src="./js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
$(function(){
	var adSourceClassHidden = $("#adSourceClassHidden").val();
	var source = adSourceClassHidden.split(",");
	if(source != null && source.length > 0){
		for(var i=0; i<source.length;i++){
			if(source[i] == "电梯轿厢广告1.0"){
				$("#source_1").prop('checked', true);
			}else if(source[i] == "电梯轿厢广告2.0"){
				$("#source_2").prop('checked', true);
			}else if(source[i] == "电梯轿厢广告3.0"){
				$("#source_3").prop('checked', true);
			}else if(source[i] == "社区灯箱"){
				$("#source_4").prop('checked', true);
			}else if(source[i] == "电梯门广告"){
				$("#source_5").prop('checked', true);
			}
			
		}
	}
});

</script>
</head>
<body>
	<jsp:include page="./sidebar.jsp"></jsp:include>
	<div class="main-wrap">

		<div class="crumb-wrap">
			<div class="crumb-list">
				<i class="icon-font"></i> <a href="./home_page">首页</a> <span class="crumb-step">&gt;</span> <a class="crumb-name" href="./industry_list">广告点位管理</a> <span
					class="crumb-step">&gt;</span><span>编辑广告点位</span>
			</div>
		</div>
		<div class="result-wrap">
			<div class="result-content">
				<form action="./ad_point_edit" method="post" id="myform" name="myform" enctype="multipart/form-data">
					<input id="id" name="id" value="${adpoint.id}" type="hidden">
					<table class="insert-tab" width="100%">
						<tbody>
							<tr>
								<th width="120"><i class="require-red">*</i>小区名称：</th>
								<td><input class="common-text" name="plotName" size="50" value="${adpoint.plotName}" type="text"></td>
							</tr>
							<tr>
								<th width="120"><i class="require-red">*</i>经度：</th>
								<td><input class="common-text" name="longitude" size="50" value="${adpoint.longitude}" type="text"></td>
							</tr>
							<tr>
								<th width="120"><i class="require-red">*</i>纬度：</th>
								<td><input class="common-text" name="latitude" size="50" value="${adpoint.latitude}" type="text"></td>
							</tr>
							<tr>
								<th width="120"><i class="require-red">*</i>广告编号：</th>
								<td><input class="common-text" name="adPointNum" size="50" value="${adpoint.adPointNum}" type="text"></td>
							</tr>
							<tr>
								<th width="120"><i class="require-red">*</i>区域：</th>
								<td><input class="common-text" name="adArea" size="50" value="${adpoint.adArea}" type="text"></td>
							</tr>
							<tr>
								<th width="120"><i class="require-red">*</i>广告资源类型：</th>
								<td>
								<input type="checkbox" class="common-text" name="adSourceClass" id="source_1" value="电梯轿厢广告1.0">电梯轿厢广告1.0
								<input type="checkbox" class="common-text" name="adSourceClass" id="source_2" value="电梯轿厢广告2.0">电梯轿厢广告2.0
								<input type="checkbox" class="common-text" name="adSourceClass" id="source_3" value="电梯轿厢广告3.0">电梯轿厢广告3.0
								<input type="checkbox" class="common-text" name="adSourceClass" id="source_4" value="社区灯箱">社区灯箱
								<input type="checkbox" class="common-text" name="adSourceClass" id="source_5" value="电梯门广告">电梯门广告
								<input type="hidden" value="${adpoint.adSourceClass}" id="adSourceClassHidden"/>
								</td>
							</tr>
							<tr>
								<th width="120"><i class="require-red">*</i>背景色：<br/><font color="#ccc;">(0x008000)</font></th>
								<td><input class="common-text" name="background" size="50" value="${adpoint.background}" type="text"></td>
							</tr>
							<tr>
								<th></th>
								<td><input class="btn btn-primary btn6 mr10" value="提交" type="submit"></td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>

	</div>

</body>
</html>