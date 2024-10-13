// course-item 클릭 시 contentid를 서버로 보내 상세 정보 가져오기
$(document).on('click', '.course-item', function () {
    var contentId = $(this).data('contentid');  // 클릭한 아이템의 contentid 가져오기

    // AJAX 요청을 통해 contentid에 맞는 데이터 가져오기
    $.ajax({
        url: '/cds/tourCourse/getCourseDetails.do',  // 절대 경로로 설정
        type: 'GET',
        data: { contentid: contentId },  // contentid 전송
        dataType: 'json',
        success: function (data) {
            if (data) {
                // 모달에 데이터 채우기
                $('.coursenamebox .first-image').attr('src', data.first_image).css('display', 'block');
                $('.coursenamebox .course-title').text(data.title);
                $('.tagbox .tag:nth-child(1) p').text(data.distance || '거리 정보 없음');
                $('.tagbox .tag:nth-child(2) p').text(data.taketime || '소요 시간 정보 없음');
                $('.overview').text(data.overview || '설명 없음');

                // 모달 열기
                $('.coursemodal').addClass('show');

                // 지도 초기화
                if (data.map_x && data.map_y) {
                    var mapContainer = document.getElementById('map'); // 지도를 표시할 div 
                    var mapOption = {
                        center: new kakao.maps.LatLng(data.map_y, data.map_x), // 지도의 중심좌표
                        level: 3 // 지도의 확대 레벨
                    };
                    var map = new kakao.maps.Map(mapContainer, mapOption); // 지도 생성 및 객체 리턴

                    // 마커 표시
                    var markerPosition  = new kakao.maps.LatLng(data.map_y, data.map_x); 
                    var marker = new kakao.maps.Marker({
                        position: markerPosition
                    });
                    marker.setMap(map);

                    // InfoWindow 생성
                    var infoContent = '<div class="info-window">' + data.title + '</div>'; // 표시할 내용
                    var infoWindow = new kakao.maps.InfoWindow({
                        content: infoContent, // 표시할 내용
                    });

                    // 마커 클릭 시 InfoWindow 열기
                    infoWindow.open(map, marker);
                } else {
                    console.error("좌표 정보가 부족합니다.");
                }
            } else {
                alert('해당 코스 정보를 가져올 수 없습니다.');
            }
        },
        error: function (xhr, status, error) {
            console.error('데이터 가져오기 실패: ', error, xhr.responseText);
            alert('코스 정보를 가져오는 중 오류가 발생했습니다.');
        }
    });
});

// 모달 닫기 버튼 클릭 시 모달 닫기
$(document).on('click', '.close-btn', function () {
    $('.coursemodal').removeClass('show');
});

// 모달 바깥 영역 클릭 시 모달 닫기
$(document).on('click', function (event) {
    // 클릭한 대상이 모달 바깥일 경우에만 모달 닫기
    if ($(event.target).closest('.coursemodal').length === 0 && $('.coursemodal').is(':visible')) {
        $('.coursemodal').removeClass('show');
    }
});

// ESC 키를 눌렀을 때 모달 닫기
$(document).on('keydown', function (event) {
    if (event.key === "Escape" && $('.coursemodal').is(':visible')) {
        $('.coursemodal').removeClass('show');
    }
});

// 카테고리 링크 클릭 시 해당 섹션으로 스크롤 이동
$(document).on('click', '.fixed-category-bar .category-link', function () {
    var targetClass = $(this).data('target');
    var targetElement = $(targetClass);

    if (targetElement.length) {
        var scrollTo = targetElement.position().top + $('.coursemodal').scrollTop() - $('.coursemodal').offset().top;
        $('.coursemodal').animate({
            scrollTop: scrollTo
        }, 500);
    }
});
