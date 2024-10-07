<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객센터</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" 
integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" 
crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" type="text/css" href="support.css">
</head>
<body>
	<div id="section">
	    <div id="supportMain">
	        <div class="supMainTop">
	            <h1>고객센터</h1>
	        </div>
	        <hr/>
	        <div class="supMainMid">
	            <h3>1:1 문의</h3>
	            <c:choose>
		            <c:when test="${ not empty userID }">
	                    <button>
	                        <p>떠나자 문의 오픈채팅 시작하기</p>
	                        <span>답변 시간 : 09:00~18:00</span>
	                    </button>
		            </c:when>
		            <c:otherwise>
	                    <button>
	                        <p>로그인하기</p>
	                        <span>로그인 하고 문의하기</span>
	                    </button>
		            </c:otherwise>
	            </c:choose>
	        </div>
<%-- <jsp:useBean id="dao" class="dao.SupportDAO" />
<c:set var="notice" value='${ dao.search("notice") }' />
<c:set var="guide" value='${ dao.search("guide") }' />
<c:set var="question" value='${ dao.search("question") }' /> --%>
<c:set var="supportSelect" value="" />
	        <div class="supMainBtm">
	            <div class="notice">
	                <h4>공지사항</h4>
	                <button onclick="goDetailSelect('notice')">더보기 <i class="fa-solid fa-plus"></i></button>
	                <div class="items">
                        <div class="item">
                            <div class="title">공지사항1</div>
                            <div class="date">2024.09.24</div>
                        </div>
                        <hr>
                        <div class="item">
                            <div class="title">공지사항2</div>
                            <div class="date">2024.09.20</div>
                        </div>
		                <%-- <c:forEach var="item" items="${ notice }">
		                	<div class="item">
		                		<div class="title">${ item.sTitle }</div>
		                		<div class="date">${ item.sDate }</div>
		                	</div>
		                </c:forEach> --%>
                    </div>
	            </div>
	            <div class="guide">
	                <h4>이용안내</h4>
	                <button onclick="goDetailSelect('guide')">더보기 <i class="fa-solid fa-plus"></i></button>
	                <div class="items">
		                <%-- <c:forEach var="item" items="${ guide }">
		                	<div class="item">
		                		<div class="title">${ item.sTitle }</div>
		                		<div class="date">${ item.sDate }</div>
		                	</div>
		                </c:forEach> --%>
		            </div>
	            </div>
	            <div class="question">
	                <h4>자주 묻는 질문</h4>
	                <button onclick="goDetailSelect('question')"> 더보기 <i class="fa-solid fa-plus"></i></button>
                    <div class="items">
		                <%-- <c:forEach var="item" items="${ question }">
		                	<div class="item">
		                		<div class="title">${ item.sTitle }</div>
		                		<div class="date">${ item.sDate }</div>
		                	</div>
		                </c:forEach> --%>
                    </div>
	            </div>
	        </div>
	    </div>
	</div>
	
	<script>
		const goDetailSelect = (select) => {
			switch(select){
				case 'notice': window.location.href = `supportDetail.jsp?supportSelect=notice`; break;
				case 'guide': window.location.href = `supportDetail.jsp?supportSelect=guide`; break;
				case 'question': window.location.href = `supportDetail.jsp?supportSelect=question`;
			}
			// window.location.href = `supportDetail.jsp?supportSelect=${select}`;
		}
	</script>
</body>
</html>