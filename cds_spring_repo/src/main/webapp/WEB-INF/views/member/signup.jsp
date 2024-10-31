<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>회원가입</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/member/signup.css">
    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/signup.js"></script>
    <style>
        /* 여기에 CSS를 추가할 수 있습니다. */
        /* 기존 CSS는 그대로 유지하되, 추가 CSS는 여기에 작성합니다. */
    </style>
</head>
<body>
    <section>
        <div class="container">
            <h2>회원가입</h2>
            <form method="post" action="signupProcess.do">
                <table>
                    <tr>
                        <td>이름</td>
                        <td><input type="text" name="name" placeholder="이름을 입력해주세요." required></td>
                    </tr>
                    <tr>
                        <td>연락처</td>
                        <td>
	                        <input type="tel" name="phone" id="phone" placeholder="010-1234-5678" required maxlength="13">
	                        <c:if test="${ not empty message }">
	                        	<br>${ message }
	                        </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2"><h4>회원정보 입력</h4></td>
                    </tr>
                    <tr>
                        <td>아이디</td>
                        <td>
                            <input type="text" name="member_id" id="member_id" placeholder="아이디를 입력해주세요." required>
                            <button type="button" class="check-btn" id="check-btn">중복확인</button>
                        </td>
                    </tr>
                    <tr>
                        <td>비밀번호</td>
                        <td>
                            <div class="input-container">
                                <input type="password" id="password" name="password" placeholder="6~12자 영문, 숫자, 특수문자 조합" required>
                                <p><img src="${pageContext.request.contextPath}/resources/img/비밀번호표시.png" alt="비밀번호 보기" onclick="togglePassword('password')"></p>                            
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>비밀번호 확인</td>
                        <td>
                            <div class="input-container">
                                <input type="password" id="confirm_password" name="confirm_password" placeholder="비밀번호를 한번 더 입력해주세요." required>
                                <p><img src="${pageContext.request.contextPath}/resources/img/비밀번호표시.png" alt="비밀번호 보기" onclick="togglePassword('confirm_password')"></p>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>이메일</td>
                        <td>
                            <div class="email-input">
                                <input type="text" id="email_prefix" name="email_prefix" placeholder="이메일을 입력해주세요." required>
                                <select id="email_domain" name="email_domain" onchange="toggleEmailDomainInput()">
                                    <option value="@naver.com">@naver.com</option>
                                    <option value="@gmail.com">@gmail.com</option>
                                    <option value="@daum.net">@daum.net</option>
                                    <option value="@nate.com">@nate.com</option>
                                    <option value="direct">직접입력</option>
                                </select>
                                <input type="text" id="custom_email_domain" placeholder="직접 입력" style="display:none;">
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>이메일 인증번호</td>
                        <td>
                            <div class="email-auth">
                                <input type="text" id="auth_num_input" placeholder="인증번호 6자리를 입력해 주세요" disabled="disabled" maxlength="6" required>
                                <button type="button" class="send-auth-code-btn" id="sendAuthCodeBtn">인증번호 전송</button>
                            </div>
                            <button id="confirm_email_btn" class="dup-check-btn" disabled="disabled">인증확인</button>
                            <input type="hidden" name="result_confirm" id="result_confirm">
                            <div id="resultEmail"></div>
                        </td>
                    </tr>
                    <tr>
                        <td>성별</td>
                        <td class="gender-group">
                            <input type="radio" name="gender" value="M" required> 남자
                            <input type="radio" name="gender" value="F" required> 여자
                        </td>
                    </tr>
                </table>

                <hr class="separator-line">

                <h4>약관동의</h4>
                <div class="checkbox-group">
                    <label><input type="checkbox" id="allAgree" onclick="toggleAllCheckboxes(this)"> 회원약관 및 개인정보처리방침 모두 동의합니다. [선택항목 포함]</label>
                    <label><input type="checkbox" class="terms-checkbox" name="use_agree" required> [필수] 홈페이지 이용약관 동의</label>
                    <label><input type="checkbox" class="terms-checkbox" name="use_age" required> [필수] 만 14세 이상 확인</label>
                    <label><input type="checkbox" class="terms-checkbox" name="collect_agree" required> [필수] 개인정보 수집 및 이용목적에 대한 동의</label>
                    <label><input type="checkbox" class="terms-checkbox" name="thirdparty_agree" required> [필수] 개인정보의 제3자 제공 동의</label>
                    <label><input type="checkbox" class="terms-checkbox" name="outsourcing_agree" required> [필수] 개인정보의 위탁 동의</label>
                    <label><input type="checkbox" class="terms-checkbox" name="marketing_agree"> [선택] 마케팅 수신 동의</label>
                </div>

                <div class="centered" style="margin-top: 20px;">
                    <input type="submit" value="회원가입">
                    <button type="button" onclick="location.href='/cds/index.do'">취소</button>
                </div>
            </form>
        </div>
    </section>

    <script src="${pageContext.request.contextPath}/resources/signup.js"></script>
    <script>
    // 비밀번호 표시/숨기기 기능
    function togglePassword(id) {
        const passwordField = document.getElementById(id);
        if (passwordField.type === "password") {
            passwordField.type = "text";
        } else {
            passwordField.type = "password";
        }
    }

    // 모든 약관 체크박스 토글
    function toggleAllCheckboxes(checkbox) {
        const checkboxes = document.querySelectorAll('.terms-checkbox');
        checkboxes.forEach(function(item) {
            item.checked = checkbox.checked;
        });
    }

    // 이메일 도메인 선택 시 '직접입력'을 선택하면 입력 필드 표시
    function toggleEmailDomainInput() {
        const emailDomainSelect = document.getElementById('email_domain');
        const customEmailDomain = document.getElementById('custom_email_domain');
        
        if (emailDomainSelect.value === 'direct') {
            customEmailDomain.style.display = 'inline-block';
            customEmailDomain.required = true; // 직접 입력 필드 필수 입력
        } else {
            customEmailDomain.style.display = 'none';
            customEmailDomain.required = false;
        }
    }

/*     // 이메일 전체 주소 결합
    document.querySelector('form').addEventListener('submit', function(event) {
        const emailPrefix = document.getElementById('email_prefix').value;
        const emailDomainSelect = document.getElementById('email_domain').value;
        const customEmailDomain = document.getElementById('custom_email_domain').value;

        let emailDomain = emailDomainSelect === 'direct' ? customEmailDomain : emailDomainSelect;
        let fullEmail = emailPrefix + emailDomain;

        // 이메일을 숨겨진 input에 저장하고 form 전송
        const emailInput = document.createElement('input');
        emailInput.type = 'hidden';
        emailInput.name = 'email';
        emailInput.value = fullEmail;
        this.appendChild(emailInput);
    }); */

    // 연락처 입력 시 자동으로 '-' 추가 및 숫자만 입력 가능하게 처리
    document.getElementById('phone').addEventListener('input', function(event) {
        let value = event.target.value.replace(/[^0-9]/g, ''); // 숫자만 남기기

        if (value.length > 11) {
            value = value.slice(0, 11); // 11자리로 제한
        }

        if (value.length > 3 && value.length <= 7) {
            value = value.slice(0, 3) + '-' + value.slice(3);
        } else if (value.length > 7) {
            value = value.slice(0, 3) + '-' + value.slice(3, 7) + '-' + value.slice(7);
        }
        event.target.value = value;
    });
/*
    // 중복 확인 함수
    function checkDuplicate() {
        const member_id = document.getElementById('member_id').value;

        // 중복 확인 요청을 서버로 보내기
        fetch('/checkDuplicate', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ member_id: member_id }),
        })
        .then(response => response.json())
        .then(data => {
            if (data.isDuplicate) {
                alert('이미 사용 중인 아이디입니다.');
            } else {
                alert('사용 가능한 아이디입니다.');
            }
        })
        .catch(error => console.error('Error:', error));
    }

    // 중복확인 버튼에 이벤트 리스너 추가
    document.querySelector('.dup-check-btn').addEventListener('click', checkDuplicate);
 */
    
    
    // 비밀번호 유효성 검사 함수
    function validatePassword() {
        const password = document.getElementById('password').value;
        const confirmPassword = document.getElementById('confirm_password').value;

        // 비밀번호 유효성 검사 (6~12자 영문, 숫자, 특수문자 조합)
        const passwordPattern = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{6,12}$/;

        if (!passwordPattern.test(password)) {
            alert('비밀번호는 6~12자의 영문, 숫자, 특수문자를 포함해야 합니다.');
            return false;
        }

        if (password !== confirmPassword) {
            alert('비밀번호와 비밀번호 확인이 일치하지 않습니다.');
            return false;
        }

        return true;
    }

    // 폼 제출 시 비밀번호 유효성 검사
    document.querySelector('form').addEventListener('submit', function(event) {
        if (!validatePassword()) {
            event.preventDefault();  // 조건에 맞지 않으면 폼 제출 막기
        }
    });
    
  //비밀번호 입력칸 및 표시 크기 고정
    function togglePassword(inputId) {
     const passwordInput = document.getElementById(inputId);
 	   
 	    //console.log(list);
 	    //const toggleIcon = passwordInput.nextElementSibling.querySelector('img');
 	    //const isPasswordVisible = passwordInput.type === 'text';
 	    
 	    //const passwordField = document.getElementById(inputId);
     if (passwordInput.type === "password") {
     	passwordInput.type = "text";
     	passwordInput.setAttribute("style", "width: calc(100% - 50px)");
         //toggleIcon.src ='../resources/img/비밀번호표시.png'
     } else {
     	passwordInput.type = "password";
         //toggleIcon.src ='../resources/img/비밀번호표시.png'
         
 	    }
 	
 	    // 비밀번호 타입 전환
 	    //passwordInput.type = isPasswordVisible ? 'password' : 'text';
 	    // 아이콘 변경
 	    //toggleIcon.src = isPasswordVisible ? '../resources/img/비밀번호표시.png' : '../resources/img/비밀번호표시.png';
 	}
    </script>
</body>
</html>
