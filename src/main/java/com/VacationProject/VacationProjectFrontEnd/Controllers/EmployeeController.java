package com.VacationProject.VacationProjectFrontEnd.Controllers;

import com.VacationProject.VacationProjectFrontEnd.Employee.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

@Controller
public class EmployeeController {

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

    @DeleteMapping("/employees/delete/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){
        employeeService.deleteById(employeeId);
        return "redirect:/admin/dashboard";
    }
}
