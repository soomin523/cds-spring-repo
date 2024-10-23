<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="java.security.SecureRandom"%>
<%@ page import="java.math.BigInteger"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>로그인 모달</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/member/login.css">
<script
	src="${pageContext.request.contextPath}/resources/js/jquery-3.7.1.min.js"></script>
<script src="https://apis.google.com/js/platform.js" async defer></script>
<script src="../resources/js/jquery-3.7.1.min.js"></script>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/login.js"></script>
<meta name="google-signin-scope" content="profile email">
<meta name="google-signin-client_id"
	content="172267091290-704rp9g9evbu8na2co56nmop1i13d1ul.apps.googleusercontent.com">
</head>
<body>

	<!-- 사람 모양 아이콘 로그인 버튼 -->
	<button class="log-login-btn" onclick="openModal()">
		<img src="${pageContext.request.contextPath}/resources/img/프로필.png" />
	</button>

	<!-- 로그인 모달 -->
	<div id="log-loginModal" class="log-modal">
		<div class="log-modal-content">
			<span class="log-close" onclick="closeModal()">&times;</span>

			<!-- 로그인 선택 화면 -->
			<div class="log-login-options">
				<p>
					<img
						src="${pageContext.request.contextPath}/resources/img/떠나자logo.png"
						class="log-logo" />
				</p>
				<h2>로그인</h2>
				<p class="log-login-description">떠나자 회원으로 가입하시면 회원전용 혜택 제공, 이벤트
					등 더 많은 서비스를 이용하실 수 있습니다.</p>
				<button onclick="showLoginForm()">일반 로그인</button>

				<!-- '또는'을 선으로 구분 -->
				<div class="log-divider">
					<hr>
					<span>또는</span>
					<hr>
				</div>

				<!-- 네이버 로그인 버튼 -->
				<button class="log-naver-login">
					<div class="log-social-icons">
						<img
							src="${pageContext.request.contextPath}/resources/img/naver-icon.png"
							alt="네이버 아이콘" />네이버로 시작하기
					</div>
				</button>

				<!-- 카카오 로그인 버튼 -->
				<button class="log-kakao-login" onclick="redirectToKakaoLogin()">
					<div class="log-social-icons">
						<img
							src="${pageContext.request.contextPath}/resources/img/kakao-icon.png" />카카오로
						시작하기
					</div>
				</button>

				<!-- 구글 로그인 버튼 -->
				<button class="log-google-login">
					<div class="log-social-icons">
						<img
							src="${pageContext.request.contextPath}/resources/img/google.png"
							alt="구글 아이콘" />구글로 시작하기
					</div>
				</button>

				<div class="log-footer-links">
					<a href="${pageContext.request.contextPath}/member/idFind.do">아이디
						찾기</a> | <a
						href="${pageContext.request.contextPath}/member/passwordFind.do">비밀번호
						찾기</a> | <a href="${pageContext.request.contextPath}/member/signup.do">회원가입</a>
				</div>
			</div>

			<!-- 일반 로그인 화면 -->
			<form>
				<div class="log-login-form" id="loginForm">
					<img
						src="${pageContext.request.contextPath}/resources/img/떠나자logo.png"
						class="log-logo" />
					<h2>일반 로그인</h2>
					<p class="log-login-description">떠나자 회원으로 가입하시면 회원전용 혜택 제공, 이벤트
						등 더 많은 서비스를 이용하실 수 있습니다.</p>

					<!-- 아이디 입력 -->
					<input type="text" name="member_id" id="log-emailInput"
						placeholder="아이디">

					<!-- 비밀번호 입력 -->
					<div class="log-input-container">
						<input type="password" name="password" id="log-passwordInput"
							placeholder="비밀번호 확인">
						<p>
							<img
								src="${pageContext.request.contextPath}/resources/img/비밀번호표시.png"
								alt="비밀번호 보기" onclick="togglePassword('log-passwordInput')">
						</p>
					</div>

					<button type="button" id="loginButton">로그인</button>
					<button class="log-back-button" onclick="goBack()">이전 페이지</button>

					<div class="log-footer-links">
						<a href="idFind.jsp">아이디 찾기</a> | <a href="passwordFind.jsp">비밀번호
							찾기</a> | <a href="signup.jsp">회원가입</a>
					</div>
				</div>
			</form>
		</div>
	</div>




</body>
</html>
