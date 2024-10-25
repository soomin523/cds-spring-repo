<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>마이페이지</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/mypagemain.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/mypagemain.js"></script>

    <!-- Slick Slider CSS -->
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css" />
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick-theme.css" />

    <!-- jQuery (필수) -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <!-- Slick Slider JS -->
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
</head>

<body>
<div class="section">
    <div class="background">
        <!-- 맨위 섹션 -->
        <div class="main-areaname">
            <h2>마이페이지</h2>
        </div>
        <div class="main-info">
            <div class="myinfo-top">
                <p>${member.name}</p>
                <button onclick="goAmendPage()"><img src="${pageContext.request.contextPath}/resources/img/정보수정.png" alt=""></button>
            </div>
            <div class="myinfo-sub">
                <p>${member.email}</p>
            </div>
        </div>
        <hr>
        <!-- 마이페이지 카테고리 -->
        <div class="main-category">
            <a href="mypagewrite.do">
                <div>
                    <img src="${pageContext.request.contextPath}/resources/img/작성내역.png" alt="">
                </div>
                <p>내가 쓴 글</p>
            </a>
            <a href="mypagequery.do">
                <div>
                    <img src="${pageContext.request.contextPath}/resources/img/문의하기.png" alt="">
                </div>
                <p>문의하기</p>
            </a>
        </div>
		<hr>
        <!-- 슬라이더 영역 -->
        <div class="slider">
            <div class="sliderimg"><img src="${pageContext.request.contextPath}/resources/img/banner-1.png" alt="배너1"></div>
            <div class="sliderimg"><img src="${pageContext.request.contextPath}/resources/img/banner-2.png" alt="배너2"></div>
            <div class="sliderimg"><img src="${pageContext.request.contextPath}/resources/img/banner-3.png" alt="배너3"></div>
        </div>
		<hr>
        <div class="main-logout" >
            <button onclick="location.href='logout.do'">로그아웃</button>
            <button onclick="location.href='../index.do'">홈으로</button>
        </div>
    </div>
    </div>

    <script>
        $(document).ready(function(){
            $('.slider').slick({
                autoplay: true,
                autoplaySpeed: 4000,
                dots: true,
                arrows: true,
                infinite: true,
                speed: 500,
                fade: true,
                cssEase: 'linear'
            });
        });
    </script>
</body>

</html>
