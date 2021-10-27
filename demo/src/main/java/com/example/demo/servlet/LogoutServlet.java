package com.example.demo.servlet;

import com.example.demo.service.CookieSessionManager;
import com.example.demo.service.SessionManager;
import com.example.demo.service.SessionManagerMaker;
import com.example.demo.service.SessionSessionManager;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LogoutServlet", value = "/logout.do")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/http;charset=UTF-8");

        SessionManager sessionManager = SessionManagerMaker.make();
        sessionManager.doLogout(request, response);


        response.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
