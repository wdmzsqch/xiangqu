<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>享趣后台管理</title>
    <link href="css/admin_login.css" rel="stylesheet" type="text/css" />
    <script src="./js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript">
    	function clearerror(){
    		$("#errmsg").text("");
    	}
    </script>
</head>
<body>
<div class="admin_login_wrap">
<h1>享趣水票后台管理</h1>
    <div class="adming_login_border">
        <div class="admin_input">
            <form action="./wlogin" method="post">
                <ul class="admin_items">
                    <li>
                        <label for="user">用户名：</label>
                        <input type="text" name="username" value="" size=36 class="admin_input_style" onfocus="clearerror()"/>
                    </li>
                    <li>
                        <label for="pwd">密码：</label>
                        <input type="password" name="pwd" value="" size=36 class="admin_input_style" onfocus="clearerror()" />
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