package com.VacationProject.VacationProjectFrontEnd;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfigurer implements WebMvcConfigurer {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("").setViewName("pages/index");
        registry.addViewController("/").setViewName("pages/index");
        registry.addViewController("/index").setViewName("pages/index");
        registry.addViewController("/vacations/login").setViewName("login");
        registry.addViewController("/vacations/employee/home").setViewName("employee/home");
    }
}
