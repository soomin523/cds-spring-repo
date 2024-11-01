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


 
<div class="main-container">
    <div class="title">교통 조회</div>
    <div class="title-border"></div>

    <div class="ticket-type-container">
        <div class="label bus" onclick="window.location.href='${pageContext.request.contextPath}/transportation/bus.do'">고속버스</div>
        <div class="label train" style="background: #4caff6; color:white; border-radius: 5px;  position: relative;">기차</div>
        <div class="label flight" onclick="window.location.href='${pageContext.request.contextPath}/transportation/flight.do'">항공권</div>
    </div>

    <div class="location-date-container">
        <div class="location-label departure-location" onclick="openModal('departure')">출발지</div>
        <div class="location-label arrival-location" onclick="openModal('arrival')">도착지</div>
        <div class="date-label departure">
            <span class="sideup">가는날</span>  <input type="date" class="date-input" id="departure-date" onchange="updateDepartureDate()" />
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
            <div class="cp">열차종류</div>
            <div class="departure-time">출발 시간</div>
            <div class="arrival-time">도착 시간</div>
            <div class="adultcharge">운임</div>
            <div class="trainno">열차번호</div>
        </div>
        <div class="results-list"></div>
    </div>

</div>


<!-- Station selection modal -->
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

        <div class="selection station">
            <c:set var="stations" value=  "가남,감곡장호원,강경,강구,강릉,개포,경산,계룡,고한,곡성,공주,광명,광양,광주,광주송정,광천,구례구,구미,구포,군북,군산,극락강,기장,김제,김천,김천구미,나주,남성현,남원,논산,능주,다시,단양,대구,대야,대전,대천,덕소,도계,도고온천,동대구,동백산,동해,둔내,득량,마산,만종,매곡,명봉,목포,몽탄,무안,묵호,물금,민둥산,밀양,반성,백양사,벌교,보성,봉화,부강,부발,부산,부전,북영천,북울산,북천,분천,사북,사상,삼랑진,삼례,삼산,삼탄,삽교,상동,상봉,상주,서경주,서광주,서대구,서대전,서울,서원주,서정리,서천,석불,석포,성환,센텀,수원,순천,승부,신경주,신기,신녕,신동,신례원,신탄진,신태인,신해운대,심천,쌍룡,아산,아화,안강,안동,안양,앙성온천,약목,양동,양원,양평,여수EXPO,여천,연산,영덕,영동,영등포,영월,영주,영천,예당,예미,예산,예천,오근장,오산,오송,오수,옥산,옥천,온양온천,완사,왜관,용궁,용문,용산,울산,웅천,원동,원주,월포,음성,의성,이양,이원,익산,일로,일신,임기,임성리,임실,장사,장성,장항,전의,전주,점촌,정동진,정읍,제천,조성,조치원,주덕,중리,증평,지탄,지평,진례,진부,진상,진영,진주,창원,창원중앙,천안,천안아산,철암,청도,청량리,청리,청소,청주,청주공항,추풍령,춘양,충주,탑리,태백,태화강,판교(경기),판교(충남),평창,평택,포항,풍기,하동,하양,한림정,함안,함열,함창,함평,행신,현동,홍성,화명,화본,화순,황간,횡성,횡천,효천"/>
            
            <c:forEach items="${fn:split(stations, ',')}" var="station">
                <button onclick="selectLocation('${station}')">${station}</button>
            </c:forEach>
        </div>
    </div>
</div>


<jsp:include page="../main/footer.jsp" />
</body>
</html>

