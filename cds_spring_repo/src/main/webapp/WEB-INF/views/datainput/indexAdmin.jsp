<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공공데이터관리</title>
</head>
<body>
<h2>공공데이터 관리</h2>
<hr>
<P><a href = "insertCourseid.jsp">코스데이터정보입력</a></P>


	<%
	//request객체에 저장된 오류 메시지 확인하기
	String msg = (String)request.getAttribute("msg");
	if(msg != null){
		out.print("<p>"+msg+"</p>");
	}
	%>
</body>
</html>