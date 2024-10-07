<%@ page language="java" contentType="application/json; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="DAO.*, DTO.*"%>

<%
String contentId = request.getParameter("contentId");
CoursemodalDAO dao = new CoursemodalDAO();
CoursemodalDTO course = dao.getCoursemodalById(contentId);

if (course != null) {
	// JSON 응답으로 코스 정보를 반환
	out.print("{");
	out.print("\"title\": \"" + course.getTitle() + "\",");
	out.print("\"map_x\": \"" + course.getMap_x() + "\",");
	out.print("\"map_y\": \"" + course.getMap_y() + "\",");
	out.print("\"overview\": \"" + course.getOverview() + "\",");
	out.print("\"first_image\": \"" + course.getFirst_image() + "\"");
	out.print("}");
} else {
	// 오류 처리
	out.print("{\"error\": \"해당 코스 정보를 찾을 수 없습니다.\"}");
}
%>
