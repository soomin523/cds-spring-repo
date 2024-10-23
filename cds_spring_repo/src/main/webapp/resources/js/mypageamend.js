$(function() {
    // 저장하기 버튼 클릭 시 이벤트 처리
    $("#saveButton").on("click", function() {

        // 유효성 검사 먼저 진행
        if (validateForm()) {
            const m_id = amendForm.m_id.value;
            const name = amendForm.name.value;
            const email = amendForm.email.value;
            const password = amendForm.password.value;
            const phone = amendForm.phone.value;
            const birth_date = amendForm.birth_date.value;

            const member = {
                m_id: m_id,
                name: name, 
                email: email, 
                password: password, 
                phone: phone, 
                birth_date: birth_date 
            };

            // AJAX 요청 보내기
            $.ajax({
                url: "amendUpdateProcess",  // 정보를 처리할 서버의 URL
                type: "POST",
                data: member, 
                dataType: "json",
                success: function(response) {
                    console.log("실행결과: ", response.res);
                
                    if (response.res === "SUCCESS") {
                        alert("정보가 성공적으로 저장되었습니다.");
                        location.href = "../index.do";  // 저장 후 페이지 이동
                    } else {
                        alert("정보 저장에 실패했습니다. 다시 시도해주세요.");
                    }
                },
                error: function() {
                    alert("서버 요청 중 오류가 발생했습니다.");
                }
            }); // end of ajax
        } // end of if

    });
});

// 유효성 검사 함수
function validateForm() {
    const password = document.querySelector("input[name='password']").value;
    const passwordConfirm = document.querySelector("input[placeholder='비밀번호 재입력']").value;
    const phoneNumber = document.querySelector("input[name='phone']").value;
    const birthDate = document.querySelector("input[name='birth_date']").value;

    // 비밀번호 유효성 검사 (대소문자, 숫자, 특수기호 포함 8자 이상)
    const passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
    if (!passwordPattern.test(password)) {
        alert("비밀번호는 대소문자, 숫자, 특수기호 포함 8자 이상이어야 합니다.");
        return false;
    }

    // 비밀번호 확인 검사
    if (password !== passwordConfirm) {
        alert("입력한 비밀번호와 일치하지 않습니다.");
        return false;
    }

    // 휴대폰 번호 유효성 검사 (숫자만 입력)
    const phonePattern = /^[0-9]{10,11}$/;
    if (!phonePattern.test(phoneNumber)) {
        alert("휴대폰 번호는 10자리 또는 11자리의 숫자만 입력 가능합니다.");
        return false;
    }

    // 생년월일 유효성 검사 (8자리 숫자)
    const birthPattern = /^\d{4}-\d{2}-\d{2}$/;
    if (!birthPattern.test(birthDate)) {
        alert("생년월일은 yyyy-mm-dd 형식으로 입력해야 합니다.");
        return false;
    }

    return true; // 모든 유효성 검사를 통과하면 true 반환
}
