$(function () {

//어디로 갈지 고민된다면 떠나자에서

  	//더보기 버튼 클릭 시 관광지 페이지 이동
	$(".mainTop > .topIntro > button").click(function(){
		location.href=`destination/destination.do`;
	});
	//이미지 아이템 클릭 시 관광지 페이지 이동
	$(".imgSlider > .imgContainer > .imgList > .imgItem").click(function(){
		let areacode = $(this).data("areacode");
		location.href=`destination/destination.do?areacode=${areacode}`;
	});
	
	const imgList = $(".imgSlider > .imgContainer > .imgList");
    const scrollStep = 210; //움직이는 width 값
    let autoScroll; //타이머
    
    //왼쪽으로 스크롤 (맨 뒤의 요소가 첫 번째로 감)
	$(".imgSlider > .imgButton > .prevBtn").click(function() {
	    imgList.css("transition", "transform 0.5s ease");
	    imgList.css("transform", `translateX(${scrollStep}px)`);
	    
	    setTimeout(function () {
	        imgList.css("transition", "none");
	        imgList.prepend(imgList.children().last()); //마지막 요소를 첫 번째로 이동
	        imgList.css("transform", "translateX(0)"); //변환 초기화
	        
	        imgItemCss();
	    }, 500);
	    
	    //타이머
	    clearInterval(autoScroll); //기존 타이머 클리어
    	startAutoScroll(); //새로 시작
	});
	
	//오른쪽으로 스크롤 (첫 번째 요소가 맨 뒤로 감)
	$(".imgSlider > .imgButton > .nextBtn").click(function() {
	    imgList.css("transition", "transform 0.5s ease");
	    imgList.css("transform", `translateX(-${scrollStep}px)`);
	    
	    setTimeout(function () {
	        imgList.css("transition", "none");
	        imgList.append(imgList.children().first()); //첫 번째 요소를 마지막으로 이동
		    imgList.css("transform", "translateX(0)"); //변환 초기화	 
		           
		    imgItemCss();
	    }, 500);
	    
	    //타이머
	    clearInterval(autoScroll); //기존 타이머 클리어
    	startAutoScroll(); //새로 시작
    });
    
    function startAutoScroll() {
	    autoScroll = setInterval(function() {
	        $(".imgSlider > .imgButton > .nextBtn").click();
	    }, 3500); // 3.5초
	}
	startAutoScroll();  
    
    
    //관광지 리스트 글씨, 첫번째 요소 css
    function imgItemCss(){
		imgList.children().find("h4").show();
	    imgList.children().find("div").css({
		    "height": "200px",
		    "width": "170px"
		});
	    imgList.children().find("h3").hide();
    	
    	const firstItem = imgList.children().first();
	  	firstItem.find("h4").hide();
		firstItem.find("div").css({
		    "height": "300px",
		    "width": "200px"
		});
		firstItem.find("h3").show();
    };
    imgItemCss();
    


//대한민국 추천 여행

	$(".mainCategory > .cateSelect > .festivalImg").hide();
    $(".mainCategory > .cateSelect > .productImg").hide();
    $(".mainCategory > .cateSelect > .selectBox > button:first-child").css("background-color", "#87bee585");
    
  	//버튼 클릭에 따른 해당 카테고리 아이템 보여주기
    $(".mainCategory > .cateSelect > .selectBox > button").click(function(){
    	let select = $(this).val();
    	$(".mainCategory > .cateSelect > .selectBox > button").css("background-color", "#fff0");
    	$(this).css("background-color", "#87bee585");
    	
    	$(".mainCategory > .cateSelect > .destinationImg, .mainCategory > .cateSelect > .festivalImg, .mainCategory > .cateSelect > .productImg").hide();
    	
    	switch(select){
    		case "destination":
    			$(".mainCategory > .cateSelect > .destinationImg").show();
    			break;
    		case "festival":
    			$(".mainCategory > .cateSelect > .festivalImg").show();
    			break;
    		case "product":
    			$(".mainCategory > .cateSelect > .productImg").show();
    			break;
    	}
    });
    
	//아이템 클릭 시 해당 페이지로 이동
    $(".mainCategory > .cateSelect > .destinationImg > div").click(function(){
    	let contentid = $(this).data("contentid");
    	let areacode = $(this).data("areacode");
    	location.href=`destination/destination.do?areacode=${areacode}&contentid=${contentid}`;
    });
    $(".mainCategory > .cateSelect > .festivalImg > div").click(function(){
    	let contentid = $(this).data("contentid");
    	location.href=`festival/getFestivalList.do?contentid=${contentid}`;
    });
    $(".mainCategory > .cateSelect > .productImg > div").click(function(){
    	let contentid = $(this).data("contentid");
    	location.href=`products/products.do?contentid=${contentid}`;
    });
    
    
    
//믿고보는 고객님의 찐리뷰

	//커뮤니티 슬라이드
	const totalItems = $('.reviewItem').length;
    let currentIndex = 0;
    const itemWidth = 190; // 아이템의 너비
    
    $('#nextBtn').click(function() {
        if (currentIndex < totalItems - 3) {
            currentIndex++;
            updateSlider();
        }
    });

    $('#prevBtn').click(function() {
        if (currentIndex > 0) {
            currentIndex--;
            updateSlider();
        }
    });

    function updateSlider() {
        const offset = -currentIndex * itemWidth; // 현재 인덱스에 따른 오프셋 계산
        $('.reviewList').css('transform', 'translateX(' + offset + 'px)');
    }
    
	//각 아이템 클릭 시 여행공유 페이지 이동 + 모달 띄우기
	$(".reviewList > .reviewItem").click(function(){
		let c_idx = $(this).data("c_idx");
		location.href=`community/commu?c_idx=${c_idx}`;
	});

    //더보기 버튼 클릭 시 여행공유 페이지 이동
	$(".mainReview > button").click(function(){
		location.href=`community/commu`;
	});
    
    
    
//여행콕콕 추천 코스

    $(".mainCourse > .courseImg > div:eq(0)").css("width", "50%"); //첫번째 요소 크게
    
  	//코스 리스트에 마우스 올리면 크기 변경
    $(".mainCourse > .courseImg > div").mouseover(function(){
        $(".mainCourse > .courseImg > div").css("width", "12%");
        $(this).css("width", "50%");
	});	
	
 	//코스 화면으로 이동
	$(".mainCourse > .courseImg > div").click(function(){
		let contentid = $(this).data("contentid");
    	location.href=`tourCourse/Course.do?contentId=${contentid}`;
    });
    
    
    
//공지사항

  	//더보기 버튼 클릭 시 서포트 메인 화면 이동
    $(".mainSupport > .supportBox > .supportIntro > div > button").click(function(){
    	location.href=`support/support.do`;
    });
    
  	//공지사항 리스트 클릭 시 각 카테고리 화면 이동
    $(".mainSupport > .supportBox > .supportList > .supportItem").click(function(){
    	let select = $(this).data('category');
    	let s_idx = $(this).data('s_idx');
    
    	location.href=`support/supportDetail.do?select=${select}&s_idx=${s_idx}`;
    });
	
});
