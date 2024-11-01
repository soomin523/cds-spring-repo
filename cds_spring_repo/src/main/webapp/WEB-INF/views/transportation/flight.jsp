<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/transportation.css">
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="../resources/js/jquery-3.7.1.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/transportation.js"></script>
    <title>교통 조회</title>
    
</head>
<body>
<%@ include file="../main/header2.jsp"%>
<div class="loading-spinner">
    <div class="spinner"></div>
</div>

<!-- Common header code -->

<div class="main-container">
    <div class="title">교통 조회</div>
    <div class="title-border"></div>

    <div class="ticket-type-container">
        <div class="label bus" onclick="window.location.href='${pageContext.request.contextPath}/transportation/bus.do'">고속버스</div>
        <div class="label train" onclick="window.location.href='${pageContext.request.contextPath}/transportation/train.do'">기차</div>
        <div class="label flight" style="background: #4caff6; color:white; border-radius: 5px;  position: relative;">항공권</div>
    </div>

    <div class="location-date-container">
        <div class="location-label departure-location" onclick="openModal('departure')">출발지</div>
        <div class="location-label arrival-location" onclick="openModal('arrival')">도착지</div>
        <div class="date-label departure">
            <span class="sideup">가는날</span> 
            <input type="date" class="date-input" id="departure-date" onchange="updateDepartureDate()" />
        </div>
        <div class="button inquiry">조회</div>
    </div>

    <img class="logo" src="${pageContext.request.contextPath}/resources/img/logo-transportation.png" alt="Logo" />

<div class="search-results-container" style="display: none;">
    <div class="date-navigation">
        <button class="prev-date">◀</button>
        <div class="result-date"></div>
        <button class="next-date">▶</button>
    </div>
    <div class="result-header">
        <div class="cp">항공사</div>
        <div class="departure-time">출발 시간</div>
        <div class="arrival-time">도착 시간</div>
        <div class="price">가격</div>
        <div class="seats">편명</div>
    </div>
    <div class="results-list"></div>
</div>

</div>

<!-- Airport selection modal -->
<div id="myModal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeModal()">&times;</span>

        <div class="selection airport">
            <button onclick="selectLocation('무안')">무안</button>
            <button onclick="selectLocation('광주')">광주</button>
            <button onclick="selectLocation('군산')">군산</button>
            <button onclick="selectLocation('여수')">여수</button>
            <button onclick="selectLocation('원주')">원주</button>
            <button onclick="selectLocation('양양')">양양</button>
            <button onclick="selectLocation('제주')">제주</button>
            <button onclick="selectLocation('김해')">김해</button>
            <button onclick="selectLocation('사천')">사천</button>
            <button onclick="selectLocation('울산')">울산</button>
            <button onclick="selectLocation('인천')">인천</button>
            <button onclick="selectLocation('김포')">김포</button>
            <button onclick="selectLocation('포항')">포항</button>
            <button onclick="selectLocation('대구')">대구</button>
            <button onclick="selectLocation('청주')">청주</button>
        </div>
        
    </div>
</div>



<jsp:include page="../main/footer.jsp" />
</body>
</html>
