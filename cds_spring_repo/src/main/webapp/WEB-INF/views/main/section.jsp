<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>떠나자</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" 
integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" 
crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main.css">
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/section.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


</head>
<body>
	<div id="section">
		<div id="mainSection">
			<div class="mainTop">
				<div class="topIntro">
					<h2>국내여행</h2>
					<h2>어디로 갈지 고민된다면</h2>
					<h2><span>떠나자</span> 에서</h2>
					<button>바로가기 <i class="fa-solid fa-arrow-right"></i></button>
				</div>
				<div class="imgSlider">
					<div class="imgContainer">
						<div class="imgList">
							<div class="imgItem">
								<h3>서울</h3>
								<img src="" alt="관광지이미지">
								<h4>서울</h4>
							</div>
							<div class="imgItem">
								<h3>인천</h3>
								<img src="" alt="관광지이미지">
								<h4>인천</h4>
							</div>
						</div>
					</div>
					<div class="imgButton">
						<button class="prevBtn">
							<i class="fa-solid fa-angle-left"></i>
						</button>
						<button class="nextBtn">
							<i class="fa-solid fa-angle-right"></i>
						</button>
					</div>
				</div>
			</div>
			<div class="mainCategory">
				<div class="cateIntro">
					<div>여행자 이용 서비스</div>
					<h1>대한민국 추천 여행지</h1>
					<p>'2024 여행지 추천', '이벤트' 등<br>
					즐거움을 위한 다양한 여행지를 소개합니다 :)</p>
				</div>
				<div class="cateSelect">
					<div class="selectBox">
						<button>여행지추천</button>
						<button>축제/행사</button>
						<button>전시/공연</button>
					</div>
					<div class="imgBox">
						<img src="" alt="테마별이미지" id="eventImage1">
						<img src="" alt="테마별이미지" id="eventImage2">
						<img src="" alt="테마별이미지" id="eventImage3">
					</div>
				</div>
			</div>
			<div class="mainReview">
				<h2 class="reviewTitle">믿고보는 고객님의 찐리뷰✍</h2>
				<div class="reviewContainer">
					<div class="reviewList">
						<div class="reviewItem">
							<img src="" alt="리뷰사진">
							<div>⭐️⭐️⭐️⭐️⭐️</div>
							<div>리뷰 제목</div>
							<div>작성일자</div>
						</div>
						<div class="reviewItem">
							<img src="" alt="리뷰사진">
							<div>⭐️⭐️⭐️⭐️⭐️</div>
							<div>리뷰 제목</div>
							<div>작성일자</div>
						</div>
					</div>
				</div>
				<button>더보기 <i class="fa-solid fa-plus"></i></button>
			</div>
			<div class="mainCourse">
				<div class="courseIntro">
					<img src="${pageContext.request.contextPath}/resources/img/trablecokcok.png">
					<h2>여행콕콕</h2>
					<span>|</span>
					<div>계절별 추천 여행지 및 코스를 알려드려요.</div>
				</div>
				<div class="courseImg">
					<img src="" alt="봄">
					<img src="" alt="여름">
					<img src="" alt="가을">
					<img src="" alt="겨울">
				</div>
			</div>
			<div class="mainSupport">
				<img src="${pageContext.request.contextPath}/resources/img/support.png">
				<div class="supportBox">
					<div class="supportIntro">
						<h2>공지사항</h2>
						<button>더보기 <i class="fa-solid fa-plus"></i></button>
					</div>
					<div class="supportList">
						<div class="supportItem">
							<div>카테고리</div>
							<span>|</span>
							<div>고객센터 글 제목</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>