function pullUpAction() {
	var pushflg = $("#pushflg").val();
	if (pushflg == 0) {
		loadMoreOrderList();
	}
}

function loadMoreOrderList() {
	$("#loadmorecontent").text("努力加载中......");
	$("#pushflg").val(1);
	var pageIndex = $("#pageIndex").val();
	var state = $("#state").val();
	$.ajax({
		url:'./orderlistpush',
		type:'post',
		data:{state:state,pageIndex:pageIndex},
		success:function(msg){
			var orderlist = msg.orderlist;
			var fileRootUrl = msg.fileRootUrl;
			if(orderlist==null||orderlist.length==0){
				$("#loadmorecontent").text("暂无更多商品!");
			}else{
				var html = "";
				for(var i=0;i<orderlist.length;i++){
					html += '<div class="ordernumdiv">';
					html += '<img src="./imgs/order.png" width=15px style="position: relative;top: 2px;">';
					html += '<font style="font-size:15px;color:#666666;margin-left:8px;">订单编号：'+orderlist[i].orderNo+'</font>';
					html += '</div>'
					html += '<div class="contentdiv">';
					html += '<img  style="float:left;width:65px;height:65px;" src="'+fileRootUrl+orderlist[i].pic+'">';
					html += '<div style="margin-left:12px;float:left;width: 70%; overflow: hidden;">';
					html += '<div style="width:100%;font-size:16px;height:22px;overflow: hidden;">'+orderlist[i].goodsname+'</div>';
					html += '<p class="goodscount">';
					html += '共'+orderlist[i].goodscount+'件';
					html += '</p></div></div>';
					html += '<div class="bottomdiv">';
					html += '<font style="font-size:13px;color:#666666">总计:</font><font style="font-size:13px;color:#989898">￥</font><font style="color:#E84E4D;font-size:13px;">'+orderlist[i].totalPrice+'</font>';
					if(orderlist[i].state==2){
						html += '<div class="detailbtn" onclick="sendOrdershow('+orderlist[i].id+')" >发货</div></div>';
					}else{
						html += '<div class="detailbtn" onclick="detail('+orderlist[i].id+')" >详情</div></div>';
					}
				}
				$("#loadmorecontent").text("下拉加载更多");
				$("#orderlist").append(html);
				pageIndex = parseInt(pageIndex) + 1;
				$("#pageIndex").val(pageIndex);
				$("#pushflg").val(0);
				myScroll.refresh();
			}
		}
	})
}

