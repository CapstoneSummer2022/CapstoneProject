package com.example.electriccomponentsshop.config;

import com.example.electriccomponentsshop.controller.AuthController;
import com.example.electriccomponentsshop.services.AccountDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


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

    @Override
    protected void configure(HttpSecurity http) throws  Exception {

            http.logout()
                    .logoutSuccessUrl("/signin")
                    .logoutUrl("/logout")
                    .permitAll();

            http.cors().and().csrf().disable()
                    .authorizeRequests().antMatchers("/error23").permitAll()
                    .antMatchers("/admin/**").hasAuthority("ROLE_MANAGER")
                    .anyRequest().authenticated()
                    .and().exceptionHandling().accessDeniedHandler(accessDeniedHandler()).accessDeniedPage("/error23").and().exceptionHandling().authenticationEntryPoint(unauthorizedHandler);
            http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);


//        http.logout()
//                .logoutSuccessUrl("/signin")
//                .logoutUrl("/logout")
//                .invalidateHttpSession(true)
//                .permitAll();
//        http.cors().and().csrf().disable()
//                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                .authorizeRequests().antMatchers("/list", "/test", "/signup", "/order", "cart", "/signin").permitAll().and()
//                .authorizeRequests().antMatchers("/administrator/**").hasAnyAuthority("ROLE_EMPLOYEE", "ROLE_MANAGER")
//                .antMatchers("/customer/change-password/**").hasAuthority("ROLE_CUSTOMER")
//                .antMatchers("/customer/information/**").hasAnyAuthority("ROLE_CUSTOMER")
//                .antMatchers("/logout").hasAnyAuthority("ROLE_CUSTOMER", "ROLE_EMPLOYEE", "ROLE_MANAGER")
//                .anyRequest().authenticated();
//
//        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}




