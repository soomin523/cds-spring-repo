<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>떠나자</title>
</head>
<body>
	<jsp:include page="main/header.jsp" />
	<h3>축제</h3>
 	<a href="festival/festival.do">축제 리스트 등록</a><br>
 	<a href="festival/getAreaName.do">축제 리스트 지역 등록</a><br>
 	<a href="festival/getFestivalList.do">축제 리스트 보기</a><br>
 	<h3>코스</h3>
   	<a href="tourCourse/Insertcourse.do">코스데이터삽입</a><br>
   	<a href="tourCourse/Courseitems.do">코스데이터 목록 조회 및 추가 삽입</a><br>
   	<a href="tourCourse/Course.do">코스화면</a><br>
   	<h3>관광지</h3>
   	<a href="destination/DestinationList.do">관광지 리스트 등록</a><br>
    <a href="destination/DestinationNameList.do">관광지 지역 등록</a><br>
 	<h3>고객센터</h3>
   	<a href="support/support.do">고객센터</a><br>
   	<h3>로그인</h3>
   	<a href="member/login.do">로그인</a><br>
   	<h3>숙소</h3>
   	<a href="accommodations/accommo.do">숙소</a><br>
   	<a href="accommodations/Insertaccommo.do">숙소데이터삽입</a><br>
   	<a href="accommodations/accommoitems.do">숙소데이터 목록 조회 및 추가 삽입</a><br>
   	<h3>관리자</h3>
   	<a href="manager/manager.do">관리자페이지 이동</a><br>
   	
    <c:if test="${ not empty msg }">
		<p>${ msg }</p>
	</c:if>
	
	<jsp:include page="main/section.jsp" />
    
</body>
</html>