package com.VacationProject.VacationProjectFrontEnd.Controllers;

import com.VacationProject.VacationProjectFrontEnd.Employee.Employee;
import com.VacationProject.VacationProjectFrontEnd.Employee.EmployeeService;
import com.VacationProject.VacationProjectFrontEnd.Employee.Employees;
import com.VacationProject.VacationProjectFrontEnd.Vacation.Vacation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
public class EmployeeController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final RestTemplate restTemplate;
    public final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService,
                              RestTemplate restTemplate
    ) {
        this.employeeService = employeeService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/employee/home/{employeeId}")
    public String getEmployeeHomeWithId(@PathVariable int employeeId, Model model) {
        model.addAttribute("employee", employeeService.findById(employeeId));
        return "employee/home";
    }

    @GetMapping("/employee/home")
    public String getEmployeeHome(Employee employee, Model model) {
        model.addAttribute("employee", employee);
        return "employee/home";
    }

    @DeleteMapping("/employees/delete/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {
        employeeService.deleteById(employeeId);
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/employee/{employeeId}/addVacation/{vacationId}")
    public String addVacationToEmployee(
            @PathVariable int employeeId,
            @PathVariable int vacationId) {

        ResponseEntity<Vacation> response = restTemplate
                .getForEntity(
                        "http://localhost:8082/vacations/vacation/" + vacationId, Vacation.class);
        logger.info("" + response.getBody());
        employeeService.addVacationToEmployee(
                employeeId,
                response.getBody()
        );
        logger.info("here " + employeeService.findById(employeeId));
        restTemplate.put("http://localhost:8082/vacations/vacation/",
                response.getBody(), vacationId);
        return "redirect:/employee/home";
    }
}
