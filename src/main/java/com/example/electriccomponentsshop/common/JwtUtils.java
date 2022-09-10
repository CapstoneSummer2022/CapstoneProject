package com.example.electriccomponentsshop.common;

import com.example.electriccomponentsshop.entities.Account;
import com.example.electriccomponentsshop.entities.RefreshToken;
import com.example.electriccomponentsshop.repositories.AccountRepository;
import com.example.electriccomponentsshop.services.AccountDetailImpl;
import com.example.electriccomponentsshop.services.RefreshTokenService;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class JwtUtils {
    public static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    @Value("${bezkoder.app.jwtSecret}")
    private String jwtSecret;
    @Autowired
    AuthenticationManager authenticationManager;
    @Value("${bezkoder.app.jwtExpirationMs}")
    private long jwtExpirationMs;
    @Value("86400000")
    private long jwtRefreshExpirationMs;
    @Autowired
    private RefreshTokenService refreshTokenService;
    @Autowired
    private AccountRepository accountRepository;

    public String generateJwtToken(Authentication authentication) {
        AccountDetailImpl accountPrincipal = (AccountDetailImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((accountPrincipal.getEmail()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(jwtExpirationMs + new Date().getTime()))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();


    }

    public String getEmailFromJwtToken(String token){

        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
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
    public static String token = null;
    public boolean isTokenCorrect(HttpServletRequest request, HttpServletResponse response){
        try{
             token = getJwtFromRequest(request);
            System.out.println(token + "deop");
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if(authentication == null||(authentication instanceof AnonymousAuthenticationToken) ){
                return false;
            }
            else {
                AccountDetailImpl accountDetail = (AccountDetailImpl) authentication.getPrincipal();
                String email = accountDetail.getEmail();
                System.out.println("gout");
                Optional<RefreshToken> refreshToken = refreshTokenService.findByAccount_Email(email);
                if(refreshToken.isPresent()){
                    RefreshToken rToken = refreshToken.get();
                    if(!refreshTokenService.isExpiration(rToken)){
                        try {
                            token = generateJwtToken(authentication);
                            System.out.println(token + "hoang");
                            Cookie cookie = new Cookie("accessToken", token);
                            cookie.setHttpOnly(true);
                            cookie.setPath("/");
                            cookie.setMaxAge(1200);
                            response.addCookie(cookie);

                            System.out.println("new token");
                            return true;
                        }
                        catch(Exception ex){
                            logger.error("Invalid JWT signature: {}", ex.getMessage());
                        }
                    }
                    else return false;
                }

            }


        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

}

