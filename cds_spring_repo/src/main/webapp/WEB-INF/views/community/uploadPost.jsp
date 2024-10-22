<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>커뮤니티 게시물 업로드</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/member/uploadPost.css">
    <script>
    let locations = []; // 위치를 저장할 배열
    let rating = 0; // 평점을 저장할 변수
    let imageFiles = []; // 이미지 파일을 저장할 배열

    // 위치 추가 함수
    function addLocation() {
        const locationInput = document.getElementById('location');
        const locationValue = locationInput.value.trim();

        if (locationValue && !locations.includes(locationValue)) {
            locations.push(locationValue); // 위치 추가
            displayLocations(); // 화면에 위치 표시
            locationInput.value = ''; // 입력 필드 초기화
        } else if (locations.includes(locationValue)) {
            alert("이미 추가된 위치입니다."); // 중복 경고
        } else {
            alert("위치를 입력하세요."); // 비어있을 때 경고
        }
    }

    // 이미지 미리보기 및 저장 함수
    function previewImages() {
        const fileInput = document.getElementById('images');
        const fileList = fileInput.files;
        const previewContainer = document.getElementById('image-preview');

        previewContainer.innerHTML = ''; // 기존 미리보기 초기화
        imageFiles = []; // 이전 파일 목록 초기화

        for (let i = 0; i < fileList.length; i++) {
            const file = fileList[i];
            const reader = new FileReader();

            reader.onload = function(e) {
                const imgContainer = document.createElement('div');
                imgContainer.style.display = 'flex';
                imgContainer.style.alignItems = 'center';
                imgContainer.style.marginRight = '10px';

                const img = document.createElement('img');
                img.src = e.target.result;
                img.style.width = '100px'; // 미리보기 이미지 크기 조정
                img.style.height = '100px'; // 미리보기 이미지 크기 조정

                // Remove button for the image
                const removeButton = document.createElement('button');
                removeButton.textContent = 'X';
                removeButton.style.border = 'none';
                removeButton.style.background = 'none';
                removeButton.style.color = '#ff0000'; // 빨간색으로 X 버튼
                removeButton.style.cursor = 'pointer';
                removeButton.style.marginLeft = '5px';
                removeButton.onclick = function() {
                    removeImage(i); // 이미지 제거 함수 호출
                };

                imgContainer.appendChild(img);
                imgContainer.appendChild(removeButton);
                previewContainer.appendChild(imgContainer);
                imageFiles.push(file); // 파일 목록에 추가
            };

            reader.readAsDataURL(file);
        }
    }

    // 이미지 제거 함수
    function removeImage(index) {
        imageFiles.splice(index, 1); // 해당 인덱스의 이미지 파일 제거
        const fileInput = document.getElementById('images');
        const dataTransfer = new DataTransfer();
        imageFiles.forEach(file => dataTransfer.items.add(file)); // 남은 파일을 DataTransfer에 추가
        fileInput.files = dataTransfer.files; // input 요소의 files 업데이트
        previewImages(); // 미리보기 갱신
    }

   
    // 위치 추가 함수
    function addLocation() {
        const locationInput = document.getElementById('location');
        const locationValue = locationInput.value.trim();

        if (locationValue && !locations.includes(locationValue)) {
            locations.push(locationValue); // 위치 추가
            displayLocations(); // 화면에 위치 표시
            locationInput.value = ''; // 입력 필드 초기화
        } else if (locations.includes(locationValue)) {
            alert("이미 추가된 위치입니다."); // 중복 경고
        } else {
            alert("위치를 입력하세요."); // 비어있을 때 경고
        }
    }
    
    // DOMContentLoaded 이벤트를 사용하여 페이지가 로드되었을 때 이벤트 리스너 추가
    document.addEventListener('DOMContentLoaded', function() {
        document.getElementById('images').addEventListener('change', previewImages);
    });

    // 위치를 화면에 표시하는 함수
    function displayLocations() {
        const modalContainer = document.getElementById('modal-container');
        modalContainer.innerHTML = ''; // 모달 초기화
        modalContainer.style.display = 'block'; // 모달 표시

        locations.forEach((location, index) => {
            const locationTag = document.createElement('div');
            locationTag.classList.add('location-tag');
            locationTag.style.display = 'flex';
            locationTag.style.alignItems = 'center';
            locationTag.style.padding = '5px 10px';
            locationTag.style.border = '1px solid #007bff';
            locationTag.style.borderRadius = '15px';
            locationTag.style.backgroundColor = '#e9f7ff';
            locationTag.style.margin = '2px'; // 태그 간의 간격

            // 위치 내용 추가
            const locationName = document.createElement('span');
            locationName.textContent = location;
            locationName.style.marginRight = '10px'; // X 버튼과 간격

            locationTag.appendChild(locationName);

            // 지우기 버튼
            const removeButton = document.createElement('button');
            removeButton.textContent = 'X';
            removeButton.className = 'remove-location-button'; // 클래스 추가
            removeButton.style.border = 'none';
            removeButton.style.background = 'none';
            removeButton.style.color = '#ff0000'; // 빨간색으로 X 버튼
            removeButton.style.cursor = 'pointer';
            removeButton.onclick = function() {
                removeLocation(index);
            };

            locationTag.appendChild(removeButton);
            modalContainer.appendChild(locationTag); // 모달에 태그 추가
        });
    }

    // 위치 제거 함수
    function removeLocation(index) {
        locations.splice(index, 1); // 해당 인덱스의 위치 제거
        displayLocations(); // 변경된 리스트 표시
    }

    // 평점 선택 함수
    function selectRating(value) {
        rating = value;
        const stars = document.querySelectorAll('.star');
        stars.forEach((star, index) => {
            star.classList.toggle('filled', index < rating); // 1점 단위로 별 채우기
        });
    }

    // 게시하기 함수 (예시로 추가, 필요한 경우 실제 로직으로 변경)
    function submitPost() {
        // 게시물 제출 로직 추가
        alert("게시물이 제출되었습니다.\n평점: " + rating + "점\n위치: " + locations.join(", ")); // 예시 알림
    }
</script>

    <style>
        .modal-container {
            border: 1px solid #007bff;
            border-radius: 5px;
            background-color: #f1f1f1;
            padding: 10px;
            z-index: 1000; // 모달이 다른 요소 위에 오도록 조정
            position: absolute; // 버튼 오른쪽에 위치하도록 설정
            top: 30px; // 버튼 아래에 위치하도록 조정
            left: 210px; // 버튼 오른쪽에 위치하도록 조정 (버튼 넓이에 맞게 조절)
            display: none; // 처음에 보이지 않도록 설정
        }

        .location-tag {
            display: flex;
            align-items: center;
            padding: 5px 10px;
            margin: 2px;
        }

        .star {
            font-size: 24px;
            color: #ccc; /* 기본 별 색상 */
            cursor: pointer;
        }

        .star.filled {
            color: #ffcc00; /* 채워진 별 색상 */
        }
    </style>
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
                <div id="location-list" style="display: flex; flex-wrap: wrap; gap: 5px; margin-top: 10px;"></div>
            </div>

            <!-- 위치 태그 모달 -->
            <div id="modal-container" class="modal-container"></div>

           <label class="commu-label">평점</label>
			<div style="margin-bottom: 10px;">
			    <span class="star" onclick="selectRating(1)">&#9733;</span>
			    <span class="star" onclick="selectRating(2)">&#9733;</span>
			    <span class="star" onclick="selectRating(3)">&#9733;</span>
			    <span class="star" onclick="selectRating(4)">&#9733;</span>
			    <span class="star" onclick="selectRating(5)">&#9733;</span>
			</div>

        </form>

       <label class="commu-label" for="images">이미지 업로드</label>
<div class="image-upload-container">
    <input type="file" id="images" name="images" class="commu-file-input" accept="image/*" multiple required style="display: none;">
    <label class="upload-button" for="images" style="cursor: pointer; display: flex; flex-direction: column; align-items: center;">
        <img src="${pageContext.request.contextPath}/resources/img/사진첨부.png" alt="사진 아이콘" style="width: 60px; height: 60px; vertical-align: middle;">
        <span style="margin-top: 5px;">사진 추가</span>
    </label>
</div>

<!-- 미리보기 이미지 컨테이너 추가 -->
<div id="image-preview" style="display: flex; flex-wrap: wrap; margin-top: 10px;"></div>


        <div class="commu-button-container">
            <a href="${pageContext.request.contextPath}/community/commu.do">
                <button type="button" class="commu-button">커뮤니티 보기</button>
            </a>
            <button type="button" class="commu-button" onclick="submitPost()">게시하기</button>
        </div>
    </div>
</body>
</html>
