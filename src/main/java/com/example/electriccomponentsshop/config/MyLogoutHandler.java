package com.example.electriccomponentsshop.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyLogoutHandler implements LogoutHandler {

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication)  {
        Cookie[] cookies = request.getCookies();

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("accessToken")) {
                cookie.setHttpOnly(true);
                cookie.setMaxAge(0);

                response.addCookie(cookie);

                break;
            }
        }
        System.out.println("F");
        SecurityContextHolder.getContext().setAuthentication(null);
        SecurityContextHolder.clearContext();
        try {
            response.sendRedirect("/auth/signin");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
