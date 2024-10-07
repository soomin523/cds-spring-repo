<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="DAO.CourseDAO" %>

<%
    String areaCode = request.getParameter("areaCode");  // 지역 코드
    int pageNo = Integer.parseInt(request.getParameter("pageNo"));  // 현재 페이지 번호
    int pageSize = Integer.parseInt(request.getParameter("pageSize"));  // 페이지 당 아이템 수
    
    CourseDAO courseDAO = new CourseDAO();
    
    // 페이지네이션을 적용하여 데이터를 JSON으로 반환
    String jsonResponse = courseDAO.getCoursesAsJsonByRegionCode(areaCode, pageNo, pageSize);
    
    // 로그에 JSON 응답 출력 (확인용)
    System.out.println("JSON Response: " + jsonResponse);  // jsonResponse 출력

    // 응답을 클라이언트에게 전달
    out.print(jsonResponse);
%>
