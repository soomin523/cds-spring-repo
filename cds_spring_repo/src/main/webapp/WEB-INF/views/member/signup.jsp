<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>회원가입</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/member/signup.css">
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
                        <td><input type="tel" name="phone" id="phone" placeholder="010-1234-5678" required></td>
                    </tr>
                    <tr>
                        <td colspan="2"><h4>회원정보 입력</h4></td>
                    </tr>
                    <tr>
                        <td>아이디</td>
                        <td>
                            <input type="text" name="member_id" id="member_id" placeholder="아이디를 입력해주세요." required>
                            <button type="button" class="dup-check-btn">중복확인</button>
                        </td>
                    </tr>
                    <tr>
                        <td>비밀번호</td>
                        <td>
                            <div class="input-container">
                                <input type="password" id="password" name="password" placeholder="6~12자 영문, 숫자, 특수문자 조합" required style="width: 100%; padding-right: 40px;">
                                <p><img src="${pageContext.request.contextPath}/resources/img/비밀번호표시.png" alt="비밀번호 보기" onclick="togglePassword('password')"></p>                            
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>비밀번호 확인</td>
                        <td>
                            <div class="input-container">
                                <input type="password" id="confirm_password" name="confirm_password" placeholder="비밀번호를 한번 더 입력해주세요." required style="width: 100%; padding-right: 40px;">
                                <p><img src="${pageContext.request.contextPath}/resources/img/비밀번호표시.png" alt="비밀번호 보기" onclick="togglePassword('confirm_password')"></p>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>이메일</td>
                        <td>
                            <div class="email-input">
                                <input type="text" id="email_prefix" placeholder="이메일을 입력해주세요." required>
                                <select id="email_domain" onchange="toggleEmailDomainInput()">
                                    <option value="@naver.com">@naver.com</option>
                                    <option value="@gmail.com">@gmail.com</option>
                                    <option value="@daum.net">@daum.net</option>
                                    <option value="@nate.com">@nate.com</option>
                                    <option value="direct">직접입력</option>
                                </select>
                                <input type="text" id="custom_email_domain" placeholder="직접 입력">
                            </div>
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
                    <button type="button" onclick="location.href='/'">취소</button>
                </div>
            </form>
        </div>
    </section>

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

    // 이메일 전체 주소 결합
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
    });

    // 연락처 입력 시 자동으로 '-' 추가 및 숫자만 입력 가능하게 처리
    document.getElementById('phone').addEventListener('input', function(event) {
        let value = event.target.value.replace(/[^0-9]/g, '');

        if (value.length > 3 && value.length <= 7) {
            value = value.slice(0, 3) + '-' + value.slice(3);
        } else if (value.length > 7) {
            value = value.slice(0, 3) + '-' + value.slice(3, 7) + '-' + value.slice(7, 11);
        }
        event.target.value = value;
    });

    // 중복 확인 함수
    function checkDuplicate() {
        const username = document.getElementById('memder_id').value;

        // 중복 확인 요청을 서버로 보내기
        fetch('/checkDuplicate', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ memder_id: memder_id }),
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
    </script>
</body>
</html>
