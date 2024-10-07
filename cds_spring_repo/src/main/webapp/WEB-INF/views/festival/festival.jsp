<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>축제행사</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" 
integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" 
crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/festival.css">
<script src="../resources/js/jquery-3.7.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/festival.js"></script>
</head>
<body>
    <div id="section">
        <div id="festivalMain">
            <div class="search">
                <input type="text" id="searchText" placeholder="검색어를 입력하세요(축제/행사 명)">
                <span>|</span>
                <button type="submit">
                    <i class="fa-solid fa-magnifying-glass"></i>
                </button>
            </div>
            <div class="select">
                <div class="selectDate">
                	<i class="fa-regular fa-calendar calendar-icon"></i>
	                <input type="date" id="dateInput" data-placeholder="날짜">
                </div>
                <div class="selectArea">
                	<i class="fa-solid fa-location-dot"></i>
	                <select class="areaSelect">
	                    <option value="">지역</option>
	                    <option value="1">서울</option>
	                    <option value="2">인천</option>
	                    <option value="3">대전</option>
	                    <option value="4">대구</option>
	                    <option value="5">광주</option>
	                    <option value="6">부산</option>
	                    <option value="7">울산</option>
	                    <option value="8">세종시</option>
	                    <option value="31">경기도</option>
	                    <option value="32">강원특별자치도</option>
	                    <option value="33">충청북도</option>
	                    <option value="34">충청남도</option>
	                    <option value="35">경상북도</option>
	                    <option value="36">경상남도</option>
	                    <option value="37">전북특별자치도</option>
	                    <option value="38">전라남도</option>
	                    <option value="39">제주도</option>
	                </select>
                </div>
                <div class="selectCategory">
                	<i class="fa-regular fa-folder"></i>
	                <select class="categorySelect">
	                    <option value="">카테고리</option>
	                    <option value="0">나들이</option>
	                    <option value="0">가을꽃</option>
	                    <option value="0">야경</option>
	                    <option value="0">문화예술</option>
	                    <option value="0">미디어아트</option>
	                    <option value="0">빛</option>
	                </select>
                </div>
                <button class="selectRefresh">
                	<i class="fa-solid fa-arrows-rotate"></i>
                </button>
            </div>
            <div class="duration">
                <button class="ing"># 진행중</button>
                <button class="soon"># 진행예정</button>
            </div>
<c:choose>
   	<c:when test="${ not empty festivalList }">
            <div class="festivallist">
   		<c:forEach var="item" items="${ festivalList }">
                <div class="festivalitem">
                    <div class="itemImg" style="background-image: url(${ item.getF_firstimage() });">
                        <div class="hiddenItem">
                            <div>${ item.getF_areacode() } ${ item.getF_sigungucode() }</div>
                            <p>${ item.getF_eventstartdate() } ~ ${ item.getF_eventenddate() }</p>
                            <button>바로가기</button>
                        </div>
                    </div>
                    <div>${ item.getF_title() }</div>
                </div>
   		</c:forEach>
            </div>
   	</c:when>
   	<c:otherwise>
            <div class="festivalNonelist">
                <p>검색 결과가 없습니다.</p>
                <p>다시 검색해 주세요.</p>
            </div>
   	</c:otherwise>
</c:choose>
<c:if test="${ festivalList.size() lt 5 }">
            <div class="recommend">
                <div class="recTitle">이런 축제는 어때요?</div>
                <div class="recList">
                    <div class="recItemImg">
                        <div>
                            <div class="recItemText">
                                <div>A 축제</div>
                                <div>2024.09.16~2024.09.30</div>
                                <div>충청남도 천안시</div>
                            </div>
                            <button class="recItemText">
                                <i class="fa-solid fa-arrow-right"></i>
                            </button>
                        </div>
                    </div>
                    <div class="recItemImg">
                        <div>
                            <div class="recItemText">
                                <div>B 축제</div>
                                <div>2024.09.16~2024.09.30</div>
                                <div>충청남도 아산시</div>
                            </div>
                            <button class="recItemText">
                                <i class="fa-solid fa-arrow-right"></i>
                            </button>
                        </div>
                    </div>
                    <div class="recItemImg">
                        <div>
                            <div class="recItemText">
                                <div>C 축제</div>
                                <div>2024.09.16~2024.09.30</div>
                                <div>충청남도 보령시</div>
                            </div>
                            <button class="recItemText">
                                <i class="fa-solid fa-arrow-right"></i>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
</c:if>
        </div>
        <div id="modalOverlay"></div>
        <div id="festivalModal">
            <div class="festivalName">
                <h1>축제명</h1>
                <button class="closeModal">
                    <i class="fa-solid fa-xmark"></i>
                </button>
            </div>
            <div class="modalDate">
                <p>행사시작일</p>
                <p>~</p>
                <p>행사종료일</p>
            </div>
            <div class="modalStatus">
                <div class="modalIng">진행중</div>
                <div class="modalIng">진행예정</div>
                <div class="modalIng">진행종료</div>
            </div>
            <div class="modalMid">
                <button class="prevBtn">
                    <i class="fa-solid fa-angle-left"></i>
                </button>
                <div class="modalImgContainer">
                    <div class="modalImgs">
                        <div class="modalImg">1</div>
                        <div class="modalImg">2</div>
                        <div class="modalImg">3</div>
                        <div class="modalImg">4</div>
                        <div class="modalImg">5</div>
                        <div class="modalImg">6</div>
                        <div class="modalImg">7</div>
                        <div class="modalImg">8</div>
                    </div>
                </div>
                <button class="nextBtn">
                    <i class="fa-solid fa-angle-right"></i>
                </button>
            </div>
            <div class="modalContentBox">
                <p class="modalContent">Lorem Ipsum is simply dummy text of the printing and typesetting industry. 
                    Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, 
                    when an unknown printer took a galley of type and scrambled it to make a type specimen book. 
                    It has survived not only five centuries, but also the leap into electronic typesetting, 
                    remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets 
                    containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker 
                    including versions of Lorem Ipsum.
                </p>
                <button><i class="fa-solid fa-plus"></i> 더보기</button>
                <div class="modalContentPlus">
                    <div class="plusLeft">
                        <p>예매처 : 예매처</p>
                        <p>이용요금 : 이용요금</p>
                        <p>할인정보 : 할인정보</p>
                    </div>
                    <div class="plusRight">
                        <p>행사홈페이지 : 링크</p>
                        <p>행사장위치안내 : 지역</p>
                        <p>행사장소 : 장소</p>
                        <p>행사프로그램 : 내용</p>
                    </div>
                </div>
            </div>
            <hr />
            <div class="modalMap">
                <h3>길찾기</h3>
                <div class="map"></div>
                <div class="mapContent">
                    <p>주소</p>
                    <p>주최자정보</p>
                    <p>전화번호</p>
                </div>
            </div>
        </div>
    </div>
    
    <!-- <script>
        $(function(){
            
        	

        });
          
    </script> -->
</body>
</html>