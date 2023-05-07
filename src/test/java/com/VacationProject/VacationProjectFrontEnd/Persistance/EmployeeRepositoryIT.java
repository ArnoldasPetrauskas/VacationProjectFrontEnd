package com.VacationProject.VacationProjectFrontEnd.Persistance;

import com.VacationProject.VacationProjectFrontEnd.Employee.Employee;
import com.VacationProject.VacationProjectFrontEnd.Employee.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class EmployeeRepositoryIT {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EmployeeRepository repository;

    @Test
    public void whenEmployeeIsCreated_theyArePersisted(){
        var individual = new Employee(
                "name",
                "password"
        );

        var savedEmployee = repository.save(individual);
        var persistedEmployee = entityManager.find(Employee.class, savedEmployee.getId());
        assertEquals(persistedEmployee, savedEmployee);
    }
}
