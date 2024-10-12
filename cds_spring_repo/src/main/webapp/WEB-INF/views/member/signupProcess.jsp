<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String name = request.getParameter("log-name");
    String phone = request.getParameter("log-phone");
    String username = request.getParameter("log-username");
    String password = request.getParameter("log-password");
    String email = request.getParameter("log-email");
    String gender = request.getParameter("log-gender");
    String zipcode = request.getParameter("log-zipcode");
    String address = request.getParameter("log-address");

    // 회원 정보를 데이터베이스에 저장하는 로직 추가
    // 예시: 성공적으로 회원 정보가 저장되었으면 페이지 이동
    boolean isSignupSuccess = true; // 실제로는 DB 처리 결과에 따라 결정됨

    if (isSignupSuccess) {
        response.sendRedirect("signugEnd.jsp"); // 회원가입 성공 시 이동
    } else {
        out.println("회원가입에 실패했습니다. 다시 시도해주세요.");
    }
%>
