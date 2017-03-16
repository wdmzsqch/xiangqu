<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no">
<link rel="stylesheet" type="text/css" href="./css/common.css" />
<link rel="stylesheet" type="text/css" href="./css/address.css" />
<script src="./js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
function addaddress(){
	var chooseaddressid =$("#addressid").val();
	var goodsnum = $("#goodsnum").val();
	var goodsid = $("#goodsid").val();
	var goodstype = $("#goodstype").val();
	var shareuserid = $("#shareuserid").val();
	var property = $("#property").val();
	var channelCode = $("#channelCode").val();
	window.location.href="./addressdetail?addressid="+chooseaddressid+"&goodstype="+goodstype+"&goodsid="+goodsid+"&goodsnum="+goodsnum
			+"&shareuserid="+shareuserid+"&property="+property+"&channelCode="+channelCode;
}

function editaddress(editaddressid){
	var addressid =$("#addressid").val();
	var goodsnum = $("#goodsnum").val();
	var goodsid = $("#goodsid").val();
	var goodstype = $("#goodstype").val();
	var shareuserid = $("#shareuserid").val();
	var property = $("#property").val();
	var channelCode = $("#channelCode").val();
	window.location.href="./addressdetail?editaddressid="+editaddressid+"&addressid="+addressid+"&goodstype="+goodstype+"&goodsid="+goodsid+"&goodsnum="+goodsnum+"&shareuserid="+shareuserid+"&property="+property+"&channelCode="+channelCode;

}

function checkaddress(checkaddressid){
	var addressid =$("#addressid").val();
	var goodsnum = $("#goodsnum").val();
	var goodsid = $("#goodsid").val();
	var goodstype = $("#goodstype").val();
	var shareuserid = $("#shareuserid").val();
	var property = $("#property").val();
	var channelCode = $("#channelCode").val();
	window.location.href="./checkaddress?checkaddressid="+checkaddressid+"&addressid="+addressid+"&goodstype="+goodstype+"&goodsid="+goodsid+"&goodsnum="+goodsnum+"&shareuserid="+shareuserid+"&property="+property+"&channelCode="+channelCode;
}
</script>
<title>管理收货地址</title>
</head>
<body>
<c:forEach items="${addresslist}" var="p">
<div class="addresstitle" style="height:auto;overflow:hidden;padding-top:15px;padding-bottom:15px;">
<div  class="addressimgdiv" onclick="checkaddress(${p.id})">
<c:if test="${p.id == addressid }">
<img src="./imgs/selected.png" class="addressimg" >
</c:if>
</div>
<div class="addressdiv" onclick="checkaddress(${p.id})">
<div class="addresscontent">收件人：${p.consignee }</div>
<div class="addresscontent">联系电话：${p.phone }</div>
<div class="addresscontent">收货地址：${p.proname }${p.cityname }${p.areaname }${p.address }</div>
</div>
<div class="addressedit" onclick="editaddress(${p.id})">
<img src="./imgs/edit.png" class="addressimg" style="width:17px;">
</div>
</div>
</c:forEach>

<input type="hidden" id="addressid" value="${addressid }">
<input type="hidden" id="goodstype" value="${goodstype }">
<input type="hidden" id="goodsid" value="${goodsid }">
<input type="hidden" id="goodsnum" value="${goodsnum }">
<input type="hidden" id="property" value='${property }'>
<input type="hidden" value="${shareuserid }" id="shareuserid" name="shareuserid">
<input type="hidden" value="${channelCode }" id="channelCode" name="channelCode">
<div class="submitdiv" onclick="addaddress()">
<div class="submit" >新增收货地址</div>
</div>

<%@ include file="cs.jsp" %>
<%CS cs = new CS(1256812462);cs.setHttpServlet(request,response);
String imgurl = cs.trackPageView();%> 
<img src="<%= imgurl %>" width="0" height="0"  />
</body>
</html>