<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="api.*, DAO.*, DTO.CourseInfoDTO.Item, java.util.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관광지 목록</title>
<script type="text/javascript">
    // 전체 선택/해제 함수
    function toggleCheckboxes(source) {
        const checkboxes = document.querySelectorAll('input[name="selectedContentIds"]');
        checkboxes.forEach(checkbox => checkbox.checked = source.checked);
    }

    // 선택된 contentid를 서버로 전송하는 함수
    function updateSelectedContent() {
        const form = document.getElementById("contentForm");
        form.action = "updateContentIds.jsp";
        form.method = "POST";
        form.submit();
    }
</script>
</head>

<body>
<h3>관광지 목록 (contenttypeid: 25)</h3>

<hr/>

<%
    CoursesDAO dao = new CoursesDAO(); // DAO 객체 생성
    List<Item> courseList = dao.getCoursesByContentTypeId(25); // contenttypeid가 25인 데이터 가져오기
%>

<form id="contentForm">
<table border="1"> 
<tr>
    <th><input type="checkbox" onclick="toggleCheckboxes(this)"> 전체 선택</th>
    <th>제목</th>
    <th>내용 ID</th>
</tr>

<% 
if (courseList != null && !courseList.isEmpty()) { // 조회된 데이터가 있는 경우
    for (Item course : courseList) { 
%>
        <tr>
            <td><input type="checkbox" name="selectedContentIds" value="<%= course.getContentid() %>"></td>
            <td><%= course.getTitle() %></td>
            <td><%= course.getContentid() %></td>
        </tr>
<%
    }
} else { // 조회된 데이터가 없는 경우
%>
    <tr><td colspan="3">조회된 데이터가 없습니다.</td></tr>
<%
}
%>
</table>

<!-- 선택된 값 서버로 전송하는 버튼 -->
<input type="button" value="선택된 관광지 업데이트" onclick="updateSelectedContent()">
</form>

</body>
</html>
