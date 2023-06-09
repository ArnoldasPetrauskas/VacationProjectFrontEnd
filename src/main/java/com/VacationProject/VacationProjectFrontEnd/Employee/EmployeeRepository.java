package com.VacationProject.VacationProjectFrontEnd.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByEmployeeName(String employeeName);
    Employee findById(int id);
}
