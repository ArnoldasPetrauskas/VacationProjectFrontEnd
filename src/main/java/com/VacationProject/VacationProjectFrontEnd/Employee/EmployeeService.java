package com.VacationProject.VacationProjectFrontEnd.Employee;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee findByEmployeeName(String employeeName) {
        return employeeRepository.findByEmployeeName(employeeName)
                .orElseThrow(() -> new UsernameNotFoundException("No user found with the given " +
                        "employeeName: " + employeeName));

    }

    public Employee findById(int id){
        return employeeRepository.findById(id);
    }
    public void saveAll(List<Employee> employeeList) {
        employeeRepository.saveAll(employeeList);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }
}
