<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>마이페이지- 내정보수정란</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/mypageamend.css">
    <script src="../resources/js/jquery-3.7.1.min.js"></script>
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
             <input type="text" name="member_id"  value="${member.member_id}" readonly>
        </div>
		
        <form action="amendUpdateProcess" method="post">
            <div class="myinfo-amend">
            <input type="hidden" name="m_id"  value="${member.m_id}">
                <h2>회원 정보</h2>
                <p>이름</p>
                <input type="text" name="name" value="${member.name}" readonly>
                <p>이메일</p>
                <input type="text" name="email" value="${member.email}" readonly>
                <p>비밀번호</p>
                <input type="password" name="password"  value="${member.password}">
                <p>비밀번호 재입력</p>
                <input type="password" placeholder="비밀번호 재입력" >
                <p>휴대폰 번호</p>
                <input type="text" name="phone"  value="${member.phone}">
                <p>생년월일</p>
                <input type="text" name="birth_date"  placeholder="생년월일" value="${member.birth_date}">

                <button type="button" class="info-status" onclick="location.href='http://localhost:9090/cds/mypage/mypagestatus.do'">회원탈퇴</button>
                <button type="submit" class="info-save" id="saveInfo()">저장하기</button>
            </div>
        </form>
    </div>

</body>

</html>