<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>커뮤니티 게시물 목록</title>
    <link rel="stylesheet" type="text/css" href="resources/css/community.css">
    <script src="resources/js/community.js"></script>
</head>
<body>
    <header>
        <h1>커뮤니티 게시판</h1>
        <div class="commu-header">
            <input type="text" id="searchBox" placeholder="게시물 검색" onkeyup="searchPosts()">
            <button class="commu-post-button" onclick="window.location.href='uploadPost.jsp'">게시물 올리기</button>
        </div>
    </header>
    
    <div class="container">
        <div class="post-list">
            <c:forEach var="post" items="${postList}">
                <div class="post-item">
                    <h3><a href="communityDetail.jsp?id=${post.id}">${post.title}</a></h3>
                    <p>작성자: ${post.author}</p>
                    <p>작성일: ${post.date}</p>
                </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>
