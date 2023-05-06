package com.VacationProject.VacationProjectFrontEnd.Employee;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomEmployeeDetailsService implements UserDetailsService {
    private final EmployeeService employeeService;

    public CustomEmployeeDetailsService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public UserDetails loadUserByUsername(String employeeName) throws UsernameNotFoundException {
        Employee employee = employeeService
            .findByEmployeeName(employeeName)
            .orElseThrow(() -> new UsernameNotFoundException("No user found with the given " +
                "employeeName: " + employeeName));

        return new EmployeeDetailsMapper(employee);
    }
}
