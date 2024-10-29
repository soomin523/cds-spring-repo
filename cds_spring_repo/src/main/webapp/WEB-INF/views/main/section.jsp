<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>떠나자</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" 
integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" 
crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main.css">
<script src="../resources/js/jquery-3.7.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/section.js"></script>
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
							<div class="imgItem" data-areacode="1">
								<h4>서울특별시</h4>
								<div style="background-image: url('${pageContext.request.contextPath}/resources/img/main_서울.jpg')"></div>
								<h3>서울특별시</h3>
							</div>
							<div class="imgItem" data-areacode="2">
								<h4>인천광역시</h4>
								<div style="background-image: url('${pageContext.request.contextPath}/resources/img/main_인천.jpg')"></div>
								<h3>인천광역시</h3>
							</div>
							<div class="imgItem" data-areacode="3">
								<h4>대전광역시</h4>
								<div style="background-image: url('${pageContext.request.contextPath}/resources/img/main_대전.JPG')"></div>
								<h3>대전광역시</h3>
							</div>
							<div class="imgItem" data-areacode="4">
								<h4>대구광역시</h4>
								<div style="background-image: url('${pageContext.request.contextPath}/resources/img/main_대구.jpg')"></div>
								<h3>대구광역시</h3>
							</div>
							<div class="imgItem" data-areacode="5">
								<h4>광주광역시</h4>
								<div style="background-image: url('${pageContext.request.contextPath}/resources/img/main_광주.jpg')"></div>
								<h3>광주광역시</h3>
							</div>
							<div class="imgItem" data-areacode="6">
								<h4>부산광역시</h4>
								<div style="background-image: url('${pageContext.request.contextPath}/resources/img/main_부산.jpg')"></div>
								<h3>부산광역시</h3>
							</div>
							<div class="imgItem" data-areacode="7">
								<h4>울산광역시</h4>
								<div style="background-image: url('${pageContext.request.contextPath}/resources/img/main_울산.jpg')"></div>
								<h3>울산광역시</h3>
							</div>
							<div class="imgItem" data-areacode="8">
								<h4>세종특별자치시</h4>
								<div style="background-image: url('${pageContext.request.contextPath}/resources/img/main_세종.jpg')"></div>
								<h3>세종특별자치시</h3>
							</div>
							<div class="imgItem" data-areacode="31">
								<h4>경기도</h4>
								<div style="background-image: url('${pageContext.request.contextPath}/resources/img/main_경기도.jpg')"></div>
								<h3>경기도</h3>
							</div>
							<div class="imgItem" data-areacode="32">
								<h4>강원특별자치도</h4>
								<div style="background-image: url('${pageContext.request.contextPath}/resources/img/main_강원도.jpg')"></div>
								<h3>강원특별자치도</h3>
							</div>
							<div class="imgItem" data-areacode="33">
								<h4>충청북도</h4>
								<div style="background-image: url('${pageContext.request.contextPath}/resources/img/main_충청북도.JPG')"></div>
								<h3>충청북도</h3>
							</div>
							<div class="imgItem" data-areacode="34">
								<h4>충청남도</h4>
								<div style="background-image: url('${pageContext.request.contextPath}/resources/img/main_충청남도.jpg')"></div>
								<h3>충청남도</h3>
							</div>
							<div class="imgItem" data-areacode="35">
								<h4>경상북도</h4>
								<div style="background-image: url('${pageContext.request.contextPath}/resources/img/main_경상북도.jpg')"></div>
								<h3>경상북도</h3>
							</div>
							<div class="imgItem" data-areacode="36">
								<h4>경상남도</h4>
								<div style="background-image: url('${pageContext.request.contextPath}/resources/img/main_경상남도.jpg')"></div>
								<h3>경상남도</h3>
							</div>
							<div class="imgItem" data-areacode="37">
								<h4>전북특별자치도</h4>
								<div style="background-image: url('${pageContext.request.contextPath}/resources/img/main_전라북도.jpg')"></div>
								<h3>전북특별자치도</h3>
							</div>
							<div class="imgItem" data-areacode="38">
								<h4>전라남도</h4>
								<div style="background-image: url('${pageContext.request.contextPath}/resources/img/main_전라남도.jpg')"></div>
								<h3>전라남도</h3>
							</div>
							<div class="imgItem" data-areacode="39">
								<h4>제주도</h4>
								<div style="background-image: url('${pageContext.request.contextPath}/resources/img/main_제주도.jpg')"></div>
								<h3>제주도</h3>
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
					<h1>대한민국 추천 여행</h1>
					<p>'관광지 추천', '축제/행사', '전시/공연'<br>
					즐거움을 위한 다양한 여행지를 소개합니다 :)</p>
				</div>
				<div class="cateSelect">
					<div class="selectBox">
						<button value="destination">관광지추천</button>
						<button value="festival">축제/행사</button>
						<button value="product">전시/공연</button>
					</div>
					<div class="selectimgBox destinationImg">
<c:forEach var="i" begin="0" end="2" varStatus="status">
						<div style="background-image: url('${ destinationList[i].d_firstimage }');" 
							data-contentid=${ destinationList[i].d_contentid } 
							data-areacode=${ destinationList[i].d_areacode }>
							<p>${ destinationList[i].d_title }</p>
						</div>
</c:forEach>
					</div>
					<div class="selectimgBox festivalImg">
<c:forEach var="i" begin="0" end="2" varStatus="status">
						<div style="background-image: url('${ festivalList[i].f_firstimage }');"
							data-contentid=${ festivalList[i].f_contentid }>
							<p>${ festivalList[i].f_title }</p>
						</div>
</c:forEach>
					</div>
					<div class="selectimgBox productImg">
<c:forEach var="i" begin="0" end="2" varStatus="status">
						<div style="background-image: url('${ productList[i].firstimage }');"
							data-contentid=${ productList[i].contentid }>
							<p>${ productList[i].title }</p>
						</div>
</c:forEach>
					</div>
				</div>
			</div>
			<div class="mainReview">
				<h2 class="reviewTitle">믿고보는 고객님의 찐리뷰✍</h2>
				<div class="reviewBox">
					<button id="prevBtn">&lt;</button>
					<div class="reviewContainer">
						<div class="reviewList">
	<c:forEach var="i" begin="0" end="${ communityList.size() > 6 ? 6 : communityList.size()-1 }" varStatus="status">					
							<div class="reviewItem" data-c_idx="${ communityList[i].c_idx }">
								<div class="commuImg" style="background-image: url('${pageContext.request.contextPath}/resources/uploads/${ communityList[i].attachedList[0].save_filename }');"></div>
								<div>⭐ ${ communityList[i].rating }</div>
								<div class="commuTitle">${ communityList[i].title }</div>
								<div><fmt:formatDate value="${communityList[i].created_at}" type="date" pattern="yyyy-MM-dd" /></div>
							</div>
	</c:forEach>
						</div>
					</div>
					<button id="nextBtn">&gt;</button>
				</div>
				<button>더보기 <i class="fa-solid fa-plus"></i></button>
			</div>
			<div class="mainCourse">
				<div class="courseIntro">
					<img src="${pageContext.request.contextPath}/resources/img/trablecokcok.png">
					<h2>여행콕콕</h2>
					<span>|</span>
					<div>추천 코스를 알려드려요.</div>
				</div>
				<div class="courseImg">
<c:forEach var="i" begin="0" end="3" varStatus="status">
					<div style="background-image: url('${ courseList[i].first_image }');"
						data-contentid=${ courseList[i].content_id }>
						<p>${ courseList[i].title }</p>
					</div>
</c:forEach>
				</div>
			</div>
			<div class="mainSupport">
				<img src="${pageContext.request.contextPath}/resources/img/support.png">
				<div class="supportBox">
					<div class="supportIntro">
						<h2>공지사항</h2>
						<div><button>더보기 <i class="fa-solid fa-plus"></i></button></div>
					</div>
					<div class="supportList">
<c:forEach var="i" begin="0" end="${ supportList.size() > 6 ? 6 : supportList.size()-1 }" varStatus="status">
						<div class="supportItem" data-category="${ supportList[i].s_category }" 
							data-s_idx="${ supportList[i].s_idx }">
							<div>${ supportList[i].s_category }</div>
							<span>|</span>
							<div>${ supportList[i].s_title }</div>
						</div>
	<c:if test="${ status.index < 6 }">
						<hr>
	</c:if>
</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>