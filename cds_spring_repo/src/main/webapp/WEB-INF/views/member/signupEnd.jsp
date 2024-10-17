<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>회원가입 완료</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/member/signupEnd.css">
</head>
<body>
    <div class="log-container"> <!-- class 이름에 log- 접두사 추가 -->
        <h1>회원가입</h1>
        <p class="subtitle">즐거움을 드리는 떠나자가 되겠습니다</p>

        <div class="log-message-box"> <!-- class 이름에 log- 접두사 추가 -->
            <h2>떠나자 가입을 <strong>축하드립니다</strong></h2>
            <p>우리 고객님을 위해 다양한 국내여행 상품과 친절한 서비스로 언제나 즐거움을 드리는 떠나자가 되겠습니다</p>
        </div>

        <table class="info-table">
            <tr>
                <td>이름</td>
                <td>${ joinMember.name }</td>
                <%-- <td><%= request.getParameter("name") %></td> --%>
            </tr>
            <tr>
                <td>연락처</td>
                <td>${ joinMember.phone }</td>
                <%-- <td><%= request.getParameter("phone") %></td> --%>
            </tr>
            <tr>
                <td>이메일</td>
                <td>${ joinMember.email }</td>
            </tr>
        </table>

        <a href="../index.do" class="log-main-btn">메인으로</a> <!-- class 이름에 log- 접두사 추가 -->
    </div>
</body>
</html>
