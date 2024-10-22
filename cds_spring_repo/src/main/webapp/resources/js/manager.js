$(function () {
    
    //관리자 페이지 내부 이동
    $(".select > button").click(function(){
    	let select = $(this).val();
    
    	location.href=`manager.do?select=${select}`;
    });
    
    //회원 삭제
    $(".deleteMember").click(function(){
    	const m_id = this.dataset.id;
    	
    	$.ajax({ 
            type:"get",
            url:"http://localhost:9090/cds/manager/deleteMember.do",
            data:{ m_id: m_id },
            success:function(data){
            	if(data.trim() == 'ok'){
                	window.location.href = '/cds/manager/manager.do';
            	} else {
            		alert("회원 삭제 실패");
            	}
            },
            error:function(jqXHR){
                console.log("회원 정보를 삭제 중 에러가 발생했습니다.");
                console.log("상태 코드: " + jqXHR.status);
                console.log("상태 텍스트: " + jqXHR.statusText);
            }
        });
    	
    });
	
});
