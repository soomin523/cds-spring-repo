<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>여행지 둘러보기</title>
        <link rel="stylesheet" type="text/css"
            href="${pageContext.request.contextPath}/destination/destination_main.css">
    </head>

    <body>
        <section>
            <div id="des1">
                <div class="desheader">
                    <div class="desheader_1">
                        <div class="desheader_1a">
                            <h2>국내 여행지 정보</h2>
                            <p>얼마나 알고 있니?</p>
                        </div>
                        <div class="desheader_2">
                            <span>&lt;</span>
                            <div>
                                <div class="desheader_circle" style="background-image: url(prjt-이미지circle.jpg);"></div>
                                <p>서울</p>
                            </div>
                            <div>
                                <div class="desheader_circle" style="background-image: url(prjt-이미지circle.jpg);"></div>
                                <p>인천</p>
                            </div>
                            <div>
                                <div class="desheader_circle" style="background-image: url(prjt-이미지circle.jpg);"></div>
                                <p>경기</p>
                            </div>
                            <div>
                                <div class="desheader_circle" style="background-image: url(prjt-이미지circle.jpg);"></div>
                                <p>부산</p>
                            </div>
                            <div>
                                <div class="desheader_circle" style="background-image: url(prjt-이미지circle.jpg);"></div>
                                <p>울산</p>
                            </div>
                            <div>
                                <div class="desheader_circle" style="background-image: url(prjt-이미지circle.jpg);"></div>
                                <p>대전</p>
                            </div>
                            <div>
                                <div class="desheader_circle" style="background-image: url(prjt-이미지circle.jpg);"></div>
                                <p>광주</p>
                            </div>
                            <div>
                                <div class="desheader_circle" style="background-image: url(prjt-이미지circle.jpg);"></div>
                                <p>세종</p>
                            </div>
                            <div>
                                <div class="desheader_circle" style="background-image: url(prjt-이미지circle.jpg);"></div>
                                <p>청양</p>
                            </div>
                            <span>&gt;</span>

                        </div>
                    </div>
                    <div class="desselect">
                        <h3>서울시</h3>
                        <select name="" id="">
                            <option value="">금천구</option>
                            <option value="">구로구</option>
                            <option value="">광진구</option>
                            <option value="">강서구</option>
                        </select>
                        
                        <input type="text" placeholder="검색창">
                        
                    </div>


                    <div class="desimg1">
                        <div>
                            <div class="desimg2" style="background-image:url(prjt-이미지01.jpg) ;"></div>
                            <div>설명 1</div>
                        </div>
                        <div>
                            <div class="desimg2" style="background-image:url(prjt-이미지01.jpg) ;"></div>
                            <div>설명 1</div>
                        </div>
                        <div>
                            <div class="desimg2" style="background-image:url(prjt-이미지01.jpg) ;"></div>
                            <div>설명 1</div>
                        </div>
                        <div>
                            <div class="desimg2" style="background-image:url(prjt-이미지01.jpg) ;"></div>
                            <div>설명 1</div>
                        </div>
                        <div>
                            <div class="desimg2" style="background-image:url(prjt-이미지01.jpg) ;"></div>
                            <div>설명 1</div>
                        </div>
                        <div>
                            <div class="desimg2" style="background-image:url(prjt-이미지01.jpg) ;"></div>
                            <div>설명 1</div>
                        </div>
                        <div>
                            <div class="desimg2" style="background-image:url(prjt-이미지01.jpg) ;"></div>
                            <div>설명 1</div>
                        </div>
                        <div>
                            <div class="desimg2" style="background-image:url(prjt-이미지01.jpg) ;"></div>
                            <div>설명 1</div>
                        </div>
                        <div>
                            <div class="desimg2" style="background-image:url(prjt-이미지01.jpg) ;"></div>
                            <div>설명 1</div>
                        </div>
                        <div>
                            <div class="desimg2" style="background-image:url(prjt-이미지01.jpg) ;"></div>
                            <div>설명 1</div>
                        </div>
                        <div>
                            <div class="desimg2" style="background-image:url(prjt-이미지01.jpg) ;"></div>
                            <div>설명 1</div>
                        </div>
                        <div>
                            <div class="desimg2" style="background-image:url(prjt-이미지01.jpg) ;"></div>
                            <div>설명 1</div>
                        </div>
                    </div>

                    <div class="desplacepagebutton">
                        <button type="submit">1</button>
                        <button type="submit">2</button>
                        <button type="submit">3</button>
                        <button type="submit">></button>
                    </div>
                    <div class="desslogan" style="background-image: url(des슬로건.png);"></div>






                </div>

            </div>
        </section>
        <script>
                            // 좌우로 스크롤 할 수 있는 상태 설정
                let scrollPosition = 0; // 현재 스크롤 위치
                const scrollStep = 100; // 한 번에 이동하는 거리
                const regionCircles = document.querySelector(".region-circles");

                // 왼쪽으로 스크롤
                document.querySelector("span:first-child").addEventListener("click", function() {
                    if (scrollPosition > 0) {
                        scrollPosition -= scrollStep;
                        regionCircles.style.transform = `translateX(-${scrollPosition}px)`;
                    }
                });

                // 오른쪽으로 스크롤
                document.querySelector("span:last-child").addEventListener("click", function() {
                    if (scrollPosition < regionCircles.scrollWidth - regionCircles.clientWidth) {
                        scrollPosition += scrollStep;
                        regionCircles.style.transform = `translateX(-${scrollPosition}px)`;
                    }
                });

        </script>
    </body>

    </html>