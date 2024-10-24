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

<h1>코스 설명</h1>

<!-- 새로운 버튼을 위한 폼 -->
<form id="updateCourseDetailsForm" method="post" action="updateOverview.do">
<button type="submit">선택한 항목 업데이트 (Course Details)</button>
   <!-- updateCourseDetails.do 이건 거리 및 소요시간-->
   <!-- updateOverview.do  설명 -->
   
   <!-- overview 입력 -->
   	 <table border="1">
        <thead>
            <tr>
                <th><input type="checkbox" id="selectAll"></th> <!-- 전체 선택 체크박스 -->
                <th>제목</th>
                <th>콘텐츠 ID</th>
                <th>콘텐츠 타입 ID</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="course" items="${courseList}">
                <tr>
                    <td>
                        <input type="checkbox" name="contentIds" value="${course['content_id']}">
                    </td> <!-- 각 행의 체크박스에 content_id와 contenttypeid를 함께 전송 -->
                    <td>${course['title']}</td>
                    <td>${course['content_id']}</td>
                    <td>${course['content_type_id']}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table> 
   
   
   
   
   
   
   
    <!-- 동일한 체크박스를 사용하여 선택된 항목을 전송 -->
    <!-- 거리 소요시간 입력 -->
    <%-- <table border="1">
        <thead>
            <tr>
                <th><input type="checkbox" id="selectAll"></th> <!-- 전체 선택 체크박스 -->
                <th>제목</th>
                <th>콘텐츠 ID</th>
                <th>콘텐츠 타입 ID</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="course" items="${courseList}">
                <tr>
                    <td>
                        <input type="checkbox" name="selectedItems" value="${course['content_id']},${course['content_type_id']}">
                    </td> <!-- 각 행의 체크박스에 content_id와 contenttypeid를 함께 전송 -->
                    <td>${course['title']}</td>
                    <td>${course['content_id']}</td>
                    <td>${course['content_type_id']}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table> --%>
</form>

<script>
    // 전체 선택 체크박스 기능
    // 거리 소요시간입력
     /* $(document).ready(function(){
        $('#selectAll').click(function() {
            $('input[name="selectedItems"]').prop('checked', this.checked);
        });

        $('input[name="selectedItems"]').click(function() {
            if (!this.checked) {
                $('#selectAll').prop('checked', false);
            }
        });
    }); */
    
 // 전체 선택 체크박스 기능
 // overview
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
