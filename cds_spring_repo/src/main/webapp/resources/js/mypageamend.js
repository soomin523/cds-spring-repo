// 저장하기 버튼 클릭 시
$(function() {

	//저장하기 버튼 클릭 시 이벤트 처리
	$("#saveButton").on("click", function(){

	    // 유효성 검사 먼저 진행
	    //if (validateForm()) {
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
	            	
	            	console.log("실행결과: ",response.res);
	            
	                if (response.res === "SUCCESS") {
	                    alert("정보가 성공적으로 저장되었습니다.");
	                    location.href="../index.do";  // 저장 후 페이지를 새로 고침해서 변경된 정보 반영
	                } else {
	                    alert("정보 저장에 실패했습니다. 다시 시도해주세요.");
	                }
	            },
	            error: function() {
	                alert("서버 요청 중 오류가 발생했습니다.");
	            }
	        });//end of ajax
	    //}//end of if
    
    });
    
});

// 유효성 검사 함수는 기존의 validateForm을 그대로 사용
function validateForm() {
    const password = document.querySelector("input[placeholder='비밀번호']").value;
    const passwordConfirm = document.querySelector("input[placeholder='비밀번호 재입력']").value;
    const phoneNumber = document.querySelector("input[placeholder='휴대폰 번호']").value;
    const birthDate = document.querySelector("input[placeholder='생년월일']").value;

    const passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
    if (!passwordPattern.test(password)) {
        alert("대소문자와 특수기호 포함 8자 이상으로 입력해주세요.");
        return false;
    }

    if (password !== passwordConfirm) {
        alert("입력한 비밀번호와 일치하지 않습니다.");
        return false;
    }

    const phonePattern = /^[0-9]{10,}$/;
    if (!phonePattern.test(phoneNumber)) {
        alert("숫자만 입력 가능합니다.");
        return false;
    }

    const birthPattern = /^\d{8}$/;
    if (!birthPattern.test(birthDate)) {
        alert("생년월일은 8자리로 입력하셔야 합니다 ex)20000101");
        return false;
    }

    return true;
}
