<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no">
<link rel="stylesheet" type="text/css" href="css/common.css" />
<script type="text/javascript" src="./js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">
	function turntocomment() {
		window.location.href = "./comment?relative_id=" + '${mission.id}' + "&type=2";
		// 		if ('${type }' == 2) {
		// 			window.location.href = "./comment?relative_id=" + '${connent_id}' + "&type=4";
		// 		} else if ('${type }' == 3) {
		// 			window.location.href = "./comment?relative_id=" + '${connent_id}' + "&type=1";
		// 		} else if ('${type }' == 4) {
		// 			window.location.href = "./comment?relative_id=" + '${connent_id}' + "&type=3";
		// 		} else if ('${type }' == 1) {
		// 			window.location.href = "./comment?relative_id=" + '${connent_id}' + "&type=2";
		// 		} else if ('${type }' == 5){
		// 			window.location.href = "./comment?relative_id=" + '${mission.id}' + "&type=2";
		// 		}
	}
	$(function() {
		var height = document.documentElement.clientHeight;
		if ('${mission.detailUrl}' != "") {
			if ('${mission.detailUrl}'.indexOf("xiangqu/user/goods") > 0 || '${mission.detailUrl}'.indexOf("xiangqu/user/lookeventdetail") > 0
					|| '${mission.detailUrl}'.indexOf("xiangqu/user/subjectdetail") > 0) {
				$("#detailframe").attr("src", '${mission.detailUrl}' + '&shareuserid=' + '${shareuserid}');
			} else if ('${mission.detailUrl}'.indexOf("xiangqu/user/coupondetail") > 0) {
				$("#detailframe").attr("src", '${mission.detailUrl}' + '&share_userid=' + '${shareuserid}');
			} else if ('${mission.detailUrl}'.indexOf("xiangqu/user/article") > 0) {
				$("#detailframe").attr("src", '${mission.detailUrl}' + '&mission_id=' + '${mission.id}');
			} else {
				$("#detailframe").attr("src", '${mission.detailUrl}');
			}
		}

		if('${mission.detailUrl}'.indexOf("xiangqu/user/article") > 0){
			$("#bottomdiv").hide();
			$("#otherbottom").hide();
			$("#detailframe").attr("height", parseInt(height));
		}else{
		if (isWeiXin()) {
			$("#bottomdiv").hide();
			$("#otherbottom").show();
			$("#detailframe").attr("height", parseInt(height - $("#otherbottom").height()));
			$("#framebo").css("padding-bottom", $("#otherbottom").height());
		} else {
			$("#bottomdiv").show();
			$("#otherbottom").hide();
			$("#detailframe").attr("height", parseInt(height - 49));
			$("#framebo").css("padding-bottom", 49);
		}
		}
	})

	function isWeiXin() {
		var ua = window.navigator.userAgent.toLowerCase();
		if (ua.match(/MicroMessenger/i) == 'micromessenger') {
			return true;
		} else {
			return false;
		}
	}

	function truntodown() {
		window.location.href = "http://www.xiangqu100.com/download.html";
	}

	$(function() {
		var ua = navigator.userAgent.toLowerCase();
		if (ua.match(/MicroMessenger/i) == "micromessenger") {
			var backurl = window.location.href;
			$.ajax({
				url : "./getWxPreperSign",
				data : {
					sign_backurl : backurl
				},
				type : "POST",
				success : function(msg) {
					var nonce_str = msg.nonce_str;
					var appid = msg.appid;
					var sign = msg.sign;
					var timestamp = msg.timestamp;
					wx.config({
						debug : false,
						appId : appid,
						timestamp : timestamp,
						nonceStr : nonce_str,
						signature : sign,
						jsApiList : [ 'onMenuShareTimeline', 'onMenuShareAppMessage', 'onMenuShareQQ', 'onMenuShareWeibo', 'onMenuShareQZone' ],
					});
				}
			});
		}
	})

	wx.ready(function() {
		wx.onMenuShareTimeline({
			title : '${mission.name}', // 分享标题
			link : window.location.href, // 分享链接
			imgUrl : '${fileRootUrl}${mission.pic}', // 分享图标
			success : function() {
				// 用户确认分享后执行的回调函数
			},
			cancel : function() {
				// 用户取消分享后执行的回调函数
			}
		});

		wx.onMenuShareAppMessage({
			title : '${mission.name}', // 分享标题
			desc : '${mission.name}', // 分享描述
			link : window.location.href, // 分享链接
			imgUrl : '${fileRootUrl}${mission.pic}', // 分享图标
			type : '', // 分享类型,music、video或link，不填默认为link
			dataUrl : '', // 如果type是music或video，则要提供数据链接，默认为空
			success : function() {
				// 用户确认分享后执行的回调函数
			},
			cancel : function() {
				// 用户取消分享后执行的回调函数
			}
		});

		wx.onMenuShareQQ({
			title : '${mission.name}', // 分享标题
			desc : '${mission.name}', // 分享描述
			link : window.location.href, // 分享链接
			imgUrl : '${fileRootUrl}${mission.pic}', // 分享图标
			success : function() {
				// 用户确认分享后执行的回调函数
			},
			cancel : function() {
				// 用户取消分享后执行的回调函数
			}
		});

		wx.onMenuShareWeibo({
			title : '${mission.name}', // 分享标题
			desc : '${mission.name}', // 分享描述
			link : window.location.href, // 分享链接
			imgUrl : '${fileRootUrl}${mission.pic}', // 分享图标
			success : function() {
				// 用户确认分享后执行的回调函数
			},
			cancel : function() {
				// 用户取消分享后执行的回调函数
			}
		});

		wx.onMenuShareQZone({
			title : '${mission.name}', // 分享标题
			desc : '${mission.name}', // 分享描述
			link : window.location.href, // 分享链接
			imgUrl : '${fileRootUrl}${mission.pic}', // 分享图标
			success : function() {
				// 用户确认分享后执行的回调函数
			},
			cancel : function() {
				// 用户取消分享后执行的回调函数
			}
		});
		// config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
	});

	wx.error(function(res) {
		// config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。

	});
</script>
<title>任务详情</title>
</head>
<body style="margin: 0;">
	<div id="framebo">
		<iframe id="detailframe" src="" width="100%" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="yes" allowtransparency="yes"></iframe>
	</div>
	
	<div id="bottomdiv" style="width: 100%; height: 49px; position: fixed; bottom: 0px; background: #fff; z-index: 101;">
		<div style="position: absolute; bottom: 14px; z-index: 201; left: 4%; font-size: 13px;" onclick="turntocomment()">
			<img src="imgs/comment.png" style="width: 16px; height: 15px; float: left;">
			<div style="float: left; line-height: 14px; margin-left: 10px;">评论(${commentcount })</div>
		</div>
		<a href="xqshare://type=1&id=${mission.id}">
			<div
				style="position: absolute; bottom: 8px; z-index: 201; right: 4%; width: 43%; height: 32px; background: #00d1bc; border-radius: 16px; text-align: center; line-height: 32px; color: #fff; margin: 0 auto;">
				<img src="imgs/small_share_icon.png" style="width: 16px; height: 15px;" />
				分享赚积分
			</div>
		</a>
	</div>
	
	<img src="imgs/loadapp.jpg" id="otherbottom" style="position: fixed; bottom: 0px; width: 100%; z-index: 101;" onclick="truntodown()">
	
	<!-- 	<div id="otherbottom" style="width: 100%; height: auto; position: fixed; bottom: 0px; background: #fff; background-image: url('imgs/loadapp.jpg'); background-size: 100% 100%; background-repeat: no-repeat; z-index: 101; display: none;" onclick="truntodown()"> -->
	<!-- 		<div style="width: 96%; margin-left: 2%; height: 45px; margin-top: 2px; text-align: center; background: #3598dc; border-radius: 6px; font-size: 15px; color: #fff; line-height: 45px;"> -->
	<!-- 		<font style="font-size: 23px; color: RGB(255,0,8);">点击这里</font>关注<font style="font-size: 23px;">“享趣100</font>公众号<font style="font-size: 23px;">”</font>一起分享赚钱 -->
	<!-- 		<img src="imgs/download.png" style="width: 28px; height: 31px; margin-top: 7px; float: right; margin-right: 4%;">		 -->
	<!-- 		</div> -->
	<!-- 	</div> -->

	<%@ include file="cs.jsp"%>
	<%
		CS cs = new CS(1256812462);
		cs.setHttpServlet(request, response);
		String imgurl = cs.trackPageView();
	%>
	<img src="<%=imgurl%>" width="0" height="0" />
</body>
</html>