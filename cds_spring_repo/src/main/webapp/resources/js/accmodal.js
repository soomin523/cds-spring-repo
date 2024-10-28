$(document).ready(function () {
    //----------------------------------------------모달관련 js
    var modal = $('.accmodal');
    var imageModal = $('.image-modal');

    function getParameterByName(name) {
        let url = window.location.href;
        name = name.replace(/[\[\]]/g, '\\$&');
        let regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
            results = regex.exec(url);
        if (!results) return null;
        if (!results[2]) return '';
        return decodeURIComponent(results[2].replace(/\+/g, ' '));
    }

    // 모달 표시를 위한 함수
    function openModalWithData(data) {
        // 모달에 데이터 표시
        $('#modalTitle').text(data.title.replace(/\s*\[.*?\]\s*/g, ''));
        $('#modalImage').attr('src', data.first_image);
        $('#modalAddress').text(data.addr1);
		$('#accmap').css(
		{'width':'100%',
		 'height':'400px',
		 'margin-top':'10px',
		 'margin-bottom':'10px',
		 'border': '2px solid #000',
		 'border-radius':'15px'
		 }
		 );
       
        if (data.tel == null || data.tel === "") {
            $('#modaltel h4').text("전화번호 정보없음");
        } else {
            $('#modaltel h4').text(data.tel);
        }

        $('#modaloverview h3').text(data.overview);
        
        if(data.map_x && data.map_y){
        	var mapContainer = document.getElementById('accmap');
        	var mapOption={
        		center: new kakao.maps.LatLng(data.map_y,data.map_x),
        		level:3,
        		scrollwheel: false
        	};
        	var map = new kakao.maps.Map(mapContainer,mapOption);
        	        	
        }
        
        
        var markerPosition= new kakao.maps.LatLng(data.map_y,data.map_x);
        var marker = new kakao.maps.Marker({
        	position:markerPosition
        });
        marker.setMap(map);
        
        var infoContent = '<div style="padding:5px; max-width:170px; word-wrap:break-word; text-align:center;">' + data.title.replace(/\s*\[.*?\]\s*/g, '') + '</div>';
                        var infoWindow = new kakao.maps.InfoWindow({
                            content: infoContent,
                        });
                        
                        infoWindow.open(map,marker);
        

        // 방 정보 표시
        let roomInfoHtml = '';
        if (data.rooms && data.rooms.length > 0) {
            data.rooms.forEach(room => {
                roomInfoHtml += `
                    <div class="room-info-card">
                        <h4>${room.roomtitle}</h4>
                        <p>가격: ${room.roomOffSeasonMinFee1}원</p>
                        <div class="room-images">
                            ${room.roomImg1 ? `<img src="${room.roomImg1}" alt="${room.roomImg1Alt}" class="room-image" onclick="showLargeImage('${room.roomImg1}')">` : ''}
                            ${room.roomImg2 ? `<img src="${room.roomImg2}" alt="${room.roomImg2Alt}" class="room-image" onclick="showLargeImage('${room.roomImg2}')">` : ''}
                            ${room.roomImg3 ? `<img src="${room.roomImg3}" alt="${room.roomImg3Alt}" class="room-image" onclick="showLargeImage('${room.roomImg3}')">` : ''}
                            ${room.roomImg4 ? `<img src="${room.roomImg4}" alt="${room.roomImg4Alt}" class="room-image" onclick="showLargeImage('${room.roomImg4}')">` : ''}
                            ${room.roomImg5 ? `<img src="${room.roomImg5}" alt="${room.roomImg5Alt}" class="room-image" onclick="showLargeImage('${room.roomImg5}')">` : ''}
                        </div>
                    </div>
                `;
            });
        } else {
            roomInfoHtml = '<p>방 정보가 없습니다.</p>';
        }
        $('#modalRoomInfo').html(roomInfoHtml);

        // 모달 표시 (애니메이션 설정)
        modal.css({
            'opacity': '0',
            'transform': 'translate(-50%, -50%) scale(0.95)'
        });

        modal.addClass('show');

        // 애니메이션 효과 적용
        setTimeout(function () {
            modal.css({
                'opacity': '1',
                'transform': 'translate(-50%, -50%) scale(1)'
            });
        }, 10);
    }

    // contentId 파라미터로 모달 열기
    let contentId = getParameterByName('contentId');
    if (contentId) {
        $.ajax({
            url: '/cds/accommodations/getAccommodationDetails.do',
            type: 'GET',
            data: { contentId: contentId },
            dataType: 'json',
            success: openModalWithData,
            error: function () {
                alert('숙소 정보를 불러오는 데 실패했습니다.');
            }
        });
    }

    // accobox 클릭 시 모달 열기
    $(document).on('click', '.accobox', function () {
        let contentId = $(this).data('contentid');
		document.body.style.overflow = 'hidden'; // body의 스크롤 비활성화
        // Ajax 요청을 통해 서버에서 숙소 정보를 가져옴
        $.ajax({
            url: '/cds/accommodations/getAccommodationDetails.do',
            type: 'GET',
            data: { contentId: contentId },
            dataType: 'json',
            success: openModalWithData,
            error: function () {
                alert('숙소 정보를 불러오는 데 실패했습니다.');
            }
        });
    });
    
    //
    document.querySelectorAll('.accodesbox > div').forEach((div) => {
    div.addEventListener('click', function () {
        // 모든 div에서 active 클래스 제거
        document.querySelectorAll('.accodesbox > div').forEach((d) => d.classList.remove('active'));

        // 클릭한 div에 active 클래스 추가
        this.classList.add('active');
    });
});

    // 확대 이미지 표시 함수
    window.showLargeImage = function (src) {
        $('#largeImage').attr('src', src); // 확대할 이미지 설정
        $('.image-modal').fadeIn(); // 확대 모달 표시
    };

    // 모달 닫기
    $('.x-mark').on('click', function () {
    document.body.style.overflow = ''; // body의 스크롤 활성화
        modal.css({
            'opacity': '0',
            'transform': 'translate(-50%, -50%) scale(0.95)'
        });

        setTimeout(function () {
            modal.removeClass('show');
        }, 300);
        $('.image-modal').fadeOut(); // 확대 모달도 닫기
    });

    // 사진 모달 닫기
    $(document).on('keydown', function (e) {
        if (e.key === "Escape") {
            imageModal.fadeOut();
        }
    });
    
    $(document).on('keydown', function (e) {
    document.body.style.overflow = ''; // body의 스크롤 활성화
        if (e.key === "Escape") {
            modal.css({
            'opacity': '0',
            'transform': 'translate(-50%, -50%) scale(0.95)'
        });

        setTimeout(function () {
            modal.removeClass('show');
        }, 300);
        $('.image-modal').fadeOut();
        }
    });

    // 모달 외부 클릭 시 닫기
    $(document).on('click', function (event) {
        if ($(event.target).is('.image-modal')) {
            $('.image-modal').fadeOut(); // 확대 모달 숨기기
        }
    });
});