package com.VacationProject.VacationProjectFrontEnd.Controllers;

import com.VacationProject.VacationProjectFrontEnd.Employee.EmployeeService;
import com.VacationProject.VacationProjectFrontEnd.Organizer.Organizer;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

@Controller
public class OrganizerController {

    private final RestTemplate restTemplate;

    private final EmployeeService employeeService;

    public OrganizerController(
            RestTemplate restTemplate,
            EmployeeService employeeService
    ) {
        this.restTemplate = restTemplate;
        this.employeeService = employeeService;
    }

    @GetMapping("/vacations/organizer/{id}")
    public String singleOrganizer(@PathVariable int id, Model model){
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        ResponseEntity<Organizer> response = restTemplate
                .getForEntity("http://localhost:8082/organizers/organizer/" + id, Organizer.class);

        model.addAttribute("employeeId",
                employeeService.findByEmployeeName(currentUser).getId());
        model.addAttribute("organizer", response.getBody());

        return "organizer/organizer";
    }
}
