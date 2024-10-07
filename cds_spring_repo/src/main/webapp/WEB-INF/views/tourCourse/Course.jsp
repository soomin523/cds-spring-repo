<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여행 코스</title>
<!-- CSS 파일을 불러오는 링크 태그 -->
<link rel="stylesheet" type="text/css" href="Course.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<section>
		<div class="fullcourse">
			<!-- 제목이 변경될 영역 -->
			<h1 id="course-title">#전체</h1>
			<div class="coursebox">
				<!-- 여행 설명 내용 -->
				<div class="course-story" id="course-story">
					<div class="course-search">
						<input type="text" id="search-input" placeholder="여행 코스 검색" />
						<button id="search-button">검색</button>
					</div>
					<!-- 코스 아이템이 추가될 영역 -->
					<div id="course-results"></div>
					<!-- 페이지네이션 영역 -->
					<div id="pagination"></div>
				</div>
				<div class="fesorcitybox">
					<!-- 지역 버튼들 -->
					<div class="citylist">
						<div class="course-button" data-region="1" data-rname="서울">서울</div>
						<div class="course-button" data-region="2" data-rname="인천">인천</div>
						<div class="course-button" data-region="3" data-rname="대전">대전</div>
						<div class="course-button" data-region="4" data-rname="대구">대구</div>
						<div class="course-button" data-region="5" data-rname="광주">광주</div>
						<div class="course-button" data-region="6" data-rname="부산">부산</div>
						<div class="course-button" data-region="7" data-rname="울산">울산</div>
						<div class="course-button" data-region="39" data-rname="제주">제주</div>
					</div>
					<div class="festa">
						<h1>이곳은 축제가 랜덤으로 표시가 됩니다.</h1>
					</div>
				</div>
			</div>
		</div>
	</section>

	<!-- 모달 -->
	<div class="coursemodal" style="display: none;">
		<!-- 닫기 버튼 -->
		<div class="close-btn">&times;</div>
		<!-- 코스 이름 박스 -->
		<div class="coursenamebox">
			<p>코스 이름</p>
		</div>
		<hr>
		<!-- 태그 박스 -->
		<div class="tagbox">
			<div class="tag">
				<img src="https://via.placeholder.com/30" alt="태그 아이콘">
				<p>당일여행</p>
			</div>
			<div class="tag">
				<img src="https://via.placeholder.com/30" alt="태그 아이콘">
				<p>가족코스</p>
			</div>
		</div>
		<!-- 지도 박스 -->
		<div class="mapbox">
			<p>지도가 여기에 표시됩니다</p>
		</div>
		<!-- 설명 -->
		<div class="overview"></div>
		<!-- 이미지 추가 -->
		<img class="first-image" src="" alt="코스 이미지" style="display: none;">
		<!-- 댓글란 -->
		<div class="commentbox">
			<div class="comment-title">댓글란</div>
		</div>
		<!-- 댓글 입력 -->
		<div class="comment-input">
			<textarea id="new-comment" placeholder="댓글을 작성하세요"></textarea>
			<button id="add-comment">댓글 작성</button>
		</div>
	</div>

	<!-- jQuery 스크립트 -->
	<script>
	$(document).ready(function () {
	    let currentPage = 1;
	    let pageSize = 9;

	    // 지역 버튼 클릭 시 AJAX로 데이터를 불러오기
	    $('.course-button').on('click', function () {
	        var areaCode = $(this).data('region');
	        var rname = $(this).data("rname");
	        $('#course-title').text('#' + rname);

	        // 첫 페이지 데이터 로드
	        loadCourses(areaCode, 1);
	    });

	    // 코스 데이터를 로드하는 함수 (페이지 번호에 따라)
function loadCourses(areaCode, pageNo) {
    $.ajax({
        url: 'getCoursesByRegion.jsp',
        type: 'GET',
        data: { areaCode: areaCode, pageNo: pageNo, pageSize: pageSize },
        dataType: 'json',
        success: function (response) {
            var courseResults = $('#course-results'); // 코스 아이템이 표시될 영역
            courseResults.empty(); // 기존 코스 아이템만 비움

            // 데이터를 화면에 표시
            $.each(response, function (index, course) {
                console.log("Course content_id: " + course.content_id);  // content_id 값을 콘솔에 출력

                // course-item 생성 시 content_id를 HTML 요소에 추가
                var courseItem = '<div class="course-item" data-contentid="' + course.content_id + '">' +
    '<img src="' + course.firstImage + '" alt="코스 이미지" />' +
    '<p>' + course.title + '</p>' +
    '</div>';
                courseResults.append(courseItem); // courseResults에 courseItem 추가
            });

            // 페이지네이션 업데이트
            updatePagination(areaCode, pageNo);
        },
        error: function (xhr, status, error) {
            console.error('AJAX 에러: ' + error);
        }
    });
}



	    function updatePagination(areaCode, currentPage) {
	        var pagination = $('#pagination');
	        pagination.empty();

	        var totalPages = 10; // 예시로 총 10페이지라고 가정

	        for (var i = 1; i <= totalPages; i++) {
	            var pageButton = $('<button>').text(i);
	            if (i == currentPage) {
	                pageButton.addClass('active');
	            }
	            pageButton.on('click', function () {
	                loadCourses(areaCode, $(this).text());
	            });
	            pagination.append(pageButton);
	        }
	    }

	    // 모달 관련 기능
	$(document).on('click', '.course-item', function () {
    var contentId = $(this).data('contentid');  // data-contentid 값을 가져옴
    console.log("Clicked Content ID: " + contentId);  // 클릭된 content_id를 콘솔에 출력

    // contentId가 null 또는 빈 값이 아닌지 확인
    if (!contentId) {
        console.error("Content ID가 설정되지 않았습니다.");
        return;
    }

    // AJAX로 서버에서 데이터를 가져옴
    $.ajax({
        url: 'getCourseDetails.jsp',
        type: 'GET',
        data: { contentId: contentId },  // content_id를 서버로 전송
        dataType: 'json',
        success: function (response) {
            if (!response.error) {
                // 모달에 데이터를 채움
                $('.coursenamebox p').text(response.title);  // 코스 이름
                $('.mapbox').text('지도 좌표: ' + response.map_x + ', ' + response.map_y);  // 지도 좌표

                // overview가 null일 경우 처리
                if (response.overview && response.overview.trim() !== "") {
                    $('.overview').text(response.overview);  // 코스 설명
                } else {
                    $('.overview').text("해당 코스에 대한 설명이 없습니다.");  // 기본 문구
                }

                // first_image 이미지 추가
                $('.first-image').attr('src', response.first_image).show();

                // 모달을 열음
                $('.coursemodal').css('display', 'flex');
            } else {
                alert('코스 정보를 불러오지 못했습니다.');
            }
        },
        error: function (xhr, status, error) {
            console.error('데이터 불러오기 실패: ' + error);
        }
    });
});



	    $('.close-btn').on('click', function () {
	        $('.coursemodal').css('display', 'none');
	    });

	    // 댓글 추가 기능 (UI에서만 처리)
	    $('#add-comment').on('click', function () {
	        var newComment = $('#new-comment').val();

	        if (newComment.trim() !== "") {
	            // 댓글을 UI에 추가
	            $('.coursemodal .commentbox').append(`
	                <div class="comment">
	                    <img src="https://via.placeholder.com/40" alt="유저 이미지">
	                    <p>${newComment}</p>
	                </div>
	            `);

	            // 입력 필드를 비움
	            $('#new-comment').val('');
	        } else {
	            alert("댓글을 입력해 주세요!");
	        }
	    });

	    // ESC 키 누르면 모달 닫기
	    $(document).on('keydown', function (e) {
	        if (e.key === 'Escape') {
	            $('.coursemodal').css('display', 'none');
	        }
	    });
	});
	</script>
</body>
</html>
