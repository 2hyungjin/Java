package com.example.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@WebServlet("/session_read")
public class SessionReadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //세션 받기
        HttpSession session = request.getSession();

        //세션 초기화
//        session.invalidate();

        //특정 세션 삭제
//        session.removeAttribute("id");

        String id = (String) session.getAttribute("id");
        String pw = (String) session.getAttribute("pw");

        StringBuilder buffer = new StringBuilder();
        buffer.append("id : ").append(id).append("<br>");
        buffer.append("pw : ").append(pw).append("<br>");

        response.getWriter().append(buffer.toString());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
