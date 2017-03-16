<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>后台管理</title>
<link rel="stylesheet" type="text/css" href="css/common.css" />
<link rel="stylesheet" type="text/css" href="css/main.css" />
<script type="text/javascript" src="js/libs/modernizr.min.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script src="./js/jquery-1.11.3.min.js"></script>
<script charset="utf-8" src="js/kindeditor/kindeditor-min.js" type="text/javascript"></script>
<script charset="utf-8" src="js/kindeditor/lang/zh_CN.js"></script>
<script type="text/javascript">
	var editor;
	//添加
	KindEditor.ready(function(K) {
		editor = K.create('textarea[name="description"]', {
			pasteType : 2,
			//设置可否改变编辑器大小
			resizeType : 1,
			allowPreviewEmoticons : false,
			allowImageUpload : true,
			uploadJson : 'js/kindeditor/jsp/upload_json.jsp',
			fileManagerJson : '	js/kindeditor/jsp/file_manager_json.jsp',
			width : "900px",
			height : "270px",
			urlType : "domain"
		});
	});

	$(function() {
		if ($("#goodpayType").val() == 1 || $("#goodpayType").val() == "") {
			$("#jifen").text("销售价：");
			$("#fen").text("元");
			$("#goodprice").removeAttr("onkeyup");
		} else {
			$("#jifen").text("兑换积分：");
			$("#fen").text("分");
			$("#goodprice").attr("onkeyup", "checkNum(event,'goodprice')");
		}
	});
	$(document).ready(function() {
		$('#categoryId').change(function() {
			var cname = $("#categoryId").find("option:selected").text();
			$("#category").val(cname);
		});

		$(".radioItem").change(function() {
			var $selectedvalue = $("input[name='payType']:checked").val();
			if ($selectedvalue == 1) {
				$("#jifen").text("销售价：");
				$("#fen").text("元");
				$("#goodprice").removeAttr("onkeyup");
			} else {
				$("#jifen").text("兑换积分：");
				$("#fen").text("分");
				$("#goodprice").attr("onkeyup", "checkNum(event,'goodprice')");
			}
		});
	})

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
				pic.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='image',src=\"" + reallocalpath + "\")";
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

	function addproperty() {
		var index = $("#proindex").val();
		index = parseInt(index) + 1;
		$("#proindex").val(index);
		var html = '<tr><th></th>';
		html += '<td>属性名称:<input class="common-text required" type="text" name="proname"  value="">&nbsp;&nbsp; ';
		html += '属性值:<input class="common-text required provalue'+index+'" type="text" value=""> ';
		html += '<input class="btn btn-primary btn6 mr10" value="新增属性值" onclick="addprovalue(this,' + index + ')" type="button"><input type="hidden" name="provalue"  id="pvalue'+index+'"></td></tr>';
		$("#proafter").before(html);
	}

	function addprovalue(object, index) {
		var html = '<input class="common-text required provalue'+index+'" type="text" value=""> ';
		$(object).before(html);

	}

	function checkvalue() {
		// 取得HTML内容
		html = editor.html();
		editor.html(html);

		// 同步数据后可以直接取得textarea的value
		editor.sync();
		var index = parseInt($("#proindex").val());
		var novalue = false;
		var noname = false;
		for (var i = 0; i < index; i++) {
			var j = i + 1;
			$(".provalue" + j).each(function() {
				var value = $(this).val();
				if (value != '') {
					var provalue = $("#pvalue" + j).val();
					if (provalue == '') {
						provalue = value;
					} else {
						provalue = provalue + "," + value;
					}
					$("#pvalue" + j).val(provalue);
				}
			})
			var provalue = $("#pvalue" + j).val();
			if (provalue == '') {
				novalue = true;
			}
		}
		$("input[name='proname']").each(function() {
			if ($(this).val() == '') {
				noname = true;
			}
		})
		if (noname) {
			alert("属性名称不能为空!");
			return false;
		}
		if (novalue) {
			alert("商品必须需要一个属性!");
			return false;
		}
		var originPrice = $("input[name=originPrice]").val();
		if (originPrice == "") {
			alert("请输入市场价");
			return false;
		}

		var price = $("input[name=price]").val();
		if (price == "") {
			alert("请输入积分或金额");
		}
		var goodsType = $("input[name=payType]:checked").val();
		if (goodsType == 2 && parseInt(price) < 1) {
			alert("积分不能小于1");
			return false;
		}

		$("#myform").submit();
	}
</script>
</head>
<body>
	<jsp:include page="./sidebar.jsp"></jsp:include>
	<div class="main-wrap">

		<div class="crumb-wrap">
			<div class="crumb-list">
				<i class="icon-font"></i>
				<a href="./home_page">首页</a>
				<span class="crumb-step">&gt;</span>
				<a class="crumb-name" href="./goods_list">商品管理</a>
				<span class="crumb-step">&gt;</span><span>新增商品</span>
			</div>
		</div>
		<div class="result-wrap">
			<input type="hidden" id="goodpayType" value="${goods.payType }" />
			<div class="result-content">
				<form action="./goods_edit" method="post" id="myform" name="myform" enctype="multipart/form-data">
					<input id="id" name="id" value="${goods.id}" type="hidden">
					<table class="insert-tab" width="100%">
						<tbody>
							<tr>
								<th width="120"><i class="require-red">*</i>商品名称：</th>
								<td><input class="common-text" name="name" size="50" value="${goods.name}" type="text"></td>
							</tr>
							<tr>
								<th>产品主图</th>
								<td><c:if test="${empty goods.mianPic }">
										<img src="images/defalut.png" style="width: 45px; height: 45px;" id="mainimgs" />
									</c:if> <c:if test="${not empty goods.mianPic }">
										<img src="${fileurl}${goods.mianPic}" style="width: 45px; height: 45px;" id="mainimgs" />
									</c:if> <input class="common-text required" id="mainpic" name="mainpic" size="20" value="" type="file" onchange="change('mainimgs','mainpic')">（建议：800*800） <input id="mianPic" name="mianPic"
										value="${goods.mianPic}" type="hidden"></td>
							</tr>
							<tr>
								<th>产品详细图</th>
								<td><c:if test="${empty goods.detailPic1 }">
										<img src="images/defalut.png" style="width: 45px; height: 45px;" id="detailPic1imgs" />
									</c:if> <c:if test="${not empty goods.detailPic1 }">
										<img src="${fileurl}${goods.detailPic1}" style="width: 45px; height: 45px;" id="detailPic1imgs" />
									</c:if> <input class="common-text required" id="detailpic1" name="detailpic1" size="20" value="" type="file" onchange="change('detailPic1imgs','detailpic1')" />（建议：800*800）<br /> <c:if
										test="${empty goods.detailPic2 }">
										<img src="images/defalut.png" style="width: 45px; height: 45px;" id="detailpic2imgs" />
									</c:if> <c:if test="${not empty goods.detailPic2 }">
										<img src="${fileurl}${goods.detailPic2}" style="width: 45px; height: 45px;" id="detailpic2imgs" />
									</c:if> <input class="common-text required" id="detailpic2" name="detailpic2" size="20" value="" type="file" onchange="change('detailpic2imgs','detailpic2')" />（建议：800*800）<br /> <c:if
										test="${empty goods.detailPic3 }">
										<img src="images/defalut.png" style="width: 45px; height: 45px;" id="detailpic3imgs" />
									</c:if> <c:if test="${not empty goods.detailPic3 }">
										<img src="${fileurl}${goods.detailPic3}" style="width: 45px; height: 45px;" id="detailpic3imgs" />
									</c:if> <input class="common-text required" id="detailpic3" name="detailpic3" size="20" value="" type="file" onchange="change('detailpic3imgs','detailpic3')" />（建议：800*800）<br /> <c:if
										test="${empty goods.detailPic4 }">
										<img src="images/defalut.png" style="width: 45px; height: 45px;" id="detailpic4imgs" />
									</c:if> <c:if test="${not empty goods.detailPic4 }">
										<img src="${fileurl}${goods.detailPic4}" style="width: 45px; height: 45px;" id="detailpic4imgs" />
									</c:if> <input class="common-text required" id="detailpic4" name="detailpic4" size="20" value="" type="file" onchange="change('detailpic4imgs','detailpic4')" />（建议：800*800）<br /> <c:if
										test="${empty goods.detailPic5 }">
										<img src="images/defalut.png" style="width: 45px; height: 45px;" id="detailpic5imgs" />
									</c:if> <c:if test="${not empty goods.detailPic5 }">
										<img src="${fileurl}${goods.detailPic5}" style="width: 45px; height: 45px;" id="detailpic5imgs" />
									</c:if> <input class="common-text required" id="detailpic5" name="detailpic5" size="20" value="" type="file" onchange="change('detailpic5imgs','detailpic5')" />（建议：800*800）<br /> <input
										id="detailPic1" name="detailPic1" value="${goods.detailPic1}" type="hidden" /> <input id="detailPic2" name="detailPic2" value="${goods.detailPic2}" type="hidden" /> <input id="detailPic3"
										name="detailPic3" value="${goods.detailPic3}" type="hidden" /> <input id="detailPic4" name="detailPic4" value="${goods.detailPic4}" type="hidden" /> <input id="detailPic5"
										name="detailPic5" value="${goods.detailPic5}" type="hidden" /></td>
							</tr>
							<tr>
								<th><i class="require-red">*</i>所属商家：</th>
								<td>${goods.storeName}<input id="storeName" name="storeName" value="${goods.storeName}" type="hidden"> <input id="storeId" name="storeId" value="${goods.storeId}" type="hidden">
									<!-- <input id="storeId" name="storeId" value="1" type="hidden"></td> -->
							</tr>
							<tr>
								<th><i class="require-red">*</i>所属分类：</th>
								<td><select id="categoryId" name="categoryId" style="width: 150px;">
										<option value="0">请选择分类</option>
										<c:forEach items="${categorylist}" var="category">
											<option value="${category.id }" <c:if test="${category.id==goods.categoryId}">selected="selected"</c:if>>${category.cotegoryName}</option>
										</c:forEach>
								</select> <input id="category" name="category" value="${goods.category}" type="hidden"> <%-- <input id="category_id" value="${goods.category}" type="hidden"> --%></td>
							</tr>
							<!-- 							<tr> -->
							<!-- 								<th height="auto;"><i class="require-red">*</i>商品状态</th> -->
							<!-- 								<td><label><input name="delFlg" value="1" type="radio">上架</label> <label><input name="delFlg" value="0" type="radio" checked="checked">下架</label></td> -->
							<!-- 							</tr> -->
							<tr>
								<th height="auto;"><i class="require-red">*</i>支付类型</th>
								<td><c:if test="${goods.payType == 1 || empty goods.payType}">
										<label><input name="payType" value="1" type="radio" class="radioItem" checked="checked">支付宝（微信）</label>
										<label><input name="payType" value="2" type="radio" class="radioItem">积分（返利）</label>
									</c:if> <c:if test="${goods.payType == 2 }">
										<label><input name="payType" value="1" type="radio" class="radioItem">支付宝（微信）</label>
										<label><input name="payType" value="2" type="radio" class="radioItem" checked="checked">积分（返利）</label>
									</c:if> <!-- <label><input name="goods_status" value="" type="radio">混合支付</label> --></td>
							</tr>
							<tr>
								<th><i class="require-red">*</i>商品价格与返利</th>
								<td>市场价：<input class="common-text required" type="number" name="originPrice" value="${goods.originPrice}" size="6">
									元&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span id="jifen">销售价：</span> <input class="common-text required" type="number" name="price"
										value="${goods.price}" id="goodprice" size="6"><span id="fen">元 </span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 返利收益： <input
										class="common-text required" type="number" name="backMoney" value="${goods.backMoney}" size="6" max="100"> 积分
								</td>

							</tr>

							<tr>
								<th><i class="require-red"></i>商品属性</th>
								<td><input class="btn btn-primary btn6 mr10" value="新增属性" type="button" onclick="addproperty()"> <input type="hidden" id="proindex"
										value="<c:if test="${empty goods.property}">0</c:if><c:if test="${not empty goods.property}">${fn:length(goods.prolist)}</c:if>"></td>
							</tr>
							<c:if test="${not empty goods.property}">
								<c:forEach items="${goods.prolist }" var="pro" varStatus="st">
									<tr>
										<th></th>
										<td>属性名称:<input class="common-text required" type="text" name="proname" value="${pro.name }">&nbsp;&nbsp; 属性值: <c:forEach items="${pro.valuelist }" var="pvalue">
												<input class="common-text required provalue${st.count }" type="text" value="${pvalue }">
											</c:forEach> <input class="btn btn-primary btn6 mr10" value="新增属性值" onclick="addprovalue(this,'${st.count}')" type="button">
											<input type="hidden" name="provalue" id="pvalue${st.count }"></td>
									</tr>
								</c:forEach>
							</c:if>
							<tr id="proafter">
								<th>产品库存</th>
								<td><input class="common-text required" name="stock" size="10" value="${goods.stock}" type="number">件</td>
							</tr>
							<tr>
								<th>商品类型</th>
								<td><select id="isServe" name="isServe" style="width: 150px;">
										<option value="0" <c:if test="${empty goods.isServe || goods.isServe==0}">selected="selected"</c:if>>实物商品</option>
										<option value="1" <c:if test="${goods.isServe==1}">selected="selected"</c:if>>服务商品</option>
										<option value="2" <c:if test="${goods.isServe==2}">selected="selected"</c:if>>充值商品</option>
								</select></td>
							</tr>
							<tr>
								<th>限购数量</th>
								<td><input class="common-text" name="limitNum" size="10" value="${goods.limitNum}" type="text">&nbsp;<span style="color: #f00">*0或不填不限制</span></td>
							</tr>
							<tr>
								<th>产品详情</th>
								<td><textarea name="description" style="width: 80%; height: 350px;">${goods.description}</textarea> <!-- 								<input style="width: 80%; height: 350px;" name="title" value="" type="text"> -->
								</td>
							</tr>
							<c:if
								test="${(sessionScope.adminPrivilege != 1 && sessionScope.adminPrivilege != 2 && sessionScope.adminPrivilege != 3 && sessionScope.adminPrivilege != 4) && allcheck == 0 || sessionScope.adminPrivilege == 5}">
								<tr>
									<th></th>
									<td><input class="btn btn-primary btn6 mr10" value="提交" onclick="checkvalue()" type="button"> <input class="btn btn6" name="reset" value="重置" type="reset"></td>
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