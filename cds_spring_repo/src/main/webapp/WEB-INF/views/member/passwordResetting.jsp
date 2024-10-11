<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>비밀번호 재설정</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/passwordResetting.css">
</head>
<body>

<!-- 비밀번호 재설정 완료 화면 -->
<div class="log-container">
    <h2>비밀번호 재설정</h2>
    <p>이메일 발송이 완료되었습니다.</p>

    <div class="log-divider"></div>

    <div class="log-user-info">
        1234q 회원님
    </div>

    <p>회원님의 임시비밀번호가 1234@naver.com 으로 발송되었습니다<br>로그인 후 마이페이지에서 변경 해 주세요</p>

    <div class="log-divider"></div>

    <button class="log-btn" onclick="location.href='login.jsp'">로그인</button>
</div>

</body>
</html>
