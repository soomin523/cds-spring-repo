<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>여행 코스</title>
<!-- CSS 파일을 불러오는 링크 태그 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/Course.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/Course.js"></script>
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
						<div class="course-button" data-region="all" data-rname="전체">전체</div>
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
						<h2>이런 축제는 어때요?</h2>
						<hr>
						<h3>축제정보가 없음</h3>
						<div class="festaimgbox"><img src="" loading="lazy" alt="축제이미지"></div>
						<h4></h4>
					</div>
					
					
				</div>
			</div>
		</div>
	</section>




	<!-- 모달 -->
	<div class="coursemodal" style="display: none;">
		<!-- 닫기 버튼 -->
		<div class="close-btn">&times;</div>

		<!-- 코스 이름 및 이미지 박스 -->
		<div class="coursenamebox">
			<hr>
			<p class="course-title">코스 이름</p>
			<hr>
			<img class="first-image" src="" alt="코스 이미지"
				style="display: none; width: 100%; height: auto; max-height: 300px;">
		</div>
		<hr>

		<!-- 태그 박스 -->
		<div class="tagbox">
			<div class="tag">
				<h3>거리정보</h3>
				<p>거리 정보 없음</p>
				<!-- 여기에 distance 값이 들어감 -->
			</div>
			<div class="tag">
				<h3>소요시간</h3>
				<p>소요 시간 정보 없음</p>
				<!-- 여기에 taketime 값이 들어감 -->
			</div>
		</div>
		<hr>
		<!-- 지도 박스 -->
        <div style="width:90%;height:300px;"></div>
		<hr>
		<!-- 설명 -->
		<div class="overview">설명 없음</div>
		<hr>
		<!-- 댓글란 -->
		<div class="commentbox">
			<div class="comment-title">댓글란</div>
		</div>
		<hr>
		<!-- 댓글 입력 -->
		<div class="comment-input">
			<textarea id="new-comment" placeholder="댓글을 작성하세요"></textarea>
			<button id="add-comment">댓글 작성</button>
		</div>
	</div>



</body>

</html>