<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>后台管理</title>
<link rel="stylesheet" type="text/css" href="css/common.css" />
<link rel="stylesheet" type="text/css" href="css/main.css" />
<link rel="stylesheet" type="text/css" href="css/page.css" />
<script type="text/javascript" src="js/libs/modernizr.min.js"></script>
<script src="./js/jquery-1.11.3.min.js"></script>
<script src="./js/page.js"></script>
<script type="text/javascript">
    function linktoinsert() {
    	var sid = $('#select_shop_id').val();
    	var sname = $('#select_shop_id').find("option:selected").text();
    	window.location.href='./goods_detail?storeid='+sid+'&storename='+sname;
	}
    
    function putaway() {
    	$.ajax({ url: "./goods_state?statetype=1",
    		async:false,
    		type:'post',
    		data:$("input[name='goodids']:checked").serialize(),
    		success: function(data){
    			if(data == "success"){
    				alert("操作成功");
    				window.location.reload();
    			}
    		}
    		});
	}
    
    function soldout() {
    	$.ajax({ url: "./goods_state?statetype=0",
    		async:false,
    		type:'post',
    		data:$("input[name='goodids']:checked").serialize(),
    		success: function(data){
    			if(data == "success"){
    				alert("操作成功");
    				window.location.reload();
    			}
    		}
    		});
	}
    
    function delall() {
    	if(confirm("是否删除？")){
    		$.ajax({ url: "./goods_state?statetype=-1",
        		async:false,
        		type:'post',
        		data:$("input[name='goodids']:checked").serialize(),
        		success: function(data){
        			if(data == "success"){
        				alert("操作成功");
        				window.location.reload();
        			}
        		}
        		});
    	}
	}
    
    $(document).ready(function(){ 
    	$('#select_shop_id').change(function(){ 
    	if($('option:selected', '#select_shop_id').index()>0){
    		$('#toinsert').show();
    	}else{
    		$('#toinsert').hide();
    	}
    	});
    	
   		$("#allChoose").change(function() {
   			$("input[name='goodids']").prop("checked",$("#allChoose").prop("checked")); 
   		});
    		 
	    if($('option:selected', '#select_shop_id').index()==0){
	    	$('#toinsert').hide();
	    }
	    
//     	if ($.browser.msie) {
//      		  $('#allChoose').click(function () { 
//      		   this.blur();   
//      		   this.focus(); 
//      		  });   
//       	};
    	}) 
    	
    	function getgoodurl(id, goodstype){
    	$(".mask").show();
    	$(".alert").css("top",($(".mask").height()-120)/2);
    	$(".alert").css("left",($(".mask").width()-400)/2);
    	$(".alert").text("${webrooturl}"+"user/goods?goodsid="+id+"&goodstype="+goodstype);
    	$(".alert").show();
    }
    
    function hide(){
    	$(".mask").hide();
    	$(".alert").hide();
    }
    
    function goods_del(goodsid,checkstate1,checkstate2,checkstate3,checkstate4){
    	if(checkstate1 == 0){
    		alert("内容未审核");
    		return;
    	}else if(checkstate2 == 0){
    		alert("技术未审核");
    		return;
    	}else if(checkstate3 == 0){
    		alert("财务未审核");
    		return;
    	}else if(checkstate4 == 0){
    		alert("最终未审核");
    		return;
    	}else{
        	if(confirm("是否删除")){
        		window.location.href = "./goods_del?goodsid="+goodsid;
        	}
    	}
    }
    
    function check(relativeId,index){
    	var comment = $("#comment_"+index).val();
    	if(confirm("是否确认审核？")){
    		$.ajax({
    			type: "post",
    			url: "./adminCheck",
    			data:{relativeId :relativeId, type :2, comment :comment},
    			success: function(data){
    				if(data == "success"){
    					alert("审核成功");
    					window.location.reload();
    				}
    			}
    		});
    	}
    }
    
    function onLine(id,checkstate1,checkstate2,checkstate3,checkstate4){
    	if(checkstate1 == 0){
    		alert("内容未审核");
    		return;
    	}else if(checkstate2 == 0){
    		alert("技术未审核");
    		return;
    	}else if(checkstate3 == 0){
    		alert("财务未审核");
    		return;
    	}else if(checkstate4 == 0){
    		alert("最终未审核");
    		return;
    	}else{
    		if(confirm("是否上架商品")){
    			$.ajax({
    				type: "post",
    				url: "./changeonline",
    				data: {goodsid :id, type :1},
    				success: function(data){
    					if(data == "success"){
    						alert("上架成功");
    						window.location.reload();
    					}
    				}
    			});
    		}
    	}
    }
    
    function outLine(id,checkstate1,checkstate2,checkstate3,checkstate4){
    	if(checkstate1 == 0){
    		alert("内容未审核");
    		return;
    	}else if(checkstate2 == 0){
    		alert("技术未审核");
    		return;
    	}else if(checkstate3 == 0){
    		alert("财务未审核");
    		return;
    	}else if(checkstate4 == 0){
    		alert("最终未审核");
    		return;
    	}else{
    		if(confirm("是否下架该商品")){
    			$.ajax({
    				type: "post",
    				url: "./changeonline",
    				data: {goodsid :id, type :0},
    				success: function(data){
    					if(data == "success"){
    						alert("下架成功");
    						window.location.reload();
    					}
    				}
    			});
    		}
    	}
    }
    </script>
</head>
<body>


	<jsp:include page="./sidebar.jsp"></jsp:include>

	<div class="main-wrap">
		<form name="pageForm" id="pageForm" method="post" action="./goods_list">
			<div class="crumb-wrap">
				<div class="crumb-list">
					<i class="icon-font"></i><a href="./home_page">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">商品管理</span>
				</div>
			</div>
			<div class="search-wrap">
				<div class="search-content">
					<table class="search-tab">
						<tr>
							<th width="120">订单搜索:</th>
							<td><select name="type">
									<option value="1" <c:if test="${type==1}">selected</c:if>>商品名称</option>
									<option value="2" <c:if test="${type==2}">selected</c:if>>商品编号</option>
							</select></td>

							<td><input class="common-text" placeholder="关键字" name="keywords" value="${keywords}" type="text"></td>
							<td><select name="delFlg">
									<c:if test="${delFlg == 0 }">
										<option value="">状态</option>
										<option value="0" selected="selected">下架</option>
										<option value="1">上架</option>
									</c:if>
									<c:if test="${delFlg == 1 }">
										<option value="">状态</option>
										<option value="0">下架</option>
										<option value="1" selected="selected">上架</option>
									</c:if>
									<c:if test="${empty delFlg }">
										<option value="">状态</option>
										<option value="0">下架</option>
										<option value="1">上架</option>
									</c:if>
							</select></td>
							<td><select name="storeId">
									<option value="">商店</option>
									<c:forEach items="${shoplist }" var="shop">
										<option value="${shop.id }" <c:if test="${shop.id == storeId }">selected="selected"</c:if>>${shop.companyName }</option>
									</c:forEach>
							</select></td>
							<td><select name="categoryId">
									<option value="">分类</option>
									<c:forEach items="${categorylist }" var="cate">
										<option value="${cate.id }" <c:if test="${cate.id == categoryId }">selected="selected"</c:if>>${cate.cotegoryName }</option>
									</c:forEach>
							</select></td>
							<td><input class="btn btn-primary btn2" value="搜索" type="submit"> <input id="pageIndex" name="pageIndex" value="${pageIndex}" type="hidden"></td>

							<c:if test="${sessionScope.adminPrivilege != 1  && sessionScope.adminPrivilege != 2 && sessionScope.adminPrivilege != 3 && sessionScope.adminPrivilege != 4}">
							<td><select id="select_shop_id" name="select_shop_id" style="width: 200px; margin-left: 50px;">
									<option selected value="0">请选择商店</option>
									<c:forEach items="${shoplist}" var="shop">
										<option value="${shop.id }">${shop.companyName }</option>
									</c:forEach>
							</select></td>
							<td><input id="toinsert" class="btn btn-primary btn2" value="创建商品" type="button" onClick="linktoinsert()"></td>
							</c:if>

						</tr>
					</table>
					<div style="position: absolute; right: 40px; top: 115px;">当前总数：${perCount}</div>
				</div>
			</div>



			<div class="result-wrap">

				<!-- <div class="result-title">
                    <div class="result-list">
                        <input class="btn btn-primary btn2" name="creat_task" value="创建商品" type="button" onClick="javascript:location='goods_insert.html'" >
                       
                    </div>
                </div> -->
				<div class="result-content">
					<table class="result-tab" width="100%">
						<tr>
							<th class="tc" width="5%"><input id="allChoose" type="checkbox"></th>
							<th>商品编号</th>
							<th>所属商家</th>
							<th>商品主图</th>
							<th>商品名称</th>
							<c:if test="${sessionScope.adminPrivilege == 5 }">
							<th>销售价</th>
							<th>返利收益</th>
							<th>库存</th>
							</c:if>
							<th>状态</th>
							<th>商品类型</th>
							<th>审核状态</th>
							<c:if test="${sessionScope.adminPrivilege == 1 || sessionScope.adminPrivilege == 2 || sessionScope.adminPrivilege == 3 || sessionScope.adminPrivilege == 4  }">
							<th>备注</th>
							</c:if>
							<th>操作</th>
						</tr>


						<c:forEach items="${goodslist}" var="goods" varStatus="st">
							<tr>
								<td class="tc"><input name="goodids" value="${goods.id}" type="checkbox"></td>
								<td>${goods.id}</td>
								<td>${goods.storeName }</td>
								<td><img src="${fileurl}${goods.mianPic}" width="100" height="100"></td>
								<td>${goods.name}</td>
								<c:if test="${sessionScope.adminPrivilege == 5 }">
								<td>${goods.price}</td>
								<td>${goods.backMoney}</td>
								<td>${goods.stock}</td>
								</c:if>
								<td><c:if test="${goods.delFlg == 0}">下架</c:if>
									<c:if test="${goods.delFlg == 1}">上架</c:if></td>
								<td><c:if test="${goods.isServe == 0 || empty goods.isServe}">实物商品</c:if>
									<c:if test="${goods.isServe == 1}">服务商品</c:if><c:if test="${goods.isServe == 2}">充值商品</c:if></td>
								<td width="10%">
								<c:if test="${goods.checkStatus == 1 }">
								内容已审核
								</c:if>
								<c:if test="${goods.checkStatus == 0 }">
								内容未审核
								</c:if>
								<br />
								<c:if test="${goods.checkStatusY == 1 }">
								技术已审核
								</c:if>
								<c:if test="${goods.checkStatusY == 0 }">
								技术未审核
								</c:if>
								<br />
								<c:if test="${goods.checkStatusC == 1 }">
								财务已审核
								</c:if>
								<c:if test="${goods.checkStatusC == 0 }">
								财务未审核
								</c:if>
								<br />
								<c:if test="${goods.checkStatusB == 1 }">
								最终已审核
								</c:if>
								<c:if test="${goods.checkStatusB == 0 }">
								最终未审核
								</c:if>
								</td>
								<c:if test="${sessionScope.adminPrivilege == 1 || sessionScope.adminPrivilege == 2 || sessionScope.adminPrivilege == 3 || sessionScope.adminPrivilege == 4  }">
								<td>
								<c:if test="${checktype == 1 }">
								<input id="comment_${st.index }" class="common-text" value="${goods.comment }">
								<br>
								<input  class="common-text" value="${goods.comment1 }" readonly="readonly" disabled="disabled">
								<br>
								<input  class="common-text" value="${goods.comment2 }" readonly="readonly" disabled="disabled">
								<br>
								<input  class="common-text" value="${goods.comment3 }" readonly="readonly" disabled="disabled">
								</c:if>
								<c:if test="${checktype == 2 }">
								<input  class="common-text" value="${goods.comment }" readonly="readonly" disabled="disabled">
								<br>
								<input id="comment_${st.index }"  class="common-text" value="${goods.comment1 }">
								<br>
								<input  class="common-text" value="${goods.comment2 }" readonly="readonly" disabled="disabled">
								<br>
								<input  class="common-text" value="${goods.comment3 }" readonly="readonly" disabled="disabled">
								</c:if>
								<c:if test="${checktype == 3 }">
								<input  class="common-text" value="${goods.comment }" readonly="readonly" disabled="disabled">
								<br>
								<input  class="common-text" value="${goods.comment1 }" readonly="readonly" disabled="disabled">
								<br>
								<input id="comment_${st.index }" class="common-text" value="${goods.comment2 }">
								<br>
								<input  class="common-text" value="${goods.comment3 }" readonly="readonly" disabled="disabled">
								</c:if>
								<c:if test="${checktype == 4 }">
								<input  class="common-text" value="${goods.comment }" readonly="readonly" disabled="disabled">
								<br>
								<input  class="common-text" value="${goods.comment1 }" readonly="readonly" disabled="disabled">
								<br>
								<input  class="common-text" value="${goods.comment2 }" readonly="readonly" disabled="disabled">
								<br>
								<input id="comment_${st.index }" class="common-text" value="${goods.comment3 }">
								</c:if>
								</td>
								</c:if>
								<td>
								<c:if test="${sessionScope.adminPrivilege == 5 }">
								<a class="link-update" href="./goods_detail?goodsid=${goods.id}">修改商品</a> <a class="link-del" onclick="goods_del(${goods.id},${goods.checkStatus},${goods.checkStatusY},${goods.checkStatusC},${goods.checkStatusB})">删除</a> <c:if
										test="${goods.delFlg == 0}">
										<a class="link-update" onclick="onLine(${goods.id},${goods.checkStatus},${goods.checkStatusY},${goods.checkStatusC},${goods.checkStatusB})">上架</a>
									</c:if> <c:if test="${goods.delFlg == 1}">
										<a class="link-update" onclick="outLine(${goods.id},${goods.checkStatus},${goods.checkStatusY},${goods.checkStatusC},${goods.checkStatusB})">下架</a>
									</c:if> <br />
									<a class="link-del" href="./goodsSort_up?id=${goods.id}">置顶</a>
									<br/>
									<div style="cursor: pointer; color: #428bca;" onclick="getgoodurl(${goods.id}, ${goods.payType })">获取商品地址</div>
								</c:if>
								<c:if test="${sessionScope.adminPrivilege == 8 }">
								<a class="link-update" href="./goods_detail?goodsid=${goods.id}">修改商品</a> 
								<c:if test="${goods.delFlg == 0}">
										<a class="link-update" onclick="onLine(${goods.id},${goods.checkStatus},${goods.checkStatusY},${goods.checkStatusC},${goods.checkStatusB})">上架</a>
									</c:if> <br />
									<a class="link-del" href="./goodsSort_up?id=${goods.id}">置顶</a>
									<br/>
									<div style="cursor: pointer; color: #428bca;" onclick="getgoodurl(${goods.id}, ${goods.payType })">获取商品地址</div>
								</c:if>
								<a class="link-del" href="./good2dCode?id=${goods.id}">二维码详情</a>
								<c:if test="${sessionScope.adminPrivilege == 1 || sessionScope.adminPrivilege == 2 || sessionScope.adminPrivilege == 3 || sessionScope.adminPrivilege == 4 }">
								<a class="link-update" href="./goods_detail?goodsid=${goods.id}">查看商品</a>
								<c:if test="${goods.isCheck == 0 }">
								<br/>
								<a class="link-update" style="cursor: pointer;" onclick="check(${goods.id},${st.index })">审核</a>
								</c:if>
								<c:if test="${goods.isCheck == 1 }"><br/>已审核</c:if>
								</c:if>
								</td>

							</tr>

						</c:forEach>



					</table>
					<div style="float: left">
						<font size="+1">批量处理:</font> 
<!-- 						<input class="btn btn-primary btn2" type="button" value="上架" onclick="putaway()" /> <input class="btn btn-primary btn2" type="button" value="下架" -->
<!-- 							onclick="soldout()" />  -->
							<input class="btn btn-primary btn2" type="button" value="删除" onclick="delall()" />
					</div>



					<br />
					<c:if test="${pageCount>1}">
						<input type="hidden" value="${pageCount }" id="pageCount" name="pageCount"/>
					<input type="hidden" value="${pageIndex }" id="pageIndex" name="pageIndex"/>
					<div id="untreatedpage" ></div>
					<script type="text/javascript">
						//container 容器，count 总页数 pageindex 当前页数
						function setPage(container, count, pageindex) {
						var a = [];
						  //总页数少于10 全部显示,大于10 显示前3 后3 中间3 其余....
						  if (pageindex == 1) {
							  //alert(pageindex);
						    a[a.length] = "<a onclick=\"\" class=\"hide_page_prev pagehover unclickprev \">&lt;&nbsp;上一页</a>";
						  } else {
						    a[a.length] = "<a onclick=\"pageprev()\" class=\"pagehover page_prev\">&lt;&nbsp;上一页</a>";
						  }
						  function setPageList() {
						    if (pageindex == i) {
						      a[a.length] = "<a onclick=\"pageto(" + i + ")\" class=\"on\">" + i + "</a>";
						    } else {
						      a[a.length] = "<a onclick=\"pageto(" + i + ")\">" + i + "</a>";
						    }
						  }
						  //总页数小于10
						  if (count <= 10) {
						    for (var i = 1; i <= count; i++) {
						      setPageList();
						    };
						  } else {
							//总页数大于10页
						    if (pageindex <= 4) {
						      for (var i = 1; i <= 5; i++) {
						        setPageList();
						      }
						      a[a.length] = "...<a onclick=\"pageto(" + count + ")\">" + count + "</a>";
						    } else if (pageindex >= count - 3) {
						      a[a.length] = "<a onclick=\"pageto(1)\">1</a>...";
						      for (var i = count - 4; i <= count; i++) {
						        setPageList();
						      };
						    } else { //当前页在中间部分
						      a[a.length] = "<a onclick=\"pageto(1)\">1</a>...";
						      for (var i = pageindex - 2; i <= pageindex+2; i++) {
						        setPageList();
						      }
						      a[a.length] = "...<a onclick=\"pageto(" + count + ")\">" + count + "</a>";
						    }
						  }
						  if (pageindex == count) {
							    a[a.length] = "<a onclick=\"\" class=\"hide_page_next pagehover unclicknext \">下一页&nbsp;&gt;</a> ";
							  } else {
							    a[a.length] = 
							    	"<a onclick=\"pagenext()\" "+
							    	"class=\"pagehover page_next \">下一页&nbsp;&gt;</a> ";
							  }
						  container.innerHTML = a.join("");
						} 
						setPage(document.getElementById("untreatedpage"),parseInt($("#pageCount").val()),parseInt($("#pageIndex").val()));
						</script>	
					</c:if>








				</div>

			</div>

		</form>
	</div>
	<!--/main-->
	</div>
	<div class="mask" style="display: none;" onclick="hide()"></div>
	<div class="alert" style="position: fixed; display: none; width: 400px; height: 120px; overflow: hidden; background: #fff; z-index: 101; padding: 20px;"></div>
</body>
</html>