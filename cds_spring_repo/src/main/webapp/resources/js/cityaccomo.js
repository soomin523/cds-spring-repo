$(document).ready(function () {
    let areaCode = null; // 전역 변수로 설정하여 무한 스크롤에서 참조 가능하게
    let cat3Code = 'all'; // 초기값으로 전체 선택
    let page = 1; // 초기 페이지 번호
    let pageSize = 10; // 처음에 10개만 불러옴
    let loading = false; // 로딩 상태 확인
    let moreData = true; // 더 불러올 데이터가 있는지 여부
    let loadedContentIds = new Set(); // 이미 로드된 콘텐츠 ID 저장 (중복 방지)
	
	
	$('.accomobefore').on('click',function(){
		location.href ='/accommodations/accommo.do';
	});
	
    // 1. 지역별 배경 이미지 설정
    $.ajax({
        url: '/cds/accommodations/accoImg.do', // 정확한 경로 확인 필요
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            // 지역 코드에 맞는 배경 이미지 설정
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

    // 2. 지역을 클릭하면 해당 지역 숙소 데이터를 무한 스크롤로 로드
    $('.region-btn').on('click', function () {
        areaCode = $(this).data('region'); // 클릭한 지역의 코드 가져오기
        console.log("선택한 지역 코드:", areaCode); // 선택한 지역 코드 콘솔 로그

        // 기존 데이터를 비우고 초기화
        $('#citymiddle').empty();
        page = 1; // 페이지 초기화
        moreData = true; // 더 불러올 데이터가 있는지 여부 초기화
        loadedContentIds.clear(); // 이미 로드된 콘텐츠 ID 초기화
		cat3Code='all';
		
		
        // 클릭 시 첫 번째 페이지 데이터를 로드 (예: 처음 10개)
        loadAccommodations();
        $('#atypeline').show();
        $('.atype').show();
    });

    // 3. cat3 버튼 클릭 이벤트 처리
    $('.atype-btn').on('click', function () {
        cat3Code = $(this).data('cat3'); // 클릭한 버튼의 cat3 값을 가져오기
        console.log("선택한 cat3 코드:", cat3Code); // 선택한 cat3 코드 콘솔 로그

        // 기존 데이터를 비우고 초기화
        $('#citymiddle').empty();
        page = 1; // 페이지 초기화
        moreData = true; // 더 불러올 데이터가 있는지 여부 초기화
        loadedContentIds.clear(); // 이미 로드된 콘텐츠 ID 초기화

        // 클릭 시 첫 번째 페이지 데이터를 로드
        loadAccommodations();
    });

    // 4. 무한 스크롤 기능 추가
    $(window).scroll(function () {
        if ($(window).scrollTop() + $(window).height() >= $(document).height() - 100 && !loading && moreData) {
            page++;
            loadAccommodations();
        }
    });

    // 5. cat3에 해당하는 숙소 타입 텍스트를 반환하는 함수
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

    // 6. 숙소 데이터를 로드하는 함수 (처음 로드와 무한 스크롤 시 모두 사용)
    function loadAccommodations() {
        if (!moreData) return; // 더 불러올 데이터가 없으면 중단

        loading = true; // 로딩 상태 설정

        $.ajax({
            url: '/cds/accommodations/getRegionAccommodations.do', // Controller에 설정된 URL
            type: 'GET',
            data: { area_code: areaCode, cat3: cat3Code, page: page, pageSize: pageSize }, // 지역 코드와 cat3 코드, 페이지 번호, 페이지 크기 전송
            dataType: 'json',
            success: function (data) {
    if (data.length > 0) {
        let middlebox = $('<div class="middlebox"></div>'); // 새로운 middlebox 생성
        data.forEach(function (item, index) {
            // 중복된 content_id 체크
            if (!loadedContentIds.has(item.content_id)) {
                loadedContentIds.add(item.content_id); // content_id 추가 (중복 방지)

                let title = item.title.replace(/\s*\[.*?\]\s*/g, ''); // title에서 [한국관광 품질인증/Korea Quality] 제거
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

                // 두 개씩 채워질 때마다 middlebox를 citymiddle에 append
                if ((index + 1) % 2 === 0 || index === data.length - 1) {
                    $('#citymiddle').append(middlebox);
                    middlebox = $('<div class="middlebox"></div>'); // 새로운 middlebox 생성
                }
            }
        });

        // 더 이상 불러올 데이터가 없는 경우
        if (data.length < pageSize) {
            moreData = false; // 더 불러올 데이터가 없음을 표시
        }
    } else {
        // 조건에 맞는 데이터가 없을 때 "숙소정보가 없습니다." 메시지를 표시
        $('#citymiddle').html('<p>숙소정보가 없습니다.</p>');
        moreData = false; // 더 이상 데이터가 없음을 표시
    }

    loading = false; // 로딩 상태 해제
},


            error: function () {
                alert('데이터를 불러오는데 실패했습니다.');
                loading = false; // 로딩 상태 해제
            }
        });
    }
});
