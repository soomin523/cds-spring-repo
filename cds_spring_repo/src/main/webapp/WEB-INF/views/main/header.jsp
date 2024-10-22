<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>떠나자</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main.css">
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.1.min.js"></script>
</head>
<body>
    <header>
    	<div class="headerLogo">
    		<img src="${pageContext.request.contextPath}/resources/img/떠나자logo.png" />
    	</div>
    	<div class="pageLink">
    		<div class="destination">관광지</div>
    		<div class="festival">축제/행사</div>
    		<div class="tourCourse">여행 코스</div>
    		<div class="gift">관광 상품</div>
    		<div class="accommodation">숙소</div>
    		<div class="community">여행공유</div>
    		<div class="support">고객센터</div>
    		<div class="manager">관리자</div>
    	</div>
  <c:if test="${ not empty member }">
  		<button class="mypage-btn"> 
            <img src="${pageContext.request.contextPath}/resources/img/프로필.png" />
        </button>
  </c:if>
  <c:if test="${ empty member }">
        <button class="log-login-btn"> 
            <img src="${pageContext.request.contextPath}/resources/img/프로필.png" />
        </button>
  </c:if>
    </header>
    
    <script>
    	$(function(){
    		
    		//화면 이동
    		$(".headerLogo").click(function(){
                location.href = "index.do";
            });
            $(".destination").click(function(){
                location.href = "destination/destination.do";
            });
            $(".festival").click(function(){
                location.href = "festival/getFestivalList.do";
            });
            $(".tourCourse").click(function(){
                location.href = "tourCourse/Course.do";
            });
            $(".gift").click(function(){
                location.href = "products/getProductsList.do";
            });
            $(".accommodation").click(function(){
                location.href = "accommodations/accommo.do";
            });
            $(".community").click(function(){
                location.href = "index.do";
            });
            $(".support").click(function(){
                location.href = "support/support.do";
            });
            $(".manager").click(function(){
                location.href = "manager/manager.do";
            });
            $(".log-login-btn").click(function(){
                location.href = "member/login.do";
            });
            $(".mypage-btn").click(function(){
                location.href = "mypage/mypagemain.do";
            });
            
            //스크롤 시 배경 하얀색 채우기
            $(window).scroll(function () {
                if ($(this).scrollTop() === 0) {
                    $("header").css("background-color", "#fff0"); // 스크롤이 최상단일 때
                } else {
                    $("header").css("background-color", "#fff"); // 스크롤이 내려갔을 때
                }
            });
    	
    	});
    </script>
</body>
</html>