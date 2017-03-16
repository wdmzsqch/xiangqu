<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<style type="text/css">
</style>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, user-scalable=no">
	<link rel="stylesheet" type="text/css" href="./css/common.css" />
	<script type="text/javascript" src="./js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript">
$(function(){
	setTabSelected(4);
})

	function changepage(index) {
		switch (index) {
		case 1:
			window.location.href= './message';return false;
			break;
		case 2:
			window.location.href= './setting';return false;
			break;
		case 3:
			window.location.href= './about.jsp';return false;
			break;
		case 4:
			window.location.href= './shopdata';return false;
			break;
		case 5:
			window.location.href= './eventcheck.jsp';return false;
			break;
		case 6:
			window.location.href= './ordercheck.jsp';return false;
			break;
		case 7:
			window.location.href= './couponcheck.jsp';return false;
			break;
		case 8:
			window.location.href= './shcoupon';return false;
			break;
		case 9:
			window.location.href= './event_list';return false;
			break;
		default:
			break;
		}
	}

</script>
<title>商家中心</title>
</head>

<body
	style="margin: 0px; padding: 0px; border: 0px; background-color: #f6f6f6; font-family:'华文细黑'">

	<!-- 公司图片 -->

	<div
		style="font-size: 13px; width: 100%; height: 130px; background-color: #ffffff;">
			<div
				style="width: 100%; height:130px; overflow: hidden; position:relative;">
				<img src="./imgs/pg.png" width="100%" height="130px" style="float: left;" />
			</div>
			<div onclick="changepage(4)"
				style="width: 80px; height: 80px; border-radius: 100px; overflow: hidden; position:absolute; margin:auto; left:0; right:0;top:15px">
				<img src="${fileRootUrl }${shopinfo.pic }" style="width: 80px; height: 80px;"/>
			</div>
			
			<div
				style="width: 100%; color: #f9f5f2;height: 20px; overflow: hidden; text-align: center; position: absolute; top: 100px;">
			
				${shopinfo.companyName }
			</div>
	</div>


	<!-- 系统消息 -->
	<div style="font-size: 13px; margin-top: 11px; width: 100%;height:40px; background-color: #ffffff" onclick="changepage(1)">
		<div
			style="float: left; height: 40px; line-height: 40px; margin-left: 17px; margin-right: 13px">
			<img src="./imgs/message.png" width="20px" height="20px "
				style="float: left; margin-top: 10px; margin-left: 10px;" />
			<span style="float: left; margin-left: 10px">系统消息</span>
		</div>
		<div style="float: right;">
			<img src="./imgs/rightarrow.png" width="15px" height="15px " style="margin-right: 7px; margin-top: 12px;"/>
		</div>
	</div>

	<div style="clear:both;border-bottom: 1px solid #CCCCCC; margin-left: 22px"></div>

	<!-- 系统设置 -->
	<div style="width: 100%;height:40px;background-color: #ffffff"  onclick="changepage(2)">
		<div
			style="float: left; height: 40px; line-height: 40px; font-size: 13px; margin-left: 17px; margin-right: 13px">
			<img src="./imgs/setting.png" width="20px" height="20px "
				style="float: left; margin-top: 10px; margin-left: 10px;" />
			<span style="float: left; margin-left: 10px">系统设置</span>
		</div>
		<div style="float: right;">
			<img src="./imgs/rightarrow.png" width="15px" height="15px " style="margin-right: 7px; margin-top: 12px;"/>
		</div>
	</div>

	<div style="clear:both;border-bottom: 1px solid #CCCCCC; margin-left: 22px"></div>
	
	<!-- 关于享趣 -->
	<div style="width: 100%;height:40px; background-color: #ffffff" onclick="changepage(3)">
		<div
			style="float: left; height: 40px; line-height: 40px; font-size: 13px; margin-left: 17px; margin-right: 13px">
			<img src="./imgs/about.png" width="20px" height="20px "
				style="float: left; margin-top: 10px; margin-left: 10px;" />
			<span style="float: left; margin-left: 10px">关于享趣</span>
		</div>
		<div style="float: right;">
			<img src="./imgs/rightarrow.png" width="15px" height="15px " style="margin-right: 7px; margin-top: 12px;"/>
		</div>
	</div>
	
		<div style="clear:both;border-bottom: 1px solid #CCCCCC; margin-left: 22px"></div>
	
	<!-- 验证活动报名 -->
	<div style="width: 100%;height:40px; background-color: #ffffff" onclick="changepage(5)">
		<div
			style="float: left; height: 40px; line-height: 40px; font-size: 13px; margin-left: 17px; margin-right: 13px">
			<img src="./imgs/magnifier.png" width="20px" height="20px "
				style="float: left; margin-top: 10px; margin-left: 10px;" />
			<span style="float: left; margin-left: 10px">验证活动报名</span>
		</div>
		<div style="float: right;">
			<img src="./imgs/rightarrow.png" width="15px" height="15px " style="margin-right: 7px; margin-top: 12px;"/>
		</div>
	</div>
	
		<div style="clear:both;border-bottom: 1px solid #CCCCCC; margin-left: 22px"></div>
	
	<!-- 验证服务商品 -->
	<div style="width: 100%;height:40px; background-color: #ffffff" onclick="changepage(6)">
		<div
			style="float: left; height: 40px; line-height: 40px; font-size: 13px; margin-left: 17px; margin-right: 13px">
			<img src="./imgs/magnifier.png" width="20px" height="20px "
				style="float: left; margin-top: 10px; margin-left: 10px;" />
			<span style="float: left; margin-left: 10px">验证服务商品</span>
		</div>
		<div style="float: right;">
			<img src="./imgs/rightarrow.png" width="15px" height="15px " style="margin-right: 7px; margin-top: 12px;"/>
		</div>
	</div>
	<div style="clear:both;border-bottom: 1px solid #CCCCCC; margin-left: 22px"></div>
	
	<div style="width: 100%;height:40px; background-color: #ffffff" onclick="changepage(7)">
		<div
			style="float: left; height: 40px; line-height: 40px; font-size: 13px; margin-left: 17px; margin-right: 13px">
			<img src="./imgs/magnifier.png" width="20px" height="20px "
				style="float: left; margin-top: 10px; margin-left: 10px;" />
			<span style="float: left; margin-left: 10px">验证优惠券</span>
		</div>
		<div style="float: right;">
			<img src="./imgs/rightarrow.png" width="15px" height="15px " style="margin-right: 7px; margin-top: 12px;"/>
		</div>
	</div>
	
	<div style="clear:both;border-bottom: 1px solid #CCCCCC; margin-left: 22px"></div>
		
	<div style="width: 100%;height:40px; background-color: #ffffff" onclick="changepage(8)">
		<div
			style="float: left; height: 40px; line-height: 40px; font-size: 13px; margin-left: 17px; margin-right: 13px">
			<img src="./imgs/magnifier.png" width="20px" height="20px "
				style="float: left; margin-top: 10px; margin-left: 10px;" />
			<span style="float: left; margin-left: 10px">优惠券列表</span>
		</div>
		<div style="float: right;">
			<img src="./imgs/rightarrow.png" width="15px" height="15px " style="margin-right: 7px; margin-top: 12px;"/>
		</div>
	</div>
	
	<div style="clear:both;border-bottom: 1px solid #CCCCCC; margin-left: 22px"></div>
	
	<div style="width: 100%;height:40px; background-color: #ffffff" onclick="changepage(9)">
		<div
			style="float: left; height: 40px; line-height: 40px; font-size: 13px; margin-left: 17px; margin-right: 13px">
			<img src="./imgs/magnifier.png" width="20px" height="20px "
				style="float: left; margin-top: 10px; margin-left: 10px;" />
			<span style="float: left; margin-left: 10px">活动列表</span>
		</div>
		<div style="float: right;">
			<img src="./imgs/rightarrow.png" width="15px" height="15px " style="margin-right: 7px; margin-top: 12px;"/>
		</div>
	</div>
	<div style="width:100%;height:45px;"></div>
<%@ include file="tab.jsp"%> 
</body>

</html>



<!-- 1.通过window.open函数
<div onclick="window.open('http://www.cdxwcx.com')">在新窗口跳转至创新互联</div>
<div onclick="window.open('http://www.cdxwcx.com','_self')">在当前窗口跳转至网站制作</div>

2.通过window.location.href函数
<div onclick="window.location.href= 'http://www.cdxwcx.com';return false">在当前窗口跳转至网站建设</div> -->