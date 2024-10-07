$(function(){
        //검색 선택창 초기화
        $(".selectRefresh").click(()=>{
            $("#dateInput").val("");
            $(".areaSelect").val("");
            $(".categorySelect").val("");
            
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
            // 클릭한 버튼의 배경색을 토글
            if (!isColor) {
                $(this).css("backgroundColor", "#87bee541");
            }
        });
        
        
        //축제 리스트 hover 효과
        $(".festivalitem > .itemImg > .hiddenItem").hide();
        $(".festivalitem > .itemImg").hover(
            function(){
                $(this).find(".hiddenItem").show();
            },
            function(){
                $(this).find(".hiddenItem").hide();
            }
        );
        
        
        //지역 선택에 따른 리스트 불러오기
    	$(".areaSelect").change(function() {
            const selectarea = $(this).val();
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
                    console.log("지역 선택 리스트를 불러오는 데 실패했습니다.");
                }
            });

        });
        
        //날짜 선택에 따른 리스트 불러오기
        $("#dateInput").change(function() {
        	const selectarea = $(".areaSelect").val();
            const selectValue = $(this).val();
            const selectDate = selectValue.replace(/-/g, '');

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

    	//축제 리스트 html에 표시하기
        function loadingFestivalList(festivalList){
            let htmlContent = "";
            
            festivalList.forEach(function(item) {
                htmlContent += "<div class='festivalitem'>" +
                "<div class='itemImg' style='background-image: url(" + item.f_firstimage + ");'>" +
                    "<div class='hiddenItem'>" +
                        "<div>" + item.f_areaname + " " + item.f_sigunguname + "</div>" +
                        "<p>" + item.f_eventstartdate + " ~ " + item.f_eventenddate + "</p>" +
                        "<button value=" + item.f_contentid + ">바로가기</button>" +
                    "</div>" +
                "</div>" +
                "<div>" + item.f_title + "</div>" +
                "</div>";
            });
            
            if(festivalList.length < 5){
            	$(".recommend").show();
            }else{
            	$(".recommend").hide();
            }

            $(".festivallist").html(htmlContent);
            
            $(".festivalitem > .itemImg > .hiddenItem > button").click(function(){
                $("#festivalModal").show();
                $("#modalOverlay").show();
                window.scrollTo({
                    top: 0,
                    behavior: 'smooth' // 부드러운 스크롤
                });
                const contentid = $(this).val();
	        	
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
                
            });
            
            //축제 리스트 hover 효과
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
        
        
        //모달 띄울 때 안의 콘텐츠 가져오기
        $(".festivalitem > .itemImg > .hiddenItem > button , .recList > .recItemImg > div > button").click(function(){
        	const contentid = $(this).val();
        	
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
        	
        });
        
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
            
            //모달 내 이미지 슬라이드 효과
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
	        
	        //모달 닫기
	        $(".closeModal").click(function(){
	        	$("#festivalModal").hide();
	            $("#modalOverlay").hide();
	        });
        	
        };
        
        
        //검색결과 없을 때 p태그
        $(".festivalNonelist>p:eq(0)").css({
        	fontSize: "25px",
            fontWeight: "bold"
        });
        
        
        //이런축제는어때요 랜덤 리스트 3개 뽑아오기
        
        //이런축제는 어때요 기본적으로 숨기기
        $(".recommend").hide();
        
        //이런축제는어때요 크기
        $(".recList > .recItemImg:eq(0)").css("width", "57%"); //첫번째 요소 크게
        $(".recList > .recItemImg .recItemText").hide();
        $(".recList > .recItemImg:eq(0) .recItemText").show();

        //이런축제는어때요 mouseover 효과
        $(".recList > .recItemImg").mouseover(function(){
            $(".recList > .recItemImg").css("width", "19%");
            $(this).css("width", "57%");
            $(".recList > .recItemImg .recItemText").hide();
			$(this).find(".recItemText").show();
        });
        
        
        //modal
        $("#festivalModal").hide();
        $("#modalOverlay").hide();
        
        $(".festivalitem > .itemImg > .hiddenItem > button , .recList > .recItemImg > div > button").click(function(){
            $("#festivalModal").show();
            $("#modalOverlay").show();
            window.scrollTo({
                top: 0,
                behavior: 'smooth' // 부드러운 스크롤
            });
        });

        $(".closeModal").click(function(){
        	$("#festivalModal").hide();
            $("#modalOverlay").hide();
        });
        
        
        //모달 내 이미지 슬라이드 효과
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
            
});