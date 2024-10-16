$(document).ready(function () {
    let areaCode = getParameterByName('areaCode'); // URL 파라미터에서 areaCode를 가져옴
    let cat3Code = 'all'; // 초기값으로 전체 선택
    let page = 1; // 초기 페이지 번호
    let pageSize = 10; // 처음에 10개만 불러옴
    let loading = false; // 로딩 상태 확인
    let moreData = true; // 더 불러올 데이터가 있는지 여부
    let loadedContentIds = new Set(); // 이미 로드된 콘텐츠 ID 저장 (중복 방지)

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
        let searchValue = $(this).val().toLowerCase();
        filterAccommodations(searchValue);
    });

    function filterAccommodations(searchValue) {
        $('.accobox').each(function () {
            let title = $(this).find('h3').text().toLowerCase();
            let address = $(this).find('p').text().toLowerCase();

            // 제목이나 주소에 검색어가 포함되어 있으면 표시, 그렇지 않으면 숨김
            if (title.includes(searchValue) || address.includes(searchValue)) {
                $(this).show();
            } else {
                $(this).hide();
            }
        });
    }

    // 1. 지역별 배경 이미지 설정
    $.ajax({
        url: '/cds/accommodations/accoImg.do', // 정확한 경로 확인 필요
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

    // 2. 숙소 데이터를 무한 스크롤로 로드
    $('.region-btn').on('click', function () {
        areaCode = $(this).data('region');
        console.log("선택한 지역 코드:", areaCode);

        $('#citymiddle').empty();
        page = 1;
        moreData = true;
        loadedContentIds.clear();
        cat3Code = 'all';

        loadAccommodations();
    });

    $('.atype-btn').on('click', function () {
        cat3Code = $(this).data('cat3');
        console.log("선택한 cat3 코드:", cat3Code);

        $('#citymiddle').empty();
        page = 1;
        moreData = true;
        loadedContentIds.clear();

        loadAccommodations();
    });

    $(window).scroll(function () {
        if ($(window).scrollTop() + $(window).height() >= $(document).height() - 100 && !loading && moreData) {
            page++;
            loadAccommodations();
        }
    });


//데이터가 있을 시 박스형으로 데이터 박스 생성하기
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
                        if (!loadedContentIds.has(item.content_id)) {
                            loadedContentIds.add(item.content_id);
                            addedData = true;

                            let title = item.title.replace(/\s*\[.*?\]\s*/g, '');
                            middlebox.append(`
                                <div class="accobox">
                                    <div class="imgbox"><img src="${item.first_image}" alt="${item.title}"></div>
                                    <div class="info">
                                        <h3>${title}</h3>
                                        <p>(${item.addr1})</p>
                                        <h4 class="acctag">${cat3Text(item.cat3)}</h4>
                                    </div>
                                </div>
                            `);

                            if ((index + 1) % 2 === 0 || index === data.length - 1) {
                                $('#citymiddle').append(middlebox);
                                middlebox = $('<div class="middlebox"></div>');
                            }
                        }
                    });

                    if (!addedData && data.length < pageSize) {
                        moreData = false;
                    }

                    if (addedData) {
                        page++;
                    }
                } else {
                    $('#citymiddle').append('<p style="text-align: center; font-size: 18px; font-weight: bold; margin-top: 20px;">숙소정보가 없습니다.</p>');
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
