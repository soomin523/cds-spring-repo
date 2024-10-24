$(function () {
	
	closeModal();
	
	//게시물의 세부 정보를 모달 창에 표시하는 역할
	$(".post-item").click(function(){
		let id = $(this).data("id");
		
		$.ajax({ 
	        type:"get",
	        url:"/cds/community/getcommunity.do",
	        data:{ id: id },
	        headers: {"Accept": "application/json"},
	        success:function(data){
	            
	            $(".commu-comment-box").attr("data-idx", data.c_idx); // 게시물번호
			    $("#commu-modalUserId").text(data.memberId); // 아이디
			    $("#commu-modalLocation").text("위치: " + data.location); // 위치
			    $("#commu-modalCommentsCount").text(data.commentNum); // 댓글 수
			    const createdAt = new Date(data.created_at); // 밀리초를 Date 객체로 변환
			    $("#commu-modalMeta").text("작성일: " + createdAt.toLocaleString()); // 원하는 형식으로 출력
			    $("#commu-modalTitle").text(data.title); // 제목
			    $("#commu-modalDescription").text(data.content); // 내용
			    
			    let htmls = ''; //댓글목록
				data.comments.forEach(function(comment) {
				    htmls += `<p>${comment.memberId} ${comment.content} ${comment.created_at}</p>`;
				});
				$("#commu-modalComments").html(htmls);
	        },
	        error:function(){
	            console.log("커뮤니티 세부목록을 불러오는데 실패했습니다.");
	        }
	    });
		
		// 모달 열기
	    $("#commu-modal").show();
	});
	
	//모달 닫기 기능
	function closeModal() {
		$("#commu-modal").hide();
	}
	
	$(".commu-modal-close").click(function(){
		closeModal();
	});
	
	//지역별 게시물 보기
	$(".commu-button-container > .commu-button").click(function(){
		let area = $(this).val();
		
		location.href=`getLocationList.do?location=${area}`;
	});
	
	//최신순, 평점순 게시물 보기
	$(".commu-sort-options > .commu-sort-button").click(function(){
		let select = $(this).val();
		let area = $(".commu-sort-options").data("location");
	
		location.href=`commupost.do?location=${area}&select=${select}`;
	});
	
	//검색 기능
	$("#commu-regionSearch").on("keypress", function(event) {
   		let search = $("#commu-regionSearch").val();
   		
   		location.href=`getSearchList.do?search=${search}`;
	});
	
	// 댓글 기능
$("#commentSubmitBtn").click(function() {
    let c_idx = $(".commu-comment-box").data("idx");
    let memberId = $(".commu-comment-box").data("name");
    let content = $("#commu-commentText").val();

    if (!memberId) {
        alert("로그인이 필요한 기능입니다.");
    } else {
        $.ajax({
            type: "GET", // POST 방식으로 변경
            url: "/cds/community/insertComment.do",
            data: { memberId: memberId, content: content, c_idx: c_idx },
            headers: { "Accept": "application/json" },
            success: function(data) {
                let htmls = `<p><strong>${data.memberId}:</strong> ${data.content} <em>${data.created_at}</em></p>`;
                $("#commu-modalComments").append(htmls);

                // 텍스트박스 초기화
                $("#commu-commentText").val("");

                // 스크롤을 아래로 이동
                $("#commu-modalComments").scrollTop($("#commu-modalComments")[0].scrollHeight);
            },
            error: function() {
                console.log("댓글을 입력하는데 오류가 났습니다.");
            }
        });
    }
});

	
	
	var currentPage = 1;
	var postsPerPage = 12;
	
	//게시물 목록 페이지네이션 버튼
	function displayPagination(filteredPosts) {
	    var paginationDiv = document.getElementById("commu-pagination");
	    paginationDiv.innerHTML = "";
	    var totalPages = Math.ceil(filteredPosts.length / postsPerPage);
	
	    for (var i = 1; i <= totalPages; i++) {
	        var button = document.createElement("button");
	        button.innerText = i;
	        if (i === currentPage) {
	            button.className = "commu-active";
	        }
	        button.addEventListener("click", function () {
	            currentPage = parseInt(this.innerText);
	            displayPosts(currentPage, filteredPosts);
	        });
	        paginationDiv.appendChild(button);
	    }
	};
	
    
});