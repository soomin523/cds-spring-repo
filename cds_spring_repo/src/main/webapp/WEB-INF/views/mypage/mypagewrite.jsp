<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>작성내역</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/mypagewrite.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/mypagewrite.js"></script>
</head>

<body>
	<div class="section">
		<div class="background">
			<div class="mywrite-title">
				<h2>작성내역</h2>
			</div>
			<div class="mywrite-category">
				<button id="redirectButton_post">게시글</button>
				<button id="redirectButton_comment">댓글</button>
			</div>
			<hr>
			<!-- 게시글 작성 내역이 없을 때 -->
			<div class="mywrite-content-none">
				<p>등록된 게시글이 없습니다</p>
			</div>

			<!-- 댓글 작성 내역이 있을 때 -->
			<div class="mywrite-comment-exist">
				<div id="comment-list">
					<!-- 댓글 리스트가 이곳에 추가됩니다 -->
				</div>
			</div>

			<!-- 게시글 작성 내역이 있을 때 -->
			<div class="mywrite-content-exist">
				<p>커뮤게시글 제목</p>
				<p>날짜</p>
			</div>
			<hr>
		<div><button class="homebt" onclick="location.href='${pageContext.request.contextPath}/mypage/mypagemain.do'"></button></div>
		</div>
	</div>
</body>

</html>
