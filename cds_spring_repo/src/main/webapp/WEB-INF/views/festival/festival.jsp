<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>축제행사</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/festival.css">
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9dc9962fd8d9c313d5ca5a57212228ab"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" 
integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" 
crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="../resources/js/jquery-3.7.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/festival.js"></script>
</head>
<body>
	<%@ include file="../main/header2.jsp"%>
    <div id="section">
        <div id="festivalMain">
            <div class="search">
                <input type="text" id="searchText" placeholder="검색어를 입력하세요(축제/행사 명)">
                <span>|</span>
                <button>
                    <i class="fa-solid fa-magnifying-glass"></i>
                </button>
            </div>
            <div class="select">
                <div class="selectDate">
                	<i class="fa-regular fa-calendar calendar-icon"></i>
	                <input type="date" id="dateInput" data-placeholder="날짜">
                </div>
                <div class="selectArea">
                	<i class="fa-solid fa-location-dot"></i>
	                <select class="areaSelect">
	                    <option value="">지역</option>
	                    <option value="1">서울</option>
	                    <option value="2">인천</option>
	                    <option value="3">대전</option>
	                    <option value="4">대구</option>
	                    <option value="5">광주</option>
	                    <option value="6">부산</option>
	                    <option value="7">울산</option>
	                    <option value="8">세종시</option>
	                    <option value="31">경기도</option>
	                    <option value="32">강원특별자치도</option>
	                    <option value="33">충청북도</option>
	                    <option value="34">충청남도</option>
	                    <option value="35">경상북도</option>
	                    <option value="36">경상남도</option>
	                    <option value="37">전북특별자치도</option>
	                    <option value="38">전라남도</option>
	                    <option value="39">제주도</option>
	                </select>
                </div>
                <div class="selectCategory">
                	<i class="fa-regular fa-folder"></i>
	                <select class="categorySelect">
	                    <option value="">상세지역</option>
	                </select>
                </div>
                <button class="selectRefresh">
                	<i class="fa-solid fa-arrows-rotate"></i>
                </button>
            </div>
            <div class="duration">
                <button class="ing"># 진행중</button>
                <button class="soon"># 진행예정</button>
            </div>
            <div class="festivallist">
<c:forEach var="item" items="${ festivalList }">
                <div class="festivalitem">
                    <div class="itemImg" style="background-image: url(${ item.f_firstimage });">
                        <div class="hiddenItem">
                            <div>${ item.f_areaname } ${ item.f_sigunguname }</div>
                            <p>${ item.f_eventstartdate } ~ ${ item.f_eventenddate }</p>
                            <button value="${ item.f_contentid }">바로가기</button>
                        </div>
                    </div>
                    <div>${ item.f_title }</div>
                </div>
</c:forEach>
            </div>
            <div class="festivalNonelist"></div>
            <div class="recommend"></div>
        </div>
        <div id="modalOverlay"></div>
        <div id="festivalModal">
        	<div class='festivalName'>
		        <h1></h1>
		        <div class="mxbox"><p class="x-mark"></p></div>
		    </div>
		    <div class='modalDate'>
		        <p></p>
		        <p>~</p>
		        <p></p>
		    </div>
		    <div class="modalStatus">
        		<div class="modalIng"></div>
        	</div>
            <div class="modalMid"></div>
            <div class="modalContentBox">
                <p class="modalContent"></p>
                <button class="plusBtn"><i class="fa-solid fa-plus"></i> 더보기</button>
                <div class="modalContentPlus">
                    <div class="plusLeft">
                        <p></p>
                        <p></p>
                        <p></p>
                    </div>
                    <div class="plusRight">
                        <p></p>
                        <p></p>
                        <p></p>
                        <p></p>
                    </div>
                </div>
            </div>
            <hr />
            <div class="modalMap">
                <h3>길찾기</h3>
                <div class="mapbox" id="map"></div>
                <div class="mapContent">
                    <p></p>
                    <p></p>
                    <p></p>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="../main/footer.jsp"%>
    <!-- <script>
        
          
    </script> -->
</body>
</html>