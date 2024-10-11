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
    <div id="section">
        <div id="supportDetail">
            <aside>
                <h2>고객센터</h2>
                <hr>
                <div class="select">
                    <button value="notice">공지사항</button>
                    <hr>
                    <button value="guide">이용안내</button>
                    <hr>
                    <button value="question">자주묻는질문</button>
                    <hr>
                    <button value="question">1:1문의</button>
                    <hr>
                </div>
            </aside>
            <section>
                <div class="sectionTop">
                    <h1>
						<c:choose>
							<c:when test="${ select eq 'notice' }">공지사항</c:when>
							<c:when test="${ select eq 'guide' }">이용안내</c:when>
							<c:otherwise>자주묻는질문</c:otherwise>
						</c:choose>          
                    </h1>
                </div>
                <div class="newContent">
                	<button>새글쓰기</button>
                </div>
                <hr>
<c:if test="${ not empty support }">
	<c:forEach var="item" items="${ support }">
                <div class="content">
                    <div class="contentTitle">
                        <div>${ item.s_title }</div>
                        <i class="fa-solid fa-angle-up"></i>
                        <i class="fa-solid fa-angle-down"></i>
                    </div>
                    <div id="contentText" class="contentText">
                    	<p>${ item.s_content }</p>
<%-- <c:if test="${ membership_level == 3 }"> --%>
                    	<div>
	                    	<button class="update">수정하기</button>
	                    	<span>|</span>
	                    	<button class="delete">삭제하기</button>
                    	</div>
<%-- </c:if> --%>
                    </div>
                </div>
                <hr>
	</c:forEach>
</c:if>
            </section>
        </div>
    </div>
</body>
</html>