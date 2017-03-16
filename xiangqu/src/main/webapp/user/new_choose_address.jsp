<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, user-scalable=no">
<title>修改地址</title>
<link rel="stylesheet" type="text/css" href="./css/common.css" />
<link rel="stylesheet" type="text/css" href="css/user.css" />
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">

</script>
</head>

<body class="bj01">
<form action="mission" enctype="multipart/form-data" method="post" id="editorform">
<c:if test="${type == 0}">
<a href="xqmissiongeo://">
<div class="hotcity">自动定位</div>
</a>
</c:if>
<c:if test="${type == 1}">
<a href="./missiongeo?type=1">
<div class="hotcity">自动定位</div>
</a>
</c:if>
<div style="width: 96%; height: 23px; line-height: 23px; margin: 0 auto; font-size: 14px; clear: both;">热门城市</div>
<c:if test="${type == 0}">
<a href="xqlocation://province=0&city=0&area=0">
<div class="hotcity">全国</div>
</a>
<a href="xqlocation://province=0&city=35&area=0">
<div class="hotcity">北京</div>
</a>
<a href="xqlocation://province=0&city=271&area=0">
<div class="hotcity">成都</div>
</a>
<a href="xqlocation://province=0&city=270&area=0">
<div class="hotcity">重庆</div>
</a>
<a href="xqlocation://province=0&city=232&area=0">
<div class="hotcity">广州</div>
</a>
<a href="xqlocation://province=0&city=121&area=0">
<div class="hotcity">杭州</div>
</a>
<a href="xqlocation://province=0&city=108&area=0">
<div class="hotcity">南京</div>
</a>
<a href="xqlocation://province=0&city=107&area=0">
<div class="hotcity">上海</div>
</a>
<a href="xqlocation://province=0&city=234&area=0">
<div class="hotcity">深圳</div>
</a>
<a href="xqlocation://province=0&city=36&area=0">
<div class="hotcity">天津</div>
</a>
<a href="xqlocation://province=0&city=204&area=0">
<div class="hotcity">武汉</div>
</a>
<a href="xqlocation://province=0&city=125&area=0">
<div class="hotcity">湖州</div>
</a>
<div style="clear: both; width: 96%; margin:0 auto; height: 10px; border-bottom: 1px solid #d8d8d8;"></div>
<div style="width: 96%; height: 40px; line-height: 23px; margin: 0 auto; font-size: 14px;">全部城市</div>
<c:forEach items="${list }" var="p">
<a href="xqlocation://province=${p.id}&city=0&area=0">
<div style="padding-left: 10px; padding-right: 10px; margin-left: 2%; margin-top: 10px; width: auto; float: left; height: 33px; line-height: 33px; text-align: center; border: 1px solid #dcdcdc; color: #999999; background: #f6f7f2;">${p.name }</div>
</a>
<div style="clear: both;"></div>
<c:forEach items="${p.citylist }" var="c">
<a href="xqlocation://province=${p.id}&city=${c.id }&area=0">
	<div style="width: 96%; height: 38px; line-height: 38px; margin: auto; color: #7c7c7c; border-bottom: 1px solid #d7d7d7; font-size: 12px;">${c.name }</div>
</a>
</c:forEach>
</c:forEach>
</c:if>
<c:if test="${type == 1}">
<a href="./mission?province=&city=&area=">
<div class="hotcity">全国</div>
</a>
<a href="./mission?province=&city=35&area=">
<div class="hotcity">北京</div>
</a>
<a href="./mission?province=&city=271&area=">
<div class="hotcity">成都</div>
</a>
<a href="./mission?province=&city=270&area=">
<div class="hotcity">重庆</div>
</a>
<a href="./mission?province=&city=232&area=">
<div class="hotcity">广州</div>
</a>
<a href="./mission?province=&city=121&area=">
<div class="hotcity">杭州</div>
</a>
<a href="./mission?province=&city=108&area=">
<div class="hotcity">南京</div>
</a>
<a href="./mission?province=&city=107&area=">
<div class="hotcity">上海</div>
</a>
<a href="./mission?province=&city=234&area=">
<div class="hotcity">深圳</div>
</a>
<a href="./mission?province=&city=36&area=">
<div class="hotcity">天津</div>
</a>
<a href="./mission?province=&city=204&area=">
<div class="hotcity">武汉</div>
</a>
<a href="./mission?province=&city=125&area=">
<div class="hotcity">湖州</div>
</a>
<div style="clear: both; width: 96%; margin:0 auto; height: 10px; border-bottom: 1px solid #d8d8d8;"></div>
<div style="width: 96%; height: 40px; line-height: 23px; margin: 0 auto; font-size: 14px;">全部城市</div>
<c:forEach items="${list }" var="p">
<a href="./mission?province=${p.id}&city=&area=">
<div style="padding-left: 10px; padding-right: 10px; margin-left: 2%; margin-top: 10px; width: auto; float: left; height: 33px; line-height: 33px; text-align: center; border: 1px solid #dcdcdc; color: #999999; background: #f6f7f2;">${p.name }</div>
</a>
<div style="clear: both;"></div>
<c:forEach items="${p.citylist }" var="c">
<a href="./mission?province=${p.id}&city=${c.id }&area=">
	<div style="width: 96%; height: 38px; line-height: 38px; margin: auto; color: #7c7c7c; border-bottom: 1px solid #d7d7d7; font-size: 12px;">${c.name }</div>
</a>
</c:forEach>
</c:forEach>
</c:if>
</form>

<%@ include file="cs.jsp" %>
<%CS cs = new CS(1256812462);cs.setHttpServlet(request,response);
String imgurl = cs.trackPageView();%> 
<img src="<%= imgurl %>" width="0" height="0"  />
</body>
</html>
