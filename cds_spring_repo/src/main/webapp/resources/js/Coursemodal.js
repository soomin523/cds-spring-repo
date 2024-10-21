$(document).ready(function () {
    // 페이지 로드 시 로그인 상태 확인
    $.ajax({
        url: '/cds/tourCourse/checkLoginStatus.do',
        type: 'POST',
        success: function (response) {
            if (response === "belogin") {
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
        loadComments(contentId, 1); // 첫 페이지의 댓글을 로드
        // AJAX 요청을 통해 contentid에 맞는 데이터 가져오기
        $.ajax({
            url: '/cds/tourCourse/getCourseDetails.do',
            type: 'GET',
            data: { contentid: contentId },
            dataType: 'json',
            success: function (data) {
                if (data) {
                    $('.coursenamebox .first-image').attr('src', data.first_image).css('display', 'block');
                    $('.coursenamebox .course-title').text(data.title);
                    $('.tagbox .tag:nth-child(1) p').text(data.distance || '거리 정보 없음');
                    $('.tagbox .tag:nth-child(2) p').text(data.taketime || '소요 시간 정보 없음');
                    $('.overview').text(data.overview || '설명 없음');
                    $('#add-comment').data('contentid', contentId);
                    $('.coursemodal').addClass('show');

                    if (data.map_x && data.map_y) {
                        var mapContainer = document.getElementById('map');
                        var mapOption = {
                            center: new kakao.maps.LatLng(data.map_y, data.map_x),
                            level: 3
                        };
                        var map = new kakao.maps.Map(mapContainer, mapOption);
                        var markerPosition = new kakao.maps.LatLng(data.map_y, data.map_x);
                        var marker = new kakao.maps.Marker({
                            position: markerPosition
                        });
                        marker.setMap(map);

                        var infoContent = '<div class="info-window">' + data.title + '</div>';
                        var infoWindow = new kakao.maps.InfoWindow({
                            content: infoContent,
                        });
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
                    loadComments(contentId, 1); // 첫 페이지 댓글 다시 불러오기
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
});
