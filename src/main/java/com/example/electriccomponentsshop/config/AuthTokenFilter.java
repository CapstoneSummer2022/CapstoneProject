package com.example.electriccomponentsshop.config;

import com.example.electriccomponentsshop.common.JwtUtils;
import com.example.electriccomponentsshop.services.AccountDetailServiceImpl;
import com.example.electriccomponentsshop.services.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
public class AuthTokenFilter extends OncePerRequestFilter {
//    @Autowired
//    private JwtUtils jwtUtils;
//    @Autowired
//    private AccountDetailServiceImpl accountDetailService;
//       private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);
//
//    public AuthTokenFilter() {
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        try{
//            String jwt = parseJwt(request);
//            if(jwt!=null&&jwtUtils.isTokenCorrect(jwt,response)){
//                String email = jwtUtils.getEmailFromJwtToken(jwt);
//                UserDetails userDetails = accountDetailService.loadUserByUsername(email);
//                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
//                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(auth);
//            }
//        }
//        catch(Exception e){
//            logger.error("hoang dz");
//        }
//        filterChain.doFilter(request,response);
//    }
//    private String parseJwt(HttpServletRequest request){
//        String headerAuth = request.getHeader("Authorization");
//        if(StringUtils.hasText(headerAuth)&&headerAuth.startsWith("Bearer ")){
//            return headerAuth.substring(7,headerAuth.length());
//        }
//        return null;
//    }
@Autowired
private JwtUtils jwtUtils;
    @Autowired
    private AccountDetailServiceImpl accountDetailService;
    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try{

            String jwt = getJwtFromRequest(request);

            if(jwt!=null&&jwtUtils.isTokenCorrect(jwt)){
                String email = jwtUtils.getEmailFromJwtToken(jwt);
                UserDetails userDetails = accountDetailService.loadUserByUsername(email);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);
                System.out.println("có chuỗi");
            }
            else if(jwt==null) {
                SecurityContextHolder.getContext().setAuthentication(null);
                SecurityContextHolder.clearContext();
                request.setAttribute("Chưa đăng nhập", null);
            }

        }

        catch(Exception e){
            logger.error("hoang dz");
        }
        System.out.println("f8");
        filterChain.doFilter(request,response);
    }
    private String getJwtFromRequest(HttpServletRequest request){

        Cookie[] c = request.getCookies();
        String value ="";
        for (int i = 0; i< c.length;i++){
            if(c[i].getName().equals("accessToken")){
                value = c[i].getValue();

                return value;
            }
        }
        return null;
    }

}