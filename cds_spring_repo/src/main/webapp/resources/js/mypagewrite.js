$(function() {
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
                $.each(data, function(index, item) {
                	console.log(item);
                    var commentHtml = `
                        <div class="comment-card">
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
                    $('#comment-list').append(commentHtml);
                });
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
