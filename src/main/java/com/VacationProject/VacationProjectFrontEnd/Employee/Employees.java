package com.VacationProject.VacationProjectFrontEnd.Employee;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Employees {

    @NonNull
    private List<Employee> employees;
}
