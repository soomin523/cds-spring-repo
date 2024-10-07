<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>비밀번호 재설정</title>
    <style>
        /* 기본 페이지 스타일 */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f9f9f9;
        }

        .container {
            width: 400px;
            margin: 0 auto;
            background-color: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        h2 {
            font-size: 24px;
            margin-bottom: 20px;
        }

        p {
            color: #777;
            margin-bottom: 30px;
        }

        .user-info {
            font-size: 20px;
            font-weight: bold;
            margin-bottom: 20px;
        }

        .btn {
            width: 100%;
            padding: 12px;
            background-color: #4caff6;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            margin-top: 20px;
        }

        .btn:hover {
            background-color: #87bee585(86, 167, 254, 0.429)85;
        }

        .divider {
            margin: 20px 0;
            height: 1px;
            background-color: #ddd;
        }

    </style>
</head>
<body>

<!-- 비밀번호 재설정 완료 화면 -->
<div class="container">
    <h2>비밀번호 재설정</h2>
    <p>이메일 발송이 완료되었습니다.</p>

    <div class="divider"></div>

    <div class="user-info">
        1234q 회원님
    </div>

    <p>회원님의 임시비밀번호가 1234@naver.com 으로 발송되었습니다<br>로그인 후 마이페이지에서 변경 해 주세요</p>

    <div class="divider"></div>

    <button class="btn" onclick="location.href='login.jsp'">로그인</button>
</div>

</body>
</html>
