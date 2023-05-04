package com.VacationProject.VacationProjectFrontEnd.Controllers;

import com.VacationProject.VacationProjectFrontEnd.Persistance.EmployeeRepository;
import org.springframework.stereotype.Controller;

@Controller
public class EmployeeController {

    EmployeeRepository userRepository;

    public EmployeeController(EmployeeRepository userRepository){
        this.userRepository = userRepository;
    }


}
