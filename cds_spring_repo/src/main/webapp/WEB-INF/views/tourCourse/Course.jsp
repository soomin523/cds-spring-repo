<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여행 코스</title>
<!-- CSS 파일을 불러오는 링크 태그 -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/Course.css">
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

</body>

<script>
    // 지역 버튼 클릭 시 이벤트
    $('.course-button').click(function() {
        var regionCode = $(this).data('region');  // 지역 코드 가져오기
        var regionName = $(this).data('rname');  // 지역 이름 가져오기
        
        // 제목을 변경
        $('#course-title').text("#" + regionName);

        // AJAX 요청
        $.ajax({
            url: '/tourCourse/getCoursesByRegion',  // 서버에서 지역별 데이터를 가져오는 경로
            type: 'GET',
            data: { areaCode: regionCode },  // 요청에 지역 코드를 전송
            success: function(response) {
                // course-results 영역을 초기화
                $('#course-results').empty();
                
                // 데이터를 반복 처리
                response.forEach(function(course) {
                    var courseItem = `
                        <div class="course-item">
                            <img src="${course.firstImage}" alt="코스 이미지">
                            <p>${course.title}</p>
                        </div>
                    `;
                    // course-results에 추가
                    $('#course-results').append(courseItem);
                });
            },
            error: function(xhr, status, error) {
            	console.log("오류 상태: ", status);  // 오류 상태 출력
                console.log("오류 메시지: ", error);  // 오류 메시지 출력
                console.log("서버 응답 텍스트: ", xhr.responseText);  // 서버로부터 받은 응답 텍스트 출력
                alert('데이터 로드 실패');
            }
        });
    });
</script>
</html>
