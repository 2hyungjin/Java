package com.example.demo.servlet.member;

import com.example.demo.domain.Member;
import com.example.demo.service.MemberService;
import com.example.demo.service.MemberServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "/member/register.do")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            MemberService memberService = new MemberServiceImpl();
            //중복 체크
            if (memberService.isEmailDuplicated(request.getParameter("email"))) {
                String url="registerFail.jsp?reason=이미 사용 중인 아이디입니다.";
                response.sendRedirect(URLEncoder.encode(url,"UTF-8"));
                return;
            }
            Member member = new Member();
            member.setEmail(request.getParameter("email"));
            member.setPassword(request.getParameter("password"));
            member.setName(request.getParameter("name"));
            member.setContact(request.getParameter("contact"));
            member.setBirthday(request.getParameter("birthday"));

            memberService.registerMember(member);

            response.sendRedirect("registerSuccess.html");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());

            String url="registerFail.jsp?reason=회원 가입에 실패하였습니다.";
            response.sendRedirect(URLEncoder.encode(url,"UTF-8"));
        }
    }
}

