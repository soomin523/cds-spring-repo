<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>여행지 둘러보기</title>
    <!-- CSS 연결 -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/destination_main.css">
    <!-- jQuery 및 JS 파일 연결 -->
    <script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9dc9962fd8d9c313d5ca5a57212228ab"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/destination_main.js"></script>
</head>

<body>
	<%@ include file="../main/header2.jsp"%>
    <section>
        <div id="des1">
            <div class="desheader">
                <div class="desheader_1">
                    <div class="desheader_1a">
                        <h2>국내 관광지 정보</h2>
                        <p>얼마나 알고 있니?</p>
                    </div>
                    <div class="desheader_2">
                        <!-- 왼쪽 스크롤 버튼 -->
                        <span class="nav-left">&lt;</span>

                        <!-- 원들이 들어있는 컨테이너 -->
                        <div class="region-container">
                            <div class="region-circles">
                                <div class="circle-item" data-region="1" data-rname="서울">
                                    <div class="desheader_circle"
                                        style="background-image: url(${pageContext.request.contextPath}/resources/img/서울.PNG);"></div>
                                    <p>서울</p>
                                </div>
                                <div class="circle-item" data-region="2" data-rname="인천">
                                    <div class="desheader_circle"
                                        style="background-image: url(${pageContext.request.contextPath}/resources/img/인천.PNG);"></div>
                                    <p>인천</p>
                                </div>
                                <div class="circle-item" data-region="3" data-rname="대전">
                                    <div class="desheader_circle"
                                        style="background-image: url(${pageContext.request.contextPath}/resources/img/대전.PNG);"></div>
                                    <p>대전</p>
                                </div>
                                <div class="circle-item" data-region="4" data-rname="대구">
                                    <div class="desheader_circle"
                                        style="background-image: url(${pageContext.request.contextPath}/resources/img/대구.PNG);"></div>
                                    <p>대구</p>
                                </div>
                                <div class="circle-item" data-region="5" data-rname="광주">
                                    <div class="desheader_circle"
                                        style="background-image: url(${pageContext.request.contextPath}/resources/img/광주.png);"></div>
                                    <p>광주</p>
                                </div>
                                <div class="circle-item" data-region="6" data-rname="부산">
                                    <div class="desheader_circle"
                                        style="background-image: url(${pageContext.request.contextPath}/resources/img/부산.PNG);"></div>
                                    <p>부산</p>
                                </div>
                                <div class="circle-item" data-region="7" data-rname="울산">
                                    <div class="desheader_circle"
                                        style="background-image: url(${pageContext.request.contextPath}/resources/img/울산.PNG);"></div>
                                    <p>울산</p>
                                </div>
                                <div class="circle-item" data-region="31" data-rname="경기">
                                    <div class="desheader_circle"
                                        style="background-image: url(${pageContext.request.contextPath}/resources/img/경기.PNG);"></div>
                                    <p>경기</p>
                                </div>
                                <div class="circle-item" data-region="32" data-rname="강원">
                                    <div class="desheader_circle"
                                        style="background-image: url(${pageContext.request.contextPath}/resources/img/강원.PNG);"></div>
                                    <p>강원</p>
                                </div>
                                <div class="circle-item" data-region="33" data-rname="충북">
                                    <div class="desheader_circle"
                                        style="background-image: url(${pageContext.request.contextPath}/resources/img/충북.PNG);"></div>
                                    <p>충북</p>
                                </div>
                                <div class="circle-item" data-region="34" data-rname="충남">
                                    <div class="desheader_circle"
                                        style="background-image: url(${pageContext.request.contextPath}/resources/img/충남.PNG);"></div>
                                    <p>충남</p>
                                </div>
                                <div class="circle-item" data-region="35" data-rname="경북">
                                    <div class="desheader_circle"
                                        style="background-image: url(${pageContext.request.contextPath}/resources/img/경북.PNG);"></div>
                                    <p>경북</p>
                                </div>
                                <div class="circle-item" data-region="36" data-rname="경남">
                                    <div class="desheader_circle"
                                        style="background-image: url(${pageContext.request.contextPath}/resources/img/경남.PNG);"></div>
                                    <p>경남</p>
                                </div>
                                <div class="circle-item" data-region="37" data-rname="전북">
                                    <div class="desheader_circle"
                                        style="background-image: url(${pageContext.request.contextPath}/resources/img/전북.PNG);"></div>
                                    <p>전북</p>
                                </div>
                                <div class="circle-item" data-region="38" data-rname="전남">
                                    <div class="desheader_circle"
                                        style="background-image: url(${pageContext.request.contextPath}/resources/img/전남.PNG);"></div>
                                    <p>전남</p>
                                </div>
                                <div class="circle-item" data-region="39" data-rname="제주">
                                    <div class="desheader_circle"
                                        style="background-image: url(${pageContext.request.contextPath}/resources/img/제주도.PNG);"></div>
                                    <p>제주</p>
                                </div>
                                <div class="circle-item" data-region="8" data-rname="세종">
                                    <div class="desheader_circle"
                                        style="background-image: url(${pageContext.request.contextPath}/resources/img/세종.PNG);"></div>
                                    <p>세종</p>
                                </div>
                            </div>
                        </div>

                        <!-- 오른쪽 스크롤 버튼 -->
                        <span class="nav-right">&gt;</span>
                    </div>
                </div>
            </div>
        </div>

        <!-- 선택 옵션 및 검색 -->
        <div class="desselect">
            <h3>서울</h3>
            <select name="sigunguselect" id="sigunguselect" class="select-container">
                <option>종로구</option>
                <option>용산구</option>
                <option>영등포구</option>
                <option>동작구</option>
                <option>양천구</option>
                <option>중랑구</option>
                <option>서초구</option>
                <option>동대문구</option>
                <option>도봉구</option>
                <option>광진구</option>
                <option>중구</option>
                <option>강북구</option>
                <option>송파구</option>
                <option>서대문구</option>
                <option>강동구</option>
                <option>마포구</option>
                <option>강남구</option>
                <option>강서구</option>
                <option>성북구</option>
                <option>관악구</option>
                <option>은평구</option>
                <option>성동구</option>
                <option>노원구</option>
                <option>구로구</option>
            </select>
            <input type="text" id="search-box" placeholder="검색어를 입력해주세요">
        </div>

        <!-- 여행지 리스트 -->
        <div class="desimg1"></div>

        <!-- 상세보기 모달창 -->
        <div class="modal-background">
            <div id="myModal"></div>
        </div>

        <div>
        <img class="desslogan" src="${pageContext.request.contextPath}/resources/img/desbanner.png">
        </div>
    </section>
    <%@ include file="../main/footer.jsp"%>
</body>

</html>
