<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>아이디 찾기</title>
    <style>
        /* 기본 페이지 스타일 */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f9f9f9;
        }
        .container {
            width: 500px;
            margin: 50px auto;
            background-color: #ffffff;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.1);
        }
        h1 {
            font-size: 28px;
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }
        h2 {
            font-size: 20px;
            text-align: center;
            margin-bottom: 10px;
            color: #62b2eb;
        }
        p {
            text-align: center;
            color: #777;
            font-size: 16px;
            margin-bottom: 30px;
        }
        .input-group {
            margin-bottom: 15px;
        }
        .input-group label {
            display: block;
            margin-bottom: 5px;
            color: #333;
        }
        .input-group input[type="text"],
        .input-group input[type="email"] {
            width: 100%;
            padding: 12px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }
        .btn {
            width: 60%;
            padding: 12px;
            background-color: #62b2eb;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            margin: 20px auto 0;
            display: block;
        }
        .btn:hover {
            background-color: #87bee585;
        }
        .result-container {
            text-align: center;
        }
        .result-container h2 {
            margin-bottom: 20px;
            color: #333;
        }
        .result-container .user-id {
            font-size: 24px;
            font-weight: bold;
            margin-bottom: 20px;
        }
        .btn-group {
            display: flex;
            justify-content: space-between;
            margin-top: 30px;
        }
        .btn-small {
            width: 48%;
            padding: 12px;
            background-color: #f1f1f1;
            border: 1px solid #ccc;
            border-radius: 5px;
            cursor: pointer;
            text-align: center;
            font-size: 16px;
        }
        .btn-small.primary {
            background-color: #62b2eb;
            color: white;
        }
        .btn-small:hover {
            background-color: #ddd;
        }
        .btn-small.primary:hover {
            background-color: #87bee585;
        }
        .divider {
            margin: 20px 0;
            height: 1px;
            background-color: #ddd;
        }
        /* 추가된 선 스타일 */
        .line-divider {
            width: 100%;
            height: 1px;
            background-color: #ddd;
            margin-top: 10px;
            margin-bottom: 30px;
        }
    </style>
</head>
<body>
    <!-- 아이디 찾기 화면 -->
    <div class="container" id="idFindScreen">
        <h1>아이디 찾기</h1>
        <p>회원로그인을 통해 더욱 다양한 서비스를 이용하세요.</p>
        <div class="line-divider"></div>
        <h2>등록된 이메일로 아이디 찾기</h2>
        <p>회원정보에 등록된 이메일 주소와 입력한 이메일 주소가 같아야 아이디 찾기가 가능합니다.</p>
        <div class="input-group">
            <label for="name">이름</label>
            <input type="text" id="name" placeholder="이름을 입력해주세요." required>
        </div>
        <div class="input-group">
            <label for="email">이메일</label>
            <input type="email" id="email" placeholder="이메일을 입력해주세요." required>
        </div>
        <button class="btn" onclick="showResult()">아이디 찾기</button>
    </div>

    <!-- 찾기 결과 화면 -->
    <div class="container" id="idFindResultScreen" style="display:none;">
        <h2>아이디 찾기 결과</h2>
        <p>고객님의 정보와 일치하는 아이디입니다.</p>
        <div class="divider"></div>
        <div class="result-container">
            <div class="user-id">1234q</div>
            <a href="#">일반회원</a>
        </div>
        <div class="divider"></div>
        <div class="btn-group">
            <button class="btn-small" onclick="location.href='passwordFind.jsp'">비밀번호 찾기</button>
            <button class="btn-small primary" onclick="location.href='login.jsp'">로그인</button>
        </div>
    </div>

    <script>
        function showResult() {
            // 아이디 찾기 화면 숨기고 결과 화면 표시
            document.getElementById('idFindScreen').style.display = 'none';
            document.getElementById('idFindResultScreen').style.display = 'block';
        }
    </script>
</body>
</html>
