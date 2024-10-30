<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원탈퇴창</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/mypagestatus.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/mypagestatus.js"></script>
</head>

<body>
	<div class="section">
		<div class="background">
			<div class="mystatus-title">
				<h2>회원탈퇴</h2>
			</div>
			<div class="mystatus-content">
				<div>
					<li>회원탈퇴 시 회원정보는 거래 관련 권리 의무 관계의 확인 등을 이유로 상법, 전자상거래 등에서의
						소비자보호에 관한 법률 등 관련법령의 규정 또는 회사 내부 방침에 의한 정보 보호 사유에 의하여 5년 간 관리됩니다.</li>
					<li>회원탈퇴 시 2주 이내로 탈퇴를 취소할 수 있습니다.</li>
					<li>회원 탈퇴 후 게시물 삭제 요청은 고객센터에 문의해 주시기 바랍니다.</li>
				</div>
			</div>
			<div class="mystatus-check">
				<div>
					<input type="checkbox" id="agreeCheckbox">
					<p>회원탈퇴 안내를 모두 확인하였으며 탈퇴에 동의합니다</p>
				</div>
			</div>
			<div class="mystatus-button" >
				<button onclick="validateWithdrawal()">탈퇴</button>
			</div>
		</div>
	</div>
</body>

</html>