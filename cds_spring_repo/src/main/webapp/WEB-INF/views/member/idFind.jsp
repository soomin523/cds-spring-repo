<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>아이디 찾기</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/member/idFind.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/member/member/all.min.css" 
integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" 
crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.1.min.js"></script>
</head>
<body>
    <!-- 아이디 찾기 화면 -->
    <div class="log-container" id="logIdFindScreen"> 
        <h1>아이디 찾기</h1>
        <p>회원로그인을 통해 더욱 다양한 서비스를 이용하세요.</p>
        <div class="log-line-divider"></div> 
        <h2>등록된 이메일로 아이디 찾기</h2>
        <p>회원정보에 등록된 이메일 주소와 입력한 이메일 주소가 같아야 아이디 찾기가 가능합니다.</p>
        <div class="log-input-group"> 
            <label for="name">이름</label>
            <input type="text" id="name" placeholder="이름을 입력해주세요." required>
        </div>
        <div class="log-input-group"> 
            <label for="email">이메일</label>
            <input type="email" id="email" placeholder="이메일을 입력해주세요." required>
        </div>
	<button class="log-btn" onclick="findId()">아이디 찾기</button>
    </div>

    <!-- 찾기 결과 화면 -->
    <div class="log-container" id="IdFindResultScreen" style="display:none;"> 
        <h2>아이디 찾기 결과</h2>
        <p>고객님의 정보와 일치하는 아이디입니다.</p>
        <div class="log-divider"></div> 
        <div class="log-result-container"> 
            <div class="log-user-id"></div> 
            <a href="#">일반회원</a>
        </div>
        <div class="log-divider"></div> 
        <div class="log-btn-group"> 
            <button class="log-btn-small" onclick="location.href='passwordFind.do'">비밀번호 찾기</button> 
            <button class="log-btn-small primary" onclick="location.href='login.do'">로그인</button> 
        </div>
    </div>

    <script>
    function findId() {
        const name = document.getElementById('name').value;
        const email = document.getElementById('email').value;
        
        $.ajax({
            type: 'post',
            url: 'idFind', // 실제 서버 로그인 엔드포인트
            data: {
                name: name,
                email: email
            },
            success: function(data) {
                if (data) {
                	console.log(data)
                	document.getElementById('logIdFindScreen').style.display = 'none';
                    document.getElementById('IdFindResultScreen').style.display = 'block';
                    $(".log-user-id").text(data);
                } else {
                	alert("일치하는 아이디가 없습니다."); // 오류 메시지 표시
                }
            },
            error: function(error) {
                console.error('Error:', error);
                alert('서버 오류 발생. 다시 시도해주세요.');
            }
        });//end of ajax

    }
</script>

</body>
</html>
