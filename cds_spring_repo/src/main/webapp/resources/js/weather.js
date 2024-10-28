$(document).ready(function () {
    var container = document.getElementById('map'); // 지도를 표시할 div
    var options = {
        center: new kakao.maps.LatLng(35.8800, 127.5829), // 중심 좌표
        level: 13, // 지도의 확대 레벨
        scrollwheel: false, // 스크롤 줌 비활성화
        disableDoubleClickZoom: true, // 더블 클릭 줌 비활성화
        draggable: false, // 드래그 비활성화
    };

    function updateDateTime() {
        var now = new Date();
        var year = now.getFullYear();
        var month = ('0' + (now.getMonth() + 1)).slice(-2);
        var day = ('0' + now.getDate()).slice(-2);
        var hours = ('0' + now.getHours()).slice(-2);
        var minutes = ('0' + now.getMinutes()).slice(-2);
        var seconds = ('0' + now.getSeconds()).slice(-2);
        var currentDateTime = '현재시각 : ' + year + '-' + month + '-' + day + ' ' + hours + ':' + minutes + ':' + seconds;
        document.getElementById('currentDateTime').innerText = currentDateTime;
    }

    setInterval(updateDateTime, 100);

    var map = new kakao.maps.Map(container, options); // 지도 생성
    var polygons = []; // 폴리곤을 저장할 배열
    var activePolygon = null; // 현재 활성화된(클릭된) 폴리곤

    // sido.json 파일에서 데이터를 불러와 폴리곤을 생성하는 함수
    function loadSidoPolygons() {
        $.getJSON("/cds/resources/static/sido.json", function (geojson) {
            var units = geojson.features; // sido.json의 features 속성

            $.each(units, function (index, unit) {
                var coordinates = unit.geometry.coordinates;
                var path = [];
                $.each(coordinates[0], function (index, coordinate) {
                    path.push(new kakao.maps.LatLng(coordinate[1], coordinate[0]));
                });

                // 폴리곤 객체 생성 및 지도에 표시
                var polygon = new kakao.maps.Polygon({
                    map: map,
                    path: path,
                    strokeWeight: 2,
                    strokeColor: '#004c80',
                    strokeOpacity: 0.8,
                    fillColor: '#fff', // 기본 채우기 색상
                    fillOpacity: 0.7,  // 기본 투명도
                });
                polygons.push(polygon);

                var regionNameDiv = $('#region'); // 지도 구석에 표시할 div

                // 마우스 오버 시 폴리곤 색상 및 지역 이름 표시
                kakao.maps.event.addListener(polygon, 'mouseover', function () {
                    if (polygon !== activePolygon) { // 클릭된 폴리곤이 아닌 경우에만 색상 변경
                        polygon.setOptions({ fillColor: '#09f', fillOpacity: 0.9 });
                    }
                    regionNameDiv.html('<h3>' + unit.properties.SIG_KOR_NM + '</h3><p>클릭시 지역 날씨 정보를 확인할 수 있습니다.</p>');
                    regionNameDiv.show();
                });

                // 마우스 아웃 시 활성화되지 않은 폴리곤의 색상 복원
                kakao.maps.event.addListener(polygon, 'mouseout', function () {
                    if (polygon !== activePolygon) { // 클릭된 폴리곤이 아닌 경우에만 색상 복원
                        polygon.setOptions({ fillColor: '#fff', fillOpacity: 0.7 });
                    }
                });

                // 폴리곤 클릭 시
                kakao.maps.event.addListener(polygon, 'click', function () {
                    if (activePolygon) { // 이전에 클릭된 폴리곤이 있으면 색상 초기화
                        activePolygon.setOptions({ fillColor: '#fff', fillOpacity: 0.7 });
                    }
                    // 현재 클릭한 폴리곤을 active로 설정하고 색상 유지
                    polygon.setOptions({ fillColor: '#09f', fillOpacity: 0.9 });
                    activePolygon = polygon; // 현재 클릭된 폴리곤으로 설정

                    // 좌표 가져와 날씨 및 미세먼지 정보 요청
                    var lat = path[0].getLat();
                    var lon = path[0].getLng();
                    var region = unit.properties.SIG_KOR_NM;

                    $.when(
                        getWeatherData(lat, lon),
                        getAirPollutionData(lat, lon),
                        getFiveDayForecast(lat, lon)
                    ).done(function (weatherData, airData, forecastData) {
                        var temp = weatherData[0].main.temp;
                        var description = weatherData[0].weather[0].description;
                        var weatherHtml = `<h4>날씨 정보</h4><p>온도: ${temp} °C</p><p>날씨 설명: ${description}</p>`;

                        var pm10 = airData[0].list[0].components.pm10;
                        var pm2_5 = airData[0].list[0].components.pm2_5;
                        var airPollutionHtml = `<h4>공기 오염 정보</h4><p>미세먼지 (PM10): ${pm10}</p><p>초미세먼지 (PM2.5): ${pm2_5}</p>`;

                        var forecastHtml = '<h4>5일간 예보</h4><ul>';
                        var previousDate = '';

                        $.each(forecastData[0].list, function (index, forecast) {
                            var dateTime = forecast.dt_txt;
                            var date = dateTime.split(' ')[0];
                            var time = dateTime.split(' ')[1];

                            if (time === "12:00:00" && date !== previousDate) {
                                var temp = forecast.main.temp;
                                var weatherDescription = forecast.weather[0].description;
                                var icon = forecast.weather[0].icon;
                                var iconUrl = `http://openweathermap.org/img/wn/${icon}@2x.png`;

                                forecastHtml += `<li><img src="${iconUrl}" alt="${weatherDescription}" style="width: 30px; height: 30px; vertical-align: middle;" />${date} : ${temp}°C, ${weatherDescription}</li>`;

                                previousDate = date;
                            }
                        });
                        forecastHtml += '</ul>';

                        $('#exp').html('<div class="expbox"><h3>' + region + '</h3><div class="exfive">' + weatherHtml + '</div><div class="exfive">' + airPollutionHtml + '</div><div class="exfive">' + forecastHtml + '</div></div>');
                    });
                });
            });
        });
    }

    loadSidoPolygons();
		//날씨정보
    function getWeatherData(lat, lon) {
        var apiKey = 'd230d08fe6ad082f54615c077bf76b16';
        var url = `https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lon}&appid=${apiKey}&units=metric&lang=kr`;

        return $.getJSON(url);
    }
		//대기오염 정보
    function getAirPollutionData(lat, lon) {
        var apiKey = 'd230d08fe6ad082f54615c077bf76b16';
        var url = `http://api.openweathermap.org/data/2.5/air_pollution?lat=${lat}&lon=${lon}&appid=${apiKey}`;

        return $.getJSON(url);
    }
		//5일간일기 예보
    function getFiveDayForecast(lat, lon) {
        var apiKey = 'd230d08fe6ad082f54615c077bf76b16';
        var url = `https://api.openweathermap.org/data/2.5/forecast?lat=${lat}&lon=${lon}&appid=${apiKey}&units=metric&lang=kr`;

        return $.getJSON(url);
    }
});
