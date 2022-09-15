package com.example.electriccomponentsshop.config;

import com.example.electriccomponentsshop.controller.AuthController;
import com.example.electriccomponentsshop.services.AccountDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@Primary
@EnableWebSecurity
    public class WebSecurityConfig extends WebSecurityConfigurerAdapter{


    @Autowired
    AccountDetailServiceImpl accountDetailService;
    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    public WebSecurityConfig() {
    }

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter(){
        return new AuthTokenFilter();
    }
    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        authenticationManagerBuilder.userDetailsService(accountDetailService).passwordEncoder(passwordEncoder());

    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder() ;

    }
    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new MyAccessDeniedHandler();
    }
    @Bean
    LogoutHandler logoutHandler(){
        return new MyLogoutHandler();
    }
    @Bean
    AuthenticationFailureHandler authenticationFailureHandler(){
        return new CustomFailureHandler();
    }

    @Override
    protected void configure(HttpSecurity http) throws  Exception {

            http.logout().invalidateHttpSession(true).addLogoutHandler(logoutHandler()).logoutUrl("/sign-out").permitAll();
            http.cors().and().csrf().disable()
                    .authorizeRequests().antMatchers("/error23","/css/**","/js/**","/resources/**",
                            "/auth/signin", "/auth/signup", "/auth/sign-out","/home","/error-401", "/product?**",
                            "/signup", "/address/**", "/img/**", "/product/**", "/contact").permitAll()
                    .antMatchers("/admin/**","/admin-home").hasAnyAuthority("ROLE_EMPLOYEE", "ROLE_MANAGER")
                    .antMatchers("/addToCart/**", "/profile/**", "/createOrder",
                            "/order/**", "/change-password", "/cart/**").hasAnyAuthority(("ROLE_CUSTOMER"))
                    .anyRequest().authenticated()
                    .and().exceptionHandling().accessDeniedHandler(accessDeniedHandler()).and().exceptionHandling().authenticationEntryPoint(unauthorizedHandler);
            http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);


    }
}




