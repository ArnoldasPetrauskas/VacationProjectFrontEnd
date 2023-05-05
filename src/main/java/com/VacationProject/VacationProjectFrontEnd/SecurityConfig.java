package com.VacationProject.VacationProjectFrontEnd;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    private final CustomSuccessHandler customSuccessHandler;
    public SecurityConfig (
            CustomSuccessHandler customSuccessHandler
    ){
        this.customSuccessHandler = customSuccessHandler;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests()
                .requestMatchers(
                        "/style/**", "/resources/**",  "", "/", "/vacations", "/login"
                )
                .permitAll()
           .and()
                .authorizeHttpRequests()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/vacations/**").access(
                        new WebExpressionAuthorizationManager(
                                "hasRole('ADMIN') and hasRole('EMPLOYEE')"
                        ))
           .and()
                .formLogin()
                .loginPage("/vacations/login")
                .usernameParameter("username")
                .loginProcessingUrl("/vacations/login")
                .successHandler(customSuccessHandler)
                .permitAll()
           .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/index")
                .permitAll()
           .and()
                .build();
    }
}
