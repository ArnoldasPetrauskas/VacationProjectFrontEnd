package com.VacationProject.VacationProjectFrontEnd.Organizer;

import com.VacationProject.VacationProjectFrontEnd.Vacation.Vacation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Organizer {

    @Id
    private Integer id;

    @NonNull
    private String name;


    private Set<Vacation> vacations;
}
