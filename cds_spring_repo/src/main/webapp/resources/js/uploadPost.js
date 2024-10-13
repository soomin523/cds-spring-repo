
    function submitPost() {
        const title = document.getElementById('title').value;
        const content = document.getElementById('content').value;
        const region = document.getElementById('region').value;
        const files = document.getElementById('images').files;

        if (!title || !content || !region || files.length === 0) {
            alert('모든 필드를 채워주세요!');
            return;
        }

        // FormData를 사용하여 데이터와 파일 전송
        const formData = new FormData();
        formData.append('title', title);
        formData.append('content', content);
        formData.append('region', region);
        Array.from(files).forEach(file => {
            formData.append('images', file);
        });

        // 서버로 게시물 전송 (Ajax 또는 Fetch 사용)
        fetch('${pageContext.request.contextPath}/uploadPost', {
            method: 'POST',
            body: formData
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                alert('게시물이 성공적으로 업로드되었습니다!');
                // 게시물 업로드 후 community.jsp로 이동
                window.location.href = '${pageContext.request.contextPath}/community.jsp';
            } else {
                alert('업로드에 실패했습니다.');
            }
        })
        .catch(error => {
            console.error('에러:', error);
            alert('에러가 발생했습니다.');
        });
    }

    let locations = [];

 // 위치 추가 함수
    function addLocation() {
        const locationInput = document.getElementById('location');
        const locationValue = locationInput.value.trim();

        if (locationValue && !locations.includes(locationValue)) {
            locations.push(locationValue);
            displayLocations();  // 입력한 위치를 화면에 표시하는 함수 호출
        }

        locationInput.value = ''; // 입력 필드 초기화
    }

    // 위치 삭제 함수
    function removeLocation(index) {
        locations.splice(index, 1);
        displayLocations();  // 삭제된 후 위치 목록을 다시 표시
    }


 // 위치를 화면에 표시하는 함수
    function displayLocations() {
        const locationList = document.getElementById('location-list');
        locationList.innerHTML = ''; // 기존 리스트 초기화

        locations.forEach((location, index) => {
            const locationTag = document.createElement('div');
            locationTag.classList.add('location-tag');
            locationTag.style.display = 'flex'; // 유연하게 가로로 배치

            // 위치 내용 추가
      const locationName = document.createElement('span');
locationName.textContent = location;
locationName.style.marginTop = '2px';  // 글자를 아래로 내리기
locationTag.appendChild(locationName);


            // 지우기 버튼
            const removeButton = document.createElement('button');
            removeButton.textContent = 'X';
            removeButton.className = 'remove-location-button'; // 클래스 추가
            removeButton.onclick = function() {
                removeLocation(index);
            };

            // 지우기 버튼을 위치 태그의 오른쪽 끝으로 이동
            locationTag.style.justifyContent = 'space-between'; // 가로 방향으로 배치
            locationTag.appendChild(removeButton);

            locationList.appendChild(locationTag);
        });
    }



    // 이미지 미리보기 기능
    const imageInput = document.getElementById('images');
    const imagePreview = document.getElementById('imagePreview');



    imageInput.addEventListener('change', function() {
        imagePreview.innerHTML = ''; // 기존 미리보기 삭제

        Array.from(this.files).forEach(file => {
            const reader = new FileReader();
            reader.onload = function(e) {
                const img = document.createElement('img');
                img.src = e.target.result;
                imagePreview.appendChild(img);
            }
            reader.readAsDataURL(file);
        });
    });

    function submitPost() {
        const title = document.getElementById('title').value;
        const content = document.getElementById('content').value;
        const region = document.getElementById('region').value;
        const files = imageInput.files;

        if (!title || !content || !region || files.length === 0) {
            alert('모든 필드를 채워주세요!');
            return;
        }

        // FormData를 사용하여 데이터와 파일 전송
        const formData = new FormData();
        formData.append('title', title);
        formData.append('content', content);
        formData.append('region', region);

        // 위치 데이터를 FormData에 추가
        locations.forEach((location, index) => {
            formData.append(`location[${index}]`, location); // 추가된 위치 데이터를 전송
        });

        Array.from(files).forEach(file => {
            formData.append('images', file);
        });

        // 서버로 게시물 전송 (Ajax 또는 Fetch 사용)
        fetch('/uploadPost', {
            method: 'POST',
            body: formData
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                alert('게시물이 성공적으로 업로드되었습니다!');
                window.location.href = '/community';  // 게시물 목록으로 이동
            } else {
                alert('업로드에 실패했습니다.');
            }
        })
        .catch(error => {
            console.error('에러:', error);
            alert('에러가 발생했습니다.');
        });
    }


    // 페이지 로드 후 '게시물 올리기' 버튼을 community.jsp에도 추가
    window.onload = function() {
        const header = document.querySelector(".commu-header");
        if (header) {
            const postButton = document.createElement("button");
            postButton.className = "commu-post-button";
            postButton.textContent = "게시물 올리기";
            postButton.onclick = function() {
                window.location.href = 'uploadPost.jsp';
            };
            header.appendChild(postButton);
        }
    };




    // 이미지 미리보기 기능
    const imageInput = document.getElementById('images');
    const imagePreview = document.getElementById('imagePreview');

    imageInput.addEventListener('change', function() {
        imagePreview.innerHTML = ''; // 기존 미리보기 삭제

        Array.from(this.files).forEach(file => {
            const reader = new FileReader();
            reader.onload = function(e) {
                const img = document.createElement('img');
                img.src = e.target.result;
                imagePreview.appendChild(img);
            }
            reader.readAsDataURL(file);
        });
    });

    function submitPost() {
        const title = document.getElementById('title').value;
        const content = document.getElementById('content').value;
        const region = document.getElementById('region').value;
        const files = imageInput.files;

        if (!title || !content || !region || files.length === 0) {
            alert('모든 필드를 채워주세요!');
            return;
        }

     // FormData를 사용하여 데이터와 파일 전송
        const formData = new FormData();
        formData.append('title', title);
        formData.append('content', content);
        formData.append('region', region);
        Array.from(files).forEach(file => {
            formData.append('images', file);
        });


        // 서버로 게시물 전송 (Ajax 또는 Fetch 사용)
        fetch('/uploadPost', {
            method: 'POST',
            body: formData
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                alert('게시물이 성공적으로 업로드되었습니다!');
                window.location.href = '/community';  // 게시물 목록으로 이동
            } else {
                alert('업로드에 실패했습니다.');
            }
        })
        .catch(error => {
            console.error('에러:', error);
            alert('에러가 발생했습니다.');
        });
    }

    // 페이지 로드 후 '게시물 올리기' 버튼을 community.jsp에도 추가
    window.onload = function() {
        const header = document.querySelector(".commu-header");
        if (header) {
            const postButton = document.createElement("button");
            postButton.className = "commu-post-button";
            postButton.textContent = "게시물 올리기";
            postButton.onclick = function() {
                window.location.href = 'uploadPost.jsp';
            };
            header.appendChild(postButton);
        }
    };
