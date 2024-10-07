<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>모달 예제</title>
    <style>
        /* 모달을 숨긴 상태의 기본 스타일 */
        .modal {
            display: none; /* 처음에는 모달이 보이지 않음 */
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            justify-content: center;
            align-items: center;
        }

        /* 모달 컨텐츠 스타일 */
        .modal-content {
            background-color: white;
            padding: 20px;
            border-radius: 5px;
            width: 300px;
            text-align: center;
        }

        /* 닫기 버튼 스타일 */
        .close-btn {
            color: red;
            cursor: pointer;
        }
    </style>
</head>
<body>

    <h2>모달 열기 예제</h2>

    <!-- 모달을 여는 버튼 -->
    <button id="openModalBtn">모달 열기</button>

    <!-- 모달 -->
    <div id="myModal" class="modal">
        <div class="modal-content">
            <span class="close-btn" id="closeModalBtn">&times;</span>
            <p>이것은 모달입니다.</p>
        </div>
    </div>

    <script>
        // 모달 엘리먼트와 버튼 가져오기
        const modal = document.getElementById('myModal');
        const openModalBtn = document.getElementById('openModalBtn');
        const closeModalBtn = document.getElementById('closeModalBtn');

        // 모달 열기
        openModalBtn.addEventListener('click', () => {
            modal.style.display = 'flex';  // 모달을 화면에 보여줌
        });

        // 모달 닫기 (닫기 버튼 클릭 시)
        closeModalBtn.addEventListener('click', () => {
            modal.style.display = 'none';  // 모달을 숨김
        });

        // 모달 외부 클릭 시 닫기
        document.addEventListener('DOMContentLoaded', () => {
			    window.addEventListener('click', (e) => {
			        if (e.target == modal) {
			            modal.style.display = 'none';  // 모달을 숨김
			        }
			    });
			});
    </script>

</body>
</html>
