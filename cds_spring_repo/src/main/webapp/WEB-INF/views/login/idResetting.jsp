<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>아이디 찾기 결과</title>
    <style>
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
        p {
            text-align: center;
            color: #777;
            font-size: 16px;
            margin-bottom: 30px;
        }
        .divider {
            width: 100%;
            height: 1px;
            background-color: #ddd;
            margin-top: 10px;
            margin-bottom: 30px;
        }
        .result-container {
            text-align: center;
        }
        .result-container h2 {
            font-size: 24px;
            color: #333;
            margin-bottom: 10px;
        }
        .result-container .user-id {
            font-size: 24px;
            font-weight: bold;
            margin-bottom: 10px;
        }
        .btn-group {
            display: flex;
            justify-content: space-between;
            margin-top: 20px;
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
    </style>
</head>
<body>

<div class="container">
    <h1>아이디 찾기 결과</h1>
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

</body>
</html>
