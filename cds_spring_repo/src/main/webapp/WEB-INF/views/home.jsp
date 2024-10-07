<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>떠나자</title>
</head>
<body>

 	<a href="festival/festival.do">축제 리스트 등록</a><br>
 	<a href="festival/getAreaName.do">축제 리스트 지역 등록</a><br>
 	<a href="festival/getFestivalList.do">축제 리스트 보기</a><br>
    		
    <c:if test="${ not empty msg }">
		<p>${ msg }</p>
	</c:if>
    
</body>
</html>