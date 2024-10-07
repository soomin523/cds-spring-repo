<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>


    <head>
        <meta charset="UTF-8">
        <title>지역별 숙소</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/accommodations/cityaccomo.css">
        <link rel="stylesheet" type="text/css" href="accommo.css">
    </head>


    <body>
        <section>
            <div class="fullcity">
                <div class="cityaccoheader">
                    <button>◀</button>
                    <h2>지역 별 숙소</h2>
                    <div><button>돋보기</button><button>장바구니</button></div>
                </div>

                <hr>

                <div class="accodesbox">
                    <div>
                        <div></div>
                        <h3>서울</h3>
                    </div>
                    <div>
                        <div></div>
                        <h3>대구</h3>
                    </div>
                    <div>
                        <div></div>
                        <h3>대전</h3>
                    </div>
                    <div>
                        <div></div>
                        <h3>부산</h3>
                    </div>
                    <div>
                        <div></div>
                        <h3>울산</h3>
                    </div>
                    <div>
                        <div></div>
                        <h3>제주</h3>
                    </div>
                    <div>
                        <div></div>
                        <h3>광주</h3>
                    </div>
                    <div>
                        <div></div>
                        <h3>인천</h3>
                    </div>
                </div>
                
                <hr>


                <div class="citymiddle" id="citymiddle">
                    <div>


                    </div>
                </div>




            </div>

        </section>
    </body>

    </html>

    <script>
        // 현재 페이지 번호
        let page = 1;

// 더미 데이터를 생성하는 함수 (한 행에 2개씩 추가)
function createDummyBoxes(page) {
    const cityMiddle = document.getElementById('citymiddle');
    
    for (let i = 0; i < 5; i++) { // 한 번에 5개의 행을 추가
        const row = document.createElement('div');
        row.classList.add('city-row');
        
        // 각 행에 2개의 박스를 추가
        for (let j = 0; j < 2; j++) {
            const boxNumber = (page - 1) * 10 + (i * 2 + j + 1);
            const dummyBox = document.createElement('div');
            dummyBox.classList.add('city-accommodation');
            dummyBox.innerHTML = `<h3>박스 ${boxNumber}</h3><p>여기에 내용이 들어갑니다.</p>`;
            row.appendChild(dummyBox);
        }
        
        cityMiddle.appendChild(row);
    }
}

// 스크롤 이벤트 리스너 추가
window.addEventListener('scroll', () => {
    const scrollable = document.documentElement.scrollHeight - window.innerHeight;
    const scrolled = window.scrollY;

    // 스크롤이 페이지 끝에 도달했을 때 추가 데이터 로드
    if (scrollable - scrolled <= 1) {
        page++; // 페이지 번호 증가
        createDummyBoxes(page); // 더미 박스 생성
    }
});

// 초기 더미 박스 로드
createDummyBoxes(page);

    </script>