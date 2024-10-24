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
	            
			    $("#commu-modalUserId").text(data.memberId); // 아이디
			    $("#commu-modalLocation").text("위치: " + data.location); // 위치
			    $("#commu-modalLikes").text(data.likes); // 좋아요 수
			    $("#commu-modalCommentsCount").text(data.comments); // 댓글 수
			    const createdAt = new Date(data.created_at); // 밀리초를 Date 객체로 변환
			    $("#commu-modalMeta").text("작성일: " + createdAt.toLocaleString()); // 원하는 형식으로 출력
			    $("#commu-modalTitle").text(data.title); // 제목
			    $("#commu-modalDescription").text(data.content); // 내용
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
	$("#commu-regionSearch").on("input", function() {
		let search = $("#commu-regionSearch").val();
	
    	$.ajax({ 
	        type:"get",
	        url:"/cds/community/getSearchList.do",
	        data:{ search: search },
	        //headers: {"Accept": "application/json"},
	        dataType: "json",
	        success:function(data){
	        	console.log("data:",data);
	            let htmls = ``;
	            data.forEach(function(search) {
	            	let createDate = new Date(search.created_at).toLocaleDateString();
                        htmls += `<div class="post-item" data-id="${search.c_idx}">
                            <div class="post-image" style="background-image: url('${search.imagePaths[0].imagePath}');">
	                            <p>작성자 : ${search.memberId}</p>
                            	<p>지역 : ${search.location}</p>
                            </div>
                            <p>제목: ${search.title}</p>
                            <div class="post-rating">
                                <span>⭐ ${ search.rating }</span>
                            </div>
                            
                            <div class="post-actions">
                                <span>👍 ${search.likes}</span>
                                <span>💬 ${search.comments}</span>
                            </div>
                            <p>
                            	작성일: createDate
                            </p>
                            
                        </div>`;
                    });
                    
                    $("#commu-postLis").html(htmls);
	            
	        },
	        error:function(){
	            console.log("커뮤니티 검색목록을 불러오는데 실패했습니다.");
	        }
	    });
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