<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>비밀번호 변경</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/member/passwordChange.css">
</head>
<body>
    <div class="container">
        <h1>비밀번호 변경</h1>
        <div class="input-group">
            <label for="current-password">현재 비밀번호</label>
            <input type="password" id="current-password" required>
        </div>
        <div class="input-group">
            <label for="new-password">새 비밀번호</label>
            <input type="password" id="new-password" required>
        </div>
        <button onclick="changePassword()">비밀번호 변경</button>
        <p id="message"></p>
    </div>

     <script>
        function changePassword() {
            const currentPassword = document.getElementById('current-password').value;
            const newPassword = document.getElementById('new-password').value;

            $.ajax({
                type: 'POST',
                url: '/changePassword', // 서버의 엔드포인트
                data: {
                    currentPassword: currentPassword,
                    newPassword: newPassword
                },
                success: function(response) {
                    // 서버에서 비밀번호 변경 성공 시 메시지 표시
                    if (response.success) {
                        document.getElementById('message').textContent = '비밀번호가 변경되었습니다.';
                        alert('비밀번호가 변경되었습니다.'); // 알림 창 추가
                    } else {
                        document.getElementById('message').textContent = response.message; // 서버에서 반환한 메시지
                    }
                },
                error: function(error) {
                    console.error('Error:', error);
                    document.getElementById('message').textContent = '비밀번호 변경에 실패했습니다.'; // 기본 오류 메시지
                }
            });
        }
    </script>
</body>
</html>
