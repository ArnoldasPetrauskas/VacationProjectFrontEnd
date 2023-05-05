package com.VacationProject.VacationProjectFrontEnd.Employee;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomEmployeeDetailsService implements UserDetailsService {
    private final EmployeeRepository employeeRepository;

    public CustomEmployeeDetailsService(EmployeeRepository userRepository) {
        this.employeeRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String employeeName) throws UsernameNotFoundException {
        Employee user = employeeRepository
            .findByEmployeeName(employeeName)
            .orElseThrow(() -> new UsernameNotFoundException("No user found with the given " +
                "employeeName: " + employeeName));
        return new EmployeeDetailsMapper(user);
    }
}
