<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>아이디 찾기 결과</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/member/idReseting.css">
</head>
<body>

<div class="log-container"> 
    <h1>아이디 찾기 결과</h1>
    <p>고객님의 정보와 일치하는 아이디입니다.</p>
    <div class="log-divider"></div> 
    <div class="log-result-container"> 
        <div class="log-user-id">1234q</div> 
        <a href="#">일반회원</a>
    </div>
    <div class="log-divider"></div> 
    <div class="log-btn-group"> 
        <button class="log-btn-small" onclick="location.href='passwordFind.jsp'">비밀번호 찾기</button> 
        <button class="log-btn-small primary" onclick="location.href='login.jsp'">로그인</button> 
    </div>
</div>

</body>
</html>
