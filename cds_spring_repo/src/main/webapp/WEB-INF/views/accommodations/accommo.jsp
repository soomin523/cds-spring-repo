<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>숙소 검색</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/accommo.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/Accommo.js"></script>
</head>

<body>
	<section>
		<div class="accoheader">
			<!-- 검색 박스 컨테이너 -->
			<div class="accoheaderbox">
				<form action="/search" method="get">
					<div class="input-group">
						<input type="text" name="destination" placeholder="목적지" /> <input
							type="text" name="dates" placeholder="일정 선택" /> <input
							type="text" name="guests" placeholder="숙박 인원" />
					</div>
					<div class="headerbutton">
						<button type="submit">숙소 검색</button>
					</div>
				</form>
			</div>
		</div>

		<hr class="hr" />
		<h2>여행지 별 숙소</h2>
		<h5>여행지 별 숙소 현황을 확인하세요.</h5>
		<br>
		<div class="accodesbox">
			<div>
				<div data-region="1"></div>
				<h3>서울</h3>
			</div>
			<div>
				<div data-region="4"></div>
				<h3>대구</h3>
			</div>
			<div>
				<div data-region="3"></div>
				<h3>대전</h3>
			</div>
			<div>
				<div data-region="6"></div>
				<h3>부산</h3>
			</div>
			<div>
				<div data-region="7"></div>
				<h3>울산</h3>
			</div>
			<div>
				<div data-region="39"></div>
				<h3>제주</h3>
			</div>
			<div>
				<div data-region="5"></div>
				<h3>광주</h3>
			</div>
			<div>
				<div data-region="2"></div>
				<h3>인천</h3>
			</div>
		</div>

		<hr class="hr" />
		<h2>인기 만점 숙소</h2>
		<h5>사람들의 관심이 많은 숙소을 확인하세요.</h5>
		<div class="scroll-container">
			<div class="accodesbox2">
				<button class="scroll-button left">◀</button>
				<div>
					<div></div><h3>타이틀 정보없음</h3><h4>조회수 정보없음</h4>
				</div>
				<div>
					<div></div><h3>타이틀 정보없음</h3><h4>조회수 정보없음</h4>
				</div>
				<div>
					<div></div><h3>타이틀 정보없음</h3><h4>조회수 정보없음</h4>
				</div>
				<div>
					<div></div><h3>타이틀 정보없음</h3><h4>조회수 정보없음</h4>
				</div>
				<div>
					<div></div><h3>타이틀 정보없음</h3><h4>조회수 정보없음</h4>
				</div>
				<div>
					<div></div><h3>타이틀 정보없음</h3><h4>조회수 정보없음</h4>
				</div>
				<div>
					<div></div><h3>타이틀 정보없음</h3><h4>조회수 정보없음</h4>
				</div>
				<div>
					<div></div><h3>타이틀 정보없음</h3><h4>조회수 정보없음</h4>
				</div>
				<button class="scroll-button right">▶</button>
			</div>
		</div>
	
	<hr>
	<div class="accofoot">
	<ul>
	<h2>• 떠나자 <br>&nbsp 숙소 안내</h2>
	<li>Lorem ipum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</li>
	<li>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</li>
	<li>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</li>
	<li>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</li>
	<li>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</li>
	</ul>
	
	
	</div>

	</section>
</body>

</html>

<script>
        document.querySelector('.scroll-button.left').addEventListener('click', () => {
            document.querySelector('.accodesbox2').scrollBy({
                left: -1000, // 왼쪽으로 100px 이동
                behavior: 'smooth'
            });
        });

        document.querySelector('.scroll-button.right').addEventListener('click', () => {
            document.querySelector('.accodesbox2').scrollBy({
                left: 1000, // 오른쪽으로 100px 이동
                behavior: 'smooth'
            });
        });
    </script>