<%@ page import="com.example.demo.service.People" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String name = request.getParameter("name");
    String year = request.getParameter("year");
    int birthYear = Integer.parseInt(year);

    People people = new People(name, birthYear);

%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>JSP - Hello World</title>
</head>
<body>
<%
    for (int i = 0; i < 10; i++) {


%>
<p>안녕하세요. <%=name%>님 나이는 <%= people.getAge()%></p>
<%
    }
%>

</body>
</html>