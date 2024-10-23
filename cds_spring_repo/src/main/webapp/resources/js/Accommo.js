$(document).ready(function () {
    // 지역 버튼 클릭 시 페이지 이동
    $(document).on('click', '.region-btn', function () {
        let areaCode = $(this).data('region'); // 클릭한 지역의 코드 가져오기
        console.log("선택한 지역 코드:", areaCode);

        // 파라미터를 포함한 URL로 페이지 이동
        window.location.href = '/cds/accommodations/cityaccomo.do?areaCode=' + areaCode;
    });
    
    
    
    $(document).on('click','.popular-box',function() {
    let contentId = $(this).data('contentid');
    let region = $(this).data('region');
    let cat3 = $(this).data('cat3');
    
    	console.log(contentId);
    	console.log(region);
    	console.log(cat3);
    	
    	let url ='/cds/accommodations/cityaccomo.do?contentId='+contentId+'&areaCode='+region+'&cat3='+cat3;
    	
    	window.location.href = url;
    });
    
    
$('.scroll-button.left').on('click', function () {
    let scrollAmount = $('.accodesbox2').scrollLeft() - 300;  // 왼쪽으로 300px 이동
    $('.accodesbox2').scrollLeft(scrollAmount);  // 즉시 이동
});

$('.scroll-button.right').on('click', function () {
    let scrollAmount = $('.accodesbox2').scrollLeft() + 300;  // 오른쪽으로 300px 이동
    $('.accodesbox2').scrollLeft(scrollAmount);  // 즉시 이동
});

    

    // 폼 제출 시 검색 처리
    $('form').on('submit', function (e) {
        e.preventDefault(); // 기본 폼 제출 방지

        let region = $('#region').val();  // 지역 선택 값 가져오기
        let acccate = $('#acccate').val();  // 숙소 카테고리 값 가져오기

        if (region === "" && acccate === "") {
            alert("검색 조건을 선택해 주세요.");  // 지역이나 카테고리가 선택되지 않았을 때 경고
            return;
        } else if (region === "") {
            alert("지역은 필수 입력 항목입니다.");  // 지역이나 카테고리가 선택되지 않았을 때 경고
            return;
        }

        // 검색 결과 페이지로 이동, 파라미터를 URL에 추가
        let url = '/cds/accommodations/cityaccomo.do?';

        if (region !== "") {
            url += 'areaCode=' + region;  // 지역 선택 시 areaCode 파라미터 추가
        }

        if (acccate !== "") {
            if (region !== "") {
                url += '&';  // 이미 지역 파라미터가 있으면 & 추가
            }
            url += 'cat3=' + acccate;  // 숙소 카테고리 파라미터 추가
        }

        window.location.href = url;  // 검색 결과 페이지로 이동
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

            console.log(data.length); // 반환되는 데이터의 길이 출력
            // 조회수 순으로 인기 숙소 배경 이미지 설정 및 제목 추가
            data.sort(function (a, b) {
                return b.cnt - a.cnt;
            });

            console.log("반환된 숙소 데이터 수: " + data.length);

            let popularContainer = $('.accodesbox2'); // 인기 숙소를 담을 컨테이너

            popularContainer.empty(); // 기존 내용을 비움

            // 상위 8개의 인기 숙소만 표시하도록 설정
            let topData = data.slice(0, 8);

            topData.forEach(function (item) {
                let imageUrl = item.first_image ? item.first_image : '/path/to/default-image.jpg';
                let title = item.title.replace(/\s*\[.*?\]\s*/g, ''); // 정규 표현식으로 제거
                let cnt = item.cnt;
                let contentId=item.content_id;
                let region=item.area_code;
                let cat3=item.cat3;

                // 인기 숙소를 위한 HTML 구조
                let popularHtml = `
                    <div class="popular-box" data-contentid="${contentId}" data-region="${region}" data-cat3="${cat3}">
                        <div class="popular-image" style="background-image: url(${imageUrl}); width: 150px; height: 150px;"></div>
                        <h3>${title}</h3>
                        <h4>조회수: ${cnt}</h4>
                    </div>
                `;

                // 인기 숙소 HTML을 추가
                popularContainer.append(popularHtml);
            });

            // 인기 숙소가 없을 경우 처리
            if (topData.length === 0) {
                popularContainer.append('<p>인기 숙소 데이터가 없습니다.</p>');
            }
        },
        error: function () {
            alert('데이터를 가져오는데 실패했습니다.');
        }
    });
});
