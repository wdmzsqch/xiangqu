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
	editor = K.create('textarea[name="detail"]', {
		pasteType : 2,
		//设置可否改变编辑器大小
		resizeType : 1,
		allowPreviewEmoticons : false,
		allowImageUpload : true,
		uploadJson : 'js/kindeditor/jsp/upload_json.jsp',
  fileManagerJson : '	js/kindeditor/jsp/file_manager_json.jsp',
		width: "900px", 
		height: "270px",
		urlType: "domain"
	});
});

function checkvalue(){
	// 取得HTML内容
	html = editor.html();
	editor.html(html);

	// 同步数据后可以直接取得textarea的value
	editor.sync();
	
	if('${count}' > 0){
		alert("此文章在任务中使用，不能进行编辑");
		return;
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
				<i class="icon-font"></i> <a href="./home_page">首页</a> <span class="crumb-step">&gt;</span> <a class="crumb-name" href="./article_list">文章管理</a> <span
					class="crumb-step">&gt;</span><span>新增文章</span>
			</div>
		</div>
		<div class="result-wrap">
			<div class="result-content">
				<form action="./article_edit" method="post" id="myform" name="myform" enctype="multipart/form-data">
					<input id="id" name="id" value="${article.id}" type="hidden">
					<table class="insert-tab" width="100%">
						<tbody>
							<tr>
								<th width="120"><i class="require-red">*</i>标题：</th>
								<td><input class="common-text" name="title" size="50" value="${article.title}" type="text"></td>
							</tr>
							<tr>
								<th>内容</th>
								<td><textarea name="detail" style="width: 80%; height: 350px;">${article.detail}</textarea>
<!-- 								<input style="width: 80%; height: 350px;" name="title" value="" type="text"> -->
								</td>
							</tr>
							<tr>
								<th></th>
								<td><input class="btn btn-primary btn6 mr10" value="提交" onclick="checkvalue()" type="button"></td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>

	</div>

</body>
</html>