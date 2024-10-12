<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>회원 정보 수정</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/member/update.css">
</head>
<body>
    <h2>회원 정보 수정</h2>
    <form action="${pageContext.request.contextPath}/member/update" method="post">
        <!-- 숨겨진 회원 ID 필드 -->
        <input type="hidden" name="id" value="${member.id}" />

        <!-- 이름 입력 -->
        <label for="name">이름:</label>
        <input type="text" name="name" value="${member.name}" required/><br/>

        <!-- 이메일 입력 -->
        <label for="email">이메일:</label>
        <input type="email" name="email" value="${member.email}" required/><br/>

        <!-- 전화번호 입력 -->
        <label for="phone">전화번호:</label>
        <input type="text" name="phone" value="${member.phone}" required/><br/>

        <!-- 비밀번호 입력 -->
        <label for="password">비밀번호:</label>
        <input type="password" name="password" value="${member.password}" required/><br/>

        <!-- 성별 입력 -->
        <label for="gender">성별:</label>
        <select name="gender">
            <option value="M" ${member.gender == 'M' ? 'selected' : ''}>남성</option>
            <option value="F" ${member.gender == 'F' ? 'selected' : ''}>여성</option>
        </select><br/>

        <!-- 마케팅 수신 동의 여부 -->
        <label for="marketingConsent">마케팅 수신 동의:</label>
        <input type="checkbox" name="marketingConsent" ${member.marketingConsent ? 'checked' : ''}/><br/>

        <!-- 프로필 이미지 -->
        <label for="profileImage">프로필 이미지:</label>
        <input type="text" name="profileImage" value="${member.profileImage}" /><br/>

        <!-- 제출 버튼 -->
        <input type="submit" value="정보 수정" />
    </form>
</body>
</html>
