<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9dc9962fd8d9c313d5ca5a57212228ab"></script>

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
<%@ include file="../main/header2.jsp"%>
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
                    <div class="location-icon">📍전체</div>
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


<script>
$(document).ready(function() {
    var page = 2;
    var loading = false;
    var pageSize = 12;
    var currentCategory = 'all';
    var currentLocation = '전체';
    var isSearchMode = false; // 검색 모드 상태
    var currentSearchTerm = ''; // 검색어 상태

    function convertCat3ToCategory(cat3) {
    if (cat3.startsWith('A0203')) return '체험';
    if (cat3.startsWith('A03')) return '레포츠';
    if (cat3.startsWith('A02080')) return '공연/전시';
    return '기타';
}
        // Show/hide dropdown
    $('.location-icon').click(function() {
        $('.location-dropdown').toggle();
    });

        // Location selection
    $('.location-dropdown div').click(function() {
        if(loading) return;
        currentLocation = $(this).data('location');
        $('.location-icon').text('📍' + currentLocation);
        $('.location-dropdown').hide();
        $('.product-grid').empty();
        isSearchMode = false; // Reset search mode
        currentSearchTerm = '';
         $('#search-input').val('');
        page = 1;
        loadMoreProducts();
    });

    function loadMoreProducts() {
        if (loading) return;
        loading = true;

         $('#no-more-products').hide();

         let cardCount = 0;

         function fetchData() {
            $.ajax({
                url: '${pageContext.request.contextPath}/products/getMoreProducts',
                method: 'GET',
                data: { page: page, size: pageSize, category: currentCategory, 
                     location: currentLocation, searchTerm: currentSearchTerm },
                dataType: 'json',
                success: function(data) {
                    console.log(data);
                    if (data.length > 0) {
                        data.forEach(function(item) {
                            var itemCategory = convertCat3ToCategory(item.cat3);
                            // Convert area name before comparison
                           let convertedAreaName = item.areaname || ''; // Set default empty string if null

                            if (convertedAreaName.includes('강원특별자치도')) convertedAreaName = '강원';
                            if (convertedAreaName.includes('경기도')) convertedAreaName = '경기';
                            if (convertedAreaName.includes('경상남도')) convertedAreaName = '경남';
                            if (convertedAreaName.includes('경상북도')) convertedAreaName = '경북';
                            if (convertedAreaName.includes('세종특별자치시')) convertedAreaName = '세종';
                            if (convertedAreaName.includes('전라남도')) convertedAreaName = '전남';
                            if (convertedAreaName.includes('전북특별자치도')) convertedAreaName = '전북';
                            if (convertedAreaName.includes('제주도')) convertedAreaName = '제주';
                            if (convertedAreaName.includes('충청남도')) convertedAreaName = '충남';
                            if (convertedAreaName.includes('충청북도')) convertedAreaName = '충북';

                            if ((currentCategory === 'all' || itemCategory === currentCategory) &&
                                (currentLocation === '전체' || convertedAreaName === currentLocation)) {
                                var productCard = createProductCard(item);
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
                        if (data.length < pageSize) {
                            $('#no-more-products').show();
                        }
                    } else {
                        $('#no-more-products').show();
                    }
                        loading = false;
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
    } else if (item.cat3.startsWith('A02080')) {
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
        'data-price': item.price || '',
        'data-mapx': item.mapx || '',
        'data-mapy': item.mapy || '',
        'data-overview': item.overview || '',
        'data-infoname1': item.infoname1 || '',
        'data-infotext1': item.infotext1 || '',
        'data-infoname2': item.infoname2 || '',
        'data-infotext2': item.infotext2 || '',
        'data-infoname3': item.infoname3 || '',
        'data-infotext3': item.infotext3 || '',
        'data-infoname4': item.infoname4 || '',
        'data-infotext4': item.infotext4 || ''
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

    // 스크롤 이벤트

        let throttleTimeout;

        $(window).scroll(function() {
             if(isSearchMode) {
            return; // 검색 모드일 때는 추가 로딩 중지
              }

            clearTimeout(throttleTimeout);
            throttleTimeout = setTimeout(function() {
                var scrollPosition = $(window).scrollTop() + $(window).height();
                var documentHeight = $(document).height();
                var buffer = 3000;
                if (!loading && (documentHeight - scrollPosition) <= buffer) {
                    loadMoreProducts();
                }
            }, 300);
        });

    $('#scrollToTop').click(function() {
        $('html, body').animate({scrollTop: 0}, 'slow');
    });



    // 카테고리 클릭 시
     $('.category-item').click(function(e) {
        if(loading) return;
        e.preventDefault();
        currentCategory = $(this).data('category');
        page = 1;
        $('.category-item').removeClass('active');
        $(this).addClass('active');
        $('.product-grid').empty();
        isSearchMode = false; // Reset search mode
        currentSearchTerm = '';
         $('#search-input').val(''); // Clear search input
        loadMoreProducts();
    });
          // 검색 기능
          function performSearch() {
              var searchTerm = $('#search-input').val().toLowerCase();
              if(searchTerm) {
                  isSearchMode = true;
                  currentSearchTerm = searchTerm;
              } else {
                  isSearchMode = false;
                  currentSearchTerm = '';
              }
        
              $('.product-grid').empty();
        
              $.ajax({
                  url: '${pageContext.request.contextPath}/products/searchProducts',
                  method: 'GET',
                  data: { 
                      searchTerm: searchTerm,
                      category: currentCategory,
                      location: currentLocation
                  },
                  dataType: 'json',
                  success: function(data) {
                      data.forEach(function(item) {
                          var itemCategory = convertCat3ToCategory(item.cat3);
                          let convertedAreaName = item.areaname || '';
                    
                          // 지역명 변환
                          if (convertedAreaName.includes('강원특별자치도')) convertedAreaName = '강원';
                          if (convertedAreaName.includes('경기도')) convertedAreaName = '경기';
                          if (convertedAreaName.includes('경상남도')) convertedAreaName = '경남';
                          if (convertedAreaName.includes('경상북도')) convertedAreaName = '경북';
                          if (convertedAreaName.includes('세종특별자치시')) convertedAreaName = '세종';
                          if (convertedAreaName.includes('전라남도')) convertedAreaName = '전남';
                          if (convertedAreaName.includes('전북특별자치도')) convertedAreaName = '전북';
                          if (convertedAreaName.includes('제주도')) convertedAreaName = '제주';
                          if (convertedAreaName.includes('충청남도')) convertedAreaName = '충남';
                          if (convertedAreaName.includes('충청북도')) convertedAreaName = '충북';

                          // 카테고리와 지역 필터 적용
                          if ((currentCategory === 'all' || itemCategory === currentCategory) &&
                              (currentLocation === '전체' || convertedAreaName === currentLocation)) {
                              var productCard = createProductCard(item);
                              $('.product-grid').append(productCard);
                          }
                      });
                      applyProductCardStyles();
                      $('#no-more-products').show();
                  },
                  error: function(xhr, status, error) {
                      console.error("Error searching products:", error);
                  }
              });
          }
      
    // 검색 버튼 클릭 이벤트
    $('#search-button').click(function() {
        performSearch();
    });

    // 엔터키 이벤트 추가
    $('#search-input').keypress(function(e) {
        if (e.which === 13) { // 엔터키 코드
            e.preventDefault();
            performSearch();
        }
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
        var mapx = $(this).data('mapx');
        var mapy = $(this).data('mapy');
        var overview = $(this).data('overview');
        var infoname1 = $(this).data('infoname1');
        var infotext1 = $(this).data('infotext1');
        var infoname2 = $(this).data('infoname2');
        var infotext2 = $(this).data('infotext2');
        var infoname3 = $(this).data('infoname3');
        var infotext3 = $(this).data('infotext3');
        var infoname4 = $(this).data('infoname4');
        var infotext4 = $(this).data('infotext4'); 
            
            setInfoText(infoname1, infotext1, '#productModal .modal-info.info1');
            setInfoText(infoname2, infotext2, '#productModal .modal-info.info2');
            setInfoText(infoname3, infotext3, '#productModal .modal-info.info3');
            setInfoText(infoname4, infotext4, '#productModal .modal-info.info4');

        // info 데이터 표시 함수
        function setInfoText(name, text, selector) {
            if (name && text) {
                 $(selector).html(name + ' : ' + text.replace(/<br>/g, '<br>')).show();
            } else {
                $(selector).hide();
            }
        }

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
        setModalInfo('#productModal .modal-info.price', price ? "요금정보: " + price.replace(/<br>/g, '<br>') : '');
            
                if (overview) {
            $('#productModal .modal-info.overview').text(overview).show();
        } else {
            $('#productModal .modal-info.overview').hide();
        }

        setHomepageLink(homepage);

        modal.fadeIn(300, function() {
            var container = document.getElementById('map');
            var options = {
                center: new kakao.maps.LatLng(mapy, mapx),
                level: 3
            };
            var map = new kakao.maps.Map(container, options);
            
            var markerPosition = new kakao.maps.LatLng(mapy, mapx);
            var marker = new kakao.maps.Marker({
                position: markerPosition
            });
            marker.setMap(map);
            
            // Trigger map resize to ensure proper rendering
            setTimeout(function() {
                map.relayout();
            }, 0);
        });
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
