package com.VacationProject.VacationProjectFrontEnd.Controllers;

import com.VacationProject.VacationProjectFrontEnd.Organizer.Organizer;
import com.VacationProject.VacationProjectFrontEnd.Vacation.Vacation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

@Controller
public class OrganizerController {
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/vacations/organizer/{id}")
    public String singleOrganizer(@PathVariable int id, Model model){
        ResponseEntity<Organizer> response = restTemplate
                .getForEntity("http://localhost:8082/organizers/organizer/" + id, Organizer.class);

        model.addAttribute("organizer", response.getBody());

        return "organizer/organizer";
    }
}
