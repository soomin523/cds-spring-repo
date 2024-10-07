<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>마이페이지</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/mypage/mypage.css">
    </head>

    <body>
        <button class="mainicon" id="openModalBtn">
            <img src="mypageicon.png" alt="마이페이지 아이콘">
        </button>

        <!-- 메인 마이페이지 -->
        <!-- 모달 박스 -->
        <div class="mypagemodal" id="myModal">
            <div class="mypagemain">
                <!-- 상단 -->
                <div class="mypagemain-top">
                    <h2>마이페이지</h2>
                    <span id="closeModalBtn">&times;</span>
                </div>
                <hr class="hr1">
                <!-- 중단1 -->
                <div class="mypagemain-middle1">
                    <div>
                        <img src="mypage내사진.jpg" alt="마이페이지 내사진">
                    </div>
                    <p>김문배 님</p>
                    <button id="infoBtn">내 정보</button>
                </div>

                <!-- 중단2(아이콘 버튼들) -->
                <div class="mypagemain-middle-icon1">
                    <div>
                        <button id="likeBtn"><img src="좋아요.png" alt="좋아요"></button>
                        <p>좋아요/찜</p>
                    </div>
                    <div>
                        <button id="cartBtn"><img src="장바구니.png" alt="장바구니"></button>
                        <p>장바구니</p>
                    </div>
                    <div>
                        <button id="paymentBtn"><img src="결제내역.png" alt="결제내역"></button>
                        <p>결제 및/<br>예약확인</p>
                    </div>
                </div>

                <div class="mypagemain-middle-icon2">
                    <div>
                        <button id="couponBtn"><img src="쿠폰.png" alt="쿠폰"></button>
                        <p>쿠폰함</p>
                    </div>
                    <div style="margin-left: 10px;">
                        <button id="queryBtn"><img src="문의내역.png" alt="문의하기"></button>
                        <p>문의하기</p>
                    </div>
                    <div>
                        <button id="postBtn"><img src="작성내역.png" alt="작성내역"></button>
                        <p>작성내역</p>
                    </div>
                </div>

                <div class="mypagemain-low">
                    <!-- 하단(로그아웃 버튼) -->
                    <button>로그아웃</button>
                </div>
            </div>

            <!-- 표시할 div들 -->
            <!-- 내 정보 start -->
            <div id="infoDiv" class="mypagemyinfo">
                <div class="mypagemyinfo-top">
                    <span id="backToMainBtn">&lt;</span>
                    <span class="closeModalBtn">&times;</span>
                    <h3>회원정보</h3>
                    <div>
                        <img src="mypage내사진.jpg" alt="마이페이지 내사진">
                    </div>
                    <p>김문배</p>
                </div>

                <div class="mypagemyinfo-middle">
                    <input type="text" placeholder="  이름">
                    <input type="text" placeholder="  이메일">
                    <input type="text" placeholder="  비밀번호">
                    <input type="text" placeholder="  비밀번호확인">
                    <input type="text" placeholder="  연락처">
                </div>
                <div class="mypagemyinfo-low">
                    <button>정보수정</button>
                    <button>회원탈퇴</button>
                </div>
            </div>
            <!-- 내 정보 end -->
            <!-- 좋아요 / 찜 start -->
            <div id="likeDiv" class="mypagelike">
                <!-- 상단 -->
                <div class="mypagelike-top">
                    <div>
                        <button>
                            <h3>좋아요</h3>
                        </button>
                    </div>
                    <div>
                        <button>
                            <h3>찜</h3>
                        </button>
                    </div>
                    <span id="backToMainBtn">&lt;</span>
                    <span class="closeModalBtn">&times;</span>
                </div>
                <hr>
            </div>
            <!-- 좋아요 / 찜 end -->

            <!-- 장바구니 start -->
            <div id="cartDiv" class="mypagecart">
                <div class="mypagecart-top">
                    <h3>장바구니</h3>
                    <span id="backToMainBtn">&lt;</span>
                    <span class="closeModalBtn">&times;</span>
                </div>
                <hr>
                <div class="mypagecart-middle">
                    <!-- 구매 카테고리 -->
                    <p>카테고리</p>
                    <!-- 담아둔 상품목록 -->
                    <div class="mypagecart-price">
                        <img src="mypage내사진.jpg" alt="">
                        <div>
                            <p>상품명</p>
                            <p>가격</p>
                        </div>
                    </div><!-- 구매 카테고리 -->
                    <p>카테고리</p>
                    <!-- 담아둔 상품목록 -->
                    <div class="mypagecart-price">
                        <img src="mypage내사진.jpg" alt="">
                        <div>
                            <p>상품명</p>
                            <p>가격</p>
                        </div>
                    </div><!-- 구매 카테고리 -->
                    <p>카테고리</p>
                    <!-- 담아둔 상품목록 -->
                    <div class="mypagecart-price">
                        <img src="mypage내사진.jpg" alt="">
                        <div>
                            <p>상품명</p>
                            <p>가격</p>
                        </div>
                    </div><!-- 구매 카테고리 -->
                    <p>카테고리</p>
                    <!-- 담아둔 상품목록 -->
                    <div class="mypagecart-price">
                        <img src="mypage내사진.jpg" alt="">
                        <div>
                            <p>상품명</p>
                            <p>가격</p>
                        </div>
                    </div>
                </div>

                <div class="mypagecart-low">
                    <div>
                        <p>총 액</p>
                        <p>할인금액</p>
                        <p>결제금액</p>
                    </div>
                    <div>
                        <p>50000원</p>
                        <p>-2000원</p>
                        <p>48000원</p>
                    </div>
                </div>
            </div>
            <!-- 장바구니 end -->

            <!-- 결제 예약 start-->
            <div id="paymentDiv" class="mypagepay">
                <div class="mypagepay-top">
                    <h3>결제 및 예약 내역</h3>
                    <span id="backToMainBtn">&lt;</span>
                    <span class="closeModalBtn">&times;</span>

                </div>
                <hr>
                <div class="mypagepay-middle-select">
                    <select name="" id="">
                        <option value="">숙박</option>
                        <option value="">교통</option>
                        <option value="">관광</option>
                    </select>
                </div>
                <p>날짜</p>
                <div class="mypagepay-middle-priceinfo">
                    <img src="mypage내사진.jpg" alt="">
                    <div>
                        <p>상품명(주저리주저리)</p>
                        <p>가격</p>
                        <p>상품개수</p>
                        <p>상세보기</p>
                        <span>&#x02C5;</span>
                    </div>
                </div>
                <div class="mypagepay-middle-payinfo">
                    <div class="mapagepay-middle-payinfo1">
                        <p>결제정보</p>
                        <p>상품가격</p>
                        <p>할인가격</p>
                        <p>결제수단(카드)</p>
                        <p>총 결제금액</p>
                    </div>
                    <div class="mapagepay-middle-payinfo2">
                        <p>xxx원</p>
                        <p>x원</p>
                        <p>xxx원</p>
                        <p>xxx원</p>
                    </div>
                    <hr style="border: none; border-top: 1px solid #000; margin: 10px 0;">
                </div>

                <div class="mapagepay-middle-payinfo3">

                </div>
            </div>
            <!-- 결제 예약 end -->

            <!-- 쿠폰 start -->
            <div id="couponDiv" class="mypagecoupon">
                <div class="mypagecoupon-top">
                    <h3>쿠폰함</h3>
                    <span id="backToMainBtn">&lt;</span>
                    <span class="closeModalBtn">&times;</span>
                </div>
                <hr>
                <div class="mypagecoupon-middle">
                    <div>
                        <div>
                            <img src="mypage내사진.jpg" alt="">
                        </div>
                        <div>
                            <p>쿠폰명</p>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 쿠폰 end -->

            <!-- 문의 start -->
            <div id="queryDiv" class="mypagequery">
                <div class="mypagequery-top">
                    <h3>문의하기</h3>
                    <span id="backToMainBtn">&lt;</span>
                    <span class="closeModalBtn">&times;</span>
                </div>
                <div class="mypagequery-middle">
                    <a href="https://pf.kakao.com/_Lcpln">
                        <button>
                            떠나자 카카오 문의채널
                        </button>
                    </a>
                </div>
            </div>
            <!-- 문의 end -->

            <!-- 작성 내역 start -->
            <div id="postDiv" class="mypagepost">
                <div class="mypagepost-top">
                    <h3>작성내역</h3>
                    <span id="backToMainBtn">&lt;</span>
                    <span class="closeModalBtn">&times;</span>
                </div>
            </div>
            <!-- 작성 내역 end -->
        </div>

        <script>
            // 모달 엘리먼트와 버튼 가져오기
            const modal = document.getElementById('myModal');
            const openModalBtn = document.getElementById('openModalBtn');
            const closeModalBtns = document.querySelectorAll('.closeModalBtn'); // 모든 x 버튼 선택
            const backToMainBtns = document.querySelectorAll('#backToMainBtn'); // 모든 < 버튼 선택

            // 각 버튼과 div 요소 가져오기
            const infoBtn = document.getElementById('infoBtn');
            const likeBtn = document.getElementById('likeBtn');
            const cartBtn = document.getElementById('cartBtn');
            const paymentBtn = document.getElementById('paymentBtn');
            const couponBtn = document.getElementById('couponBtn');
            const queryBtn = document.getElementById('queryBtn');
            const postBtn = document.getElementById('postBtn');

            const infoDiv = document.getElementById('infoDiv');
            const likeDiv = document.getElementById('likeDiv');
            const cartDiv = document.getElementById('cartDiv');
            const paymentDiv = document.getElementById('paymentDiv');
            const couponDiv = document.getElementById('couponDiv');
            const queryDiv = document.getElementById('queryDiv');
            const postDiv = document.getElementById('postDiv');

            const mypageMain = modal.querySelector('.mypagemain');

            // 모달 열기
            openModalBtn.addEventListener('click', () => {
                hideAllDivs();
                mypageMain.style.display = 'block'; // 기본 화면 보이기
                modal.style.display = 'flex';
            });

            // 모달 닫기 (닫기 버튼 클릭 시)
            closeModalBtns.forEach(btn => {
                btn.addEventListener('click', () => {
                    modal.style.display = 'none';
                    hideAllDivs(); // 모달을 닫을 때 모든 div 숨기기
                    mypageMain.style.display = 'block'; // 기본 마이페이지 화면으로 돌아가도록 수정
                });
            });

            // "<" 버튼 클릭 시 기본 마이페이지 화면으로 돌아가기
            backToMainBtns.forEach(btn => {
                btn.addEventListener('click', () => {
                    hideAllDivs();
                    mypageMain.style.display = 'block'; // 기본 마이페이지 화면 보이기
                });
            });

            // 모달 외부 클릭 시 닫기
            window.addEventListener('click', (e) => {
                if (e.target == modal) {
                    modal.style.display = 'none';
                    hideAllDivs(); // 모달을 닫을 때 모든 div 숨기기
                    mypageMain.style.display = 'block'; // 기본 마이페이지 화면으로 돌아가도록 수정
                }
            });

            // 버튼 클릭 시 div 표시
            infoBtn.addEventListener('click', () => {
                hideAllDivs();
                mypageMain.style.display = 'none'; // 마이페이지 내용 숨기기
                infoDiv.style.display = 'block'; // 내 정보 div 보이기
            });

            likeBtn.addEventListener('click', () => {
                hideAllDivs();
                mypageMain.style.display = 'none'; // 마이페이지 내용 숨기기
                likeDiv.style.display = 'block'; // 좋아요/찜 div 보이기
            });

            cartBtn.addEventListener('click', () => {
                hideAllDivs();
                mypageMain.style.display = 'none'; // 마이페이지 내용 숨기기
                cartDiv.style.display = 'block'; // 장바구니 div 보이기
            });

            paymentBtn.addEventListener('click', () => {
                hideAllDivs();
                mypageMain.style.display = 'none'; // 마이페이지 내용 숨기기
                paymentDiv.style.display = 'block'; // 결제 내역 div 보이기
            });

            couponBtn.addEventListener('click', () => {
                hideAllDivs();
                mypageMain.style.display = 'none'; // 마이페이지 내용 숨기기
                couponDiv.style.display = 'block'; // 쿠폰함 div 보이기
            });

            queryBtn.addEventListener('click', () => {
                hideAllDivs();
                mypageMain.style.display = 'none'; // 마이페이지 내용 숨기기
                queryDiv.style.display = 'block'; // 문의하기 div 보이기
            });

            postBtn.addEventListener('click', () => {
                hideAllDivs();
                mypageMain.style.display = 'none'; // 마이페이지 내용 숨기기
                postDiv.style.display = 'block'; // 작성내역 div 보이기
            });

            // 모든 div 숨기기
            function hideAllDivs() {
                infoDiv.style.display = 'none';
                likeDiv.style.display = 'none';
                cartDiv.style.display = 'none';
                paymentDiv.style.display = 'none';
                couponDiv.style.display = 'none';
                queryDiv.style.display = 'none';
                postDiv.style.display = 'none';
            }
        </script>
    </body>

    </html>