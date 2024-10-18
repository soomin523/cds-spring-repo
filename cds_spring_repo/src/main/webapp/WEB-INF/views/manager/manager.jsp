<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>떠나자 관리</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" 
integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" 
crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/manager.css">
<script src="../resources/js/jquery-3.7.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/manager.js"></script>
</head>
<body>
	<%@ include file="../main/header2.jsp"%>
    <div id="section">
        <div id="managerPage">
            <aside>
                <h2>떠나자</h2>
                <hr>
                <div class="select">
                    <button value="member">회원관리</button>
                    <hr>
                    <button value="api">API</button>
                    <hr>
                    <button value="community">커뮤니티</button>
                    <hr>
                </div>
            </aside>
            <section>
                <div class="sectionTop">
                    <h1>
						<c:choose>
							<c:when test="${ select eq 'api' }">API</c:when>
							<c:when test="${ select eq 'community' }">커뮤니티</c:when>
							<c:otherwise>회원관리</c:otherwise>
						</c:choose>          
                    </h1>
                </div>
                <hr>
<c:choose>
	<c:when test="${ select eq 'api' }">
				<div class="apiContainer container">
					<div class="destination">
						<h3>관광지</h3>
						<a href="../destination/DestinationList.do">관광지 리스트 등록</a><br>
                        <a href="../destination/DestinationNameList.do">관광지 지역 등록</a><br>
					</div>
                    <div class="festival">
                    	<h3>축제/행사</h3>
						<a href="../festival/festival.do">축제 리스트 등록</a><br>
 	                    <a href="../festival/getAreaName.do">축제 리스트 지역 등록</a><br>
					</div>
                    <div class="tourCourse">
						<h3>코스</h3>
						<a href="../tourCourse/Insertcourse.do">코스데이터삽입</a><br>
   	                    <a href="../tourCourse/Courseitems.do">코스데이터 목록 조회 및 추가 삽입</a><br>
					</div>
                    <div class="gift">
						<h3>관광상품</h3>
						<a href="../products/insertProducts.do">관광상품 리스트 등록</a><br>
 	                    <a href="../products/getAreaName.do">관광상품 지역명 업데이트</a><br>						
					</div>
                    <div class="accommodation">
						<h3>숙박</h3>
						<a href="../accommodations/accommo.do">숙소</a><br>
						<a href="../accommodations/cityaccomo.do">숙소 목록</a><br>
						<a href="../accommodations/accommoitems.do">숙소데이터 목록 조회 및 추가 삽입</a><br>
   	                    <a href="../accommodations/Insertaccommo.do">숙소데이터삽입</a><br>
					</div>
                </div>
	</c:when>
	<c:when test="${ select eq 'community' }">
				<div class="communityContainer container">
					
                </div>
	</c:when>
	<c:otherwise>
				<div class="memberContainer container">
                    
                </div>
	</c:otherwise>
</c:choose>				
            </section>
        </div>
    </div>
    <%@ include file="../main/footer.jsp"%>
    
    <script>
    	$(function(){
    		
    		$("aside > h2").click(function(){
    			location.href = "../index.do";
    		});
    		
    	});
    </script>
</body>
</html>