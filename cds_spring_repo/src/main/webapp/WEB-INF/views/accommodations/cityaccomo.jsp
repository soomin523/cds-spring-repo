<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>


<head>
<meta charset="UTF-8">
<title>지역별 숙소</title>
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9dc9962fd8d9c313d5ca5a57212228ab"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/accommo.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/cityaccomo.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/cityaccomo.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/accmodal.js"></script></head>


<body>
	<section>
	<%@ include file="../main/header2.jsp"%>
		<div class="fullcity">
			<div class="cityaccoheader">
				<div class="h">
					<button class="accomobefore">◀</button>
				</div>
				<div class="h">
					<h2>지역 별 숙소</h2>
				</div>
				<div class="h">
					<input type="text" id="searchInput" placeholder="숙소 제목이나 주소 검색" />
				</div>
			</div>

			<hr>

			<div class="accodesbox">
				<div>
					<div data-region="1" class="region-btn"></div>
					<h3>서울</h3>
				</div>
				<div>
					<div data-region="4" class="region-btn"></div>
					<h3>대구</h3>
				</div>
				<div>
					<div data-region="3" class="region-btn"></div>
					<h3>대전</h3>
				</div>
				<div>
					<div data-region="6" class="region-btn"></div>
					<h3>부산</h3>
				</div>
				<div>
					<div data-region="7" class="region-btn"></div>
					<h3>울산</h3>
				</div>
				<div>
					<div data-region="39" class="region-btn"></div>
					<h3>제주</h3>
				</div>
				<div>
					<div data-region="5" class="region-btn"></div>
					<h3>광주</h3>
				</div>
				<div>
					<div data-region="2" class="region-btn"></div>
					<h3>인천</h3>
				</div>
			</div>

			<hr id="atypeline">

			<div class="atype">
				<div data-cat3="all" class="atype-btn">전체</div>
				<div data-cat3="B02010100" class="atype-btn">호텔</div>
				<div data-cat3="B02010900" class="atype-btn">모텔</div>
				<div data-cat3="B02010600" class="atype-btn">유스호스텔</div>
				<div data-cat3="A02020200" class="atype-btn">리조트</div>
				<div data-cat3="B02010500" class="atype-btn">콘도</div>
				<div data-cat3="B02010700" class="atype-btn">팬션</div>
				<div data-cat3="B02011600" class="atype-btn">한옥</div>
				<div data-cat3="B02011100" class="atype-btn">게스트하우스</div>
			</div>

			<hr>


			<div class="citymiddle" id="citymiddle"></div>

		</div>
		<button id="scrollToTopBtn">▲</button>

		<!-- 모달 -->

			<div class="accmodal">
				<div class="mxbox"><p class="x-mark"></p></div>
				<div class="acmimg"><img id="modalImage" src="" alt="숙소 이미지"></div>
				<h2 id="modalTitle">숙소 이름</h2>
				<p id="modalAddress">숙소 주소</p>
				<div id="modaltel"><h4>숙소전화번호</h4></div>
				<div id="modaloverview"><h3>숙소 소개</h3></div>
				<div id="accmap">맵정보 표시</div>
				<div id="modalRoomInfo">방 정보 표시</div>
			</div>
			
			<div class="image-modal" style="display:none;">
    <p class="close">&times;</p>
    <img id="largeImage" src="" alt="확대된 이미지">
</div>

	<%@ include file="../main/footer.jsp"%>
	</section>
</body>

</html>
