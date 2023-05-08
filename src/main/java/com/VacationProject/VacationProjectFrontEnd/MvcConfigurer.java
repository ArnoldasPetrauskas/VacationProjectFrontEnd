package com.VacationProject.VacationProjectFrontEnd;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfigurer implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("").setViewName("pages/index");
        registry.addViewController("/").setViewName("pages/index");
        registry.addViewController("/index").setViewName("pages/index");
        registry.addViewController("/vacations/login").setViewName("login");
        registry.addViewController("/vacations/employee/home").setViewName("employee/home");
    }
}
