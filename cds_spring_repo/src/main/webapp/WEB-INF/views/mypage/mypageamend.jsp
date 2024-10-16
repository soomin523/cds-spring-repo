<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>마이페이지- 내정보수정란</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/mypageamend.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/mypageamend.js"></script>
</head>

<body>
   <div class="background">
        <div class="myinfo-title">
            <h2>내정보</h2>
        </div>
        <div class="myinfo-logininfo">
            <h2>로그인 정보</h2>
            <p>아이디</p>
            <input type="text" placeholder="*아이디" value="${member.mamber_id}" readonly>
        </div>

        <form onsubmit="return false;">
            <div class="myinfo-amend">
                <h2>회원 정보</h2>
                <p>이름</p>
                <input type="text" placeholder="이름" value="${member.name}">
                <p>이메일</p>
                <div class="myinfo-email">
                    <input type="text" placeholder="이메일">
                    <button type="button" onclick="checkEmail()">중복검사</button>
                </div>
                <p>비밀번호</p>
                <input type="password" placeholder="비밀번호" value="${member.password}">
                <p>비밀번호 재입력</p>
                <input type="password" placeholder="비밀번호 재입력">
                <p>휴대폰 번호</p>
                <input type="text" placeholder="휴대폰 번호" value="${member.phone}">
                <p>생년월일</p>
                <input type="text" placeholder="생년월일" value="${member.birth_date}">

                <button type="button" class="info-status" onclick="goToWithdrawalPage()">회원탈퇴</button>
                <button type="button" class="info-save" onclick="saveInfo()">저장하기</button>
            </div>
        </form>
    </div>

</body>

</html>