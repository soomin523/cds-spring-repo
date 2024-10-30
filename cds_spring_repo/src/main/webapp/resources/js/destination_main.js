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
     	let areacode = getParameterByName('areacode'); // URL 파라미터에서 areaCode를 가져옴
        let defaultRname = '';
        switch(areacode){
        	case '1' : defaultRname = '서울'; break;
        	case '2' : defaultRname = '인천'; break;
        	case '3' : defaultRname = '대전'; break;
        	case '4' : defaultRname = '대구'; break;
        	case '5' : defaultRname = '광주'; break;
        	case '6' : defaultRname = '부산'; break;
        	case '7' : defaultRname = '울산'; break;
        	case '31' : defaultRname = '경기'; break;
        	case '32' : defaultRname = '강원'; break;
        	case '33' : defaultRname = '충북'; break;
        	case '34' : defaultRname = '충남'; break;
        	case '35' : defaultRname = '경북'; break;
        	case '36' : defaultRname = '경남'; break;
        	case '37' : defaultRname = '전북'; break;
        	case '38' : defaultRname = '전남'; break;
        	case '39' : defaultRname = '제주'; break;
        	case '8' : defaultRname = '세종'; break;
        };
        getList(areacode, defaultRname);

		// URL 파라미터에서 값을 추출하는 함수
    	function getParameterByName(name) {
	        let url = window.location.href;
	        name = name.replace(/[\[\]]/g, '\\$&');
	        let regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
	            results = regex.exec(url);
	        if (!results) return null;
	        if (!results[2]) return '';
	        return decodeURIComponent(results[2].replace(/\+/g, ' '));
	       
	    }
		
		let contentId = getParameterByName('contentid'); // URL 파라미터에서 areaCode를 가져옴
		if(contentId != null){
			loadDesInfo(contentId);
		}


		//시군구 이름 받아오기
        $(".circle-item").on("click", function() {
            var region = $(this).data('region');
            var rname = $(this).data('rname');
        	getList(region, rname);
        });
            
        function getList(region, rname){    
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
            
            // 모든 .desheader_circle 스타일 초기화
		    $(".desheader_circle").css({
		        "border-radius": "",
		        "width": "",
		        "height": "",
		        "background-size": "",
		        "background-position": "",
		        "border": "",
		        "margin": "",
		        "cursor": ""
		    });
		    
		    var desheader_circles = document.querySelectorAll(".circle-item");
		    desheader_circles.forEach(function(item){
		    	var area = item.getAttribute("data-region");
		    	if(area == region){
		    		var div = item.querySelector("div"); // 자식 <div> 요소 찾기
			        if (div) {
			            div.style.borderRadius = "50%";
			            div.style.width = "90px";
			            div.style.height = "90px";
			            div.style.backgroundSize = "cover";
			            div.style.backgroundPosition = "center";
			            div.style.border = "2px solid skyblue";
			            div.style.margin = "0 15px";
			            div.style.cursor = "pointer";
			        }
		    	}
		    
		    });
            
            $(this).find("div").css({
            	"border-radius": "50%",
			    "width": "90px",
			    "height": "90px",
			    "background-size": "cover",
			    "background-position": "center",
			    "border":"skyblue solid 2px",
			    "margin": "0 15px",
			    "cursor":"pointer"
            });
            
        };

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
				    </div>
				    <div class="des-title">
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
                    <p>${data.d_areaname} ${data.d_sigunguname}</p>
                </div>
                <div>
                    <img src="${data.d_firstimage}">
                </div>
                <div class="boxoverview">
                    <h2>설명</h2>
                </div>
                <div class="boxoverviewtext">
                	<p>${data.overview}</p>
                </div>
                <div>
                    <div id="map" style="width:100%;height:300px;"></div>
                </div>
                <div class="boxaddr">
                   <p>상세주소 : ${data.d_addr1}</p>
                </div>
                <div class="boxdetailinfo">
                	<h2>상세정보</h2>
                	<p>(관광지별 상세정보의 유무가 다르니 유의해주시길 바랍니다)</p>
                </div>
                <div class="boxdetailinfotext">
                	<div class="detailtext1">
	                  <p> 문의 :  ${data.infocenter} </p>
	                  <p> 쉬는 날 : ${data.restdate}</p>
	                  <p> 이용시간 : ${data.usetime} </p>
                   	</div>
                   	<div class="detailtext2">
	                  <p> 	주차시설 : ${data.parking} </p>
	                  <p> 유모차대여정보 : ${data.chkbabycarriage} </p>
	                  <p> 애완동물동반가능정보 :${data.chkpet}</p>
                   	</div>
                   
                </div>
            </div>`;

            $("#myModal").html(htmlInfo);
            $(".modal-background").css("display", "block");

            if (data.d_mapx && data.d_mapy) {
                var mapContainer = document.getElementById('map');
                var mapOption = {
                    center: new kakao.maps.LatLng(data.d_mapy, data.d_mapx),
                    level: 3,
                     scrollwheel: false, // 스크롤 줌 비활성화
			         disableDoubleClickZoom: true, // 더블 클릭 줌 비활성화
			         draggable: false, //드래그 비활성화
			                    
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

    });
});
