$(function () {
    var courseData = [];  // 전체 코스를 저장할 변수

    // 페이지 로드 시 전체 데이터를 먼저 불러오기
    loadCoursesByRegion('all', '#전체');

    // 지역 버튼 클릭 시 AJAX 호출
    $('.course-button').click(function () {
        var areaCode = $(this).data('region');  // 지역 코드 가져오기
        var regionName = $(this).data('rname'); // 지역 이름 가져오기

        // 제목 업데이트
        $('#course-title').text('#' + regionName);

        // 이전 결과 초기화
        $('#course-results').empty();

        // 해당 지역의 코스 데이터를 가져오는 함수 호출
        loadCoursesByRegion(areaCode, regionName);
    });

    // 검색 버튼 클릭 시 필터링된 결과 표시
    $('#search-button').click(function () {
        var searchWord = $('#search-input').val().toLowerCase();  // 검색어 가져오기 및 소문자로 변환
        var filteredData = courseData.filter(function(course) {
            return course.title.toLowerCase().includes(searchWord);  // 검색어가 title에 포함된 데이터 필터링
        });
        displayCourses(filteredData);  // 필터링된 데이터를 화면에 표시
    });

    // 엔터키를 눌렀을 때 검색 버튼 클릭 이벤트 실행
    $('#search-input').keypress(function (e) {
        if (e.which === 13) {  // 13은 엔터키의 키 코드
            $('#search-button').click();  // 검색 버튼 클릭 이벤트 호출
            e.preventDefault();  // 폼 제출 같은 기본 동작을 막음
        }
    });

    // 코스 데이터를 화면에 표시하는 함수
    function displayCourses(courses) {
        $('#course-results').empty();  // 기존 결과 초기화
        if (courses.length > 0) {
            $.each(courses, function (index, course) {
                var courseItem = '<div class="course-item" data-contentid="' + course.content_id + '">' +
                    '<img src="' + course.first_image + '" alt="코스 이미지" />' +
                    '<p>' + course.title + '</p>' +
                    '</div>';
                $('#course-results').append(courseItem);  // 결과 추가
            });
        } else {
            $('#course-results').html('<p>검색 결과가 없습니다.</p>');
        }
    }

    // course-item 클릭 시 contentid를 서버로 보내 상세 정보 가져오기
    $(document).on('click', '.course-item', function () {
        var contentId = $(this).data('contentid');  // 클릭한 아이템의 contentid 가져오기

        // AJAX 요청을 통해 contentid에 맞는 데이터 가져오기
        $.ajax({
            url: '/cds/tourCourse/getCourseDetails.do',  // 절대 경로로 설정
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

    // 모달 바깥 영역 클릭 시 모달 닫기
    $(document).on('click', function (event) {
        // 클릭한 대상이 모달 바깥일 경우에만 모달 닫기
        if ($(event.target).closest('.coursemodal').length === 0 && $('.coursemodal').is(':visible')) {
            $('.coursemodal').css('display', 'none');
        }
    });

    // 모달 내부의 어느 곳이든 클릭 시 이벤트 전파 중지 (모달이 닫히지 않도록)
    $('.coursemodal').on('click', function (event) {
        event.stopPropagation(); // 이벤트 전파 방지
    });

    // 랜덤 축제 데이터를 가져오는 함수
    function getRandomFestival() {
        $.ajax({
            url: '/cds/tourCourse/getRandomFestival.do',  // 절대 경로로 설정
            type: 'GET',
            dataType: 'json',
            success: function (festival) {
                if (festival) {
                    // 축제 데이터를 페이지에 표시
                    $('.festa h3').text('축제 :' + festival.f_title);  

                    // 이미지 src 속성 변경
                    $('.festa img').attr('src', festival.f_firstimage);  
                    $('.festa h4').text('시작일: ' + festival.f_eventstartdate);

                    // contentid 저장 (필요한 경우)
                    $('.festa').data('contentid', festival.f_contentid);
                } else {
                    $('.festa').html('<p>축제 정보를 가져올 수 없습니다.</p>');
                }
            },
            error: function (xhr, status, error) {
                console.error('축제 데이터 가져오기 실패: ', error, xhr.responseText);
                $('.festa').html('<p>축제 정보를 가져오는 중 오류가 발생했습니다.</p>');
            }
        });
    }

    // 페이지 로드 시 랜덤 축제 데이터를 가져옴
    getRandomFestival();

    // 페이지 로드 시 전체 지역 데이터를 불러오는 함수
    function loadCoursesByRegion(areaCode, regionName) {
        $.ajax({
            url: '/cds/tourCourse/getCoursesByRegion.do', // 절대 경로로 설정
            type: 'GET',
            data: areaCode === 'all' ? {} : { areaCode: areaCode },  // 파라미터로 지역 코드를 전송
            dataType: 'json',
            success: function (response) {
                if (response && response.length > 0) {
                    courseData = response;  // 받은 데이터를 저장
                    displayCourses(courseData);  // 코스 데이터를 표시하는 함수 호출
                } else {
                    $('#course-results').html('<p>해당 지역에 대한 여행 코스가 없습니다.</p>');
                }
            },
            error: function (xhr, status, error) {
                console.error('데이터 가져오기 실패: ', error, xhr.responseText);
                $('#course-results').html('<p>데이터를 가져오는 중 오류가 발생했습니다. 서버 응답: ' + xhr.responseText + '</p>');
            }
        });
    }
});
