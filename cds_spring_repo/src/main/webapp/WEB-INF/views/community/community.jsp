<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>커뮤니티 페이지</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        .commu-container {
            display: flex;
            flex-direction: row;
            justify-content: space-between;
            width: 90%; /* 양쪽 여백을 줄이기 위해 90%로 설정 */
            max-width: 1400px;
            margin: 20px auto;
        }
        .commu-content {
            width: 75%; /* 사이드바 여백 줄이기 */
            padding: 20px;
            background-color: white;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .commu-sidebar {
            width: 20%;
            padding: 20px;
            background-color: white;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            height: auto;
        }
        .commu-search-box {
            display: flex;
            align-items: center;
            padding: 5px 10px;
            background-color: #eee;
            border-radius: 20px;
            margin-bottom: 20px;
        }
        .commu-search-box input {
            border: none;
            background: none;
            outline: none;
            margin-left: 10px;
            font-size: 14px;
        }
        
        .commu-search-box svg {
            width: 20px;
            height: 20px;
        }
        .commu-post-grid {
            display: grid;
            grid-template-columns: repeat(4, 1fr); /* 한 줄에 4개씩 배치 */
            grid-gap: 20px;
        }
        .commu-post {
            position: relative;
            padding: 10px;
            border: 1px solid #ddd;
            background-color: #fff;
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            text-align: center;
            cursor: pointer;
        }
        .commu-post img {
            width: 100%;
            height: 200px; /* 사진 세로 길이 설정 */
            object-fit: cover;
            border-radius: 8px;
            margin-bottom: 10px;
        }
        .commu-post .commu-profile {
            position: absolute;
            top: 15px;
            left: 20px;
            display: flex;
            align-items: center;
            gap: 10px; /* 프로필과 이미지 사이 간격 추가 */
        }
        .commu-post .commu-profile img {
            width: 30px;
            height: 30px;
            border-radius: 50%;
        }
        .commu-post .commu-profile span {
            position: relative;
            top: -5px; /* 유저 아이디 위치를 조금 올리기 */
        }
        .commu-post-title {
            font-size: 16px;
            font-weight: bold;
            margin-bottom: 5px;
        }
        .commu-post-meta {
            font-size: 12px;
            color: #777;
            margin-bottom: 10px;
        }
        .commu-rating {
            display: flex;
            justify-content: center;
            align-items: center;
            font-size: 14px;
            margin-bottom: 10px;
        }
        .commu-rating .commu-star {
            width: 16px;
            height: 16px;
            background-color: #ddd;
            clip-path: polygon(50% 0%, 61% 35%, 98% 35%, 68% 57%, 79% 91%, 50% 70%, 21% 91%, 32% 57%, 2% 35%, 39% 35%);
            margin-right: 2px;
        }
        .commu-rating .commu-star.filled {
            background-color: #4caff6;
        }
        .likes-comments {
            font-size: 14px;
            text-align: left;
            margin-top: 5px;
        }
        .commu-likes-comments .commu-icon {
            margin-right: 10px;
        }
        .commu-pagination {
            margin-top: 20px;
            text-align: center;
        }
        .commu-pagination button {
            padding: 8px 12px;
            margin: 2px;
            background-color: #ddd;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .commu-pagination button.active {
            background-color: #4caff6;
            color: white;
        }
        .commu-sort-options {
            display: inline-block;
            margin-left: 20px;
        }
        .commu-location-tag {
            position: absolute;
            top: 10px;
            right: 10px;
            padding: 5px 10px;
            background-color: #333;
            color: white;
            font-size: 12px;
            border-radius: 5px;
        }
        .commu-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        /* 수정된 정렬 버튼 스타일 */
        .commu-sort-options button {
            padding: 10px 15px;
            margin-left: 10px;
            background-color: #b2e0f5;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }
        .commu-sort-options button.active {
            background-color: #333;
            color: white;
        }
        /* 모달 스타일 */
        .commu-modal {
            display: none;
            position: fixed;
            z-index: 5;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0, 0, 0, 0.5);
            justify-content: center;
            align-items: center;
        }

        .commu-modal-content {
            background-color: white;
            padding: 40px 20px;
            border-radius: 10px;
            width: 30%;
            max-width: 500px;
            text-align: center;
            position: relative;
        }

        /* 고객 프로필 및 닫기 버튼 배치 */
        .commu-modal-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            position: absolute;
            top: 4px;
            left: 20px;
            right: 20px;
            gap: 10px; /* 닫기 버튼과 위치 아이콘의 간격을 좁힘 */
        }

        .commu-location-icon {
            display: flex;
            align-items: center;
            font-size: 12px;
            color: #555;
            position: absolute; /* 위치를 오른쪽으로 조정 */
            right: 20px; /* 오른쪽에서 20px 떨어지도록 */
            top: 4px; /* 위쪽 여백 */
        }

        .commu-location-icon img {
            width: 20px; /* 이미지 크기 설정 */
            height: 20px;
            margin-right: 5px; /* 이미지와 텍스트 간격 */
        }

        .commu-modal-header .profile {
            display: flex;
            align-items: center;
            left: 40px;
            margin-right: 10px; /* 프로필과 아이디 간격 추가 */
        }

        .commu-modal-header .commu-profile img {
            width: 30px;
            height: 30px;
            border-radius: 50%;
            margin-right: 10px;
        }

        .commu-modal-close {
            font-size: 30px;
            cursor: pointer;
        }

        .commu-modal img {
            width: 100%;
            height: auto;
            margin-top: 0px; /* 이미지의 위치를 아래로 조정하여 프로필과 겹치지 않게 설정 */
        }

        /* 모달 하단에 좋아요 수, 댓글 수 및 작성일 표시 스타일 */
        .commu-modal-meta {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-top: 10px;
        }

        .commu-modal-meta .likes-comments {
            display: flex;
            align-items: center;
            font-size: 14px;
        }

        .commu-modal-meta .likes-comments .icon {
            margin-right: 10px;
        }

        .commu-modal-meta .post-date {
            font-size: 12px;
            color: #777; /* 회색 글자 */
            font-size: 12px; /* 글자 크기 줄임 */
        }

        .commu-modal-title {
            font-weight: bold;
            font-size: 18px;
            text-align: left; /* 제목을 왼쪽으로 정렬 */
            width: 100%;
        }

        .commu-modal-description {
            margin-top: 10px;
            font-size: 14px;
            color: #555;
            text-align: left; /* 내용도 왼쪽으로 정렬 */
        }

        .commu-search-box img {
            width: 20px; /* 돋보기 이미지 크기 */
            height: 20px;
        }

        .commu-location-icon img {
            width: 18px; /* 위치 아이콘 이미지 크기 */
            height: 18px;
            margin-right: 5px; /* 이미지와 텍스트 간격 */
        }

        .commu-button-container {
            display: flex;
            flex-wrap: wrap; /* 버튼이 줄 바꿈을 할 수 있도록 설정 */
            justify-content: space-between; /* 버튼 간의 간격을 자동으로 조절 */
            margin-top: 10px;
        }

        .commu-button-container button {
            flex: 0 1 calc(44.44% - 2px); /* 각 버튼의 가로 길이를 1/3로 설정 (여백 포함) */
            margin-bottom: 10px; /* 버튼 간의 세로 간격 추가 */
        }
        .commu-button-container button:hover {
            background-color: #b2e0f5; /* 마우스 오버 시 연한색으로 변경 */
        }

        /* 추가된 스타일: 선택된 버튼의 배경색 변경 */
        .commu-button.selected {
            background-color: #b2e0f5;
            color: white;
        }

        .commu-button {
            padding: 8px 10px;
            margin: 5px 0;
            background-color: #4caff6;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .commu-button.active {
            background-color: #333;
        }
        
        .commu-comment-box {
            display: flex;
            margin-top: 20px;
            border-top: 1px solid #ddd;
            padding-top: 10px;
        }
        .commu-comment-box textarea {
            flex: 1;
            padding: 10px;
            font-size: 14px;
            border: 1px solid #ccc;
            border-radius: 5px;
            resize: none;
        }
        .commu-comment-box button {
            margin-left: 10px;
            padding: 10px 15px;
            background-color: #4caff6;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        
        .commu-likes-container {
            display: flex;
            align-items: center;
            justify-content: flex-end; /* 좋아요와 댓글을 오른쪽에 배치 */
        }

        .commu-likes-container .commu-likes-comments {
            display: flex;
            align-items: center;
            font-size: 14px;
            margin-left: 10px;
        }

        .commu-likes-container .commu-likes-comments .icon {
            margin-right: 5px;
        }

        /* 작성일자, 조회수 위치 수정 */
        .commu-meta-info {
            display: flex;
            justify-content: space-between;
            align-items: center;
            font-size: 12px;
            color: #777;
            margin-top: 10px;
        }

        /* 댓글 스타일 수정 */
        .commu-modal-comments p {
            text-align: left; /* 댓글을 왼쪽으로 정렬 */
            display: flex;
            justify-content: space-between; /* 댓글과 삭제 버튼 간격 조절 */
            margin-bottom: 10px;
        }
        .commu-comment-delete {
            margin-left: 10px; /* 삭제 버튼을 오른쪽에 밀착 */
            cursor: pointer;
            color: red;
        }

    </style>
</head>
<body>

<div class="commu-container">
    <!-- 메인 콘텐츠 영역 -->
    <div class="commu-content">
        <div class="commu-header">
            <h2>떠나자 커뮤니티 ✍️</h2>

            <div class="commu-sort-options">
                <span></span>
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
            <img src="돋보기.png" alt="Location Icon">
            <input type="text" placeholder="지역명을 검색하세요" id="commu-regionSearch">
        </div>

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
                <img src="위치.jpg" alt="Location Icon">
                <span id="commu-modalLocation"></span>
            </div>

            <span class="commu-modal-close" onclick="closeModal()">&times;</span>
        </div>
        <img id="commu-modalImage" src="">
        
        <!-- 좋아요 수, 댓글 수, 작성일을 사진 아래로 이동 -->
        <div class="commu-modal-meta"> 
            <div class="commu-likes-comments">
                <span class="commu-icon" onclick="toggleLike()">👍 <span id="commu-modalLikes">0</span></span>
                <span class="commu-icon">💬 <span id="commu-modalCommentsCount">0</span></span>
            </div>
            <span id="commu-modalMeta" class="commu-post-date"></span>
        </div>

        <!-- 평점을 조회수 아래로 추가 -->
        <div class="commu-rating modal-rating">
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
   


<script>
    var currentPost; // 전역 변수로 currentPost 선언
    var isAdmin = true; // 관리자 여부를 설정하는 변수
    var currentUserId = "User_ID"; // 현재 접속한 사용자의 ID

    // 게시물 데이터 예시
    var posts = [
        {title: '게시물 제목 1', date: '2024-09-29', views: 120, likes: 10, comments: 0, rating: 4.5, location: '서울', profile: 'https://via.placeholder.com/30', user: 'User_ID', image: 'https://via.placeholder.com/100', description: '게시물 내용 1', commentsData: []},
        {title: '게시물 제목 2', date: '2024-09-28', views: 200, likes: 50, comments: 25, rating: 3.8, location: '부산', profile: 'https://via.placeholder.com/30', user: 'User_ID_2', image: 'https://via.placeholder.com/100', description: '게시물 내용 2', commentsData: ['comment 3', 'comment 4']},
        {title: '게시물 제목 3', date: '2024-09-27', views: 50, likes: 10, comments: 5, rating: 5.0, location: '대구', profile: 'https://via.placeholder.com/30', user: 'User_ID_3', image: 'https://via.placeholder.com/100', description: '게시물 내용 3', commentsData: ['comment 5']},
        {title: '게시물 제목 4', date: '2024-09-26', views: 75, likes: 12, comments: 3, rating: 4.0, location: '인천', profile: 'https://via.placeholder.com/30', user: 'User_ID_4', image: 'https://via.placeholder.com/100', description: '게시물 내용 4', commentsData: ['comment 6', 'comment 7']},
        {title: '게시물 제목 5', date: '2024-09-25', views: 45, likes: 5, comments: 0, rating: 3.5, location: '서울', profile: 'https://via.placeholder.com/30', user: 'User_ID', image: 'https://via.placeholder.com/100', description: '게시물 내용 5', commentsData: []},
        {title: '게시물 제목 6', date: '2024-09-24', views: 180, likes: 30, comments: 20, rating: 4.8, location: '부산', profile: 'https://via.placeholder.com/30', user: 'User_ID_5', image: 'https://via.placeholder.com/100', description: '게시물 내용 6', commentsData: ['comment 9', 'comment 10']}
    ];

    var currentPage = 1;
    var postsPerPage = 12;

    function displayPosts(page, filteredPosts) {
        var postList = document.getElementById("commu-postList");
        postList.innerHTML = "";
        var start = (page - 1) * postsPerPage;
        var end = start + postsPerPage;
        var paginatedPosts = filteredPosts.slice(start, end);

        paginatedPosts.forEach(function (post) {
            var postDiv = document.createElement("div");
            postDiv.className = "commu-post";
            postDiv.onclick = function() { openModal(post); }; // 클릭 시 모달 열기

            var img = document.createElement("img");
            img.src = post.image;
            postDiv.appendChild(img);

            var profileDiv = document.createElement("div");
            profileDiv.className = "commu-profile";
            var profileImg = document.createElement("img");
            profileImg.src = post.profile;
            var userName = document.createElement("span");
            userName.innerText = post.user;
            profileDiv.appendChild(profileImg);
            profileDiv.appendChild(userName);
            postDiv.appendChild(profileDiv);

            var locationTag = document.createElement("div");
            locationTag.className = "commu-location-tag";
            locationTag.innerText = post.location;
            postDiv.appendChild(locationTag);

            var title = document.createElement("div");
            title.className = "commu-post-title";
            title.innerText = post.title;
            postDiv.appendChild(title);

            var ratingDiv = document.createElement("div");
            ratingDiv.className = "commu-rating";
            for (var i = 1; i <= 5; i++) {
                var star = document.createElement("div");
                star.className = "commu-star" + (i <= post.rating ? " filled" : "");
                ratingDiv.appendChild(star);
            }
            var ratingText = document.createElement("span");
            ratingText.innerText = " " + post.rating;
            ratingDiv.appendChild(ratingText);
            postDiv.appendChild(ratingDiv);

            var likesCommentsDiv = document.createElement("div");
            likesCommentsDiv.className = "commu-likes-comments";
            likesCommentsDiv.innerHTML = `<span class="commu-icon">👍 ${post.likes}</span><span class="commu-icon">💬 ${post.comments}</span>`;
            postDiv.appendChild(likesCommentsDiv);

            var metaInfo = document.createElement("div");
            metaInfo.className = "commu-meta-info";
            metaInfo.innerHTML = `<span>작성일: ${post.date}</span><span>조회수: ${post.views}</span>`;
            postDiv.appendChild(metaInfo);

            postList.appendChild(postDiv);
        });

        displayPagination(filteredPosts);
    }

    function displayPagination(filteredPosts) {
        var paginationDiv = document.getElementById("commu-pagination");
        paginationDiv.innerHTML = "";
        var totalPages = Math.ceil(filteredPosts.length / postsPerPage);

        for (var i = 1; i <= totalPages; i++) {
            var button = document.createElement("button");
            button.innerText = i;
            if (i === currentPage) {
                button.className = "commu-active";
            }
            button.addEventListener("click", function () {
                currentPage = parseInt(this.innerText);
                displayPosts(currentPage, filteredPosts);
            });
            paginationDiv.appendChild(button);
        }
    }

    function sortPosts() {
        var sortOption = document.getElementById("commu-sort").value;

        if (sortOption === "latest") {
            posts.sort(function (a, b) {
                return new Date(b.date) - new Date(a.date);
            });
        } else if (sortOption === "rating") {
            posts.sort(function (a, b) {
                return b.rating - a.rating;
            });
        }

        filterByRegion('all'); // 정렬 후 필터 적용
    }

    function filterByRegion(region) {
        var buttons = document.querySelectorAll('.commu-button'); // 모든 버튼을 선택
        buttons.forEach(function(button) {
            button.classList.remove('commu-selected'); // 모든 버튼에서 'selected' 클래스 제거
        });
        
        // 해당 지역 버튼에만 'selected' 클래스 추가
        var selectedButton = Array.from(buttons).find(function(button) {
            return button.innerText === region || (region === 'all' && button.innerText === '모든 지역');
        });
        if (selectedButton) {
            selectedButton.classList.add('commu-selected');
        }

        var filteredPosts = posts.filter(function(post) {
            return region === 'all' || post.location === region;
        });
        displayPosts(currentPage, filteredPosts);
        document.getElementById("commu-selectedLocation").innerText = "선택된 지역: " + (region === 'all' ? "모든 지역" : region);
    }

    function openModal(post) {
        currentPost = post; // currentPost에 클릭한 게시물 데이터를 저장
	
        document.getElementById("commu-modalProfileImage").src = post.profile;
        document.getElementById("commu-modalUserId").innerText = post.user;
        document.getElementById("commu-modalLocation").innerText = post.location;
        document.getElementById("commu-modalImage").src = post.image;
        document.getElementById("commu-modalTitle").innerText = post.title;
        document.getElementById("commu-modalMeta").innerText = "작성일: " + post.date + " | 조회수: " + post.views;
        document.getElementById("commu-modalLikes").innerText = post.likes;
        document.getElementById("commu-modalCommentsCount").innerText = post.comments;
        document.getElementById("commu-modalDescription").innerText = post.description;

        // 평점 추가
        var modalRating = document.querySelector(".commu-modal-rating");
        modalRating.innerHTML = '';
        for (var i = 1; i <= 5; i++) {
            var star = document.createElement("div");
            star.className = "commu-star" + (i <= post.rating ? " filled" : "");
            modalRating.appendChild(star);
        }

        // 댓글 표시
        var modalComments = document.getElementById("commu-modalComments");
        modalComments.innerHTML = ""; // 기존 댓글 삭제
        post.commentsData.forEach(function(comment, index) {
            var commentP = document.createElement("p");
            commentP.innerText = comment;

            // 삭제 버튼을 게시물 등록자와 관리자만 추가
            if (currentUserId === post.user || isAdmin) {
                var deleteButton = document.createElement("span");
                deleteButton.innerText = " x";
                deleteButton.classList.add("commu-comment-delete");
                deleteButton.onclick = function() {
                    deleteComment(index); // 댓글 삭제
                };
                commentP.appendChild(deleteButton);
            }

            modalComments.appendChild(commentP);
        });

        document.getElementById("commu-modal").style.display = "flex"; // 모달 열기
    }

    function submitComment() {
        var commentText = document.getElementById("commu-commentText").value;
        if (commentText.trim()) {
            var modalComments = document.getElementById("commu-modalComments");
            var commentP = document.createElement("p");
            commentP.innerText = commentText;

            var deleteButton = document.createElement("span");
            deleteButton.innerText = " x";
            deleteButton.classList.add("commu-comment-delete");
            deleteButton.onclick = function() {
                deleteComment(currentPost.commentsData.length); // 새로 추가되는 댓글의 인덱스 설정
            };

            commentP.appendChild(deleteButton);
            modalComments.appendChild(commentP);

            document.getElementById("commu-commentText").value = ''; // 댓글 입력 후 텍스트박스 비우기

            currentPost.commentsData.push(commentText); // 새 댓글 저장
            currentPost.comments++; // 댓글 수 증가

            document.getElementById("commu-modalCommentsCount").innerText = currentPost.comments; // 댓글 수 갱신
        }
    }

    // 댓글 삭제 함수
    function deleteComment(index) {
        if (currentPost.commentsData.length > 0) { // 댓글 수가 0 이하가 되지 않도록 설정
            currentPost.commentsData.splice(index, 1); // 댓글 삭제
            currentPost.comments--; // 댓글 수 감소
        }
        openModal(currentPost); // 모달 다시 열기 (업데이트된 댓글 반영)
    }

    // 모달 닫기 함수
    function closeModal() {
        document.getElementById("commu-modal").style.display = "none";
    }

    // 모달 외부 클릭 시 닫기 함수
    window.onclick = function(event) {
        var modal = document.getElementById("commu-modal");
        if (event.target === modal) {
            modal.style.display = "none";
        }
    }
    
    function sortPosts(option) {
        var sortButtons = document.querySelectorAll('.commu-sort-button');
        sortButtons.forEach(function(button) {
            button.classList.remove('commu-active');
        });
        if (option === "latest") {
            document.getElementById("commu-sortLatest").classList.add('commu-active');
            posts.sort(function (a, b) {
                return new Date(b.date) - new Date(a.date);
            });
        } else if (option === "rating") {
            document.getElementById("commu-sortRating").classList.add('commu-active');
            posts.sort(function (a, b) {
                return b.rating - a.rating;
            });
        }

        filterByRegion('all'); // 정렬 후 필터 적용
    }
    
    function toggleLike() {
        var likeCount = document.getElementById("commu-modalLikes");
        var currentLikes = parseInt(likeCount.innerText);
        if (!likeCount.classList.contains("commu-liked")) {
            likeCount.innerText = currentLikes + 1;
            likeCount.classList.add("commu-liked");
        } else {
            likeCount.innerText = currentLikes - 1;
            likeCount.classList.remove("commu-liked");
        }
    }

    // 초기 게시물 표시
    filterByRegion('all');
</script>

</body>
</html>
