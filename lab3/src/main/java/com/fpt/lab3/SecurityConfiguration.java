package com.fpt.lab3;

import com.fpt.lab3.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan({"com.fpt"})
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Qualifier("customUserDetailService")
    private CustomUserDetailService customUserDetailService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/", "/home","/login", "/logout","/user/logout").permitAll();
        http.authorizeRequests().antMatchers("/product/**","/customer/**").hasAnyRole("ADMIN", "USER");
        http.authorizeRequests().antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().and().formLogin()
                .loginProcessingUrl("/j_spring_security_check")
                .loginPage("/user/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/user/profile")
                .failureUrl("/user/login?error=true")
                .and().logout().logoutUrl("/user/logout")
                .and().exceptionHandling().accessDeniedPage("/user/access-denied");
    }



}
