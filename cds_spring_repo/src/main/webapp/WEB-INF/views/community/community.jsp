<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>커뮤니티 페이지</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/community.css">
<script src="../resources/js/jquery-3.7.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/community.js"></script>
</head>   
<body>

<div class="commu-container">
    <!-- 메인 콘텐츠 영역 -->
	<div class="commu-content">
		<div class="commu-header">
		    <h2 style="display: inline-block; margin-right: 20px;">떠나자 커뮤니티 ✍️</h2>
     <a href="${pageContext.request.contextPath}/community/upload.do">
<button class="commu-post-button" style="display: inline-block;" onclick="goToUploadPost()">게시물 올리기</button>
 </a>
<script>
    function goToUploadPost() {
        var contextPath = '${pageContext.request.contextPath}';
        window.location.href = contextPath + '/community/uploadPost.jsp';
    }
</script>

		    <div class="commu-sort-options" style="margin-left: auto;">
		        <button class="commu-sort-button" id="commu-sortLatest" onclick="sortPosts('latest')">최신순</button>
		        <button class="commu-sort-button" id="commu-sortRating" onclick="sortPosts('rating')">평점순</button>
		    </div>
		</div>
		<!-- 게시물 목록 -->
		<div id="commu-postList" class="commu-post-grid">
		    <!-- 게시물 12개가 그리드로 나올 영역 -->
		</div>
		
		<!-- 페이지네이션 -->
		<div class="commu-pagination" id="commu-pagination"></div>
    </div>

    <!-- 사이드바 (지역별 카테고리) -->
    <div class="commu-sidebar">
		<!-- 지역 검색창 -->
		<div class="commu-search-box">
			<p> <img src="${pageContext.request.contextPath}/resources/img/돋보기.png" /></p>
			<input type="text" placeholder="지역명, 제목, ID 검색 가능" id="commu-regionSearch" onkeyup="searchPosts()">
		</div>
		<!-- 지역별 게시물 보기 바로 아래 -->
		<h4 id="commu-selectedLocation" style="margin-top: 10px;"></h4>

        <h3>지역별 게시물 보기</h3>
        <div class="commu-button-container">
            <!-- 지역 버튼 생성 -->
            <button class="commu-button" onclick="filterByRegion('all')">모든 지역</button>
            <button class="commu-button" onclick="filterByRegion('서울')">서울</button>
            <button class="commu-button" onclick="filterByRegion('경기')">경기</button>
            <button class="commu-button" onclick="filterByRegion('인천')">인천</button>
            <button class="commu-button" onclick="filterByRegion('대전')">대전</button>
            <button class="commu-button" onclick="filterByRegion('강원')">강원</button>
            <button class="commu-button" onclick="filterByRegion('광주')">광주</button>
            <button class="commu-button" onclick="filterByRegion('울산')">울산</button>
            <button class="commu-button" onclick="filterByRegion('대구')">대구</button>
            <button class="commu-button" onclick="filterByRegion('부산')">부산</button>
            <button class="commu-button" onclick="filterByRegion('충북')">충북</button>
            <button class="commu-button" onclick="filterByRegion('충남')">충남</button>
            <button class="commu-button" onclick="filterByRegion('전북')">전북</button>
            <button class="commu-button" onclick="filterByRegion('전남')">전남</button>
            <button class="commu-button" onclick="filterByRegion('경북')">경북</button>
            <button class="commu-button" onclick="filterByRegion('경남')">경남</button>
            <button class="commu-button" onclick="filterByRegion('제주')">제주</button>
        </div>

 <h4 id="commu-selectedLocation" style="margin-top: 10px;"></h4>
<!-- 이미지 추가 -->
<p style="position: relative;">
    <img src="${pageContext.request.contextPath}/resources/img/후기.png" class="log-logo" style="width: 100%; position: absolute;">
     <a href="${pageContext.request.contextPath}/community/upload.do">
        <button class="commu-post-button" style="position: relative; margin: 96% 27% 0px;">게시물 올리기</button>
    </a>
</p>

        
    </div>

</div>

<!-- 모달 창 -->
<div id="commu-modal" class="commu-modal">
    <div class="commu-modal-content">
        <div class="commu-modal-header">
            <div class="commu-profile">
                <img id="commu-modalProfileImage" src="">
                <span id="commu-modalUserId"></span>
            </div>
            <div class="commu-location-icon">
                    <p> <img src="${pageContext.request.contextPath}/resources/img/위치.jpg" /></p>
                <span id="commu-modalLocation"></span>
            </div>

            <span class="commu-modal-close" onclick="closeModal()">&times;</span>
        </div>
        <div class="commu-modal-slider">
    <button class="prev-slide" onclick="prevSlide()">&#10094;</button>
    <img id="commu-modalImage" src="">
    <button class="next-slide" onclick="nextSlide()">&#10095;</button>
</div>
    
      <!-- 좋아요 수, 댓글 수, 작성일을 사진 아래로 이동 -->
      <div class="commu-modal-meta"> 
          <div class="commu-likes-comments">
              <span class="commu-icon" onclick="toggleLike()">👍 <span id="commu-modalLikes">0</span></span>
              <span class="commu-icon">💬 <span id="commu-modalCommentsCount">0</span></span>
          </div>
          <span id="commu-modalMeta" class="commu-post-date"></span>
      </div>

<!-- HTML에서 클래스명을 commu-modal-rating으로 수정 -->
<div class="commu-rating commu-modal-rating">
    <span>평점:</span>
    <div class="commu-star filled"></div> <!-- 별 1 -->
    <div class="commu-star filled"></div> <!-- 별 2 -->
    <div class="commu-star filled"></div> <!-- 별 3 -->
    <div class="commu-star filled"></div> <!-- 별 4 -->
    <div class="commu-star"></div> <!-- 빈 별 5 -->
</div>

        <h3 id="commu-modalTitle" class="commu-modal-title"></h3>
        <p class="commu-modal-description" id="commu-modalDescription"></p>

        <div id="commu-modalComments" class="commu-modal-comments"></div>
        
        <!-- 댓글 작성 영역 추가 -->
        <div class="commu-comment-box">
            <textarea id="commu-commentText" placeholder="댓글을 작성하세요"></textarea>
            <button onclick="submitComment()">올리기</button>
        </div>
    </div>
</div>
   
<!-- <script>

</script> -->

</body>
</html>
