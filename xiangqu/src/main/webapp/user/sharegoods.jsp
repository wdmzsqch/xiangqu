<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no">
<title>商品分享</title>
<script src="./js/jquery-1.11.3.min.js"></script>
<link rel="stylesheet" type="text/css" href="./css/common.css" />
<link rel="stylesheet" type="text/css" href="./css/share.css" />
<script>

function goods(goodsid,goodstype){
	var shareuserid = $("#shareuserid").val();
	window.location.href="./goods?goodsid="+goodsid+"&goodstype="+goodstype+"&shareuserid="+shareuserid;
}

function missionShare() {
	window.location.replace("./mission");
}
</script>
</head>
<body style="font-family:'微软雅黑'">
<%-- <jsp:include page="./top_noback.jsp"/> --%>
<div style="width:100%;margin-top: 10px;margin-bottom: 65px;">
<div class="typediv" >
<div class="typebtn typeleft unusetype" onclick="missionShare()">任务分享</div>
<div class="typebtn typeright " onclick="goodsShare()">商品分享</div>
</div>

<c:forEach items="${goodslist}" var="p">
<div  class="goodsDiv" onclick="goods(${p.id},${p.payType })">
<img class="mallgoodsimg" src="${fileRootUrl }${p.mianPic}">
<div class="goodsTitle overflowOmit">${p.name }</div>
<div class="moneyInfo">
<font class="price">
${p.price }
</font>
<font class="originPrice">
${p.originPrice }
</font>
<font class="stock">
剩余：${p.stock }
</font>
<div  class="sharebtn" >
<img src="./imgs/share.png" width=12px style="float:left;margin-left:5%;"><font style="float:left;">分享赚收益</font>
</div>
<c:if test="${not empty p.backMoney && p.backMoney > 0  }">
<font class="backMoney">${p.backMoney}</font>
<div class="backMoneyDiv">
返
</div>
</c:if>
</div>
</div>
</c:forEach>

</div>
<input type="hidden" value="${shareuserid }" id="shareuserid">

<%@ include file="cs.jsp" %>
<%CS cs = new CS(1256812462);cs.setHttpServlet(request,response);
String imgurl = cs.trackPageView();%> 
<img src="<%= imgurl %>" width="0" height="0"  />
</body>
</html>