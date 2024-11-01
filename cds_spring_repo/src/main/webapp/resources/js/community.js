//슬라이더 변수 선언
let currentSlide = 0;

$(function () {

	function getParameterByName(name) {
        let url = window.location.href;
        name = name.replace(/[\[\]]/g, '\\$&');
        let regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
            results = regex.exec(url);
        if (!results) return null;
        if (!results[2]) return '';
        return decodeURIComponent(results[2].replace(/\+/g, ' '));
    }
	
	let c_idx = getParameterByName('c_idx');
	if(!c_idx){
	closeModal();
	}
	
	console.log(c_idx);
	if (c_idx) {
        $.ajax({
            type: "get",
            url: "/cds/community/getcommunity.do",
            data: { id: c_idx }, // c_idx를 이용해 게시물의 ID를 가져옵니다
            headers: { "Accept": "application/json" },
            success: function(data) {
                $(".commu-comment-box").attr("data-idx", data.c_idx); // 게시물번호
                $("#commu-modalUserId").text(data.memberId); // 아이디
                $("#commu-modalLocation").text("위치: " + data.location); // 위치
                $("#commu-modalLikes").text(data.likeNum); // 좋아요 수
                $("#commu-modalCommentsCount").text(data.commentNum); // 댓글 수
                const createdAt = new Date(data.created_at);
                const formattedDate = createdAt.toISOString().slice(0, 10).replace(/-/g, '-');
                $("#commu-modalMeta").text("작성일: " + createdAt.toLocaleString());
                $("#commu-modalTitle").text(data.title); // 제목
                $("#commu-modalDescription").text(data.content); // 내용

                let htmlI = ''; // 이미지 목록
                data.attachedList.forEach(function(item) {
                    htmlI += `<img src="../resources/uploads/${item.save_filename}" alt="커뮤니티 이미지"/>`;
                });

                $(".commu-imageList").html(htmlI);
                images = data.attachedList;

                if (images.length == 1) {
                    $(".prev-slide").css("opacity", "0");
                    $(".next-slide").css("opacity", "0");
                } else {
                    $(".prev-slide").css("opacity", "1");
                    $(".next-slide").css("opacity", "1");
                }

                let htmls = ''; // 댓글 목록
                data.comments.forEach(function(comment) {
                    htmls += `<div class="commu-modal-comment">
                        <p class="comment-author"><strong>${comment.memberId}</strong></p>
                        <p class="comment-content">${comment.content}</p>
                        <div>
                            <p class="comment-date">${formattedDate.slice(2)}</p>
                            <button class="deleteBtn" value="${comment.comment_id}">❌</button>
                        </div>
                    </div>`;
                });

                $("#commu-modalComments").html(htmls);

                // 본인 게시글이 아니면 수정/삭제 버튼 숨기기
                if (memberId != data.memberId) {
                    $(".community-delete").hide();
                }

                // 댓글 삭제 기능 및 모달 열기
                $(".deleteBtn").click(function() {
                    let comment_id = $(this).val();
                    $.ajax({
                        type: "GET",
                        url: "/cds/community/deleteComment.do",
                        data: { comment_id: comment_id },
                        headers: { "Accept": "application/json" },
                        success: function(data) {
                            if (data == 1) {
                                location.href = `/cds/community/commu`;
                            } else {
                                alert("댓글 삭제 실패");
                            }
                        },
                        error: function() {
                            console.log("댓글 삭제 중 오류가 났습니다.");
                        }
                    });
                });

                // 모달 열기
                $("#commu-modal").show();
            },
            error: function() {
                console.log("커뮤니티 세부목록을 불러오는데 실패했습니다.");
            }
        });
    }
	
	
	
	let images = [];
	
	//게시물의 세부 정보를 모달 창에 표시하는 역할
	$(".post-item").click(function(){
		let id = $(this).data("id");
		let memberId = $(".commu-comment-box").data("name");
		
		$.ajax({ 
	        type:"get",
	        url:"/cds/community/getcommunity.do",
	        data:{ id: id },
	        headers: {"Accept": "application/json"},
	        success:function(data){
	            $(".commu-comment-box").attr("data-idx", data.c_idx); // 게시물번호
			    $("#commu-modalUserId").text(data.memberId); // 아이디
			    $("#commu-modalLocation").text("위치: " + data.location); // 위치
			    $("#commu-modalLikes").text(data.likeNum); // 좋아요 수
			    $("#commu-modalCommentsCount").text(data.commentNum); // 댓글 수
			    const createdAt = new Date(data.created_at); // 밀리초를 Date 객체로 변환
			    const formattedDate = createdAt.toISOString().slice(0, 10).replace(/-/g, '-'); // YYYY-MM-DD 형식으로 변환
			    $("#commu-modalMeta").text("작성일: " + createdAt.toLocaleString()); // 원하는 형식으로 출력
			    $("#commu-modalTitle").text(data.title); // 제목
			    $("#commu-modalDescription").text(data.content); // 내용
			  
			    let htmlI = ''; //이미지 목록
			    data.attachedList.forEach(function(item){
			    	htmlI += `<img src="../resources/uploads/${item.save_filename}" alt="커뮤니티 이미지"/>`;
			    });
			    
			    $(".commu-imageList").html(htmlI);
			    
			    
			    images = data.attachedList;
			    if(images.length == 1){
			    	$(".prev-slide").css("opacity", "0");
			    	$(".next-slide").css("opacity", "0");
			    }else{
			    	$(".prev-slide").css("opacity", "1");
			    	$(".next-slide").css("opacity", "1");
			    }
			    
			    let htmls = ''; //댓글목록
				data.comments.forEach(function(comment) {
				    htmls += `<div class="commu-modal-comment">
			            <p class="comment-author"><strong>${comment.memberId}</strong></p>
			            <p class="comment-content">${comment.content}</p>
			            <div>
				            <p class="comment-date">${formattedDate.slice(2)}</p>
				       	    <button class="deleteBtn" value="${comment.comment_id}">❌</button>
			       	    </div>       
			        </div>`;
				});
				
				$("#commu-modalComments").html(htmls);
				
				//본인 게시글이 아니면 수정/삭제가 뜨지 않게하기
				if(memberId != data.memberId){
					$(".community-delete").hide();
				}
				
				// 각 댓글에 대해 삭제 버튼 숨기기
				data.comments.forEach(function(comment, index) {
				    if (memberId != comment.memberId) {
				        $(`.deleteBtn:eq(${index})`).hide(); // 수정된 선택자
				}
				    
			});
				
				//댓글 삭제 기능
				$(".deleteBtn").click(function(){
					let comment_id = $(this).val(); //게시물번호
					$.ajax({
			            type: "GET",
			            url: "/cds/community/deleteComment.do",
			            data: { comment_id: comment_id },
			            headers: { "Accept": "application/json" },
			            success: function(data) {
			            	if(data == 1){
				            	location.href=`/cds/community/commu`;
			            	}else{
			            		alart("댓글 삭제 실패");
			            	}
			            },
			            error: function() {
			                console.log("댓글 삭제 중 오류가 났습니다.");
			            }
			        });
				});
				
	        },
	        error:function(){
	            console.log("커뮤니티 세부목록을 불러오는데 실패했습니다.");
	        }
	    });
		
		// 모달 열기
	    $("#commu-modal").show();
	});
	
	//모달 내 이미지 슬라이더 추가
	// 슬라이더 위치 업데이트 함수
	function updateSlidePosition() {
	    const imageList = $(".commu-imageList");
	    imageList.css("transform", `translateX(-${currentSlide * 100}%)`);
	};
	
	// 이전 슬라이드로 이동
	$(".prev-slide").click(function(){
	    if (currentSlide > 0) {
	        currentSlide--;
	    } else {
	        currentSlide = images.length - 1; // 처음 슬라이드에서 마지막으로 이동
	    }
	    updateSlidePosition();
	});
	
	// 다음 슬라이드로 이동
	$(".next-slide").click(function(){
	    if (currentSlide < images.length - 1) {
	        currentSlide++;
	    } else {
	        currentSlide = 0; // 마지막 슬라이드에서 처음으로 이동
	    }
	    updateSlidePosition();
	});
	
	//모달 닫기 기능
	function closeModal() {
		$("#commu-modal").hide();
		currentSlide = 0; // 슬라이드 인덱스 초기화
    	updateSlidePosition(); // 슬라이드 위치 초기화
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
		
		console.log(area);
	
		location.href=`commupost.do?location=${area}&select=${select}`;
	});
	
	//검색 기능
	$("#commu-regionSearch").on("keypress", function(event) {
	    if (event.key === "Enter") { // Enter 키 확인
	        let search = $("#commu-regionSearch").val();
	        location.href = `getSearchList.do?search=${search}`;
	    }
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
	            type: "GET",
	            url: "/cds/community/insertComment.do",
	            data: { memberId: memberId, content: content, c_idx: c_idx },
	            headers: { "Accept": "application/json" },
	            success: function(data) {
	            	const createdAt = new Date(data.created_at);
	            	const formattedDate = createdAt.toISOString().slice(0, 10).replace(/-/g, '-'); // YYYY-MM-DD 형식으로 변환
	                let htmls = `<div class="commu-modal-comment">
				            <p class="comment-author"><strong>${data.memberId}</strong></p>
				            <p class="comment-content">${data.content}</p>
				            <p class="comment-date">${formattedDate.slice(2)}</p>
				            <button class="deleteBtn" value="${data.comment_id}">❌</button>

				        </div>`;
	                $("#commu-modalComments").append(htmls);
	
	                // 텍스트박스 초기화
	                $("#commu-commentText").val("");
	
	                // 스크롤을 아래로 이동
	                const commentsDiv = $("#commu-modalComments");
	                commentsDiv.scrollTop(commentsDiv.prop("scrollHeight"));
	                
	                // 댓글 삭제 기능
		            $(".deleteBtn").click(function() {
		                let comment_id = $(this).val();
		                $.ajax({
		                    type: "GET",
		                    url: "/cds/community/deleteComment.do",
		                    data: { comment_id: comment_id },
		                    headers: { "Accept": "application/json" },
		                    success: function(data) {
		                        if (data == 1) {
		                            location.href = `/cds/community/commu`;
		                        } else {
		                            alert("댓글 삭제 실패");
		                        }
		                    },
		                    error: function() {
		                        console.log("댓글 삭제 중 오류가 났습니다.");
		                    }
		                });
		            });
	            },
	            error: function() {
	                console.log("댓글을 입력하는데 오류가 났습니다.");
	            }
	            
	            
	        });
	        
	    }
	});
	
	//좋아요 기능
	$(".commu-likeBtn").click(function() {
		let c_idx = $(".commu-comment-box").data("idx");
		let memberId = $(".commu-comment-box").data("name");
		
		if (!memberId) {
	        alert("로그인이 필요한 기능입니다.");
	    } else {
	        $.ajax({
	            type: "GET",
	            url: "/cds/community/insertLike.do",
	            data: { memberId: memberId, c_idx: c_idx },
	            headers: { "Accept": "application/json" },
	            success: function(data) {
	            	$("#commu-modalLikes").text(data);
	            },
	            error: function() {
	                console.log("좋아요누르는데 오류가 났습니다.");
	            }
	        });
	    }
	
	});
	
	//게시물 수정 기능
	$("#commu-Edit-Btn").click(function(){
		let c_idx = $(".commu-comment-box").data("idx"); //게시물번호
		
		location.href=`/cds/community/updatePage.do?c_idx=${c_idx}`;
		
	});
	
	//게시물 삭제 기능
	$("#commu-delete-Btn").click(function(){
		let c_idx = $(".commu-comment-box").data("idx"); //게시물번호
		$.ajax({
            type: "GET",
            url: "/cds/community/deletePost.do",
            data: { c_idx: c_idx },
            headers: { "Accept": "application/json" },
            success: function(data) {
            	if(data == 1){
	            	location.href=`/cds/community/commu`;
            	}else{
            		alart("게시물 삭제 실패");
            	}
            },
            error: function() {
                console.log("게시물 삭제 중 오류가 났습니다.");
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