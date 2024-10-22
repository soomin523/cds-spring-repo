<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>커뮤니티 게시물 목록</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/member/communityList.css">
    <script src="${pageContext.request.contextPath}/resources/js/community.js"></script>
</head>
<body>
<!-- <header>
    <div class="commu-header">
        헤더 내용 추가
    </div>
</header> -->

<div class="container">
    <div class="post-list">
        <c:forEach var="community" items="${communityList}">
            <div class="post-item">
                <div class="post-image">
                    <img src="${community.imagePath}" alt="게시물 이미지" width="100" height="100">
                </div>
                <p>작성자: ${community.memberId}</p>
                <p>작성일: ${community.createdAt}</p>
                <div class="post-rating">
                    <span>⭐ ${community.rating}</span>
                </div>
                <div class="post-actions">
                    <span>👍 ${community.likes}</span>
                    <span>💬 ${community.comments}</span>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
