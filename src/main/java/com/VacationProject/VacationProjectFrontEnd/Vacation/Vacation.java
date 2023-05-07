package com.VacationProject.VacationProjectFrontEnd.Vacation;

import com.VacationProject.VacationProjectFrontEnd.Employee.Employee;
import com.VacationProject.VacationProjectFrontEnd.Organizer.Organizer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@ToString
@Table(name = "vacations")
public class Vacation {

    public Vacation(
            String title,
            String description,
            String country,
            String city,
            LocalDate vacationStartDate,
            LocalDate vacationEndDate,
            Double price
    ) {
        this.title = title;
        this.description = description;
        this.country = country;
        this.city = city;
        this.vacationStartDate = vacationStartDate;
        this.vacationEndDate = vacationEndDate;
        this.price = price;
    }

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
    @Transient
    private Organizer organizer;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "vacation_employees",
            joinColumns = @JoinColumn(name = "vacation_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"))
    private Set<Employee> vacationEmployees;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getVacationStartDate() {
        return vacationStartDate;
    }

    public void setVacationStartDate(LocalDate vacationStartDate) {
        this.vacationStartDate = vacationStartDate;
    }

    public LocalDate getVacationEndDate() {
        return vacationEndDate;
    }

    public void setVacationEndDate(LocalDate vacationEndDate) {
        this.vacationEndDate = vacationEndDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }
    public void addEmployee(Employee employee) {
        vacationEmployees.add(employee);

    }

    public void removeEmployee(int id) {
        this.vacationEmployees
                .stream()
                .filter(
                        e -> e.getId() == id
                )
                .findFirst()
                .ifPresent(
                        employee -> this.vacationEmployees.remove(employee)
                );
    }

}
