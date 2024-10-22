<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>마이페이지</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/mypagequery.css">
</head>

<body>
	<div class="section">
		<div class="background">
			<div class="myquery-title">
				<h2>문의하기</h2>
			</div>
			<hr>
			<div class="myquery-intro">
				<p>떠나자 사이트의 문의는 떠나자 카카오톡 채널을 이용해주세요.</p>
			</div>
			<hr>
			<div class="myquery-kakao">
				<img
					src="${pageContext.request.contextPath}/resources/img/카카오톡문의.png">
				<p>아래 버튼을 누르시면 카카오톡 채널로 이동합니다.</p>
				<a href="http://pf.kakao.com/_LcpIn"><button>떠나자 문의하기</button></a>
			</div>
			<div>
				<button class="homebt"
				onclick="location.href='${pageContext.request.contextPath}/mypage/mypagemain.do'"></button>
			</div>
		</div>
	</div>
</body>

</html>