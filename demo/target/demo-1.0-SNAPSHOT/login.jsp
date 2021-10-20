<%--
  Created by IntelliJ IDEA.
  User: jinstonlee
  Date: 2021/10/20
  Time: 1:46 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        label {
            display: inline-block;
            width: 120px;
            margin-right: 10px;
            text-align: right;
        }
    </style>
    <title>login</title>
</head>
<body>
<form method="post" action="login.do">
    <div>
        <div>
            <label>id</label>
            <input type="text" name="id">

        </div>
        <div>
            <label>pw</label>
            <input type="password" name="pw">
        </div>
        <div>
            <input type="submit" name="submit">
        </div>
    </div>
</form>

</body>
</html>
