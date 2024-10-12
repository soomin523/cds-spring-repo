$(document).ready(function() {
    $('#signupForm').on('submit', function(e) {
        e.preventDefault(); // Prevent the default form submission

        $.ajax({
            type: 'POST',
            url: '/member/signup.do', // Your endpoint
            data: $(this).serialize(), // Serialize form data
            success: function(response) {
                if (response.success) {
                    alert('회원가입 성공');
                    window.location.href = '/login'; // Redirect to login or desired page
                } else {
                    alert('회원가입 실패: ' + response.message);
                }
            },
            error: function() {
                alert('서버 오류. 다시 시도해주세요.');
            }
        });
    });
});
