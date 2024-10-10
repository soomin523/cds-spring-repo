$(function(){

	//검색어 입력창
	$(".search > button").click(function(){
		$("#dateInput").val("");
        $(".areaSelect").val("");
		const searchText = $("#searchText").val().trim();
		
		if(searchText != ""){
			searchTitle(searchText);
		}else{
			alert("검색어를 입력해주세요");
		}
	});
	// 검색어 입력창
    $("#searchText").on("keydown", function(event) {
        if (event.key === "Enter") {
        	$("#dateInput").val("");
        	$(".areaSelect").val("");
            const searchText = $(this).val().trim();
            if (searchText !== "") {
                searchTitle(searchText);
            } else {
                alert("검색어를 입력해주세요");
            }
        }
    });
    //검색 리스트 요청
    function searchTitle(searchText){
    	$.ajax({
            type:"get",
            url:"http://localhost:9090/web/festival/getFestivalSearchTitle.do",
            data:{ searchText: searchText },
            headers: {"Accept": "application/json"},
            success:function(festivalList){
                loadingFestivalList(festivalList);
            },
            error:function(){
                console.log("초기화된 리스트를 불러오는 데 실패했습니다.");
            }
        });
    };
    
    
    //진행중, 진행예정 버튼 초기화
    function ingSoonReset(){
		if($(".duration > .ing").css("backgroundColor") !== "rgba(255, 255, 255, 0)"){
	    	$(".duration > .ing").css("backgroundColor", "rgba(255, 255, 255, 0)")
	    }
	    if($(".duration > .soon").css("backgroundColor") !== "rgba(255, 255, 255, 0)"){
	    	$(".duration > .soon").css("backgroundColor", "rgba(255, 255, 255, 0)")
	    }
    };

    //select 선택창 초기화
    $(".selectRefresh").click(()=>{
        $(".categorySelect").val("");
        $("#dateInput").val("");
        $(".areaSelect").val("");
        $("#searchText").val("");
        
        ingSoonReset();

		const selectarea = $(".areaSelect").val();
        const dateInput = $("#dateInput").val();
        const selectDate = dateInput.replace(/-/g, '');
        
        $.ajax({
            type:"get",
            url:"http://localhost:9090/web/festival/getFestivalSelectList.do",
            data:{ areaCode: selectarea, selectDate: selectDate },
            headers: {"Accept": "application/json"},
            success:function(festivalList){
                loadingFestivalList(festivalList);
            },
            error:function(){
                console.log("초기화된 리스트를 불러오는 데 실패했습니다.");
            }
        });
    });
    

    //진행중/예정 버튼
    $(".duration > button").click(function(){
        let isColor = $(this).css("backgroundColor") !== "rgba(255, 255, 255, 0)";
        
        // 모든 버튼의 배경을 초기화
        $(".duration button").css("backgroundColor", "#fff0");
        $("#dateInput").val("");
        $("#searchText").val("");
        
        // 클릭한 버튼의 배경색을 토글
        if (!isColor) {
            $(this).css("backgroundColor", "#87bee541");
            const selectarea = $(".areaSelect").val();
            
            const today = new Date();
			const year = today.getFullYear();
			const month = String(today.getMonth() + 1).padStart(2, '0'); // 월은 0부터 시작하므로 1을 더해줌
			const day = String(today.getDate()).padStart(2, '0');
			
			const selectDate = `${year}${month}${day}`;
            
            // 버튼의 클래스에 따라 작업 수행
	        if ($(this).hasClass('ing')) {
	            $.ajax({
	                type:"get",
	                url:"http://localhost:9090/web/festival/getFestivalSelectList.do",
	                data:{ areaCode: selectarea, selectDate: selectDate },
	                headers: {"Accept": "application/json"},
	                success:function(festivalList){
	                    loadingFestivalList(festivalList);
	                },
	                error:function(){
	                    console.log("진행중 리스트를 불러오는 데 실패했습니다.");
	                }
	            });

	        } else if ($(this).hasClass('soon')) {
	            $.ajax({
	                type:"get",
	                url:"http://localhost:9090/web/festival/getFestivalSoonList.do",
	                data:{ areaCode: selectarea, selectDate: selectDate },
	                headers: {"Accept": "application/json"},
	                success:function(festivalList){
	                    loadingFestivalList(festivalList);
	                },
	                error:function(){
	                    console.log("진행예정 리스트를 불러오는 데 실패했습니다.");
	                }
	            });
	        }
        }else {
        	const selectarea = $(".areaSelect").val();
        	const selectDate = $("#dateInput").val();
        
        	$.ajax({
                type:"get",
                url:"http://localhost:9090/web/festival/getFestivalSelectList.do",
                data:{ areaCode: selectarea, selectDate: selectDate },
                headers: {"Accept": "application/json"},
                success:function(festivalList){
                    loadingFestivalList(festivalList);
                },
                error:function(){
                    console.log("진행버튼 초기화 리스트를 불러오는 데 실패했습니다.");
                }
            });
        
        }
    });
    
    //지역 선택에 따른 리스트 불러오기
	$(".areaSelect").change(function() {
        const selectarea = $(this).val();
        const dateInput = $("#dateInput").val();
        const selectDate = dateInput.replace(/-/g, '');
        $("#searchText").val("");
        
        ingSoonReset();
      
    	$.ajax({
            type:"get",
            url:"http://localhost:9090/web/festival/getFestivalSelectList.do",
            data:{ areaCode: selectarea, selectDate: selectDate },
            headers: {"Accept": "application/json"},
            success:function(festivalList){
                loadingFestivalList(festivalList);
            },
            error:function(){
                console.log("지역 선택 리스트를 불러오는 데 실패했습니다.");
            }
        });
        
        //상세지역 선택창 만들기
        $.ajax({
            type:"get",
            url:"http://localhost:9090/web/festival/getAreaList.do",
            data:{ areaCode: selectarea },
            headers: {"Accept": "application/json"},
            success:function(AreaList){
                addAreaList(AreaList);
            },
            error:function(){
                console.log("지역 리스트를 불러오는 데 실패했습니다.");
            }
        });

    });
    //상세지역 선택창 리스트 불러오기
    function addAreaList(AreaList){
    	let htmlContent = `
    		<option value="">상세지역</option>
    	`;
    	
    	AreaList.forEach(function(item) {
	    	htmlContent += `
	    		<option value="${item.f_sigungucode}">${item.f_sigunguname}</option>
	    	`;
    	});
    	
    	$(".categorySelect").html(htmlContent);
    };
    
    //상세지역 선택에 따른 리스트 불러오기
    $("categorySelect").change(function(){
    	
    
    });
    
    //날짜 선택에 따른 리스트 불러오기
    $("#dateInput").change(function() {
    	const selectarea = $(".areaSelect").val();
        const selectValue = $(this).val();
        const selectDate = selectValue.replace(/-/g, '');
        $("#searchText").val("");
        
        ingSoonReset();

        $.ajax({
            type:"get",
            url:"http://localhost:9090/web/festival/getFestivalSelectList.do",
            data:{ areaCode: selectarea, selectDate: selectDate },
            headers: {"Accept": "application/json"},
            success:function(festivalList){
                loadingFestivalList(festivalList);
            },
            error:function(){
                console.log("날짜 선택 리스트를 불러오는 데 실패했습니다.");
            }
        });
    });
    
    
    //축제 리스트 hover 효과
    function festivalListHover(){
	    $(".festivalitem > .itemImg > .hiddenItem").hide();
	    $(".festivalitem > .itemImg").hover(
	        function(){
	            $(this).find(".hiddenItem").show();
	        },
	        function(){
	            $(this).find(".hiddenItem").hide();
	        }
	    );    
    };
    festivalListHover();

	//선택에 따른 축제 리스트 html에 표시하기
    function loadingFestivalList(festivalList){
        let htmlContent = "";
        let contentid = [];
        
        if(festivalList.length == 0){
        	htmlContent += `
        		<p>검색 결과가 없습니다.</p>
                <p>다시 검색해 주세요.</p>
        	`;
        	$(".festivallist").hide();
        	$(".festivalNonelist").html(htmlContent);
        	$(".festivalNonelist").show();
        }else{
	        festivalList.forEach(function(item, index) {
	            htmlContent += `
				    <div class='festivalitem'>
				        <div class='itemImg' style='background-image: url(${item.f_firstimage});'>
				            <div class='hiddenItem'>
				                <div>${item.f_areaname} ${item.f_sigunguname}</div>
				                <p>${item.f_eventstartdate} ~ ${item.f_eventenddate}</p>
				                <button value="${item.f_contentid}">바로가기</button>
				            </div>
				        </div>
				        <div>${item.f_title}</div>
				    </div>`;
				contentid[index] = item.f_contentid;
	        });
	        
	        $(".festivalNonelist").hide();
        	$(".festivallist").html(htmlContent);
        	$(".festivallist").show();
	    }
	        
        if(festivalList.length < 5){ //이런축제는어때요 리스트 표기 조건
        	$(".recommend").show();
        	
        	$.ajax({
	            type:"get",
	            url:"http://localhost:9090/web/festival/getFestivalRandomList.do",
	            data:{ contentid: contentid },
	            headers: {"Accept": "application/json"},
	            success:function(recommendList){
	                recommendFestivalList(recommendList);
	            },
	            error:function(){
	                console.log("이런 축제는 어때요 리스트를 불러오는 데 실패했습니다.");
	            }
	        });
        }else{
        	$(".recommend").hide();
        }
	        
        $(".festivalitem > .itemImg > .hiddenItem > button").click(function(){
        	const contentid = $(this).val();
       		showModal(contentid);
        });
        
        festivalListHover();
    };
    
    
    //이런 축제는 어때요 리스트 띄우기
    function recommendFestivalList(recommendList){
    	let htmlContent = `
    		<div class="recTitle">이런 축제는 어때요?</div>
            <div class="recList">
    	`;
    	
    	recommendList.forEach(function(item) {
    		htmlContent += ` 
                    <div class="recItemImg" style='background-image: url(${item.f_firstimage});'>
                        <div>
                            <div class="recItemText">
                            	<div>
	                                <div>${item.f_title}</div>
	                                <div>${item.f_eventstartdate} ~ ${item.f_eventenddate}</div>
	                                <div>${item.f_areaname} ${item.f_sigunguname}</div>
                            	</div>
                            </div>
                            <button class="recItemText" value="${item.f_contentid}">
                                <i class="fa-solid fa-arrow-right"></i>
                            </button>
                        </div>
                    </div>
            `;
        });
        
        htmlContent += `</div>`;
        
        $(".recommend").html(htmlContent);
         
        //이런축제는어때요 크기
	    $(".recList > .recItemImg:eq(0)").css("width", "57%"); //첫번째 요소 크게
	    $(".recList > .recItemImg > div > .recItemText").hide();
	    $(".recList > .recItemImg:eq(0) .recItemText").show();
	
	    //이런축제는어때요 mouseover 효과
	    $(".recList > .recItemImg").mouseover(function(){
	        $(".recList > .recItemImg").css("width", "19%");
	        $(this).css("width", "57%");
	        $(".recList > .recItemImg .recItemText").hide();
			$(this).find(".recItemText").show();
	    });	
         
        $(".recList > .recItemImg > div > button").click(function(){
        	const contentid = $(this).val();
       		showModal(contentid);
        });
    };
    
    //모달에 html 태그 집어넣기
    function showFestivalModal(item){
    	let htmlContent = "";
   
   		htmlContent += `
		    <div class='festivalName'>
		        <h1>${item.f_title}</h1>
		        <button class='closeModal'>
		            <i class="fa-solid fa-xmark"></i>
		        </button>
		    </div>
		    <div class='modalDate'>
		        <p>${item.f_eventstartdate}</p>
		        <p>~</p>
		        <p>${item.f_eventenddate}</p>
		    </div>
		    <div class="modalStatus">
		`;
		
		//오늘 날짜 가져오기    
		const today = new Date();
		const TDate = today.toISOString().slice(0, 10).replace(/-/g, '');
	
		if(item.f_eventenddate < TDate){
			htmlContent += `<div class="modalIng">진행종료</div>`;
		}else if(item.f_eventstartdate > TDate){
			htmlContent += `<div class="modalIng">진행예정</div>`;
		}else{
			htmlContent += `<div class="modalIng">진행중</div>`;
		}

		htmlContent += `	                
            </div>
            <div class="modalMid">
        `;
                    
        //이미지 파일 가져오기
        const originimgurl = item.originimgurl;
        
        if(originimgurl.length > 3){
        	htmlContent += `
        		<button class="prevBtn">
                    <i class="fa-solid fa-angle-left"></i>
                </button>
        	`;
        }
        
        htmlContent += `
	        	<div class="modalImgContainer">
	                    <div class="modalImgs">
        `;
        
        for(let i=0; i<originimgurl.length; i++){
        	htmlContent += `
                <div class="modalImg" style="background-image: url('${originimgurl[i]}');"></div>
        	`;
        }	 
        
        htmlContent += `
	        		</div>
                </div>
        `;
        
        if(originimgurl.length > 3){
        	htmlContent += `
        		<button class="nextBtn">
                    <i class="fa-solid fa-angle-right"></i>
                </button>
        	`;
        }
                        
         htmlContent += `
            </div>
            <div class="modalContentBox">
                <p class="modalContent">${item.overview}</p>
                <button><i class="fa-solid fa-plus"></i> 더보기</button>
                <div class="modalContentPlus">
                    <div class="plusLeft">
                        <p>예매처 : ${item.bookingplace}</p>
                        <p>이용요금 : ${item.usetimefestival}</p>
                        <p>할인정보 : ${item.discountinfofestival}</p>
                    </div>
                    <div class="plusRight">
                        <p>행사홈페이지 : ${item.eventhomepage}</p>
                        <p>행사장위치안내 : ${item.f_addr1} ${item.f_addr2}</p>
                        <p>행사장소 : ${item.eventplace}</p>
                        <p>행사프로그램 : ${item.program}</p>
                    </div>
                </div>
            </div>
            <hr />
            <div class="modalMap">
                <h3>길찾기</h3>
                <div class="map"></div>
                <div class="mapContent">
                    <p>${item.f_addr1} ${item.f_addr2}</p>
                    <p>${item.sponsor1} / ${item.sponsor2}</p>
                    <p>${item.sponsor1tel}</p>
                </div>
            </div>`;	
            
        $("#festivalModal").html(htmlContent);
        
        modalImgSlide();
        
        initMap(item.f_mapx, item.f_mapy);
        
        //모달 닫기
        $(".closeModal").click(function(){
        	$("#festivalModal").hide();
            $("#modalOverlay").hide();
        });
    	
    };
    
    //지도 함수
    function initMap(mapx, mapy){
		let mapContainer = $(".map")[0]; // 지도를 표시할 div
		var mapOption = {
			center: new kakao.maps.LatLng(mapx, mapy), // 지도의 중심좌표
			level: 6 // 지도의 확대 레벨
		};

		// 지도 생성
		let map = new kakao.maps.Map(mapContainer, mapOption);

		// 마커 생성
		var markerPosition = new kakao.maps.LatLng(mapx, mapy);
		var marker = new kakao.maps.Marker({
			position: markerPosition
		});

		// 마커 지도에 추가
		marker.setMap(map);
    };
    
    
    //검색결과 없을 때 p태그
    $(".festivalNonelist>p:eq(0)").css({
    	fontSize: "25px",
        fontWeight: "bold"
    });
    
    
    
    //받아온 리스트 없을 때 기본적으로 숨기기
    $(".festivalNonelist").hide();
    
    //이런축제는 어때요 기본적으로 숨기기
    $(".recommend").hide();
    
    //modal 기본적으로 숨기기
    $("#festivalModal").hide();
    $("#modalOverlay").hide();
    
    //모달 보이기
    function showModal(contentid){
    	 setTimeout(function() { 
	            $("#festivalModal").show();
	            $("#modalOverlay").show();
			}, 500);
        window.scrollTo({
            top: 0,
            behavior: 'smooth' // 부드러운 스크롤
        });
    	
    	$.ajax({
            type:"get",
            url:"http://localhost:9090/web/festival/getFestival.do",
            data:{ contentid: contentid },
            headers: {"Accept": "application/json"},
            success:function(item){
                showFestivalModal(item);
            },
            error:function(){
                console.log("축제 상세보기를 불러오는 데 실패했습니다.");
            }
        });
    };
    
    $(".festivalitem > .itemImg > .hiddenItem > button , .recList > .recItemImg > div > button").click(function(){
    	const contentid = $(this).val();
       	showModal(contentid);
    });
    
    //모달 내 이미지 슬라이드 효과
    function modalImgSlide(){
	    let currentIndex = 0;
	    const totalImages = $('.modalImg').length-1;
	    const imageWidth = 200;
	
	    $(".prevBtn").click(() => {
	        currentIndex = (currentIndex > 0) ? currentIndex - 1 : totalImages - 1;
	        updateImagePosition();
	    });
	
	    $(".nextBtn").click(() => {
	        currentIndex = (currentIndex < totalImages - 1) ? currentIndex + 1 : 0;
	        updateImagePosition();
	    });
	    
	    function updateImagePosition() {
	        const offset = -currentIndex * imageWidth;
	        $('.modalImgs').css({
	            transform: `translateX(${offset}px)`,
	            transition: 'transform 0.3s ease' // 부드러운 이동 효과
	        });
	    }
    };
    modalImgSlide();
    
    
    //무한스크롤
    let page = 1;
    
    function MoreFestivalData() {
	    $.ajax({
	        type: "GET",
	        url: "http://localhost:9090/web/festival/getMoreFestivalData.do",
	        data: { page: page },
	        headers: {"Accept": "application/json"},
	        success: function (data) {
	        	console.log(data);
	            appendData(data);
	            page++;
	        },
	        error: function () {
	            console.log("추가 축제 리스트 데이터 로드 실패");
	        }
	    });
	    
	}
	
	// 스크롤 이벤트 리스너
	$(window).scroll(function () {
	    if ($(window).scrollTop() + $(window).height() >= $(document).height()
	    	&& $("#dateInput").val() == "" && $(".areaSelect").val() == "" && $("#searchText").val() == "") {
	        MoreFestivalData();
	    }
	});
	
	function appendData(data) {
		let htmlContent = "";
		data.forEach(function(item) {
			htmlContent += `
				<div class="festivalitem">
	                    <div class="itemImg" style="background-image: url(${ item.f_firstimage });">
	                        <div class="hiddenItem">
	                            <div>${ item.f_areaname } ${ item.f_sigunguname }</div>
	                            <p>${ item.f_eventstartdate } ~ ${ item.f_eventenddate }</p>
	                            <button value="${ item.f_contentid }">바로가기</button>
	                        </div>
	                    </div>
	                    <div>${ item.f_title }</div>
	                </div>
			`;
		});
		htmlContent += `</div>`;
		
		$(".festivallist").append(htmlContent);
		
		festivalListHover();
		
		$(".festivalitem > .itemImg > .hiddenItem > button").click(function(){
        	const contentid = $(this).val();
       		showModal(contentid);
		});
	}
            
});