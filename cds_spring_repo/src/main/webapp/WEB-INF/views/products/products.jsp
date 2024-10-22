<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>여행 상품</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/products.css">
    <script src="../resources/js/jquery-3.7.1.min.js"></script>
</head>
<body>
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
            <div class="areaname-selector">
                <div class="areaname-icon">📍전체</div>
                <div class="areaname-dropdown">
                    <div data-areaname="전체"></div>
                    <!-- areaname items... -->
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
            <c:set var="convertedAreaName" value="${fn:replace(item.areaname, '강원특별자치도', '강원')}" />
            <c:set var="convertedAreaName" value="${fn:replace(convertedAreaName, '경기도', '경기')}" />
            <c:set var="convertedAreaName" value="${fn:replace(convertedAreaName, '경상남도', '경남')}" />
            <c:set var="convertedAreaName" value="${fn:replace(convertedAreaName, '경상북도', '경북')}" />
            <c:set var="convertedAreaName" value="${fn:replace(convertedAreaName, '세종특별자치시', '세종')}" />
            <c:set var="convertedAreaName" value="${fn:replace(convertedAreaName, '전라남도', '전남')}" />
            <c:set var="convertedAreaName" value="${fn:replace(convertedAreaName, '전북특별자치도', '전북')}" />
            <c:set var="convertedAreaName" value="${fn:replace(convertedAreaName, '제주도', '제주')}" />
            <c:set var="convertedAreaName" value="${fn:replace(convertedAreaName, '충청남도', '충남')}" />
            <c:set var="convertedAreaName" value="${fn:replace(convertedAreaName, '충청북도', '충북')}" />

                <!-- 카테고리 변환 -->
            <c:set var="category" value="기타" /> <!-- 기본값을 "기타"로 설정 -->
            <c:if test="${item.cat3.startsWith('A0203')}">
                <c:set var="category" value="체험" />
            </c:if>
            <c:if test="${item.cat3.startsWith('A03')}">
                <c:set var="category" value="레포츠" />
            </c:if>
            <c:if test="${fn:substring(item.cat3, 0, 6) == 'A02080'}">
                <c:set var="category" value="공연/전시" />
            </c:if>
            <c:if test="${fn:substring(item.cat3, 0, 6) == 'A02081'}">
                <c:set var="category" value="공연/전시" />
            </c:if>

            <div class="product-card"
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
                data-price="${item.price}">
                
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
                <p class="modal-homepage-links"></p>
                <div class="modal-button-container">
                     <%-- <button class="modal-button" id="reserve-button">예약하기</button> --%>
                </div>
            </div>
        </div>
    </div>

    <!-- 상단 이동 버튼 -->
    <img id="scrollToTop" src="${pageContext.request.contextPath}/resources/img/top.png" alt="상단이동" class="scroll-to-top" />
</div>

<script>
$(document).ready(function() {
    var page = 1;
    var loading = false;
    var pageSize = 12;
    var currentCategory = 'all';

    function convertCat3ToCategory(cat3) {
    if (cat3.startsWith('A0203')) return '체험';
    if (cat3.startsWith('A03')) return '레포츠';
    if (cat3.startsWith('A02080')) return '공연/전시';
    if (cat3.startsWith('A02081')) return '공연/전시';
    return '기타';
}

    function loadMoreProducts() {
        if (loading) return;
        loading = true;

         let cardCount = 0;

         function fetchData() {
            $.ajax({
                url: '${pageContext.request.contextPath}/products/getMoreProducts',
                method: 'GET',
                data: { page: page, size: pageSize, category: currentCategory },
                dataType: 'json',
                success: function(data) {
                    console.log(data);
                    if (data.length > 0) {
                        data.forEach(function(item) {
                            var itemCategory = convertCat3ToCategory(item.cat3);
                            if (currentCategory === 'all' || itemCategory === currentCategory) {
                                var productCard = createProductCard(item, itemCategory);
                                $('.product-grid').append(productCard);
                                cardCount++;
                            }
                        });
                        page++;
                        applyProductCardStyles();
                        
                        if (cardCount < 12 && data.length === pageSize) {
                                fetchData(); // Fetch more data if we don't have 12 cards yet
                            } else {
                                loading = false;
                            }
                        } else {
                            loading = false;
                        }
                    },
                    error: function(xhr, status, error) {
                        console.error("Error loading products:", error);
                        loading = false;
                    }
                });
            }

            fetchData();
    }

function createProductCard(item) {
    // 지역 이름 변환
    let convertedAreaName = item.areaname
        .replace('강원특별자치도', '강원')
        .replace('경기도', '경기')
        .replace('경상남도', '경남')
        .replace('경상북도', '경북')
        .replace('세종특별자치시', '세종')
        .replace('전라남도', '전남')
        .replace('전북특별자치도', '전북')
        .replace('제주도', '제주')
        .replace('충청남도', '충남')
        .replace('충청북도', '충북');

    // 카테고리 변환
    let category = "기타"; // 기본값을 "기타"로 설정
    if (item.cat3.startsWith('A0203')) {
        category = "체험";
    } else if (item.cat3.startsWith('A03')) {
        category = "레포츠";
    } else if (item.cat3.substring(0, 6) === 'A02080') {
        category = "공연/전시";
    } else if (item.cat3.substring(0, 6) === 'A02081') {
        category = "공연/전시";
    }

    var $card = $('<div>').addClass('product-card');

    $card.attr({
        'data-title': item.title || '',
        'data-category': category || '', // 변환된 카테고리 사용
        'data-areaname': convertedAreaName || '', // 변환된 지역 이름 사용
        'data-addr1': item.addr1 || '',
        'data-image': item.firstimage || '',
        'data-phone': item.tel || '',
        'data-info': item.info || '',
        'data-usetime': item.usetime || '',
        'data-opendate': item.opendate || '',
        'data-restdate': item.restdate || '',
        'data-price': item.price || ''
    });
    
     var categoryClass = 'bg-' + category.replace('/', '_');
     
    $card.html(
        '<div class="product-image">' +
            '<img src="' + (item.firstimage || '') + '" alt="상품 이미지">' +
        '</div>' +
        '<div class="product-category ' + categoryClass + '">' + category + '</div>' + // 변환된 카테고리 사용
        '<div class="product-details">' +
            '<h3 class="product-title">' + (item.title || '') + '</h3>' +
            '<span class="product-areaname">' + (convertedAreaName || '') + '</span>' + // 변환된 지역 이름 사용
        '</div>' +
        '<div class="hidden-homepage" style="display: none;">' + (item.homepage || '') + '</div>'
    );

    return $card;
}


    function applyProductCardStyles() {
        $('.product-card').each(function() {
            var category = $(this).data('category');
            var categoryClass = 'bg-' + category;
            $(this).find('.product-category').addClass(categoryClass);
        });
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

    // 카테고리 클릭 시
     $('.category-item').click(function(e) {
        e.preventDefault();
        currentCategory = $(this).data('category');
        page = 1;
        $('.category-item').removeClass('active');
        $(this).addClass('active');
        $('.product-grid').empty();
        loadMoreProducts();
    });


    // 검색 기능
    $('#search-button').click(function() {
        var searchTerm = $('#search-input').val().toLowerCase();
        $('.product-grid').empty(); // 기존 카드 비우기

        $.ajax({
            url: '${pageContext.request.contextPath}/products/searchProducts',
            method: 'GET',
            data: { searchTerm: searchTerm },
            dataType: 'json',
            success: function(data) {
                data.forEach(function(item) {
                    var productCard = createProductCard(item);
                    $('.product-grid').append(productCard);
                });
                 applyProductCardStyles();
            },
            error: function(xhr, status, error) {
                console.error("Error searching products:", error);
            }
        });
    });


    // 모달 표시
    var modal = $('#productModal');
    var span = $('.close');

    $(document).on('click', '.product-card', function() {
        var image = $(this).data('image');
        var title = $(this).data('title');
        var addr1 = $(this).data('addr1');
        var phone = $(this).data('phone'); 
        var info = $(this).data('info');
        var usetime = $(this).data('usetime');
        var opendate = $(this).data('opendate');
        var restdate = $(this).data('restdate');
        var price = $(this).data('price');
        var homepage = $(this).find('.hidden-homepage').html();

        function setHomepageLink(homepage) {
            if (homepage && homepage.trim() !== '') {
                var tempDiv = $('<div>').html(homepage);
                var link = tempDiv.find('a');
                if (link.length > 0) {
                    $('#productModal .modal-homepage-links').html('<a href="' + link.attr('href') + '" target="_blank">홈페이지 방문하기</a>');
                } else {
                    $('#productModal .modal-homepage-links').html(homepage);
                }
            } else {
                $('#productModal .modal-homepage-links').html('');
            }
        }

        function setModalInfo(selector, text) {
            if (text) {
                $(selector).text(text);
            } else {
                $(selector).text('');
            }
        }

        $('#productModal .modal-image').attr('src', image);
        $('#productModal .modal-title').text(title);
        setModalInfo('#productModal .modal-info.addr1', addr1);
        setModalInfo('#productModal .modal-info.phone', phone ? phone.replace(/<br>/g, '<br>') : '');
        setModalInfo('#productModal .modal-info.info', info);
        setModalInfo('#productModal .modal-info.opendate', opendate ? "기간: " + opendate : '');
        setModalInfo('#productModal .modal-info.usetime', usetime ? "이용가능시간: " + usetime : '');
        setModalInfo('#productModal .modal-info.restdate', restdate ? "쉬는날: " + restdate : '');
        setModalInfo('#productModal .modal-info.price', price ? "요금정보: " + price : '');

        setHomepageLink(homepage);

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

     loadMoreProducts();
});
</script>

</body>
</html>
