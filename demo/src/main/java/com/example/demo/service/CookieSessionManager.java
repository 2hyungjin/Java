package com.example.demo.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieSessionManager implements SessionManager {

    public static final String COOKE_NAME = "cookie";

    @Override
    public boolean isAuthorized(HttpServletRequest request) {
        return getId(request) != null;
    }

    @Override
    public String getId(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(COOKE_NAME)) {
                return cookie.getValue();
            }
        }
        return null;
    }

    @Override
    public void doLogin(HttpServletRequest request, HttpServletResponse response, String id) {
        Cookie cookie = new Cookie(COOKE_NAME, id);
        response.addCookie(cookie);
    }

    @Override
    public void doLogout(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie(COOKE_NAME, "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
