package com.VacationProject.VacationProjectFrontEnd.Vacation;

import com.VacationProject.VacationProjectFrontEnd.Employee.Employee;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Table(name = "vacations")
public class Vacation {

    @Id
    private Integer id;

    @NonNull
    private String title;

    @NonNull
    private String description;

    @NonNull
    private String country;

    @NonNull
    private String city;

    private LocalDate vacationStartDate;

    private LocalDate vacationEndDate;

    @NonNull
    private Double price;

    @NonNull
    private String organizer;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "vacation_employees",
            joinColumns = @JoinColumn(name = "vacation_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Employee> employees;
}
