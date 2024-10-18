$(document).ready(function () {
    let areaCode = getParameterByName('areaCode'); // URL 파라미터에서 areaCode를 가져옴
    let cat3Code = 'all'; // 초기값으로 전체 선택
    let page = 1; // 초기 페이지 번호
    let pageSize = 8; // 페이지 크기
    let loading = false; // 로딩 상태 확인
    let moreData = true; // 더 불러올 데이터가 있는지 여부
    let loadedContentIds = new Set(); // 이미 로드된 콘텐츠 ID 저장 (중복 방지)
    let searchValue = ''; // 검색어를 저장할 변수

    // URL 파라미터에서 값을 추출하는 함수
    function getParameterByName(name) {
        let url = window.location.href;
        name = name.replace(/[\[\]]/g, '\\$&');
        let regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
            results = regex.exec(url);
        if (!results) return null;
        if (!results[2]) return '';
        return decodeURIComponent(results[2].replace(/\+/g, ' '));
    }

    if (areaCode) {
        loadAccommodations(); // 초기 로드
    }

    $('.accomobefore').on('click', function () {
        location.href = '../accommodations/accommo.do';
    });

    $('#searchInput').on('input', function () {
        searchValue = $(this).val().toLowerCase();
        page = 1; // 페이지를 처음부터 다시 로드
        moreData = true; // 더 불러올 데이터가 있다고 설정
        loadedContentIds.clear(); // 중복된 콘텐츠 ID 초기화
        $('#citymiddle').empty(); // 기존 리스트 초기화
        loadAccommodations(); // 새로 로드 및 필터링
    });

    // 지역별 배경 이미지 설정
    $.ajax({
        url: '/cds/accommodations/accoImg.do',
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            $('.accodesbox div[data-region]').each(function () {
                let regionCode = $(this).data('region');
                let matchingData = data.find(item => item.area_code == regionCode);

                if (matchingData) {
                    let imageUrl = matchingData.first_image ? matchingData.first_image : '/path/to/default-image.jpg';
                    $(this).css('background-image', `url(${imageUrl})`);
                }
            });
        },
        error: function () {
            alert('데이터를 가져오는데 실패했습니다.');
        }
    });

    // 지역 버튼 클릭 시 로드
    $('.region-btn').on('click', function () {
        areaCode = $(this).data('region');
        history.pushState(null, '', '?areaCode=' + areaCode);
        resetAndLoadAccommodations();
    });

    // 카테고리 버튼 클릭 시 로드
    $('.atype-btn').on('click', function () {
        cat3Code = $(this).data('cat3');
        resetAndLoadAccommodations();
    });

    function resetAndLoadAccommodations() {
        $('#citymiddle').empty();
        page = 1;
        moreData = true;
        loadedContentIds.clear();
        loadAccommodations();
    }

    // 무한 스크롤 처리
    $(window).scroll(function () {
        if ($(window).scrollTop() + $(window).height() >= $(document).height() - 100 && !loading && moreData) {
            page++;
            loadAccommodations();
        }
    });

    $('#scrollToTopBtn').on('click', function () {
        $('html, body').animate({ scrollTop: 0 }, 'slow');
    });

    // 데이터가 있을 시 박스형으로 데이터 박스 생성하기
    function loadAccommodations() {
        if (!moreData || loading) return;

        loading = true;

        $.ajax({
            url: '/cds/accommodations/getRegionAccommodations.do',
            type: 'GET',
            data: { area_code: areaCode, cat3: cat3Code, page: page, pageSize: pageSize },
            dataType: 'json',
            success: function (data) {
                if (data.length > 0) {
                    let middlebox = $('<div class="middlebox"></div>');
                    let addedData = false;
					
					
                    data.forEach(function (item, index) {
                        // 검색어 필터링
                        let title = item.title
                        let idx = title.indexOf('[');
                        //title = title.substring(0, idx);
                        
                        //console.log(title);
                        
                        let address = item.addr1.toLowerCase();
                        if (!loadedContentIds.has(item.content_id) &&
                            (title.includes(searchValue) || address.includes(searchValue) || searchValue === '')) {
                            
                            let title = item.title.replace()
                            loadedContentIds.add(item.content_id);
                            addedData = true;

                            let accobox = `
                                <div class="accobox" data-contentid="${item.content_id}">
                                    <div class="imgbox"><img src="${item.first_image}" alt="${item.title}"></div>
                                    <div class="info">
                                        <h3>${(idx===-1) ? title : title.substring(0, idx)}</h3>
                                        <p>(${item.addr1})</p>
                                        <h4 class="acctag">${cat3Text(item.cat3)}</h4>
                                    </div>
                                </div>
                            `;

							$('#citymiddle').append(accobox);
                        }
                    });

                    // 데이터가 추가되지 않았거나 페이지 크기보다 적은 경우
                    if (!addedData || data.length < pageSize) {
                        moreData = false;
                    }

                    if (addedData) {
                        page++;
                    }

                    // 필터링 후 데이터가 없으면 메시지 표시
                    if (!addedData && $('#citymiddle .accobox').length === 0) {
                        $('#citymiddle').append('<div class="acconodatabox"><p class="acconodata">검색 결과가 없습니다.</p></div>');
                    }
                } else {
                    $('#citymiddle').append('<div class="acconodatabox"><p class="acconodata">숙소정보가 없습니다.</p></div>');
                    moreData = false;
                }

                loading = false;
            },
            error: function () {
                alert('데이터를 불러오는데 실패했습니다.');
                loading = false;
            }
        });
    }

    function cat3Text(cat3) {
        var cat3Text = {
            "B02010100": "호텔",
            "B02010900": "모텔",
            "B02010600": "유스호스텔",
            "A02020200": "리조트",
            "B02010500": "콘도",
            "B02010700": "팬션",
            "B02011600": "한옥",
            "B02011100": "게스트하우스"
        };
        return cat3Text[cat3] || "기타";
    }
});
