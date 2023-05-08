package com.VacationProject.VacationProjectFrontEnd.Employee;

import com.VacationProject.VacationProjectFrontEnd.Vacation.Vacation;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final PasswordEncoder passwordEncoder;

    private final EmployeeRepository employeeRepository;

    public EmployeeService(
            EmployeeRepository employeeRepository,
            PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder =passwordEncoder;
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

    public void deleteById(int employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    public void addVacationToEmployee(int employeeId, Vacation vacation){
        Employee employee = findById(employeeId);
        employee.addVacation(vacation);
        employeeRepository.save(employee);
    }
}
