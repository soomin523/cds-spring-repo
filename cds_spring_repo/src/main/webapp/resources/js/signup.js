$(document).ready(function() {

    // 회원가입 처리
    $('#signupForm').on('submit', function(e) {
        e.preventDefault(); // 기본 폼 제출 방지

        $.ajax({
            type: 'POST',
            url: '/member/signupProcess.do', // 서버에서 처리하는 경로
            data: $(this).serialize(), // 폼 데이터를 직렬화
            success: function(response) {
                if (response.trim() == "ok") {
                    alert('회원가입 성공');
                    window.location.href = '/index.do'; // 로그인 페이지로 리다이렉트
                } else {
                    alert('회원가입 실패: ' + response.trim());
                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
            	console.error("AJAX 오류:", textStatus, errorThrown);
                alert('서버 오류. 다시 시도해주세요.');
            }
        });
    });


// 아이디 중복 검사
$("#check-btn").on("click", function() {
    const member_id = $("#member_id").val();
    
    if (member_id.length === 0) {
        alert("아이디가 입력되지 않았습니다.");
        $("#member_id").focus();
    }else{ 
	    $.ajax({
	        type: "post",
	        url: "checkDuplicate",  // 서버로 보낼 URL
	        data: { member_id: member_id },
	        success: function(resData) {
			    console.log(resData); // 응답값 확인
			    console.log(typeof resData);
			    //const msg = $("#resultMsg");
			    //msg.show();
			   
			    if (resData.trim() == "ok") {  // 중복이 아니라면
			        //msg.html("사용 가능한 아이디입니다.").css("color", "green");
			        alert('사용 가능한 아이디입니다.');
			
			    } else {  // 중복인 경우
			        //msg.html("이미 사용중인 아이디입니다.").css("color", "red");
			        alert('이미 사용 중인 아이디입니다.');
			        $("#member_id").val("").trigger("focus");
			    }
			},
	
	            error: function(error) {
	                console.log("아이디 중복 검사 중 에러 발생:", error);
	            }
	        });//end of ajax
       }
	
});

// 이메일 인증
$("#sendAuthCodeBtn").on("click", function() {
    console.log("인증번호 전송 버튼이 클릭되었습니다.");  // 버튼 클릭 여부 확인
    const emailPrefix = $("#email_prefix").val();
    const emailDomain = $("#email_domain").val();
    let email = emailPrefix + emailDomain;

    if (emailDomain === "direct") {
        const customDomain = $("#custom_email_domain").val();
        if (!customDomain) {
            alert("도메인을 입력하세요.");
            return;
        }
        email = emailPrefix + customDomain; // 사용자 정의 도메인 사용
    }

    if (!emailPrefix || !emailDomain) {
        alert("이메일을 입력하세요.");
        return;
    }

    // AJAX로 이메일 존재 여부를 체크
    $.ajax({
        type: "POST",
        url: "checkEmailAvailability.do", // 이메일 중복 체크 엔드포인트
        data: { email: email },
        success: function(data) {
            if (data.trim() == "ok") { // 이메일 사용 가능
                // 인증 번호 전송
                sendAuthCode(email);
            } else { // 이미 존재하는 이메일
                alert("이미 등록된 이메일입니다. 다른 이메일을 사용하세요.");
            }
        },
        error: function(e) {
            console.log("AJAX 요청 에러:", e);
            alert("서버 통신 중 문제가 발생했습니다.");
        }
    });
});

// 인증번호 전송 함수
function sendAuthCode(email) {
    $.ajax({
        type: "POST",
        url: "checkEmail.do",
        data: { email: email },
        success: function(data) {
            if (data !== "ERROR") {
                alert("인증번호가 전송되었습니다.");
                $("#auth_num_input").attr("disabled", false);
                $("#confirm_email_btn").attr("disabled", false);
                $("#confirm_email_btn").val(data);
            } else {
                alert("인증번호 전송 중 오류가 발생했습니다.");
            }
        },
        error: function(e) {
            console.log("AJAX 요청 에러:", e);
            alert("서버 통신 중 문제가 발생했습니다.");
        }
    });
}


// 인증 확인 버튼 클릭 이벤트 핸들러 추가
$("#confirm_email_btn").on("click", function() {
    const enteredCode = $("#auth_num_input").val(); // 사용자가 입력한 인증 코드
    const authcode = $("#confirm_email_btn").val();
 
    if (!enteredCode) {
        alert("인증 코드를 입력해주세요.");
        return;
    }

    // AJAX로 인증 코드 확인 요청
    $.ajax({
        type: "POST",
        url: "verifyEmailCode.do",  // 서버에 인증 코드 확인 요청
        data: {
            enteredCode: enteredCode,
            authcode: authcode
        },
        success: function(response) {
            console.log("서버 응답 데이터:", response);  // 서버 응답 로그
            if (response === "ok") {
                alert("인증이 완료되었습니다.");
                $("#result_confirm").val("true"); // 인증 성공 시 결과 저장
                $("#resultEmail").html("인증이 완료되었습니다."); // 결과 표시
            } else {
                alert("인증 코드가 일치하지 않습니다.");
            }
        },
        error: function(e) {
            console.log("AJAX 요청 에러:", e);
            alert("서버 통신 중 문제가 발생했습니다.");
        }
    });
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
