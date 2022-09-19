package com.example.electriccomponentsshop.config;


import com.example.electriccomponentsshop.common.JwtUtils;
import com.example.electriccomponentsshop.services.AccountDetailServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
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

            System.out.println("hoan"+ request.getMethod()+"  " +request.getRequestURI()+ " " + request.getPathInfo()+"    "+JwtUtils.token);

            if(jwtUtils.isTokenCorrect(request,response)&&JwtUtils.token!=null){
                System.out.println(JwtUtils.token);
                System.out.println("dcccb");
                String email = jwtUtils.getEmailFromJwtToken(JwtUtils.token);
                UserDetails userDetails = accountDetailService.loadUserByUsername(email);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);

            }
           else if(JwtUtils.token==null&& !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)){
                SecurityContextHolder.getContext().setAuthentication(null);
                SecurityContextHolder.clearContext();
            }
        }
        catch (SignatureException | MalformedJwtException| UnsupportedJwtException| IllegalArgumentException e) {
            SecurityContextHolder.getContext().setAuthentication(null);
            SecurityContextHolder.clearContext();
        }
        catch (ExpiredJwtException e){

        }
        filterChain.doFilter(request,response);

    }


}
