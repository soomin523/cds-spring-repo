$(function () {
    // 지역 버튼 클릭 시 AJAX 호출
    $('.course-button').click(function () {
        var areaCode = $(this).data('region');  // 지역 코드 가져오기
        var regionName = $(this).data('rname'); // 지역 이름 가져오기

        // 제목 업데이트
        $('#course-title').text('#' + regionName);

        // 이전 결과 초기화
        $('#course-results').empty();

        // AJAX 요청을 통해 코스 데이터를 가져오기
        $.ajax({
            url: 'getCoursesByRegion.do', // 서버 컨트롤러에 맞게 URL 수정
            type: 'GET',
            data: { areaCode: areaCode },  // 파라미터로 지역 코드를 전송
            dataType: 'json',              // 서버 응답을 JSON으로 기대
            success: function (response) {
                if (response && response.length > 0) {
                    // 성공적으로 데이터를 받은 경우
                    $.each(response, function (index, course) {
                        var courseItem = '<div class="course-item" data-contentid="' + course.content_id + '">' +
                            '<img src="' + course.first_image + '" alt="코스 이미지" />' +
                            '<p>' + course.title + '</p>' +
                            '</div>';
                        // 결과를 course-results에 추가
                        $('#course-results').append(courseItem);
                    });
                } else {
                    $('#course-results').html('<p>해당 지역에 대한 여행 코스가 없습니다.</p>');
                }
            },
            error: function (xhr, status, error) {
                console.error('데이터 가져오기 실패: ', error, xhr.responseText);
                $('#course-results').html('<p>데이터를 가져오는 중 오류가 발생했습니다. 서버 응답: ' + xhr.responseText + '</p>');
            }
        });
    });

    // course-item 클릭 시 contentid를 서버로 보내 상세 정보 가져오기
    $(document).on('click', '.course-item', function () {
    var contentId = $(this).data('contentid');  // 클릭한 아이템의 contentid 가져오기

    // AJAX 요청을 통해 contentid에 맞는 데이터 가져오기
    $.ajax({
        url: 'getCourseDetails.do',  // 서버에 요청 보낼 URL
        type: 'GET',
        data: { contentid: contentId },  // contentid 전송
        dataType: 'json',
        success: function (data) {
            if (data) {
                // 모달에 데이터 채우기
                $('.coursenamebox .first-image').attr('src', data.first_image).css('display', 'block');
                $('.coursenamebox .course-title').text(data.title);
                $('.mapbox').html('좌표: ' + data.map_x + ', ' + data.map_y);

                // 태그 박스에 distance와 taketime 채우기
                $('.tagbox .tag:nth-child(1) p').text(data.distance || '거리 정보 없음');
                $('.tagbox .tag:nth-child(2) p').text(data.taketime || '소요 시간 정보 없음');

                // overview 채우기
                $('.overview').text(data.overview || '설명 없음');

                // 모달 열기
                $('.coursemodal').css('display', 'flex');
            } else {
                alert('해당 코스 정보를 가져올 수 없습니다.');
            }
        },
        error: function (xhr, status, error) {
            console.error('데이터 가져오기 실패: ', error, xhr.responseText);
            alert('코스 정보를 가져오는 중 오류가 발생했습니다.');
        }
    });
});



    // 모달 닫기 버튼 클릭 시 모달 닫기
    $('.close-btn').click(function () {
        $('.coursemodal').css('display', 'none');
    });
});
