$(function () {
    
    //supportDetail 페이지 이동
    $(".select > button").click(function(){
    	let select = $(this).val();
    	
    	if(select == "oneByone"){
    		location.href=`http://pf.kakao.com/_LcpIn`;
    	}else{
    		location.href=`supportDetail.do?select=${select}`;
    	}
    });
    
	//로그인하기로 넘어가는 버튼 클릭
	$(".login").click(function(){
		location.href="../member/login.do";
	});
	
	//1:1 오픈채팅 버튼 클릭
	$(".one").click(function(){
		console.log("문의하기 버튼 클릭");
		location.href="http://pf.kakao.com/_LcpIn";
	});
	
	//support에서 각 아이템 누르면 supportDetail로 넘어가기
	$(".select > .items > .item").click(function(){
		let select = $(this).data('category');
		location.href=`supportDetail.do?select=${select}`;
	});
    
    //supportDetail에서 support 메인페이지 이동
    $("aside > h2").click(function(){
    	location.href="support.do";
    });
    
    //게시글 toggle
    $(".contentTitle").click(function(){
        $(this).next().toggle();
        $(this).find("i").toggle();
    });
    $(".contentText").hide();
    $(".fa-angle-up").hide();
    
 
    //모달창 숨김
	function modalHide(){
	    $("#modalOverlay").hide();
	    $(".newContentPage").hide();
	    $(".updateContentPage").hide();	
	};
	modalHide();
    
    //새 글 쓰기 클릭
    $(".newContent > button").click(function(){
    	contentReset();
    
    	$("#modalOverlay").show();
    	$(".newContentPage").show();
    	
    	window.scrollTo({
            top: 0,
            behavior: 'smooth' // 부드러운 스크롤
        });
    });
    
    //글 쓰기 및 수정 취소 버튼 클릭
    $("input[type=button]").click(function(){
    	contentReset();
    	
    	modalHide();
    });
    
    //ESC 버튼 클릭 시 모달 닫힘
    $(document).keydown(function(event) {
	    if (event.key === "Escape") {
	        modalHide();
	    }
	});
    
    //새 글 쓰기 리셋 버튼 클릭
    $("input[type=reset]").click(function(){
    	contentReset();
    });
    
    function contentReset(){
    	$("input[name=s_title]").val("");
    	$("select").val("notice");
    	$("textarea").val("");
    };
    
    //새 글 쓰기 등록 버튼 클릭
    $("form[name='newContent']").on("submit", function(event) {
		event.preventDefault(); // 기본 제출 방지
		const select = $("select").val();
		
		$.ajax({
		    type: "POST",
		    url: $(this).attr("action"),
		    data: $(this).serialize(),
		    success: function(response) {
		    	if(response === "success"){
		        location.href=`supportDetail.do?select=${select}`;
		    	}else{
		    		console.log("등록에 실패했습니다.");
		    	}
		    },
		    error: function() {
		        console.log("입력 폼 제출에 실패했습니다.");
		    }
		});
	});
	
	//수정하기 버튼 클릭
	$(".contentText > div > div > .update").click(function(){
		const s_idx = $(this).data("sidx");
		
		$.ajax({ 
            type:"get",
            url:"http://localhost:9090/cds/support/getsupport.do",
            data:{ s_idx: s_idx },
            headers: {"Accept": "application/json"},
            success:function(support){
            	$("input[name='s_idx']").val(s_idx);
                $("input[name='s_writer']").val(support.s_writer);
            	$("select[name='s_category']").val(support.s_category);
	            $("input[name='s_title']").val(support.s_title);
	            $("textarea[name='s_content']").val(support.s_content);
            },
            error:function(){
                console.log("수정할 고객센터 글을 불러오는 데 실패했습니다.");
            }
        });
		
		$("#modalOverlay").show()
	    $(".updateContentPage").show();
	    
	    window.scrollTo({
            top: 0,
            behavior: 'smooth' // 부드러운 스크롤
        });
		
	});
	
	//게시글 수정 후 수정 버튼 클릭
	$("form[name='updateContent']").on("submit", function(event) {
		event.preventDefault(); // 기본 제출 방지
		const select = $("select").val();
		$.ajax({
		    type: "POST",
		    url: $(this).attr("action"),
		    data: $(this).serialize(),
		    success: function(response) {
		    	if(response === "success"){
		        location.href=`supportDetail.do?select=${select}`;
		    	}else{
		    		console.log("수정에 실패했습니다.");
		    	}
		    },
		    error: function() {
		        console.log("수정 폼 제출에 실패했습니다.");
		    }
		});
	});
	
	//삭제하기 버튼 클릭
	$(".contentText > div > div > .delete").click(function(){
	    const s_idx = $(this).data("sidx");
	    
	    const ans = confirm("정말 삭제하시겠습니까?");
	    if(ans){
	        location.href=`deleteSupport.do?s_idx=${s_idx}`;
	    }
	});
	
	
});
