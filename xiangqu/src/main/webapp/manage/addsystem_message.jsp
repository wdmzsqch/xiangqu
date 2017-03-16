<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统消息管理</title>
<link rel="stylesheet" type="text/css" href="css/common.css"/>
<link rel="stylesheet" type="text/css" href="css/main.css"/>
<script src="./js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/libs/modernizr.min.js"></script>
<script type="text/javascript">
function checkinput(){
	var title = $("#title").val();
	var content = $("#content").val();
	if(title==''){
		alert("请输入标题!");
		$("#title").focus();
		return ;
	}
	if(content==''){
		alert("请输入内容!");
		$("#content").focus();
		return;
	}
	$.ajax({
		url:'./sendsysmessage',
		type:'post',
		data:$("#myform").serialize(),
		success:function(msg){
			alert("发送消息成功!");
			window.location.href="./system_message";
		}
	})
}
</script>
</head>
<body>
<jsp:include page="./sidebar.jsp"></jsp:include>
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="./home_page">首页</a><span class="crumb-step">&gt;</span>系统消息管理</div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form action="./sendsysmessage" method="post" id="myform" name="myform" enctype="multipart/form-data">
                    <table class="insert-tab" width="100%">
                        <tbody>
                        <tr>
                            <th width="120"><i class="require-red">*</i>标题：</th>
                            <td>
                                <input class="common-text" id="title" name="title" size="50" value="" type="text">
                            </td>
                        </tr>
                        <tr>
                            <th width="120"><i class="require-red">*</i>内容：</th>
                            <td>
                                <textarea class="common-text" id="content" name="content" style="width:420px;height:200px;resize:none;"></textarea>
                            </td>
                        </tr>
                            <tr>
                                <th><i class="require-red">*</i>发送用户：</th>
                                <td>
                                    <select name="type" style="width:auto;">
                                    <option value="1">用户</option>
                                    <option value="2">商家</option>
                                    <option value="3">全部</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                               
                            
                                <th></th>
                                <td>
                                    <input class="btn btn-primary btn6 mr10" value="发送" type="button" onclick="checkinput()">
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