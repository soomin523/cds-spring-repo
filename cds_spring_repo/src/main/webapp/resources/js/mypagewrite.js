//jQuery를 이용한 ajax 처리 //작성글
//$(function(){ //window.onload 이벤트 처리 구문 })
$(function(){

	//선택자가 redirectButton인 HTML요소를 jQuery객체로 만듬
	//on("이벤트명", 이벤트처리함수): 이벤트 처리 함수
	$("#redirectButton_post").on("click", function(){
	
		//$.ajax(): jQuery의 ajax처리 함수
		$.ajax({
			type: "GET", //요청방식
		    url: "mypagewrite_post.do", //서버로 요청하는 URL
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

//ajax컨트롤러 //댓글
$(function(){

	//선택자가 redirectButton인 HTML요소를 jQuery객체로 만듬
	//on("이벤트명", 이벤트처리함수): 이벤트 처리 함수
	$("#redirectButton_comment").on("click", function(){
	
		//$.ajax(): jQuery의 ajax처리 함수
		$.ajax({
			type: "GET", //요청방식
		    url: "mypagewrite_comment.do", //서버로 요청하는 URL
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