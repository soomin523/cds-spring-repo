$(document).ready(function () {
    //----------------------------------------------모달관련 js
    var modal = $('.accmodal');
    var imageModal = $('.image-modal');

    // accobox 클릭 시 모달 열기
    $(document).on('click', '.accobox', function () {
        let contentId = $(this).data('contentid');

        // Ajax 요청을 통해 서버에서 숙소 정보를 가져옴
        $.ajax({
            url: '/cds/accommodations/getAccommodationDetails.do',
            type: 'GET',
            data: { contentId: contentId },
            dataType: 'json',
            success: function (data) {
                console.log("응답 데이터:", data);
                // 모달에 데이터 표시
                $('#modalTitle').text(data.title.replace(/\s*\[.*?\]\s*/g, ''));
                $('#modalImage').attr('src', data.first_image);
                $('#modalAddress').text(data.addr1);
                
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

                // 모달 표시
                modal.addClass('show');
            },
            error: function () {
                alert('숙소 정보를 불러오는 데 실패했습니다.');
            }
        });
    });

    // 확대 이미지 표시 함수
    window.showLargeImage = function(src) {
        $('#largeImage').attr('src', src); // 확대할 이미지 설정
        $('.image-modal').fadeIn(); // 확대 모달 표시
    };

    // 모달 닫기
    $('.mxbox').on('click', function () {
        modal.removeClass('show');
        $('.image-modal').fadeOut(); // 확대 모달도 닫기
    });

    // 확대 모달 닫기
    $('.image-modal .close').on('click', function () {
        $('.image-modal').fadeOut(); // 확대 모달 숨기기
    });

    // 모달 외부 클릭 시 닫기
    $(window).on('click', function (event) {
        if ($(event.target).is(modal)) {
            modal.removeClass('show');
        } else if ($(event.target).is('.image-modal')) {
            $('.image-modal').fadeOut(); // 확대 모달 숨기기
        }
    });
    
})