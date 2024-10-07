<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>회원가입 완료</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f0f2f5;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 50%;
            margin: 50px auto;
            background-color: white;
            padding: 50px;
            border-radius: 10px;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        h1 {
            font-size: 28px;
            color: #333;
            margin-bottom: 10px;
        }
        p.subtitle {
            font-size: 18px;
            color: #666;
            margin-bottom: 40px;
        }
        .message-box {
            background-color: #f7f7f7;
            padding: 20px;
            margin-bottom: 30px;
            border-radius: 8px;
            border: 1px solid #e0e0e0;
        }
        .message-box h2 {
            font-size: 24px;
            margin-bottom: 10px;
        }
        .message-box p {
            font-size: 18px;
            color: #333;
        }
        .info-table {
            width: 60%; 
            margin: 0 auto;
            border-collapse: collapse;
            margin-bottom: 40px;
        }
        .info-table td {
            padding: 12px;
            border: 1px solid #ddd;
            font-size: 16px;
        }
        .info-table td:first-child {
            background-color: #f7f7f7;
            width: 40%;
            font-weight: bold;
        }
        .info-table td:last-child {
            width: 60%; 
        }
        .main-btn {
            display: inline-block;
            background-color: #4caff6;
            color: white;
            padding: 12px 30px;
            border-radius: 5px;
            text-decoration: none;
            font-size: 16px;
            transition: background-color 0.3s ease;
        }
        .main-btn:hover {
            background-color: #87bee585;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>회원가입</h1>
        <p class="subtitle">즐거움을 드리는 떠나자가 되겠습니다</p>

        <div class="message-box">
            <h2>떠나자 가입을 <strong>축하드립니다</strong></h2>
            <p>우리 고객님을 위해 다양한 국내여행 상품과 친절한 서비스로 언제나 즐거움을 드리는 떠나자가 되겠습니다</p>
        </div>

        <table class="info-table">
            <tr>
                <td>이름</td>
                <td><%= request.getParameter("name") %></td>
            </tr>
            <tr>
                <td>연락처</td>
                <td><%= request.getParameter("phone") %></td>
            </tr>
            <tr>
                <td>이메일</td>
                <td><%= request.getParameter("email") %></td>
            </tr>
        </table>

        <a href="/" class="main-btn">메인으로</a>
    </div>
</body>
</html>
