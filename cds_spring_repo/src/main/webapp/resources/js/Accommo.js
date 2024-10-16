$(document).ready(function () {
    // 이벤트 위임을 사용하여 동적 요소에서도 클릭 이벤트가 연결되도록 설정
    $(document).on('click', '.region-btn', function () {
        let areaCode = $(this).data('region'); // 클릭한 지역의 코드 가져오기
        console.log("선택한 지역 코드:", areaCode);

        // 파라미터를 포함한 URL로 페이지 이동
        // 페이지 경로가 올바른지 확인하고 수정해 주세요
        window.location.href = '/cds/accommodations/cityaccomo.do?areaCode=' + areaCode;
    });

    // Ajax 요청을 사용하여 배경 이미지 설정
    $.ajax({
        url: '/cds/accommodations/accoImg.do', // 정확한 경로 확인 필요
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            // 지역 코드에 맞는 배경 이미지 설정
            $('.region-btn div[data-region]').each(function () {
                let regionCode = $(this).data('region');
                let matchingData = data.find(item => item.area_code == regionCode);

                if (matchingData) {
                    let imageUrl = matchingData.first_image ? matchingData.first_image : '/path/to/default-image.jpg';
                    $(this).css('background-image', `url(${imageUrl})`);
                }
            });

            // 조회수 순으로 인기 숙소 배경 이미지 설정 및 제목 추가
            data.sort(function (a, b) {
                return b.cnt - a.cnt;
            });

            let popularContainer = $('.accodesbox2 > div > div');

            popularContainer.each(function (index) {
                if (data[index]) {
                    let imageUrl = data[index].first_image ? data[index].first_image : '/path/to/default-image.jpg';
                    $(this).css('background-image', `url(${imageUrl})`);

                    let title = data[index].title.replace(/\s*\[.*?\]\s*/g, ''); // 정규 표현식으로 제거
                    let cnt = data[index].cnt;

                    $(this).siblings('h3').text(title);
                    $(this).siblings('h4').text("조회수 : " + cnt);
                }
            });
        },
        error: function () {
            alert('데이터를 가져오는데 실패했습니다.');
        }
    });
});
