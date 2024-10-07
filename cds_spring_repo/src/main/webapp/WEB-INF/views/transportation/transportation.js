$(document).ready(function () {
    // 출발지/도착지 모달
    window.openModal = function (locationType) {
        $('#myModal').css('display', 'block');
        const activeLocationType = locationType === '출발지' ? 'departure' : 'arrival';
        localStorage.setItem('activeLocationType', activeLocationType);
    }

    window.closeModal = function () {
        $('#myModal').css('display', 'none');
    }

    // 탑승 인원 모달
    window.openCustomerModal = function () {
        $('#customerModal').css('display', 'block');
    }

    window.closeCustomerModal = function () {
        $('#customerModal').css('display', 'none');
    }

    // 모달 닫기
    $(window).on('click', function (event) {
        if ($(event.target).is('#myModal')) {
            closeModal();
        } else if ($(event.target).is('#customerModal')) {
            closeCustomerModal();
        }
    });

    window.filterResults = function () {
        const input = $('#search').val().toLowerCase();
        const results = $('.results .result');
        const selectedFilter = $('input[name="filter"]:checked').attr('id');
        const selectedRegion = $('.region-selection button.active').text() || '전체';
        results.each(function () {
            const locationText = $(this).find('.location').text().toLowerCase();
            const category = $(this).data('category');
            const matchesCategory = (selectedFilter === 'all') || (selectedFilter === category);
            const matchesRegion = (selectedRegion === '전체') || locationText.includes(selectedRegion);
            $(this).toggle(matchesCategory && matchesRegion && locationText.includes(input));
        });
    }

    window.filterByRegion = function (region) {
        const results = $('.results .result');
        results.each(function () {
            const locationText = $(this).find('.location').text();
            $(this).toggle(region === 'all' || locationText.includes(region));
        });
        filterResults();
    }

    window.setActive = function (button) {
        $('.region-selection button').removeClass('active');
        $(button).addClass('active');
    }

    // 각 결과에 대한 클릭 이벤트 리스너 추가
    $('.results .result').on('click', function () {
        const location = $(this).find('.location').text();
        const category = $(this).find('.category').text();
        const activeLocationType = localStorage.getItem('activeLocationType');
        if (activeLocationType === 'departure') {
            $('.departure-location').text(`${location} (${category})`);
        } else if (activeLocationType === 'arrival') {
            $('.arrival-location').text(`${location} (${category})`);
        }
        closeModal();
    });

    // 티켓 유형 선택에 대한 이벤트 리스너 추가
    $('.ticket-type').on('click', function () {
        $('.ticket-type').removeClass('active');
        $(this).addClass('active');
        const isOneWay = $(this).hasClass('one-way');
        $('.return').css('display', isOneWay ? 'none' : 'block');
    });

    // 편도 선택 확인
    if ($('.ticket-type.one-way').hasClass('active')) {
        $('.return').css('display', 'none');
    }

    // 조회 버튼 
    $('.button.inquiry').on('click', function () {
        $('.search-results-container').css('display', 'block');
        $('.logo').css('display', 'none');
    });
});

// 탑승 인원
let adultCount = 1;
let childCount = 0;
let infantCount = 0;

window.changeCount = function (type, change) {
    if (type === 'adult') {
        adultCount = Math.max(1, adultCount + change);
        $('#adultCount').text(adultCount);
    } else if (type === 'child') {
        childCount = Math.max(0, childCount + change);
        $('#childCount').text(childCount);
    } else if (type === 'infant') {
        infantCount = Math.max(0, infantCount + change);
        $('#infantCount').text(infantCount);
    }
};

window.updateCustomerCount = function () {
    let countText = '';
    if (adultCount > 0) countText += `성인 ${adultCount}명`;
    if (childCount > 0) countText += `, 소아 ${childCount}명`;
    if (infantCount > 0) countText += `, 유아 ${infantCount}명`;
    if (adultCount === 1 && childCount === 0 && infantCount === 0) {
        countText = '인원 수';
    }
    $('.customer-count').text(countText);
    closeCustomerModal();
};

// 날짜 업데이트
window.updateDepartureDate = function() {
    const dateValue = document.getElementById('departure-date').value;
    
    if (dateValue) {
        const dateObject = new Date(dateValue);
        const formattedDate = dateObject.toLocaleDateString('ko-KR', { year: 'numeric', month: '2-digit', day: '2-digit' }).replace(/\./g, '.');
        
        const dayNames = ['일', '월', '화', '수', '목', '금', '토'];
        const dayOfWeek = dayNames[dateObject.getDay()];

        const resultDateElement = document.querySelector('.result-date');
        if (resultDateElement) {
            resultDateElement.textContent = `${formattedDate} ${dayOfWeek}`;
        }

        document.getElementById('departure-date-label').textContent = formattedDate;
    } else {
        document.getElementById('departure-date-label').textContent = '년. 월. 일.';
    }
}

window.updateArrivalDate = function() {
    const dateValue = document.getElementById('arrival-date').value;
    const formattedDate = dateValue ? new Date(dateValue).toLocaleDateString('ko-KR', { year: 'numeric', month: '2-digit', day: '2-digit' }).replace(/\./g, '.') : '년. 월. 일.'; 
    document.getElementById('arrival-date-label').textContent = formattedDate;
}
