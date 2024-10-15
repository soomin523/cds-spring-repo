$(document).ready(function() {

    // 회원가입 처리
    $('#signupForm').on('submit', function(e) {
        e.preventDefault(); // 기본 폼 제출 방지

        $.ajax({
            type: 'POST',
            url: '/member/signup.do', // 서버에서 처리하는 경로
            data: $(this).serialize(), // 폼 데이터를 직렬화
            success: function(response) {
                if (response.success) {
                    alert('회원가입 성공');
                    window.location.href = '/login'; // 로그인 페이지로 리다이렉트
                } else {
                    alert('회원가입 실패: ' + response.message);
                }
            },
            error: function() {
                alert('서버 오류. 다시 시도해주세요.');
            }
        });
    });

    // 아이디 중복 검사
   // 아이디 중복 검사
$("#checkId").on("click", function() {
    const member_id = $("#member_id").val();

    if (member_id.length === 0) {
        alert("아이디가 입력되지 않았습니다.");
        $("#member_id").focus();
    } else {
        $.ajax({
            type: "post",
            url: "/member/checkId.do",  // 서버로 보낼 URL
            data: { "member_id": member_id },
            success: function(resData) {
                console.log(resData); // 응답값 확인
                const msg = $("#resultMsg");
                msg.show();

                if (resData.trim() === "PASS") {
                    msg.html("사용 가능한 아이디입니다.").css("color", "green");

                    // 중복 검사 버튼을 메일 인증 버튼으로 변경
                    const checkIdBtn = $("#checkId");
                    checkIdBtn.off("click").val("메일 인증").attr("id", "email_auth_btn").on("click", function() {
                        msg.hide();
                        sendEmail();  // 이메일 인증 함수 호출
                    });

                } else {
                    msg.html("이미 사용중인 아이디입니다.").css("color", "red");
                    $("#member_id").val("").trigger("focus");
                }
            },
            error: function(error) {
                console.log("아이디 중복 검사 중 에러 발생:", error);
            }
        });
    }
});

// 이메일 인증 함수
function sendEmail() {
    const email = $("#member_id").val();

    $.ajax({
        type: "post",
        url: "/member/checkEmail.do",
        data: { email: email },
        success: function(data) {
            $("#auth_num_input").attr("disabled", false);  // 인증번호 입력란 활성화
            code = data.trim();  // 서버에서 받은 인증번호 저장
            alert("인증번호가 전송되었습니다.");
        },
        error: function(e) {
            console.log("인증번호 받기 중 에러 발생:", e);
        }
    });
}

    // 인증번호 확인
    $("#confirm_email_btn").on("click", function() {
        const inputCode = $("#auth_num_input").val();
        const resultMsg = $("#resultEmail");
        resultMsg.show();

        if (inputCode === code) {  // 입력된 인증번호가 서버에서 받은 인증번호와 일치하는지 확인
            resultMsg.html("정상적으로 인증되었습니다").css("color", "green");
            $("#result_confirm").val("PASS");  // 인증 성공
        } else {
            resultMsg.html("인증번호가 일치하지 않습니다. 다시 확인해 주세요").css("color", "red");
            $("#result_confirm").val("FAIL");  // 인증 실패
        }
    });

    // 입력 커서가 비밀번호 입력란에 놓이면 메시지 숨기기
    $("#member_pw").on("focus", function() {
        const msg = $("#resultMsg");
        const resultMsg = $("#resultEmail");
        if (msg.text() === "사용 가능한 아이디입니다." && resultMsg.text() === "정상적으로 인증되었습니다.") {
            msg.hide();
            resultMsg.hide();

            // 인증번호 초기화 및 입력란 비활성화
            $("#auth_num_input").val("").attr("disabled", true);
            $("#confirm_email_btn").attr("disabled", true);
        }
    });
});
