$(function () {
    
    //관리자 페이지 내부 이동
    $(".select > button").click(function(){
    	let select = $(this).val();
    
    	location.href=`manager.do?select=${select}`;
    });
    
	
});
