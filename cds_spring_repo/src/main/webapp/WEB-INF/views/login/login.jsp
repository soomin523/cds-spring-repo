<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인 모달</title>
    <style>
        /* 기본 페이지 스타일 */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f9f9f9;
        }

        /* 로그인 버튼 */
        .login-btn {
            position: absolute;
            top: 20px;
            right: 20px;
            background-color: transparent;
            border: none;
            cursor: pointer;
        }

        .login-btn img {
            width: 40px;
            height: 40px;
        }

        /* 모달 스타일 */
        .modal {
            display: none;
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.6);
        }

        .modal-content {
            background-color: white;
            padding: 30px;
            border-radius: 15px;
            width: 400px;
            text-align: center;
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            display: flex;
            flex-direction: column;
            justify-content: center;
        }

        .close {
            position: absolute;
            right: 15px;
            top: 15px;
            font-size: 24px;
            font-weight: bold;
            cursor: pointer;
        }

        .close:hover {
            color: red;
        }

        h2 {
            font-size: 24px;
            margin-top: 60px; /* 로고 아래에 여백 추가 */
            margin-bottom: 20px;
        }

        /* 로그인 모달 로고 왼쪽 상단 고정 */
        .logo {
            width: 100px;
            position: absolute;
            top: 15px;
            left: 15px;
        }

        /* 로그인 버튼 스타일 */
        .login-options button, .login-form button, .back-button {
            padding: 10px;
            margin: 10px 0;
            width: 100%;
            background-color: #62b2eb;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }

        .login-options button:hover, .login-form button:hover, .back-button:hover {
            background-color: #87bee585;
        }

        /* 카카오 로그인 버튼 스타일 */
        .kakao-login {
            background-color: #fee500 !important; /* 노란색 */
            color: #3c1e1e !important;
            border: none !important;
            padding: 10px !important;
            margin: 10px 0 !important;
            width: 100% !important;
            font-size: 16px !important;
            cursor: pointer !important;
            border-radius: 5px !important;
        }

        /* 네이버 로그인 버튼 스타일 */
        .naver-login {
            background-color: #03c75a !important; /* 연두색 */
            color: white !important;
            border: none !important;
            padding: 10px !important;
            margin: 10px 0 !important;
            width: 100% !important;
            font-size: 16px !important;
            cursor: pointer !important;
            border-radius: 5px !important;
        }

        /* 폼 입력 스타일 */
        .login-form input[type="text"], .login-form input[type="password"] {
            width: 100%;
            padding: 12px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }

        .login-description {
            font-size: 14px;
            margin-bottom: 20px;
            color: #333;
        }

        .footer-links a {
            font-size: 12px;
            color: #555;
            text-decoration: none;
            margin: 0 10px;
        }

        .footer-links a:hover {
            text-decoration: underline;
        }

        /* 비밀번호 표시 아이콘 */
        .input-container {
            position: relative;
        }

        .input-container input[type="password"] {
            width: 100%;
            padding-right: 40px;
        }

        .input-container img {
            position: absolute;
            right: 10px;
            top: 50%;
            transform: translateY(-50%);
            width: 24px;
            height: 24px;
            cursor: pointer;
        }

        /* 소셜 로그인 버튼에 포함된 이미지 스타일 */
        .social-icons img {
            width: 20px;
            height: 20px;
            vertical-align: middle;
            margin-right: 10px;
        }

        /* 일반 로그인 폼 숨김 */
        .login-form {
            display: none;
        }

        /* '또는' 선 스타일 */
        .divider {
            display: flex;
            align-items: center;
            text-align: center;
            margin: 20px 0;
        }

        .divider hr {
            flex: 1;
            border: none;
            border-top: 1px solid #ccc;
            margin: 0 10px;
        }

        .divider span {
            font-size: 12px;
            color: #999;
        }

        /* 여백 조정 */
        .footer-links {
            margin-top: 10px; /* 로그인 버튼과의 간격 */
            font-size: 12px;
            display: flex;
            justify-content: center;
        }

        .modal-content {
            padding-bottom: 30px; /* 하단 여백 추가 */
        }

    </style>
</head>
<body>

<!-- 사람 모양 아이콘 로그인 버튼 -->
    <button class="login-btn" onclick="openModal()">
        <img src="프로필.png" alt="프로필 아이콘">
    </button>

<!-- 로그인 모달 -->
<div id="loginModal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeModal()">&times;</span>

        <!-- 로그인 선택 화면 -->
        <div class="login-options">
            <img src="떠나자logo.png" alt="떠나자 로고" class="logo"> <!-- 로고를 왼쪽 상단에 고정 -->
            <h2>로그인</h2>
            <p class="login-description">떠나자 회원으로 가입하시면 회원전용 혜택 제공, 이벤트 등 더 많은 서비스를 이용하실 수 있습니다.</p>
            <button onclick="showLoginForm()">일반 로그인</button>

            <!-- '또는'을 선으로 구분 -->
            <div class="divider">
                <hr>
                <span>또는</span>
                <hr>
            </div>

            <!-- 소셜 로그인 버튼들 -->
            <button class="kakao-login">
                <div class="social-icons">
                    <img src="kakao-icon.png" alt="카카오 아이콘">카카오로 시작하기
                </div>
            </button>
            <button class="naver-login">
                <div class="social-icons">
                    <img src="naver-icon.png" alt="네이버 아이콘">네이버로 시작하기
                </div>
            </button>
            <div class="footer-links">
                <a href="idFind.jsp">아이디 찾기</a> | <!-- 변경된 부분 -->
                <a href="passwordFind.jsp">비밀번호 찾기</a> |
                <a href="#" onclick="redirectToSignup()">회원가입</a>
            </div>
        </div>

        <!-- 일반 로그인 화면 -->
        <div class="login-form">
            <img src="떠나자logo.png" alt="떠나자 로고" class="logo"> <!-- 일반 로그인 화면에도 로고 고정 -->
            <h2>일반 로그인</h2>
            <p class="login-description">떠나자 회원으로 가입하시면 회원전용 혜택 제공, 이벤트 등 더 많은 서비스를 이용하실 수 있습니다.</p>
            
            <!-- 이메일/아이디 입력 -->
            <input type="text" id="emailInput" placeholder="이메일 주소 혹은 아이디">

            <!-- 비밀번호 입력 -->
            <div class="input-container">
                <input type="password" id="passwordInput" placeholder="비밀번호 확인">
                <img src="비밀번호표시.png" alt="비밀번호 보기" onclick="togglePassword('passwordInput')">
            </div>
            
            <button>로그인</button>
            <!-- 이전 페이지 버튼 추가 -->
            <button class="back-button" onclick="goBack()">이전 페이지</button>
            
            <div class="footer-links">
                <a href="idFind.jsp">아이디 찾기</a> |
                <a href="passwordFind.jsp">비밀번호 찾기</a> |
                <a href="#" onclick="redirectToSignup()">회원가입</a>
            </div>
        </div>
    </div>
</div>

<script>
    // 모달 열기 함수
    function openModal() {
        document.getElementById('loginModal').style.display = 'block';
        resetModal(); // 모달 열 때 항상 첫 화면으로 초기화
    }

    // 모달 닫기 함수
    function closeModal() {
        document.getElementById('loginModal').style.display = 'none';
    }

    // 로그인 선택 화면으로 돌아가기
    function goBack() {
        document.querySelector('.login-form').style.display = 'none';
        document.querySelector('.login-options').style.display = 'block';
    }

    // 일반 로그인 화면 보여주기
    function showLoginForm() {
        document.querySelector('.login-options').style.display = 'none';
        document.querySelector('.login-form').style.display = 'block';
    }

    // 비밀번호 표시/숨기기 기능
    function togglePassword(id) {
        const passwordField = document.getElementById(id);
        if (passwordField.type === "password") {
            passwordField.type = "text"; // 비밀번호를 보이게 변경
        } else {
            passwordField.type = "password"; // 비밀번호를 숨김
        }
    }

    // 모달 초기화 (로그인 선택 화면으로 복귀 및 입력 필드 리셋)
    function resetModal() {
        document.querySelector('.login-form').style.display = 'none';
        document.querySelector('.login-options').style.display = 'block';
        // 입력 필드 리셋
        document.getElementById('emailInput').value = '';
        document.getElementById('passwordInput').value = '';
    }

    // 모달 외부 클릭 시 모달 닫기
    window.onclick = function(event) {
        if (event.target == document.getElementById('loginModal')) {
            closeModal();
        }
    }

    // 회원가입 페이지로 리다이렉트하는 함수
    function redirectToSignup() {
        window.location.href = "singup.jsp";
    }

    // 아이디 찾기 페이지로 리다이렉트하는 함수
    function redirectToIdFind() {
        window.location.href = "idFind.jsp";
    }
</script>

</body>
</html>
