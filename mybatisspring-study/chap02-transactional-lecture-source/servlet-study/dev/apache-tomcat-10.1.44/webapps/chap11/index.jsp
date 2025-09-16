<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
    <h1>Filter</h1>
    <h3>필터의 라이프 사이클</h3>
    <ul>
<%--        2단 요청 구조--%>
        <li><a href="first/filter">Filter 사용하기</a></li>
    </ul>
    <hr>
    <h3>필터의 활용</h3>
    <form action="member/regist" method="post">
        <label>아이디: </label>
        <input type="text" name="userId">
        <br>
        <label>패스워드: </label>
        <input type="password" name="password">
        <br>
        <label>이름: </label>
        <input type="text" name="name">
        <br>
        <button>가입하기</button>
    </form>
</body>
</html>