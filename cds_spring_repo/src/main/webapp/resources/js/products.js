
$(document).ready(function() {

    var page = 1;
    var loading = false;
    var pageSize = 12;
    var currentCategory = 'all';
    var currentLocation = '전체';
    var isSearchMode = false; // 검색 모드 상태
    var currentSearchTerm = ''; // 검색어 상태
    const urlParams = new URLSearchParams(window.location.search);
    const contentId = urlParams.get('contentid');

    function convertCat3ToCategory(cat3) {
    if (cat3.startsWith('A0203')) return '체험';
    if (cat3.startsWith('A03')) return '레포츠';
    if (cat3.startsWith('A02080')) return '공연/전시';
    return '기타';
    }

    function convertAreaName(areaname) {
        if (!areaname) return '';
        
        const areaMapping = {
            '강원특별자치도': '강원',
            '경기도': '경기',
            '경상남도': '경남',
            '경상북도': '경북',
            '세종특별자치시': '세종',
            '전라남도': '전남',
            '전북특별자치도': '전북',
            '제주도': '제주',
            '충청남도': '충남',
            '충청북도': '충북'
        };
    
        for (const [fullName, shortName] of Object.entries(areaMapping)) {
            if (areaname.includes(fullName)) {
                return shortName;
            }
        }
        return areaname;
    }

    
    if (contentId) {
        $('.product-grid').empty();
        
        $.ajax({
            url: '/cds/products/getProducts',
            method: 'GET',
            dataType: 'json',
            data: { 
                contentid: contentId,
                category: currentCategory,
                location: currentLocation
            },
            success: function(data) {
                if (Array.isArray(data)) {
                    data.forEach(function(item) {
                        var itemCategory = convertCat3ToCategory(item.cat3);
                        let convertedAreaName = convertAreaName(item.areaname);
    
                        if ((currentCategory === 'all' || itemCategory === currentCategory) &&
                            (currentLocation === '전체' || convertedAreaName === currentLocation)) {
                            var productCard = createProductCard(item);
                            $('.product-grid').append(productCard);
                        }
                    });
                } else {
                    var productCard = createProductCard(data);
                    $('.product-grid').append(productCard);
                }
                applyProductCardStyles();
                $('#no-more-products').show();
                
                // 첫 번째 상품 카드의 클릭 이벤트 트리거
                $('.product-card:first').trigger('click');
            },
            error: function(xhr, status, error) {
                console.error("Error loading products:", error);
            }
        });
    }

    const LoadingHandler = {
    show: function() {
        document.querySelector('.loading-spinner').style.display = 'block';
    },
    
    hide: function() {
        document.querySelector('.loading-spinner').style.display = 'none';
    }
};

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
                url: '/cds/products/getMoreProducts',
                method: 'GET',
                data: { page: page, size: pageSize, category: currentCategory, 
                     location: currentLocation, searchTerm: currentSearchTerm },
                dataType: 'json',
                success: function(data) {
                    console.log(data);
                    if (data.length > 0) {
                        data.forEach(function(item) {
                            var itemCategory = convertCat3ToCategory(item.cat3);
                            let convertedAreaName = convertAreaName(item.areaname);

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
                             LoadingHandler.show();
                            fetchData(); // Fetch more data if we don't have 12 cards yet
                        } else {
                            loading = false;
                            LoadingHandler.hide();
                        }
                        if (data.length < pageSize) {
                             setTimeout(function() {
                                $('#no-more-products').show();
                            }, 1000);
                            loading = false;
                            LoadingHandler.hide();
                        }
                    } else {
                        $('#no-more-products').show();
                        LoadingHandler.hide();
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
    let convertedAreaName = convertAreaName(item.areaname);

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
                  url: '/cds/products/searchProducts',
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
                          let convertedAreaName = convertAreaName(item.areaname);

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
                text = text.replace(/<a[^>]*>.*?<\/a>/g, '');
                $(selector).html(text.replace(/<br>/g, '<br>'));
            } else {
                $(selector).html('');
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
            $('#productModal .modal-info.overview').html(overview.replace(/<br>/g, '<br>')).show();
        } else {
            $('#productModal .modal-info.overview').hide();
        }
        

        setHomepageLink(homepage);
		
        modal.fadeIn(300, function() {
            setTimeout(function() {
        $('#productModal').scrollTop(190);
       
    }, 100);
            var container = document.getElementById('map');
            var options = {
                center: new kakao.maps.LatLng(mapy, mapx),
                level: 3,
               scrollwheel: false
            };
            var map = new kakao.maps.Map(container, options);
            
		  // 지도 컨트롤 비활성화
		    map.setDraggable(false);
		    map.setZoomable(false);
		    
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


