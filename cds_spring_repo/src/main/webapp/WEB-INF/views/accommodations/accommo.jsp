<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>숙소 검색</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/accommo.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/Accommo.js"></script>
</head>

<body>
    <%@ include file="../main/header2.jsp"%>
    <section>
        <div class="accoheader">
            <!-- 검색 박스 컨테이너 -->
            <div class="accoheaderbox fancy-search-box">
                <form action="/search" method="get">
                    <div class="input-group">
                        <!-- 지역 선택 드롭다운 -->
                        <select id="region" name="region" class="custom-dropdown">
                            <option value="">지역 선택(필수)</option>
                            <option value="1">서울</option>
                            <option value="2">인천</option>
                            <option value="3">대전</option>
                            <option value="4">대구</option>
                            <option value="5">광주</option>
                            <option value="6">부산</option>
                            <option value="7">울산</option>
                            <option value="39">제주</option>
                        </select>

                        <!-- 숙소 카테고리 선택 드롭다운 -->
                        <select id="acccate" name="acccate" class="custom-dropdown">
                            <option value="">숙소 카테고리 선택</option>
                            <option value="B02010100">호텔</option>
                            <option value="B02010900">모텔</option>
                            <option value="B02010600">유스호스텔</option>
                            <option value="A02020200">리조트</option>
                            <option value="B02010500">콘도</option>
                            <option value="B02010700">팬션</option>
                            <option value="B02011600">한옥</option>
                            <option value="B02011100">게스트하우스</option>
                        </select>
                    </div>
                    <div class="headerbutton">
                        <button type="submit" class="custom-button">숙소 검색</button>
                    </div>
                </form>
            </div>
        </div>

        <hr class="hr" />
        <div class="exbox">
        <h2>여행지 별 숙소</h2>
        <h5>여행지 별 숙소 현황을 확인하세요.</h5>
        </div>
        <br>
        <div class="accodesbox">
            <div class="region-btn" data-region="1">
                <div data-region="1"></div>
                <h3>서울</h3>
            </div>
            <div class="region-btn" data-region="4">
                <div data-region="4"></div>
                <h3>대구</h3>
            </div>
            <div class="region-btn" data-region="3">
                <div data-region="3"></div>
                <h3>대전</h3>
            </div>
            <div class="region-btn" data-region="6">
                <div data-region="6"></div>
                <h3>부산</h3>
            </div>
            <div class="region-btn" data-region="7">
                <div data-region="7"></div>
                <h3>울산</h3>
            </div>
            <div class="region-btn" data-region="39">
                <div data-region="39"></div>
                <h3>제주</h3>
            </div>
            <div class="region-btn" data-region="5">
                <div data-region="5"></div>
                <h3>광주</h3>
            </div>
            <div class="region-btn" data-region="2">
                <div data-region="2"></div>
                <h3>인천</h3>
            </div>
        </div>

        <hr class="hr" />
        <div class="exbox">
        <h2>인기 만점 숙소</h2>
        <h5>사람들의 관심이 많은 숙소를 확인하세요.</h5>
        </div>
        <div class="scroll-container">
            <button class="scroll-button left">◀</button>
            <div class="accodesbox2">
                <!-- 인기 숙소가 여기 추가됩니다. -->
            </div>
            <button class="scroll-button right">▶</button>
        </div>
    </section>
    <%@ include file="../main/footer.jsp"%>
</body>

</html>
