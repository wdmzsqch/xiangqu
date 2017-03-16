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
		url:'./orderpush',
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
//					html += '<img src="./imgs/order.png" width=15px style="position: relative;top: 2px;">';
					html += '<font style="font-size:15px;"><font style="color:rgb(191,191,191);">订单编号&nbsp;&nbsp;</font>'+orderlist[i].orderNo+'</font>';
					html += '</div>'
					html += '<div class="contentdiv">';
					html += '<img  style="float:left;width:65px;height:65px;" src="'+fileRootUrl+orderlist[i].pic+'">';
					html += '<div style="margin-left:12px;float:left;width: 70%;">';
					html += '<div style="width:100%;font-size:16px;height:22px;overflow: hidden;">'+orderlist[i].goodsname+'</div>';
					html += '<div style="height: 8px;"></div>';
					html += '<div style="height: 30px;">';
					html += '<span style="float: left;">￥'+orderlist[i].totalPrice+'</span>';
					html += '<span style="float: right; color: rgb(191,191,191)">x'+orderlist[i].goodscount+'</span>';
					html += '</div></div></div>';
					html += '<div class="bottomdiv">';
					html += '<font style="font-size:13px;">共'+orderlist[i].goodscount+'件商品&nbsp;&nbsp;全计:￥'+orderlist[i].totalPrice+'</font>';
					if(orderlist[i].state == 1){
						html += '<div class="detailbtn" onclick="detail('+orderlist[i].id+')" >付款</div></div>';
					}
					if(orderlist[i].state == 2){
						html += '<div class="detailbtn" style="border: 0px; width: 60px;" onclick="detail('+orderlist[i].id+')" >等待发货</div></div>';
					}
					if(orderlist[i].state == 3){
						html += '<div class="detailbtn" style="width:70px; border: 1px solid RGB(0,209,188); color: RGB(0,209,188)" onclick="reciveorder('+orderlist[i].id+')">确认收货</div></div>';
					}
					if(orderlist[i].state == 4){
						html += '<div class="detailbtn" style="width:70px; border: 1px solid RGB(0,209,188); color: RGB(0,209,188)" onclick="reciveorder('+orderlist[i].id+')">评价</div></div>';
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

