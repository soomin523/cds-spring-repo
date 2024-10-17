
//ajax
document.addEventListener("DOMContentLoaded", function() {
    document.getElementById("redirectButton").addEventListener("click", function() {
        // AJAX 요청
        var xhr = new XMLHttpRequest();
        xhr.open("GET", "/mypage/mypagelike_course.do", true);
        xhr.onload = function() {
            if (xhr.status === 200) {
                window.history.pushState({}, '', xhr.responseText);
            } else {
                console.error("Error: " + xhr.status);
            }
        };
        xhr.send();  
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