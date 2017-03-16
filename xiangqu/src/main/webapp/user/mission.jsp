<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no">
<title>我分享</title>
<link rel="stylesheet" type="text/css" href="./css/common.css" />
<link rel="stylesheet" type="text/css" href="./css/share.css" />
<link rel="stylesheet" type="text/css" href="./css/flickerplate.css" />
<script type="text/javascript" src="./js/jquery-1.11.3.min.js"></script>
<script src="./js/modernizr-custom-v2.7.1.min.js"></script>
<script src="./js/jquery-finger-v0.1.0.min.js"></script>
<script src="./js/flickerplate.min.js"></script>
<script src="js/jquery.lazyload.js?v=1.9.1"></script>
<script type="text/javascript">
	$(document).ready(function() {
		if('${isapp}' != 1){
			setTabSelected(1);
		}
		var length = $("#missionlength").val();
		if(length != 0){
			if('${dynamic.pic}' != ''){
				var demo = document.getElementById("demo");
				var demo1 = document.getElementById("demo1");
				var demo2 = document.getElementById("demo2");
				demo2.innerHTML=document.getElementById("demo1").innerHTML;
				function Marquee(){
				if(demo.scrollLeft-demo2.offsetWidth>=0){
				 demo.scrollLeft-=demo1.offsetWidth;
				}
				else{
				 demo.scrollLeft++;
				}
				}
				var myvar=setInterval(Marquee,60);
				demo.onmouseout=function (){myvar=setInterval(Marquee,60);}
				demo.onmouseover=function(){clearInterval(myvar);}
			}
		}

	});

	function goodsShare() {
		window.location.replace("./sharegoods");
	}
	
	function missiondetail(id,ramianTimes, overtime, state){
		if(state <= 2){
			window.location.href="./missiondetail?id="+id+"&shareuserid="+'${userid}';
		}else{
			$(".missalert").css("left",($(".mask").width()-290)/2);
			$(".missalert").css("top",($(".mask").height()-$(".missalert").height())/2);
			$(".mask").show();
			$(".missalert").show();
			$("#alertId").val(id);
			$("#shareId").val('${userid}');
		}
	}
	
	function submitForm(){
		window.location.href="./missiondetail?id="+$("#alertId").val()+"&shareuserid="+$("#shareId").val();
	}
	
	$(function(){
		 $("img.lazy").lazyload({
			 effect: "fadeIn",
			 threshold: 200,
			 placeholder : "imgs/load_default.jpg"
		});
		 $("li").lazyload({effect: "fadeIn"});
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
	});
	

	function hide(){
		$(".mask").hide();
		$(".missalert").hide();
	}
	
	function getsubject(){
		window.location.href = "./subject";
	}
	
	function getevent(){
		window.location.href = "./eventlist";
	}
	
	function turntonew(type, id, url){
		if(type == 0){
			window.location.href = url;
		}
		if(type == 1){
			window.location.href = "./lookeventdetail?id="+id;
		}
		if(type == 2){
			window.location.href = "./subjectdetail?id="+id;
		}
	}
	
	function editorAddress(userid){
		window.location.href = "./chooseaddress?userid="+userid+"&type=1";
	}
	
	function gohref(url){
		window.location.href=url;
	}
	
	function turntosysmessage(){
		window.location.href = "./message";
	}
</script>
</head>
<body style="font-family: '微软雅黑'">
	<%-- 	<jsp:include page="./top_noback.jsp" /> --%>
	<div style="position: fixed; top: 0px; z-index: 1000; height: 44px; line-height: 44px; width: 100%; background: #00d1bc; color: #fff;">
		<img src="imgs/location_bai.png" style="width: 12px; height: 16px; margin-top: 14px; float:left; margin-left: 12px;" onclick="editorAddress(${userid})">
		<div style="padding-left: 6px; padding-right: 12px; font-size: 11px; float: left;" onclick="editorAddress(${userid})">
				<c:if test="${empty proviceName && empty cityName && empty areaName}">全国</c:if>
				<c:if test="${not empty proviceName || not empty cityName || not empty areaName}">${proviceName }${cityName }${areaName }</c:if>
		</div>
		<c:if test="${count > 0 }">
		<div style="float: right; margin-right: 12px; margin-top: 10px; width: 8px; height: 8px; border-radius: 4px; background: #FF0000;"></div>
		<img src="imgs/message_icon.png" style="float: right; margin-top: 13px; width: 19px; height: 15px;" onclick="turntosysmessage()">
		</c:if>
		<c:if test="${count <= 0 }">
		<img src="imgs/message_icon.png" style="float: right; margin-right: 12px; margin-top: 13px; width: 19px; height: 15px;" onclick="turntosysmessage()">
		</c:if>
	</div>
<%-- 			<div style="position: fixed; top: 6px; z-index: 1000; height: 26px; line-height: 26px; left: 0px; background: #00d1bc; opacity: 0.95; color: #fff; border-top-right-radius:13px; border-bottom-right-radius: 13px;" onclick="editorAddress(${userid})"> --%>
<!-- 				<img src="imgs/location_bai.png" style="width: 12px; height: 16px; margin-top: 5px; float:left; margin-left: 12px;"> -->
<!-- 				<div style="padding-left: 6px; padding-right: 12px; font-size: 11px; float: left;"> -->
<%-- 				<c:if test="${empty proviceName && empty cityName && empty areaName}">全国</c:if> --%>
<%-- 				<c:if test="${not empty proviceName || not empty cityName || not empty areaName}">${proviceName }${cityName }${areaName }</c:if> --%>
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 			<div style="position: fixed; top: 6px; z-index: 1000; width: 50px; height: 26px; line-height: 26px; right: 0px; background: #00d1bc; opacity: 0.95; color: #fff; border-top-left-radius:13px; border-bottom-left-radius: 13px;"> -->
<!-- 				<img src="imgs/message_icon.png" style="float: right;margin-right: 12px; margin-top: 5px; width: 19px; height: 15px;" onclick="turntosysmessage()"> -->
<!-- 			</div> -->
	<%-- 	<div style="width: 100%; height: 69px; border-bottom: 1px solid #e8e8e8;" onclick="turntonew(${homeActivityOne.type }, ${homeActivityOne.relatived_id })"> --%>
	<%-- 		<img src="${fileRootUrl }${homeActivityOne.pic }" style="width: 100%; height: 69px;"> --%>
	<!-- 	</div> -->
	<!-- 	<div style="width: 100%; height: 69px; border-bottom: 1px solid #e8e8e8;" > -->
	<%-- 		<div style="width: 46%; height: 69px; border-right: 1px solid #e8e8e8; margin-left: 4%;float: left;" onclick="turntonew(${homeActivityTwo.type }, ${homeActivityTwo.relatived_id })"> --%>
	<%-- 			<img src="${fileRootUrl }${homeActivityTwo.pic }" style="width: 92%; height: 69px;"> --%>
	<!-- 		</div> -->
	<!-- 		<div style="width: 45%; height: 69px; margin-left: 4%;float: left;"> -->
	<%-- 			<img src="${fileRootUrl }${homeActivityThree.pic }" style="width: 92%; height: 69px;" onclick="turntonew(${homeActivityThree.type }, ${homeActivityThree.relatived_id })"> --%>
	<!-- 		</div> -->
	<!-- 	</div> -->
	<!-- 	<div style="width: 100%; height: 69px; border-bottom: 1px solid #e8e8e8;"> -->
	<%-- 		<div style="width: 46%; height: 69px; border-right: 1px solid #e8e8e8; margin-left: 4%;float: left;" onclick="turntonew(${homeActivityFour.type }, ${homeActivityFour.relatived_id })"> --%>
	<%-- 			<img src="${fileRootUrl }${homeActivityFour.pic }" style="width: 92%; height:69px;"> --%>
	<!-- 		</div> -->
	<!-- 		<div style="width: 45%; height: 69px; margin-left: 4%;float: left;"> -->
	<%-- 			<img src="${fileRootUrl }${homeActivityFive.pic }" style="width: 92%; height: 69px;" onclick="turntonew(${homeActivityFive.type }, ${homeActivityFive.relatived_id })"> --%>
	<!-- 		</div> -->
	<!-- 	</div> -->

	<!-- 	<div style="width: 100%; height: 35px; border-bottom: 1px solid #e8e8e8; line-height: 35px; font-size: 11px; color: #d1d1d1;"> -->
	<!-- 		<div style="width: 50%;text-align: center; float:left;" onclick="getsubject()">查看全部专题&nbsp;&nbsp;&gt;</div> -->
	<!-- 		<div style="float: left; height: 21px; border-left: 1px solid #e8e8e8;margin-top: 7px;"></div> -->
	<!-- 		<div style="width: 49%;text-align: center; float:left;" onclick="getevent()">查看全部活动&nbsp;&nbsp;&gt;</div> -->
	<!-- 	</div> -->

	<div class="flicker-example" style="margin-top: 44px;">
		<ul>
			<c:forEach items="${adlist}" var="p">
				<li data-background="${fileRootUrl }${p.pic}" onclick='gohref("${p.url}")' style="background-image: url('${fileRootUrl }${p.pic}');"></li>
			</c:forEach>
		</ul>
	</div>
	<c:if test="${not empty modelList }">
	<div style="width: 100%; height: 75px; background: #fff; border-bottom: 1px solid #e8e8e8;">
		<div style="width: 92%; height:75px; margin: 0 auto;">
			<c:forEach items="${modelList }" var="model">
				<a href="${model.url }">
				<div style="width: 20%; height: 75px; float: left; text-align: center; font-size: 11px;">
					<img src="${fileRootUrl }${model.pic}" style="width: 40px; height: 40px; margin-top: 10px; border-radius: 49px;">
					<br/>
					${model.title }
				</div>
				</a>
			</c:forEach>
		</div>
	</div>
	</c:if>
	
	
	
	
	<div style="width: 100%; height: auto;" >
			<div style="width: 50%; height: auto; float: left;" onclick="turntonew(${homeActivityOne.type }, ${homeActivityOne.relatived_id }, '${homeActivityOne.detailUrl }')">
				<img src="${fileRootUrl }${homeActivityOne.pic }" style="width: 100%;">
			</div>
			<div style="width: 50%; height: auto; float: left;">
				<img src="${fileRootUrl }${homeActivityTwo.pic }" style="width: 100%;" onclick="turntonew(${homeActivityTwo.type }, ${homeActivityTwo.relatived_id }, '${homeActivityTwo.detailUrl }')">
			</div>
		</div>
		<div style="width: 100%; height: auto;">
			<div style="width: 50%; height: auto; float: left;" onclick="turntonew(${homeActivityThree.type }, ${homeActivityThree.relatived_id }, '${homeActivityThree.detailUrl }')">
				<img src="${fileRootUrl }${homeActivityThree.pic }" style="width: 100%; ">
			</div>
			<div style="width: 50%; height: auto; float: left;">
				<img src="${fileRootUrl }${homeActivityFour.pic }" style="width: 100%; " onclick="turntonew(${homeActivityFour.type }, ${homeActivityFour.relatived_id }, '${homeActivityFour.detailUrl }')">
			</div>
			<div style="width: 100%; height: 10px; background-color: #e8e8e8; float: left;" ></div>
		</div>
	
	
	
	<input type="hidden" value="${fn:length(missionlist)}" id="missionlength"/>
	
	<div style="padding-bottom: 47px;">
		<c:forEach items="${missionlist}" var="p" varStatus="st">
			<c:if test="${st.first }">
				<div style="width: 100%; height: 8px; border-bottom: 1px solid #e8e8e8; background: #f2f2ef;"></div>
				<div style="width: 100%; height: 33px; border-bottom: 1px solid #e8e8e8; line-height: 33px; overflow: hidden;">
				<div style="width: 92%; height: 33px; margin: 0 auto;" id="weekDy">
				<div style="width: 114px; height: 33px; float: left;">
					<span style="font-size: 14px;"><fmt:formatDate value="${p.publishTime }" pattern="MM月dd日" />&nbsp;&nbsp;${p.weekOfDate } </span>
				</div>
				<c:if test="${not empty dynamic.pic }">
				<a href="${dynamic.url }">
				<div style="height: 33px; float: right; font-size: 11px; color: #00d1bc; " id="dydiv"> 
				<div style="float: right; overflow: hidden; width: 70px;" id="demo">     
				<div style="width: 8000%; height: 30px;">     
				<div id="demo1" style="float:left;">${dynamic.pic }&nbsp;${dynamic.pic }&nbsp;${dynamic.pic }&nbsp;</div>     
				<div id="demo2" style="float:left;"></div>     
				</div> 
				</div> 
				<img src="imgs/tongzhi_icon.png" style="width: 13px; height: 12px; float: right; margin-top: 10px; margin-right: 10px;">
				</div>
				</a>
				</c:if>
				</div>
				</div>
			</c:if>

			<c:set var="currentday">
				<fmt:formatDate value="${p.publishTime }" pattern="yyyyMMdd" />
			</c:set>

			<c:if test="${not st.first }">
				<c:if test="${empty lastday || not (currentday eq lastday)}">
					<div style="width: 100%; height: 8px; border-bottom: 1px solid #e8e8e8; background: #f2f2ef;"></div>
					<div style="width: 100%; height: 33px; border-bottom: 1px solid #e8e8e8; line-height: 33px;">
						<span style="padding-left: 4%; font-size: 14px;"><fmt:formatDate value="${p.publishTime }" pattern="MM月dd日" />&nbsp;&nbsp;${p.weekOfDate } </span>
					</div>
				</c:if>

			</c:if>
			<c:set var="lastday">
				<fmt:formatDate value="${p.publishTime }" pattern="yyyyMMdd" />
			</c:set>

			<div style="width: 100%; height: auto; border-bottom: 1px solid #e8e8e8;" onclick="missiondetail(${p.id},${p.ramianTimes },${p.overtime},${p.state })">
				<div class="missionDiv">
					<%-- <c:if test="${p.ramianTimes != 0 && p.overtime == 0}"> --%>
					<c:if test="${p.state <= 2}">
						<div style="position: relative;">
							<img class="lazy" data-original="${fileRootUrl }${p.pic}" style="width: 100%; border-radius: 10px; min-height: 100px; height: auto;" >
						</div>
					</c:if>

					<%-- <c:if test="${p.ramianTimes == 0 || p.overtime == 1}"> --%>
					<c:if test="${p.state >= 3}">
						<div style="position: relative;">
							<%-- <c:if test="${empty p.pic}">
								<img src="${fileRootUrl }${p.pic}" class="grayimage" style="width: 100%; border-radius: 10px; min-height: 100px;">
								<div style="position: absolute; width: 100%; min-height: 100px; border-radius: 10px; background: #353535; height: 100%; opacity: 0.8; top: 0px; text-align: center; color: #fff;">
							</c:if>
							<c:if test="${not empty p.pic }">
								<img src="${fileRootUrl }${p.pic}" class="grayimage" style="width: 100%; border-radius: 10px; min-height: 100px;">
								<div style="position: absolute; width: 100%; border-radius: 10px; min-height: 100px; background: #353535; height: 100%; opacity: 0.8; top: 0px; text-align: center; color: #fff;">
							</c:if> --%>

							<img class="lazy" data-original="${fileRootUrl }${p.pic}" style="width: 100%; border-radius: 10px; min-height: 100px; height: auto;">
							<div
								style="position: absolute; width: 100%; border-radius: 10px; min-height: 100px; height: 100%; opacity: 0.9; top: 0px;">
								<div style="bottom: 10px; text-align: center; position: absolute; right: 10px;">
									<!-- <div style="position: relative; top: -50%; left: -50%; height: 30px; border-bottom: 1px solid #fff; font-size: 22px;">已结束</div> -->
									<img src="./imgs/over.png" style="width: 69px; height: 58px;"/>
								</div>
							</div>
						</div>
					</c:if>

					<div
						style="position: absolute; height: 20px; background: #4c4b49; opacity: 0.8; color: #fff; z-index: 200; top: 5px; right: 5px; overflow: hidden; border-radius: 16px; line-height: 20px; font-size: 10px;">
						<div style="float: left; padding-left: 10px; padding-right: 5px; text-align: center;" class="overflowOmit">已投放&nbsp;${p.totalTimes-p.ramianTimes}</div>
						<%-- ${p.totalTimes-p.ramianTimes } --%>
						<div style="float: left; text-align: center;">|</div>
						<div style="float: left; padding-left: 5px; padding-right: 10px; text-align: center;" class="overflowOmit">剩余&nbsp;${p.ramianTimes}</div>
					</div>
					<div style="width: 100%; height: auto; overflow: hidden; padding-bottom: 15px; padding-top: 12px;">
						<div style="float: left; width: 60%;">
							<div style="width: 100%;; font-size: 17px; overflow: hidden;">${p.name}</div>
							<div style="width: 100%; height: 14px; font-size: 14px; line-height: 14px; margin-top: 2px;">
								<!-- 						<div style="height: 100%; width: 3px; background-color: #F84E4E; float: left;"></div> -->
								<div style="height: 100%; color: #F84E4E; float: left; font-size: 12px; margin-top: 5px; overflow: hidden;">
									收益:<font id="income"><fmt:formatNumber type="number" value="${p.income }" maxFractionDigits="0" /></font>积分<span style="font-size: 10px;">(分享被点击即可获得)</span>
								</div>
							</div>
						</div>
						<div style="text-align: center; float: left; width: 40%;">
<%-- 							<c:if test="${p.isShare==0 }"> --%>
<%-- 								<c:if test="${p.state >= 3}"> --%>
<!-- 									<div style="float: right; line-height: 27px; color: #c3c3c3; height: 27px; width: 98px; border: 1px solid #c3c3c3; border-radius: 14px; margin-top: 3.5px">分享赚积分</div> -->
<%-- 								</c:if> --%>
<%-- 								<c:if test="${p.state <= 2}"> --%>
<!-- 									<div style="float: right; line-height: 27px;color:#fff;background: #00d1bc; height: 27px; width: 98px; border-radius: 14px; margin-top: 3.5px">分享赚积分</div> -->
<%-- 								</c:if> --%>
<%-- 							</c:if> --%>
<%-- 							<c:if test="${p.isShare==1 }"> --%>
<%-- 								<c:if test="${p.state >= 3}"> --%>
<!-- 									<div style="float: right; line-height: 27px; color: #fff; background: #c3c3c3; height: 27px; width: 98px; border-radius: 14px; margin-top: 3.5px">分享赚积分</div> -->
<%-- 								</c:if> --%>
<%-- 								<c:if test="${p.state <= 2}"> --%>
<!-- 									<div style="float: right; line-height: 27px; color: #fff; background: #00d1bc; height: 27px; width: 98px; border-radius: 14px; margin-top: 3.5px">分享赚积分</div> -->
<%-- 								</c:if> --%>
<%-- 							</c:if> --%>
							<div style="float: right; line-height: 27px;color:#fff;background: #00d1bc; height: 27px; width: 98px; border-radius: 14px; margin-top: 3.5px">分享赚积分</div>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
	<c:if test="${isapp != 1 }">
	<jsp:include page="./tab.jsp" />
	</c:if>

<%@ include file="cs.jsp" %>
<%CS cs = new CS(1256812462);cs.setHttpServlet(request,response);
String imgurl = cs.trackPageView();%> 
<img src="<%= imgurl %>" width="0" height="0"  />
</body>
<div class="mask" style="display: none; z-index: 1000" onclick="hide()"></div>
<div class="missalert" style="width: 290px;border-radius: 4px;position: fixed; padding: 5px; background-color: #ffffff;height: auto;z-index: 100;display: none; z-index: 100000;">
	<div style="width: 100%; height: 36px; text-align: center; line-height: 36px;">任务已结束，再分享点击不再有收益。</div>
	<input type="hidden" id="alertId"/>
	<input type="hidden" id="shareId"/>
	<div style="width: 100%; height: 30px; float: left; margin:0 auto; color: #33CCFF; font-weight: bold;  text-align: center; line-height: 30px; border-radius: 6px;margin-top: 13px;  border-top: 1px solid #e8e8e8;"
					onclick="submitForm()">我知道了</div>
</div>
</html>