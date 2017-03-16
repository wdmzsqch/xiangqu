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
<link rel="stylesheet" type="text/css" href="css/page.css" />
<script src="./js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/libs/modernizr.min.js"></script>
<script src="./js/page.js"></script>
<script type="text/javascript">
$(function(){
	if('${checktype}'== 6){
		$("#province").attr("disabled","disabled");
		$("#city").attr("disabled","disabled");
		$("#area").attr("disabled","disabled");
	}
});

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
				html += '<option value="">市</option>';
				for(var i= 0; i< citylist.length; i++){
					html += '<option value="'+citylist[i].id+'">'+citylist[i].name+'</option>';
				}
				$("#city").append(html);
				html2 = '';
				html2 += '<option value="">区</option>';
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
		html += '<option value="">市</option>';
		html2 = '';
		html2 += '<option value="">区</option>';
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
				html += '<option value="">区</option>';
				for(var i= 0; i< arealist.length; i++){
					html += '<option value="'+arealist[i].id+'">'+arealist[i].name+'</option>';
				}
				$("#area").append(html);
			}
		});
	}else{
		$("#area").empty();
		html = '';
		html += '<option value="">区</option>';
		$("#area").append(html);
	}
}

function submitTimes(){
	var alert_times = $("#alert_times").val();
	if(alert_times == ""){
		alert("添加数量不能为空");
		return;
	}
	$.ajax({
		type: "post",
		url: "./addtimes",
		data:  $("#addTimesForm").serialize(),
		success: function(data){
			if(data == "success"){
				alert("添加成功");
				window.location.reload();
			}
		}
	});
}

function outLine(id,checkstate1,checkstate2,checkstate3,checkstate4){
	if(checkstate1 == 0){
		alert("内容未审核");
		return;
	}else if(checkstate2 == 0){
		alert("技术未审核");
		return;
	}else if(checkstate3 == 0){
		alert("财务未审核");
		return;
	}else if(checkstate4 == 0){
		alert("最终未审核");
		return;
	}else{
		if(confirm("是否下线该任务")){
			$.ajax({
				type: "post",
				url: "./online",
				data: {id :id, type :1},
				success: function(data){
					if(data == "success"){
						alert("下线成功");
						window.location.reload();
					}
				}
			});
		}
	}
}

function onLine(id,checkstate1,checkstate2,checkstate3,checkstate4){
	if(checkstate1 == 0){
		alert("内容未审核");
		return;
	}else if(checkstate2 == 0){
		alert("技术未审核");
		return;
	}else if(checkstate3 == 0){
		alert("财务未审核");
		return;
	}else if(checkstate4 == 0){
		alert("最终未审核");
		return;
	}else{
		if(confirm("是否上线该任务")){
			$.ajax({
				type: "post",
				url: "./online",
				data: {id :id, type :2},
				success: function(data){
					if(data == "success"){
						alert("上线成功");
						window.location.reload();
					}
				}
			});
		}
	}
}

function getmissionurl(id){
	$(".mask").show();
	$(".alert").css("top",($(".mask").height()-120)/2);
	$(".alert").css("left",($(".mask").width()-400)/2);
	$(".alert").text("${webrooturl}"+"user/missiondetail?id="+id);
	$(".alert").show();
}

function getscancodemissionurl(id){
	$(".mask").show();
	$(".alert").css("top",($(".mask").height()-120)/2);
	$(".alert").css("left",($(".mask").width()-400)/2);
	$(".alert").text("${webrooturl}"+"user/scancode?mission_id="+id);
	$(".alert").show();
}

function showaddtimes(id){
	$(".mask").show();
	$("#alert_id").val(id);
	$(".addTimesalert").css("top",($(".mask").height()-240)/2);
	$(".addTimesalert").css("left",($(".mask").width()-400)/2);
	$(".addTimesalert").show();
}

function hide(){
	$(".mask").hide();
	$(".alert").hide();
	$(".addTimesalert").hide();
}

function check(relativeId,index){
	var comment = $("#comment_"+index).val();
	if(confirm("是否确认审核？")){
		$.ajax({
			type: "post",
			url: "./adminCheck",
			data:{relativeId :relativeId, type :1, comment :comment},
			success: function(data){
				if(data == "success"){
					alert("审核成功");
					window.location.reload();
				}
			}
		});
	}
}

function endmission(id,checkstate1,checkstate2,checkstate3,checkstate4){
	if(checkstate1 == 0){
		alert("内容未审核");
		return;
	}else if(checkstate2 == 0){
		alert("技术未审核");
		return;
	}else if(checkstate3 == 0){
		alert("财务未审核");
		return;
	}else if(checkstate4 == 0){
		alert("最终未审核");
		return;
	}else{
		window.location.href="./endMission?id="+id;
	}
}

function testshare(mission_id,ramianTimes){
	if(ramianTimes <= 0){
		alert("任务已分享完");
		return;
	}else{
		if(confirm("是否进行分享")){
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
		}
	}
}
</script>
</head>
<body>
	<jsp:include page="./sidebar.jsp"></jsp:include>
	<div class="main-wrap">

		<div class="crumb-wrap">
			<div class="crumb-list">
				<i class="icon-font"></i><a href="./home_page">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">任务管理</span>
			</div>
		</div>
		<form action="./task_list" method="post" name="pageForm" id="pageForm">
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
							<td>
							<c:if test="${sessionScope.adminPrivilege == 6}">
							可用条数：${leftcount}条
							已用条数：${usecount}条
							</c:if>
							</td>
						</tr>
					</table>
					<div style="float: right; margin-right: 40px; margin-top: -30px;">当前总数：${perCount}</div>
				</div>
			</div>
			<div class="result-wrap">
				<div class="result-title">
					<div class="result-list">
					<c:if test="${sessionScope.adminPrivilege != 1  && sessionScope.adminPrivilege != 2 && sessionScope.adminPrivilege != 3 && sessionScope.adminPrivilege != 4}">
						<input class="btn btn-primary btn2" name="creat_task" value="新建任务" type="button" onclick="javascript:location='./task_detail'">
					</c:if>
					</div>
				</div>
				<div class="result-content">
					<table class="result-tab" width="100%">
						<tr>
							<th class="tc" width="10%">任务编号</th>
							<th>合同编号</th>
							<th>所属商家</th>
							<th>任务主题</th>
							<th>投放起止时间</th>
							<th>审核状态</th>
							<c:if test="${sessionScope.adminPrivilege == 5 || sessionScope.adminPrivilege == 6}">
							<th>任务状态</th>
							<th>已发放积分</th>
							<th>剩余积分</th>
							<th>已投放条数</th>
							<th>剩余条数</th>
							</c:if>
							<c:if test="${sessionScope.adminPrivilege == 1 || sessionScope.adminPrivilege == 2 || sessionScope.adminPrivilege == 3 || sessionScope.adminPrivilege == 4}">
							<th>备注</th>
							</c:if>
							<th width="7%">操作</th>
						</tr>
						
						<c:forEach items="${missionlist}" var="mission" varStatus="st">
							<tr>
								<td class="tc">${mission.id}</td>
								<td>${mission.contractNo}</td>
								<td>${mission.shopName}</td>
								<td>${mission.name}</td>
								<td><fmt:formatDate value="${mission.startDate}" type="date" />至<fmt:formatDate value="${mission.endTime}" type="date" /></td>
								<td width="10%">
								<c:if test="${mission.checkStatus == 1 }">
								内容已审核
								</c:if>
								<c:if test="${mission.checkStatus == 0 }">
								内容未审核
								</c:if>
								<br />
								<c:if test="${mission.checkStatusY == 1 }">
								技术已审核
								</c:if>
								<c:if test="${mission.checkStatusY == 0 }">
								技术未审核
								</c:if>
								<br />
								<c:if test="${mission.checkStatusC == 1 }">
								财务已审核
								</c:if>
								<c:if test="${mission.checkStatusC == 0 }">
								财务未审核
								</c:if>
								<br />
								<c:if test="${mission.checkStatusB == 1 }">
								最终已审核
								</c:if>
								<c:if test="${mission.checkStatusB == 0 }">
								最终未审核
								</c:if>
								</td>
								<c:if test="${sessionScope.adminPrivilege == 5  || sessionScope.adminPrivilege == 6}">
								<td><c:if test="${mission.state<=2}">进行中</c:if>
								<c:if test="${mission.state>=3}">已结束</c:if></td>
<%-- 								<td><fmt:formatNumber value="${mission.income*(mission.totalTimes-mission.ramianTimes)/100}" pattern="#,#0.00#"/>元</td> --%>
								<td><fmt:formatNumber value="${mission.income*(mission.totalTimes-mission.ramianTimes)}"  maxFractionDigits="0"/>积分</td>
<%-- 								<td><fmt:formatNumber value="${mission.income*mission.ramianTimes/100}" pattern="#,#0.00#"/>元</td> --%>
								<td><fmt:formatNumber value="${mission.income*mission.ramianTimes}" maxFractionDigits="0"/>积分</td>
								<td><fmt:formatNumber value="${mission.totalTimes-mission.ramianTimes}" maxFractionDigits="0"/></td>
								<td><fmt:formatNumber value="${mission.ramianTimes}" maxFractionDigits="0"/></td>
								</c:if>
								<c:if test="${sessionScope.adminPrivilege == 1 || sessionScope.adminPrivilege == 2 || sessionScope.adminPrivilege == 3 || sessionScope.adminPrivilege == 4 }">
								<td>
								<c:if test="${checktype == 1 }">
								<input id="comment_${st.index }" class="common-text" value="${mission.comment }">
								<br>
								<input  class="common-text" value="${mission.comment1 }" readonly="readonly" disabled="disabled">
								<br>
								<input  class="common-text" value="${mission.comment2 }" readonly="readonly" disabled="disabled">
								<br>
								<input  class="common-text" value="${mission.comment3 }" readonly="readonly" disabled="disabled">
								</c:if>
								<c:if test="${checktype == 2 }">
								<input  class="common-text" value="${mission.comment }" readonly="readonly" disabled="disabled">
								<br>
								<input id="comment_${st.index }"  class="common-text" value="${mission.comment1 }">
								<br>
								<input  class="common-text" value="${mission.comment2 }" readonly="readonly" disabled="disabled">
								<br>
								<input  class="common-text" value="${mission.comment3 }" readonly="readonly" disabled="disabled">
								</c:if>
								<c:if test="${checktype == 3 }">
								<input  class="common-text" value="${mission.comment }" readonly="readonly" disabled="disabled">
								<br>
								<input  class="common-text" value="${mission.comment1 }" readonly="readonly" disabled="disabled">
								<br>
								<input id="comment_${st.index }" class="common-text" value="${mission.comment2 }">
								<br>
								<input  class="common-text" value="${mission.comment3 }" readonly="readonly" disabled="disabled">
								</c:if>
								<c:if test="${checktype == 4 }">
								<input  class="common-text" value="${mission.comment }" readonly="readonly" disabled="disabled">
								<br>
								<input  class="common-text" value="${mission.comment1 }" readonly="readonly" disabled="disabled">
								<br>
								<input  class="common-text" value="${mission.comment2 }" readonly="readonly" disabled="disabled">
								<br>
								<input id="comment_${st.index }" class="common-text" value="${mission.comment3 }">
								</c:if>
								</td>
								</c:if>
								<td width="6%">
								<c:if test="${sessionScope.adminPrivilege == 5 }">
								<a class="link-update" style="cursor: pointer;" onclick="showaddtimes(${mission.id})">加投数量</a>
								<br/>
								<a class="link-del" onclick="getmissionurl(${mission.id})">获取地址</a>
								<br/>
								<a class="link-del" onclick="getscancodemissionurl(${mission.id})">获取扫码地址</a>
								<br/>
								<a class="link-del" href="./task_detail?id=${mission.id}">查看详情</a>
								<br/>
								<a class="link-del" href="./sort_up?id=${mission.id}">置顶</a>
								<br/>
								<c:if test="${mission.state <= 2 }">
								<a class="link-del" onclick="endmission(${mission.id},${mission.checkStatus},${mission.checkStatusY},${mission.checkStatusC},${mission.checkStatusB})">结束任务</a>
								<br/>
								</c:if>
								<c:if test="${mission.online != 1}">
								<a class="link-del" href="" onclick="outLine(${mission.id},${mission.checkStatus},${mission.checkStatusY},${mission.checkStatusC},${mission.checkStatusB})">下线</a>
								</c:if>
								<c:if test="${mission.online == 1}">
								<a class="link-del" href="" onclick="onLine(${mission.id},${mission.checkStatus},${mission.checkStatusY},${mission.checkStatusC},${mission.checkStatusB})">上线</a>
								</c:if>
								</c:if>
								<c:if test="${(sessionScope.adminPrivilege == 8) }">
								<%-- <a class="link-update" style="cursor: pointer;" onclick="showaddtimes(${mission.id})">加投数量</a>
								<br/> --%>
								<a class="link-del" onclick="getmissionurl(${mission.id})">获取地址</a>
								<br/>
								<a class="link-del" onclick="getscancodemissionurl(${mission.id})">获取扫码地址</a>
								<br/>
								<a class="link-del" href="./task_detail?id=${mission.id}">查看详情</a>
								<br/>
								<a class="link-del" href="./sort_up?id=${mission.id}">置顶</a>
								<br/>
								<c:if test="${mission.state <= 2 }">
								<a class="link-del" onclick="endmission(${mission.id},${mission.checkStatus},${mission.checkStatusY},${mission.checkStatusC},${mission.checkStatusB})">结束任务</a>
								<br/>
								</c:if>
								<c:if test="${mission.online == 1}">
								<a class="link-del" href="" onclick="onLine(${mission.id},${mission.checkStatus},${mission.checkStatusY},${mission.checkStatusC},${mission.checkStatusB})">上线</a>
								</c:if>
								</c:if>
								<c:if test="${sessionScope.adminPrivilege == 1 || sessionScope.adminPrivilege == 2 || sessionScope.adminPrivilege == 3 || sessionScope.adminPrivilege == 4 }">
								<c:if test="${mission.isCheck == 0}">
								<a class="link-update" style="cursor: pointer;" onclick="check(${mission.id},${st.index })">审核</a>
								</c:if>
								<c:if test="${mission.isCheck == 1}">
								已审核
								</c:if>
								<br/>
								<a class="link-del" href="./task_detail?id=${mission.id}">查看详情</a>
								</c:if>
								<c:if test="${sessionScope.adminPrivilege == 6 }">
								<a class="link-del" href="./task_detail?id=${mission.id}">查看详情</a>
								</c:if>
								</td>
							</tr>
						</c:forEach>

					</table>

					<c:if test="${pageCount>1}">
					<input type="hidden" value="${pageCount }" id="pageCount" name="pageCount"/>
					<input type="hidden" value="${pageIndex }" id="pageIndex" name="pageIndex"/>
					<input type="hidden" value="${childreninfo.id }" id="columnid" name="columnid"/>
					<div id="untreatedpage" ></div>
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
						    };
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
						      };
						    } else { //当前页在中间部分
						      a[a.length] = "<a onclick=\"pageto(1)\">1</a>...";
						      for (var i = pageindex - 2; i <= pageindex+2; i++) {
						        setPageList();
						      }
						      a[a.length] = "...<a onclick=\"pageto(" + count + ")\">" + count + "</a>";
						    }
						  }
						  if (pageindex == count) {
							    a[a.length] = "<a onclick=\"\" class=\"hide_page_next pagehover unclicknext \">下一页&nbsp;&gt;</a> ";
							  } else {
							    a[a.length] = 
							    	"<a onclick=\"pagenext()\" "+
							    	"class=\"pagehover page_next \">下一页&nbsp;&gt;</a> ";
							  }
						  container.innerHTML = a.join("");
						} 
						setPage(document.getElementById("untreatedpage"),parseInt($("#pageCount").val()),parseInt($("#pageIndex").val()));
						</script>
					</c:if>

				</div>

			</div>
		</form>

	</div>
	
	
	<div class="mask" style="display: none;" onclick="hide()"></div>
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
	</div>

</body>

</html>