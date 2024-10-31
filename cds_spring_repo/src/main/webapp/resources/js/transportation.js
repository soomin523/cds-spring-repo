const LoadingHandler = {
    show: function() {
        $('.loading-spinner').css('display', 'block');
    },
    hide: function() {
        $('.loading-spinner').css('display', 'none');
    }
};
window.LoadingHandler = LoadingHandler;

$(document).ready(function() {
    const today = new Date();
    $('#departure-date').val(today.toISOString().split('T')[0]);

    window.openModal = function(locationType) {
        $('#myModal').css('display', 'block');
        localStorage.setItem('activeLocationType', locationType);
    }

    window.closeModal = function() {
        $('#myModal').css('display', 'none');
    }

    $(window).on('click', function(event) {
        if ($(event.target).is('#myModal')) {
            closeModal();
        }
    });

    $('.inquiry').on('click', function() {
        const departureLocation = $('.departure-location').text();
        const arrivalLocation = $('.arrival-location').text();
        
        if (departureLocation === '출발지' || arrivalLocation === '도착지') {
            alert('출발지와 도착지를 선택해주세요.');
            return;
        }
    

        const transportType = $('.ticket-type-container .label[style*="background"]').text();
        switch(transportType) {
            case '항공권':
                searchFlights();
                break;
            case '기차':
                searchTrains();
                break;
            case '고속버스':
                searchBuses();
                break;
        }
    });

    $(document).on('click', '.prev-date', function() {
        const date = new Date($('#departure-date').val());
        date.setDate(date.getDate() - 1);
        $('#departure-date').val(date.toISOString().split('T')[0]);
        searchTransport();
    });

    $(document).on('click', '.next-date', function() {
        const date = new Date($('#departure-date').val());
        date.setDate(date.getDate() + 1);
        $('#departure-date').val(date.toISOString().split('T')[0]);
        searchTransport();
    });

    $('.filter-btn').click(function() {
        $('.filter-btn').removeClass('active');
        $(this).addClass('active');
        
        const selectedConsonant = $(this).data('consonant');
        const stationButtons = $('.selection button');
        
        if (selectedConsonant === 'all') {
            stationButtons.show();
            return;
        }
        
        stationButtons.each(function() {
            const stationName = $(this).text();
            const firstChar = stationName.charAt(0);
            const consonant = getInitialConsonant(firstChar);
            
            if (consonant === selectedConsonant) {
                $(this).show();
            } else {
                $(this).hide();
            }
        });
    });

    $('#search-input').on('input', function() {
        const searchText = $(this).val().toLowerCase();
        $('.selection button').each(function() {
            const stationName = $(this).text().toLowerCase();
            $(this).toggle(stationName.includes(searchText));
        });
    });
});

function getInitialConsonant(char) {
    const consonants = ['ㄱ','ㄲ','ㄴ','ㄷ','ㄸ','ㄹ','ㅁ','ㅂ','ㅃ','ㅅ','ㅆ','ㅇ','ㅈ','ㅉ','ㅊ','ㅋ','ㅌ','ㅍ','ㅎ'];
    const code = char.charCodeAt(0) - 0xAC00;
    if (code > -1 && code < 11172) {
        return consonants[Math.floor(code / 588)];
    }
    return char;
}
function searchTransport() {
    const transportType = $('.ticket-type-container .label[style*="background"]').text();
    LoadingHandler.show();
    
    switch(transportType) {
        case '항공권':
            searchFlights();
            break;
        case '기차':
            searchTrains();
            break;
        case '고속버스':
            searchBuses();
            break;
    }
}

function selectLocation(locationName) {
    const locationType = localStorage.getItem('activeLocationType');
    const departureLocation = $('.departure-location').text();
    const arrivalLocation = $('.arrival-location').text();
    const DEFAULT_DEPARTURE = '출발지';
    const DEFAULT_ARRIVAL = '도착지';

    if (locationType === 'departure') {
        if (locationName === arrivalLocation && arrivalLocation !== DEFAULT_ARRIVAL) {
            $('.arrival-location').text(departureLocation === DEFAULT_DEPARTURE ? DEFAULT_ARRIVAL : departureLocation);
        }
        $('.departure-location').text(locationName);
    } else if (locationType === 'arrival') {
        if (locationName === departureLocation && departureLocation !== DEFAULT_DEPARTURE) {
            $('.departure-location').text(arrivalLocation === DEFAULT_ARRIVAL ? DEFAULT_DEPARTURE : arrivalLocation);
        }
        $('.arrival-location').text(locationName);
    }
    closeModal();
}


function updateDepartureDate() {
    const selectedDate = $('#departure-date').val();
    console.log('Departure date:', selectedDate);
}

function formatDate(dateStr) {
    const dateObj = new Date(dateStr + 'T00:00:00');
    return dateObj.toLocaleDateString('ko-KR', {
        year: 'numeric',
        month: 'long',
        day: 'numeric',
        weekday: 'long'
    });
}

function formatTime(timeStr) {
    return timeStr ? String(timeStr).slice(8,10) + ':' + String(timeStr).slice(10,12) : '';
}




// Train related functions
function searchTrains() {
    const depPlaceName = $('.departure-location').text();
    const arrPlaceName = $('.arrival-location').text();
    const depPlandTime = $('#departure-date').val().replace(/-/g, '');

    console.log('Train Search Inputs:', {
        depPlaceName,
        arrPlaceName,
        depPlandTime
    });

    LoadingHandler.show();

    $.ajax({
        url: '/cds/transportation/getTrainOperationInfo',
        method: 'GET',
        dataType: 'json',
        data: {
            depPlaceName: depPlaceName,
            arrPlaceName: arrPlaceName,
            depPlandTime: depPlandTime
        },
        success: function(response) {
            console.log('Train Search Response:', response);
            displayTrainResults(response);
            LoadingHandler.hide();
        },
        error: function(xhr, status, error) {
            console.error('Train API Error:', {
                status: status,
                error: error,
                response: xhr.responseText
            });
            LoadingHandler.hide();
        }
    });
}

function displayTrainResults(data) {
    const $resultsContainer = $('.search-results-container');
    $resultsContainer.empty();

    const selectedDate = $('#departure-date').val();
    const formattedDate = formatDate(selectedDate);

    const dateNav = $('<div>').addClass('date-navigation');
    const prevBtn = $('<button>').addClass('prev-date').text('◀');
    const nextBtn = $('<button>').addClass('next-date').text('▶');
    const dateDisplay = $('<div>').addClass('result-date').text(formattedDate);

    dateNav.append(prevBtn).append(dateDisplay).append(nextBtn);
    $resultsContainer.append(dateNav);
          
    console.log('Selected Date:', selectedDate);
    console.log('Response Data:', data);

    if ($.isArray(data) && data.length > 0) {
        $resultsContainer.append(`
            <div class="result-header">
                <div class="cp">열차종류</div>
                <div class="departure-time">출발 시간</div>
                <div class="arrival-time">도착 시간</div>
                <div class="adultcharge">운임</div>
                <div class="trainno">열차번호</div>
            </div>
        `);

        const $resultsList = $('<div>').addClass('results-list');

        $.each(data, function(i, train) {
            const depTime = train.depplandtime ? String(train.depplandtime).slice(8,10) + ':' + String(train.depplandtime).slice(10,12) : '';
            const arrTime = train.arrplandtime ? String(train.arrplandtime).slice(8,10) + ':' + String(train.arrplandtime).slice(10,12) : '';

            const $resultItem = $('<div>').addClass('result-item');
            $resultItem.append([
                $('<div>').addClass('cp').text(train.traingradename || ''),
                $('<div>').addClass('departure-time').text(depTime),
                $('<div>').addClass('arrival-time').text(arrTime),
                $('<div>').addClass('adultcharge').text(!train.adultcharge || train.adultcharge === "0" ? "-" : train.adultcharge + "원"),
                $('<div>').addClass('trainno').text(train.trainno || '')
            ]);

            $resultsList.append($resultItem);
        });

        $resultsContainer.append($resultsList).show();
        $('.logo').hide();
    } else {
        $resultsContainer
            .append($('<div>').addClass('no-results').text('조회된 열차가 없습니다.'))
            .show();
            $('.logo').hide();
    }
}




// Flight related functions
function searchFlights() {
    const depAirportNm = $('.departure-location').text();
    const arrAirportNm = $('.arrival-location').text();
    const depPlandTime = $('#departure-date').val().replace(/-/g, '');

    LoadingHandler.show();

    $.ajax({
        url: '/cds/transportation/getFlightOperationInfo',
        method: 'GET',
        dataType: 'json',
        data: {
            depAirportNm: depAirportNm,
            arrAirportNm: arrAirportNm,
            depPlandTime: depPlandTime
        },
        success: function(response) {
            displayFlightResults(response);
            LoadingHandler.hide();
        },
        error: function(xhr, status, error) {
            console.error('API Error:', error);
            LoadingHandler.hide();
        }
    });
}

function displayFlightResults(data) {
    const $resultsContainer = $('.search-results-container');
    $resultsContainer.empty();

    const selectedDate = $('#departure-date').val();
    const formattedDate = formatDate(selectedDate);

    const dateNav = $('<div>').addClass('date-navigation');
    const prevBtn = $('<button>').addClass('prev-date').text('◀');
    const nextBtn = $('<button>').addClass('next-date').text('▶');
    const dateDisplay = $('<div>').addClass('result-date').text(formattedDate);

    dateNav.append(prevBtn).append(dateDisplay).append(nextBtn);
    $resultsContainer.append(dateNav);

    if ($.isArray(data) && data.length > 0) {
        $resultsContainer.append(`
            <div class="result-header">
                <div class="cp">항공사</div>
                <div class="departure-time">출발 시간</div>
                <div class="arrival-time">도착 시간</div>
                <div class="price">가격</div>
                <div class="seats">편명</div>
            </div>
        `);

        const $resultsList = $('<div>').addClass('results-list');
        
        $.each(data, function(i, flight) {
            const depTime = formatTime(flight.depPlandTime);
            const arrTime = formatTime(flight.arrPlandTime);
            
            const $resultItem = $('<div>').addClass('result-item');
            $resultItem.append([
                $('<div>').addClass('cp').text(flight.airlineNm || ''),
                $('<div>').addClass('departure-time').text(depTime),
                $('<div>').addClass('arrival-time').text(arrTime),
                $('<div>').addClass('price').text(flight.economyCharge ? flight.economyCharge + '원' : '-'),
                $('<div>').addClass('seats').text(flight.vihicleId || '')
            ]);

            $resultsList.append($resultItem);
        });

        $resultsContainer.append($resultsList).show();
    } else {
        $resultsContainer
            .append($('<div>').addClass('no-results').text('조회된 항공편이 없습니다.'))
            .show();
    }
    $('.logo').hide();
}




// Bus related functions
function searchBuses() {
    const depTerminalNm = $('.departure-location').text();
    const arrTerminalNm = $('.arrival-location').text();
    const depPlandTime = $('#departure-date').val().replace(/-/g, '');

    console.log('Bus Search Inputs:', {
        depTerminalNm,
        arrTerminalNm,
        depPlandTime
    });

    LoadingHandler.show();

    $.ajax({
        url: '/cds/transportation/searchBus.do',
        method: 'GET',
        dataType: 'json',
        data: {
            depTerminalNm: depTerminalNm,
            arrTerminalNm: arrTerminalNm,
            depPlandTime: depPlandTime
        },
        success: function(response) {
            console.log('Bus Search Response:', response);
            displayBusResults(response);
            LoadingHandler.hide();
        },
        error: function(xhr, status, error) {
            console.error('API Error:', error);
            LoadingHandler.hide();
        }
    });
}

function displayBusResults(data) {
    const $resultsContainer = $('.search-results-container');
    $resultsContainer.empty();

    const selectedDate = $('#departure-date').val();
    const formattedDate = formatDate(selectedDate);

    const dateNav = $('<div>').addClass('date-navigation');
    const prevBtn = $('<button>').addClass('prev-date').text('◀');
    const nextBtn = $('<button>').addClass('next-date').text('▶');
    const dateDisplay = $('<div>').addClass('result-date').text(formattedDate);

    dateNav.append(prevBtn).append(dateDisplay).append(nextBtn);
    $resultsContainer.append(dateNav);

    if (data.response.body.items && data.response.body.items.item) {
        $resultsContainer.append(`
            <div class="result-header">
                <div class="grade">등급</div>
                <div class="departure-time">출발 시간</div>
                <div class="arrival-time">도착 시간</div>
                <div class="charge">요금</div>
            </div>
        `);

        const items = data.response.body.items.item;
        const $resultsList = $('<div>').addClass('results-list');

        $.each(items, function(i, bus) {
            const depTime = formatTime(bus.depPlandTime);
            const arrTime = formatTime(bus.arrPlandTime);

            const $resultItem = $('<div>').addClass('result-item');
            $resultItem.append([
                $('<div>').addClass('grade').text(bus.gradeNm || ''),
                $('<div>').addClass('departure-time').text(depTime),
                $('<div>').addClass('arrival-time').text(arrTime),
                $('<div>').addClass('charge').text(bus.charge ? bus.charge + '원' : '-')
            ]);

            $resultsList.append($resultItem);
        });

        $resultsContainer.append($resultsList).show();
    } else {
        $resultsContainer
            .append($('<div>').addClass('no-results').text('조회된 버스가 없습니다.'))
            .show();
    }
    $('.logo').hide();
}

