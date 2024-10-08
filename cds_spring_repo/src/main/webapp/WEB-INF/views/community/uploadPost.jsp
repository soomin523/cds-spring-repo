<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>커뮤니티 게시물 업로드</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/uploadPost.css">    
</head>
<body>
<div class="commu-upload-container">
   <h2>게시물 올리기📝</h2>

   <form id="uploadForm">
    <label class="commu-label" for="title">제목</label>
    <input type="text" id="title" name="title" class="commu-input commu-placeholder" required placeholder="제목을 입력하세요.">

    <label class="commu-label" for="content">내용</label>
    <textarea id="content" name="content" class="commu-textarea commu-placeholder" required placeholder="내용을 입력하세요."></textarea>

<div style="display: flex; gap: 10px; position: relative;">
    <!-- 지역 선택 필드 -->
    <div style="flex: 1;">
        <label class="commu-label" for="region">지역 선택</label>
        <select id="region" name="region" class="commu-select" style="width: 50%;" required>
            <option value="서울">서울</option>
            <option value="경기">경기</option>
            <option value="인천">인천</option>
            <option value="대전">대전</option>
            <option value="강원">강원</option>
            <option value="광주">광주</option>
            <option value="울산">울산</option>
            <option value="대구">대구</option>
            <option value="부산">부산</option>
            <option value="충북">충북</option>
            <option value="충남">충남</option>
            <option value="전북">전북</option>
            <option value="전남">전남</option>
            <option value="경북">경북</option>
            <option value="경남">경남</option>
            <option value="제주">제주</option>
        </select>
    </div>

<!-- 위치 추가 필드 -->
<div style="flex: 1; display: flex; align-items: center;">
    <label class="commu-label" for="location" style="width: auto; margin-right: 10px; margin-top: 10px;">위치 추가</label>
    <input type="text" id="location" name="location" class="commu-location-input" placeholder="위치를 입력하세요." style="flex: 1; margin-top: 20px;">
    <button type="button" class="commu-button" onclick="addLocation()" style="margin-left: 10px;">추가</button>
</div>




    <!-- 추가된 위치를 표시하는 영역 -->
    <div id="location-list"></div>
</div>



<label class="commu-label" for="images">이미지 업로드</label>
<div class="image-upload-container">
    <input type="file" id="images" name="images" class="commu-file-input" accept="image/*" multiple required style="display: none;"> <!-- 파일 입력 숨기기 -->
    <label class="upload-button" for="images" style="cursor: pointer; display: flex; flex-direction: column; align-items: center;">
        <img src="사진첨부.png" alt="사진 아이콘" style="width: 60px; height: 60px; vertical-align: middle;"> <!-- 아이콘 크기 변경 -->
        <span style="margin-top: 5px;">사진 추가</span> <!-- 아이콘 아래에 텍스트 배치 -->
    </label>
</div>

<!-- 이미지 미리보기 영역 추가 -->
<div class="commu-upload-images" id="imagePreview"></div>

<div class="commu-button-container">
        <button type="button" class="commu-button" onclick="window.location.href='community.jsp'">커뮤니티 보기</button>
        <button type="button" class="commu-button" onclick="submitPost()">게시하기</button>
    </div>

   </form>
</div>

<script>
    let locations = [];

 // 위치 추가 함수
    function addLocation() {
        const locationInput = document.getElementById('location');
        const locationValue = locationInput.value.trim();

        if (locationValue && !locations.includes(locationValue)) {
            locations.push(locationValue);
            displayLocations();  // 입력한 위치를 화면에 표시하는 함수 호출
        }

        locationInput.value = ''; // 입력 필드 초기화
    }

    // 위치 삭제 함수
    function removeLocation(index) {
        locations.splice(index, 1);
        displayLocations();  // 삭제된 후 위치 목록을 다시 표시
    }


 // 위치를 화면에 표시하는 함수
    function displayLocations() {
        const locationList = document.getElementById('location-list');
        locationList.innerHTML = ''; // 기존 리스트 초기화

        locations.forEach((location, index) => {
            const locationTag = document.createElement('div');
            locationTag.classList.add('location-tag');
            locationTag.style.display = 'flex'; // 유연하게 가로로 배치

            // 위치 내용 추가
      const locationName = document.createElement('span');
locationName.textContent = location;
locationName.style.marginTop = '2px';  // 글자를 아래로 내리기
locationTag.appendChild(locationName);


            // 지우기 버튼
            const removeButton = document.createElement('button');
            removeButton.textContent = 'X';
            removeButton.className = 'remove-location-button'; // 클래스 추가
            removeButton.onclick = function() {
                removeLocation(index);
            };

            // 지우기 버튼을 위치 태그의 오른쪽 끝으로 이동
            locationTag.style.justifyContent = 'space-between'; // 가로 방향으로 배치
            locationTag.appendChild(removeButton);

            locationList.appendChild(locationTag);
        });
    }



    // 이미지 미리보기 기능
    const imageInput = document.getElementById('images');
    const imagePreview = document.getElementById('imagePreview');



    imageInput.addEventListener('change', function() {
        imagePreview.innerHTML = ''; // 기존 미리보기 삭제

        Array.from(this.files).forEach(file => {
            const reader = new FileReader();
            reader.onload = function(e) {
                const img = document.createElement('img');
                img.src = e.target.result;
                imagePreview.appendChild(img);
            }
            reader.readAsDataURL(file);
        });
    });

    function submitPost() {
        const title = document.getElementById('title').value;
        const content = document.getElementById('content').value;
        const region = document.getElementById('region').value;
        const files = imageInput.files;

        if (!title || !content || !region || files.length === 0) {
            alert('모든 필드를 채워주세요!');
            return;
        }

        // FormData를 사용하여 데이터와 파일 전송
        const formData = new FormData();
        formData.append('title', title);
        formData.append('content', content);
        formData.append('region', region);

        // 위치 데이터를 FormData에 추가
        locations.forEach((location, index) => {
            formData.append(`location[${index}]`, location); // 추가된 위치 데이터를 전송
        });

        Array.from(files).forEach(file => {
            formData.append('images', file);
        });

        // 서버로 게시물 전송 (Ajax 또는 Fetch 사용)
        fetch('/uploadPost', {
            method: 'POST',
            body: formData
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                alert('게시물이 성공적으로 업로드되었습니다!');
                window.location.href = '/community';  // 게시물 목록으로 이동
            } else {
                alert('업로드에 실패했습니다.');
            }
        })
        .catch(error => {
            console.error('에러:', error);
            alert('에러가 발생했습니다.');
        });
    }


    // 페이지 로드 후 '게시물 올리기' 버튼을 community.jsp에도 추가
    window.onload = function() {
        const header = document.querySelector(".commu-header");
        if (header) {
            const postButton = document.createElement("button");
            postButton.className = "commu-post-button";
            postButton.textContent = "게시물 올리기";
            postButton.onclick = function() {
                window.location.href = 'uploadPost.jsp';
            };
            header.appendChild(postButton);
        }
    };
</script>


<script>
    // 이미지 미리보기 기능
    const imageInput = document.getElementById('images');
    const imagePreview = document.getElementById('imagePreview');

    imageInput.addEventListener('change', function() {
        imagePreview.innerHTML = ''; // 기존 미리보기 삭제

        Array.from(this.files).forEach(file => {
            const reader = new FileReader();
            reader.onload = function(e) {
                const img = document.createElement('img');
                img.src = e.target.result;
                imagePreview.appendChild(img);
            }
            reader.readAsDataURL(file);
        });
    });

    function submitPost() {
        const title = document.getElementById('title').value;
        const content = document.getElementById('content').value;
        const region = document.getElementById('region').value;
        const files = imageInput.files;

        if (!title || !content || !region || files.length === 0) {
            alert('모든 필드를 채워주세요!');
            return;
        }

     // FormData를 사용하여 데이터와 파일 전송
        const formData = new FormData();
        formData.append('title', title);
        formData.append('content', content);
        formData.append('region', region);
        Array.from(files).forEach(file => {
            formData.append('images', file);
        });


        // 서버로 게시물 전송 (Ajax 또는 Fetch 사용)
        fetch('/uploadPost', {
            method: 'POST',
            body: formData
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                alert('게시물이 성공적으로 업로드되었습니다!');
                window.location.href = '/community';  // 게시물 목록으로 이동
            } else {
                alert('업로드에 실패했습니다.');
            }
        })
        .catch(error => {
            console.error('에러:', error);
            alert('에러가 발생했습니다.');
        });
    }

    // 페이지 로드 후 '게시물 올리기' 버튼을 community.jsp에도 추가
    window.onload = function() {
        const header = document.querySelector(".commu-header");
        if (header) {
            const postButton = document.createElement("button");
            postButton.className = "commu-post-button";
            postButton.textContent = "게시물 올리기";
            postButton.onclick = function() {
                window.location.href = 'uploadPost.jsp';
            };
            header.appendChild(postButton);
        }
    };
</script>

</body>
</html>
