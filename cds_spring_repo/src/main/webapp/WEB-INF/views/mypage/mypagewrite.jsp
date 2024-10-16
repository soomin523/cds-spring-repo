<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <html>

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>회원탈퇴창</title>
            <link rel="stylesheet" type="text/css"
                href="${pageContext.request.contextPath}/resources/css/mypagewrite.css">
        </head>

        <body>
            <div class="background">
                <div class="mywrite-title">
                    <h2>작성내역</h2>
                </div>
                <div class="mywrite-category">
                    <button>게시글</button>
                    <button>댓글</button>
                </div>
                <!-- 초기값 게시글 작성내역(카테고리별로 나뉨) -->
                <div class="mywrite-content-none">
                    <p>등록된 게시글이 없습니다</p>
                </div>
                <div class="mywrite-comment-none">
                    <p>등록된 댓글이 없습니다.</p>
                </div>
                <!-- display none값으로 해논 댓글 작성내역 -->
                <div class="mywrite-content-exist">
                    <p>커뮤게시글 제목</p>
                    <p>날짜</p>
                </div>
                <div class="mywrite-comment-exist">
                    <p>댓글 내용</p>
                    <p>날짜</p>
                </div>
            </div>
        </body>

        </html>