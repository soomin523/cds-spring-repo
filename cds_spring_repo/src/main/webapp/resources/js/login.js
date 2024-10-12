$(document).ready(function() {
    $('#loginButton').on('click', function(e) {
        e.preventDefault(); // 폼 기본 제출 방지
        
        var member_id = $('#log-emailInput').val().trim();
        var password = $('#log-passwordInput').val();
        
        if (!member_id || !password) {
            alert('이메일/아이디와 비밀번호를 입력해주세요.');
            return; // 유효성 검사에 실패하면 AJAX 요청을 보내지 않음
        }
        $.ajax({
            type: 'POST',
            url: '/web/member/login.do', // 실제 서버 로그인 엔드포인트
            data: {
                member_id: member_id,
                password: password
            },
            dataType: 'json',
            success: function(response) {
                if (response.success) {
                    alert('로그인 성공');
                    window.location.href = '/'; // 로그인 성공 시 리다이렉트
                } else {
                    alert('로그인 실패: ' + (response.message || '알 수 없는 오류'));
                }
            },
            error: function(xhr, status, error) {
                console.error('Error:', error);
                alert('서버 오류. 다시 시도해주세요.');
            }
        });
    });
});