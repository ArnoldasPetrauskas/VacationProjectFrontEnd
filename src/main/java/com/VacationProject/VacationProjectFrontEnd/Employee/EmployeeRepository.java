package com.VacationProject.VacationProjectFrontEnd.Persistance;

import com.VacationProject.VacationProjectFrontEnd.User.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByUsername(String username);
}
