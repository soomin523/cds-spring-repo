$(function() {

	$(document).on('click','.comment-card', function(){
		let contentId=$(this).data('contentid');
		
			window.location.href='/cds/tourCourse/Course.do?contentId='+contentId;
									
	});





    $('#redirectButton_comment').on('click', function() {
        $('.mywrite-comment-none').css('display', 'none');
        $('.mywrite-comment-exist').css('display', 'flex');
        $('.mywrite-content-none').css('display', 'none');
        $('.mywrite-content-exist').css('display', 'none');
        
        // AJAX 요청
        $.ajax({
            url: '/cds/mypage/mypagewrite_comment.do',
            type: 'POST',
            dataType: 'json',
            success: function(data) {
                $('#comment-list').empty(); // 기존 댓글 비움
                
                // 댓글 목록을 카드 형식으로 추가
                if (data.length === 0) {
                    // 댓글이 없을 경우 "작성한 댓글이 없습니다." 메시지 추가
                    $('#comment-list').append('<p class="no-comments">작성한 댓글이 없습니다.</p>');
                } else {
                    // 댓글 목록을 카드 형식으로 추가
                    $.each(data, function(index, item) {
                        var commentHtml = `
                            <div class="comment-card" data-contentid=${item.contentId}>
                                <div class="comment-header">
                                    <div class="comment-image">
                                        <img src="${item.firstimage}" alt="이미지" />
                                    </div>
                                    <div class="comment-info">
                                        <p class="comment-author">${item.title}</p>
                                        <p class="comment-content">${item.content}</p>
                                        <p class="comment-date">${new Date(item.createdAt).toLocaleString()}</p>
                                    </div>
                                </div>
                            </div>
                        `;
                        $('#comment-list').append(commentHtml); // 댓글 카드 추가
                    });
                }
            },
            error: function(error) {
                console.log('댓글 로딩 실패: ', error);
            }
        });
    });

    $('#redirectButton_post').on('click', function() {
        $('.mywrite-content-none').css('display', 'none');
        $('.mywrite-content-exist').css('display', 'flex');
        $('.mywrite-comment-none').css('display', 'none');
        $('.mywrite-comment-exist').css('display', 'none');
    });
});
