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
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9dc9962fd8d9c313d5ca5a57212228ab&libraries=services"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/Course.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/Coursemodal.js"></script>
</head>

<body>
	<%@ include file="../main/header2.jsp"%>
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



						<hr>
						<div class="course-button" data-cat2="C0112" data-rname="가족코스">가족코스</div>
						<div class="course-button" data-cat2="C0113" data-rname="나홀로코스">나홀로코스</div>
						<div class="course-button" data-cat2="C0114" data-rname="힐링코스">힐링코스</div>
						<div class="course-button" data-cat2="C0115" data-rname="도보코스">도보코스</div>
						<div class="course-button" data-cat2="C0116" data-rname="캠핑코스">캠핑코스</div>
						<div class="course-button" data-cat2="C0117" data-rname="맛코스">맛코스</div>

					</div>


					<div class="festa">
						<h2>이런 축제는 어때요?</h2>
						<hr>
						<h3>축제정보가 없음</h3>
						<div class="festaimgbox">
							<img src="" loading="lazy" alt="축제이미지">
						</div>
						<h4></h4>
					</div>


				</div>
			</div>
		</div>
	</section>
	<jsp:include page="../main/footer.jsp" />


	<!-- 모달 -->
	<div class="coursemodal">

		<!-- 닫기 버튼 -->
		<div class="close-btn">&times;</div>

		<!-- 고정된 카테고리 바 -->
		<div class="fixed-category-bar">
			<button class="category-link" data-target=".coursenamebox">코스
				이름</button>
			<button class="category-link" data-target=".tagbox">코스 정보</button>
			<button class="category-link" data-target=".mapbox">지도</button>
			<button class="category-link" data-target=".overview">설명</button>
			<button class="category-link" data-target=".commentbox">댓글란</button>
		</div>

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
			</div>
			<div class="tag" style="color: white;">
				<h3>소요시간</h3>
				<p>소요 시간 정보 없음</p>
			</div>
		</div>
		<hr>

		<!-- 지도 박스 -->
		<div class="mapbox" id="map">
			<div id="category-buttons">
				<button class="category-btn" data-category="PK6">주차장</button>
				<button class="category-btn" data-category="FD6">음식점</button>
				<button class="category-btn" data-category="CE7">카페</button>
				<button class="category-btn" data-category="CS2">편의점</button>
			</div>
			
			<!-- 새로운 날씨 정보 패널 -->
    <div id="weather-info-panel">
    <h3>날씨 정보</h3>
    <div id="weather-content">
        <!-- 기본 텍스트를 한국어로 변경 -->
        <p>위치를 선택하면 날씨 정보를 볼 수 있습니다.</p>
    </div>
</div>
		</div>


		<hr>

		<!-- 설명 -->
		<div class="overview">설명 없음</div>
		<hr>

		<!-- 댓글란 -->
		<div class="commentbox">
			<div class="comment-title">댓글란</div>
			<div class="comment-count">
				총 댓글 수: <span id="comment-count">0개</span>
			</div>

			<!-- 기존 댓글들 (서버에서 로딩될 영역) -->
			<div class="comment-thread" id="comment-thread">
				<!-- 댓글이 여기에 로딩됩니다. -->
			</div>
			<hr>

			<!-- 댓글 입력 폼 -->
			<form id="comment-form">
				<div class="comment-input">
					<textarea id="new-comment" name="content" placeholder="댓글을 작성하세요"
						rows="3" required></textarea>
					<input type="hidden" id="contentId" name="contentId" value="123">
					<!-- contentId 값을 숨겨서 전송 -->
					<button type="submit" id="add-comment">댓글 작성</button>
				</div>
			</form>

		</div>

	</div>
</body>

</html>