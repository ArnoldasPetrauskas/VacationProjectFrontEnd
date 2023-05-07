package com.VacationProject.VacationProjectFrontEnd.Controllers;

import com.VacationProject.VacationProjectFrontEnd.Employee.EmployeeService;
import com.VacationProject.VacationProjectFrontEnd.Organizer.Organizers;
import com.VacationProject.VacationProjectFrontEnd.Vacation.Vacation;
import com.VacationProject.VacationProjectFrontEnd.Vacation.Vacations;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

@Controller
public class VacationsController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    RestTemplate restTemplate;

    private final EmployeeService employeeService;

    public VacationsController(
            EmployeeService employeeService
    ) {
        this.employeeService = employeeService;
    }

    @GetMapping("/vacations")
    public String index(Model model) {
        ResponseEntity<Organizers> response = restTemplate
                .getForEntity("http://localhost:8082/organizers", Organizers.class);

        model.addAttribute("organizers", response.getBody().getOrganizers());

        return "pages/index";
    }

    @GetMapping("/vacations/vacation/{id}")
    public String singleVacation(@PathVariable int id, Model model){
        ResponseEntity<Vacation> response = restTemplate
                .getForEntity("http://localhost:8082/vacations/vacation/" + id, Vacation.class);

        model.addAttribute("vacation", response.getBody());

        return "vacation/vacation";
    }
}
