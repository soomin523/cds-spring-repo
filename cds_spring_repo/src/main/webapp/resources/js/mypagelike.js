
//ajax
/*
document.addEventListener("DOMContentLoaded", function() {
    document.getElementById("redirectButton").addEventListener("click", function() {
        // AJAX 요청
        var xhr = new XMLHttpRequest();
        xhr.open("GET", "mypagelike_course.do", true);
        xhr.onload = function() {
            if (xhr.status === 200) {
                //window.history.pushState({}, '', xhr.responseText);
                //console.log("response: ", xhr.responseText);
                window.location.href= xhr.responseText.trim();
            } else {
                console.error("Error: " + xhr.status);
            }
        };
        xhr.send();  
    });
});
*/

//jQuery를 이용한 ajax 처리 //코스
//$(function(){ //window.onload 이벤트 처리 구문 })
$(function(){

	//선택자가 redirectButton인 HTML요소를 jQuery객체로 만듬
	//on("이벤트명", 이벤트처리함수): 이벤트 처리 함수
	$("#redirectButton_course").on("click", function(){
	
		//$.ajax(): jQuery의 ajax처리 함수
		$.ajax({
			type: "GET", //요청방식
		    url: "mypagelike_course.do", //서버로 요청하는 URL
		    data: null, //서버로 보내는 데이터
		    success: function(data){ //요청 응답이 성공한 경우, data: 서버로부터 받은 응답내용
		    	console.log(data);
		    	window.location.href= data.trim();
		    	
		    }, 
		    error: function(error){ //요청 응답 실패한 경우, error: 에러 객체
		    	console.log("error 발생: ", error);
		    }
		
		});//end of ajax
	
	});

});

//jQuery를 이용한 ajax 처리 //축제
//$(function(){ //window.onload 이벤트 처리 구문 })
$(function(){

	//선택자가 redirectButton인 HTML요소를 jQuery객체로 만듬
	//on("이벤트명", 이벤트처리함수): 이벤트 처리 함수
	$("#redirectButton_festival").on("click", function(){
	
		//$.ajax(): jQuery의 ajax처리 함수
		$.ajax({
			type: "GET", //요청방식
		    url: "mypagelike_festival.do", //서버로 요청하는 URL
		    data: null, //서버로 보내는 데이터
		    success: function(data){ //요청 응답이 성공한 경우, data: 서버로부터 받은 응답내용
		    	console.log(data);
		    	window.location.href= data.trim();
		    	
		    }, 
		    error: function(error){ //요청 응답 실패한 경우, error: 에러 객체
		    	console.log("error 발생: ", error);
		    }
		
		});//end of ajax
	
	});

});

//jQuery를 이용한 ajax 처리 //상품
//$(function(){ //window.onload 이벤트 처리 구문 })
$(function(){

	//선택자가 redirectButton인 HTML요소를 jQuery객체로 만듬
	//on("이벤트명", 이벤트처리함수): 이벤트 처리 함수
	$("#redirectButton_product").on("click", function(){
	
		//$.ajax(): jQuery의 ajax처리 함수
		$.ajax({
			type: "GET", //요청방식
		    url: "mypagelike_product.do", //서버로 요청하는 URL
		    data: null, //서버로 보내는 데이터
		    success: function(data){ //요청 응답이 성공한 경우, data: 서버로부터 받은 응답내용
		    	console.log(data);
		    	window.location.href= data.trim();
		    	
		    }, 
		    error: function(error){ //요청 응답 실패한 경우, error: 에러 객체
		    	console.log("error 발생: ", error);
		    }
		
		});//end of ajax
	
	});

});


document.addEventListener("DOMContentLoaded", function () {
    const sliderContainers = document.querySelectorAll(".mylike-random-festival, .mylike-random-destination");

    sliderContainers.forEach(container => {
        let isDown = false;
        let startX;
        let scrollLeft;

        // 마우스를 누르면 슬라이더 활성화
        container.addEventListener("mousedown", (e) => {
            isDown = true;
            container.classList.add("active");
            startX = e.pageX - container.offsetLeft;
            scrollLeft = container.scrollLeft;
            e.preventDefault();
        });

        // 마우스를 떼면 슬라이더 비활성화
        container.addEventListener("mouseleave", () => {
            isDown = false;
            container.classList.remove("active");
        });

        container.addEventListener("mouseup", () => {
            isDown = false;
            container.classList.remove("active");
        });

        // 마우스 움직임에 따라 슬라이더 이동
        container.addEventListener("mousemove", (e) => {
            if (!isDown) return; // 마우스를 누르고 있지 않다면 실행 중지
            e.preventDefault();
            const x = e.pageX - container.offsetLeft;
            const walk = (x - startX) * 2; // 슬라이드 속도 조절
            container.scrollLeft = scrollLeft - walk;
        });

        // 터치 시작 시 슬라이더 활성화 (모바일 지원)
        container.addEventListener("touchstart", (e) => {
            isDown = true;
            startX = e.touches[0].pageX - container.offsetLeft;
            scrollLeft = container.scrollLeft;
        });

        // 터치가 끝나면 슬라이더 비활성화
        container.addEventListener("touchend", () => {
            isDown = false;
        });

        // 터치 이동에 따라 슬라이더 이동
        container.addEventListener("touchmove", (e) => {
            if (!isDown) return;
            const x = e.touches[0].pageX - container.offsetLeft;
            const walk = (x - startX) * 2;
            container.scrollLeft = scrollLeft - walk;
        });
    });
});