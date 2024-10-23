
$(function() {

    $('#loginButton').on('click', function(e) {
    
        e.preventDefault(); // 폼 기본 제출 방지
        
        const member_id = $('#log-emailInput').val().trim();
        const password = $('#log-passwordInput').val();
        
        if (!member_id || !password) {
            alert('이메일/아이디와 비밀번호를 입력해주세요.');
            return; // 유효성 검사에 실패하면 AJAX 요청을 보내지 않음
        }
        
        console.log(member_id+", "+password);
        
        $.ajax({
            type: 'post',
            url: 'loginPass.do', // 실제 서버 로그인 엔드포인트
            data: {
                member_id: member_id,
                password: password
            },
            success: function(data) {
                if (data.trim() == "ok") {
                    window.location.href = '/cds/index.do'; // 로그인 성공 시 리다이렉트
                } else {
                    alert('아이디나 비밀번호가 일치하지 않습니다.');
                }
            },
            error: function(error) {
                console.error('Error:', error);
                alert('서버 오류 발생. 다시 시도해주세요.');
            }
        });//end of ajax
        
    });
    
    //네이버 로그인
    $(".log-naver-login").on('click', function() {
	    const naverClientId = "4IZekkKFksLclCpboj2G";
	    const redirectUri = "http://localhost:9090/cds/member/naverLogin.do";
	    const state = "cds_prtj";
	
	    window.location.href = `https://nid.naver.com/oauth2.0/authorize?client_id=${naverClientId}&redirect_uri=${encodeURIComponent(redirectUri)}&response_type=code&state=${state}`;
	});
    
    //카카오 로그인
    $(".log-kakao-login").on('click', function() {
	    const kakaoClientId = "c07530622585d316fcad9f90e1d935b5";
	    const redirectUri = "http://localhost:9090/cds/member/kakaoLogin.do";
	
	    window.location.href = `https://kauth.kakao.com/oauth/authorize?client_id=${kakaoClientId}&redirect_uri=${encodeURIComponent(redirectUri)}&response_type=code`;
	});
    
    //구글 로그인
    $(".log-google-login").on('click', function() {
    	window.location.href = "/cds/member/googleLogin.do";
    });
    
});

function openModal() {
    console.log("openModal called");
        document.getElementById('log-loginModal').style.display = 'block'; 
        resetModal(); 
    }

    // 모달 닫기 함수
    function closeModal() {
        document.getElementById('log-loginModal').style.display = 'none'; 
    }

    // 로그인 선택 화면으로 돌아가기
    function goBack() {
        document.querySelector('.log-login-form').style.display = 'none'; 
        document.querySelector('.log-login-options').style.display = 'block'; 
    }

    // 일반 로그인 화면 보여주기
    function showLoginForm() {
        document.querySelector('.log-login-options').style.display = 'none'; 
        document.querySelector('.log-login-form').style.display = 'block'; 
    }

    // 비밀번호 표시/숨기기 기능
    function togglePassword(id) {
        const passwordField = document.getElementById(id);
        if (passwordField.type === "password") {
            passwordField.type = "text";
        } else {
            passwordField.type = "password";
        }
    }

    // 모달 초기화
    function resetModal() {
        document.querySelector('.log-login-form').style.display = 'none'; 
        document.querySelector('.log-login-options').style.display = 'block'; 
        document.getElementById('log-emailInput').value = ''; 
        document.getElementById('log-passwordInput').value = ''; 
    }

    // 카카오 로그인 요청 URL 생성 및 리다이렉트
    function redirectToKakaoLogin() {
        window.Kakao.init("9dc9962fd8d9c313d5ca5a57212228ab");
        window.Kakao.Auth.login({
            scope: 'account_email',
            success: function(authObj) {
                console.log(authObj);
                window.Kakao.API.request({
                    url: '/v2/user/me',
                    success: res => {
                        const kakao_account = res.kakao_account;
                        console.log(kakao_account);
                    }
                });
            }
        });
    }

    // 폼 제출 방지 (엔터 키로 인한 모달 닫힘 현상 방지)
    document.querySelector('form').addEventListener('submit', function(event) {
        event.preventDefault(); // 폼 제출을 막고 모달이 닫히는 것을 방지
    });

    // 엔터 키 눌렀을 때 로그인 버튼 클릭
    function submitOnEnter(event) {
        if (event.keyCode === 13) { // 엔터 키 코드 13
            event.preventDefault(); // 기본 엔터 키 동작 방지
            document.getElementById('loginButton').click(); // 로그인 버튼 클릭
        }
    }

    // 아이디와 비밀번호 입력 칸에 엔터 키 감지 이벤트 추가
    document.getElementById('log-emailInput').addEventListener('keydown', submitOnEnter);
    document.getElementById('log-passwordInput').addEventListener('keydown', submitOnEnter);
    

