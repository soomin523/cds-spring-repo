// 회원 탈퇴 유효성 검사 함수
function validateWithdrawal() {
    const checkbox = document.getElementById("agreeCheckbox");

	
    if (!checkbox.checked) {
        // 체크박스가 선택되지 않았을 때 경고 메시지 표시
        alert("회원탈퇴 동의를 해주셔야 합니다.");
        return;
    }

    // 체크박스가 선택된 경우, mypagemain으로 이동
    location.href = 'cancelProcess.do';
}