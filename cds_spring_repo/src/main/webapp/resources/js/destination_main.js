document.addEventListener("DOMContentLoaded", function () {
    const regionCircles = document.querySelector(".region-circles"); // 원들이 담긴 컨테이너
    const scrollStep = 80; // 원 하나의 크기 (70px + 간격 10px)

    // 왼쪽으로 스크롤 (첫 번째 원을 마지막으로 이동)
    document.querySelector(".nav-left").addEventListener("click", function () {
        regionCircles.style.transition = "transform 0.5s ease"; // 부드러운 스크롤
        regionCircles.style.transform = `translateX(${scrollStep}px)`; // 왼쪽으로 스크롤

        // 스크롤 애니메이션이 끝난 후, 마지막 원을 첫 번째 앞으로 이동
        setTimeout(function () {
            regionCircles.style.transition = "none"; // 애니메이션 끄기
            regionCircles.insertBefore(regionCircles.lastElementChild, regionCircles.firstElementChild); // 마지막 원을 첫 번째 원 앞으로 이동
            regionCircles.style.transform = "translateX(0)"; // 위치 초기화
        }, 500); // 애니메이션 시간 후 실행
    });

    // 오른쪽으로 스크롤 (첫 번째 원을 마지막으로 이동)
    document.querySelector(".nav-right").addEventListener("click", function () {
        regionCircles.style.transition = "transform 0.5s ease"; // 부드러운 스크롤
        regionCircles.style.transform = `translateX(-${scrollStep}px)`; // 오른쪽으로 스크롤

        // 스크롤 애니메이션이 끝난 후, 첫 번째 원을 마지막으로 이동
        setTimeout(function () {
            regionCircles.style.transition = "none"; // 애니메이션 끄기
            regionCircles.appendChild(regionCircles.firstElementChild); // 첫 번째 원을 마지막으로 이동
            regionCircles.style.transform = "translateX(0)"; // 위치 초기화
        }, 500); // 애니메이션 시간 후 실행
    });

    $(document).ready(function() {
        // 초기화 시 서울과 종로구를 기본 선택
        let defaultRegion = 1;  // 서울 areacode
        let defaultRname = "서울";  // 서울 이름
        let defaultSigungucode = "종로구";  // 기본 종로구 값
        initialize(defaultRegion, defaultRname);  // 초기 설정

        // 원 클릭 시 처리
        $(".circle-item").on("click", function() {
            var region = $(this).data('region');
            var rname = $(this).data('rname');

            // 선택한 지역으로 텍스트 업데이트
            $('.desselect h3').text(rname);

            // 해당 지역에 맞는 시군구 데이터를 가져오는 AJAX 호출
            $.ajax({
                type: "GET",
                url: "getSigungunName.do",    
                data: { areacode: region },
                headers: { "Accept": "application/json" },
                success: function(data) {
                    let htmlc = ''; // 시군구 옵션 초기화
                    data.forEach(function(selectItem) {
                        htmlc += `<option value="${selectItem.d_sigungucode}">${selectItem.d_sigunguname}</option>`;
                    });
                    
                    // 시군구 선택창 업데이트
                    $("#sigunguselect").html(htmlc);

                    // 첫 번째 시군구 값으로 데이터 가져오기
                    let firstSigungucode = $("#sigunguselect option:first").val();
                    loadDesList(region, firstSigungucode); // 첫 번째 시군구 값으로 데이터 호출

                    // 시군구 선택 시 새로운 데이터를 가져오는 이벤트 등록
                    $("#sigunguselect").off("change").on("change", function() {
                        let sigungucode = $("#sigunguselect").val();
                        loadDesList(region, sigungucode);  // 선택한 시군구 값으로 데이터 호출
                    });
                },
                error: function() {
                    console.log("지역 리스트를 불러오는 데 실패했습니다.");
                }
            });
        });

        // 데이터 로드 함수
        function loadDesList(region, sigungucode) {
            console.log("시군구 코드:", sigungucode);
            $.ajax({
                type: "GET",
                url: "getDesList.do",
                data: {
                    areacode: region, 
                    sigungucode: sigungucode
                },
                headers: { "Accept": "application/json" },
                success: function(data) {
                    console.log("받은 데이터:", data);
                    if (Array.isArray(data)) {
                        let htmlList = ''; // 초기값 빈 문자열로 설정
                        data.forEach(function(desList) {
                            htmlList += `
                            <div class="desimg1 desList" data-title="${desList.d_title}" data-description="${desList.d_overview}" data-image="${desList.d_firstimage}">
                                <div class="desimg2" style="background-image:url('${desList.d_firstimage}')"></div>
                                <div>${desList.d_title}</div>
                            </div>`;
                        });

                        // 데이터 반영
                        $('.desimg1').html(htmlList);

                        // 관광지 클릭 이벤트 추가
                        $('.desList').on('click', function () {
                            const title = $(this).data('title');
                            const description = $(this).data('description');
                            const image = $(this).data('image');
                            openModal(title, description, image);
                        });
                    } else {
                        console.log("데이터가 배열이 아닙니다. 반환된 데이터:", data);
                    }
                },
                error: function() {
                    console.log("데이터를 불러오는 데 실패했습니다.");
                }
            });
        }

        // 초기화 함수: 서울 및 종로구 데이터 불러오기
        function initialize(region, rname) {
            $('.desselect h3').text(rname);  // 기본 지역 이름 설정
            $.ajax({
                type: "GET",
                url: "getSigungunName.do",    
                data: { areacode: region },
                headers: { "Accept": "application/json" },
                success: function(data) {
                    let htmlc = ''; // 시군구 옵션 초기화
                    data.forEach(function(selectItem) {
                        htmlc += `<option value="${selectItem.d_sigungucode}">${selectItem.d_sigunguname}</option>`;
                    });
                    
                    // 시군구 선택창 업데이트
                    $("#sigunguselect").html(htmlc);

                    // 초기화 시 첫 번째 시군구(종로구) 데이터 불러오기
                    let firstSigungucode = $("#sigunguselect option:first").val();
                    loadDesList(region, firstSigungucode);

                    // 시군구 선택 이벤트 등록
                    $("#sigunguselect").off("change").on("change", function() {
                        let sigungucode = $("#sigunguselect").val();
                        loadDesList(region, sigungucode); // 선택한 시군구 값으로 데이터 호출
                    });
                },
                error: function() {
                    console.log("지역 리스트를 불러오는 데 실패했습니다.");
                }
            });
        }
    });

    // 모달 요소
	const modal = document.getElementById('myModal');
	
	// 모달을 여는 함수 (특정 버튼을 클릭했을 때 실행)
	function openModal(title, description, imageSrc) {
	    document.getElementById('modal-title').textContent = title;
	    document.getElementById('modal-description').textContent = description;
	    document.getElementById('modal-image').src = imageSrc;
	    modal.style.display = 'block'; // 모달 보이기
	}
	
	// 모달을 닫는 함수
	function closeModal() {
	    modal.style.display = 'none'; // 모달 숨기기
	}
	
	// 닫기 버튼 클릭 시 모달 닫기
	document.querySelector('.close-btn').addEventListener('click', closeModal);
	
	// 배경 클릭 시 모달 닫기
	modal.addEventListener('click', function(event) {
	    if (event.target === modal) {
	        closeModal(); // 배경 클릭 시 모달 닫기
	    }
	});
});
