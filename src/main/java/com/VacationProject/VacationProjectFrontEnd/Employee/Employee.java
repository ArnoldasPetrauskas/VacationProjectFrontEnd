package com.VacationProject.VacationProjectFrontEnd.Employee;

import com.VacationProject.VacationProjectFrontEnd.Vacation.Vacation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @Column(nullable = false, unique = true, length = 30)
    private String employeeName;

    @NonNull
    @Column(nullable = false)
    private String password;

    @NonNull
    @Column(nullable = false)
    private String role = "EMPLOYEE";

    @ManyToMany(mappedBy = "vacationEmployees")
    private List<Vacation> vacations = new ArrayList<>();


    public Employee() {
    }

    public Employee(String employeeName, String password) {
        this.employeeName = employeeName;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Vacation> getVacations() {
        return vacations;
    }

    public void addVacation(Vacation vacation) {
        this.vacations.add(vacation);
        vacation.addEmployee(this);
    }
}
