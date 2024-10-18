$(document).ready(function () {
    // 페이지 로드 시 로그인 상태 확인
    $.ajax({
        url: '/cds/tourCourse/checkLoginStatus.do',
        type: 'POST',
        success: function (response) {
            if (response === "belogin") {
                // 로그인하지 않은 경우
                $('#add-comment').prop('disabled', true); // 버튼 비활성화
                $('#new-comment').attr('placeholder', '로그인 후 이용 가능합니다.'); // 안내 메시지 변경
                $('#new-comment').prop('disabled', true);
            }
        },
        error: function () {
            console.error('로그인 상태 확인 중 오류가 발생했습니다.');
        }
    });

    // course-item 클릭 시 contentid를 서버로 보내 상세 정보 가져오기
    $(document).on('click', '.course-item', function () {
        var contentId = $(this).data('contentid');
        loadComments(contentId);
        // AJAX 요청을 통해 contentid에 맞는 데이터 가져오기
        $.ajax({
            url: '/cds/tourCourse/getCourseDetails.do',
            type: 'GET',
            data: { contentid: contentId },
            dataType: 'json',
            success: function (data) {
                if (data) {
                    // 모달에 데이터 채우기
                    $('.coursenamebox .first-image').attr('src', data.first_image).css('display', 'block');
                    $('.coursenamebox .course-title').text(data.title);
                    $('.tagbox .tag:nth-child(1) p').text(data.distance || '거리 정보 없음');
                    $('.tagbox .tag:nth-child(2) p').text(data.taketime || '소요 시간 정보 없음');
                    $('.overview').text(data.overview || '설명 없음');

                    // 댓글 작성 버튼에 contentId 저장
                    $('#add-comment').data('contentid', contentId);

                    // 모달 열기
                    $('.coursemodal').addClass('show');

                    // 지도 초기화
                    if (data.map_x && data.map_y) {
                        var mapContainer = document.getElementById('map');
                        var mapOption = {
                            center: new kakao.maps.LatLng(data.map_y, data.map_x),
                            level: 3
                        };
                        var map = new kakao.maps.Map(mapContainer, mapOption);

                        // 마커 표시
                        var markerPosition = new kakao.maps.LatLng(data.map_y, data.map_x);
                        var marker = new kakao.maps.Marker({
                            position: markerPosition
                        });
                        marker.setMap(map);

                        // InfoWindow 생성
                        var infoContent = '<div class="info-window">' + data.title + '</div>';
                        var infoWindow = new kakao.maps.InfoWindow({
                            content: infoContent,
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

    // 댓글 작성
    $(document).on('click', '#add-comment', function (event) {
        event.preventDefault();  // 폼 전송 방지
        var contentId = $(this).data('contentid');
        var commentContent = $('#new-comment').val().trim();

        if (commentContent === "") {
            alert("댓글을 입력해주세요.");
            return;
        }

        // 서버로 보낼 데이터 구성
        var data = {
            content: commentContent,
            contentId: contentId
        };

        // AJAX로 댓글 작성 요청 보내기
        $.ajax({
            url: '/cds/tourCourse/addComment.do',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (response) {
                if (response === "success") {
                    alert('댓글 작성 성공');
                    $('#new-comment').val('');  // 댓글 입력창 비우기
                    loadComments(contentId);    // 댓글 다시 불러오기
                } else if (response === "belogin") {
                    alert('로그인 후 이용 가능합니다.');
                } else {
                    alert('어떠한 문제로 댓글 작성에 실패했습니다.');
                }
            },
            error: function () {
                alert('서버와 통신 중 오류가 발생했습니다.');
            }
        });
    });

    // 모달 닫기 버튼 클릭 시 모달 닫기
    $(document).on('click', '.close-btn', function () {
        $('.coursemodal').removeClass('show');
    });

    // 모달 바깥 영역 클릭 시 모달 닫기
    $(document).on('click', function (event) {
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


    // 댓글을 가져오는 함수
    function loadComments(contentId) {
        $.ajax({
            url: '/cds/tourCourse/getComments.do', // 댓글을 불러올 API 엔드포인트
            type: 'POST',
            data: { contentId: contentId }, // contentId를 서버로 전송
            dataType: 'json',
            success: function (comments) {
                // 댓글 리스트를 성공적으로 가져왔을 때
                console.log(comments);
                displayComments(comments); // 댓글을 화면에 표시하는 함수 호출
            },
            error: function () {
                alert('댓글을 가져오는 중 오류가 발생했습니다.');
            }
        });
    }

    // 댓글을 화면에 표시하는 함수
    function displayComments(comments) {
        // 기존 댓글을 모두 지우고 새 댓글을 추가
        
        $('#comment-thread').empty();

        if (comments.length === 0) {
            $('#comment-thread').append('<p>댓글이 없습니다. 첫 댓글을 작성해보세요!</p>');
        } else {
            comments.forEach(function (comment) {
 
                // 각 댓글을 HTML 형태로 구성
                var commentHtml = `
                <div class="comment">
                    <div class="comment-author">
                         
           <img src="${comment.gender == 'F' ? '../resources/img/womanfile.png' : '../resources/img/manprofile.png'}" 
            alt="프사" 
            class="author-photo"/>
                        <span class="author-name">${comment.memberId}</span>
                        <span class="comment-date">${new Date(comment.createdAt).toLocaleString()}</span>
                    </div>
                    <div class="comment-content">${comment.content}</div>
                    <div class="comment-meta">
                        <button class="like-btn">👍 좋아요 ${comment.clike}</button>
                        <button class="dislike-btn">👎 싫어요 ${comment.unlike}</button>
                    </div>
                </div>
            `;

                // 댓글 HTML을 댓글 리스트에 추가
                $('#comment-thread').append(commentHtml);
            });
        }
    }


});
