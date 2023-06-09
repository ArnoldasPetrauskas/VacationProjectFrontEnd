package com.VacationProject.VacationProjectFrontEnd.Controllers;

import com.VacationProject.VacationProjectFrontEnd.Employee.Employee;
import com.VacationProject.VacationProjectFrontEnd.Employee.EmployeeService;
import com.VacationProject.VacationProjectFrontEnd.Organizer.Organizers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class DashboardController {

    @Autowired
    RestTemplate restTemplate;
    private final EmployeeService employeeService;

    private final OrganizerController organizerController;

    private final VacationsController vacationsController;

    public DashboardController(
            EmployeeService employeeService,
            OrganizerController organizerController,
            VacationsController vacationsController
    ) {
        this.employeeService = employeeService;
        this.organizerController = organizerController;
        this.vacationsController = vacationsController;
    }

    @GetMapping("/admin/dashboard")
    public String dashboard(Model model){
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("organizers", retrieveOrganizersFromRest().getOrganizers());
        return "admin/dashboard";
    }

    private Organizers retrieveOrganizersFromRest(){
         return new ResponseEntity<>(restTemplate
                .getForEntity("http://localhost:8082/organizers", Organizers.class),
                 HttpStatus.OK).getBody().getBody();

    }

    @GetMapping("/admin/dashboard/newEmployee")
    public String newEmployeePage(Model model) {
        model.addAttribute("employee", new Employee());
        return "admin/newEmployee";
    }

    @PostMapping("/admin/dashboard/newEmployee")
    public String createNewEmployee(Employee employee, Model model) {
        try {
            employeeService.findByEmployeeName(employee.getEmployeeName());
        } catch (UsernameNotFoundException e){
            employeeService.saveAndEncodePassword(employee);
            return "redirect:/admin/dashboard";
        }
        model.addAttribute("error", "Username already taken");
        return "/admin/dashboard/newEmployee";
    }
}
