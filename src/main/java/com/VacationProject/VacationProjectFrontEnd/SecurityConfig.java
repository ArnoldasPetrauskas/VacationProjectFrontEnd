package com.VacationProject.VacationProjectFrontEnd;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
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
                        "/style/**", "/resources/**", "/vacations/**", "/login", "/organizer/**"
                )
                .permitAll()
           .and()
                .authorizeHttpRequests().requestMatchers("/admin/**").hasRole("ADMIN")
           .and()
                .authorizeHttpRequests().requestMatchers("/employee/**").hasRole("EMPLOYEE")
                .anyRequest().authenticated()
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
