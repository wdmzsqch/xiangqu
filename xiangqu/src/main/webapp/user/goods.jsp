<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no">
<title>商品详情</title>
<script src="./js/jquery-1.11.3.min.js"></script>
<script src="./js/modernizr-custom-v2.7.1.min.js"></script>
<script src="./js/jquery-finger-v0.1.0.min.js"></script>
<script src="./js/flickerplate.min.js"></script>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<link rel="stylesheet" type="text/css" href="./css/flickerplate.css" />
<link rel="stylesheet" type="text/css" href="./css/common.css" />
<link rel="stylesheet" type="text/css" href="./css/goods.css" />
<script>

$(function(){
	
	 
    var width = document.documentElement.clientWidth;
    $(".flicker-example").css("height",width);
	
    $('.flicker-example').flicker({
        arrows: false,
        arrows_constraint: true,
        auto_flick: true,
        auto_flick_delay: 2,
        block_text: false,
        dot_navigation: true,
        dot_alignment: 'center',
        flick_animation: 'jquery-slide',
        flick_position: 1,
        inner_width: false,
        theme: 'light'
    });
    var ua = navigator.userAgent.toLowerCase();  
	 if(ua.match(/MicroMessenger/i)=="micromessenger") {  
    var backurl = window.location.href;
	  $.ajax({
			url: "./getWxPreperSign",
			data:{sign_backurl:backurl},
			type: "POST",
			success: function(msg){
					var nonce_str = msg.nonce_str;
					var appid = msg.appid;
					var sign = msg.sign;
					var timestamp = msg.timestamp;
					 wx.config({
						    debug: false,
						    appId: appid, 
						    timestamp:timestamp , 
						    nonceStr: nonce_str, 
						    signature: sign,
						    jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage','onMenuShareQQ','onMenuShareWeibo','onMenuShareQZone'],
						});
				}
			});
	 }
});

wx.ready(function(){
	  wx.onMenuShareTimeline({
		    title: '${goods.name}', // 分享标题
		    link: window.location.href, // 分享链接
		    imgUrl: '${fileRootUrl}${goods.mianPic}', // 分享图标
		    success: function () { 
		        // 用户确认分享后执行的回调函数
		    },
		    cancel: function () { 
		        // 用户取消分享后执行的回调函数
		    }
		});


		wx.onMenuShareAppMessage({
		    title: '${goods.name}', // 分享标题
		    desc: '${goods.name}', // 分享描述
		    link: window.location.href, // 分享链接
		    imgUrl: '${fileRootUrl}${goods.mianPic}', // 分享图标
		    type: '', // 分享类型,music、video或link，不填默认为link
		    dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
		    success: function () { 
		        // 用户确认分享后执行的回调函数
		    },
		    cancel: function () { 
		        // 用户取消分享后执行的回调函数
		    }
		});
		  
		wx.onMenuShareQQ({
			title: '${goods.name}', // 分享标题
		    desc: '${goods.name}', // 分享描述
		    link: window.location.href, // 分享链接
		    imgUrl: '${fileRootUrl}${goods.mianPic}', // 分享图标
		    success: function () { 
		       // 用户确认分享后执行的回调函数
		    },
		    cancel: function () { 
		       // 用户取消分享后执行的回调函数
		    }
		});

		wx.onMenuShareWeibo({
			title: '${goods.name}', // 分享标题
		    desc: '${goods.name}', // 分享描述
		    link: window.location.href, // 分享链接
		    imgUrl: '${fileRootUrl}${goods.mianPic}', // 分享图标
		    success: function () { 
		       // 用户确认分享后执行的回调函数
		    },
		    cancel: function () { 
		        // 用户取消分享后执行的回调函数
		    }
		});


		wx.onMenuShareQZone({
			title: '${goods.name}', // 分享标题
		    desc: '${goods.name}', // 分享描述
		    link: window.location.href, // 分享链接
		    imgUrl: '${fileRootUrl}${goods.mianPic}', // 分享图标
		    success: function () { 
		       // 用户确认分享后执行的回调函数
		    },
		    cancel: function () { 
		        // 用户取消分享后执行的回调函数
		    }
		});
  // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
});

wx.error(function(res){
  // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。

});

function makeorder(){
	var proindex = $("#proindex").val();
	var noprovalue = false;
	for(var i=0;i<proindex;i++){
		var j = i+1;
		var provalue = $("#provalue"+j ).val();
		if(provalue==''){
			noprovalue = true;
		}
	}
	if(noprovalue){
		alert("请选择商品属性!");
		return ;
	}
	if(proindex>0){
		$.ajax({
			url:'./creatgoodjson',
			type:'post',
			data:$("#labelform").serialize(),
			success:function(msg){
				if(msg.code==1){
					$("#property").val(msg.projson);
					$("#labelform").submit();
				}
			}
		})
	}else{
		$("#labelform").submit();
	}
	
// 	window.location.href="./makeorder?goodsid="+goodsid+"&goodstype="+goodstype+"&shareuserid="+shareuserid;
}

function checkpro(object,index){
	$(".provalue"+index).removeClass("provaluecheck");
	$(object).addClass("provaluecheck");
	var value = $(object).text();
	$("#provalue"+index).val(value);
}
</script>
</head>
<body>
<form action="makeorder" id="labelform" method="post">
<div class="flicker-example">
    <ul>
    	<c:if test="${ goods.detailPic1 != '' }">
    	 <li data-background="${fileRootUrl }${goods.detailPic1}">
        </li>
        </c:if>
        <c:if test="${ goods.detailPic2 != '' }">
    	 <li data-background="${fileRootUrl }${goods.detailPic2}">
        </li>
        </c:if>
        <c:if test="${ goods.detailPic3 != '' }">
    	 <li data-background="${fileRootUrl }${goods.detailPic3}">
        </li>
        </c:if>
        <c:if test="${ goods.detailPic4 != '' }">
    	 <li data-background="${fileRootUrl }${goods.detailPic4}">
        </li>
        </c:if>
        <c:if test="${ goods.detailPic5 != '' }">
    	 <li data-background="${fileRootUrl }${goods.detailPic5}">
        </li>
        </c:if>
    </ul>
</div>
<div style="margin-bottom:48px;">
<div class="goodsTitle overflowOmit">${ goods.name }</div>
<div class="moneyInfo">
<font class="price">
<c:if test="${goodstype==1 }">￥${goods.price }</c:if><c:if test="${goodstype==2 }">积分<fmt:formatNumber type="number" value="${goods.price }" maxFractionDigits="0"/></c:if> 
</font>
<c:if test="${goodstype==1 }">
<font class="originPrice">
${goods.originPrice }
</font>
</c:if>
<font class="stock" style="float:right;color:#bfbfbf">
剩余数量&nbsp;<font style="color:#000">${goods.stock }</font> 
</font>
<%-- <c:if test="${not empty goods.backMoney && goods.backMoney > 0  }"> --%>
<%-- <font class="backMoney">${goods.backMoney}</font> --%>
<!-- <div class="backMoneyDiv"> -->
<!-- 返 -->
<!-- </div> -->
<%-- </c:if> --%>
</div>
<div style="width: 100%; height: 8px; background: #f2f2ef; border-top: 1px solid #e8e8e8; border-bottom: 1px solid #e8e8e8;"></div>
<input type="hidden" id="proindex" value="<c:if test="${empty goods.property}">0</c:if><c:if test="${not empty goods.property}">${fn:length(goods.prolist)}</c:if>" >
<c:if test="${not empty goods.property && goods.property !='' }">
<div class="moneyInfo" style="margin-left:0;border-bottom:none;">
<c:forEach items="${goods.prolist }" var = "pro" varStatus="st">
<div style="color:#bfbfbf;font-size:12px;font-family:'微软雅黑';padding-top:10px;">
${pro.name }
</div>
<div style="border-bottom: 1px solid #e8e8e8;height:auto;overflow:hidden;padding-left:5px;padding-top:10px;padding-bottom:10px;">
<c:forEach items="${pro.valuelist }" var ="pvalue" >
<div class="provalueun provalue${st.count } " onclick="checkpro(this,${st.count })">${pvalue }</div>
</c:forEach>
<input type="hidden" name="provalue" id="provalue${st.count }" >
<input type="hidden" name="proname" value="${pro.name }" >
</div>
</c:forEach>
</div>
<div style="width: 100%; height: 8px; background: #f2f2ef; border-top: 1px solid #e8e8e8; border-bottom: 1px solid #e8e8e8;"></div>
</c:if>

<c:if test="${not empty goods.description && goods.description !='' }">
<div class="moneyInfo" style="border-bottom:none;">
<font color="#bfbfbf">商品描述</font>
<br/>
${goods.description }
</div>
</c:if>
<div class="btngroup">
<%-- <div  class="buybtn"  onclick="makeorder(${goods.id})">立即兑换/购买</div> --%>

<!-- <div  class="sharebtn" > -->
<!-- <img src="./imgs/share.png" width=12px style="float:left;margin-left:30%;"><font style="float:left;">分享赚收益</font></div> -->
<!-- <div class="" style="float:right;height:30px;line-height:30px;width:70px;border-radius:16px; border: 1px solid #00d1bc; color: #00d1bc; font-size: 12px; text-align: center; line-height: 30px; margin-top: 9px;">加购物车</div> -->
<div class="" style="float:right;height:30px;line-height:30px;width:70px;border-radius:16px; color:#fff;background: #00d1bc; font-size: 12px; text-align: center; line-height: 30px; margin-right: 5px; margin-top: 9px;" onclick="makeorder()">
<c:if test="${goodstype==2 }">
立即兑换
</c:if>
<c:if test="${goodstype==1 }">
立即购买
</c:if>
</div>
</div>
</div>
<input type="hidden" value="${goodstype }" id="goodstype" name="goodstype">
<input type="hidden" value="${shareuserid }" id="shareuserid" name="shareuserid">
<input type="hidden" value="${goods.id}" id="goodsid" name="goodsid">
<input type="hidden" value="${channelCode}" id="channelCode" name="channelCode">
<input type="hidden" value="" id="property" name="property">
</form>
<%@ include file="cs.jsp" %>
<%CS cs = new CS(1256812462);cs.setHttpServlet(request,response);
String imgurl = cs.trackPageView();%> 
<img src="<%= imgurl %>" width="0" height="0"  />
</body>
</html>