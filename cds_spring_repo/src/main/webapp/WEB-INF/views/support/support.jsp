<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객센터</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" 
integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" 
crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/support.css">
<script src="../resources/js/jquery-3.7.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/support.js"></script>
</head>
<body>
	<%@ include file="../main/header2.jsp"%>
	<div id="section">
	    <div id="supportMain">
	        <div class="supMainTop">
	            <h1>고객센터</h1>
	        </div>
	        <hr/>
	        <div class="supMainMid">
	            <h3>1:1 문의</h3>
<c:choose>
	<c:when test="${ not empty member }">
                    <button class="one">
                        <p>떠나자 문의 오픈채팅 시작하기</p>
                        <span>답변 시간 : 09:00~18:00</span>
                    </button>
	</c:when>
	<c:otherwise>
                    <button class="login">
                        <p>로그인하기</p>
                        <span>로그인 하고 문의하기</span>
                    </button>
	</c:otherwise>
</c:choose>
	        </div>
	        <div class="supMainBtm">
	            <div class="notice select">
	                <h4>공지사항</h4>
	                <button value="notice">더보기 <i class="fa-solid fa-plus"></i></button>
	                <div class="items">
	                <hr>
<c:if test="${ not empty notice }">
	<c:forEach var="i" begin="0" end="${ notice.size() > 7 ? 7 : notice.size()-1 }">
						<div class="item" data-category="notice" data-s_idx="${ notice[i].s_idx }">
	                		<div class="title">${ notice[i].s_title }</div>
	                		<div class="date">
		<fmt:formatDate value="${ notice[i].post_date }" type="date" pattern="yyyy-MM-dd" />
	                		</div>
	                	</div>
	                <hr>	
	</c:forEach>
</c:if>
                    </div>
	            </div>
	            <div class="guide select">
	                <h4>이용안내</h4>
	                <button value="guide">더보기 <i class="fa-solid fa-plus"></i></button>
	                <div class="items">
	                <hr>
<c:if test="${ not empty guide }">
	<c:forEach var="i" begin="0" end="${ guide.size() > 7 ? 7 : guide.size()-1 }">
						<div class="item" data-category="guide" data-s_idx="${ guide[i].s_idx }">
		                	<div class="title">${ guide[i].s_title }</div>
	                		<div class="date">
		<fmt:formatDate value="${ guide[i].post_date }" type="date" pattern="yyyy-MM-dd" />
	                		</div>
	                	</div>
	                <hr>
	</c:forEach>
</c:if>
		            </div>
	            </div>
	            <div class="question select">
	                <h4>자주 묻는 질문</h4>
	                <button value="question"> 더보기 <i class="fa-solid fa-plus"></i></button>
                    <div class="items">
                    <hr>
<c:if test="${ not empty question }">
	<c:forEach var="i" begin="0" end="${ question.size() > 7 ? 7 : question.size()-1 }">
						<div class="item" data-category="question" data-s_idx="${ question[i].s_idx }">
	                		<div class="title">${ question[i].s_title }</div>
	                		<div class="date">
		<fmt:formatDate value="${ question[i].post_date }" type="date" pattern="yyyy-MM-dd" />
	                		</div>
	                	</div>
	                <hr>
	</c:forEach>
</c:if>
                    </div>
	            </div>
	        </div>
	    </div>
	</div>
	<%@ include file="../main/footer.jsp"%>
</body>
</html>