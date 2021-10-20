package com.example.demo;

import com.example.demo.service.CookieSessionManager;
import com.example.demo.service.SessionManager;
import com.example.demo.service.SessionManagerMaker;
import com.example.demo.service.SessionSessionManager;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login.do")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String id = request.getParameter("id");
        String pw = request.getParameter("pw");

        // id pw 검증

        SessionManager sessionManager = SessionManagerMaker.make();
        sessionManager.doLogin(request, response, id);

        response.sendRedirect("work.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
