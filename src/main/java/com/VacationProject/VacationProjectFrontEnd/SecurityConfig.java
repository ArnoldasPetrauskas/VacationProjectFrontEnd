package com.VacationProject.VacationProjectFrontEnd;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.web.SecurityFilterChain;

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
                        "/style/**", "/resources/**",  "", "/", "/vacations", "/login", "/register"
                )
                .permitAll()
           .and()
                .authorizeHttpRequests()
                    .requestMatchers("/user/**").hasAuthority("USER")
                    .requestMatchers("/admin/**").hasAuthority("ADMIN")
                .anyRequest()
                .authenticated()
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
