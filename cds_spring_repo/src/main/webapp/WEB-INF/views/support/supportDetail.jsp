<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객센터</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" 
integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" 
crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/support.css">
<script src="../resources/js/jquery-3.7.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/support.js"></script>
</head>
<body>
	<%@ include file="../main/header2.jsp"%>
    <div id="section">
        <div id="supportDetail">
            <aside>
                <h2>고객센터</h2>
                <hr>
                <div class="select">
                    <button value="notice">공지사항</button>
                    <hr>
                    <button value="guide">이용안내</button>
                    <hr>
                    <button value="question">자주묻는질문</button>
                    <hr>
                    <button value="oneByone">1:1문의</button>
                    <hr>
                </div>
            </aside>
            <section>
                <div class="sectionTop">
                    <h1>
						<c:choose>
							<c:when test="${ select eq 'guide' }">이용안내</c:when>
							<c:when test="${ select eq 'question' }">자주묻는질문</c:when>
							<c:otherwise>공지사항</c:otherwise>
						</c:choose>          
                    </h1>
                </div>
<c:if test="${ member.membership_level == 3 }">
                <div class="newContent">
                	<button>새글쓰기</button>
                </div>
</c:if>
                <hr>
                <div class="contentBox">
<c:if test="${ not empty support }">
	<c:forEach var="item" items="${ support }">
	                <div class="content">
	                    <div class="contentTitle">
	                        <div>${ item.s_title }</div>
	                        <i class="fa-solid fa-angle-up"></i>
	                        <i class="fa-solid fa-angle-down"></i>
	                    </div>
	                    <div class="contentText" id="contentText-${ item.s_idx }">
	                    	<p>${ item.s_content }</p>
	                    	<div>
			<fmt:formatDate value="${ item.update_date }" pattern="yyyy-MM-dd HH:mm:ss" />
		<c:if test="${ member.membership_level == 3 }">
								<div>
			                    	<button class="update" data-sidx="${ item.s_idx }" data-category="${ item.s_category }">
			                    		수정하기</button>
			                    	<span>|</span>
			                    	<button class="delete" data-sidx="${ item.s_idx }" data-category="${ item.s_category }">
			                    		삭제하기</button>
								</div>
		</c:if>
	                    	</div>
	                    </div>
	                </div>
	                <hr>
	</c:forEach>
</c:if>
				</div>
				<div id="modalOverlay"></div>
				<div class="newContentPage">
				    <h2>새 글 작성</h2>
				    <h2 style="color: #fff0;">띄우기</h2>
	               <form name="newContent" action="insertSupport.do" method="post">
	                   <input type="text" name="s_writer" value="${ member.member_id }" readonly><br>
	                   <select name="s_category">
	                       <option value="notice">공지사항</option>
	                       <option value="guide">이용안내</option>
	                       <option value="question">자주묻는질문</option>
	                   </select>
	                   <input type="text" name="s_title" placeholder="제목을 입력하세요"><br>
	                   <textarea name="s_content" cols="50" rows="25" placeholder="내용을 입력하세요"></textarea>
	                   <div class="button-group">
		                <input type="submit" value="등록">
		                <input type="reset" value="리셋">
		                <input type="button" value="취소">
		            </div>
	               </form>
	           </div>
	           <div class="updateContentPage">
	           		<h2>게시글 수정</h2>
	           		<h2 style="color: #fff0;">띄우기</h2>
	               <form name="updateContent" action="updateSupport.do" method="post">
	               	   <input type="number" name="s_idx" style="display: none;">
	                   <input type="text" name="s_writer" readonly><br>
	                   <select name="s_category">
	                       <option value="notice">공지사항</option>
	                       <option value="guide">이용안내</option>
	                       <option value="question">자주묻는질문</option>
	                   </select>
	                   <input type="text" name="s_title"><br>
	                   <textarea name="s_content" cols="50" rows="25"></textarea>
	                   <div>
		                <input type="submit" value="수정">
		                <input type="button" value="취소">
		            </div>
	               </form>
	           </div>
            </section>
        </div>
    </div>
    <%@ include file="../main/footer.jsp"%>
</body>
</html>