<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9dc9962fd8d9c313d5ca5a57212228ab&libraries=services"></script>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>여행 상품</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/products.css">
    <script src="../resources/js/jquery-3.7.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/products.js"></script>
</head>
<body>
<%@ include file="../main/header2.jsp"%>
<div class="loading-spinner">
    <div class="spinner"></div>
</div>

<div class="travel-products">
    <h1 class="main-title">여행 상품</h1>
    <div class="title-border"></div>

    <div class="content-wrapper">
        <nav class="category-menu">
            <a href="#" class="category-item active" data-category="all">전체</a>
            <a href="#" class="category-item" data-category="공연/전시">공연/전시</a>
            <a href="#" class="category-item" data-category="체험">체험</a>
            <a href="#" class="category-item" data-category="레포츠">레포츠</a>
            <a href="#" class="category-item" data-category="기타">기타</a>
        </nav>

        <div class="search-container">
                <div class="location-selector">
                    <div class="location-icon">📍지역</div>
                    <div class="location-dropdown">
                        <div data-location="전체">지역</div>
                        <div data-location="서울">서울</div>
                        <div data-location="부산">부산</div>
                        <div data-location="대구">대구</div>
                        <div data-location="인천">인천</div>
                        <div data-location="광주">광주</div>
                        <div data-location="대전">대전</div>
                        <div data-location="울산">울산</div>
                        <div data-location="세종">세종</div>
                        <div data-location="경기">경기</div>
                        <div data-location="강원">강원</div>
                        <div data-location="충북">충북</div>
                        <div data-location="충남">충남</div>
                        <div data-location="전북">전북</div>
                        <div data-location="전남">전남</div>
                        <div data-location="경북">경북</div>
                        <div data-location="경남">경남</div>
                        <div data-location="제주">제주</div>
                    </div>
                </div>
            <div class="search-bar">
                <input type="text" id="search-input" placeholder="상품명 검색">
                <button id="search-button">검색</button>
            </div>
        </div>
    </div>

    <main class="product-grid">
        <c:forEach var="item" items="${initialProducts}">
            <c:set var="convertedAreaName" value="${item.areaname}" />
            <c:if test="${fn:contains(item.areaname, '강원특별자치도')}">
                <c:set var="convertedAreaName" value="강원" />
            </c:if>
            <c:if test="${fn:contains(item.areaname, '경기도')}">
                <c:set var="convertedAreaName" value="경기" />
            </c:if>
            <c:if test="${fn:contains(item.areaname, '경상남도')}">
                <c:set var="convertedAreaName" value="경남" />
            </c:if>
            <c:if test="${fn:contains(item.areaname, '경상북도')}">
                <c:set var="convertedAreaName" value="경북" />
            </c:if>
            <c:if test="${fn:contains(item.areaname, '세종특별자치시')}">
                <c:set var="convertedAreaName" value="세종" />
            </c:if>
            <c:if test="${fn:contains(item.areaname, '전라남도')}">
                <c:set var="convertedAreaName" value="전남" />
            </c:if>
            <c:if test="${fn:contains(item.areaname, '전북특별자치도')}">
                <c:set var="convertedAreaName" value="전북" />
            </c:if>
            <c:if test="${fn:contains(item.areaname, '제주도')}">
                <c:set var="convertedAreaName" value="제주" />
            </c:if>
            <c:if test="${fn:contains(item.areaname, '충청남도')}">
                <c:set var="convertedAreaName" value="충남" />
            </c:if>
            <c:if test="${fn:contains(item.areaname, '충청북도')}">
                <c:set var="convertedAreaName" value="충북" />
            </c:if>

                <!-- 카테고리 변환 -->
            <c:set var="category" value="기타" /> <!-- 기본값을 "기타"로 설정 -->
            <c:if test="${item.cat3.startsWith('A0203')}">
                <c:set var="category" value="체험" />
            </c:if>
            <c:if test="${item.cat3.startsWith('A03')}">
                <c:set var="category" value="레포츠" />
            </c:if>
            <c:if test="${item.cat3.startsWith('A02080')}">
                <c:set var="category" value="공연/전시" />
            </c:if>
              <div class="product-card"
                data-mapx="${item.mapx}"
                data-mapy="${item.mapy}"
                data-title="${item.title}" 
                data-category="${category}"  
                data-areaname="${convertedAreaName}"
                data-addr1="${item.addr1}"
                data-image="${item.firstimage}"
                data-phone="${item.tel}"
                data-info="${item.info}"
                data-usetime="${item.usetime}"
                data-opendate="${item.opendate}"
                data-restdate="${item.restdate}"
                data-price="${item.price}"
                data-overview="${item.overview}"
                data-infoname1="${item.infoname1}"
                data-infotext1="${item.infotext1}"
                data-infoname2="${item.infoname2}"
                data-infotext2="${item.infotext2}"
                data-infoname3="${item.infoname3}"
                data-infotext3="${item.infotext3}"
                data-infoname4="${item.infoname4}"
                data-infotext4="${item.infotext4}">
            
                <div class="product-image">
                    <img src="${item.firstimage}" alt="상품 이미지">
                </div>
                <div class="product-category bg-${category.replace('/', '_')}">${category}</div>  
                <div class="product-details">
                    <h3 class="product-title">${item.title}</h3>
                    <span class="product-areaname">${convertedAreaName}</span>  
                </div>
                <div class="hidden-homepage" style="display: none;">${item.homepage}</div>
            </div>
        </c:forEach>

    </main>
    <div id="no-more-products" style="display: none; text-align: center; padding: 20px; color: #666;">
        더 이상 조회할 상품이 없습니다. 
    </div>

  


    <!-- 모달 추가 -->
    <div id="productModal" class="modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <img src="" alt="상품 이미지" class="modal-image">
            <div class="modal-body">
                <h2 class="modal-title"></h2>
                <p class="modal-info addr1"></p>
                <p class="modal-info phone"></p>
                <p class="modal-info info"></p>
                <p class="modal-info usetime"></p>
                <p class="modal-info opendate"></p>
                <p class="modal-info restdate"></p>
                <p class="modal-info price"></p>
                <div class="modal-info info1"></div>
                <div class="modal-info info2"></div>
                <div class="modal-info info3"></div>
                <div class="modal-info info4"></div>
                <p class="modal-info overview"></p>
                <p class="modal-homepage-links"></p>
            </div>
            <div class="modal-map">
                <div id="map" style="width:100%;height:400px;margin-top:20px;"></div>
            </div>
        </div>
    </div>

    <!-- 상단 이동 버튼 -->
    <img id="scrollToTop" src="${pageContext.request.contextPath}/resources/img/top.png" alt="상단이동" class="scroll-to-top" />
</div>


	<jsp:include page="../main/footer.jsp" />



</body>
</html>