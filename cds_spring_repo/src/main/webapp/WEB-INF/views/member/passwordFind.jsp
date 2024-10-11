<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>비밀번호 찾기</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/passwordFind.css">
</head>
<body>
    <!-- 비밀번호 찾기 화면 -->
    <div class="log-container" id="pwFindScreen"> 
        <h1>비밀번호 찾기</h1>
        <p>회원로그인을 통해 더욱 다양한 서비스를 이용하세요.</p>
        <div class="log-line-divider"></div> 
        <h2>등록된 이메일로 비밀번호 찾기</h2>
        <p>회원정보에 등록된 이메일 주소와 입력한 이메일 주소가 같아야 비밀번호 찾기가 가능합니다.</p>
        <div class="log-input-group"> 
            <label for="username">아이디</label>
            <input type="text" id="username" placeholder="아이디를 입력해주세요." required>
        </div>
        <div class="log-input-group"> 
            <label for="name">이름</label>
            <input type="text" id="name" placeholder="이름을 입력해주세요." required>
        </div>
        <div class="log-input-group"> 
            <label for="email">이메일</label>
            <input type="email" id="email" placeholder="이메일을 입력해주세요." required>
        </div>
        <button class="log-btn" onclick="showPwResult()">비밀번호 찾기</button> 
    </div>

    <!-- 찾기 결과 화면 -->
    <div class="log-container" id="pwFindResultScreen" style="display:none;"> 
        <h2>비밀번호 찾기 결과</h2>
        <p>고객님의 정보와 일치하는 비밀번호입니다.</p>
        <div class="log-divider"></div> 
        <div class="log-result-container"> 
            <div class="log-user-id">****</div> 
            <a href="#">비밀번호 변경하기</a>
        </div>
        <div class="log-divider"></div> 
        <div class="log-btn-group"> 
            <button class="log-btn-small" onclick="location.href='login.jsp'">로그인</button> 
            <button class="log-btn-small primary" onclick="location.href='passwordChange.jsp'">비밀번호 변경</button> 
        </div>
    </div>

    <script>
        function showPwResult() {
            // 비밀번호 찾기 화면 숨기고 결과 화면 표시
            document.getElementById('pwFindScreen').style.display = 'none';
            document.getElementById('pwFindResultScreen').style.display = 'block';
        }
    </script>
</body>
</html>
