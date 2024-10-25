<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>날씨정보</title>
<script type="text/javascript"
    src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9dc9962fd8d9c313d5ca5a57212228ab&libraries=services,drawing"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<link rel="stylesheet" type="text/css"
    href="${pageContext.request.contextPath}/resources/css/weather.css">
<script src="${pageContext.request.contextPath}/resources/js/weather.js"></script>
</head>

<body>
    <div class="section">
    <div class="weatherheader"><button class="homebt" onclick="location.href='${pageContext.request.contextPath}/index.do'"></button><div id="currentDateTime"></div></div>
        <div class="grid-container">
            <!-- 지도 영역 -->
            <div class="map"><div id="map"></div></div>
            <!-- 지역 이름 표시 영역 -->
            <div id="region" class="region"></div>
            <!-- 설명 영역 -->
            <div id="exp" class="exp"></div>
        </div>
    </div>
</body>
</html>
