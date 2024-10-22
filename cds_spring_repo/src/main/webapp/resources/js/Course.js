$(function () {
    var courseData = [];  // 전체 코스를 저장할 변수
    var currentPage = 1;  // 현재 페이지
    var itemsPerPage = 8;  // 페이지당 항목 수
	let contentId = getParameterByName('contentId');
	
    function getParameterByName(name) {
        let url = window.location.href;
        name = name.replace(/[\[\]]/g, '\\$&');
        let regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
            results = regex.exec(url);
        if (!results) return null;
        if (!results[2]) return '';
        return decodeURIComponent(results[2].replace(/\+/g, ' '));
    }
    
    // 페이지 로드 시 전체 데이터를 먼저 불러오기
    loadCoursesByRegion('all', '');

    // 지역 버튼 클릭 시 AJAX 호출
    let selectedRegion = 'all'; // 초기 값으로 전체 선택
    let selectedRegionName = '전체'; // 초기 값으로 전체 선택
    let selectedCourseTheme = null; // 선택된 코스 테마 변수

    $('.course-button').click(function () {
        var areaCode = $(this).data('region');
        
       //areaCode= "39"
        var regionName = $(this).data('rname');
        
        // regionName="제주"
        var coursecode = $(this).data('cat2');

        // 지역 버튼 클릭 시
        if (typeof areaCode !== 'undefined') {
            if (selectedRegion === areaCode) {
                // 이미 선택된 지역을 다시 클릭하면 전체로 설정
                selectedRegion = 'all'; // 전체로 초기화
                selectedRegionName = '전체';
            } else {
                // 새로운 지역 선택
                selectedRegion = areaCode === 'all' ? 'all' : areaCode; // 전체 버튼 클릭 시 areaCode를 'all'로 설정
                selectedRegionName = regionName; // 지역 이름을 selectedRegionName에 저장
            }
        }

        // 코스 테마 버튼 클릭 시
        if (typeof coursecode !== 'undefined') {
            if (selectedCourseTheme === coursecode) {
                // 이미 선택된 테마를 다시 클릭하면 선택 해제
                selectedCourseTheme = null;
            } else {
                // 새로운 코스 테마 선택
                selectedCourseTheme = coursecode; // 테마 코드를 selectedCourseTheme에 저장
            }
        }

        // 제목 업데이트
        let titleText = '';
        if (selectedRegion && selectedRegion !== 'all') {
            titleText += '#' + selectedRegionName; // 지역 이름을 표시
        } else if (selectedRegion === 'all') {
            // 전체가 선택된 초기 상태 처리
            titleText = '#전체';
        }

        if (selectedCourseTheme) {
            if (titleText !== '') {
                titleText += ' '; // 지역과 테마 사이 공백 추가
            }
            titleText += '#' + getCat2Text(selectedCourseTheme);
        }

        $('#course-title').text(titleText);

        // 이전 결과 초기화
        $('#course-results').empty();
        currentPage = 1;  // 페이지 초기화

        // 해당 지역의 코스 데이터를 가져오는 함수 호출
        if (selectedRegion === 'all' && selectedCourseTheme === null) {
            // 선택된 지역이나 테마가 없으면 전체 데이터를 로드
            loadCoursesByRegion('all', '');
        } else {
            // 선택된 지역이나 테마가 있는 경우, 해당 데이터를 로드
            loadCoursesByRegion(selectedRegion, selectedCourseTheme);
        }
    });

    // 검색 버튼 클릭 시 필터링된 결과 표시
    $('#search-button').click(function () {
        var searchWord = $('#search-input').val().toLowerCase();  // 검색어 가져오기 및 소문자로 변환
        var filteredData = courseData.filter(function(course) {
            return course.title.toLowerCase().includes(searchWord);  // 검색어가 title에 포함된 데이터 필터링
        });
        currentPage = 1;  // 페이지 초기화
        displayCourses(filteredData);  // 필터링된 데이터를 화면에 표시
    });

    // 엔터키를 눌렀을 때 검색 버튼 클릭 이벤트 실행
    $('#search-input').keypress(function (e) {
        if (e.which === 13) {  // 13은 엔터키의 키 코드
            $('#search-button').click();  // 검색 버튼 클릭 이벤트 호출
            e.preventDefault();  // 폼 제출 같은 기본 동작을 막음
        }
    });

    function getCat2Text(cat2Code) {
        var cat2Text = {
            "C0112": "가족코스",
            "C0113": "나홀로코스",
            "C0114": "힐링코스",
            "C0115": "도보코스",
            "C0116": "캠핑코스",
            "C0117": "맛코스",
        };
        return cat2Text[cat2Code];
    }

    // 코스 데이터를 화면에 표시하는 함수
    function displayCourses(courses) {
        $('#course-results').empty();  // 기존 결과 초기화
        var startIndex = (currentPage - 1) * itemsPerPage;
        var endIndex = Math.min(startIndex + itemsPerPage, courses.length);
        var paginatedCourses = courses.slice(startIndex, endIndex);

        if (paginatedCourses.length > 0) {
            $.each(paginatedCourses, function (index, course) {
                var cat2Text = getCat2Text(course.cat2);
                var courseItem = '<div class="course-item" data-contentid="' + course.content_id + '">' +
                    '<img src="' + course.first_image + '" alt="코스 이미지" />' +
                    '<p>' + course.title + '</p>' +
                    '<p class="cat2">' + cat2Text + '</p>' +
                    '</div>';
                $('#course-results').append(courseItem);  // 결과 추가
            });
        } else {
            $('#course-results').html('<p>검색 결과가 없습니다.</p>');
        }

        // 페이지네이션 버튼 표시
        setupPagination(courses.length);
    }

    // 페이지 로드 시 전체 지역 데이터를 불러오는 함수
    function loadCoursesByRegion(areaCode, coursecode) {
        $.ajax({
            url: '/cds/tourCourse/getCoursesByRegion.do',
            type: 'GET',
            data: {
                areaCode: areaCode === 'all' ? '' : areaCode,
                cat2: coursecode ? coursecode : ''
            },
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

    // 페이지네이션 버튼 설정
    function setupPagination(totalItems) {
    $('#pagination').empty();  // 기존 페이지네이션 초기화
    var totalPages = Math.ceil(totalItems / itemsPerPage);  // 총 페이지 수 계산
    var maxVisiblePages = 5;  // 한 번에 표시할 최대 페이지 수
    var startPage = Math.max(1, currentPage - Math.floor(maxVisiblePages / 2));  // 시작 페이지 계산
    var endPage = Math.min(totalPages, startPage + maxVisiblePages - 1);  // 끝 페이지 계산

    // '처음' 버튼 추가
    if (currentPage > 1) {
        var firstButton = $('<button class="page-button"><<</button>');
        firstButton.click(function () {
            currentPage = 1;
            displayCourses(courseData);
        });
        $('#pagination').append(firstButton);
    }

    // '이전' 버튼 추가
    if (currentPage > 1) {
        var prevButton = $('<button class="page-button">이전</button>');
        prevButton.click(function () {
            currentPage = Math.max(1, currentPage - 5);  // 5 페이지씩 이동
            displayCourses(courseData);
        });
        $('#pagination').append(prevButton);
    }

    // 페이지 버튼 생성
    for (var i = startPage; i <= endPage; i++) {
        var pageButton = $('<button class="page-button">' + i + '</button>');

        if (i === currentPage) {
            pageButton.addClass('active');
        }

        pageButton.click(function () {
            currentPage = parseInt($(this).text());
            displayCourses(courseData);
        });

        $('#pagination').append(pageButton);
    }

    // '다음' 버튼 추가
    if (currentPage < totalPages) {
        var nextButton = $('<button class="page-button">다음</button>');
        nextButton.click(function () {
            currentPage = Math.min(totalPages, currentPage + 5);  // 5 페이지씩 이동
            displayCourses(courseData);
        });
        $('#pagination').append(nextButton);
    }

    // '마지막' 버튼 추가
    if (currentPage < totalPages) {
        var lastButton = $('<button class="page-button">>></button>');
        lastButton.click(function () {
            currentPage = totalPages;
            displayCourses(courseData);
        });
        $('#pagination').append(lastButton);
    }
}



    // 랜덤 축제 데이터를 가져오는 함수
    function getRandomFestival() {
        $.ajax({
            url: '/cds/tourCourse/getRandomFestival.do',
            type: 'GET',
            dataType: 'json',
            success: function (festival) {
                if (festival && festival.f_firstimage != null) {
                    $('.festa h3').text('축제 :' + festival.f_title);
                    $('.festa img').attr('src', festival.f_firstimage);
                    $('.festa h4').text('시작일: ' + festival.f_eventstartdate);
                    $('.festa').data('contentid', festival.f_contentid);
                } else {
                    $('.festa').html('<p>축제 정보를 가지고 올 수 없습니다.</p>');
                }
            },
            error: function (xhr, status, error) {
                console.error('축제 데이터 가져오기 실패: ', error, xhr.responseText);
                $('.festa').html('<p>축제 정보를 가져오는 중 오류가 발생했습니다.</p>');
            }
        });
    }
    
    $('.festa').click(function () {
        window.location.href = '/cds/festival/getFestivalList.do';
    });

    // 페이지 로드 시 랜덤 축제 데이터를 가져옴
    getRandomFestival();
    
    
    
    
});
