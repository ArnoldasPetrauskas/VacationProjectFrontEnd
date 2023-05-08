package com.VacationProject.VacationProjectFrontEnd;

import com.VacationProject.VacationProjectFrontEnd.Employee.CustomEmployeeDetailsService;
import com.VacationProject.VacationProjectFrontEnd.Employee.Employee;
import com.VacationProject.VacationProjectFrontEnd.Employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
public class VacationProjectFrontEndApplication implements CommandLineRunner {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmployeeService employeeService;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(new CustomEmployeeDetailsService(employeeService));
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }



    public static void main(String[] args) {
        SpringApplication.run(VacationProjectFrontEndApplication.class, args);
    }

    @Override
    public void run(String... args) {
//        employeeService.saveAll(initEmployees());
    }

    private List<Employee> initEmployees() {
        Employee user = new Employee("user", passwordEncoder.encode("pass"));
        Employee god = new Employee("god", passwordEncoder.encode("god"));
        god.setRole("ADMIN");
        return List.of(user, god);
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
