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
	window.location.href="./useraddressdetail";
}

function editaddress(editaddressid){
	window.location.href="./useraddressdetail?editaddressid="+editaddressid;

}

function checkaddress(checkaddressid){
	window.location.replace("./checkuseraddress?checkaddressid="+checkaddressid);
}
</script>
<title>管理地址</title>
</head>
<body>
<c:forEach items="${addresslist}" var="p">
<div class="addresstitle" style="height:auto;overflow:hidden;padding-top:15px;">
<div  class="addressimgdiv" onclick="checkaddress(${p.id})">
<c:if test="${p.isDefult == 1 }">
<img src="./imgs/check_address_icon.png" class="addressimg" style="width: 26px; height: 20px;">
</c:if>
</div>
<div class="addressdiv" onclick="editaddress(${p.id})">
<div class="addresscontent"><span style="font-size: 16px;">${p.consignee }</span>&nbsp;&nbsp;${p.phone }</div>
<!-- <div class="addresscontent">联系电话：</div> -->
<div class="addresscontent" style="line-height: 19px;">${p.proname }&nbsp;&nbsp;${p.cityname }&nbsp;&nbsp;${p.areaname }&nbsp;&nbsp;${p.address }</div>
</div>
<div class="addressedit" onclick="editaddress(${p.id})">
<img src="./imgs/right.png" class="addressimg" style="width:7px; height: 14px;">
</div>
</div>
</c:forEach>

<div class="submitdiv" onclick="addaddress()">
<div style="height: 8px;"></div>
<div style="width: 73%; height: 32px; line-height: 32px; margin: 0 auto; text-align: center; background: #00d1bc; color: #fff; border-radius: 16px;" >新增收货地址</div>
</div>

<%@ include file="cs.jsp" %>
<%CS cs = new CS(1256812462);cs.setHttpServlet(request,response);
String imgurl = cs.trackPageView();%> 
<img src="<%= imgurl %>" width="0" height="0"  />
</body>
</html>