<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>coursedata 입력</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

    <h1>Course Data 1차 입력</h1>

<form method="post" action="InsertCoursesProcess.do">
	<input type="hidden" name="serviceKey" value="y%2BM4KcA3dU54OMX03WyfG7Vgskk1N4ti1JPnqNLJgfSxfGZDGpJzCXttag92jy9eIo3XD6a89LQXwVwD%2BM9RyQ%3D%3D" />
	<input type="hidden" name="srcUrl" value="http://apis.data.go.kr/B551011/KorService1/areaBasedList1" />
	<input type="hidden" name="pageNo" value="1" />
	<input type="hidden" name="numOfRows" value="10" />
	<input type="submit" value="API 요청하기" />
</form>



</body>
</html>
