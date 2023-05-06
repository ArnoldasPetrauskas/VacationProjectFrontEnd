package com.VacationProject.VacationProjectFrontEnd.Employee;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Optional<Employee> findByEmployeeName(String employeeName) {
        return employeeRepository.findByEmployeeName(employeeName);
    }

    public void saveAll(List<Employee> employeeList) {
        employeeRepository.saveAll(employeeList);
    }
}
