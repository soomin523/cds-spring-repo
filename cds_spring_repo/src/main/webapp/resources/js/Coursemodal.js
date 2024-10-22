$(document).ready(function () {
    let contentId = getParameterByName('contentId');
    let markers = [];
    let infoWindows = []; // 정보 창을 저장할 배열
    let openInfoWindow = null; // 현재 열린 정보창을 저장

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
        $('.coursenamebox .first-image').attr('src', data.first_image).css('display', 'block');
        $('.coursenamebox .course-title').text(data.title);
        $('.tagbox .tag:nth-child(1) p').text(data.distance || '거리 정보 없음');
        $('.tagbox .tag:nth-child(2) p').text(data.taketime || '소요 시간 정보 없음');
        $('.overview').text(data.overview || '설명 없음');
        $('#add-comment').data('contentid', data.contentId || contentId);
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
            level: 3
        };
        var map = new kakao.maps.Map(mapContainer, mapOption);

        // 마커 생성
        var markerPosition = new kakao.maps.LatLng(map_y, map_x);
        var marker = new kakao.maps.Marker({
            position: markerPosition
        });
        marker.setMap(map);

        // 정보창 생성
        var infoContent = '<div class="info-window">' + title + '</div>';
        var infoWindow = new kakao.maps.InfoWindow({
            content: infoContent,
        });
        infoWindows.push(infoWindow);
        infoWindow.open(map, marker);

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
        }, {location: location, radius: 3000}); 
    }

    // 마커 표시 함수
    function displayMarker(place, map) {
        var marker = new kakao.maps.Marker({
            map: map,
            position: new kakao.maps.LatLng(place.y, place.x) 
        });
        markers.push(marker);

        var infoWindow = new kakao.maps.InfoWindow({
            content: '<div style="padding:5px;font-size:12px; item-align:center;">' + place.place_name + '</div>'
        });
        infoWindows.push(infoWindow);

        // 마커 클릭 시 정보창 토글
        kakao.maps.event.addListener(marker, 'click', function() {
            if (openInfoWindow) {
                openInfoWindow.close(); // 열려있는 정보창을 닫음
            }
            if (openInfoWindow === infoWindow) {
                openInfoWindow = null; // 이미 열린 상태였으면 null로 설정
            } else {
                infoWindow.open(map, marker);
                openInfoWindow = infoWindow; // 새로운 정보창을 설정
            }
        });
    }

    // 기존 마커 및 정보 창 제거
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
        loadCourseDetails(contentId);
    }

    // course-item 클릭 시 contentid를 서버로 보내 상세 정보 가져오기
    $(document).on('click', '.course-item', function () {
        var contentId = $(this).data('contentid');
        loadCourseDetails(contentId);
    });

    // 댓글 작성
    $(document).on('click', '#add-comment', function (event) {
        event.preventDefault();
        var contentId = $(this).data('contentid');
        var commentContent = $('#new-comment').val().trim();

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
        $('.coursemodal').removeClass('show');
    });

    $(document).on('click', function (event) {
        if ($(event.target).closest('.coursemodal').length === 0 && $('.coursemodal').is(':visible')) {
            $('.coursemodal').removeClass('show');
        }
    });

    $(document).on('keydown', function (event) {
        if (event.key === "Escape" && $('.coursemodal').is(':visible')) {
            $('.coursemodal').removeClass('show');
        }
    });

    // 댓글 로드 함수
    function loadComments(contentId, page) {
        $.ajax({
            url: '/cds/tourCourse/getComments.do',
            type: 'POST',
            data: { 
                contentId: contentId,
                page: page
            },
            dataType: 'json',
            success: function (comments) {
                displayComments(comments, page);
                $('#comment-count').text(comments.length + "개");
            },
            error: function () {
                alert('댓글을 가져오는 중 오류가 발생했습니다.');
            }
        });
    }

    // 댓글 표시
    function displayComments(comments, page) {
        if (page === 1) {
            $('#comment-thread').empty();
        }

        if (comments.length === 0 && page === 1) {
            $('#comment-thread').append('<p>댓글이 없습니다. 첫 댓글을 작성해보세요!</p>');
        } else {
            comments.forEach(function (comment) {
                var commentHtml = `
                <div class="comment" data-comment-id="${comment.c_idx}">
                    <div class="comment-author">
                        <img src="${comment.gender == 'F' ? '../resources/img/womanfile.png' : '../resources/img/manprofile.png'}" 
                            alt="프사" class="author-photo"/>
                        <span class="author-name">${comment.memberId}</span>
                        <span class="comment-date">${new Date(comment.createdAt).toLocaleString()}</span>
                    </div>
                    <div class="comment-content">${comment.content}</div>
                    <div class="comment-meta">
                        <button class="like-btn">👍 좋아요 <span class="like-count">${comment.clike}</span></button>
                        <button class="dislike-btn">👎 싫어요 <span class="dislike-count">${comment.unlike}</span></button>
                    </div>
                </div>`;
                $('#comment-thread').append(commentHtml);
            });

            $('#comment-thread').data('page', page + 1);
        }
    }
});
