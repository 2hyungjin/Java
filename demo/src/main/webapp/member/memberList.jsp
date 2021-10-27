<%@page import="java.util.List"%>
<%@ page import="com.example.demo.domain.Member" %>
<%@ page import="com.example.demo.service.MemberService" %>
<%@ page import="com.example.demo.service.MemberServiceImpl" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%
    List<Member> memberList = null;
    try {
        MemberService memberService = new MemberServiceImpl();

        memberList = memberService.getList();

    } catch (Exception e) {
        // do nothing
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원 목록</title>
    <style>
        h1 {
            width: 600px;
            margin: 10px auto 10px auto;
        }
        table {
            width: 600px;
            margin: 15px auto 10px auto;
            border-collapse: collapse;
        }

        th,td {
            border: 1px solid #CCC;
            padding: 3px;
        }
    </style>
</head>
<body>

<h1>회원 목록</h1>
<table>
    <thead>
    <tr>
        <th>아이디</th>
        <th>이름</th>
        <th>연락처</th>
        <th>나이</th>
    </tr>
    </thead>

    <tbody>
    <%
        if (memberList == null || memberList.size() == 0) {
    %>
    <tr>
        <td colspan="4">가입된 회원이 없거나, 읽어 올 수 없습니다.</td>
    </tr>
    <%
    } else {
        for (Member member: memberList) {
    %>
    <tr>
        <td><%= member.getEmail() %></td>
        <td><%= member.getName() %></td>
        <td><%= member.getContact() %></td>
        <td><%= member.getAge() %>세</td>
    </tr>
    <%
            }
        }
    %>
    </tbody>

</table>

</body>
</html>