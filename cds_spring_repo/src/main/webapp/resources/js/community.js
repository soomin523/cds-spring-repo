// 게시물 검색 기능
function searchPosts() {
    var searchQuery = document.getElementById('searchBox').value.toLowerCase(); // 검색어 가져오기
    var posts = document.querySelectorAll('.post-item'); // 모든 게시물 요소 선택
    
    posts.forEach(function(post) {
        var title = post.querySelector('h3').textContent.toLowerCase(); // 게시물 제목 가져오기
        if (title.includes(searchQuery)) { // 제목에 검색어 포함 여부 확인
            post.style.display = ''; // 포함되면 게시물 보이기
        } else {
            post.style.display = 'none'; // 포함되지 않으면 게시물 숨기기
        }
    });
}

// 게시물 업로드 기능 (Ajax)
function uploadPost() {
    var title = document.getElementById('title').value; // 제목 가져오기
    var content = document.getElementById('content').value; // 내용 가져오기
    var region = document.getElementById('region').value; // 지역 가져오기
    var files = document.getElementById('fileUpload').files; // 업로드할 파일 가져오기

    // 모든 필드가 입력되었는지 확인
    if (!title || !content || !region || files.length === 0) {
        alert('모든 필드를 입력해주세요.'); // 필드 미입력 시 경고 메시지
        return;
    }

    var formData = new FormData(); // FormData 객체 생성
    formData.append('title', title); // 제목 추가
    formData.append('content', content); // 내용 추가
    formData.append('region', region); // 지역 추가
    for (var i = 0; i < files.length; i++) {
        formData.append('files', files[i]); // 파일 추가
    }

    fetch('/community/uploadPost', { // AJAX 요청 보내기
        method: 'POST',
        body: formData // FormData를 본문으로 설정
    })
    .then(response => response.json()) // 응답을 JSON으로 변환
    .then(data => {
        if (data.success) {
            alert('게시물이 업로드되었습니다.'); // 성공 시 알림
            location.reload(); // 페이지 새로고침
        } else {
            alert('업로드에 실패했습니다.'); // 실패 시 알림
        }
    })
    .catch(error => console.error('Error:', error)); // 에러 로그 출력
}
