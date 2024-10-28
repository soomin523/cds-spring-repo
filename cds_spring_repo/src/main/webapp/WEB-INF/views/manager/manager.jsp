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
                <h2>관리</h2>
                <hr>
                <div class="select">
                    <button value="member">회원관리</button>
                    <hr>
                    <button value="api">API</button>
                    <hr>
                </div>
            </aside>
            <section>
                <div class="sectionTop">
                    <h1>
						<c:choose>
							<c:when test="${ select eq 'api' }">API</c:when>
							<c:otherwise>회원관리</c:otherwise>
						</c:choose>          
                    </h1>
                </div>
                <hr>
<c:choose>
	<c:when test="${ select eq 'api' }">
				<div class="apiContainer container">
					<div class="destination destinationDeco">
						<h3>관광지</h3>
						<a href="../destination/DestinationList.do">관광지 리스트 등록</a><br>
                        <a href="../destination/DestinationNameList.do">관광지 지역 등록</a><br>
					</div>
                    <div class="festival festivalDeco">
                    	<h3>축제/행사</h3>
						<a href="../festival/festival.do">축제 리스트 등록</a><br>
 	                    <a href="../festival/getAreaName.do">축제 리스트 지역 등록</a><br>
					</div>
                    <div class="tourCourse tourCourseDeco">
						<h3>코스</h3>
						<a href="../tourCourse/Insertcourse.do">코스데이터삽입</a><br>
   	                    <a href="../tourCourse/Courseitems.do">코스데이터 목록 조회 및 추가 삽입</a><br>
   	                    <a href="../tourCourse/Courseitems2.do">코스데이터 목록 조회 및 추가 삽입2</a><br>
					</div>
                    <div class="gift giftDeco">
						<h3>관광상품</h3>
						<a href="../products/insertProducts.do">관광상품 리스트 등록</a><br>
 	                    <a href="../products/getAreaName.do">관광상품 지역명 업데이트</a><br>						
 	                    <a href="../products/updateProductDetails.do">관광상품 상세정보 업데이트</a><br>						
 	                    <a href="../products/updateProductInfo.do">관광상품 반복정보 업데이트</a><br>						
					</div>
                    <div class="accommodation accommodationDeco">
						<h3>숙박</h3>
   	                    <a href="../accommodations/Insertaccommo.do">숙소데이터삽입</a><br>
						<a href="../accommodations/accommoitems.do">숙소데이터 목록 조회 및 추가 삽입</a><br>
						<a href="../accommodations/accommoitems2.do">숙소데이터 목록 조회 및 추가 삽입2</a><br>
					</div>
                </div>
	</c:when>
	<c:otherwise>
				<div class="memberContainer container">
					<table>
						<tr>
							<td>회원 번호</td>
							<td>아이디</td>
							<td>이름</td>
							<td>이메일</td>
							<td>전화번호</td>
							<td>성별</td>
							<td>탈퇴 여부</td>
							<td>가입일</td>
							<td>삭제</td>
						</tr>
		<c:if test="${ not empty memberList }">
			<c:forEach var="item" items="${ memberList }">
						<tr>
							<td>${ item.m_id }</td>
							<td>${ item.member_id }</td>
							<td>${ item.name }</td>
							<td>${ item.email }</td>
							<td>${ item.phone }</td>
							<td>${ item.gender }</td>
							<td>${ item.withdrawal_request }</td>
							<td>${ item.created_at }</td>
							<td>
				<c:if test="${ item.withdrawal_request eq 'Y' }">
							<i class="fa-solid fa-trash-can deleteMember" data-id="${ item.m_id }"></i>
				</c:if>
							</td>
						</tr>
			</c:forEach>
		</c:if>
					</table>
				</div>
	</c:otherwise>
</c:choose>				
            </section>
        </div>
    </div>
    <%@ include file="../main/footer.jsp"%>
    
</body>
</html>