<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>코스 목록</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

<h1>코스 제목과 ID</h1>

<form id="updateForm" method="post" action="updateOverview.do">
    <table border="1">
        <thead>
            <tr>
                <th><input type="checkbox" id="selectAll"></th> <!-- 전체 선택 체크박스 -->
                <th>제목</th>
                <th>콘텐츠 ID</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="course" items="${courseList}">
                <tr>
                    <td><input type="checkbox" name="contentIds" value="${course['content_id']}"></td> <!-- 각 행의 체크박스 -->
                    <td>${course['title']}</td>
                    <td>${course['content_id']}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <!-- 업데이트 버튼 -->
    <button type="submit">선택한 항목 업데이트</button>
</form>

<script>
    // 전체 선택 체크박스 기능
    $(document).ready(function(){
        $('#selectAll').click(function() {
            $('input[name="contentIds"]').prop('checked', this.checked);
        });

        $('input[name="contentIds"]').click(function() {
            if (!this.checked) {
                $('#selectAll').prop('checked', false);
            }
        });
    });
</script>

</body>
</html>
