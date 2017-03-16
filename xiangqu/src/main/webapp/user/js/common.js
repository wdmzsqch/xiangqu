function checkphone(moblie) {
	if (moblie.length != 11) {
		return false;
	}
	var myreg = /^(?:13\d|15[0123456789]|18[0123456789]|12[0123456789]|14[0123456789]|15[0123456789]|16[0123456789]|17[0123456789]|19[0123456789])-?\d{5}(\d{3}|\*{3})$/;
	if (!myreg.test(moblie)) {
		return false;
	}
	return true;
}

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