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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Optional;

@Component
public class JwtUtils {
//    public static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
//    @Value("${bezkoder.app.jwtSecret}")
//    private String jwtSecret;
//    @Autowired
//    AuthenticationManager authenticationManager;
//    @Value("${bezkoder.app.jwtExpirationMs}")
//    private long jwtExpirationMs;
//    @Value("86400000")
//    private long jwtRefreshExpirationMs;
//    @Autowired
//    private RefreshTokenService refreshTokenService;
//    @Autowired
//    private AccountRepository accountRepository;
//
//    public String generateJwtToken(Authentication authentication) throws Exception {
//        AccountDetailImpl accountPrincipal = (AccountDetailImpl) authentication.getPrincipal();
//
//        return Jwts.builder()
//                .setSubject((accountPrincipal.getEmail()))
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(jwtExpirationMs + new Date().getTime()))
//                .signWith(SignatureAlgorithm.HS512, jwtSecret)
//                .compact();
//
//
//    }
//
//    public String getEmailFromJwtToken(String token){
//        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
//    }
//    public boolean isTokenCorrect(String token, HttpServletResponse response){
//        try{
//            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
//            return true;
//        }catch (SignatureException e) {
//            logger.error("Invalid JWT signature: {}", e.getMessage());
//        } catch (MalformedJwtException e) {
//            logger.error("Invalid JWT token: {}", e.getMessage());
//        } catch (ExpiredJwtException e) {
//                String email = getEmailFromJwtToken(token);
//                Optional<RefreshToken> refreshToken = refreshTokenService.findByAccount_Email(email);
//                if(refreshToken.isPresent()){
//                    RefreshToken rToken = refreshToken.get();
//                    if(!refreshTokenService.isExpiration(rToken)){
//                        Account account =accountRepository.findByEmail(email).get();
//                        Authentication authentication = authenticationManager.authenticate(
//                                new UsernamePasswordAuthenticationToken(account.getEmail(),account.getPassword())
//                        );
//                        try {
//
//
//                            Cookie cookie = new Cookie("accessToken", generateJwtToken(authentication));
//                            cookie.setHttpOnly(true);
//                            cookie.setMaxAge(1200);
//                            response.addCookie(cookie);
//
//                            return true;
//                        }
//                        catch(Exception ex){
//                            logger.error("Invalid JWT signature: {}", ex.getMessage());
//                        }
//                    }
//                    else return false;
//                }
//
//
//        } catch (UnsupportedJwtException e) {
//            logger.error("JWT token is unsupported: {}", e.getMessage());
//        } catch (IllegalArgumentException e) {
//            logger.error("JWT claims string is empty: {}", e.getMessage());
//        }
//        return false;
//    }
public static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    @Value("${bezkoder.app.jwtSecret}")
    private String jwtSecret;
    @Value("${bezkoder.app.jwtExpirationMs}")
    private long jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) throws Exception {
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
    public boolean isTokenCorrect(String token){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}

