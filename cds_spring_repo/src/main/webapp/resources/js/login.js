
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

