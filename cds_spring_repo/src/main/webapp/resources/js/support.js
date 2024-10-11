$(function () {
    
    //supportDetail 페이지 이동
    $(".select > button").click(function(){
    	select = $(this).val();
    
    	location.href=`supportDetail.do?select=${select}`;
    });
    
    //support 메인페이지 이동
    $("h2").click(function(){
    	location.href="support.do";
    });
    
    //게시글 toggle
    $(".contentTitle").click(function(){
        $(this).next().toggle();
        $(this).find("i").toggle();
    });
    $(".contentText").hide();
    $(".fa-angle-up").hide();
    
    //새글쓰기
    
    
});
