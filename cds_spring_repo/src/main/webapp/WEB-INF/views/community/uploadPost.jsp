<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>커뮤니티 게시물 업로드</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/member/uploadPost.css">
    <script src="../resources/js/jquery-3.7.1.min.js"></script>
    <script>
    let locations = []; // 태그를 저장할 배열
    let rating = 0; // 평점을 저장할 변수
    let imageFiles = []; // 이미지 파일을 저장할 배열

    // 이미지 미리보기 및 저장 함수
    function previewImages() {
    const fileInput = document.getElementById('imagenames');
    const fileList = Array.from(fileInput.files); // 선택된 파일을 배열로 변환
    const previewContainer = document.getElementById('image-preview');

    fileList.forEach(file => {
        const reader = new FileReader();
        reader.onload = function(e) {
            const imgContainer = document.createElement('div');
            imgContainer.style.display = 'flex';
            imgContainer.style.alignItems = 'center';
            imgContainer.style.marginRight = '10px';

            const img = document.createElement('img');
            img.src = e.target.result;
            img.style.width = '100px';
            img.style.height = '100px';

            // Remove button for the image
            const removeButton = document.createElement('button');
            removeButton.textContent = 'X';
            removeButton.style.border = 'none';
            removeButton.style.background = 'none';
            removeButton.style.color = '#ff0000';
            removeButton.style.cursor = 'pointer';
            removeButton.style.marginLeft = '5px';

            // 파일 삭제 이벤트를 추가하여 제거 시 인덱스 수정
            removeButton.onclick = function() {
                removeImage(imageFiles.indexOf(file));
            };

            imgContainer.appendChild(img);
            imgContainer.appendChild(removeButton);
            previewContainer.appendChild(imgContainer);
        };
        reader.readAsDataURL(file);
        imageFiles.push(file); // 새 파일을 imageFiles에 추가
    });

    // `DataTransfer` 객체를 사용하여 input 요소의 파일 목록을 동기화
    const dataTransfer = new DataTransfer();
    imageFiles.forEach(file => dataTransfer.items.add(file));
    fileInput.files = dataTransfer.files;
}

    // 이미지 제거 함수
function removeImage(index) {
    // 이미지 파일 배열에서 해당 인덱스의 파일 제거
    imageFiles.splice(index, 1);

    // 미리보기 컨테이너를 비우고, 남은 이미지들로 미리보기를 다시 생성
    const previewContainer = document.getElementById('image-preview');
    previewContainer.innerHTML = '';

    imageFiles.forEach((file, i) => {
        const reader = new FileReader();
        
        reader.onload = function(e) {
            const imgContainer = document.createElement('div');
            imgContainer.style.display = 'flex';
            imgContainer.style.alignItems = 'center';
            imgContainer.style.marginRight = '10px';

            const img = document.createElement('img');
            img.src = e.target.result;
            img.style.width = '100px';
            img.style.height = '100px';

            // 새 X 버튼 생성
            const removeButton = document.createElement('button');
            removeButton.textContent = 'X';
            removeButton.style.border = 'none';
            removeButton.style.background = 'none';
            removeButton.style.color = '#ff0000';
            removeButton.style.cursor = 'pointer';
            removeButton.style.marginLeft = '5px';
            
            // 클로저를 사용하여 index의 i 값을 저장
            removeButton.onclick = function() {
                removeImage(i); // 새로 만든 removeImage 호출
            };

            imgContainer.appendChild(img);
            imgContainer.appendChild(removeButton);
            previewContainer.appendChild(imgContainer);
        };

        reader.readAsDataURL(file);
    });

    // DataTransfer로 파일 배열을 업데이트하여 input 요소에 반영
    const dataTransfer = new DataTransfer();
    imageFiles.forEach(file => dataTransfer.items.add(file));
    document.getElementById('imagenames').files = dataTransfer.files;
}


    // 태그 추가 함수
    function addLocation() {
        const locationInput = document.getElementById('location2');
        const locationValue = locationInput.value.trim();

        if (locationValue && !locations.includes(locationValue)) {
            locations.push(locationValue); // 위치 추가
            $("#tag").val(locations.join(','));
            console.log($("#tag").val());
            
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
        document.getElementById('imagenames').addEventListener('change', previewImages);
    });

    // 태그를 화면에 표시하는 함수
    function displayLocations() {
        const modalContainer = document.getElementById('modal-container');
        modalContainer.innerHTML = ''; // 모달 초기화
        modalContainer.style.display = 'flex'; // 모달 표시

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
        starnum = value;
        const stars = document.querySelectorAll('.star');
        stars.forEach((star, index) => {
            star.classList.toggle('filled', index < starnum); // 1점 단위로 별 채우기
        });
        $(".starnumber").val(value);
    }

</script>

</head>
<body>
    <div class="commu-upload-container">
        <h2>
        	게시물 올리기📝&nbsp;
   <c:if test="${ not empty msg }">
        	<span class="failupload">${ msg }</span>
   </c:if>
        </h2>
		
        <form id="uploadForm" action="uploadPost.do" method="post" enctype="multipart/form-data">
            <label class="commu-label" for="title">제목</label>
            <input type="text" id="title" name="title" class="commu-input commu-placeholder" required placeholder="제목을 입력하세요.">
			<input type="hidden" id="memberId" name="memberId" value="${ member.member_id }">

            <label class="commu-label" for="content">내용</label>
            <textarea id="content" name="content" class="commu-textarea commu-placeholder" required placeholder="내용을 입력하세요."></textarea>

            <div style="display: flex; gap: 10px; position: relative;">
                <!-- 지역 선택 필드 -->
                <div style="flex: 1;">
                    <label class="commu-label" for="location">지역 선택</label>
                    <select id="location" name="location" class="commu-select" style="width: 50%;" required>
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

                <!-- 해시 태그 필드 -->
                <div style="flex: 1; display: flex; align-items: center;">
                    <label class="commu-label" for="location2" style="width: auto; margin-right: 10px; margin-top: 10px;">해시 태그</label>
                    <input type="text" id="location2" name="location2" class="commu-location-input" placeholder="태그를 입력하세요." style="flex: 1; margin-top: 20px;">
                    <button type="button" class="commu-button" onclick="addLocation()" style="margin-left: 10px;">추가</button>
					<input type="hidden" id="tag" name="tag">
		            <!-- 위치 태그 모달 -->
		            <div id="modal-container" class="modal-container"></div>
                </div>
                
            </div>


           <label class="commu-label">평점</label>
			<div style="margin-bottom: 10px;">
			    <span class="star" onclick="selectRating(1)">&#9733;</span>
			    <span class="star" onclick="selectRating(2)">&#9733;</span>
			    <span class="star" onclick="selectRating(3)">&#9733;</span>
			    <span class="star" onclick="selectRating(4)">&#9733;</span>
			    <span class="star" onclick="selectRating(5)">&#9733;</span>
			</div>
			<input type="hidden" class="starnumber" id="rating" name="rating" value="0">

       <label class="commu-label" for="imagenames">이미지 업로드</label>
<div class="image-upload-container">
    <input type="file" id="imagenames" name="imagenames" class="commu-file-input" accept="image/*" multiple required style="display: none;">
    <label class="upload-button" for="imagenames" style="cursor: pointer; display: flex; flex-direction: column; align-items: center;">
        <img src="${pageContext.request.contextPath}/resources/img/사진첨부.png" alt="사진 아이콘" style="width: 60px; height: 60px; vertical-align: middle;">
        <span style="margin-top: 5px;">사진 추가</span>
    </label>
    
</div>

<div id="image-preview" style="display: flex; flex-wrap: wrap; margin-top: 10px;"></div>


        <div class="commu-button-container">
            <a href="${pageContext.request.contextPath}/community/commu.do">
                <button type="button" class="commu-button">취소</button>
            </a>
            <button type="submit" class="commu-button">게시하기</button>
        </div>
        </form>


<!-- 미리보기 이미지 컨테이너 추가 -->
    </div>
</body>
</html>
