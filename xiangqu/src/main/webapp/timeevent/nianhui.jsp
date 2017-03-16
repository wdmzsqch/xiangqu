<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body style="background-image: url('./imgs/bj.png');">
	<div style="width: 1440px; height: 900px; margin: auto;">
		<div style="height: auto; margin-top: 40px; margin-left: 70px; margin-right: 70px;">
			<div style="width: 1300px; height: 148px;">
				<div style="float: left; background-image: url('./imgs/name.png'); height: 126px; margin-top: 11px; width: 523px;"></div>
				<div style="float: right; width: 150px; height: 126px; background-image: url('./imgs/logo.png'); margin-top: 11px;"></div>
			</div>
			<div style="background-image: url('./imgs/35p.png'); height: 700px; width: 1300px;">
				<div style="height: 20px; width: 1300px;"></div>
				<div style="color: #fff; text-align: center; font-size: 48px; line-height: 48px;">欢迎${cname}公司代表光临</div>

				<div style="background-color: #fff; height: 570px; width: 1200px; margin-left: 50px; margin-top: 20px;">
					<c:forEach items="${clist}" var="c" varStatus="status">
						<div style="background-image: url('./imgs/${status.index+1}.jpg'); float:left; width: 120px; height: 30px;">
							<c:if test="${not (c.isArrive==1)}">
								<img style="width: 120px; height: 30px; margin: 0px;" src="./imgs/70p.png">
							</c:if>
						</div>
					</c:forEach>

					<!-- <div style="background-image: url('./imgs/b1.jpg'); float: left; width: 120px; height: 30px;"></div> -->
					<!-- <div style="background-image: url('./imgs/b2.jpg'); float: left; width: 120px; height: 30px;"></div> -->
					<div style="background-image: url('./imgs/b1.jpg'); float: left; width: 120px; height: 30px;"></div>
					<div style="background-image: url('./imgs/b2.jpg'); float: left; width: 120px; height: 30px;"></div>
				</div>

			</div>
		</div>

	</div>

</body>
</html>