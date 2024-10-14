$(document).ready(function(){
    $.ajax({
        url: '/cds/accommodations/accoImg.do', // 정확한 경로 확인 필요
        type: 'GET',
        dataType: 'json',
        success: function(data) {
            // 1. 지역 코드에 맞는 배경 이미지 설정
            $('.accodesbox div[data-region]').each(function() {
                let regionCode = $(this).data('region');
                
                let matchingData = data.find(item => item.area_code == regionCode);

                if (matchingData) {
                    let imageUrl = matchingData.first_image ? matchingData.first_image : '/path/to/default-image.jpg';
                    $(this).css('background-image', `url(${imageUrl})`);
                }
            });

            // 2. 조회수 순으로 인기 숙소 배경 이미지 설정 및 제목 추가
            data.sort(function(a, b) {
                return b.cnt - a.cnt; 
            });

            let popularContainer = $('.accodesbox2 > div > div'); 

            popularContainer.each(function(index) {
                if (data[index]) {
                    let imageUrl = data[index].first_image ? data[index].first_image : '/path/to/default-image.jpg';
                    $(this).css('background-image', `url(${imageUrl})`);
                    
                    // 숙소 이름에서 [한국관광 품질인증/Korea Quality] 부분 제거
                    let title = data[index].title.replace(/\s*\[.*?\]\s*/g, ''); // 정규 표현식으로 제거
                    $(this).next('h3').text(title);
                }
            });
        },
        error: function() {
            alert('데이터를 가져오는데 실패했습니다.');
        }
    });
});
