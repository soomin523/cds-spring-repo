<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>

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

<div class="main-container">
    <div class="title">교통 조회</div>
    <div class="title-border"></div>

    <div class="ticket-type-container">
        <div class="label bus" style="background: #4caff6; color:white; border-radius: 5px; position: relative;">고속버스</div>
        <div class="label train" onclick="window.location.href='${pageContext.request.contextPath}/transportation/train.do'">기차</div>
        <div class="label flight" onclick="window.location.href='${pageContext.request.contextPath}/transportation/flight.do'">항공권</div>
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
            <div class="grade">등급</div>
            <div class="departure-time">출발 시간</div>
            <div class="arrival-time">도착 시간</div>
            <div class="charge">요금</div>
        </div>
        <div class="results-list"></div>
    </div>

</div>

<!-- Terminal selection modal -->
<div id="myModal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeModal()">&times;</span>
        <div class="consonant-filter"></div>
            <button class="filter-btn" data-consonant="all">전체</button>
            <button class="filter-btn" data-consonant="ㄱ">ㄱ</button>
            <button class="filter-btn" data-consonant="ㄴ">ㄴ</button>
            <button class="filter-btn" data-consonant="ㄷ">ㄷ</button>
            <button class="filter-btn" data-consonant="ㄹ">ㄹ</button>
            <button class="filter-btn" data-consonant="ㅁ">ㅁ</button>
            <button class="filter-btn" data-consonant="ㅂ">ㅂ</button>
            <button class="filter-btn" data-consonant="ㅅ">ㅅ</button>
            <button class="filter-btn" data-consonant="ㅇ">ㅇ</button>
            <button class="filter-btn" data-consonant="ㅈ">ㅈ</button>
            <button class="filter-btn" data-consonant="ㅊ">ㅊ</button>
            <button class="filter-btn" data-consonant="ㅋ">ㅋ</button>
            <button class="filter-btn" data-consonant="ㅌ">ㅌ</button>
            <button class="filter-btn" data-consonant="ㅍ">ㅍ</button>
            <button class="filter-btn" data-consonant="ㅎ">ㅎ</button>
            <input type="text" id="search-input" placeholder="검색">

        <div class="selection terminal">
            <c:set var="stations" value="강릉,강진,경북도청,경주,경포해변,고대조치원,고양백석,고창,고흥,공주,광양,광주(유·스퀘어),광주비아,교통대,구리,구미,군산,금산,김제,김해,김해장유,나주,나주혁신,낙동강(휴)상행,낙동강(휴)하행,남원,내포,녹동,논산,능주,담양,당진,당진기지시,대구용계,대전도룡,대전복합,대전청사(샘머리),덕산스파,동광양(중마),동대구,동서울,동해,마산,마산내서,목포,무안,배방정류소,벌교,보성,부산,삼척,상주,서대구,서부산(사상),서산,서울경부,서충주,선문대,선산(휴)상행,선산(휴)하행,섬진강(휴)상행,섬진강(휴)하행,성남(분당),세종국무조정실,세종시청,세종연구단지,세종청사,세종터미널,센트럴시티(서울),속초,수원,순창,순천,순천신대지구,시흥(시화),신갈시외(두진A),신갈영덕(고속도로),신대CGV,아산둔포,아산서부(호서대),아산온양,아산탕정사무소,아산테크노밸리,안동,안면도,안산,안성,안성공도,안성대림,안성중대,안성풍림,안성한경,안성회관,안중,안중오거리,애통리,양양,여수,여주,여주대,여주프리미엄아울렛,여천,연무대,영광,영덕,영암,영주,영천,영천망정동,영통,예산,예천,오산,옥과,완도,용인,용인기흥역,용인신갈(고가밑),용인유림,울산,울산신복,원동,원주,원주기업도시,원주문막,원주혁신,유성,의정부,이천,이천부발(신하리),익산,익산팔봉,인삼랜드(휴)상행,인삼랜드(휴)하행,인천,인천공항T1,인천공항T2,자치인재원,장성,장흥,전북혁신,전주고속터미널,전주호남제일문,점촌,정산,정안(휴)상행,정안(휴)하행,정읍,제천,제천하소,조치원,죽전,지도,진도,진주,진주개양,진주혁신,진해,창기리,창원,창원역,천안,천안3공단,천안아산역,청양,청주(센트럴),청주고속터미널,청주공항,청주대정류소,청주북부,춘천,충주,탕정삼성LCD,태안,태인,통영,평택,평택대,평택용이동,포항,포항시청,풍기,함평,해남,해제,홍대조치원,홍성,화순,황간,횡성(휴)상행,횡성(휴)하행,흥덕"/>
            
            <c:forEach items="${fn:split(stations, ',')}" var="station">
                <button onclick="selectLocation('${station}')">${station}</button>
            </c:forEach>
        </div>
    </div>
</div>




<jsp:include page="../main/footer.jsp" />
</body>
</html>