<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>享趣后台管理</title>
    <link href="css/admin_login.css" rel="stylesheet" type="text/css" />
    <script src="./js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript">
    	function getcode(){
    		var username = $("input[name=username]").val();
    		if(username == ""){
    			alert("请输入用户名");
    			return;
    		}
    		$.ajax({
    			type: "post",
    			url: "./loginvcode",
    			data: {username: username},
    			success: function(data){
    				times();
    				if(data == "error1"){
    					$("#errmsg").text("用户不存在");
    				}else{
    					alert("成功获取验证码,请稍候再取");
    					return;
    				}
    			}
    		});
    	}
    	
    	var wait = 60;
    	function times(){
    		   if (wait == 0) {  
    			   $(".getvcode").attr("onclick", "getcode()");
    	           wait = 60;  
    	        } else {  
    	    		$(".getvcode").removeAttr("onclick");
    	            wait--;  
    	            setTimeout(function() {  
    	                times()  
    	            },  
    	            1000)  
    	        }  
    	}
    	
    	function clearerror(){
    		$("#errmsg").text("");
    	}
    </script>
</head>
<body>
<div class="admin_login_wrap">
<h1>享趣后台管理</h1>
    <div class="adming_login_border">
        <div class="admin_input">
            <form action="./login" method="post">
                <ul class="admin_items">
                    <li>
                        <label for="user">用户名：</label>
                        <input type="text" name="username" value="" size=36 class="admin_input_style" onfocus="clearerror()"/>
                    </li>
                    <li>
                        <label for="pwd">密码：</label>
                        <input type="password" name="pwd" value="" size=36 class="admin_input_style" onfocus="clearerror()" />
                    </li>
                    <li>
                        <label for="pwd">验证码：</label>
                        <input type="text" style="float: left;" name="vcode" value="" size=18 class="admin_input_style" onfocus="clearerror()" />
                        <input type="button" style="float: right; width: 100px; height: 37px;line-height: 14px;" value="获取验证码" class="btn btn-primary getvcode" onclick="getcode()"/>
                    </li>
                    <li style="text-align: center; color: #f00" id="errmsg">${errmsg}</li>
                    <li>
                        <input type="submit" tabindex="3" value="提交" class="btn btn-primary" />
                    </li>
                </ul>
            </form>
        </div>
    </div>
    <p class="admin_copyright"><a tabindex="5" href="#">返回首页</a> &copy; 2014 Powered by <a href="#" target="_blank">享趣管理后台</a></p>
</div>
</body>
</html>