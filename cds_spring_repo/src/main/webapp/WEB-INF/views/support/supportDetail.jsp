<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객센터</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" 
integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" 
crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" type="text/css" href="support.css">
<script src="../resources/js/jquery-3.7.1.min.js"></script>
</head>
<body>
    <div id="section">
        <div id="supportDetail">
            <aside>
                <h2>고객센터</h2>
                <hr>
                <div class="select">
                    <button onclick="goTopage('notice')">공지사항</button>
                    <hr>
                    <button onclick="goTopage('guide')">이용안내</button>
                    <hr>
                    <button onclick="goTopage('question')">자주묻는질문</button>
                    <hr>
                    <button onclick="goTopage('inquiry')">1:1문의</button>
                    <hr>
                </div>
            </aside>
            <section>
                <div class="sectionTop">
                    <h1>
						<c:choose>
							<c:when test="${ param.supportSelect eq 'notice' }">공지사항</c:when>
							<c:when test="${ param.supportSelect eq 'guide' }">이용안내</c:when>
							<c:otherwise>자주묻는질문</c:otherwise>
						</c:choose>          
                    </h1>
                </div>
                <hr>
                <div class="content">
                    <div class="contentTitle">
                        <div>게시글5</div>
                        <i class="fa-solid fa-angle-up"></i>
                        <i class="fa-solid fa-angle-down"></i>
                    </div>
                    <div id="contentText1" class="contentText">
                        게시글 내용<br>
                        What is Lorem Ipsum? Lorem Ipsum is simply dummy text of the printing and typesetting industry.
                        Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,
                        when an unknown printer took a galley of type and scrambled it to make a type specimen book.
                        It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. 
                        It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, 
                        and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
                    </div>
                </div>
                <hr>
                <div class="content">
                    <div class="contentTitle">
                        <div>게시글4</div>
                        <i class="fa-solid fa-angle-up"></i>
                        <i class="fa-solid fa-angle-down"></i>
                    </div>
                    <div id="contentText2" class="contentText">
                        게시글 내용<br>
                        What is Lorem Ipsum? Lorem Ipsum is simply dummy text of the printing and typesetting industry.
                        Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,
                        when an unknown printer took a galley of type and scrambled it to make a type specimen book.
                    </div>
                </div>
                <hr>
            </section>
        </div>
    </div>

    <script>
        //왼쪽 네비게이션 해당 페이지 이동
        const goTopage = (select) => {
            switch(select){
				case 'notice': window.location.href = `supportDetail.jsp?supportSelect=notice`; break;
				case 'guide': window.location.href = `supportDetail.jsp?supportSelect=guide`; break;
				case 'question': window.location.href = `supportDetail.jsp?supportSelect=question`; break;
                case 'inquiry': window.location.href = `supportDetail.jsp?supportSelect=inquiry`;
			}
        };

        $(function(){

            //고객센터 메인페이지로 이동
            $("h2").click(function(){
                window.location.href = `support.jsp`;
            })

            //게시글 toggle
            $(".contentTitle").click(function(){
                $(this).next().toggle();
                $(this).find("i").toggle();
            });
            $(".contentText").hide();
            $(".fa-angle-up").hide();
        });

    </script>
</body>
</html>