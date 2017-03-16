<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>后台管理</title>
    <link rel="stylesheet" type="text/css" href="css/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
    <script src="./js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="js/libs/modernizr.min.js"></script>
    <script type="text/javascript">
    $(function(){
    	var roletype = $("#roletype").val();
		if(roletype == 6 || roletype == 9){
			$("#areaplace").show();
		}else{
			$("#areaplace").hide();
		}
    });
    
	function checkinput(){
		var userName = $("input[name=userName]").val();
		if(userName == ""){
			alert("请输入用户名");
			return false;
		}
		var realName = $("input[name=realName]").val();
		if(realName == ""){
			alert("请输入真实姓名");
			return false;
		}
		var mobile = $("input[name=mobile]").val();
		if(mobile == ""){
			alert("请输入电话号码");
			return false;
		}
		var roletype = $("#roletype").val();
		if(roletype == 6){
			var province = $("#province").val();
			if(province == ""){
				alert("至少选择省份");
				return;
			}
		}
		var id = $("input[name=id]").val();
		if(id == ""){
			var password = $("input[name=password]").val();
			if(password == ""){
				alert("请输入密码");
				return false;
			}
		}
		$.ajax({
			type: "post",
			url: "./admin_editor",
			data: $("#myform").serialize(),
			success: function(data){
				if(data == "success"){
					if('${sessionScope.adminPrivilege}' == 9){
						window.location.href = "./area_account_list";
					}else{
						window.location.href = "./account_list";
					}
				}else{
					alert("账号已存在");
					return;
				}
			}
		});
	}
	
	function changeType(){
		var roletype = $("#roletype").val();
		if(roletype == 6 || roletype == 9){
			$("#areaplace").show();
		}else{
			$("#areaplace").hide();
		}
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
			html += '<option value="0">请选择</option>';
			$("#area").append(html);
		}
	}
</script>
</head>
<body>
<jsp:include page="./sidebar.jsp"></jsp:include>
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="./home_page">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="./account_list">权限账号列表</a><span class="crumb-step">&gt;</span>新建账号</div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form action="" method="post" id="myform" name="myform" enctype="multipart/form-data">
                	<input type="hidden" value="${admin.id }" name="id"/>
                    <table class="insert-tab" width="100%">
                        <tbody>
                        <tr>
                            <th width="120"><i class="require-red">*</i>用户名：</th>
                            <td>
                                <input class="common-text" name="userName" size="50" value="${admin.userName }" type="text">
                            </td>
                        </tr>
                        <tr>
                            <th width="120"><i class="require-red">*</i>真实姓名：</th>
                            <td>
                                <input class="common-text" name="realName" size="50" value="${admin.realName }" type="text">
                            </td>
                        </tr>
                        <tr>
                            <th width="120"><i class="require-red">*</i>手机号码：</th>
                            <td>
                                <input class="common-text" name="mobile" size="50" value="${admin.mobile }" type="text">
                            </td>
                        </tr>
                            <tr>
                                <th><i class="require-red">*</i>密码：</th>
                                <td>
                                    <input class="common-text required" id="title" name="password" size="50" value="" type="password">
                                </td>
                            </tr>
                            <tr>
                                <th><i class="require-red">*</i>所属权限组：</th>
                                <td>
                                    <select name="type" id="roletype" style="width:auto;" onchange="changeType()">
                                    <c:if test="${sessionScope.adminPrivilege != 9 }">
                                     <c:forEach items="${rolelist }" var="role">
                                    	<c:if test="${admin.type == role.id }">
                                    	<option  value="${role.id }" selected="selected">${role.roleName }</option>
                                    	</c:if>
                                    	<c:if test="${admin.type != role.id }">
                                    	<option  value="${role.id }">${role.roleName }</option>
                                    	</c:if>
                                    </c:forEach>
                                    </c:if>
                                    <c:if test="${sessionScope.adminPrivilege == 9 }">
                                    	<option  value="6" selected="selected">地区管理员</option>
                                    </c:if>
                                    </select>
                                </td>
                            </tr>
                            <tr id="areaplace" style="display: none">
                                <th><i class="require-red">*</i>管理地区：</th>
                                <td>
                                    <select name="province" id="province" class="common-text" style="height: 27px;" onchange="selectCity()">
										<option value="">请选择</option>
										<c:forEach items="${prolist }" var="pro">
											<c:if test="${pro.id == province_id}">
												<option value="${pro.id}" selected="selected">${pro.name }</option>
											</c:if>
											<c:if test="${pro.id != province_id}">
												<option value="${pro.id}">${pro.name }</option>
											</c:if>
										</c:forEach>
								</select>&nbsp;<select name="city" id="city" class="common-text" style="height: 27px;" onchange="selectArea()">
										<option value="">请选择</option>
										<c:forEach items="${citylist }" var="city">
											<c:if test="${city.id == city_id}">
												<option value="${city.id}" selected="selected">${city.name }</option>
											</c:if>
											<c:if test="${city.id != city_id}">
												<option value="${city.id}">${city.name }</option>
											</c:if>
										</c:forEach>
								</select>&nbsp;&nbsp;<select name="area" id="area" class="common-text" style="height: 27px;">
										<option value="">请选择</option>
										<c:forEach items="${arealist }" var="area">
											<c:if test="${area.id == area_id}">
												<option value="${area.id}" selected="selected">${area.name }</option>
											</c:if>
											<c:if test="${area.id != area_id}">
												<option value="${area.id}">${area.name }</option>
											</c:if>
										</c:forEach>
								</select>
                                </td>
                            </tr>
                            
                            <tr>
                               
                            
                                <th></th>
                                <td>
                                    <input class="btn btn-primary btn6 mr10" value="提交" type="button" onclick="checkinput()">
<!--                                     <input class="btn btn6" name="reset"  value="重置" type="reset"> -->
                                </td>
                            </tr>
                        </tbody></table>
                </form>
            </div>
        </div>

    </div>
    
</body>
</html>