$(document).ready(function () {
    var container = document.getElementById('map'); // 지도를 표시할 div
    var options = {
        center: new kakao.maps.LatLng(35.8800, 127.5829), // 중심 좌표
        level: 13, // 지도의 확대 레벨
        scrollwheel: false, // 스크롤 줌 비활성화
        disableDoubleClickZoom: true, // 더블 클릭 줌 비활성화
    };

    var map = new kakao.maps.Map(container, options); // 지도 생성
    var polygons = []; // 폴리곤을 저장할 배열

    // sido.json 파일에서 데이터를 불러와 폴리곤을 생성하는 함수
    function loadSidoPolygons() {
        $.getJSON("/cds/resources/static/sido.json", function (geojson) {
            var units = geojson.features; // sido.json의 features 속성

            // 각 지역에 대해 폴리곤 데이터 생성
            $.each(units, function (index, unit) {
                var coordinates = unit.geometry.coordinates; // 좌표 배열
                var path = []; // 폴리곤 경로
                $.each(coordinates[0], function (index, coordinate) {
                    path.push(new kakao.maps.LatLng(coordinate[1], coordinate[0]));
                });

                // 폴리곤 객체 생성 및 지도에 표시
                var polygon = new kakao.maps.Polygon({
                    map: map,
                    path: path, // 경로 설정
                    strokeWeight: 2,
                    strokeColor: '#004c80',
                    strokeOpacity: 0.8,
                    fillColor: '#fff',  // 기본 채우기 색상
                    fillOpacity: 0.7    // 기본 투명도
                });
                polygons.push(polygon); // 폴리곤을 배열에 저장

                // 지역 이름 표시할 div 가져오기
                var regionNameDiv = $('#region'); // 지도 구석에 표시할 div

                // 마우스 오버 시 폴리곤 색상 및 지역 이름 표시
                kakao.maps.event.addListener(polygon, 'mouseover', function () {
                    polygon.setOptions({ fillColor: '#09f', fillOpacity: 0.9 }); // 색상 변경
                    regionNameDiv.html('<h3>' + unit.properties.SIG_KOR_NM + '</h3>' + '<p>클릭시 지역 날씨 정보를 확인할 수 있습니다.</p>');
                    regionNameDiv.show(); // 지역 이름 표시 div 보이기
                });

                // 마우스 아웃 시 원래 색상 복원 및 지역 이름 숨기기
                kakao.maps.event.addListener(polygon, 'mouseout', function () {
                    polygon.setOptions({ fillColor: '#fff', fillOpacity: 0.7 }); // 원래 색상으로 복원
                });

                // 폴리곤 클릭 시 날씨, 공기 오염 정보를 가져와서 #exp에 표시
                kakao.maps.event.addListener(polygon, 'click', function () {
                    var lat = path[0].getLat();  // 폴리곤의 첫 번째 좌표의 위도
                    var lon = path[0].getLng();  // 폴리곤의 첫 번째 좌표의 경도
					var region = unit.properties.SIG_KOR_NM;
					
                    // 모든 API 요청이 완료될 때까지 기다림
                    $.when(
                        getWeatherData(lat, lon),       // 날씨 정보
                        getAirPollutionData(lat, lon),  // 공기 오염 정보
                        getFiveDayForecast(lat, lon)    // 5일간 예보
                    ).done(function (weatherData, airData, forecastData) {
                        // 날씨 정보 처리
                        var temp = weatherData[0].main.temp;  // 온도
                        var description = weatherData[0].weather[0].description;  // 날씨 설명
                        var weatherHtml = `<h4>날씨 정보</h4>
                                           <p>온도: ${temp} °C</p>
                                           <p>날씨 설명: ${description}</p>`;

                        // 공기 오염 정보 처리
                        var pm10 = airData[0].list[0].components.pm10;  // 미세먼지 (PM10)
                        var pm2_5 = airData[0].list[0].components.pm2_5;  // 초미세먼지 (PM2.5)
                        var airPollutionHtml = `<h4>공기 오염 정보</h4>
                                               <p>미세먼지 (PM10): ${pm10}</p>
                                               <p>초미세먼지 (PM2.5): ${pm2_5}</p>`;

                        // 5일간 예보 정보 처리
                        var forecastHtml = '<h4>5일간 예보</h4><ul>';
                        var previousDate = ''; // 이전 날짜를 추적할 변수

                        $.each(forecastData[0].list, function (index, forecast) {
                            var dateTime = forecast.dt_txt;  // 날짜 및 시간
                            var date = dateTime.split(' ')[0]; // 날짜 부분만 추출
                            var time = dateTime.split(' ')[1]; // 시간 부분만 추출

                            // 매일 12:00 (정오) 시간의 데이터만 사용
                            if (time === "12:00:00" && date !== previousDate) {
                                var temp = forecast.main.temp;  // 온도
                                var weatherDescription = forecast.weather[0].description;  // 날씨 설명
                                var icon = forecast.weather[0].icon;  // 날씨 아이콘 코드
                                var iconUrl = `http://openweathermap.org/img/wn/${icon}@2x.png`;  // 아이콘 URL

                                // 아이콘 이미지를 추가한 HTML 구성
                                forecastHtml += `<li>
            <img src="${iconUrl}" alt="${weatherDescription}" style="width: 30px; height: 30px; vertical-align: middle;" />
            ${date} : ${temp}°C, ${weatherDescription}
        </li>`;

                                previousDate = date; // 추적한 날짜 갱신
                            }
                        });
                        forecastHtml += '</ul>';


                        // 결과 한꺼번에 표시
                        $('#exp').html('<div class="expbox">'+'<h3>'+region+'</h3>'+'<div class="exfive">' + weatherHtml+'</div>'+'<div class="exfive">' + airPollutionHtml +'</div>' + '<div class="exfive">' + forecastHtml + '</div>' + '</div>');
                    });
                });
            });
        });
    }

    // 초기 실행 시 sido.json 로드하여 폴리곤 생성
    loadSidoPolygons();

    // 날씨 정보 가져오기 함수
    function getWeatherData(lat, lon) {
        var apiKey = 'd230d08fe6ad082f54615c077bf76b16';
        var url = `https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lon}&appid=${apiKey}&units=metric&lang=kr`;

        return $.getJSON(url);
    }

    // 공기 오염 정보 가져오기 함수
    function getAirPollutionData(lat, lon) {
        var apiKey = 'd230d08fe6ad082f54615c077bf76b16';
        var url = `http://api.openweathermap.org/data/2.5/air_pollution?lat=${lat}&lon=${lon}&appid=${apiKey}`;

        return $.getJSON(url);
    }

    // 5일/3시간 날씨 예보 정보 가져오기 함수
    function getFiveDayForecast(lat, lon) {
        var apiKey = 'd230d08fe6ad082f54615c077bf76b16';
        var url = `https://api.openweathermap.org/data/2.5/forecast?lat=${lat}&lon=${lon}&appid=${apiKey}&units=metric&lang=kr`;

        return $.getJSON(url);
    }
});
