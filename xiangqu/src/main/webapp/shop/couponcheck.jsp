<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, user-scalable=no">
<title>优惠券认证</title>
</head>
<body>
	<form action="./coupon_check" method="post">
		<div style="margin-top: 10px; font-size: 15px;">
			<input type="text" style="width: 80%; margin-left: 10%; height: 30px; line-height: 30px; border-radius: 4px; border: 1px solid #ccc;" placeholder="优惠券码" name="code" required="required">
			<input type="submit" value="验证" style="width: 80%; margin-left: 10%; margin-top: 20px; height: 30px; border-radius: 8px; border: 1px solid #ccc;">
		</div>
	</form>
</body>

</html>