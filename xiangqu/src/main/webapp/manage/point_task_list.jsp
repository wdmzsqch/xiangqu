<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!Doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>后台管理</title>
<link rel="stylesheet" type="text/css" href="css/common.css" />
<link rel="stylesheet" type="text/css" href="css/main.css" />
<link rel="stylesheet" type="text/css" href="css/page.css" />
<script src="./js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/libs/modernizr.min.js"></script>
<script src="./js/page.js"></script>
<script type="text/javascript">
	function selectCity() {
		var parentid = $("#province").val();
		if (parentid != "") {
			$.ajax({
				type : "post",
				url : "./selectMissionArea",
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
				url : "./selectMissionArea",
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
	
	function changenum(missionid){
		var num = $("#num_"+missionid).val();
		if(!IsNum(num)||!(parseInt(num)>0)){
			alert("请输入正确的数字");
			return;
		}
		$.ajax({
			type : "get",
			url : "./edit_task_point?missioid="+missionid+"&point="+num,
			success : function() {
				window.location = window.location;
			}
		});
	}
	
	
	function testshare(mission_id,ramianTimes){
		if(ramianTimes <= 0){
			alert("任务已分享完");
			return;
		}else{
			//if(confirm("是否进行分享")){
				$.ajax({
					type: "post",
					url: "testShare",
					data: {mission_id :mission_id},
					success: function(data){
						if(data == "success"){
							alert("分享成功");
							window.location.reload();
						}else{
							alert("分享失败");
							return;
						}
					}
				});
			//}
		}
	}
	
	function IsNum(s)
	{
	    if(s!=null){
	        var r,re;
	        re = /\d*/i; //\d表示数字,*表示匹配多个数字
	        r = s.match(re);
	        return (r==s)?true:false;
	    }
	    return false;
	}
</script>
</head>
<body>
	<div class="main-wrap" style="margin-left: 0px;">

		<div class="crumb-wrap">
			<!-- <div class="crumb-list">
				<i class="icon-font"></i><a href="./home_page">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">任务管理</span>
			</div> -->
		</div>
		<form action="./point_task_list" method="post" name="pageForm" id="pageForm">
			<div class="search-wrap">
				<div class="search-content">

					<table class="search-tab">
						<tr>
							<th width="120">任务搜索:</th>
							<td><select name="cotegory_id" id="cotegory_id" class="common-text" style="margin-top: 10px;">
									<option value="">--任务类型--</option>
									<c:forEach items="${cotegorylist }" var="cote">
										<c:if test="${cote.id == cotegory_id }">
											<option value="${cote.id}" selected="selected">${cote.name }</option>
										</c:if>
										<c:if test="${cote.id != cotegory_id }">
											<option value="${cote.id}">${cote.name }</option>
										</c:if>
									</c:forEach>
							</select></td>
							<td><select name="searchtype" id="" class="common-text" style="margin-top: 10px;">
									<option value="" <c:if test="${empty searchtype}">selected</c:if>>--任务状态--</option>
									<option value="2" <c:if test="${searchtype==2}">selected</c:if>>进行中</option>
									<option value="3" <c:if test="${searchtype==3}">selected</c:if>>已结束</option>

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
							<td><input class="common-text" placeholder="商家名" name="keywords" value="${keywords}" id="" type="text"></td>
							<td><input class="btn btn-primary btn2" value="搜索" type="submit"></td>
						</tr>
					</table>
					<%-- <div style="position: absolute; right: 40px; top: 115px;">当前总数：${perCount}</div> --%>
				</div>
			</div>
			<div class="result-wrap">
				<!-- <div class="result-title">
					<div class="result-list">
						<input class="btn btn-primary btn2" name="creat_task" value="新建任务" type="button" onclick="javascript:location='./task_detail'">
					</div>
				</div> -->
				<div class="result-content">
					<table class="result-tab" width="100%">
						<tr>
							<th class="tc" width="60" style="min-width: 60px;">任务编号</th>
							<th style="min-width: 75px;">合同编号</th>
							<th style="min-width: 75px;">所属商家</th>
							<th style="min-width: 75px;">任务主题</th>
							<th width="185" style="min-width: 185px;">投放起止时间</th>
							<th width="75" style="min-width: 75px;">任务状态</th>
							<th width="75" style="min-width: 75px;">总点击数</th>
							<th width="80" style="min-width: 80px;">前端点击数</th>
							<th width="80" style="min-width: 80px;">增加点击数</th>
							<th width="80" style="min-width: 80px;">500号的数量</th>
							<th width="100" style="min-width: 100px;">操作</th>
						</tr>

						<c:forEach items="${missionlist}" var="mission" varStatus="st">
							<tr>
								<td class="tc">${mission.id}</td>
								<td>${mission.contractNo}</td>
								<td>${mission.shopName}</td>
								<td>${mission.name}</td>
								<td><fmt:formatDate value="${mission.startDate}" type="date" />至<fmt:formatDate value="${mission.endTime}" type="date" /></td>
								<td><c:if test="${mission.state<=2}">进行中</c:if> <c:if test="${mission.state>=3}">已结束</c:if></td>
								<td>${mission.totalTimes}</td>
								<td>${mission.totalTimes - mission.ramianTimes}</td>
								<td>${mission.brushNum}</td>
								<td><input id="num_${mission.id}" class="common-text" style="width: 100px; min-width: 70px;" type="number" value="${mission.showTimes }"></td>
								<td><a class="link-del" onclick="changenum(${mission.id})" >修改增加点击数</a>
								<br />
								<a class="link-del" style="cursor: pointer;" onclick="testshare(${mission.id},${mission.ramianTimes})" >分享</a>
								</td>
							</tr>
						</c:forEach>

					</table>

					<c:if test="${pageCount>1}">
						<input type="hidden" value="${pageCount }" id="pageCount" name="pageCount" />
						<input type="hidden" value="${pageIndex }" id="pageIndex" name="pageIndex" />
						<input type="hidden" value="${childreninfo.id }" id="columnid" name="columnid" />
						<div id="untreatedpage"></div>
						<script type="text/javascript">
							//container 容器，count 总页数 pageindex 当前页数
							function setPage(container, count, pageindex) {
								// 						var container = container;
								// 						var count = parseInt(count);
								// 						var pageindex = parseInt(pageindex);
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


	<!-- 	<div class="mask" style="display: none;" onclick="hide()"></div>
	<div class="alert" style="position: fixed; display: none; width: 400px; height: 120px; overflow: hidden; background: #fff; z-index: 101; padding: 20px;"></div>
	<div class="addTimesalert" style="position: fixed; border-radius: 8px; display: none; width: 400px; height: 240px; overflow: hidden; background: #fff; z-index: 101; padding: 20px;">
	<form action="" method="post" id="addTimesForm">
	<input type="hidden" id="alert_id" name="mission_id"/>
	<p style="text-align: center; font-size: 24px;">加投数量</p>
	<br>
	<div style="width: 180px; height: 40px; margin: 0 auto;">数量：<input id="alert_times" name="times" style="width: 120px; height: 38px; border: 1px solid #e8e8e8;"/></div>
	<br>
	<div style="width: 145px; height: 40px; margin: 0 auto;">
	<div style="width: 60px; height: 38px; background: #428bca; color: #fff; float: left; text-align: center; line-height: 38px; cursor: pointer;" onclick="submitTimes()">确定</div>
	<div style="width: 60px; height: 38px; background: #ccc; color: #fff; float: right; text-align: center; line-height: 38px; cursor: pointer;" onclick="hide()">取消</div>
	</div>
	</form>
	</div> -->

</body>

</html>