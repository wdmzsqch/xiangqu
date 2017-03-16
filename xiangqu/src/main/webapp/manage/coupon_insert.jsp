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
	
	function checkForm(){
		var title = $("input[name=title]").val();
		if(title == ""){
			alert("标题不能为空");
			return false;
		}
		var intro = $("textarea[name=intro]").val();
		if(intro == ""){
			alert("简介不能为空");
			return false;
		}
		var money = $("input[name=money]").val();
		if(money == ""){
			alert("优惠金额不能为空");
			return false;
		}
		var moneyLimit = $("input[name=moneyLimit]").val();
		if(moneyLimit == ""){
			alert("满多少使用不能为空");
			return false;
		}
		var endTime = $("input[name=endTime]").val();
		if(endTime == ""){
			alert("截止时间不能为空");
			return false;
		}
		var numLimit = $("input[name=numLimit]").val();
		if(numLimit == ""){
			alert("限领张数不能为空");
			return false;
		}
		var total = $("input[name=total]").val();
		if(total == ""){
			alert("总数不能为空");
			return false;
		}
		var shareIncome = $("input[name=shareIncome]").val();
		if(shareIncome == ""){
			alert("分享收益不能为空");
			return false;
		}
		var useIncome = $("input[name=useIncome]").val();
		if(useIncome == ""){
			alert("使用收益不能为空");
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
				<i class="icon-font"></i><a href="./home_page">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="./coupon_list">优惠券管理</a><span class="crumb-step">&gt;</span>新建优惠券
			</div>
		</div>
		<div class="result-wrap">
			<div class="result-content">
				<form action="./coupon_edit" method="post" enctype="multipart/form-data">
					<input type="hidden" value="${coupon.id }" name="id"/>
					<table class="insert-tab" width="100%">
						<tbody>
							<tr>
								<th width="120">标题：</th>
								<td><input class="common-text" name="title" size="50" value="${coupon.title}" type="text"></td>
							</tr>
							<tr>
								<th height="auto;">介绍：</th>
								<td><textarea name="intro"style="width: 80%; height: 60px;">${coupon.intro}</textarea></td>
							</tr>
							<tr>
								<th height="auto;">优惠金额：</th>
								<td><input class="common-text" name="money" size="50" value="${coupon.money}" type="number"></td>
							</tr>
							<tr>
								<th height="auto;">满多少使用：</th>
								<td><input class="common-text" name="moneyLimit" size="50" value="${coupon.moneyLimit}" type="number"></td>
							</tr>
							<tr>
								<th>截止时间：</th>
								<td><input class="common-text" name="endTime" size="20" value="<fmt:formatDate value="${coupon.validity}" type="date" pattern="yyyy-MM-dd"/>" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
								</td>
							</tr>
							<tr>
								<th>关联商家：</th>
								<td>
									<select name="storeId">
										<c:forEach items="${shoplist }" var="shop">
										<c:if test="${coupon.storeId ==  shop.id}">
											<option value="${shop.id }" selected="selected">${shop.companyName }</option>
										</c:if>
										<c:if test="${coupon.storeId !=  shop.id}">
											<option value="${shop.id }">${shop.companyName }</option>
										</c:if>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<th height="auto;">类型：</th>
								<td>
									<select name="type">
										<c:if test="${coupon.type == 1 }">
											<option value="1" selected="selected">实物券</option>
											<option value="2">抵扣券</option>
										</c:if>
										<c:if test="${coupon.type == 2 }">
											<option value="1">实物券</option>
											<option value="2" selected="selected">抵扣券</option>
										</c:if>
										<c:if test="${empty coupon.type }">
											<option value="1" selected="selected">实物券</option>
											<option value="2">抵扣券</option>
										</c:if>
									</select>
								</td>
							</tr>
							<tr>
								<th>限领张数：</th>
								<td><input class="common-text" name="numLimit" size="50" value="${coupon.numLimit}" type="number"></td>
							</tr>
							<tr>
								<th>总数：</th>
								<td><input class="common-text" name="total" size="50" value="${coupon.total}" type="number"></td>
							</tr>
							<tr>
								<th>分享收益：</th>
								<td><input class="common-text" name="shareIncome" size="50" value="${coupon.shareIncome}" type="number"></td>
							</tr>
							<tr>
								<th>使用收益：</th>
								<td><input class="common-text" name="useIncome" size="50" value="${coupon.useIncome}" type="number"></td>
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