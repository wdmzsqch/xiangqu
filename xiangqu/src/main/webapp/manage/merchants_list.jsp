<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>后台管理</title>
<link rel="stylesheet" type="text/css" href="css/common.css" />
<link rel="stylesheet" type="text/css" href="css/main.css" />
<link rel="stylesheet" type="text/css" href="css/page.css" />
<script type="text/javascript" src="js/libs/modernizr.min.js"></script>
<script src="./js/jquery-1.11.3.min.js"></script>
<script src="./js/page.js"></script>
<script type="text/javascript">
	function selectCity() {
		var parentid = $("#province").val();
		if (parentid != "") {
			$.ajax({
				type : "post",
				url : "./selectShopArea",
				data : {
					parentid : parentid,
					type : 1
				},
				success : function(msg) {
					var msgdata = eval("(" + msg + ")");
					var citylist = msgdata.citylist;
					var arealist = msgdata.arealist;
					$("#city").empty();
					$("#area").empty();
					html = '';
					html += '<option value="">市</option>';
					for (var i = 0; i < citylist.length; i++) {
						html += '<option value="'+citylist[i].id+'">' + citylist[i].name + '</option>';
					}
					$("#city").append(html);
					html2 = '';
					html2 += '<option value="">区</option>';
					for (var i = 0; i < arealist.length; i++) {
						html2 += '<option value="'+arealist[i].id+'">' + arealist[i].name + '</option>';
					}
					$("#area").append(html2);
				}
			});
		} else {
			$("#city").empty();
			$("#area").empty();
			html = '';
			html += '<option value="">市</option>';
			html2 = '';
			html2 += '<option value="">区</option>';
			$("#city").append(html);
			$("#area").append(html2);
		}
	}

	function selectArea() {
		var parentid = $("#city").val();
		if (parentid != "") {
			$.ajax({
				type : "post",
				url : "./selectShopArea",
				data : {
					parentid : parentid,
					type : 2
				},
				success : function(msg) {
					var msgdata = eval("(" + msg + ")");
					var arealist = msgdata.arealist;
					$("#area").empty();
					html = '';
					html += '<option value="">区</option>';
					for (var i = 0; i < arealist.length; i++) {
						html += '<option value="'+arealist[i].id+'">' + arealist[i].name + '</option>';
					}
					$("#area").append(html);
				}
			});
		} else {
			$("#area").empty();
			html = '';
			html += '<option value="">区</option>';
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
				<i class="icon-font"></i>
				<a href="./home_page">首页</a>
				<span class="crumb-step">&gt;</span><span class="crumb-name">商家管理</span>
			</div>
		</div>
		<form action="./merchants_list" method="post" id="pageForm" name="pageForm">
			<div class="search-wrap">
				<div class="search-content">

					<table class="search-tab">
						<tr>
							<!-- <th width="120">选择分类:</th>
                           <td>
                                <select name="search-sort" id="">
                                    <option value="">全部</option>
                                    <option value="19">精品界面</option><option value="20">推荐界面</option>
                                </select>
                            </td> -->
							<th width="80">商家搜索:</th>
							<td><input class="common-text" placeholder="关键字" name="keywords" value="${keywords}" id="" type="text"></td>
							<td><select name="industry_id" id="industry_id" class="common-text" style="margin-top: 10px;">
									<option value="">--行业选择--</option>
									<c:forEach items="${industrylist }" var="in">
										<c:if test="${in.id == industry_id }">
											<option value="${in.id}" selected="selected">${in.name }</option>
										</c:if>
										<c:if test="${in.id != industry_id }">
											<option value="${in.id}">${in.name }</option>
										</c:if>
									</c:forEach>
							</select></td>
							<td><select name="province" id="province" class="common-text" style="margin-top: 10px;" onchange="selectCity()">
									<option value="">省</option>
									<c:forEach items="${prolist }" var="prolist">
										<c:if test="${prolist.id == province }">
											<option value="${prolist.id}" selected="selected">${prolist.name }</option>
										</c:if>
										<c:if test="${prolist.id != province }">
											<option value="${prolist.id}">${prolist.name }</option>
										</c:if>
									</c:forEach>
							</select></td>
							<td><select name="city" id="city" class="common-text" style="margin-top: 10px;" onchange="selectArea()">
									<option value="">市</option>
									<c:forEach items="${citylist }" var="citylist">
										<c:if test="${citylist.id == city }">
											<option value="${citylist.id}" selected="selected">${citylist.name }</option>
										</c:if>
										<c:if test="${citylist.id != city }">
											<option value="${citylist.id}">${citylist.name }</option>
										</c:if>
									</c:forEach>
							</select></td>
							<td><select name="area" id="area" class="common-text" style="margin-top: 10px;">
									<option value="">区</option>
									<c:forEach items="${arealist }" var="arealist">
										<c:if test="${arealist.id == area }">
											<option value="${arealist.id}" selected="selected">${arealist.name }</option>
										</c:if>
										<c:if test="${arealist.id != area }">
											<option value="${arealist.id}">${arealist.name }</option>
										</c:if>
									</c:forEach>
							</select></td>
							<td><input class="btn btn-primary btn2" name="sub" value="搜索" type="submit"></td>
						</tr>
					</table>
					<div style="position: absolute; right: 40px; top: 115px;">当前总数：${perCount}</div>
				</div>
			</div>
			<div class="result-wrap">
				<c:if test="${ not (sessionScope.adminPrivilege == 8) }">
					<div class="result-title">
						<div class="result-list">
							<input class="btn btn-primary btn2" name="creat" value="新增商家" type="button" onClick="javascript:location='./merchant_detail'">
						</div>
					</div>
				</c:if>
				<div class="result-content">
					<table class="result-tab" width="100%">
						<tr>
							<th class="tc" width="10%">商家编号</th>
							<th>用户名</th>
							<th>商家名称</th>
							<th>联系电话</th>
							<th>手机号码</th>
							<th>地址</th>
							<c:if test="${ not (sessionScope.adminPrivilege == 8) }">
								<th>操作</th>
							</c:if>
						</tr>
						<c:forEach items="${shoplist}" var="shop">
							<tr>
								<td class="tc">${shop.id}</td>
								<td>${shop.userName}</td>
								<td>${shop.companyName}</td>
								<td>${shop.phone}</td>
								<td>${shop.mobile}</td>
								<td>${shop.proname}${shop.cityname}${shop.areaname}${shop.address}</td>
								<c:if test="${ not (sessionScope.adminPrivilege == 8) }">
									<td width="8%"><a class="link-update" href="./merchant_detail?shopid=${shop.id}">修改</a> <a class="link-del" href="./del_merchant?shopid=${shop.id}">删除</a></td>
								</c:if>
							</tr>
						</c:forEach>
					</table>

					<br />
					<c:if test="${pageCount>1}">
						<input type="hidden" value="${pageCount }" id="pageCount" name="pageCount" />
						<input type="hidden" value="${pageIndex }" id="pageIndex" name="pageIndex" />
						<div id="untreatedpage"></div>
						<script type="text/javascript">
							//container 容器，count 总页数 pageindex 当前页数
							function setPage(container, count, pageindex) {
								var a = [];
								//总页数少于10 全部显示,大于10 显示前3 后3 中间3 其余....
								if (pageindex == 1) {
									//alert(pageindex);
									a[a.length] = "<a onclick=\"\" class=\"hide_page_prev pagehover unclickprev \">&lt;&nbsp;上一页</a>";
								} else {
									a[a.length] = "<a onclick=\"pageprev()\" class=\"pagehover page_prev\">&lt;&nbsp;上一页</a>";
								}
								function setPageList() {
									if (pageindex == i) {
										a[a.length] = "<a onclick=\"pageto(" + i + ")\" class=\"on\">" + i + "</a>";
									} else {
										a[a.length] = "<a onclick=\"pageto(" + i + ")\">" + i + "</a>";
									}
								}
								//总页数小于10
								if (count <= 10) {
									for (var i = 1; i <= count; i++) {
										setPageList();
									}
									;
								} else {
									//总页数大于10页
									if (pageindex <= 4) {
										for (var i = 1; i <= 5; i++) {
											setPageList();
										}
										a[a.length] = "...<a onclick=\"pageto(" + count + ")\">" + count + "</a>";
									} else if (pageindex >= count - 3) {
										a[a.length] = "<a onclick=\"pageto(1)\">1</a>...";
										for (var i = count - 4; i <= count; i++) {
											setPageList();
										}
										;
									} else { //当前页在中间部分
										a[a.length] = "<a onclick=\"pageto(1)\">1</a>...";
										for (var i = pageindex - 2; i <= pageindex + 2; i++) {
											setPageList();
										}
										a[a.length] = "...<a onclick=\"pageto(" + count + ")\">" + count + "</a>";
									}
								}
								if (pageindex == count) {
									a[a.length] = "<a onclick=\"\" class=\"hide_page_next pagehover unclicknext \">下一页&nbsp;&gt;</a> ";
								} else {
									a[a.length] = "<a onclick=\"pagenext()\" " + "class=\"pagehover page_next \">下一页&nbsp;&gt;</a> ";
								}
								container.innerHTML = a.join("");
							}
							setPage(document.getElementById("untreatedpage"), parseInt($("#pageCount").val()), parseInt($("#pageIndex").val()));
						</script>
					</c:if>


				</div>
			</div>



		</form>
	</div>

</body>
</html>