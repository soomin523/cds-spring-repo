<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./transportation.css">
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="../resources/js/jquery-3.7.1.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
    <script src="transportation.js"></script>
    <title>교통 예약</title>
</head>
<body>
    <div class="main-container">
        <div class="title">교통 예약</div>
        <div class="title-border"></div>

        <!-- 티켓 종류 컨테이너 -->
        <div class="ticket-type-container">
            <div class="ticket-types-left">
                <div class="ticket-type one-way active">편도</div>
                <div class="ticket-type round-trip">왕복</div>
            </div>
            <div class="ticket-types-right">
                <div class="label bus" onclick="window.location.href='bus.jsp'">고속·시외버스</div>
                <div class="label train" onclick="window.location.href='train.jsp'">
                    기차</div>
                <div class="label flight" style="background: #f7f7f9; border-radius: 5px; border: 1px solid #bbb9b9; position: relative;">항공권</div>
            </div>
        </div>
        <!-- 출발지 및 날짜 컨테이너 -->
        <div class="location-date-container">
            <div class="location-label departure-location" onclick="openModal('출발지')">출발지</div>
            <div class="location-label arrival-location" onclick="openModal('도착지')">도착지</div>
            <div class="date-label departure">
                가는날 
                <input type="date" class="date-input" id="departure-date"  onchange="updateDepartureDate()" />
            </div>
            <div class="date-label return">
                오는날 
                <input type="date" class="date-input" id="arrival-date" onchange="updateArrivalDate()" />
            </div>
            <div class="customer-count" onclick="openCustomerModal()">인원 수</div>
            <div class="button inquiry">조회</div>
        </div>

        
        <!-- 로고 -->
        <img class="logo" src="black-logo-with-transparency-1-30.png" alt="Logo" />

        <!-- 조회결과 -->
        <div class="search-results-container" style="display: none;">
            <div class="result-date"> 2024/10/02 수 </div>
            <div class="result-header">
                <div class="cp">항공사</div>
                <div class="departure-time">출발 시간</div>
                <div class="arrival-time">도착 시간</div>
                <div class="price">가격</div>
                <div class="seats">잔여석</div>
            </div>
             
            <div class="result-item">
                <div class="cp">ㅇㅇ항공</div>
                <div class="departure-time">12:00</div>
                <div class="arrival-time">13:30</div>
                <div class="price">₩49,000</div>
                <div class="seats">10석</div>
            </div>
            <div class="result-item">
                <div class="cp">ㅁㅁ에어</div>
                <div class="departure-time">14:00</div>
                <div class="arrival-time">15:30</div>
                <div class="price">₩52,000</div>
                <div class="seats">5석</div>
            </div>
        </div>
    </div>

  
    <div id="myModal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeModal()" style="float: right; cursor: pointer;">&times;</span>
            <div class="container">
                <div class="left-panel">
                    
                    <div class="selection airport">
                        <button onclick="filterByRegion('SEL'); setActive(this)">서울</button>
                        <button onclick="filterByRegion('CJU'); setActive(this)">제주</button>
                        <button onclick="filterByRegion('GMP'); setActive(this)">김포</button>
                        <button onclick="filterByRegion('PUS'); setActive(this)">부산</button>
                        <button onclick="filterByRegion('CJJ'); setActive(this)">청주</button>
                        <button onclick="filterByRegion('KWJ'); setActive(this)">광주</button>
                        <button onclick="filterByRegion('TAE'); setActive(this)">대구</button>
                        <button onclick="filterByRegion('RSU'); setActive(this)">여수</button>
                        <button onclick="filterByRegion('USN'); setActive(this)">울산</button>
                        <button onclick="filterByRegion('HIN'); setActive(this)">사천</button>
                        <button onclick="filterByRegion('KPO'); setActive(this)">포항</button>
                        <button onclick="filterByRegion('WJU'); setActive(this)">원주</button>
                        <button onclick="filterByRegion('KUV'); setActive(this)">군산</button>
                        <button onclick="filterByRegion('MWX'); setActive(this)">무안</button>
                        <button onclick="filterByRegion('ICN'); setActive(this)">인천</button>
                    </div>
                </div>
                
            </div>
        </div>
    </div>

    <!-- 탑승 인원 선택 모달 -->
    <div id="customerModal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeCustomerModal()" style="float: right; cursor: pointer;">&times;</span>
            <div class="container">
                <div class="passenger-count">
                    <div>
                        성인: <button onclick="changeCount('adult', -1)">-</button> <span id="adultCount">1</span> <button onclick="changeCount('adult', 1)">+</button>
                    </div>
                    <div>
                        소아: <button onclick="changeCount('child', -1)">-</button> <span id="childCount">0</span> <button onclick="changeCount('child', 1)">+</button>
                    </div>
                    <div>
                        유아: <button onclick="changeCount('infant', -1)">-</button> <span id="infantCount">0</span> <button onclick="changeCount('infant', 1)">+</button>
                    </div>
                </div>
                <button onclick="updateCustomerCount()">확인</button>
            </div>
        </div>
    </div>

    
    
</body>
</html>
