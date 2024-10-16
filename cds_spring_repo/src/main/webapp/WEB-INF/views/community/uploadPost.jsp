
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>커뮤니티 게시물 업로드</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/member/uploadPost.css">
    <script src="${pageContext.request.contextPath}/resources/js/uploadPost.js"></script>
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
            </div>

            <!-- 추가된 위치를 표시하는 영역 -->
            <div id="location-list"></div>
        </form>

        <label class="commu-label" for="images">이미지 업로드</label>
        <div class="image-upload-container">
            <input type="file" id="images" name="images" class="commu-file-input" accept="image/*" multiple required style="display: none;">
            <!-- 파일 입력 숨기기 -->
            <label class="upload-button" for="images" style="cursor: pointer; display: flex; flex-direction: column; align-items: center;">
                <img src="${pageContext.request.contextPath}/resources/img/사진첨부.png" alt="사진 아이콘" style="width: 60px; height: 60px; vertical-align: middle;">
                <!-- 아이콘 크기 변경 -->  
                <span style="margin-top: 5px;">사진 추가</span> <!-- 아이콘 아래에 텍스트 배치 -->
            </label>
        </div>

        <!-- 이미지 미리보기 영역 추가 -->
        <div class="commu-button-container">
            <!-- 커뮤니티 보기 버튼 -->
            <a href="${pageContext.request.contextPath}/community/commu.do">
                <button type="button" class="commu-button">커뮤니티 보기</button>
            </a>

            <!-- 게시하기 버튼 -->
            <button type="button" class="commu-button" onclick="submitPost()">게시하기</button>
        </div>
    </div>
</body>
</html>
