<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>마이페이지</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/mypagelike.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/mypagelike.js"></script>
</head>

<body>
    <div class="background">
        <div class="mylike-title">
            <h2>즐겨찾기</h2>
        </div>
        <div class="mylike-category-button">
            <button class="category-btn" id="redirectButton" data-category="코스">코스</button>
            <button class="category-btn" data-category="축제">축제</button>
            <button class="category-btn" data-category="상품">상품</button>
        </div>
        <div class="mylike-totalnum">
            <p>총</p>
            <p id="total-count">#</p>
            <p>개</p>
        </div>
        <div class="mylike-none">
            <p>즐겨찾기 한 내역이 없습니다</p>
        </div>
        <div class="mylike-exist" data-category="코스">
            <div class="mylike-exist-data">
                <img src="${pageContext.request.contextPath}/resources/img/정보수정.png">
                <p>코스 컨텐츠 이름</p>
            </div>
        </div>
        <div class="mylike-exist" data-category="축제">
            <div class="mylike-exist-data">
                <img src="${pageContext.request.contextPath}/resources/img/정보수정.png">
                <p>축제 컨텐츠 이름</p>
            </div>
        </div>
        <div class="mylike-exist" data-category="상품">
            <div class="mylike-exist-data">
                <img src="${pageContext.request.contextPath}/resources/img/정보수정.png">
                <p>상품 컨텐츠 이름</p>
            </div>
        </div>
        <div class="mylike-random-festival-title">
            <h2>랜덤으로 훑어보는 축제!</h2>
        </div>
        <div class="mylike-random-festival custom-slider">
            <div class="custom-slider-item">
                <img src="${pageContext.request.contextPath}/resources/img/정보수정.png">
                <p>(지역)축제이름</p>
            </div>
            <div class="custom-slider-item">
                <img src="${pageContext.request.contextPath}/resources/img/정보수정.png">
                <p>(지역)관광지이름</p>
            </div>
            <div class="custom-slider-item">
                <img src="${pageContext.request.contextPath}/resources/img/정보수정.png">
                <p>(지역)관광지이름</p>
            </div>
            <div class="custom-slider-item">
                <img src="${pageContext.request.contextPath}/resources/img/정보수정.png">
                <p>(지역)관광지이름</p>
            </div>
            <div class="custom-slider-item">
                <img src="${pageContext.request.contextPath}/resources/img/정보수정.png">
                <p>(지역)관광지이름</p>
            </div>
            <div class="custom-slider-item">
                <img src="${pageContext.request.contextPath}/resources/img/정보수정.png">
                <p>(지역)관광지이름</p>
            </div>
            <div class="custom-slider-item">
                <img src="${pageContext.request.contextPath}/resources/img/정보수정.png">
                <p>(지역)관광지이름</p>
            </div>
            <div class="custom-slider-item">
                <img src="${pageContext.request.contextPath}/resources/img/정보수정.png">
                <p>(지역)관광지이름</p>
            </div>
            <div class="custom-slider-item">
                <img src="${pageContext.request.contextPath}/resources/img/정보수정.png">
                <p>(지역)관광지이름</p>
            </div>
        </div>
        <div class="mylike-random-destination-title">
            <h2>떠나자에서 추천하는 관광지</h2>
        </div>
        <div class="mylike-random-destination custom-slider">
            <div class="custom-slider-item">
                <img src="${pageContext.request.contextPath}/resources/img/정보수정.png">
                <p>(지역)관광지이름</p>
            </div>
            <div class="custom-slider-item">
                <img src="${pageContext.request.contextPath}/resources/img/정보수정.png">
                <p>(지역)관광지이름</p>
            </div>
            <div class="custom-slider-item">
                <img src="${pageContext.request.contextPath}/resources/img/정보수정.png">
                <p>(지역)관광지이름</p>
            </div>
            <div class="custom-slider-item">
                <img src="${pageContext.request.contextPath}/resources/img/정보수정.png">
                <p>(지역)관광지이름</p>
            </div>
            <div class="custom-slider-item">
                <img src="${pageContext.request.contextPath}/resources/img/정보수정.png">
                <p>(지역)관광지이름</p>
            </div>
            <div class="custom-slider-item">
                <img src="${pageContext.request.contextPath}/resources/img/정보수정.png">
                <p>(지역)관광지이름</p>
            </div>
            <div class="custom-slider-item">
                <img src="${pageContext.request.contextPath}/resources/img/정보수정.png">
                <p>(지역)관광지이름</p>
            </div>
            <div class="custom-slider-item">
                <img src="${pageContext.request.contextPath}/resources/img/정보수정.png">
                <p>(지역)관광지이름</p>
            </div>
        </div>
    </div>
</body>

</html>