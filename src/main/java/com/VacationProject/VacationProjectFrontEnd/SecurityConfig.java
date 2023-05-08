package com.VacationProject.VacationProjectFrontEnd;

import com.VacationProject.VacationProjectFrontEnd.Employee.CustomEmployeeDetailsService;
import com.VacationProject.VacationProjectFrontEnd.Employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    @Autowired
    private DaoAuthenticationProvider authenticationProvider;

    @Autowired
    private EmployeeService employeeService;


    private final CustomSuccessHandler customSuccessHandler;
    public SecurityConfig (
            CustomSuccessHandler customSuccessHandler
    ){
        this.customSuccessHandler = customSuccessHandler;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authenticationProvider(authenticationProvider);

        return http
                .authorizeHttpRequests()
                .requestMatchers(
                        "/style/**", "/resources/**", "/vacations/**", "/login", "/organizer/**"
                )
                .permitAll()
           .and()
                .authorizeHttpRequests().requestMatchers("/admin/**").hasRole("ADMIN")
           .and()
                .authorizeHttpRequests().requestMatchers("/employee/**").hasAnyRole("USER", "ADMIN")
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
                .logoutSuccessUrl("/vacations")
                .permitAll()
           .and()
                .build();
    }
}
