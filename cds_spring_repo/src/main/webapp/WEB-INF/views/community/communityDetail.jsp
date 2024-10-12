<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>게시물 상세 보기</title>
    <link rel="stylesheet" type="text/css" href="resources/css/community.css">
</head>
<body>
    <header>
        <h1>${post.title}</h1>
    </header>
    
    <div class="container">
        <p>작성자: ${post.author}</p>
        <p>작성일: ${post.date}</p>
        <hr>
        <p>${post.content}</p>
        
        <h3>첨부 파일</h3>
        <c:forEach var="file" items="${post.files}">
            <a href="${file.url}">${file.name}</a><br>
        </c:forEach>
    </div>
</body>
</html>
