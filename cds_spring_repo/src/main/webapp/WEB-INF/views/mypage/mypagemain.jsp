<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <html>

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>마이페이지</title>
            <link rel="stylesheet" type="text/css"
                href="${pageContext.request.contextPath}/resources/css/mypagemain.css">
            <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/mypagemain.js"></script>
        </head>

        <body>
            <div class="background">
                <!-- 맨위 섹션 -->
                <div class="main-areaname">
                    <h2>마이페이지</h2>
                </div>
                <div class="main-info">
                    <div class="myinfo-top">
                        <p>회원이름</p>
                        <button onclick="goAmendPage()"><img src="${pageContext.request.contextPath}/resources/img/정보수정.png" alt=""></button>
                    </div>
                    <div class="myinfo-sub">
                        <p>userID@gmail.com</p>
                    </div>
                </div>
                <!-- 마이페이지 카테고리 -->
                <div class="main-category">
                    <a href="mypagelike.do">
                        <div>

                            <img src="${pageContext.request.contextPath}/resources/img/즐겨찾기.png" alt="">

                        </div>
                        <p>즐겨찾기</p>
                    </a>
                    <a href="mypagewrite.do">
                        <div>

                            <img src="${pageContext.request.contextPath}/resources/img/작성내역.png" alt="">

                        </div>
                        <p>내가 쓴 글</p>
                    </a>
                    <a href="mypagequery.do">
                        <div>

                            <img src="${pageContext.request.contextPath}/resources/img/문의하기.png" alt="">

                        </div>
                        <p>문의하기</p>
                    </a>

                </div>

                <div class="main-slogan">
                    <div style="background-color: red;">
                        슬로건1
                    </div>
                    <div style="background-color: orange;">
                        슬로건2
                    </div>
                    <div style="background-color: yellow">
                        슬로건3
                    </div>
                </div>
                <div class="main-logout">
                    <button>로그아웃</button>

                </div>


            </div>
        </body>

        </html>