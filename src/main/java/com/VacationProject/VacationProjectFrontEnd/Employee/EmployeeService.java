package com.VacationProject.VacationProjectFrontEnd.Employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee findByEmployeeName(String employeeName) {
        return employeeRepository.findByEmployeeName(employeeName)
                .orElseThrow(() -> new UsernameNotFoundException("No user found with the given " +
                        "employeeName: " + employeeName));

    }

    public Employee findById(int id) {
        return employeeRepository.findById(id);
    }

    public void saveAll(List<Employee> employeeList) {
        employeeRepository.saveAll(employeeList);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    public void saveAndEncodePassword(Employee employee) {
        employee.setPassword(
                passwordEncoder
                        .encode(employee.getEmployeeName())
        );
        employeeRepository.save(employee);
    }
}
