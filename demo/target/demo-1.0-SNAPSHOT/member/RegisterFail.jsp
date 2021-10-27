<%--
  Created by IntelliJ IDEA.
  User: jinstonlee
  Date: 2021/10/27
  Time: 1:27 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String reason = request.getParameter("reason");
    if (reason == null) {
        reason = "";
    }
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>회원 가입에 실패했습니다.</p>
<p><%= reason %>
</p>
</body>
</html>
