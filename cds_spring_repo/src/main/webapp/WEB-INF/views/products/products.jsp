<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>여행 상품</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/products.css">
    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.1.min.js"></script>
</head>
<body>
<div class="travel-products">
    <h1 class="main-title">여행 상품</h1>
    <div class="title-border"></div>

    <div class="content-wrapper">
        <nav class="category-menu">
            <a href="#" class="category-item active" data-category="all">전체</a>
            <a href="#" class="category-item" data-category="공연">공연</a>
            <a href="#" class="category-item" data-category="체험">체험</a>
            <a href="#" class="category-item" data-category="전시">전시</a>
            <a href="#" class="category-item" data-category="레포츠">레포츠</a>
            <a href="#" class="category-item" data-category="기타">기타</a>
        </nav>

        <div class="search-container">
            <div class="areaname-selector">
                <div class="areaname-icon">📍전체</div>
                <div class="areaname-dropdown">
                    <div data-areaname="전체"></div>
                    <!-- areaname items... -->
                </div>
            </div>
            <input type="text" id="search-input" placeholder="상품명 검색">
            <button id="search-button">검색</button>
        </div>
    </div>

    <main class="product-grid">
        <c:forEach var="item" items="${productsList}">
            <c:if test="${not empty item.firstimage}">
                <c:set var="category" value="기타" />
                <c:if test="${item.cat3.startsWith('A0203')}">
                    <c:set var="category" value="체험" />
                </c:if>
                <!-- More category checks... -->
                
                <div class="product-card"
                     data-title="${item.title}"
                     data-category="${category}"
                     data-areaname="${item.areaname}"
                     data-addr1="${item.addr1}"
                     data-image="${item.firstimage}"
                     data-phone="${item.tel}">
                     
                    <div class="product-image">
                        <img src="${item.firstimage}" alt="상품 이미지">
                    </div>
                    
                    <div class="product-category">${category}</div>
                    <h3 class="product-title">${item.title}</h3>
                    <div class="product-info">
                        <span>${item.areaname}</span>
                    </div>
                </div>
            </c:if>
        </c:forEach>
    </main>

    <!-- 모달 추가 -->
    <div id="productModal" class="modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <img src="" alt="상품 이미지" class="modal-image">
            <div class="modal-body">
                <h2 class="modal-title"></h2>
                <p class="modal-info addr1"></p>
                <p class="modal-info phone"></p>
                <div class="modal-details">
                    <a href="#" class="modal-info details">자세히 보기</a>
                </div>
                <div class="modal-button-container">
                    <button class="modal-button" onclick="window.areaname.href='https://daraemall.com/shop/goods/goods_view.html?goods_no=16';">예약하기</button>
                </div>
            </div>
        </div>
    </div>

    <!-- 상단 이동 버튼 -->
    <img id="scrollToTop" src="${pageContext.request.contextPath}/resources/img/top.png" alt="상단이동" class="scroll-to-top" />
</div>

<script>
$(document).ready(function() {
    $('.product-card').each(function() {
        var category = $(this).data('category');
        var categoryClass = 'bg-' + category;  // bg-공연, bg-체험 등 동적 클래스 생성
        $(this).find('.product-category').addClass(categoryClass);  // product-category에 클래스를 추가
    });

    var page = 1;
    var loading = false;
    var pageSize = 16;

    function loadMoreProducts() {
        if (loading) return;
        loading = true;

        var productCards = $('.product-card:hidden').slice(0, 4);
        if (productCards.length > 0) {
            productCards.show();
            loading = false;
        } else {
            loading = false;
        }
    }

    $(window).scroll(function() {
        if ($(window).scrollTop() + $(window).height() > $(document).height() - 100) {
            loadMoreProducts();
        }

        if ($(window).scrollTop() > 300) {
            $('#scrollToTop').fadeIn();
        } else {
            $('#scrollToTop').fadeOut();
        }
    });

    $('#scrollToTop').click(function() {
        $('html, body').animate({scrollTop: 0}, 'slow');
    });

    // Initially hide all product cards except the first 16
    $('.product-card:gt(15)').hide();

    // 카테고리 클릭 시
    $('.category-item').click(function(e) {
        e.preventDefault();
        var selectedCategory = $(this).data('category');

        $('.category-item').removeClass('active');
        $(this).addClass('active');

        // 전체 카테고리 클릭 시
        if (selectedCategory === 'all') {
            $('.product-card').show().slice(16).hide();
        } else {
            // 선택된 카테고리에 맞는 상품만 표시
            $('.product-card').each(function() {
                if ($(this).data('category') === selectedCategory) {
                    $(this).show(); // 선택된 카테고리에 해당하는 상품을 보여줌
                } else {
                    $(this).hide(); // 다른 카테고리 상품은 숨김
                }
            });
        }
    });

    // 검색 기능
    $('#search-button').click(function() {
        var searchTerm = $('#search-input').val().toLowerCase();
        $('.product-card').each(function() {
            var productTitle = $(this).data('title').toLowerCase();
            if (productTitle.includes(searchTerm)) {
                $(this).show();
            } else {
                $(this).hide();
            }
        });
    });

    // 모달 표시
    var modal = $('#productModal');
    var span = $('.close');

    $('.product-card').on('click', function() {
        var title = $(this).data('title');
        var addr1 = $(this).data('addr1');
        var image = $(this).data('image');
        var phone = $(this).data('phone');

        // 모달에 값 세팅
        $('#productModal .modal-title').text(title);
        $('#productModal .modal-info.addr1').text('위치: ' + addr1);
        $('#productModal .modal-info.phone').text('전화번호: ' + phone);
        $('#productModal .modal-image').attr('src', image);

        modal.fadeIn(300);
    });

    // 모달 닫기
    span.click(function() {
        modal.fadeOut(300);
    });

    $(window).click(function(event) {
        if (event.target == modal[0]) {
            modal.fadeOut(300);
        }
    });

    // 초기 로딩
    loadMoreProducts();
});
</script>

</body>
</html>
