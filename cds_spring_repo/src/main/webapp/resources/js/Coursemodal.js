$(document).ready(function () {
    let contentId = getParameterByName('contentId');
    let markers = [];
    let infoWindows = []; // 정보 창을 저장할 배열
    let openInfoWindow = null; // 현재 열린 정보창을 저장
	checkLoginStatus();
	
    function getParameterByName(name) {
        let url = window.location.href;
        name = name.replace(/[\[\]]/g, '\\$&');
        let regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
            results = regex.exec(url);
        if (!results) return null;
        if (!results[2]) return '';
        return decodeURIComponent(results[2].replace(/\+/g, ' '));
    }

    // 로그인 상태 확인
    function checkLoginStatus() {
        $.ajax({
            url: '/cds/tourCourse/checkLoginStatus.do',
            type: 'POST',
            success: function (response) {
                if (response === "belogin") {
                    $('#add-comment').prop('disabled', true);
                    $('#new-comment').attr('placeholder', '로그인 후 이용 가능합니다.');
                    $('#new-comment').prop('disabled', true);
                }
            },
            error: function () {
                console.error('로그인 상태 확인 중 오류가 발생했습니다.');
            }
        });
    }

    // 코스 정보 및 댓글 로드
    function loadCourseDetails(contentId) {
        loadComments(contentId, 1); // 첫 페이지의 댓글을 로드
        $.ajax({
            url: '/cds/tourCourse/getCourseDetails.do',
            type: 'GET',
            data: { contentid: contentId },
            dataType: 'json',
            success: function (data) {
                if (data) {
                console.log(data);
                    updateCourseModal(data);
                    history.replaceState(null, '', '/cds/tourCourse/Course.do');
                } else {
                    alert('해당 코스 정보를 가져올 수 없습니다.');
                }
            },
            error: function (xhr, status, error) {
                console.error('데이터 가져오기 실패: ', error, xhr.responseText);
                alert('코스 정보를 가져오는 중 오류가 발생했습니다.');
            }
        });
    }

    // 코스 모달 업데이트
    function updateCourseModal(data) {
        // 날씨 패널 초기화
        $('#weather-content').html('<p>날씨 정보를<br> 확인하려면 마커를 클릭하세요.</p>');

        $('.coursenamebox .first-image').attr('src', data.first_image).css('display', 'block');
        $('.coursenamebox .course-title').text(data.title);
        $('.tagbox .tag:nth-child(1) p').text(data.distance || '거리 정보 없음');
        $('.tagbox .tag:nth-child(2) p').text(data.taketime || '소요 시간 정보 없음');
        $('.overview').text(data.overview || '설명 없음');
        $('#add-comment').data('contentid', data.content_id || content_id);
        $('.coursemodal').addClass('show');

        if (data.map_x && data.map_y) {
            initializeMap(data.map_y, data.map_x, data.title);
        } else {
            console.error("좌표 정보가 부족합니다.");
        }
    }

    // 맵 초기화
    function initializeMap(map_y, map_x, title) {
        var mapContainer = document.getElementById('map');
        var mapOption = {
            center: new kakao.maps.LatLng(map_y, map_x),
            level: 3,
            scrollwheel: false, // 스크롤 줌 비활성화
        disableDoubleClickZoom: true, // 더블 클릭 줌 비활성화
        draggable: false, //드래그 비활성화
        };
        var map = new kakao.maps.Map(mapContainer, mapOption);

        // 마커 생성
        var markerPosition = new kakao.maps.LatLng(map_y, map_x);
        var marker = new kakao.maps.Marker({
            position: markerPosition,
            map: map
        });

        // InfoWindow 생성 - title 값으로 초기화
        var infoWindow = new kakao.maps.InfoWindow({
            content: `<div style="padding:5px; max-width:170px; word-wrap:break-word;">${title}</div>`
        });
        infoWindow.open(map, marker);
        // 마커 클릭 시 InfoWindow와 날씨 및 미세먼지 데이터를 동시에 표시
        kakao.maps.event.addListener(marker, 'click', function () {
            console.log("Initial marker clicked at:", map_y, map_x);

            // InfoWindow를 열어서 마커 위에 설명을 표시

            // 날씨 및 미세먼지 데이터를 병렬로 가져오기 위한 좌표
            var lat = map_y;
            var lon = map_x;

            // Promise.all을 사용하여 날씨 데이터와 미세먼지 데이터를 병렬로 가져옴
            Promise.all([getWeatherData(lat, lon), getAirPollutionData(lat, lon)])
                .then(function ([weatherData, airData]) {
                    // 날씨 정보 처리
                    var temp = weatherData.main.temp;
                    var weather = weatherData.weather[0].description;
                    var icon = weatherData.weather[0].icon;
                    var humidity = weatherData.main.humidity;
                    var windSpeed = weatherData.wind.speed;

                    // 미세먼지 정보 처리
                    var aqi = airData.list[0].main.aqi;
                    var pm2_5 = airData.list[0].components.pm2_5;
                    var pm10 = airData.list[0].components.pm10;

                    // AQI 값에 따른 설명을 객체로 정의
                    var aqiDescriptions = {
                        1: "좋음",
                        2: "양호",
                        3: "보통",
                        4: "나쁨",
                        5: "매우 나쁨"
                    };
                    var aqiDescription = aqiDescriptions[aqi] || "미세먼지 정보없음";

                    // 날씨 및 미세먼지 정보를 패널에 추가
                    var weatherAndAirQualityHtml = `
                    <div style="padding:2px; font-size:12px;">
                        <p>온도: ${temp}℃</p>
                        <p>날씨: <br>${weather}<img src="http://openweathermap.org/img/wn/${icon}.png" alt="날씨 아이콘" class="mapicon"/></p>
                        <p>습도: ${humidity}%</p>
                        <p>풍속: ${windSpeed} m/s</p>
                        <br>
                        <p>공기질 지수 (AQI): ${aqiDescription}</p>
                        <p>미세먼지 (PM2.5): ${pm2_5} μg/m³</p>
                        <p>초미세먼지 (PM10): ${pm10} μg/m³</p>
                    </div>
                `;

                    // 우측 패널에 날씨와 미세먼지 정보를 동시에 표시
                    $('#weather-content').html(weatherAndAirQualityHtml);
                })
                .catch(function (error) {
                    console.error("데이터를 가져오는 중 오류가 발생했습니다.", error);
                });
        });

        // 카테고리 버튼 클릭 시 장소 검색
        $('.category-btn').off('click').on('click', function () {
            var category = $(this).data('category');
            searchCategory(category, map_y, map_x, map);
        });
    }



    // 카테고리 검색
    function searchCategory(category, map_y, map_x, map) {
        var places = new kakao.maps.services.Places();
        var location = new kakao.maps.LatLng(map_y, map_x);

        places.categorySearch(category, function (data, status) {
            if (status === kakao.maps.services.Status.OK) {
                clearMarkers();
                clearInfoWindows();
                for (var i = 0; i < data.length; i++) {
                    displayMarker(data[i], map);
                }
            } else {
                console.error('카테고리 검색 실패:', status);
            }
        }, { location: location, radius: 3000 });
    }

    // 마커 표시 함수 (미세먼지 추가)
    function displayMarker(place, map) {
        var marker = new kakao.maps.Marker({
            map: map,
            position: new kakao.maps.LatLng(place.y, place.x)
        });

        markers.push(marker);

        // 마커 클릭 시 날씨 및 미세먼지 정보 표시
        // 마커 클릭 이벤트 추가 (초기 마커에도 날씨 및 미세먼지 데이터를 표시)
        kakao.maps.event.addListener(marker, 'click', function () {
            console.log("Initial marker clicked at:", map_y, map_x); // 클릭 로그 추가

            // 날씨 및 미세먼지 데이터를 병렬로 가져오기 위한 좌표
            var lat = map_y;
            var lon = map_x;

            // Promise.all을 사용하여 날씨 데이터와 미세먼지 데이터를 병렬로 가져옴
            Promise.all([getWeatherData(lat, lon), getAirPollutionData(lat, lon)])
                .then(function ([weatherData, airData]) {
                    // 날씨 정보 처리
                    var temp = weatherData.main.temp; // 온도
                    var weather = weatherData.weather[0].description; // 날씨 설명
                    var icon = weatherData.weather[0].icon; // 날씨 아이콘
                    var humidity = weatherData.main.humidity; // 습도
                    var windSpeed = weatherData.wind.speed; // 풍속

                    // 미세먼지 정보 처리
                    var aqi = airData.list[0].main.aqi; // 공기질 지수 (AQI)
                    var pm2_5 = airData.list[0].components.pm2_5; // PM2.5 농도
                    var pm10 = airData.list[0].components.pm10;  // PM10 농도

                    // AQI 값에 따른 설명을 객체로 정의
                    var aqiDescriptions = {
                        1: "좋음",
                        2: "양호",
                        3: "보통",
                        4: "나쁨",
                        5: "매우 나쁨"
                    };
                    var aqiDescription = aqiDescriptions[aqi] || "미세먼지 정보없음";

                    // 날씨 및 미세먼지 정보를 패널에 추가
                    var weatherAndAirQualityHtml = `
                <div style="padding:2px; font-size:12px;">
                    <p>온도: ${temp}℃</p>
                    <p><span>날씨: <br>${weather}<img src="http://openweathermap.org/img/wn/${icon}.png" alt="날씨 아이콘" class="mapicon"/></p>
                    <p>습도: ${humidity}%</p>
                    <p>풍속: ${windSpeed} m/s</p>
                    <br>
                    <p>공기질 지수 (AQI): ${aqiDescription}</p>
                    <p>미세먼지 (PM2.5): ${pm2_5} μg/m³</p>
                    <p>초미세먼지 (PM10): ${pm10} μg/m³</p>
                </div>
            `;

                    // 우측 패널에 날씨와 미세먼지 정보를 동시에 표시
                    $('#weather-content').html(weatherAndAirQualityHtml);
                })
                .catch(function (error) {
                    console.error("데이터를 가져오는 중 오류가 발생했습니다.", error);
                });
        });

    }



    // 날씨 데이터를 가져오는 함수
    function getWeatherData(lat, lon) {
        var apiKey = 'd230d08fe6ad082f54615c077bf76b16'; // OpenWeatherMap API 키
        var url = `https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lon}&appid=${apiKey}&units=metric&lang=kr`;

        return $.getJSON(url);
    }

    function getAirPollutionData(lat, lon) {
        var apiKey = 'd230d08fe6ad082f54615c077bf76b16';  // OpenWeatherMap API 키
        var url = `http://api.openweathermap.org/data/2.5/air_pollution?lat=${lat}&lon=${lon}&appid=${apiKey}`;

        return $.getJSON(url);
    }



    // 기타 함수들 (마커 초기화 등)
    function clearMarkers() {
        for (var i = 0; i < markers.length; i++) {
            markers[i].setMap(null);
        }
        markers = [];
    }

    function clearInfoWindows() {
        for (var i = 0; i < infoWindows.length; i++) {
            infoWindows[i].close();
        }
        infoWindows = [];
        openInfoWindow = null; // 모든 정보창 닫았으므로 null로 초기화
    }

    // 초기 페이지 로드에서 contentId가 있을 경우 코스 정보 로드
    if (contentId) {
    document.body.style.overflow = 'hidden'; // body의 스크롤 비활성화
        loadCourseDetails(contentId);
        loadComments(contentId, 1);
    }

    // course-item 클릭 시 contentid를 서버로 보내 상세 정보 가져오기
    // 모달열기
    $(document).on('click', '.course-item', function () {
    document.body.style.overflow = 'hidden'; // body의 스크롤 비활성화
        var contentId = $(this).data('contentid');
        loadCourseDetails(contentId);
    });

    // 댓글 작성
    $(document).on('click', '#add-comment', function (event) {
        event.preventDefault();
        var contentId = $(this).data('contentid');
        var commentContent = $('#new-comment').val().trim();

        console.log(contentId);

        if (commentContent === "") {
            alert("댓글을 입력해주세요.");
            return;
        }

        var data = {
            content: commentContent,
            contentId: contentId
        };

        $.ajax({
            url: '/cds/tourCourse/addComment.do',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (response) {
                if (response === "success") {
                    alert('댓글 작성 성공');
                    $('#new-comment').val('');
                    loadComments(contentId, 1);
                } else if (response === "belogin") {
                    alert('로그인 후 이용 가능합니다.');
                } else {
                    alert('댓글 작성에 실패했습니다.');
                }
            },
            error: function () {
                alert('서버와 통신 중 오류가 발생했습니다.');
            }
        });
    });

    // 모달 닫기
    $(document).on('click', '.close-btn', function () {
    document.body.style.overflow = ''; // body의 스크롤 활성화
        $('.coursemodal').removeClass('show');
    });

    $(document).on('click', function (event) {
     document.body.style.overflow = ''; // body의 스크롤 활성화
        if ($(event.target).closest('.coursemodal').length === 0 && $('.coursemodal').is(':visible')) {
            $('.coursemodal').removeClass('show');
        }
    });

    $(document).on('keydown', function (event) {
     document.body.style.overflow = ''; // body의 스크롤 활성화
        if (event.key === "Escape" && $('.coursemodal').is(':visible')) {
            $('.coursemodal').removeClass('show');
        }
    });

    // 댓글 로드 함수
function loadComments(contentId, page) {
    console.log("Loading comments for contentId:", contentId, "on page:", page); // 확인용 로그 추가
    $.ajax({
        url: '/cds/tourCourse/getComments.do',
        type: 'POST',
        data: {
            contentId: contentId,
            page: page
        },
        dataType: 'json',
        success: function (response) {
            console.log("Comments loaded:", response); // 로드된 데이터 확인
            const comments = response.comments;
            const member = response.member;  // 서버에서 로그인된 사용자 정보도 함께 반환
            displayComments(comments, page, member);
            $('#comment-count').text(comments.length + "개");
        },
        error: function () {
            alert('댓글을 가져오는 중 오류가 발생했습니다.');
        }
    });
}



    // 댓글 표시 함수
// 댓글 표시 함수
function displayComments(comments, page, member) {
    console.log(comments, member);

    // member가 null인지 먼저 확인
    var membershipLevel = member ? member.membership_level : null;

    if (page === 1) {
        $('#comment-thread').empty();  // 댓글을 처음 로드할 때는 기존 댓글 목록을 비웁니다.
    }

    if (comments.length === 0 && page === 1) {
        $('#comment-thread').append('<p>댓글이 없습니다. 첫 댓글을 작성해보세요!</p>');
    } else {
        comments.forEach(function (comment) {
            // member가 null이 아닐 때만 삭제 버튼을 표시
            var isDeletable = member && (comment.name === member.name || membershipLevel == 3);

            var deleteButtonHtml = isDeletable ? `<button class="delete-btn" data-c_idx="${comment.c_idx}">🗑️ 댓글삭제</button>` : '';

            // Unix timestamp를 Date 객체로 변환
            var commentDate = new Date(comment.createdAt);  // createdAt을 Date 객체로 변환
            var formattedDate = commentDate.toLocaleString('ko-KR', { timeZone: 'Asia/Seoul' });  // 한국 시간대에 맞춰 변환

            // 댓글 HTML을 동적으로 생성합니다.
            var commentHtml = `
            <div class="comment" data-comment-id="${comment.c_idx}">
                <div class="comment-author">
                    <img src="${comment.gender == 'F' ? '../resources/img/womanfile.png' : '../resources/img/manprofile.png'}" 
                        alt="프사" class="author-photo"/>
                    <span class="author-name">${comment.name}</span>
                    <span class="comment-date">${formattedDate}</span> <!-- 한국 시간으로 표시 -->
                </div>
                <div class="comment-content">${comment.content}</div>
                <div class="comment-meta">
                    <button class="like-btn">👍 좋아요 <span class="like-count">${comment.clike}</span></button>
                    <button class="dislike-btn">👎 싫어요 <span class="dislike-count">${comment.unlike}</span></button>
                    ${deleteButtonHtml} <!-- 조건에 따라 삭제 버튼을 표시 -->
                </div>
            </div>`;
            
            $('#comment-thread').append(commentHtml);  // 댓글을 목록에 추가합니다.
        });

        $('#comment-thread').data('page', page + 1);  // 현재 페이지 정보 업데이트
    }
}



    // 좋아요/싫어요 버튼 클릭 이벤트 핸들러
    $(document).on('click', '.like-btn, .dislike-btn', function () {
        var commentId = $(this).closest('.comment').data('comment-id');
        var actionType = $(this).hasClass('like-btn') ? 'like' : 'dislike';

        $.ajax({
            url: '/cds/tourCourse/toggleLike.do',
            type: 'POST',
            data: {
                c_idx: commentId,
                actionType: actionType
            },
            success: function (response) {
                var $comment = $('.comment[data-comment-id="' + commentId + '"]');
                var $likeCount = $comment.find('.like-count');
                var $dislikeCount = $comment.find('.dislike-count');

                if (response === "success") {
                    // 좋아요/싫어요가 성공적으로 등록되었을 때
                    if (actionType === 'like') {
                        var currentLikeCount = parseInt($likeCount.text()) || 0;
                        $likeCount.text(currentLikeCount + 1);
                    } else if (actionType === 'dislike') {
                        var currentDislikeCount = parseInt($dislikeCount.text()) || 0;
                        $dislikeCount.text(currentDislikeCount + 1);
                    }
                } else if (response === "cancel") {
                    // 좋아요나 싫어요가 취소되면 감소시킴
                    if (actionType === 'like') {
                        var currentLikeCount = parseInt($likeCount.text()) || 0;
                        $likeCount.text(currentLikeCount - 1);
                    } else if (actionType === 'dislike') {
                        var currentDislikeCount = parseInt($dislikeCount.text()) || 0;
                        $dislikeCount.text(currentDislikeCount - 1);
                    }
                } else if (response === "belogin") {
                    alert("로그인 후 이용 가능한 서비스입니다.");
                }

                // 좋아요/싫어요 상태 업데이트 후 댓글 다시 불러오기
                loadComments($('#add-comment').data('contentid'), 1);
            },
            error: function () {
                alert('좋아요/싫어요 처리 중 오류가 발생했습니다.');
            }
        });
    });

    // 댓글 무한 스크롤 로딩
    $('#comment-thread').on('scroll', function () {
        var $commentThread = $(this);
        if ($commentThread.scrollTop() + $commentThread.innerHeight() >= $commentThread[0].scrollHeight) {
            var page = $commentThread.data('page') || 1;
            var contentId = $('#add-comment').data('contentid');
            loadComments(contentId, page); // 다음 페이지 댓글 로드
        }
    });

    $(document).on('click', '.delete-btn', function () {
        // 댓글 ID 가져오기
        var c_idx = $(this).data('c_idx');
        
        var contentId = $('#add-comment').data('contentid');

        if (confirm('댓글을 삭제하시겠습니까?')) {
            // AJAX 요청으로 댓글 삭제
            $.ajax({
                url: '/cds/tourCourse/deleteComment.do',  // 댓글 삭제를 처리할 서버 경로
                type: 'POST',
                data: { c_idx: c_idx },  // c_idx를 서버로 전송
                success: function (response) {
                    if (response === 'success') {
                        alert('댓글이 삭제되었습니다.');
                        // 댓글 목록을 다시 로드하거나 해당 댓글을 DOM에서 제거
                        loadComments(contentId, 1);  // 다시 댓글을 로드하는 함수
                    } else {
                        alert('댓글 삭제에 실패했습니다.');
                    }
                },
                error: function () {
                    alert('서버 요청 중 오류가 발생했습니다.');
                }
            });
        }
    });
    const categoryLinks = document.querySelectorAll('.category-link');

    // 각 카테고리 링크에 클릭 이벤트를 추가합니다.
    categoryLinks.forEach(function (link) {
        link.addEventListener('click', function () {
            // data-target 속성으로부터 타겟 클래스를 가져옵니다.
            const target = document.querySelector(this.getAttribute('data-target'));

            if (target) {
                // 해당 섹션으로 부드럽게 스크롤합니다.
                target.scrollIntoView({ behavior: 'smooth' });
            }
        });
    });

});
