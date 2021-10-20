<%@ page import="com.example.demo.service.SessionManager" %>
<%@ page import="com.example.demo.service.CookieSessionManager" %><%--
  Created by IntelliJ IDEA.
  User: jinstonlee
  Date: 2021/10/20
  Time: 1:46 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    SessionManager sessionManager = new CookieSessionManager();

    String id = sessionManager.getId(request);
    if (!sessionManager.isAuthorized(request)) {
        response.sendRedirect("login.jsp");
    }
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
님의 아이디는 <strong><%= id%>
</strong>입니다. <br>
<a href="logout.do">로그아웃</a>
</body>
</html>
