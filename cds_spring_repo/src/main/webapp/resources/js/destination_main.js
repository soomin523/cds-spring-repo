document.addEventListener("DOMContentLoaded", function () {
    const regionCircles = document.querySelector(".region-circles");
    const scrollStep = 80;

    // 왼쪽으로 스크롤 (첫 번째 원을 마지막으로 이동)
    document.querySelector(".nav-left").addEventListener("click", function () {
        regionCircles.style.transition = "transform 0.5s ease";
        regionCircles.style.transform = `translateX(${scrollStep}px)`;

        setTimeout(function () {
            regionCircles.style.transition = "none";
            regionCircles.insertBefore(regionCircles.lastElementChild, regionCircles.firstElementChild);
            regionCircles.style.transform = "translateX(0)";
        }, 500);
    });

    // 오른쪽으로 스크롤 (첫 번째 원을 마지막으로 이동)
    document.querySelector(".nav-right").addEventListener("click", function () {
        regionCircles.style.transition = "transform 0.5s ease";
        regionCircles.style.transform = `translateX(-${scrollStep}px)`;

        setTimeout(function () {
            regionCircles.style.transition = "none";
            regionCircles.appendChild(regionCircles.firstElementChild);
            regionCircles.style.transform = "translateX(0)";
        }, 500);
    });

    $(document).ready(function() {
        let defaultRegion = 1;  
        let defaultRname = "서울";
        let defaultSigungucode = "종로구"; 
        initialize(defaultRegion, defaultRname);

        $(".circle-item").on("click", function() {
            var region = $(this).data('region');
            var rname = $(this).data('rname');
            $('.desselect h3').text(rname);

            $.ajax({
                type: "GET",
                url: "getSigungunName.do",    
                data: { areacode: region },
                headers: { "Accept": "application/json" },
                success: function(data) {
                    let htmlc = '';
                    data.forEach(function(selectItem) {
                        htmlc += `<option value="${selectItem.d_sigungucode}">${selectItem.d_sigunguname}</option>`;
                    });
                    
                    $("#sigunguselect").html(htmlc);
                    let firstSigungucode = $("#sigunguselect option:first").val();
                    loadDesList(region, firstSigungucode);

                    $("#sigunguselect").off("change").on("change", function() {
                        let sigungucode = $("#sigunguselect").val();
                        loadDesList(region, sigungucode);
                    });
                },
                error: function() {
                    console.log("지역 리스트를 불러오는 데 실패했습니다.");
                }
            });
        });

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
                        renderDesList(data);

                        $('#search-box').off('keyup').on('keyup', function() {
                            const searchTerm = $(this).val().toLowerCase();
                            const filteredData = data.filter(function(desList) {
                                return desList.d_title.toLowerCase().includes(searchTerm);
                            });
                            renderDesList(filteredData);
                        });
                    } else {
                        console.log("데이터가 배열이 아닙니다.");
                    }
                },
                error: function() {
                    console.log("데이터를 불러오는 데 실패했습니다.");
                }
            });
        }

        function renderDesList(data) {
            let htmlList = '';
            data.forEach(function(desList) {
                htmlList += `
                  <div class="desList" data-contentid="${desList.d_contentid}" data-title="${desList.d_title}" data-image="${desList.d_firstimage}">
			            <div class="desimg-container">
			                <img src="${desList.d_firstimage}" alt="${desList.d_title}">
			                <p>${desList.d_title}</p>
			            </div>
			            
			     </div>`;
            });
               $('.desimg1').html(htmlList);
    
			  
						
			    $('.des-title').css({
			        'padding': '10px',
			        'font-size': '16px',  // 텍스트 크기 조정
			        'color': '#333',
			        'height': '50px',  // 텍스트 영역 고정 높이
			        'display': 'flex',
			        'align-items': 'center',
			        'justify-content': 'center',
			        'text-align': 'center',
			        'background-color': '#f8f8f8'  // 텍스트 배경색 추가
			    });

            $('.desList').on('click', function () {
                const contentid = this.dataset.contentid;
                console.log("콘텐츠아이디", contentid);
                loadDesInfo(contentid);
            });
        }

        function loadDesInfo(contentid) {
            $.ajax({
                type: "GET",
                url: "getDesInfo.do",
                data: { contentid: contentid },
                headers: { "Accept": "application/json" },
                success: function(data) {
                    console.log("모달데이터", data);
                    showModal(data);
                },
                error: function() {
                    console.log("데이터를 불러오는 데 실패했습니다.");
                }
            });
        }

        function showModal(data) {
            let htmlInfo = `<div class="modalbox">
                <span class="close-btn">&times;</span>
                <div class="boxtitle">
                    <h2>${data.d_title}</h2>
                    <p>${data.d_addr1}</p>
                </div>
                <div>
                    <img src="${data.d_firstimage}">
                </div>
                <div>
                    <h2>상세정보</h2>
                    <p>${data.overview}</p>
                </div>
                <div>
                    카카오지도
                    <div id="map" style="width:100%;height:300px;"></div>
                </div>
                <div>
                   상세주소 : ${data.d_addr1}<br>
                   문의 :  ${data.infocenter}<br>
                   쉬는 날 : ${data.restdate}<br>
                   이용시간 : ${data.usetime}<br>
                   주차시설 : ${data.parking}<br>
                   유모차대여정보 : ${data.chkbabycarriage}<br>
                   애완동물동반가능정보 :${data.chkpet}<br>
                </div>
            </div>`;

            $("#myModal").html(htmlInfo);
            $(".modal-background").css("display", "block");

            if (data.d_mapx && data.d_mapy) {
                var mapContainer = document.getElementById('map');
                var mapOption = {
                    center: new kakao.maps.LatLng(data.d_mapy, data.d_mapx),
                    level: 3
                };
                var map = new kakao.maps.Map(mapContainer, mapOption);
                var markerPosition = new kakao.maps.LatLng(data.d_mapy, data.d_mapx);
                var marker = new kakao.maps.Marker({ position: markerPosition });
                marker.setMap(map);
                var infoContent = '<div class="info-window">' + data.d_title + '</div>';
                var infoWindow = new kakao.maps.InfoWindow({ content: infoContent });
                infoWindow.open(map, marker);
            } else {
                console.error("좌표 정보가 부족합니다.");
            }

            $(".close-btn").on("click", function() {
                $(".modal-background").css("display", "none");
            });

            $(".modal-background").on("click", function(event) {
                if (event.target.classList.contains("modal-background")) {
                    $(".modal-background").css("display", "none");
                }
            });
        }

        function initialize(region, rname) {
            $('.desselect h3').text(rname);
            $.ajax({
                type: "GET",
                url: "getSigungunName.do",
                data: { areacode: region },
                headers: { "Accept": "application/json" },
                success: function(data) {
                    let htmlc = '';
                    data.forEach(function(selectItem) {
                        htmlc += `<option value="${selectItem.d_sigungucode}">${selectItem.d_sigunguname}</option>`;
                    });

                    $("#sigunguselect").html(htmlc);
                    let firstSigungucode = $("#sigunguselect option:first").val();
                    loadDesList(region, firstSigungucode);

                    $("#sigunguselect").off("change").on("change", function() {
                        let sigungucode = $("#sigunguselect").val();
                        loadDesList(region, sigungucode);
                    });
                },
                error: function() {
                    console.log("지역 리스트를 불러오는 데 실패했습니다.");
                }
            });
        }
    });
});
