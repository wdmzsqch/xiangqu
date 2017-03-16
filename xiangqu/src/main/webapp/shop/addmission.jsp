<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, user-scalable=no">
<title>发布任务</title>
<script type="text/javascript" src="./js/jquery-1.11.3.min.js"></script>
</head>
<body style="font-family:'华文细黑';margin:0px;">
<form action="" id="labelform"> 
<div style="text-align:center;width:100%;height:30px;line-height:30px">任务概况</div>
<textarea id="intro" name="intro" style="border-radius:4px;height:210px;-webkit-appearance:none;resize:none;margin-left:5%;width: 90%;">
</textarea>
<input type="button" onclick="addmission()" value="提交任务" style="height:30px;line-height:30px;margin-top:10px;border: none;-webkit-appearance:none;text-align:center;width:90%;margin-left:5%;color:#ffffff;background-color:#F84E4E">
</form>
</body>
<script type="text/javascript">
function addmission(){
	var intro = $("#intro").val();
	if(intro==""){
		alert("请输入任务概况!");
		return false;
	}
	$.ajax({
		type: "post",
		url: "./addmission",
		data: $("#labelform").serialize(),
		success: function(data){
			if(data == "success"){
				alert("任务提交成功，等待审核");
				window.location.replace("./mission");
			}
		}
	});
}
</script>
</html>