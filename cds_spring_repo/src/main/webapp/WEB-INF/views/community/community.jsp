<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>커뮤니티 페이지</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/member/community.css">
    <script src="../resources/js/jquery-3.7.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/community.js"></script>
</head>  
<body>
<%@ include file="../main/header2.jsp"%>
<div class="section">
<div class="commu-container">
        <!-- 메인 콘텐츠 영역 -->
        <div class="commu-content">
            <div class="commu-header">
                <h2 style="display: inline-block; margin-right: 20px;">떠나자 커뮤니티 ✍️</h2>
<c:if test="${ not empty member }">
                <a href="${pageContext.request.contextPath}/community/upload.do">
                    <button class="commu-post-button" style="display: inline-block;">게시물 올리기</button>
                </a>
</c:if>
                <div class="commu-sort-options" style="margin-left: auto;" data-location="${ area ? area : 'all' }">
                    <button class="commu-sort-button" id="commu-sortLatest" value="latest">최신순</button>
                    <button class="commu-sort-button" id="commu-sortRating" value="rating">평점순</button>
                </div>
            </div>
            
            <!-- 게시물 목록 -->
            <div id="commu-postList" class="commu-post-grid">
                <!-- 게시물 12개가 그리드로 나올 영역 -->
                    <c:forEach var="community" items="${communityList}">
                        <div class="post-item" data-id="${ community.c_idx }">
						<div class="post-image" style="background-image: url('${pageContext.request.contextPath}/resources/uploads/${ community.attachedList[0].save_filename }');">
	                            <p class= "postlocation">작성자 : ${community.memberId}</p>
                            	<p class= "postlocation">${community.location}</p>
                            </div>
                            <p>제목: ${community.title}</p>
                            <div class="post-rating">
                                <span>⭐ ${ community.rating }</span>
                            </div>
                            
                            <div class="post-actions">
                                <span>
                                	<c:if test="${ not empty community.tag }">
	                                	<c:forEach var="tag" items="${fn:split(community.tag, ',')}">
						                    #${tag.trim()} 
						                </c:forEach>
					                </c:if>
                                </span>
                            </div>
                            <p>
                            	작성일: <fmt:formatDate value="${community.created_at}" type="date" pattern="yyyy-MM-dd" />
                            </p>
                            
                        </div>
                    </c:forEach>
            </div>

            <!-- 페이지네이션 -->
            <div class="commu-pagination" id="commu-pagination"></div>
        </div>

        <!-- 사이드바 (지역별 카테고리) -->
        <div class="commu-sidebar">
            <!-- 지역 검색창 -->
            <div class="commu-search-box">
                <p><img src="${pageContext.request.contextPath}/resources/img/돋보기.png" /></p>
                <input type="text" placeholder="제목, ID 검색 가능" id="commu-regionSearch">
            </div>
            <h4 id="commu-selectedLocation" style="margin-top: 10px;"></h4>

            <h3>지역별 게시물 보기</h3>
            <div class="commu-button-container">
                <!-- 지역 버튼 생성 -->
                <button class="commu-button" value="all">모든 지역</button>
                <button class="commu-button" value="서울">서울</button>
                <button class="commu-button" value="경기">경기</button>
                <button class="commu-button" value="인천">인천</button>
                <button class="commu-button" value="대전">대전</button>
                <button class="commu-button" value="강원">강원</button>
                <button class="commu-button" value="광주">광주</button>
                <button class="commu-button" value="울산">울산</button>
                <button class="commu-button" value="대구">대구</button>
                <button class="commu-button" value="부산">부산</button>
                <button class="commu-button" value="충북">충북</button>
                <button class="commu-button" value="충남">충남</button>
                <button class="commu-button" value="전북">전북</button>
                <button class="commu-button" value="전남">전남</button>
                <button class="commu-button" value="경북">경북</button>
                <button class="commu-button" value="경남">경남</button>
                <button class="commu-button" value="제주">제주</button>
            </div>
            <h4 id="commu-selectedLocation" style="margin-top: 10px;"></h4>
            
            <!-- 이미지 추가 -->
            <p style="position: relative;">
                <img src="${pageContext.request.contextPath}/resources/img/후기.png" class="log-logo2" style="width: 100%; position: absolute;">
<c:if test="${ not empty member }">
                <a href="${pageContext.request.contextPath}/community/upload.do">
                    <button class="commu-post-button" style="position: relative; margin: 96% 30% 0px; font-size:12px;">게시물 올리기</button>
                </a>
</c:if>
            </p>
        </div>
    </div>

    <!-- 모달 창 -->
    <div id="commu-modal" class="commu-modal">
        <div class="commu-modal-content">
            <div class="commu-modal-header">
                <div class="commu-profile">
                    <span id="commu-modalUserId"></span>
                </div>
                <div class="commu-location-icon">
                    <p><img src="${pageContext.request.contextPath}/resources/img/위치.jpg" /></p>
                    <span id="commu-modalLocation"></span>
                </div>
                <span class="commu-modal-close">&times;</span>
            </div>
            <div class="commu-modal-slider">
                <button class="prev-slide">&#10094;</button>
                <div class="commu-modalImage">
                	<div class="commu-imageList"></div>
                </div>
                <button class="next-slide">&#10095;</button>
            </div>
    
            <!-- 좋아요 수, 댓글 수, 작성일을 사진 아래로 이동 -->
            <div class="commu-modal-meta"> 
                <div class="commu-likes-comments">
                    <div class="commu-icon commu-likeBtn">👍 <span id="commu-modalLikes"></span></div>
                    <div class="commu-icon">💬 <span id="commu-modalCommentsCount"></span></div>
                </div>
                <span id="commu-modalMeta" class="commu-post-date"></span>
            </div>

            <h3 id="commu-modalTitle" class="commu-modal-title"></h3>
            <p class="commu-modal-description" id="commu-modalDescription"></p>

            <div id="commu-modalComments" class="commu-modal-comments"></div>
            
            <!-- 댓글 작성 영역 추가 -->
            <div class="commu-comment-box" data-name='${member.member_id}'>
                <textarea id="commu-commentText" placeholder="댓글을 작성하세요"></textarea>
                <button id="commentSubmitBtn">올리기</button>
            </div>
            <!-- 게시글 수정삭제 -->
            <div class="community-delete">
                <button id="commu-Edit-Btn">게시글 수정</button>
                <span> | </span>
                <button id="commu-delete-Btn">삭제</button>      
            </div>     
        </div>
    </div>  
 </div>   
<%@ include file="../main/footer.jsp"%>

</body>
</html>
