<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>커뮤니티 페이지</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/community.css">
</head>   
<body>

<div class="commu-container">
    <!-- 메인 콘텐츠 영역 -->
    <div class="commu-content">
<div class="commu-header">
    <h2 style="display: inline-block; margin-right: 20px;">떠나자 커뮤니티 ✍️</h2>
    <button class="commu-post-button" style="display: inline-block;" onclick="window.location.href='uploadPost.jsp'">게시물 올리기</button>

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
    <img src="돋보기.png" alt="Location Icon">
    <input type="text" placeholder="지역명, 제목, ID 검색 가능" id="commu-regionSearch" onkeyup="searchPosts()">
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
   
<script>
    var currentPost; // 전역 변수로 currentPost 선언
    var isAdmin = true; // 관리자 여부를 설정하는 변수
    var currentUserId = "User_ID"; // 현재 접속한 사용자의 ID

    // 게시물 데이터 예시
    var posts = [
    {
        title: '게시물 제목 1',
        date: '2024-09-29',
        views: 120,
        likes: 10,
        comments: 0,
        rating: 4.5,
        location: '서울',
        profile: 'https://via.placeholder.com/30',
        user: 'User_ID',
        images: ['https://via.placeholder.com/100', 'https://via.placeholder.com/101', 'https://via.placeholder.com/102'], // 여러 이미지 추가
        description: '게시물 내용 1',
        commentsData: []
    },
    {
        title: '게시물 제목 2',
        date: '2024-09-28',
        views: 200,
        likes: 50,
        comments: 25,
        rating: 3.8,
        location: '부산',
        profile: 'https://via.placeholder.com/30',
        user: 'User_ID_2',
        images: ['https://via.placeholder.com/100', 'https://via.placeholder.com/101', 'https://via.placeholder.com/102'], // 여러 이미지 추가
        description: '게시물 내용 2',
        commentsData: ['comment 3', 'comment 4']
    },
    {
        title: '게시물 제목 3',
        date: '2024-09-27',
        views: 50,
        likes: 10,
        comments: 5,
        rating: 5.0,
        location: '대구',
        profile: 'https://via.placeholder.com/30',
        user: 'User_ID_3',
        images: ['https://via.placeholder.com/100', 'https://via.placeholder.com/101', 'https://via.placeholder.com/102'], // 여러 이미지 추가
        description: '게시물 내용 3',
        commentsData: ['comment 5']
    },
    {
        title: '게시물 제목 4',
        date: '2024-09-26',
        views: 75,
        likes: 12,
        comments: 3,
        rating: 4.0,
        location: '인천',
        profile: 'https://via.placeholder.com/30',
        user: 'User_ID_4',
        images: ['https://via.placeholder.com/100', 'https://via.placeholder.com/101', 'https://via.placeholder.com/102'], // 여러 이미지 추가
        description: '게시물 내용 4',
        commentsData: ['comment 6', 'comment 7']
    },
    {
        title: '게시물 제목 5',
        date: '2024-09-25',
        views: 45,
        likes: 5,
        comments: 0,
        rating: 3.5,
        location: '서울',
        profile: 'https://via.placeholder.com/30',
        user: 'User_ID',
        images: ['https://via.placeholder.com/100', 'https://via.placeholder.com/101', 'https://via.placeholder.com/102'], // 여러 이미지 추가
        description: '게시물 내용 5',
        commentsData: []
    },
    {
        title: '게시물 제목 6',
        date: '2024-09-24',
        views: 180,
        likes: 30,
        comments: 20,
        rating: 4.8,
        location: '부산',
        profile: 'https://via.placeholder.com/30',
        user: 'User_ID_5',
        images: ['https://via.placeholder.com/100', 'https://via.placeholder.com/101', 'https://via.placeholder.com/102'], // 여러 이미지 추가
        description: '게시물 내용 6',
        commentsData: ['comment 9', 'comment 10']
    }];

    var currentPage = 1;
    var postsPerPage = 12;

    function displayPosts(page, filteredPosts) {
        var postList = document.getElementById("commu-postList");
        postList.innerHTML = "";
        var start = (page - 1) * postsPerPage;
        var end = start + postsPerPage;
        var paginatedPosts = filteredPosts.slice(start, end);

        paginatedPosts.forEach(function (post) {
            // 좋아요와 댓글 수를 확인하기 위한 로그 출력
            console.log("Likes: ", post.likes, "Comments: ", post.comments);

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

            // 좋아요와 댓글 수를 동적으로 설정
            var likesCommentsDiv = document.createElement("div");
            likesCommentsDiv.className = "commu-likes-comments";
            likesCommentsDiv.innerHTML = `<span class="commu-icon">👍 ${post.likes}</span>
                                          <span class="commu-icon">💬 ${post.comments}</span>`;
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
    function searchPosts() {
        var searchQuery = document.getElementById("commu-regionSearch").value.toLowerCase();

        // 지역, 제목, 사용자 아이디를 모두 검색할 수 있도록 필터링
        var filteredPosts = posts.filter(function(post) {
            var postLocation = post.location.toLowerCase();
            var postTitle = post.title.toLowerCase();
            var postUser = post.user.toLowerCase();
            return postLocation.includes(searchQuery) || postTitle.includes(searchQuery) || postUser.includes(searchQuery);
        });

        displayPosts(currentPage, filteredPosts);
    }



    function filterByRegion(region) {
        // 모든 버튼의 selected 클래스 제거
        var buttons = document.querySelectorAll('.commu-button'); // 모든 지역 버튼을 선택
        buttons.forEach(function(button) {
            button.classList.remove('selected'); // 선택된 버튼의 'selected' 클래스를 제거
        });

        // 클릭한 버튼에 selected 클래스 추가
        var selectedButton = Array.from(buttons).find(function(button) {
            return button.innerText === region || (region === 'all' && button.innerText === '모든 지역');
        });
        if (selectedButton) {
            selectedButton.classList.add('selected'); // 선택된 버튼에 'selected' 클래스 추가
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
        var currentSlideIndex = 0;

        function openModal(post) {
            currentPost = post; 
            currentSlideIndex = 0;  // 첫 번째 이미지로 초기화

            document.getElementById("commu-modalProfileImage").src = post.profile;
            document.getElementById("commu-modalUserId").innerText = post.user;
            document.getElementById("commu-modalLocation").innerText = post.location;
            document.getElementById("commu-modalTitle").innerText = post.title;
            document.getElementById("commu-modalMeta").innerText = "작성일: " + post.date + " | 조회수: " + post.views;
            document.getElementById("commu-modalLikes").innerText = post.likes;
            document.getElementById("commu-modalCommentsCount").innerText = post.comments;
            document.getElementById("commu-modalDescription").innerText = post.description;

            // 첫 번째 이미지 표시
            document.getElementById("commu-modalImage").src = post.images[currentSlideIndex];

            document.getElementById("commu-modal").style.display = "flex";  // 모달 열기
        }

        function nextSlide() {
            currentSlideIndex = (currentSlideIndex + 1) % currentPost.images.length;
            document.getElementById("commu-modalImage").src = currentPost.images[currentSlideIndex];
        }

        function prevSlide() {
            currentSlideIndex = (currentSlideIndex - 1 + currentPost.images.length) % currentPost.images.length;
            document.getElementById("commu-modalImage").src = currentPost.images[currentSlideIndex];
        }

        document.getElementById("commu-modalTitle").innerText = post.title;
        document.getElementById("commu-modalMeta").innerText = "작성일: " + post.date + " | 조회수: " + post.views;
        document.getElementById("commu-modalLikes").innerText = post.likes;
        document.getElementById("commu-modalCommentsCount").innerText = post.comments;
        document.getElementById("commu-modalDescription").innerText = post.description;

     // 평점 추가 부분 수정
        var modalRating = document.querySelector(".commu-modal-rating"); // 수정된 클래스 이름 사용
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
        // 모든 버튼의 active 클래스 제거
        sortButtons.forEach(function(button) {
            button.classList.remove('active'); // 'active' 클래스로 변경
        });

        // 선택된 버튼에 active 클래스 추가
        if (option === "latest") {
            document.getElementById("commu-sortLatest").classList.add('active'); // 'commu-active'에서 'active'로 변경
            posts.sort(function (a, b) {
                return new Date(b.date) - new Date(a.date);
            });
        } else if (option === "rating") {
            document.getElementById("commu-sortRating").classList.add('active'); // 'commu-active'에서 'active'로 변경
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
