<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>회원가입</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/login/login.css">
    <style>
        body { 
            font-family: 'Arial', sans-serif;
            background-color: #f0f2f5;
        }
        .container {
            width: 40%;
            margin: 0 auto;
            padding: 40px;
            border-radius: 10px;
            background-color: white;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
            text-align: left;
        }
        h2 {
            text-align: center;
            margin-bottom: 30px;
            font-size: 24px;
            color: #333;
        }
        h4 {
            font-size: 20px; /* 글자 크기를 키움 */
            color: #4caff6; /* 하늘색으로 변경 */
            margin-bottom: 10px;
        }
        table {
            width: 100%;
            margin-bottom: 20px;
        }
        table td { 
            padding: 8px;
        }
        input[type="text"], input[type="password"], input[type="tel"], input[type="email"] {
            padding: 12px;
            margin: 5px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
            width: calc(60% - 30px); /* 아이콘 공간을 고려하여 고정 */
        }
        input[type="submit"], button {
            width: 120px;
            background-color: #4caff6;
            color: white;
            padding: 8px 12px;
            border: none;
            border-radius: 5px;
            cursor: pointer; 
            font-size: 14px;
            transition: background-color 0.3s ease;
        }
        input[type="submit"]:hover, button:hover {
            background-color: #87bee585;
        }
        .checkbox-group {
            margin-top: 20px;
        }
        .checkbox-group label {
            display: block;
            margin: 8px 0;
            font-size: 14px;
        }
        .gender-group {
            margin: 10px 0;
        }
        .note {
            font-size: 12px;
            color: gray;
        }
        .centered {
            text-align: center;
        }
        .dup-check-btn {
            padding: 6px 10px;
            background-color: #87bee5;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-left: 10px;
            font-size: 12px;
            transition: background-color 0.3s ease;
        }
        .dup-check-btn:hover {
            background-color: #87bee585;
        }
        .input-container {
            position: relative;
            width: calc(60% - 10px); /* 입력 칸의 너비를 고정시켜 아이콘 크기 변화 방지 */
        }
        .input-container input[type="password"] {
            width: 100%; /* 아이콘을 고려하여 입력 필드 너비 100% */
            padding-right: 40px; /* 아이콘 공간 확보 */
            padding: 12px;
            margin: 5px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }
        .input-container img {
            position: absolute;
            right: 10px;
            top: 50%;
            transform: translateY(-50%);
            width: 24px;
            height: 24px;
            cursor: pointer;
        }
        .email-input {
            display: flex;
            align-items: center;
        }
        #email_prefix {
            width: 50%;
            margin-right: 10px;
        }
        #email_domain {
            width: 120px;
            height: 40px;
        }
        #custom_email_domain {
            width: 120px;
            margin-left: 10px;
            display: none;
        }
        .separator-line {
            border: none;
            height: 1px;
            background-color: #ddd;
            margin: 20px 0;
        }
    </style>
</head>
<body>
    <section>
        <div class="container">
            <h2>회원가입</h2>
            <form method="post" action="signupProcess.jsp">
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
                            <input type="text" name="username" placeholder="아이디를 입력해주세요." required>
                            <button type="button" class="dup-check-btn">중복확인</button>
                        </td>
                    </tr>
                    <tr>
                        <td>비밀번호</td>
                        <td>
                            <div class="input-container">
                                <input type="password" id="password" name="password" placeholder="6~12자 영문, 숫자, 특수문자 조합" required style="width: 100%; padding-right: 40px;">
                                <img src="비밀번호표시.png" alt="비밀번호 보기" onclick="togglePassword('password')">
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>비밀번호 확인</td>
                        <td>
                            <div class="input-container">
                                <input type="password" id="confirm_password" name="confirm_password" placeholder="비밀번호를 한번 더 입력해주세요." required style="width: 100%; padding-right: 40px;">
                                <img src="비밀번호표시.png" alt="비밀번호 보기" onclick="togglePassword('confirm_password')">
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
                            <input type="radio" name="gender" value="남자" required> 남자
                            <input type="radio" name="gender" value="여자" required> 여자
                        </td>
                    </tr>
                    
                </table>

                <!-- 약관동의 전 선 추가 -->
                 <hr class="separator-line">
                
                <h4>약관동의</h4>
                <div class="checkbox-group">
                    <label><input type="checkbox" id="allAgree" onclick="toggleAllCheckboxes(this)"> 회원약관 및 개인정보처리방침 모두 동의합니다. [선택항목 포함]</label>
                    <!-- 얇은 선 추가 -->

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
