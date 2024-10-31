<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9dc9962fd8d9c313d5ca5a57212228ab&libraries=services"></script>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관광 상품</title>
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
    <h1 class="main-title">관광 상품</h1>
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
                        <div data-location="전체">전체</div>
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
