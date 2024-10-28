$(document).ready(function () {
    let areaCode = getParameterByName('areaCode'); // URL 파라미터에서 areaCode를 가져옴
    let cat3Code = getParameterByName('cat3') || 'all'; // URL 파라미터에서 cat3를 가져옴, 없으면 전체 선택
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

    // 초기 로드 시 areaCode 또는 cat3Code가 있으면 숙소 로드
    if (areaCode || cat3Code) {
        loadAccommodations(); // 초기 로드
    }

    // 이전 페이지로 돌아가기 버튼
    $('.accomobefore').on('click', function () {
        location.href = '../accommodations/accommo.do';
    });

    // 검색 입력 필터링
    $('#searchInput').on('input', function () {
        searchValue = $(this).val().toLowerCase();
        resetAndLoadAccommodations(); // 검색어 입력 시 기존 데이터를 초기화하고 새로 로드
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
    
    document.querySelectorAll('.atype-btn').forEach((button) => {
    button.addEventListener('click', function () {
        // 모든 버튼에서 active 클래스 제거
        document.querySelectorAll('.atype-btn').forEach((btn) => btn.classList.remove('active'));

        // 클릭한 버튼에 active 클래스 추가
        this.classList.add('active');
    });
});
    

    // 지역 버튼 클릭 시 지역 숙소 데이터 로드
    $('.region-btn').on('click', function () {
        areaCode = $(this).data('region');
        history.pushState(null, '', '?areaCode=' + areaCode);
        resetAndLoadAccommodations();
    });

    // 카테고리 버튼 클릭 시 해당 숙소 카테고리 데이터 로드
    $('.atype-btn').on('click', function () {
        cat3Code = $(this).data('cat3');
        history.pushState(null, '', '?areaCode=' + areaCode + '&cat3=' + cat3Code);
        resetAndLoadAccommodations();
    });

    // 로드된 숙소 데이터 초기화 및 재로드
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

        // 스크롤 시 '위로 가기' 버튼 표시
        if ($(window).scrollTop() > 300) {
            $('#scrollToTopBtn').fadeIn(); // 스크롤 시 버튼을 서서히 나타나게 함
        } else {
            $('#scrollToTopBtn').fadeOut(); // 스크롤 상단에 있으면 버튼을 숨김
        }
    });

    // 스크롤 버튼 클릭 시 페이지 상단으로 스크롤
    $('#scrollToTopBtn').on('click', function () {
        $('html, body').animate({ scrollTop: 0 }, 'slow');
    });

    // 숙소 데이터를 로드하는 함수
    function loadAccommodations() {
        if (!moreData || loading) return;

        loading = true;

        $.ajax({
            url: '/cds/accommodations/getRegionAccommodations.do',
            type: 'GET',
            data: { 
                area_code: areaCode, 
                cat3: cat3Code, 
                page: page, 
                pageSize: pageSize,
                search: searchValue // 서버에 검색어를 추가로 전달
            },
            dataType: 'json',
            success: function (data) {
                if (data.length > 0) {
                    let middlebox = $('<div class="middlebox"></div>');
                    let addedData = false;

                    data.forEach(function (item, index) {
                        let title = item.title;
                        let idx = title.indexOf('[');

                        // 이미 로드된 콘텐츠가 아니면 추가
                        if (!loadedContentIds.has(item.content_id)) {
                            loadedContentIds.add(item.content_id);
                            addedData = true;

                            let accobox = `
                                <div class="accobox" data-contentid="${item.content_id}">
                                    <div class="imgbox"><img src="${item.first_image}" alt="${item.title}"></div>
                                    <div class="info">
                                        <h3>${(idx === -1) ? title : title.substring(0, idx)}</h3>
                                        <p>(${item.addr1})</p>
                                        <h4 class="acctag">${cat3Text(item.cat3)}</h4>
                                    </div>
                                </div>
                            `;
                            $('#citymiddle').append(accobox);
                        }
                    });
                    console.log('Data rendered: ' + data.length);

                    // 더 이상 불러올 데이터가 없으면 처리
                    if (!addedData || data.length < pageSize) {
                        moreData = false;
                    }

                    // 필터링 후 데이터가 없으면 메시지 표시
                    if (!addedData && $('#citymiddle .accobox').length === 0) {
                        $('#citymiddle').append('<div class="acconodatabox"><p class="acconodata">검색 결과가 없습니다.</p></div>');
                    }
                } else {
                    $('#citymiddle').append('<div class="acconodatabox"><p class="acconodata">숙소 정보가 없습니다.</p></div>');
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

    // 카테고리 코드 텍스트 변환
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
