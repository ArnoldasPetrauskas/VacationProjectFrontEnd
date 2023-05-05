package com.VacationProject.VacationProjectFrontEnd.Employee;

import org.springframework.stereotype.Controller;

@Controller
public class EmployeeController {

    EmployeeRepository userRepository;

    public EmployeeController(EmployeeRepository userRepository){
        this.userRepository = userRepository;
    }


}
