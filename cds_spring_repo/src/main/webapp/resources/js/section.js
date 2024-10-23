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
    const scrollStep = 210;
    
    // 왼쪽으로 스크롤 (맨 뒤의 요소가 첫 번째로 감)
	$(".imgSlider > .imgButton > .prevBtn").click(function() {
	    imgList.css("transition", "transform 0.5s ease");
	    imgList.css("transform", `translateX(${scrollStep}px)`);
	    
	    setTimeout(function () {
	        imgList.css("transition", "none");
	        imgList.prepend(imgList.children().last()); // 마지막 요소를 첫 번째로 이동
	        imgList.css("transform", "translateX(0)"); // 변환 초기화
	        
	        imgItemCss();
	    }, 500);
	});
	
	// 오른쪽으로 스크롤 (첫 번째 요소가 맨 뒤로 감)
	$(".imgSlider > .imgButton > .nextBtn").click(function() {
	    imgList.css("transition", "transform 0.5s ease");
	    imgList.css("transform", `translateX(-${scrollStep}px)`);
	    
	    setTimeout(function () {
	        imgList.css("transition", "none");
	        imgList.append(imgList.children().first()); // 첫 번째 요소를 마지막으로 이동
		    imgList.css("transform", "translateX(0)"); // 변환 초기화	 
		           
		    imgItemCss();
	    }, 500);
    });
    
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
    
  	//버튼 클릭에 따른 해당 카테고리 아이템 보여주기
    $(".mainCategory > .cateSelect > .selectBox > button").click(function(){
    	let select = $(this).val();
    	
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
    	location.href=`destination/destination.do`;
    });
    $(".mainCategory > .cateSelect > .festivalImg > div").click(function(){
    	location.href=`festival/getFestivalList.do`;
    });
    $(".mainCategory > .cateSelect > .productImg > div").click(function(){
    	location.href=`products/products.do`;
    });
    
    
    
//믿고보는 고객님의 찐리뷰
    
    
    
//여행콕콕 추천 코스

    $(".mainCourse > .courseImg > div:eq(0)").css("width", "50%"); //첫번째 요소 크게
    
  	//코스 리스트에 마우스 올리면 크기 변경
    $(".mainCourse > .courseImg > div").mouseover(function(){
	        $(".mainCourse > .courseImg > div").css("width", "12%");
	        $(this).css("width", "50%");
	});	
	
 	//코스 화면으로 이동
	$(".mainCourse > .courseImg > div").click(function(){
    	location.href=`tourCourse/Course.do`;
    });
    
    
    
//공지사항

  	//더보기 버튼 클릭 시 서포트 메인 화면 이동
    $(".mainSupport > .supportBox > .supportIntro > div > button").click(function(){
    	location.href=`support/support.do`;
    });
    
  	//공지사항 리스트 클릭 시 각 카테고리 화면 이동
    $(".mainSupport > .supportBox > .supportList > .supportItem").click(function(){
    	let select = $(this).data('category');
    
    	location.href=`support/supportDetail.do?select=${select}`;
    });
	
});
