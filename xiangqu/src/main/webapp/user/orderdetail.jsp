<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no">
<link rel="stylesheet" type="text/css" href="./css/common.css" />
<link rel="stylesheet" type="text/css" href="./css/myorder.css" />
<script type="text/javascript" src="./js/jquery-1.11.3.min.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js" ></script>
<script type="text/javascript">
$(function(){
	var state = $("#state").val();
	if(state==''){
		$("#li_all").addClass("select");
	}else{
		$("#li_"+state).addClass("select");
	}
	var width = document.documentElement.clientWidth;
	$(".goodscontent").css("width",width-95)
})

function buyorder(){
	var orderid = $("#orderid").val();
	var paytype = $("#paytype").val();
	$.ajax({
		type: "post",
		url: "./checkgoods",
		data: {orderid :orderid},
		success: function(msg){
			var msgdata = eval("("+msg+")");
			var code = msgdata.code;
			if(code==1){
				if(paytype==2){
					$.ajax({
						url:'./pointpay',
						type:'post',
						data:{orderid:orderid},
						success:function(msg){
							var code = msg.code;
							if(code==1){
								alert("支付成功!");
								window.location.href="./orderdetail?orderid="+orderid;
							}else{
								alert(msg.message);
							}
						}
					})
				}else{
					var ua = navigator.userAgent.toLowerCase();  
					
				    if(ua.match(/MicroMessenger/i)=="micromessenger") { 
				    	//微信
				    	$.ajax({
									url:'./wxpay',
									data:{orderid:orderid},
									type:'get',
									success:function(msg){
										var appid = msg.appid;
										var timeStamp = msg.timeStamp;
										var nonceStr = msg.nonceStr;
										var packagename = msg.packagename;
										var sign = msg.sign;
										$("#appid").val(appid);
										$("#timeStamp").val(timeStamp);
										$("#nonceStr").val(nonceStr);
										$("#packagename").val(packagename);
										$("#sign").val(sign);
										callpay(orderid);
									}
								})
				    } else {  
				    	//alipay
				    	$("#alipayment").submit();
				    }  
				}
			}else{
				alert(msgdata.message);
			}
		}
	});
}

function callpay(orderid){	
	
	WeixinJSBridge.invoke('getBrandWCPayRequest',{
  		 "appId" :$("#appid").val(),"timeStamp" : $("#timeStamp").val(), "nonceStr" : $("#nonceStr").val(), "package" : $("#packagename").val(),"signType" : "MD5", "paySign" : $("#sign").val(), 
   			},function(res){
				WeixinJSBridge.log(res.err_msg);
	            if(res.err_msg == "get_brand_wcpay_request:ok"){  
	            	alert("支付成功!");
	            	window.location.href="./orderdetail?orderid="+orderid;
	            }else if(res.err_msg == "get_brand_wcpay_request:cancel"){  
	            	alert("用户取消支付!");
	            }else{  
	            }  
			});
}
</script>
<title>订单列表</title>
</head>
<body style="font-family:'华文细黑'">
<%-- <%@ include file="ordertop.jsp"%>  --%>
<c:if test="${order.state==1 }">
<div style="font-size:12px;color:#ffffff;text-align:center;line-height:120px;height:80px;background-color:#ff5c4b;background-image: url(./imgs/waitpay.png);background-size: 20%;background-repeat: no-repeat;background-position: center 30%;">
待付款
</div></c:if>
<c:if test="${order.state==2 }">
<div style="font-size:12px;color:#ffffff;text-align:center;line-height:135px;height:80px;background-color:#ffae3a;background-image: url(./imgs/waitsend.png);background-size: 12%;background-repeat: no-repeat;background-position: center 30%;">
待发货
</div>
</c:if>
<c:if test="${order.state==3 }">
<div style="font-size:12px;color:#ffffff;text-align:center;line-height:135px;height:80px;background-color:#00d16c;background-image: url(./imgs/sended.png);background-size: 12%;background-repeat: no-repeat;background-position: center 30%;">
已发货
</div></c:if>
<c:if test="${order.state==4 }">
<div style="font-size:12px;color:#ffffff;text-align:center;line-height:135px;height:80px;background-color:#00d1bc;background-image: url(./imgs/success.png);background-size: 12%;background-repeat: no-repeat;background-position: center 30%;">
已成功
</div></c:if>
<div style="padding-left:17px;background-color:#f6f6f6;height:10px;line-height:30px;font-size:13px;">
</div>
<div style="width:100%;background-color:#ffffff;">
<c:forEach items="${order.goodsList}" var="p" varStatus="status">
<c:if test="${status.first }">
<div class="contentdiv" style="height:auto;overflow:hidden;padding-left:0px;padding-right:0px;margin-left:17px;margin-right:13px;position:relative;">
<img  style="float:left;width:50px;height:50px;" src="${fileRootUrl }${p.pic}">
<div style="margin-left:12px;float:left;" class="goodscontent">
<div style="width:100%;font-size:14px;height:14px;">${p.name }</div>
<div style="line-height:12px;min-height:12px;font-size:12px;color:#bfbfbf;padding-top:5px;">
<c:forEach items="${p.prolist}" var="pro">
${pro.name }:<c:forEach items="${pro.valuelist }" var ="pvalue" >
${pvalue } &nbsp;
</c:forEach>
</c:forEach>
</div>
<div style="padding-top:3px;color:#414141;font-size:12px;">
<c:if test="${p.paytype==2 }"><fmt:parseNumber integerOnly="true" value="${p.price }" />分</c:if><c:if test="${p.paytype!=2 }">￥${p.price }</c:if>&nbsp;&nbsp;<s style="color:#bfbfbf"><c:if test="${p.paytype==2 }"><fmt:parseNumber integerOnly="true" value="${p.originPrice }" />分</c:if><c:if test="${p.paytype!=2 }">￥${p.originPrice }</c:if></s> <font style="float:right;color:#bfbfbf;position:absolute;right:10px;">X${p.num }</font></div>
</div>
</div>
<input type="hidden" id="paytype" value="${p.paytype }">
</c:if>
</c:forEach>

<c:forEach items="${order.goodsList}" var="p" varStatus="status">
<c:if test="${status.first }">
<div style="font-size:13px;line-height:13px;text-align:right;padding-top:13px;padding-bottom:3px;margin-left:17px;margin-right:13px;">
<font style="color:#414141;font-size:12px;">共${p.num }件商品</font>
</div>
</c:if>
</c:forEach>
<div style="border-bottom: 1px solid #e8e8e8;font-size:13px;line-height:13px;padding-top:5px;padding-bottom:10px;text-align:right;padding-left:17px;padding-right:13px;">
<font style="color:#414141;font-size:12px;">实付:</font>
<font style="color:#E84E4D;font-size:16px;"><c:if test="${order.paytype==2 }"><fmt:parseNumber integerOnly="true" value="${order.totalPrice }" />分</c:if><c:if test="${order.paytype!=2 }"><fmt:formatNumber value="${order.totalPrice }" type="currency" /></c:if></font>
</div>
<div style="height:8px;width:100%;background-color: #f6f6f6;">
</div>

<div style="border-top: 1px solid #e8e8e8;border-bottom: 1px solid #e8e8e8;line-height:30px;font-size:13px;height:auto;overflow:hidden;padding-left:17px;padding-right:13px;">
<div style="color:#989898">备注信息</div>
<div>
<c:if test="${order.comment != '' }">${order.comment  }</c:if>
<c:if test="${empty order.comment || order.comment == '' }">暂无备注</c:if>
</div>
</div>
<c:forEach items="${order.goodsList}" var="p" varStatus="status">
<c:if test="${status.first }">
<c:if test="${p.isServe==1 }">
<div style="height:8px;width:100%;background-color: #f6f6f6;">
</div>
<div style="text-align: center;border-top:1px solid #e8e8e8;border-bottom:1px solid #e8e8e8;">
<div style="text-align: left;color:#989898;line-height:30px;font-size:13px;height:auto;overflow:hidden;padding-left:17px;padding-right:13px;">二维码</div>
<img src="${fileRootUrl }${order.codepic}" width="40%">
<div style="font-size:14px;padding-bottom:10px;">序列号:${order.code }</div>
</div>
</c:if>
</c:if>
</c:forEach>
<div style="border-bottom: 1px solid #e8e8e8;height:8px;width:100%;background-color: #f6f6f6;">
</div>


<div style="margin-left:17px;margin-right:13px;font-size:13px;color:#bfbfbf;line-height:35px;border-bottom:1px solid #e8e8e8">
订单详情
</div>

<div style="border-bottom: 1px solid #e8e8e8;line-height:30px;font-size:13px;height:auto;overflow:hidden;margin-left:17px;margin-right:13px;">
<div style="float:left;color:#bfbfbf;width:60px">订单编号</div>
<div style="color:#414141;float:left;width:50%;text-align:left;overflow-wrap: break-word;word-break: break-all;line-height: 14px;margin-bottom: 7px;margin-top: 9px;">
${order.orderNo }
</div>
</div>

<div style="border-bottom: 1px solid #e8e8e8;line-height:30px;font-size:13px;height:auto;overflow:hidden;margin-left:17px;margin-right:13px;">
<div style="float:left;color:#bfbfbf;width:60px">订单时间</div>
<div style="color:#414141;float:left;width:50%;text-align:left;overflow-wrap: break-word;word-break: break-all;line-height: 14px;margin-bottom: 7px;margin-top: 9px;">
<fmt:formatDate value="${order.orderTime}" type="both" pattern="yyyy-MM-dd"/>
</div>
</div>

<div style="border-bottom: 1px solid #e8e8e8;line-height:30px;font-size:13px;height:auto;overflow:hidden;margin-left:17px;margin-right:13px;">
<div style="float:left;color:#bfbfbf;width:60px">收货人</div>
<div style="color:#414141;float:left;width:50%;text-align:left;overflow-wrap: break-word;word-break: break-all;line-height: 14px;margin-bottom: 7px;margin-top: 9px;">
${order.consignee }
</div>
</div>

<div style="border-bottom: 1px solid #e8e8e8;line-height:30px;font-size:13px;height:auto;overflow:hidden;margin-left:17px;margin-right:13px;">
<div style="float:left;color:#bfbfbf;width:60px">手机号</div>
<div style="color:#414141;float:left;width:50%;text-align:left;overflow-wrap: break-word;word-break: break-all;line-height: 14px;margin-bottom: 7px;margin-top: 9px;">
${order.phone }
</div>
</div>

<div style="border-bottom: 1px solid #e8e8e8;line-height:30px;font-size:13px;height:auto;overflow:hidden;margin-left:17px;margin-right:13px;">
<div style="float:left;color:#bfbfbf;width:60px">收货地址</div>
<div style="color:#414141;float:left;width:50%;text-align:left;overflow-wrap: break-word;word-break: break-all;line-height: 14px;margin-bottom: 7px;margin-top: 9px;">
${order.address }
</div>
</div>

<%-- <c:if test="${order.state == 3 || order.state == 4 }"> --%>

<div style="border-bottom: 1px solid #e8e8e8;line-height:30px;font-size:13px;height:auto;overflow:hidden;margin-left:17px;margin-right:13px;">
<div style="float:left;color:#bfbfbf;width:60px">快递公司</div>
<div style="color:#414141;float:left;width:50%;text-align:left;overflow-wrap: break-word;word-break: break-all;line-height: 14px;margin-bottom: 7px;margin-top: 9px;">
${order.express }
</div>
</div>

<div style="line-height:30px;font-size:13px;height:auto;overflow:hidden;margin-left:17px;margin-right:13px;">
<div style="float:left;color:#bfbfbf;width:60px;">快递单号</div>
<div style="color:#414141;float:left;width:50%;text-align:left;overflow-wrap: break-word;word-break: break-all;line-height: 14px;margin-bottom: 7px;margin-top: 9px;">
${order.courierNum }
</div>
</div>
<div style="width:100%;height:50px;"></div>
<%-- </c:if> --%>

</div>
</div>

<c:if test="${order.state==1 }">
<div class="submitdiv" >
<div class="submit" onclick="buyorder()" id="subbtn">支付</div>
</div>
</c:if>
<input type="hidden" value="${order.state }" id="state">
<input type="hidden" value="${order.id }" id="orderid">
<form name=alipayment id="alipayment" action=alipayapi.jsp method=post >
<input type="hidden" id="WIDout_trade_no" name="WIDout_trade_no" value="${order.orderNo }">
<input type="hidden" id="WIDtotal_fee" name="WIDtotal_fee" value="${order.totalPrice }">
<input type="hidden" id="WIDsubject" name="WIDsubject" value="享趣：${order.orderNo }">
<input type="hidden" id="WIDbody" name="WIDbody" value="享趣：${order.orderNo }订单支付">
</form>
<input type="hidden" value="" id="appid">
<input type="hidden" value="" id="timeStamp">
<input type="hidden" value="" id="nonceStr">
<input type="hidden" value="" id="packagename">
<input type="hidden" value="" id="sign">

<%@ include file="cs.jsp" %>
<%CS cs = new CS(1256812462);cs.setHttpServlet(request,response);
String imgurl = cs.trackPageView();%> 
<img src="<%= imgurl %>" width="0" height="0"  />
</body>
</html>