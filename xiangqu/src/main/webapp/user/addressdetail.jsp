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
function checkvalue(){
		var consignee = $("#consignee").val();
		if(consignee==''){
			alert("请输入收货人!");
			return false;
		}
		var phone = $("#phone").val();
		if(phone==''){
			alert("请输入手机号码!");
			return false;
		}
		var address = $("#address").val();
		if(address==''){
			alert("请输入收货地址");
			return false;
		}
}

$(function(){
	var hidpro = $("#hidpro").val();
	if(hidpro!=''){
		$("#province").val(hidpro);
	}
	var hidcity = $("#hidcity").val();
	if(hidcity!=''){
		$("#city").val(hidcity);
	}
	var hidarea = $("#hidarea").val();
	if(hidarea!=''){
		$("#area").val(hidarea);
	}
})

function changePro(){
	var province = $("#province").val();
	if(province==''){
		return;
	}
	$.ajax({
		url:'./getAreaListAsyn',
		type:'POST',
		data:{'parentid':province},
		success:function(msg){
			var arealist =msg.arealist;
			$("#city").empty();
			var html = '';
			for(var i = 0;i<arealist.length;i++){
				html += '<option value="'+arealist[i].id+'">'+arealist[i].name+'</option>';
			}
			$("#city").append(html);
			changeCity();
		}
	})
}

function changeCity(){
	var city = $("#city").val();
	if(city==''){
		return;
	}
	$.ajax({
		url:'./getAreaListAsyn',
		type:'POST',
		data:{'parentid':city},
		success:function(msg){
			var arealist =msg.arealist;
			$("#area").empty();
			var html = '';
			for(var i = 0;i<arealist.length;i++){
				html += '<option value="'+arealist[i].id+'">'+arealist[i].name+'</option>';
			}
			$("#area").append(html);
		}
	})
}
function checkNum(e,id) {
	
	var textval = $("#"+id).val();
    var k = window.event ? e.keyCode : e.which;
    if (((k >= 48) && (k <= 57)) || k == 8 || k == 0 || ((k >=96 ) && (k <= 105)) || k == 46 || k == 37 || k == 39) {
    } else {
        if (window.event) {
            window.event.returnValue = false;
        }
        else {
            e.preventDefault(); // for firefox
        }
        $("#"+id).val(textval.replace(/\D/g,''));
    }
}

function checkTextLength(obj, length) {   
    
    if(obj.value.length > length)   {   
         
        obj.value = obj.value.substr(0, length);   
    }   

}

</script>
<title>编辑收货地址</title>
</head>
<body>
<form action="editaddress" method="post">
<div class="username">
<div style="float:left;width:65px;">收货人：</div><input type="text" style="width:100px;" required="required" id="consignee" name="consignee" value="${useraddress.consignee }">
</div>
<div class="phone">
<div style="float:left;width:65px;">联系电话：</div><input  style="width:150px;" type="number" required="required"  oninput="checkTextLength( this , 11); return checkNum(event,'phone')" onkeydown= "return checkNum(event,'phone')" onblur="return checkNum(event,'phone')" id="phone" name="phone" value="${useraddress.phone }" maxlength="13" >
</div>
<div class="phone">
<div style="float:left;width:65px;">收货地址：</div>
<select style="float:left;width:70px;margin-right:10px;height:28px" id="province" name="province" onchange="changePro()">
<c:forEach items="${prolist}" var="p">
<option value="${p.id }">${p.name }</option>
</c:forEach>
</select>
<select style="float:left;width:70px;margin-right:10px;height:28px" id="city" name="city" onchange="changeCity()">
<c:if test="${empty citylist }">
<option value="">请选择</option>
</c:if>
<c:if test="${not empty citylist  }">
<c:forEach items="${citylist}" var="p">
<option value="${p.id }">${p.name }</option>
</c:forEach>
</c:if>
</select>
<select style="float:left;width:70px;height:28px" id="area" name="area">
<c:if test="${empty arealist }">
<option value="">请选择</option>
</c:if>
<c:if test="${not empty arealist  }">
<c:forEach items="${arealist}" var="p">
<option value="${p.id }">${p.name }</option>
</c:forEach>
</c:if>
</select>
</div>
<div class="phone" style="clear:both;padding-bottom:15px;border-bottom:1px solid rgb(219,219,219);">
<div style="float:left;width:65px;height:100%;"></div><input type="text" style="width:230px;" required="required" name="address" value="${useraddress.address }">
</div>
<input type="hidden" id="hidpro" value="${useraddress.province }">
<input type="hidden" id="hidcity" value="${useraddress.city }">
<input type="hidden" id="hidarea" value="${useraddress.area }">
<input type="hidden" id="editaddressid" value="${useraddress.id }" name="id">
<input type="hidden" id="addressid" value="${addressid }" name="addressid">
<input type="hidden" id="goodstype" value="${goodstype }" name="goodstype">
<input type="hidden" id="goodsid" value="${goodsid }" name="goodsid">
<input type="hidden" id="goodsnum" value="${goodsnum }" name="goodsnum">
<input type="hidden" value="${shareuserid }" id="shareuserid" name="shareuserid">
<input type="hidden" value="${channelCode }" id="channelCode" name="channelCode">
<input type="hidden" value='${property }' id="property" name="property">
<div class="submitdiv" style="text-align:center;">
<input type="submit" onclick="return checkvalue()" class="submit" style="background-color:#ffffff;" value="保存收货地址"></input>
</div>
</form>
<%@ include file="cs.jsp" %>
<%CS cs = new CS(1256812462);cs.setHttpServlet(request,response);
String imgurl = cs.trackPageView();%> 
<img src="<%= imgurl %>" width="0" height="0"  />
</body>
</html>