package com.example.demo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "PlayServlet", value = "/play")
public class PlayServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String id = null;

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            String name = cookie.getName();

            if ("cookie".equals(name)) {
                id = cookie.getValue();
                break;
            }
        }
        String message = null;
        if (id == null) {
            message = "권한이 없습니다.";
        } else {
            message = "당신의 아이디는 " + id;
        }

        response.getWriter().append(message);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
