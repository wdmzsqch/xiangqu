<div style="pisition:fixed;width:100%;height:30px;background-color:#ffffff;">
<div class="typebanner">
			<ul>
				<li onclick="order(1)" id="li_1">待付款</li>
				<li onclick="order(2)" id="li_2">待发货</li>
				<li onclick="order(3)" id="li_3">已发货</li>
				<li onclick="order(4)" id="li_4">已成功</li>
			</ul> 
		</div>	
</div>

<script>


function order(state){
	if(state==0){
		window.location.href="./orderlist";
	}else{
		window.location.href="./orderlist?state="+state;
	}
}
</script>