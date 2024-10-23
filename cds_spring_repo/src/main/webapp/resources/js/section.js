$(function () {
    function loadEventProducts() {
        $.ajax({
            url: 'http://localhost:9090/cds/products/getEventProducts',
            method: 'GET',
            dataType: 'json',
            success: function(data) {
                $('#eventImage1').attr('src', data[0].firstimage);
                $('#eventImage2').attr('src', data[1].firstimage);
                $('#eventImage3').attr('src', data[2].firstimage);
            },
            error: function(xhr, status, error) {
                console.error("Error loading event products:", error);
            }
        });
    }

    $('.imgBox img').hide();

    $('.selectBox button').click(function() {
        $('.selectBox button').removeClass('active');
        $(this).addClass('active');
        $('.imgBox img').hide();

        var category = $(this).text();
        switch(category) {
            case '전시/공연':
                $('.imgBox img').show();
                loadEventProducts();
                break;
            case '여행지추천':
                // ();  // 여행지 관련 function
                break;
            case '축제/행사':
                // ();  // 축제 관련 function
                break;
        }
    });
});
