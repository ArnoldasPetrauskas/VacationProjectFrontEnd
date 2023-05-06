package com.VacationProject.VacationProjectFrontEnd;

import com.VacationProject.VacationProjectFrontEnd.Employee.EmployeeRepository;
import com.VacationProject.VacationProjectFrontEnd.Employee.CustomEmployeeDetailsService;
import com.VacationProject.VacationProjectFrontEnd.Employee.Employee;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class VacationProjectFrontEndApplication implements CommandLineRunner {
    private final EmployeeRepository employeeRepository;

    public VacationProjectFrontEndApplication(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(new CustomEmployeeDetailsService(employeeRepository));
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        SpringApplication.run(VacationProjectFrontEndApplication.class, args);
    }
    @Override
    public void run(String... args) {
        if(employeeRepository.count() == 0) {
            employeeRepository.saveAll(initUsers());
        }
    }

    private List<Employee> initUsers() {
        Employee user = new Employee("user", passwordEncoder().encode("pass"));
        Employee god = new Employee("god", passwordEncoder().encode("god"));
        god.setRole("ADMIN");
        return List.of(user, god);
    }
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
