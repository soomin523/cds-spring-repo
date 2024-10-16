// 이메일 중복 검사
function checkEmail() {
    const emailInput = document.querySelector(".myinfo-email input");
    const email = emailInput.value.trim();
    if (email === "") {
        alert("이메일을 입력해주세요.");
        return;
    }

    // 서버에 이메일 중복 체크 요청
    // 실제로는 AJAX 요청을 사용해 데이터베이스를 확인해야 합니다.
    // 여기서는 예시로 email@example.com이 중복된 것으로 가정합니다.
    if (email === "email@example.com") {
        alert("이메일이 중복되었습니다.");
    } else {
        alert("이메일 중복검사를 통과했습니다.");
        document.querySelector(".myinfo-email button").textContent = "이메일 인증";
    }
}

// 입력 값 유효성 검사
function validateForm() {
    const password = document.querySelector("input[placeholder='비밀번호']").value;
    const passwordConfirm = document.querySelector("input[placeholder='비밀번호 재입력']").value;
    const phoneNumber = document.querySelector("input[placeholder='휴대폰 번호']").value;
    const birthDate = document.querySelector("input[placeholder='생년월일']").value;

    // 비밀번호 유효성 검사
    const passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
    if (!passwordPattern.test(password)) {
        alert("대소문자와 특수기호 포함 8자 이상으로 입력해주세요.");
        return false;
    }

    // 비밀번호 재입력 일치 여부 검사
    if (password !== passwordConfirm) {
        alert("입력한 비밀번호와 일치하지 않습니다.");
        return false;
    }

    // 휴대폰 번호 유효성 검사
    const phonePattern = /^[0-9]{10,}$/;
    if (!phonePattern.test(phoneNumber)) {
        alert("숫자만 입력 가능합니다.");
        return false;
    }

    // 생년월일 유효성 검사
    const birthPattern = /^\d{8}$/;
    if (!birthPattern.test(birthDate)) {
        alert("생년월일은 8자리로 입력하셔야 합니다 ex)20000101");
        return false;
    }

    // 모든 유효성 검사를 통과하면 true 반환
    return true;
}

// 저장하기 버튼 클릭 시 유효성 검사
function saveInfo() {
    if (validateForm()) {
        // 유효성 검사를 통과한 경우 mypagemain.do로 이동
        document.querySelector("form").action = "mypagemain.do";
        document.querySelector("form").submit();
    }
}

// 회원 탈퇴 버튼 클릭 시
function goToWithdrawalPage() {
    location.href = "mypagestatus.do";
}