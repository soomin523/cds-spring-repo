$(function(){
            //검색 선택창 초기화
            $(".selectRefresh").click(()=>{
                $("#dateInput").val("");
                $(".areaSelect").val("");
                $(".categorySelect").val("");
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
            
            
            //지역 선택에 다른 리스트 불러오기
        	$(".areaSelect").change(function() {
                const selectValue = $(this).val();
                console.log(selectValue);

                    $.ajax({
                        type:"get",
                        url:"http://localhost:9090/web/festival/getFestivalAreaSelectList.do",
                        data:{ areaCode: selectValue },
                        headers: {"Accept": "application/json"},
                        success:function(festivalList){
                            loadingFestivalList(festivalList);
                        },
                        error:function(){
                            console.log("지역 선택 리스트를 불러오는 데 실패했습니다.");
                        }
                    });
            });

        	//리스트 html에 표시하기
            function loadingFestivalList(festivalList){
                let htmlContent = "";
                
                festivalList.forEach(function(item) {
                    htmlContent += "<div class='festivalitem'>" +
                    "<div class='itemImg' style='background-image: url(" + item.f_firstimage + ");'>" +
                        "<div class='hiddenItem'>" +
                            "<div>" + item.f_areacode + " " + item.f_sigungucode + "</div>" +
                            "<p>" + item.f_eventstartdate + " ~ " + item.f_eventenddate + "</p>" +
                            "<button>바로가기</button>" +
                        "</div>" +
                    "</div>" +
                    "<div>" + item.f_title + "</div>" +
                    "</div>";
                });

                $(".festivallist").html(htmlContent);
                
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
            }
            
            
            //검색결과 없을 때 p태그
            $(".festivalNonelist>p:eq(0)").css({
            	fontSize: "25px",
                fontWeight: "bold"
            });
            
            
            //이런축제는어때요 랜덤 리스트 3개 뽑아오기
            
            
            
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